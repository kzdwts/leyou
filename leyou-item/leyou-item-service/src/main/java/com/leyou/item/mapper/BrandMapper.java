package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/7/20 22:54
 * @version: v1.0
 */
public interface BrandMapper extends Mapper<Brand> {

    @Insert("INSERT INTO tb_category_brand(category_id, brand_id) VALUES (#{cid}, #{bid})")
    int saveBrandAndCategory(Long cid, Long bid);

    @Select("SELECT * FROM tb_category WHERE id IN(SELECT category_id FROM tb_category_brand WHERE brand_id=#{id})")
    List<Category> queryBrandListById(Long bid);

    @Delete("DELETE FROM tb_category_brand WHERE brand_id=#{bid}")
    int deleteCategory(Long bid);

    @Select("SELECT * FROM tb_brand b INNER JOIN tb_category_brand cb ON cb.brand_id=b.id WHERE cb.category_id=#{cid}")
    List<Brand> queryBrandsByCid(Long cid);
}
