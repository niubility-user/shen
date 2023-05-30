package com.jingdong.app.mall;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import cn.com.union.fido.common.MIMEType;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes19.dex */
public class PrivacyWebActivity extends BaseActivity {

    /* renamed from: g  reason: collision with root package name */
    private ViewGroup f7651g;

    /* renamed from: h  reason: collision with root package name */
    private WebView f7652h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f7653i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PrivacyWebActivity.this.f7652h != null && PrivacyWebActivity.this.f7652h.canGoBack()) {
                PrivacyWebActivity.this.f7652h.goBack();
            } else {
                PrivacyWebActivity.this.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b extends WebViewClient {
        b(PrivacyWebActivity privacyWebActivity) {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return false;
        }
    }

    private void initView() {
        try {
            this.f7652h = new WebView(this);
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.rq);
            this.f7651g = viewGroup;
            viewGroup.addView(this.f7652h, new ViewGroup.LayoutParams(-1, -1));
            this.f7653i = (TextView) findViewById(R.id.pw);
            findViewById(R.id.be7).setOnClickListener(new a());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        w();
    }

    private void v() {
        TextView textView;
        String stringExtra = getIntent().getStringExtra("title");
        String stringExtra2 = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(stringExtra) && (textView = this.f7653i) != null) {
            textView.setText(stringExtra);
        }
        if (this.f7652h == null || TextUtils.isEmpty(stringExtra2) || !stringExtra2.startsWith("http")) {
            return;
        }
        this.f7652h.loadUrl(stringExtra2);
    }

    private void w() {
        try {
            WebView webView = this.f7652h;
            if (webView == null) {
                ToastUtils.shortToast(this, (int) R.string.eh);
                finish();
                return;
            }
            WebSettings settings = webView.getSettings();
            if (settings == null) {
                ToastUtils.shortToast(this, (int) R.string.eh);
                finish();
                return;
            }
            this.f7652h.setScrollBarStyle(33554432);
            settings.setJavaScriptEnabled(false);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
            settings.setAllowFileAccess(false);
            settings.setUserAgentString("jd");
            settings.setSavePassword(false);
            settings.setBuiltInZoomControls(false);
            settings.setGeolocationEnabled(false);
            if (SDKUtils.getSDKVersion() >= 21) {
                settings.setMixedContentMode(0);
            }
            this.f7652h.setWebViewClient(new b(this));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_webview);
        initView();
        v();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.f7652h;
        if (webView != null) {
            webView.stopLoading();
            this.f7652h.loadDataWithBaseURL(null, "", MIMEType.MIME_TYPE_HTML, "utf-8", null);
            this.f7652h.clearHistory();
            ViewGroup viewGroup = this.f7651g;
            if (viewGroup != null) {
                viewGroup.removeView(this.f7652h);
            }
            this.f7652h.destroy();
            this.f7652h = null;
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        WebView webView;
        if (i2 == 4 && (webView = this.f7652h) != null && webView.canGoBack()) {
            this.f7652h.goBack();
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }
}
