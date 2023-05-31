package bookstore.bean;

import java.math.BigDecimal;

/**
 * 购物车商品项
 */
public class CartItem {
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal priceAll;
    private String imgeUrl;

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal priceAll) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.priceAll = priceAll;
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal priceAll, String imgeUrl) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.priceAll = priceAll;
        this.imgeUrl = imgeUrl;
    }

    public String getImgeUrl() {
        return imgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(BigDecimal priceAll) {
        this.priceAll = priceAll;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", priceAll=" + priceAll +
                ", imgeUrl='" + imgeUrl + '\'' +
                '}';
    }
}


