package com.jingdong.common.impl.parse;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.jd.framework.json.TypeToken;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class GsonParser implements IJsonParse {
    private static final String TAG = "GsonParser";
    Gson gson;
    private final GsonBuilder gsonBuilder = new GsonBuilder();

    /* loaded from: classes5.dex */
    public interface ParserTaskInterceptor {
        void injectionTask(GsonBuilder gsonBuilder);
    }

    public GsonParser() {
        createGson();
    }

    public void createGson() {
        GsonBuilder gsonBuilder = this.gsonBuilder;
        if (gsonBuilder != null) {
            this.gson = gsonBuilder.setLenient().create();
        }
    }

    public GsonBuilder getGsonBuilder() {
        return this.gsonBuilder;
    }

    public GsonParser injectionTaskInterceptor(ParserTaskInterceptor parserTaskInterceptor) {
        if (parserTaskInterceptor != null) {
            parserTaskInterceptor.injectionTask(this.gsonBuilder);
            createGson();
        }
        return this;
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public boolean optBoolean(String str, String str2) {
        try {
            JsonObject jsonObject = toJsonObject(str);
            if (jsonObject == null || jsonObject.get(str2) == null) {
                return false;
            }
            return jsonObject.get(str2).getAsBoolean();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return false;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public int optInt(String str, String str2) {
        try {
            JsonObject jsonObject = toJsonObject(str);
            if (jsonObject == null || jsonObject.get(str2) == null) {
                return 0;
            }
            return jsonObject.get(str2).getAsInt();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return 0;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public String optString(String str, String str2) {
        try {
            JsonObject jsonObject = toJsonObject(str);
            if (jsonObject == null || jsonObject.get(str2) == null) {
                return null;
            }
            return jsonObject.get(str2).getAsString();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public <T> List<T> parseJsonToArray(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        try {
            Type type = new TypeToken<List<JsonObject>>() { // from class: com.jingdong.common.impl.parse.GsonParser.1
            }.getType();
            ArrayList arrayList = new ArrayList();
            Iterator it = ((List) this.gson.fromJson(str, type)).iterator();
            while (it.hasNext()) {
                arrayList.add(this.gson.fromJson(((JsonObject) it.next()).toString(), (Class) cls));
            }
            return arrayList;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "parse String to object in exception \n" + e2.getMessage());
            }
            return new ArrayList();
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public <T> T parseJsonToObject(String str, Class<T> cls) {
        try {
            return (T) this.gson.fromJson(str, (Class) cls);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "parse String to object in exception \n" + e2.getMessage());
                return null;
            }
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public String toJsonString(Object obj) {
        try {
            return this.gson.toJson(obj);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public JsonArray optJsonArray(String str, String str2) {
        try {
            JsonObject jsonObject = toJsonObject(str);
            if (jsonObject == null || jsonObject.get(str2) == null) {
                return null;
            }
            return jsonObject.get(str2).getAsJsonArray();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public JsonObject toJsonObject(String str) {
        try {
            return new JsonParser().parse(str).getAsJsonObject();
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    public <T> List<T> parseJsonToArray(JsonReader jsonReader, Class<T> cls) {
        if (jsonReader == null) {
            return new ArrayList();
        }
        try {
            Type type = new TypeToken<List<JsonObject>>() { // from class: com.jingdong.common.impl.parse.GsonParser.2
            }.getType();
            ArrayList arrayList = new ArrayList();
            Iterator it = ((List) this.gson.fromJson(jsonReader, type)).iterator();
            while (it.hasNext()) {
                arrayList.add(this.gson.fromJson(((JsonObject) it.next()).toString(), (Class) cls));
            }
            return arrayList;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "parse String to object in exception \n" + e2.getMessage());
            }
            return new ArrayList();
        }
    }
}
