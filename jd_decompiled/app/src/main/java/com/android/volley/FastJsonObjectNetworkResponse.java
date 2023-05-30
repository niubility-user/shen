package com.android.volley;

import com.android.volley.Request;
import com.jd.framework.json.JDJSONObject;
import java.util.Map;

/* loaded from: classes.dex */
public class FastJsonObjectNetworkResponse extends NetworkResponse {
    public JDJSONObject object;

    public FastJsonObjectNetworkResponse(int i2, byte[] bArr, JDJSONObject jDJSONObject, Map<String, String> map, boolean z, long j2, Request.DownGradeType downGradeType) {
        super(i2, bArr, map, z, j2, downGradeType);
        this.object = jDJSONObject;
    }
}
