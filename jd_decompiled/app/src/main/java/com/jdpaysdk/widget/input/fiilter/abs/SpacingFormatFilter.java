package com.jdpaysdk.widget.input.fiilter.abs;

import com.jdpaysdk.widget.input.span.IFormatSpan;
import com.jdpaysdk.widget.input.span.SpacingSpan;

/* loaded from: classes18.dex */
public abstract class SpacingFormatFilter extends FormatFilter {
    @Override // com.jdpaysdk.widget.input.fiilter.abs.FormatFilter
    protected IFormatSpan createSpan() {
        return SpacingSpan.create();
    }
}
