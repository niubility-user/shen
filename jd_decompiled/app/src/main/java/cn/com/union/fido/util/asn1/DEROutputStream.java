package cn.com.union.fido.util.asn1;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class DEROutputStream extends FilterOutputStream implements DERTags {
    public DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    private void writeLength(int i2) {
        if (i2 <= 127) {
            write((byte) i2);
            return;
        }
        int i3 = i2;
        int i4 = 1;
        while (true) {
            i3 >>>= 8;
            if (i3 == 0) {
                break;
            }
            i4++;
        }
        write((byte) (i4 | 128));
        for (int i5 = (i4 - 1) * 8; i5 >= 0; i5 -= 8) {
            write((byte) (i2 >> i5));
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        ((FilterOutputStream) this).out.write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        ((FilterOutputStream) this).out.write(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeEncoded(int i2, int i3, byte[] bArr) {
        writeTag(i2, i3);
        writeLength(bArr.length);
        write(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeEncoded(int i2, byte[] bArr) {
        write(i2);
        writeLength(bArr.length);
        write(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeNull() {
        write(5);
        write(0);
    }

    public void writeObject(Object obj) {
        DERObject dERObject;
        if (obj == null) {
            writeNull();
            return;
        }
        if (obj instanceof DERObject) {
            dERObject = (DERObject) obj;
        } else if (!(obj instanceof DEREncodable)) {
            throw new IOException("object not DEREncodable");
        } else {
            dERObject = ((DEREncodable) obj).getDERObject();
        }
        dERObject.encode(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeTag(int i2, int i3) {
        if (i3 < 31) {
            write(i2 | i3);
            return;
        }
        write(i2 | 31);
        if (i3 < 128) {
            write(i3);
            return;
        }
        byte[] bArr = new byte[5];
        int i4 = 4;
        bArr[4] = (byte) (i3 & 127);
        do {
            i3 >>= 7;
            i4--;
            bArr[i4] = (byte) ((i3 & 127) | 128);
        } while (i3 > 127);
        write(bArr, i4, 5 - i4);
    }
}
