package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;

@Beta
@GwtIncompatible
/* loaded from: classes12.dex */
public interface ByteProcessor<T> {
    T getResult();

    @CanIgnoreReturnValue
    boolean processBytes(byte[] bArr, int i2, int i3) throws IOException;
}
