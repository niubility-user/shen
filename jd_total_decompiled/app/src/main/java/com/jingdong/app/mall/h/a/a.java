package com.jingdong.app.mall.h.a;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.verify.VerifyPrivacyInfoProxy;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/* loaded from: classes19.dex */
public class a implements IGuardVerifyPlugin {

    /* renamed from: e  reason: collision with root package name */
    public static boolean f8437e;
    public b a;
    private com.jingdong.app.mall.h.a.b b;

    /* renamed from: c  reason: collision with root package name */
    private int f8438c = 0;
    private String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.h.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes19.dex */
    public class C0265a implements HttpGroup.CustomOnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f8439g;

        C0265a(String str) {
            this.f8439g = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String str = "\u8fd4\u56de\u7ed3\u679c : " + httpResponse.getString();
            String g2 = a.this.g(httpResponse.getFastJsonObject());
            if (TextUtils.isEmpty(g2)) {
                return;
            }
            a.this.n(g2, this.f8439g);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            String str = "\u8fd4\u56de\u7ed3\u679c\u5931\u8d25 " + httpError;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* loaded from: classes19.dex */
    public interface b {
        String getEid();

        long getExpiresTime();

        String getUUID();

        String getUserName();

        VerifyPrivacyInfoProxy getVerifyPrivacyInfoProxy();

        boolean isDebug();

        boolean isOnlineSwitchOpen();
    }

    public a(b bVar) {
        this.a = bVar;
        if (bVar != null) {
            f8437e = bVar.isDebug();
        }
        this.b = new com.jingdong.app.mall.h.a.b(this);
    }

    private void c() {
        try {
            synchronized (this) {
                this.d = null;
                SharedPreferences.Editor edit = e().getSharedPreferences("guard_verify_sp", 0).edit();
                edit.remove("guard_verify_token");
                edit.remove("guard_verify_check_time");
                edit.apply();
            }
        } catch (Throwable unused) {
        }
    }

    private void d() {
        try {
            synchronized (this) {
                this.d = null;
                SharedPreferences.Editor edit = e().getSharedPreferences("guard_verify_sp", 0).edit();
                edit.remove("guard_verify_token");
                edit.apply();
            }
        } catch (Throwable unused) {
        }
    }

    public static Context e() {
        try {
            return JDHttpTookit.getEngine().getApplicationContext();
        } catch (Throwable unused) {
            return null;
        }
    }

    private long f() {
        b bVar = this.a;
        if (bVar == null || bVar.getExpiresTime() <= 0) {
            return 1800000L;
        }
        return this.a.getExpiresTime() * 60 * 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String g(JDJSONObject jDJSONObject) {
        return TextUtils.equals(jDJSONObject.getString("s_code"), "10000") ? "" : jDJSONObject.getString("sessionid");
    }

    public static boolean h() {
        return f8437e;
    }

    private long i(long j2) {
        return TimeUnit.MILLISECONDS.toMinutes(j2);
    }

    private void k(int i2) {
        this.f8438c = i2;
        e().getSharedPreferences("guard_verify_sp", 0).edit().putInt("guard_lmt", this.f8438c).apply();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean m(String str, String str2) {
        long j2;
        if (h()) {
            String str3 = "\u7ebf\u4e0a\u52a0\u7b7e\u5f00\u5173 : " + str + " , \u8d85\u65f6\u65f6\u95f4\u6233 : " + str2;
        }
        if (!this.a.isOnlineSwitchOpen()) {
            h();
            return false;
        }
        if (TextUtils.equals(str, "0")) {
            d();
        }
        if (TextUtils.equals(str, "1") && !TextUtils.isEmpty(str2)) {
            try {
                long parseLong = Long.parseLong(str2);
                if (e() != null) {
                    SharedPreferences sharedPreferences = e().getSharedPreferences("guard_verify_sp", 0);
                    if (sharedPreferences.contains("guard_verify_check_time")) {
                        j2 = sharedPreferences.getLong("guard_verify_check_time", 0L);
                        if (h()) {
                            String str4 = "\u4e0a\u4e00\u6b21\u52a0\u7b7e\u9a8c\u8bc1\u65f6\u95f4\u6233 " + j2;
                        }
                        if (j2 > 0) {
                            long j3 = parseLong - j2;
                            if (h()) {
                                String str5 = "\u8ddd\u79bb\u4e0a\u4e00\u6b21\u52a0\u7b7e\u65f6\u95f4\u95f4\u9694 " + j3 + ", \u672c\u5730\u8bbe\u7f6e\u65f6\u95f4\u95f4\u9694 " + f();
                            }
                            if (j3 <= f()) {
                                k(1);
                                return false;
                            }
                        }
                        e().getSharedPreferences("guard_verify_sp", 0).edit().putLong("guard_verify_check_time", parseLong).apply();
                        k(0);
                        return true;
                    }
                }
                j2 = 0;
                if (h()) {
                }
                if (j2 > 0) {
                }
                e().getSharedPreferences("guard_verify_sp", 0).edit().putLong("guard_verify_check_time", parseLong).apply();
                k(0);
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
        h();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str, String str2) {
        Activity currentMyActivity = JDHttpTookit.getEngine().getAppProxy().getCurrentMyActivity();
        if (currentMyActivity == null || currentMyActivity.isFinishing()) {
            return;
        }
        this.b.b(str, str2, currentMyActivity, this.a);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
    public String getLmtValue() {
        this.f8438c = e().getSharedPreferences("guard_verify_sp", 0).getInt("guard_lmt", 0);
        if (h()) {
            String str = "\u83b7\u53d6\u5f53\u524dlmt\u503c " + this.f8438c;
        }
        return String.valueOf(this.f8438c);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
    public String getVerifyToken() {
        if (this.a.isOnlineSwitchOpen() && e() != null && TextUtils.isEmpty(this.d)) {
            synchronized (this) {
                if (TextUtils.isEmpty(this.d)) {
                    String string = e().getSharedPreferences("guard_verify_sp", 0).getString("guard_verify_token", "");
                    if (!TextUtils.isEmpty(string)) {
                        try {
                            this.d = URLEncoder.encode(string, "UTF-8");
                        } catch (UnsupportedEncodingException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        return this.d;
    }

    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
    public boolean isVerifyWindowShowing() {
        return this.b.c();
    }

    public void j(String str) {
        C0265a c0265a = new C0265a(str);
        String str2 = this.a.isDebug() ? "beta-api.m.jd.com" : "api.m.jd.com";
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(str2);
        httpSetting.setAppId(JDHttpTookit.getEngine().getAppId());
        httpSetting.setSecretKey(JDHttpTookit.getEngine().getSecretKey());
        httpSetting.setFunctionId("getLightCapSid");
        httpSetting.setListener(c0265a);
        httpSetting.setNeedGlobalInitialization(false);
        httpSetting.setTopPriority(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setPost(true);
        httpSetting.putJsonParam("functionId", str);
        httpSetting.putJsonParam("ti", String.valueOf(i(f())));
        httpSetting.putJsonParam("ts", String.valueOf(System.currentTimeMillis()));
        httpSetting.putJsonParam("version", "3");
        new HttpGroupUtils();
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void l(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            synchronized (this) {
                try {
                    this.d = URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
                e().getSharedPreferences("guard_verify_sp", 0).edit().putString("guard_verify_token", str).apply();
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
    public void onActivityDestroyed(Activity activity) {
        this.b.d(activity);
    }

    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
    public boolean onLineSwitchOpen() {
        b bVar = this.a;
        return bVar != null && bVar.isOnlineSwitchOpen();
    }

    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
    public void onLogout() {
        c();
    }

    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
    public void onTriggerVerifyCheck(String str, String str2, String str3) {
        if (h()) {
            String str4 = "\u62e6\u622a\u5230\u7f51\u5173\u4e8b\u4ef6 functionId:" + str + " verifyAdd:" + str2 + " expire:" + str3;
        }
        if (!m(str2, str3)) {
            h();
        } else {
            j(str);
        }
    }
}
