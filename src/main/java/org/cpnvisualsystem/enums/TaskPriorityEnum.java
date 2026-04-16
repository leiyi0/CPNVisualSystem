package org.cpnvisualsystem.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskPriorityEnum {
    SAFE_CRITICAL("安全关键级", 3),
    TRAIN_CRITICAL("行车关键级", 2),
    NON_CRITICAL("非关键级", 1);

    private final String displayName;
    private final Integer priorityValue;

}