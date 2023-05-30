package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.listener.NumKeyListener;

/* loaded from: classes18.dex */
public class CVVEditText extends AbsEditText {
    public CVVEditText(@NonNull Context context) {
        super(context);
        KeyListener numKeyListener = new NumKeyListener(this);
        setKeyListener(numKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = numKeyListener;
        inputFilterArr[1] = new MaxInputFilter(3);
        setFilters(inputFilterArr);
    }

    public CVVEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener numKeyListener = new NumKeyListener(this);
        setKeyListener(numKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = numKeyListener;
        inputFilterArr[1] = new MaxInputFilter(3);
        setFilters(inputFilterArr);
    }

    public CVVEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener numKeyListener = new NumKeyListener(this);
        setKeyListener(numKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = numKeyListener;
        inputFilterArr[1] = new MaxInputFilter(3);
        setFilters(inputFilterArr);
    }
}
