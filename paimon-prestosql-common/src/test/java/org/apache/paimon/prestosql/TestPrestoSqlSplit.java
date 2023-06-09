/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.prestosql;

import io.airlift.json.JsonCodec;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/** Test for {@link PrestoSqlSplit}. */
public class TestPrestoSqlSplit {

    private final JsonCodec<PrestoSqlSplit> codec = JsonCodec.jsonCodec(PrestoSqlSplit.class);

    @Test
    public void testJsonRoundTrip() throws Exception {
        byte[] serializedTable = PrestoSqlTestUtils.getSerializedTable();
        PrestoSqlSplit expected = new PrestoSqlSplit(Arrays.toString(serializedTable));
        String json = codec.toJson(expected);
        PrestoSqlSplit actual = codec.fromJson(json);
        assertThat(actual.getSplitSerialized()).isEqualTo(expected.getSplitSerialized());
    }
}
