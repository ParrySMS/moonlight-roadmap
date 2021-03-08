package com.moonlight.roadmapapi.entity;

public enum RoadmapResponseStatus {
    SYSTEM_INTERNAL_ERROR("500500", "Internal Error"),
    BAD_REQUEST_ERROR("400400", "Bad Request Error")
    //todo add enum
    ;

    private String appCode;
    private String message;

    public String getAppCode() {
        return this.appCode;
    }

    public String getMessage() {
        return this.message;
    }

    public static RoadmapResponseStatus of(String appCode) {
        RoadmapResponseStatus[] responseCodes = values();
        for (RoadmapResponseStatus responseCode : responseCodes) {
            if (responseCode.getAppCode().equals(appCode)) {
                return responseCode;
            }
        }

        throw new IllegalArgumentException("No matching app code for [" + appCode + "]");
    }

    RoadmapResponseStatus(String appCode, String message) {
        this.appCode = appCode;
        this.message = message;
    }
}