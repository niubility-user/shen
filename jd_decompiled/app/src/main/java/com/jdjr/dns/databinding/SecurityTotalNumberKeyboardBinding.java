package com.jdjr.dns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public abstract class SecurityTotalNumberKeyboardBinding extends ViewDataBinding {
    @NonNull
    public final Button btnNumberABC;
    @NonNull
    public final ImageButton btnNumberDel;
    @NonNull
    public final Button btnNumberSure;
    @NonNull
    public final Button btnNumberSymbol;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityTotalNumberKeyboardBinding(Object obj, View view, int i2, Button button, ImageButton imageButton, Button button2, Button button3) {
        super(obj, view, i2);
        this.btnNumberABC = button;
        this.btnNumberDel = imageButton;
        this.btnNumberSure = button2;
        this.btnNumberSymbol = button3;
    }

    public static SecurityTotalNumberKeyboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityTotalNumberKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityTotalNumberKeyboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityTotalNumberKeyboardBinding) ViewDataBinding.bind(obj, view, R.layout.security_total_number_keyboard);
    }

    @NonNull
    @Deprecated
    public static SecurityTotalNumberKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityTotalNumberKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_total_number_keyboard, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityTotalNumberKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityTotalNumberKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityTotalNumberKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_total_number_keyboard, null, false, obj);
    }
}
