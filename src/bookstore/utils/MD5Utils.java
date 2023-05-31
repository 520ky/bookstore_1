package bookstore.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5(String text){


        byte[] md5String = null;
        try {
           md5String =  MessageDigest.getInstance("md5").digest(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //转换为16进制
        String s = new BigInteger(1, md5String).toString(16);
        //如果没有满足32位加0
        for (int i =0 ;i<32-s.length();i++){
            s += "0";
        }
        return s;
    }
}
