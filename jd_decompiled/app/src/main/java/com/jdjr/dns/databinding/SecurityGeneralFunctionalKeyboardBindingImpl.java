package com.jdjr.dns.databinding;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.jdjr.dns.BR;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class SecurityGeneralFunctionalKeyboardBindingImpl extends SecurityGeneralFunctionalKeyboardBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @NonNull
    private final FrameLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(6);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"security_layout_functional_tile", "security_functional_loading"}, new int[]{2, 3}, new int[]{R.layout.security_layout_functional_tile, R.layout.security_functional_loading});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_function_layout, 4);
        sparseIntArray.put(R.id.ll_keyboard_layout, 5);
    }

    public SecurityGeneralFunctionalKeyboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private boolean onChangeLayoutTitle(SecurityLayoutFunctionalTileBinding securityLayoutFunctionalTileBinding, int i2) {
        if (i2 == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLoadingLayout(SecurityFunctionalLoadingBinding securityFunctionalLoadingBinding, int i2) {
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
        Context context;
        int i2;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j3 = j2 & 4;
        if (j3 != 0 && j3 != 0) {
            j2 |= KeyboardUiMode.isDark() ? 16L : 8L;
        }
        if ((j2 & 4) != 0) {
            LinearLayout linearLayout = this.keyboardContainer;
            if (KeyboardUiMode.isDark()) {
                context = this.keyboardContainer.getContext();
                i2 = R.drawable.security_general_corner_bg_dark;
            } else {
                context = this.keyboardContainer.getContext();
                i2 = R.drawable.security_general_corner_bg;
            }
            ViewBindingAdapter.setBackground(linearLayout, AppCompatResources.getDrawable(context, i2));
        }
        ViewDataBinding.executeBindingsOn(this.layoutTitle);
        ViewDataBinding.executeBindingsOn(this.loadingLayout);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.layoutTitle.hasPendingBindings() || this.loadingLayout.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        this.layoutTitle.invalidateAll();
        this.loadingLayout.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i2, Object obj, int i3) {
        if (i2 != 0) {
            if (i2 != 1) {
                return false;
            }
            return onChangeLayoutTitle((SecurityLayoutFunctionalTileBinding) obj, i3);
        }
        return onChangeLoadingLayout((SecurityFunctionalLoadingBinding) obj, i3);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.layoutTitle.setLifecycleOwner(lifecycleOwner);
        this.loadingLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    private SecurityGeneralFunctionalKeyboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (LinearLayout) objArr[1], (SecurityLayoutFunctionalTileBinding) objArr[2], (LinearLayout) objArr[4], (LinearLayout) objArr[5], (SecurityFunctionalLoadingBinding) objArr[3]);
        this.mDirtyFlags = -1L;
        this.keyboardContainer.setTag(null);
        setContainedBinding(this.layoutTitle);
        setContainedBinding(this.loadingLayout);
        FrameLayout frameLayout = (FrameLayout) objArr[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
