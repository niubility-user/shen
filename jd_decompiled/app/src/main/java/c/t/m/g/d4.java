package c.t.m.g;

import android.content.SharedPreferences;
import c.t.m.g.c0;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d4 {
    public static volatile boolean a = true;

    /* loaded from: classes.dex */
    public static class a extends TimerTask {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ Timer f351g;

        public a(Timer timer) {
            this.f351g = timer;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            d.d("th_loc_task_t_consume", new b(null));
            this.f351g.cancel();
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {

        /* loaded from: classes.dex */
        public class a implements c0.c {
            public a(b bVar) {
            }

            @Override // c.t.m.g.c0.c
            public void a(String str) {
            }

            @Override // c.t.m.g.c0.c
            public void b(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i2 = jSONObject.getInt("status");
                    if (i2 != 0) {
                        StringBuilder sb = new StringBuilder("parse json status:");
                        sb.append(i2);
                        sb.append(", json=");
                        sb.append(str);
                        return;
                    }
                    String string = jSONObject.getString("version");
                    String a = x5.a(jSONObject.getString("key"));
                    f6.a(k2.d(a), string);
                    SharedPreferences a2 = r3.a();
                    r3.f(a2, "loc_comm_rsa_pub_key_ver", string);
                    r3.f(a2, "loc_comm_rsa_pub_key_64", a);
                    r3.f(a2, "loc_comm_rsa_key_update_time", Long.valueOf(System.currentTimeMillis()));
                } catch (Throwable unused) {
                }
            }
        }

        public b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (d4.a) {
                c0.f("https://cs.map.qq.com/key", new a(this));
            }
        }
    }

    public static void b() {
        SharedPreferences a2 = r3.a();
        long longValue = ((Long) r3.c(a2, "loc_comm_rsa_key_update_time", 0L)).longValue();
        if (longValue != 0) {
            f6.a(k2.d((String) r3.c(a2, "loc_comm_rsa_pub_key_64", "")), (String) r3.c(a2, "loc_comm_rsa_pub_key_ver", ""));
        }
        if (Math.abs(System.currentTimeMillis() - longValue) < 259200000) {
            return;
        }
        Timer timer = new Timer();
        timer.schedule(new a(timer), Final.FIVE_SECOND);
    }
}
