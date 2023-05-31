package bookstore.dao;

import bookstore.bean.Order;
import bookstore.bean.Order_manager;

import java.util.List;

public interface OrderDao {
    /**
     * 保存订单order
     * @param order
     */
    int saveOrder(Order order) throws Exception;

    /**
     * 通过用户id查询订单
     * @param userId
     * @return 返回订单order信息
     */
    List<Order> queryOrderById(int userId);

    /**
     * 通过订单号修改，订单状态0未发货，1已发货
     * @param orderId
     * @param status
     */
    void updateOrder(String orderId ,int status);

    /**
     * 查询所有订单信息
     * @return 订单list集合
     */
    List<Order_manager> queryAllOrder();

    /**
     * 通过orderID删除订单
     * @param orderId
     */
    void delete(String orderId);


}
