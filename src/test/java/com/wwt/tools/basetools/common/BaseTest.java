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

import org.junit.Ignore;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author benw-at-wwt
 */
@Ignore
public class BaseTest {

    public static class TestElement implements Comparable<TestElement> {

        private int integer;

        public TestElement(int i) {
            integer = i;
        }

        @Override
        public int compareTo(TestElement o) {
            if(integer==o.getInteger()) return 0;
            return integer<o.getInteger()? -1:1;
        }

        private int getInteger() {return integer;}

        public void setInteger(int i) {
            integer = i;
        }

        @Override
        public String toString(){ return Integer.toString(integer);}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestElement that = (TestElement) o;
            return integer == that.integer;
        }

        @Override
        public int hashCode() {
            return Objects.hash( integer );
        }
    }

    /* ------- Generate test data ------------*/

    protected static TestElement[] createRandomTestElementArray(int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement((ThreadLocalRandom.current().nextInt(100)));
        }
        return returnValue;
    }

    protected static TestElement[] createAscTestElementArray(@SuppressWarnings("SameParameterValue") int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement(i);
        }
        return returnValue;
    }

    protected static TestElement[] createDescTestElementArray(int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement(size-i);
        }
        return returnValue;
    }

    protected static TestElement[] createConstantTestElementArray(int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement(1);
        }
        return returnValue;
    }
}
