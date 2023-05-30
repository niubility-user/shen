package com.meizu.cloud.pushsdk.handler.f.i;

import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: classes14.dex */
public class b {
    private final File a;

    public b(String str) {
        this.a = new File(str);
    }

    private void a(File file, ZipOutputStream zipOutputStream, String str) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.trim().length() == 0 ? "" : File.separator);
        sb.append(file.getName());
        String sb2 = sb.toString();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (File file2 : listFiles) {
                a(file2, zipOutputStream, sb2);
            }
            return;
        }
        DebugLogger.i("ZipTask", "current file " + sb2 + "/" + file.getName() + " size is " + (file.length() / 1024) + "KB");
        if (file.length() >= 10485760) {
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(sb2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read == -1) {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, read);
        }
    }

    private void c(Collection<File> collection, File file) throws Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        Iterator<File> it = collection.iterator();
        while (it.hasNext()) {
            a(it.next(), zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    public void b(String str, List<String> list) throws Exception {
        if (!this.a.exists()) {
            this.a.getParentFile().mkdirs();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            File file = new File(str + "/" + it.next());
            if (file.exists()) {
                arrayList.add(file);
            }
        }
        c(arrayList, this.a);
    }
}
