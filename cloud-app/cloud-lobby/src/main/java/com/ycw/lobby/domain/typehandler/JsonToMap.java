package com.ycw.lobby.domain.typehandler;

import com.ycw.common.converter.JsonConvert;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Json <-> Map<Long, String>
 */
@MappedTypes({Map.class})
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class JsonToMap implements TypeHandler<Map<Long, String>> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Map<Long, String> map, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, JsonConvert.mapLongStringToString(map));
    }

    @Override
    public Map<Long, String> getResult(ResultSet resultSet, String s) throws SQLException {
        return JsonConvert.stringToMapLongString(resultSet.getString(s));
    }

    @Override
    public Map<Long, String> getResult(ResultSet resultSet, int i) throws SQLException {
        return JsonConvert.stringToMapLongString(resultSet.getString(i));
    }

    @Override
    public Map<Long, String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        return JsonConvert.stringToMapLongString(callableStatement.getString(i));
    }
}
