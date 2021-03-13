package com.moonlight.roadmapapi.service;

import com.amazonaws.util.CollectionUtils;
import com.moonlight.roadmapapi.common.utils.RoadmapRowPropertyUtils;
import com.moonlight.roadmapapi.entity.EpicState;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import com.moonlight.roadmapapi.repository.EpicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EpicService {
    private static final Logger logger = LoggerFactory.getLogger(EpicService.class);
    private EpicRepository epicRepo;

    @Autowired
    public EpicService(EpicRepository epicRepo) {
        this.epicRepo = epicRepo;
    }

    public RoadmapPanel setEpicProperties(List<RoadmapRow> rows) {
        if (CollectionUtils.isNullOrEmpty(rows)) {
            return RoadmapPanel.builder().build();
        }
        logger.info("getEpicRow:" + rows.size());
        rows.stream()
                .map(RoadmapRowPropertyUtils::calculateCompletedPercent)
                .forEach(row -> row.setStateMessage(
                        EpicState.getMessageBy(row.getStateCode())
                ));
        return RoadmapRowPropertyUtils.buildRoadmapPanel(rows);
    }

    public RoadmapPanel getAllEpics(Date startDate, Date endDate) {
        return setEpicProperties(
                epicRepo.getEpicRows(
                        null,
                        startDate,
                        endDate,
                        0,
                        RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

    public RoadmapPanel getAllEpicsBy(String initiativeId, Date startDate, Date endDate) {
        return setEpicProperties(
                epicRepo.getEpicRows(
                        initiativeId,
                        startDate,
                        endDate,
                        0,
                        RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

    public RoadmapPanel getEpicsInPrograms(List<String> programNames, Date startDate, Date endDate) {
        return setEpicProperties(
                epicRepo.getEpicRowsInPrograms(
                        programNames,
                        null,
                        startDate,
                        endDate,
                        0,
                        RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

    public RoadmapPanel getEpicsInPrograms(List<String> programNames, String initiativeId, Date startDate, Date endDate) {
        return setEpicProperties(
                epicRepo.getEpicRowsInPrograms(
                        programNames,
                        initiativeId,
                        startDate,
                        endDate,
                        0,
                        RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

}
