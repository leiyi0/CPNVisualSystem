package org.cpnvisualsystem.entity;

import lombok.Data;

/**
 * 静态算力信息实体类，包含计算算力、存储算力和网络传输算力
 */
@Data
public class StaticPowerInfo {
    Double computerPower;
    Double storagePower;
    Double transportPower;
}
