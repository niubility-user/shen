package com.xiaomi.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class g9 extends j9 {
    protected InputStream a = null;
    protected OutputStream b;

    public g9(OutputStream outputStream) {
        this.b = null;
        this.b = outputStream;
    }

    @Override // com.xiaomi.push.j9
    public int b(byte[] bArr, int i2, int i3) {
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                int read = inputStream.read(bArr, i2, i3);
                if (read >= 0) {
                    return read;
                }
                throw new k9(4);
            } catch (IOException e2) {
                throw new k9(0, e2);
            }
        }
        throw new k9(1, "Cannot read from null inputStream");
    }

    @Override // com.xiaomi.push.j9
    public void d(byte[] bArr, int i2, int i3) {
        OutputStream outputStream = this.b;
        if (outputStream == null) {
            throw new k9(1, "Cannot write to null outputStream");
        }
        try {
            outputStream.write(bArr, i2, i3);
        } catch (IOException e2) {
            throw new k9(0, e2);
        }
    }
}
