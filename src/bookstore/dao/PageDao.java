package bookstore.dao;

import bookstore.bean.Bookbean;
import bookstore.bean.Page;

import java.awt.print.Book;
import java.sql.Connection;
import java.util.List;

public interface PageDao {
    /**
     * 查询总记录数
     * @return
     */
    Integer queryForPageTotalCount();

    /**
     * 查询所有在一定范围的记录数
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min,int max);

    /**
     * 查询每页的数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Bookbean> queryForPageItems(int begin, int pageSize);

    List<Bookbean> queryforPrice(int begin,int pageSize ,int min,int max);

    Integer queryForPageTotalCountByName(String goodsName);

    List<Bookbean> queryforName(int begin, int pageSize, String goodsName);
}
