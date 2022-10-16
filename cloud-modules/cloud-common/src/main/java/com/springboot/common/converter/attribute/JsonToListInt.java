package com.springboot.common.converter.attribute;


import com.springboot.common.converter.JsonConvert;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 实现json的序列化和反序列化, 只需要对需要转换的属性打上@convet主机即可
 */
@Converter
public class JsonToListInt implements AttributeConverter<List<Integer>, String> {
    @Override
    public String convertToDatabaseColumn(List<Integer> value) {
        return JsonConvert.listIntToString(value);
    }

    @Override
    public List<Integer> convertToEntityAttribute(String s) {
        return JsonConvert.stringToListInt(s);
    }
}


