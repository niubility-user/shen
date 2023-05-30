package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.listener.SMSCodeKeyListener;

/* loaded from: classes18.dex */
public class SMSCodeEditText extends AbsEditText {
    public SMSCodeEditText(@NonNull Context context) {
        super(context);
        KeyListener sMSCodeKeyListener = new SMSCodeKeyListener(this);
        setKeyListener(sMSCodeKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = sMSCodeKeyListener;
        inputFilterArr[1] = new MaxInputFilter(6);
        setFilters(inputFilterArr);
    }

    public SMSCodeEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener sMSCodeKeyListener = new SMSCodeKeyListener(this);
        setKeyListener(sMSCodeKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = sMSCodeKeyListener;
        inputFilterArr[1] = new MaxInputFilter(6);
        setFilters(inputFilterArr);
    }

    public SMSCodeEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener sMSCodeKeyListener = new SMSCodeKeyListener(this);
        setKeyListener(sMSCodeKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = sMSCodeKeyListener;
        inputFilterArr[1] = new MaxInputFilter(6);
        setFilters(inputFilterArr);
    }
}
