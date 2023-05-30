package com.jdjr.dns.databinding;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class SecurityCompomentVerifyCodeEdittextBindingImpl extends SecurityCompomentVerifyCodeEdittextBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public SecurityCompomentVerifyCodeEdittextBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        Context context;
        int i2;
        Button button;
        int i3;
        EditText editText;
        int i4;
        EditText editText2;
        int i5;
        Context context2;
        int i6;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j5 = j2 & 1;
        if (j5 != 0 && j5 != 0) {
            if (KeyboardUiMode.isDark()) {
                j3 = j2 | 4 | 16 | 64 | 256;
                j4 = 1024;
            } else {
                j3 = j2 | 2 | 8 | 32 | 128;
                j4 = 512;
            }
            j2 = j3 | j4;
        }
        if ((j2 & 1) != 0) {
            Button button2 = this.btnCountdown;
            if (KeyboardUiMode.isDark()) {
                context = this.btnCountdown.getContext();
                i2 = R.drawable.security_verify_code_button_gray_bg_dark;
            } else {
                context = this.btnCountdown.getContext();
                i2 = R.drawable.security_verify_code_button_gray_bg;
            }
            ViewBindingAdapter.setBackground(button2, AppCompatResources.getDrawable(context, i2));
            Button button3 = this.btnCountdown;
            if (KeyboardUiMode.isDark()) {
                button = this.btnCountdown;
                i3 = R.color.keyboard_color_countdown_btn_txt_dark;
            } else {
                button = this.btnCountdown;
                i3 = R.color.keyboard_color_countdown_btn_txt;
            }
            button3.setTextColor(ViewDataBinding.getColorFromResource(button, i3));
            EditText editText3 = this.etVerifyCode;
            if (KeyboardUiMode.isDark()) {
                editText = this.etVerifyCode;
                i4 = R.color.keyboard_color_input_text_dark;
            } else {
                editText = this.etVerifyCode;
                i4 = R.color.keyboard_color_input_text;
            }
            editText3.setTextColor(ViewDataBinding.getColorFromResource(editText, i4));
            EditText editText4 = this.etVerifyCode;
            if (KeyboardUiMode.isDark()) {
                editText2 = this.etVerifyCode;
                i5 = R.color.keyboard_color_input_text_dark;
            } else {
                editText2 = this.etVerifyCode;
                i5 = R.color.color_FFDADADA;
            }
            editText4.setHintTextColor(ViewDataBinding.getColorFromResource(editText2, i5));
            RelativeLayout relativeLayout = this.rlVerifyContainer;
            if (KeyboardUiMode.isDark()) {
                context2 = this.rlVerifyContainer.getContext();
                i6 = R.drawable.security_verify_code_edittext_bg_dark;
            } else {
                context2 = this.rlVerifyContainer.getContext();
                i6 = R.drawable.security_verify_code_edittext_bg;
            }
            ViewBindingAdapter.setBackground(relativeLayout, AppCompatResources.getDrawable(context2, i6));
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    private SecurityCompomentVerifyCodeEdittextBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[1], (EditText) objArr[2], (RelativeLayout) objArr[0]);
        this.mDirtyFlags = -1L;
        this.btnCountdown.setTag(null);
        this.etVerifyCode.setTag(null);
        this.rlVerifyContainer.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
