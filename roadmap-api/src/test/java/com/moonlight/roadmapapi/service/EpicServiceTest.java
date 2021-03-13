package com.moonlight.roadmapapi.service;

import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.mockRepo.MockRepo;
import com.moonlight.roadmapapi.repository.EpicRepository;
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

class EpicServiceTest {

    private EpicService epicService;
    private static String MOCK_INITIATIVE_ID = "0734b06b90224c1eb0f0923a1c040f82";

    @BeforeEach
    void setUp() throws ParseException {
        EpicRepository epicRepository = mock(EpicRepository.class);
        when(epicRepository.getEpicRows(
                eq(MOCK_INITIATIVE_ID), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());
        when(epicRepository.getEpicRows(
                eq(MOCK_INITIATIVE_ID), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());
        when(epicRepository.getEpicRows(
                eq(null), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());
        when(epicRepository.getEpicRows(
                eq(null), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());

        when(epicRepository.getEpicRowsInPrograms(
                anyList(), eq(MOCK_INITIATIVE_ID), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());
        when(epicRepository.getEpicRowsInPrograms(
                anyList(), eq(MOCK_INITIATIVE_ID), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());
        when(epicRepository.getEpicRowsInPrograms(
                anyList(), eq(null), any(Date.class), any(Date.class), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());
        when(epicRepository.getEpicRowsInPrograms(
                anyList(), eq(null), eq(null), eq(null), anyInt(), anyInt()))
                .thenReturn(MockRepo.getEpicRows());

        epicService = new EpicService(epicRepository);
    }

    @Test
    void getAllEpics() throws ParseException {
        RoadmapPanel actualRoadmapPanel = epicService.getAllEpics(
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getAllEpics(null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }


    @Test
    void getAllEpicsBy() throws ParseException {
        RoadmapPanel actualRoadmapPanel = epicService.getAllEpicsBy(
                MOCK_INITIATIVE_ID,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getAllEpicsBy(MOCK_INITIATIVE_ID, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getAllEpicsBy(null,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getAllEpicsBy(null, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }

    @Test
    void getEpicsInPrograms() throws ParseException {
        List<String> programNames = Stream.of("Cloud Import", "Liquid Objects").collect(Collectors.toList());
        RoadmapPanel actualRoadmapPanel = epicService.getEpicsInPrograms(
                programNames,
                MOCK_INITIATIVE_ID,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getEpicsInPrograms(
                programNames,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getEpicsInPrograms(
                programNames,
                null,
                MockRepo.dateList().get(0),
                MockRepo.dateList().get(3));
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());


        actualRoadmapPanel = epicService.getEpicsInPrograms(
                programNames, MOCK_INITIATIVE_ID, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getEpicsInPrograms(
                programNames, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());

        actualRoadmapPanel = epicService.getEpicsInPrograms(
                programNames, null, null, null);
        Assert.assertEquals(MockRepo.MOCK_ROW_SIZE, actualRoadmapPanel.getRows().size());
    }
}