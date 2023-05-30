package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityCompomentVerifyCodeEdittextBinding extends ViewDataBinding {
    @NonNull
    public final Button btnCountdown;
    @NonNull
    public final EditText etVerifyCode;
    @NonNull
    public final RelativeLayout rlVerifyContainer;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityCompomentVerifyCodeEdittextBinding(Object obj, View view, int i2, Button button, EditText editText, RelativeLayout relativeLayout) {
        super(obj, view, i2);
        this.btnCountdown = button;
        this.etVerifyCode = editText;
        this.rlVerifyContainer = relativeLayout;
    }

    public static SecurityCompomentVerifyCodeEdittextBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityCompomentVerifyCodeEdittextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityCompomentVerifyCodeEdittextBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityCompomentVerifyCodeEdittextBinding) ViewDataBinding.bind(obj, view, R.layout.security_compoment_verify_code_edittext);
    }

    @NonNull
    @Deprecated
    public static SecurityCompomentVerifyCodeEdittextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityCompomentVerifyCodeEdittextBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_compoment_verify_code_edittext, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityCompomentVerifyCodeEdittextBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityCompomentVerifyCodeEdittextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityCompomentVerifyCodeEdittextBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_compoment_verify_code_edittext, null, false, obj);
    }
}
