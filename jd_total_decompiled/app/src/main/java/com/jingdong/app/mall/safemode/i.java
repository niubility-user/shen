package com.jingdong.app.mall.safemode;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class i {
    private boolean a;
    private File b;

    /* renamed from: c  reason: collision with root package name */
    private File f11630c;
    private File d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements j {
        a(i iVar, File file) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class b {
        public static i a = new i(null);
    }

    /* synthetic */ i(a aVar) {
        this();
    }

    private void a() {
        try {
            if (this.d == null) {
                this.d = new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_cache" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat");
            }
            JSONObject b2 = h.b(this.d);
            String userPin = LoginUserBase.getUserPin();
            if (!TextUtils.isEmpty(userPin)) {
                b2.put("p", userPin);
                h.e(b2.toString(), this.d);
            }
            String cookies = SafetyManager.getCookies();
            if (TextUtils.isEmpty(cookies)) {
                return;
            }
            b2.put("c", cookies);
            h.e(b2.toString(), this.d);
        } catch (Throwable unused) {
        }
    }

    private void b() {
        File file = new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_fixing_flag" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat");
        if ("1".equals(h.c(file))) {
            g.c("safemodefixed", "fixed", new a(this, file));
        }
    }

    private JSONObject f() {
        return h.b(this.b);
    }

    public static i g() {
        return b.a;
    }

    private synchronized void k(String str) {
        h.e(str, this.b);
    }

    private void l(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int lastIndexOf = str.lastIndexOf("---");
        if (-1 != lastIndexOf) {
            str = str.substring(lastIndexOf);
            if (str.length() > 300) {
                str = str.substring(3, 300);
            }
        }
        if (this.f11630c == null) {
            this.f11630c = new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_crashinfo" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat");
        }
        a();
        JSONObject b2 = h.b(this.f11630c);
        if (b2 != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("time", new SimpleDateFormat("MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
                jSONObject.put("stack", str);
                JSONArray optJSONArray = b2.optJSONArray("crashArray");
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                }
                while (optJSONArray.length() >= com.jingdong.app.mall.safemode.b.a().b()) {
                    optJSONArray = h.d(optJSONArray, 0);
                }
                optJSONArray.put(jSONObject);
                b2.put("crashArray", optJSONArray);
                h.e(b2.toString(), this.f11630c);
            } catch (Throwable unused) {
            }
        }
    }

    public void c() {
        File file;
        File file2 = this.b;
        if (file2 == null || file2.delete()) {
            return;
        }
        try {
            this.b.deleteOnExit();
            file = this.b;
            if (file == null) {
                return;
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                file = this.b;
                if (file == null) {
                    return;
                }
            } catch (Throwable th2) {
                if (this.b != null) {
                    this.b.delete();
                }
                throw th2;
            }
        }
        file.delete();
    }

    public void d(String str) {
        l(str);
        JSONObject f2 = f();
        if (f2 != null) {
            try {
                f2.put("safeModeCount", f2.optInt("safeModeCount", 0) + 1);
                k(f2.toString());
            } catch (Throwable unused) {
            }
        }
    }

    public String e() {
        try {
            String stringFromPreference = CommonBase.getStringFromPreference("safeModeUsedApkUrl", "");
            String stringFromPreference2 = CommonBase.getStringFromPreference("safeModeUsedApkVersion", "");
            String versionName = PackageInfoUtil.getVersionName();
            if (!TextUtils.isEmpty(stringFromPreference) && !TextUtils.isEmpty(stringFromPreference2) && !TextUtils.isEmpty(versionName) && versionName.trim().compareTo(stringFromPreference2.trim()) < 0) {
                LogUtil.e("SafeModeWatcher", "getApkUrl() local contians latest valid apk url,return!!!");
                return stringFromPreference;
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public boolean h() {
        return this.a;
    }

    public void i() {
        try {
            h.e("1", new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_fixing_flag" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat"));
        } catch (Throwable unused) {
        }
    }

    public synchronized void j() {
        this.a = true;
        c();
        b();
    }

    public boolean m() {
        JSONObject f2;
        if (!com.jingdong.app.mall.safemode.b.a().c() || this.a || (f2 = f()) == null) {
            return false;
        }
        try {
            return f2.optInt("safeModeCount", 0) >= com.jingdong.app.mall.safemode.b.a().b();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void n() {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity != null) {
            currentMyActivity.finish();
        }
        IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
        if (mainFrameActivity != null) {
            mainFrameActivity.finish();
        }
        Application application = JdSdk.getInstance().getApplication();
        Intent intent = new Intent(application, SafeModeFixActivity.class);
        intent.setFlags(1409384452);
        application.startActivity(intent);
    }

    private i() {
        File file = new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_watcher" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat");
        this.b = file;
        if (file.exists()) {
            return;
        }
        try {
            this.b.createNewFile();
        } catch (Throwable unused) {
        }
    }
}
