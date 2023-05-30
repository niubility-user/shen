package com.jingdong.manto.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ICustomMenuInterface;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.sdk.api.IShareManager;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.w;
import com.jingdong.manto.widget.CircleImageView;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.jingdong.manto.widget.g.g;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoAboutActivity extends MantoBaseActivity implements View.OnClickListener, a.b {
    private g b;

    /* renamed from: c  reason: collision with root package name */
    private TextView f14232c;
    private ImageView d;

    /* renamed from: e  reason: collision with root package name */
    private View f14233e;

    /* renamed from: f  reason: collision with root package name */
    private ImageView f14234f;

    /* renamed from: g  reason: collision with root package name */
    private CircleImageView f14235g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f14236h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f14237i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f14238j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f14239k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f14240l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f14241m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f14242n;
    private TextView o;
    private View p;
    private View q;
    private View r;
    private View s;
    private View t;
    private ImageView u;
    private RelativeLayout v;
    private PkgDetailEntity w;
    private Handler a = new Handler();
    private boolean x = true;

    /* loaded from: classes16.dex */
    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: com.jingdong.manto.ui.MantoAboutActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class RunnableC0680a implements Runnable {
            RunnableC0680a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MantoAboutActivity mantoAboutActivity = MantoAboutActivity.this;
                if (mantoAboutActivity == null || mantoAboutActivity.isFinishing()) {
                    return;
                }
                MantoAboutActivity mantoAboutActivity2 = MantoAboutActivity.this;
                mantoAboutActivity2.a(mantoAboutActivity2.w);
            }
        }

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            PkgDetailEntity c2 = com.jingdong.manto.b.k().c(this.a, this.b);
            if (c2 != null) {
                MantoAboutActivity.this.w = c2;
                MantoAboutActivity.this.a.post(new RunnableC0680a());
                return;
            }
            MantoLog.d("better", "about ui detail is null,db is: " + com.jingdong.manto.b.m());
        }
    }

    /* loaded from: classes16.dex */
    class b implements IShareManager.ShareCallback {
        b() {
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareCancel() {
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareClickChannel(Bundle bundle) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("channel", bundle.getString("shareChannel", ""));
                jSONObject.put("vapp_type", MantoAboutActivity.this.w.type);
                MantoTrack.sendCommonDataWithExt(MantoProcessUtil.getContext(), "\u5206\u4eab", "applets_capsule_share_channel", MantoAboutActivity.this.w.appId, "\u5206\u4eab\u5f39\u7a97", "", jSONObject.toString(), "applets_share", null);
            } catch (Exception e2) {
                MantoLog.e(DYConstants.DY_TRACK, e2);
            }
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareFailed(Bundle bundle) {
        }

        @Override // com.jingdong.manto.sdk.api.IShareManager.ShareCallback
        public void onShareSuccess(Bundle bundle) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements g.c {
        c() {
        }

        @Override // com.jingdong.manto.widget.g.g.c
        public void a(com.jingdong.manto.widget.j.d dVar) {
            if (dVar.b() != 0 || MantoAboutActivity.this.w == null) {
                return;
            }
            MantoAboutActivity mantoAboutActivity = MantoAboutActivity.this;
            MantoSettingActivity.a(mantoAboutActivity, mantoAboutActivity.w.appId, MantoAboutActivity.this.w.name, MantoAboutActivity.this.w.versionName);
        }
    }

    private void a() {
        this.f14232c = (TextView) findViewById(R.id.manto_ui_nav_title);
        this.d = (ImageView) findViewById(R.id.manto_ui_nav_back);
        this.f14233e = findViewById(R.id.manto_ui_actionbar);
        this.f14234f = (ImageView) findViewById(R.id.manto_ui_nav_option);
        this.r = findViewById(R.id.manto_about_main_info);
        this.f14235g = (CircleImageView) findViewById(R.id.manto_app_logo);
        this.f14236h = (TextView) findViewById(R.id.manto_app_name);
        this.f14237i = (TextView) findViewById(R.id.manto_app_desc);
        this.s = findViewById(R.id.manto_about_list);
        this.t = findViewById(R.id.divider_view);
        this.f14238j = (TextView) findViewById(R.id.manto_app_about_service_type);
        this.f14239k = (TextView) findViewById(R.id.manto_app_about_service_tel);
        this.p = findViewById(R.id.manto_app_share);
        this.q = findViewById(R.id.manto_app_about_service_tel_area);
        this.o = (TextView) findViewById(R.id.tv_vender_name);
        this.f14240l = (TextView) findViewById(R.id.title_service);
        this.f14241m = (TextView) findViewById(R.id.title_tel);
        this.f14242n = (TextView) findViewById(R.id.tv_vender_title);
        this.u = (ImageView) findViewById(R.id.manto_chartered_arrow);
        this.v = (RelativeLayout) findViewById(R.id.manto_app_about_vender_area);
        this.f14232c.setText("\u5173\u4e8e");
        this.d.setOnClickListener(this);
        this.f14239k.setOnClickListener(this);
        this.b = new g(this);
    }

    private void a(int i2) {
        View view;
        Resources resources;
        int i3;
        int color = getResources().getColor(R.color.manto_white);
        int color2 = getResources().getColor(R.color.manto_black);
        if (i2 == 0) {
            Resources resources2 = getResources();
            int i4 = R.color.manto_day_text_weight;
            int color3 = resources2.getColor(i4);
            int color4 = getResources().getColor(i4);
            int color5 = getResources().getColor(R.color.manto_day_background_light);
            int color6 = getResources().getColor(R.color.manto_day_background_weight);
            MantoStatusBarUtil.setStatusBarColor(this, -1, true);
            this.f14232c.setTextColor(color3);
            this.d.setColorFilter(color2);
            this.f14233e.setBackgroundColor(color5);
            this.f14234f.setColorFilter(color2);
            this.r.setBackgroundColor(color5);
            this.f14236h.setTextColor(color3);
            this.f14237i.setTextColor(color4);
            this.s.setBackgroundColor(color5);
            this.t.setBackgroundColor(color6);
            this.f14240l.setTextColor(color3);
            this.f14238j.setTextColor(color4);
            this.f14242n.setTextColor(color3);
            this.o.setTextColor(color4);
            this.u.setColorFilter(color4);
            this.f14241m.setTextColor(color3);
            this.f14239k.setTextColor(color4);
            view = this.p;
            resources = getResources();
            i3 = R.color.manto_open_main_color;
        } else {
            int color7 = getResources().getColor(R.color.manto_dark_text_light);
            int color8 = getResources().getColor(R.color.manto_dark_text_weight);
            int color9 = getResources().getColor(R.color.manto_dark_background_light);
            int color10 = getResources().getColor(R.color.manto_dark_background_weight);
            MantoStatusBarUtil.setStatusBarColor(this, color9, false);
            this.f14232c.setTextColor(color8);
            this.d.setColorFilter(color);
            this.f14233e.setBackgroundColor(color9);
            this.f14234f.setColorFilter(color);
            this.r.setBackgroundColor(color9);
            this.f14236h.setTextColor(color8);
            this.f14237i.setTextColor(color7);
            this.s.setBackgroundColor(color9);
            this.t.setBackgroundColor(color10);
            this.f14240l.setTextColor(color8);
            this.f14238j.setTextColor(color7);
            this.f14242n.setTextColor(color8);
            this.o.setTextColor(color7);
            this.u.setColorFilter(color7);
            this.f14241m.setTextColor(color8);
            this.f14239k.setTextColor(color7);
            view = this.p;
            resources = getResources();
            i3 = R.color.manto_dark_open_main_color;
        }
        view.setBackgroundColor(resources.getColor(i3));
    }

    public static void a(Context context, String str, String str2, boolean z) {
        Intent intent = new Intent(context, MantoAboutActivity.class);
        if (MantoStringUtils.isEmpty(str)) {
            return;
        }
        intent.putExtra("app_id", str);
        intent.putExtra("app_type", str2);
        intent.putExtra("show_share", z);
        context.startActivity(intent);
    }

    private void a(Intent intent) {
        this.x = intent.getExtras().getBoolean("show_share", true);
        ICustomMenuInterface iCustomMenuInterface = (ICustomMenuInterface) com.jingdong.a.n(ICustomMenuInterface.class);
        if (iCustomMenuInterface != null) {
            if (!this.x || iCustomMenuInterface.disableAboutShare()) {
                this.p.setVisibility(4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(PkgDetailEntity pkgDetailEntity) {
        IImageLoader iImageLoader;
        this.f14234f.setOnClickListener(this);
        this.p.setOnClickListener(this);
        if (!MantoStringUtils.isEmpty(pkgDetailEntity.name)) {
            this.f14236h.setText(pkgDetailEntity.name);
        }
        if (!MantoStringUtils.isEmpty(pkgDetailEntity.description)) {
            this.f14237i.setText(pkgDetailEntity.description);
        }
        if (!MantoStringUtils.isEmpty(pkgDetailEntity.logo) && (iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class)) != null) {
            iImageLoader.loadImage(this.f14235g, pkgDetailEntity.logo);
        }
        if (!MantoStringUtils.isEmpty(pkgDetailEntity.categories)) {
            this.f14238j.setText(pkgDetailEntity.categories);
        }
        if (MantoStringUtils.isEmpty(pkgDetailEntity.servicePhone)) {
            this.q.setVisibility(8);
        } else {
            this.q.setVisibility(0);
            this.f14239k.setText(pkgDetailEntity.servicePhone);
        }
        this.o.setText(pkgDetailEntity.venderName);
        if (!MantoStringUtils.isEmpty(pkgDetailEntity.charteredUrl)) {
            this.u.setVisibility(0);
            this.v.setOnClickListener(this);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", pkgDetailEntity.appId);
            if (pkgDetailEntity != null) {
                jSONObject.put("vapp_type", pkgDetailEntity.type);
                jSONObject.put("version", String.valueOf(pkgDetailEntity.build));
            }
        } catch (JSONException e2) {
            MantoLog.e("MantoAboutUI", e2);
        }
        MantoTrack.sendPagePv(com.jingdong.manto.c.a(), "\u5173\u4e8e", jSONObject.toString(), "applets_pages", null);
    }

    private void a(String str) {
        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(WebView.SCHEME_TEL + str.trim())));
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        com.jingdong.manto.widget.j.b bVar = new com.jingdong.manto.widget.j.b(this, 0);
        bVar.a("\u8bbe\u7f6e").a(R.drawable.manto_menu_setting).a(true);
        arrayList.add(bVar);
        this.b.a(arrayList);
        this.b.a(new c());
        this.b.showAsDropDown(this.f14233e);
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity
    public int getLayoutId() {
        return R.layout.manto_ui_about;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PkgDetailEntity pkgDetailEntity;
        PkgDetailEntity pkgDetailEntity2;
        if (view.getId() == R.id.manto_ui_nav_back) {
            finish();
        }
        if (view.getId() == R.id.manto_ui_nav_option) {
            b();
        }
        if (view.getId() == R.id.manto_app_share && (pkgDetailEntity2 = this.w) != null) {
            w.a(this, pkgDetailEntity2, null, new b());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("vapp_type", this.w.type);
                jSONObject.put("url", "about");
            } catch (JSONException e2) {
                MantoLog.e(DYConstants.DY_TRACK, e2);
            }
            MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u5173\u4e8e-\u63a8\u8350", "applets_capsule_about_share", this.w.appId, "\u5173\u4e8e\u9875\u9762", "", jSONObject.toString(), "applets_about", null);
        }
        if (view.getId() == R.id.manto_app_about_service_tel && (pkgDetailEntity = this.w) != null && !MantoStringUtils.isEmpty(pkgDetailEntity.servicePhone)) {
            a(this.w.servicePhone);
        }
        if (view.getId() == R.id.manto_app_about_vender_area) {
            Intent intent = new Intent(this, MantoWebActivity.class);
            intent.putExtra("url", this.w.charteredUrl);
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a();
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        String string = intent.getExtras().getString("app_id");
        String string2 = intent.getExtras().getString("app_type");
        a(intent);
        if (MantoStringUtils.isEmpty(string)) {
            return;
        }
        com.jingdong.manto.b.d().diskIO().execute(new a(string, string2));
        com.jingdong.manto.k.a.b().a(this);
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jingdong.manto.k.a.b().b(this);
    }
}
