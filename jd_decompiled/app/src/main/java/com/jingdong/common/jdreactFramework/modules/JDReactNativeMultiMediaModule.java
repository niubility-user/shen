package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeAudioRecordListener;
import com.jingdong.common.jdreactFramework.listener.NativeFileUploadListener;
import com.jingdong.common.jdreactFramework.listener.NativeMultiMediaModuleListener;

/* loaded from: classes5.dex */
public class JDReactNativeMultiMediaModule extends ReactContextBaseJavaModule implements ActivityEventListener, LifecycleEventListener {
    private NativeAudioRecordListener mNativeAudioRecordListener;
    private NativeFileUploadListener mNativeFileUploadListener;
    private NativeMultiMediaModuleListener mNativeMultiMediaModuleListener;
    private ReactApplicationContext mReactContext;

    public JDReactNativeMultiMediaModule(ReactApplicationContext reactApplicationContext, NativeMultiMediaModuleListener nativeMultiMediaModuleListener, NativeFileUploadListener nativeFileUploadListener, NativeAudioRecordListener nativeAudioRecordListener) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        reactApplicationContext.addLifecycleEventListener(this);
        this.mNativeMultiMediaModuleListener = nativeMultiMediaModuleListener;
        this.mNativeFileUploadListener = nativeFileUploadListener;
        this.mNativeAudioRecordListener = nativeAudioRecordListener;
        this.mReactContext = reactApplicationContext;
    }

    @ReactMethod
    private void imageCompressToBase64(String str, int i2, Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.imageCompressToBase64(str, i2, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void captureVideo(Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.captureVideo(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void captureVideoWithParams(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.captureVideo(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void deleteRecordingFile(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeAudioRecordListener nativeAudioRecordListener = this.mNativeAudioRecordListener;
        if (nativeAudioRecordListener != null) {
            nativeAudioRecordListener.deleteRecordingFile(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void fileToBase64(String str, Callback callback, Callback callback2) {
        NativeFileUploadListener nativeFileUploadListener = this.mNativeFileUploadListener;
        if (nativeFileUploadListener != null) {
            nativeFileUploadListener.fileToBase64(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getFileName(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeFileUploadListener nativeFileUploadListener = this.mNativeFileUploadListener;
        if (nativeFileUploadListener != null) {
            nativeFileUploadListener.getFileName(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getFileSize(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeFileUploadListener nativeFileUploadListener = this.mNativeFileUploadListener;
        if (nativeFileUploadListener != null) {
            nativeFileUploadListener.getFileSize(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeMultiMediaModule";
    }

    @ReactMethod
    public void hidFullScreenVideoView(Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.hidFullScreenVideoView(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void imageCompression(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.imageCompression(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.onActivityResult(activity, i2, i3, intent);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.onHostDestroy();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.onHostPause();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.onHostResume();
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void pickPhoto(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.pickPhoto(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void scanCode(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.scanCode(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void showFullScreenVideoView(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.showFullScreenVideoView(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void startPlaying(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.startPlaying(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void startRecording(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeAudioRecordListener nativeAudioRecordListener = this.mNativeAudioRecordListener;
        if (nativeAudioRecordListener != null) {
            nativeAudioRecordListener.startRecording(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2), getCurrentActivity());
        }
    }

    @ReactMethod
    public void stopPlaying(Callback callback, Callback callback2) {
        NativeMultiMediaModuleListener nativeMultiMediaModuleListener = this.mNativeMultiMediaModuleListener;
        if (nativeMultiMediaModuleListener != null) {
            nativeMultiMediaModuleListener.stopPlaying(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void stopRecording(Callback callback, Callback callback2) {
        NativeAudioRecordListener nativeAudioRecordListener = this.mNativeAudioRecordListener;
        if (nativeAudioRecordListener != null) {
            nativeAudioRecordListener.stopRecording(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void upLoadingFile(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeFileUploadListener nativeFileUploadListener = this.mNativeFileUploadListener;
        if (nativeFileUploadListener != null) {
            nativeFileUploadListener.upLoadingFile(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
