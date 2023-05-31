package bookstore.bean;


import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

//    private BigDecimal allCount 商品总数量
//    private BigDecimal allPrice;//商品总价钱

//    private List<CartItem> list ;//商品项集合


    /**
     * 创建map存放商品
     * key存放id
     * value存放商品项
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
    //判断商品是否已经存在map集合中
        CartItem item = items.get(cartItem.getId());
        if (item==null){
            //添加的商品不在map集合中,向map中添加该商品项
            items.put(cartItem.getId(),cartItem);

        }else {
            //商品在map集合中，数量和金额改变
            item.setCount(item.getCount()+1);
            //单价乘数量
            item.setPriceAll(cartItem.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品
     * @param id
     */
    public void delete(Integer id){
        //找到传入id的商品并删除
        items.remove(id);
    }

    /**
     * 获取购物车中商品总数量
     * @return
     */
    public Integer getAllCount(){
        Integer Count = 0;
        //便利map集合获取数量和
        for (Map.Entry<Integer, CartItem> entry :items.entrySet()) {
            Count+=entry.getValue().getCount();
        }
        return Count;
    }

    /**
     * 清空购物车
     */
    public void clear(){
       items.clear();
    }

    /**
     * 获取购物车商品总价格
     * @return
     */
    public BigDecimal getAllPrice(){
        BigDecimal allPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry :items.entrySet()) {
               allPrice = allPrice.add(entry.getValue().getPriceAll());
        }
        return allPrice;
    }

    /**
     * 修改购物车商品数量
     * @param id
     * @param count
     */
    public BigDecimal changeCount(Integer id,Integer count){
        //获取修改商品
        CartItem cartItem = items.get(id);
        BigDecimal price = cartItem.getPrice();
        //修改商品新的数量,判断商品数量防止缓冲区溢出
        //限制商品数量最多为999个最少为一个
        if (count>=1 && count <=999){
            cartItem.setCount(count);
        }

        if (cartItem.getCount()==0){
            items.remove(id);
        }
         cartItem.setPriceAll(new BigDecimal(count).multiply(price));
        return cartItem.getPriceAll();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
