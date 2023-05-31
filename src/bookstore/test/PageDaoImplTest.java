package bookstore.test;

import bookstore.bean.Bookbean;
import bookstore.dao.PageDao;
import bookstore.dao.impl.PageDaoImpl;
import bookstore.service.Pageservice;
import bookstore.service.impl.PageserviceImpl;
import bookstore.utils.MD5Utils;

import org.junit.jupiter.api.Test;

import java.util.List;


public class PageDaoImplTest {
    PageDao pageDao = new PageDaoImpl();


  @Test
    public void queryForPageTotalCount() {
        Integer integer = pageDao.queryForPageTotalCount();
        System.out.println(integer);
    }

    @Test
    public void queryForPageItems() {
        List<Bookbean> bookbeans = pageDao.queryForPageItems(0, 4);
        for (Bookbean book : bookbeans) {
            System.out.println(book);
        }
    }
}