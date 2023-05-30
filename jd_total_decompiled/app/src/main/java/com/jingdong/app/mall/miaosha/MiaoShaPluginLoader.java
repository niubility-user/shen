package com.jingdong.app.mall.miaosha;

import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.web.managers.WebPerfManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MiaoShaPluginLoader {
    private static final String FUNCTION_COOL_START = "coolStart";
    private static final String FUNCTION_HOT_START = "hotStart";
    private static final String FUNCTION_ID = "functionID";
    private static final String FUNCTION_WARM_START = "warmStart";
    private static MiaoShaPluginLoader sInstance;
    private long coolStartTime;
    private long hotStartTime;
    private long warmStartTime;

    public static MiaoShaPluginLoader getInstance() {
        if (sInstance == null) {
            sInstance = new MiaoShaPluginLoader();
        }
        return sInstance;
    }

    private void reset() {
        this.coolStartTime = 0L;
        this.hotStartTime = 0L;
        this.warmStartTime = 0L;
    }

    public void report(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap hashMap = new HashMap();
            hashMap.put("typeId", "12");
            hashMap.put("chId", "1");
            hashMap.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
            hashMap.put(WebPerfManager.PAGE_NAME, str);
            ArrayList arrayList = new ArrayList();
            if (this.coolStartTime != 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(FUNCTION_ID, FUNCTION_COOL_START);
                jSONObject.put("startTime", this.coolStartTime);
                jSONObject.put("endTime", currentTimeMillis);
                arrayList.add(jSONObject.toString());
            }
            if (this.warmStartTime != 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(FUNCTION_ID, FUNCTION_WARM_START);
                jSONObject2.put("startTime", this.warmStartTime);
                jSONObject2.put("endTime", currentTimeMillis);
                arrayList.add(jSONObject2.toString());
            }
            if (this.hotStartTime != 0) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(FUNCTION_ID, FUNCTION_HOT_START);
                jSONObject3.put("startTime", this.hotStartTime);
                jSONObject3.put("endTime", currentTimeMillis);
                arrayList.add(jSONObject3.toString());
            }
            if (arrayList.size() != 0) {
                hashMap.put("requestInfo", arrayList.toString());
                PerformanceReporter.reportData(hashMap);
            }
            reset();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setCoolStartTime(long j2) {
        this.coolStartTime = j2;
    }

    public void setHotStartTime(long j2) {
        this.hotStartTime = j2;
    }

    public void setWarmStartTime(long j2) {
        this.warmStartTime = j2;
    }
}
