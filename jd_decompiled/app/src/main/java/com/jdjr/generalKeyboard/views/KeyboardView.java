package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public class KeyboardView extends FrameLayout {
    public View keyboardView;
    protected KeyboardViewCallback keyboardViewCallback;
    protected boolean showPressBg;

    /* loaded from: classes18.dex */
    public interface KeyboardViewCallback {
        void onHide(View view);

        void onKeyDelete(View view);

        void onKeyDeleteAll(View view);

        void onKeyInput(View view);

        void onSure(View view);
    }

    public KeyboardView(@NonNull Context context) {
        super(context);
        this.showPressBg = true;
    }

    public void setKeyboardViewCallback(KeyboardViewCallback keyboardViewCallback) {
        this.keyboardViewCallback = keyboardViewCallback;
    }

    public void setSureBackgroundResource(String str) {
    }

    public void setSureBackgroundResource(String str, String str2, String str3) {
    }

    public void setSureBackgroundResource(int... iArr) {
    }

    public void setSureEnabled(boolean z) {
    }

    public void setSureText(CharSequence charSequence) {
    }

    public void showAt(View view) {
        if (view == null || !(view instanceof ViewGroup)) {
            return;
        }
        int i2 = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i2 < viewGroup.getChildCount()) {
                viewGroup.removeView(viewGroup.getChildAt(i2));
                i2++;
            } else {
                view.setLayoutParams(view.getLayoutParams());
                viewGroup.addView(this.keyboardView);
                return;
            }
        }
    }

    public void showKeyPressBg(boolean z) {
        this.showPressBg = z;
    }

    public KeyboardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.showPressBg = true;
    }

    public KeyboardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.showPressBg = true;
    }
}
