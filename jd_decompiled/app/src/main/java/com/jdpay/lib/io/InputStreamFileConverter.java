package com.jdpay.lib.io;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.converter.Converter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class InputStreamFileConverter implements Converter<InputStream, File> {
    private int bufferSize;
    private File target;

    public InputStreamFileConverter(@NonNull File file) {
        this.bufferSize = 32768;
        this.target = file;
    }

    @Override // com.jdpay.lib.converter.Converter
    public File convert(@Nullable InputStream inputStream) throws Throwable {
        FileOutputStream fileOutputStream = null;
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[this.bufferSize];
            FileOutputStream fileOutputStream2 = new FileOutputStream(this.target);
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException unused) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            }
            fileOutputStream2.close();
            return this.target;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public InputStreamFileConverter(@NonNull File file, int i2) {
        this.bufferSize = 32768;
        this.target = file;
        this.bufferSize = i2;
    }
}
