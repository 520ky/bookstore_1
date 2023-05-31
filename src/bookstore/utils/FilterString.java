package bookstore.utils;

import com.alibaba.druid.sql.visitor.functions.Char;

public class FilterString { //封装过滤的特殊字符的方法

    public static Boolean filterString(String text){
        char[] string = {'$','#','’','%','^','&','?','(',')','<','>',
                '[',']','{','}','/',',',';',':','”'};

        for (int i=0;i<string.length;i++){

//            if (text.contains(c))
            //包含非法字符
            return false;
        }
        return true;

    }
}
