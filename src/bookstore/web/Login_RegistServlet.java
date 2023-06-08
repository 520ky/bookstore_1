package bookstore.web;

import bookstore.bean.AdminUser;
import bookstore.bean.Bookbean;
import bookstore.bean.Salt;
import bookstore.bean.Userbean;
import bookstore.service.Userservice;
import bookstore.service.impl.UserserviceImpl;
import bookstore.utils.AliyunOSS;
import bookstore.utils.CopyBean;
import bookstore.utils.MD5Utils;
import bookstore.utils.RandomString;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class Login_RegistServlet extends BaseServlet {
    Userservice userservice = new UserserviceImpl();

    public void testUsername(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");

        Userbean userbean = new Userbean(username);
        boolean flag = userservice.checkUser(userbean);
        Map<String ,Integer> map = new HashMap<>();
        if (flag){
            //用户名不可注册
            map.put("result",0);
        }else{
            //用户名可用
            map.put("result",1);
        }
        //map转换为json对象传送
        Gson gson = new Gson();
        String result = gson.toJson(map);
        response.getWriter().write(result); //相应到浏览器
    }
    protected  void logoff(HttpServletRequest request , HttpServletResponse response) throws IOException {
        //销毁用户登录的信息
        request.getSession().invalidate();
        //重定向到主页面
        response.sendRedirect(request.getContextPath());
    }
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取验证码session域
        String kaptcha = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //移除销毁验证码session域
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //获取请求参数
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userAdmin = request.getParameter("user");

        String password1 = null;
        try {
            Salt salt = userservice.getSalt(username);
            String salt1 = salt.getSalt();
            password1 = MD5Utils.md5(password + salt1);
        } catch (Exception e) {
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username",username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            throw new RuntimeException(e);
        }

        if (kaptcha!=null && kaptcha.equalsIgnoreCase(code)){
            //验证码正确
            Userbean user = new Userbean(username,password1,userAdmin);

            Userbean userbean = userservice.loginUser(user);

            if (userbean!=null){
                //登录成功
                userbean.setUserAdmin(userAdmin);
                request.getSession().setAttribute("user",userbean);
                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
            }else{
                request.setAttribute("msg","用户名或密码错误");
                request.setAttribute("username",username);
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            }
        }else{
            request.setAttribute("msg","验证码错误！");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }

    }
//注册
protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   //获取session域中的验证码
    String kapcha = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
    //删除销毁session域
    request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


    //获取用户填写的信息
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String code = request.getParameter("code");
    String url = request.getParameter("url");
    //MD5加盐对密码进行加密存储到数据库中
    //生成随机字符
    String string = new RandomString().getString(6);
    //把盐封装到对象中去
    Salt salt = new Salt(username,string);
    String md5Password = MD5Utils.md5(password+string);
    //封装到user对象
    Userbean user = new Userbean();
    user.setUrl(url);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(md5Password);
//    CopyBean.copyBean(user,request.getParameterMap());
    //转发请求
    request.setAttribute("username",username);
    request.setAttribute("email",email);
    request.setAttribute("password",password);
    //判断验证码是否正确
    if (kapcha!=null && kapcha.equalsIgnoreCase(code)){
        //验证码正确

        //判断用户名是否重复
        boolean b = userservice.checkUser(user);

        if (b){  //true表示用户存在
            request.setAttribute("msg","用户名已经被注册！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);

        }else{
            //用户名不存在,可以注册
            if (userservice.registerUser(user)){     //true注册成功
                userservice.saveSalt(salt);
                //注册成功
                //条转到注册成功页面
                request.getSession().setAttribute("user",user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);

            }else{
                //没有注册到数据库中
                request.setAttribute("msg","没有注册到数据库中");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);

            }
        }

    }else {
        //验证码错误
        request.setAttribute("msg","验证码错误");
        //请求转发
        request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
    }
}

public void adminLogin(HttpServletRequest request ,HttpServletResponse response){
    String username = request.getParameter("username");
    String password = request.getParameter("password");

}



   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //判断请求是注册请求还是登录请求
        String action = req.getParameter("action");
        *//*if ("login".equals(action)){
            login(req,resp);
        }else {
            regist(req,resp);
        }*//*
        try {
            Method method = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
