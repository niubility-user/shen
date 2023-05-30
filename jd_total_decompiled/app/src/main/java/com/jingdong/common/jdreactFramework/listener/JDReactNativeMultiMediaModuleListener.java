package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.activities.FullScreenVideoActivity;
import com.jingdong.common.jdreactFramework.activities.FullScreenVideoPlayerAcitivity;
import com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactMediaUtil;
import com.jingdong.common.jdreactFramework.utils.ReactMessageUtils;
import com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil;
import com.jingdong.common.jdreactFramework.utils.video.VideoRecordActivity;
import com.jingdong.manto.jsapi.refact.JsApiScanCode;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes5.dex */
public class JDReactNativeMultiMediaModuleListener implements NativeMultiMediaModuleListener, JDReactOnActivityResultCallBack, JDFlutterCall {
    public static final String MULTIMEDIACHANNEL = "com.jd.jdflutter/multiMedia";
    public static final int REQUESTCAPTUREVIDEO = 12346;
    public static final int REQUESTPHOTO = 12345;
    public static final int REQUESTQRCODE = 10086;
    private static final String TAG = "JDReactNativeMultiMediaModuleListener";
    private ExecutorService mFixedThreadPool;
    public HashMap<String, WritableMap> mHashMap = new HashMap<>();
    public HashMap<String, WritableArray> mHashArray = new HashMap<>();

