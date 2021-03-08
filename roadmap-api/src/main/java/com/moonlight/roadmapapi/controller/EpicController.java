package com.moonlight.roadmapapi.controller;

import com.amazonaws.util.CollectionUtils;
import com.moonlight.roadmapapi.common.exception.ValidationException;
import com.moonlight.roadmapapi.common.utils.ValidateUtils;
import com.moonlight.roadmapapi.entity.ApiResult;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/epics")
@CrossOrigin
public class EpicController {
    @Autowired
    private EpicService epicService;

    @GetMapping("")
    public ResponseEntity<ApiResult<RoadmapPanel>> getEpics(
            @RequestParam(value = "programs", required = false) List<String> programNames,
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr
    ) throws ValidationException, ParseException {
        Date startDate = ValidateUtils.parseDate(startDateStr);
        Date endDate = ValidateUtils.parseDate(endDateStr);
        ValidateUtils.Friendly.assertFalse(endDate.before(startDate),
                "endDate should not before startDate");
        if (CollectionUtils.isNullOrEmpty(programNames)) {//default
            return ApiResult.ok(epicService.getAllEpics(startDate, endDate));
        }
        return ApiResult.ok(epicService.getEpicsInPrograms(programNames, startDate, endDate));
    }

}

