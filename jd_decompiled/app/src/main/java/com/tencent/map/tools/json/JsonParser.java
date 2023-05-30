package com.tencent.map.tools.json;

import org.json.JSONObject;

/* loaded from: classes.dex */
public interface JsonParser {

    /* loaded from: classes.dex */
    public interface Deserializer<T> {
        T deserialize(Object obj, String str, Object obj2);
    }

    void parse(JSONObject jSONObject);
}
