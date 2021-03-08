package com.moonlight.roadmapapi.mockRepo;

import com.moonlight.roadmapapi.entity.EpicState;
import com.moonlight.roadmapapi.entity.InitiativeState;
import com.moonlight.roadmapapi.entity.PLGoalState;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import com.moonlight.roadmapapi.entity.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockRepo {
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
    public final static int MOCK_ROW_SIZE = 3 ;

    public static List<RoadmapRow> getEpicRows() throws ParseException {
        List<RoadmapRow> rows = setPLInitEpicRow(Entity.newRows(MOCK_ROW_SIZE));
        rows.get(0).setStateCode(EpicState.READY_TO_START.id);
        rows.get(1).setStateCode(EpicState.ACCEPTED.id);
        rows.get(2).setStateCode(EpicState.PENDING_APPROVAL.id);
        return rows;
    }

    public static List<RoadmapRow> getInitRows() throws ParseException {
        List<RoadmapRow> rows = setPLInitEpicRow(Entity.newRows(MOCK_ROW_SIZE));
        rows.get(0).setStateCode(InitiativeState.IN_PROGRESS.id);
        rows.get(1).setStateCode(InitiativeState.ACCEPTED.id);
        rows.get(2).setStateCode(InitiativeState.NOT_STARTED.id);
        return rows;
    }

    public static List<RoadmapRow> getPLRows() throws ParseException {
        List<RoadmapRow> rows = setPLInitEpicRow(Entity.newRows(MOCK_ROW_SIZE));
        rows.get(0).setStateCode(PLGoalState.IN_PROGRESS.id);
        rows.get(1).setStateCode(PLGoalState.Done.id);
        rows.get(2).setStateCode(PLGoalState.NOT_STARTED.id);
        return rows;
    }

    private static List<RoadmapRow> setPLInitEpicRow(List<RoadmapRow> rows) throws  ParseException{
        rows.get(0)
                .setId("id0")
                .setName("name0")
                .setReportColor("#ff4e4d")
                .setReleaseStartDate(dateList().get(0))
                .setReleaseEndDate(dateList().get(1))
                .setStoryPointsAccepted(1)
                .setStoryPointsTotal(30);
        rows.get(1)
                .setId("id1")
                .setName("name1")
                .setReportColor("#ff4e4d")
                .setReleaseStartDate(dateList().get(1))
                .setReleaseEndDate(dateList().get(2))
                .setStoryPointsAccepted(30)
                .setStoryPointsTotal(30);
        rows.get(2)
                .setId("id2")
                .setName("name2")
                .setReportColor("#ff4e4d")
                .setReleaseStartDate(dateList().get(2))
                .setReleaseEndDate(dateList().get(3))
                .setStoryPointsAccepted(0)
                .setStoryPointsTotal(30);
        return rows;
    }

    public static List<Date> dateList() throws ParseException {
        Date dateA = simpleDateFormat.parse("2011/10/30");
        Date dateB = simpleDateFormat.parse("2015/10/30");
        Date dateC = simpleDateFormat.parse("2018/10/30");
        Date dateD = simpleDateFormat.parse("2020/10/30");
        return Stream.of(dateA,dateB,dateC,dateD).collect(Collectors.toList());
    }
}