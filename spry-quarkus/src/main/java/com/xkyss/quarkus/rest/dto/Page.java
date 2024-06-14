package com.xkyss.quarkus.rest.dto;

public class Page {
    /**
     * 总数
     */
    private Integer total;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 当前第几页
     */
    private Integer page;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
