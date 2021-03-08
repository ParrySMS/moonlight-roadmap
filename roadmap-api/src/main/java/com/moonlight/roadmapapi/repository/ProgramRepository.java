package com.moonlight.roadmapapi.repository;

import com.moonlight.roadmapapi.common.mybatis.handler.Shema;
import com.moonlight.roadmapapi.entity.Program;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProgramRepository {
    @Select(" SELECT id,name " +
            " FROM " + Shema.SCHEMA_ROADMAP + ".program " +
            " LIMIT #{offset},#{pageSize};")
    List<Program> getPrograms(int offset, int pageSize);
}
