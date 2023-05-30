package com.jingdong.app.mall.update;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.R;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.appupdate.UpdateSharedPreferenceUtil;
import com.jingdong.common.database.table.VersionUpdataTable;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.IDialogShow;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.widget.JDToast;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.jdupgrade.VersionEntity;
import java.io.File;

/* loaded from: classes4.dex */
public class ApplicationUpgradeHelper {
    private static final String TAG = "ApplicationUpgradeHelper";
    private static JDDialog autoUpdateAlertDialog;
    public static boolean isGrayWifiAutoDownload;
    private static Bundle mBundle;
    private static IDialogShow mIDialogShow;
    private static com.jingdong.app.mall.update.view.c updateProgressDialog;
    private static int upgradeState;

    /* loaded from: classes4.dex */
    public class a implements com.jingdong.app.mall.update.b {
        final /* synthetic */ IMyActivity a;

        a(IMyActivity iMyActivity) {
            this.a = iMyActivity;
        }

        @Override // com.jingdong.app.mall.update.b
        public void a() {
            if (this.a.getThisActivity() != null) {
                JDMtaUtils.onClick(this.a.getThisActivity(), "ApvUpgrade_ForcedU_Retry", "ApvUpgrade_ForcedU");
                this.a.getThisActivity().sendBroadcast(new Intent("PausableDownloadService.Download"));
            }
        }

        @Override // com.jingdong.app.mall.update.b
        public void downloadFinish() {
            ApplicationUpgradeHelper.updateProgressDialog.dismiss();
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ToastUtils.shortToast(JdSdk.getInstance().getApplication(), "\u7f51\u7edc\u5728\u5f00\u5c0f\u5dee\uff0c\u68c0\u67e5\u540e\u518d\u8bd5\u5427");
        }
    }

    /* loaded from: classes4.dex */
    public class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IMyActivity f11677g;

        /* renamed from: h */
        final /* synthetic */ VersionEntity f11678h;

        /* loaded from: classes4.dex */
        class a implements DialogInterface.OnDismissListener {
            a(c cVar) {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (ApplicationUpgradeHelper.updateProgressDialog == null) {
                    ApplicationUpgradeHelper.checkDialogIsShowing();
                }
            }
        }

