package cn.com.union.fido.util.asn1.util.encoders;

import java.io.OutputStream;

/* loaded from: classes.dex */
public interface Encoder {
    int decode(String str, OutputStream outputStream);

    int decode(byte[] bArr, int i2, int i3, OutputStream outputStream);

    int encode(byte[] bArr, int i2, int i3, OutputStream outputStream);
}
