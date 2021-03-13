package com.moonlight.roadmapapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Accessors(chain = true)
public class RoadmapRow {
    public String id;
    public String name;
    @JsonIgnore
    public int stateCode;// status number id
    @JsonProperty("report_color")
    public String reportColor;
    @JsonProperty("start_date")
    @JsonFormat(pattern = "MM/dd/yyyy", timezone = "GMT+8")
    public Date releaseStartDate;
    @JsonProperty("end_date")
    @JsonFormat(pattern = "MM/dd/yyyy", timezone = "GMT+8")
    public Date releaseEndDate;
    @JsonProperty("percent_completed")
    public int completedPercent;//eg. 30 = storyPointsAccepted/storyPointsTotal

    @JsonIgnore
    public int storyPointsTotal;
    @JsonIgnore
    public int storyPointsAccepted;
    @JsonProperty("state")
    public String stateMessage;// status in UI
}
