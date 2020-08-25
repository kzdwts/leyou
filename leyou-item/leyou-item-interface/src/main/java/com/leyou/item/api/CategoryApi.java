package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/7/15 23:49
 * @version: v1.0
 */
@RequestMapping("/category")
public interface CategoryApi {

    /**
     * 根据父类id查询子类列表
     *
     * @param pid 父类id
     * @return
     */
    @GetMapping("/list")
    public List<Category> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid);

    /**
     * 根据id查询名称
     *
     * @param ids
     * @return
     */
    @GetMapping("/names")
    public List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);

}
