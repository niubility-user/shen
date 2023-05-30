package com.tencent.map.tools.json;

import android.text.TextUtils;
import com.tencent.map.tools.Util;
import com.tencent.map.tools.json.JsonParser;
import com.tencent.map.tools.json.annotation.JsonType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class JsonUtils {
    public static <T> String collectionToJson(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (T t : collection) {
            if (t instanceof JsonEncoder) {
                jSONArray.put(((JsonEncoder) t).toJson());
            } else {
                jSONArray.put((Object) null);
            }
        }
        return jSONArray.toString();
    }

    public static <T> JSONObject modelToJson(T t) {
        if (t instanceof JsonEncoder) {
            return ((JsonEncoder) t).toJson();
        }
        return null;
    }

    public static <T> String modelToJsonString(T t) {
        JSONObject modelToJson = modelToJson(t);
        if (modelToJson != null) {
            return modelToJson.toString();
        }
        return null;
    }

    public static <C extends Collection> C parseTo(Class<C> cls, JSONArray jSONArray, Class cls2, Object... objArr) {
        int i2;
        float floatValue;
        double doubleValue;
        int intValue;
        long longValue;
        C c2 = (C) Util.newInstance(cls, new Object[0]);
        if (jSONArray != null) {
            while (i2 < jSONArray.length()) {
                Object opt = jSONArray.opt(i2);
                if (opt instanceof JSONArray) {
                    opt = parseTo(cls, (JSONArray) opt, cls2, objArr);
                } else if (opt instanceof JSONObject) {
                    opt = parseToModel((JSONObject) opt, cls2, objArr);
                } else if (opt.getClass() != cls2) {
                    if (opt instanceof Integer) {
                        if (cls2 == Double.class) {
                            doubleValue = ((Integer) opt).doubleValue();
                            opt = Double.valueOf(doubleValue);
                        } else if (cls2 == Long.class) {
                            longValue = ((Integer) opt).longValue();
                            opt = Long.valueOf(longValue);
                        } else if (cls2 == Float.class) {
                            floatValue = ((Integer) opt).floatValue();
                            opt = Float.valueOf(floatValue);
                        } else {
                            i2 = cls2 != String.class ? i2 + 1 : 0;
                            opt = opt.toString();
                        }
                    } else if (!(opt instanceof Double)) {
                        if (opt instanceof Long) {
                            if (cls2 == Integer.class) {
                                intValue = ((Long) opt).intValue();
                                opt = Integer.valueOf(intValue);
                            } else if (cls2 == Double.class) {
                                doubleValue = ((Long) opt).doubleValue();
                                opt = Double.valueOf(doubleValue);
                            } else if (cls2 == Float.class) {
                                floatValue = ((Long) opt).floatValue();
                                opt = Float.valueOf(floatValue);
                            } else {
                                if (cls2 != String.class) {
                                }
                                opt = opt.toString();
                            }
                        }
                    } else if (cls2 == Integer.class) {
                        intValue = ((Double) opt).intValue();
                        opt = Integer.valueOf(intValue);
                    } else if (cls2 == Long.class) {
                        longValue = ((Double) opt).longValue();
                        opt = Long.valueOf(longValue);
                    } else if (cls2 == Float.class) {
                        floatValue = ((Double) opt).floatValue();
                        opt = Float.valueOf(floatValue);
                    } else {
                        if (cls2 != String.class) {
                        }
                        opt = opt.toString();
                    }
                }
                c2.add(opt);
            }
        }
        return c2;
    }

    public static Object parseToArray(JSONArray jSONArray, Class cls) {
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        Object newInstance = Array.newInstance(cls, length);
        for (int i2 = 0; i2 < length; i2++) {
            try {
                Array.set(newInstance, i2, jSONArray.get(i2));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return newInstance;
    }

    public static <T> List<T> parseToList(JSONArray jSONArray, Class<T> cls, Object... objArr) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(parseToModel(jSONArray.optJSONObject(i2), cls, objArr));
            }
        }
        return arrayList;
    }

    public static <T> T parseToModel(String str, Class<T> cls, Object... objArr) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (T) parseToModel(new JSONObject(str), cls, objArr);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7 */
    public static <T> T parseToModel(JSONObject jSONObject, Class<T> cls, Object... objArr) {
        Class<? extends JsonParser.Deserializer> deserializer;
        JsonType jsonType = (JsonType) cls.getAnnotation(JsonType.class);
        T t = null;
        if (jsonType != null && (deserializer = jsonType.deserializer()) != JsonParser.Deserializer.class) {
            try {
                ?? deserialize = ((JsonParser.Deserializer) Util.newInstance(deserializer, new Object[0])).deserialize(null, "", jSONObject);
                if (deserialize != null) {
                    if (cls.isAssignableFrom(deserialize.getClass())) {
                        t = deserialize;
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (t == null) {
            t = (T) Util.newInstance(cls, objArr);
        }
        if (t instanceof JsonParser) {
            t.parse(jSONObject);
        }
        return t;
    }
}
