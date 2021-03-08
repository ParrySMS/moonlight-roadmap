package com.moonlight.roadmapapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
public enum InitiativeState {
    NOT_STARTED(1, "Not Started"),
    IN_PROGRESS(2, "In Progress"),
    ACCEPTED(3, "Accepted");

    public final int id;
    public final String message;
    private final static Map<Integer, InitiativeState> map = new HashMap<>();

    static {
        for (InitiativeState item : InitiativeState.values()) {
            map.put(item.id, item);
        }
    }

    public static boolean isValid(int id) {
        return map.containsKey(id);
    }

    public static InitiativeState ofId(Integer id) {
        if (id == null) {
            return NOT_STARTED;
        }
        return Optional.ofNullable(map.get(id)).orElse(NOT_STARTED);
    }

    public static String getMessageBy(Integer id) {
        return ofId(id).message;
    }
}
