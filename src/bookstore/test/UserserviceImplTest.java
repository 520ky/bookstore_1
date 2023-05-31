package bookstore.test;

import bookstore.bean.Userbean;
import bookstore.service.Userservice;
import bookstore.service.impl.UserserviceImpl;
import org.junit.jupiter.api.Test;


public class UserserviceImplTest {
    Userbean user = new Userbean("admin","admin","4444@qq.com");
    Userservice userservice = new UserserviceImpl();
    @Test
    public void registerUser() {
        boolean b = userservice.registerUser(user);
        System.out.println(b);
    }

    @Test
    public void loginUser() {
        Userbean userbean = userservice.loginUser(user);
        System.out.println(userbean);
    }

    @Test
    public void checkUser() {
    }
}