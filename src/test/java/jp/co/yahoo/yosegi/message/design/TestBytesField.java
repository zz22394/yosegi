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
package jp.co.yahoo.yosegi.message.design;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestBytesField {

  @Test
  public void createNewInstanceFromFieldName() {
    IField field = new BytesField( "test" );
  }

  @Test
  public void createNewInstanceFromFieldNameAndProperties() {
    Properties prop = new Properties();
    prop.set( "key1" , "value1" );
    IField field = new BytesField( "test" , prop );
  }

  @Test
  public void getName() {
    IField field = new BytesField( "test" );
    assertEquals( field.getName() , "test" );
  }

  @Test
  public void getProperties() {
    Properties prop = new Properties();
    prop.set( "key1" , "value1" );
    IField field = new BytesField( "test" , prop );
    Properties prop2 = field.getProperties();
    assertEquals( prop2.get( "key1" ) , "value1" );
  }

  @Test
  public void getFieldType() {
    IField field = new BytesField( "test" );
    assertEquals( FieldType.BYTES , field.getFieldType() );
  }

}
