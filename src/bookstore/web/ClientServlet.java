package bookstore.web;

import bookstore.bean.Bookbean;
import bookstore.bean.Page;
import bookstore.service.Pageservice;
import bookstore.service.impl.PageserviceImpl;
import bookstore.utils.CopyBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientServlet extends BaseServlet{
    Pageservice pageservice = new PageserviceImpl();
    public void clientIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int min = CopyBean.parseInt(request.getParameter("min"), 0);
        int max = CopyBean.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        int pageNo = CopyBean.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = CopyBean.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page byprice = pageservice.getByprice(pageNo, pageSize, min, max);


        StringBuilder sb = new StringBuilder("clientServlet?action=clientIndex");
        //如果min不为空追加到sb
        if (request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        //如果max不为空追加到地址
        if (request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        //将追加后的地址添加到url

        byprice.setUrl(sb.toString());
        request.setAttribute("page",byprice);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
    public void selectGoods(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
        String goodsName = request.getParameter("goodsName");
        int pageNo = CopyBean.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = CopyBean.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        Page byName =  pageservice.getByName(pageNo,pageSize,goodsName);
        request.setAttribute("page",byName);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }
}
