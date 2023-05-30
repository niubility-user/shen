package com.jdjr.risk.identity.verify.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.identity.R;
import com.jd.aips.verify.ui.PromptDialog;
import com.jd.aips.widget.LoadingView;
import com.jdjr.risk.identity.verify.IdentityVerifyConfig;
import com.jdjr.risk.identity.verify.IdentityVerifySession;
import com.jdjr.risk.identity.verify.IdentityVerifyTracker;
import com.jdjr.risk.identity.verify.IdentityVerityEngine;
import com.jdjr.risk.identity.verify.api.IdentityVerifyConfigLoader;

/* loaded from: classes18.dex */
public class LauncherActivity extends AppCompatActivity {
    private IdentityVerityEngine a;
    private IdentityVerifySession b;

    /* renamed from: c */
    private IdentityVerifyTracker f7484c;
    LoadingView d;

    /* renamed from: e */
    LoaderManager.LoaderCallbacks<Bundle> f7485e = new LoaderManager.LoaderCallbacks<Bundle>() { // from class: com.jdjr.risk.identity.verify.activity.LauncherActivity.1
        {
            LauncherActivity.this = this;
        }

        public void a(Bundle bundle) {
            String str;
            LauncherActivity.this.d.setVisibility(8);
            int i2 = bundle.getInt("code", -1);
            IdentityVerifyConfig identityVerifyConfig = (IdentityVerifyConfig) bundle.get("data");
            if (identityVerifyConfig != null) {
                VerificationSdk verificationSdk = identityVerifyConfig.verificationSdk;
                if (verificationSdk != null && verificationSdk.config != null) {
                    LauncherActivity.this.a.setConfig(identityVerifyConfig);
                    LauncherActivity.this.f7484c.a(identityVerifyConfig.allInOneVersion);
                    LauncherActivity launcherActivity = LauncherActivity.this;
                    launcherActivity.getClass();
                    Intent intent = new Intent();
                    intent.putExtra("config", identityVerifyConfig);
                    VerificationSdk.Config config = identityVerifyConfig.verificationSdk.config;
                    if (config.privacy_authorization_flag) {
                        int i3 = config.authentication_mode;
                        if (i3 == 1) {
                            intent.setClass(launcherActivity, IdentityPrivacyAgrActivity.class);
                            launcherActivity.startActivityForResult(intent, 100);
                            return;
                        } else if (i3 == 3) {
                            intent.setClass(launcherActivity, IdentityPrivacyAgrDialogActivity.class);
                            launcherActivity.startActivityForResult(intent, 100);
                            return;
                        } else {
                            intent.setClass(launcherActivity, IdentityPermissionActivity.class);
                            launcherActivity.startActivityForResult(intent, 200);
                            return;
                        }
                    }
                    intent.setClass(launcherActivity, IdentityPermissionActivity.class);
                    launcherActivity.startActivityForResult(intent, 200);
                    return;
                }
                str = identityVerifyConfig.promptMsg;
            } else {
                str = "";
            }
            if (TextUtils.isEmpty(str)) {
                str = bundle.getString("msg");
            }
            if (i2 == 1183) {
                LauncherActivity.this.f7484c.e();
                if (TextUtils.isEmpty(str)) {
                    str = "\u73af\u5883\u8b66\u544a\uff0c\u8bbe\u5907\u73af\u5883\u5f02\u5e38";
                }
                LauncherActivity.a(LauncherActivity.this, str);
                return;
            }
            if (TextUtils.isEmpty(str)) {
                str = "\u83b7\u53d6\u4e1a\u52a1\u914d\u7f6e\u5931\u8d25";
            }
            LauncherActivity.this.f7484c.a(i2);
            LauncherActivity.this.a.callbackFinishSDK(2, str);
            LauncherActivity.this.finish();
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<Bundle> onCreateLoader(int i2, Bundle bundle) {
            LauncherActivity launcherActivity = LauncherActivity.this;
            return new IdentityVerifyConfigLoader(launcherActivity, launcherActivity.b);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public /* bridge */ /* synthetic */ void onLoadFinished(Loader<Bundle> loader, Bundle bundle) {
            a(bundle);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<Bundle> loader) {
        }
    };

    /* renamed from: f */
    private volatile Dialog f7486f;

    static void d(LauncherActivity launcherActivity) {
        launcherActivity.a.callbackFinishSDK(6, "\u73af\u5883\u5b58\u5728\u98ce\u9669");
        launcherActivity.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 200) {
            if (i3 == -1) {
                this.a.launchVerifySdks();
            } else {
                this.a.callbackFinishSDK(4, "\u6ca1\u6709\u76f8\u673a\u6743\u9650");
            }
        } else if (i2 == 100) {
            if (i3 == -1) {
                this.a.launchVerifySdks();
            } else if (i3 == 900) {
                this.a.callbackFinishSDK(4, "\u6ca1\u6709\u76f8\u673a\u6743\u9650");
            } else {
                this.a.callbackFinishSDK(3, "\u7528\u6237\u53d6\u6d88\u6838\u9a8c");
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        this.a.callbackFinishSDK(3, "\u7528\u6237\u53d6\u6d88\u6838\u9a8c");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IdentityVerityEngine identityVerityEngine = IdentityVerityEngine.getInstance();
        this.a = identityVerityEngine;
        IdentityVerifySession session = identityVerityEngine.getSession();
        this.b = session;
        if (session != null && session.verifyParams != 0) {
            this.f7484c = (IdentityVerifyTracker) session.verifyTracker;
            setContentView(R.layout.vf_activity_launcher_identity);
            LoadingView loadingView = (LoadingView) findViewById(R.id.aips_vf_loading);
            this.d = loadingView;
            loadingView.setText(R.string.aips_vf_loading);
            this.d.setVisibility(0);
            getSupportLoaderManager().restartLoader(this.f7485e.hashCode(), Bundle.EMPTY, this.f7485e);
            this.f7484c.d();
            return;
        }
        this.a.callbackFinishSDK(5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u53c2\u6570\u4e3a\u7a7a\uff01");
        finish();
    }

    static void a(LauncherActivity launcherActivity, String str) {
        if (launcherActivity.f7486f != null && launcherActivity.f7486f.isShowing()) {
            launcherActivity.f7486f.dismiss();
        }
        launcherActivity.f7486f = new PromptDialog(launcherActivity, str, new PromptDialog.PromptDelegate() { // from class: com.jdjr.risk.identity.verify.activity.LauncherActivity.2
            {
                LauncherActivity.this = launcherActivity;
            }

            @Override // com.jd.aips.verify.ui.PromptDialog.PromptDelegate
            public void onConfirm() {
                LauncherActivity.d(LauncherActivity.this);
            }
        });
        launcherActivity.f7486f.show();
    }
}
