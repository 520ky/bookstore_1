package bookstore.service.impl;

import bookstore.bean.Bookbean;
import bookstore.dao.BookDao;
import bookstore.dao.impl.BookDaoImpl;
import bookstore.service.Bookservice;

import java.util.List;

public class BookserviceImpl implements Bookservice {
    BookDao bookDao = new BookDaoImpl();
    @Override
    public void add(Bookbean book) {
        bookDao.addBook(book);
    }

    @Override
    public void delet(int id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void update(Bookbean book) {
        bookDao.updateBook(book);
    }

    @Override
    public Bookbean findById(int id) {
        return bookDao.findBook(id);

    }

    @Override
    public List<Bookbean> findAll() {
        List<Bookbean> all = bookDao.findAll();
        return all;
    }

    @Override
    public Object isRepeat(Bookbean book) {
        return bookDao.repeat(book); //返回null图书不能存在

    }

}
