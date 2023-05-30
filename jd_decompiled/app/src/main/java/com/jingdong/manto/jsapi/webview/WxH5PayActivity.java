package com.jingdong.manto.jsapi.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.webview.g;
import com.jingdong.manto.k.a;
import com.jingdong.manto.pkg.db.entity.DomainBlackListEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class WxH5PayActivity extends FragmentActivity implements View.OnClickListener, a.b, g.InterfaceC0533g {
    private TextView a;
    private ImageView b;

    /* renamed from: c  reason: collision with root package name */
    private View f13192c;
    private ViewGroup d;

    /* renamed from: e  reason: collision with root package name */
    private g f13193e;

    /* renamed from: f  reason: collision with root package name */
    private JSONObject f13194f;

    /* renamed from: g  reason: collision with root package name */
    private String f13195g;

    /* renamed from: h  reason: collision with root package name */
    private String f13196h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f13197i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f13198j;

    private void a(int i2) {
        int color = getResources().getColor(R.color.manto_white);
        int color2 = getResources().getColor(R.color.manto_black);
        if (i2 == 0) {
            int color3 = getResources().getColor(R.color.manto_day_text_weight);
            int color4 = getResources().getColor(R.color.manto_day_background_light);
            MantoStatusBarUtil.setStatusBarColor(this, -1, true);
            this.a.setTextColor(color3);
            this.b.setColorFilter(color2);
            this.f13192c.setBackgroundColor(color4);
            return;
        }
        int color5 = getResources().getColor(R.color.manto_dark_text_weight);
        int color6 = getResources().getColor(R.color.manto_dark_background_light);
        MantoStatusBarUtil.setStatusBarColor(this, color6, false);
        this.a.setTextColor(color5);
        this.b.setColorFilter(color);
        this.f13192c.setBackgroundColor(color6);
    }

    private void a(String str, String str2, String str3) {
        this.a = (TextView) findViewById(R.id.manto_ui_nav_title);
        this.b = (ImageView) findViewById(R.id.manto_ui_nav_back);
        this.f13192c = findViewById(R.id.manto_ui_actionbar);
        this.d = (ViewGroup) findViewById(R.id.manto_ui_webview);
        this.a.setText(getString(R.string.manto_wx_h5_pay_title));
        this.b.setOnClickListener(this);
        DomainBlackListEntity c2 = com.jingdong.manto.b.k().c();
        PkgDetailEntity k2 = com.jingdong.a.k(str, str2);
        String str4 = k2 != null ? k2.domains : "";
        MantoLog.d("WxH5PayActivity", "domainBlackListEntity:" + c2 + ", miniapp white domains:" + str4);
        String[] split = TextUtils.isEmpty(str4) ? null : str4.split("@,@");
        g gVar = new g((Activity) this, c2, split, this.f13196h, true);
        this.f13193e = gVar;
        gVar.setH5PayInterruptCallbackReference(this);
        this.d.addView(this.f13193e);
        if (b.a(c2, split, str3)) {
            str3 = b.a();
        }
        this.f13193e.getWebView().loadUrl(str3);
    }

    public static boolean a(Activity activity, String str, String str2, String str3, JSONObject jSONObject, String str4) {
        try {
            Intent intent = new Intent(activity, WxH5PayActivity.class);
            intent.putExtra("appId", str);
            intent.putExtra("appType", str2);
            intent.putExtra("url", str3);
            intent.putExtra("mtaJson", jSONObject.toString());
            intent.putExtra("merchantDomain", str4);
            activity.startActivity(intent);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.jingdong.manto.jsapi.webview.g.InterfaceC0533g
    public void a() {
        this.f13198j = true;
        JSONObject jSONObject = this.f13194f;
        if (jSONObject != null) {
            try {
                jSONObject.put("host_id", com.jingdong.a.b);
                this.f13194f.put("success", "\u6210\u529f");
                MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u7528\u6237\u8df3\u8f6c\u5fae\u4fe1", "applets_WXPayment_Jump", this.f13195g, "", "", this.f13194f.toString(), "", null);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.jingdong.manto.jsapi.webview.g.InterfaceC0533g
    public void b() {
        this.f13198j = true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        g gVar = this.f13193e;
        if (gVar == null || !gVar.b()) {
            super.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.manto_ui_nav_back) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.manto_wx_h5_pay);
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            Toast.makeText(this, "\u6ca1\u6709\u652f\u4ed8\u4fe1\u606f", 0).show();
            finish();
            return;
        }
        this.f13195g = intent.getStringExtra("appId");
        this.f13196h = intent.getStringExtra("merchantDomain");
        String stringExtra = intent.getStringExtra("appType");
        String stringExtra2 = intent.getStringExtra("url");
        try {
            this.f13194f = new JSONObject(intent.getStringExtra("mtaJson"));
        } catch (Throwable unused) {
        }
        intent.getStringExtra("assistActionName");
        MantoLog.d("WxH5PayActivity", "url:" + stringExtra2);
        MantoLog.d("WxH5PayActivity", "appId:" + this.f13195g);
        MantoLog.d("WxH5PayActivity", "appType:" + stringExtra);
        if (TextUtils.isEmpty(this.f13195g) || TextUtils.isEmpty(stringExtra2)) {
            Toast.makeText(this, "\u652f\u4ed8\u4fe1\u606f\u9519\u8bef", 0).show();
            finish();
        } else if (stringExtra2.startsWith("http:") || stringExtra2.startsWith("https:")) {
            a(this.f13195g, stringExtra, stringExtra2);
            com.jingdong.manto.k.a.b().a(this);
        } else {
            Toast.makeText(this, "\u652f\u4ed8\u4fe1\u606f\u53c2\u6570\u5f02\u5e38", 0).show();
            finish();
        }
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jingdong.manto.k.a.b().b(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!this.f13197i) {
            this.f13197i = true;
        } else if (this.f13198j) {
            finish();
        }
    }
}
