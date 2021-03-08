package com.moonlight.roadmapapi.service;

import com.moonlight.roadmapapi.mockRepo.MockRepo;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.repository.PLGoalRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PLGoalServiceTest {
    private PLGoalService plGoalService;

    @BeforeEach
    void setUp() throws ParseException {
        PLGoalRepository plGoalRepository = mock(PLGoalRepository.class);
        when(plGoalRepository.getPLRows(
                any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getPLRows());
        when(plGoalRepository.getPLRows(
                eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getPLRows());

        when(plGoalRepository.getPLRowsInPrograms(
                anyList(), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getPLRows());
        when(plGoalRepository.getPLRowsInPrograms(
                anyList(), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getPLRows());
        plGoalService = new PLGoalService(plGoalRepository);
    }

    @Test
    void getAllPLGoals() throws ParseException {
        RoadmapPanel actualRoadmapPanel = plGoalService.getAllPLGoals(
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = plGoalService.getAllPLGoals(null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }

    @Test
    void getPLGoalsInPrograms() throws ParseException {
        List<String> programNames = Stream.of("Cloud Import", "Liquid Objects").collect(Collectors.toList());
        RoadmapPanel actualRoadmapPanel = plGoalService.getPLGoalsInPrograms(
                programNames,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = plGoalService.getPLGoalsInPrograms(
                programNames, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }
}