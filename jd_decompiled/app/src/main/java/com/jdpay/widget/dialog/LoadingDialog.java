package com.jdpay.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.helper.ContextHelper;
import com.jdpay.widget.R;

/* loaded from: classes18.dex */
public class LoadingDialog extends BaseDialog {
    private ImageView imgIcon;
    private Animation operatingAnim;
    private TextView txtMessage;

    public LoadingDialog(Context context) {
        super(context, R.style.Theme_AppCompat_JDPay_Dialog_Transparent);
        this.imgIcon = null;
        this.txtMessage = null;
        initDialog(context);
    }

    private void initDialog(Context context) {
        setContentView(R.layout.jdpay_widget_dialog_loading);
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = 17;
        }
        this.imgIcon = (ImageView) findViewById(R.id.icon);
        this.txtMessage = (TextView) findViewById(R.id.message);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.jdpay_widget_anim_rotate);
        this.operatingAnim = loadAnimation;
        loadAnimation.setInterpolator(new LinearInterpolator());
    }

    @Override // com.jdpay.widget.dialog.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Activity activity = ContextHelper.getActivity(getContext());
        if (activity == null || activity.isFinishing()) {
            return;
        }
        super.dismiss();
        this.imgIcon.clearAnimation();
    }

    public LoadingDialog setMessage(CharSequence charSequence) {
        TextView textView = this.txtMessage;
        if (textView != null) {
            textView.setText(charSequence);
            this.txtMessage.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        }
        return this;
    }

    @Override // com.jdpay.widget.dialog.BaseDialog, android.app.Dialog
    public void show() {
        Activity activity = ContextHelper.getActivity(getContext());
        if (activity == null || activity.isFinishing()) {
            return;
        }
        super.show();
        this.imgIcon.clearAnimation();
        this.imgIcon.startAnimation(this.operatingAnim);
    }

    public LoadingDialog setMessage(int i2) {
        setMessage(getContext().getText(i2));
        return this;
    }

    public LoadingDialog(Context context, int i2) {
        super(context, i2);
        this.imgIcon = null;
        this.txtMessage = null;
        initDialog(context);
    }

    public LoadingDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.imgIcon = null;
        this.txtMessage = null;
        initDialog(context);
    }
}
