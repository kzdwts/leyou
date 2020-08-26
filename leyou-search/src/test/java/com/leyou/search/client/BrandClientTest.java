package com.leyou.search.client;

import com.leyou.item.pojo.Brand;
import com.leyou.search.LeyouSearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/26 12:27
 * @version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeyouSearchApplication.class)
public class BrandClientTest {

    @Autowired
    private BrandClient brandClient;

    /**
     * 根据id查询品牌名称
     */
    @Test
    public void testQueryBrandById() {
        Brand brand = brandClient.queryBrandById(8551L);
        System.out.println(brand);
    }

}
