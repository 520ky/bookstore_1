package bookstore.service.impl;

import bookstore.bean.Bookbean;
import bookstore.bean.Page;
import bookstore.dao.PageDao;
import bookstore.dao.impl.PageDaoImpl;
import bookstore.service.Pageservice;

import java.util.List;

public class PageserviceImpl implements Pageservice {

    PageDao pageDao =  new PageDaoImpl();
    @Override
    public Page<Bookbean> getpage(int pageNo, int pageSize) {
        //分装page对象
        Page<Bookbean> page = new Page<>();
        //1、获取总记录数
        Integer integer = pageDao.queryForPageTotalCount();
        page.setPageTotalCount(integer);

        //2、获取页面大小（显示的数据条数）
        page.setPageSize(pageSize);
        //3、获取页面数（pageTotal）
        int pageTotal  = integer/pageSize;
        if (integer%pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //4、获取当前页号
        //判断输入的当前页面输入是否合法
        if (pageNo<1){
            pageNo = 1;
        } else if (pageNo>pageTotal) {
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);

        //5、获取每页的book对象集合
        int begin = (pageNo-1)*pageSize;
        List<Bookbean> bookbeans = pageDao.queryForPageItems(begin, pageSize);
        page.setItems(bookbeans);

        //获取路径
        return page;
    }

    @Override
    public Page getByprice(int pageNo, int pageSize, int min, int max) {

        Page<Bookbean> page = new Page<>();

        page.setPageSize(pageSize);
        Integer integer = pageDao.queryForPageTotalCountByPrice(min,max);
        int pageTotal = integer/pageSize;
        if (integer%pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        //防止用户随意输入页码
        if (pageNo<0){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }

        page.setPageNo(pageNo);

        page.setPageTotalCount(integer);
        int begin = (pageNo-1)*pageSize;
        List<Bookbean> bookbeans = pageDao.queryforPrice(begin, pageSize, min, max);
        page.setItems(bookbeans);
        return page;
    }

    @Override
    public Page getByName(int pageNo, int pageSize, String goodsName) {
        Page<Bookbean> page = new Page<>();
        page.setPageSize(pageSize);
        //商品总量
        Integer integer = pageDao.queryForPageTotalCountByName(goodsName);
        int pageTotal = integer/pageSize;
        if (integer%pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);

        page.setPageTotalCount(integer);

        int begin = (pageNo-1)*pageSize;
        List<Bookbean> bookbeans = pageDao.queryforName(begin, pageSize, goodsName);
        page.setItems(bookbeans);
        return page;
    }
}
