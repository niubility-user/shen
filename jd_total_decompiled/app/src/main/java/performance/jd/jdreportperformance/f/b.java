package performance.jd.jdreportperformance.f;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b implements Runnable {
    private volatile boolean a = false;

    private void a() {
        JSONObject a;
        performance.jd.jdreportperformance.b.b.b.b("DataRecordRunnable", "one loop");
        List<HashMap<String, String>> n2 = performance.jd.jdreportperformance.d.b.d().n();
        if (n2 == null) {
            performance.jd.jdreportperformance.b.b.b.a("DataRecordRunnable", "something error  occurred");
            return;
        }
        for (HashMap<String, String> hashMap : n2) {
            if (hashMap != null && (a = performance.jd.jdreportperformance.b.b.a.a(hashMap)) != null) {
                performance.jd.jdreportperformance.e.b bVar = new performance.jd.jdreportperformance.e.b();
                bVar.b(a.toString());
                if (performance.jd.jdreportperformance.d.a.a().a(bVar, performance.jd.jdreportperformance.d.b.d().c())) {
                    performance.jd.jdreportperformance.d.b.d().f();
                    performance.jd.jdreportperformance.b.b.b.a("DataRecordRunnable", "write db success, current db data count: " + performance.jd.jdreportperformance.d.b.d().c());
                } else {
                    performance.jd.jdreportperformance.b.b.b.a("DataRecordRunnable", "write db failed");
                }
                performance.jd.jdreportperformance.d.b.d().h();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.a) {
            a();
        }
    }
}
