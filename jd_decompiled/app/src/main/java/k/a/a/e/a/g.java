package k.a.a.e.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes11.dex */
public class g extends RandomAccessFile {

    /* renamed from: g  reason: collision with root package name */
    private long f20216g;

    /* renamed from: h  reason: collision with root package name */
    private File[] f20217h;

    /* renamed from: i  reason: collision with root package name */
    private RandomAccessFile f20218i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f20219j;

    /* renamed from: k  reason: collision with root package name */
    private int f20220k;

    /* renamed from: l  reason: collision with root package name */
    private String f20221l;

    public g(File file, String str, File[] fileArr) throws IOException {
        super(file, str);
        this.f20219j = new byte[1];
        this.f20220k = 0;
        super.close();
        if (!k.a.a.f.o.e.WRITE.getValue().equals(str)) {
            f(fileArr);
            this.f20218i = new RandomAccessFile(file, str);
            this.f20217h = fileArr;
            this.f20216g = file.length();
            this.f20221l = str;
            return;
        }
        throw new IllegalArgumentException("write mode is not allowed for NumberedSplitRandomAccessFile");
    }

    private void f(File[] fileArr) throws IOException {
        int i2 = 1;
        for (File file : fileArr) {
            String f2 = k.a.a.i.c.f(file);
            try {
                if (i2 != Integer.parseInt(f2)) {
                    throw new IOException("Split file number " + i2 + " does not exist");
                }
                i2++;
            } catch (NumberFormatException unused) {
                throw new IOException("Split file extension not in expected format. Found: " + f2 + " expected of format: .001, .002, etc");
            }
        }
    }

    private void h(int i2) throws IOException {
        if (this.f20220k == i2) {
            return;
        }
        if (i2 <= this.f20217h.length - 1) {
            RandomAccessFile randomAccessFile = this.f20218i;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            this.f20218i = new RandomAccessFile(this.f20217h[i2], this.f20221l);
            this.f20220k = i2;
            return;
        }
        throw new IOException("split counter greater than number of split files");
    }

    public void g() throws IOException {
        h(this.f20217h.length - 1);
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() throws IOException {
        return this.f20218i.getFilePointer();
    }

    public void i(long j2) throws IOException {
        this.f20218i.seek(j2);
    }

    @Override // java.io.RandomAccessFile
    public long length() throws IOException {
        return this.f20218i.length();
    }

    @Override // java.io.RandomAccessFile
    public int read() throws IOException {
        if (read(this.f20219j) == -1) {
            return -1;
        }
        return this.f20219j[0] & 255;
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j2) throws IOException {
        int i2 = (int) (j2 / this.f20216g);
        if (i2 != this.f20220k) {
            h(i2);
        }
        this.f20218i.seek(j2 - (i2 * this.f20216g));
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(int i2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
        throw null;
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int read = this.f20218i.read(bArr, i2, i3);
        if (read == -1) {
            int i4 = this.f20220k;
            if (i4 == this.f20217h.length - 1) {
                return -1;
            }
            h(i4 + 1);
            return read(bArr, i2, i3);
        }
        return read;
    }
}
