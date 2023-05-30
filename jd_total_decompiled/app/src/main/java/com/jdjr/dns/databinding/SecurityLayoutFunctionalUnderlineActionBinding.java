package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityLayoutFunctionalUnderlineActionBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout llActions;
    @NonNull
    public final TextView tvActionLeft;
    @NonNull
    public final TextView tvActionMiddle;
    @NonNull
    public final TextView tvActionRight;
    @NonNull
    public final View vVerticalLine;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityLayoutFunctionalUnderlineActionBinding(Object obj, View view, int i2, RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i2);
        this.llActions = relativeLayout;
        this.tvActionLeft = textView;
        this.tvActionMiddle = textView2;
        this.tvActionRight = textView3;
        this.vVerticalLine = view2;
    }

    public static SecurityLayoutFunctionalUnderlineActionBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityLayoutFunctionalUnderlineActionBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityLayoutFunctionalUnderlineActionBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityLayoutFunctionalUnderlineActionBinding) ViewDataBinding.bind(obj, view, R.layout.security_layout_functional_underline_action);
    }

    @NonNull
    @Deprecated
    public static SecurityLayoutFunctionalUnderlineActionBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityLayoutFunctionalUnderlineActionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_layout_functional_underline_action, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityLayoutFunctionalUnderlineActionBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityLayoutFunctionalUnderlineActionBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityLayoutFunctionalUnderlineActionBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_layout_functional_underline_action, null, false, obj);
    }
}
