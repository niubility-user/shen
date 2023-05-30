package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityLayoutFunctionalTileBinding extends ViewDataBinding {
    @NonNull
    public final Button btnBack;
    @NonNull
    public final Button btnClose;
    @NonNull
    public final RelativeLayout rlTitle;
    @NonNull
    public final TextView tvTitle;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityLayoutFunctionalTileBinding(Object obj, View view, int i2, Button button, Button button2, RelativeLayout relativeLayout, TextView textView) {
        super(obj, view, i2);
        this.btnBack = button;
        this.btnClose = button2;
        this.rlTitle = relativeLayout;
        this.tvTitle = textView;
    }

    public static SecurityLayoutFunctionalTileBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityLayoutFunctionalTileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityLayoutFunctionalTileBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityLayoutFunctionalTileBinding) ViewDataBinding.bind(obj, view, R.layout.security_layout_functional_tile);
    }

    @NonNull
    @Deprecated
    public static SecurityLayoutFunctionalTileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityLayoutFunctionalTileBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_layout_functional_tile, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityLayoutFunctionalTileBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityLayoutFunctionalTileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityLayoutFunctionalTileBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_layout_functional_tile, null, false, obj);
    }
}
