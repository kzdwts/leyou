package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/7/20 22:56
 * @version: v1.0
 */
@RequestMapping("/brand")
public interface BrandApi {

    /**
     * 根据id查询品牌详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Brand queryBrandById(@PathVariable("id") Long id);

}
