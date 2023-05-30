package com.jingdong.manto.jsapi.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.pkg.db.entity.DomainBlackListEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.sdk.api.IWebview;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.widget.MantoStatusBarUtil;

/* loaded from: classes15.dex */
public class DownGradeToH5Activity extends FragmentActivity implements View.OnClickListener, a.b {
    private TextView a;
    private ImageView b;

    /* renamed from: c  reason: collision with root package name */
    private View f13190c;
    private ViewGroup d;

    /* renamed from: e  reason: collision with root package name */
    private g f13191e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements ILogin.WebCookieCallBack {
        final /* synthetic */ String a;

        /* renamed from: com.jingdong.manto.jsapi.webview.DownGradeToH5Activity$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0530a implements Runnable {
            final /* synthetic */ String a;

            RunnableC0530a(String str) {
                this.a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                DownGradeToH5Activity.this.a(this.a);
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                DownGradeToH5Activity.this.a(aVar.a);
            }
        }

        a(String str) {
            this.a = str;
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.WebCookieCallBack
        public void onFailure() {
            MantoThreadUtils.runOnUIThread(new b());
        }

        @Override // com.jingdong.manto.sdk.api.ILogin.WebCookieCallBack
        public void onSuccess(String str) {
            MantoThreadUtils.runOnUIThread(new RunnableC0530a(str));
        }
    }

    private void a(int i2) {
        int color = getResources().getColor(R.color.manto_white);
        int color2 = getResources().getColor(R.color.manto_black);
        if (i2 == 0) {
            int color3 = getResources().getColor(R.color.manto_day_text_weight);
            int color4 = getResources().getColor(R.color.manto_day_background_light);
            MantoStatusBarUtil.setStatusBarColor(this, -1, true);
            this.a.setTextColor(color3);
            this.b.setColorFilter(color2);
            this.f13190c.setBackgroundColor(color4);
            return;
        }
        int color5 = getResources().getColor(R.color.manto_dark_text_weight);
        int color6 = getResources().getColor(R.color.manto_dark_background_light);
        MantoStatusBarUtil.setStatusBarColor(this, color6, false);
        this.a.setTextColor(color5);
        this.b.setColorFilter(color);
        this.f13190c.setBackgroundColor(color6);
    }

    public static void a(Context context, String str, String str2) {
        if (context == null) {
            try {
                context = com.jingdong.a.g();
            } catch (Throwable unused) {
                return;
            }
        }
        Intent intent = new Intent(context, DownGradeToH5Activity.class);
        intent.putExtra("url", str);
        intent.putExtra("appName", str2);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        IWebview.IMantoWebViewInterface webView;
        g gVar = this.f13191e;
        if (gVar == null || (webView = gVar.getWebView()) == null) {
            return;
        }
        webView.loadUrl(str);
    }

    private void a(String str, String str2) {
        this.a = (TextView) findViewById(R.id.manto_ui_nav_title);
        this.b = (ImageView) findViewById(R.id.manto_ui_nav_back);
        this.f13190c = findViewById(R.id.manto_ui_actionbar);
        this.d = (ViewGroup) findViewById(R.id.manto_ui_webview);
        this.a.setText(str2);
        this.b.setOnClickListener(this);
        g gVar = new g((Activity) this, (DomainBlackListEntity) null, (String[]) null, "", false);
        this.f13191e = gVar;
        this.d.addView(gVar);
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin == null || !iLogin.needSyncWebCookies()) {
            a(str);
        } else {
            iLogin.syncWebCookies(str, new a(str));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        g gVar = this.f13191e;
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
        setContentView(R.layout.manto_down_grade_activity);
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("url");
        String stringExtra2 = intent.getStringExtra("appName");
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
        } else if (!stringExtra.startsWith("http:") && !stringExtra.startsWith("https:")) {
            finish();
        } else {
            a(stringExtra, stringExtra2);
            com.jingdong.manto.k.a.b().a(this);
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
}
