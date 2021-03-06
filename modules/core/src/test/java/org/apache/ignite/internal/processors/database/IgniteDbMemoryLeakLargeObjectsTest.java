/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.database;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.internal.IgniteEx;

/**
 *
 */
public class IgniteDbMemoryLeakLargeObjectsTest extends IgniteDbMemoryLeakAbstractTest {
    /** */
    private final static int[] ARRAY;

    static {
        ARRAY = new int[1024];

        for (int i = 0; i < ARRAY.length; i++)
            ARRAY[i] = nextInt();
    }

    /** {@inheritDoc} */
    @Override protected IgniteCache<Object, Object> cache(IgniteEx ig) {
        return ig.cache("large");
    }

    /** {@inheritDoc} */
    @Override protected Object key() {
        return new LargeDbKey(nextInt(10_000), 1024);
    }

    /** {@inheritDoc} */
    @Override protected Object value(Object key) {
        return new LargeDbValue("test-value-1-" + nextInt(200), "test-value-2-" + nextInt(200), ARRAY);
    }

    /** {@inheritDoc} */
    @Override protected long pagesMax() {
        return 35_000;
    }
}
