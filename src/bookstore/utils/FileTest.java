package bookstore.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class FileTest {
    // 允许上传的文件类型
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg",".webp", ".png", ".pdf","gif");
    // 允许上传的文件最大大小
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

    public boolean testType(String fileName){
        String fileEnd = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (ALLOWED_EXTENSIONS.contains(fileEnd)){
            return true;
        }
        return false;

    }
    public boolean testFileSize(long fileSize){
        if (fileSize<=MAX_FILE_SIZE){
            return true;
        }
        return false;
    }

    public boolean testContext(InputStream inputStream){
        try {
            BufferedImage image =  ImageIO.read(inputStream);
            if (image==null){
                return false;
            }
            int height = image.getHeight();
            if (height!=0){
                return true;
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        return false;
    }
}
