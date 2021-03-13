package com.moonlight.roadmapapi.common.utils;

import com.moonlight.roadmapapi.common.exception.RoadmapException;
import com.moonlight.roadmapapi.entity.RoadmapPanel;
import com.moonlight.roadmapapi.entity.RoadmapResponseStatus;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RoadmapRowPropertyUtils {
    private RoadmapRowPropertyUtils() {
    }

    public static final int DEF_PAGE_SIZE = 1000;
    private static final Logger logger = LoggerFactory.getLogger(RoadmapRowPropertyUtils.class);

    public static RoadmapPanel buildRoadmapPanel(List<RoadmapRow> rows) {
        RoadmapPanel roadmapPanel = RoadmapPanel.builder()
                .maxDate(getMaxReleaseEndDate(rows))
                .minDate(getMinReleaseStartDate(rows))
                .rows(rows)
                .build();
        if (roadmapPanel.maxDate.before(
                roadmapPanel.minDate)) {
            logger.error("ReleaseDate is illogical.");
            logger.error("roadmapPanel.maxDate=" + roadmapPanel.maxDate.toString());
            logger.error("roadmapPanel.minDate=" + roadmapPanel.minDate.toString());
            throw new RoadmapException(RoadmapResponseStatus.SYSTEM_INTERNAL_ERROR);
        }
        return roadmapPanel;
    }

    public static RoadmapRow calculateCompletedPercent(RoadmapRow row) {
        ValidateUtils.internalNotNull(row);

        int total = row.getStoryPointsTotal();
        int accept = row.getStoryPointsAccepted();

        if (total < 0 || accept < 0) {
            row.setCompletedPercent(0);
            return row;
        }

        if (accept == 0) {
            row.setCompletedPercent(0);
            return row;
        }

        if (accept > total) {
            row.setCompletedPercent(100);
            return row;
        }

        row.setCompletedPercent(
                100 * row.getStoryPointsAccepted() / row.getStoryPointsTotal());
        return row;
    }

    public static Date getMaxReleaseEndDate(List<RoadmapRow> rows) {
        return rows.stream()
                .filter(row -> row.getReleaseEndDate() != null)
                .map(RoadmapRow::getReleaseEndDate)
                .distinct()
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public static Date getMinReleaseStartDate(List<RoadmapRow> rows) {
        return rows.stream()
                .filter(row -> row.getReleaseStartDate() != null)
                .map(RoadmapRow::getReleaseStartDate)
                .distinct()
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

}
