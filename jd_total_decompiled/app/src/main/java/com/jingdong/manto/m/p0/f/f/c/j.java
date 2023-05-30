package com.jingdong.manto.m.p0.f.f.c;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes15.dex */
final class j extends a {
    private RandomAccessFile c(File file) {
        try {
            return new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void e() {
        RandomAccessFile c2 = c(this.b);
        c2.seek(0L);
        c2.write(new k(this.a.a(), this.b.length()).a());
        c2.close();
    }

    @Override // com.jingdong.manto.m.p0.f.f.c.a
    public void d() {
        try {
            super.d();
            e();
        } catch (IOException e2) {
            throw new RuntimeException("Error in applying wav header", e2);
        }
    }
}
