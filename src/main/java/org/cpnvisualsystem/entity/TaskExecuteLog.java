package org.cpnvisualsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
/**
 * 任务执行日志实体类，包含任务ID、日志内容和创建时间
 */
@Data
@AllArgsConstructor
public class TaskExecuteLog {

    Integer id;
    Integer taskId;
    String log;
    Date createTime;

}