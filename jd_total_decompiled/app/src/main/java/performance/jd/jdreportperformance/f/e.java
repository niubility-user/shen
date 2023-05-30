package performance.jd.jdreportperformance.f;

import com.jingdong.common.jdreactFramework.JDReactConstant;

/* loaded from: classes.dex */
public class e implements Runnable {
    private void a() {
        boolean a = performance.jd.jdreportperformance.b.b.a.a();
        StringBuilder sb = new StringBuilder();
        sb.append("fetch rule: ");
        sb.append(a ? "success" : JDReactConstant.FAILED);
        performance.jd.jdreportperformance.b.b.b.a("RuleFetchRunnable", sb.toString());
        if (!a) {
            performance.jd.jdreportperformance.d.b.d().b();
        } else {
            performance.jd.jdreportperformance.d.b.d().h();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        a();
    }
}
