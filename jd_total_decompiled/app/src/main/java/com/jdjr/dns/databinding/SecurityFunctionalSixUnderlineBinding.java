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
import com.jdjr.generalKeyboard.views.SixInputLayout;

/* loaded from: classes18.dex */
public abstract class SecurityFunctionalSixUnderlineBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout flInput;
    @NonNull
    public final LinearLayout generalKeyboardTop;
    @NonNull
    public final SixInputLayout sixInput;
    @NonNull
    public final TextView tvTips;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityFunctionalSixUnderlineBinding(Object obj, View view, int i2, FrameLayout frameLayout, LinearLayout linearLayout, SixInputLayout sixInputLayout, TextView textView) {
        super(obj, view, i2);
        this.flInput = frameLayout;
        this.generalKeyboardTop = linearLayout;
        this.sixInput = sixInputLayout;
        this.tvTips = textView;
    }

    public static SecurityFunctionalSixUnderlineBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityFunctionalSixUnderlineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityFunctionalSixUnderlineBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityFunctionalSixUnderlineBinding) ViewDataBinding.bind(obj, view, R.layout.security_functional_six_underline);
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalSixUnderlineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityFunctionalSixUnderlineBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_six_underline, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityFunctionalSixUnderlineBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalSixUnderlineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityFunctionalSixUnderlineBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_six_underline, null, false, obj);
    }
}
