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
public abstract class SecurityTotalLetterKeyboardBinding extends ViewDataBinding {
    @NonNull
    public final ImageButton btnKeyCapslock;
    @NonNull
    public final Button btnLetter123;
    @NonNull
    public final ImageButton btnLetterDel;
    @NonNull
    public final Button btnLetterSure;
    @NonNull
    public final Button btnLetterSymbol;

    /* JADX INFO: Access modifiers changed from: protected */
    public SecurityTotalLetterKeyboardBinding(Object obj, View view, int i2, ImageButton imageButton, Button button, ImageButton imageButton2, Button button2, Button button3) {
        super(obj, view, i2);
        this.btnKeyCapslock = imageButton;
        this.btnLetter123 = button;
        this.btnLetterDel = imageButton2;
        this.btnLetterSure = button2;
        this.btnLetterSymbol = button3;
    }

    public static SecurityTotalLetterKeyboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SecurityTotalLetterKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SecurityTotalLetterKeyboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SecurityTotalLetterKeyboardBinding) ViewDataBinding.bind(obj, view, R.layout.security_total_letter_keyboard);
    }

    @NonNull
    @Deprecated
    public static SecurityTotalLetterKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SecurityTotalLetterKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_total_letter_keyboard, viewGroup, z, obj);
    }

    @NonNull
    public static SecurityTotalLetterKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SecurityTotalLetterKeyboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SecurityTotalLetterKeyboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.security_total_letter_keyboard, null, false, obj);
    }
}
