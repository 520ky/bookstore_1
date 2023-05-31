package bookstore.web;

import bookstore.bean.Bookbean;
import bookstore.bean.Cart;
import bookstore.bean.CartItem;
import bookstore.service.Bookservice;
import bookstore.service.impl.BookserviceImpl;
import bookstore.utils.CopyBean;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    Bookservice bookservice = new BookserviceImpl();
    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //获取id
        int id = CopyBean.parseInt(request.getParameter("id"), 0);
        //通过id获取book对象
        Bookbean bookById = bookservice.findById(id);
        //封装到购物车cartItem模块中
        CartItem cartItem = new CartItem(bookById.getId(), bookById.getName(), 1, bookById.getPrice(), bookById.getPrice(),bookById.getImgPath());
        //获取session域，如果没有session域就是第一次添加创建新的session域
        Cart cartSession = (Cart) request.getSession().getAttribute("cart");
        if (cartSession==null){
            cartSession = new Cart();
            request.getSession().setAttribute("cart",cartSession);
        }
        //添加到购物车cart中的map中
        cartSession.addItem(cartItem);

        //购物车中回显加入购物车名称,设置ajax
        Map<String,Object> map = new HashMap<>();
        map.put("lastname", cartItem.getName());
        map.put("allcount",cartSession.getAllCount());
        //把map转换为json
        Gson gson = new Gson();
        String name = gson.toJson(map);
        response.getWriter().write(name);
      /*  //获取发送过来请求地址
        String referer = request.getHeader("Referer");
//        System.out.println(referer);
        //重定向到原来发来的地址
        response.sendRedirect(referer);*/
    }

    /**
     *删除商品
     */
    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取删除商品id
        int id = CopyBean.parseInt(request.getParameter("id"), 0);
        //获取session域
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.delete(id);
        }
        //获取地址
        response.sendRedirect(request.getHeader("Referer"));
    }
    /**
     * 清空购物车
     */
    public void clear(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session域
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
//            request.getSession().invalidate();
        }
        //获取地址
        String referer = request.getHeader("Referer");
        //重定向
        response.sendRedirect(referer);
    }
    /**
     * 更新表单数量
     */
    public void update(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        //获取id,和session域
        int id = CopyBean.parseInt(request.getParameter("id"), 0);
        int count = CopyBean.parseInt(request.getParameter("count"), 1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
            BigDecimal bigDecimal = cart.changeCount(id, count);

            Integer allCount = cart.getAllCount();
            BigDecimal cartAllPrice = cart.getAllPrice();
            Map<String, Object> map = new HashMap<>();

            map.put("priceAll", bigDecimal);
            map.put("allCount", allCount);
            map.put("cartAllPrice", cartAllPrice);
            Gson gson = new Gson();
            String result = gson.toJson(map);
            response.getWriter().write(result);

        }
//        //获取地址重定向
//        response.sendRedirect(request.getHeader("Referer"));
    }
}
