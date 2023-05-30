package com.jingdong.union.common.helper;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.union.a.f;
import com.jingdong.union.common.config.JdUnionBase;
import java.util.ArrayList;
import java.util.HashMap;
import jpbury.t;

/* loaded from: classes12.dex */
public class d {
    private static String a() {
        return b(System.currentTimeMillis());
    }

    private static String b(long j2) {
        double d = j2;
        Double.isNaN(d);
        return String.format("%.6f", Double.valueOf(d / 1000.0d));
    }

    public static ArrayList c(int i2, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            arrayList.add("UnionDesResponseUrlExc");
        }
        if (i2 == 1) {
            if (TextUtils.isEmpty(str3)) {
                arrayList.add("UnionDesResponseUnplExc");
            }
        } else if (i2 == 2) {
            if (TextUtils.isEmpty(str3)) {
                arrayList.add("UnionDesResponseUnplExc");
            }
            if (TextUtils.isEmpty(str2)) {
                arrayList.add("UnionDesResponseJdvExc");
            }
        }
        return arrayList;
    }

    public static void d(String str, HttpSetting httpSetting, HttpResponse httpResponse, HttpError httpError) {
        if (httpSetting != null) {
            try {
                HashMap<String, String> hashMap = new HashMap<>();
                if (!TextUtils.isEmpty(str)) {
                    hashMap.put("function", str);
                }
                String url = httpSetting.getUrl();
                if (!TextUtils.isEmpty(url)) {
                    hashMap.put("url", url);
                }
                hashMap.put("errCode", "959");
                hashMap.put(PerformanceManager.ERR_TYPE, "2");
                if (httpResponse != null) {
                    hashMap.put("httpResp", "" + Integer.valueOf(httpResponse.getCode()));
                    hashMap.put("errMsg", httpResponse.getString());
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (fastJsonObject != null) {
                        hashMap.put(t.f20145j, fastJsonObject.toJSONString());
                    }
                }
                if (httpError != null) {
                    hashMap.put("errMsg", httpError.toString());
                    hashMap.put("httpResp", String.valueOf(httpError.getResponseCode()));
                    hashMap.put(PerformanceManager.ERR_TYPE, "1");
                }
                hashMap.put("occurTime", a());
                f.a("reportRequestDesUrlException:id\uff1a" + httpSetting.getId() + ", data: " + hashMap.toString());
                JdUnionBase.getUnionExceptionReport().report(JdUnionBase.getContext(), hashMap);
            } catch (Throwable th) {
                f.d(th.toString());
            }
        }
    }

    public static void e(String str, String str2, String str3) {
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("function", str);
            }
            hashMap.put("errCode", "959");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            if (!TextUtils.isEmpty(str3)) {
                hashMap.put(t.f20145j, str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put("url", str2);
            }
            hashMap.put("occurTime", a());
            f.a("reportUnionSourceException: data = " + hashMap.toString());
            JdUnionBase.getUnionExceptionReport().report(JdUnionBase.getContext(), hashMap);
        } catch (Throwable th) {
            f.d(th.toString());
        }
    }
}
