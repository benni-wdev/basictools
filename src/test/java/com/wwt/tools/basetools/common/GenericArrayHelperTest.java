/* Copyright 2018-2019 Wehe Web Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wwt.tools.basetools.common;

import com.wwt.tools.basetools.sort.BaseSortingTest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author benw-at-wwt
 */
public class GenericArrayHelperTest {


    @Test
    public void swapTest() {
        BaseSortingTest.TestElement[] arr = new BaseSortingTest.TestElement[2];
        arr[1] = new BaseSortingTest.TestElement(1);
        arr[0] = new BaseSortingTest.TestElement(0);
        GenericArrayHelper.swap(arr,0,1);
        assertEquals("1",arr[0].toString());
        assertEquals("0",arr[1].toString());
    }

    @Test
    public void guaranteeCapacityTest() {
        int initialCapacity = 10;
        Object [] arr = BaseTest.createConstantTestElementArray( initialCapacity );
        int lengthTest = 30;
        arr = GenericArrayHelper.guaranteeCapacity( arr,lengthTest );
        assertEquals( lengthTest,arr.length );
        BaseTest.TestElement constant = new BaseTest.TestElement( 1 );
        for(int i = 0; i < arr.length; i++) {
            if(i < initialCapacity) {
                assertEquals(constant,arr[i]);
            }
            else {
                assertNull(arr[i]);
            }

        }
        int newLengthTest = 50;
        arr = GenericArrayHelper.guaranteeCapacity( arr,newLengthTest );
        assertEquals( lengthTest*2,arr.length );
        for(int i = 0; i < arr.length; i++) {
            if(i < initialCapacity) {
                assertEquals(constant,arr[i]);
            }
            else {
                assertNull(arr[i]);
            }

        }
        Object [] arrSave = arr;
        arr = GenericArrayHelper.guaranteeCapacity( arr,newLengthTest );
        assertArrayEquals( arrSave,arr );
        arr = GenericArrayHelper.guaranteeCapacity( arr,lengthTest*2 );
        assertArrayEquals( arrSave,arr );
    }
}
