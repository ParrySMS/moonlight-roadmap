package com.moonlight.roadmapapi.service;

import com.moonlight.roadmapapi.common.utils.RoadmapRowPropertyUtils;
import com.moonlight.roadmapapi.entity.Program;
import com.moonlight.roadmapapi.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private ProgramRepository programRepos;

    @Autowired
    public ProgramService(ProgramRepository programRepos) {
        this.programRepos = programRepos;
    }

    public List<Program> getPrograms() {
        return programRepos.getPrograms(0, RoadmapRowPropertyUtils.DEF_PAGE_SIZE);
    }

}
