package bookstore.test;

import bookstore.bean.Cart;
import bookstore.bean.CartItem;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;



public class CartTest {


    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(2,"java联系",1,new BigDecimal(1000),new BigDecimal(1000),"urldsfsdf"));

        System.out.println(cart);
    }

    @Test
    public void delete() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(2,"java联系",1,new BigDecimal(1000),new BigDecimal(1000),"urldsfsdf"));

        cart.delete(2);
        System.out.println(cart);

    }

    @Test
    public void getAllCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(2,"java联系",1,new BigDecimal(1000),new BigDecimal(1000),"urldsfsdf"));

        Integer allCount = cart.getAllCount();
        System.out.println(cart);
        System.out.println(allCount);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(2,"java联系",1,new BigDecimal(1000),new BigDecimal(1000),"urldsfsdf"));

        System.out.println("删除前="+cart);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void getAllPrice() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(2,"java联系",1,new BigDecimal(1000),new BigDecimal(1000),"urldsfsdf"));

        BigDecimal allPrice = cart.getAllPrice();
        System.out.println(cart);
        System.out.println(allPrice);
    }

    @Test
    public void changeCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100),"urldsfsdf"));
        cart.addItem(new CartItem(2,"java联系",1,new BigDecimal(1000),new BigDecimal(1000),"urldsfsdf"));

        cart.changeCount(1,1);
        System.out.println(cart);
    }

}