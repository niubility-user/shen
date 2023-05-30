package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityKeyboardKeyPreviewLayoutBinding extends ViewDataBinding {
    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityKeyboardKeyPreviewLayoutBinding(Object obj, View view, int i2) {
        super(obj, view, i2);
    }

    public static SecurityKeyboardKeyPreviewLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityKeyboardKeyPreviewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityKeyboardKeyPreviewLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityKeyboardKeyPreviewLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.security_keyboard_key_preview_layout);
    }

    @NonNull
    @Deprecated
    public static SecurityKeyboardKeyPreviewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityKeyboardKeyPreviewLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_keyboard_key_preview_layout, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityKeyboardKeyPreviewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityKeyboardKeyPreviewLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityKeyboardKeyPreviewLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_keyboard_key_preview_layout, null, false, obj);
    }
}
