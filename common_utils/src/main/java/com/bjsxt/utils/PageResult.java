package com.bjsxt.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象工具
 */
public class PageResult implements Serializable {
    //当前页
    private Integer pageIndex;
    //总页数
    private long pageTotal;
    //分页结果集
    private List result;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