    public void compressBitmap(Bitmap bitmap, int i2, JDCallback jDCallback) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
        bitmap.recycle();
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        AresCommonUtils.invokeCallback(jDCallback, encodeToString);
    }

    public void compressBitmapToFile(Bitmap bitmap, int i2, JDCallback jDCallback, JDCallback jDCallback2) throws FileNotFoundException {
        BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (baseActivity != null && !baseActivity.isFinishing()) {
            File file = new File(baseActivity.getApplicationContext().getExternalCacheDir() + "/jdRN/");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, "compressed_" + System.currentTimeMillis() + ".jpg");
            bitmap.compress(Bitmap.CompressFormat.JPEG, i2, new BufferedOutputStream(new FileOutputStream(file2)));
            bitmap.recycle();
            AresCommonUtils.invokeCallback(jDCallback, file2.getAbsolutePath());
            return;
        }
        JLog.e(TAG, "activity is finish!");
        jDCallback2.invoke(new Object[0]);
    }

    public void preCompressBitmap(String str, int i2, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            compressBitmap(BitmapFactory.decodeFile(str, options), i2, jDCallback);
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        } catch (OutOfMemoryError e3) {
            JLog.e(TAG, e3.toString());
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        }
    }

    public void preCompressBitmapToFile(String str, int i2, JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            compressBitmapToFile(BitmapFactory.decodeFile(str, options), i2, jDCallback, jDCallback2);
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        } catch (OutOfMemoryError e3) {
            JLog.e(TAG, e3.toString());
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void captureVideo(JDCallback jDCallback, JDCallback jDCallback2) {
        BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (baseActivity != null) {
            ReactMessageUtils.startCaptureVideo(baseActivity, getCaptureVideoIntent(), this, 12346, jDCallback, jDCallback2);
        } else {
            jDCallback2.invoke(new Object[0]);
        }
    }

    public Intent getCaptureVideoIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(f.f19954c, "com.jingdong.common.jdreactFramework.activities.JDReactVideoRecordActivity"));
        return intent;
    }

    public Intent getFullScreenVideoView(String str, double d, boolean z) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        if (d == 0.0d) {
            intent.setComponent(new ComponentName(f.f19954c, "com.jingdong.common.jdreactFramework.activities.JDReactFullScreenVideoActivity"));
        } else {
            intent.setComponent(new ComponentName(f.f19954c, "com.jingdong.common.jdreactFramework.activities.FullScreenVideoPlayerAcitivity"));
            intent.putExtra(FullScreenVideoPlayerAcitivity.EXTRA_SHOW_CLOSE_BTN, z);
        }
        intent.putExtra("url", str);
        return intent;
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableMap getState(String str) {
        return this.mHashMap.get(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableArray getStateArray(String str) {
        return this.mHashArray.get(str);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void hidFullScreenVideoView(JDCallback jDCallback, JDCallback jDCallback2) {
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (currentMyActivity instanceof FullScreenVideoActivity) {
            if (!currentMyActivity.isFinishing()) {
                currentMyActivity.finish();
                jDCallback.invoke(new Object[0]);
                return;
            }
            jDCallback2.invoke(new Object[0]);
            return;
        }
        jDCallback2.invoke(new Object[0]);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void imageCompressToBase64(final String str, final int i2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        if (TextUtils.isEmpty(str)) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        if (this.mFixedThreadPool == null) {
            this.mFixedThreadPool = Executors.newSingleThreadExecutor();
        }
        this.mFixedThreadPool.execute(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.2
            {
                JDReactNativeMultiMediaModuleListener.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmap = null;
                try {
                    if (str.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
                        BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
                        if (baseActivity != null) {
                            bitmap = BitmapFactory.decodeStream(baseActivity.getContentResolver().openInputStream(Uri.parse(str)));
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(str);
                    }
                    if (bitmap != null) {
                        JDReactNativeMultiMediaModuleListener.this.compressBitmap(bitmap, i2, jDCallback);
                    } else {
                        AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
                    }
                } catch (OutOfMemoryError e2) {
                    JLog.e(JDReactNativeMultiMediaModuleListener.TAG, e2.toString());
                    if (0 == 0) {
                        return;
                    }
                    bitmap.recycle();
                    JDReactNativeMultiMediaModuleListener.this.preCompressBitmap(str, i2, jDCallback, jDCallback2);
                } catch (Throwable th) {
                    JLog.e(JDReactNativeMultiMediaModuleListener.TAG, th.toString());
                    AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
                }
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void imageCompression(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        try {
            if (hashMap.containsKey("fileurl") && hashMap.containsKey("compressionQuality")) {
                final String str = (String) hashMap.get("fileurl");
                double doubleValue = ((Double) hashMap.get("compressionQuality")).doubleValue();
                if (doubleValue < 0.0d) {
                    doubleValue = 0.1d;
                } else if (doubleValue > 1.0d) {
                    doubleValue = 0.9d;
                }
                final int i2 = (int) (doubleValue * 100.0d);
                if (this.mFixedThreadPool == null) {
                    this.mFixedThreadPool = Executors.newSingleThreadExecutor();
                }
                this.mFixedThreadPool.execute(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.3
                    {
                        JDReactNativeMultiMediaModuleListener.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapFactory.decodeFile(str);
                            JDReactNativeMultiMediaModuleListener.this.compressBitmapToFile(bitmap, i2, jDCallback, jDCallback2);
                        } catch (Exception e2) {
                            JLog.e(JDReactNativeMultiMediaModuleListener.TAG, e2.toString());
                            if (bitmap == null) {
                                return;
                            }
                            bitmap.recycle();
                            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
                        } catch (OutOfMemoryError e3) {
                            JLog.e(JDReactNativeMultiMediaModuleListener.TAG, "" + e3.toString());
                            JDReactNativeMultiMediaModuleListener.this.preCompressBitmapToFile(str, i2, jDCallback, jDCallback2);
                        }
                    }
                });
                return;
            }
            JLog.d(TAG, "not found key [fileUrl or quality]");
            jDCallback2.invoke(new Object[0]);
        } catch (Exception e2) {
            JLog.e(TAG, "" + e2.toString());
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        if (i2 == 10086) {
            if (-1 != i3) {
                if (i3 == 0) {
                    saveState(String.valueOf(10086), null);
                    return;
                }
                return;
            }
            String stringExtra = intent.getStringExtra("content");
            if (stringExtra != null) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("content", stringExtra);
                saveState(String.valueOf(10086), createMap);
                return;
            }
            saveState(String.valueOf(10086), null);
        } else if (i2 == 12345) {
            if (-1 == i3) {
                ReactMediaUtil.dealActivityResult(activity, i2, i3, intent);
            } else if (i3 == 0) {
                saveState(String.valueOf(12345), null);
            }
        } else if (i2 != 12346) {
        } else {
            if (-1 != i3) {
                if (i3 == 0) {
                    saveState(String.valueOf(12346), null);
                    return;
                }
                return;
            }
            String stringExtra2 = intent.getStringExtra(VideoRecordActivity.RECORD_VIDEO_PATH);
            if (stringExtra2 != null) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("filepath", stringExtra2);
                saveState(String.valueOf(12346), createMap2);
                return;
            }
            saveState(String.valueOf(12346), null);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onHostDestroy() {
        try {
            ReactAudioPlayerUtil.getInstance().stop();
            ReactAudioPlayerUtil.getInstance().free();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onHostPause() {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void onHostResume() {
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("showFullScreenVideoView")) {
            showFullScreenVideoView(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.4
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.5
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("stopPlaying")) {
            stopPlaying(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.6
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.7
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("startPlaying")) {
            startPlaying(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.8
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.9
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("hidFullScreenVideoView")) {
            hidFullScreenVideoView(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.10
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.11
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals(JsApiScanCode.JSAPI_NAME)) {
            scanCode(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.12
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.13
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("captureVideo")) {
            captureVideo(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.14
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.15
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("pickPhoto")) {
            pickPhoto(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.16
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.17
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("imageCompressToBase64")) {
            imageCompressToBase64(hashMap.containsKey("path") ? (String) hashMap.get("path") : "", hashMap.containsKey("quality") ? (int) ((Double) hashMap.get("quality")).doubleValue() : 0, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.18
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.19
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("imageCompression")) {
            imageCompression(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.20
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.21
                {
                    JDReactNativeMultiMediaModuleListener.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void pickPhoto(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback2 != null) {
            jDCallback2.invoke("function deprecated");
        }
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableMap removeState(String str) {
        return this.mHashMap.remove(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public WritableArray removeStateArray(String str) {
        return this.mHashArray.remove(str);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public void saveState(String str, WritableMap writableMap) {
        this.mHashMap.put(str, writableMap);
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack
    public void saveStateArray(String str, WritableArray writableArray) {
        this.mHashArray.put(str, writableArray);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void scanCode(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (baseActivity != null) {
            ReactMessageUtils.startQRScan(baseActivity, this, 10086, jDCallback, jDCallback2);
        } else {
            jDCallback2.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void showFullScreenVideoView(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap.containsKey("url")) {
            JDReactHelper.newInstance().getApplicationContext().startActivity(getFullScreenVideoView((String) hashMap.get("url"), hashMap.containsKey("type") ? ((Double) hashMap.get("type")).doubleValue() : 0.0d, hashMap.containsKey("showCloseBtn") ? ((Boolean) hashMap.get("showCloseBtn")).booleanValue() : false));
            jDCallback.invoke(new Object[0]);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
    public void startPlaying(HashMap hashMap, final JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            if (hashMap.containsKey("filepath")) {
                final String str = (String) hashMap.get("filepath");
                ReactAudioPlayerUtil.getInstance();
                new Handler(Looper.getMainLooper()).post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0021: INVOKE 
                      (wrap: android.os.Handler : 0x0019: CONSTRUCTOR 
                      (wrap: android.os.Looper : 0x0015: INVOKE  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper A[Catch: Exception -> 0x002b, MD:():android.os.Looper (c), WRAPPED])
                     A[Catch: Exception -> 0x002b, MD:(android.os.Looper):void (c), WRAPPED] (LINE:4) call: android.os.Handler.<init>(android.os.Looper):void type: CONSTRUCTOR)
                      (wrap: java.lang.Runnable : 0x001e: CONSTRUCTOR 
                      (r4v0 'this' com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener A[IMMUTABLE_TYPE, THIS])
                      (r0 I:com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v4 'str' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r6v0 'jDCallback' com.jingdong.common.jdreactFramework.JDCallback A[DONT_INLINE])
                     A[Catch: Exception -> 0x002b, MD:(com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener, com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil, java.lang.String, com.jingdong.common.jdreactFramework.JDCallback):void (m), WRAPPED] call: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.1.<init>(com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener, com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil, java.lang.String, com.jingdong.common.jdreactFramework.JDCallback):void type: CONSTRUCTOR)
                     type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[Catch: Exception -> 0x002b, MD:(java.lang.Runnable):boolean (c)] (LINE:4) in method: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.startPlaying(java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void, file: classes5.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    this = this;
                    java.lang.String r0 = "filepath"
                    r1 = 0
                    boolean r2 = r5.containsKey(r0)     // Catch: java.lang.Exception -> L2b
                    if (r2 == 0) goto L25
                    java.lang.Object r5 = r5.get(r0)     // Catch: java.lang.Exception -> L2b
                    java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> L2b
                    com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil r0 = com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil.getInstance()     // Catch: java.lang.Exception -> L2b
                    android.os.Handler r2 = new android.os.Handler     // Catch: java.lang.Exception -> L2b
                    android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch: java.lang.Exception -> L2b
                    r2.<init>(r3)     // Catch: java.lang.Exception -> L2b
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener$1 r3 = new com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener$1     // Catch: java.lang.Exception -> L2b
                    r3.<init>()     // Catch: java.lang.Exception -> L2b
                    r2.post(r3)     // Catch: java.lang.Exception -> L2b
                    goto L30
                L25:
                    java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L2b
                    r7.invoke(r5)     // Catch: java.lang.Exception -> L2b
                    goto L30
                L2b:
                    java.lang.Object[] r5 = new java.lang.Object[r1]
                    r7.invoke(r5)
                L30:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener.startPlaying(java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void");
            }

            @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
            public void stopPlaying(JDCallback jDCallback, JDCallback jDCallback2) {
                try {
                    ReactAudioPlayerUtil.getInstance().stop();
                    jDCallback.invoke(new Object[0]);
                } catch (Exception unused) {
                    jDCallback2.invoke(new Object[0]);
                }
            }

            public Intent getCaptureVideoIntent(int i2) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(f.f19954c, "com.jingdong.common.jdreactFramework.activities.JDReactVideoRecordActivity"));
                intent.putExtra(VideoRecordActivity.KEY_MAX_RECORD_TIME, i2);
                return intent;
            }

            @Override // com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener
            public void captureVideo(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
                int i2;
                BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
                if (baseActivity != null) {
                    try {
                        i2 = Integer.parseInt((String) hashMap.get("maxTimeInSeconds")) * 1000;
                    } catch (Exception unused) {
                        i2 = 10000;
                    }
                    ReactMessageUtils.startCaptureVideo(baseActivity, getCaptureVideoIntent(i2 > 0 ? i2 : 10000), this, 12346, jDCallback, jDCallback2);
                    return;
                }
                jDCallback2.invoke(new Object[0]);
            }
        }
