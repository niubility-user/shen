package com.jingdong.sdk.jdcrashreport.common;

import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class a implements Serializable {
    public long a = 300000;
    public int b = 10;

    /* renamed from: c  reason: collision with root package name */
    public int f14900c = 10;
    public int d = 10;

    public static a a(JSONObject jSONObject) {
        a aVar = new a();
        try {
            aVar.a = Long.valueOf(jSONObject.optString("reportRate")).longValue() * 1000;
            aVar.b = Integer.valueOf(jSONObject.optString("userReportThreshold")).intValue();
            aVar.f14900c = Integer.valueOf(jSONObject.optString("rnReportThreshold")).intValue();
            aVar.d = Integer.valueOf(jSONObject.optString("flutterReportThreshold", "10")).intValue();
            if (aVar.b > 30) {
                aVar.b = 30;
            }
        } catch (Throwable unused) {
        }
        return aVar;
    }
}
