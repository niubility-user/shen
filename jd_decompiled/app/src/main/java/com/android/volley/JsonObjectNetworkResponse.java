package com.android.volley;

import com.android.volley.Request;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsonObjectNetworkResponse extends NetworkResponse {
    public JSONObject object;

    public JsonObjectNetworkResponse(int i2, byte[] bArr, JSONObject jSONObject, Map<String, String> map, boolean z, long j2, Request.DownGradeType downGradeType) {
        super(i2, bArr, map, z, j2, downGradeType);
        this.object = jSONObject;
    }
}
