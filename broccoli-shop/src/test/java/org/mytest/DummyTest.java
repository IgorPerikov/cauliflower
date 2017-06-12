package org.mytest;

import org.junit.Test;

public class DummyTest {

    @Test
    public void test() {
        int a = 1;
        a *= 10;
        assert a == 10;
    }

    @Test
    public void duplicatedTest() {
        int a = 1;
        a *= 10;
        assert a == 10;
    }

    @Test
    public void stupidTest() {
        assert 1 == 1;
        assert true == true;
    }
}
