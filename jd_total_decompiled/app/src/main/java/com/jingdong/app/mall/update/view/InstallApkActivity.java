package com.jingdong.app.mall.update.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.FileProvider;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.update.ApplicationUpgradeHelper;
import com.jingdong.app.mall.update.UpdateInitialization;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.apkcenter.ApkUtils;
import com.jingdong.common.database.table.VersionUpdataTable;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.jdupgrade.VersionEntity;
import java.io.File;

/* loaded from: classes4.dex */
public class InstallApkActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private JDDialog f11713g;

    /* renamed from: h  reason: collision with root package name */
    private VersionEntity f11714h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements DialogInterface.OnDismissListener {
        a(InstallApkActivity installApkActivity) {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            ApplicationUpgradeHelper.checkDialogIsShowing();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (InstallApkActivity.this.f11714h.state != 303) {
                if (InstallApkActivity.this.f11714h.state != 302) {
                    if (InstallApkActivity.this.f11714h.state == 301) {
                        JDMtaUtils.onClick(InstallApkActivity.this, "ApvUpgrade_NormalUInstall_Now", "ApvUpgrade_NormalU", "1");
                    }
                } else {
                    JDMtaUtils.onClick(InstallApkActivity.this, "ApvUpgrade_NormalUInstall_Now", "ApvUpgrade_NormalU", "0");
                }
            } else {
                JDMtaUtils.onClick(InstallApkActivity.this, "ApvUpgrade_ForcedUInstall_Now", "ApvUpgrade_ForcedU");
            }
            ApplicationUpgradeHelper.shutdownUploadService();
            String stringFromPreference = CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_INSTALL_FILE, "");
            File file = new File(stringFromPreference);
            if (!ApkUtils.isPackageValid(JdSdk.getInstance().getApplication(), stringFromPreference)) {
                ApplicationUpgradeHelper.reportFailEvent("", "signatures is error");
                InstallApkActivity.this.D(file, true);
                return;
            }
            InstallApkActivity installApkActivity = InstallApkActivity.this;
            if (installApkActivity.B(stringFromPreference, installApkActivity.f11714h)) {
                InstallApkActivity.this.D(file, false);
                return;
            }
            InstallApkActivity.this.E(file);
            if (InstallApkActivity.this.f11714h.state != 300) {
                InstallApkActivity.this.f11713g.dismiss();
                InstallApkActivity.this.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (InstallApkActivity.this.f11714h.state != 303) {
                if (InstallApkActivity.this.f11714h.state != 302) {
                    if (InstallApkActivity.this.f11714h.state == 301) {
                        JDMtaUtils.onClick(InstallApkActivity.this, "ApvUpgrade_NormalUInstal_Cancel", "ApvUpgrade_NormalU", "1");
                    }
                } else {
                    JDMtaUtils.onClick(InstallApkActivity.this, "ApvUpgrade_NormalUInstal_Cancel", "ApvUpgrade_NormalU", "0");
                }
            } else {
                JDMtaUtils.onClick(InstallApkActivity.this, "ApvUpgrade_ForcedUInstal_Quit", "ApvUpgrade_ForcedU");
            }
            ApplicationUpgradeHelper.reportFailEvent("", "install cancel");
            CommonBase.putLongToPreference("jd_app_install_prompt_date", System.currentTimeMillis());
            if (InstallApkActivity.this.f11714h.state == 303) {
                if (!InstallApkActivity.this.f11714h.isAutoCheck) {
                    InstallApkActivity.this.f11713g.dismiss();
                    InstallApkActivity.this.finish();
                    ActivityNumController.exitActivityWithoutTop();
                    BaseFrameUtil.exit(InstallApkActivity.this);
                    if (BaseFrameUtil.getInstance().getMainFrameActivity() != null) {
                        BaseFrameUtil.getInstance().getMainFrameActivity().finish();
                        return;
                    }
                    return;
                }
                BaseFrameUtil.exit(InstallApkActivity.this);
                return;
            }
            InstallApkActivity.this.f11713g.dismiss();
            InstallApkActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements DialogInterface.OnClickListener {
        d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (InstallApkActivity.this.f11713g != null) {
                InstallApkActivity.this.f11713g.dismiss();
            }
            ApplicationUpgradeHelper.checkDialogIsShowing();
            InstallApkActivity.this.finish();
        }
    }

    private void A() {
        VersionEntity versionEntity = this.f11714h;
        if (versionEntity == null) {
            return;
        }
        String str = versionEntity.installTitle;
        String str2 = versionEntity.installText;
        String str3 = versionEntity.installConfirm;
        String str4 = versionEntity.installCancel;
        int i2 = versionEntity.state;
        if (i2 == 303) {
            JDMtaUtils.onClick(this, "ApvUpgrade_ForcedUInstall_Expo", "ApvUpgrade_ForcedU");
        } else if (i2 == 302) {
            JDMtaUtils.onClick(this, "ApvUpgrade_NormalUInstall_Expo", "ApvUpgrade_NormalU", "0");
        } else if (i2 == 301) {
            JDMtaUtils.onClick(this, "ApvUpgrade_NormalUInstall_Expo", "ApvUpgrade_NormalU", "1");
        }
        JDDialog b2 = com.jingdong.app.mall.update.view.a.f().b(this, str, str2, str3, str4);
        this.f11713g = b2;
        b2.setOnDismissListener(new a(this));
        this.f11713g.setCancelable(false);
        this.f11713g.setOnRightButtonClickListener(new b());
        this.f11713g.setOnLeftButtonClickListener(new c());
        this.f11713g.show();
        UpdateInitialization.callBackDialogShowing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean B(String str, VersionEntity versionEntity) {
        if (TextUtils.isEmpty(str)) {
            ApplicationUpgradeHelper.reportFailEvent("", "ApkFilePath is null");
            return true;
        } else if (!FileUtils.fileIsExists(str)) {
            ApplicationUpgradeHelper.reportFailEvent("", "ApkFile not found");
            return true;
        } else {
            File file = new File(str);
            if (y(file, versionEntity)) {
                String fileMD5 = ApplicationUpgradeHelper.getFileMD5(file);
                if (versionEntity != null && !TextUtils.equals(fileMD5, versionEntity.fileMd5)) {
                    try {
                        JSONObjectProxy jSONObjectProxy = new JSONObjectProxy();
                        jSONObjectProxy.put("serverMd5", versionEntity.fileMd5);
                        jSONObjectProxy.put("localMd5", fileMD5);
                        ExceptionReporter.reportApplicationInstallEvent(versionEntity.url, jSONObjectProxy.toString());
                    } catch (Exception unused) {
                    }
                }
                return !ApplicationUpgradeHelper.checkApkIsAvailable(str) || BaseFrameUtil.getInstance().getCurrentMyActivity() == null;
            }
            return true;
        }
    }

    private void C(IMyActivity iMyActivity, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setDataAndType(z(file, intent), "application/vnd.android.package-archive");
        iMyActivity.startActivityNoException(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(File file, boolean z) {
        if (file != null && file.exists()) {
            file.delete();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("\u77e5\u9053\u4e86", new d());
        if (z) {
            builder.setMessage("\u5b89\u88c5\u5305\u5f02\u5e38\uff0c\u8bf7\u91cd\u8bd5\u6216\u524d\u5f80\u5b98\u7f51\u4e0b\u8f7d\uff01");
        } else {
            builder.setMessage("\u4e0b\u8f7d\u7684\u5b89\u88c5\u5305\u6709\u95ee\u9898\uff0c\u65e0\u6cd5\u5b89\u88c5\uff01\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01");
        }
        builder.show();
        UpdateInitialization.callBackDialogShowing();
    }

    private boolean y(File file, VersionEntity versionEntity) {
        String str = versionEntity == null ? "" : versionEntity.version;
        if (Log.D) {
            Log.d("InstallApkActivity", "apkIsAvailable-apkVersion=" + str);
        }
        if (TextUtils.isEmpty(str)) {
            ApplicationUpgradeHelper.reportFailEvent("", "ApkVersion is null in apkIsAvailable()");
            return false;
        } else if (file == null) {
            ApplicationUpgradeHelper.reportFailEvent("", "file is null in apkIsAvailable()");
            return false;
        } else {
            String fileMD5 = ApplicationUpgradeHelper.getFileMD5(file);
            String md5 = VersionUpdataTable.getMD5(str);
            if (Log.D) {
                Log.d("InstallApkActivity", "sdApkFileMd5: " + fileMD5);
                Log.d("InstallApkActivity", "dbApkFileMd5: " + md5);
            }
            if (TextUtils.equals(fileMD5, md5)) {
                return true;
            }
            if (file.exists()) {
                file.delete();
            }
            ApplicationUpgradeHelper.reportFailEvent(fileMD5 + "/" + md5, "MD5 is different in apkIsAvailable()");
            return false;
        }
    }

    private Uri z(File file, Intent intent) {
        Activity thisActivity = BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity();
        if (thisActivity == null || file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 23) {
            Uri uriForFile = FileProvider.getUriForFile(thisActivity, thisActivity.getPackageName() + ".fileProviderRootPath", file);
            if (intent != null) {
                intent.addFlags(3);
                return uriForFile;
            }
            return uriForFile;
        }
        return Uri.fromFile(file);
    }

    public void E(File file) {
        if (!CommonBase.checkSDcard()) {
            FileService.chModFile(" -R 755", file.getParentFile().getParentFile());
        }
        CommonBase.putLongToPreference("app_install_time", ApplicationUpgradeHelper.getAppLastModified(JdSdk.getInstance().getApplication()));
        if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
            C(BaseFrameUtil.getInstance().getCurrentMyActivity(), file);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTintEnable = false;
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        setContentView(R.layout.install_app_dredge_dialog);
        Intent intent = getIntent();
        if (intent != null && (intent.getSerializableExtra("UpgradeEntity") instanceof VersionEntity)) {
            VersionEntity versionEntity = (VersionEntity) intent.getSerializableExtra("UpgradeEntity");
            this.f11714h = versionEntity;
            if (versionEntity != null) {
                A();
                ApplicationUpgradeHelper.dismissUpdateDialog();
                return;
            }
        }
        ApplicationUpgradeHelper.reportFailEvent("", "InstallApkActivity_finish");
        finish();
    }
}
