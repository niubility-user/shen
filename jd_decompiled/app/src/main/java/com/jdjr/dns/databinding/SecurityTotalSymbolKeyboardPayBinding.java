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
public abstract class SecurityTotalSymbolKeyboardPayBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSymbol123;
    @NonNull
    public final Button btnSymbolABC;
    @NonNull
    public final ImageButton btnSymbolDel;
    @NonNull
    public final Button btnSymbolMore;
    @NonNull
    public final Button btnSymbolSure;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityTotalSymbolKeyboardPayBinding(Object obj, View view, int i2, Button button, Button button2, ImageButton imageButton, Button button3, Button button4) {
        super(obj, view, i2);
        this.btnSymbol123 = button;
        this.btnSymbolABC = button2;
        this.btnSymbolDel = imageButton;
        this.btnSymbolMore = button3;
        this.btnSymbolSure = button4;
    }

    public static SecurityTotalSymbolKeyboardPayBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityTotalSymbolKeyboardPayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityTotalSymbolKeyboardPayBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityTotalSymbolKeyboardPayBinding) ViewDataBinding.bind(obj, view, R.layout.security_total_symbol_keyboard_pay);
    }

    @NonNull
    @Deprecated
    public static SecurityTotalSymbolKeyboardPayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityTotalSymbolKeyboardPayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_total_symbol_keyboard_pay, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityTotalSymbolKeyboardPayBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityTotalSymbolKeyboardPayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityTotalSymbolKeyboardPayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_total_symbol_keyboard_pay, null, false, obj);
    }
}
