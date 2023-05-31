package bookstore.service.impl;

import bookstore.bean.*;
import bookstore.dao.BaseDao;
import bookstore.dao.BookDao;
import bookstore.dao.OrderDao;
import bookstore.dao.Order_itemDao;
import bookstore.dao.impl.BookDaoImpl;
import bookstore.dao.impl.OrderDaoImpl;
import bookstore.dao.impl.Order_itemDaoImpl;
import bookstore.service.Orderservice;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderserviceImpl implements Orderservice {
    OrderDao orderDao = new OrderDaoImpl();
    Order_itemDao order_itemDao = new Order_itemDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(int userId, Cart cart) {
        //创建一个订单对象
        Order order = new Order();
        //获取一个订单号，用时间戳加用户id保证唯一性,UUID获取
        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid);
        String orderId = uuid.toString().replace("-", "");
//        System.out.println(orderId);
        //为order对象赋值
        order.setOrderId(orderId);
        order.setPrice(cart.getAllPrice());
        order.setStatus(0);
        order.setUserId(userId);
        //获取当前时间
        Date date = new Date();
        order.setCreateTime(date);
        try {
            orderDao.saveOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建订单的同时也要创建订单项记录订单的详情
        order_itemDao.saveOrderItems(orderId,cart);
        //更新图书的库存和销量
        for (Map.Entry<Integer, CartItem> item:cart.getItems().entrySet()) {

            Bookbean book = bookDao.findBook(item.getValue().getId());
            book.setSales(book.getSales()+item.getValue().getCount());
            book.setStock(book.getStock()-item.getValue().getCount());
            //更新图书
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();
        //返回订单号
        return orderId;
    }

    //查询订单项详细信息
    @Override
    public List<Order_items> queryOrder_items(String orderId) {
        List<Order_items> order_items = order_itemDao.queryOrderItemsById(orderId);
        return order_items;
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        List<Order> orders = orderDao.queryOrderById(userId);
        return orders;
    }

    @Override
    public void deleteOrderItem(String orderId) {
        order_itemDao.deleteOrder(orderId);
        orderDao.delete(orderId);
    }

    @Override
    public List<Order_manager> queryOrder_manager() {
        List<Order_manager> orders = orderDao.queryAllOrder();
        return orders;
    }
}
