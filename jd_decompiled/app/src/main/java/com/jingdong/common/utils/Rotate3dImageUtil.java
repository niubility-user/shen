package com.jingdong.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class Rotate3dImageUtil {
    private static final float FLIP_DEGREE = 20.0f;
    private static final float NS2S = 1.0E-9f;
    private static final String TAG = "Rotate3dImageUtil";
    private static final int what = 111;
    private Sensor gyroscopeSensor;
    private List<String> imageUrls;
    private ImageView imageView;
    private boolean isSupportRote3d;
    private int loadCount;
    private Context mContext;
    private SensorManager mSensorManager;
    Bitmap mTempBitmap;
    private OnLoadCompletedListener onLoadCompletedListener;
    private String pageName;
    private String skuId;
    private String skuTag;
    private long timestamp;
    private int imageCount = 0;
    private boolean loadFail = false;
    private float minDegree = -1.0f;
    private int lastIndex = -1;
    private boolean isDestroy = false;
    private boolean isRegister = false;
    private boolean forceShow = false;
    public int lastExpoIndex = -1;
    private JDSimpleImageLoadingListener jdSimpleImageLoadingListener = new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.utils.Rotate3dImageUtil.1
        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            if (Rotate3dImageUtil.this.isDestroy || bitmap == null || Rotate3dImageUtil.this.imageView == null) {
                return;
            }
            Rotate3dImageUtil.this.imageView.setImageBitmap(bitmap);
            Bitmap bitmap2 = Rotate3dImageUtil.this.mTempBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                Rotate3dImageUtil.this.mTempBitmap.recycle();
            }
            Rotate3dImageUtil.this.mTempBitmap = bitmap;
        }
    };
    private JDDisplayImageOptions options = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565);
    private Handler handler = new Handler() { // from class: com.jingdong.common.utils.Rotate3dImageUtil.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (Rotate3dImageUtil.this.isDestroy || message.what != 111 || Rotate3dImageUtil.this.imageView == null || !Rotate3dImageUtil.this.isRegister()) {
                return;
            }
            Rotate3dImageUtil.this.showImage(((Float) message.obj).floatValue());
        }
    };
    private MySensorEventListener mMySensorEventListener = new MySensorEventListener();
    private float[] angle = new float[3];
    private float[] degree = new float[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class MySensorEventListener implements SensorEventListener {
        MySensorEventListener() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 4) {
                if (Rotate3dImageUtil.this.timestamp != 0) {
                    float f2 = ((float) (sensorEvent.timestamp - Rotate3dImageUtil.this.timestamp)) * Rotate3dImageUtil.NS2S;
                    float[] fArr = Rotate3dImageUtil.this.angle;
                    fArr[0] = fArr[0] + (sensorEvent.values[0] * f2);
                    float[] fArr2 = Rotate3dImageUtil.this.angle;
                    fArr2[1] = fArr2[1] + (sensorEvent.values[1] * f2);
                    float[] fArr3 = Rotate3dImageUtil.this.angle;
                    fArr3[2] = fArr3[2] + (sensorEvent.values[2] * f2);
                    Rotate3dImageUtil.this.degree[0] = (float) Math.toDegrees(Rotate3dImageUtil.this.angle[0]);
                    Rotate3dImageUtil.this.degree[1] = (float) Math.toDegrees(Rotate3dImageUtil.this.angle[1]);
                    Rotate3dImageUtil.this.degree[2] = (float) Math.toDegrees(Rotate3dImageUtil.this.angle[2]);
                    Rotate3dImageUtil.this.handler.sendMessage(Rotate3dImageUtil.this.handler.obtainMessage(111, Float.valueOf(Rotate3dImageUtil.this.degree[1])));
                }
                Rotate3dImageUtil.this.timestamp = sensorEvent.timestamp;
            }
        }
    }

    /* loaded from: classes6.dex */
    public interface OnLoadCompletedListener {
        void onLoadCompleted(boolean z);
    }

    private Rotate3dImageUtil(Context context) {
        this.isSupportRote3d = false;
        this.mContext = context;
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.mSensorManager = sensorManager;
        if (sensorManager != null) {
            Sensor defaultSensor = sensorManager.getDefaultSensor(4);
            this.gyroscopeSensor = defaultSensor;
            boolean z = defaultSensor != null;
            this.isSupportRote3d = z;
            if (z && this.options != null && supportHighQuality()) {
                this.options.bitmapConfig(Bitmap.Config.ARGB_8888);
            }
        }
    }

    static /* synthetic */ int access$308(Rotate3dImageUtil rotate3dImageUtil) {
        int i2 = rotate3dImageUtil.loadCount;
        rotate3dImageUtil.loadCount = i2 + 1;
        return i2;
    }

    public static Rotate3dImageUtil create(Context context) {
        return new Rotate3dImageUtil(context);
    }

    public static boolean isSupportRotate3d(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        return (sensorManager == null || sensorManager.getDefaultSensor(4) == null) ? false : true;
    }

    private void loadAllImage() {
        this.loadCount = 0;
        this.loadFail = false;
        if (this.isSupportRote3d) {
            Iterator<String> it = this.imageUrls.iterator();
            while (it.hasNext()) {
                JDImageUtils.loadImageToDiskCache(it.next(), new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.utils.Rotate3dImageUtil.3
                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        Rotate3dImageUtil.access$308(Rotate3dImageUtil.this);
                        if (Rotate3dImageUtil.this.loadFail || Rotate3dImageUtil.this.loadCount != Rotate3dImageUtil.this.imageCount || Rotate3dImageUtil.this.onLoadCompletedListener == null) {
                            return;
                        }
                        Rotate3dImageUtil.this.onLoadCompletedListener.onLoadCompleted(true);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        Rotate3dImageUtil.this.loadFail = true;
                        if (Rotate3dImageUtil.this.onLoadCompletedListener != null) {
                            Rotate3dImageUtil.this.onLoadCompletedListener.onLoadCompleted(false);
                        }
                    }
                });
            }
            return;
        }
        OnLoadCompletedListener onLoadCompletedListener = this.onLoadCompletedListener;
        if (onLoadCompletedListener != null) {
            onLoadCompletedListener.onLoadCompleted(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showImage(float f2) {
        List<String> list;
        int i2 = this.imageCount;
        if (i2 == 0 || (list = this.imageUrls) == null) {
            return;
        }
        float f3 = this.minDegree;
        if (f3 == -1.0f) {
            this.minDegree = f2 - 20.0f;
        } else if (f2 < f3) {
            this.minDegree = f2;
        } else if (f2 > f3 + 40.0f) {
            this.minDegree = f2 - 40.0f;
        }
        int i3 = (int) (((f2 - this.minDegree) * i2) / 40.0f);
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 >= i2) {
            i3 = i2 - 1;
        }
        if (this.forceShow || i3 != this.lastIndex) {
            this.forceShow = false;
            JDImageUtils.loadImage(list.get(i3), this.options, this.jdSimpleImageLoadingListener, (JDImageLoadingProgressListener) null);
            this.lastIndex = i3;
            if (TextUtils.isEmpty(this.pageName)) {
                return;
            }
            if ((i3 == 0 || i3 == this.imageCount - 1) && this.lastExpoIndex != i3) {
                this.lastExpoIndex = i3;
                PDHelper.onExposure(this.pageName, this.skuId, "Productdetail_3DPanoramaEffect", null, null, this.skuTag);
            }
        }
    }

    public boolean isActivity() {
        return this.imageCount > 0;
    }

    public boolean isRegister() {
        return this.isRegister;
    }

    public void onDestroy() {
        this.isDestroy = true;
        unRegister();
        this.mSensorManager = null;
        this.mMySensorEventListener = null;
        this.imageView = null;
        List<String> list = this.imageUrls;
        if (list != null) {
            list.clear();
            this.imageUrls = null;
        }
        this.mTempBitmap = null;
    }

    public synchronized void register() {
        SensorManager sensorManager;
        OKLog.d(TAG, "register");
        if (!this.isDestroy && (sensorManager = this.mSensorManager) != null && this.isSupportRote3d) {
            if (!this.isRegister) {
                sensorManager.registerListener(this.mMySensorEventListener, this.gyroscopeSensor, 1);
                this.isRegister = true;
                this.forceShow = true;
                Handler handler = this.handler;
                if (handler != null) {
                    handler.sendMessage(handler.obtainMessage(111, Float.valueOf(0.0f)));
                }
            }
        }
    }

    public Rotate3dImageUtil setImageUrls(List<String> list) {
        if (list != null) {
            this.imageUrls = list;
            this.imageCount = list.size();
            loadAllImage();
        } else {
            this.imageCount = 0;
        }
        return this;
    }

    public Rotate3dImageUtil setImageView(ImageView imageView) {
        if (imageView != null) {
            this.imageView = imageView;
        }
        return this;
    }

    public void setMtaParam(String str, String str2, String str3) {
        this.pageName = str;
        this.skuId = str2;
        this.skuTag = str3;
    }

    public void setOnLoadCompletedListener(OnLoadCompletedListener onLoadCompletedListener) {
        this.onLoadCompletedListener = onLoadCompletedListener;
    }

    public Rotate3dImageUtil setOnlyImage(List<String> list) {
        if (list != null) {
            this.imageUrls = list;
            this.imageCount = list.size();
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0048 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004a A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean supportHighQuality() {
        /*
            r5 = this;
            com.jingdong.jdsdk.JdSdk r0 = com.jingdong.jdsdk.JdSdk.getInstance()
            android.app.Application r0 = r0.getApplication()
            java.lang.String r1 = "activity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            if (r0 == 0) goto L3f
            android.app.ActivityManager$MemoryInfo r1 = new android.app.ActivityManager$MemoryInfo
            r1.<init>()
            int r2 = android.os.Build.VERSION.SDK_INT
            r0.getMemoryInfo(r1)
            r0 = 16
            if (r2 < r0) goto L3f
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D
            if (r0 == 0) goto L3c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Mem.totalSize = "
            r0.append(r2)
            long r2 = r1.totalMem
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "Rotate3dImageUtil"
            com.jingdong.sdk.oklog.OKLog.d(r2, r0)
        L3c:
            long r0 = r1.totalMem
            goto L41
        L3f:
            r0 = 0
        L41:
            r2 = -2147483648(0xffffffff80000000, double:NaN)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L4a
            r0 = 1
            goto L4b
        L4a:
            r0 = 0
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.Rotate3dImageUtil.supportHighQuality():boolean");
    }

    public synchronized void unRegister() {
        MySensorEventListener mySensorEventListener;
        OKLog.d(TAG, "unRegister");
        this.isRegister = false;
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null && (mySensorEventListener = this.mMySensorEventListener) != null) {
            sensorManager.unregisterListener(mySensorEventListener);
        }
    }
}
