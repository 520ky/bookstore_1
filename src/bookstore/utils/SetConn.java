package bookstore.utils;



import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.util.JdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SetConn {

    private static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
    private static DruidDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection Conn()  {

        Connection conn = connection.get();
        if (conn==null){
            //获取连接失败，从连接池中获取连接
            try {
                conn = dataSource.getConnection();
                conn.setAutoCommit(true);
                connection.set(conn);//设置到Threadlocal中让后面使用
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return conn;
    }

    public static void commit() {
        Connection conn = connection.get();
        if (conn!=null){  //如果不为空有线程有连接
            try {
                conn.commit();  //提交事务
                conn.setAutoCommit(true); //恢复自动提交
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
        connection.remove();
    }
    public static void rollBack(){
        Connection conn = connection.get(); //获取当前线程
        try {
            conn.rollback();  //回滚事务
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();  //关闭连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        connection.remove();
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
//    public static void stop(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
