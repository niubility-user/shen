package com.jd.stat.common.relinker.a;

import com.facebook.soloader.MinElf;
import com.jd.stat.common.relinker.a.c;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes18.dex */
public class f implements c, Closeable {
    private final int a = MinElf.ELF_MAGIC;
    private final FileChannel b;

    public f(File file) throws FileNotFoundException {
        if (file != null && file.exists()) {
            this.b = new FileInputStream(file).getChannel();
            return;
        }
        throw new IllegalArgumentException("File is null or does not exist");
    }

    public c.b a() throws IOException {
        this.b.position(0L);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (c(allocate, 0L) == 1179403647) {
            short e2 = e(allocate, 4L);
            boolean z = e(allocate, 5L) == 2;
            if (e2 == 1) {
                return new d(z, this);
            }
            if (e2 == 2) {
                return new e(z, this);
            }
            throw new IllegalStateException("Invalid class type!");
        }
        throw new IllegalArgumentException("Invalid ELF Magic!");
    }

    public List<String> b() throws IOException {
        long j2;
        this.b.position(0L);
        ArrayList arrayList = new ArrayList();
        c.b a = a();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(a.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = a.f7016f;
        int i2 = 0;
        if (j3 == 65535) {
            j3 = a.a(0).a;
        }
        long j4 = 0;
        while (true) {
            if (j4 >= j3) {
                j2 = 0;
                break;
            }
            c.AbstractC0219c a2 = a.a(j4);
            if (a2.a == 2) {
                j2 = a2.b;
                break;
            }
            j4++;
        }
        if (j2 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j5 = 0;
        while (true) {
            c.a a3 = a.a(j2, i2);
            long j6 = j2;
            long j7 = a3.a;
            if (j7 == 1) {
                arrayList2.add(Long.valueOf(a3.b));
            } else if (j7 == 5) {
                j5 = a3.b;
            }
            i2++;
            if (a3.a == 0) {
                break;
            }
            j2 = j6;
        }
        if (j5 != 0) {
            long a4 = a(a, j3, j5);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList.add(a(allocate, ((Long) it.next()).longValue() + a4));
            }
            return arrayList;
        }
        throw new IllegalStateException("String table offset not found!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long c(ByteBuffer byteBuffer, long j2) throws IOException {
        a(byteBuffer, j2, 4);
        return byteBuffer.getInt() & 4294967295L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.b.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d(ByteBuffer byteBuffer, long j2) throws IOException {
        a(byteBuffer, j2, 2);
        return byteBuffer.getShort() & 65535;
    }

    protected short e(ByteBuffer byteBuffer, long j2) throws IOException {
        a(byteBuffer, j2, 1);
        return (short) (byteBuffer.get() & 255);
    }

    private long a(c.b bVar, long j2, long j3) throws IOException {
        for (long j4 = 0; j4 < j2; j4++) {
            c.AbstractC0219c a = bVar.a(j4);
            if (a.a == 1) {
                long j5 = a.f7020c;
                if (j5 <= j3 && j3 <= a.d + j5) {
                    return (j3 - j5) + a.b;
                }
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    protected String a(ByteBuffer byteBuffer, long j2) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j3 = 1 + j2;
            short e2 = e(byteBuffer, j2);
            if (e2 != 0) {
                sb.append((char) e2);
                j2 = j3;
            } else {
                return sb.toString();
            }
        }
    }

    protected void a(ByteBuffer byteBuffer, long j2, int i2) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        long j3 = 0;
        while (j3 < i2) {
            int read = this.b.read(byteBuffer, j2 + j3);
            if (read == -1) {
                throw new EOFException();
            }
            j3 += read;
        }
        byteBuffer.position(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long b(ByteBuffer byteBuffer, long j2) throws IOException {
        a(byteBuffer, j2, 8);
        return byteBuffer.getLong();
    }
}
