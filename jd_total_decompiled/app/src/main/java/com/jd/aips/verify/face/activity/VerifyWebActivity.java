package com.jd.aips.verify.face.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class VerifyWebActivity extends AppCompatActivity {
    private ImageButton mBackBtn;
    private String mFaqUrl;
    private ProgressBar mProgressBar;
    private TextView mTitleTv;
    private WebView mWebView;
    private WebViewClient webViewClient = new WebViewClient() { // from class: com.jd.aips.verify.face.activity.VerifyWebActivity.2
        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            VerifyWebActivity.this.mProgressBar.setVisibility(8);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            VerifyWebActivity.this.mProgressBar.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    };
    private WebChromeClient webChromeClient = new WebChromeClient() { // from class: com.jd.aips.verify.face.activity.VerifyWebActivity.3
        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
            builder.setMessage(str2).setPositiveButton("\u786e\u5b9a", (DialogInterface.OnClickListener) null);
            builder.setCancelable(false);
            builder.create().show();
            jsResult.confirm();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i2) {
            VerifyWebActivity.this.mProgressBar.setProgress(i2);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            VerifyWebActivity.this.mTitleTv.setText(str);
        }
    };

    public static void intentTo(Context context, String str) {
        Intent intent = new Intent(context, VerifyWebActivity.class);
        intent.putExtra("faqUrl", str);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fcvf_activity_faq);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.mFaqUrl = intent.getStringExtra("faqUrl");
        this.mTitleTv = (TextView) findViewById(R.id.vf_title_tv);
        WebView webView = (WebView) findViewById(R.id.vf_faq_webview);
        this.mWebView = webView;
        webView.loadUrl(this.mFaqUrl);
        this.mProgressBar = (ProgressBar) findViewById(R.id.vf_faq_progressbar);
        this.mWebView.setWebChromeClient(this.webChromeClient);
        this.mWebView.setWebViewClient(this.webViewClient);
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDomStorageEnabled(true);
        findViewById(R.id.vf_faq_back_btn).setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.face.activity.VerifyWebActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerifyWebActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
        this.mWebView = null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.mWebView.canGoBack() && i2 == 4) {
            this.mWebView.goBack();
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }
}
