package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.listener.BirthdayKeyListener;

/* loaded from: classes18.dex */
public class BirthdayEditText extends AbsEditText {
    public BirthdayEditText(@NonNull Context context) {
        super(context);
        KeyListener birthdayKeyListener = new BirthdayKeyListener(this);
        setKeyListener(birthdayKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = birthdayKeyListener;
        inputFilterArr[1] = new MaxInputFilter(8);
        setFilters(inputFilterArr);
    }

    public BirthdayEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener birthdayKeyListener = new BirthdayKeyListener(this);
        setKeyListener(birthdayKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = birthdayKeyListener;
        inputFilterArr[1] = new MaxInputFilter(8);
        setFilters(inputFilterArr);
    }

    public BirthdayEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener birthdayKeyListener = new BirthdayKeyListener(this);
        setKeyListener(birthdayKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = birthdayKeyListener;
        inputFilterArr[1] = new MaxInputFilter(8);
        setFilters(inputFilterArr);
    }
}
