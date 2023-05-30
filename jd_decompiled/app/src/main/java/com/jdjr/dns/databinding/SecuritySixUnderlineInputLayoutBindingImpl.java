package com.jdjr.dns.databinding;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.views.SixUnderlineInputItemView;

/* loaded from: classes18.dex */
public class SecuritySixUnderlineInputLayoutBindingImpl extends SecuritySixUnderlineInputLayoutBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final SixUnderlineInputItemView mboundView1;
    @NonNull
    private final SixUnderlineInputItemView mboundView2;
    @NonNull
    private final SixUnderlineInputItemView mboundView3;
    @NonNull
    private final SixUnderlineInputItemView mboundView4;
    @NonNull
    private final SixUnderlineInputItemView mboundView5;
    @NonNull
    private final SixUnderlineInputItemView mboundView6;

    public SecuritySixUnderlineInputLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        Context context;
        int i2;
        Context context2;
        int i3;
        Context context3;
        int i4;
        Context context4;
        int i5;
        Context context5;
        int i6;
        Context context6;
        int i7;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j5 = j2 & 1;
        if (j5 != 0 && j5 != 0) {
            if (KeyboardUiMode.isDark()) {
                j3 = j2 | 4 | 16 | 64 | 256 | 1024;
                j4 = 4096;
            } else {
                j3 = j2 | 2 | 8 | 32 | 128 | 512;
                j4 = 2048;
            }
            j2 = j3 | j4;
        }
        if ((j2 & 1) != 0) {
            SixUnderlineInputItemView sixUnderlineInputItemView = this.mboundView1;
            if (KeyboardUiMode.isDark()) {
                context = this.mboundView1.getContext();
                i2 = R.drawable.security_general_six_underline_item_bg_dark;
            } else {
                context = this.mboundView1.getContext();
                i2 = R.drawable.security_general_six_underline_item_bg;
            }
            ViewBindingAdapter.setBackground(sixUnderlineInputItemView, AppCompatResources.getDrawable(context, i2));
            SixUnderlineInputItemView sixUnderlineInputItemView2 = this.mboundView2;
            if (KeyboardUiMode.isDark()) {
                context2 = this.mboundView2.getContext();
                i3 = R.drawable.security_general_six_underline_item_bg_dark;
            } else {
                context2 = this.mboundView2.getContext();
                i3 = R.drawable.security_general_six_underline_item_bg;
            }
            ViewBindingAdapter.setBackground(sixUnderlineInputItemView2, AppCompatResources.getDrawable(context2, i3));
            SixUnderlineInputItemView sixUnderlineInputItemView3 = this.mboundView3;
            if (KeyboardUiMode.isDark()) {
                context3 = this.mboundView3.getContext();
                i4 = R.drawable.security_general_six_underline_item_bg_dark;
            } else {
                context3 = this.mboundView3.getContext();
                i4 = R.drawable.security_general_six_underline_item_bg;
            }
            ViewBindingAdapter.setBackground(sixUnderlineInputItemView3, AppCompatResources.getDrawable(context3, i4));
            SixUnderlineInputItemView sixUnderlineInputItemView4 = this.mboundView4;
            if (KeyboardUiMode.isDark()) {
                context4 = this.mboundView4.getContext();
                i5 = R.drawable.security_general_six_underline_item_bg_dark;
            } else {
                context4 = this.mboundView4.getContext();
                i5 = R.drawable.security_general_six_underline_item_bg;
            }
            ViewBindingAdapter.setBackground(sixUnderlineInputItemView4, AppCompatResources.getDrawable(context4, i5));
            SixUnderlineInputItemView sixUnderlineInputItemView5 = this.mboundView5;
            if (KeyboardUiMode.isDark()) {
                context5 = this.mboundView5.getContext();
                i6 = R.drawable.security_general_six_underline_item_bg_dark;
            } else {
                context5 = this.mboundView5.getContext();
                i6 = R.drawable.security_general_six_underline_item_bg;
            }
            ViewBindingAdapter.setBackground(sixUnderlineInputItemView5, AppCompatResources.getDrawable(context5, i6));
            SixUnderlineInputItemView sixUnderlineInputItemView6 = this.mboundView6;
            if (KeyboardUiMode.isDark()) {
                context6 = this.mboundView6.getContext();
                i7 = R.drawable.security_general_six_underline_item_bg_dark;
            } else {
                context6 = this.mboundView6.getContext();
                i7 = R.drawable.security_general_six_underline_item_bg;
            }
            ViewBindingAdapter.setBackground(sixUnderlineInputItemView6, AppCompatResources.getDrawable(context6, i7));
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

    private SecuritySixUnderlineInputLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[0]);
        this.mDirtyFlags = -1L;
        SixUnderlineInputItemView sixUnderlineInputItemView = (SixUnderlineInputItemView) objArr[1];
        this.mboundView1 = sixUnderlineInputItemView;
        sixUnderlineInputItemView.setTag(null);
        SixUnderlineInputItemView sixUnderlineInputItemView2 = (SixUnderlineInputItemView) objArr[2];
        this.mboundView2 = sixUnderlineInputItemView2;
        sixUnderlineInputItemView2.setTag(null);
        SixUnderlineInputItemView sixUnderlineInputItemView3 = (SixUnderlineInputItemView) objArr[3];
        this.mboundView3 = sixUnderlineInputItemView3;
        sixUnderlineInputItemView3.setTag(null);
        SixUnderlineInputItemView sixUnderlineInputItemView4 = (SixUnderlineInputItemView) objArr[4];
        this.mboundView4 = sixUnderlineInputItemView4;
        sixUnderlineInputItemView4.setTag(null);
        SixUnderlineInputItemView sixUnderlineInputItemView5 = (SixUnderlineInputItemView) objArr[5];
        this.mboundView5 = sixUnderlineInputItemView5;
        sixUnderlineInputItemView5.setTag(null);
        SixUnderlineInputItemView sixUnderlineInputItemView6 = (SixUnderlineInputItemView) objArr[6];
        this.mboundView6 = sixUnderlineInputItemView6;
        sixUnderlineInputItemView6.setTag(null);
        this.sixInputEndit.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
