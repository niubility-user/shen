package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.ForeignIDCardFormatFilter;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.listener.ForeignIDCardKeyListener;

/* loaded from: classes18.dex */
public class ForeignIDCardEditText extends AbsEditText {
    public ForeignIDCardEditText(@NonNull Context context) {
        super(context);
        KeyListener foreignIDCardKeyListener = new ForeignIDCardKeyListener(this);
        setKeyListener(foreignIDCardKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = foreignIDCardKeyListener;
        inputFilterArr[1] = new MaxInputFilter(15);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new ForeignIDCardFormatFilter();
        setFilters(inputFilterArr);
    }

    public ForeignIDCardEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener foreignIDCardKeyListener = new ForeignIDCardKeyListener(this);
        setKeyListener(foreignIDCardKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = foreignIDCardKeyListener;
        inputFilterArr[1] = new MaxInputFilter(15);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new ForeignIDCardFormatFilter();
        setFilters(inputFilterArr);
    }

    public ForeignIDCardEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener foreignIDCardKeyListener = new ForeignIDCardKeyListener(this);
        setKeyListener(foreignIDCardKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = foreignIDCardKeyListener;
        inputFilterArr[1] = new MaxInputFilter(15);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new ForeignIDCardFormatFilter();
        setFilters(inputFilterArr);
    }
}
