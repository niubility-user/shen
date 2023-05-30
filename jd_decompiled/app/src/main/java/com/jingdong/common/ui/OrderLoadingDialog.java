package com.jingdong.common.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.base.history.widget.R;

/* loaded from: classes6.dex */
public class OrderLoadingDialog extends Dialog {
    private AnimationDrawable mLoadingAnimation;
    private ImageView mOrderLoadingDialogDot;
    private TextView mOrderLoadingDialogTitle;

    public OrderLoadingDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        requestWindowFeature(1);
        setContentView(R.layout.order_loading_dialog);
        this.mOrderLoadingDialogTitle = (TextView) findViewById(R.id.orderLoadingDialogTitle);
        ImageView imageView = (ImageView) findViewById(R.id.orderLoadingDialogDot);
        this.mOrderLoadingDialogDot = imageView;
        this.mLoadingAnimation = (AnimationDrawable) imageView.getDrawable();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.mLoadingAnimation.stop();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
    }

    public void setTitle(String str) {
        this.mOrderLoadingDialogTitle.setText(str);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.mLoadingAnimation.start();
    }

    public OrderLoadingDialog(Context context, int i2) {
        super(context, i2);
        initView();
    }

    protected OrderLoadingDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initView();
    }
}
