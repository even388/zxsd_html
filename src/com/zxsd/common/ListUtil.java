package com.zxsd.common;

import java.util.ArrayList;
import java.util.List;

/**
 * TEAM3
 *
 * @author qwb
 * @Description
 * @date 2018年9月3日 下午5:28:30
 */
public class ListUtil {

    public static <T> List<List<T>> split(List<T> source, int limit) {
        if (source == null || limit < 1) {
            throw new IllegalArgumentException();
        }
        List<T> tl = new ArrayList<>(source);
        List<List<T>> result = new ArrayList<>();
        if (tl.size() <= limit) {
            result.add(tl);
            return result;
        }
        int part = tl.size() / limit;// 分批数
        for (int i = 0; i < part; i++) {
            List<T> listPart = tl.subList(0, limit);
            result.add(new ArrayList<T>(listPart));
            listPart.clear();
        }
        if (!tl.isEmpty()) {
            result.add(new ArrayList<T>(tl));
        }
        return result;
    }

    public static String getlistStr(List<String> list, String fenGeFu) {
        String result = "";
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (String item : list) {
                sb.append(item + fenGeFu);
            }
            if (sb.length() > 0) {
                sb.delete(sb.length() - fenGeFu.length(), sb.length());
                result = sb.toString();
            }
        }
        return result;
    }

}
