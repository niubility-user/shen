package com.jdpay.lib.converter;

import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public interface Converter<INPUT, OUTPUT> {
    OUTPUT convert(@Nullable INPUT input) throws Throwable;
}
