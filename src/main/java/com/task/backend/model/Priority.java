package com.task.backend.model;

import java.util.HashMap;
import java.util.Map;

public enum Priority {
    VERY_HIGH(1),
    HIGH(2),
    MODERATE(3),
    LOW(4),
    VERY_LOW(5);

    public final int value;
    private static final Map<Integer, Priority> BY_LABEL = new HashMap<>();

    static {
        for (Priority e: values()) {
            BY_LABEL.put(e.value, e);
        }
    }

    private Priority(int value) {
        this.value = value;
    }

    public static Priority valueOfLabel(Integer label) {
        return BY_LABEL.get(label);
    }

}
