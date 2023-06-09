package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.nio.charset.Charset;
import org.apache.commons.codec.CharEncoding;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public final class Charsets {
    @GwtIncompatible
    public static final Charset US_ASCII = Charset.forName(CharEncoding.US_ASCII);
    public static final Charset ISO_8859_1 = Charset.forName(CharEncoding.ISO_8859_1);
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    @GwtIncompatible
    public static final Charset UTF_16BE = Charset.forName(CharEncoding.UTF_16BE);
    @GwtIncompatible
    public static final Charset UTF_16LE = Charset.forName(CharEncoding.UTF_16LE);
    @GwtIncompatible
    public static final Charset UTF_16 = Charset.forName(CharEncoding.UTF_16);

    private Charsets() {
    }
}
