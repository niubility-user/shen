package com.jingdong.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes6.dex */
public class CommonDialogController {
    private static final String TAG = "CommonDialogController";
    protected BaseActivity mActivity;
    protected DialogVisibilityListener mDialogShownListener;
    protected View.OnClickListener mLeftButtonClickListener;
    protected JDDialog mMainDialog;
    protected String mMsg;
    protected View.OnClickListener mRightButtonClickListener;
    protected String mTitle;

    /* loaded from: classes6.dex */
    public interface DialogVisibilityListener {
        void onDialogShown(Dialog dialog);
    }

    public CommonDialogController(Context context) {
        String str;
        if (context != null && (context instanceof BaseActivity)) {
            this.mActivity = (BaseActivity) context;
            return;
        }
        String simpleName = getClass().getSimpleName();
        if (this.mActivity == null) {
            str = "null==mActivity";
        } else {
            str = " mActivity: " + this.mActivity.getClass().toString();
        }
        ExceptionReporter.reportWebViewCommonError("CommonDialogController_Construct_Error", simpleName, str, "CommonDialogController  construct   context is not activity!");
    }

    protected boolean checkBaseActivityIsRunning() {
        BaseActivity baseActivity = this.mActivity;
        return (baseActivity == null || baseActivity.isFinishing()) ? false : true;
    }

    public void configData(String str, View.OnClickListener onClickListener) {
        configData(str, onClickListener, null);
    }

    protected boolean isDialogShowing(JDDialog jDDialog) {
        return jDDialog != null && jDDialog.isShowing();
    }

    protected void safelyDismissDialog(JDDialog jDDialog) {
        if (checkBaseActivityIsRunning() && isDialogShowing(jDDialog)) {
            jDDialog.dismiss();
        }
    }

    protected void safelyHideDialog(JDDialog jDDialog) {
        if (checkBaseActivityIsRunning() && isDialogShowing(jDDialog)) {
            jDDialog.hide();
        }
    }

    protected void safelyShowDialog(JDDialog jDDialog) {
        if (checkBaseActivityIsRunning()) {
            DialogVisibilityListener dialogVisibilityListener = this.mDialogShownListener;
            if (dialogVisibilityListener != null) {
                dialogVisibilityListener.onDialogShown(jDDialog);
            }
            jDDialog.show();
        }
    }

    protected void tryShowMainDialog() {
        safelyDismissDialog(this.mMainDialog);
        if (checkBaseActivityIsRunning()) {
            if (this.mMainDialog == null) {
                JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(this.mActivity, this.mMsg, "\u53d6\u6d88", "\u786e\u5b9a");
                this.mMainDialog = createJdDialogWithStyle2;
                createJdDialogWithStyle2.setCanceledOnTouchOutside(true);
                this.mMainDialog.setCancelable(true);
            }
            if (this.mRightButtonClickListener != null) {
                this.mMainDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.CommonDialogController.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CommonDialogController.this.mRightButtonClickListener.onClick(view);
                        CommonDialogController commonDialogController = CommonDialogController.this;
                        commonDialogController.safelyDismissDialog(commonDialogController.mMainDialog);
                    }
                });
            }
            if (this.mLeftButtonClickListener != null) {
                this.mMainDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.CommonDialogController.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CommonDialogController.this.mLeftButtonClickListener.onClick(view);
                        CommonDialogController commonDialogController = CommonDialogController.this;
                        commonDialogController.safelyDismissDialog(commonDialogController.mMainDialog);
                    }
                });
            }
            this.mMainDialog.setMessage(this.mMsg);
            safelyShowDialog(this.mMainDialog);
        }
    }

    public void configData(String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogVisibilityListener dialogVisibilityListener) {
        String str2;
        if (checkBaseActivityIsRunning()) {
            this.mMsg = str;
            this.mRightButtonClickListener = onClickListener;
            this.mLeftButtonClickListener = onClickListener2;
            this.mDialogShownListener = dialogVisibilityListener;
            tryShowMainDialog();
            return;
        }
        String simpleName = getClass().getSimpleName();
        if (this.mActivity == null) {
            str2 = "null==mActivity";
        } else {
            str2 = " mActivity: " + this.mActivity.getClass().toString() + "checkBaseActivityIsRunning(): " + checkBaseActivityIsRunning() + " null===m";
        }
        ExceptionReporter.reportWebViewCommonError("CommonDialogController_ConfigData_Error", simpleName, str2, "CommonDialogController  configData   errorhappend dialog not shown!");
    }

    public void configData(String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        configData(str, onClickListener, onClickListener2, null);
    }
}
