package com.leyou.item.mapper;

import com.leyou.item.pojo.Stock;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/9 23:02
 * @version: v1.0
 */
public interface StockMapper extends Mapper<Stock>, DeleteByIdListMapper<Stock, Long> {
}
