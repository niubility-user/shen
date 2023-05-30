package com.jdjr.dns.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.jdjr.dns.BR;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class SecurityGeneralKeyboardTotalBindingImpl extends SecurityGeneralKeyboardTotalBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(4);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"security_total_letter_keyboard", "security_total_number_keyboard", "security_total_symbol_keyboard_pay"}, new int[]{1, 2, 3}, new int[]{R.layout.security_total_letter_keyboard, R.layout.security_total_number_keyboard, R.layout.security_total_symbol_keyboard_pay});
        sViewsWithIds = null;
    }

    public SecurityGeneralKeyboardTotalBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private boolean onChangeTotalLetterKeyboard(SecurityTotalLetterKeyboardBinding securityTotalLetterKeyboardBinding, int i2) {
        if (i2 == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeTotalNumberKeyboard(SecurityTotalNumberKeyboardBinding securityTotalNumberKeyboardBinding, int i2) {
        if (i2 == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeTotalSymbolKeyboard(SecurityTotalSymbolKeyboardPayBinding securityTotalSymbolKeyboardPayBinding, int i2) {
        if (i2 == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        FrameLayout frameLayout;
        int i2;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j3 = j2 & 8;
        if (j3 != 0 && j3 != 0) {
            j2 |= KeyboardUiMode.isDark() ? 32L : 16L;
        }
        if ((j2 & 8) != 0) {
            FrameLayout frameLayout2 = this.totalKeyboard;
            if (KeyboardUiMode.isDark()) {
                frameLayout = this.totalKeyboard;
                i2 = R.color.keyboard_color_keyboard_bg_dark;
            } else {
                frameLayout = this.totalKeyboard;
                i2 = R.color.keyboard_color_keyboard_bg;
            }
            ViewBindingAdapter.setBackground(frameLayout2, Converters.convertColorToDrawable(ViewDataBinding.getColorFromResource(frameLayout, i2)));
        }
        ViewDataBinding.executeBindingsOn(this.totalLetterKeyboard);
        ViewDataBinding.executeBindingsOn(this.totalNumberKeyboard);
        ViewDataBinding.executeBindingsOn(this.totalSymbolKeyboard);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.totalLetterKeyboard.hasPendingBindings() || this.totalNumberKeyboard.hasPendingBindings() || this.totalSymbolKeyboard.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        this.totalLetterKeyboard.invalidateAll();
        this.totalNumberKeyboard.invalidateAll();
        this.totalSymbolKeyboard.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i2, Object obj, int i3) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return false;
                }
                return onChangeTotalLetterKeyboard((SecurityTotalLetterKeyboardBinding) obj, i3);
            }
            return onChangeTotalSymbolKeyboard((SecurityTotalSymbolKeyboardPayBinding) obj, i3);
        }
        return onChangeTotalNumberKeyboard((SecurityTotalNumberKeyboardBinding) obj, i3);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.totalLetterKeyboard.setLifecycleOwner(lifecycleOwner);
        this.totalNumberKeyboard.setLifecycleOwner(lifecycleOwner);
        this.totalSymbolKeyboard.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    private SecurityGeneralKeyboardTotalBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (FrameLayout) objArr[0], (SecurityTotalLetterKeyboardBinding) objArr[1], (SecurityTotalNumberKeyboardBinding) objArr[2], (SecurityTotalSymbolKeyboardPayBinding) objArr[3]);
        this.mDirtyFlags = -1L;
        this.totalKeyboard.setTag(null);
        setContainedBinding(this.totalLetterKeyboard);
        setContainedBinding(this.totalNumberKeyboard);
        setContainedBinding(this.totalSymbolKeyboard);
        setRootTag(view);
        invalidateAll();
    }
}
