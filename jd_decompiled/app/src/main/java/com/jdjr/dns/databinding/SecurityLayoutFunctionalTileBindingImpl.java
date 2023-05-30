package com.jdjr.dns.databinding;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class SecurityLayoutFunctionalTileBindingImpl extends SecurityLayoutFunctionalTileBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public SecurityLayoutFunctionalTileBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        Context context;
        int i2;
        Context context2;
        int i3;
        TextView textView;
        int i4;
        TextView textView2;
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
            Button button = this.btnBack;
            if (KeyboardUiMode.isDark()) {
                context = this.btnBack.getContext();
                i2 = R.drawable.security_keyboard_back_icon_dark;
            } else {
                context = this.btnBack.getContext();
                i2 = R.drawable.security_keyboard_back_icon;
            }
            ViewBindingAdapter.setBackground(button, AppCompatResources.getDrawable(context, i2));
            Button button2 = this.btnClose;
            if (KeyboardUiMode.isDark()) {
                context2 = this.btnClose.getContext();
                i3 = R.drawable.security_keyboard_close_icon_dark;
            } else {
                context2 = this.btnClose.getContext();
                i3 = R.drawable.security_keyboard_close_icon;
            }
            ViewBindingAdapter.setBackground(button2, AppCompatResources.getDrawable(context2, i3));
            TextView textView3 = this.tvTitle;
            if (KeyboardUiMode.isDark()) {
                textView = this.tvTitle;
                i4 = R.color.keyboard_color_title_bg_dark;
            } else {
                textView = this.tvTitle;
                i4 = R.color.keyboard_color_title_bg;
            }
            ViewBindingAdapter.setBackground(textView3, Converters.convertColorToDrawable(ViewDataBinding.getColorFromResource(textView, i4)));
            TextView textView4 = this.tvTitle;
            if (KeyboardUiMode.isDark()) {
                textView2 = this.tvTitle;
                i5 = R.color.keyboard_color_title_dark;
            } else {
                textView2 = this.tvTitle;
                i5 = R.color.keyboard_color_title;
            }
            textView4.setTextColor(ViewDataBinding.getColorFromResource(textView2, i5));
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

    private SecurityLayoutFunctionalTileBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[1], (Button) objArr[3], (RelativeLayout) objArr[0], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        this.btnBack.setTag(null);
        this.btnClose.setTag(null);
        this.rlTitle.setTag(null);
        this.tvTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
