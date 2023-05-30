package com.jingdong.app.mall.nfc;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.DeviceFinger;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.login.LoginUserHelper;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.log.Log;

/* loaded from: classes4.dex */
public class NfcIntentHandleActivity extends BaseActivity {

    /* renamed from: n  reason: collision with root package name */
    private static final String f11405n = NfcIntentHandleActivity.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private b f11406g;

    /* renamed from: h  reason: collision with root package name */
    private g.f.c.b.a f11407h;

    /* renamed from: i  reason: collision with root package name */
    private Intent f11408i;

    /* renamed from: j  reason: collision with root package name */
    private JDLocationCacheOption f11409j = new JDLocationCacheOption();

    /* renamed from: k  reason: collision with root package name */
    private String f11410k = LoginUserHelper.getInstance().getLoginUser().getLoginUserName();

    /* renamed from: l  reason: collision with root package name */
    private double f11411l = JDLocationCache.getInstance().getLocation(this.f11409j).getLng();

    /* renamed from: m  reason: collision with root package name */
    private double f11412m = JDLocationCache.getInstance().getLocation(this.f11409j).getLat();

    /* loaded from: classes4.dex */
    class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            try {
                if (NfcIntentHandleActivity.this.f11407h != null) {
                    NfcIntentHandleActivity.this.f11407h.d(NfcIntentHandleActivity.this.f11408i);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private void y() {
        b bVar = new b(this);
        this.f11406g = bVar;
        bVar.d();
        try {
            g.f.c.b.a aVar = new g.f.c.b.a(this, x(), Configuration.getPortalHost().equals("api.m.jd.com") ? "https://rfidauth-xl.jd.com/auth" : "https://rfidauth-xlyfdev.jd.com/auth");
            this.f11407h = aVar;
            aVar.c();
        } catch (Throwable unused) {
        }
    }

    private void z(Intent intent) {
        if (intent == null || this.f11406g == null) {
            return;
        }
        this.f11408i = intent;
        if (c.e((Tag) intent.getParcelableExtra("android.nfc.extra.TAG"))) {
            DeepLinkChargeHelper.startYCTNfcActivity(this, intent);
            finish();
        } else if (this.f11406g.c(intent) == 196) {
            Toast.makeText(this, (int) R.string.nfc_no_support, 0).show();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!JDPrivacyHelper.isAcceptPrivacy(this)) {
            Log.i(f11405n, "Failed privacy authentication\uff0cActivity Will be finished");
            finish();
            return;
        }
        Log.i(f11405n, "Accept privacy authentication\uff0cThe business to continue");
        y();
        z(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(f11405n, "-------onNewIntent intent:" + intent);
        if (intent == null) {
            return;
        }
        setIntent(intent);
        z(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        b bVar = this.f11406g;
        if (bVar != null) {
            bVar.a();
        }
        try {
            g.f.c.b.a aVar = this.f11407h;
            if (aVar != null) {
                aVar.a();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b bVar = this.f11406g;
        if (bVar != null) {
            bVar.b();
        }
        try {
            g.f.c.b.a aVar = this.f11407h;
            if (aVar != null) {
                aVar.b();
            }
        } catch (Throwable unused) {
        }
    }

    public void w() {
        new Thread(new a()).start();
        finish();
    }

    public String x() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.put("pin", (Object) this.f11410k);
            jDJSONObject.put(HybridSDK.LNG, (Object) Double.valueOf(this.f11411l));
            jDJSONObject.put("lat", (Object) Double.valueOf(this.f11412m));
            jDJSONObject.put(HybridSDK.D_BRAND, (Object) BaseInfo.getDeviceBrand());
            jDJSONObject.put(HybridSDK.D_MODEL, (Object) BaseInfo.getDeviceBrand());
            jDJSONObject.put("client", (Object) "Android");
            jDJSONObject.put(HybridSDK.OS_VERSION, (Object) Build.VERSION.RELEASE);
            String str = "";
            jDJSONObject.put(HybridSDK.APP_VERSION, (Object) (TextUtils.isEmpty(PackageInfoUtil.getVersionName()) ? "" : PackageInfoUtil.getVersionName()));
            if (!TextUtils.isEmpty(DeviceFinger.getFinger(JdSdk.getInstance().getApplicationContext()))) {
                str = DeviceFinger.getFinger(JdSdk.getInstance().getApplicationContext());
            }
            jDJSONObject.put("eid", (Object) str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jDJSONObject.toString();
    }
}
