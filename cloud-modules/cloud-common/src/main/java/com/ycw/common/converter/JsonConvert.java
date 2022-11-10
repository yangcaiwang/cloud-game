package com.ycw.common.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类型转换器
 *
 */
public class JsonConvert {
    /**
     * String转化Map
     */
    public static Map<Long, String> stringToMapLongString(String str) {
        return Arrays.stream(str.split(";")).collect(Collectors.toMap(s -> Long.parseLong(s.split("-")[0]), s -> s.split("-")[1]));
    }

    /**
     * Map转化String
     */
    public static String mapLongStringToString(Map<Long, String> map) {
        return map.entrySet().stream().map(entry -> entry.getKey().toString().concat("-").concat(entry.getValue())).collect(Collectors.joining(";"));
    }

    /**
     * List<Integer>转化String
     */
    public static String listIntToString(List<Integer> list) {
        List<String> listString = list.stream().map(String::valueOf).collect(Collectors.toList());
        return String.join(";", listString);
    }

    /**
     * String转化List<Integer>
     */
    public static List<Integer> stringToListInt(String str) {
        return Arrays.stream(str.split(";")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
