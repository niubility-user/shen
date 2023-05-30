package com.jingdong.manto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes15.dex */
public class AppLifeCycle {
    private static Map<String, Set<Listener>> listeners = new ConcurrentHashMap();

    /* loaded from: classes15.dex */
    public static class Listener {
        public void onAppCreate() {
        }

        public void onAppDestroy() {
        }

        public void onAppPause() {
        }

        public void onAppResume() {
        }
    }

    public static void add(String str, Listener listener) {
        if (str == null || listener == null) {
            return;
        }
        if (!listeners.containsKey(str)) {
            listeners.put(str, Collections.newSetFromMap(new ConcurrentHashMap()));
        }
        listeners.get(str).add(listener);
    }

    private static Iterator<Listener> getIterator(String str) {
        Map<String, Set<Listener>> map = listeners;
        if (str == null) {
            str = "";
        }
        Set<Listener> set = map.get(str);
        if (set == null) {
            set = new HashSet<>();
        }
        return set.iterator();
    }

    public static void notifyOnAppCreate(String str) {
        Iterator<Listener> iterator = getIterator(str);
        while (iterator.hasNext()) {
            iterator.next().onAppCreate();
        }
    }

    public static void notifyOnAppDestroy(String str) {
        Iterator<Listener> iterator = getIterator(str);
        while (iterator.hasNext()) {
            iterator.next().onAppDestroy();
        }
    }

    public static void notifyOnAppPause(String str) {
        Iterator<Listener> iterator = getIterator(str);
        while (iterator.hasNext()) {
            iterator.next().onAppPause();
        }
    }

    public static void notifyOnAppResume(String str) {
        Iterator<Listener> iterator = getIterator(str);
        while (iterator.hasNext()) {
            iterator.next().onAppResume();
        }
    }

    public static void remove(String str, Listener listener) {
        Set<Listener> set;
        if (listener == null || (set = listeners.get(str)) == null || set.isEmpty()) {
            return;
        }
        set.remove(listener);
    }
}
