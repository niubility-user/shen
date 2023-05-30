package com.jdjr.risk.jdcn.common.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class FsGsonUtil {
    private static Gson gson = new Gson();

    private FsGsonUtil() {
    }

    public static String getNoteJsonString(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            try {
                throw new Exception("json\u5b57\u7b26\u4e32");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(str2)) {
            try {
                throw new Exception("note\u6807\u7b7e\u4e0d\u80fd\u4e3a\u7a7a");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        JsonElement parse = new JsonParser().parse(str);
        if (parse.isJsonNull()) {
            try {
                throw new Exception("\u5f97\u5230\u7684jsonElement\u5bf9\u8c61\u4e3a\u7a7a");
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        return parse.getAsJsonObject().get(str2).toString();
    }

    public static String gsonString(Object obj) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return gson2.toJson(obj);
        }
        return null;
    }

    public static <T> T gsonToBean(String str, Class<T> cls) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return (T) gson2.fromJson(str, (Class) cls);
        }
        return null;
    }

    public static <T> List<T> gsonToList(String str, Class<T> cls) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return (List) gson2.fromJson(str, new TypeToken<List<T>>() { // from class: com.jdjr.risk.jdcn.common.utils.FsGsonUtil.1
            }.getType());
        }
        return null;
    }

    public static <T> List<Map<String, T>> gsonToListMaps(String str) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return (List) gson2.fromJson(str, new TypeToken<List<Map<String, T>>>() { // from class: com.jdjr.risk.jdcn.common.utils.FsGsonUtil.2
            }.getType());
        }
        return null;
    }

    public static <T> Map<String, T> gsonToMaps(String str) {
        Gson gson2 = gson;
        if (gson2 != null) {
            return (Map) gson2.fromJson(str, new TypeToken<Map<String, T>>() { // from class: com.jdjr.risk.jdcn.common.utils.FsGsonUtil.3
            }.getType());
        }
        return null;
    }

    public static JsonArray jsonToJsonArray(String str) {
        try {
            JsonElement parse = new JsonParser().parse(str);
            if (parse.isJsonArray()) {
                return parse.getAsJsonArray();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static <T> List<T> jsonToList(String str, Class<T> cls) {
        Gson gson2 = new Gson();
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = new JsonParser().parse(str).getAsJsonArray().iterator();
        while (it.hasNext()) {
            arrayList.add(gson2.fromJson(it.next(), (Class) cls));
        }
        return arrayList;
    }

    public static <T> T parserJsonToArrayBean(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            try {
                throw new Exception("json\u5b57\u7b26\u4e32\u4e3a\u7a7a");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        JsonElement parse = new JsonParser().parse(str);
        if (parse.isJsonNull()) {
            try {
                throw new Exception("json\u5b57\u7b26\u4e32\u4e3a\u7a7a");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (!parse.isJsonObject()) {
            try {
                throw new Exception("json\u4e0d\u662f\u4e00\u4e2a\u5bf9\u8c61");
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        return (T) new Gson().fromJson(parse, (Class) cls);
    }

    public static <T> List<T> parserJsonToArrayBeans(String str, String str2, Class<T> cls) {
        return parserJsonToArrayBeans(getNoteJsonString(str, str2), cls);
    }

    public static String toJsonString(Object obj) {
        if (obj != null) {
            return new Gson().toJson(obj);
        }
        try {
            throw new Exception("\u5bf9\u8c61\u4e0d\u80fd\u4e3a\u7a7a");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static <T> List<T> parserJsonToArrayBeans(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            try {
                throw new Exception("json\u5b57\u7b26\u4e32\u4e3a\u7a7a");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        JsonElement parse = new JsonParser().parse(str);
        if (parse.isJsonNull()) {
            try {
                throw new Exception("\u5f97\u5230\u7684jsonElement\u5bf9\u8c61\u4e3a\u7a7a");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (!parse.isJsonArray()) {
            try {
                throw new Exception("json\u5b57\u7b26\u4e0d\u662f\u4e00\u4e2a\u6570\u7ec4\u5bf9\u8c61\u96c6\u5408");
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        JsonArray asJsonArray = parse.getAsJsonArray();
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = asJsonArray.iterator();
        while (it.hasNext()) {
            arrayList.add(new Gson().fromJson(it.next(), (Class) cls));
        }
        return arrayList;
    }

    public static <T> T parserJsonToArrayBean(String str, String str2, Class<T> cls) {
        return (T) parserJsonToArrayBean(getNoteJsonString(str, str2), cls);
    }
}
