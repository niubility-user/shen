package com.jingdong.common.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class AbsDialogController implements DialogInterface.OnKeyListener {
    private static final String TAG = "AbsDialogController";
    protected Context activity;
    private boolean canBack = false;
    private boolean canceledOnTouchOutside = true;
    protected IDialog dialogImpl;
    private boolean hasSetView;
    private CharSequence initMessage;
    private CharSequence initNegativeButton;
    private CharSequence initNeutralButton;
    private CharSequence initPositiveButton;
    private CharSequence initTitle;
    private DialogInterface.OnCancelListener onCancelListener;
    private View view;

    /* loaded from: classes5.dex */
    public class AlertDialogImpl implements IDialog, DialogInterface.OnClickListener {
        private AbsDialogController absDialogController;
        protected AlertDialog alertDialog;
        protected AlertDialog.Builder builder;
        private DialogInterface.OnClickListener onNegativeClickListener;
        private DialogInterface.OnClickListener onPositiveClickListener;

        public AlertDialogImpl(AbsDialogController absDialogController) {
            this.absDialogController = absDialogController;
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
        public void createDialog(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, CharSequence charSequence5) {
            this.builder = new AlertDialog.Builder(context);
            if (!TextUtils.isEmpty(charSequence)) {
                this.builder.setTitle(charSequence);
            }
            if (!TextUtils.isEmpty(charSequence2)) {
                this.builder.setMessage(charSequence2);
            }
            if (AbsDialogController.this.view != null) {
                this.builder.setView(AbsDialogController.this.view);
            }
            this.builder.setOnKeyListener(this.absDialogController);
            if (AbsDialogController.this.onCancelListener != null) {
                this.builder.setOnCancelListener(AbsDialogController.this.onCancelListener);
            }
            if (!TextUtils.isEmpty(charSequence3)) {
                this.builder.setPositiveButton(charSequence3, this);
            }
            if (!TextUtils.isEmpty(charSequence4)) {
                this.builder.setNeutralButton(charSequence4, this);
            }
            if (TextUtils.isEmpty(charSequence5)) {
                return;
            }
            this.builder.setNegativeButton(charSequence5, this);
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
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

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            DialogInterface.OnClickListener onClickListener;
            if (i2 != -2) {
                if (i2 == -1 && (onClickListener = this.onPositiveClickListener) != null) {
                    onClickListener.onClick(dialogInterface, i2);
                    return;
                }
                return;
            }
            DialogInterface.OnClickListener onClickListener2 = this.onNegativeClickListener;
            if (onClickListener2 != null) {
                onClickListener2.onClick(dialogInterface, i2);
            }
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
        public void setMessage(CharSequence charSequence) {
            AlertDialog alertDialog = this.alertDialog;
            if (alertDialog != null) {
                alertDialog.setMessage(charSequence);
                return;
            }
            AlertDialog.Builder builder = this.builder;
            if (builder != null) {
                builder.setMessage(charSequence);
            }
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
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
                builder.setNegativeButton(AbsDialogController.this.initNegativeButton, this);
            }
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
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
                builder.setNeutralButton(AbsDialogController.this.initNeutralButton, this);
            }
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
        public void setOnNegativeClickListener(DialogInterface.OnClickListener onClickListener) {
            this.onNegativeClickListener = onClickListener;
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
        public void setOnPositiveClickListener(DialogInterface.OnClickListener onClickListener) {
            this.onPositiveClickListener = onClickListener;
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
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
            }
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
        public void setTitle(CharSequence charSequence) {
            AlertDialog alertDialog = this.alertDialog;
            if (alertDialog != null) {
                alertDialog.setTitle(charSequence);
                return;
            }
            AlertDialog.Builder builder = this.builder;
            if (builder != null) {
                builder.setTitle(charSequence);
            }
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
        public void setView(View view) {
            AlertDialog alertDialog = this.alertDialog;
            if (alertDialog != null) {
                alertDialog.setView(view);
                return;
            }
            AlertDialog.Builder builder = this.builder;
            if (builder != null) {
                builder.setView(view);
            }
        }

        @Override // com.jingdong.common.network.AbsDialogController.IDialog
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
                    if (Build.VERSION.SDK_INT >= 27 && !AbsDialogController.this.hasSetView) {
                        RelativeLayout relativeLayout = new RelativeLayout(AbsDialogController.this.activity);
                        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                        TextView textView = new TextView(AbsDialogController.this.activity);
                        textView.setHeight(1);
                        textView.setText("This is a trickView to avoid the bug on the 9.0 devices");
                        textView.setIncludeFontPadding(false);
                        textView.setTextColor(0);
                        textView.setBackgroundColor(0);
                        relativeLayout.addView(textView);
                        this.alertDialog.setView(relativeLayout, 0, 0, 0, 0);
                    }
                    this.alertDialog.show();
                    this.alertDialog.setCanceledOnTouchOutside(AbsDialogController.this.canceledOnTouchOutside);
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

    /* loaded from: classes.dex */
    public interface IDialog {
        void createDialog(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, CharSequence charSequence5);

        void dismiss();

        void setMessage(CharSequence charSequence);

        void setNegativeButton(CharSequence charSequence);

        void setNeutralButton(CharSequence charSequence);

        void setOnNegativeClickListener(DialogInterface.OnClickListener onClickListener);

        void setOnPositiveClickListener(DialogInterface.OnClickListener onClickListener);

        void setPositiveButton(CharSequence charSequence);

        void setTitle(CharSequence charSequence);

        void setView(View view);

        void show();
    }

    public void dismiss() {
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.dismiss();
        }
    }

    public void init(Context context, IDialog iDialog) {
        this.activity = context;
        this.dialogImpl = iDialog;
        if (iDialog == null) {
            this.dialogImpl = new AlertDialogImpl(this);
        }
        this.dialogImpl.createDialog(this.activity, this.initTitle, this.initMessage, this.initPositiveButton, this.initNeutralButton, this.initNegativeButton);
    }

    public boolean isCanBack() {
        return this.canBack;
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
        this.initMessage = charSequence;
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setMessage(charSequence);
        }
    }

    public void setNegativeButton(CharSequence charSequence) {
        this.initNegativeButton = charSequence;
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setNegativeButton(charSequence);
        }
    }

    public void setNeutralButton(CharSequence charSequence) {
        this.initNeutralButton = charSequence;
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setNeutralButton(charSequence);
        }
    }

    public void setOnNegativeClickListener(DialogInterface.OnClickListener onClickListener) {
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setOnNegativeClickListener(onClickListener);
        }
    }

    public void setOnPositiveClickListener(DialogInterface.OnClickListener onClickListener) {
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setOnPositiveClickListener(onClickListener);
        }
    }

    public void setPositiveButton(CharSequence charSequence) {
        this.initPositiveButton = charSequence;
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setPositiveButton(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.initTitle = charSequence;
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setTitle(charSequence);
        }
    }

    public void setView(View view) {
        this.view = view;
        this.hasSetView = true;
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.setView(view);
        }
    }

    public void show() {
        IDialog iDialog = this.dialogImpl;
        if (iDialog != null) {
            iDialog.show();
        }
    }
}
