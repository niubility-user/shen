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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.util.List<performance.jd.jdreportperformance.e.b> r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = -1
            java.lang.Object r0 = r6.get(r0)     // Catch: java.lang.Exception -> L28
            performance.jd.jdreportperformance.e.b r0 = (performance.jd.jdreportperformance.e.b) r0     // Catch: java.lang.Exception -> L28
            int r3 = r6.size()     // Catch: java.lang.Exception -> L28
            int r3 = r3 + (-1)
            java.lang.Object r6 = r6.get(r3)     // Catch: java.lang.Exception -> L28
            performance.jd.jdreportperformance.e.b r6 = (performance.jd.jdreportperformance.e.b) r6     // Catch: java.lang.Exception -> L28
            java.lang.String r0 = r0.a()     // Catch: java.lang.Exception -> L28
            long r3 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Exception -> L28
            java.lang.String r6 = r6.a()     // Catch: java.lang.Exception -> L26
            long r1 = java.lang.Long.parseLong(r6)     // Catch: java.lang.Exception -> L26
            goto L2d
        L26:
            r6 = move-exception
            goto L2a
        L28:
            r6 = move-exception
            r3 = r1
        L2a:
            r6.printStackTrace()
        L2d:
            performance.jd.jdreportperformance.d.a r6 = performance.jd.jdreportperformance.d.a.a()
            int r6 = r6.a(r3, r1)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = " delete db data count: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "CommonReportRunnable"
            performance.jd.jdreportperformance.b.b.b.b(r1, r0)
            if (r6 <= 0) goto L54
            performance.jd.jdreportperformance.d.b r0 = performance.jd.jdreportperformance.d.b.d()
            r0.a(r6)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: performance.jd.jdreportperformance.f.a.a(java.util.List):void");
    }
}
