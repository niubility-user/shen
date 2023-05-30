package g.f.a.a;

import android.media.AudioRecord;
import android.util.Log;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes12.dex */
public final class d extends InputStream {

    /* renamed from: i  reason: collision with root package name */
    private static final Logger f19490i = Logger.getLogger("MicrophoneInputStream");

    /* renamed from: g  reason: collision with root package name */
    private final b f19491g;

    /* renamed from: h  reason: collision with root package name */
    private final InputStream f19492h;

    /* loaded from: classes12.dex */
    private static class b extends InputStream implements Runnable {

        /* renamed from: k  reason: collision with root package name */
        private static final byte[] f19493k = new byte[1966080];

        /* renamed from: l  reason: collision with root package name */
        private static int f19494l;

        /* renamed from: m  reason: collision with root package name */
        private static int f19495m;

        /* renamed from: n  reason: collision with root package name */
        private static IOException f19496n;
        private static a o;

        /* renamed from: g  reason: collision with root package name */
        private long f19497g;

        /* renamed from: h  reason: collision with root package name */
        private volatile boolean f19498h;

        /* renamed from: i  reason: collision with root package name */
        int f19499i = 0;

        /* renamed from: j  reason: collision with root package name */
        int f19500j = 0;

        public b(int i2, int i3, InputStream inputStream, AudioRecord audioRecord) throws IOException {
            synchronized (b.class) {
                if (o == null) {
                    if (inputStream == null) {
                        if (audioRecord == null) {
                            try {
                                audioRecord = new AudioRecord(1, i3, 16, 2, 163840);
                                if (audioRecord.getState() == 1) {
                                    audioRecord.startRecording();
                                } else {
                                    throw new IOException("bad recorder, Recorder init failed...");
                                }
                            } catch (Exception unused) {
                                throw new IOException("bad recorder,start Recorder failed...audioSouce: " + i2 + " sample: " + i3);
                            }
                        }
                        int i4 = 0;
                        for (int i5 = 0; i5 < 10; i5++) {
                            int read = audioRecord.read(new byte[32], 0, 32);
                            i4 += read;
                            if (read > 0) {
                                break;
                            }
                        }
                        if (i4 > 0) {
                            if (audioRecord.getRecordingState() == 3) {
                                o = new a(audioRecord);
                            } else {
                                audioRecord.release();
                                throw new IOException("recorder start failed, RecordingState=" + audioRecord.getRecordingState());
                            }
                        } else {
                            audioRecord.release();
                            throw new IOException("bad recorder, read(byte[]) can't read audio data ");
                        }
                    } else {
                        o = new a(inputStream);
                    }
                    new Thread(this, "record").start();
                }
            }
            f19495m++;
            f(f19494l);
            d.f19490i.info("sUsingCount=" + f19495m + ", sInnerSourceInputStream=" + o);
        }

        private void g() throws IOException {
            byte[] bArr;
            int b;
            a aVar = o;
            if (aVar != null && (b = aVar.b((bArr = new byte[256]))) >= 0) {
                int i2 = f19494l;
                byte[] bArr2 = f19493k;
                int length = i2 % bArr2.length;
                int min = Math.min(bArr2.length - length, 256);
                int i3 = 256 - min;
                if (min > 0 && length >= 0) {
                    System.arraycopy(bArr, 0, bArr2, length, min);
                }
                if (i3 > 0) {
                    System.arraycopy(bArr, 0, bArr2, 0, i3);
                }
                f19494l += b;
            }
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a aVar;
            super.close();
            synchronized (this) {
                if (!this.f19498h) {
                    synchronized (b.class) {
                        int i2 = f19495m - 1;
                        f19495m = i2;
                        if (i2 == 0 && (aVar = o) != null) {
                            aVar.a();
                            o = null;
                            f19494l = 0;
                            f19496n = null;
                        }
                    }
                    d.f19490i.info("close(), sUsingCount=" + f19495m + ", sInnerSourceInputStream=" + o);
                }
                this.f19498h = true;
            }
        }

        public b f(long j2) {
            long j3;
            if (j2 < 0) {
                d.f19490i.warning("error position: " + j2);
                j3 = 0L;
            } else {
                j3 = j2;
            }
            while (j3 % 4 != 0) {
                j3--;
            }
            this.f19497g = j3;
            if (Log.isLoggable("MicrophoneInputStream", 3) || d.f19490i.isLoggable(Level.ALL)) {
                d.f19490i.info("position to: " + j3 + ", by raw postion: " + j2);
            }
            return this;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Runnable
        public void run() {
            while (f19495m > 0) {
                try {
                    g();
                } catch (IOException e2) {
                    f19496n = e2;
                }
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            if (i3 <= f19493k.length) {
                IOException iOException = f19496n;
                if (iOException == null) {
                    if (!this.f19498h) {
                        int i4 = 0;
                        for (int i5 = 0; i5 < 30 && f19494l - this.f19497g < i3; i5++) {
                            try {
                                Thread.sleep(1L);
                            } catch (InterruptedException e2) {
                                throw new InterruptedIOException("" + e2);
                            }
                        }
                        long j2 = this.f19497g;
                        if (f19494l - j2 >= i3) {
                            byte[] bArr2 = f19493k;
                            int length = (int) (j2 % bArr2.length);
                            int min = Math.min(i3, bArr2.length - length);
                            int i6 = i3 - min;
                            System.arraycopy(bArr2, length, bArr, i2, min);
                            if (i6 > 0) {
                                System.arraycopy(bArr2, 0, bArr, i2 + min, i6);
                            }
                            i4 = min + i6;
                            this.f19497g += i4;
                        }
                        int i7 = this.f19499i + i4;
                        this.f19499i = i7;
                        if (i7 > this.f19500j) {
                            d.f19490i.fine("mic:" + this.f19499i);
                            this.f19500j = this.f19500j + R2.attr.additionBottom;
                        }
                        return i4;
                    }
                    throw new IOException("mic stream closed");
                }
                throw iOException;
            }
            throw new IOException("buffer too long");
        }
    }

    public d(int i2) throws IOException {
        this(1, i2);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.f19492h.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class a {
        private static AudioRecord b;
        private InputStream a;

        a(InputStream inputStream) {
            this.a = inputStream;
        }

        public void a() throws IOException {
            InputStream inputStream = this.a;
            if (inputStream != null) {
                inputStream.close();
                return;
            }
            AudioRecord audioRecord = b;
            if (audioRecord != null) {
                audioRecord.release();
            }
        }

        public int b(byte[] bArr) throws IOException {
            InputStream inputStream = this.a;
            if (inputStream == null) {
                int read = b.read(bArr, 0, bArr.length);
                if (read >= 0) {
                    return read;
                }
                throw new IOException("recorder error #" + read);
            }
            return inputStream.read(bArr);
        }

        a(AudioRecord audioRecord) {
            b = audioRecord;
        }
    }

    public d(int i2, int i3) throws IOException {
        this(i2, i3, null);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return this.f19492h.read(bArr, 0, bArr.length);
    }

    public d(int i2, int i3, InputStream inputStream) throws IOException {
        this(1, i3, inputStream, null);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        return this.f19492h.read(bArr, i2, i3);
    }

    public d(int i2, int i3, InputStream inputStream, AudioRecord audioRecord) throws IOException {
        b bVar = new b(i2, i3, inputStream, audioRecord);
        this.f19491g = bVar;
        if (i3 == 16000) {
            this.f19492h = bVar;
            return;
        }
        throw new UnsupportedOperationException("bad sample, " + i3);
    }
}
