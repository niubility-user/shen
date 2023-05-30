package com.jd.viewkit.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JSONTool {
    public static JSONArray JSONArray(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static JSONObject JSONObject(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Object get(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(str)) {
                    return jSONObject.get(str);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static boolean getBoolean(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(str) && (jSONObject.get(str) instanceof Boolean)) {
                    return jSONObject.getBoolean(str);
                }
                return false;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static double getDouble(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(str)) {
                    return Double.parseDouble(jSONObject.optString(str));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0.0d;
    }

    public static int getInt(JSONArray jSONArray, int i2) {
        if (jSONArray != null) {
            try {
                if (!jSONArray.isNull(i2)) {
                    if (jSONArray.get(i2) instanceof Integer) {
                        return jSONArray.getInt(i2);
                    }
                    if ((jSONArray.get(i2) instanceof String) && StringTool.isInteger(jSONArray.getString(i2))) {
                        return NumberTool.valueOfInt(jSONArray.getString(i2));
                    }
                    return 0;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    public static JSONArray getJSONArray(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(str) && (jSONObject.get(str) instanceof JSONArray)) {
                    return jSONObject.getJSONArray(str);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject getJSONObject(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(str) && (jSONObject.get(str) instanceof JSONObject)) {
                    return jSONObject.getJSONObject(str);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String getJsonString(Map map) {
        if (map == null || !(map instanceof Map)) {
            return null;
        }
        return new JSONObject(map).toString();
    }

    public static List<Map<String, Object>> getList(JSONArray jSONArray) {
        if (jSONArray == null || !(jSONArray instanceof JSONArray)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            Map<String, Object> map = getMap(getJSONObject(jSONArray, i2));
            if (map != null) {
                arrayList.add(map);
            }
        }
        return arrayList;
    }

    public static Map<String, Object> getMap(JSONObject jSONObject) {
        if (jSONObject == null || !(jSONObject instanceof JSONObject)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = get(jSONObject, next);
            if (obj instanceof JSONObject) {
                Map<String, Object> map = getMap((JSONObject) obj);
                if (map != null) {
                    hashMap.put(next, map);
                }
            } else if (obj instanceof JSONArray) {
                List<Map<String, Object>> list = getList((JSONArray) obj);
                if (list != null) {
                    hashMap.put(next, list);
                }
            } else {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }

    public static String getString(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(str) && (jSONObject.get(str) instanceof String)) {
                    return jSONObject.getString(str);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject getJSONObject(JSONArray jSONArray, int i2) {
        if (jSONArray != null) {
            try {
                if (!jSONArray.isNull(i2) && (jSONArray.get(i2) instanceof JSONObject)) {
                    return jSONArray.getJSONObject(i2);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String getString(JSONArray jSONArray, int i2) {
        if (jSONArray != null) {
            try {
                if (!jSONArray.isNull(i2) && (jSONArray.get(i2) instanceof String)) {
                    return jSONArray.getString(i2);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static int getInt(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(str)) {
                    if (jSONObject.get(str) instanceof Integer) {
                        return jSONObject.getInt(str);
                    }
                    if ((jSONObject.get(str) instanceof String) && StringTool.isInteger(jSONObject.getString(str))) {
                        return NumberTool.valueOfInt(jSONObject.getString(str));
                    }
                    return 0;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }
}
