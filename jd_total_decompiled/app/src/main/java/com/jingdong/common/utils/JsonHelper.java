package com.jingdong.common.utils;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JsonHelper {
    public static final String TAG = "JsonHelper";

    /* loaded from: classes6.dex */
    public static class MapKeyComparator implements Comparator<String>, Serializable {
        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    }

    private static Object fromJDJson(Object obj) {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JDJSONObject) {
            return toMap((JDJSONObject) obj);
        }
        return obj instanceof JDJSONArray ? toList((JDJSONArray) obj) : obj;
    }

    private static Object fromJson(Object obj) throws JSONException {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return toMap((JSONObject) obj);
        }
        return obj instanceof JSONArray ? toList((JSONArray) obj) : obj;
    }

    public static Map<String, Object> getMap(JSONObject jSONObject, String str) throws JSONException {
        return toMap(jSONObject.getJSONObject(str));
    }

    public static boolean isEmptyObject(JSONObject jSONObject) {
        return jSONObject.names() == null;
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        TreeMap treeMap = new TreeMap(new MapKeyComparator());
        treeMap.putAll(map);
        return treeMap;
    }

    public static Object toJSON(Object obj) throws JSONException {
        if (obj instanceof Map) {
            JSONObject jSONObject = new JSONObject();
            Map map = (Map) obj;
            for (Object obj2 : map.keySet()) {
                jSONObject.put(obj2.toString(), toJSON(map.get(obj2)));
            }
            return jSONObject;
        } else if (obj instanceof Iterable) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            return jSONArray;
        } else {
            return obj;
        }
    }

    public static List toList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(fromJson(jSONArray.get(i2)));
        }
        return arrayList;
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, fromJson(jSONObject.get(next)));
        }
        return sortMapByKey(hashMap);
    }

    public static Map<String, Object> toNoSortMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, fromJson(jSONObject.get(next)));
        }
        return hashMap;
    }

    public static Map<Integer, Object> toNoSortRemindMap(JSONObject jSONObject) throws JSONException {
        TreeMap treeMap = new TreeMap(new Comparator<Integer>() { // from class: com.jingdong.common.utils.JsonHelper.1
            @Override // java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return num2.compareTo(num);
            }
        });
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            treeMap.put(Integer.valueOf(next), fromJson(jSONObject.get(next)));
        }
        return treeMap;
    }

    public static List toList(JDJSONArray jDJSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                arrayList.add(fromJDJson(jDJSONArray.get(i2)));
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(TAG, "JDJSON to list error!", th);
                }
            }
        }
        return arrayList;
    }

    public static Map<String, Object> toNoSortMap(JDJSONObject jDJSONObject) {
        HashMap hashMap = new HashMap();
        try {
            for (Map.Entry<String, Object> entry : jDJSONObject.entrySet()) {
                hashMap.put(entry.getKey(), fromJDJson(entry.getValue()));
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, "JDJSON to no sort map error!", th);
            }
        }
        return hashMap;
    }

    public static Map<Integer, Object> toNoSortRemindMap(JDJSONObject jDJSONObject) {
        TreeMap treeMap = new TreeMap(new Comparator<Integer>() { // from class: com.jingdong.common.utils.JsonHelper.2
            @Override // java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return num2.compareTo(num);
            }
        });
        try {
            for (Map.Entry<String, Object> entry : jDJSONObject.entrySet()) {
                treeMap.put(Integer.valueOf(entry.getKey()), fromJDJson(entry.getValue()));
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, "JDJSON to no sort map error!", th);
            }
        }
        return treeMap;
    }

    public static Map<String, Object> toMap(JDJSONObject jDJSONObject) {
        HashMap hashMap = new HashMap();
        try {
            for (Map.Entry<String, Object> entry : jDJSONObject.entrySet()) {
                hashMap.put(entry.getKey(), fromJDJson(entry.getValue()));
            }
            return sortMapByKey(hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, "JDJSON to map error!", th);
                return hashMap;
            }
            return hashMap;
        }
    }
}
