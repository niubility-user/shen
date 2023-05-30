package com.jdpaysdk.widget.input.fiilter;

import com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter;

/* loaded from: classes18.dex */
public class MaxInputFilter extends AbsMaxInputFilter {
    private final int maxLength;

    public MaxInputFilter(int i2) {
        this.maxLength = i2;
    }

    @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
    protected final int getMaxLength() {
        return this.maxLength;
    }
}
