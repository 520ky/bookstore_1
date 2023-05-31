package bookstore.dao;

import bookstore.bean.Cart;
import bookstore.bean.CartItem;
import bookstore.bean.Order_items;

import java.util.List;

public interface Order_itemDao {
    /**
     * 根据订单编号查找商品项Order_items
     * @param orderId
     * @return 返回商品项order_items集合
     */
    List<Order_items> queryOrderItemsById(String orderId);

    /**
     * 保存订单项order_items
     * @param orderId
     * @param cart
     */
    void saveOrderItems(String orderId, Cart cart);

    /**
     * 删除订单项
     * @param orderId
     */
    void deleteOrder(String orderId);


}
