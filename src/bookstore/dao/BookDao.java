package bookstore.dao;

import bookstore.bean.Bookbean;

import java.util.List;

public interface BookDao {
    /**
     * 添加图书
     * @param book
     */
    void addBook(Bookbean book);

    /**
     * 修改图书
     * @param book
     */
    void updateBook(Bookbean book);

    /**
     * 删除图书
     * @param id
     */
    void deleteBook(int id);

    /**
     * 查找一本图书
     * @param id
     * @return BookDao为查到，null为没有查到
     */
    Bookbean findBook(int id);

    /**
     * 查询所有图书

     * @return 返回图书集合list
     */
    List<Bookbean> findAll();

    Object repeat(Bookbean book);
}
