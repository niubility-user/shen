package com.jdjr.risk.identity.verify.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.aips.common.permisson.FsCameraPermissonCheck;
import com.jd.aips.common.permisson.FsRequestCameraPermissionUtils;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.face.view.MiscUtil;
import com.jd.aips.verify.identity.R;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.aips.verify.utils.FsJsonStringChangeUtil;
import com.jdjr.risk.identity.verify.IdentityVerifyConfig;
import com.jdjr.risk.identity.verify.IdentityVerifySession;
import com.jdjr.risk.identity.verify.IdentityVerifyTracker;
import com.jdjr.risk.identity.verify.IdentityVerityEngine;
import com.jingdong.common.permission.PermissionHelper;

/* loaded from: classes18.dex */
public class IdentityPrivacyAgrDialogActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView a;
    private TextView b;

    /* renamed from: c  reason: collision with root package name */
    private TextView f7473c;
    private TextView d;

    /* renamed from: e  reason: collision with root package name */
    private ImageView f7474e;

    /* renamed from: f  reason: collision with root package name */
    private RelativeLayout f7475f;

    /* renamed from: g  reason: collision with root package name */
    private WebView f7476g;
    private ConstraintLayout p;
    private ConstraintLayout q;
    private IdentityVerityEngine t;
    private IdentityVerifySession u;
    private IdentityVerifyTracker v;
    private IdentityVerifyConfig w;

    /* renamed from: h  reason: collision with root package name */
    private boolean f7477h = false;

    /* renamed from: i  reason: collision with root package name */
    private String f7478i = "#EF4034";

    /* renamed from: j  reason: collision with root package name */
    private String f7479j = "#00000000";

    /* renamed from: k  reason: collision with root package name */
    private String f7480k = "\u5f00\u542f\u4eba\u8138\u8ba4\u8bc1";

    /* renamed from: l  reason: collision with root package name */
    private String f7481l = "";

    /* renamed from: m  reason: collision with root package name */
    private String f7482m = "\u300a\u4eba\u8138\u8bc6\u522b\u670d\u52a1\u534f\u8bae\u300b";

    /* renamed from: n  reason: collision with root package name */
    private String f7483n = "\u6211\u5df2\u9605\u8bfb\u5e76\u540c\u610f\u300a\u4eba\u8138\u8bc6\u522b\u670d\u52a1\u534f\u8bae\u300b\uff0c\u5f00\u542f\u540e\u53ef\u4ee5\u53bb\u300c\u6211\u7684\u300d\u9875\u9762\uff0c\u6253\u5f00\u300c\u8bbe\u7f6e\u300d\u8fdb\u884c\u67e5\u770b\u548c\u64cd\u4f5c\u3002";
    private String o = "https://qiye.jd.com/contractCenter2_noHead.html?#/template-preview?cid=00006&sid=AIZT&tid=7283128";
    private String r = "#FFFFFF";
    private int s = 0;

    static void d(IdentityPrivacyAgrDialogActivity identityPrivacyAgrDialogActivity) {
        identityPrivacyAgrDialogActivity.setResult(-1);
        identityPrivacyAgrDialogActivity.v.track(VerifyTracker.EVENT_AUTHORITY);
        FsRequestCameraPermissionUtils.releasePermissionCallback();
        identityPrivacyAgrDialogActivity.finish();
    }

    static void e(IdentityPrivacyAgrDialogActivity identityPrivacyAgrDialogActivity) {
        identityPrivacyAgrDialogActivity.setResult(900);
        identityPrivacyAgrDialogActivity.v.track(VerifyTracker.EVENT_NO_AUTHORITY);
        FsRequestCameraPermissionUtils.releasePermissionCallback();
        identityPrivacyAgrDialogActivity.finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources.getConfiguration().fontScale != 1.0f) {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.v.b();
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.vf_close) {
            view.setClickable(false);
            onBackPressed();
        } else if (view.getId() == R.id.identity_privacy_agr_container) {
            view.setClickable(false);
            this.f7477h = !this.f7477h;
            b();
        } else if (view.getId() == R.id.identity_privacy_identity) {
            if (!this.f7477h) {
                Toast.makeText(getApplicationContext(), "\u8bf7\u5148\u540c\u610f\u670d\u52a1\u534f\u8bae", 0).show();
                return;
            }
            view.setClickable(false);
            this.v.a();
            if (!FsCameraPermissonCheck.hasCameraPermission(this)) {
                this.v.track(VerifyTracker.EVENT_CHECK_CAMERA);
                Bundle bundle = new Bundle();
                bundle.putString("moduleName", "jdcn_face_camera");
                bundle.putString("className", "IdentityPrivacyAgrDialogActivity");
                bundle.putString("methodName", "requestCameraPermissions");
                bundle.putBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true);
                FsRequestCameraPermissionUtils.requestPermission(this, bundle, "android.permission.CAMERA", new FsRequestCameraPermissionUtils.PermissionResultCallBack() { // from class: com.jdjr.risk.identity.verify.activity.IdentityPrivacyAgrDialogActivity.3
                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onCanceled() {
                        super.onCanceled();
                        IdentityPrivacyAgrDialogActivity.e(IdentityPrivacyAgrDialogActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onDenied() {
                        super.onDenied();
                        IdentityPrivacyAgrDialogActivity.e(IdentityPrivacyAgrDialogActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onGranted() {
                        super.onGranted();
                        IdentityPrivacyAgrDialogActivity.d(IdentityPrivacyAgrDialogActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onIgnored() {
                        super.onIgnored();
                        IdentityPrivacyAgrDialogActivity.e(IdentityPrivacyAgrDialogActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onOpenSetting() {
                        super.onOpenSetting();
                        IdentityPrivacyAgrDialogActivity.e(IdentityPrivacyAgrDialogActivity.this);
                    }
                }, "\u6444\u50cf\u5934", "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u6444\u50cf\u5934\u62cd\u6444\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u6b63\u5e38\u4f7f\u7528\u4eba\u8138\u8bc6\u522b\u670d\u52a1");
                return;
            }
            setResult(-1);
            this.v.track(VerifyTracker.EVENT_AUTHORITY);
            FsRequestCameraPermissionUtils.releasePermissionCallback();
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        if (configuration.fontScale != 1.0f) {
            getResources();
        }
        super.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        VerificationSdk verificationSdk;
        VerificationSdk.Config config;
        requestWindowFeature(1);
        super.onCreate(bundle);
        IdentityVerityEngine identityVerityEngine = IdentityVerityEngine.getInstance();
        this.t = identityVerityEngine;
        IdentityVerifySession session = identityVerityEngine.getSession();
        this.u = session;
        if (session != null && session.verifyParams != 0) {
            IdentityVerifyTracker identityVerifyTracker = (IdentityVerifyTracker) session.verifyTracker;
            this.v = identityVerifyTracker;
            identityVerifyTracker.c();
            IdentityVerifyConfig identityVerifyConfig = (IdentityVerifyConfig) getIntent().getSerializableExtra("config");
            this.w = identityVerifyConfig;
            if (identityVerifyConfig != null && (verificationSdk = identityVerifyConfig.verificationSdk) != null && (config = verificationSdk.config) != null) {
                if (!TextUtils.isEmpty(config.privacy_agreemen_name)) {
                    this.f7482m = this.w.verificationSdk.config.privacy_agreemen_name;
                }
                if (!TextUtils.isEmpty(this.w.verificationSdk.config.privacy_agreemen_text)) {
                    this.f7483n = this.w.verificationSdk.config.privacy_agreemen_text;
                }
                if (!TextUtils.isEmpty(this.w.verificationSdk.config.privacy_agreemen_url)) {
                    this.o = this.w.verificationSdk.config.privacy_agreemen_url;
                }
                VerificationSdk.Config config2 = this.w.verificationSdk.config;
                if (!config2.privacy_agreemen_show_flag) {
                    this.f7477h = true;
                }
                if (!TextUtils.isEmpty(config2.privacy_agreemen_statement)) {
                    this.f7481l = this.w.verificationSdk.config.privacy_agreemen_statement;
                }
                if (!TextUtils.isEmpty(this.w.verificationSdk.config.privacy_agreemen_background_colour)) {
                    this.r = this.w.verificationSdk.config.privacy_agreemen_background_colour;
                    String str = "mPriAgrBgColor\uff1a" + this.r;
                }
            } else {
                finish();
            }
            setContentView(R.layout.vf_dialog_privacy_agreement);
            a();
            return;
        }
        this.t.callbackFinishSDK(5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u53c2\u6570\u4e3a\u7a7a\uff01");
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        FsRequestCameraPermissionUtils.onRequestPermissionsResult(this, i2, strArr, iArr);
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.a.setClickable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        GradientDrawable a;
        this.b.setText(this.f7480k);
        this.f7473c.setText(this.f7481l);
        TextView textView = this.d;
        String str = this.f7483n;
        SpannableString spannableString = new SpannableString(str);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.jdjr.risk.identity.verify.activity.IdentityPrivacyAgrDialogActivity.2
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                IdentityPrivacyAgrDialogActivity.this.s = 1;
                IdentityPrivacyAgrDialogActivity.this.b();
                try {
                    int parseColor = FsJsonStringChangeUtil.parseColor(IdentityPrivacyAgrDialogActivity.this.v, IdentityPrivacyAgrDialogActivity.this.r, "privacy_agreemen_background_colour");
                    IdentityPrivacyAgrDialogActivity.this.findViewById(R.id.vf_content_main).setBackgroundDrawable(IdentityPrivacyAgrDialogActivity.this.a(parseColor, parseColor, 1, MiscUtil.dipToPx(r2, 8.0f)));
                } catch (Exception unused) {
                    String unused2 = IdentityPrivacyAgrDialogActivity.this.r;
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }
        };
        try {
            String str2 = this.f7482m;
            int indexOf = str.indexOf(str2);
            if (indexOf >= 0) {
                int length = str2.length() + indexOf;
                spannableString.setSpan(clickableSpan, indexOf, length, 17);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFCC8C4E")), indexOf, length, 17);
            }
        } catch (Exception unused) {
        }
        textView.setText(spannableString);
        int i2 = this.s;
        if (i2 == 0) {
            this.p.setVisibility(0);
            this.q.setVisibility(8);
            this.a.setText("\u7ee7\u7eed");
        } else if (i2 == 1) {
            this.f7477h = true;
            this.p.setVisibility(8);
            this.q.setVisibility(0);
            this.a.setText("\u540c\u610f\u5e76\u7ee7\u7eed");
        }
        findViewById(R.id.dialog_pri_agr_container).setVisibility(this.w.verificationSdk.config.privacy_agreemen_show_flag ? 0 : 8);
        try {
            this.d.setMovementMethod(LinkMovementMethod.getInstance());
            if (this.f7478i.length() == 9) {
                this.f7478i = "#" + this.f7478i.substring(3);
            }
            String replace = this.f7478i.replace("#", this.f7477h ? "#FF" : "#66");
            this.f7478i = replace;
            ImageView imageView = this.f7474e;
            if (this.f7477h) {
                a = a(Color.parseColor(replace), Color.parseColor(this.f7478i), MiscUtil.dipToPx(this, 1.0f), MiscUtil.dipToPx(this, 15.0f));
            } else {
                a = a(Color.parseColor(this.f7479j), Color.parseColor("#FF" + this.f7478i.substring(3)), MiscUtil.dipToPx(this, 1.0f), MiscUtil.dipToPx(this, 15.0f));
            }
            imageView.setBackgroundDrawable(a);
            this.f7474e.setImageResource(this.f7477h ? R.drawable.jdcn_identity_agree_check_icon : 0);
            this.a.setBackgroundDrawable(a(Color.parseColor(this.f7478i), Color.parseColor(this.f7478i), 0, MiscUtil.dipToPx(this, 25.0f)));
            if (this.f7477h) {
                this.a.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                this.a.setTextColor(Color.parseColor("#99FFFFFF"));
            }
        } catch (Exception unused2) {
        }
    }

    private void a() {
        this.b = (TextView) findViewById(R.id.identity_privacy_title);
        this.f7473c = (TextView) findViewById(R.id.identity_privacy_desc);
        this.d = (TextView) findViewById(R.id.identity_privacy_agreement);
        TextView textView = (TextView) findViewById(R.id.identity_privacy_identity);
        this.a = textView;
        textView.setOnClickListener(this);
        this.f7474e = (ImageView) findViewById(R.id.identity_privacy_agr_icon);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.identity_privacy_agr_container);
        this.f7475f = relativeLayout;
        relativeLayout.setOnClickListener(this);
        findViewById(R.id.vf_close).setOnClickListener(this);
        this.p = (ConstraintLayout) findViewById(R.id.dialog_prv_agr_container);
        this.q = (ConstraintLayout) findViewById(R.id.dialog_prv_web_container);
        WebView webView = (WebView) findViewById(R.id.vf_faq_webview);
        this.f7476g = webView;
        webView.setWebViewClient(new WebViewClient(this) { // from class: com.jdjr.risk.identity.verify.activity.IdentityPrivacyAgrDialogActivity.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                webView2.loadUrl(str);
                return super.shouldOverrideUrlLoading(webView2, str);
            }
        });
        WebSettings settings = this.f7476g.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDomStorageEnabled(true);
        try {
            this.f7476g.setBackgroundColor(0);
            this.q.setBackgroundColor(FsJsonStringChangeUtil.parseColor(this.v, this.r, "privacy_agreemen_background_colour"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f7476g.loadUrl(this.o);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GradientDrawable a(@ColorInt int i2, @ColorInt int i3, int i4, float f2) {
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setColor(i2);
            gradientDrawable.setStroke(i4, i3);
            gradientDrawable.setCornerRadius(f2);
            return gradientDrawable;
        } catch (Exception unused) {
            return new GradientDrawable();
        }
    }
}
