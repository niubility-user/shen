package com.jingdong.common.login;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JMAUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DialogLoginUtil {
    private static DialogLoginUtil mInstance;
    private IDialogLogin proxy;

    private DialogLoginUtil() {
    }

    public static DialogLoginUtil getInstance() {
        DialogLoginUtil dialogLoginUtil;
        DialogLoginUtil dialogLoginUtil2 = mInstance;
        if (dialogLoginUtil2 != null) {
            return dialogLoginUtil2;
        }
        synchronized (DialogLoginUtil.class) {
            if (mInstance == null) {
                mInstance = new DialogLoginUtil();
            }
            dialogLoginUtil = mInstance;
        }
        return dialogLoginUtil;
    }

    private IDialogLogin getProxy() {
        try {
            return (IDialogLogin) JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.login.dialoglogin.DialogLoginUtil").newInstance();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean checkPreGetMobile() {
        boolean checkPreGetMobile = MobileLoginUtil.checkPreGetMobile();
        if (Log.D) {
            Log.d("DailogLoginUtil", "checkPreGetMobile =" + checkPreGetMobile);
        }
        return checkPreGetMobile;
    }

    public boolean showDialog(Activity activity, Bundle bundle) {
        try {
            if (LoginUserBase.hasLogin()) {
                return false;
            }
            if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
                DeepLinkLoginHelper.startLoginActivity(activity, null);
                return false;
            }
            if (this.proxy == null) {
                this.proxy = getProxy();
            }
            if (this.proxy == null) {
                DeepLinkLoginHelper.startLoginActivity(activity, null);
                return false;
            }
            LoginConstans.FROM_BMODE = 0;
            if (bundle != null) {
                int i2 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d("DialogLoginUtil", "showDialog getBModel=" + i2);
                }
                LoginConstans.FROM_BMODE = i2;
            }
            boolean showDialog = this.proxy.showDialog(activity, bundle);
            JMAUtils.JMAReportForScene("basicShoppingProcess", "03b05847ea061aff507a5eedda9b5e29");
            return showDialog;
        } catch (Throwable th) {
            th.printStackTrace();
            DeepLinkLoginHelper.startLoginActivity(activity, null);
            return false;
        }
    }

    public void showDialog(Activity activity, Bundle bundle, ILogin iLogin, String str) {
        try {
            if (LoginUserBase.hasLogin()) {
                return;
            }
            if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
                DeepLinkLoginHelper.startLoginActivity(activity, bundle, iLogin, str);
                LoginObserverManager.getInstance().registerLoginListener(iLogin, str);
                return;
            }
            if (this.proxy == null) {
                this.proxy = getProxy();
            }
            if (this.proxy == null) {
                DeepLinkLoginHelper.startLoginActivity(activity, bundle, iLogin, str);
                LoginObserverManager.getInstance().registerLoginListener(iLogin, str);
                return;
            }
            LoginConstans.FROM_BMODE = 0;
            if (bundle != null) {
                int i2 = bundle.getInt(LoginConstans.NEED_REFRESH_MODE, 0);
                if (OKLog.D) {
                    OKLog.d("DialogLoginUtil", "showDialog getBModel=" + i2);
                }
                LoginConstans.FROM_BMODE = i2;
            }
            this.proxy.showDialog(activity, bundle);
            LoginObserverManager.getInstance().registerLoginListener(iLogin, str);
            JMAUtils.JMAReportForScene("basicShoppingProcess", "03b05847ea061aff507a5eedda9b5e29");
        } catch (Throwable th) {
            th.printStackTrace();
            DeepLinkLoginHelper.startLoginActivity(activity, bundle, iLogin, str);
            LoginObserverManager.getInstance().registerLoginListener(iLogin, str);
        }
    }
}
