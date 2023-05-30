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
import com.jdjr.generalKeyboard.views.SixSquareInputItemView;

/* loaded from: classes18.dex */
public class SecuritySixSqaureInputLayoutBindingImpl extends SecuritySixSqaureInputLayoutBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final SixSquareInputItemView mboundView1;
    @NonNull
    private final SixSquareInputItemView mboundView2;
    @NonNull
    private final SixSquareInputItemView mboundView3;
    @NonNull
    private final SixSquareInputItemView mboundView4;
    @NonNull
    private final SixSquareInputItemView mboundView5;
    @NonNull
    private final SixSquareInputItemView mboundView6;

    public SecuritySixSqaureInputLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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
            SixSquareInputItemView sixSquareInputItemView = this.mboundView1;
            if (KeyboardUiMode.isDark()) {
                context = this.mboundView1.getContext();
                i2 = R.drawable.security_general_six_square_first_item_bg_dark;
            } else {
                context = this.mboundView1.getContext();
                i2 = R.drawable.security_general_six_square_first_item_bg;
            }
            ViewBindingAdapter.setBackground(sixSquareInputItemView, AppCompatResources.getDrawable(context, i2));
            SixSquareInputItemView sixSquareInputItemView2 = this.mboundView2;
            if (KeyboardUiMode.isDark()) {
                context2 = this.mboundView2.getContext();
                i3 = R.drawable.security_general_six_square_item_bg_dark;
            } else {
                context2 = this.mboundView2.getContext();
                i3 = R.drawable.security_general_six_square_item_bg;
            }
            ViewBindingAdapter.setBackground(sixSquareInputItemView2, AppCompatResources.getDrawable(context2, i3));
            SixSquareInputItemView sixSquareInputItemView3 = this.mboundView3;
            if (KeyboardUiMode.isDark()) {
                context3 = this.mboundView3.getContext();
                i4 = R.drawable.security_general_six_square_item_bg_dark;
            } else {
                context3 = this.mboundView3.getContext();
                i4 = R.drawable.security_general_six_square_item_bg;
            }
            ViewBindingAdapter.setBackground(sixSquareInputItemView3, AppCompatResources.getDrawable(context3, i4));
            SixSquareInputItemView sixSquareInputItemView4 = this.mboundView4;
            if (KeyboardUiMode.isDark()) {
                context4 = this.mboundView4.getContext();
                i5 = R.drawable.security_general_six_square_item_bg_dark;
            } else {
                context4 = this.mboundView4.getContext();
                i5 = R.drawable.security_general_six_square_item_bg;
            }
            ViewBindingAdapter.setBackground(sixSquareInputItemView4, AppCompatResources.getDrawable(context4, i5));
            SixSquareInputItemView sixSquareInputItemView5 = this.mboundView5;
            if (KeyboardUiMode.isDark()) {
                context5 = this.mboundView5.getContext();
                i6 = R.drawable.security_general_six_square_item_bg_dark;
            } else {
                context5 = this.mboundView5.getContext();
                i6 = R.drawable.security_general_six_square_item_bg;
            }
            ViewBindingAdapter.setBackground(sixSquareInputItemView5, AppCompatResources.getDrawable(context5, i6));
            SixSquareInputItemView sixSquareInputItemView6 = this.mboundView6;
            if (KeyboardUiMode.isDark()) {
                context6 = this.mboundView6.getContext();
                i7 = R.drawable.security_general_six_square_last_item_bg_dark;
            } else {
                context6 = this.mboundView6.getContext();
                i7 = R.drawable.security_general_six_square_last_item_bg;
            }
            ViewBindingAdapter.setBackground(sixSquareInputItemView6, AppCompatResources.getDrawable(context6, i7));
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

    private SecuritySixSqaureInputLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[0]);
        this.mDirtyFlags = -1L;
        SixSquareInputItemView sixSquareInputItemView = (SixSquareInputItemView) objArr[1];
        this.mboundView1 = sixSquareInputItemView;
        sixSquareInputItemView.setTag(null);
        SixSquareInputItemView sixSquareInputItemView2 = (SixSquareInputItemView) objArr[2];
        this.mboundView2 = sixSquareInputItemView2;
        sixSquareInputItemView2.setTag(null);
        SixSquareInputItemView sixSquareInputItemView3 = (SixSquareInputItemView) objArr[3];
        this.mboundView3 = sixSquareInputItemView3;
        sixSquareInputItemView3.setTag(null);
        SixSquareInputItemView sixSquareInputItemView4 = (SixSquareInputItemView) objArr[4];
        this.mboundView4 = sixSquareInputItemView4;
        sixSquareInputItemView4.setTag(null);
        SixSquareInputItemView sixSquareInputItemView5 = (SixSquareInputItemView) objArr[5];
        this.mboundView5 = sixSquareInputItemView5;
        sixSquareInputItemView5.setTag(null);
        SixSquareInputItemView sixSquareInputItemView6 = (SixSquareInputItemView) objArr[6];
        this.mboundView6 = sixSquareInputItemView6;
        sixSquareInputItemView6.setTag(null);
        this.sixInputEndit.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
