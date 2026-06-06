package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DynamicPowerInfo {
    /**
     * 存储力使用率
     */
    Double storageUsageRate;
    String storageUsageRateUnit = "%";
    /**
     * 计算力使用率
     */
    Double computeUsageRate;
    String computeUsageRateUnit = "%";
    /**
     * 输出运载力使用率
     */
    Double outputTransportUsageRate;
    String outputTransportUsageRateUnit = "%";
    /**
     * 输入运载力使用率
     */
    Double inputTransportUsageRate;
    String inputTransportUsageRateUnit = "%";

    String createdAt;
}

