package com.leyou.search.elasticsearch;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.SpuBo;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.repository.GoodsRepository;
import com.leyou.search.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/26 12:57
 * @version: v1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private GoodsService goodsService;

    @Test
    public void createIndex() {
        // 创建索引库
        elasticsearchTemplate.createIndex("goods");
        // 创建映射
        elasticsearchTemplate.putMapping(Goods.class);
    }

    /**
     * 查询商品，写入索引库
     */
    @Test
    public void queryGoodsPutToES() {
        log.info("==============写入ES数据===========START====");
        // 创建索引
        elasticsearchTemplate.createIndex(Goods.class);
        // 创建映射
        elasticsearchTemplate.putMapping(Goods.class);

        // 查询数据
        Integer page = 1;
        Integer rows = 100;

        do {
            // 查询spu
            PageResult<SpuBo> pageResult = this.goodsClient.querySpuBoByPage(page, rows, null, true);
            // 调用业务层将spu构建成goods，组装为list
            List<Goods> goodsList = pageResult.getItems().stream().map(spuBo -> {
                Goods goods = null;
                try {
                    goods = this.goodsService.buildGoods(spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return goods;
            }).collect(Collectors.toList());
            // 写入索引库
            Iterable<Goods> goods = this.goodsRepository.saveAll(goodsList);

            // 当前页面的数据条数,最后一页没有100
            rows = pageResult.getItems().size();
            // 页码+1
            page++;

        } while (rows == 100);
        log.info("==============写入ES数据===========END====");
    }

}
