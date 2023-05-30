package com.jd.dynamic.base.timer;

import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.t;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class TimerManager {

    /* renamed from: c  reason: collision with root package name */
    private ScheduledExecutorService f2056c;
    private Map<String, DynTimerTask> a = new HashMap(16);
    private Map<String, ArrayList<TimerResultReceiver>> b = new HashMap(16);
    private final Object d = new Object();

    private void a() {
        ScheduledExecutorService scheduledExecutorService = this.f2056c;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.f2056c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Map.Entry entry) {
        unregisterTimerReceiver((String) entry.getKey());
    }

    private void c(boolean z) {
        Map<String, DynTimerTask> map = this.a;
        if (map != null) {
            Observable.from(map.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.base.timer.b
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    TimerManager.e((Map.Entry) obj);
                }
            });
            synchronized (this.d) {
                this.a.clear();
            }
        }
        if (z) {
            Observable.from(this.b.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.base.timer.a
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    TimerManager.this.b((Map.Entry) obj);
                }
            });
        }
    }

    private static boolean d(TimerRequest timerRequest) {
        return (timerRequest == null || TextUtils.isEmpty(timerRequest.getTimerId())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(Map.Entry entry) {
        ((DynTimerTask) entry.getValue()).cancel(true);
    }

    public void cancelAllTimerTask() {
        c(true);
        if (m.J(this.a)) {
            this.a.clear();
        }
        if (m.J(this.b)) {
            this.b.clear();
        }
    }

    public void cancelTimerRequest(@NonNull String str) {
        if (this.a == null || TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.d) {
            DynTimerTask remove = this.a.remove(str);
            if (remove != null) {
                remove.cancel(true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void insertTimerRequest(@NonNull TimerRequest timerRequest) {
        if (!d(timerRequest)) {
            t.e("TimerManager", "TimerRequest \u53c2\u6570\u975e\u6cd5\uff0c\u8bf7\u68c0\u67e5");
            return;
        }
        String timerId = timerRequest.getTimerId();
        long interval = timerRequest.getInterval();
        if (this.a.containsKey(timerId)) {
            return;
        }
        DynTimerTask dynTimerTask = new DynTimerTask(timerRequest, this);
        if (this.f2056c == null) {
            this.f2056c = new ScheduledThreadPoolExecutor(1);
        }
        dynTimerTask.setScheduledFuture(this.f2056c.scheduleAtFixedRate(dynTimerTask, 0L, interval, TimeUnit.MILLISECONDS));
        synchronized (this.d) {
            this.a.put(timerId, dynTimerTask);
        }
    }

    public boolean isTimerReceiverRegisted(String str) {
        return this.b.containsKey(str);
    }

    public void onDestroy() {
        c(true);
        if (m.J(this.a)) {
            this.a.clear();
        }
        if (m.J(this.b)) {
            this.b.clear();
        }
        a();
    }

    public void onTimerFinish(String str) {
        if (this.a == null || TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.d) {
            this.a.remove(str);
        }
    }

    public void registerTimerReceiver(@NonNull TimerResultReceiver timerResultReceiver) {
        String timerId = timerResultReceiver.getTimerId();
        synchronized (this.d) {
            ArrayList<TimerResultReceiver> arrayList = this.b.get(timerId);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.b.put(timerId, arrayList);
            }
            arrayList.add(timerResultReceiver);
            t.e("TimerReceiver", "registerTimerReceiver");
            LocalBroadcastManager.getInstance(DynamicSdk.getEngine().getContext()).registerReceiver(timerResultReceiver, new IntentFilter(timerId));
        }
    }

    public void stopAllTimer() {
        c(false);
    }

    public void unregisterTimerReceiver(@NonNull String str) {
        synchronized (this.d) {
            ArrayList<TimerResultReceiver> arrayList = this.b.get(str);
            if (m.I(arrayList)) {
                Iterator<TimerResultReceiver> it = arrayList.iterator();
                while (it.hasNext()) {
                    t.e("TimerReceiver", "unregisterTimerReceiver");
                    LocalBroadcastManager.getInstance(DynamicSdk.getEngine().getContext()).unregisterReceiver(it.next());
                }
            }
        }
    }
}
