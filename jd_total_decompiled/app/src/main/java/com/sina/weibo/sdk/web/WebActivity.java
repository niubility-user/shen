package com.sina.weibo.sdk.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import com.sina.weibo.sdk.b.e;
import com.sina.weibo.sdk.web.b.b;

/* loaded from: classes9.dex */
public class WebActivity extends Activity implements com.sina.weibo.sdk.web.a {
    private LinearLayout am;
    private TextView an;
    private TextView ao;
    private WebView ap;
    private ProgressBar aq;
    private com.sina.weibo.sdk.web.b.b ar;
    private com.sina.weibo.sdk.web.a.b as;
    private String at;

    /* loaded from: classes9.dex */
    final class a implements b.a {
        a() {
        }

        @Override // com.sina.weibo.sdk.web.b.b.a
        public final void onComplete() {
            String url = WebActivity.this.ar.getUrl();
            if (TextUtils.isEmpty(url) || !WebActivity.j(url)) {
                return;
            }
            WebActivity.this.ap.loadUrl(url);
        }

        @Override // com.sina.weibo.sdk.web.b.b.a
        public final void onError(String str) {
            WebActivity.this.as.p(str);
        }
    }

    /* loaded from: classes9.dex */
    final class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            WebActivity.this.as.q();
        }
    }

    /* loaded from: classes9.dex */
    final class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            WebActivity.d(WebActivity.this);
            WebActivity.this.ap.reload();
        }
    }

    /* loaded from: classes9.dex */
    final class d extends WebChromeClient {
        d() {
        }

        @Override // android.webkit.WebChromeClient
        public final void onProgressChanged(WebView webView, int i2) {
            super.onProgressChanged(webView, i2);
            WebActivity.this.aq.setProgress(i2);
            if (i2 == 100) {
                WebActivity.this.aq.setVisibility(4);
            } else {
                WebActivity.this.aq.setVisibility(0);
            }
        }

        @Override // android.webkit.WebChromeClient
        public final void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }
    }

    static /* synthetic */ void d(WebActivity webActivity) {
        webActivity.am.setVisibility(8);
        webActivity.ap.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean j(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("https://service.weibo.com/share/mobilesdk.php") || str.startsWith("https://open.weibo.cn/oauth2/authorize?");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            getWindow().getDecorView().setSystemUiVisibility(8192);
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(-1);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        TextView textView = new TextView(this);
        this.an = textView;
        textView.setText(StringUtil.app_error_close);
        this.an.setTextSize(17.0f);
        this.an.setTextColor(-32256);
        this.an.setOnClickListener(new b());
        TextView textView2 = new TextView(this);
        this.ao = textView2;
        textView2.setTextSize(18.0f);
        this.ao.setTextColor(-11382190);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        this.an.setPadding(e.a(10, this), 0, e.a(10, this), 0);
        layoutParams2.addRule(13);
        relativeLayout2.addView(this.an, layoutParams);
        relativeLayout2.addView(this.ao, layoutParams2);
        relativeLayout.addView(relativeLayout2, new RelativeLayout.LayoutParams(-1, e.a(55, this)));
        this.ap = new WebView(getApplicationContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.topMargin = e.a(55, this);
        relativeLayout.addView(this.ap, layoutParams3);
        this.aq = new ProgressBar(this);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, e.a(3, this));
        layoutParams4.topMargin = e.a(55, this);
        relativeLayout.addView(this.aq, layoutParams4);
        View view = new View(this);
        view.setBackgroundResource(getResources().getIdentifier("weibosdk_common_shadow_top", "drawable", getPackageName()));
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, e.a(3, this));
        layoutParams5.topMargin = e.a(55, this);
        relativeLayout.addView(view, layoutParams5);
        LinearLayout linearLayout = new LinearLayout(this);
        this.am = linearLayout;
        linearLayout.setOrientation(1);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(getResources().getIdentifier("weibosdk_empty_failed", "drawable", getPackageName()));
        this.am.addView(imageView);
        TextView textView3 = new TextView(this);
        textView3.setTextSize(14.0f);
        textView3.setTextColor(-4342339);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams6.topMargin = e.a(18, this);
        layoutParams6.bottomMargin = e.a(20, this);
        this.am.addView(textView3, layoutParams6);
        textView3.setText("\u7f51\u7edc\u51fa\u9519\u5566\uff0c\u8bf7\u70b9\u51fb\u6309\u94ae\u91cd\u65b0\u52a0\u8f7d");
        Button button = new Button(this);
        button.setTextSize(16.0f);
        button.setTextColor(-8882056);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(e.a(R2.anim.manto_translate_dialog_in, this), e.a(46, this));
        layoutParams7.gravity = 17;
        this.am.addView(button, layoutParams7);
        button.setBackgroundResource(getResources().getIdentifier("retry_btn_selector", "drawable", getPackageName()));
        button.setText("\u91cd\u65b0\u52a0\u8f7d");
        button.setOnClickListener(new c());
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams8.addRule(13);
        relativeLayout.addView(this.am, layoutParams8);
        this.am.setVisibility(8);
        this.ap.setWebChromeClient(new d());
        setContentView(relativeLayout);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            finish();
            return;
        }
        int intExtra = intent.getIntExtra("web_type", -1);
        if (intExtra == -1) {
            finish();
            return;
        }
        if (intExtra == 1) {
            this.at = "\u5fae\u535a\u5206\u4eab";
            this.ar = new com.sina.weibo.sdk.web.b.d(this);
            this.as = new com.sina.weibo.sdk.web.a.d(this, this, this.ar);
        } else if (intExtra == 2) {
            this.at = "\u5fae\u535a\u767b\u5f55";
            this.ar = new com.sina.weibo.sdk.web.b.a();
            this.as = new com.sina.weibo.sdk.web.a.a(this, this, this.ar);
        } else if (intExtra == 3) {
            this.ar = new com.sina.weibo.sdk.web.b.c();
            this.as = new com.sina.weibo.sdk.web.a.c(this, this.ar);
        }
        this.ap.setWebViewClient(this.as);
        this.ar.readFromBundle(extras);
        WebSettings settings = this.ap.getSettings();
        settings.setSavePassword(false);
        settings.setAllowContentAccess(false);
        settings.setUserAgentString(e.o());
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(false);
        if (i2 >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        this.ap.requestFocus();
        this.ap.setScrollBarStyle(0);
        a(this.ap, "searchBoxJavaBridge_");
        a(this.ap, "accessibility");
        a(this.ap, "accessibilityTraversal");
        if (i2 >= 21) {
            settings.setMixedContentMode(2);
        }
        if (this.ar.t()) {
            this.ar.a(new a());
        } else {
            String url = this.ar.getUrl();
            if (!TextUtils.isEmpty(url) && j(url)) {
                this.ap.loadUrl(url);
            }
        }
        TextView textView4 = this.ao;
        if (textView4 != null) {
            textView4.setText(this.at);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (this.as.s()) {
                return true;
            }
            if (this.ap.canGoBack()) {
                this.ap.goBack();
                return true;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // com.sina.weibo.sdk.web.a
    public final void p() {
        this.am.setVisibility(0);
        this.ap.setVisibility(8);
    }

    @Override // com.sina.weibo.sdk.web.a
    public final void q() {
        finish();
    }

    private static void a(WebView webView, String str) {
        try {
            WebView.class.getDeclaredMethod("removeJavascriptInterface", String.class).invoke(webView, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
