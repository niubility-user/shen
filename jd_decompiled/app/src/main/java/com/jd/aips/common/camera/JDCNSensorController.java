package com.jd.aips.common.camera;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.jd.aips.common.utils.FsBaseInfoUtils;
import java.util.Calendar;

/* loaded from: classes12.dex */
public class JDCNSensorController implements SensorEventListener {
    private SensorManager a;
    private Sensor b;

    /* renamed from: c  reason: collision with root package name */
    private int f1569c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f1570e;

    /* renamed from: g  reason: collision with root package name */
    private Calendar f1572g;

    /* renamed from: j  reason: collision with root package name */
    private CameraFocusListener f1575j;

    /* renamed from: f  reason: collision with root package name */
    private long f1571f = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f1573h = false;

    /* renamed from: i  reason: collision with root package name */
    private int f1574i = 0;

    /* loaded from: classes12.dex */
    public interface CameraFocusListener {
        void onFocus();
    }

    public JDCNSensorController(Context context) {
        if (FsBaseInfoUtils.isAgreedPrivacy()) {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            this.a = sensorManager;
            if (sensorManager != null) {
                this.b = sensorManager.getDefaultSensor(1);
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor != null && sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            int i2 = (int) fArr[0];
            int i3 = (int) fArr[1];
            int i4 = (int) fArr[2];
            Calendar calendar = Calendar.getInstance();
            this.f1572g = calendar;
            long timeInMillis = calendar.getTimeInMillis();
            this.f1572g.get(13);
            if (this.f1574i != 0) {
                int abs = Math.abs(this.f1569c - i2);
                int abs2 = Math.abs(this.d - i3);
                int abs3 = Math.abs(this.f1570e - i4);
                if (Math.sqrt((abs * abs) + (abs2 * abs2) + (abs3 * abs3)) > 1.4d) {
                    this.f1574i = 2;
                } else {
                    if (this.f1574i == 2) {
                        this.f1571f = timeInMillis;
                        this.f1573h = true;
                    }
                    if (this.f1573h && timeInMillis - this.f1571f > 300) {
                        this.f1573h = false;
                        CameraFocusListener cameraFocusListener = this.f1575j;
                        if (cameraFocusListener != null) {
                            cameraFocusListener.onFocus();
                        }
                    }
                    this.f1574i = 1;
                }
            } else {
                this.f1571f = timeInMillis;
                this.f1574i = 1;
            }
            this.f1569c = i2;
            this.d = i3;
            this.f1570e = i4;
        }
    }

    public void onStart() {
        this.f1574i = 0;
        this.f1573h = false;
        this.f1569c = 0;
        this.d = 0;
        this.f1570e = 0;
        SensorManager sensorManager = this.a;
        if (sensorManager != null) {
            sensorManager.registerListener(this, this.b, 3);
        }
    }

    public void onStop() {
        this.f1575j = null;
        SensorManager sensorManager = this.a;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this, this.b);
        }
    }

    public void setCameraFocusListener(CameraFocusListener cameraFocusListener) {
        this.f1575j = cameraFocusListener;
    }
}
