package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DynamicPowerInfo {
    /**
     * 存储力使用率
     */
    Double storageUsageRate;
    /**
     * 计算力使用率
     */
    Double computeUsageRate;
    /**
     * 输出运载力使用率
     */
    Double outputTransportUsageRate;
    /**
     * 输入运载力使用率
     */
    Double inputTransportUsageRate;

    String createdAt;
}

