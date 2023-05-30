package cn.com.union.fido.util.asn1.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class Hex {
    private static final Encoder encoder = new HexEncoder();

    public static int decode(String str, OutputStream outputStream) {
        return encoder.decode(str, outputStream);
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new RuntimeException("exception decoding Hex string: ".concat(String.valueOf(e2)));
        }
    }

    public static byte[] decode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new RuntimeException("exception decoding Hex string: ".concat(String.valueOf(e2)));
        }
    }

    public static int encode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        return encoder.encode(bArr, i2, i3, outputStream);
    }

    public static int encode(byte[] bArr, OutputStream outputStream) {
        return encoder.encode(bArr, 0, bArr.length, outputStream);
    }

    public static byte[] encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static byte[] encode(byte[] bArr, int i2, int i3) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.encode(bArr, i2, i3, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new RuntimeException("exception encoding Hex string: ".concat(String.valueOf(e2)));
        }
    }
}
