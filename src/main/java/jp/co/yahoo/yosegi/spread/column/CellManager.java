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

package jp.co.yahoo.yosegi.spread.column;

import jp.co.yahoo.yosegi.inmemory.IMemoryAllocator;
import jp.co.yahoo.yosegi.message.objects.PrimitiveObject;
import jp.co.yahoo.yosegi.spread.column.filter.IFilter;
import jp.co.yahoo.yosegi.spread.column.index.DefaultCellIndex;
import jp.co.yahoo.yosegi.spread.column.index.ICellIndex;
import jp.co.yahoo.yosegi.spread.expression.IExpressionIndex;
import jp.co.yahoo.yosegi.util.IndexAndObject;
import jp.co.yahoo.yosegi.util.RangeBinarySearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CellManager implements ICellManager<ICell> {

  private final RangeBinarySearch<ICell> rangeBinarySearch =
      new RangeBinarySearch<ICell>();
  private ICellIndex index = new DefaultCellIndex();

  @Override
  public void add( final ICell cell , final int index ) {
    rangeBinarySearch.add( cell , index );
  }

  @Override
  public ICell get( final int index , final ICell defaultCell ) {
    ICell cell = rangeBinarySearch.get( index );
    if ( cell == null ) {
      return defaultCell;
    }
    return cell;
  }

  @Override
  public int size() {
    return rangeBinarySearch.size();
  }

  @Override
  public void clear() {
    rangeBinarySearch.clear();
  }

  @Override
  public void setIndex( final ICellIndex index ) {
    this.index = index;
  }

  @Override
  public boolean[] filter( final IFilter filter , final boolean[] filterArray ) throws IOException {
    switch ( filter.getFilterType() ) {
      case NOT_NULL:
        for ( IndexAndObject index : rangeBinarySearch.getIndexAndObjectList() ) {
          int startIndex = index.getStartIndex();
          for ( int i = startIndex ; i < ( startIndex + index.size() ) ; i++ ) {
            filterArray[i] = true;
          }
        }
        return filterArray;
      case NULL:
        return null;
      default:
        return index.filter( filter , filterArray );
    }
  }

  @Override
  public PrimitiveObject[] getPrimitiveObjectArray(
      final IExpressionIndex indexList , final int start , final int length ) {
    PrimitiveObject[] result = new PrimitiveObject[length];
    for ( int i = 0,index = start ; i < length && index < indexList.size() ; i++,index++ ) {
      Object obj = get( indexList.get( index ) , NullCell.getInstance() ).getRow();
      if ( obj instanceof PrimitiveObject ) {
        result[i] = (PrimitiveObject)obj;
      }
    }
    return result;
  }

  @Override
  public void setPrimitiveObjectArray(
      final IExpressionIndex indexList ,
      final int start ,
      final int length ,
      final IMemoryAllocator allocator ) {
    int arrayIndex = 0;
    for ( int index = start ; arrayIndex < length && index < indexList.size() ;
        arrayIndex++,index++ ) {
      Object obj = get( indexList.get( index ) , NullCell.getInstance() ).getRow();
      try {
        if ( obj instanceof PrimitiveObject ) {
          allocator.setPrimitiveObject( arrayIndex , (PrimitiveObject)obj );
        } else {
          allocator.setNull( arrayIndex );
        }
      } catch ( IOException ex ) {
        throw new RuntimeException( ex );
      }
    }
    for ( ; arrayIndex < length ; arrayIndex++ ) {
      allocator.setNull( arrayIndex );
    }
  }

}
