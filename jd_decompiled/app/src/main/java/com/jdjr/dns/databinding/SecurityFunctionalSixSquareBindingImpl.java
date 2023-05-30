package com.jdjr.dns.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.views.SixInputLayout;

/* loaded from: classes18.dex */
public class SecurityFunctionalSixSquareBindingImpl extends SecurityFunctionalSixSquareBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @Nullable
    private final SecurityLayoutFunctionalActionBinding mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"security_layout_functional_action"}, new int[]{2}, new int[]{R.layout.security_layout_functional_action});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.fl_input, 3);
        sparseIntArray.put(R.id.six_input, 4);
    }

    public SecurityFunctionalSixSquareBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        TextView textView;
        int i2;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j3 = j2 & 1;
        if (j3 != 0 && j3 != 0) {
            j2 |= KeyboardUiMode.isDark() ? 4L : 2L;
        }
        if ((j2 & 1) != 0) {
            TextView textView2 = this.tvTips;
            if (KeyboardUiMode.isDark()) {
                textView = this.tvTips;
                i2 = R.color.keyboard_color_subtitle_dark;
            } else {
                textView = this.tvTips;
                i2 = R.color.keyboard_color_subtitle;
            }
            textView2.setTextColor(ViewDataBinding.getColorFromResource(textView, i2));
        }
        ViewDataBinding.executeBindingsOn(this.mboundView0);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        this.mboundView0.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    private SecurityFunctionalSixSquareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (FrameLayout) objArr[3], (LinearLayout) objArr[0], (SixInputLayout) objArr[4], (TextView) objArr[1]);
        this.mDirtyFlags = -1L;
        this.generalKeyboardTop.setTag(null);
        SecurityLayoutFunctionalActionBinding securityLayoutFunctionalActionBinding = (SecurityLayoutFunctionalActionBinding) objArr[2];
        this.mboundView0 = securityLayoutFunctionalActionBinding;
        setContainedBinding(securityLayoutFunctionalActionBinding);
        this.tvTips.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
