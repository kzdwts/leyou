package com.leyou.item.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/9 14:27
 * @version: v1.0
 */
@Data
public class SpuBo extends Spu {

    /**
     * 商品分类名称
     */
    private String cname;

    /**
     * 品牌名称
     */
    private String bname;

    /**
     * 商品详情
     */
    private SpuDetail spuDetail;

    /**
     * sku列表
     */
    private List<Sku> skus;

}
