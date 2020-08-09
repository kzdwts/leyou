package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/7/15 23:53
 * @version: v1.0
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点id查询子集
     *
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category condition = new Category();
        condition.setParentId(pid);
        return categoryMapper.select(condition);
    }

    /**
     * 根据id查询类别名称
     *
     * @param ids
     * @return
     */
    public List<String> queryNameByIds(List<Long> ids) {
        List<Category> categoryList = categoryMapper.selectByIdList(ids);
        List<String> categoryNames = new ArrayList<String>();

        // 分类对象
        for (Category c : categoryList) {
            categoryNames.add(c.getName());
        }
        return categoryNames;
    }
}
