package com.jingdong.discovertask.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.jingdong.common.R;
import com.jingdong.discovertask.model.inter.BeforeDialogDismiss;

/* loaded from: classes12.dex */
public class CustomDialog extends Dialog {
    public static final String TAG = "CustomDialog";
    private boolean cancel;
    private int gravity;
    private float height;
    private int inAnim;
    private Animation inAnimation;
    private BeforeDialogDismiss mBeforeDialogDismiss;
    private Context mContext;
    private int outAnim;
    private Animation outAnimation;
    private View view;
    private float width;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private boolean cancel;
        private Context context;
        private int gravity;
        private float height;
        private int inAnim;
        private BeforeDialogDismiss mBeforeDialogDismiss;
        private int outAnim;
        private int themeResId;
        private View view;
        private float width;

        public Builder(Context context) {
            this.themeResId = -1;
            this.context = context;
            this.themeResId = R.style.CustomDialogTheme;
        }

        public Builder addViewOnClick(int i2, View.OnClickListener onClickListener) {
            this.view.findViewById(i2).setOnClickListener(onClickListener);
            return this;
        }

        public Builder beforeDismiss(BeforeDialogDismiss beforeDialogDismiss) {
            this.mBeforeDialogDismiss = beforeDialogDismiss;
            return this;
        }

        public CustomDialog build() {
            return new CustomDialog(this);
        }

        public Builder cancel(boolean z) {
            this.cancel = z;
            return this;
        }

        public Builder gravity(int i2) {
            this.gravity = i2;
            return this;
        }

        public Builder handleView(int i2, OnHandleView onHandleView) {
            View view = this.view;
            if (view == null) {
                return this;
            }
            View findViewById = view.findViewById(i2);
            if (onHandleView != null) {
                onHandleView.onHandle(findViewById);
            }
            return this;
        }

        public Builder height(float f2) {
            this.height = f2;
            return this;
        }

        public Builder inAnim(int i2) {
            this.inAnim = i2;
            return this;
        }

        public Builder outAnim(int i2) {
            this.outAnim = i2;
            return this;
        }

        public Builder themeResId(int i2) {
            this.themeResId = i2;
            return this;
        }

        public Builder view(int i2) {
            this.view = LayoutInflater.from(this.context).inflate(i2, (ViewGroup) new FrameLayout(this.context), false);
            return this;
        }

        public Builder width(float f2) {
            this.width = f2;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public interface OnHandleView<T extends View> {
        void onHandle(T t);
    }

    private void measureContentView() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (this.height == 0.0f) {
            this.height = layoutParams.height;
        }
        if (this.width == 0.0f) {
            this.width = layoutParams.width;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realDismiss() {
        BeforeDialogDismiss beforeDialogDismiss = this.mBeforeDialogDismiss;
        if (beforeDialogDismiss != null) {
            beforeDialogDismiss.beforeDismiss(this.view);
        }
        Animation animation = this.outAnimation;
        if (animation != null) {
            animation.cancel();
            this.outAnimation = null;
        }
        Animation animation2 = this.inAnimation;
        if (animation2 != null) {
            animation2.cancel();
            this.inAnimation = null;
        }
        super.dismiss();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        int i2 = this.outAnim;
        if (i2 == 0 || this.outAnimation != null) {
            if (i2 == 0) {
                realDismiss();
                return;
            }
            return;
        }
        this.outAnimation = AnimationUtils.loadAnimation(this.mContext, i2);
        this.view.clearAnimation();
        this.view.setAnimation(this.outAnimation);
        this.outAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.discovertask.view.CustomDialog.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CustomDialog.this.outAnimation = null;
                CustomDialog.this.view.clearAnimation();
                CustomDialog.this.view.setVisibility(8);
                CustomDialog.this.realDismiss();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.outAnimation.start();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.view == null) {
            return;
        }
        measureContentView();
        setContentView(this.view);
        setCanceledOnTouchOutside(this.cancel);
        setCancelable(this.cancel);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            float f2 = this.height;
            if (f2 != 0.0f) {
                float f3 = this.width;
                if (f3 != 0.0f) {
                    attributes.height = (int) f2;
                    attributes.width = (int) f3;
                    window.setAttributes(attributes);
                }
            }
            window.setGravity(this.gravity);
            window.setAttributes(attributes);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        if (this.inAnim != 0) {
            this.view.setVisibility(0);
            this.inAnimation = AnimationUtils.loadAnimation(this.mContext, this.inAnim);
            this.view.clearAnimation();
            this.view.setAnimation(this.inAnimation);
            this.inAnimation.start();
        }
    }

    private CustomDialog(Builder builder) {
        super(builder.context, builder.themeResId);
        this.mContext = builder.context;
        this.view = builder.view;
        this.width = builder.width;
        this.height = builder.height;
        this.cancel = builder.cancel;
        this.inAnim = builder.inAnim;
        this.outAnim = builder.outAnim;
        this.gravity = builder.gravity;
        this.mBeforeDialogDismiss = builder.mBeforeDialogDismiss;
    }
}
