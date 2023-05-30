package com.jingdong.common.sample.jshop.utils;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.utils.ImageUtil;

/* loaded from: classes6.dex */
public class JShopDynaDialogUtils {
    private final MyActivity mActivity;
    private String mCancelText;
    private String mConfirmText;
    private Dialog mDialog;
    private View mView;

    public JShopDynaDialogUtils(MyActivity myActivity) {
        this.mActivity = myActivity;
    }

    public void dismiss() {
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    public void showCancelDialog(IDialogClickListener iDialogClickListener) {
        showCancelDialog(iDialogClickListener, 0);
    }

    public void showCancelDialog(String str, String str2, IDialogClickListener iDialogClickListener) {
        this.mConfirmText = str;
        this.mCancelText = str2;
        showCancelDialog(iDialogClickListener, 0);
    }

    public void showCancelDialog(String str, String str2, int i2, IDialogClickListener iDialogClickListener) {
        this.mConfirmText = str;
        this.mCancelText = str2;
        showCancelDialog(iDialogClickListener, i2);
    }

    public void showCancelDialog(final IDialogClickListener iDialogClickListener, int i2) {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mActivity, R.style.f73tx);
        }
        if (this.mView == null) {
            this.mView = ImageUtil.inflate(this.mActivity, (int) R.layout.jshop_dynamic_cancel_concern_dialog, (ViewGroup) null);
        }
        final View findViewById = this.mView.findViewById(R.id.items_view);
        ((LinearLayout.LayoutParams) this.mView.findViewById(R.id.dialog_bg).getLayoutParams()).height = i2;
        this.mView.findViewById(R.id.dialog_bg_container).setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.sample.jshop.utils.JShopDynaDialogUtils.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                findViewById.startAnimation(AnimationUtils.loadAnimation(JShopDynaDialogUtils.this.mActivity, R.anim.jshop_out_from_bottom));
                JShopDynaDialogUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.utils.JShopDynaDialogUtils.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynaDialogUtils.this.mDialog.dismiss();
                    }
                }, 300);
                return true;
            }
        });
        TextView textView = (TextView) this.mView.findViewById(R.id.cancel_icon);
        if (!TextUtils.isEmpty(this.mConfirmText)) {
            textView.setText(this.mConfirmText);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.utils.JShopDynaDialogUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                findViewById.startAnimation(AnimationUtils.loadAnimation(JShopDynaDialogUtils.this.mActivity, R.anim.jshop_out_from_bottom));
                JShopDynaDialogUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.utils.JShopDynaDialogUtils.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynaDialogUtils.this.mDialog.dismiss();
                        IDialogClickListener iDialogClickListener2 = iDialogClickListener;
                        if (iDialogClickListener2 != null) {
                            iDialogClickListener2.onConfirm();
                        }
                    }
                }, 300);
            }
        });
        TextView textView2 = (TextView) this.mView.findViewById(R.id.back_icon);
        if (!TextUtils.isEmpty(this.mCancelText)) {
            textView2.setText(this.mCancelText);
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.utils.JShopDynaDialogUtils.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                findViewById.startAnimation(AnimationUtils.loadAnimation(JShopDynaDialogUtils.this.mActivity, R.anim.jshop_out_from_bottom));
                JShopDynaDialogUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.utils.JShopDynaDialogUtils.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynaDialogUtils.this.mDialog.dismiss();
                        IDialogClickListener iDialogClickListener2 = iDialogClickListener;
                        if (iDialogClickListener2 != null) {
                            iDialogClickListener2.onCancel();
                        }
                    }
                }, 300);
            }
        });
        this.mDialog.setCanceledOnTouchOutside(true);
        this.mDialog.setContentView(this.mView);
        this.mDialog.show();
        Window window = this.mDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.y = JShopUtil.getStatusBarHeight(this.mActivity);
        window.setAttributes(attributes);
        findViewById.startAnimation(AnimationUtils.loadAnimation(this.mActivity, R.anim.jshop_in_from_bottom));
    }
}
