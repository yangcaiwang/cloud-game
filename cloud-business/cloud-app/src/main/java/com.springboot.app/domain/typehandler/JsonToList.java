package com.springboot.app.domain.typehandler;

import com.springboot.common.converter.JsonConvert;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Json <-> List<Integer>
 */
//使用纯注解方式，这里需要配置MappedTypes注解，不然会不使用对应处理器
//从而导致该类型处理器不会生效，并出现`No enum constant...`异常等情况
@MappedTypes({List.class})
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class JsonToList implements TypeHandler<List<Integer>> {

    //设置参数，在数据库中存储的应该为状态码，这个方法用来对数据库进行操作时，给数据库传递枚举参数时传递的是状态码
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<Integer> list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, JsonConvert.listIntToString(list));
    }

    //用来查询操作，直接利用结果集返回，columnName为列名
    @Override
    public List<Integer> getResult(ResultSet resultSet, String s) throws SQLException {
        return JsonConvert.stringToListInt(resultSet.getString(s));
    }

    //用来查询操作，直接利用结果集返回，columnIndex是列索引
    @Override
    public List<Integer> getResult(ResultSet resultSet, int i) throws SQLException {
        return JsonConvert.stringToListInt(resultSet.getString(i));
    }

    //用来存储过程的查询操作，直接利用结果集返回，columnIndex是列索引
    @Override
    public List<Integer> getResult(CallableStatement callableStatement, int i) throws SQLException {
        return JsonConvert.stringToListInt(callableStatement.getString(i));
    }
}