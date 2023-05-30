package com.jdpay.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.jdpay.membercode.R;

/* loaded from: classes18.dex */
public class CPProgressDialog extends Dialog {
    private ImageView mImageView;
    private TextView mMsgTxt;
    private Animation operatingAnim;

    public CPProgressDialog(Context context) {
        super(context, R.style.CustomProgressDialog);
        this.mImageView = null;
        this.mMsgTxt = null;
        initDialog(context);
    }

    public CPProgressDialog(Context context, int i2) {
        super(context, i2);
        this.mImageView = null;
        this.mMsgTxt = null;
        initDialog(context);
    }

    private void initDialog(Context context) {
        setContentView(R.layout.jdpay_common_cp_progressdialog);
        getWindow().getAttributes().gravity = 17;
        this.mImageView = (ImageView) findViewById(R.id.loadingImageView);
        this.mMsgTxt = (TextView) findViewById(R.id.id_tv_loadingmsg);
        this.operatingAnim = AnimationUtils.loadAnimation(context, R.anim.jdpay_rotate);
        this.operatingAnim.setInterpolator(new LinearInterpolator());
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.mImageView.clearAnimation();
    }

    public CPProgressDialog setMessage(String str) {
        TextView textView = this.mMsgTxt;
        if (textView != null) {
            textView.setText(str);
        }
        return this;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.mImageView.clearAnimation();
        this.mImageView.startAnimation(this.operatingAnim);
    }
}
