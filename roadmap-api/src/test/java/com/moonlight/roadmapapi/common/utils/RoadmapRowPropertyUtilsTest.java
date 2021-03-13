package com.moonlight.roadmapapi.common.utils;

import com.moonlight.roadmapapi.common.exception.RoadmapException;
import com.moonlight.roadmapapi.entity.Entity;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


class RoadmapRowPropertyUtilsTest {
    private SimpleDateFormat simpleDateFormat;

    @BeforeEach
    void setUp() {
        simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
    }

    @Test
    void calculateCompletedPercentNormal() {
        RoadmapRow row1 = new RoadmapRow();
        row1.setStoryPointsAccepted(1);
        row1.setStoryPointsTotal(3);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row1);
        Assert.assertEquals(33, row1.getCompletedPercent());

        RoadmapRow row2 = new RoadmapRow();
        row2.setStoryPointsAccepted(10);
        row2.setStoryPointsTotal(10);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row2);
        Assert.assertEquals(100, row2.getCompletedPercent());
    }

    @Test
    void calculateCompletedPercentZero() {
        RoadmapRow row1 = new RoadmapRow();
        row1.setStoryPointsAccepted(0);
        row1.setStoryPointsTotal(3);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row1);
        Assert.assertEquals(0, row1.getCompletedPercent());

        RoadmapRow row2 = new RoadmapRow();
        row2.setStoryPointsAccepted(10);
        row2.setStoryPointsTotal(0);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row2);
        Assert.assertEquals(100, row2.getCompletedPercent());
    }

    @Test
    void calculateCompletePercantEmpty() {
        RoadmapRow row1 = new RoadmapRow();
        RoadmapRowPropertyUtils.calculateCompletedPercent(row1);
        Assert.assertEquals(0, row1.getCompletedPercent());
    }

    @Test
    void calculateCompletedPercentNull() {
        assertThrows(RoadmapException.class, () -> {
            RoadmapRowPropertyUtils.calculateCompletedPercent(null);
        });
    }

    @Test
    void calculateCompletedPercentEdge() {
        RoadmapRow row1 = new RoadmapRow();
        row1.setStoryPointsAccepted(Integer.MAX_VALUE);
        row1.setStoryPointsTotal(Integer.MAX_VALUE);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row1);
        //default value of  int completedPercent is 0
        Assert.assertEquals(0, row1.getCompletedPercent());
    }

    @Test
    void calculateCompletedPercentNegative() {
        RoadmapRow row1 = new RoadmapRow();
        row1.setStoryPointsAccepted(-200);
        row1.setStoryPointsTotal(200);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row1);
        Assert.assertEquals(0, row1.getCompletedPercent());

        RoadmapRow row2 = new RoadmapRow();
        row2.setStoryPointsAccepted(200);
        row2.setStoryPointsTotal(-200);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row2);
        Assert.assertEquals(0, row2.getCompletedPercent());

        RoadmapRow row3 = new RoadmapRow();
        row3.setStoryPointsAccepted(-100);
        row3.setStoryPointsTotal(-100);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row3);
        Assert.assertEquals(0, row3.getCompletedPercent());

        RoadmapRow row4 = new RoadmapRow();
        row4.setStoryPointsAccepted(Integer.MAX_VALUE);
        row4.setStoryPointsTotal(Integer.MIN_VALUE);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row4);
        Assert.assertEquals(0, row4.getCompletedPercent());
    }

    @Test
    void calculateCompletedPercentTotalSmallerThanAccepted() {
        RoadmapRow row1 = new RoadmapRow();
        row1.setStoryPointsAccepted(200);
        row1.setStoryPointsTotal(100);
        RoadmapRowPropertyUtils.calculateCompletedPercent(row1);
        Assert.assertEquals(100, row1.getCompletedPercent());

        RoadmapRow row2 = new RoadmapRow();
        row2.setStoryPointsAccepted(1);
        row2.setStoryPointsTotal(Integer.MIN_VALUE);//negative
        RoadmapRowPropertyUtils.calculateCompletedPercent(row1);
        Assert.assertEquals(0, row2.getCompletedPercent());
    }

    @Test
    void getMaxDateNormal() throws ParseException {
        Date smallDate = simpleDateFormat.parse("2018/11/30");
        Date bigDate = simpleDateFormat.parse("2020/11/30");
        List<RoadmapRow> rows = Entity.newRows(2);
        rows.get(0).setReleaseEndDate(smallDate);
        rows.get(1).setReleaseEndDate(bigDate);
        Date maxDate = RoadmapRowPropertyUtils.getMaxReleaseEndDate(rows);
        Assert.assertEquals(bigDate, maxDate);
    }

    @Test
    void getMinDateNormal() throws ParseException {
        Date smallDate = simpleDateFormat.parse("2018/11/30");
        Date bigDate = simpleDateFormat.parse("2020/11/30");
        List<RoadmapRow> rows = Entity.newRows(2);
        rows.get(0).setReleaseStartDate(smallDate);
        rows.get(1).setReleaseStartDate(bigDate);
        Date minDate = RoadmapRowPropertyUtils.getMinReleaseStartDate(rows);
        Assert.assertEquals(smallDate, minDate);
    }

    @Test
    void getDateInSame() throws ParseException {
        Date oneDate = simpleDateFormat.parse("2018/11/30");
        List<RoadmapRow> rows = Entity.newRows(2);
        rows.get(0)
                .setReleaseStartDate(oneDate)
                .setReleaseEndDate(oneDate);
        rows.get(1)
                .setReleaseStartDate(oneDate)
                .setReleaseEndDate(oneDate);
        Date minDate = RoadmapRowPropertyUtils.getMinReleaseStartDate(rows);
        Date maxDate = RoadmapRowPropertyUtils.getMaxReleaseEndDate(rows);
        Assert.assertEquals(oneDate, minDate);
        Assert.assertEquals(oneDate, maxDate);
    }

    @Test
    void getDateOneNullDate() throws ParseException {
        Date oneDate = simpleDateFormat.parse("2018/11/30");
        List<RoadmapRow> rows = Entity.newRows(2);
        rows.get(0)
                .setReleaseStartDate(null)
                .setReleaseEndDate(null);
        rows.get(1)
                .setReleaseStartDate(oneDate)
                .setReleaseEndDate(oneDate);
        Date minDate = RoadmapRowPropertyUtils.getMinReleaseStartDate(rows);
        Date maxDate = RoadmapRowPropertyUtils.getMaxReleaseEndDate(rows);
        Assert.assertEquals(oneDate, minDate);
        Assert.assertEquals(oneDate, maxDate);
    }

    @Test
    void getDateTwoNullDate() throws ParseException {
        List<RoadmapRow> rows = Entity.newRows(2);
        rows.get(0)
                .setReleaseStartDate(null)
                .setReleaseEndDate(null);
        rows.get(1)
                .setReleaseStartDate(null)
                .setReleaseEndDate(null);
        Date minDate = RoadmapRowPropertyUtils.getMinReleaseStartDate(rows);
        Date maxDate = RoadmapRowPropertyUtils.getMaxReleaseEndDate(rows);
        Assert.assertNull(minDate);
        Assert.assertNull(maxDate);
    }

    @Test
    void getDateEmptyRows() {
        List<RoadmapRow> rows = new ArrayList<>();
        Date minDate = RoadmapRowPropertyUtils.getMinReleaseStartDate(rows);
        Date maxDate = RoadmapRowPropertyUtils.getMaxReleaseEndDate(rows);
        Assert.assertNull(minDate);
        Assert.assertNull(maxDate);
    }

    @Test
    void getDateEdge() throws ParseException {
        List<RoadmapRow> rows = Entity.newRows(3);
        Date zeroDate = simpleDateFormat.parse("0000/00/00");
        Date minRangeDate = simpleDateFormat.parse("1000/01/01");
        Date maxRangeDate = simpleDateFormat.parse("9999/12/31");
        rows.get(0)
                .setReleaseStartDate(zeroDate)
                .setReleaseEndDate(zeroDate);
        rows.get(1)
                .setReleaseStartDate(minRangeDate)
                .setReleaseEndDate(minRangeDate);
        rows.get(2)
                .setReleaseStartDate(maxRangeDate)
                .setReleaseEndDate(maxRangeDate);
        Date minDate = RoadmapRowPropertyUtils.getMinReleaseStartDate(rows);
        Date maxDate = RoadmapRowPropertyUtils.getMaxReleaseEndDate(rows);
        Assert.assertEquals(zeroDate, minDate);
        Assert.assertEquals(maxRangeDate, maxDate);
    }
}