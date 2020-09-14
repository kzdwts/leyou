package com.leyou.goods.controller;

import com.leyou.goods.service.GoodsHtmlService;
import com.leyou.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 商品 controller
 * @author: kangyong
 * @date: 2020/9/6 23:22
 * @version: v1.0
 */
@Controller
@RequestMapping("/item")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsHtmlService goodsHtmlService;

    /**
     * 跳转打开商品详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}.html")
    public String toItemPage(@PathVariable("id") Long id, Model model) {
        // 加载数据
        Map<String, Object> map = this.goodsService.loadData(id);
        // 放入模型
        model.addAllAttributes(map);

        // 生成静态页面
        this.goodsHtmlService.asyncExcute(id);

        return "item";
    }
}
