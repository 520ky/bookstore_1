package bookstore.test;


import bookstore.bean.Userbean;
import bookstore.dao.Userdao;
import bookstore.dao.impl.UserdaoImpl;
import org.junit.jupiter.api.Test;


public class UserdaoImplTest {
    Userbean user = new Userbean(null,"12345","admin","4444@qq.com");
    Userdao userdao = new UserdaoImpl();
    @Test
    public void userAble() {
        boolean b = userdao.userAble(user);
        System.out.println(b);
    }

    @Test
    public void userlogin() {
        Userbean userlogin = userdao.userlogin(user);
        System.out.println(userlogin);
    }

    @Test
    public void userinsert() {
        boolean userinsert = userdao.userinsert(user);
        System.out.println(userinsert);
    }
}