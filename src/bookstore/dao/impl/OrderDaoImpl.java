package bookstore.dao.impl;

import bookstore.bean.Order;
import bookstore.bean.Order_manager;
import bookstore.dao.BaseDao;
import bookstore.dao.OrderDao;
import bookstore.utils.SetConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        //获取数据库连接
        Connection conn = SetConn.Conn();
        //获取SQL语句
        String sql ="INSERT INTO t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)" +
                " VALUES(?,?,?,?,?)";
        int i = 0;
        try {
            i = updateAll(conn, sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(),
                    order.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return i;
    }

    @Override
    public List<Order> queryOrderById(int userId) {
        Connection conn = SetConn.Conn();
        String sql = "SELECT order_id AS orderId ,create_time AS createTime" +
                ",price,`status`,user_id AS userId FROM t_order WHERE user_id =? order by `create_time` ";
        List<Order> orders = null;
        try {
            orders = selectListall(conn, Order.class, sql, userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

        return orders;
    }

    @Override
    public void updateOrder(String orderId, int status) {
        Connection conn = SetConn.Conn();
        String sql = "UPDATE  t_order SET `status`=? WHERE order_id =?";
        try {
            updateAll(conn, sql, status, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    @Override
    public List<Order_manager> queryAllOrder() {
        Connection conn = SetConn.Conn();
        String sql = "SELECT order_id AS orderId ,create_time AS createTime,price,`status`,user_id AS userId ,username\n" +
                "FROM t_order o,t_user  u\n" +
                "WHERE o.user_id = u.id";
        List<Order_manager> orders = null;
        try {
            orders = selectListall(conn, Order_manager.class, sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

        return orders;
    }

    @Override
    public void delete(String orderId) {
        Connection conn = SetConn.Conn();

        String sql = "DELETE FROM t_order WHERE order_id =?";
        try {
            updateAll(conn, sql, orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
