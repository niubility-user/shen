package c.t.m.g;

import android.os.Bundle;
import c.t.m.g.c0;

/* loaded from: classes.dex */
public class n5 implements e5 {

    /* loaded from: classes.dex */
    public class a implements c0.c {
        public final /* synthetic */ Bundle a;
        public final /* synthetic */ v4 b;

        public a(n5 n5Var, Bundle bundle, v4 v4Var) {
            this.a = bundle;
            this.b = v4Var;
        }

        @Override // c.t.m.g.c0.c
        public void a(String str) {
            this.a.putString("msg_fail", str);
            v4 v4Var = this.b;
            if (v4Var != null) {
                v4Var.a(str);
            }
        }

        @Override // c.t.m.g.c0.c
        public void b(String str) {
            this.a.putString("msg_suc", str);
            v4 v4Var = this.b;
            if (v4Var != null) {
                v4Var.b(str);
            }
        }
    }

    @Override // c.t.m.g.e5
    public Bundle a(String str, byte[] bArr, v4 v4Var) {
        Bundle bundle = new Bundle();
        c0.c(str, bArr, new a(this, bundle, v4Var));
        return bundle;
    }
}
