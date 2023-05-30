package com.getkeepsafe.relinker.g;

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

/* loaded from: classes12.dex */
public class i implements Closeable {

    /* renamed from: g  reason: collision with root package name */
    private final FileChannel f1062g;

    public i(File file) throws FileNotFoundException {
        if (file != null && file.exists()) {
            this.f1062g = new FileInputStream(file).getChannel();
            return;
        }
        throw new IllegalArgumentException("File is null or does not exist");
    }

    private long f(d dVar, long j2, long j3) throws IOException {
        for (long j4 = 0; j4 < j2; j4++) {
            e b = dVar.b(j4);
            if (b.a == 1) {
                long j5 = b.f1059c;
                if (j5 <= j3 && j3 <= b.d + j5) {
                    return (j3 - j5) + b.b;
                }
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1062g.close();
    }

    public d g() throws IOException {
        this.f1062g.position(0L);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (n(allocate, 0L) == 1179403647) {
            short j2 = j(allocate, 4L);
            boolean z = j(allocate, 5L) == 2;
            if (j2 == 1) {
                return new g(z, this);
            }
            if (j2 == 2) {
                return new h(z, this);
            }
            throw new IllegalStateException("Invalid class type!");
        }
        throw new IllegalArgumentException("Invalid ELF Magic!");
    }

    public List<String> h() throws IOException {
        long j2;
        this.f1062g.position(0L);
        ArrayList arrayList = new ArrayList();
        d g2 = g();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(g2.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = g2.f1057e;
        int i2 = 0;
        if (j3 == 65535) {
            j3 = g2.c(0).a;
        }
        long j4 = 0;
        while (true) {
            if (j4 >= j3) {
                j2 = 0;
                break;
            }
            e b = g2.b(j4);
            if (b.a == 2) {
                j2 = b.b;
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
            c a = g2.a(j2, i2);
            long j6 = j2;
            long j7 = a.a;
            if (j7 == 1) {
                arrayList2.add(Long.valueOf(a.b));
            } else if (j7 == 5) {
                j5 = a.b;
            }
            i2++;
            if (a.a == 0) {
                break;
            }
            j2 = j6;
        }
        if (j5 != 0) {
            long f2 = f(g2, j3, j5);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList.add(m(allocate, ((Long) it.next()).longValue() + f2));
            }
            return arrayList;
        }
        throw new IllegalStateException("String table offset not found!");
    }

    protected void i(ByteBuffer byteBuffer, long j2, int i2) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        long j3 = 0;
        while (j3 < i2) {
            int read = this.f1062g.read(byteBuffer, j2 + j3);
            if (read == -1) {
                throw new EOFException();
            }
            j3 += read;
        }
        byteBuffer.position(0);
    }

    protected short j(ByteBuffer byteBuffer, long j2) throws IOException {
        i(byteBuffer, j2, 1);
        return (short) (byteBuffer.get() & 255);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int k(ByteBuffer byteBuffer, long j2) throws IOException {
        i(byteBuffer, j2, 2);
        return byteBuffer.getShort() & 65535;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long l(ByteBuffer byteBuffer, long j2) throws IOException {
        i(byteBuffer, j2, 8);
        return byteBuffer.getLong();
    }

    protected String m(ByteBuffer byteBuffer, long j2) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j3 = 1 + j2;
            short j4 = j(byteBuffer, j2);
            if (j4 != 0) {
                sb.append((char) j4);
                j2 = j3;
            } else {
                return sb.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long n(ByteBuffer byteBuffer, long j2) throws IOException {
        i(byteBuffer, j2, 4);
        return byteBuffer.getInt() & 4294967295L;
    }
}
