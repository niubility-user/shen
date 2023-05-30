package g.g.a.d;

/* loaded from: classes18.dex */
public class a implements b {
    b a;

    /* renamed from: g.g.a.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    static /* synthetic */ class C0839a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.g.a.c.values().length];
            a = iArr;
            try {
                iArr[g.g.a.c.AUDIO_ENCODE_PCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.g.a.c.AUDIO_ENCODE_MP3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.g.a.c.AUDIO_ENCODE_WAV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[g.g.a.c.AUDIO_ENCODE_OPUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public a(g.g.a.c cVar, int i2) {
        this.a = null;
        int i3 = C0839a.a[cVar.ordinal()];
        if (i3 == 1) {
            g.g.a.f.b("AudioDecoder", "new PCMDecoder");
            this.a = new e();
        } else if (i3 == 2) {
            g.g.a.f.b("AudioDecoder", "new MP3Decoder");
            this.a = new c(i2);
        } else if (i3 == 3) {
            g.g.a.f.b("AudioDecoder", "new WAVDecoder");
            this.a = new f();
        } else if (i3 != 4) {
            g.g.a.f.b("AudioDecoder", "AudioDecoder: " + cVar);
            this.a = null;
        } else {
            g.g.a.f.b("AudioDecoder", "new OpusDecoder, sample=" + i2);
            this.a = new d(i2);
        }
    }

    @Override // g.g.a.d.b
    public byte[] a(byte[] bArr, boolean z, boolean z2) {
        return this.a.a(bArr, z, z2);
    }

    @Override // g.g.a.d.b
    public void stop() {
        this.a.stop();
    }
}
