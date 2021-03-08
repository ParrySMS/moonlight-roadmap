package com.moonlight.roadmapapi.controller;

import com.moonlight.roadmapapi.entity.Program;
import com.moonlight.roadmapapi.entity.ApiResult;
import com.moonlight.roadmapapi.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
@CrossOrigin
public class ProgramController {
    @Autowired
    ProgramService programService;

    @GetMapping("")
    public ResponseEntity<ApiResult<List<Program>>> getPrograms() {
        return ApiResult.ok(programService.getPrograms());
    }

}
