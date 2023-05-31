package bookstore.service.impl;


import bookstore.bean.Salt;
import bookstore.bean.Userbean;
import bookstore.dao.Userdao;
import bookstore.dao.impl.UserdaoImpl;
import bookstore.service.Userservice;

public class UserserviceImpl implements Userservice {

    private Userdao userdao = new UserdaoImpl();
    @Override
    public boolean registerUser(Userbean user) {

        boolean flag = userdao.userinsert(user);
        return  flag ; // true--插入成功
    }

    @Override
    public Userbean loginUser(Userbean user) {
        return   userdao.userlogin(user);
          //如果为null则登录不成功，登录成功返回userbean
    }

    @Override
    public boolean checkUser(Userbean user) {
        return   userdao.userAble(user);         //true --用户名存在
    }

    @Override
    public void saveSalt(Salt salt) {
        userdao.saveSalt(salt);
    }

    @Override
    public Salt getSalt(String username) {

        try {
            return userdao.getSalat(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
