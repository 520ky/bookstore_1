package bookstore.web;

import bookstore.bean.*;
import bookstore.service.Orderservice;
import bookstore.service.Userservice;
import bookstore.service.impl.OrderserviceImpl;
import bookstore.service.impl.UserserviceImpl;
import bookstore.utils.CopyBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    Orderservice orderservice = new OrderserviceImpl();
   public void createOrder(HttpServletRequest request ,HttpServletResponse response) throws IOException {
       //获取用户session
       Userbean user = (Userbean) request.getSession().getAttribute("user");

       //判断是否已经登录
       if (user==null){
           //没有登录,跳转到登录页面
           request.getRequestDispatcher("/pages/user/login.jsp");
           return ;
       }
       //获取购物车cart的session域
       Cart cart = (Cart) request.getSession().getAttribute("cart");
       //创建订单并返回订单号
       String orderId = orderservice.createOrder(user.getId(), cart);
       request.getSession().setAttribute("orderId",orderId);

       //重定向到订单成功页面
       response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
   }

    /**
     * 查询订单信息
     * @param request
     * @param response
     */
   public void queryOrder(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
       //获取用户id
       Userbean user = (Userbean) request.getSession().getAttribute("user");
       //判断用户是否为空
       if (user==null){
           //重定向到登录页面
           response.sendRedirect(request.getContextPath()+"/pages/user/login.jsp");
           return;
       }
       List<Order> orders = orderservice.queryOrderByUserId(user.getId());
       request.getSession().setAttribute("orders",orders);

//       response.sendRedirect(request.getContextPath()+"/pages/order/order.jsp");
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
   }

   public void queryOrderItemByOrderId(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       //获取orderId
       String orderId = request.getParameter("orderId");
       List<Order_items> order_items = orderservice.queryOrder_items(orderId);
       //将商品项order_items存放到request域中
       request.setAttribute("order_item",order_items);
       //转发到商品详情页面
       request.getRequestDispatcher("/pages/order/order_detail.jsp").forward(request,response);

   }

   public void deleteOrder(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
       //获取订单号

       String orderId = request.getParameter("orderId");
       orderservice.deleteOrderItem(orderId);
       //转发向到订单页面
       request.getRequestDispatcher("/orderServlet?action=queryOrder").forward(request,response);
//       response.sendRedirect(request.getContextPath()+"/pages/order/order.jsp");

   }

    /**
     * 订单管理查询所有订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
   public void order_manager(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
       List<Order_manager> order_managers = orderservice.queryOrder_manager();
       request.setAttribute("order_manager",order_managers);
       request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
   }
}
