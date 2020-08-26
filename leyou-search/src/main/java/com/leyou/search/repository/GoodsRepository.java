package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/26 12:55
 * @version: v1.0
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
