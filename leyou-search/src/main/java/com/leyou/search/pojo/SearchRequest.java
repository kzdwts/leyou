package com.leyou.search.pojo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 搜索结果实体类
 * @author: kangyong
 * @date: 2020/8/26 18:27
 * @version: v1.0
 */
public class SearchRequest implements Serializable {

    /**
     * 每页大小，不从页面接收，而是固定大小
     */
    private static final Integer DEFAULT_SIZE = 20;

    /**
     * 默认页面
     */
    private static final Integer DEFAULT_PAGE = 1;

    /**
     * 搜索条件
     */
    private String key;

    /**
     * 当前页
     */
    private Integer page;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if (page == null) {
            return DEFAULT_PAGE;
        }
        // 取大，获取页码的校验，不能小于1
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 获取每页大小
     *
     * @return
     */
    public Integer getSize() {
        return DEFAULT_SIZE;
    }
}
