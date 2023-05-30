package com.jdpay.lib.event;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes18.dex */
public class JPEventManager {
    private static final ArrayList<WeakReference<JPEventObserver>> observers = new ArrayList<>(8);

    public static synchronized void addObserver(@NonNull JPEventObserver jPEventObserver) {
        synchronized (JPEventManager.class) {
            if (hasObserver(jPEventObserver)) {
                return;
            }
            observers.add(new WeakReference<>(jPEventObserver));
        }
    }

    public static synchronized boolean hasObserver(@NonNull JPEventObserver jPEventObserver) {
        boolean z;
        synchronized (JPEventManager.class) {
            int i2 = 0;
            z = false;
            while (true) {
                ArrayList<WeakReference<JPEventObserver>> arrayList = observers;
                if (i2 < arrayList.size()) {
                    JPEventObserver jPEventObserver2 = arrayList.get(i2).get();
                    if (jPEventObserver2 == null) {
                        int i3 = i2 - 1;
                        arrayList.remove(i2);
                        i2 = i3;
                    } else if (jPEventObserver2 == jPEventObserver) {
                        z = true;
                    }
                    i2++;
                }
            }
        }
        return z;
    }

    public static synchronized int post(@NonNull JPEvent jPEvent) {
        int i2;
        synchronized (JPEventManager.class) {
            int i3 = 0;
            i2 = 0;
            while (true) {
                ArrayList<WeakReference<JPEventObserver>> arrayList = observers;
                if (i3 < arrayList.size()) {
                    JPEventObserver jPEventObserver = arrayList.get(i3).get();
                    if (jPEventObserver == null) {
                        arrayList.remove(i3);
                        i3--;
                    } else if (jPEventObserver.onJPEvent(jPEvent)) {
                        i2++;
                    }
                    i3++;
                }
            }
        }
        return i2;
    }

    public static synchronized void removeObserver(@NonNull JPEventObserver jPEventObserver) {
        synchronized (JPEventManager.class) {
            int i2 = 0;
            while (true) {
                ArrayList<WeakReference<JPEventObserver>> arrayList = observers;
                if (i2 < arrayList.size()) {
                    JPEventObserver jPEventObserver2 = arrayList.get(i2).get();
                    if (jPEventObserver2 == null || jPEventObserver2 == jPEventObserver) {
                        int i3 = i2 - 1;
                        arrayList.remove(i2);
                        i2 = i3;
                    }
                    i2++;
                }
            }
        }
    }
}
