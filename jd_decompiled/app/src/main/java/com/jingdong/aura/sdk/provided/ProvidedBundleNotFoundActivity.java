package com.jingdong.aura.sdk.provided;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import com.jingdong.aura.provided.api.IAuraInstallCallBack;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes4.dex */
public class ProvidedBundleNotFoundActivity extends Activity {
    ProvidedBundleNotFoundView a;
    private Date b;

    /* renamed from: c  reason: collision with root package name */
    private Date f12194c;
    private ArrayList<String> d;

    /* renamed from: e  reason: collision with root package name */
    private IBinder f12195e;

    /* renamed from: g  reason: collision with root package name */
    private String f12197g;

    /* renamed from: h  reason: collision with root package name */
    private String f12198h;

    /* renamed from: i  reason: collision with root package name */
    private String f12199i;

    /* renamed from: j  reason: collision with root package name */
    private String f12200j;

    /* renamed from: k  reason: collision with root package name */
    private Uri f12201k;

    /* renamed from: l  reason: collision with root package name */
    private Bundle f12202l;

    /* renamed from: n  reason: collision with root package name */
    private Button f12204n;
    private Button o;
    private FrameLayout p;
    private Runnable q;

    /* renamed from: f  reason: collision with root package name */
    private int f12196f = -1;

    /* renamed from: m  reason: collision with root package name */
    private boolean f12203m = false;

    static /* synthetic */ void a() {
        SharedPreferences sharedPreferences = com.jingdong.aura.sdk.update.a.a().f12239i;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putBoolean("bundle_provided_tip_button", true).apply();
    }

    private static boolean a(String str) {
        SharedPreferences sharedPreferences = com.jingdong.aura.sdk.update.a.a().f12239i;
        if (sharedPreferences == null) {
            return false;
        }
        String string = sharedPreferences.getString("first_to_provided_bundle_name", "");
        return TextUtils.isEmpty(string) || string.equals(str);
    }

    static /* synthetic */ void d(ProvidedBundleNotFoundActivity providedBundleNotFoundActivity) {
        Runnable runnable;
        ProvidedBundleNotFoundView providedBundleNotFoundView = providedBundleNotFoundActivity.a;
        if (providedBundleNotFoundView == null || (runnable = providedBundleNotFoundActivity.q) == null || !providedBundleNotFoundView.removeCallbacks(runnable)) {
            return;
        }
        providedBundleNotFoundActivity.a.post(providedBundleNotFoundActivity.q);
    }

    static /* synthetic */ boolean g(ProvidedBundleNotFoundActivity providedBundleNotFoundActivity) {
        providedBundleNotFoundActivity.f12203m = true;
        return true;
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (this.f12203m) {
            return;
        }
        ((IAuraInstallCallBack) AuraServiceLoader.get(this, IAuraInstallCallBack.class)).installFinished(this.f12198h, true, null);
        com.jingdong.aura.sdk.update.a.a().f12243m.onException(this.f12198h, -1, "install not finished and finish download page!!", "ProvidedBundleNotFoundActivity.finish", null);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00f4  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onCreate(android.os.Bundle r7) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundActivity.onCreate(android.os.Bundle):void");
    }
}
