package bookstore.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CopyBean {
//    public static void copyBean(Object bean, Map value){
//        try {
//            BeanUtils.populate(bean,value);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static <T> T copyBean(T bean,Map value){
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转化为整型
     * @param strint
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strint,int defaultValue){
        try {
            return Integer.parseInt(strint);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

}
