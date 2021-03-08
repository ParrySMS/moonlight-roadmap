package com.moonlight.roadmapapi.repository;

import com.moonlight.roadmapapi.common.mybatis.handler.Shema;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface PLGoalRepository {

    String selectPropertiesFromPlGoalAsPL = "SELECT PL.id,PL.name,PL.state,PL.report_color,PL.release_start_date,PL.release_end_date,PL.story_points_total,PL.story_points_accepted " +
            " FROM " + Shema.SCHEMA_ROADMAP + ".pl_goal AS PL ";

    @Select({"<script> " +
            selectPropertiesFromPlGoalAsPL +
            "<where> " +
            "  <if test='startDate!=null and endDate!=null'>" +
            "    AND release_start_date >= #{startDate} AND release_end_date &lt;= #{endDate} " +
            "  </if>" +
            "</where>" +
            " LIMIT #{offset},#{pageSize};" +
            "</script>"})
    @Results({
            @Result(property = "stateCode", column = "state_code"),
            @Result(property = "reportColor", column = "report_color"),
            @Result(property = "releaseStartDate", column = "release_start_date", javaType = Date.class),
            @Result(property = "releaseEndDate", column = "release_end_date", javaType = Date.class),
            @Result(property = "storyPointsTotal", column = "story_points_total"),
            @Result(property = "storyPointsAccepted", column = "story_points_accepted"),
    })
    List<RoadmapRow> getPLRows(Date startDate, Date endDate, int offset, int pageSize);

    @Select({"<script> " +
            selectPropertiesFromPlGoalAsPL +
            " INNER JOIN " + Shema.SCHEMA_ROADMAP + ".pl_goal_program AS PLPG on PLPG.pl_goal_id = PL.id " +
            "<where>" +
            "  <if test='programNames!=null'>" +
            "    PLPG.program_name IN " +
            "      <foreach collection='programNames' item='programName' open='(' separator=',' close=')'> " +
            "        #{programName} " +
            "      </foreach>" +
            "  </if>" +
            "  <if test='startDate!=null and endDate!=null'>" +
            "    AND release_start_date >= #{startDate} AND release_end_date &lt;= #{endDate} " +
            "  </if>" +
            "</where>" +
            " LIMIT #{offset},#{pageSize};" +
            "</script>"})
    @Results({
            @Result(property = "stateCode", column = "state_code"),
            @Result(property = "reportColor", column = "report_color"),
            @Result(property = "releaseStartDate", column = "release_start_date", javaType = Date.class),
            @Result(property = "releaseEndDate", column = "release_end_date", javaType = Date.class),
            @Result(property = "storyPointsTotal", column = "story_points_total"),
            @Result(property = "storyPointsAccepted", column = "story_points_accepted"),
    })
    List<RoadmapRow> getPLRowsInPrograms(@Param("programNames") List<String> programNames, Date startDate, Date endDate, int offset, int pageSize);

}
