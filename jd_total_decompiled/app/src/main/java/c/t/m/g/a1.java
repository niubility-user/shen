package c.t.m.g;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

/* loaded from: classes.dex */
public class a1 {

    /* renamed from: e  reason: collision with root package name */
    public static a1 f288e;
    public y4 a;
    public TencentLocationListener b;
    public long d = 0;

    /* renamed from: c  reason: collision with root package name */
    public b f289c = new b();

    /* loaded from: classes.dex */
    public class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            o2 o2Var;
            int i2;
            String str;
            if (message.what != 1 || (o2Var = (o2) h2.b(a1.this.a).e()) == null) {
                return;
            }
            if (o2Var == o2.q) {
                str = "ERROR_NETWORK";
                i2 = 1;
            } else {
                i2 = 0;
                str = "OK";
            }
            if ((o2Var.getProvider() != null && !"network".equals(o2Var.getProvider())) || System.currentTimeMillis() - a1.this.d > Final.FIVE_SECOND) {
                a1.this.b.onLocationChanged(o2Var, i2, str);
            }
            a1.this.f289c.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    public a1(y4 y4Var) {
        this.a = y4Var;
    }

    public static a1 c(y4 y4Var) {
        if (f288e == null) {
            synchronized (a1.class) {
                if (f288e == null) {
                    f288e = new a1(y4Var);
                }
            }
        }
        return f288e;
    }

    public int a(int i2, TencentLocationListener tencentLocationListener) {
        if (i2 == 10) {
            TencentLocationRequest create = TencentLocationRequest.create();
            create.setLocMode(10).setAllowGPS(true).setGpsFirst(true).setGpsFirstTimeOut(3000).setRequestLevel(3);
            return TencentLocationManager.getInstance(this.a.a).requestSingleFreshLocation(create, tencentLocationListener, Looper.getMainLooper());
        } else if (i2 == 12) {
            TencentLocationRequest create2 = TencentLocationRequest.create();
            create2.setLocMode(10).setAllowGPS(true).setLocMode(12).setInterval(1000L);
            return TencentLocationManager.getInstance(this.a.a).requestLocationUpdates(create2, tencentLocationListener, Looper.getMainLooper());
        } else if (i2 == 11) {
            if (h2.b(this.a).f()) {
                this.b = tencentLocationListener;
                int a2 = h2.b(this.a).a(TencentLocationManager.DR_TYPE_WALK);
                if (a2 == 0) {
                    if (this.d == 0) {
                        this.d = System.currentTimeMillis();
                    }
                    this.f289c.sendEmptyMessageDelayed(1, 1000L);
                    return a2;
                }
                return a2;
            }
            return -1;
        } else {
            return 0;
        }
    }

    public void e(int i2, TencentLocationListener tencentLocationListener) {
        if (i2 == 12) {
            TencentLocationManager.getInstance(this.a.a).removeUpdates(tencentLocationListener);
        } else if (i2 == 11) {
            h2.b(this.a).h();
            this.f289c.removeCallbacksAndMessages(null);
            this.d = 0L;
        }
    }
}
