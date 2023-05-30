package com.jingdong.sdk.talos.inner.a;

import com.google.common.net.HttpHeaders;
import com.jingdong.sdk.talos.inner.e;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes10.dex */
public final class c extends a {

    /* renamed from: l  reason: collision with root package name */
    public List<File> f15491l = new ArrayList();

    /* renamed from: m  reason: collision with root package name */
    HashMap<String, String> f15492m = new HashMap<>();

    public c() {
        d("Connection", "Keep-Alive");
        d("Charset", "UTF-8");
        d(HttpHeaders.CONTENT_TYPE, "multipart/form-data;boundary=---------------------------823928434");
    }

    @Override // com.jingdong.sdk.talos.inner.a.a
    public final void b(OutputStream outputStream) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] bArr = new byte[2048];
        for (Map.Entry<String, String> entry : this.f15492m.entrySet()) {
            dataOutputStream.writeBytes("-----------------------------823928434\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
            dataOutputStream.writeBytes(entry.getValue());
            dataOutputStream.writeBytes("\r\n");
            e.d.b("MultipartRequest", "write filed [" + entry.getKey() + "]:" + entry.getValue());
        }
        for (File file : this.f15491l) {
            dataOutputStream.writeBytes("-----------------------------823928434\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            dataOutputStream.writeBytes("Content-Type: text/plain\r\n\r\n");
            dataOutputStream.writeBytes("\r\n");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    dataOutputStream.write(bArr, 0, read);
                }
            }
            dataOutputStream.writeBytes("\r\n");
            fileInputStream.close();
            e.d.b("MultipartRequest", "write file :" + file.getAbsolutePath());
        }
        dataOutputStream.writeBytes("-----------------------------823928434--\r\n");
        dataOutputStream.flush();
    }

    public final void g(String str, String str2) {
        this.f15492m.put(str, str2);
    }
}
