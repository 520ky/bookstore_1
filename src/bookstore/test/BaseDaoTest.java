package bookstore.test;

import bookstore.dao.BaseDao;
import bookstore.utils.SetConn;

import org.junit.jupiter.api.Test;

import java.sql.Connection;



public class BaseDaoTest extends BaseDao {

    @Test
    public void updateAll() {
        Connection conn = SetConn.Conn();
        String sql = "INSERT INTO t_user(username,PASSWORD,email) VALUES(?,?,?)";
        int i = updateAll(conn, sql, "12314222", "123456", "3333@qq.com");
        System.out.println(i);
    }

    @Test
    public void selectAll() {

    }

    @Test
    public void selectListall() {
    }

    @Test
    public void selecScan() {
    }
}