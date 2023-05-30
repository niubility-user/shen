package com.jingdong.sdk.aac.util;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class SyncEventBus {
    public static final String EXTRA_KEY = "key";
    public static final String EXTRA_KEY_1 = "key1";
    public static final String EXTRA_KEY_2 = "key2";
    private static final String TAG = "SyncEventBus";
    private static SyncEventBus mEmptyEventBus;
    private static volatile Handler mMainHandler;
    private List<EventBusListener> eventListenerList;
    private boolean isDestroy = false;
    private String mkey;
    private static Map<String, SyncEventBus> eventBusMap = new HashMap(3);
    private static long managerKey = 0;
    private static Object mLock = new Object();
    private static ArrayList<String> mDestroyKeys = new ArrayList<>(3);

    /* loaded from: classes7.dex */
    public interface EventBusListener {
        String getActionName();

        Object getData(String str);

        boolean isValid();

        void onEvent(String str, Object obj);
    }

    private SyncEventBus(String str) {
        this.mkey = str;
    }

    private synchronized void clearList() {
        List<EventBusListener> list = this.eventListenerList;
        if (list != null) {
            list.clear();
        }
        this.eventListenerList = null;
    }

    public static synchronized String createKey() {
        String valueOf;
        synchronized (SyncEventBus.class) {
            long currentTimeMillis = managerKey + System.currentTimeMillis();
            managerKey = currentTimeMillis;
            valueOf = String.valueOf(currentTimeMillis);
        }
        return valueOf;
    }

    public static void destroy(String str) {
        ArrayList<String> arrayList;
        Map<String, SyncEventBus> map = eventBusMap;
        if (map != null) {
            SyncEventBus remove = map.remove(str);
            if (remove != null) {
                remove.onDestroy();
            }
            if (TextUtils.isEmpty(str) || (arrayList = mDestroyKeys) == null) {
                return;
            }
            arrayList.add(str);
        }
    }

    private static synchronized SyncEventBus getEmptyEventBus() {
        SyncEventBus syncEventBus;
        synchronized (SyncEventBus.class) {
            if (mEmptyEventBus == null) {
                mEmptyEventBus = new SyncEventBus("");
            }
            syncEventBus = mEmptyEventBus;
        }
        return syncEventBus;
    }

    public static SyncEventBus getExistEventBusOrNull(String str) {
        if (eventBusMap.isEmpty()) {
            return null;
        }
        return eventBusMap.get(str);
    }

    public static synchronized SyncEventBus getInstances(String str) {
        ArrayList<String> arrayList;
        synchronized (SyncEventBus.class) {
            if (!TextUtils.isEmpty(str) && ((arrayList = mDestroyKeys) == null || !arrayList.contains(str))) {
                SyncEventBus syncEventBus = eventBusMap.isEmpty() ? null : eventBusMap.get(str);
                if (syncEventBus == null) {
                    syncEventBus = new SyncEventBus(str);
                    eventBusMap.put(str, syncEventBus);
                }
                return syncEventBus;
            }
            return getEmptyEventBus();
        }
    }

    private boolean isEmpty() {
        return TextUtils.isEmpty(getKey());
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    private void onDestroy() {
        this.isDestroy = true;
        clearList();
        mMainHandler = null;
    }

    public static void postToMainThread(Runnable runnable) {
        if (mMainHandler == null) {
            synchronized (mLock) {
                if (mMainHandler == null) {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        mMainHandler.post(runnable);
    }

    public synchronized Object getData(String str, String str2) {
        if (!this.isDestroy && !isEmpty()) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            List<EventBusListener> list = this.eventListenerList;
            if (list != null && !list.isEmpty()) {
                for (EventBusListener eventBusListener : this.eventListenerList) {
                    if (eventBusListener != null && str.equals(eventBusListener.getActionName()) && eventBusListener.isValid()) {
                        return eventBusListener.getData(str2);
                    }
                }
            }
            return null;
        }
        return null;
    }

    public String getKey() {
        return this.mkey;
    }

    public void post(String str, String str2) {
        post(str, str2, null);
    }

    public synchronized void register(EventBusListener eventBusListener) {
        if (eventBusListener != null) {
            if (!isEmpty()) {
                if (this.eventListenerList == null) {
                    this.eventListenerList = new CopyOnWriteArrayList();
                }
                this.eventListenerList.add(eventBusListener);
            }
        }
    }

    public synchronized void post(String str, String str2, Object obj) {
        if (!this.isDestroy && !isEmpty()) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            List<EventBusListener> list = this.eventListenerList;
            if (list != null && !list.isEmpty()) {
                for (EventBusListener eventBusListener : this.eventListenerList) {
                    if (eventBusListener != null && str.equals(eventBusListener.getActionName()) && eventBusListener.isValid()) {
                        eventBusListener.onEvent(str2, obj);
                    }
                }
            }
        }
    }

    public static void postToMainThread(Runnable runnable, long j2) {
        if (mMainHandler == null) {
            synchronized (mLock) {
                if (mMainHandler == null) {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        mMainHandler.postDelayed(runnable, j2);
    }

    public void postToMainThread(String str, String str2) {
        postToMainThread(str, str2, null);
    }

    public synchronized void postToMainThread(String str, final String str2, final Object obj) {
        if (!this.isDestroy && !isEmpty()) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            List<EventBusListener> list = this.eventListenerList;
            if (list != null && !list.isEmpty()) {
                for (final EventBusListener eventBusListener : this.eventListenerList) {
                    if (eventBusListener != null && str.equals(eventBusListener.getActionName()) && eventBusListener.isValid()) {
                        postToMainThread(new Runnable() { // from class: com.jingdong.sdk.aac.util.SyncEventBus.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (SyncEventBus.this.isDestroy) {
                                    return;
                                }
                                eventBusListener.onEvent(str2, obj);
                            }
                        });
                    }
                }
            }
        }
    }
}
