package com.moonlight.roadmapapi.service;

import com.amazonaws.util.CollectionUtils;
import com.moonlight.roadmapapi.common.utils.RoadmapRowPropertyUtils;
import com.moonlight.roadmapapi.repository.InitiativeRepository;
import com.moonlight.roadmapapi.entity.InitiativeState;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InitiativeService {
    private static final Logger logger = LoggerFactory.getLogger(InitiativeService.class);
    private InitiativeRepository initRepo;

    @Autowired //more easy to mock for test
    public InitiativeService(InitiativeRepository initRepo) {
        this.initRepo = initRepo;
    }

    public RoadmapPanel setInitProperties(List<RoadmapRow> rows) {
        logger.info("getInitRow:" + rows.size());
        if (CollectionUtils.isNullOrEmpty(rows)) {
            return RoadmapPanel.builder().build();
        }
        rows.stream()
                .map(RoadmapRowPropertyUtils::calculateCompletedPercent)
                .forEach(row -> row.setStateMessage(
                        InitiativeState.getMessageBy(row.getStateCode())
                ));
        return RoadmapRowPropertyUtils.buildRoadmapPanel(rows);
    }

    public RoadmapPanel getAllInits(Date startDate, Date endDate) {
        return setInitProperties(
                initRepo.getInitRows(
                        null, startDate, endDate, 0, RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

    public RoadmapPanel getAllInitsBy(String plGoalId, Date startDate, Date endDate) {
        return setInitProperties(
                initRepo.getInitRows(
                        plGoalId, startDate, endDate, 0, RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

    public RoadmapPanel getInitsInPrograms(List<String> programNames, Date startDate, Date endDate) {
        return setInitProperties(
                initRepo.getInitRowsInPrograms(
                        programNames, null, startDate, endDate, 0, RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }

    public RoadmapPanel getInitsInPrograms(List<String> programNames, String plGoalId, Date startDate, Date endDate) {
        return setInitProperties(
                initRepo.getInitRowsInPrograms(
                        programNames, plGoalId, startDate, endDate, 0, RoadmapRowPropertyUtils.DEF_PAGE_SIZE));
    }
}
