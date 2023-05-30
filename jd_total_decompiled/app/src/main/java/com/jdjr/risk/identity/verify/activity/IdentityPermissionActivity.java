package com.jdjr.risk.identity.verify.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.jd.aips.common.permisson.FsCameraPermissonCheck;
import com.jd.aips.common.permisson.FsRequestCameraPermissionUtils;
import com.jd.aips.common.utils.AppInfoUtil;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.identity.R;
import com.jd.dynamic.DYConstants;
import com.jdjr.risk.identity.verify.IdentityVerifyConfig;
import com.jdjr.risk.identity.verify.IdentityVerifySession;
import com.jdjr.risk.identity.verify.IdentityVerifyTracker;
import com.jdjr.risk.identity.verify.IdentityVerityEngine;
import com.jingdong.common.permission.PermissionHelper;

/* loaded from: classes18.dex */
public class IdentityPermissionActivity extends AppCompatActivity {
    View a;
    TextView b;

    /* renamed from: c  reason: collision with root package name */
    private IdentityVerityEngine f7460c;
    private IdentityVerifySession d;

    /* renamed from: e  reason: collision with root package name */
    private IdentityVerifyTracker f7461e;

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.a.setVisibility(4);
        this.f7461e.trackRequestPermission();
        Bundle bundle = new Bundle();
        bundle.putString("moduleName", "jdcn_face_camera");
        bundle.putString("className", "IdentityPermissionActivity");
        bundle.putString("methodName", "requestCameraPermissions");
        bundle.putBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true);
        FsRequestCameraPermissionUtils.requestPermission(this, bundle, "android.permission.CAMERA", new FsRequestCameraPermissionUtils.PermissionResultCallBack() { // from class: com.jdjr.risk.identity.verify.activity.IdentityPermissionActivity.2
            @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
            public void onCanceled() {
                super.onCanceled();
                IdentityPermissionActivity.c(IdentityPermissionActivity.this);
            }

            @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
            public void onDenied() {
                super.onDenied();
                IdentityPermissionActivity.c(IdentityPermissionActivity.this);
            }

            @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
            public void onGranted() {
                super.onGranted();
                IdentityPermissionActivity.b(IdentityPermissionActivity.this);
            }

            @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
            public void onIgnored() {
                super.onIgnored();
                IdentityPermissionActivity.c(IdentityPermissionActivity.this);
            }

            @Override // com.jd.aips.common.permisson.FsRequestCameraPermissionUtils.PermissionResultCallBack
            public void onOpenSetting() {
                super.onOpenSetting();
                IdentityPermissionActivity.this.finish();
            }
        }, "\u6444\u50cf\u5934", "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u6444\u50cf\u5934\u62cd\u6444\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u6b63\u5e38\u4f7f\u7528\u4eba\u8138\u8bc6\u522b\u670d\u52a1");
    }

    static void c(IdentityPermissionActivity identityPermissionActivity) {
        identityPermissionActivity.f7461e.trackRequestPermissionFailed();
        identityPermissionActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IdentityVerityEngine identityVerityEngine = IdentityVerityEngine.getInstance();
        this.f7460c = identityVerityEngine;
        IdentityVerifySession session = identityVerityEngine.getSession();
        this.d = session;
        if (session != null && session.verifyParams != 0) {
            this.f7461e = (IdentityVerifyTracker) session.verifyTracker;
            setContentView(R.layout.vf_activity_permission_identity);
            this.a = findViewById(R.id.lay_permission_tip);
            this.b = (TextView) findViewById(R.id.tv_tip_content);
            findViewById(R.id.aips_prompt_confirm).setOnClickListener(new View.OnClickListener() { // from class: com.jdjr.risk.identity.verify.activity.IdentityPermissionActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    IdentityPermissionActivity.this.b();
                }
            });
            a();
            return;
        }
        this.f7460c.callbackFinishSDK(5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u53c2\u6570\u4e3a\u7a7a\uff01");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        IdentityVerifySession identityVerifySession = this.d;
        if (identityVerifySession != null && identityVerifySession.verifyParams != 0) {
            FsRequestCameraPermissionUtils.releasePermissionCallback();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        FsRequestCameraPermissionUtils.onRequestPermissionsResult(this, i2, strArr, iArr);
    }

    private void a() {
        VerificationSdk verificationSdk;
        VerificationSdk.Config config;
        if (!FsCameraPermissonCheck.hasCameraPermission(this)) {
            IdentityVerifyConfig identityVerifyConfig = (IdentityVerifyConfig) getIntent().getSerializableExtra("config");
            if (identityVerifyConfig != null && (verificationSdk = identityVerifyConfig.verificationSdk) != null && (config = verificationSdk.config) != null && DYConstants.DY_TRUE.equals(Boolean.valueOf(config.privacy_authorization_flag))) {
                b();
                return;
            }
            this.b.setText(getString(R.string.aips_vf_permission_tips, new Object[]{AppInfoUtil.getAppName(this)}));
            this.a.setVisibility(0);
            return;
        }
        setResult(-1);
        this.f7461e.trackRequestPermissionSuccess();
        finish();
    }

    static void b(IdentityPermissionActivity identityPermissionActivity) {
        identityPermissionActivity.setResult(-1);
        identityPermissionActivity.f7461e.trackRequestPermissionSuccess();
        identityPermissionActivity.finish();
    }
}
