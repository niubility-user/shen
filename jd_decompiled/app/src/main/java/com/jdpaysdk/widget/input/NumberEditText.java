package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter;
import com.jdpaysdk.widget.input.listener.NumKeyListener;

/* loaded from: classes18.dex */
public class NumberEditText extends AbsEditText {
    private int maxLength;

    public NumberEditText(@NonNull Context context) {
        super(context);
        this.maxLength = Integer.MAX_VALUE;
        KeyListener numKeyListener = new NumKeyListener(this);
        setKeyListener(numKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = numKeyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.NumberEditText.1
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return NumberEditText.this.maxLength;
            }
        };
        setFilters(inputFilterArr);
    }

    public void setMaxLength(int i2) {
        this.maxLength = i2;
    }

    public NumberEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxLength = Integer.MAX_VALUE;
        KeyListener numKeyListener = new NumKeyListener(this);
        setKeyListener(numKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = numKeyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.NumberEditText.1
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return NumberEditText.this.maxLength;
            }
        };
        setFilters(inputFilterArr);
    }

    public NumberEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.maxLength = Integer.MAX_VALUE;
        KeyListener numKeyListener = new NumKeyListener(this);
        setKeyListener(numKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = numKeyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.NumberEditText.1
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return NumberEditText.this.maxLength;
            }
        };
        setFilters(inputFilterArr);
    }
}
