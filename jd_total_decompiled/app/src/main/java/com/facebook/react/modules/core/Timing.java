package com.facebook.react.modules.core;

import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.SystemClock;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@ReactModule(name = Timing.NAME)
/* loaded from: classes12.dex */
public final class Timing extends ReactContextBaseJavaModule implements LifecycleEventListener, HeadlessJsTaskEventListener {
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    public static final String NAME = "Timing";
    private final AtomicBoolean isPaused;
    private final AtomicBoolean isRunningTasks;
    @Nullable
    private IdleCallbackRunnable mCurrentIdleCallbackRunnable;
    private final DevSupportManager mDevSupportManager;
    private boolean mFrameCallbackPosted;
    private boolean mFrameIdleCallbackPosted;
    private final Object mIdleCallbackGuard;
    private final IdleFrameCallback mIdleFrameCallback;
    private final ReactChoreographer mReactChoreographer;
    private boolean mSendIdleEvents;
    private final TimerFrameCallback mTimerFrameCallback;
    private final Object mTimerGuard;
    private final SparseArray<Timer> mTimerIdsToTimers;
    private final PriorityQueue<Timer> mTimers;

    /* loaded from: classes12.dex */
    private class IdleCallbackRunnable implements Runnable {
        private volatile boolean mCancelled = false;
        private final long mFrameStartTime;

        public IdleCallbackRunnable(long j2) {
            this.mFrameStartTime = j2;
        }

