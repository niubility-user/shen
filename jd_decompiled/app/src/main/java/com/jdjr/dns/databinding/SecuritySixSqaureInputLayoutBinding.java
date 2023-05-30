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
public abstract class SecuritySixSqaureInputLayoutBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout sixInputEndit;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecuritySixSqaureInputLayoutBinding(Object obj, View view, int i2, LinearLayout linearLayout) {
        super(obj, view, i2);
        this.sixInputEndit = linearLayout;
    }

    public static SecuritySixSqaureInputLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecuritySixSqaureInputLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecuritySixSqaureInputLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecuritySixSqaureInputLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.security_six_sqaure_input_layout);
    }

    @NonNull
    @Deprecated
    public static SecuritySixSqaureInputLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecuritySixSqaureInputLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_six_sqaure_input_layout, viewGroup, z, obj);
    }

    @NonNull
    public static SecuritySixSqaureInputLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecuritySixSqaureInputLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecuritySixSqaureInputLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_six_sqaure_input_layout, null, false, obj);
    }
}
