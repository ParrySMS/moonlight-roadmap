package com.moonlight.roadmapapi.common.exception;

import com.moonlight.roadmapapi.entity.RoadmapResponseStatus;

public class RoadmapException extends RuntimeException {
    private RoadmapResponseStatus responseStatus;

    public RoadmapException(RoadmapResponseStatus responseStatus) {
        this(responseStatus, responseStatus.getMessage());
    }

    public RoadmapException(RoadmapResponseStatus responseStatus, String errorMessage) {
        super(errorMessage);
        this.responseStatus = responseStatus;
    }

    public RoadmapException(RoadmapResponseStatus responseStatus, String error, Exception e) {
        super(error, e);
        this.responseStatus = responseStatus;
    }

    public RoadmapResponseStatus getResponseStatus() {
        return responseStatus;
    }
}
