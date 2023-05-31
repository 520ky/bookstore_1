package bookstore.test;

import bookstore.utils.MD5Utils;
import bookstore.utils.RandomString;

import java.util.Arrays;


class MD5UtilsTest {

    @org.junit.jupiter.api.Test
    void md5() {
        RandomString randomString = new RandomString();
        String string = randomString.getString(6);
        System.out.println(string);
        String s = string + "123456";
        String lhc = MD5Utils.md5(s);
        System.out.println(lhc);
    }
}