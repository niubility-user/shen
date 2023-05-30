package com.android.volley;

import com.android.volley.Request;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class JsonArrayNetworkResponse extends NetworkResponse {
    public JSONArray array;

    public JsonArrayNetworkResponse(int i2, byte[] bArr, JSONArray jSONArray, Map<String, String> map, boolean z, long j2, Request.DownGradeType downGradeType) {
        super(i2, bArr, map, z, j2, downGradeType);
        this.array = jSONArray;
    }
}
