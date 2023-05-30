package com.jingdong.app.mall.utils;

import android.app.Activity;
import android.content.Context;
import com.jingdong.app.mall.JDApp;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.main.MainActivity;
import com.jingdong.app.mall.open.InterfaceActivity;
import com.jingdong.app.mall.open.MessageNotificationActivity;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdcrashreport.JDCrashReportConfig;
import java.util.LinkedHashMap;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class i {
    static Class[] a = {MainActivity.class, InterfaceActivity.class, MessageNotificationActivity.class};

    /* loaded from: classes4.dex */
    public class a implements com.jingdong.sdk.jdcrashreport.a {
        a() {
        }

        @Override // com.jingdong.sdk.jdcrashreport.a
        public LinkedHashMap<String, String> a(String str, String str2) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("bundles", com.jingdong.app.mall.aura.e.g());
            linkedHashMap.put("auraVersion", com.jingdong.app.mall.aura.e.f());
            linkedHashMap.put("auraServerConfig", com.jingdong.app.mall.aura.h.d().c() + ", " + com.jingdong.app.mall.aura.e.i());
            linkedHashMap.put("tbsSdkVersion", String.valueOf(com.jingdong.lib.monitor.a.b().getInt("tbsSdkVersion", -1)));
            linkedHashMap.put("tbsCoreVersion", String.valueOf(com.jingdong.lib.monitor.a.b().getInt("tbsCoreVersion", -1)));
            linkedHashMap.put("userX5Core", String.valueOf(com.jingdong.lib.monitor.a.b().getBoolean("userX5Core", false)));
            linkedHashMap.put("sysBugHelperMsg", com.jingdong.app.mall.g.f.a());
            linkedHashMap.put("libsInfo", AuraConfig.getInternalLibInfo());
            linkedHashMap.put("basicInfo", i.a());
            com.jingdong.app.mall.aura.f.a(linkedHashMap);
            return linkedHashMap;
        }
    }

    /* loaded from: classes4.dex */
    public class b implements JDCrashReportConfig.e.a {
        final /* synthetic */ Context a;

        b(Context context) {
            this.a = context;
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportConfig.e.a
        public void a(Activity activity) {
            JDMtaUtils.sendExposureData(this.a, activity, "", "", "AppCrash_JumpCurrentSuccess", "", "", "", "");
        }
    }

    static /* synthetic */ String a() {
        return b();
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("processInitStatus:");
            sb.append(String.valueOf(JDApp.getInstance().initStatus()));
            sb.append(";processLazyInit:");
            sb.append(String.valueOf(JDApp.getInstance().isLazyInit()));
            sb.append(";successInit:");
            sb.append(String.valueOf(JDApp.getInstance().isSuccessInit()));
            sb.append(";lastClientVersionInfo:");
            sb.append(l.b());
            sb.append(";arm64_only:");
            sb.append(String.valueOf(false));
            sb.append(";isGoogleChannel:");
            sb.append(String.valueOf(false));
            sb.append(";gitSha:");
            sb.append("623f2058dc4");
            sb.append(";pkg_type:");
            sb.append(String.valueOf(3));
            sb.append(";patronsInitStatus:");
            sb.append(String.valueOf(com.jingdong.app.mall.k.a.b));
            sb.append(";appInitErrorMsg:");
            sb.append(JDApp.getInstance().getInitErrorMsg());
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    @NotNull
    public static com.jingdong.sdk.jdcrashreport.a c() {
        return new a();
    }

    @NotNull
    public static JDCrashReportConfig.e d(Context context) {
        return new JDCrashReportConfig.e(MainFrameActivity.class, new b(context), a);
    }
}
