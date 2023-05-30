package com.jingdong.c.b.d;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.c.b.f;
import com.jingdong.c.b.h;
import com.jingdong.c.b.i;
import com.jingdong.c.b.j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

/* loaded from: classes10.dex */
public final class c implements f {
    private static String b() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static String c(Context context) {
        try {
            File file = new File(context.getFilesDir(), "Android/device");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, ".DEVICE");
            if (!file2.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(b().getBytes());
                fileOutputStream.close();
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "r");
            byte[] bArr = new byte[(int) randomAccessFile.length()];
            randomAccessFile.readFully(bArr);
            randomAccessFile.close();
            return new String(bArr);
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jingdong.c.b.f
    public final j a(f.a aVar) {
        h.a("Enter RandomUUIDInterceptor intercept()");
        com.jingdong.c.b.b a = aVar.a();
        i.c(a);
        String b = i.a().b("randomUUID");
        if (TextUtils.isEmpty(b)) {
            if (Build.VERSION.SDK_INT <= 29 && a.d()) {
                b = c(a.b());
            }
            if (TextUtils.isEmpty(b)) {
                b = b();
            }
            i.a().d("randomUUID", b);
        }
        j jVar = new j(a, false);
        jVar.b = b;
        jVar.f12306c = false;
        return jVar;
    }
}
