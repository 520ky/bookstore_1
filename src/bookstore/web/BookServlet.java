package bookstore.web;

import bookstore.bean.Bookbean;
import bookstore.bean.Page;
import bookstore.service.Bookservice;
import bookstore.service.Pageservice;
import bookstore.service.impl.BookserviceImpl;
import bookstore.service.impl.PageserviceImpl;
import bookstore.utils.CopyBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    Pageservice pageservice = new PageserviceImpl();
    Bookservice bookservice = new BookserviceImpl();

    /**
     * 显示所有数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
//    public void listAll(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//       //查询数据库中所有的数据
//        List<Bookbean> all = bookservice.findAll();
//        request.setAttribute("allBook",all);
//        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
//
//    }

    /**
     * 添加一条数据
     * @param request
     * @param response
     */
    public void addBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //创建book对象
        Bookbean book = new Bookbean();
        CopyBean.copyBean(book,request.getParameterMap());
      /*  //查看图书是否存在
        Object repeat = bookservice.isRepeat(book);
        request.setAttribute("msg","");
        if (repeat==null){
            //想要添加的图书不存在可以添加

            //转发会出现刷新一直添加数据*/
//        request.getRequestDispatcher("/manager/bookServlet?action=listAll").forward(request,response);
            //用重定向
            bookservice.add(book);
        String pageNo = request.getParameter("pageNo");
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=apage&pageNo="+pageNo);

       /* }else {
            request.setAttribute("msg","该图书已经存在");
            request.getRequestDispatcher("../pages/manager/book_edit.jsp").forward(request,response);
        }*/

    }

    public void delete(HttpServletRequest request ,HttpServletResponse response) throws IOException {
       //获取要删除的id
        String id = request.getParameter("id");
        int i = Integer.parseInt(id);
        String pageNo = request.getParameter("pageNo");
        //删除操作
        bookservice.delet(i);
        //请求重定向
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=apage&pageNo="+pageNo);

    }

    //修改图书
    public void update(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Bookbean book = new Bookbean();
        CopyBean.copyBean(book,request.getParameterMap());
        System.out.println(book);
        bookservice.update(book);
        String pageNo = request.getParameter("pageNo");
        //重定向
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=apage&pageNo="+pageNo);
    }

    //得到bean
    public void getBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int y = Integer.parseInt(id);
        Bookbean book = bookservice.findById(y);
//        System.out.println("getBook=="+book);
        //获取修改对象，重定向到修改页面
        request.setAttribute("onebook",book);
        String pageNo = request.getParameter("pageNo");
        request.getRequestDispatcher("/pages/manager/book_edit.jsp?pageNo="+pageNo).forward(request,response);
    }

    public void apage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //获取当前页面pageNo
        String page = request.getParameter("pageNo");
        int pageNo = CopyBean.parseInt(page, 1);
        //获取每页大小（pagesize）
        String pageSize1 = request.getParameter("pageSize");
        int pageSize = CopyBean.parseInt(pageSize1, Page.PAGE_SIZE);
        //获取page对象
        Page bookPage = pageservice.getpage(pageNo, pageSize);
        request.setAttribute("page",bookPage);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }






}
