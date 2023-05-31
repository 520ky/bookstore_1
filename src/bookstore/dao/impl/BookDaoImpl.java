package bookstore.dao.impl;

import bookstore.bean.Bookbean;
import bookstore.dao.BaseDao;
import bookstore.dao.BookDao;
import bookstore.utils.SetConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    /**
     * 添加图书
     * @param book
     * @return 返回-1添加失败，返回1位添加成功
     */
    @Override
    public void addBook(Bookbean book) {

        Connection conn = SetConn.Conn();

        String sql = "INSERT INTO t_book(NAME,author,price,sales,stock,img_path) VALUES(?,?,?,?,?,?)";
        try {
            updateAll(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void updateBook(Bookbean book) {
        Connection conn = SetConn.Conn();

        String sql = "UPDATE t_book SET NAME=?,author=?,price=?," +
                "sales=?,stock=?,img_path=? WHERE id=?";
        try {
            updateAll(conn,sql,book.getName(), book.getAuthor(),book.getPrice(),
                    book.getSales(),book.getStock(),book.getImgPath(),book.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    /**
     * 删除图书
     * @param id
     */
    @Override
    public void deleteBook(int id) {
        Connection conn = SetConn.Conn();

        String sql = "DELETE FROM t_book WHERE id=?";
        try {
            updateAll(conn,sql,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 通过id查询图书
     * @param id
     * @return 返回BookBean类型
     */
    @Override
    public Bookbean findBook(int id) {
        Connection conn = SetConn.Conn();
        String sql = "SELECT id,NAME,author,price,sales," +
                "stock,img_path AS imgPath FROM t_book WHERE id =?";
        Bookbean book = null;
        try {
            book = selectAll(conn, Bookbean.class, sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

        return book;
    }

    /**
     * 查询全部图书信息
     * @return list图书集合
     */
    @Override
    public List<Bookbean> findAll() {
        Connection conn = SetConn.Conn();
        String sql = "SELECT id,NAME,author,price,sales,stock,img_path FROM t_book";
        List<Bookbean> bookbeans = null;
        try {
            bookbeans = selectListall(conn, Bookbean.class, sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bookbeans;
    }

    @Override
    public Object repeat(Bookbean book) {
        Connection conn = SetConn.Conn();
        String sql = "SELECT NAME ,author FROM t_book WHERE NAME=? AND author=?";
        //null--书不存在
        try {
            return selecScan(conn, sql, book.getName(), book.getAuthor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
