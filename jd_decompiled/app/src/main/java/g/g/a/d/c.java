package g.g.a.d;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.Surface;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes18.dex */
public class c implements b {
    private MediaCodec a;
    private MediaFormat b;

    /* renamed from: c  reason: collision with root package name */
    private MediaCodec.BufferInfo f19580c;
    private ByteBuffer[] d;

    /* renamed from: e  reason: collision with root package name */
    private ByteBuffer[] f19581e;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f19582f;

    /* renamed from: g  reason: collision with root package name */
    private byte[] f19583g;

    /* renamed from: h  reason: collision with root package name */
    private byte[] f19584h;

    /* renamed from: i  reason: collision with root package name */
    private BlockingQueue<byte[]> f19585i;

    /* renamed from: j  reason: collision with root package name */
    private int f19586j;

    /* renamed from: k  reason: collision with root package name */
    private int f19587k;

    /* renamed from: l  reason: collision with root package name */
    private int f19588l;

    /* renamed from: m  reason: collision with root package name */
    private int f19589m;

    /* renamed from: n  reason: collision with root package name */
    private int f19590n;
    private int o;
    int p;
    int q;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(int i2) {
        new MediaExtractor();
        new ArrayList();
        this.f19585i = new LinkedBlockingQueue();
        this.f19586j = 0;
        this.f19587k = 1;
        this.f19588l = 0;
        this.f19589m = 1;
        this.f19590n = 0;
        this.o = 0;
        this.p = R2.id.rn_redbox_report_label;
        this.q = R2.id.rn_redbox_report_label;
        g.g.a.f.c("MP3Decoder", "new MP3Decoder :sample=" + i2);
        this.f19582f = null;
        this.b = MediaFormat.createAudioFormat("audio/mpeg", i2, 1);
        try {
            if (this.a == null) {
                this.a = MediaCodec.createDecoderByType("audio/mpeg");
            }
            this.a.configure(this.b, (Surface) null, (MediaCrypto) null, 0);
            MediaCodec mediaCodec = this.a;
            if (mediaCodec == null) {
                return;
            }
            mediaCodec.start();
            ByteBuffer[] inputBuffers = this.a.getInputBuffers();
            this.d = inputBuffers;
            if (inputBuffers == null) {
                g.g.a.f.b("MP3Decoder", "new MP3Decoder :inputBuffers null=");
            }
            ByteBuffer[] outputBuffers = this.a.getOutputBuffers();
            this.f19581e = outputBuffers;
            if (outputBuffers == null) {
                g.g.a.f.b("MP3Decoder", "new MP3Decoder :outputBuffers null=");
            }
            this.f19580c = new MediaCodec.BufferInfo();
            g.g.a.f.c("MP3Decoder", "outputBuffers size=" + this.f19581e.length);
            g.g.a.f.c("MP3Decoder", "inputBuffers size=" + this.d.length);
        } catch (IOException e2) {
            g.g.a.f.c("MP3Decoder", "exception=" + e2.toString());
        }
    }

