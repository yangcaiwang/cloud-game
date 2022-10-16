package com.springboot.app.domain.typehandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.domain.entity.word.WordCache;
import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 单词缓存
 * Json <-> WordCache
 */
@MappedTypes({WordCache.class})
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class WordCacheToJson implements TypeHandler<WordCache> {

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, WordCache wordCache, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, new ObjectMapper().writeValueAsString(wordCache));
    }

    @SneakyThrows
    @Override
    public WordCache getResult(ResultSet resultSet, String s) throws SQLException {
        return new ObjectMapper().readValue(resultSet.getString(s), WordCache.class);
    }

    @SneakyThrows
    @Override
    public WordCache getResult(ResultSet resultSet, int i) throws SQLException {
        return new ObjectMapper().readValue(resultSet.getString(i), WordCache.class);
    }

    @SneakyThrows
    @Override
    public WordCache getResult(CallableStatement callableStatement, int i) throws SQLException {
        return new ObjectMapper().readValue(callableStatement.getString(i), WordCache.class);
    }
}



