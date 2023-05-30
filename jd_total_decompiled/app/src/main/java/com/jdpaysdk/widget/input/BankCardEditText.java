package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.BankCardFormatFilter;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.listener.MaskNumberKeyListener;

/* loaded from: classes18.dex */
public class BankCardEditText extends AbsEditText {
    public BankCardEditText(@NonNull Context context) {
        super(context);
        KeyListener maskNumberKeyListener = new MaskNumberKeyListener(this);
        setKeyListener(maskNumberKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = maskNumberKeyListener;
        inputFilterArr[1] = new MaxInputFilter(21);
        inputFilterArr[length - 1] = new BankCardFormatFilter();
        setFilters(inputFilterArr);
    }

    public BankCardEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener maskNumberKeyListener = new MaskNumberKeyListener(this);
        setKeyListener(maskNumberKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = maskNumberKeyListener;
        inputFilterArr[1] = new MaxInputFilter(21);
        inputFilterArr[length - 1] = new BankCardFormatFilter();
        setFilters(inputFilterArr);
    }

    public BankCardEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener maskNumberKeyListener = new MaskNumberKeyListener(this);
        setKeyListener(maskNumberKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = maskNumberKeyListener;
        inputFilterArr[1] = new MaxInputFilter(21);
        inputFilterArr[length - 1] = new BankCardFormatFilter();
        setFilters(inputFilterArr);
    }
}
