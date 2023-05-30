package com.jdjr.dns.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.jdjr.dns.BR;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class SecurityGeneralBasicKeyboardBindingImpl extends SecurityGeneralBasicKeyboardBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final FrameLayout mboundView0;

    public SecurityGeneralBasicKeyboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private boolean onChangeUiMode(KeyboardUiMode keyboardUiMode, int i2) {
        if (i2 == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        LinearLayout linearLayout;
        int i2;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j3 = j2 & 2;
        if (j3 != 0 && j3 != 0) {
            j2 |= KeyboardUiMode.isDark() ? 8L : 4L;
        }
        if ((j2 & 2) != 0) {
            LinearLayout linearLayout2 = this.llBasicKeyboard;
            if (KeyboardUiMode.isDark()) {
                linearLayout = this.llBasicKeyboard;
                i2 = R.color.keyboard_color_keyboard_bg_dark;
            } else {
                linearLayout = this.llBasicKeyboard;
                i2 = R.color.keyboard_color_keyboard_bg;
            }
            ViewBindingAdapter.setBackground(linearLayout2, Converters.convertColorToDrawable(ViewDataBinding.getColorFromResource(linearLayout, i2)));
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
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i2, Object obj, int i3) {
        if (i2 != 0) {
            return false;
        }
        return onChangeUiMode((KeyboardUiMode) obj, i3);
    }

    @Override // com.jdjr.dns.databinding.SecurityGeneralBasicKeyboardBinding
    public void setUiMode(@Nullable KeyboardUiMode keyboardUiMode) {
        this.mUiMode = keyboardUiMode;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (BR.uiMode == i2) {
            setUiMode((KeyboardUiMode) obj);
            return true;
        }
        return false;
    }

    private SecurityGeneralBasicKeyboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (LinearLayout) objArr[1]);
        this.mDirtyFlags = -1L;
        this.llBasicKeyboard.setTag(null);
        FrameLayout frameLayout = (FrameLayout) objArr[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
