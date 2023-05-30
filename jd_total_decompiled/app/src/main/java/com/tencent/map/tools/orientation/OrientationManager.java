package com.tencent.map.tools.orientation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.OrientationEventListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class OrientationManager extends OrientationEventListener implements SensorEventListener {
    private Context contextObj;
    private float mLastAngle;
    private List<OrientationListener> mListeners;
    private int mOrientation;

    public OrientationManager(Context context) {
        super(context, 3);
        this.mListeners = new ArrayList();
        this.contextObj = null;
        this.contextObj = context;
    }

    public void addOrientationListener(OrientationListener orientationListener) {
        if (this.mListeners.contains(orientationListener)) {
            return;
        }
        this.mListeners.add(orientationListener);
        if (this.mListeners.size() == 1) {
            try {
                enable();
                SensorManager sensorManager = (SensorManager) this.contextObj.getSystemService("sensor");
                List<Sensor> sensorList = sensorManager.getSensorList(3);
                if (sensorList.isEmpty()) {
                    return;
                }
                sensorManager.registerListener(this, sensorList.get(0), 2);
            } catch (Exception unused) {
            }
        }
    }

    public void destroy() {
        this.mListeners.clear();
        try {
            disable();
            SensorManager sensorManager = (SensorManager) this.contextObj.getSystemService("sensor");
            if (!sensorManager.getSensorList(3).isEmpty()) {
                sensorManager.unregisterListener(this);
            }
        } catch (Exception unused) {
        }
        this.contextObj = null;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i2) {
        if (i2 >= 0) {
            this.mOrientation = i2;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f2;
        if (sensorEvent.sensor.getType() == 3) {
            float[] fArr = sensorEvent.values;
            boolean z = false;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[2];
            if (f3 == 0.0f) {
                return;
            }
            if (Math.abs(this.mLastAngle - f3) > 30.0f) {
                this.mLastAngle = f3;
                return;
            }
            float f6 = (f3 + this.mLastAngle) / 2.0f;
            this.mLastAngle = f6;
            try {
                if (this.contextObj.getResources().getConfiguration().orientation == 2) {
                    z = true;
                }
            } catch (Exception unused) {
            }
            int i2 = this.mOrientation;
            if (z) {
                if (i2 > 45 && i2 <= 135) {
                    f2 = 270.0f;
                } else if (i2 > 135 && i2 <= 225) {
                    f2 = 180.0f;
                } else if (i2 > 225 && i2 < 315) {
                    f2 = 90.0f;
                }
                f6 = (f6 + f2) % 360.0f;
            }
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.mListeners);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    OrientationListener orientationListener = (OrientationListener) it.next();
                    if (orientationListener != null) {
                        orientationListener.onOrientationChanged(f6, f4, f5);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | OutOfMemoryError unused2) {
            }
        }
    }

    public void removeOrientationListener(OrientationListener orientationListener) {
        if (this.mListeners.contains(orientationListener)) {
            this.mListeners.remove(orientationListener);
            if (this.mListeners.isEmpty()) {
                try {
                    disable();
                    SensorManager sensorManager = (SensorManager) this.contextObj.getSystemService("sensor");
                    if (sensorManager.getSensorList(3).isEmpty()) {
                        return;
                    }
                    sensorManager.unregisterListener(this);
                } catch (Exception unused) {
                }
            }
        }
    }
}
