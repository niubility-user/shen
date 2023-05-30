package com.facebook.react.modules.debug;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class FpsDebugFrameCallback extends ChoreographerCompat.FrameCallback {
    private static final double EXPECTED_FRAME_TIME = 16.9d;
    @Nullable
    private ChoreographerCompat mChoreographer;
    private final ReactContext mReactContext;
    @Nullable
    private TreeMap<Long, FpsInfo> mTimeToFps;
    private final UIManagerModule mUIManagerModule;
    private boolean mShouldStop = false;
    private long mFirstFrameTime = -1;
    private long mLastFrameTime = -1;
    private int mNumFrameCallbacks = 0;
    private int mExpectedNumFramesPrev = 0;
    private int m4PlusFrameStutters = 0;
    private int mNumFrameCallbacksWithBatchDispatches = 0;
    private boolean mIsRecordingFpsInfoAtEachFrame = false;
    private final DidJSUpdateUiDuringFrameDetector mDidJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();

    /* loaded from: classes12.dex */
    public static class FpsInfo {
        public final double fps;
        public final double jsFps;
        public final int total4PlusFrameStutters;
        public final int totalExpectedFrames;
        public final int totalFrames;
        public final int totalJsFrames;
        public final int totalTimeMs;

        public FpsInfo(int i2, int i3, int i4, int i5, double d, double d2, int i6) {
            this.totalFrames = i2;
            this.totalJsFrames = i3;
            this.totalExpectedFrames = i4;
            this.total4PlusFrameStutters = i5;
            this.fps = d;
            this.jsFps = d2;
            this.totalTimeMs = i6;
        }
    }

    public FpsDebugFrameCallback(ReactContext reactContext) {
        this.mReactContext = reactContext;
        this.mUIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
    }

    @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
    public void doFrame(long j2) {
        if (this.mShouldStop) {
            return;
        }
        if (this.mFirstFrameTime == -1) {
            this.mFirstFrameTime = j2;
        }
        long j3 = this.mLastFrameTime;
        this.mLastFrameTime = j2;
        if (this.mDidJSUpdateUiDuringFrameDetector.getDidJSHitFrameAndCleanup(j3, j2)) {
            this.mNumFrameCallbacksWithBatchDispatches++;
        }
        this.mNumFrameCallbacks++;
        int expectedNumFrames = getExpectedNumFrames();
        if ((expectedNumFrames - this.mExpectedNumFramesPrev) - 1 >= 4) {
            this.m4PlusFrameStutters++;
        }
        if (this.mIsRecordingFpsInfoAtEachFrame) {
            Assertions.assertNotNull(this.mTimeToFps);
            this.mTimeToFps.put(Long.valueOf(System.currentTimeMillis()), new FpsInfo(getNumFrames(), getNumJSFrames(), expectedNumFrames, this.m4PlusFrameStutters, getFPS(), getJSFPS(), getTotalTimeMS()));
        }
        this.mExpectedNumFramesPrev = expectedNumFrames;
        ChoreographerCompat choreographerCompat = this.mChoreographer;
        if (choreographerCompat != null) {
            choreographerCompat.postFrameCallback(this);
        }
    }

    public int get4PlusFrameStutters() {
        return this.m4PlusFrameStutters;
    }

    public int getExpectedNumFrames() {
        double totalTimeMS = getTotalTimeMS();
        Double.isNaN(totalTimeMS);
        return (int) ((totalTimeMS / EXPECTED_FRAME_TIME) + 1.0d);
    }

    public double getFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        double numFrames = getNumFrames();
        Double.isNaN(numFrames);
        double d = this.mLastFrameTime - this.mFirstFrameTime;
        Double.isNaN(d);
        return (numFrames * 1.0E9d) / d;
    }

    @Nullable
    public FpsInfo getFpsInfo(long j2) {
        Assertions.assertNotNull(this.mTimeToFps, "FPS was not recorded at each frame!");
        Map.Entry<Long, FpsInfo> floorEntry = this.mTimeToFps.floorEntry(Long.valueOf(j2));
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getValue();
    }

    public double getJSFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        double numJSFrames = getNumJSFrames();
        Double.isNaN(numJSFrames);
        double d = this.mLastFrameTime - this.mFirstFrameTime;
        Double.isNaN(d);
        return (numJSFrames * 1.0E9d) / d;
    }

    public int getNumFrames() {
        return this.mNumFrameCallbacks - 1;
    }

    public int getNumJSFrames() {
        return this.mNumFrameCallbacksWithBatchDispatches - 1;
    }

    public int getTotalTimeMS() {
        double d = this.mLastFrameTime;
        double d2 = this.mFirstFrameTime;
        Double.isNaN(d);
        Double.isNaN(d2);
        return ((int) (d - d2)) / 1000000;
    }

    public void reset() {
        this.mFirstFrameTime = -1L;
        this.mLastFrameTime = -1L;
        this.mNumFrameCallbacks = 0;
        this.m4PlusFrameStutters = 0;
        this.mNumFrameCallbacksWithBatchDispatches = 0;
        this.mIsRecordingFpsInfoAtEachFrame = false;
        this.mTimeToFps = null;
    }

    public void start() {
        this.mShouldStop = false;
        this.mReactContext.getCatalystInstance().addBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback.1
            {
                FpsDebugFrameCallback.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                FpsDebugFrameCallback.this.mChoreographer = ChoreographerCompat.getInstance();
                FpsDebugFrameCallback.this.mChoreographer.postFrameCallback(this);
            }
        });
    }

    public void startAndRecordFpsAtEachFrame() {
        this.mTimeToFps = new TreeMap<>();
        this.mIsRecordingFpsInfoAtEachFrame = true;
        start();
    }

    public void stop() {
        this.mShouldStop = true;
        this.mReactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(null);
    }
}
