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

package jp.co.yahoo.yosegi.message.formatter.text;

import jp.co.yahoo.yosegi.message.objects.PrimitiveObject;
import jp.co.yahoo.yosegi.message.parser.IParser;
import jp.co.yahoo.yosegi.util.ByteArrayData;

import java.io.IOException;

public class TextShortFormatter implements ITextFormatter {

  private byte[] convert( final short target ) throws IOException {
    return Short.valueOf( target ).toString().getBytes("UTF-8");
  }

  @Override
  public void write(final ByteArrayData buffer , final Object obj ) throws IOException {
    if ( obj instanceof Byte ) {
      byte target = ( (Byte) obj ).byteValue();
      buffer.append( convert( target ) );
    } else if ( obj instanceof Short ) {
      short target = ( (Short) obj ).shortValue();
      buffer.append( convert( target ) );
    } else if ( obj instanceof Integer ) {
      short target = ( (Integer) obj ).shortValue();
      buffer.append( convert( target ) );
    } else if ( obj instanceof Long ) {
      short target = ( (Long) obj ).shortValue();
      buffer.append( convert( target ) );
    } else if ( obj instanceof Float ) {
      short target = ( (Float) obj ).shortValue();
      buffer.append( convert( target ) );
    } else if ( obj instanceof Double ) {
      short target = ( (Double) obj ).shortValue();
      buffer.append( convert( target ) );
    } else if ( obj instanceof PrimitiveObject) {
      buffer.append( convert( ( (PrimitiveObject)obj ).getShort() ) );
    }
  }

  @Override
  public void writeParser(
      final ByteArrayData buffer ,
      final PrimitiveObject obj ,
      final IParser parser ) throws IOException {
    if ( obj != null ) {
      buffer.append( convert( ( (PrimitiveObject)obj ).getShort() ) );
    }
  }

}
