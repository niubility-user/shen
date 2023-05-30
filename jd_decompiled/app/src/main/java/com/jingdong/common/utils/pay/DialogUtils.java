package com.jingdong.common.utils.pay;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class DialogUtils {
    private static final String TAG = "DialogUtils";
    private String allPayUrl;
    private CashDeskConfig cashDeskConfig;
    private DialogListener dialogListener;
    private JDDialog jdDialog;
    private JDDialog jdDialogWithMessage;
    private JDDialog jdDialogX;
    private String leftButtonText;
    private String message;
    private String rightButtonText;
    private String title;

    public DialogUtils(Context context, String str, DialogListener dialogListener) {
        this.dialogListener = dialogListener;
        this.leftButtonText = context.getResources().getString(R.string.dialog_continue_pay);
        this.rightButtonText = context.getResources().getString(R.string.dialog_cancel_pay);
        this.title = context.getResources().getString(R.string.dialog_title_pay);
        this.message = str;
        createJDDialogWithMessage(context);
    }

    private void createJDDialog(Context context) {
        JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(context, this.title, this.leftButtonText, this.rightButtonText);
        this.jdDialog = createJdDialogWithStyle2;
        createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.DialogUtils.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogUtils.this.jdDialog != null) {
                    if (DialogUtils.this.dialogListener != null) {
                        DialogUtils.this.dialogListener.doConfirm(DialogUtils.this.cashDeskConfig);
                    }
                    DialogUtils.this.jdDialog.dismiss();
                }
            }
        });
        this.jdDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.DialogUtils.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogUtils.this.jdDialog != null) {
                    if (DialogUtils.this.dialogListener != null) {
                        DialogUtils.this.dialogListener.doCancel();
                    }
                    DialogUtils.this.jdDialog.dismiss();
                }
            }
        });
    }

    private void createJDDialogWithMessage(Context context) {
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(context, this.title, this.message, this.leftButtonText, this.rightButtonText);
        this.jdDialogWithMessage = createJdDialogWithStyle6;
        createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.DialogUtils.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogUtils.this.jdDialogWithMessage != null) {
                    if (DialogUtils.this.dialogListener != null) {
                        DialogUtils.this.dialogListener.doConfirm(DialogUtils.this.cashDeskConfig);
                    }
                    DialogUtils.this.jdDialogWithMessage.dismiss();
                }
            }
        });
        this.jdDialogWithMessage.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.DialogUtils.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogUtils.this.jdDialogWithMessage != null) {
                    if (DialogUtils.this.dialogListener != null) {
                        DialogUtils.this.dialogListener.doCancel();
                    }
                    DialogUtils.this.jdDialogWithMessage.dismiss();
                }
            }
        });
    }

    private void createJDDialogX(Context context) {
        JDDialog createJdDialogWithStyle13 = JDDialogFactory.getInstance().createJdDialogWithStyle13(context, this.title, this.message, this.leftButtonText, this.rightButtonText);
        this.jdDialogX = createJdDialogWithStyle13;
        createJdDialogWithStyle13.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.DialogUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogUtils.this.jdDialogX != null) {
                    if (DialogUtils.this.dialogListener == null || !(DialogUtils.this.dialogListener instanceof DialogListenerX)) {
                        if (DialogUtils.this.dialogListener != null) {
                            DialogUtils.this.dialogListener.doCancel();
                        }
                    } else {
                        ((DialogListenerX) DialogUtils.this.dialogListener).doChangePayX(DialogUtils.this.allPayUrl);
                    }
                    DialogUtils.this.jdDialogX.dismiss();
                }
            }
        });
        this.jdDialogX.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.pay.DialogUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogUtils.this.jdDialogX != null) {
                    if (DialogUtils.this.dialogListener == null || !(DialogUtils.this.dialogListener instanceof DialogListenerX)) {
                        if (DialogUtils.this.dialogListener != null) {
                            DialogUtils.this.dialogListener.doConfirm(DialogUtils.this.cashDeskConfig);
                        }
                    } else {
                        ((DialogListenerX) DialogUtils.this.dialogListener).doCancelPayX(DialogUtils.this.cashDeskConfig);
                    }
                    DialogUtils.this.jdDialogX.dismiss();
                }
            }
        });
    }

    public void dismiss() {
        try {
            JDDialog jDDialog = this.jdDialogWithMessage;
            if (jDDialog != null && jDDialog.isShowing()) {
                this.jdDialogWithMessage.dismiss();
            }
            JDDialog jDDialog2 = this.jdDialog;
            if (jDDialog2 == null || !jDDialog2.isShowing()) {
                return;
            }
            this.jdDialog.dismiss();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public boolean showDialog() {
        JDDialog jDDialog = this.jdDialog;
        if (jDDialog == null || jDDialog.isShowing()) {
            return false;
        }
        this.jdDialog.show();
        return true;
    }

    public boolean showDialogWithMessage(String str) {
        this.message = str;
        JDDialog jDDialog = this.jdDialogWithMessage;
        if (jDDialog != null) {
            jDDialog.setMessage(str);
            if (!this.jdDialogWithMessage.isShowing()) {
                this.jdDialogWithMessage.show();
            }
            return true;
        }
        return false;
    }

    public boolean showDialogX(String str) {
        this.message = str;
        JDDialog jDDialog = this.jdDialogX;
        if (jDDialog != null) {
            jDDialog.setMessage(str);
            if (!this.jdDialogX.isShowing()) {
                this.jdDialogX.show();
            }
            return true;
        }
        return false;
    }

    public DialogUtils(Context context, CashDeskConfig cashDeskConfig, String str, DialogListener dialogListener) {
        this.dialogListener = dialogListener;
        this.cashDeskConfig = cashDeskConfig;
        if (!TextUtils.isEmpty(cashDeskConfig.title)) {
            this.title = cashDeskConfig.title;
        } else {
            this.title = context.getResources().getString(R.string.dialog_title_pay);
        }
        if (!TextUtils.isEmpty(cashDeskConfig.rightBtn)) {
            this.rightButtonText = cashDeskConfig.rightBtn;
        } else {
            this.rightButtonText = context.getResources().getString(R.string.dialog_cancel_pay);
        }
        if (!TextUtils.isEmpty(cashDeskConfig.leftBtn)) {
            this.leftButtonText = cashDeskConfig.leftBtn;
        } else {
            this.leftButtonText = context.getResources().getString(R.string.dialog_continue_pay);
        }
        this.message = str;
        createJDDialogWithMessage(context);
    }

    public DialogUtils(Context context, DialogListener dialogListener) {
        this.dialogListener = dialogListener;
        this.leftButtonText = context.getResources().getString(R.string.dialog_continue_pay);
        this.rightButtonText = context.getResources().getString(R.string.dialog_cancel_pay);
        this.title = context.getResources().getString(R.string.dialog_title_pay);
        createJDDialog(context);
    }

    public DialogUtils(Context context, CashDeskConfig cashDeskConfig, String str, DialogListener dialogListener, String str2) {
        this.dialogListener = dialogListener;
        this.cashDeskConfig = cashDeskConfig;
        if (!TextUtils.isEmpty(cashDeskConfig.title)) {
            this.title = cashDeskConfig.title;
        } else {
            this.title = context.getResources().getString(R.string.dialog_title_X);
        }
        if (!TextUtils.isEmpty(cashDeskConfig.rightBtn)) {
            this.rightButtonText = cashDeskConfig.rightBtn;
        } else {
            this.rightButtonText = context.getResources().getString(R.string.dialog_cancel_pay_X);
        }
        if (!TextUtils.isEmpty(cashDeskConfig.leftBtn)) {
            this.leftButtonText = cashDeskConfig.leftBtn;
        } else {
            this.leftButtonText = context.getResources().getString(R.string.dialog_change_pay_X);
        }
        this.allPayUrl = str2;
        createJDDialogX(context);
    }
}
