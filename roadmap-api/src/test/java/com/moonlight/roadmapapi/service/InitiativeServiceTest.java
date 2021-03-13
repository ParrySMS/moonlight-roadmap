package com.moonlight.roadmapapi.service;

import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.mockRepo.MockRepo;
import com.moonlight.roadmapapi.repository.InitiativeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InitiativeServiceTest {
    private InitiativeService initiativeService;
    private static String MOCK_PL_GOAL_ID = "0734b06b90224c1eb0f0923a1c040f82";

    @BeforeEach
    void setUp() throws ParseException {
        InitiativeRepository initiativeRepository = mock(InitiativeRepository.class);
        when(initiativeRepository.getInitRows(
                eq(MOCK_PL_GOAL_ID), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());
        when(initiativeRepository.getInitRows(
                eq(MOCK_PL_GOAL_ID), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());
        when(initiativeRepository.getInitRows(
                eq(null), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());
        when(initiativeRepository.getInitRows(
                eq(null), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());

        when(initiativeRepository.getInitRowsInPrograms(
                anyList(), eq(MOCK_PL_GOAL_ID), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());
        when(initiativeRepository.getInitRowsInPrograms(
                anyList(), eq(MOCK_PL_GOAL_ID), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());
        when(initiativeRepository.getInitRowsInPrograms(
                anyList(), eq(null), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());
        when(initiativeRepository.getInitRowsInPrograms(
                anyList(), eq(null), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getInitRows());
        initiativeService = new InitiativeService(initiativeRepository);
    }

    @Test
    void getAllInits() throws ParseException {
        RoadmapPanel actualRoadmapPanel = initiativeService.getAllInits(
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getAllInits(null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }

    @Test
    void getAllInitsBy() throws ParseException {
        RoadmapPanel actualRoadmapPanel = initiativeService.getAllInitsBy(
                MOCK_PL_GOAL_ID,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getAllInitsBy(MOCK_PL_GOAL_ID, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getAllInitsBy(null,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getAllInitsBy(null, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }

    @Test
    void getInitsInPrograms() throws ParseException {
        List<String> programNames = Stream.of("Cloud Import", "Liquid Objects").collect(Collectors.toList());
        RoadmapPanel actualRoadmapPanel = initiativeService.getInitsInPrograms(
                programNames,
                MOCK_PL_GOAL_ID,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getInitsInPrograms(
                programNames,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getInitsInPrograms(
                programNames,
                null,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());


        actualRoadmapPanel = initiativeService.getInitsInPrograms(
                programNames, MOCK_PL_GOAL_ID, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getInitsInPrograms(
                programNames, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = initiativeService.getInitsInPrograms(
                programNames, null, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }
}
