package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.views.FlexibleEditText;

/* loaded from: classes18.dex */
public abstract class SecurityFunctionalPaymentBinding extends ViewDataBinding {
    @NonNull
    public final FlexibleEditText flexibleEditText;
    @NonNull
    public final LinearLayout generalKeyboardTop;
    @NonNull
    public final LinearLayout llInput;
    @NonNull
    public final TextView tvTips;

    public SecurityFunctionalPaymentBinding(Object obj, View view, int i2, FlexibleEditText flexibleEditText, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView) {
        super(obj, view, i2);
        this.flexibleEditText = flexibleEditText;
        this.generalKeyboardTop = linearLayout;
        this.llInput = linearLayout2;
        this.tvTips = textView;
    }

    public static SecurityFunctionalPaymentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityFunctionalPaymentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityFunctionalPaymentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityFunctionalPaymentBinding) ViewDataBinding.bind(obj, view, R.layout.security_functional_payment);
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalPaymentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityFunctionalPaymentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_payment, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityFunctionalPaymentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalPaymentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityFunctionalPaymentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_payment, null, false, obj);
    }
}
