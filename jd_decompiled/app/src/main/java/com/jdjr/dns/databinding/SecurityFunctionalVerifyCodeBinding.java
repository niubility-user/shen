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
import com.jdjr.generalKeyboard.views.VerifyCodeEditText;

/* loaded from: classes18.dex */
public abstract class SecurityFunctionalVerifyCodeBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout flInput;
    @NonNull
    public final LinearLayout generalKeyboardTop;
    @NonNull
    public final TextView tvTips;
    @NonNull
    public final VerifyCodeEditText verifyCodeEditText;

    public SecurityFunctionalVerifyCodeBinding(Object obj, View view, int i2, FrameLayout frameLayout, LinearLayout linearLayout, TextView textView, VerifyCodeEditText verifyCodeEditText) {
        super(obj, view, i2);
        this.flInput = frameLayout;
        this.generalKeyboardTop = linearLayout;
        this.tvTips = textView;
        this.verifyCodeEditText = verifyCodeEditText;
    }

    public static SecurityFunctionalVerifyCodeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityFunctionalVerifyCodeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityFunctionalVerifyCodeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityFunctionalVerifyCodeBinding) ViewDataBinding.bind(obj, view, R.layout.security_functional_verify_code);
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalVerifyCodeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityFunctionalVerifyCodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_verify_code, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityFunctionalVerifyCodeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalVerifyCodeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityFunctionalVerifyCodeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_verify_code, null, false, obj);
    }
}
