package com.jingdong.aura.sdk.provided;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import com.jingdong.aura.provided.api.IAuraInstallCallBack;
import com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView;
import com.jingdong.aura.sdk.update.R;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.aura.wrapper.AuraConfig;
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
    */
    protected void onCreate(Bundle bundle) {
        final boolean z;
        ArrayList<String> arrayList;
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.f12200j = intent.getStringExtra("trigger");
        setContentView(R.layout.provided_bundle_download_container);
        this.a = (ProvidedBundleNotFoundView) findViewById(R.id.provided_bundle_notfound_view);
        if (TextUtils.isEmpty(this.f12200j)) {
            this.a.setDownLoadFrom(3);
        } else {
            this.a.setDownLoadFrom(1);
        }
        this.a.setIsVisibleToUser(true);
        ((Button) findViewById(R.id.provided_bundle_title_back)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundActivity.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProvidedBundleNotFoundActivity.this.finish();
            }
        });
        this.p = (FrameLayout) findViewById(R.id.provided_bundle_download_tip_container);
        this.f12204n = (Button) findViewById(R.id.provided_tip_button_open);
        this.o = (Button) findViewById(R.id.provided_tip_button_off);
        this.p.setVisibility(4);
        this.o.setVisibility(0);
        this.f12204n.setVisibility(4);
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundActivity.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProvidedBundleNotFoundActivity.this.o.setVisibility(4);
                ProvidedBundleNotFoundActivity.this.f12204n.setVisibility(0);
                ProvidedBundleNotFoundActivity.a();
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(500L);
                ProvidedBundleNotFoundActivity.this.p.startAnimation(alphaAnimation);
                ProvidedBundleNotFoundActivity.this.p.setVisibility(4);
                com.jingdong.aura.sdk.provided.ui.a.a(ProvidedBundleNotFoundActivity.this, "\u5df2\u8bbe\u7f6e\u6210\u529f!");
                ProvidedBundleNotFoundActivity.d(ProvidedBundleNotFoundActivity.this);
            }
        });
        this.f12197g = intent.getStringExtra("aura_target_classname");
        this.f12198h = intent.getStringExtra("aura_target_bundlename");
        this.d = intent.getStringArrayListExtra("aura_not_prepared_bundlename");
        Bundle extras = intent.getExtras();
        if (Build.VERSION.SDK_INT >= 18) {
            this.f12195e = extras.getBinder("mToken");
        }
        this.f12196f = intent.getIntExtra("requestCode", -1);
        this.f12199i = intent.getStringExtra("aura_action");
        this.f12201k = intent.getData();
        this.f12202l = intent.getExtras();
        if (!a(this.f12198h)) {
            SharedPreferences sharedPreferences = com.jingdong.aura.sdk.update.a.a().f12239i;
            if (!(sharedPreferences == null ? false : sharedPreferences.getBoolean("bundle_provided_tip_button", false))) {
                z = true;
                if (z) {
                    this.p.setVisibility(0);
                }
                if (a(this.f12198h)) {
                    String str = this.f12198h;
                    SharedPreferences sharedPreferences2 = com.jingdong.aura.sdk.update.a.a().f12239i;
                    if (sharedPreferences2 != null) {
                        sharedPreferences2.edit().putString("first_to_provided_bundle_name", str).apply();
                    }
                }
                this.b = new Date(System.currentTimeMillis());
                arrayList = this.d;
                if (arrayList != null || arrayList.size() <= 0) {
                }
                if (c.a.containsKey(this.f12198h)) {
                    c.a(this.f12198h, false);
                } else {
                    c.a(this.f12198h, true);
                }
                this.a.a(this.d, this.f12198h, "activity");
                this.a.setProvidedBundleDownloadListener(new ProvidedBundleNotFoundView.g() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundActivity.3
                    @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.g
                    public final void a() {
                        if ("installbundle".equals(ProvidedBundleNotFoundActivity.this.f12199i)) {
                            ProvidedBundleNotFoundActivity.this.runOnUiThread(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundActivity.3.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    ((IAuraInstallCallBack) AuraServiceLoader.get(ProvidedBundleNotFoundActivity.this, IAuraInstallCallBack.class)).installFinished(ProvidedBundleNotFoundActivity.this.f12198h, true, null);
                                    ProvidedBundleNotFoundActivity.g(ProvidedBundleNotFoundActivity.this);
                                    ProvidedBundleNotFoundActivity.this.finish();
                                }
                            });
                            return;
                        }
                        ProvidedBundleNotFoundActivity.this.f12194c = new Date(System.currentTimeMillis());
                        long time = 3000 - (ProvidedBundleNotFoundActivity.this.f12194c.getTime() - ProvidedBundleNotFoundActivity.this.b.getTime());
                        ProvidedBundleNotFoundActivity.this.q = new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundActivity.3.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                Intent intent2 = new Intent();
                                ProvidedBundleNotFoundActivity providedBundleNotFoundActivity = ProvidedBundleNotFoundActivity.this;
                                intent2.setComponent(new ComponentName(providedBundleNotFoundActivity, providedBundleNotFoundActivity.f12197g));
                                if (ProvidedBundleNotFoundActivity.this.f12201k != null) {
                                    intent2.setData(ProvidedBundleNotFoundActivity.this.f12201k);
                                }
                                if (ProvidedBundleNotFoundActivity.this.f12202l != null) {
                                    intent2.putExtras(ProvidedBundleNotFoundActivity.this.f12202l);
                                }
                                ProvidedBundleNotFoundActivity.this.finish();
                                if (ProvidedBundleNotFoundActivity.this.f12196f < 0 || ProvidedBundleNotFoundActivity.this.f12195e == null) {
                                    com.jingdong.aura.sdk.update.b.c.a("startActivity: intent:".concat(String.valueOf(intent2)));
                                    ProvidedBundleNotFoundActivity.this.startActivity(intent2);
                                    return;
                                }
                                Activity activity = AuraConfig.getActivity(ProvidedBundleNotFoundActivity.this.f12195e);
                                com.jingdong.aura.sdk.update.b.c.a("startActivityForResult: requestCode:" + ProvidedBundleNotFoundActivity.this.f12196f + "intent:" + intent2 + " activity:" + activity);
                                if (activity != null) {
                                    activity.startActivityForResult(intent2, ProvidedBundleNotFoundActivity.this.f12196f);
                                } else {
                                    ProvidedBundleNotFoundActivity.this.startActivity(intent2);
                                }
                            }
                        };
                        if (z) {
                            ProvidedBundleNotFoundActivity providedBundleNotFoundActivity = ProvidedBundleNotFoundActivity.this;
                            providedBundleNotFoundActivity.a.postDelayed(providedBundleNotFoundActivity.q, time);
                            return;
                        }
                        ProvidedBundleNotFoundActivity providedBundleNotFoundActivity2 = ProvidedBundleNotFoundActivity.this;
                        providedBundleNotFoundActivity2.a.post(providedBundleNotFoundActivity2.q);
                    }
                });
                return;
            }
        }
        z = false;
        if (z) {
        }
        if (a(this.f12198h)) {
        }
        this.b = new Date(System.currentTimeMillis());
        arrayList = this.d;
        if (arrayList != null) {
        }
    }
}
