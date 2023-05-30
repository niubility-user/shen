package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityBaseNumberXCanFinishBinding extends ViewDataBinding {
    @NonNull
    public final Button btnOk;
    @NonNull
    public final ImageButton deleteKey;
    @NonNull
    public final ImageButton hideKey;
    @NonNull
    public final LinearLayout keyboardButtons;
    @NonNull
    public final FrameLayout okLayout;
    @NonNull
    public final ImageButton symbolKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityBaseNumberXCanFinishBinding(Object obj, View view, int i2, Button button, ImageButton imageButton, ImageButton imageButton2, LinearLayout linearLayout, FrameLayout frameLayout, ImageButton imageButton3) {
        super(obj, view, i2);
        this.btnOk = button;
        this.deleteKey = imageButton;
        this.hideKey = imageButton2;
        this.keyboardButtons = linearLayout;
        this.okLayout = frameLayout;
        this.symbolKey = imageButton3;
    }

    public static SecurityBaseNumberXCanFinishBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityBaseNumberXCanFinishBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityBaseNumberXCanFinishBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityBaseNumberXCanFinishBinding) ViewDataBinding.bind(obj, view, R.layout.security_base_number_x_can_finish);
    }

    @NonNull
    @Deprecated
    public static SecurityBaseNumberXCanFinishBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityBaseNumberXCanFinishBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_base_number_x_can_finish, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityBaseNumberXCanFinishBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityBaseNumberXCanFinishBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityBaseNumberXCanFinishBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_base_number_x_can_finish, null, false, obj);
    }
}
