package c.t.m.g;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;

/* loaded from: classes.dex */
public class g0 extends b0 {
    public a d = null;

    /* renamed from: c  reason: collision with root package name */
    public SensorManager f422c = (SensorManager) y3.a().getSystemService("sensor");

    /* loaded from: classes.dex */
    public static class a extends Handler implements SensorEventListener {

        /* renamed from: g  reason: collision with root package name */
        public volatile SensorEvent f423g;

        /* renamed from: h  reason: collision with root package name */
        public volatile SensorEvent f424h;

        /* renamed from: i  reason: collision with root package name */
        public volatile long f425i;

        /* renamed from: j  reason: collision with root package name */
        public long f426j;

        /* renamed from: k  reason: collision with root package name */
        public long f427k;

        /* renamed from: l  reason: collision with root package name */
        public long f428l;

        /* renamed from: m  reason: collision with root package name */
        public int f429m;

        /* renamed from: n  reason: collision with root package name */
        public volatile double f430n;

        public a(Looper looper) {
            super(looper);
            this.f425i = 0L;
            this.f426j = 0L;
            this.f427k = 0L;
            this.f428l = 0L;
            this.f429m = 0;
            this.f430n = 50.0d;
            this.f426j = 40L;
        }

        public final void a(Message message) {
            if (message.what == 2001) {
                removeMessages(2001);
                sendEmptyMessageDelayed(2001, 20L);
                long currentTimeMillis = System.currentTimeMillis();
                if (this.f423g == null || this.f424h == null) {
                    if (Math.abs(currentTimeMillis - this.f428l) >= Final.HALF_MINUTE) {
                        z0.d("AR", "acc or gyr is null.");
                        this.f428l = currentTimeMillis;
                        return;
                    }
                    return;
                }
                float[] fArr = this.f423g.values;
                float[] fArr2 = this.f424h.values;
                if (currentTimeMillis - this.f427k < this.f426j) {
                    return;
                }
                if (Math.abs(currentTimeMillis - this.f428l) >= Final.HALF_MINUTE) {
                    z0.d("AR", "accuracy:acc=" + this.f423g.accuracy + ", gyr=" + this.f424h.accuracy);
                    this.f428l = currentTimeMillis;
                }
                this.f427k = currentTimeMillis;
                if (i.f() != null) {
                    i.f().c(currentTimeMillis, fArr, fArr2);
                }
                if (currentTimeMillis - this.f425i > 2500 || this.f430n < 20.0d) {
                    t1.a("SensorHandler", "Ar reset by sensor:" + (currentTimeMillis - this.f425i) + DYConstants.DY_REGEX_COMMA + f2.b(this.f430n, 2));
                    if (i.f() != null) {
                        i.f().g();
                    }
                    this.f424h = null;
                    this.f423g = null;
                }
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                a(message);
            } catch (Throwable th) {
                t1.b("SensorHandler", Thread.currentThread().getName() + " error.", th);
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
            z0.d("AR", "sensor accuracy changed," + sensor.getType() + DYConstants.DY_REGEX_COMMA + i2 + DYConstants.DY_REGEX_COMMA + sensor.getName());
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            int type = sensorEvent.sensor.getType();
            if (type != 1) {
                if (type == 4) {
                    this.f424h = sensorEvent;
                    return;
                }
                return;
            }
            this.f423g = sensorEvent;
            int i2 = this.f429m + 1;
            this.f429m = i2;
            if (i2 == 25 || this.f425i == 0) {
                long currentTimeMillis = System.currentTimeMillis();
                double d = 50.0d;
                if (this.f425i != 0 && currentTimeMillis != this.f425i) {
                    double d2 = currentTimeMillis - this.f425i;
                    Double.isNaN(d2);
                    d = 1000.0d / (d2 / 25.0d);
                }
                this.f430n = d;
                this.f425i = currentTimeMillis;
                this.f429m = 0;
            }
        }
    }

    @Override // c.t.m.g.i0
    public String a() {
        return "ArSensorPro";
    }

    @Override // c.t.m.g.i0
    public void d() {
        this.f422c.unregisterListener(this.d);
        a aVar = this.d;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
        }
        this.d = null;
        t1.a("ArSensorPro", "status:[shutdown]");
    }

    @Override // c.t.m.g.b0
    public int h(Looper looper) {
        if (this.f422c == null) {
            return -1;
        }
        a aVar = new a(looper);
        this.d = aVar;
        SensorManager sensorManager = this.f422c;
        sensorManager.registerListener(aVar, sensorManager.getDefaultSensor(1), 1, this.d);
        SensorManager sensorManager2 = this.f422c;
        sensorManager2.registerListener(this.d, sensorManager2.getDefaultSensor(4), 1, this.d);
        this.d.sendEmptyMessageDelayed(2001, 100L);
        t1.a("ArSensorPro", "status:[start]");
        return 0;
    }
}
