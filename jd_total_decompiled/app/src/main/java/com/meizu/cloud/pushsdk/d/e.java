package com.meizu.cloud.pushsdk.d;

import com.jingdong.common.utils.LangUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes14.dex */
public class e {
    private final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
    private final d b = new d("lo");

    /* renamed from: c  reason: collision with root package name */
    private BufferedWriter f15711c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements FileFilter {
        a(e eVar) {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.getName().endsWith(".log.txt");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Comparator<File> {
        b(e eVar) {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            int i2 = ((file.lastModified() - file2.lastModified()) > 0L ? 1 : ((file.lastModified() - file2.lastModified()) == 0L ? 0 : -1));
            if (i2 > 0) {
                return -1;
            }
            return i2 == 0 ? 0 : 1;
        }
    }

    public void a() throws IOException {
        BufferedWriter bufferedWriter = this.f15711c;
        if (bufferedWriter != null) {
            bufferedWriter.flush();
            this.f15711c.close();
            this.f15711c = null;
        }
    }

    void b(File file) {
        File[] listFiles = file.listFiles(new a(this));
        if (listFiles != null) {
            if (listFiles.length > 7) {
                Arrays.sort(listFiles, new b(this));
                for (int i2 = 7; i2 < listFiles.length; i2++) {
                    listFiles[i2].delete();
                }
            }
        }
    }

    public void c(String str) throws IOException {
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("create " + str + " dir failed!!!");
        }
        String format = this.a.format(new Date());
        File file2 = new File(str, format + ".log.txt");
        if (!file2.exists()) {
            if (file2.createNewFile()) {
                b(file);
            } else {
                String str2 = "create new file " + format + " failed !!!";
            }
        }
        this.f15711c = new BufferedWriter(new FileWriter(file2, true));
    }

    public void d(String str, String str2, String str3) throws IOException {
        if (this.f15711c != null) {
            this.f15711c.write(this.b.a((str + str2 + LangUtils.SINGLE_SPACE + str3).getBytes()));
            this.f15711c.write("\r\n");
        }
    }
}
