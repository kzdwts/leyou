package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 规格参数组下的参数名
 * @author: kangyong
 * @date: 2020/8/7 19:19
 * @version: v1.0
 */
@Data
@Table(name = "tb_spec_param")
public class SpecParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @Column(name = "`numeric`")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;

}
