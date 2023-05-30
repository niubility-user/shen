package com.jingdong.discovertask.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;

/* loaded from: classes12.dex */
public class CustomPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener {
    public static final String TAG = "CustomPopupWindow";
    private int animStyle;
    private boolean cancel;
    private Context context;
    private int gravity;
    private int height;
    private View view;
    private int width;

    /* loaded from: classes12.dex */
    public static class Builder {
        private int animStyle;
        private boolean cancel;
        private Context context;
        private int gravity;
        private int height;
        private View view;
        private int width;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder addAnim(int i2) {
            this.animStyle = i2;
            return this;
        }

        public Builder addViewOnClick(int i2, View.OnClickListener onClickListener) {
            this.view.findViewById(i2).setOnClickListener(onClickListener);
            return this;
        }

        public CustomPopupWindow build() {
            return new CustomPopupWindow(this);
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

        public Builder height(int i2) {
            this.height = i2;
            return this;
        }

        public Builder view(int i2) {
            this.view = LayoutInflater.from(this.context).inflate(i2, (ViewGroup) new FrameLayout(this.context), false);
            return this;
        }

        public Builder width(int i2) {
            this.width = i2;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public interface OnHandleView {
        void onHandle(View view);
    }

    public CustomPopupWindow(Builder builder) {
        super(builder.view, builder.width, builder.height);
        this.view = builder.view;
        this.width = builder.width;
        this.height = builder.height;
        this.cancel = builder.cancel;
        this.animStyle = builder.animStyle;
        this.gravity = builder.gravity;
        setTouchable(this.cancel);
        setOutsideTouchable(this.cancel);
        setBackgroundDrawable(new BitmapDrawable((Resources) null, (Bitmap) null));
        setAnimationStyle(this.animStyle);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        Context context = this.context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity != null) {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.alpha = 1.0f;
                activity.getWindow().setAttributes(attributes);
            }
            super.dismiss();
        }
    }

    public void dismissAlpha() {
        dismiss();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        Context context = this.context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.alpha = 1.0f;
            activity.getWindow().setAttributes(attributes);
        }
    }

    @Override // android.widget.PopupWindow
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        super.setOnDismissListener(onDismissListener);
    }

    public void showAtCenter() {
        Context context = this.context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.alpha = 0.7f;
            activity.getWindow().setAttributes(attributes);
            setOnDismissListener(this);
            setTouchable(true);
            setOutsideTouchable(true);
            setBackgroundDrawable(new BitmapDrawable((Resources) null, (Bitmap) null));
            setAnimationStyle(R.style.popup_window_center);
            showAtLocation(this.view, 17, 0, 0);
        }
    }

    public void showAtViewBottom(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Context context = this.context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            showAtLocation(view, 0, (DpiUtil.getAppWidth(activity) - getWidth()) / 2, iArr[1] + view.getHeight());
            if (activity != null) {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.alpha = 0.7f;
                activity.getWindow().setAttributes(attributes);
            }
        }
    }

    public void showFromBottom() {
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable((Resources) null, (Bitmap) null));
        setAnimationStyle(R.style.popup_window_from_bottom);
        showAtLocation(this.view, 80, 0, 0);
    }

    public void showFromBottomAlpha() {
        Context context = this.context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.alpha = 0.7f;
            activity.getWindow().setAttributes(attributes);
            setOnDismissListener(this);
            showFromBottom();
        }
    }

    public void showFromTop() {
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable((Resources) null, (Bitmap) null));
        setAnimationStyle(R.style.popup_window_from_top);
        showAtLocation(this.view, 48, 0, 0);
    }

    public void showFromTopAlpha() {
        Context context = this.context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.alpha = 0.7f;
            activity.getWindow().setAttributes(attributes);
            setOnDismissListener(this);
            showFromTop();
        }
    }

    public void showFromBottom(boolean z) {
        setTouchable(true);
        if (z) {
            setOutsideTouchable(true);
            setBackgroundDrawable(new BitmapDrawable((Resources) null, (Bitmap) null));
        } else {
            setOutsideTouchable(false);
        }
        setAnimationStyle(R.style.popup_window_from_bottom);
        showAtLocation(this.view, 80, 0, 0);
    }

    public void showAtViewBottom(View view, int i2) {
        showAtViewBottom(view, i2, 0);
    }

    public void showAtViewBottom(View view, int i2, int i3) {
        showAtViewBottom(view, i2, 0, i3);
    }

    public void showAtViewBottom(View view, int i2, int i3, int i4) {
        Activity activity;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (i4 != 0) {
            setAnimationStyle(i4);
        }
        showAtLocation(view, 0, i2, iArr[1] + view.getHeight() + i3);
        Context context = this.context;
        if (!(context instanceof Activity) || (activity = (Activity) context) == null) {
            return;
        }
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.alpha = 0.7f;
        activity.getWindow().setAttributes(attributes);
    }
}
