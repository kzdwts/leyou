package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 规格参数的分组表，每个商品分类下有多个规格参数组
 * @author: kangyong
 * @date: 2020/8/7 19:18
 * @version: v1.0
 */
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;

    private String name;

    @Transient
    private List<SpecParam> params;

}