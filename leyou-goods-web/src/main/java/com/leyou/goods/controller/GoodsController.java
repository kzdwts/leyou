package com.leyou.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/9/6 23:22
 * @version: v1.0
 */
@Controller
public class GoodsController {

    @GetMapping("/item/{id}.html")
    public String toItemPage(@PathVariable("id") Long id, Model model) {
        return "item";
    }
}
