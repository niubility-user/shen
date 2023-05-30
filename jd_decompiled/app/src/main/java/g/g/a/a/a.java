package g.g.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.jd.ai.auth.basic.JDAIStatistics;
import com.jd.ai.auth.basic.SdkInvokeReport;
import com.jd.ai.auth.basic.StatListener;
import com.jd.ai.tool.LogUtil;
import g.g.a.f;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/* loaded from: classes18.dex */
public class a extends Thread {
    private static int r = 5;
    private static long s;

    /* renamed from: g  reason: collision with root package name */
    private SharedPreferences f19567g;

    /* renamed from: h  reason: collision with root package name */
    private SharedPreferences f19568h;

    /* renamed from: i  reason: collision with root package name */
    private SharedPreferences.Editor f19569i;

    /* renamed from: j  reason: collision with root package name */
    private SharedPreferences.Editor f19570j;

    /* renamed from: l  reason: collision with root package name */
    private boolean f19572l;
    private long o;
    long p;
    private String q;

    /* renamed from: k  reason: collision with root package name */
    private boolean f19571k = false;

    /* renamed from: m  reason: collision with root package name */
    private Object f19573m = new Object();

    /* renamed from: n  reason: collision with root package name */
    private boolean f19574n = false;

    /* renamed from: g.g.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    class RunnableC0838a implements Runnable {
        RunnableC0838a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                if (!a.this.f19574n) {
                    synchronized (a.this.f19573m) {
                        try {
                            f.c("Analyze", "analyze wait");
                            a.this.f19573m.wait();
                            if (!a.this.f19574n) {
                                synchronized (a.this.f19567g) {
                                    a.this.p = (new Date().getTime() / 3600000) * 3600000;
                                    String string = a.this.f19567g.getString(String.valueOf(a.this.p), "0");
                                    a.this.f19569i.putString(String.valueOf(a.this.p), String.valueOf(a.s + Long.valueOf(string).longValue()));
                                    a.this.f19569i.commit();
                                    a.this.f19567g.getAll();
                                    long unused = a.s = 0L;
                                    f.c("Analyze", "currentHourMS=" + a.this.p + ", lastCommitHourMS=" + a.this.o + "new count=" + a.s + ", old cout=" + string);
                                    a.this.f19572l = true;
                                    a.this.n();
                                    a.this.f19572l = false;
                                    a aVar = a.this;
                                    aVar.o = aVar.p;
                                    a.this.f19570j.putString("LastCommitHourMS", String.valueOf(a.this.o));
                                    a.this.f19570j.commit();
                                }
                            } else {
                                f.c("Analyze", "analyze exit thread");
                            }
                        } catch (InterruptedException e2) {
                            f.c("Analyze", "Analyze Exception=" + e2.toString());
                        }
                    }
                    break;
                }
                break;
            }
            f.f("Analyze", "Analyze Exit");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class b implements StatListener {
        b(a aVar) {
        }
    }

    public a(Context context, String str) {
        this.o = 0L;
        this.p = (new Date().getTime() / 3600000) * 3600000;
        this.q = "";
        this.q = str;
        SharedPreferences sharedPreferences = context.getSharedPreferences("AnalyzeInfo", 0);
        this.f19567g = sharedPreferences;
        this.f19569i = sharedPreferences.edit();
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("TimeSPF", 0);
        this.f19568h = sharedPreferences2;
        this.f19570j = sharedPreferences2.edit();
        LogUtil.setLogLevel(3);
        Map<String, ?> all = this.f19567g.getAll();
        Map<String, ?> all2 = this.f19568h.getAll();
        f.c("Analyze", "analyzeInfoMap1=" + all.toString());
        f.c("Analyze", "timeMap1=" + all2.toString());
        this.o = (new Date().getTime() / 3600000) * 3600000;
        this.p = (new Date().getTime() / 3600000) * 3600000;
        String string = this.f19568h.getString("LastCommitHourMS", String.valueOf(this.o));
        this.f19570j.putString("LastCommitHourMS", string);
        this.f19570j.commit();
        this.o = Long.valueOf(string).longValue();
        f.c("Analyze", "currentHourMS=" + this.p + ", lastCommitHourMS1=" + this.o);
        if (this.f19571k && this.p > this.o) {
            f.c("Analyze", "commitData first");
            n();
        }
        new Thread(new RunnableC0838a()).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ArrayList arrayList = new ArrayList();
        Map<String, ?> all = this.f19567g.getAll();
        f.c("Analyze", "analyzeInfoMap=" + all);
        this.p = (new Date().getTime() / 3600000) * 3600000;
        int i2 = 0;
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            String str = (String) entry.getValue();
            System.out.println(key + ":" + str);
            if (!key.equals(String.valueOf(this.p))) {
                i2++;
                f.c("Analyze", "currentHourMS=" + this.p + ", post time=" + key + ", count=" + str);
                arrayList.add(new SdkInvokeReport(Long.valueOf(key).longValue(), Integer.valueOf(str)));
            }
        }
        if (i2 == 0) {
            f.c("Analyze", "Post no");
        } else {
            JDAIStatistics.getInsttance().uploaderData(this.q, arrayList, new b(this));
        }
    }

    public void l() {
        s++;
        f.c("Analyze", "addCount=" + s);
        if (s % r == 0) {
            synchronized (this.f19573m) {
                this.f19573m.notifyAll();
            }
        }
    }

    public int m() {
        this.f19574n = true;
        synchronized (this.f19573m) {
            this.f19573m.notifyAll();
        }
        return 0;
    }
}
