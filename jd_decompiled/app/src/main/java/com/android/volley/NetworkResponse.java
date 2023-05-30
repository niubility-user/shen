package com.android.volley;

import com.android.volley.Request;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public class NetworkResponse {
    public final byte[] data;
    public final Request.DownGradeType downGradeType;
    public final Map<String, String> headers;
    public final long networkTimeMs;
    public final boolean notModified;
    public final int statusCode;

    public NetworkResponse(int i2, byte[] bArr, Map<String, String> map, boolean z, long j2) {
        this(i2, bArr, map, z, j2, Request.DownGradeType.NoDownGrade);
    }

    public NetworkResponse(int i2, byte[] bArr, Map<String, String> map, boolean z, long j2, Request.DownGradeType downGradeType) {
        this.statusCode = i2;
        this.data = bArr;
        this.headers = map;
        this.notModified = z;
        this.networkTimeMs = j2;
        this.downGradeType = downGradeType;
    }

    public NetworkResponse(int i2, byte[] bArr, Map<String, String> map, boolean z) {
        this(i2, bArr, map, z, 0L);
    }

    public NetworkResponse(byte[] bArr) {
        this(200, bArr, Collections.emptyMap(), false, 0L);
    }

    public NetworkResponse(byte[] bArr, Map<String, String> map) {
        this(200, bArr, map, false, 0L);
    }
}
