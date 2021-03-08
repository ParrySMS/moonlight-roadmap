package com.moonlight.roadmapapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
public enum PLGoalState {
    NOT_STARTED(1, "Not Started"),
    IN_PROGRESS(2, "In Progress"),
    Done(3, "Done");

    public final int id;
    public final String message;
    private final static Map<Integer, PLGoalState> map = new HashMap<>();

    static {
        for (PLGoalState item : PLGoalState.values()) {
            map.put(item.id, item);
        }
    }

    public static boolean isValid(int id) {
        return map.containsKey(id);
    }

    public static PLGoalState ofId(Integer id) {
        if (id == null) {
            return NOT_STARTED;
        }
        return Optional.ofNullable(map.get(id)).orElse(NOT_STARTED);
    }

    public static String getMessage(Integer id) {
        return ofId(id).message;
    }
}
