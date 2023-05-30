package performance.jd.jdreportperformance;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import performance.jd.jdreportperformance.d.b;
import performance.jd.jdreportperformance.minterface.InitInformation;

/* loaded from: classes.dex */
public class a {
    private static volatile a b;
    private volatile Context a = null;

    private a() {
    }

    public static a b() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(Context context, InitInformation initInformation) {
        a(context);
        performance.jd.jdreportperformance.e.a.c().a(initInformation);
        b.d().a(initInformation);
    }

    public boolean a(Context context, InitInformation initInformation, HashMap<String, String> hashMap) {
        if (hashMap != null && context != null && initInformation != null) {
            a(context, initInformation);
            return b.g(hashMap);
        }
        performance.jd.jdreportperformance.b.b.b.a("JDPerformanceReporterSdk", "exist invoke param == null");
        return false;
    }

    public boolean a(Context context, InitInformation initInformation, ArrayList<HashMap<String, String>> arrayList) {
        if (arrayList != null && context != null && initInformation != null) {
            a(context, initInformation);
            return b.a(arrayList);
        }
        performance.jd.jdreportperformance.b.b.b.a("JDPerformanceReporterSdk", "exist invoke param == null");
        return false;
    }

    public void a(Context context) {
        if (context != null && this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    this.a = context;
                }
            }
        }
    }

    public Context a() {
        return this.a;
    }
}
