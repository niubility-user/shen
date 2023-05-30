package g.f.a.c;

import android.content.Context;

/* loaded from: classes12.dex */
public class e {

    /* loaded from: classes12.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.f.a.c.a.values().length];
            a = iArr;
            try {
                iArr[g.f.a.c.a.ASR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.f.a.c.a.WAKEUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.f.a.c.a.KWS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static d a(Context context, g.f.a.c.a aVar) {
        int i2 = a.a[aVar.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return new g.f.a.a.b(context);
                }
                return new g.f.a.a.l.b(context);
            }
            return new g.f.a.a.m.a(context);
        }
        return new g.f.a.a.b(context);
    }
}
