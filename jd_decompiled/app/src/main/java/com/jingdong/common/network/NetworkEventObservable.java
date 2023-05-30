package com.jingdong.common.network;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes5.dex */
public class NetworkEventObservable {
    private static NetworkEventObservable instance;
    private final Object LOCK = new Object();
    private Set<Observer> mObservers;

    /* loaded from: classes5.dex */
    public interface Observer {
        void onNetworkChanged();
    }

    private NetworkEventObservable() {
    }

    public static synchronized NetworkEventObservable getInstance() {
        NetworkEventObservable networkEventObservable;
        synchronized (NetworkEventObservable.class) {
            if (instance == null) {
                instance = new NetworkEventObservable();
            }
            networkEventObservable = instance;
        }
        return networkEventObservable;
    }

    public void notifyObservers() {
        synchronized (this.LOCK) {
            if (this.mObservers == null) {
                return;
            }
            Iterator it = new HashSet(this.mObservers).iterator();
            while (it.hasNext()) {
                try {
                    ((Observer) it.next()).onNetworkChanged();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public void registerObserver(Observer observer) {
        if (observer == null) {
            return;
        }
        synchronized (this.LOCK) {
            if (this.mObservers == null) {
                this.mObservers = new HashSet(1);
            }
            this.mObservers.add(observer);
        }
    }

    public void unregisterObserver(Observer observer) {
        if (observer == null) {
            return;
        }
        synchronized (this.LOCK) {
            Set<Observer> set = this.mObservers;
            if (set != null) {
                set.remove(observer);
            }
        }
    }
}
