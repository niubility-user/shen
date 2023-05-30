package com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.annotation.Nullable;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class AppRTCProximitySensor implements SensorEventListener {
    private static final String TAG = "AppRTCProximitySensor";
    private boolean lastStateReportIsNear;
    private final Runnable onSensorStateListener;
    @Nullable
    private Sensor proximitySensor;
    private final SensorManager sensorManager;
    private final ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();

    private AppRTCProximitySensor(Context context, Runnable runnable) {
        OKLog.d(TAG, TAG + AppRTCUtils.getThreadInfo());
        this.onSensorStateListener = runnable;
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
    }

    public static AppRTCProximitySensor create(Context context, Runnable runnable) {
        return new AppRTCProximitySensor(context, runnable);
    }

    private boolean initDefaultSensor() {
        if (this.proximitySensor != null) {
            return true;
        }
        Sensor defaultSensor = this.sensorManager.getDefaultSensor(8);
        this.proximitySensor = defaultSensor;
        if (defaultSensor == null) {
            return false;
        }
        logProximitySensorInfo();
        return true;
    }

    private void logProximitySensorInfo() {
        if (this.proximitySensor == null) {
            return;
        }
        StringBuilder sb = new StringBuilder("Proximity sensor: ");
        sb.append("name=");
        sb.append(this.proximitySensor.getName());
        sb.append(", vendor: ");
        sb.append(this.proximitySensor.getVendor());
        sb.append(", power: ");
        sb.append(this.proximitySensor.getPower());
        sb.append(", resolution: ");
        sb.append(this.proximitySensor.getResolution());
        sb.append(", max range: ");
        sb.append(this.proximitySensor.getMaximumRange());
        sb.append(", min delay: ");
        sb.append(this.proximitySensor.getMinDelay());
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            sb.append(", type: ");
            sb.append(this.proximitySensor.getStringType());
        }
        if (i2 >= 21) {
            sb.append(", max delay: ");
            sb.append(this.proximitySensor.getMaxDelay());
            sb.append(", reporting mode: ");
            sb.append(this.proximitySensor.getReportingMode());
            sb.append(", isWakeUpSensor: ");
            sb.append(this.proximitySensor.isWakeUpSensor());
        }
        OKLog.d(TAG, sb.toString());
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i2) {
        this.threadChecker.checkIsOnValidThread();
        AppRTCUtils.assertIsTrue(sensor.getType() == 8);
        if (i2 == 0) {
            OKLog.e(TAG, "The values returned by this sensor cannot be trusted");
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        this.threadChecker.checkIsOnValidThread();
        AppRTCUtils.assertIsTrue(sensorEvent.sensor.getType() == 8);
        if (sensorEvent.values[0] < this.proximitySensor.getMaximumRange()) {
            OKLog.d(TAG, "Proximity sensor => NEAR state");
            this.lastStateReportIsNear = true;
        } else {
            OKLog.d(TAG, "Proximity sensor => FAR state");
            this.lastStateReportIsNear = false;
        }
        Runnable runnable = this.onSensorStateListener;
        if (runnable != null) {
            runnable.run();
        }
        OKLog.d(TAG, "onSensorChanged" + AppRTCUtils.getThreadInfo() + ": accuracy=" + sensorEvent.accuracy + ", timestamp=" + sensorEvent.timestamp + ", distance=" + sensorEvent.values[0]);
    }

    public boolean sensorReportsNearState() {
        this.threadChecker.checkIsOnValidThread();
        return this.lastStateReportIsNear;
    }

    public boolean start() {
        this.threadChecker.checkIsOnValidThread();
        OKLog.d(TAG, "start" + AppRTCUtils.getThreadInfo());
        if (initDefaultSensor()) {
            this.sensorManager.registerListener(this, this.proximitySensor, 3);
            return true;
        }
        return false;
    }

    public void stop() {
        this.threadChecker.checkIsOnValidThread();
        OKLog.d(TAG, "stop" + AppRTCUtils.getThreadInfo());
        Sensor sensor = this.proximitySensor;
        if (sensor == null) {
            return;
        }
        this.sensorManager.unregisterListener(this, sensor);
    }
}
