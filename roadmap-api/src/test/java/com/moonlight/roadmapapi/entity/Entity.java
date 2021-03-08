package com.moonlight.roadmapapi.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Entity {
    public static List<RoadmapRow> newRows(int size) {
        return IntStream.range(0, size)
                .mapToObj(r -> new RoadmapRow())
                .collect(Collectors.toList());
    }
}
