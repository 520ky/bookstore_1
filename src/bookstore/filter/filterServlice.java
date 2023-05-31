package bookstore.filter;

import bookstore.utils.SetConn;

import javax.servlet.*;
import java.io.IOException;

public class filterServlice implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);

        } catch (IOException e) {
            //出现异常回滚
            SetConn.rollBack();
        }

    }

    @Override
    public void destroy() {

    }
}
