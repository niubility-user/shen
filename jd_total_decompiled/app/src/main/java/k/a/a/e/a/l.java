package k.a.a.e.a;

import java.io.IOException;

/* loaded from: classes11.dex */
class l extends b<k.a.a.b.e> {
    public l(j jVar, k.a.a.f.i iVar, char[] cArr) throws IOException, k.a.a.c.a {
        super(jVar, iVar, cArr);
    }

    private byte[] m() throws IOException {
        byte[] bArr = new byte[12];
        l(bArr);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k.a.a.e.a.b
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public k.a.a.b.e k(k.a.a.f.i iVar, char[] cArr) throws k.a.a.c.a, IOException {
        return new k.a.a.b.e(cArr, iVar.f(), m());
    }
}
