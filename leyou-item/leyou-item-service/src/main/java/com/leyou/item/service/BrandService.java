package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 品牌 业务实现层
 * @author: kangyong
 * @date: 2020/7/20 22:55
 * @version: v1.0
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /**
     * 分页查询品牌数据
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        // 查询条件
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        // 分页
        PageHelper.startPage(page, rows);

        // 查询
        List<Brand> brandList = brandMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        // 返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }

    /**
     * 新增品牌
     *
     * @param brand
     * @param cids
     */
    public void saveBrand(Brand brand, List<Long> cids) {
        // 保存品牌数据
        int rows = brandMapper.insertSelective(brand);

        // 保存品牌和类别对应关系
        cids.forEach(cid -> {
            brandMapper.saveBrandAndCategory(cid, brand.getId());
        });

    }

    /**
     * 获取品牌详情
     *
     * @param id
     * @return
     */
    public List<Category> queryBrandById(Long id) {
        return brandMapper.queryBrandById(id);
    }

    public void updateById(Brand brand, List<Long> cids) {
        // 保存品牌信息
        int rows = brandMapper.updateByPrimaryKey(brand);

        //删除品牌类别
        brandMapper.deleteCategory(brand.getId());

        // 保存品牌类别
        cids.forEach(cid -> {
            brandMapper.saveBrandAndCategory(cid, brand.getId());
        });
    }

    /**
     * 根据分类id查询品牌列表
     *
     * @param cid 分类id
     * @return
     */
    public List<Brand> queryBrandsByCid(Long cid) {
        return brandMapper.queryBrandsByCid(cid);
    }
}
