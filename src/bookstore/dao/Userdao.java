package bookstore.dao;


import bookstore.bean.Salt;
import bookstore.bean.Userbean;

public interface Userdao {
    /**
     * 查询用户名是可用
     */
    boolean userAble(Userbean user);

    /**
     * 根据用户名和密码查询用户是否存在
     */
    Userbean userlogin(Userbean user);

    /**
     * 保存用户信息
     */
    boolean userinsert(Userbean user);

    void saveSalt(Salt salt);

    Salt getSalat(String username);

    void updateUser( String username,String newPassword, String email, String url);
}
