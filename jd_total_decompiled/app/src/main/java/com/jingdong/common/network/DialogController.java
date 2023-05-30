package com.jingdong.common.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DialogController implements DialogInterface.OnClickListener, DialogInterface.OnKeyListener {
    private static final String TAG = "DialogController";
    protected Context activity;
    protected AlertDialog alertDialog;
    protected AlertDialog.Builder builder;
    private boolean canBack = false;
    private boolean canceledOnTouchOutside = true;
    private boolean hasSetView;
    private CharSequence initMessage;
    private CharSequence initNegativeButton;
    private CharSequence initNeutralButton;
    private CharSequence initPositiveButton;
    private CharSequence initTitle;
    private DialogInterface.OnCancelListener onCancelListener;
    private View view;

    public static DialogController getSimpleDialogController(Context context, String[] strArr, int i2, DialogInterface.OnClickListener onClickListener) {
        DialogController dialogController = new DialogController();
        dialogController.setCanBack(true);
        dialogController.init(context);
        dialogController.builder.setSingleChoiceItems(strArr, i2, onClickListener).create();
        return dialogController;
    }

    public void dismiss() {
        try {
            AlertDialog alertDialog = this.alertDialog;
            if (alertDialog == null || !alertDialog.isShowing()) {
                return;
            }
            this.alertDialog.dismiss();
            this.alertDialog = null;
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
            }
        }
    }

    public Button getButton(int i2) {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            return alertDialog.getButton(i2);
        }
        return null;
    }

    public DialogInterface.OnCancelListener getOnCancelListener() {
        return this.onCancelListener;
    }

    public void init(Context context) {
        this.builder = new AlertDialog.Builder(context);
        this.activity = context;
        initContent();
        initButton();
    }

    protected void initButton() {
        if (!TextUtils.isEmpty(this.initPositiveButton)) {
            this.builder.setPositiveButton(this.initPositiveButton, this);
        }
        if (!TextUtils.isEmpty(this.initNeutralButton)) {
            this.builder.setNeutralButton(this.initNeutralButton, this);
        }
        if (TextUtils.isEmpty(this.initNegativeButton)) {
            return;
        }
        this.builder.setNegativeButton(this.initNegativeButton, this);
    }

    protected void initContent() {
        if (!TextUtils.isEmpty(this.initTitle)) {
            this.builder.setTitle(this.initTitle);
        }
        if (!TextUtils.isEmpty(this.initMessage)) {
            this.builder.setMessage(this.initMessage);
        }
        View view = this.view;
        if (view != null) {
            this.builder.setView(view);
        }
        this.builder.setOnKeyListener(this);
        DialogInterface.OnCancelListener onCancelListener = this.onCancelListener;
        if (onCancelListener != null) {
            this.builder.setOnCancelListener(onCancelListener);
        }
    }

    public boolean isCanBack() {
        return this.canBack;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        return !isCanBack() && 4 == i2;
    }

    public void setCanBack(boolean z) {
        this.canBack = z;
    }

    public void setCanceledOnTouchOutside(boolean z) {
        this.canceledOnTouchOutside = z;
    }

    public void setMessage(CharSequence charSequence) {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.setMessage(charSequence);
            return;
        }
        AlertDialog.Builder builder = this.builder;
        if (builder != null) {
            builder.setMessage(charSequence);
        } else {
            this.initMessage = charSequence;
        }
    }

    public void setNegativeButton(CharSequence charSequence) {
        if (this.alertDialog != null) {
            if (TextUtils.isEmpty(charSequence)) {
                this.alertDialog.getButton(-2).setVisibility(8);
                return;
            } else {
                this.alertDialog.setButton(-2, charSequence, this);
                return;
            }
        }
        AlertDialog.Builder builder = this.builder;
        if (builder != null) {
            builder.setNegativeButton(this.initNegativeButton, this);
        } else {
            this.initNegativeButton = charSequence;
        }
    }

    public void setNeutralButton(CharSequence charSequence) {
        if (this.alertDialog != null) {
            if (TextUtils.isEmpty(charSequence)) {
                this.alertDialog.getButton(-3).setVisibility(8);
                return;
            } else {
                this.alertDialog.setButton(-3, charSequence, this);
                return;
            }
        }
        AlertDialog.Builder builder = this.builder;
        if (builder != null) {
            builder.setNeutralButton(this.initNeutralButton, this);
        } else {
            this.initNeutralButton = charSequence;
        }
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public void setPositiveButton(CharSequence charSequence) {
        if (this.alertDialog != null) {
            if (TextUtils.isEmpty(charSequence)) {
                this.alertDialog.getButton(-1).setVisibility(8);
                return;
            } else {
                this.alertDialog.setButton(-1, charSequence, this);
                return;
            }
        }
        AlertDialog.Builder builder = this.builder;
        if (builder != null) {
            builder.setPositiveButton(charSequence, this);
        } else {
            this.initPositiveButton = charSequence;
        }
    }

    public void setTitle(CharSequence charSequence) {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.setTitle(charSequence);
            return;
        }
        AlertDialog.Builder builder = this.builder;
        if (builder != null) {
            builder.setTitle(charSequence);
        } else {
            this.initTitle = charSequence;
        }
    }

    public void setView(View view) {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.setView(view);
        } else {
            AlertDialog.Builder builder = this.builder;
            if (builder != null) {
                builder.setView(view);
            } else {
                this.view = view;
            }
        }
        this.hasSetView = true;
    }

    public void show() {
        try {
            AlertDialog alertDialog = this.alertDialog;
            if (alertDialog != null) {
                alertDialog.show();
                return;
            }
            AlertDialog.Builder builder = this.builder;
            if (builder != null) {
                this.alertDialog = builder.create();
                if (Build.VERSION.SDK_INT >= 27 && !this.hasSetView) {
                    RelativeLayout relativeLayout = new RelativeLayout(this.activity);
                    relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                    TextView textView = new TextView(this.activity);
                    textView.setHeight(1);
                    textView.setText("This is a trickView to avoid the bug on the 9.0 devices");
                    textView.setIncludeFontPadding(false);
                    textView.setTextColor(0);
                    textView.setBackgroundColor(0);
                    relativeLayout.addView(textView);
                    this.alertDialog.setView(relativeLayout, 0, 0, 0, 0);
                }
                this.alertDialog.show();
                this.alertDialog.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
                return;
            }
            throw new RuntimeException("builder is null, need init this controller");
        } catch (Throwable th) {
            if (OKLog.E) {
                th.printStackTrace();
            }
        }
    }
}
