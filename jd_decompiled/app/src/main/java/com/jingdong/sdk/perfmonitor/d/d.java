package com.jingdong.sdk.perfmonitor.d;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class d extends b {
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f15411e;

    /* renamed from: f  reason: collision with root package name */
    public int f15412f;

    /* renamed from: g  reason: collision with root package name */
    public int f15413g;

    /* renamed from: h  reason: collision with root package name */
    public int f15414h;

    /* renamed from: i  reason: collision with root package name */
    public int f15415i;

    /* renamed from: j  reason: collision with root package name */
    public int f15416j;

    /* renamed from: k  reason: collision with root package name */
    public boolean f15417k;

    public d(@NonNull Context context) {
        this.d = 10;
        this.f15411e = 2;
        this.f15412f = 3;
        this.f15413g = 80;
        this.f15414h = 50;
        this.f15415i = 240;
        this.f15416j = 52;
        this.f15417k = false;
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(context.getApplicationContext(), "11", "2");
        if (stategyEntitiy == null) {
            return;
        }
        boolean z = true;
        if (OKLog.D) {
            OKLog.d("PerfMonitor", String.format("jank: %s, params: %s", stategyEntitiy.ret, stategyEntitiy.param));
        }
        try {
            if (TextUtils.isEmpty(stategyEntitiy.param) || !"1".equals(stategyEntitiy.ret)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(stategyEntitiy.param);
            this.a = "1".equals(jSONObject.getString("type"));
            if (jSONObject.has("rows")) {
                this.d = jSONObject.getInt("rows");
            }
            if (jSONObject.has("lStuckThreshold")) {
                this.f15411e = jSONObject.getInt("lStuckThreshold");
            }
            if (jSONObject.has("cStuckThreshold")) {
                this.f15412f = jSONObject.getInt("cStuckThreshold");
            }
            if (jSONObject.has("criticalBlockTime")) {
                this.f15413g = jSONObject.getInt("criticalBlockTime");
            }
            if (jSONObject.has("majorBlockTime")) {
                this.f15414h = jSONObject.getInt("majorBlockTime");
            }
            if (jSONObject.has("bigJankTime")) {
                this.f15415i = jSONObject.getInt("bigJankTime");
            }
            if (jSONObject.has("samplingFrequency")) {
                this.f15416j = jSONObject.getInt("samplingFrequency");
            }
            if (jSONObject.has("isReportAllStack")) {
                if (jSONObject.getInt("isReportAllStack") != 1) {
                    z = false;
                }
                this.f15417k = z;
            }
            if (jSONObject.has("module")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("module");
                if (jSONObject2.length() > 0) {
                    this.b = new ArrayMap();
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if ("1".equals(jSONObject2.getJSONObject(next).getString("switch"))) {
                            this.b.put(next, next);
                        }
                    }
                }
            }
        } catch (JSONException e2) {
            OKLog.e("PerfMonitor", "JSON\u89e3\u6790\u9519\u8bef", e2);
        }
    }
}
