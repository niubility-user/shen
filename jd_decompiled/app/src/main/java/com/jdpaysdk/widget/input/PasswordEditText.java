package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter;
import com.jdpaysdk.widget.input.listener.PasswordKeyListener;
import com.jdpaysdk.widget.input.method.PasswordTransformationMethod;

/* loaded from: classes18.dex */
public class PasswordEditText extends AbsEditText {
    private volatile boolean isPlain;
    private volatile int maxLength;

    public PasswordEditText(@NonNull Context context) {
        super(context);
        this.maxLength = 20;
        this.isPlain = false;
        KeyListener passwordKeyListener = new PasswordKeyListener(this);
        setKeyListener(passwordKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = passwordKeyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.PasswordEditText.1
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return PasswordEditText.this.maxLength;
            }
        };
        setFilters(inputFilterArr);
        setTransformationMethod(new PasswordTransformationMethod(this, new PasswordTransformationMethod.ModeSwitchController() { // from class: com.jdpaysdk.widget.input.PasswordEditText.2
            @Override // com.jdpaysdk.widget.input.method.PasswordTransformationMethod.ModeSwitchController
            public boolean showPlaint() {
                return PasswordEditText.this.isPlain;
            }
        }));
    }

    public boolean isPlain() {
        return this.isPlain;
    }

    public void setMaxLength(int i2) {
        this.maxLength = i2;
    }

    public void setPlain(boolean z) {
        this.isPlain = z;
        refreshContent();
    }

    public PasswordEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxLength = 20;
        this.isPlain = false;
        KeyListener passwordKeyListener = new PasswordKeyListener(this);
        setKeyListener(passwordKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = passwordKeyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.PasswordEditText.1
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return PasswordEditText.this.maxLength;
            }
        };
        setFilters(inputFilterArr);
        setTransformationMethod(new PasswordTransformationMethod(this, new PasswordTransformationMethod.ModeSwitchController() { // from class: com.jdpaysdk.widget.input.PasswordEditText.2
            @Override // com.jdpaysdk.widget.input.method.PasswordTransformationMethod.ModeSwitchController
            public boolean showPlaint() {
                return PasswordEditText.this.isPlain;
            }
        }));
    }

    public PasswordEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.maxLength = 20;
        this.isPlain = false;
        KeyListener passwordKeyListener = new PasswordKeyListener(this);
        setKeyListener(passwordKeyListener);
        InputFilter[] filters = getFilters();
        InputFilter[] inputFilterArr = new InputFilter[filters.length + 2];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = passwordKeyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.PasswordEditText.1
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return PasswordEditText.this.maxLength;
            }
        };
        setFilters(inputFilterArr);
        setTransformationMethod(new PasswordTransformationMethod(this, new PasswordTransformationMethod.ModeSwitchController() { // from class: com.jdpaysdk.widget.input.PasswordEditText.2
            @Override // com.jdpaysdk.widget.input.method.PasswordTransformationMethod.ModeSwitchController
            public boolean showPlaint() {
                return PasswordEditText.this.isPlain;
            }
        }));
    }
}
