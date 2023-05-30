package g.g.a.d;

import com.jd.ai.asr.jni.JDOpusJni;

/* loaded from: classes18.dex */
public class d implements b {
    JDOpusJni a = new JDOpusJni();

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(int i2) {
        g.g.a.f.c("OpusDecoder", "new OpusDecoder :sample=" + i2);
        JDOpusJni.Initial(i2, 1);
    }

    @Override // g.g.a.d.b
    public byte[] a(byte[] bArr, boolean z, boolean z2) {
        return JDOpusJni.process(bArr, bArr.length, z2);
    }

    @Override // g.g.a.d.b
    public void stop() {
    }
}
