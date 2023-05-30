package com.jd.verify.j;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jd.dynamic.DYConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class f implements SensorEventListener {
    private static f p;
    private static boolean q;
    private SensorManager b;

    /* renamed from: e  reason: collision with root package name */
    private Handler f7181e;

    /* renamed from: f  reason: collision with root package name */
    private Context f7182f;

    /* renamed from: g  reason: collision with root package name */
    private CountDownTimer f7183g;
    private JSONObject a = new JSONObject();

    /* renamed from: c  reason: collision with root package name */
    private int f7180c = 5;
    private boolean d = false;

    /* renamed from: h  reason: collision with root package name */
    private int f7184h = 1;

    /* renamed from: i  reason: collision with root package name */
    private Object f7185i = new Object();

    /* renamed from: j  reason: collision with root package name */
    private boolean f7186j = false;

    /* renamed from: k  reason: collision with root package name */
    private JSONArray f7187k = new JSONArray();

    /* renamed from: l  reason: collision with root package name */
    private JSONArray f7188l = new JSONArray();

    /* renamed from: m  reason: collision with root package name */
    private JSONArray f7189m = new JSONArray();

    /* renamed from: n  reason: collision with root package name */
    private JSONArray f7190n = new JSONArray();
    private JSONArray o = new JSONArray();

    /* loaded from: classes18.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                f.this.d();
            }
            if (message.what == -1) {
                f.this.g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class b extends CountDownTimer {
        b(long j2, long j3) {
            super(j2, j3);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            d.a("JDVerify.Verify.SensorObserver", "setTimer CountDownTimer onFinish");
            if (f.this.f7181e != null) {
                f.this.f7181e.sendEmptyMessage(-1);
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
        }
    }

    private f() {
        HandlerThread handlerThread = new HandlerThread("verify-register-sensor");
        handlerThread.start();
        this.f7181e = new a(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Context context;
        try {
            if (q || this.f7186j) {
                e();
                this.a = null;
                this.a = new JSONObject();
                this.f7187k = new JSONArray();
                this.f7188l = new JSONArray();
                this.f7189m = new JSONArray();
                this.f7190n = new JSONArray();
                this.o = new JSONArray();
                if (this.b == null && (context = this.f7182f) != null) {
                    this.b = (SensorManager) context.getSystemService("sensor");
                }
                if (this.b == null || this.d) {
                    return;
                }
                this.d = true;
                d.a("JDVerify.Verify.SensorObserver", "really registerSensorListeners sensors ");
                a(4, 1, 11, 9);
                SensorManager sensorManager = this.b;
                sensorManager.registerListener(this, sensorManager.getDefaultSensor(5), 3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        d.a("JDVerify.Verify.SensorObserver", "setTimer");
        if (this.f7183g != null) {
            this.f7183g = null;
        }
        b bVar = new b(this.f7184h * 1000, 1000L);
        this.f7183g = bVar;
        bVar.start();
    }

    public void c() {
        Handler handler = this.f7181e;
        if (handler != null) {
            handler.sendEmptyMessage(1);
        }
    }

    public void f() {
        d.a("JDVerify.Verify.SensorObserver", "stopTimer");
        Handler handler = this.f7181e;
        if (handler != null) {
            handler.sendEmptyMessage(-1);
        }
        CountDownTimer countDownTimer = this.f7183g;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f7183g = null;
        }
    }

    public void g() {
        SensorManager sensorManager;
        d.a("SensorObserver unregisterSensorListeners");
        boolean z = this.d;
        if (!z || (sensorManager = this.b) == null) {
            return;
        }
        if (z) {
            sensorManager.unregisterListener(this);
        }
        this.d = false;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            int type = sensorEvent.sensor.getType();
            if (type == 1) {
                this.f7188l.put(sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
            } else if (type == 9) {
                if (sensorEvent.values.length >= 3) {
                    this.f7190n.put(sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
                } else {
                    this.f7190n.put(r0[0]);
                }
            } else if (type != 11) {
                if (type == 4) {
                    this.f7189m.put(sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
                } else if (type == 5) {
                    this.f7187k.put(sensorEvent.values[0]);
                }
            } else if (sensorEvent.values.length >= 3) {
                this.o.put(sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[3]);
            } else {
                this.o.put(sensorEvent.values[0] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[1] + DYConstants.DY_REGEX_COMMA + sensorEvent.values[2]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void b(boolean z) {
        q = z;
    }

    public void a(Context context) {
        this.f7182f = context;
    }

    public static f b() {
        if (p == null) {
            synchronized (f.class) {
                if (p == null) {
                    p = new f();
                }
            }
        }
        return p;
    }

    public void a(boolean z) {
        this.f7186j = z;
        Handler handler = this.f7181e;
        if (handler != null) {
            handler.sendEmptyMessage(1);
        }
    }

    private void a(int... iArr) {
        this.f7180c = 5;
        for (int i2 : iArr) {
            d.a("JDVerify.Verify.SensorObserver", "really registerSensorUpdateTaskNum type =" + i2);
            Sensor defaultSensor = this.b.getDefaultSensor(i2);
            if (defaultSensor == null) {
                this.f7180c--;
            } else {
                this.b.registerListener(this, defaultSensor, 3);
            }
        }
    }

    public JSONObject a() {
        try {
            if (this.a == null) {
                this.a = new JSONObject();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!q && !this.f7186j) {
            return this.a;
        }
        synchronized (this.f7185i) {
            Handler handler = this.f7181e;
            if (handler != null) {
                handler.sendEmptyMessage(-1);
            }
            JSONArray jSONArray = this.f7187k;
            JSONArray jSONArray2 = this.f7188l;
            JSONArray jSONArray3 = this.f7189m;
            JSONArray jSONArray4 = this.f7190n;
            JSONArray jSONArray5 = this.o;
            this.a.put("tlgt", jSONArray);
            this.a.put("tgar", jSONArray4);
            this.a.put("tacc", jSONArray2);
            this.a.put("tgyr", jSONArray3);
            this.a.put("trov", jSONArray5);
        }
        return this.a;
    }
}
