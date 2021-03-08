package com.moonlight.roadmapapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
public enum StoryState {
    PENDING(0, "Pending"),
    READY_TO_START(1, "Ready To Start"),
    IN_PROGRESS(2, "In Progress"),
    DEV_COMPLETE(3, "Dev Complete"),
    TEST_COMPLETE(4, "Test Complete"),
    ACCEPTED(5, "Accepted");

    public final int id;
    public final String message;
    private final static Map<Integer, StoryState> map = new HashMap<>();

    static {
        for (StoryState item : StoryState.values()) {
            map.put(item.id, item);
        }
    }

    public static boolean isValid(int id) {
        return map.containsKey(id);
    }

    public static StoryState ofId(Integer id) {
        if (id == null) {
            return PENDING;
        }
        return Optional.ofNullable(map.get(id)).orElse(PENDING);
    }

    public static String getMessageBy(Integer id) {
        return ofId(id).message;
    }
}
