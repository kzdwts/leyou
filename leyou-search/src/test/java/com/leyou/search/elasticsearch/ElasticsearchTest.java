package com.leyou.search.elasticsearch;

import com.leyou.search.pojo.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/26 12:57
 * @version: v1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndex() {
        // 创建索引库
        elasticsearchTemplate.createIndex("goods");
        // 创建映射
        elasticsearchTemplate.putMapping(Goods.class);
    }

}
