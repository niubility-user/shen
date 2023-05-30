package c.t.m.g;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

/* loaded from: classes.dex */
public class u2 implements SensorEventListener {

    /* renamed from: k  reason: collision with root package name */
    public static volatile u2 f703k;

    /* renamed from: g  reason: collision with root package name */
    public final SensorManager f704g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f705h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f706i;

    /* renamed from: j  reason: collision with root package name */
    public double f707j;

    public u2(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.f704g = sensorManager;
        this.f705h = sensorManager != null;
    }

    public static u2 b(Context context) {
        if (f703k == null) {
            f703k = new u2(context);
        }
        return f703k;
    }

    public double a() {
        double d;
        if (this.f706i) {
            synchronized (this) {
                d = this.f707j;
            }
            return d;
        }
        return Double.NaN;
    }

    public void c(Handler handler) {
        if (this.f705h && !this.f706i) {
            try {
                Sensor defaultSensor = this.f704g.getDefaultSensor(11);
                if (defaultSensor == null || handler == null) {
                    return;
                }
                this.f704g.registerListener(this, defaultSensor, 3, handler);
                this.f706i = true;
            } catch (Throwable unused) {
            }
        }
    }

    public void d() {
        if (this.f705h && this.f706i) {
            this.f706i = false;
            synchronized (this) {
                this.f707j = Double.NaN;
            }
            this.f704g.unregisterListener(this);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            if (sensorEvent.sensor.getType() == 11) {
                float[] fArr = new float[16];
                float[] fArr2 = new float[3];
                SensorManager.getRotationMatrixFromVector(fArr, sensorEvent.values);
                SensorManager.getOrientation(fArr, fArr2);
                double d = fArr2[0];
                synchronized (this) {
                    Double.isNaN(d);
                    this.f707j = (d * 180.0d) / 3.1415926d;
                }
            }
        } catch (Throwable unused) {
        }
    }
}
