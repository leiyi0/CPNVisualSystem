package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> data;
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;

    public PageResult(List<T> data, Integer total, Integer pageNum, Integer pageSize) {
        this.data = data;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
