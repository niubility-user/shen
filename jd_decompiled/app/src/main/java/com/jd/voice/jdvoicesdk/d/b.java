package com.jd.voice.jdvoicesdk.d;

import android.content.Context;
import android.media.AudioRecord;
import com.jd.voice.jdvoicesdk.util.SpeexTool;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes18.dex */
public class b extends Thread {
    private static volatile boolean s = true;

    /* renamed from: g  reason: collision with root package name */
    private Context f7215g;

    /* renamed from: i  reason: collision with root package name */
    private c f7217i;

    /* renamed from: j  reason: collision with root package name */
    private InterfaceC0226b f7218j;

    /* renamed from: k  reason: collision with root package name */
    private AudioRecord f7219k;

    /* renamed from: l  reason: collision with root package name */
    private File f7220l;

    /* renamed from: m  reason: collision with root package name */
    private String f7221m;

    /* renamed from: n  reason: collision with root package name */
    private String f7222n;
    private FileOutputStream o;
    int p;

    /* renamed from: h  reason: collision with root package name */
    private int f7216h = R2.id.rn_redbox_report_label;
    private int q = -1;
    private float[] r = new float[5];

    /* loaded from: classes18.dex */
    class a implements Runnable {

        /* renamed from: h  reason: collision with root package name */
        private final /* synthetic */ Runnable f7224h;

