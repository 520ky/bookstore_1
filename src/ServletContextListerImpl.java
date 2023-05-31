import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextLister被创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextLister被销毁了");
    }
}
