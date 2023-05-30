package com.jingdong.common.jdreactFramework.views;

import android.animation.Animator;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDLottieViewManager extends SimpleViewManager<LottieAnimationView> {
    private static final int COMMAND_PLAY = 1;
    private static final int COMMAND_RELEASE = 3;
    private static final int COMMAND_RESET = 2;
    private static final String REACT_CLASS = "LottieAnimationView";
    private static final String TAG = "JDLottieViewManager";
    private static final int VERSION = 1;
    private Handler mHandler;

    private void releaseLottieView(LottieAnimationView lottieAnimationView) {
        if (lottieAnimationView == null) {
            return;
        }
        try {
            lottieAnimationView.clearColorFilter();
            lottieAnimationView.cancelAnimation();
        } catch (Exception e2) {
            if (JDReactHelper.newInstance().isDebug()) {
                e2.toString();
            }
        }
    }

    private void runOnMainThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
        this.mHandler.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOnAnimationFinishEvent(LottieAnimationView lottieAnimationView, boolean z) {
        ReactContext reactContext;
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("isCancelled", z);
        Context context = lottieAnimationView.getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                reactContext = null;
                break;
            } else if (context instanceof ReactContext) {
                reactContext = (ReactContext) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        if (reactContext != null) {
            ((RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(lottieAnimationView.getId(), "animationFinish", createMap);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("play", 1, "reset", 2, "release", 3);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("animationFinish", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onAnimationFinish"))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", 1).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "enableMergePathsAndroidForKitKatAndAbove")
    public void setEnableMergePaths(LottieAnimationView lottieAnimationView, boolean z) {
        lottieAnimationView.enableMergePathsForKitKatAndAbove(z);
    }

    @ReactProp(name = "hardwareAccelerationAndroid")
    public void setHardwareAcceleration(LottieAnimationView lottieAnimationView, boolean z) {
    }

    @ReactProp(name = "imageAssetsFolder")
    public void setImageAssetsFolder(LottieAnimationView lottieAnimationView, String str) {
        lottieAnimationView.setImageAssetsFolder(str);
    }

    @ReactProp(name = JDPureVideoManager.SourceKey.LOOP)
    public void setLoop(LottieAnimationView lottieAnimationView, boolean z) {
        lottieAnimationView.loop(z);
    }

    @ReactProp(name = "loopCount")
    public void setLoopCount(LottieAnimationView lottieAnimationView, int i2) {
        lottieAnimationView.setRepeatCount(i2);
    }

    @ReactProp(name = NotificationCompat.CATEGORY_PROGRESS)
    public void setProgress(LottieAnimationView lottieAnimationView, float f2) {
        lottieAnimationView.setProgress(f2);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(LottieAnimationView lottieAnimationView, String str) {
        if ("cover".equals(str)) {
            lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if ("contain".equals(str)) {
            lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else if (DYConstants.DY_CENTER.equals(str)) {
            lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER);
        }
    }

    @ReactProp(name = "sourceJson")
    public void setSourceJson(LottieAnimationView lottieAnimationView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            lottieAnimationView.setAnimationFromJson(str);
        } catch (Exception e2) {
            if (JDReactHelper.newInstance().isDebug()) {
                e2.toString();
            }
        }
    }

    @ReactProp(name = "sourceJsonWithCache")
    public void setSourceJsonWithCache(LottieAnimationView lottieAnimationView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            lottieAnimationView.setAnimationFromJson(str, String.valueOf(str.hashCode()));
        } catch (Exception e2) {
            if (JDReactHelper.newInstance().isDebug()) {
                e2.toString();
            }
        }
    }

    @ReactProp(name = "sourceName")
    public void setSourceName(LottieAnimationView lottieAnimationView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        lottieAnimationView.setAnimation(str);
    }

    @ReactProp(name = "sourceUrl")
    public void setSourceUrl(LottieAnimationView lottieAnimationView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        lottieAnimationView.setAnimationFromUrl(str);
    }

    @ReactProp(name = "speed")
    public void setSpeed(LottieAnimationView lottieAnimationView, float f2) {
        lottieAnimationView.setSpeed(f2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LottieAnimationView createViewInstance(ThemedReactContext themedReactContext) {
        final LottieAnimationView lottieAnimationView = new LottieAnimationView(themedReactContext);
        lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.jdreactFramework.views.JDLottieViewManager.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                JDLottieViewManager.this.sendOnAnimationFinishEvent(lottieAnimationView, true);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                JDLottieViewManager.this.sendOnAnimationFinishEvent(lottieAnimationView, false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        return lottieAnimationView;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(LottieAnimationView lottieAnimationView) {
        super.onDropViewInstance((JDLottieViewManager) lottieAnimationView);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        releaseLottieView(lottieAnimationView);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(final LottieAnimationView lottieAnimationView, int i2, ReadableArray readableArray) {
        if (i2 == 1) {
            runOnMainThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.JDLottieViewManager.2
                @Override // java.lang.Runnable
                public void run() {
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.setProgress(0.0f);
                        lottieAnimationView.playAnimation();
                    }
                }
            });
        } else if (i2 == 2) {
            runOnMainThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.JDLottieViewManager.3
                @Override // java.lang.Runnable
                public void run() {
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.cancelAnimation();
                        lottieAnimationView.setProgress(0.0f);
                    }
                }
            });
        } else if (i2 != 3) {
        } else {
            releaseLottieView(lottieAnimationView);
        }
    }
}
