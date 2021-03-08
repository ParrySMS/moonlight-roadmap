package com.moonlight.roadmapapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class RoadmapPanel {
    @JsonProperty("min_date")
    @JsonFormat(pattern="MM/dd/yyyy",timezone = "GMT+8")
    public Date minDate;
    @JsonProperty("max_date")
    @JsonFormat(pattern="MM/dd/yyyy",timezone = "GMT+8")
    public Date maxDate;
    public List<RoadmapRow> rows;
}
