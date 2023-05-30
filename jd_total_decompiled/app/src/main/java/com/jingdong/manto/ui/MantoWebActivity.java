package com.jingdong.manto.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jingdong.manto.BaseWebView;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.widget.MantoProgressBar;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebViewClient;

/* loaded from: classes16.dex */
public class MantoWebActivity extends MantoBaseActivity implements View.OnClickListener, a.b {
    private TextView a;
    private ImageView b;

    /* renamed from: c  reason: collision with root package name */
    private ImageView f14281c;
    private View d;

    /* renamed from: e  reason: collision with root package name */
    private ViewGroup f14282e;

    /* renamed from: f  reason: collision with root package name */
    private BaseWebView f14283f;

    /* renamed from: g  reason: collision with root package name */
    private MantoProgressBar f14284g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a extends JWebViewClient {
        a(MantoWebActivity mantoWebActivity) {
        }

        @Override // com.jingdong.sdk.jweb.JWebViewClient
        public boolean shouldOverrideUrlLoading(JDWebView jDWebView, String str) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b extends JWebChromeClient {
        b() {
        }

        @Override // com.jingdong.sdk.jweb.JWebChromeClient
        public void onProgressChanged(JDWebView jDWebView, int i2) {
            if (i2 == 100) {
                MantoWebActivity.this.f14284g.b();
            } else {
                MantoWebActivity.this.f14284g.c();
            }
            super.onProgressChanged(jDWebView, i2);
        }
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.manto_ui_nav_title);
        this.f14281c = (ImageView) findViewById(R.id.manto_ui_nav_back);
        this.b = (ImageView) findViewById(R.id.manto_ui_nav_option);
        this.d = findViewById(R.id.manto_ui_actionbar);
        this.f14282e = (ViewGroup) findViewById(R.id.manto_ui_webview);
        this.a.setText(getString(R.string.manto_chartered_info));
        this.b.setVisibility(8);
        this.f14281c.setOnClickListener(this);
    }

    private void a(int i2) {
        int color = getResources().getColor(R.color.manto_white);
        int color2 = getResources().getColor(R.color.manto_black);
        if (i2 != 0) {
            getResources().getColor(R.color.manto_dark_text_light);
            int color3 = getResources().getColor(R.color.manto_dark_text_weight);
            int color4 = getResources().getColor(R.color.manto_dark_background_light);
            getResources().getColor(R.color.manto_dark_background_weight);
            MantoStatusBarUtil.setStatusBarColor(this, color4, false);
            this.a.setTextColor(color3);
            this.f14281c.setColorFilter(color);
            this.d.setBackgroundColor(color4);
            this.b.setColorFilter(color);
            return;
        }
        Resources resources = getResources();
        int i3 = R.color.manto_day_text_weight;
        int color5 = resources.getColor(i3);
        getResources().getColor(i3);
        int color6 = getResources().getColor(R.color.manto_day_background_light);
        getResources().getColor(R.color.manto_day_background_weight);
        MantoStatusBarUtil.setStatusBarColor(this, -1, true);
        this.a.setTextColor(color5);
        this.f14281c.setColorFilter(color2);
        this.d.setBackgroundColor(color6);
        this.b.setColorFilter(color2);
    }

    private void a(Context context, String str) {
        BaseWebView baseWebView = new BaseWebView(this);
        this.f14283f = baseWebView;
        baseWebView.getSettings().setDomStorageEnabled(true);
        this.f14283f.getSettings().setJavaScriptEnabled(true);
        this.f14283f.getSettings().setMediaPlaybackRequiresUserGesture(false);
        this.f14283f.getView().setHorizontalScrollBarEnabled(false);
        this.f14283f.getView().setVerticalScrollBarEnabled(false);
        this.f14283f.getSettings().setBuiltInZoomControls(true);
        this.f14283f.getSettings().setUseWideViewPort(true);
        this.f14283f.getSettings().setLoadWithOverviewMode(true);
        this.f14283f.getSettings().setAllowFileAccess(false);
        this.f14283f.getSettings().setGeolocationEnabled(true);
        this.f14283f.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.f14283f.getSettings().enableMixedContent();
        this.f14283f.setWebViewClient(new a(this));
        MantoProgressBar mantoProgressBar = new MantoProgressBar(context);
        this.f14284g = mantoProgressBar;
        mantoProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.manto_progress_bar));
        ViewGroup viewGroup = this.f14282e;
        if (viewGroup != null) {
            viewGroup.addView(this.f14283f, new FrameLayout.LayoutParams(-1, -1));
            this.f14282e.addView(this.f14284g, new FrameLayout.LayoutParams(-1, MantoDensityUtils.dip2pixel(context, 3)));
        }
        this.f14283f.setWebChromeClient(new b());
        this.f14283f.loadUrl(str);
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity
    public int getLayoutId() {
        return R.layout.manto_ui_web;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BaseWebView baseWebView = this.f14283f;
        if (baseWebView == null || !baseWebView.canGoBack()) {
            super.onBackPressed();
        } else {
            this.f14283f.goBack();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.manto_ui_nav_back) {
            finish();
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
        String stringExtra = intent.getStringExtra("url");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        a(this, stringExtra);
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
