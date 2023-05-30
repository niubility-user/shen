package com.jingdong.sdk.perfmonitor.b;

import android.text.TextUtils;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.Reporter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class g {
    private ConcurrentHashMap<String, CopyOnWriteArrayList<com.jingdong.sdk.perfmonitor.a.d>> a = new ConcurrentHashMap<>();
    Reporter b;

    public g(Reporter reporter) {
        this.b = reporter;
    }

    private String a(String str, String str2) {
        return str + CartConstant.KEY_YB_INFO_LINK + str2;
    }

    private void d(String str, com.jingdong.sdk.perfmonitor.a.d dVar) {
        if (dVar == null) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("typeId", "11");
        hashMap.put("chId", "5");
        hashMap.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
        hashMap.put(WebPerfManager.PAGE_NAME, str);
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("functionID", dVar.a);
            jSONObject.put("startTime", String.valueOf(dVar.b));
            jSONObject.put("endTime", String.valueOf(dVar.f15300c));
            jSONObject.put("errCode", String.valueOf(dVar.d));
            if (!TextUtils.isEmpty(dVar.f15301e)) {
                jSONObject.put("errMsg", dVar.f15301e);
            }
            jSONArray.put(jSONObject);
            hashMap.put("requestInfo", jSONArray.toString());
            if (OKLog.D) {
                OKLog.d("PerfMonitor", hashMap.toString());
            }
            this.b.submit(hashMap);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return;
        }
        String a = a(str, str2);
        CopyOnWriteArrayList<com.jingdong.sdk.perfmonitor.a.d> copyOnWriteArrayList = this.a.get(a);
        com.jingdong.sdk.perfmonitor.a.d dVar = new com.jingdong.sdk.perfmonitor.a.d();
        dVar.a = str2;
        dVar.b = System.currentTimeMillis();
        if (copyOnWriteArrayList == null) {
            CopyOnWriteArrayList<com.jingdong.sdk.perfmonitor.a.d> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
            copyOnWriteArrayList2.add(dVar);
            this.a.put(a, copyOnWriteArrayList2);
            return;
        }
        copyOnWriteArrayList.add(dVar);
    }

    public void c(String str, String str2, int i2, String str3) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return;
        }
        CopyOnWriteArrayList<com.jingdong.sdk.perfmonitor.a.d> copyOnWriteArrayList = this.a.get(a(str, str2));
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() == 0) {
            return;
        }
        com.jingdong.sdk.perfmonitor.a.d dVar = null;
        Iterator<com.jingdong.sdk.perfmonitor.a.d> it = copyOnWriteArrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            com.jingdong.sdk.perfmonitor.a.d next = it.next();
            if (next.f15300c == 0) {
                next.f15300c = System.currentTimeMillis();
                next.d = i2;
                next.f15301e = str3;
                dVar = next;
                break;
            }
        }
        d(str, dVar);
        copyOnWriteArrayList.remove(dVar);
    }
}
