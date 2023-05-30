package com.jingdong.app.mall.bundle.smile.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public class GsonFactory {
    private static final String TAG = "GsonFactory";
    private static Gson sGson;
    private static GsonFactory sInstance;

    private GsonFactory() {
        sGson = new GsonBuilder().disableHtmlEscaping().registerTypeHierarchyAdapter(String.class, new JsonDeserializer<String>() { // from class: com.jingdong.app.mall.bundle.smile.utils.GsonFactory.1
            @Override // com.google.gson.JsonDeserializer
            public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                String str = "";
                try {
                    if (jsonElement.isJsonPrimitive()) {
                        str = jsonElement.getAsString();
                    } else if (jsonElement.isJsonObject()) {
                        str = jsonElement.getAsJsonObject().toString();
                    } else if (jsonElement.isJsonArray()) {
                        str = jsonElement.getAsJsonArray().toString();
                    } else {
                        jsonElement.isJsonNull();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return str;
            }
        }).create();
    }

    public static GsonFactory getInstance() {
        if (sInstance == null) {
            synchronized (GsonFactory.class) {
                if (sInstance == null) {
                    sInstance = new GsonFactory();
                }
            }
        }
        return sInstance;
    }

    public <T> T formJson(String str, Type type) {
        try {
            return (T) sGson.fromJson(str, type);
        } catch (Exception e2) {
            OKLog.e(TAG, e2.toString());
            return null;
        }
    }

    public String toJson(Object obj) {
        try {
            return sGson.toJson(obj);
        } catch (Exception e2) {
            OKLog.e(TAG, e2.toString());
            return null;
        }
    }

    public String toJson(Object obj, Type type) {
        try {
            return sGson.toJson(obj, type);
        } catch (Exception e2) {
            OKLog.e(TAG, e2.toString());
            return null;
        }
    }
}
