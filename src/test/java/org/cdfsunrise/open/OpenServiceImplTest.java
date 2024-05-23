package org.cdfsunrise.open;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenServiceImplTest {

    @Test
    void test() {
        var svc = new OpenServiceImpl();
        String word = "hello world";
        var rt = svc.Test(word);
        Assertions.assertEquals(rt, word);
    }
}