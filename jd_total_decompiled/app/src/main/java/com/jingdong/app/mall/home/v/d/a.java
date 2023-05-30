package com.jingdong.app.mall.home.v.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.v.b;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.bmode.util.JDBModeManager;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {
    private static b.a d;
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c */
    private static final AtomicBoolean f10981c = new AtomicBoolean(false);

    /* renamed from: e */
    private static final BroadcastReceiver f10982e = new C0333a();

    /* renamed from: com.jingdong.app.mall.home.v.d.a$a */
    /* loaded from: classes4.dex */
    class C0333a extends BroadcastReceiver {

        /* renamed from: com.jingdong.app.mall.home.v.d.a$a$a */
        /* loaded from: classes4.dex */
        class C0334a extends com.jingdong.app.mall.home.o.a.b {
            C0334a(C0333a c0333a) {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                com.jingdong.app.mall.home.state.dark.a.a();
                if (a.d != null) {
                    a.d.a();
                }
            }
        }

        C0333a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                if (TextUtils.equals(intent.getAction(), JDBModeManager.ACTION_MODE_SWITCH) && TextUtils.equals((CharSequence) ((HashMap) intent.getSerializableExtra(JDBModeManager.KEY_EXTRA)).get(JDBModeManager.KEY_EXTRA_MAP_PARAM_TO_FINAL), "1")) {
                    f.E0(new C0334a(this));
                }
            } catch (Throwable th) {
                th.printStackTrace();
                f.o(th.getMessage());
            }
        }
    }

    public static void b(JSONObject jSONObject) {
        try {
            if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
                AtomicBoolean atomicBoolean = f10981c;
                if (atomicBoolean.get()) {
                    return;
                }
                atomicBoolean.set(true);
                String requestBodyParam = JDBModeUtils.getRequestBodyParam();
                if (k.w()) {
                    requestBodyParam = k.f10483i;
                }
                if (TextUtils.isEmpty(requestBodyParam)) {
                    return;
                }
                jSONObject.put("bparams", requestBodyParam);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void c() {
        AtomicBoolean atomicBoolean = a;
        atomicBoolean.set(!atomicBoolean.get());
        if (atomicBoolean.get()) {
            JDBModeUtils.changeToBMode("1");
        } else {
            JDBModeUtils.changeToNormalMode("1");
        }
        b.a aVar = d;
        if (aVar != null) {
            aVar.a();
        }
    }

    public static boolean d() {
        return f() && e();
    }

    public static boolean e() {
        return TextUtils.equals(JDBModeUtils.getCurrentMode(), "2");
    }

    public static boolean f() {
        return a.get();
    }

    public static boolean g() {
        return f() || e();
    }

    public static boolean h() {
        return !a.get() || g.p();
    }

    public static void i(BaseActivity baseActivity, b.a aVar) {
        try {
            d = aVar;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(JDBModeManager.ACTION_MODE_SWITCH);
            LocalBroadcastManager.getInstance(baseActivity).registerReceiver(f10982e, intentFilter);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void j(JDJSONObject jDJSONObject, boolean z) {
        a.set(jDJSONObject.optInt("appType", 0) == 4);
        com.jingdong.app.mall.home.state.dark.a.a();
        if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) || z) {
            return;
        }
        AtomicBoolean atomicBoolean = b;
        if (atomicBoolean.get() || jDJSONObject.optInt("serverCache", 0) == 1) {
            return;
        }
        atomicBoolean.set(true);
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("adviseVersionInfo");
        if (optJSONObject != null) {
            JDBModeUtils.handleMajorResponse(optJSONObject.toString());
        }
    }
}
