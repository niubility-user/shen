package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import javax.annotation.Nullable;

@ReactModule(name = "JSCHeapCapture", needsEagerInit = true)
/* loaded from: classes12.dex */
public class JSCHeapCapture extends ReactContextBaseJavaModule {
    @Nullable
    private CaptureCallback mCaptureInProgress;

    /* loaded from: classes12.dex */
    public interface CaptureCallback {
        void onFailure(CaptureException captureException);

        void onSuccess(File file);
    }

    /* loaded from: classes12.dex */
    public static class CaptureException extends Exception {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CaptureException(String str) {
            super(str);
        }

        CaptureException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes12.dex */
    public interface HeapCapture extends JavaScriptModule {
        void captureHeap(String str);
    }

    public JSCHeapCapture(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCaptureInProgress = null;
    }

    @ReactMethod
    public synchronized void captureComplete(String str, String str2) {
        CaptureCallback captureCallback = this.mCaptureInProgress;
        if (captureCallback != null) {
            if (str2 == null) {
                captureCallback.onSuccess(new File(str));
            } else {
                captureCallback.onFailure(new CaptureException(str2));
            }
            this.mCaptureInProgress = null;
        }
    }

    public synchronized void captureHeap(String str, CaptureCallback captureCallback) {
        if (this.mCaptureInProgress != null) {
            captureCallback.onFailure(new CaptureException("Heap capture already in progress."));
            return;
        }
        File file = new File(str + "/capture.json");
        file.delete();
        HeapCapture heapCapture = (HeapCapture) getReactApplicationContext().getJSModule(HeapCapture.class);
        if (heapCapture == null) {
            captureCallback.onFailure(new CaptureException("Heap capture js module not registered."));
            return;
        }
        this.mCaptureInProgress = captureCallback;
        heapCapture.captureHeap(file.getPath());
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JSCHeapCapture";
    }
}
