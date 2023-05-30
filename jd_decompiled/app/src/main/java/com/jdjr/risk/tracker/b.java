package com.jdjr.risk.tracker;

import android.content.Context;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jdjr.risk.tracker.util.CryptoUtil;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.tencent.connect.common.Constants;
import com.tencent.mapsdk.internal.l4;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes18.dex */
public class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f7494c;
    final /* synthetic */ String d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f7495e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, String str, String str2, String str3, String str4) {
        this.a = context;
        this.b = str;
        this.f7494c = str2;
        this.d = str3;
        this.f7495e = str4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String b;
        try {
            String packageName = this.a.getPackageName();
            String str = this.a.getApplicationInfo().targetSdkVersion + "";
            String str2 = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
            String str3 = System.currentTimeMillis() + "";
            b = c.b(this.a);
            if (b.equals("") && c.b.equals("")) {
                c.b = com.jdjr.risk.tracker.a.b.a(this.a);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.getFilesDir().getAbsolutePath());
            sb.append(File.separator);
            sb.append("jdjrrisktrackercache.txt");
            String sb2 = sb.toString();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("logId", this.b);
            jSONObject.put("appPackage", packageName);
            jSONObject.put(l4.f16791e, this.f7494c);
            jSONObject.put("buildVersion", str);
            jSONObject.put("appVersion", str2);
            jSONObject.put("eventId", this.d);
            jSONObject.put("trackId", str3);
            jSONObject.put(Constants.PARAM_PLATFORM, "android");
            jSONObject.put("deviceInfo", c.b);
            JSONObject jSONObject2 = new JSONObject(this.f7495e);
            jSONObject2.put("token", b);
            jSONObject2.put(VerifyTracker.KEY_APP_VER_NAME, str2);
            jSONObject.put("kvs", jSONObject2.toString());
            jSONObject.put("time", System.currentTimeMillis());
            String a = CryptoUtil.a(jSONObject.toString().getBytes());
            String a2 = com.jdjr.risk.tracker.util.b.a("https://mllog.jd.com/mlog/unite/v.do", a);
            String optString = new JSONObject(a2).optString(NotifyType.LIGHTS);
            if (!TextUtils.isEmpty(optString) && !"0".equals(optString)) {
                a.a().a(optString);
            }
            if (a2.equals("")) {
                com.jdjr.risk.tracker.util.c.a(a, sb2);
            } else {
                c.b(sb2);
            }
        } catch (Throwable unused) {
        }
    }
}
