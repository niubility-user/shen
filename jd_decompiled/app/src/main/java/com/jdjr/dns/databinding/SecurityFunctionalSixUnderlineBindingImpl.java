package com.jdjr.dns.databinding;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.views.SixInputLayout;

/* loaded from: classes18.dex */
public class SecurityFunctionalSixUnderlineBindingImpl extends SecurityFunctionalSixUnderlineBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @Nullable
    private final SecurityLayoutFunctionalUnderlineActionBinding mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"security_layout_functional_underline_action"}, new int[]{2}, new int[]{R.layout.security_layout_functional_underline_action});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.fl_input, 3);
        sparseIntArray.put(R.id.six_input, 4);
    }

    public SecurityFunctionalSixUnderlineBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        Context context;
        int i2;
        TextView textView;
        int i3;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j5 = j2 & 1;
        if (j5 != 0 && j5 != 0) {
            if (KeyboardUiMode.isDark()) {
                j3 = j2 | 4;
                j4 = 16;
            } else {
                j3 = j2 | 2;
                j4 = 8;
            }
            j2 = j3 | j4;
        }
        if ((j2 & 1) != 0) {
            LinearLayout linearLayout = this.generalKeyboardTop;
            if (KeyboardUiMode.isDark()) {
                context = this.generalKeyboardTop.getContext();
                i2 = R.drawable.security_general_corner_bg_dark;
            } else {
                context = this.generalKeyboardTop.getContext();
                i2 = R.drawable.security_general_corner_bg;
            }
            ViewBindingAdapter.setBackground(linearLayout, AppCompatResources.getDrawable(context, i2));
            TextView textView2 = this.tvTips;
            if (KeyboardUiMode.isDark()) {
                textView = this.tvTips;
                i3 = R.color.keyboard_color_subtitle_dark;
            } else {
                textView = this.tvTips;
                i3 = R.color.keyboard_color_subtitle;
            }
            textView2.setTextColor(ViewDataBinding.getColorFromResource(textView, i3));
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

    private SecurityFunctionalSixUnderlineBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (FrameLayout) objArr[3], (LinearLayout) objArr[0], (SixInputLayout) objArr[4], (TextView) objArr[1]);
        this.mDirtyFlags = -1L;
        this.generalKeyboardTop.setTag(null);
        SecurityLayoutFunctionalUnderlineActionBinding securityLayoutFunctionalUnderlineActionBinding = (SecurityLayoutFunctionalUnderlineActionBinding) objArr[2];
        this.mboundView0 = securityLayoutFunctionalUnderlineActionBinding;
        setContainedBinding(securityLayoutFunctionalUnderlineActionBinding);
        this.tvTips.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