        a(Runnable runnable) {
            this.f7224h = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (com.jd.voice.jdvoicesdk.a.a.equals("wav") || com.jd.voice.jdvoicesdk.a.a.equals("ogg")) {
                    b bVar = b.this;
                    bVar.g(bVar.f7221m, b.this.f7222n);
                    new File(b.this.f7221m).delete();
                }
                if (com.jd.voice.jdvoicesdk.a.a.equals("ogg")) {
                    SpeexTool.speekEncode(b.this.f7222n);
                    new File(b.this.f7222n).delete();
                }
                this.f7224h.run();
            } catch (Exception e2) {
                if (b.this.f7218j != null) {
                    b.this.f7218j.onError(new com.jd.voice.jdvoicesdk.c.a(0, "\u6587\u4ef6\u8f6c\u5316\u5931\u8d25"));
                }
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: com.jd.voice.jdvoicesdk.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    public interface InterfaceC0226b {
        void onError(com.jd.voice.jdvoicesdk.c.a aVar);
    }

    /* loaded from: classes18.dex */
    public interface c {
        void changVolumLevel(int i2, int i3);
    }

    public b(Context context, c cVar, InterfaceC0226b interfaceC0226b) {
        this.f7221m = "";
        this.f7222n = "";
        this.f7215g = context;
        this.f7221m = com.jd.voice.jdvoicesdk.util.a.b(context, ".pcm");
        this.f7222n = com.jd.voice.jdvoicesdk.util.a.b(this.f7215g, ".wav");
        this.f7220l = new File(this.f7221m);
        this.f7217i = cVar;
        this.f7218j = interfaceC0226b;
    }

    private void a(FileOutputStream fileOutputStream, long j2, long j3, long j4, int i2, long j5) throws IOException {
        fileOutputStream.write(new byte[]{82, 73, 70, 70, (byte) (j3 & 255), (byte) ((j3 >> 8) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 24) & 255), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte) i2, 0, (byte) (j4 & 255), (byte) ((j4 >> 8) & 255), (byte) ((j4 >> 16) & 255), (byte) ((j4 >> 24) & 255), (byte) (j5 & 255), (byte) ((j5 >> 8) & 255), (byte) ((j5 >> 16) & 255), (byte) ((j5 >> 24) & 255), 4, 0, 16, 0, ReplyCode.reply0x64, 97, 116, 97, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) (255 & (j2 >> 24))}, 0, 44);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void f(byte[] r11, int r12) {
        /*
            r10 = this;
            r0 = 4621819117588971520(0x4024000000000000, double:10.0)
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            r4 = 0
            r5 = 2
            r6 = 1
            if (r12 == r6) goto L49
            if (r12 == r5) goto Lf
            r11 = 0
            goto L59
        Lf:
            r12 = 0
            r7 = 0
        L11:
            int r8 = r11.length
            if (r12 < r8) goto L33
            int r11 = r10.q
            int r11 = r11 + r6
            r10.q = r11
            float[] r12 = r10.r
            r8 = 5
            int r11 = r11 % r8
            r12[r11] = r7
        L1f:
            if (r3 < r8) goto L2b
            r11 = 1084227584(0x40a00000, float:5.0)
            float r4 = r4 / r11
            float r4 = r4 + r2
            double r11 = (double) r4
            double r11 = java.lang.Math.log10(r11)
            goto L57
        L2b:
            float[] r11 = r10.r
            r11 = r11[r3]
            float r4 = r4 + r11
            int r3 = r3 + 1
            goto L1f
        L33:
            r8 = r11[r12]
            int r9 = r12 + 1
            r9 = r11[r9]
            int r9 = r9 << 8
            r8 = r8 | r9
            short r8 = (short) r8
            int r8 = java.lang.Math.abs(r8)
            int r9 = r11.length
            int r9 = r9 / r5
            int r8 = r8 / r9
            float r8 = (float) r8
            float r7 = r7 + r8
            int r12 = r12 + 2
            goto L11
        L49:
            int r12 = r11.length
            if (r3 < r12) goto L62
            int r11 = r11.length
            int r11 = r11 * 2
            float r11 = (float) r11
            float r4 = r4 / r11
            float r4 = r4 + r2
            double r11 = (double) r4
            double r11 = java.lang.Math.log10(r11)
        L57:
            double r11 = r11 * r0
        L59:
            com.jd.voice.jdvoicesdk.d.b$c r0 = r10.f7217i
            if (r0 == 0) goto L61
            int r11 = (int) r11
            r0.changVolumLevel(r11, r11)
        L61:
            return
        L62:
            r12 = r11[r3]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r6 = r3 + 1
            r6 = r11[r6]
            int r6 = r6 << 8
            r12 = r12 | r6
            short r12 = (short) r12
            int r12 = java.lang.Math.abs(r12)
            float r12 = (float) r12
            float r4 = r4 + r12
            int r3 = r3 + 2
            goto L49
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.voice.jdvoicesdk.d.b.f(byte[], int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g(String str, String str2) {
        int i2 = this.f7216h;
        long j2 = i2;
        long j3 = ((i2 * 16) * 1) / 8;
        byte[] bArr = new byte[this.p];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            long size = fileInputStream.getChannel().size();
            FileOutputStream fileOutputStream2 = fileOutputStream;
            a(fileOutputStream, size, size + 36, j2, 1, j3);
            while (fileInputStream.read(bArr) != -1) {
                FileOutputStream fileOutputStream3 = fileOutputStream2;
                fileOutputStream3.write(bArr);
                fileOutputStream2 = fileOutputStream3;
            }
            fileInputStream.close();
            fileOutputStream2.close();
        } catch (FileNotFoundException e2) {
            s = true;
            if (this.f7218j != null) {
                this.f7218j.onError(new com.jd.voice.jdvoicesdk.c.a(0, "\u751f\u6210wav\u6587\u4ef6\u51fa\u9519"));
            }
            e2.printStackTrace();
        } catch (IOException e3) {
            s = true;
            if (this.f7218j != null) {
                this.f7218j.onError(new com.jd.voice.jdvoicesdk.c.a(0, "\u751f\u6210wav\u6587\u4ef6\u51fa\u9519"));
            }
            e3.printStackTrace();
        }
    }

    public void h() {
        if (s) {
            s = false;
            start();
        }
    }

    public void i(Runnable runnable) {
        s = true;
        new Thread(new a(runnable)).start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        byte[] bArr;
        this.p = AudioRecord.getMinBufferSize(this.f7216h, 16, 2);
        AudioRecord audioRecord = this.f7219k;
        if (audioRecord != null) {
            audioRecord.stop();
            this.f7219k.release();
            this.f7219k = null;
        }
        try {
            try {
                this.f7219k = new AudioRecord(1, this.f7216h, 16, 2, this.p);
                this.o = new FileOutputStream(this.f7220l);
                bArr = new byte[this.p];
            } catch (Exception e2) {
                s = true;
                if (this.f7218j != null) {
                    this.f7218j.onError(new com.jd.voice.jdvoicesdk.c.a(0, "\u5f55\u97f3\u673a\u7533\u8bf7\u5931\u8d25"));
                }
                e2.printStackTrace();
                AudioRecord audioRecord2 = this.f7219k;
                if (audioRecord2 == null || audioRecord2.getState() != 1) {
                    return;
                }
            }
            if (this.f7219k.getState() != 1) {
                AudioRecord audioRecord3 = this.f7219k;
                if (audioRecord3 == null || audioRecord3.getState() != 1) {
                    return;
                }
                this.f7219k.stop();
                this.f7219k.release();
                this.f7219k = null;
                return;
            }
            this.f7219k.startRecording();
            while (!s) {
                int read = this.f7219k.read(bArr, 0, this.p);
                String str = "bufferReadResult = " + read;
                if (read > 0) {
                    this.o.write(bArr, 0, read);
                    f(bArr, 2);
                } else {
                    this.f7219k.startRecording();
                }
            }
            AudioRecord audioRecord4 = this.f7219k;
            if (audioRecord4 == null || audioRecord4.getState() != 1) {
                return;
            }
            this.f7219k.stop();
            this.f7219k.release();
            this.f7219k = null;
        } catch (Throwable th) {
            AudioRecord audioRecord5 = this.f7219k;
            if (audioRecord5 != null && audioRecord5.getState() == 1) {
                this.f7219k.stop();
                this.f7219k.release();
                this.f7219k = null;
            }
            throw th;
        }
    }
}
