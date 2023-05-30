package com.jdjr.risk.jdcn.common.camera;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.Calendar;

/* loaded from: classes18.dex */
public class JDCNSensorControler implements SensorEventListener {
    private Calendar mCalendar;
    private CameraFocusListener mCameraFocusListener;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private int mX;
    private int mY;
    private int mZ;
    private long lastStaticStamp = 0;
    private final int DELEY_DURATION = 300;
    private boolean isFocusing = false;
    private boolean canFocusIn = false;
    private final int STATUS_NONE = 0;
    private final int STATUS_STATIC = 1;
    private final int STATUS_MOVE = 2;
    private int STATUE = 0;

    /* loaded from: classes18.dex */
    public interface CameraFocusListener {
        void onFocus();
    }

    public JDCNSensorControler(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.mSensorManager = sensorManager;
        if (sensorManager != null) {
            this.mSensor = sensorManager.getDefaultSensor(1);
        }
    }

    private void restParams() {
        this.STATUE = 0;
        this.canFocusIn = false;
        this.mX = 0;
        this.mY = 0;
        this.mZ = 0;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor == null) {
            return;
        }
        if (this.isFocusing) {
            restParams();
        } else if (sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            int i2 = (int) fArr[0];
            int i3 = (int) fArr[1];
            int i4 = (int) fArr[2];
            Calendar calendar = Calendar.getInstance();
            this.mCalendar = calendar;
            long timeInMillis = calendar.getTimeInMillis();
            this.mCalendar.get(13);
            if (this.STATUE != 0) {
                int abs = Math.abs(this.mX - i2);
                int abs2 = Math.abs(this.mY - i3);
                int abs3 = Math.abs(this.mZ - i4);
                if (Math.sqrt((abs * abs) + (abs2 * abs2) + (abs3 * abs3)) > 1.4d) {
                    this.STATUE = 2;
                } else {
                    if (this.STATUE == 2) {
                        this.lastStaticStamp = timeInMillis;
                        this.canFocusIn = true;
                    }
                    if (this.canFocusIn && timeInMillis - this.lastStaticStamp > 300) {
                        this.canFocusIn = false;
                        CameraFocusListener cameraFocusListener = this.mCameraFocusListener;
                        if (cameraFocusListener != null) {
                            cameraFocusListener.onFocus();
                        }
                    }
                    this.STATUE = 1;
                }
            } else {
                this.lastStaticStamp = timeInMillis;
                this.STATUE = 1;
            }
            this.mX = i2;
            this.mY = i3;
            this.mZ = i4;
        }
    }

    public void onStart() {
        restParams();
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.registerListener(this, this.mSensor, 3);
        }
    }

    public void onStop() {
        this.mCameraFocusListener = null;
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this, this.mSensor);
        }
    }

    public void setCameraFocusListener(CameraFocusListener cameraFocusListener) {
        this.mCameraFocusListener = cameraFocusListener;
    }
}
