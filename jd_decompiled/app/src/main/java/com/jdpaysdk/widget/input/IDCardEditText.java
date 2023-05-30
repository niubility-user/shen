package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.IDCardFormatFilter;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.listener.IDCardKeyListener;

/* loaded from: classes18.dex */
public class IDCardEditText extends AbsEditText {
    public IDCardEditText(@NonNull Context context) {
        super(context);
        KeyListener iDCardKeyListener = new IDCardKeyListener(this);
        setKeyListener(iDCardKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = iDCardKeyListener;
        inputFilterArr[1] = new MaxInputFilter(18);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new IDCardFormatFilter();
        setFilters(inputFilterArr);
    }

    public IDCardEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener iDCardKeyListener = new IDCardKeyListener(this);
        setKeyListener(iDCardKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = iDCardKeyListener;
        inputFilterArr[1] = new MaxInputFilter(18);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new IDCardFormatFilter();
        setFilters(inputFilterArr);
    }

    public IDCardEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener iDCardKeyListener = new IDCardKeyListener(this);
        setKeyListener(iDCardKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = iDCardKeyListener;
        inputFilterArr[1] = new MaxInputFilter(18);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new IDCardFormatFilter();
        setFilters(inputFilterArr);
    }
}
