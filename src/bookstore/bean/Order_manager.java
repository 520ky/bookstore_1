package bookstore.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order_manager {
    private String username;
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    //订单状态，默认是0未发货，1,已发货
    private Integer status=0;
    private Integer userId;

    public Order_manager() {
    }

    public Order_manager(String username, String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.username = username;
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order_manager{" +
                "username='" + username + '\'' +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
