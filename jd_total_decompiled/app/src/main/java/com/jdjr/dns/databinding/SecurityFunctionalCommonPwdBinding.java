package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.views.FlexibleEditText;

/* loaded from: classes18.dex */
public abstract class SecurityFunctionalCommonPwdBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout flInput;
    @NonNull
    public final FlexibleEditText flexibleEditText;
    @NonNull
    public final LinearLayout generalKeyboardTop;
    @NonNull
    public final TextView tvTips;

    public SecurityFunctionalCommonPwdBinding(Object obj, View view, int i2, FrameLayout frameLayout, FlexibleEditText flexibleEditText, LinearLayout linearLayout, TextView textView) {
        super(obj, view, i2);
        this.flInput = frameLayout;
        this.flexibleEditText = flexibleEditText;
        this.generalKeyboardTop = linearLayout;
        this.tvTips = textView;
    }

    public static SecurityFunctionalCommonPwdBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityFunctionalCommonPwdBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityFunctionalCommonPwdBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityFunctionalCommonPwdBinding) ViewDataBinding.bind(obj, view, R.layout.security_functional_common_pwd);
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalCommonPwdBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityFunctionalCommonPwdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_common_pwd, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityFunctionalCommonPwdBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalCommonPwdBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityFunctionalCommonPwdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_common_pwd, null, false, obj);
    }
}