    @Override // g.g.a.d.b
    public byte[] a(byte[] bArr, boolean z, boolean z2) {
        byte[] bArr2;
        int i2;
        byte[] bArr3 = null;
        this.f19584h = null;
        char c2 = 1;
        if (z) {
            this.f19582f = null;
            byte[] c3 = c(bArr, 0, 4);
            b(c3[1], 0);
            this.f19586j = b(c3[2], 2);
            this.f19587k = b(c3[2], 3);
            this.f19588l = b(c3[2], 4);
            this.f19589m = b(c3[2], 5);
            this.f19590n = b(c3[2], 6);
            int b = b(c3[2], 7);
            this.o = b;
            this.q = R2.id.rn_redbox_report_label;
            int i3 = this.f19587k;
            if (i3 == 0 && this.f19586j == 1) {
                this.q = R2.styleable.MaterialCalendarItem_android_insetRight;
            } else if (i3 == 1 && this.f19586j == 0) {
                this.q = R2.id.rn_redbox_report_label;
            } else {
                g.g.a.f.f("MP3Decoder", "sample1 sample not suport=" + (this.f19587k & 1) + (this.f19586j & 1));
                return null;
            }
            this.p = R2.id.rn_redbox_report_label;
            if (b == 0 && this.f19590n == 1 && this.f19589m == 0 && this.f19588l == 0) {
                this.p = 32000;
            } else if (b == 0 && this.f19590n == 1 && this.f19589m == 1 && this.f19588l == 0) {
                this.p = 48000;
            } else {
                g.g.a.f.f("MP3Decoder", "getPCMData:bitrate not suport =" + (this.o & 1) + (this.f19590n & 1) + (this.f19589m & 1) + (this.f19588l & 1));
                return null;
            }
        }
        int i4 = this.q;
        int i5 = (this.p * 72) / i4;
        int i6 = (576000 / i4) * 1000;
        this.f19583g = null;
        byte[] bArr4 = this.f19582f;
        if (bArr4 != null) {
            if (bArr4.length != 0) {
                byte[] bArr5 = new byte[bArr4.length + bArr.length];
                this.f19583g = bArr5;
                System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
                System.arraycopy(bArr, 0, this.f19583g, this.f19582f.length, bArr.length);
                this.f19582f = null;
            } else {
                this.f19583g = bArr;
            }
        } else {
            this.f19583g = bArr;
        }
        byte[] bArr6 = this.f19583g;
        if (bArr6.length < i5) {
            this.f19582f = null;
            byte[] bArr7 = new byte[bArr6.length];
            this.f19582f = bArr7;
            System.arraycopy(bArr6, 0, bArr7, 0, bArr6.length);
            return null;
        }
        byte[] c4 = c(bArr6, 0, 4);
        b(c4[1], 0);
        b(c4[2], 2);
        b(c4[2], 3);
        b(c4[2], 4);
        b(c4[2], 5);
        b(c4[2], 6);
        b(c4[2], 7);
        int length = this.f19583g.length / i5;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i7 >= this.f19583g.length) {
                break;
            }
            int dequeueInputBuffer = this.a.dequeueInputBuffer(-1L);
            if (dequeueInputBuffer >= 0) {
                ByteBuffer byteBuffer = this.d[dequeueInputBuffer];
                byteBuffer.clear();
                byte[] c5 = c(this.f19583g, i7, i5);
                int i10 = i7 + i5;
                if (c5[0] != -1 && c5[c2] != -13) {
                    g.g.a.f.c("MP3Decoder", "invalid head, give up=");
                    this.f19584h = bArr3;
                    break;
                }
                byteBuffer.put(c5);
                long j2 = i8 * i6;
                i8++;
                if (z2) {
                    this.a.queueInputBuffer(dequeueInputBuffer, 0, 0, 0L, 4);
                    i2 = -1;
                } else {
                    i2 = -1;
                    this.a.queueInputBuffer(dequeueInputBuffer, 0, c5.length, j2, 0);
                }
                boolean z3 = false;
                do {
                    int dequeueOutputBuffer = this.a.dequeueOutputBuffer(this.f19580c, -1L);
                    if (dequeueOutputBuffer == -3) {
                        this.f19581e = this.a.getOutputBuffers();
                    } else if (dequeueOutputBuffer != -2 && dequeueOutputBuffer != i2) {
                        ByteBuffer byteBuffer2 = this.f19581e[dequeueOutputBuffer];
                        int i11 = this.f19580c.size;
                        byte[] bArr8 = new byte[i11];
                        byteBuffer2.get(bArr8);
                        i9 += i11;
                        this.f19585i.add(bArr8);
                        byteBuffer2.clear();
                        this.a.releaseOutputBuffer(dequeueOutputBuffer, false);
                        z3 = true;
                    }
                } while (!z3);
                if (i8 >= length) {
                    int i12 = length * i5;
                    byte[] bArr9 = this.f19583g;
                    if (i12 < bArr9.length) {
                        this.f19582f = c(bArr9, i12, bArr9.length - i12);
                    }
                    this.f19584h = null;
                    this.f19584h = new byte[i9];
                    int i13 = 0;
                    while (true) {
                        byte[] poll = this.f19585i.poll();
                        if (poll == null) {
                            break;
                        }
                        System.arraycopy(poll, 0, this.f19584h, i13, poll.length);
                        i13 += poll.length;
                    }
                } else {
                    bArr2 = null;
                    i7 = i10;
                }
            } else {
                bArr2 = bArr3;
            }
            bArr3 = bArr2;
            c2 = 1;
        }
        return this.f19584h;
    }

    public int b(byte b, int i2) {
        return (b >> i2) & 1;
    }

    public byte[] c(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return bArr2;
    }

    @Override // g.g.a.d.b
    public void stop() {
        this.f19582f = null;
    }
}
