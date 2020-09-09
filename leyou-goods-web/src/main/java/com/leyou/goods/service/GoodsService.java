package com.leyou.goods.service;

import com.leyou.goods.client.BrandClient;
import com.leyou.goods.client.CategoryClient;
import com.leyou.goods.client.GoodsClient;
import com.leyou.goods.client.SpecificationClient;
import com.leyou.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 商品处理业务实现层
 * @author: kangyong
 * @date: 2020/9/8 22:17
 * @version: v1.0
 */
@Service
public class GoodsService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Autowired
    private GoodsClient goodsClient;

    /**
     * 查询商品详情页面数据
     *
     * @param spuId
     * @return
     */
    public Map<String, Object> loadData(Long spuId) {
        // 返回值
        Map<String, Object> model = new HashMap<>();

        // 根据spuId查询spu
        Spu spu = this.goodsClient.querySpuById(spuId);

        // 查询spu详情
        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spuId);

        // 查询商品下的skus
        List<Sku> skus = this.goodsClient.querySkuListBySpuId(spuId);

        // 查询品牌
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());

        // 查询分类
        List<Long> cids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> names = this.categoryClient.queryNamesByIds(cids);
        List<Map<String, Object>> categories = new ArrayList<>();
        for (int i = 0; i < cids.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", cids.get(i));
            map.put("name", names.get(i));
            categories.add(map);
        }

        // 查询规格参数组
        List<SpecGroup> groups = this.specificationClient.queryGroupsByCid(spu.getCid3());
        
        // 查询特殊的参数
        List<SpecParam> params = this.specificationClient.queryParams(null, spu.getCid3(), null, null);
        Map<Long, String> paramMap = new HashMap<>();
        params.forEach(param -> {
            paramMap.put(param.getId(), param.getName());
        });

        model.put("spu", spu);
        model.put("spuDetail", spuDetail);
        model.put("skus", skus);
        model.put("brand", brand);
        model.put("categories", categories);
        model.put("groups", groups);
        model.put("paramMap", paramMap);
        return model;
    }

}
