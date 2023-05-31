package bookstore.dao;

import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用dbutils
    private final QueryRunner queryRunner = new QueryRunner();
    //通用的增删改操作
    /**
     * update()
     * 返回-1为更新失败，返回1为更新成功
     */
    public int updateAll(Connection conn, String sql, Object ...args){
        //更新数据库操作
        try {
          return queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        //插入不成功return-1
    }
    /**
     * 返回一个javabean对象
     * 查询一条记录 id=?
     *return() 返回值为null为没有查到
     */
    public <T> T selectAll(Connection conn,Class<T> clazz,String sql,Object ...args){
        T query = null;
        try {
            query =  queryRunner.query(conn, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        return  query;
    }

    /**
     * 查询返回多条记录
     * return() 返回值为null则没有查询到
     */

    public <E> List<E> selectListall(Connection conn,Class<E> clazz ,String sql,Object ...args){
        List<E> query= null;
        try {
            query = queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);

        }
        return query;
    }

    /**
     * 执行返回一行或一列的数据
     */
    public Object selecScan(Connection conn, String sql,Object ...args){

        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);

        }

    }





}
