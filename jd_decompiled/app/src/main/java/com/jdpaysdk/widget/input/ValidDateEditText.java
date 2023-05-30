package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.MaxInputFilter;
import com.jdpaysdk.widget.input.fiilter.ValidDateFormatFilter;
import com.jdpaysdk.widget.input.listener.ValidDateKeyListener;

/* loaded from: classes18.dex */
public class ValidDateEditText extends AbsEditText {
    public ValidDateEditText(@NonNull Context context) {
        super(context);
        KeyListener validDateKeyListener = new ValidDateKeyListener(this);
        setKeyListener(validDateKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = validDateKeyListener;
        inputFilterArr[1] = new MaxInputFilter(4);
        inputFilterArr[length - 1] = new ValidDateFormatFilter();
        setFilters(inputFilterArr);
    }

    public ValidDateEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyListener validDateKeyListener = new ValidDateKeyListener(this);
        setKeyListener(validDateKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = validDateKeyListener;
        inputFilterArr[1] = new MaxInputFilter(4);
        inputFilterArr[length - 1] = new ValidDateFormatFilter();
        setFilters(inputFilterArr);
    }

    public ValidDateEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        KeyListener validDateKeyListener = new ValidDateKeyListener(this);
        setKeyListener(validDateKeyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = validDateKeyListener;
        inputFilterArr[1] = new MaxInputFilter(4);
        inputFilterArr[length - 1] = new ValidDateFormatFilter();
        setFilters(inputFilterArr);
    }
}
