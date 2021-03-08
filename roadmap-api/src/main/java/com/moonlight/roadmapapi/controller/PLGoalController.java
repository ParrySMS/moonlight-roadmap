package com.moonlight.roadmapapi.controller;

import com.amazonaws.util.CollectionUtils;
import com.moonlight.roadmapapi.common.utils.ValidateUtils;
import com.moonlight.roadmapapi.entity.ApiResult;
import com.moonlight.roadmapapi.service.InitiativeService;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.service.PLGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pl-goals")
@CrossOrigin
public class PLGoalController {
    @Autowired
    private PLGoalService plGoalService;
    @Autowired
    private InitiativeService initService;

    @GetMapping("")
    public ResponseEntity<ApiResult<RoadmapPanel>> getPlGoals(
            @RequestParam(value = "programs", required = false) List<String> programNames,
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr) throws ParseException {
        Date startDate = ValidateUtils.parseDate(startDateStr);
        Date endDate = ValidateUtils.parseDate(endDateStr);
        ValidateUtils.Friendly.assertFalse(endDate.before(startDate),
                "endDate should not before startDate");
        if (CollectionUtils.isNullOrEmpty(programNames)) {
            return ApiResult.ok(plGoalService.getAllPLGoals(startDate, endDate));
        }
        return ApiResult.ok(plGoalService.getPLGoalsInPrograms(programNames, startDate, endDate));
    }

    @GetMapping("/{plGoalId}/initiatives")
    public ResponseEntity<ApiResult<RoadmapPanel>> getInitsInOnePL(
            @PathVariable("plGoalId") String plGoalId,
            @RequestParam(value = "programs", required = false) List<String> programNames,
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr) throws ParseException {
        ValidateUtils.notNullOrEmpty(plGoalId, "plGoalId");
        Date startDate = ValidateUtils.parseDate(startDateStr);
        Date endDate = ValidateUtils.parseDate(endDateStr);
        ValidateUtils.Friendly.assertFalse(endDate.before(startDate),
                "endDate should not before startDate");
        if (CollectionUtils.isNullOrEmpty(programNames)) {
            return ApiResult.ok(initService.getAllInitsBy(plGoalId, startDate, endDate));
        }
        return ApiResult.ok(initService.getInitsInPrograms(programNames, plGoalId, startDate, endDate));
    }
}