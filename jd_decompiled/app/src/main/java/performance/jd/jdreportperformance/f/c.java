package performance.jd.jdreportperformance.f;

import java.util.HashMap;

/* loaded from: classes.dex */
public class c implements Runnable {
    private HashMap<String, String> a;

    public c(HashMap<String, String> hashMap) {
        this.a = null;
        this.a = hashMap;
    }

    private void a() {
        performance.jd.jdreportperformance.b.b.b.b("DataSubmitRunnable", "DataSubmitRunnable run");
        HashMap<String, String> hashMap = this.a;
        if (hashMap == null) {
            return;
        }
        if (performance.jd.jdreportperformance.d.b.c(hashMap)) {
            performance.jd.jdreportperformance.d.b.d().e(this.a);
        } else {
            performance.jd.jdreportperformance.d.b.d().f(this.a);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        a();
    }
}
