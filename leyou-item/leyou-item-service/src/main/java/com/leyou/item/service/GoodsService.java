package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuBo;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/9 14:28
 * @version: v1.0
 */
@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryService categoryService;


    /**
     * 查询商品列表
     *
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    public PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows) {
        // 查询商品 1封装查询条件
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 搜索条件
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }

        // 分页
        PageHelper.startPage(page, rows);

        // 查询商品 2执行查询
        List<Spu> spuList = spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);

        List<SpuBo> spuBoList = new ArrayList<>();
        spuList.forEach(spu -> {
            SpuBo spuBo = new SpuBo();
            // 复制相同属性
            BeanUtils.copyProperties(spu, spuBo);

            // 查询类别名称
            List<String> categoryNames = categoryService.queryNameByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(categoryNames, "/"));

            // 查询品牌名称
            spuBo.setBname(brandMapper.selectByPrimaryKey(spu.getBrandId()).getName());

            // 存入结果集
            spuBoList.add(spuBo);
        });

        return new PageResult<>(pageInfo.getTotal(), spuBoList);
    }
}
