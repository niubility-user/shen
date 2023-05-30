package com.jd.manto.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.sdk.thread.MantoHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class LoginProxyActivity extends BaseActivity {

    /* renamed from: j  reason: collision with root package name */
    private static final Map<String, Class<? extends Activity>> f6745j;

    /* renamed from: g  reason: collision with root package name */
    ResultReceiver f6746g;

    /* renamed from: h  reason: collision with root package name */
    boolean f6747h = false;

    /* renamed from: i  reason: collision with root package name */
    boolean f6748i = false;

    /* loaded from: classes17.dex */
    public static class LoginResultReceiver extends ResultReceiver {

        /* renamed from: g  reason: collision with root package name */
        ILogin.CallBack f6749g;

        /* renamed from: h  reason: collision with root package name */
        String f6750h;

        /* renamed from: i  reason: collision with root package name */
        String f6751i;

        /* loaded from: classes17.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                LoginResultReceiver loginResultReceiver = LoginResultReceiver.this;
                PkgManager.requestPkgDetail(loginResultReceiver.f6750h, loginResultReceiver.f6751i, null);
            }
        }

        LoginResultReceiver(Handler handler, Bundle bundle, ILogin.CallBack callBack) {
            super(handler);
            this.f6749g = callBack;
            if (bundle != null) {
                this.f6750h = bundle.getString("appId");
                this.f6751i = bundle.getString("debugType");
            }
        }

        @Override // android.os.ResultReceiver
        protected final void onReceiveResult(int i2, Bundle bundle) {
            if (this.f6749g == null) {
                return;
            }
            if (!LoginUserBase.hasLogin() && i2 != -1) {
                this.f6749g.onFailure();
                return;
            }
            this.f6749g.onSuccess();
            if (TextUtils.isEmpty(this.f6750h) || TextUtils.isEmpty(this.f6751i)) {
                return;
            }
            com.jingdong.a.f().networkIO().execute(new a());
        }
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(":manto0", LoginProxyProxyUI0.class);
        hashMap.put(":manto1", LoginProxyProxyUI1.class);
        hashMap.put(":manto2", LoginProxyProxyUI2.class);
        hashMap.put(":manto3", LoginProxyProxyUI3.class);
        hashMap.put(":manto4", LoginProxyProxyUI4.class);
        hashMap.put(":mantoProcess", LoginProxyProxyMT.class);
        f6745j = Collections.unmodifiableMap(hashMap);
    }

    public static void u(String str, Bundle bundle, ILogin.CallBack callBack) {
        String replaceFirst = str.replaceFirst(JdSdk.getInstance().getApplicationContext().getPackageName(), "");
        Class<? extends Activity> cls = LoginProxyActivity.class;
        if (!TextUtils.isEmpty(replaceFirst)) {
            cls = f6745j.get(replaceFirst);
        }
        Intent intent = new Intent(JdSdk.getInstance().getApplicationContext(), cls);
        intent.putExtra("extra_login_resultreceiver", new LoginResultReceiver(MantoHandler.fetchFreeHandler(Looper.getMainLooper()), bundle, callBack));
        intent.addFlags(268435456);
        JdSdk.getInstance().getApplicationContext().startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 255) {
            this.f6746g.send(i3, null);
            this.f6748i = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UnStatusBarTintUtil.setStatusBar4Base(this, 1);
        UnStatusBarTintUtil.setStatusBarLightMode(this);
        overridePendingTransition(0, 0);
        if (getIntent() != null && getIntent().getExtras() != null) {
            this.f6746g = (ResultReceiver) getIntent().getParcelableExtra("extra_login_resultreceiver");
            LoginUserHelper.getInstance().executeLoginRunnable(this, (Runnable) null, 255);
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f6747h = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.f6747h) {
            if (!this.f6748i) {
                if (LoginUserBase.hasLogin()) {
                    this.f6746g.send(-1, null);
                } else {
                    this.f6746g.send(0, null);
                }
            }
            finish();
        }
    }
}
