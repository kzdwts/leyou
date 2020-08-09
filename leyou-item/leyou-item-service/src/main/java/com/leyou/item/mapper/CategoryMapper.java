package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 分类业务 持久层
 * @author: kangyong
 * @date: 2020/7/16 0:01
 * @version: v1.0
 */
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category, Long> {

}