        public void cancel() {
            this.mCancelled = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            if (this.mCancelled) {
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis() - (this.mFrameStartTime / 1000000);
            long currentTimeMillis = SystemClock.currentTimeMillis() - uptimeMillis;
            if (Timing.FRAME_DURATION_MS - ((float) uptimeMillis) < 1.0f) {
                return;
            }
            synchronized (Timing.this.mIdleCallbackGuard) {
                z = Timing.this.mSendIdleEvents;
            }
            if (z) {
                ((JSTimers) Timing.this.getReactApplicationContext().getJSModule(JSTimers.class)).callIdleCallbacks(currentTimeMillis);
            }
            Timing.this.mCurrentIdleCallbackRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class IdleFrameCallback extends ChoreographerCompat.FrameCallback {
        private IdleFrameCallback() {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j2) {
            if (!Timing.this.isPaused.get() || Timing.this.isRunningTasks.get()) {
                if (Timing.this.mCurrentIdleCallbackRunnable != null) {
                    Timing.this.mCurrentIdleCallbackRunnable.cancel();
                }
                Timing timing = Timing.this;
                timing.mCurrentIdleCallbackRunnable = new IdleCallbackRunnable(j2);
                Timing.this.getReactApplicationContext().runOnJSQueueThread(Timing.this.mCurrentIdleCallbackRunnable);
                Timing.this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class Timer {
        private final int mCallbackID;
        private final int mInterval;
        private final boolean mRepeat;
        private long mTargetTime;

        private Timer(int i2, long j2, int i3, boolean z) {
            this.mCallbackID = i2;
            this.mTargetTime = j2;
            this.mInterval = i3;
            this.mRepeat = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class TimerFrameCallback extends ChoreographerCompat.FrameCallback {
        @Nullable
        private WritableArray mTimersToCall;

        private TimerFrameCallback() {
            this.mTimersToCall = null;
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j2) {
            if (!Timing.this.isPaused.get() || Timing.this.isRunningTasks.get()) {
                long j3 = j2 / 1000000;
                synchronized (Timing.this.mTimerGuard) {
                    while (!Timing.this.mTimers.isEmpty() && ((Timer) Timing.this.mTimers.peek()).mTargetTime < j3) {
                        Timer timer = (Timer) Timing.this.mTimers.poll();
                        if (this.mTimersToCall == null) {
                            this.mTimersToCall = Arguments.createArray();
                        }
                        this.mTimersToCall.pushInt(timer.mCallbackID);
                        if (timer.mRepeat) {
                            timer.mTargetTime = timer.mInterval + j3;
                            Timing.this.mTimers.add(timer);
                        } else {
                            Timing.this.mTimerIdsToTimers.remove(timer.mCallbackID);
                        }
                    }
                }
                if (this.mTimersToCall != null) {
                    ((JSTimers) Timing.this.getReactApplicationContext().getJSModule(JSTimers.class)).callTimers(this.mTimersToCall);
                    this.mTimersToCall = null;
                }
                Timing.this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this);
            }
        }
    }

    public Timing(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mTimerGuard = new Object();
        this.mIdleCallbackGuard = new Object();
        this.isPaused = new AtomicBoolean(true);
        this.isRunningTasks = new AtomicBoolean(false);
        this.mTimerFrameCallback = new TimerFrameCallback();
        this.mIdleFrameCallback = new IdleFrameCallback();
        this.mFrameCallbackPosted = false;
        this.mFrameIdleCallbackPosted = false;
        this.mSendIdleEvents = false;
        this.mDevSupportManager = devSupportManager;
        this.mTimers = new PriorityQueue<>(11, new Comparator<Timer>() { // from class: com.facebook.react.modules.core.Timing.1
            @Override // java.util.Comparator
            public int compare(Timer timer, Timer timer2) {
                long j2 = timer.mTargetTime - timer2.mTargetTime;
                if (j2 == 0) {
                    return 0;
                }
                return j2 < 0 ? -1 : 1;
            }
        });
        this.mTimerIdsToTimers = new SparseArray<>();
        this.mReactChoreographer = ReactChoreographer.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            this.mReactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = false;
        }
    }

    private void clearFrameCallback() {
        HeadlessJsTaskContext headlessJsTaskContext = HeadlessJsTaskContext.getInstance(getReactApplicationContext());
        if (this.mFrameCallbackPosted && this.isPaused.get() && !headlessJsTaskContext.hasActiveTasks()) {
            this.mReactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = false;
        }
    }

    private void maybeIdleCallback() {
        if (!this.isPaused.get() || this.isRunningTasks.get()) {
            return;
        }
        clearFrameCallback();
    }

    private void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEvents) {
                setChoreographerIdleCallback();
            }
        }
    }

    private void setChoreographerCallback() {
        if (this.mFrameCallbackPosted) {
            return;
        }
        this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
        this.mFrameCallbackPosted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            return;
        }
        this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
        this.mFrameIdleCallbackPosted = true;
    }

    @ReactMethod
    public void createTimer(int i2, int i3, double d, boolean z) {
        long currentTimeMillis = SystemClock.currentTimeMillis();
        long j2 = (long) d;
        if (this.mDevSupportManager.getDevSupportEnabled() && Math.abs(j2 - currentTimeMillis) > 60000) {
            ((JSTimers) getReactApplicationContext().getJSModule(JSTimers.class)).emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        long max = Math.max(0L, (j2 - currentTimeMillis) + i3);
        if (i3 == 0 && !z) {
            WritableArray createArray = Arguments.createArray();
            createArray.pushInt(i2);
            ((JSTimers) getReactApplicationContext().getJSModule(JSTimers.class)).callTimers(createArray);
            return;
        }
        Timer timer = new Timer(i2, (SystemClock.nanoTime() / 1000000) + max, i3, z);
        synchronized (this.mTimerGuard) {
            this.mTimers.add(timer);
            this.mTimerIdsToTimers.put(i2, timer);
        }
    }

    @ReactMethod
    public void deleteTimer(int i2) {
        synchronized (this.mTimerGuard) {
            Timer timer = this.mTimerIdsToTimers.get(i2);
            if (timer == null) {
                return;
            }
            this.mTimerIdsToTimers.remove(i2);
            this.mTimers.remove(timer);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).addTaskEventListener(this);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        clearFrameCallback();
        clearChoreographerIdleCallback();
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).removeTaskEventListener(this);
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskEventListener
    public void onHeadlessJsTaskFinish(int i2) {
        if (HeadlessJsTaskContext.getInstance(getReactApplicationContext()).hasActiveTasks()) {
            return;
        }
        this.isRunningTasks.set(false);
        clearFrameCallback();
        maybeIdleCallback();
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskEventListener
    public void onHeadlessJsTaskStart(int i2) {
        if (this.isRunningTasks.getAndSet(true)) {
            return;
        }
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        clearFrameCallback();
        maybeIdleCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.isPaused.set(true);
        clearFrameCallback();
        maybeIdleCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.isPaused.set(false);
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    @ReactMethod
    public void setSendIdleEvents(final boolean z) {
        synchronized (this.mIdleCallbackGuard) {
            this.mSendIdleEvents = z;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.core.Timing.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (Timing.this.mIdleCallbackGuard) {
                    if (z) {
                        Timing.this.setChoreographerIdleCallback();
                    } else {
                        Timing.this.clearChoreographerIdleCallback();
                    }
                }
            }
        });
    }
}
