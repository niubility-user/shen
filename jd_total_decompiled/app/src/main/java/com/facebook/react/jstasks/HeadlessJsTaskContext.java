package com.facebook.react.jstasks;

import android.os.Handler;
import android.util.SparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes12.dex */
public class HeadlessJsTaskContext {
    private static final WeakHashMap<ReactContext, HeadlessJsTaskContext> INSTANCES = new WeakHashMap<>();
    private final WeakReference<ReactContext> mReactContext;
    private final Set<HeadlessJsTaskEventListener> mHeadlessJsTaskEventListeners = new CopyOnWriteArraySet();
    private final AtomicInteger mLastTaskId = new AtomicInteger(0);
    private final Handler mHandler = new Handler();
    private final Set<Integer> mActiveTasks = new CopyOnWriteArraySet();
    private final SparseArray<Runnable> mTaskTimeouts = new SparseArray<>();

    private HeadlessJsTaskContext(ReactContext reactContext) {
        this.mReactContext = new WeakReference<>(reactContext);
    }

    public static HeadlessJsTaskContext getInstance(ReactContext reactContext) {
        WeakHashMap<ReactContext, HeadlessJsTaskContext> weakHashMap = INSTANCES;
        HeadlessJsTaskContext headlessJsTaskContext = weakHashMap.get(reactContext);
        if (headlessJsTaskContext == null) {
            HeadlessJsTaskContext headlessJsTaskContext2 = new HeadlessJsTaskContext(reactContext);
            weakHashMap.put(reactContext, headlessJsTaskContext2);
            return headlessJsTaskContext2;
        }
        return headlessJsTaskContext;
    }

    private void scheduleTaskTimeout(final int i2, long j2) {
        Runnable runnable = new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.2
            @Override // java.lang.Runnable
            public void run() {
                HeadlessJsTaskContext.this.finishTask(i2);
            }
        };
        this.mTaskTimeouts.append(i2, runnable);
        this.mHandler.postDelayed(runnable, j2);
    }

    public void addTaskEventListener(HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        this.mHeadlessJsTaskEventListeners.add(headlessJsTaskEventListener);
    }

    public synchronized void finishTask(final int i2) {
        Assertions.assertCondition(this.mActiveTasks.remove(Integer.valueOf(i2)), "Tried to finish non-existent task with id " + i2 + OrderISVUtil.MONEY_DECIMAL);
        Runnable runnable = this.mTaskTimeouts.get(i2);
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            this.mTaskTimeouts.remove(i2);
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = HeadlessJsTaskContext.this.mHeadlessJsTaskEventListeners.iterator();
                while (it.hasNext()) {
                    ((HeadlessJsTaskEventListener) it.next()).onHeadlessJsTaskFinish(i2);
                }
            }
        });
    }

    public boolean hasActiveTasks() {
        return this.mActiveTasks.size() > 0;
    }

    public synchronized boolean isTaskRunning(int i2) {
        return this.mActiveTasks.contains(Integer.valueOf(i2));
    }

    public void removeTaskEventListener(HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        this.mHeadlessJsTaskEventListeners.remove(headlessJsTaskEventListener);
    }

    public synchronized int startTask(HeadlessJsTaskConfig headlessJsTaskConfig) {
        int incrementAndGet;
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = (ReactContext) Assertions.assertNotNull(this.mReactContext.get(), "Tried to start a task on a react context that has already been destroyed");
        if (reactContext.getLifecycleState() == LifecycleState.RESUMED && !headlessJsTaskConfig.isAllowedInForeground()) {
            throw new IllegalStateException("Tried to start task " + headlessJsTaskConfig.getTaskKey() + " while in foreground, but this is not allowed.");
        }
        incrementAndGet = this.mLastTaskId.incrementAndGet();
        this.mActiveTasks.add(Integer.valueOf(incrementAndGet));
        ((AppRegistry) reactContext.getJSModule(AppRegistry.class)).startHeadlessTask(incrementAndGet, headlessJsTaskConfig.getTaskKey(), headlessJsTaskConfig.getData());
        if (headlessJsTaskConfig.getTimeout() > 0) {
            scheduleTaskTimeout(incrementAndGet, headlessJsTaskConfig.getTimeout());
        }
        Iterator<HeadlessJsTaskEventListener> it = this.mHeadlessJsTaskEventListeners.iterator();
        while (it.hasNext()) {
            it.next().onHeadlessJsTaskStart(incrementAndGet);
        }
        return incrementAndGet;
    }
}
