package com.xlotus.lib.http.utils;

import java.util.List;
import java.util.Map;

public class CollectionsUtil {
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
