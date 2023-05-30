package com.jdpay.net.converter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.net.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class ResponseFileConverter<RESPONSE extends Response> implements Converter<RESPONSE, File> {
    public static int DEFAULT_BUFFER_SIZE = 10240;
    protected final File target;

    public ResponseFileConverter(@NonNull String str) {
        this(new File(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jdpay.lib.converter.Converter
    public /* bridge */ /* synthetic */ File convert(@Nullable Object obj) throws Throwable {
        return convert((ResponseFileConverter<RESPONSE>) ((Response) obj));
    }

    public ResponseFileConverter(@NonNull File file) {
        this.target = file;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    public File convert(@Nullable RESPONSE response) throws Throwable {
        int read;
        FileOutputStream fileOutputStream = null;
        fileOutputStream = null;
        if (response == null) {
            return null;
        }
        File parentFile = this.target.getParentFile();
        boolean mkdirs = !parentFile.exists() ? parentFile.mkdirs() : true;
        if (mkdirs && !this.target.exists()) {
            mkdirs = this.target.createNewFile();
        }
        if (!mkdirs) {
            JDPayLog.e("Could't create file " + this.target.getAbsolutePath());
            return null;
        }
        byte[] bArr = new byte[DEFAULT_BUFFER_SIZE];
        try {
            try {
                try {
                    InputStream inputStream = response.getInputStream();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(this.target, true);
                    while (true) {
                        try {
                            read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                        } catch (IOException e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            JDPayLog.e(e);
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                                fileOutputStream = fileOutputStream;
                            }
                            return this.target;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    fileOutputStream2.close();
                    fileOutputStream = read;
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException unused2) {
            }
            return this.target;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
