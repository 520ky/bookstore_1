package bookstore.dao.impl;

import bookstore.bean.Cart;
import bookstore.bean.CartItem;
import bookstore.bean.Order_items;
import bookstore.dao.BaseDao;
import bookstore.dao.Order_itemDao;
import bookstore.utils.SetConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Order_itemDaoImpl extends BaseDao implements Order_itemDao {
    @Override
    public List<Order_items> queryOrderItemsById(String orderId) {
        Connection conn = SetConn.Conn();
        String sql =  "SELECT `id`,`name`,`count`,`price`,`total_price`AS totalPrice ,`order_id` AS orderId ,`url`  " +
                "FROM t_order_item WHERE order_id=?";
        List<Order_items> order_items = null;
        try {
            order_items = selectListall(conn, Order_items.class, sql, orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

        return order_items;
    }

    @Override
    public void saveOrderItems(String orderId, Cart cart) {
        Connection conn = SetConn.Conn();
        String sql = "INSERT INTO t_order_item(`id`,`name`,`count`,`price`,`total_price`,`order_id`,`url`)" +
                "VALUES(?,?,?,?,?,?,?)";

        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> item :items.entrySet()) {
            CartItem cartItem = item.getValue();
            try {
                updateAll(conn,sql,cartItem.getId(),cartItem.getName(),cartItem.getCount(),
                        cartItem.getPrice(),cartItem.getPriceAll(),orderId,cartItem.getImgeUrl());
            } catch (Exception e) {
                throw new RuntimeException(e);

            }
        }

    }

    @Override
    public void deleteOrder(String orderId) {
        Connection conn = SetConn.Conn();
        String sql = "DELETE FROM t_order_item WHERE order_id=?";
        try {
            updateAll(conn, sql, orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
