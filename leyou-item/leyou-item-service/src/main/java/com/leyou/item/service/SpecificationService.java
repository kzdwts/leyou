package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 规格参数组，业务实现层
 * @author: kangyong
 * @date: 2020/8/7 19:23
 * @version: v1.0
 */
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类id查询分组
     *
     * @param cid 分类id
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        // 封装查询条件
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        // 查询
        List<SpecGroup> specGroupList = specGroupMapper.select(record);
        return specGroupList;
    }

    /**
     * 根据条件查询规格参数
     *
     * @param gid 组id
     * @return
     */
    public List<SpecParam> queryParams(Long gid) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        List<SpecParam> specParamList = specParamMapper.select(record);
        return specParamList;
    }

}
