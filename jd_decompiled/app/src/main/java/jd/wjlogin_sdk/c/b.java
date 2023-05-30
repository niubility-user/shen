package jd.wjlogin_sdk.c;

import jd.wjlogin_sdk.util.t;

/* loaded from: classes11.dex */
public class b implements d {
    String a;

    @Override // jd.wjlogin_sdk.c.d
    public String a(byte[] bArr) {
        try {
            String a = t.a();
            this.a = a;
            return t.a(bArr, a);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // jd.wjlogin_sdk.c.d
    public byte[] a(String str, int i2) {
        if (i2 == 200) {
            try {
                return t.a(str, this.a);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
