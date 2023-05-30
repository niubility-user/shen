package com.facebook.react.modules.debug;

import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.debug.FpsDebugFrameCallback;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.Locale;
import javax.annotation.Nullable;

@ReactModule(name = AnimationsDebugModule.NAME)
/* loaded from: classes12.dex */
public class AnimationsDebugModule extends ReactContextBaseJavaModule {
    protected static final String NAME = "AnimationsDebugModule";
    @Nullable
    private final DeveloperSettings mCatalystSettings;
    @Nullable
    private FpsDebugFrameCallback mFrameCallback;

    public AnimationsDebugModule(ReactApplicationContext reactApplicationContext, DeveloperSettings developerSettings) {
        super(reactApplicationContext);
        this.mCatalystSettings = developerSettings;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        FpsDebugFrameCallback fpsDebugFrameCallback = this.mFrameCallback;
        if (fpsDebugFrameCallback != null) {
            fpsDebugFrameCallback.stop();
            this.mFrameCallback = null;
        }
    }

    @ReactMethod
    public void startRecordingFps() {
        DeveloperSettings developerSettings = this.mCatalystSettings;
        if (developerSettings == null || !developerSettings.isAnimationFpsDebugEnabled()) {
            return;
        }
        if (this.mFrameCallback == null) {
            FpsDebugFrameCallback fpsDebugFrameCallback = new FpsDebugFrameCallback(getReactApplicationContext());
            this.mFrameCallback = fpsDebugFrameCallback;
            fpsDebugFrameCallback.startAndRecordFpsAtEachFrame();
            return;
        }
        throw new JSApplicationCausedNativeException("Already recording FPS!");
    }

    @ReactMethod
    public void stopRecordingFps(double d) {
        FpsDebugFrameCallback fpsDebugFrameCallback = this.mFrameCallback;
        if (fpsDebugFrameCallback == null) {
            return;
        }
        fpsDebugFrameCallback.stop();
        FpsDebugFrameCallback.FpsInfo fpsInfo = this.mFrameCallback.getFpsInfo((long) d);
        if (fpsInfo == null) {
            Toast.makeText(getReactApplicationContext(), "Unable to get FPS info", 1);
        } else {
            Locale locale = Locale.US;
            String str = String.format(locale, "FPS: %.2f, %d frames (%d expected)", Double.valueOf(fpsInfo.fps), Integer.valueOf(fpsInfo.totalFrames), Integer.valueOf(fpsInfo.totalExpectedFrames)) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + String.format(locale, "JS FPS: %.2f, %d frames (%d expected)", Double.valueOf(fpsInfo.jsFps), Integer.valueOf(fpsInfo.totalJsFrames), Integer.valueOf(fpsInfo.totalExpectedFrames)) + "\nTotal Time MS: " + String.format(locale, "%d", Integer.valueOf(fpsInfo.totalTimeMs));
            FLog.d(ReactConstants.TAG, str);
            Toast.makeText(getReactApplicationContext(), str, 1).show();
        }
        this.mFrameCallback = null;
    }
}
