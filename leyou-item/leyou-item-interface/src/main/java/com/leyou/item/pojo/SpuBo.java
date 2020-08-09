package com.leyou.item.pojo;

import lombok.Data;

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

}
