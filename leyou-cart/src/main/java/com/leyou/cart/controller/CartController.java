package com.leyou.cart.controller;

import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 购物车 控制层
 * @author: kangyong
 * @date: 2020/12/3 19:29
 * @version: v1.0
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     *
     * @param cart
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody Cart cart) {
        // 添加到购物车
        this.cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询购物车数据
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cart>> queryCarts() {
        List<Cart> cartList = this.cartService.queryCarts();
        if (CollectionUtils.isEmpty(cartList)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartList);
    }
}
