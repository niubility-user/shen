package com.facebook.react.views.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import com.facebook.react.views.common.ViewHelper;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactViewBackgroundManager {
    @Nullable
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private View mView;

    public ReactViewBackgroundManager(View view) {
        this.mView = view;
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable(this.mView.getContext());
            Drawable background = this.mView.getBackground();
            ViewHelper.setBackground(this.mView, null);
            if (background == null) {
                ViewHelper.setBackground(this.mView, this.mReactBackgroundDrawable);
            } else {
                ViewHelper.setBackground(this.mView, new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
            }
        }
        return this.mReactBackgroundDrawable;
    }

    public void setBackgroundColor(int i2) {
        if (i2 == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        getOrCreateReactViewBackground().setColor(i2);
    }

    public void setBorderColor(int i2, float f2, float f3) {
        getOrCreateReactViewBackground().setBorderColor(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        getOrCreateReactViewBackground().setRadius(f2);
    }

    public void setBorderStyle(@Nullable String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setBorderWidth(int i2, float f2) {
        getOrCreateReactViewBackground().setBorderWidth(i2, f2);
    }

    public void setBorderRadius(float f2, int i2) {
        getOrCreateReactViewBackground().setRadius(f2, i2);
    }
}
