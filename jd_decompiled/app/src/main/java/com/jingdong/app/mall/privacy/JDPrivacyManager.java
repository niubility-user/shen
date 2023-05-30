package com.jingdong.app.mall.privacy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDSecUtils;
import com.jingdong.common.utils.JMAUtils;
import com.jingdong.jdma.minterface.AppMode;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class JDPrivacyManager {
    private static final String KEY_PRIVACY_DIALOG = "privacy_dialog_dismiss";
    public static boolean privacyAgreed;
    private static PrivacyCallback sPrivacyCallback;
    static JDPrivacyStateListener sSimpleStateListener;
    static JDPrivacyStateListener sStateListener;
    private static boolean sUnIconLaunch;
    Activity mCurrentActivity;
    private JDDialog mJdCheckDialog;
    private JDDialog mJdOnceDialog;
    private Dialog mPrivacyDialog;
    private static boolean sInAppAgreed = JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
    private static boolean sHasReportPvMta = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class JDPrivacyManagerHolder {
        private static final JDPrivacyManager INSTANCE = new JDPrivacyManager();

        private JDPrivacyManagerHolder() {
        }
    }

    /* loaded from: classes4.dex */
    public interface PrivacyCallback {
        void onClose(boolean z);

        void onDismiss();
    }

    private void agreeAll() {
        onClose(true);
    }

    public void agreePrivacy(boolean z) {
        privacyAgreed = true;
        JDPrivacyHelper.savePrivacy(JdSdk.getInstance().getApplication(), true);
        if (z) {
            EventBus.getDefault().post(new JDPrivacyAgreeEvent());
        }
        JDMtaUtils.setAppMode(AppMode.NORMAL);
        JMAUtils.privacyOn();
        JDSecUtils.report("privicyON", null);
    }

    private Dialog createPrivacyDialog(Activity activity) {
        Dialog dialog = new Dialog(activity, R.style.privacy_dialog);
        dialog.setContentView(JDPrivacyUtil.generateDialogView(activity));
        dialog.setCancelable(false);
        setDialogCallback(dialog);
        return dialog;
    }

    private void disagreePrivacy() {
        JDPrivacyHelper.savePrivacy(JdSdk.getInstance().getApplication(), false);
    }

    public static JDPrivacyManager getInstance() {
        return JDPrivacyManagerHolder.INSTANCE;
    }

    private void initAfterPrivacy() {
        JDPrivacyStateListener jDPrivacyStateListener = sStateListener;
        if (jDPrivacyStateListener != null) {
            jDPrivacyStateListener.afterAgree(sUnIconLaunch);
        }
    }

    public void onClose(boolean z) {
        CommonBase.putBooleanToPreference(KEY_PRIVACY_DIALOG, Boolean.TRUE);
        if (!z) {
            disagreePrivacy();
            JDPrivacyStateListener jDPrivacyStateListener = sStateListener;
            if (jDPrivacyStateListener != null) {
                jDPrivacyStateListener.afterDisagree(sUnIconLaunch);
            }
        }
        PrivacyCallback privacyCallback = sPrivacyCallback;
        if (privacyCallback != null) {
            privacyCallback.onClose(z);
        }
        release();
    }

    private void onError() {
        PrivacyCallback privacyCallback = sPrivacyCallback;
        if (privacyCallback == null) {
            return;
        }
        privacyCallback.onClose(JDPrivacyHelper.isAcceptPrivacy(this.mCurrentActivity));
        release();
    }

    public void release() {
        try {
            releaseSafe();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void releaseSafe() {
        sPrivacyCallback = null;
        this.mCurrentActivity = null;
        JDPrivacyUtil.dismissDialog(this.mJdCheckDialog);
        JDPrivacyUtil.dismissDialog(this.mPrivacyDialog);
        JDPrivacyUtil.dismissDialog(this.mJdOnceDialog);
        this.mJdCheckDialog = null;
        this.mPrivacyDialog = null;
        this.mJdOnceDialog = null;
    }

    public void safeAgreeAll() {
        try {
            sInAppAgreed = true;
            savePrivacy(false);
            initAfterPrivacy();
            agreeAll();
        } catch (Exception unused) {
            onError();
        }
    }

    private void safeOpenPrivacyDialogSafe(boolean z, Activity activity, PrivacyCallback privacyCallback) {
        if (activity == null || privacyCallback == null) {
            return;
        }
        PrivacyCallback privacyCallback2 = sPrivacyCallback;
        if (privacyCallback2 != null) {
            privacyCallback2.onDismiss();
        }
        sPrivacyCallback = privacyCallback;
        this.mCurrentActivity = activity;
        if (CommonBase.getBooleanFromPreference(KEY_PRIVACY_DIALOG, Boolean.FALSE).booleanValue()) {
            onClose(false);
            return;
        }
        sUnIconLaunch = z;
        if (!JDPrivacyHelper.isAcceptPrivacy(activity)) {
            JDPrivacyUtil.dismissDialog(this.mPrivacyDialog);
            Dialog createPrivacyDialog = createPrivacyDialog(activity);
            this.mPrivacyDialog = createPrivacyDialog;
            Window window = createPrivacyDialog.getWindow();
            if (window == null) {
                onError();
                return;
            }
            window.setGravity(17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = JDPrivacyUtil.getDpi750Width(activity, R2.attr.chipSpacingVertical);
            attributes.height = JDPrivacyUtil.getDpi750Width(activity, R2.attr.endYear);
            window.setAttributes(attributes);
            this.mPrivacyDialog.show();
            if (sHasReportPvMta) {
                return;
            }
            sHasReportPvMta = true;
            JDMtaUtils.sendPagePv(JdSdk.getInstance().getApplicationContext(), this, sUnIconLaunch ? "1" : "0", "Privacy_Policy", "");
            return;
        }
        sPrivacyCallback = privacyCallback;
        onClose(true);
    }

    private void setDialogCallback(final Dialog dialog) {
        dialog.findViewById(R.id.privacy_bottom_agree).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyManager.3
            {
                JDPrivacyManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDPrivacyUtil.dismissDialog(dialog);
                JDPrivacyManager.this.safeAgreeAll();
                JDPrivacyUtil.sendMta("PrivacyPolicy_Agree");
            }
        });
        dialog.findViewById(R.id.privacy_bottom_deny).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyManager.4
            {
                JDPrivacyManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDPrivacyUtil.dismissDialog(dialog);
                JDPrivacyManager.this.showDetainOnceDialog();
            }
        });
    }

    private void showCheckPrivacyDialogSafe(Activity activity, final PrivacyCallback privacyCallback) {
        JDPrivacyUtil.dismissDialog(this.mJdCheckDialog);
        this.mCurrentActivity = activity;
        JDDialog createCheckDialogSafe = JDPrivacyUtil.createCheckDialogSafe(activity);
        this.mJdCheckDialog = createCheckDialogSafe;
        createCheckDialogSafe.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyManager.1
            {
                JDPrivacyManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDPrivacyUtil.dismissDialog(JDPrivacyManager.this.mJdCheckDialog);
                PrivacyCallback privacyCallback2 = privacyCallback;
                if (privacyCallback2 != null) {
                    privacyCallback2.onClose(false);
                }
                JDPrivacyUtil.sendMta("PrivacyPolicy_SecondDisagree");
                JDPrivacyStateListener jDPrivacyStateListener = JDPrivacyManager.sSimpleStateListener;
                if (jDPrivacyStateListener != null) {
                    jDPrivacyStateListener.afterDisagree(true);
                }
                JDPrivacyManager.this.release();
            }
        });
        this.mJdCheckDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyManager.2
            {
                JDPrivacyManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDPrivacyUtil.dismissDialog(JDPrivacyManager.this.mJdCheckDialog);
                JDPrivacyManager.this.agreePrivacy(true);
                PrivacyCallback privacyCallback2 = privacyCallback;
                if (privacyCallback2 != null) {
                    privacyCallback2.onClose(true);
                }
                JDPrivacyUtil.sendMta("PrivacyPolicy_SecondAgree");
                JDPrivacyStateListener jDPrivacyStateListener = JDPrivacyManager.sSimpleStateListener;
                if (jDPrivacyStateListener != null) {
                    jDPrivacyStateListener.afterAgree(true);
                }
                JDPrivacyManager.this.release();
            }
        });
        this.mJdCheckDialog.show();
        JDPrivacyUtil.sendMtaExpo(" PrivacyPolicy_SecondPopupExpo");
    }

    public void showDetainOnceDialog() {
        try {
            showDetainOnceDialogSafe();
        } catch (Exception unused) {
            onError();
        }
    }

    private void showDetainOnceDialogSafe() {
        JDPrivacyUtil.dismissDialog(this.mJdOnceDialog);
        JDDialog createOnceDialogSafe = JDPrivacyUtil.createOnceDialogSafe(this.mCurrentActivity);
        this.mJdOnceDialog = createOnceDialogSafe;
        createOnceDialogSafe.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyManager.5
            {
                JDPrivacyManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDPrivacyUtil.dismissDialog(JDPrivacyManager.this.mJdOnceDialog);
                JDPrivacyManager.this.onClose(false);
            }
        });
        this.mJdOnceDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.privacy.JDPrivacyManager.6
            {
                JDPrivacyManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDPrivacyUtil.dismissDialog(JDPrivacyManager.this.mJdOnceDialog);
                JDPrivacyManager.this.safeAgreeAll();
                JDPrivacyUtil.sendMta("PrivacyPolicy_FirstAgree");
            }
        });
        this.mJdOnceDialog.show();
    }

    public boolean checkPrivacyDialog(Activity activity, PrivacyCallback privacyCallback) {
        if (JDPrivacyHelper.isAcceptPrivacy(activity)) {
            return false;
        }
        try {
            showCheckPrivacyDialogSafe(activity, privacyCallback);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public boolean isInAppAgree() {
        return sInAppAgreed;
    }

    public void openPrivacyDialog(boolean z, Activity activity, PrivacyCallback privacyCallback) {
        try {
            safeOpenPrivacyDialogSafe(z, activity, privacyCallback);
        } catch (Throwable unused) {
            onError();
        }
    }

    public void savePrivacy(boolean z) {
        agreePrivacy(false);
        if (z) {
            initAfterPrivacy();
        }
    }

    public void startAppWebActivity(Activity activity, String str) {
        DeepLinkMHelper.startWebActivity(activity, str);
    }

    public void startWebActivity(String str) {
        if (this.mCurrentActivity == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            this.mCurrentActivity.startActivity(intent);
            JDPrivacyUtil.sendMta("PrivacyPolicy_Policy");
        } catch (Throwable th) {
            th.printStackTrace();
            ToastUtils.shortToast(this.mCurrentActivity, R.string.privacy_error_browser);
        }
    }

    private JDPrivacyManager() {
    }
}
