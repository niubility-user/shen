package g.g.a.o;

import android.media.AudioTrack;
import com.jingdong.sdk.platform.business.personal.R2;
import g.g.a.f;

/* loaded from: classes18.dex */
public class a {
    protected AudioTrack b;

    /* renamed from: c  reason: collision with root package name */
    protected int f19643c;

    /* renamed from: k  reason: collision with root package name */
    private int f19650k;

    /* renamed from: m  reason: collision with root package name */
    private int f19652m;
    protected d a = null;
    protected int d = 2;

    /* renamed from: e  reason: collision with root package name */
    protected int f19644e = 0;

    /* renamed from: f  reason: collision with root package name */
    protected int f19645f = 0;

    /* renamed from: g  reason: collision with root package name */
    protected int f19646g = 0;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f19647h = true;

    /* renamed from: i  reason: collision with root package name */
    protected boolean f19648i = true;

    /* renamed from: j  reason: collision with root package name */
    protected b f19649j = b.Idle;

    /* renamed from: l  reason: collision with root package name */
    private int f19651l = 2;

    public a(int i2, int i3, int i4) {
        this.b = null;
        int i5 = 0;
        this.f19643c = 0;
        this.f19650k = R2.id.rn_redbox_report_label;
        this.f19652m = 4;
        this.f19650k = i2;
        this.f19652m = i4;
        this.f19643c = AudioTrack.getMinBufferSize(i2, i4, i3);
        AudioTrack audioTrack = this.b;
        if (audioTrack != null) {
            audioTrack.stop();
            this.b.release();
            this.b = null;
            f.c("ITTSPlayer", "audioTrack16K null");
        }
        if (this.b == null) {
            while (true) {
                if (i5 >= 3) {
                    break;
                }
                f.c("ITTSPlayer", "Create AudioTrack: sampleRate=" + this.f19650k + ", audioFormat=" + i3 + ", channel" + this.f19652m);
                AudioTrack audioTrack2 = new AudioTrack(3, this.f19650k, this.f19652m, this.f19651l, this.f19643c, 1);
                this.b = audioTrack2;
                if (audioTrack2 != null) {
                    f.c("ITTSPlayer", "audioTrack success");
                    break;
                }
                i5++;
            }
            if (this.b == null) {
                f.c("ITTSPlayer", "audioTrack try 3, but null");
            }
        }
    }

    public void a(int i2) {
        f.c("ITTSPlayer", "setPlayCache=" + i2);
        this.d = i2;
    }

    public void b(String str, d dVar) {
        this.a = dVar;
    }
}
