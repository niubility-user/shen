package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityBaseNumberPureNoFinishBinding extends ViewDataBinding {
    @NonNull
    public final ImageButton deleteKey;
    @NonNull
    public final LinearLayout keyboardButtons;
    @NonNull
    public final ImageButton symbolKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityBaseNumberPureNoFinishBinding(Object obj, View view, int i2, ImageButton imageButton, LinearLayout linearLayout, ImageButton imageButton2) {
        super(obj, view, i2);
        this.deleteKey = imageButton;
        this.keyboardButtons = linearLayout;
        this.symbolKey = imageButton2;
    }

    public static SecurityBaseNumberPureNoFinishBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityBaseNumberPureNoFinishBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityBaseNumberPureNoFinishBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityBaseNumberPureNoFinishBinding) ViewDataBinding.bind(obj, view, R.layout.security_base_number_pure_no_finish);
    }

    @NonNull
    @Deprecated
    public static SecurityBaseNumberPureNoFinishBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityBaseNumberPureNoFinishBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_base_number_pure_no_finish, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityBaseNumberPureNoFinishBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityBaseNumberPureNoFinishBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityBaseNumberPureNoFinishBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_base_number_pure_no_finish, null, false, obj);
    }
}
