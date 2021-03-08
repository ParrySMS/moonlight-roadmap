package com.moonlight.roadmapapi.common.exception;

import com.moonlight.roadmapapi.entity.RoadmapResponseStatus;

public class ValidationException extends RoadmapException {
    public ValidationException(String message) {
        super(RoadmapResponseStatus.BAD_REQUEST_ERROR, message);
    }
}
