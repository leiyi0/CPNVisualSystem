package org.cpnvisualsystem.entity.vo;

import lombok.Data;
import org.cpnvisualsystem.entity.ResourceSummary;

import java.util.List;

/**
 * 预览列表通用包装，同时携带对应层级的 resource_summary 汇总数据
 */
@Data
public class PreviewWrapper<T> {
    private List<T> items;
    private ResourceSummary resourceSummary;

    public PreviewWrapper(List<T> items, ResourceSummary resourceSummary) {
        this.items = items;
        this.resourceSummary = resourceSummary;
    }
}
