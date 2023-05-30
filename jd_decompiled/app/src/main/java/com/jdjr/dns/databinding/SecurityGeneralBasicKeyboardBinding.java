package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public abstract class SecurityGeneralBasicKeyboardBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout llBasicKeyboard;
    @Bindable
    protected KeyboardUiMode mUiMode;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityGeneralBasicKeyboardBinding(Object obj, View view, int i2, LinearLayout linearLayout) {
        super(obj, view, i2);
        this.llBasicKeyboard = linearLayout;
    }

    public static SecurityGeneralBasicKeyboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityGeneralBasicKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public KeyboardUiMode getUiMode() {
        return this.mUiMode;
    }

    public abstract void setUiMode(@Nullable KeyboardUiMode keyboardUiMode);

    @Deprecated
    public static SecurityGeneralBasicKeyboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityGeneralBasicKeyboardBinding) ViewDataBinding.bind(obj, view, R.layout.security_general_basic_keyboard);
    }

    @NonNull
    @Deprecated
    public static SecurityGeneralBasicKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityGeneralBasicKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_general_basic_keyboard, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityGeneralBasicKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityGeneralBasicKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityGeneralBasicKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_general_basic_keyboard, null, false, obj);
    }
}
