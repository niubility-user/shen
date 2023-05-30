package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.listener.QweKeyListener;

/* loaded from: classes18.dex */
public class NameEditText extends AbsEditText {
    public NameEditText(@NonNull Context context) {
        super(context);
        KeyListener qweKeyListener = new QweKeyListener(this);
        setKeyListener(qweKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = qweKeyListener;
        inputFilterArr[1] = new MaxInputFilter(50);
        setFilters(inputFilterArr);
    }

    public NameEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener qweKeyListener = new QweKeyListener(this);
        setKeyListener(qweKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = qweKeyListener;
        inputFilterArr[1] = new MaxInputFilter(50);
        setFilters(inputFilterArr);
    }

    public NameEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener qweKeyListener = new QweKeyListener(this);
        setKeyListener(qweKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = qweKeyListener;
        inputFilterArr[1] = new MaxInputFilter(50);
        setFilters(inputFilterArr);
    }
}
