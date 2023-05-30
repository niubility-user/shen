package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityGeneralFunctionalKeyboardBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout keyboardContainer;
    @NonNull
    public final SecurityLayoutFunctionalTileBinding layoutTitle;
    @NonNull
    public final LinearLayout llFunctionLayout;
    @NonNull
    public final LinearLayout llKeyboardLayout;
    @NonNull
    public final SecurityFunctionalLoadingBinding loadingLayout;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityGeneralFunctionalKeyboardBinding(Object obj, View view, int i2, LinearLayout linearLayout, SecurityLayoutFunctionalTileBinding securityLayoutFunctionalTileBinding, LinearLayout linearLayout2, LinearLayout linearLayout3, SecurityFunctionalLoadingBinding securityFunctionalLoadingBinding) {
        super(obj, view, i2);
        this.keyboardContainer = linearLayout;
        this.layoutTitle = securityLayoutFunctionalTileBinding;
        this.llFunctionLayout = linearLayout2;
        this.llKeyboardLayout = linearLayout3;
        this.loadingLayout = securityFunctionalLoadingBinding;
    }

    public static SecurityGeneralFunctionalKeyboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityGeneralFunctionalKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityGeneralFunctionalKeyboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityGeneralFunctionalKeyboardBinding) ViewDataBinding.bind(obj, view, R.layout.security_general_functional_keyboard);
    }

    @NonNull
    @Deprecated
    public static SecurityGeneralFunctionalKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityGeneralFunctionalKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_general_functional_keyboard, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityGeneralFunctionalKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityGeneralFunctionalKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityGeneralFunctionalKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_general_functional_keyboard, null, false, obj);
    }
}
