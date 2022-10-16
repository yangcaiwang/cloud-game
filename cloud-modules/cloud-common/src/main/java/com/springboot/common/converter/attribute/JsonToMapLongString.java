//package com.springboot.common.converter.attribute;
//
//import com.springboot.common.converter.JsonConvert;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.util.Map;
//
///**
// * 实现json的序列化和反序列化, 只需要对需要转换的属性打上@convet主机即可
// */
//@Converter
//public class JsonToMapLongString implements AttributeConverter<Map<Long,String>, String> {
//    @Override
//    public String convertToDatabaseColumn(Map<Long, String> map) {
//        return JsonConvert.mapLongStringToString(map);
//    }
//
//    @Override
//    public Map<Long, String> convertToEntityAttribute(String s) {
//        return JsonConvert.stringToMapLongString(s);
//    }
//}
