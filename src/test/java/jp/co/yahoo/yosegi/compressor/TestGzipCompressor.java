/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.yahoo.yosegi.compressor;

import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestGzipCompressor {

  @Test
  public void T_1() throws IOException{
    CompressResult cr = new CompressResult( CompressionPolicy.BEST_SPEED , (double)100.0 );
    int intCount = 1024*100;
    byte[] t = new byte[Integer.BYTES*intCount];
    ByteBuffer buf = ByteBuffer.wrap( t );
    for( int i = 0 ; i < intCount ; i++ ){
      buf.putInt( 1 );
    }
    GzipCompressor compressor = new GzipCompressor();
    assertEquals( cr.getCurrentLevel() , 0 );
    compressor.compress( t , 0 , t.length , cr );
    assertEquals( cr.getCurrentLevel() , 1 );
    compressor.compress( t , 0 , t.length , cr );
    assertEquals( cr.getCurrentLevel() , 0 );
     
  }

}
