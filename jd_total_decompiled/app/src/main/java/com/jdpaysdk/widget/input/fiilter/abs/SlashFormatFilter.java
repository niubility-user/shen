package com.jdpaysdk.widget.input.fiilter.abs;

import com.jdpaysdk.widget.input.span.IFormatSpan;
import com.jdpaysdk.widget.input.span.SlashSpan;

/* loaded from: classes18.dex */
public abstract class SlashFormatFilter extends FormatFilter {
    @Override // com.jdpaysdk.widget.input.fiilter.abs.FormatFilter
    protected IFormatSpan createSpan() {
        return SlashSpan.create();
    }
}
