package com.jdjr.risk.identity.verify.activity;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.jd.aips.common.permisson.FsCameraPermissonCheck;
import com.jd.aips.common.permisson.FsRequestCameraPermissionUtils;
import com.jd.aips.common.utils.AppInfoUtil;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.face.activity.VerifyWebActivity;
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
public class IdentityPrivacyAgrActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView a;
    private TextView b;

    /* renamed from: c  reason: collision with root package name */
    private TextView f7462c;
    private TextView d;

    /* renamed from: e  reason: collision with root package name */
    private ImageView f7463e;

    /* renamed from: f  reason: collision with root package name */
    private RelativeLayout f7464f;

    /* renamed from: g  reason: collision with root package name */
    private TextView f7465g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f7466h = false;

    /* renamed from: i  reason: collision with root package name */
    private String f7467i = "#EF4034";

    /* renamed from: j  reason: collision with root package name */
    private String f7468j = "#00000000";

    /* renamed from: k  reason: collision with root package name */
    private String f7469k = "";

    /* renamed from: l  reason: collision with root package name */
    private String f7470l = "\u7533\u8bf7\u4f7f\u7528\u4eba\u8138\u8bc6\u522b\u5b8c\u6210\u60a8\u7684\u8eab\u4efd\u9a8c\u8bc1";

    /* renamed from: m  reason: collision with root package name */
    private String f7471m = "\u300a\u4eba\u8138\u8bc6\u522b\u670d\u52a1\u534f\u8bae\u300b";

    /* renamed from: n  reason: collision with root package name */
    private String f7472n = "\u60a8\u540c\u610f\u4eac\u4e1c\u91d1\u878d\u53ca\u60a8\u6388\u6743\u7684\u7b2c\u4e09\u65b9\u4f7f\u7528\u5e76\u4f20\u9001\u60a8\u7684\u8eab\u4efd\u4fe1\u606f\u548c\u4eba\u8138\u4fe1\u606f\u7528\u4e8e\u8eab\u4efd\u6838\u9a8c\uff0c\u8be6\u60c5\u89c1\u300a\u4eba\u8138\u8bc6\u522b\u670d\u52a1\u534f\u8bae\u300b\u3002\u8bf7\u786e\u8ba4\u4e3a\u672c\u4eba\u64cd\u4f5c\uff0c\u5982\u975e\u672c\u4eba\u610f\u613f\u64cd\u4f5c\u8bf7\u7acb\u5373\u7ec8\u6b62\uff0c\u5e76\u8054\u7cfb\u5ba2\u670d\u6838\u5b9e\u60c5\u51b5\u3002";
    private String o = "";
    private String p = "\u5207\u6362\u5176\u4ed6\u65b9\u5f0f\u9a8c\u8bc1";
    private String q = "#FF666666";
    private IdentityVerityEngine r;
    private IdentityVerifySession s;
    private IdentityVerifyTracker t;
    private IdentityVerifyConfig u;

    private void b() {
        GradientDrawable a;
        this.b.setText(this.f7469k);
        this.f7462c.setText(this.f7470l);
        TextView textView = this.d;
        String str = this.f7472n;
        SpannableString spannableString = new SpannableString(str);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.jdjr.risk.identity.verify.activity.IdentityPrivacyAgrActivity.1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                IdentityPrivacyAgrActivity identityPrivacyAgrActivity = IdentityPrivacyAgrActivity.this;
                VerifyWebActivity.intentTo(identityPrivacyAgrActivity, identityPrivacyAgrActivity.o);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }
        };
        try {
            String str2 = this.f7471m;
            int indexOf = str.indexOf(str2);
            if (indexOf >= 0) {
                int length = str2.length() + indexOf;
                spannableString.setSpan(clickableSpan, indexOf, length, 17);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFCC8C4E")), indexOf, length, 17);
            }
        } catch (Exception unused) {
        }
        textView.setText(spannableString);
        if (this.u.verificationSdk.config.jump_other_verification_flag) {
            this.f7465g.setText(this.p);
        } else {
            this.f7465g.setVisibility(8);
        }
        try {
            this.f7465g.setTextColor(FsJsonStringChangeUtil.parseColor(this.t, this.q, "jump_colour"));
            this.d.setMovementMethod(LinkMovementMethod.getInstance());
            if (this.f7467i.length() == 9) {
                this.f7467i = "#" + this.f7467i.substring(3);
            }
            String replace = this.f7467i.replace("#", this.f7466h ? "#FF" : "#66");
            this.f7467i = replace;
            ImageView imageView = this.f7463e;
            if (this.f7466h) {
                a = a(Color.parseColor(replace), Color.parseColor(this.f7467i), MiscUtil.dipToPx(this, 1.0f), MiscUtil.dipToPx(this, 15.0f));
            } else {
                a = a(Color.parseColor(this.f7468j), Color.parseColor("#FF" + this.f7467i.substring(3)), MiscUtil.dipToPx(this, 1.0f), MiscUtil.dipToPx(this, 15.0f));
            }
            imageView.setBackgroundDrawable(a);
            this.f7463e.setImageResource(this.f7466h ? R.drawable.jdcn_identity_agree_check_icon : 0);
            this.a.setBackgroundDrawable(a(Color.parseColor(this.f7467i), Color.parseColor(this.f7467i), 0, MiscUtil.dipToPx(this, 25.0f)));
        } catch (Exception unused2) {
        }
    }

    static void c(IdentityPrivacyAgrActivity identityPrivacyAgrActivity) {
        identityPrivacyAgrActivity.setResult(900);
        identityPrivacyAgrActivity.t.track(VerifyTracker.EVENT_NO_AUTHORITY);
        FsRequestCameraPermissionUtils.releasePermissionCallback();
        identityPrivacyAgrActivity.finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.t.b();
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.identity_privacy_agr_back) {
            view.setClickable(false);
            onBackPressed();
        } else if (view.getId() == R.id.identity_privacy_agr_container) {
            this.f7466h = !this.f7466h;
            b();
        } else if (view.getId() == R.id.identity_privacy_identity) {
            if (!this.f7466h) {
                Toast.makeText(getApplicationContext(), "\u8bf7\u5148\u540c\u610f\u670d\u52a1\u534f\u8bae", 0).show();
                return;
            }
            view.setClickable(false);
            this.t.a();
            if (!FsCameraPermissonCheck.hasCameraPermission(this)) {
                this.t.track(VerifyTracker.EVENT_CHECK_CAMERA);
                Bundle bundle = new Bundle();
                bundle.putString("moduleName", "jdcn_face_camera");
                bundle.putString("className", "IdentityPrivacyAgrActivity");
                bundle.putString("methodName", "requestCameraPermissions");
                bundle.putBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true);
                FsRequestCameraPermissionUtils.requestPermission(this, bundle, "android.permission.CAMERA", new FsRequestCameraPermissionUtils.PermissionResultCallBack() { // from class: com.jdjr.risk.identity.verify.activity.IdentityPrivacyAgrActivity.2
                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onCanceled() {
                        super.onCanceled();
                        IdentityPrivacyAgrActivity.c(IdentityPrivacyAgrActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onDenied() {
                        super.onDenied();
                        IdentityPrivacyAgrActivity.c(IdentityPrivacyAgrActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onGranted() {
                        super.onGranted();
                        IdentityPrivacyAgrActivity.b(IdentityPrivacyAgrActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onIgnored() {
                        super.onIgnored();
                        IdentityPrivacyAgrActivity.c(IdentityPrivacyAgrActivity.this);
                    }

                    @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
                    public void onOpenSetting() {
                        super.onOpenSetting();
                        IdentityPrivacyAgrActivity.c(IdentityPrivacyAgrActivity.this);
                    }
                }, "\u6444\u50cf\u5934", "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u6444\u50cf\u5934\u62cd\u6444\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u6b63\u5e38\u4f7f\u7528\u4eba\u8138\u8bc6\u522b\u670d\u52a1");
                return;
            }
            setResult(-1);
            this.t.track(VerifyTracker.EVENT_AUTHORITY);
            FsRequestCameraPermissionUtils.releasePermissionCallback();
            finish();
        } else if (view.getId() == R.id.identity_privacy_next) {
            view.setClickable(false);
            onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        VerificationSdk verificationSdk;
        VerificationSdk.Config config;
        super.onCreate(bundle);
        IdentityVerityEngine identityVerityEngine = IdentityVerityEngine.getInstance();
        this.r = identityVerityEngine;
        IdentityVerifySession session = identityVerityEngine.getSession();
        this.s = session;
        if (session != null && session.verifyParams != 0) {
            IdentityVerifyTracker identityVerifyTracker = (IdentityVerifyTracker) session.verifyTracker;
            this.t = identityVerifyTracker;
            identityVerifyTracker.c();
            IdentityVerifyConfig identityVerifyConfig = (IdentityVerifyConfig) getIntent().getSerializableExtra("config");
            this.u = identityVerifyConfig;
            if (identityVerifyConfig != null && (verificationSdk = identityVerifyConfig.verificationSdk) != null && (config = verificationSdk.config) != null) {
                if (!TextUtils.isEmpty(config.button_colour)) {
                    this.f7467i = this.u.verificationSdk.config.button_colour;
                }
                if (!TextUtils.isEmpty(this.u.verificationSdk.config.scene_name)) {
                    this.f7469k = this.u.verificationSdk.config.scene_name;
                } else {
                    this.f7469k = AppInfoUtil.getAppName(this);
                }
                if (!TextUtils.isEmpty(this.u.verificationSdk.config.privacy_agreemen_name)) {
                    this.f7471m = this.u.verificationSdk.config.privacy_agreemen_name;
                }
                if (!TextUtils.isEmpty(this.u.verificationSdk.config.privacy_agreemen_text)) {
                    this.f7472n = this.u.verificationSdk.config.privacy_agreemen_text;
                }
                if (!TextUtils.isEmpty(this.u.verificationSdk.config.jump_text)) {
                    this.p = this.u.verificationSdk.config.jump_text;
                }
                if (!TextUtils.isEmpty(this.u.verificationSdk.config.jump_colour)) {
                    this.q = this.u.verificationSdk.config.jump_colour;
                }
                if (!TextUtils.isEmpty(this.u.verificationSdk.config.privacy_agreemen_url)) {
                    this.o = FsJsonStringChangeUtil.parseUrl(this.t, this.u.verificationSdk.config.privacy_agreemen_url, "privacy_agreemen_url");
                }
            } else {
                finish();
            }
            setContentView(R.layout.vf_activity_privacy_agreement);
            a();
            return;
        }
        this.r.callbackFinishSDK(5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u53c2\u6570\u4e3a\u7a7a\uff01");
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

    private void a() {
        this.b = (TextView) findViewById(R.id.identity_privacy_title);
        this.f7462c = (TextView) findViewById(R.id.identity_privacy_desc);
        this.d = (TextView) findViewById(R.id.identity_privacy_agreement);
        TextView textView = (TextView) findViewById(R.id.identity_privacy_identity);
        this.a = textView;
        textView.setOnClickListener(this);
        this.f7463e = (ImageView) findViewById(R.id.identity_privacy_agr_icon);
        this.f7465g = (TextView) findViewById(R.id.identity_privacy_next);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.identity_privacy_agr_container);
        this.f7464f = relativeLayout;
        relativeLayout.setOnClickListener(this);
        findViewById(R.id.identity_privacy_agr_back).setOnClickListener(this);
        this.f7465g.setOnClickListener(this);
        b();
    }

    private GradientDrawable a(@ColorInt int i2, @ColorInt int i3, int i4, float f2) {
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

    static void b(IdentityPrivacyAgrActivity identityPrivacyAgrActivity) {
        identityPrivacyAgrActivity.setResult(-1);
        identityPrivacyAgrActivity.t.track(VerifyTracker.EVENT_AUTHORITY);
        FsRequestCameraPermissionUtils.releasePermissionCallback();
        identityPrivacyAgrActivity.finish();
    }
}
