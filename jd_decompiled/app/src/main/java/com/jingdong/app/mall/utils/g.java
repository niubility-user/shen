package com.jingdong.app.mall.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes4.dex */
public class g {
    private static final String a = "g";
    private static final com.jingdong.sdk.dialingtest.e.a.a b = new a();

    /* renamed from: c  reason: collision with root package name */
    private static final com.jingdong.sdk.dialingtest.e.b.a f11791c = new b();

    /* loaded from: classes4.dex */
    class a implements com.jingdong.sdk.dialingtest.e.a.a {
        a() {
        }

        @Override // com.jingdong.sdk.dialingtest.e.a.a
        public String a(Context context, String str, String str2) {
            StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(context, str, str2);
            return stategyEntitiy == null ? "" : stategyEntitiy.param;
        }

        @Override // com.jingdong.sdk.dialingtest.e.a.a
        public void b(HashMap<String, String> hashMap) {
            PerformanceReporter.reportData(hashMap);
        }

        @Override // com.jingdong.sdk.dialingtest.e.a.a
        public boolean c(Context context, String str, String str2) {
            return PerformanceReporter.getIsNeedReport(context, str, str2);
        }
    }

    /* loaded from: classes4.dex */
    class b extends com.jingdong.sdk.dialingtest.e.b.a {
        b() {
        }

        @Override // com.jingdong.sdk.dialingtest.e.b.a
        public boolean c() {
            for (String str : BaseInfo.getNetAddressesForIPv6()) {
                if (!TextUtils.isEmpty(str) && !str.toLowerCase().startsWith("fe80")) {
                    return true;
                }
            }
            return false;
        }
    }

    private static void a() {
        com.jingdong.sdk.dialingtest.a.d(JdSdk.getInstance().getApplicationContext(), false, b, f11791c);
    }

    public static void b() {
        boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.NETWORK_DETECT, false);
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("the global switch is ");
        sb.append(switchBooleanValue ? "turn on" : "turn off");
        OKLog.d(str, sb.toString());
        if (switchBooleanValue) {
            a();
            com.jingdong.sdk.dialingtest.a.a();
        }
    }
}
