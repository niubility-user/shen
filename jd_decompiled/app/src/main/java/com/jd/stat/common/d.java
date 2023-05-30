package com.jd.stat.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jd.dynamic.DYConstants;
import com.jd.stat.common.p;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.unification.uniconfig.UnNewIconTable;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class d implements Application.ActivityLifecycleCallbacks, SensorEventListener {
    private static d a;
    private a A;
    private p b;

    /* renamed from: c */
    private int f6985c;

    /* renamed from: f */
    private SensorManager f6987f;

    /* renamed from: h */
    private volatile CountDownLatch f6989h;
    private Class<? extends Activity> q;
    private Handler r;
    private AtomicInteger d = new AtomicInteger(0);

    /* renamed from: e */
    private JSONObject f6986e = new JSONObject();

    /* renamed from: g */
    private int f6988g = 6;

    /* renamed from: i */
    private volatile boolean f6990i = false;

    /* renamed from: j */
    private boolean f6991j = false;

    /* renamed from: k */
    private final float[] f6992k = new float[3];

    /* renamed from: l */
    private final float[] f6993l = new float[3];

    /* renamed from: m */
    private final float[] f6994m = new float[9];

    /* renamed from: n */
    private final float[] f6995n = new float[3];
    private final int o = 1;
    private final int p = -1;
    private AtomicInteger s = new AtomicInteger();
    private JSONObject t = new JSONObject();
    private float u = 0.0f;
    private float v = 0.0f;
    private float w = 0.0f;
    private float x = 0.0f;
    private int y = 1;
    private Timer z = new Timer();
    private int B = 2000;
    private ArrayList<Float> C = new ArrayList<>();
    private ArrayList<Float> D = new ArrayList<>();
    private ArrayList<Float> E = new ArrayList<>();
    private ArrayList<Float> F = new ArrayList<>();
    private boolean G = false;
    private Handler H = new Handler(Looper.getMainLooper()) { // from class: com.jd.stat.common.d.3
        {
            d.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                if (message.what == d.this.y) {
                    d dVar = d.this;
                    float c2 = dVar.c(dVar.C);
                    d dVar2 = d.this;
                    float d = dVar2.d(dVar2.C);
                    d dVar3 = d.this;
                    float a2 = dVar3.a(dVar3.C);
                    d dVar4 = d.this;
                    float b = dVar4.b(dVar4.C);
                    d.this.C.clear();
                    d dVar5 = d.this;
                    float c3 = dVar5.c(dVar5.D);
                    d dVar6 = d.this;
                    float d2 = dVar6.d(dVar6.D);
                    d dVar7 = d.this;
                    float a3 = dVar7.a(dVar7.D);
                    d dVar8 = d.this;
                    float b2 = dVar8.b(dVar8.D);
                    d.this.D.clear();
                    d dVar9 = d.this;
                    float c4 = dVar9.c(dVar9.E);
                    d dVar10 = d.this;
                    float d3 = dVar10.d(dVar10.E);
                    d dVar11 = d.this;
                    float a4 = dVar11.a(dVar11.E);
                    d dVar12 = d.this;
                    float b3 = dVar12.b(dVar12.E);
                    d.this.E.clear();
                    d dVar13 = d.this;
                    float c5 = dVar13.c(dVar13.F);
                    d dVar14 = d.this;
                    float d4 = dVar14.d(dVar14.F);
                    d dVar15 = d.this;
                    float a5 = dVar15.a(dVar15.F);
                    d dVar16 = d.this;
                    float b4 = dVar16.b(dVar16.F);
                    d.this.F.clear();
                    d.this.t = new JSONObject();
                    String a6 = com.jd.stat.common.b.g.a();
                    d.this.t.put("az", new JSONObject().put("max", d.this.a(a2)).put("min", d.this.a(b)).put("avg", d.this.a(c2)).put(UnNewIconTable.FIELD_IS_VAR, d.this.a(d)).put("cttm", a6));
                    d.this.t.put("ag", new JSONObject().put("max", d.this.a(a3)).put("min", d.this.a(b2)).put("avg", d.this.a(c3)).put(UnNewIconTable.FIELD_IS_VAR, d.this.a(d2)).put("cttm", a6));
                    d.this.t.put("ro", new JSONObject().put("max", d.this.a(a4)).put("min", d.this.a(b3)).put("avg", d.this.a(c4)).put(UnNewIconTable.FIELD_IS_VAR, d.this.a(d3)).put("cttm", a6));
                    d.this.t.put("lg", new JSONObject().put("max", d.this.a(a5)).put("min", d.this.a(b4)).put("avg", d.this.a(c5)).put(UnNewIconTable.FIELD_IS_VAR, d.this.a(d4)).put("cttm", a6));
                    com.jd.stat.common.b.f.a("sensorvalues", d.this.t.toString());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            d.this.G = false;
        }
    };

    /* loaded from: classes18.dex */
    public class a extends TimerTask {
        a() {
            d.this = r1;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            d.this.H.sendEmptyMessage(d.this.y);
        }
    }

    private d() {
        int b = com.jd.stat.common.b.f.b("jma_sid", 0) + 1;
        this.f6985c = b;
        com.jd.stat.common.b.f.a("jma_sid", b);
        HandlerThread handlerThread = new HandlerThread("register-sensor");
        handlerThread.start();
        this.r = new Handler(handlerThread.getLooper()) { // from class: com.jd.stat.common.d.1
            {
                d.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                try {
                    if (message.what == 1) {
                        d.this.i();
                    }
                    if (message.what == -1) {
                        d.this.d();
                    }
                } catch (Throwable th) {
                    com.jd.stat.common.b.b.b("JDMob.Security.AppLifeObserver", th);
                }
            }
        };
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        try {
            Handler handler = this.r;
            if (handler != null) {
                handler.sendEmptyMessage(-1);
            }
            p pVar = this.b;
            if (pVar != null) {
                pVar.c();
            }
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("JDMob.Security.AppLifeObserver", th);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        try {
            Handler handler = this.r;
            if (handler != null) {
                handler.sendEmptyMessage(1);
            }
            if (activity == null) {
                return;
            }
            this.q = activity.getClass();
            if (this.b == null) {
                p a2 = p.a(activity.getApplicationContext());
                this.b = a2;
                a2.a(new p.b() { // from class: com.jd.stat.common.d.2
                    {
                        d.this = this;
                    }

                    @Override // com.jd.stat.common.p.b
                    public void a(String str) {
                        d.this.g();
                    }
                });
            }
            this.b.b();
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("JDMob.Security.AppLifeObserver", th);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        try {
            if (this.s.get() == 0) {
                b(com.jd.stat.security.c.a);
            }
            this.s.incrementAndGet();
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("JDMob.Security.AppLifeObserver", th);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        try {
            if (this.s.get() > 0) {
                this.s.decrementAndGet();
            }
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("JDMob.Security.AppLifeObserver", th);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            int type = sensorEvent.sensor.getType();
            if (type != 3) {
                if (type == 5 && this.G) {
                    float f2 = sensorEvent.values[0];
                    this.F.add(Float.valueOf(f2));
                    if (f2 != this.x) {
                        this.x = f2;
                    }
                }
            } else if (this.G) {
                float round = Math.round(sensorEvent.values[0] * 100.0f) / 100.0f;
                float round2 = Math.round(sensorEvent.values[1] * 100.0f) / 100.0f;
                float round3 = Math.round(sensorEvent.values[2] * 100.0f) / 100.0f;
                this.C.add(Float.valueOf(round));
                this.D.add(Float.valueOf(round2));
                this.E.add(Float.valueOf(round3));
                if (round != this.u || round2 != this.v || round3 != this.w) {
                    this.u = round;
                    this.v = round2;
                    this.w = round3;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.f6990i) {
            try {
                int type2 = sensorEvent.sensor.getType();
                if (type2 == 1) {
                    if (this.f6986e.has("accelerometer")) {
                        return;
                    }
                    float[] fArr = sensorEvent.values;
                    float[] fArr2 = this.f6992k;
                    System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
                    this.f6986e.put("accelerometer", sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
                    this.f6989h.countDown();
                } else if (type2 == 2) {
                    if (this.f6986e.has("magneticField")) {
                        return;
                    }
                    float[] fArr3 = sensorEvent.values;
                    float[] fArr4 = this.f6993l;
                    System.arraycopy(fArr3, 0, fArr4, 0, fArr4.length);
                    this.f6986e.put("magneticField", sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
                    this.f6989h.countDown();
                } else if (type2 == 3) {
                    if (this.f6986e.has(MBaseKeyNames.KEY_ORIENTATION)) {
                        return;
                    }
                    this.f6986e.put(MBaseKeyNames.KEY_ORIENTATION, sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
                    this.f6989h.countDown();
                } else if (type2 != 4) {
                    if (type2 != 5) {
                        if (type2 != 9) {
                            return;
                        }
                    } else if (!this.f6986e.has(FontsUtil.KEY_MULTI_LIGHT)) {
                        this.f6986e.put(FontsUtil.KEY_MULTI_LIGHT, sensorEvent.values[0]);
                        this.f6989h.countDown();
                    }
                    if (this.f6986e.has(DYConstants.DY_GRAVITY)) {
                        return;
                    }
                    if (sensorEvent.values.length >= 3) {
                        this.f6986e.put(DYConstants.DY_GRAVITY, sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
                    } else {
                        this.f6986e.put(DYConstants.DY_GRAVITY, r0[0]);
                    }
                    this.f6989h.countDown();
                } else if (this.f6986e.has("gyroscope")) {
                } else {
                    this.f6986e.put("gyroscope", sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
                    this.f6989h.countDown();
                }
            } catch (Throwable unused) {
                if (this.f6989h != null) {
                    this.f6989h.countDown();
                }
            }
        }
    }

    public void g() {
        Class<? extends Activity> cls = this.q;
        p.a(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(Calendar.getInstance(TimeZone.getTimeZone("GMT+8")).getTime()), cls == null ? "" : cls.getName());
    }

    private String h() {
        SensorManager.getRotationMatrix(this.f6994m, null, this.f6992k, this.f6993l);
        SensorManager.getOrientation(this.f6994m, this.f6995n);
        return Math.toDegrees(this.f6995n[0]) + DYConstants.DY_REGEX_COMMA + Math.toDegrees(this.f6995n[1]) + DYConstants.DY_REGEX_COMMA + Math.toDegrees(this.f6995n[2]);
    }

    public void i() {
        Context context;
        try {
            if (com.jd.stat.security.d.a().l() && com.jd.stat.security.c.k()) {
                if (this.f6987f == null && (context = com.jd.stat.security.c.a) != null) {
                    this.f6987f = (SensorManager) context.getSystemService("sensor");
                }
                if (this.f6987f == null || this.f6991j) {
                    return;
                }
                this.f6991j = true;
                if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.b("JDMob.Security.AppLifeObserver", "really registerSensorListeners sensors ");
                }
                a(4, 3, 1, 2, 9);
                SensorManager sensorManager = this.f6987f;
                sensorManager.registerListener(this, sensorManager.getDefaultSensor(5), 3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject e() {
        if (!com.jd.stat.security.d.a().l()) {
            return new JSONObject();
        }
        String b = com.jd.stat.common.b.f.b("sensorvalues", this.t.toString());
        f();
        try {
            return new JSONObject(b);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new JSONObject();
        }
    }

    public void f() {
        this.G = true;
        if (this.z != null) {
            a aVar = this.A;
            if (aVar != null) {
                aVar.cancel();
            }
            a aVar2 = new a();
            this.A = aVar2;
            this.z.schedule(aVar2, this.B);
        }
    }

    public int b() {
        return this.d.incrementAndGet();
    }

    public JSONObject c() {
        this.f6986e = new JSONObject();
        try {
            this.f6989h = new CountDownLatch(this.f6988g);
            this.f6990i = true;
            long currentTimeMillis = System.currentTimeMillis();
            this.f6989h.await(500L, TimeUnit.MILLISECONDS);
            com.jd.stat.common.b.b.a("jma", "wait time is " + (System.currentTimeMillis() - currentTimeMillis));
            this.f6986e.put("euler", h());
            this.f6990i = false;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        com.jd.stat.common.b.b.a("JMA_TEST", "[2]" + this.f6986e.toString() + " on mThread " + Thread.currentThread().getName());
        return this.f6986e;
    }

    public void d() {
        SensorManager sensorManager;
        if (!this.f6991j || (sensorManager = this.f6987f) == null) {
            return;
        }
        this.f6991j = false;
        sensorManager.unregisterListener(this);
    }

    private void b(Context context) {
        if (context == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", "AutoReport");
            JMA.report(context, jSONObject);
        } catch (Exception unused) {
        }
    }

    public static d a(Context context) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public float d(ArrayList<Float> arrayList) {
        if (arrayList != null) {
            try {
                if (arrayList.size() != 0) {
                    float f2 = 0.0f;
                    float c2 = c(arrayList);
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        f2 += (arrayList.get(i2).floatValue() - c2) * (arrayList.get(i2).floatValue() - c2);
                    }
                    return f2 / arrayList.size();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return -1.0f;
    }

    public float b(ArrayList<Float> arrayList) {
        if (arrayList != null) {
            try {
                if (arrayList.size() != 0) {
                    float floatValue = arrayList.get(0).floatValue();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (floatValue > arrayList.get(i2).floatValue()) {
                            floatValue = arrayList.get(i2).floatValue();
                        }
                    }
                    return floatValue;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return -1.0f;
    }

    public void a(Application application) {
        if (application != null) {
            application.registerActivityLifecycleCallbacks(this);
        }
    }

    public int a() {
        return this.f6985c;
    }

    private void a(int... iArr) {
        if (com.jd.stat.security.c.k()) {
            this.f6988g = 6;
            for (int i2 : iArr) {
                Sensor defaultSensor = this.f6987f.getDefaultSensor(i2);
                if (defaultSensor == null) {
                    this.f6988g--;
                } else {
                    this.f6987f.registerListener(this, defaultSensor, 3);
                }
            }
        }
    }

    public float c(ArrayList<Float> arrayList) {
        if (arrayList != null) {
            try {
                if (arrayList.size() != 0) {
                    float f2 = 0.0f;
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        f2 += arrayList.get(i2).floatValue();
                    }
                    return f2 / arrayList.size();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return -1.0f;
    }

    public float a(ArrayList<Float> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return -1.0f;
        }
        float floatValue = arrayList.get(0).floatValue();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (floatValue < arrayList.get(i2).floatValue()) {
                floatValue = arrayList.get(i2).floatValue();
            }
        }
        return floatValue;
    }

    public double a(double d) {
        double round = Math.round(d * 100.0d);
        Double.isNaN(round);
        return round / 100.0d;
    }
}
