package m.a.c;

import com.jingdong.common.utils.LangUtils;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
class e1 extends e0 {

    /* renamed from: j  reason: collision with root package name */
    private String f20336j;

    /* renamed from: k  reason: collision with root package name */
    private Throwable f20337k;

    public e1(e0 e0Var, Throwable th) {
        super(e0Var);
        this.f20336j = "";
        try {
            Class b = p0.b(e0Var.c());
            if (b != null) {
                this.f20336j = ((String) b.getDeclaredField("NAME").get(null)) + LangUtils.SINGLE_SPACE;
            }
        } catch (Exception unused) {
        }
        this.f20337k = th;
    }

    @Override // m.a.c.e0
    public String toString() {
        return super.toString() + "Unparseable " + this.f20336j + "extension due to\n" + this.f20337k + "\n\n" + new HexDumpEncoder().encodeBuffer(d());
    }
}
