package jd.wjlogin_sdk.c;

import android.util.Pair;
import java.io.IOException;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public abstract class a implements e {
    d a;
    jd.wjlogin_sdk.net.c b;

    /* renamed from: c */
    Pair<Integer, byte[]> f19713c;
    Pair<Byte, jd.wjlogin_sdk.tlvtype.a> d;

    /* renamed from: e */
    short f19714e;

    /* renamed from: f */
    short f19715f;

    public a(jd.wjlogin_sdk.net.c cVar, short s, short s2) {
        this.b = cVar;
        this.f19714e = s;
        this.f19715f = s2;
    }

    private int b(byte[] bArr) {
        if (bArr == null) {
            return -1;
        }
        return bArr.length >= 31 ? 0 : 1;
    }

    @Override // jd.wjlogin_sdk.c.e
    public String a() {
        return this.b.a();
    }

    @Override // jd.wjlogin_sdk.c.e
    public Pair<Byte, jd.wjlogin_sdk.tlvtype.a> d() {
        return this.d;
    }

    public Pair<Byte, jd.wjlogin_sdk.tlvtype.a> e() {
        try {
            byte[] a = a(new String((byte[]) this.f19713c.second), ((Integer) this.f19713c.first).intValue());
            int b = b(a);
            if (b != 0) {
                if (b != 1) {
                    return null;
                }
                jd.wjlogin_sdk.common.f.a().handleHeaderByteBelow31(a);
                jd.wjlogin_sdk.common.f.a().reportNewLoginResult(ReplyCode.reply0xa7, this.f19714e, this.f19715f);
                return null;
            }
            jd.wjlogin_sdk.d.a aVar = new jd.wjlogin_sdk.d.a(a);
            byte n2 = aVar.a().n();
            Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair = new Pair<>(Byte.valueOf(n2), aVar.b());
            this.d = pair;
            return pair;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // jd.wjlogin_sdk.c.e
    public String a(byte[] bArr) {
        try {
            return this.a.a(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private byte[] a(String str, int i2) {
        return this.a.a(str, i2);
    }

    public int a(Throwable th) {
        return th.getCause() instanceof IOException ? -101 : -100;
    }
}
