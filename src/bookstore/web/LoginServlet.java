package bookstore.web;

import bookstore.bean.Userbean;
import bookstore.service.Userservice;
import bookstore.service.impl.UserserviceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    Userservice userservice = new UserserviceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用户登录
        //1、获取用户登录的密码和账户
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Userbean user = new Userbean(username, password);
        //2、检验用户名密码是否正确
        Userbean userbean = userservice.loginUser(user);
        //如果userbean不为null则登录成功
        if (userbean!=null){
            //跳转到登录成功页面,转发
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("name",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }
    }
}
