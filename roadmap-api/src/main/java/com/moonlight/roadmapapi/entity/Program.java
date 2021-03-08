package com.moonlight.roadmapapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Program {
    public int id;
    public String name;
}
