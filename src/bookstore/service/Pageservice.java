package bookstore.service;

import bookstore.bean.Bookbean;
import bookstore.bean.Page;

public interface Pageservice {

    Page getpage(int pageNo, int pageSize);

    Page getByprice(int pageNo,int pageSize,int min,int max);

    Page getByName(int pageNo, int pageSize, String goodsName);
}
