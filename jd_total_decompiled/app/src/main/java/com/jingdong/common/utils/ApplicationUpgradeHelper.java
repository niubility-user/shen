package com.jingdong.common.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.widget.JDToast;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class ApplicationUpgradeHelper {
    private static final double ALL_DAY_HOUR = 24.0d;
    public static final String APP_APK_SIZE = "app_apksize";
    public static final String APP_INSTALL_FILE = "jd_app_install_file";
    public static final String APP_UPDATE_CLICK_TS = "APP_UPDATE_CLICK_TS";
    public static final String APP_UPDATE_FAILED = "jd_app_update_failed";
    public static final String APP_URL = "app_url";
    public static final String APP_VERSION = "app_version";
    public static final int INSTALL_REQUEST_CODE = 1001;
    private static final int MUST_UPDATE = 1;
    private static final String TAG = "ApplicationUpgradeHelper";
    private static final String UPGRADE_KEY = "Akd@*168MedT$Q9Vj";
    public static final String WIFI_AUTO_UPDATE_SERVICE = "com.jingdong.app.mall.service.PausableDownloadService";
    private static JDDialog alertDialog;
    private static Bundle mBundle;
    private static String mDownloadUrl;
    public static IDialogShow mIDialogShow;
    private static IMyActivity mMyActivity;
    private static int upgradeState;
    private static Integer mApkSize = -1;
    private static boolean isCancel = false;
    private static View.OnClickListener negClickListener = new View.OnClickListener() { // from class: com.jingdong.common.utils.ApplicationUpgradeHelper.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (OKLog.D) {
                OKLog.d(ApplicationUpgradeHelper.TAG, "onClick() BUTTON_POSITIVE -->> ");
            }
            ApplicationUpgradeHelper.mBundle.putString(ApplicationUpgradeHelper.APP_UPDATE_CLICK_TS, ExceptionReporter.getCurrentMicrosecond());
            if (NetUtils.isNetworkAvailable()) {
                Intent intent = new Intent(ApplicationUpgradeHelper.WIFI_AUTO_UPDATE_SERVICE);
                intent.putExtras(ApplicationUpgradeHelper.mBundle);
                ApplicationUpgradeHelper.mMyActivity.getThisActivity().startService(intent);
            } else {
                BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.utils.ApplicationUpgradeHelper.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JDToast jDToast = new JDToast((Context) JdSdk.getInstance().getApplication(), (byte) 1);
                        jDToast.setText("\u7f51\u7edc\u5728\u5f00\u5c0f\u5dee\uff0c\u68c0\u67e5\u540e\u518d\u8bd5\u5427");
                        jDToast.setImage((byte) 1);
                        jDToast.show();
                    }
                });
                ExceptionReporter.reportApplicationUpgradeEvent(ApplicationUpgradeHelper.mDownloadUrl, ApplicationUpgradeHelper.mBundle.getString(ApplicationUpgradeHelper.APP_UPDATE_CLICK_TS), "0", "" + ApplicationUpgradeHelper.mApkSize, "0");
            }
            ApplicationUpgradeHelper.alertDialog.dismiss();
        }
    };
    private static View.OnClickListener posClickListener = new View.OnClickListener() { // from class: com.jingdong.common.utils.ApplicationUpgradeHelper.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (OKLog.D) {
                OKLog.d(ApplicationUpgradeHelper.TAG, "onClick() BUTTON_NEGATIVE -->> ");
            }
            ExceptionReporter.reportApplicationUpgradeEvent("", "0", ExceptionReporter.getCurrentMicrosecond(), "-1", "0");
            if (ApplicationUpgradeHelper.upgradeState != 1) {
                boolean unused = ApplicationUpgradeHelper.isCancel = true;
            } else {
                BaseFrameUtil.exitAll();
            }
            ApplicationUpgradeHelper.alertDialog.dismiss();
        }
    };

    public static void checkDialogIsShowing(IDialogShow iDialogShow) {
        if (iDialogShow == null) {
            return;
        }
        mIDialogShow = iDialogShow;
        iDialogShow.DialogDismiss(false);
    }

    private static void initDataAndDialog(IMyActivity iMyActivity, String str, Integer num, String str2) {
        mMyActivity = iMyActivity;
        mDownloadUrl = str;
        mApkSize = num;
        isCancel = false;
        JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(mMyActivity.getThisActivity(), "\u63d0\u793a", "\u53d6\u6d88", "\u786e\u5b9a");
        alertDialog = createJdDialogWithStyle2;
        createJdDialogWithStyle2.messageView.setGravity(3);
        alertDialog.setCancelable(false);
        alertDialog.setOnLeftButtonClickListener(posClickListener);
        alertDialog.setOnRightButtonClickListener(negClickListener);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.jingdong.common.utils.ApplicationUpgradeHelper.3
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                return true;
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.utils.ApplicationUpgradeHelper.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ApplicationUpgradeHelper.checkDialogIsShowing(ApplicationUpgradeHelper.mIDialogShow);
            }
        });
        alertDialog.show();
    }

    public static void tryDownloadAndInstall(IMyActivity iMyActivity, String str, int i2, String str2, String str3) {
        if (OKLog.D) {
            OKLog.d(TAG, "tryUpgrade() -->> ");
        }
        Bundle bundle = new Bundle();
        mBundle = bundle;
        bundle.putString(APP_VERSION, str3);
        mBundle.putString(APP_URL, str);
        mBundle.putInt(APP_APK_SIZE, i2);
        initDataAndDialog(iMyActivity, str, Integer.valueOf(i2), str3);
        alertDialog.setMessage(str2);
        alertDialog.show();
    }
}
