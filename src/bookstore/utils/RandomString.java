package bookstore.utils;

public class RandomString {
    public  String getString(int length){
        //加盐处理，随机生成字符
        String no1 = "abcdefjhyjklmnopqrstuvwxyz";
        String number = "0123456789";
        String all = no1+number;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<length;i++){
            int at = (int) (Math.random()*36);
//            System.out.println(at);
            stringBuffer.append(all.charAt(at));
        }
        String randomString = stringBuffer.toString();

        return randomString;
    }
}
