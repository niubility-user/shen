package com.jingdong.app.mall.bundle.jdrhsdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.app.mall.bundle.jdrhsdk.d.d;
import com.jingdong.app.mall.bundle.jdrhsdk.d.e;

/* loaded from: classes2.dex */
public class JDRiskHandleWebActivity extends FragmentActivity {
    public static final String KEY_URL = "url";
    private static final String TAG = "RiskHandle.JDRiskHandleWebActivity";
    private RelativeLayout headLine;
    private View processBar;
    private String title;
    private TextView titleTextView;
    private String url;
    private WebView webView;
    private final WebChromeClient webChromeClient = new a();
    private final WebViewClient webViewClient = new b();

    /* loaded from: classes2.dex */
    class a extends WebChromeClient {
        a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i2) {
            super.onProgressChanged(webView, i2);
            JDRiskHandleWebActivity.this.setProcess(i2);
        }
    }

    /* loaded from: classes2.dex */
    class b extends WebViewClient {
        b() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (webView != null) {
                String title = webView.getTitle();
                if (!TextUtils.equals(title, JDRiskHandleWebActivity.this.title)) {
                    JDRiskHandleWebActivity.this.title = title;
                    JDRiskHandleWebActivity.this.titleTextView.setText(JDRiskHandleWebActivity.this.title);
                }
            }
            JDRiskHandleWebActivity.this.processBar.setVisibility(8);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            JDRiskHandleWebActivity.this.processBar.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            d.e(JDRiskHandleWebActivity.TAG, "WebView onReceivedError.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDRiskHandleWebActivity.this.finish();
        }
    }

    private int getProcessBarParentWidth() {
        ViewParent parent = this.processBar.getParent();
        if (parent instanceof View) {
            return ((View) parent).getWidth();
        }
        try {
            Point point2 = new Point();
            getWindowManager().getDefaultDisplay().getSize(point2);
            return point2.x;
        } catch (Throwable th) {
            d.g(TAG, th);
            return 0;
        }
    }

    private void initStatusBar() {
        e.f(this, findViewById(R.id.jdrhsdk_web_activity_riskhandle_status_bar));
    }

    private void initViews() {
        this.webView = (WebView) findViewById(R.id.jdrhsdk_web_activity_web_view);
        this.titleTextView = (TextView) findViewById(R.id.jdrhsdk_web_title_text);
        findViewById(R.id.jdrhsdk_activity_back_arrow).setOnClickListener(new c());
        this.processBar = findViewById(R.id.jdrhsdk_web_process_bar);
        this.headLine = (RelativeLayout) findViewById(R.id.jdrhsdk_web_main_headline);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebViewSettings() {
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(this.webViewClient);
        this.webView.setWebChromeClient(this.webChromeClient);
    }

    private void innerPreInitLayoutParams(Activity activity) {
        if (activity == null) {
            return;
        }
        e.w(activity);
        preInitLayoutParams(activity);
    }

    private void preInitLayoutParams(Activity activity) {
        RelativeLayout.LayoutParams layoutParams;
        int b2;
        if (e.v(activity)) {
            layoutParams = (RelativeLayout.LayoutParams) this.headLine.getLayoutParams();
            b2 = e.n(activity, 44);
        } else {
            layoutParams = (RelativeLayout.LayoutParams) this.headLine.getLayoutParams();
            b2 = e.b(activity, 120);
        }
        layoutParams.height = b2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcess(int i2) {
        double processBarParentWidth = getProcessBarParentWidth();
        Double.isNaN(processBarParentWidth);
        double d = i2;
        Double.isNaN(d);
        int i3 = (int) ((processBarParentWidth / 100.0d) * d);
        ViewGroup.LayoutParams layoutParams = this.processBar.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = i3;
            this.processBar.setLayoutParams(layoutParams);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        innerPreInitLayoutParams(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.jdrhsdk_activity_riskhandle_web);
        initStatusBar();
        initViews();
        Intent intent = getIntent();
        if (intent != null) {
            this.url = intent.getStringExtra("url");
        }
        if (TextUtils.isEmpty(this.url)) {
            Toast.makeText(this, "URL\u4e3a\u7a7a", 0).show();
            finish();
            return;
        }
        initWebViewSettings();
        this.webView.loadUrl(this.url);
        innerPreInitLayoutParams(this);
    }
}
