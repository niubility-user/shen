package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityFunctionalLoadingBinding extends ViewDataBinding {
    @NonNull
    public final ProgressBar pbLoading;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityFunctionalLoadingBinding(Object obj, View view, int i2, ProgressBar progressBar) {
        super(obj, view, i2);
        this.pbLoading = progressBar;
    }

    public static SecurityFunctionalLoadingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityFunctionalLoadingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityFunctionalLoadingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityFunctionalLoadingBinding) ViewDataBinding.bind(obj, view, R.layout.security_functional_loading);
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalLoadingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityFunctionalLoadingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_loading, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityFunctionalLoadingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalLoadingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityFunctionalLoadingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_loading, null, false, obj);
    }
}
