package com.moonlight.roadmapapi.common.mybatis.handler;

import com.google.common.base.Strings;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.UUID;

@MappedTypes(UUID.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class UuidToVarchar implements TypeHandler<UUID> {
	@Override
	public void setParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setObject(i, null, Types.VARCHAR);
		} else {
			ps.setObject(i, parameter.toString(), Types.VARCHAR);
		}
	}

	@Override
	public UUID getResult(ResultSet rs, String columnName) throws SQLException {
		return toUUID(rs.getString(columnName));
	}

	@Override
	public UUID getResult(ResultSet rs, int columnIndex) throws SQLException {
		return toUUID(rs.getString(columnIndex));
	}

	@Override
	public UUID getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return toUUID(cs.getString(columnIndex));
	}

	private static UUID toUUID(String val) throws SQLException {
		if (Strings.isNullOrEmpty(val)) {
			return null;
		}
		try {
			return UUID.fromString(val);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
