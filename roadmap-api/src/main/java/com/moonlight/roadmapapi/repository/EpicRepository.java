package com.moonlight.roadmapapi.repository;

import com.moonlight.roadmapapi.common.mybatis.handler.Shema;
import com.moonlight.roadmapapi.entity.RoadmapRow;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface EpicRepository {
    // mybatis consider <= as a new <script>, so just use &lt;=
    // Refer: https://blog.csdn.net/xuanzhangran/article/details/60329357?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task

    String selectPropertiesFromEpicAsE = " SELECT E.id,E.name,E.state,E.report_color,E.release_start_date,E.release_end_date,E.story_points_total,E.story_points_accepted " +
            " FROM " + Shema.SCHEMA_ROADMAP + ".epic AS E ";

    @Select({"<script> " +
            selectPropertiesFromEpicAsE +
            "<where> " +
            "  <if test='initiativeId!=null'>" +
            "    initiative_id = #{initiativeId} " +
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
    List<RoadmapRow> getEpicRows(String initiativeId, Date startDate, Date endDate, int offset, int pageSize);

    @Select({"<script>" +
            selectPropertiesFromEpicAsE +
            " INNER JOIN " + Shema.SCHEMA_ROADMAP + ".epic_program AS EP on EP.epic_id = E.id " +
            "<where>" +
            "  <if test='programNames!=null'>" +
            "    EP.program_name IN " +
            "      <foreach collection='programNames' item='programName' open='(' separator=',' close=')'> " +
            "        #{programName} " +
            "      </foreach>" +
            "  </if>" +
            "  <if test='initiativeId!=null'>" +
            "    AND initiative_id = #{initiativeId} " +
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
    List<RoadmapRow> getEpicRowsInPrograms(List<String> programNames, String initiativeId, Date startDate, Date endDate, int offset, int pageSize);

}
