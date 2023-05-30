package com.jingdong.common.utils;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class PersonalLoginUserHelper {
    private static final String TAG = "PersonalLoginUserHelper";
    private static PersonalLoginUserHelper loginUserHelper;

    private PersonalLoginUserHelper() {
    }

    public static synchronized PersonalLoginUserHelper getInstance() {
        PersonalLoginUserHelper personalLoginUserHelper;
        synchronized (PersonalLoginUserHelper.class) {
            if (loginUserHelper == null) {
                loginUserHelper = new PersonalLoginUserHelper();
            }
            personalLoginUserHelper = loginUserHelper;
        }
        return personalLoginUserHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoginActivity(final IMyActivity iMyActivity, final int i2, final Bundle bundle) {
        if (iMyActivity != null) {
            if (OKLog.D) {
                OKLog.d(TAG, "startLoginActivity -->> context : " + iMyActivity);
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt(LoginConstans.NEED_REFRESH_MODE, LoginConstans.REFRESH_MODE_VALUE);
            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.utils.PersonalLoginUserHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i2 > 0) {
                        if (OKLog.D) {
                            OKLog.d(PersonalLoginUserHelper.TAG, "startLoginActivity -->>1 context : " + iMyActivity);
                        }
                        DeepLinkLoginHelper.startLoginActivityForResult((Activity) iMyActivity, bundle, i2);
                        return;
                    }
                    DeepLinkLoginHelper.startLoginActivity((Activity) iMyActivity, bundle);
                }
            });
        }
    }

    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable) {
        executeLoginRunnable(iMyActivity, runnable, 0, null);
    }

    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, Bundle bundle) {
        executeLoginRunnable(iMyActivity, runnable, 0, bundle);
    }

    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, int i2) {
        executeLoginRunnable(iMyActivity, runnable, i2, null);
    }

    private void executeLoginRunnable(final IMyActivity iMyActivity, final Runnable runnable, final int i2, final Bundle bundle) {
        if (iMyActivity != null) {
            if (!LoginUserBase.hasLogin()) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.utils.PersonalLoginUserHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        PersonalLoginUserHelper.this.startLoginActivity(iMyActivity, i2, bundle);
                        LoginUserBase.loginCallback(iMyActivity, runnable);
                    }
                });
                return;
            }
            if (Thread.currentThread() != BaseApplication.getUiThread()) {
                iMyActivity.post(runnable);
            } else if (runnable != null) {
                runnable.run();
            }
        }
    }
}
