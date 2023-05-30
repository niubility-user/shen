package com.jd.libs.hybrid.base.entity;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface IInterfaceCheck {
    boolean useful();

    /* loaded from: classes16.dex */
    public static class Companion {
        @NonNull
        public static <T extends IInterfaceCheck> List<T> removeUseless(List<T> list) {
            if (list == null) {
                return Collections.emptyList();
            }
            Iterator<T> it = list.iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                T next = it.next();
                if (next == null) {
                    it.remove();
                } else if (!next.useful()) {
                    arrayList.add(next);
                    it.remove();
                }
            }
            return arrayList;
        }

        public static <T extends IInterfaceCheck> boolean useful(List<T> list, boolean z) {
            if (z || list != null) {
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        if (!it.next().useful()) {
                            return false;
                        }
                    }
                    return true;
                }
                return true;
            }
            return false;
        }

        @NonNull
        public static <T extends IInterfaceCheck> Map<String, T> removeUseless(Map<String, T> map) {
            if (map == null) {
                return new HashMap(0);
            }
            Iterator<String> it = map.keySet().iterator();
            HashMap hashMap = new HashMap();
            while (it.hasNext()) {
                String next = it.next();
                T t = map.get(next);
                if (t == null) {
                    it.remove();
                } else if (!t.useful()) {
                    hashMap.put(next, t);
                    it.remove();
                }
            }
            return hashMap;
        }
    }
}
