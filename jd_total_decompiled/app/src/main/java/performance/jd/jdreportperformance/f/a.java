package performance.jd.jdreportperformance.f;

import java.util.List;

/* loaded from: classes.dex */
public class a implements Runnable {
    private volatile boolean a = false;

    private void a() {
        performance.jd.jdreportperformance.b.b.b.b("CommonReportRunnable", "one loop");
        List<performance.jd.jdreportperformance.e.b> l2 = performance.jd.jdreportperformance.d.b.d().l();
        if (l2 != null && !l2.isEmpty()) {
            int size = l2.size();
            if (performance.jd.jdreportperformance.b.b.a.d(l2)) {
                performance.jd.jdreportperformance.b.b.b.b("CommonReportRunnable", "common report success, count: " + size);
                a(l2);
                return;
            }
            performance.jd.jdreportperformance.b.b.b.a("CommonReportRunnable", "common report failed, count: " + size);
            performance.jd.jdreportperformance.d.b.d().a();
            return;
        }
        performance.jd.jdreportperformance.b.b.b.a("CommonReportRunnable", "something error occurred");
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.a) {
            a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void a(List<performance.jd.jdreportperformance.e.b> list) {
        long j2;
        int a;
        long j3 = -1;
        try {
            performance.jd.jdreportperformance.e.b bVar = list.get(list.size() - 1);
            j2 = Long.parseLong(list.get(0).a());
            try {
                j3 = Long.parseLong(bVar.a());
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                a = performance.jd.jdreportperformance.d.a.a().a(j2, j3);
                performance.jd.jdreportperformance.b.b.b.b("CommonReportRunnable", " delete db data count: " + a);
                if (a <= 0) {
                }
            }
        } catch (Exception e3) {
            e = e3;
            j2 = -1;
        }
        a = performance.jd.jdreportperformance.d.a.a().a(j2, j3);
        performance.jd.jdreportperformance.b.b.b.b("CommonReportRunnable", " delete db data count: " + a);
        if (a <= 0) {
            performance.jd.jdreportperformance.d.b.d().a(a);
        }
    }
}
