package bookstore.test;

import bookstore.bean.Page;
import bookstore.service.Pageservice;
import bookstore.service.impl.PageserviceImpl;
import org.junit.jupiter.api.Test;


public class PageserviceImplTest {
    Pageservice pageservice = new PageserviceImpl();
    @Test
    public void getpage() {
        Page getpage = pageservice.getpage(1, 4);
        System.out.println(getpage);
    }
}