        c(IMyActivity iMyActivity, VersionEntity versionEntity) {
            this.f11677g = iMyActivity;
            this.f11678h = versionEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDMtaUtils.onClick(this.f11677g.getThisActivity(), "ApvUpgrade_ForcedU_Expo", "ApvUpgrade_ForcedU");
            if (ApplicationUpgradeHelper.autoUpdateAlertDialog != null && ApplicationUpgradeHelper.autoUpdateAlertDialog.isShowing()) {
                ApplicationUpgradeHelper.autoUpdateAlertDialog.dismiss();
            }
            com.jingdong.app.mall.update.view.a f2 = com.jingdong.app.mall.update.view.a.f();
            Activity thisActivity = this.f11677g.getThisActivity();
            VersionEntity versionEntity = this.f11678h;
            JDDialog unused = ApplicationUpgradeHelper.autoUpdateAlertDialog = f2.c(thisActivity, versionEntity.downloadTitle, versionEntity.state, versionEntity.downloadText, "", versionEntity.downloadConfirm, versionEntity.downloadCancel);
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setOnDismissListener(new a(this));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setCancelable(false);
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setOnLeftButtonClickListener(new h(this.f11677g));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setOnRightButtonClickListener(new i(this.f11678h, this.f11677g));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.show();
            UpdateInitialization.callBackDialogShowing();
        }
    }

    /* loaded from: classes4.dex */
    public class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IMyActivity f11679g;

        /* renamed from: h */
        final /* synthetic */ VersionEntity f11680h;

        /* loaded from: classes4.dex */
        class a implements DialogInterface.OnDismissListener {
            a(d dVar) {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ApplicationUpgradeHelper.checkDialogIsShowing();
            }
        }

        d(IMyActivity iMyActivity, VersionEntity versionEntity) {
            this.f11679g = iMyActivity;
            this.f11680h = versionEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDMtaUtils.onClick(this.f11679g.getThisActivity(), "ApvUpgrade_NormalU_Expo", "ApvUpgrade_NormalU", "0");
            if (ApplicationUpgradeHelper.autoUpdateAlertDialog != null && ApplicationUpgradeHelper.autoUpdateAlertDialog.isShowing()) {
                ApplicationUpgradeHelper.autoUpdateAlertDialog.dismiss();
            }
            com.jingdong.app.mall.update.view.a f2 = com.jingdong.app.mall.update.view.a.f();
            Activity thisActivity = this.f11679g.getThisActivity();
            VersionEntity versionEntity = this.f11680h;
            JDDialog unused = ApplicationUpgradeHelper.autoUpdateAlertDialog = f2.c(thisActivity, versionEntity.downloadTitle, versionEntity.state, versionEntity.downloadText, versionEntity.downloadWlan, versionEntity.downloadConfirm, versionEntity.downloadCancel);
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setOnDismissListener(new a(this));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setCancelable(true);
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setOnLeftButtonClickListener(new j(this.f11680h, this.f11679g));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setOnRightButtonClickListener(new k(this.f11680h, this.f11679g));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.show();
            UpdateInitialization.callBackDialogShowing();
        }
    }

    /* loaded from: classes4.dex */
    public class e implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IMyActivity f11681g;

        /* renamed from: h */
        final /* synthetic */ VersionEntity f11682h;

        /* loaded from: classes4.dex */
        class a implements DialogInterface.OnDismissListener {
            a(e eVar) {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ApplicationUpgradeHelper.checkDialogIsShowing();
            }
        }

        e(IMyActivity iMyActivity, VersionEntity versionEntity) {
            this.f11681g = iMyActivity;
            this.f11682h = versionEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDMtaUtils.onClick(this.f11681g.getThisActivity(), "ApvUpgrade_NormalU_Expo", "ApvUpgrade_NormalU", "1");
            if (ApplicationUpgradeHelper.autoUpdateAlertDialog != null && ApplicationUpgradeHelper.autoUpdateAlertDialog.isShowing()) {
                ApplicationUpgradeHelper.autoUpdateAlertDialog.dismiss();
            }
            com.jingdong.app.mall.update.view.a f2 = com.jingdong.app.mall.update.view.a.f();
            Activity thisActivity = this.f11681g.getThisActivity();
            VersionEntity versionEntity = this.f11682h;
            String str = versionEntity.downloadTitle;
            String str2 = versionEntity.downloadText;
            String str3 = versionEntity.downloadConfirm;
            String str4 = versionEntity.downloadCancel;
            IMyActivity iMyActivity = this.f11681g;
            JDDialog unused = ApplicationUpgradeHelper.autoUpdateAlertDialog = f2.a(thisActivity, str, str2, str3, str4, new k(versionEntity, iMyActivity), new j(versionEntity, iMyActivity));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setOnDismissListener(new a(this));
            ApplicationUpgradeHelper.autoUpdateAlertDialog.setCancelable(true);
            ApplicationUpgradeHelper.autoUpdateAlertDialog.show();
            UpdateInitialization.callBackDialogShowing();
        }
    }

    /* loaded from: classes4.dex */
    public class f implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f11683g;

        f(String str) {
            this.f11683g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDToast jDToast = new JDToast((Context) JdSdk.getInstance().getApplication(), (byte) 2);
            jDToast.setText(this.f11683g);
            jDToast.setDuration(0);
            jDToast.show();
        }
    }

    /* loaded from: classes4.dex */
    public class g implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IMyActivity f11684g;

        /* renamed from: h */
        final /* synthetic */ String f11685h;

        /* loaded from: classes4.dex */
        class a implements DialogInterface.OnDismissListener {
            a(g gVar) {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ApplicationUpgradeHelper.checkDialogIsShowing();
            }
        }

        /* loaded from: classes4.dex */
        class b implements View.OnClickListener {

            /* renamed from: g */
            final /* synthetic */ JDDialog f11686g;

            b(g gVar, JDDialog jDDialog) {
                this.f11686g = jDDialog;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f11686g.dismiss();
            }
        }

        g(IMyActivity iMyActivity, String str) {
            this.f11684g = iMyActivity;
            this.f11685h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(this.f11684g.getThisActivity(), this.f11685h, "\u786e\u5b9a");
            createJdDialogWithStyle1.setOnDismissListener(new a(this));
            createJdDialogWithStyle1.setOnLeftButtonClickListener(new b(this, createJdDialogWithStyle1));
            createJdDialogWithStyle1.show();
            UpdateInitialization.callBackDialogShowing();
        }
    }

    /* loaded from: classes4.dex */
    private static class h implements View.OnClickListener {

        /* renamed from: g */
        private IMyActivity f11687g;

        public h(IMyActivity iMyActivity) {
            this.f11687g = iMyActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            IMyActivity iMyActivity = this.f11687g;
            if (iMyActivity != null) {
                JDMtaUtils.onClick(iMyActivity.getThisActivity(), "ApvUpgrade_ForcedU_Quit", "ApvUpgrade_ForcedU");
                ApplicationUpgradeHelper.autoUpdateAlertDialog.dismiss();
                this.f11687g.finish();
                ActivityNumController.exitActivityWithoutTop();
                BaseFrameUtil.exit(this.f11687g);
                com.jingdong.app.mall.n.c.a();
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class i implements View.OnClickListener {

        /* renamed from: g */
        private VersionEntity f11688g;

        /* renamed from: h */
        private IMyActivity f11689h;

        public i(VersionEntity versionEntity, IMyActivity iMyActivity) {
            this.f11688g = versionEntity;
            this.f11689h = iMyActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            IMyActivity iMyActivity = this.f11689h;
            if (iMyActivity != null) {
                JDMtaUtils.onClick(iMyActivity.getThisActivity(), "ApvUpgrade_ForcedU_Download", "ApvUpgrade_ForcedU");
                ApplicationUpgradeHelper.onKnow(this.f11688g, this.f11689h);
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class j implements View.OnClickListener {

        /* renamed from: g */
        private VersionEntity f11690g;

        /* renamed from: h */
        private IMyActivity f11691h;

        public j(VersionEntity versionEntity, IMyActivity iMyActivity) {
            this.f11690g = versionEntity;
            this.f11691h = iMyActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            IMyActivity iMyActivity = this.f11691h;
            if (iMyActivity != null) {
                int i2 = this.f11690g.state;
                if (i2 == 302) {
                    JDMtaUtils.onClick(iMyActivity.getThisActivity(), "ApvUpgrade_NormalU_Cancel", "ApvUpgrade_NormalU", "0");
                } else if (i2 == 301) {
                    JDMtaUtils.onClick(iMyActivity.getThisActivity(), "ApvUpgrade_NormalU_Cancel", "ApvUpgrade_NormalU", "1");
                }
            }
            if (ApplicationUpgradeHelper.autoUpdateAlertDialog != null) {
                ApplicationUpgradeHelper.autoUpdateAlertDialog.dismiss();
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class k implements View.OnClickListener {

        /* renamed from: g */
        private VersionEntity f11692g;

        /* renamed from: h */
        private IMyActivity f11693h;

        public k(VersionEntity versionEntity, IMyActivity iMyActivity) {
            this.f11692g = versionEntity;
            this.f11693h = iMyActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            IMyActivity iMyActivity = this.f11693h;
            if (iMyActivity != null) {
                int i2 = this.f11692g.state;
                if (i2 == 302) {
                    JDMtaUtils.onClick(iMyActivity.getThisActivity(), "ApvUpgrade_NormalU_Download", "ApvUpgrade_NormalU", "0");
                } else if (i2 == 301) {
                    JDMtaUtils.onClick(iMyActivity.getThisActivity(), "ApvUpgrade_NormalU_Download", "ApvUpgrade_NormalU", "1");
                }
                if (NetUtils.isWifi()) {
                    ApplicationUpgradeHelper.showToast("\u60a8\u6b63\u5728\u4f7f\u7528WIFI\u7f51\u7edc\u4e0b\u8f7d\u6700\u65b0\u5305", this.f11693h);
                }
                ApplicationUpgradeHelper.onKnow(null, this.f11693h);
            }
        }
    }

    public static boolean apkFileIsExists(String str) {
        String stringFromPreference = CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_INSTALL_FILE, "");
        if (TextUtils.isEmpty(stringFromPreference) || !stringFromPreference.contains(str)) {
            return false;
        }
        return new File(stringFromPreference).exists();
    }

    public static boolean checkApkIsAvailable(String str) {
        boolean z = false;
        try {
            PackageInfo packageArchiveInfo = JdSdk.getInstance().getApplication().getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo != null) {
                if (TextUtils.isEmpty(packageArchiveInfo.packageName)) {
                    reportFailEvent("", "versionName is null in checkApkIsAvailable()");
                    deleteFile(str);
                } else {
                    z = true;
                }
            } else {
                reportFailEvent("", "packageInfo is null in checkApkIsAvailable()");
                deleteFile(str);
            }
        } catch (Throwable th) {
            if (Log.D) {
                th.printStackTrace();
            }
            reportFailEvent("", "throwable is found in checkApkIsAvailable()");
            deleteFile(str);
        }
        return z;
    }

    public static void checkDialogIsShowing() {
        UpdateInitialization.checkDialogIsShowing(mIDialogShow);
    }

    private static void deleteFile(String str) {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity != null) {
            JDMtaUtils.onClick(currentMyActivity.getThisActivity(), "WifiUpdate_VerifyFail", currentMyActivity.getThisActivity().getClass().getName());
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void dismissUpdateDialog() {
        if (Log.D) {
            Log.d(TAG, "autoUpdateAlertDialog= " + autoUpdateAlertDialog);
        }
        JDDialog jDDialog = autoUpdateAlertDialog;
        if (jDDialog == null || !jDDialog.isShowing()) {
            return;
        }
        if (Log.D) {
            Log.d(TAG, "autoUpdateAlertDialog.dismiss()");
        }
        autoUpdateAlertDialog.dismiss();
    }

    public static void dismissUpdateProgressDialog() {
        com.jingdong.app.mall.update.view.c cVar = updateProgressDialog;
        if (cVar == null || !cVar.isShowing()) {
            return;
        }
        if (Log.D) {
            Log.d(TAG, "updateProgressDialog.dismiss()");
        }
        updateProgressDialog.dismiss();
    }

    public static long getAppLastModified(Context context) {
        try {
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            long lastModified = new File(str).lastModified();
            if (Log.D) {
                Log.d(TAG, "getAppLastModified-appFile=" + str + " installed=" + lastModified);
            }
            return lastModified;
        } catch (Throwable unused) {
            return 0L;
        }
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0067: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:81:0x0067 */
    /* JADX WARN: Removed duplicated region for block: B:90:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFileMD5(java.io.File r7) {
        /*
            boolean r0 = r7.isFile()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r2 = new byte[r0]
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            r4.<init>(r7)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
        L17:
            r7 = 0
            int r5 = r4.read(r2, r7, r0)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L66
            r6 = -1
            if (r5 == r6) goto L23
            r3.update(r2, r7, r5)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L66
            goto L17
        L23:
            r4.close()     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L66
            r4.close()     // Catch: java.lang.Exception -> L29
        L29:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            byte[] r1 = r3.digest()
        L32:
            int r2 = r1.length
            if (r7 >= r2) goto L52
            r2 = r1[r7]
            int r2 = r2 >>> 4
            r2 = r2 & 15
            java.lang.String r3 = "0123456789abcdef"
            char r2 = r3.charAt(r2)
            r0.append(r2)
            r2 = r1[r7]
            r2 = r2 & 15
            char r2 = r3.charAt(r2)
            r0.append(r2)
            int r7 = r7 + 1
            goto L32
        L52:
            java.lang.String r7 = r0.toString()
            return r7
        L57:
            r7 = move-exception
            goto L5d
        L59:
            r7 = move-exception
            goto L68
        L5b:
            r7 = move-exception
            r4 = r1
        L5d:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L66
            if (r4 == 0) goto L65
            r4.close()     // Catch: java.lang.Exception -> L65
        L65:
            return r1
        L66:
            r7 = move-exception
            r1 = r4
        L68:
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.lang.Exception -> L6d
        L6d:
            goto L6f
        L6e:
            throw r7
        L6f:
            goto L6e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.update.ApplicationUpgradeHelper.getFileMD5(java.io.File):java.lang.String");
    }

    private static boolean isFileAvailable(String str, String str2) {
        String stringFromPreference = CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_INSTALL_FILE, "");
        String stringFromPreference2 = CommonBase.getStringFromPreference("jd_app_update_url", "");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(stringFromPreference) && stringFromPreference.contains(str) && FileUtils.fileIsExists(stringFromPreference) && str2.equals(stringFromPreference2) && !CommonBase.getBooleanFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.FALSE).booleanValue()) {
            try {
                String md5 = VersionUpdataTable.getMD5(str);
                String fileMD5 = getFileMD5(new File(stringFromPreference));
                if (Log.D) {
                    Log.d(TAG, "oldMd5-->> " + md5);
                    Log.d(TAG, "newMd5-->> " + fileMD5);
                }
                if (!TextUtils.isEmpty(md5) && !TextUtils.isEmpty(fileMD5)) {
                    if (TextUtils.equals(md5, fileMD5)) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean isVIVOPartner() {
        String property = Configuration.getProperty(Configuration.PARTNER);
        if (Log.D) {
            Log.d(TAG, "partner=" + property);
        }
        return !TextUtils.isEmpty(property) && property.toLowerCase().contains("vivo");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0045 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isVIVOStoreEnable() {
        /*
            com.jingdong.app.mall.update.UpdateInitialization r0 = com.jingdong.app.mall.update.UpdateInitialization.getUpdateInitializationInstance()
            java.lang.String r1 = "com.bbk.appstore"
            boolean r0 = r0.isAppExist(r1)
            r2 = 0
            if (r0 == 0) goto L46
            com.jingdong.jdsdk.JdSdk r0 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Exception -> L3c
            android.content.Context r0 = r0.getApplicationContext()     // Catch: java.lang.Exception -> L3c
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: java.lang.Exception -> L3c
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r1, r2)     // Catch: java.lang.Exception -> L3c
            int r0 = r0.versionCode     // Catch: java.lang.Exception -> L3c
            boolean r1 = com.jingdong.corelib.utils.Log.D     // Catch: java.lang.Exception -> L3a
            if (r1 == 0) goto L41
            java.lang.String r1 = "ApplicationUpgradeHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L3a
            r3.<init>()     // Catch: java.lang.Exception -> L3a
            java.lang.String r4 = "isVIVOStoreEnable-isAppExist=true versionCode="
            r3.append(r4)     // Catch: java.lang.Exception -> L3a
            r3.append(r0)     // Catch: java.lang.Exception -> L3a
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L3a
            com.jingdong.corelib.utils.Log.d(r1, r3)     // Catch: java.lang.Exception -> L3a
            goto L41
        L3a:
            r1 = move-exception
            goto L3e
        L3c:
            r1 = move-exception
            r0 = 0
        L3e:
            r1.printStackTrace()
        L41:
            r1 = 3100(0xc1c, float:4.344E-42)
            if (r0 < r1) goto L46
            r2 = 1
        L46:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.update.ApplicationUpgradeHelper.isVIVOStoreEnable():boolean");
    }

    public static void jumpToAppStoreDetailUpdate(Context context) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("vivomarket://details?id=" + context.getPackageName() + "&th_name=self_update"));
        intent.setPackage("com.bbk.appstore");
        intent.setFlags(335544320);
        context.startActivity(intent);
    }

    public static void onKnow(VersionEntity versionEntity, IMyActivity iMyActivity) {
        if (vivoAutoDownload(iMyActivity.getThisActivity())) {
            return;
        }
        mBundle.putString(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_CLICK_TS, ExceptionReporter.getCurrentMicrosecond());
        if (iMyActivity != null && NetUtils.isNetworkAvailable()) {
            Intent intent = new Intent("com.jingdong.app.mall.update.PausableDownloadService");
            intent.setPackage(jd.wjlogin_sdk.util.f.f19954c);
            intent.putExtras(mBundle);
            iMyActivity.getThisActivity().startService(intent);
            JDDialog jDDialog = autoUpdateAlertDialog;
            if (jDDialog != null) {
                jDDialog.dismiss();
            }
            if (upgradeState == 303) {
                com.jingdong.app.mall.update.view.c d2 = com.jingdong.app.mall.update.view.a.f().d(iMyActivity.getThisActivity(), new a(iMyActivity), iMyActivity.getThisActivity().getResources().getString(R.string.upgrade_download_progress), iMyActivity.getThisActivity().getResources().getString(R.string.upgrade_download_error));
                updateProgressDialog = d2;
                d2.setCancelable(false);
                updateProgressDialog.show();
                UpdateInitialization.callBackDialogShowing();
                return;
            }
            return;
        }
        if (isGrayWifiAutoDownload) {
            isGrayWifiAutoDownload = false;
        }
        BaseApplication.getHandler().post(new b());
        JDDialog jDDialog2 = autoUpdateAlertDialog;
        if (jDDialog2 == null || upgradeState == 303) {
            return;
        }
        jDDialog2.dismiss();
    }

    public static void reportFailEvent(String str, String str2) {
        String currentMicrosecond = ExceptionReporter.getCurrentMicrosecond();
        try {
            long longFromPreference = CommonBase.getLongFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_APK_SIZE, -1L);
            ExceptionReporter.reportApplicationUpgradeEvent(CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_URL, ""), CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_CLICK_TS, "0"), currentMicrosecond, longFromPreference + "", "0", str, str2);
        } catch (Exception unused) {
        }
    }

    public static void reset() {
        try {
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            edit.putInt("jd_app_breakpoint_transmission", 0);
            edit.putLong(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_APK_SIZE, 0L);
            edit.putString("app_upgrade", "");
            edit.putString(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_URL, "");
            edit.putString(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_VERSION, "");
            edit.putInt("app_update_percent", 0);
            edit.commit();
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public static void resumeUploadService() {
        Intent intent = new Intent("com.jingdong.app.mall.update.PausableDownloadService");
        intent.putExtra("APP_UPDATE_COMMAND", 1);
        JdSdk.getInstance().getApplication().sendBroadcast(intent);
    }

    public static void setDialogShow(IDialogShow iDialogShow) {
        mIDialogShow = iDialogShow;
    }

    private static void showGrayUpdateDialog(VersionEntity versionEntity, IMyActivity iMyActivity) {
        if (iMyActivity != null) {
            iMyActivity.post(new e(iMyActivity, versionEntity));
        }
    }

    private static void showMustUpdateDialog(VersionEntity versionEntity, IMyActivity iMyActivity) {
        if (iMyActivity != null) {
            iMyActivity.post(new c(iMyActivity, versionEntity));
        }
    }

    private static void showNeedUpdateDialog(VersionEntity versionEntity, IMyActivity iMyActivity) {
        if (iMyActivity != null) {
            iMyActivity.post(new d(iMyActivity, versionEntity));
        }
    }

    private static void showNoUpdateDialog(String str, IMyActivity iMyActivity) {
        if (iMyActivity != null) {
            iMyActivity.post(new g(iMyActivity, str));
        }
    }

    public static void showToast(String str, IMyActivity iMyActivity) {
        if (iMyActivity != null) {
            iMyActivity.post(new f(str));
        }
    }

    public static void shutdownUploadService() {
        Intent intent = new Intent("com.jingdong.app.mall.update.PausableDownloadService");
        intent.putExtra("APP_UPDATE_COMMAND", 2);
        JdSdk.getInstance().getApplication().sendBroadcast(intent);
    }

    private static void startInstallActivity(VersionEntity versionEntity) {
        if (checkApkIsAvailable(CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_INSTALL_FILE, ""))) {
            Intent intent = new Intent();
            intent.setClassName(jd.wjlogin_sdk.util.f.f19954c, "com.jingdong.app.mall.update.view.InstallApkActivity");
            intent.putExtra("UpgradeEntity", versionEntity);
            intent.addFlags(268435456);
            IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (currentMyActivity != null) {
                currentMyActivity.startActivityNoException(intent);
            }
        }
    }

    public static void stopUploadService() {
        Intent intent = new Intent("com.jingdong.app.mall.update.PausableDownloadService");
        intent.putExtra("APP_UPDATE_COMMAND", 0);
        JdSdk.getInstance().getApplication().sendBroadcast(intent);
        CommonBase.putBooleanToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.TRUE);
    }

    public static void tryUpgrade(IMyActivity iMyActivity, VersionEntity versionEntity) {
        if (iMyActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        mBundle = bundle;
        bundle.putSerializable("UpgradeEntity", versionEntity);
        upgradeState = versionEntity.state;
        boolean isFileAvailable = isFileAvailable(versionEntity.version, versionEntity.url);
        if (!isFileAvailable) {
            if (Log.D) {
                Log.d(TAG, "isInstallDialog=" + isFileAvailable + " reset()");
            }
            reset();
        }
        switch (upgradeState) {
            case 300:
                if (versionEntity.isAutoCheck) {
                    return;
                }
                showNoUpdateDialog("\u60a8\u6240\u4f7f\u7528\u7684\u5df2\u662f\u6700\u65b0\u7248\u672c", iMyActivity);
                return;
            case 301:
                if (!versionEntity.isAutoCheck) {
                    if (isFileAvailable) {
                        startInstallActivity(versionEntity);
                        return;
                    } else {
                        showGrayUpdateDialog(versionEntity, iMyActivity);
                        return;
                    }
                } else if (versionEntity.isAutoDownload && NetUtils.isWifi() && UpdateSharedPreferenceUtil.getBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, true, 1) && !isFileAvailable) {
                    showToast(TextUtils.isEmpty(versionEntity.toast) ? "\u60a8\u6b63\u5728\u4f7f\u7528WiFi\u7f51\u7edc\u4e0b\u8f7d\u6700\u65b0\u5305" : versionEntity.toast, iMyActivity);
                    isGrayWifiAutoDownload = true;
                    onKnow(null, iMyActivity);
                    return;
                } else {
                    return;
                }
            case 302:
                if (!versionEntity.isAutoCheck) {
                    if (isFileAvailable) {
                        startInstallActivity(versionEntity);
                        return;
                    } else {
                        showNeedUpdateDialog(versionEntity, iMyActivity);
                        return;
                    }
                } else if (versionEntity.isAutoDownload && NetUtils.isWifi() && UpdateSharedPreferenceUtil.getBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, true, 1) && !isFileAvailable) {
                    showToast(TextUtils.isEmpty(versionEntity.toast) ? "\u60a8\u6b63\u5728\u4f7f\u7528WiFi\u7f51\u7edc\u4e0b\u8f7d\u6700\u65b0\u5305" : versionEntity.toast, iMyActivity);
                    onKnow(null, iMyActivity);
                    return;
                } else {
                    return;
                }
            case 303:
                if (isFileAvailable) {
                    startInstallActivity(versionEntity);
                    return;
                } else {
                    showMustUpdateDialog(versionEntity, iMyActivity);
                    return;
                }
            default:
                return;
        }
    }

    private static boolean vivoAutoDownload(Context context) {
        JDDialog jDDialog;
        if (upgradeState != 301 && isVIVOPartner() && isVIVOStoreEnable()) {
            if (upgradeState != 303 && (jDDialog = autoUpdateAlertDialog) != null) {
                jDDialog.dismiss();
            }
            jumpToAppStoreDetailUpdate(context);
            return true;
        }
        return false;
    }
}
