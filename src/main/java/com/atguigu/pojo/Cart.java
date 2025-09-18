package com.atguigu.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ljg
 * @Date: 2025/9/10 9:46 PM Wednesday
 * @Description:
 */
public class Cart implements Serializable {
    private Map<Integer, CartItem> map = new HashMap<>();
    // 总金额
    private double totalAmount;
    // 总数量
    private Long totalCount;
    private Collection<CartItem> cartItems;


    // 添加到购物车
    public void addToCart(Book book) {
        CartItem cartItem = map.get(book.getId());
        if(cartItem == null) {
            // 购物车不存在 book
            cartItem = new CartItem(book, 1L);
            map.put(book.getId(), cartItem);
        } else {
            // 购物车中存在 book
            cartItem.setCount(cartItem.getCount() + 1);
        }
    }

    public void clearByBookId(int bookId) {
        map.remove(bookId);
    }

    /**
     * 获取购物车总数量
     * @return
     */
    public Long getTotalCount() {
        Collection<CartItem> cartItems = getCartItems();
        long count = 0;
        for (CartItem cartItem : cartItems) {
            count += cartItem.getCount();
        }
        this.totalCount = count;
        return count;
    }

    /**
     * 获取购物车总价格
     * @return
     */
    public double getTotalAmount() {
        Collection<CartItem> cartItems = getCartItems();
        BigDecimal totalAmountDec = new BigDecimal("0");
        for (CartItem cartItem : cartItems) {
            BigDecimal amountDec = new BigDecimal(cartItem.getAmount() + "");
            totalAmountDec = totalAmountDec.add(amountDec);
        }
        this.totalAmount = totalAmountDec.doubleValue();
        return this.totalAmount;
    }


    /**
     * 获取购物车中所有商品
     * @return
     */
    public Collection<CartItem> getCartItems() {
        Collection<CartItem> cartItems = map.values();
        return cartItems;
    }

    public void updateQuantity(Integer bookId, Long count) {
        if(count == 0) {
            map.remove(bookId);
            return;
        }
        CartItem cartItem = map.get(bookId);
        cartItem.setCount(count);
    }
}
