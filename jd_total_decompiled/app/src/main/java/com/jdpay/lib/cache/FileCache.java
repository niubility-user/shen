package com.jdpay.lib.cache;

import androidx.annotation.NonNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: classes18.dex */
public class FileCache implements Cache {
    protected final File target;

    public FileCache(@NonNull File file) {
        this.target = file;
    }

    @Override // com.jdpay.lib.cache.Cache
    public void clear() {
        if (this.target.exists()) {
            this.target.delete();
        }
    }

    @Override // com.jdpay.lib.cache.Cache
    public int length() {
        return (int) this.target.length();
    }

    @Override // com.jdpay.lib.cache.Cache
    @NonNull
    public byte[] read() throws Exception {
        int length = (int) this.target.length();
        byte[] bArr = new byte[length];
        if (length > 0) {
            FileInputStream fileInputStream = new FileInputStream(this.target);
            try {
                fileInputStream.read(bArr);
            } finally {
                fileInputStream.close();
            }
        }
        return bArr;
    }

    @Override // com.jdpay.lib.cache.Cache
    public void write(@NonNull byte[] bArr) throws Exception {
        if (this.target.exists()) {
            this.target.delete();
        }
        if (bArr.length == 0) {
            return;
        }
        if (!this.target.getParentFile().exists()) {
            this.target.getParentFile().mkdirs();
        }
        this.target.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(this.target);
        try {
            fileOutputStream.write(bArr);
        } finally {
            fileOutputStream.close();
        }
    }
}
