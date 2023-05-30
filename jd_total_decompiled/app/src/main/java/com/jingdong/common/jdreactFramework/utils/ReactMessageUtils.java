package com.jingdong.common.jdreactFramework.utils;

import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkAddressHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonPayListener;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeMultiMediaModuleListener;
import com.jingdong.common.jdreactFramework.modules.JDReactOnActivityResultCallBack;
import com.jingdong.common.jdreactFramework.utils.ReactMediaUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes5.dex */
public class ReactMessageUtils {
    public static final String TAG = "ReactMessageUtils";

    /* loaded from: classes5.dex */
    static class CallBackRunnable implements Runnable {
        JDCallback errorCB;
        JDReactOnActivityResultCallBack moudle;
        int requestCode;
        JDCallback successCB;
        int type;

        CallBackRunnable(JDCallback jDCallback, JDCallback jDCallback2, int i2, JDReactOnActivityResultCallBack jDReactOnActivityResultCallBack, int i3) {
            this.requestCode = i2;
            this.successCB = jDCallback;
            this.errorCB = jDCallback2;
            this.moudle = jDReactOnActivityResultCallBack;
            this.type = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.type == 0) {
                WritableMap removeState = this.moudle.removeState(String.valueOf(this.requestCode));
                if (removeState != null) {
                    JDCallback jDCallback = this.successCB;
                    if (jDCallback != null) {
                        jDCallback.invoke(removeState);
                        this.successCB = null;
                        return;
                    }
                    return;
                }
                JDCallback jDCallback2 = this.errorCB;
                if (jDCallback2 != null) {
                    jDCallback2.invoke(new Object[0]);
                    this.errorCB = null;
                    return;
                }
                return;
            }
            WritableArray removeStateArray = this.moudle.removeStateArray(String.valueOf(this.requestCode));
            if (removeStateArray != null) {
                JDCallback jDCallback3 = this.successCB;
                if (jDCallback3 != null) {
                    jDCallback3.invoke(removeStateArray);
                    this.successCB = null;
                    return;
                }
                return;
            }
            JDCallback jDCallback4 = this.errorCB;
            if (jDCallback4 != null) {
                jDCallback4.invoke(new Object[0]);
                this.errorCB = null;
            }
        }
    }

    public static String getPhotoCreateDate(String str) {
        JLog.d(TAG, " path: " + str);
        String str2 = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(new ExifInterface(str).getAttribute("DateTime"));
            str2 = simpleDateFormat.format(date);
            JLog.d(TAG, " createDate: " + str2);
        } catch (Exception e2) {
            JLog.e(TAG, e2.getMessage());
        }
        if (str2.isEmpty()) {
            File file = new File(str);
            if (file.exists()) {
                date.setTime(file.lastModified());
                String format = simpleDateFormat.format(date);
                JLog.d(TAG, " FileDate: " + format);
                return format;
            }
            return str2;
        }
        return str2;
    }

    public static void registCallback(final IMyActivity iMyActivity, final Runnable runnable) {
        iMyActivity.addResumeListener(new IResumeListener() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.6
            @Override // com.jingdong.common.frame.IResumeListener
            public void onResume() {
                IMyActivity.this.removeResumeListener(this);
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    public static void send(ReactContext reactContext, String str, WritableMap writableMap) {
        sendEvent(reactContext, str, writableMap);
    }

    private static void sendEvent(ReactContext reactContext, String str, @Nullable WritableMap writableMap) {
        if (reactContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    public static void startActivityWithCallback(final BaseActivity baseActivity, final Runnable runnable, final Intent intent, final int i2) {
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.1
            @Override // java.lang.Runnable
            public void run() {
                BaseActivity.this.startActivityForResult(intent, i2);
                ReactMessageUtils.registCallback(BaseActivity.this, runnable);
            }
        });
    }

    public static void startCaptureVideo(final BaseActivity baseActivity, final Intent intent, final JDReactNativeMultiMediaModuleListener jDReactNativeMultiMediaModuleListener, final int i2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.5
            @Override // java.lang.Runnable
            public void run() {
                BaseActivity.this.startActivityForResult(intent, i2);
                ReactMessageUtils.registCallback(BaseActivity.this, new CallBackRunnable(jDCallback, jDCallback2, i2, jDReactNativeMultiMediaModuleListener, 0));
            }
        });
    }

    public static void startJDPay(final BaseActivity baseActivity, final JDReactNativeCommonPayListener jDReactNativeCommonPayListener, final Bundle bundle, final int i2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.4
            @Override // java.lang.Runnable
            public void run() {
                new Bundle().putInt("needCallback", 1);
                bundle.putBoolean("JDPAY_EXTERNAL", false);
                DeeplinkJDpaySdkHelper.startJDPayActivityForResult(baseActivity, bundle, i2);
                ReactMessageUtils.registCallback(baseActivity, new CallBackRunnable(jDCallback, jDCallback2, i2, jDReactNativeCommonPayListener, 0));
            }
        });
    }

    public static void startPhotoPicker(final BaseActivity baseActivity, final int i2, final JDReactNativeMultiMediaModuleListener jDReactNativeMultiMediaModuleListener, final ArrayList<String> arrayList, final int i3, final JDCallback jDCallback, final JDCallback jDCallback2) {
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.2
            @Override // java.lang.Runnable
            public void run() {
                ReactMediaUtil.selectImage(BaseActivity.this, i2, arrayList, new ReactMediaUtil.SelectFileListener() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.2.1
                    @Override // com.jingdong.common.jdreactFramework.utils.ReactMediaUtil.SelectFileListener
                    public void onSelected(List<String> list) {
                        WritableArray createArray = Arguments.createArray();
                        if (list != null && list.size() > 0) {
                            for (String str : list) {
                                WritableMap createMap = Arguments.createMap();
                                createMap.putString("url", str);
                                createMap.putString("createdate", "" + ReactMessageUtils.getPhotoCreateDate(str));
                                createArray.pushMap(createMap);
                            }
                        }
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        jDReactNativeMultiMediaModuleListener.saveStateArray(String.valueOf(i3), createArray);
                    }
                });
                ReactMessageUtils.registCallback(BaseActivity.this, new CallBackRunnable(jDCallback, jDCallback2, i3, jDReactNativeMultiMediaModuleListener, 1));
            }
        });
    }

    public static void startQRScan(final BaseActivity baseActivity, final JDReactNativeMultiMediaModuleListener jDReactNativeMultiMediaModuleListener, final int i2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.3
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putInt("needCallback", 1);
                DeepLinkScanHelper.startCaptureActivityForResult(BaseActivity.this, bundle, i2, false);
                ReactMessageUtils.registCallback(BaseActivity.this, new CallBackRunnable(jDCallback, jDCallback2, i2, jDReactNativeMultiMediaModuleListener, 0));
            }
        });
    }

    public static void startSelectAddressList(final BaseActivity baseActivity, final Bundle bundle, final JDReactOnActivityResultCallBack jDReactOnActivityResultCallBack, final int i2, final JDCallback jDCallback, final JDCallback jDCallback2) {
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.ReactMessageUtils.7
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkAddressHelper.startNewEasyAddressListDynamic(BaseActivity.this, bundle, true, i2);
                ReactMessageUtils.registCallback(BaseActivity.this, new CallBackRunnable(jDCallback, jDCallback2, i2, jDReactOnActivityResultCallBack, 0));
            }
        });
    }
}
