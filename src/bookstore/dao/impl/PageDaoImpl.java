package bookstore.dao.impl;

import bookstore.bean.Bookbean;
import bookstore.dao.BaseDao;
import bookstore.dao.PageDao;
import bookstore.utils.SetConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PageDaoImpl extends BaseDao implements PageDao {
    /**
     * 获取页面记录数
     * @return
     */
    @Override
    public Integer queryForPageTotalCount() {
        Connection conn = SetConn.Conn();
        String sql = "SELECT COUNT(*) FROM t_book";
        Number count = null;
        try {
            count = (Number)selecScan(conn, sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        int i = count.intValue();
        return i;
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        Connection conn = SetConn.Conn();

        String sql = "SELECT COUNT(*) FROM t_book WHERE price BETWEEN ? AND ?";
        Number number = null;
        try {
            number = (Number) selecScan(conn, sql, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        int i = number.intValue();
        return i;
    }

    /**
     * 获取分页后当前页面数据
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Bookbean> queryForPageItems(int begin, int pageSize) {
        Connection conn = SetConn.Conn();
        String sql = "SELECT `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` AS imgPath FROM t_book LIMIT ?,?";
        List<Bookbean> bookbeans = null;
        try {
            bookbeans = selectListall(conn, Bookbean.class, sql, begin, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return bookbeans;
    }

    @Override
    public List<Bookbean> queryforPrice(int begin, int pageSize, int min, int max) {
        Connection conn = SetConn.Conn();

        String sql = "SELECT `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` AS imgPath FROM t_book " +

                " WHERE price BETWEEN ? AND ? " +

                "ORDER BY price ASC " +

                " LIMIT ?,?";
        List<Bookbean> bookbeans = null;
        try {
            bookbeans = selectListall(conn, Bookbean.class, sql, min, max, begin, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return bookbeans;
    }

    @Override
    public Integer queryForPageTotalCountByName(String goodsName) {
        Connection conn = SetConn.Conn();
        String sql = "select count(*) from t_book where name like ?";
        String goodsNames = "%"+goodsName +"%";
        Number number= (Number) selecScan(conn, sql, goodsNames);
        int i = number.intValue();
        return i;
    }

    @Override
    public List<Bookbean> queryforName(int begin, int pageSize, String goodsName) {
        Connection conn = SetConn.Conn();
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` AS imgPath from t_book where name like ?";
        String goodsNames = "%"+goodsName +"%";
        List<Bookbean> bookbeans = selectListall(conn, Bookbean.class, sql, goodsNames);

        return bookbeans;
    }
}
