package bookstore.test;

import bookstore.utils.AliyunOSS;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class AliyunOSSTest {

    @Test
    void getAlyunOss() throws IOException {
        File file = new File("C:/Users/HC/Pictures/Saved Pictures/1.jpg");
        InputStream inputStream = new FileInputStream(file);
        String name = file.getName();
        System.out.println(name);
        System.out.println(inputStream);
//        new AliyunOSS().getAlyunOss(inputStream,name);
    }
}