package bookstore.web;

import bookstore.service.Userservice;
import bookstore.service.impl.UserserviceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUser extends HttpServlet {

    private Userservice userservice = new UserserviceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String url = req.getParameter("url");
        userservice.updateUser(username,password,email,url);
        //销毁登录session域
        req.getSession().invalidate();
        //重定向重新登录
        resp.sendRedirect("pages/user/login.jsp");
    }
}
