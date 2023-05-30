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
public abstract class SecurityFunctionalSixSquareBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout flInput;
    @NonNull
    public final LinearLayout generalKeyboardTop;
    @NonNull
    public final SixInputLayout sixInput;
    @NonNull
    public final TextView tvTips;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityFunctionalSixSquareBinding(Object obj, View view, int i2, FrameLayout frameLayout, LinearLayout linearLayout, SixInputLayout sixInputLayout, TextView textView) {
        super(obj, view, i2);
        this.flInput = frameLayout;
        this.generalKeyboardTop = linearLayout;
        this.sixInput = sixInputLayout;
        this.tvTips = textView;
    }

    public static SecurityFunctionalSixSquareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityFunctionalSixSquareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityFunctionalSixSquareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityFunctionalSixSquareBinding) ViewDataBinding.bind(obj, view, R.layout.security_functional_six_square);
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalSixSquareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityFunctionalSixSquareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_six_square, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityFunctionalSixSquareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityFunctionalSixSquareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityFunctionalSixSquareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_functional_six_square, null, false, obj);
    }
}
