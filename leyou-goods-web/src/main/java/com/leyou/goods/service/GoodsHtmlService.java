package com.leyou.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 商品详情静态化 业务实现层
 * @author: kangyong
 * @date: 2020/9/14 22:26
 * @version: v1.0
 */
@Service
public class GoodsHtmlService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private GoodsService goodsService;

    /**
     * 创建静态化页面
     *
     * @param spuId
     */
    public void createHtml(Long spuId) {
        // 创建上下文对象
        Context context = new Context();
        // 数据
        context.setVariables(goodsService.loadData(spuId));
        PrintWriter pw = null;
        try {
            // 文件流
            File file = new File("D:\\package\\nginx-1.15.3\\html\\item\\" + spuId + ".html");
            pw = new PrintWriter(file);
            this.templateEngine.process("item", context, pw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

}
