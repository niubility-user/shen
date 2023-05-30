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
    */
    private void f(byte[] bArr, int i2) {
        double log10;
        double d;
        c cVar;
        int i3 = 0;
        float f2 = 0.0f;
        if (i2 == 1) {
            while (i3 < bArr.length) {
                f2 += Math.abs((int) ((short) ((bArr[i3] & 255) | (bArr[i3 + 1] << 8))));
                i3 += 2;
            }
            log10 = Math.log10((f2 / (bArr.length * 2)) + 1.0f);
        } else if (i2 == 2) {
            float f3 = 0.0f;
            for (int i4 = 0; i4 < bArr.length; i4 += 2) {
                f3 += Math.abs((int) ((short) (bArr[i4] | (bArr[i4 + 1] << 8)))) / (bArr.length / 2);
            }
            int i5 = this.q + 1;
            this.q = i5;
            this.r[i5 % 5] = f3;
            while (i3 < 5) {
                f2 += this.r[i3];
                i3++;
            }
            log10 = Math.log10((f2 / 5.0f) + 1.0f);
        } else {
            d = 0.0d;
            cVar = this.f7217i;
            if (cVar == null) {
                int i6 = (int) d;
                cVar.changVolumLevel(i6, i6);
                return;
            }
            return;
        }
        d = log10 * 10.0d;
        cVar = this.f7217i;
        if (cVar == null) {
        }
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
