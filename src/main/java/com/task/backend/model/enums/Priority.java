package com.task.backend.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Priority {
    VERY_HIGH(1),
    HIGH(2),
    MODERATE(3),
    LOW(4),
    VERY_LOW(5);

    private static final Map<Integer, Priority> BY_PRIORITY = new HashMap<>();

    static {
        for (Priority e : values()) {
            BY_PRIORITY.put(e.value, e);
        }
    }

    public final int value;

    Priority(int value) {
        this.value = value;
    }

    public static Priority valueOfLabel(Integer label) {
        return BY_PRIORITY.get(label);
    }

}
