package com.moonlight.roadmapapi.repository;

import com.moonlight.roadmapapi.common.mybatis.handler.Shema;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface InitiativeRepository {
    String SELECT_PROPERTIES_FROM_INITIATIVE_AS_I =
            " SELECT I.id," +
                    "I.name," +
                    "I.state," +
                    "I.report_color," +
                    "I.release_start_date," +
                    "I.release_end_date," +
                    "I.story_points_total," +
                    "I.story_points_accepted " +
                    "FROM " + Shema.SCHEMA_ROADMAP + ".initiative AS I ";

    @Select({"<script> " +
            SELECT_PROPERTIES_FROM_INITIATIVE_AS_I +
            "<where> " +
            "  <if test='plGoalId!=null'>" +
            "    pl_goal_id = #{plGoalId} " +
            "  </if>" +
            "  <if test='startDate!=null and endDate!=null'>" +
            "    AND release_start_date >= #{startDate} AND release_end_date &lt;= #{endDate} " +
            "  </if>" +
            "</where> " +
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
    List<RoadmapRow> getInitRows(String plGoalId, Date startDate, Date endDate, int offset, int pageSize);

    @Select({"<script> " +
            SELECT_PROPERTIES_FROM_INITIATIVE_AS_I +
            " INNER JOIN " + Shema.SCHEMA_ROADMAP + ".initiative_program AS IP on IP.initiative_id = I.id " +
            "<where>" +
            "  <if test='programNames!=null'>" +
            "    IP.program_name IN " +
            "      <foreach collection='programNames' item='programName' open='(' separator=',' close=')'> " +
            "        #{programName} " +
            "      </foreach>" +
            "  </if>" +
            "  <if test='plGoalId!=null'>" +
            "    AND pl_goal_id = #{plGoalId} " +
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
    List<RoadmapRow> getInitRowsInPrograms(@Param("programNames") List<String> programNames, String plGoalId, Date startDate, Date endDate, int offset, int pageSize);


}
