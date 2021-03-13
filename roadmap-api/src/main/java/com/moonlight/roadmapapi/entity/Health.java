package com.moonlight.roadmapapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author L
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
public enum Health {
    NO_HEALTH(1, "No Health"),
    DONE(2, "Done"),
    AT_RISK(3, "At Risk"),
    CRITICAL_SLIP(4, "Critical/Slip"),
    ON_TRACK(5, "On Track"),
    ON_HOLD(6, "On Hold");

    public final int id;
    public final String description;
    private static final Map<Integer, Health> map = new HashMap<>();

    static {
        for (Health item : Health.values()) {
            map.put(item.id, item);
        }
    }

    public static boolean isValid(int id) {
        return map.containsKey(id);
    }

    public static Health ofId(Integer id) {
        if (id == null) {
            return NO_HEALTH;
        }
        return Optional.ofNullable(map.get(id)).orElse(NO_HEALTH);
    }
}
