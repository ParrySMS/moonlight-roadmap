package com.moonlight.roadmapapi.controller;

import com.amazonaws.util.CollectionUtils;
import com.moonlight.roadmapapi.common.exception.ValidationException;
import com.moonlight.roadmapapi.common.utils.ValidateUtils;
import com.moonlight.roadmapapi.entity.ApiResult;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.service.EpicService;
import com.moonlight.roadmapapi.service.InitiativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/initiatives")
@CrossOrigin
public class InitiativeController {
    @Autowired
    private InitiativeService initService;

    @Autowired
    private EpicService epicService;

    @GetMapping("")
    public ResponseEntity<ApiResult<RoadmapPanel>> getInits(
            @RequestParam(value = "programs", required = false) List<String> programNames,
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr) throws ParseException {
        Date startDate = ValidateUtils.parseDate(startDateStr);
        Date endDate = ValidateUtils.parseDate(endDateStr);
        ValidateUtils.Friendly.assertFalse(endDate.before(startDate),
                "endDate should not before startDate");
        if (CollectionUtils.isNullOrEmpty(programNames)) {//default
            return ApiResult.ok(initService.getAllInits(startDate, endDate));
        }
        return ApiResult.ok(initService.getInitsInPrograms(programNames, startDate, endDate));
    }

    @GetMapping("/{initiativeId}/epics")
    public ResponseEntity<ApiResult<RoadmapPanel>> getEpicsInOneInitiative(
            @PathVariable("initiativeId") String initiativeId,
            @RequestParam(value = "programs", required = false) List<String> programNames,
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr
    ) throws ValidationException, ParseException {
        Date startDate = ValidateUtils.parseDate(startDateStr);
        Date endDate = ValidateUtils.parseDate(endDateStr);
        ValidateUtils.Friendly.assertFalse(endDate.before(startDate),
                "endDate should not before startDate");
        ValidateUtils.notNullOrEmpty(initiativeId, "initiativeId");
        // As the optional params, programNames is null
        if (CollectionUtils.isNullOrEmpty(programNames)) {
            return ApiResult.ok(epicService.getAllEpicsBy(initiativeId, startDate, endDate));
        }
        return ApiResult.ok(epicService.getEpicsInPrograms(programNames, initiativeId, startDate, endDate));
    }
}
