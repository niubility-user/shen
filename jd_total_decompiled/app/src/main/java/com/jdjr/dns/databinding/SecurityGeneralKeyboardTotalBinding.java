package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityGeneralKeyboardTotalBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout totalKeyboard;
    @NonNull
    public final SecurityTotalLetterKeyboardBinding totalLetterKeyboard;
    @NonNull
    public final SecurityTotalNumberKeyboardBinding totalNumberKeyboard;
    @NonNull
    public final SecurityTotalSymbolKeyboardPayBinding totalSymbolKeyboard;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityGeneralKeyboardTotalBinding(Object obj, View view, int i2, FrameLayout frameLayout, SecurityTotalLetterKeyboardBinding securityTotalLetterKeyboardBinding, SecurityTotalNumberKeyboardBinding securityTotalNumberKeyboardBinding, SecurityTotalSymbolKeyboardPayBinding securityTotalSymbolKeyboardPayBinding) {
        super(obj, view, i2);
        this.totalKeyboard = frameLayout;
        this.totalLetterKeyboard = securityTotalLetterKeyboardBinding;
        this.totalNumberKeyboard = securityTotalNumberKeyboardBinding;
        this.totalSymbolKeyboard = securityTotalSymbolKeyboardPayBinding;
    }

    public static SecurityGeneralKeyboardTotalBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityGeneralKeyboardTotalBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityGeneralKeyboardTotalBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityGeneralKeyboardTotalBinding) ViewDataBinding.bind(obj, view, R.layout.security_general_keyboard_total);
    }

    @NonNull
    @Deprecated
    public static SecurityGeneralKeyboardTotalBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityGeneralKeyboardTotalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_general_keyboard_total, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityGeneralKeyboardTotalBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityGeneralKeyboardTotalBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityGeneralKeyboardTotalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_general_keyboard_total, null, false, obj);
    }
}
