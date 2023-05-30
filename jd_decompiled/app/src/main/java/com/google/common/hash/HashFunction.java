package com.google.common.hash;

import com.google.common.annotations.Beta;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Beta
/* loaded from: classes12.dex */
public interface HashFunction {
    int bits();

    HashCode hashBytes(ByteBuffer byteBuffer);

    HashCode hashBytes(byte[] bArr);

    HashCode hashBytes(byte[] bArr, int i2, int i3);

    HashCode hashInt(int i2);

    HashCode hashLong(long j2);

    <T> HashCode hashObject(T t, Funnel<? super T> funnel);

    HashCode hashString(CharSequence charSequence, Charset charset);

    HashCode hashUnencodedChars(CharSequence charSequence);

    Hasher newHasher();

    Hasher newHasher(int i2);
}
