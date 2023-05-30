package com.jingdong.jdreact.plugin.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.RollRecoveryEntry;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes13.dex */
public class JDReactSensor extends ReactContextBaseJavaModule implements SensorEventListener {
    private int interval;
    private double lastReading;
    private int logLevel;
    private float[] orientation;
    private float[] quaternion;
    private final ReactApplicationContext reactContext;
    private float[] rotation;
    private Sensor sensor;
    private SensorManager sensorManager;
    private String sensorName;
    private int sensorType;

    public JDReactSensor(ReactApplicationContext reactApplicationContext, String str, int i2) {
        super(reactApplicationContext);
        this.lastReading = System.currentTimeMillis();
        this.logLevel = 0;
        this.rotation = new float[9];
        this.orientation = new float[3];
        this.quaternion = new float[4];
        this.reactContext = reactApplicationContext;
        this.sensorType = i2;
        this.sensorName = str;
        if (JDReactHelper.newInstance().isAgreedPrivacy()) {
            initSensorManager();
        }
    }

    private void initSensorManager() {
        if (this.sensorManager == null) {
            SensorManager sensorManager = (SensorManager) this.reactContext.getSystemService("sensor");
            this.sensorManager = sensorManager;
            this.sensor = sensorManager.getDefaultSensor(this.sensorType);
        }
    }

    private void sendEvent(String str, @Nullable WritableMap writableMap) {
        try {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        } catch (RuntimeException unused) {
        }
    }

    private static double sensorTimestampToEpochMilliseconds(long j2) {
        if (Build.VERSION.SDK_INT >= 17) {
            return System.currentTimeMillis() + ((j2 - SystemClock.elapsedRealtimeNanos()) / 1000000);
        }
        return System.currentTimeMillis() + 1000;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return this.sensorName;
    }

    @ReactMethod
    public void isAvailable(Promise promise) {
        if (!JDReactHelper.newInstance().isAgreedPrivacy()) {
            JLog.e("JDReactSensor", "\u9690\u79c1\u534f\u8bae\u672a\u540c\u610f");
            promise.resolve(null);
            return;
        }
        initSensorManager();
        if (this.sensor == null) {
            promise.reject(new RuntimeException("No " + this.sensorName + " found"));
            return;
        }
        promise.resolve(null);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type != this.sensorType) {
            return;
        }
        double currentTimeMillis = System.currentTimeMillis();
        double d = this.lastReading;
        Double.isNaN(currentTimeMillis);
        if (currentTimeMillis - d >= this.interval) {
            this.lastReading = currentTimeMillis;
            WritableMap createMap = Arguments.createMap();
            if (type == 1 || type == 2 || type == 4) {
                createMap.putDouble(JshopConst.JSHOP_PROMOTIO_X, sensorEvent.values[0]);
                createMap.putDouble(JshopConst.JSHOP_PROMOTIO_Y, sensorEvent.values[1]);
                createMap.putDouble("z", sensorEvent.values[2]);
            } else if (type == 6) {
                createMap.putDouble("pressure", sensorEvent.values[0]);
            } else if (type != 11) {
                String str = "Sensor type '" + type + "' not implemented!";
                return;
            } else {
                SensorManager.getQuaternionFromVector(this.quaternion, sensorEvent.values);
                SensorManager.getRotationMatrixFromVector(this.rotation, sensorEvent.values);
                SensorManager.getOrientation(this.rotation, this.orientation);
                createMap.putDouble("qw", this.quaternion[0]);
                createMap.putDouble("qx", this.quaternion[1]);
                createMap.putDouble("qy", this.quaternion[2]);
                createMap.putDouble("qz", this.quaternion[3]);
                createMap.putDouble("yaw", this.orientation[0]);
                createMap.putDouble("pitch", this.orientation[1]);
                createMap.putDouble(RollRecoveryEntry.TYPE, this.orientation[2]);
            }
            createMap.putDouble(VerifyTracker.KEY_TIMESTAMP, sensorTimestampToEpochMilliseconds(sensorEvent.timestamp));
            sendEvent(this.sensorName, createMap);
        }
    }

    @ReactMethod
    public void setLogLevel(int i2) {
        this.logLevel = i2;
    }

    @ReactMethod
    public void setUpdateInterval(int i2) {
        this.interval = i2;
    }

    @ReactMethod
    public void startUpdates() {
        if (!JDReactHelper.newInstance().isAgreedPrivacy()) {
            JLog.e("JDReactSensor", "\u9690\u79c1\u534f\u8bae\u540c\u610f\u524d\uff0c\u4e0d\u80fd\u4f7f\u7528\u4f20\u611f\u5668\u529f\u80fd");
            return;
        }
        initSensorManager();
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                this.sensorManager.registerListener(this, this.sensor, 2);
            } else {
                this.sensorManager.registerListener(this, this.sensor, this.interval * 1000);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @ReactMethod
    public void stopUpdates() {
        SensorManager sensorManager = this.sensorManager;
        if (sensorManager == null) {
            return;
        }
        sensorManager.unregisterListener(this);
    }
}
