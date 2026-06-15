package org.cpnvisualsystem.entity;

import lombok.Data;

/**
 * 静态算力信息实体类，包含计算算力、存储算力和网络传输算力
 */
@Data
public class StaticPowerInfo {
    Double computerPower;
    String computerPowerUnit = "TFLOPS";
    Double computerPowerMips;
    String computerPowerMipsUnit = "MIPS";
    Double storagePower;
    String storagePowerUnit = "GB";
    Double transportPower;
    String transportPowerUnit = "Gbps";
}
