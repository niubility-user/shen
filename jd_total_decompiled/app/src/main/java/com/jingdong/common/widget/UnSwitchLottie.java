package com.jingdong.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* loaded from: classes12.dex */
public class UnSwitchLottie extends FrameLayout implements Checkable {
    private ImageView imageView;
    private UnLottieAnimationView lottieView;
    private boolean mChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    /* loaded from: classes12.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(UnSwitchLottie unSwitchLottie, boolean z);
    }

    public UnSwitchLottie(Context context) {
        super(context);
    }

    private boolean lottieEnable() {
        return true;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.mChecked);
    }

    public UnSwitchLottie(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UnSwitchLottie(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
