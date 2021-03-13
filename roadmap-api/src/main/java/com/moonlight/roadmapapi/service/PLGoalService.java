package com.moonlight.roadmapapi.service;

import com.amazonaws.util.CollectionUtils;
import com.moonlight.roadmapapi.common.utils.RoadmapRowPropertyUtils;
import com.moonlight.roadmapapi.entity.PLGoalState;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import com.moonlight.roadmapapi.repository.PLGoalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PLGoalService {
    private static final Logger logger = LoggerFactory.getLogger(PLGoalService.class);
    private PLGoalRepository plGoalRepo;

    @Autowired
    public PLGoalService(PLGoalRepository plGoalRepo) {
        this.plGoalRepo = plGoalRepo;
    }

    public RoadmapPanel setPLProperties(List<RoadmapRow> rows) {
        logger.info("getPLRow:" + rows.size());
        if (CollectionUtils.isNullOrEmpty(rows)) {
            return RoadmapPanel.builder().build();
        }
        rows.stream()
                .map(RoadmapRowPropertyUtils::calculateCompletedPercent)
                .forEach(row -> row.setStateMessage(
                        PLGoalState.getMessage(row.getStateCode())
                ));
        return RoadmapRowPropertyUtils.buildRoadmapPanel(rows);
    }

    public RoadmapPanel getAllPLGoals(Date startDate, Date endDate) {
        return setPLProperties(
                plGoalRepo.getPLRows(
                        startDate,
                        endDate,
                        0,
                        RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

    public RoadmapPanel getPLGoalsInPrograms(List<String> programNames, Date startDate, Date endDate) {
        return setPLProperties(
                plGoalRepo.getPLRowsInPrograms(
                        programNames,
                        startDate,
                        endDate,
                        0,
                        RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

}
