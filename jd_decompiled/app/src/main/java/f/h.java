package f;

import f.f;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h {
    private f<?> a;

    public h(f<?> fVar) {
        this.a = fVar;
    }

    public void a() {
        this.a = null;
    }

    protected void finalize() throws Throwable {
        f.g q;
        try {
            f<?> fVar = this.a;
            if (fVar != null && (q = f.q()) != null) {
                q.a(fVar, new i(fVar.o()));
            }
        } finally {
            super.finalize();
        }
    }
}
