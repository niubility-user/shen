package com.android.volley;

import com.android.volley.Request;
import com.jd.framework.json.JDJSONArray;
import java.util.Map;

/* loaded from: classes.dex */
public class FastJsonArrayNetworkResponse extends NetworkResponse {
    public JDJSONArray array;

    public FastJsonArrayNetworkResponse(int i2, byte[] bArr, JDJSONArray jDJSONArray, Map<String, String> map, boolean z, long j2, Request.DownGradeType downGradeType) {
        super(i2, bArr, map, z, j2, downGradeType);
        this.array = jDJSONArray;
    }
}
