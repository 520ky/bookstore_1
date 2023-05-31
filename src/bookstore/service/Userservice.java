package bookstore.service;


import bookstore.bean.Salt;
import bookstore.bean.Userbean;

public interface Userservice {
    /**
     * 注册用户
     * @param user
     */
    boolean registerUser(Userbean user);

    /**
     * 登录用户
     * @param user
     * @return 返回null登录失败 有值登录成功
     */
    Userbean loginUser(Userbean user);

    /**
     * 检查用户名是否可注册，数据库中是否存在用户名
     * @param user
     * @return 返回TRUE用户名可注册，false用户名不可注册
     */
    boolean checkUser(Userbean user);

    /*
    存盐
     */
    void saveSalt(Salt salt);
    /*
    取盐
     */
    Salt getSalt(String username);
}
