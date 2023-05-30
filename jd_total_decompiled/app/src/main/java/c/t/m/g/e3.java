package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.connect.common.Constants;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes.dex */
public class e3 {
    public b a;
    public SensorManager b;

    /* renamed from: c */
    public m3 f377c;
    public v3 d;

    /* renamed from: e */
    public u3 f378e;

    /* renamed from: g */
    public boolean f380g;

    /* renamed from: f */
    public boolean f379f = false;

    /* renamed from: h */
    public final p3 f381h = new a();

    /* loaded from: classes.dex */
    public class a extends p3 {
        public a() {
            e3.this = r1;
        }

        @Override // c.t.m.g.p3
        public void a(h3 h3Var) {
            x3 x3Var;
            if (h3Var.a() != 10001 || (x3Var = (x3) h3Var) == null) {
                return;
            }
            t.l(e3.this.a, R2.color.keyboard_color_action_text_dark, 0, 0, x3Var.a);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends Handler implements SensorEventListener, c4 {

        /* renamed from: g */
        public final SparseArray<SensorEvent> f382g;

        /* renamed from: h */
        public WeakReference<e3> f383h;

        /* renamed from: i */
        public final float[] f384i;

        /* renamed from: j */
        public final float[] f385j;

        /* renamed from: k */
        public final float[] f386k;

        /* renamed from: l */
        public double f387l;

        /* renamed from: m */
        public double f388m;

        /* renamed from: n */
        public double f389n;

        public b(Looper looper, e3 e3Var) {
            super(looper);
            this.f382g = new SparseArray<>();
            this.f384i = new float[16];
            this.f385j = new float[16];
            this.f386k = new float[]{0.0f, 0.0f, 0.0f};
            this.f387l = 0.0d;
            this.f388m = 0.0d;
            this.f389n = 0.0d;
            this.f383h = new WeakReference<>(e3Var);
        }

        @Override // c.t.m.g.c4
        public void a(n3 n3Var) {
            Message obtainMessage = obtainMessage();
            obtainMessage.what = R2.color.keyboard_color_action_text_high_light;
            obtainMessage.obj = n3Var;
            t.e(this, obtainMessage);
        }

        public final void b() {
            synchronized (this.f382g) {
                this.f382g.clear();
            }
        }

        public final void c(Message message) {
            switch (message.what) {
                case 4001:
                    removeMessages(4001);
                    sendEmptyMessageDelayed(4001, 40L);
                    SensorEvent sensorEvent = this.f382g.get(1);
                    SensorEvent sensorEvent2 = this.f382g.get(4);
                    SensorEvent sensorEvent3 = this.f382g.get(2);
                    SensorEvent sensorEvent4 = this.f382g.get(11);
                    SensorEvent sensorEvent5 = this.f382g.get(9);
                    if (sensorEvent4 != null) {
                        SensorManager.getRotationMatrixFromVector(this.f384i, sensorEvent4.values);
                        boolean d = d(sensorEvent5);
                        if (d) {
                            SensorManager.remapCoordinateSystem(this.f384i, 1, 3, this.f385j);
                        }
                        SensorManager.getOrientation(d ? this.f385j : this.f384i, this.f386k);
                    }
                    if (sensorEvent == null || sensorEvent2 == null || sensorEvent3 == null || sensorEvent4 == null) {
                        return;
                    }
                    m3 m3Var = this.f383h.get().f377c;
                    long j2 = sensorEvent.timestamp;
                    float[] fArr = sensorEvent.values;
                    float f2 = fArr[0];
                    float f3 = fArr[1];
                    float f4 = fArr[2];
                    long j3 = sensorEvent2.timestamp;
                    float[] fArr2 = sensorEvent2.values;
                    float f5 = fArr2[0];
                    float f6 = fArr2[1];
                    float f7 = fArr2[2];
                    long j4 = sensorEvent3.timestamp;
                    float[] fArr3 = sensorEvent3.values;
                    float f8 = fArr3[0];
                    float f9 = fArr3[1];
                    float f10 = fArr3[2];
                    long j5 = sensorEvent4.timestamp;
                    float[] fArr4 = this.f386k;
                    m3Var.d(j2, f2, f3, f4, j3, f5, f6, f7, j4, f8, f9, f10, j5, fArr4[0], fArr4[1], fArr4[2]);
                    return;
                case R2.color.jrtxt_main_title_color /* 4002 */:
                    this.f383h.get().f377c.a();
                    b();
                    removeCallbacksAndMessages(null);
                    d.b("tc_pdr_thread");
                    return;
                case R2.color.key_step_logger_analyser_bg_color /* 4003 */:
                default:
                    return;
                case R2.color.keyboard_color_action_text /* 4004 */:
                    int i2 = message.arg1;
                    this.f383h.get().f377c.h();
                    this.f383h.get().f377c.e(null, i2);
                    t.k(this, 4001);
                    return;
                case R2.color.keyboard_color_action_text_dark /* 4005 */:
                    Object obj = message.obj;
                    Location location = obj != null ? (Location) obj : null;
                    if (location != null) {
                        double currentTimeMillis = System.currentTimeMillis();
                        Double.isNaN(currentTimeMillis);
                        double d2 = currentTimeMillis / 1000.0d;
                        double time = location.getTime();
                        Double.isNaN(time);
                        this.f383h.get().f377c.b(d2, time / 1000.0d, location.getLatitude(), location.getLongitude(), location.hasAltitude() ? location.getAltitude() : 9999.0d, location.hasAccuracy() ? location.getAccuracy() : 9999.0d, location.hasSpeed() ? location.getSpeed() : 9999.0d, location.hasBearing() ? location.getBearing() : 9999.0d);
                        return;
                    }
                    return;
                case R2.color.keyboard_color_action_text_high_light /* 4006 */:
                    Object obj2 = message.obj;
                    n3 n3Var = obj2 != null ? (n3) obj2 : null;
                    if (n3Var != null) {
                        z0.f("AR", n3Var.a() + DYConstants.DY_REGEX_COMMA + n3Var.b());
                        this.f383h.get().f377c.c(n3Var.a(), n3Var.b());
                        return;
                    }
                    return;
            }
        }

        public final boolean d(SensorEvent sensorEvent) {
            if (sensorEvent == null) {
                return false;
            }
            float[] fArr = sensorEvent.values;
            double d = fArr[0] * fArr[0];
            double d2 = fArr[1] * fArr[1];
            double d3 = fArr[2] * fArr[2];
            double d4 = this.f387l;
            if (d4 != 0.0d) {
                Double.isNaN(d);
                d = (d * 0.1d) + (d4 * 0.9d);
            }
            this.f387l = d;
            double d5 = this.f388m;
            if (d5 != 0.0d) {
                Double.isNaN(d2);
                d2 = (d2 * 0.1d) + (d5 * 0.9d);
            }
            this.f388m = d2;
            double d6 = this.f389n;
            if (d6 != 0.0d) {
                Double.isNaN(d3);
                d3 = (d3 * 0.1d) + (d6 * 0.9d);
            }
            this.f389n = d3;
            return d + d3 < 25.0d || d2 + d3 < 25.0d;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                c(message);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            synchronized (this.f382g) {
                sensorEvent.timestamp = System.currentTimeMillis();
                this.f382g.put(sensorEvent.sensor.getType(), sensorEvent);
            }
        }
    }

    public e3(Context context) {
        this.f380g = true;
        try {
            u3 u3Var = new u3();
            this.f378e = u3Var;
            u3Var.b(context);
            this.b = (SensorManager) context.getSystemService("sensor");
            this.d = v3.a(context);
            this.f377c = m3.f();
        } catch (Throwable th) {
            th.printStackTrace();
            this.f380g = false;
        }
    }

    public int a(int i2) {
        if (this.f379f) {
            return -2;
        }
        boolean h2 = h();
        this.f380g = h2;
        if (h2) {
            this.a = new b(d.e("tc_pdr_thread").getLooper(), this);
            if (!k()) {
                c();
                return -3;
            }
            int j2 = j();
            if (j2 != 0) {
                c();
                return j2;
            }
            z0.f("DR", "startup,".concat(String.valueOf(i2)));
            i();
            t.l(this.a, R2.color.keyboard_color_action_text, i2, 0, null);
            this.f379f = true;
            return 0;
        }
        return -1;
    }

    public final void c() {
        t.j(this.a);
        t.k(this.a, R2.color.jrtxt_main_title_color);
        this.a = null;
    }

    public double[] e() {
        if (this.f379f) {
            return this.f377c.g();
        }
        return null;
    }

    public boolean f() {
        return this.f379f;
    }

    public boolean g() {
        return this.f380g;
    }

    public final boolean h() {
        SensorManager sensorManager = this.b;
        if (sensorManager == null) {
            StringBuilder sb = new StringBuilder("sen:");
            sb.append(this.b == null);
            z0.f("DR", sb.toString());
            return false;
        }
        try {
            Sensor defaultSensor = sensorManager.getDefaultSensor(11);
            Sensor defaultSensor2 = this.b.getDefaultSensor(1);
            Sensor defaultSensor3 = this.b.getDefaultSensor(4);
            Sensor defaultSensor4 = this.b.getDefaultSensor(2);
            Sensor defaultSensor5 = this.b.getDefaultSensor(9);
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[5];
            objArr[0] = Boolean.valueOf(defaultSensor == null);
            objArr[1] = Boolean.valueOf(defaultSensor2 == null);
            objArr[2] = Boolean.valueOf(defaultSensor3 == null);
            objArr[3] = Boolean.valueOf(defaultSensor4 == null);
            if (defaultSensor5 != null) {
                r1 = false;
            }
            objArr[4] = Boolean.valueOf(r1);
            z0.f("SEN", String.format(locale, "has:%.1b,%.1b,%.1b,%.1b,%.1b", objArr));
            if (defaultSensor == null || defaultSensor2 == null || defaultSensor3 == null || defaultSensor4 == null) {
                this.f380g = false;
            }
        } catch (Throwable unused) {
            this.f380g = false;
        }
        return this.f380g;
    }

    public final void i() {
        this.d.h();
        this.d.d(this.a);
        this.d.e("set_ar_detect_cycle", Constants.DEFAULT_UIN);
    }

    @SuppressLint({"MissingPermission"})
    public final int j() {
        z2.a().b(this.f381h);
        return 0;
    }

    public final boolean k() {
        try {
            SensorManager sensorManager = this.b;
            boolean registerListener = sensorManager.registerListener(this.a, sensorManager.getDefaultSensor(11), 10000, this.a);
            SensorManager sensorManager2 = this.b;
            boolean registerListener2 = sensorManager2.registerListener(this.a, sensorManager2.getDefaultSensor(1), 10000, this.a);
            SensorManager sensorManager3 = this.b;
            boolean registerListener3 = sensorManager3.registerListener(this.a, sensorManager3.getDefaultSensor(4), 10000, this.a);
            SensorManager sensorManager4 = this.b;
            boolean registerListener4 = sensorManager4.registerListener(this.a, sensorManager4.getDefaultSensor(2), 10000, this.a);
            SensorManager sensorManager5 = this.b;
            boolean registerListener5 = sensorManager5.registerListener(this.a, sensorManager5.getDefaultSensor(9), 10000, this.a);
            z0.f("Sen", String.format(Locale.ENGLISH, "register:%.1b,%.1b,%.1b,%.1b,%.1b", Boolean.valueOf(registerListener), Boolean.valueOf(registerListener2), Boolean.valueOf(registerListener3), Boolean.valueOf(registerListener4), Boolean.valueOf(registerListener5)));
            return true;
        } catch (Throwable unused) {
            z0.d("SEN_E", "REGISTER ERR");
            return false;
        }
    }

    public void l() {
        if (this.f379f) {
            z0.f("DR", "shutdown");
            this.b.unregisterListener(this.a);
            z2.a().c(this.f381h);
            this.d.g(this.a);
            this.d.f();
            c();
            this.f379f = false;
            this.f378e.a();
        }
    }
}
