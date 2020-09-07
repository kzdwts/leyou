package com.leyou.item.api;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 规格参数组 控制层
 * @author: kangyong
 * @date: 2020/8/7 19:23
 * @version: v1.0
 */
@RequestMapping("/spec")
public interface SpecificationApi {

    /**
     * 根据分类id查询分组
     *
     * @param cid
     * @return
     */
    @GetMapping("/groups/{cid}")
    public List<SpecGroup> queryGroupsByCid(@PathVariable(value = "cid") Long cid);

    /**
     * 根据条件查询规格参数
     *
     * @param gid
     * @return
     */
    @GetMapping("/params")
    public List<SpecParam> queryParams(@RequestParam(value = "gid", required = false) Long gid,
                                       @RequestParam(value = "cid", required = false) Long cid,
                                       @RequestParam(value = "generic", required = false) Boolean generic,
                                       @RequestParam(value = "searching", required = false) Boolean searching
    );

    /**
     * 根据分类id查询规格参数组
     *
     * @param cid
     * @return
     */
    @GetMapping("/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGoodsWithParam(Long cid);

}
