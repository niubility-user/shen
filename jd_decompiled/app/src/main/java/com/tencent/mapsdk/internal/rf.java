package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonParser;
import com.tencent.map.tools.json.JsonUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class rf implements JsonParser {
    private boolean a;
    private List<tf> b;

    public List<tf> a() {
        return this.b;
    }

    public boolean b() {
        return this.a;
    }

    @Override // com.tencent.map.tools.json.JsonParser
    public void parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.a = jSONObject.optInt("enable", 0) == 1;
            JSONArray optJSONArray = jSONObject.optJSONArray("layers");
            if (optJSONArray != null) {
                this.b = JsonUtils.parseToList(optJSONArray, tf.class, new Object[0]);
            }
        }
    }
}
