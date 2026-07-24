package org.cpnvisualsystem.util;

import org.cpnvisualsystem.entity.*;
import org.cpnvisualsystem.entity.vo.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TransformUtil {

    public static TaskPreviewVO toTaskPreview(TaskInfo t) {
        TaskPreviewVO vo = new TaskPreviewVO();
        vo.setTaskId(t.getTaskId());
        vo.setTaskName(t.getTaskName());
        vo.setType(t.getTaskType());
        vo.setPriority(t.getPriorityLevel());
        vo.setStatus(t.getState());
        vo.setComputeRequirement(formatCompute(t.getComputeDemand(), t.getComputeType()));
        vo.setRunningDevice(t.getTargetDeviceTags());
        vo.setMatchStrategy(t.getMatchStrategy());
        // 车厢和设备ID
        vo.setSourceCarriage(t.getSourceCarriage());
        vo.setSourceDevice(t.getSourceDevice());
        vo.setTargetDeviceIds(t.getTargetDeviceIds());
        return vo;
    }

    public static TrainPreviewVO toTrainPreview(TrainInfo t) {
        TrainPreviewVO vo = new TrainPreviewVO();
        vo.setTrainId(t.getId());
        vo.setTrainCode(t.getTrainCode());
        double[] ll = parsePosition(t.getPosition());
        vo.setLongitude(ll[0]);
        vo.setLatitude(ll[1]);
        return vo;
    }

    public static TrainInfoVO toTrainInfo(TrainInfo t) {
        TrainInfoVO vo = new TrainInfoVO();
        vo.setTrainId(t.getId());
        vo.setTrainCode(t.getTrainCode());
        double[] ll = parsePosition(t.getPosition());
        vo.setLongitude(ll[0]);
        vo.setLatitude(ll[1]);
        vo.setSpeed(t.getSpeed());
        vo.setStatus(t.getStatus());
        vo.setCarriageCount(t.getCarriageCount());
        vo.setTrainNumber(t.getTrainNumber() != null ? "G" + t.getTrainNumber() : null);
        return vo;
    }

    public static CarriagePreviewVO toCarriagePreview(CarriageInfo c) {
        CarriagePreviewVO vo = new CarriagePreviewVO();
        vo.setCarriageId(c.getId());
        vo.setCarriageCode(c.getCarriageCode());
        vo.setDeviceCount(c.getDeviceCount());
        return vo;
    }

    public static CarriageInfoVO toCarriageInfo(CarriageInfo c) {
        CarriageInfoVO vo = new CarriageInfoVO();
        vo.setCarriageId(c.getId());
        vo.setCarriageCode(c.getCarriageCode());
        vo.setClusterId(c.getClusterId());
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
        vo.setDeviceId(n.getId());
        vo.setDeviceName(n.getName());
        vo.setType(n.getDeviceNameCn());
        vo.setStatus(n.getStatus());
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

    /**
     * 将 MyBatis GROUP BY 查询结果 List<Map<"status"/"cnt">> 转换为 Map<String, Integer>
     */
    public static Map<String, Integer> convertStatusMap(List<Map<String, Object>> rows) {
        Map<String, Integer> result = new LinkedHashMap<>();
        if (rows != null) {
            for (Map<String, Object> row : rows) {
                String key = String.valueOf(row.get("status"));
                Integer value = row.get("cnt") != null ? ((Number) row.get("cnt")).intValue() : 0;
                result.put(key, value);
            }
        }
        return result;
    }
}
