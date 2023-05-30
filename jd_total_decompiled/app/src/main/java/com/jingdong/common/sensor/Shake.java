package com.jingdong.common.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;

/* loaded from: classes6.dex */
public class Shake implements SensorEventListener {
    private static final int END_SHAKE = 2;
    private static final int SHAKE_FINISH = 3;
    private static final int SHAKE_TIME = 1000;
    private static final long SLEEP_TIME = 200;
    private static final int START_SHAKE = 1;
    private static final int VLAUEX = 0;
    private static final int VLAUEY = 1;
    private static final int VLAUEZ = 2;
    private static final long WAIT_TIME = 105;
    private static final float XDISTANCE = 19.0f;
    private static final float YDISTANCE = 19.0f;
    private static final float ZDISTANCE = 19.0f;
    private static SensorManager mSensorManager;
    private long end;
    private boolean isShake;
    private Context mContext;
    private MyHandler mHandler;
    private ShakeEventListener mShakeListener;
    private boolean once;
    private int shakeTime;
    private long start;
    private float xDistance;
    private float yDistance;
    private float zDistance;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class MyHandler extends Handler {
        private MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 1) {
                Shake.this.mHandler.sendEmptyMessageDelayed(2, Shake.SLEEP_TIME);
            } else if (i2 != 2) {
                if (i2 == 3 && !Shake.this.isShake) {
                    Shake.this.end = System.currentTimeMillis();
                    if (Shake.this.end - Shake.this.start > Shake.this.shakeTime) {
                        Shake.this.mShakeListener.onFinishShake();
                    } else {
                        Shake.this.mShakeListener.onShakeTooShort();
                    }
                    Shake.this.once = false;
                }
            } else {
                Shake.this.isShake = false;
                Shake.this.mHandler.sendEmptyMessageDelayed(3, Shake.WAIT_TIME);
            }
        }
    }

    public Shake(Context context, float f2, float f3, float f4, int i2) {
        this.shakeTime = 1000;
        this.xDistance = 19.0f;
        this.yDistance = 19.0f;
        this.zDistance = 19.0f;
        this.once = false;
        this.mContext = context;
        this.xDistance = f2;
        this.yDistance = f3;
        this.zDistance = f4;
        this.shakeTime = i2;
    }

    private void initSensorManager() {
        Sensor defaultSensor;
        SensorManager sensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        mSensorManager = sensorManager;
        if (sensorManager != null && (defaultSensor = sensorManager.getDefaultSensor(1)) != null) {
            mSensorManager.registerListener(this, defaultSensor, 2);
        }
        this.mHandler = new MyHandler();
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[2];
            if ((Math.abs(f2) > this.xDistance || Math.abs(f3) > this.yDistance || Math.abs(f4) > this.zDistance) && !this.isShake) {
                this.isShake = true;
                if (!this.once) {
                    this.start = System.currentTimeMillis();
                    this.once = true;
                    this.mShakeListener.onStartShake();
                }
                this.mHandler.sendEmptyMessageDelayed(1, SLEEP_TIME);
            }
        }
    }

    public void registerService(ShakeEventListener shakeEventListener) {
        this.mShakeListener = shakeEventListener;
        initSensorManager();
    }

    public void setShakeTime(int i2) {
        this.shakeTime = i2;
    }

    public void unRegisterService() {
        SensorManager sensorManager = mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(1);
            this.mHandler.removeCallbacksAndMessages(2);
            this.mHandler.removeCallbacksAndMessages(3);
        }
    }

    public Shake(Context context, int i2) {
        this.shakeTime = 1000;
        this.xDistance = 19.0f;
        this.yDistance = 19.0f;
        this.zDistance = 19.0f;
        this.once = false;
        this.mContext = context;
        this.shakeTime = i2;
    }

    public Shake(Context context) {
        this.shakeTime = 1000;
        this.xDistance = 19.0f;
        this.yDistance = 19.0f;
        this.zDistance = 19.0f;
        this.once = false;
        this.mContext = context;
    }
}
