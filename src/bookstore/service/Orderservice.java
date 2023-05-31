package bookstore.service;

import bookstore.bean.Cart;
import bookstore.bean.Order;
import bookstore.bean.Order_items;
import bookstore.bean.Order_manager;

import java.util.List;

public interface Orderservice {
    /**
     * 创造订单，在数据库中保存订单，和订单项
     * @param userId
     * @param cart
     */
     String createOrder(int userId, Cart cart);

    /**
     * 查询订单项的详细信息
     * @param orderId
     * @return
     */
     List<Order_items> queryOrder_items(String orderId);

    /**
     * 通过用户id查找订单信息
     * @param userId
     * @return
     */
     List<Order> queryOrderByUserId(int userId);

    /**
     * 通过orderID删除订单项和订单
     * @param orderId
     */
     void deleteOrderItem(String orderId);

    /**
     * 订单管理，查询所有订单
     * @return
     */
     List<Order_manager> queryOrder_manager();

}
