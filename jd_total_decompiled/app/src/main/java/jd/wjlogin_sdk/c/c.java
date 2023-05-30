package jd.wjlogin_sdk.c;

import jd.wjlogin_sdk.util.t;

/* loaded from: classes11.dex */
public class c implements d {
    @Override // jd.wjlogin_sdk.c.d
    public String a(byte[] bArr) {
        try {
            return t.a(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return System.currentTimeMillis() + "";
        }
    }

    @Override // jd.wjlogin_sdk.c.d
    public byte[] a(String str, int i2) {
        try {
            if (i2 == 200) {
                return t.a(str);
            }
            if (i2 == 299) {
                return t.b(str);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
