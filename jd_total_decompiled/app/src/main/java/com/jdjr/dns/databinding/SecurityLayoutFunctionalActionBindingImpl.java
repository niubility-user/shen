package com.jdjr.dns.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class SecurityLayoutFunctionalActionBindingImpl extends SecurityLayoutFunctionalActionBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public SecurityLayoutFunctionalActionBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        TextView textView;
        int i2;
        TextView textView2;
        int i3;
        TextView textView3;
        int i4;
        View view;
        int i5;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j5 = j2 & 1;
        if (j5 != 0 && j5 != 0) {
            if (KeyboardUiMode.isDark()) {
                j3 = j2 | 4 | 16 | 64;
                j4 = 256;
            } else {
                j3 = j2 | 2 | 8 | 32;
                j4 = 128;
            }
            j2 = j3 | j4;
        }
        if ((j2 & 1) != 0) {
            TextView textView4 = this.tvActionLeft;
            if (KeyboardUiMode.isDark()) {
                textView = this.tvActionLeft;
                i2 = R.color.keyboard_color_action_text_dark;
            } else {
                textView = this.tvActionLeft;
                i2 = R.color.keyboard_color_action_text;
            }
            textView4.setTextColor(ViewDataBinding.getColorFromResource(textView, i2));
            TextView textView5 = this.tvActionMiddle;
            if (KeyboardUiMode.isDark()) {
                textView2 = this.tvActionMiddle;
                i3 = R.color.keyboard_color_action_text_dark;
            } else {
                textView2 = this.tvActionMiddle;
                i3 = R.color.keyboard_color_action_text;
            }
            textView5.setTextColor(ViewDataBinding.getColorFromResource(textView2, i3));
            TextView textView6 = this.tvActionRight;
            if (KeyboardUiMode.isDark()) {
                textView3 = this.tvActionRight;
                i4 = R.color.keyboard_color_action_text_dark;
            } else {
                textView3 = this.tvActionRight;
                i4 = R.color.keyboard_color_action_text;
            }
            textView6.setTextColor(ViewDataBinding.getColorFromResource(textView3, i4));
            View view2 = this.vVerticalLine;
            if (KeyboardUiMode.isDark()) {
                view = this.vVerticalLine;
                i5 = R.color.keyboard_color_action_text_dark;
            } else {
                view = this.vVerticalLine;
                i5 = R.color.color_D8D8D8;
            }
            ViewBindingAdapter.setBackground(view2, Converters.convertColorToDrawable(ViewDataBinding.getColorFromResource(view, i5)));
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

    private SecurityLayoutFunctionalActionBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (RelativeLayout) objArr[0], (TextView) objArr[1], (TextView) objArr[4], (TextView) objArr[2], (View) objArr[3]);
        this.mDirtyFlags = -1L;
        this.llActions.setTag(null);
        this.tvActionLeft.setTag(null);
        this.tvActionMiddle.setTag(null);
        this.tvActionRight.setTag(null);
        this.vVerticalLine.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
