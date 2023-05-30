package jd.wjlogin_sdk.c;

import android.util.Pair;
import com.tencent.smtt.sdk.ProgressListener;
import jd.wjlogin_sdk.net.NetworkException;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public class k extends a {
    public k(jd.wjlogin_sdk.net.c cVar, short s, short s2) {
        super(cVar, s, s2);
        this.a = new c();
    }

    @Override // jd.wjlogin_sdk.c.e
    public int b() {
        Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair;
        Pair<Integer, byte[]> pair2;
        Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair3;
        try {
            this.f19713c = this.b.a(this);
            this.d = e();
            Pair<Integer, byte[]> pair4 = this.f19713c;
            if (pair4 != null && ((Integer) pair4.first).intValue() == 299 && (pair = this.d) != null) {
                boolean z = false;
                switch (((Byte) pair.first).byteValue()) {
                    case -19:
                        this.f19713c = this.b.a(this);
                        this.d = e();
                        z = true;
                        break;
                    case -18:
                        if (jd.wjlogin_sdk.common.c.i()) {
                            this.f19713c = this.b.a(this);
                            this.d = e();
                            z = true;
                            break;
                        }
                        break;
                    case ProgressListener.VERIFY_RENAME_FAILED /* -17 */:
                        if (jd.wjlogin_sdk.common.c.j()) {
                            this.f19713c = this.b.a(this);
                            this.d = e();
                            z = true;
                            break;
                        }
                        break;
                }
                if (z && (pair2 = this.f19713c) != null && ((Integer) pair2.first).intValue() == 299 && (pair3 = this.d) != null && (((Byte) pair3.first).byteValue() == -19 || ((Byte) this.d.first).byteValue() == -18 || ((Byte) this.d.first).byteValue() == -17)) {
                    jd.wjlogin_sdk.common.f.a().reportNewLoginResult(ReplyCode.reply0xa9, this.f19714e, this.f19715f);
                    return jd.wjweblogin.d.c.f20052g;
                }
            }
            return ((Integer) this.f19713c.first).intValue();
        } catch (Throwable th) {
            th.printStackTrace();
            if (th instanceof NetworkException) {
                jd.wjlogin_sdk.common.f.a().reportNewLoginResult((byte) -1, this.f19714e, this.f19715f);
                return a(th);
            }
            jd.wjlogin_sdk.common.f.a().reportNewLoginResult(ReplyCode.reply0xa8, this.f19714e, this.f19715f);
            return jd.wjweblogin.d.c.f20052g;
        }
    }

    @Override // jd.wjlogin_sdk.c.e
    public String c() {
        return "V2Executor";
    }
}
