package bookstore.service;

import bookstore.bean.Bookbean;

import java.util.List;

public interface Bookservice {
    //添加图书
    public void add(Bookbean book);
    //删除图书
    public void delet(int id);
    //修改图书
    public void update(Bookbean book);
    //查询一个图书
    Bookbean findById(int id);
    //查询所有图书
    List<Bookbean> findAll();
    //查询图书是否重复
    Object isRepeat(Bookbean book);

}
