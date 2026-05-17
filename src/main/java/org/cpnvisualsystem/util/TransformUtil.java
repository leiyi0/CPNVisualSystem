package org.cpnvisualsystem.util;

import org.cpnvisualsystem.entity.*;
import org.cpnvisualsystem.entity.vo.*;

public class TransformUtil {

    public static TaskPreviewVO toTaskPreview(TaskInfo t) {
        TaskPreviewVO vo = new TaskPreviewVO();
        vo.setTaskId(t.getTaskId());
        vo.setTaskName(t.getTaskName());
        vo.setType(mapDataType(t.getDataType()));
        vo.setPriority(mapPriority(t.getPriorityLevel()));
        vo.setStatus(mapState(t.getState()));
        vo.setComputeRequirement(formatCompute(t.getComputeDemand(), t.getComputeType()));
        return vo;
    }

    public static TrainPreviewVO toTrainPreview(TrainInfo t) {
        TrainPreviewVO vo = new TrainPreviewVO();
        vo.setTrainId(t.getTrainCode());
        double[] ll = parsePosition(t.getPosition());
        vo.setLongitude(ll[0]);
        vo.setLatitude(ll[1]);
        return vo;
    }

    public static TrainInfoVO toTrainInfo(TrainInfo t) {
        TrainInfoVO vo = new TrainInfoVO();
        vo.setTrainId(t.getTrainCode());
        double[] ll = parsePosition(t.getPosition());
        vo.setLongitude(ll[0]);
        vo.setLatitude(ll[1]);
        vo.setSpeed(t.getSpeed());
        vo.setStatus(mapTrainStatus(t.getStatus()));
        vo.setCarriageCount(t.getCarriageCount());
        vo.setTrainNumber(t.getTrainNumber() != null ? "G" + t.getTrainNumber() : null);
        return vo;
    }

    public static CarriagePreviewVO toCarriagePreview(CarriageInfo c) {
        CarriagePreviewVO vo = new CarriagePreviewVO();
        vo.setCarriageName(c.getCarriageCode());
        vo.setDeviceCount(c.getDeviceCount());
        return vo;
    }

    public static CarriageInfoVO toCarriageInfo(CarriageInfo c) {
        CarriageInfoVO vo = new CarriageInfoVO();
        vo.setCarriageName(c.getCarriageCode());
        vo.setType(c.getType());
        vo.setStatus(c.getStatus());
        vo.setDeviceCount(c.getDeviceCount());
        double[] ll = parsePosition(c.getPosition());
        vo.setLongitude(ll[0]);
        vo.setLatitude(ll[1]);
        return vo;
    }

    public static DevicePreviewVO toDevicePreview(ComputeNodes n) {
        DevicePreviewVO vo = new DevicePreviewVO();
        String type = (n.getHasGpu() != null && n.getHasGpu() == 1) ? "GPU服务器" : "CPU服务器";
        vo.setDeviceId(String.valueOf(n.getId()));
        vo.setDeviceName(type + String.format("%03d", n.getId()));
        vo.setType(type);
        vo.setStatus(mapDeviceStatus(n.getStatus()));
        vo.setThumbnail(null);
        return vo;
    }

    private static double[] parsePosition(String position) {
        if (position == null || position.isEmpty()) return new double[]{0, 0};
        String[] parts = position.split(",");
        try {
            return new double[]{Double.parseDouble(parts[0]), Double.parseDouble(parts[1])};
        } catch (Exception e) {
            return new double[]{0, 0};
        }
    }

    private static String formatCompute(Double demand, String type) {
        if (demand == null) return null;
        if (demand >= 1e9) return String.format("%.0f G%s", demand / 1e9, type.toUpperCase());
        if (demand >= 1e6) return String.format("%.0f M%s", demand / 1e6, type.toUpperCase());
        if (demand >= 1e3) return String.format("%.0f K%s", demand / 1e3, type.toUpperCase());
        return String.format("%.0f %s", demand, type.toUpperCase());
    }

    static String mapDataType(String dt) {
        if (dt == null) return null;
        switch (dt) {
            case "process": return "计算";
            case "stream": return "流处理";
            default: return dt;
        }
    }

    static String mapPriority(String p) {
        if (p == null) return null;
        switch (p) {
            case "SAFETY_CRITICAL": return "高";
            case "OPERATION_CRITICAL": return "中";
            case "NON_CRITICAL": return "低";
            default: return p;
        }
    }

    static String mapState(String s) {
        if (s == null) return null;
        switch (s) {
            case "Running": return "运行中";
            case "Pending": return "等待中";
            case "Succeeded": return "已完成";
            case "Failed": return "失败";
            default: return s;
        }
    }

    static String mapTrainStatus(String s) {
        if (s == null) return null;
        switch (s) {
            case "running": return "运行中";
            case "stopped": return "停靠";
            case "offline": return "离线";
            default: return s;
        }
    }

    static String mapDeviceStatus(String s) {
        if (s == null) return null;
        switch (s) {
            case "READY": return "运行中";
            case "OFFLINE": return "离线";
            case "ERROR": return "告警";
            default: return s;
        }
    }
}
