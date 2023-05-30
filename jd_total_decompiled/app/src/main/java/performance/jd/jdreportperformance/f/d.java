package performance.jd.jdreportperformance.f;

import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class d implements Runnable {
    private volatile boolean a = false;

    private void a() {
        performance.jd.jdreportperformance.b.b.b.b("RealTimeReportRunnable", "one loop");
        List<HashMap<String, String>> m2 = performance.jd.jdreportperformance.d.b.d().m();
        if (m2 != null && !m2.isEmpty()) {
            int size = m2.size();
            if (performance.jd.jdreportperformance.e.c.a().d()) {
                if (performance.jd.jdreportperformance.b.b.a.c(m2)) {
                    performance.jd.jdreportperformance.b.b.b.b("RealTimeReportRunnable", "real time report success, count: " + size);
                    return;
                }
                performance.jd.jdreportperformance.b.b.b.a("RealTimeReportRunnable", "real time report failed, count: " + size);
                return;
            }
            performance.jd.jdreportperformance.b.b.b.a("RealTimeReportRunnable", "not satisfy the network condition, write data to db, count: " + size);
            for (HashMap<String, String> hashMap : m2) {
                if (hashMap != null) {
                    performance.jd.jdreportperformance.d.b.d().f(hashMap);
                }
            }
            return;
        }
        performance.jd.jdreportperformance.b.b.b.a("RealTimeReportRunnable", "something error occurred");
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.a) {
            a();
        }
    }
}
