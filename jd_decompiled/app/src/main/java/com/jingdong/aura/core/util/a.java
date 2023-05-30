package com.jingdong.aura.core.util;

import android.os.Process;
import com.jingdong.aura.a.c.l;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class a {
    static final com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("AuraFileLock");

    /* renamed from: c  reason: collision with root package name */
    private static String f12167c;
    private static a d;
    private Map<String, C0397a> a = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jingdong.aura.core.util.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0397a {
        FileChannel a;
        RandomAccessFile b;

        /* renamed from: c  reason: collision with root package name */
        FileLock f12168c;
        int d;

        C0397a(a aVar, FileLock fileLock, int i2, RandomAccessFile randomAccessFile, FileChannel fileChannel) {
            this.f12168c = fileLock;
            this.d = i2;
            this.b = randomAccessFile;
            this.a = fileChannel;
        }
    }

    static {
        if (l.a.getApplicationContext() != null) {
            f12167c = i.a(Process.myPid());
        }
    }

    public static a a() {
        if (d == null) {
            d = new a();
        }
        return d;
    }

    public void b(File file) {
        C0397a c0397a;
        File file2 = new File(file.getParentFile().getAbsolutePath().concat("/lock"));
        if (file2.exists() && this.a.containsKey(file2.getAbsolutePath()) && (c0397a = this.a.get(file2.getAbsolutePath())) != null) {
            FileLock fileLock = c0397a.f12168c;
            RandomAccessFile randomAccessFile = c0397a.b;
            FileChannel fileChannel = c0397a.a;
            try {
                if (a(file2.getAbsolutePath()) <= 0) {
                    if (fileLock != null && fileLock.isValid()) {
                        fileLock.release();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                }
            } catch (Exception e2) {
                b.d(f12167c + " FileLock " + file.getParentFile().getAbsolutePath().concat("/lock") + " unlock FAIL! " + e2.getMessage());
            }
        }
    }

    private int a(String str, FileLock fileLock, RandomAccessFile randomAccessFile, FileChannel fileChannel) {
        Integer num;
        if (this.a.containsKey(str)) {
            C0397a c0397a = this.a.get(str);
            int i2 = c0397a.d;
            c0397a.d = i2 + 1;
            num = Integer.valueOf(i2);
        } else {
            Integer num2 = 1;
            this.a.put(str, new C0397a(this, fileLock, num2.intValue(), randomAccessFile, fileChannel));
            num = num2;
        }
        return num.intValue();
    }

    private int a(String str) {
        Integer num = 0;
        if (this.a.containsKey(str)) {
            C0397a c0397a = this.a.get(str);
            int i2 = c0397a.d - 1;
            c0397a.d = i2;
            num = Integer.valueOf(i2);
            if (num.intValue() <= 0) {
                this.a.remove(str);
            }
        }
        return num.intValue();
    }

    public boolean a(File file) {
        if (file == null) {
            return false;
        }
        try {
            File file2 = new File(file.getParentFile().getAbsolutePath().concat("/lock"));
            if (!file2.exists()) {
                file2.createNewFile();
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2.getAbsolutePath(), "rw");
            FileChannel channel = randomAccessFile.getChannel();
            FileLock lock = channel.lock();
            if (lock.isValid()) {
                a(file2.getAbsolutePath(), lock, randomAccessFile, channel);
                return true;
            }
            return false;
        } catch (Exception e2) {
            b.d(f12167c + " FileLock " + file.getParentFile().getAbsolutePath().concat("/lock") + " Lock FAIL! " + e2.getMessage());
            return false;
        }
    }
}
