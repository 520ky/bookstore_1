package bookstore.dao.impl;


import bookstore.bean.Salt;
import bookstore.bean.Userbean;
import bookstore.dao.BaseDao;
import bookstore.dao.Userdao;
import bookstore.utils.SetConn;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class UserdaoImpl extends BaseDao implements Userdao {

    /**
     * 查询用户是否存在
     * @param user
     * @return 返回TRUE为存在，返回FALSE为不存在
     */
    @Override
    public boolean userAble(Userbean user) {
        //获取连接
        Connection conn = SetConn.Conn();
        //获取SQL语句
        String sql = "SELECT username FROM t_user WHERE username =?";

        if ((selecScan(conn, sql, user.getUsername())) != null){
            return true;  //数据库中能查到用户，用户名存在
        }

        return false;      //用户名不存在
    }

    /**
     * 检查用户输入账户密码是否正确
     * @param user
     * @return
     */
    @Override
    public Userbean userlogin(Userbean user) {
        Userbean userbean = null;
        //连接数据库
        Connection conn = SetConn.Conn();
        if ("user".equals(user.getUserAdmin())){
            //SQL语句
            String sql = "SELECT id,username ,PASSWORD ,email,url FROM t_user WHERE username=? AND PASSWORD = ?";
            try {
                userbean = selectAll(conn, user.getClass(), sql, user.getUsername(), user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }
        }else {
            //SQL语句
            String sql = "SELECT id,username ,password ,email,url FROM t_admin WHERE username=? AND password = ?";
            try {
                userbean = selectAll(conn, user.getClass(), sql, user.getUsername(), user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }
        }

        return userbean;
    }

    /**
     * 插入用户注册的信息
     * @param user
     * @return 返回true为插入数据成功，返回false为插入数据失败
     */
    @Override
    public boolean userinsert(Userbean user) {
        //连接数据库
        Connection conn = SetConn.Conn();

        //编写SQL语句
        String sql = "INSERT INTO t_user(username,PASSWORD,email,url) VALUES(?,?,?,?)";
        int i = 0;
        try {
            i = updateAll(conn, sql, user.getUsername(), user.getPassword(), user.getEmail(),user.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        if (i == -1){
            return false; //插入失败
        }
        return true; //插入成功
    }

    @Override
    public void saveSalt(Salt salt) {
        //连接数据库
        Connection conn = SetConn.Conn();
        String sql ="insert into t_salt(salt, username) VALUES (?,?)";
        updateAll(conn,sql,salt.getSalt(),salt.getUsername());
    }

    @Override
    public Salt getSalat(String username) {
        //连接数据库
        Connection conn = SetConn.Conn();
        String sql = "select salt from t_salt where username = ?";
        Salt salt = null;
        try {
            salt = selectAll(conn, Salt.class,sql, username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return salt;
    }

    @Override
    public void updateUser(String username,String newPassword, String email, String url) {
        Connection conn = SetConn.Conn();
        String sql = "update t_user set password=?,email=?,url=? where username=?";
        int i = updateAll(conn, sql, newPassword, email, url, username);
    }
}
