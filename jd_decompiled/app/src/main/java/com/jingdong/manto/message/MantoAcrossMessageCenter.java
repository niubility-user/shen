package com.jingdong.manto.message;

import android.os.Parcelable;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* loaded from: classes15.dex */
public class MantoAcrossMessageCenter {
    private static final HashMap<String, MantoAcrossMessage> a = new HashMap<>();
    private static final Set<MantoAcrossMessage.Listener> b = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ Object a;

        a(Object obj) {
            this.a = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = MantoAcrossMessageCenter.b.iterator();
            while (it.hasNext()) {
                ((MantoAcrossMessage.Listener) it.next()).onCalled(this.a);
            }
        }
    }

    public static <T extends Parcelable> void notifyCommonData(T t) {
        if (t != null) {
            LinkedList linkedList = new LinkedList();
            HashMap<String, MantoAcrossMessage> hashMap = a;
            synchronized (hashMap) {
                Iterator<MantoAcrossMessage> it = hashMap.values().iterator();
                while (it.hasNext()) {
                    linkedList.add(it.next());
                }
            }
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                MantoAcrossMessage mantoAcrossMessage = (MantoAcrossMessage) it2.next();
                if (t != null) {
                    synchronized (mantoAcrossMessage) {
                        mantoAcrossMessage.f13852e = 5;
                        mantoAcrossMessage.f13853f = t.getClass().getName();
                        mantoAcrossMessage.f13854g = t;
                        mantoAcrossMessage.g();
                    }
                }
            }
        }
    }

    public static <T extends Parcelable> void notifyCommonData(List<String> list, T t) {
        if (t != null) {
            LinkedList linkedList = new LinkedList();
            HashMap<String, MantoAcrossMessage> hashMap = a;
            synchronized (hashMap) {
                Iterator<MantoAcrossMessage> it = hashMap.values().iterator();
                while (it.hasNext()) {
                    linkedList.add(it.next());
                }
            }
            Iterator<String> it2 = list.iterator();
            while (it2.hasNext()) {
                MantoAcrossMessage mantoAcrossMessage = a.get(it2.next());
                if (mantoAcrossMessage != null) {
                    synchronized (mantoAcrossMessage) {
                        mantoAcrossMessage.f13852e = 5;
                        mantoAcrossMessage.f13853f = t.getClass().getName();
                        mantoAcrossMessage.f13854g = t;
                        mantoAcrossMessage.g();
                    }
                }
            }
        }
    }

    public static void notifyMainListeners(Object obj) {
        if (obj != null) {
            LinkedList linkedList = new LinkedList();
            Set<MantoAcrossMessage.Listener> set = b;
            synchronized (set) {
                Iterator<MantoAcrossMessage.Listener> it = set.iterator();
                while (it.hasNext()) {
                    linkedList.add(it.next());
                }
            }
            com.jingdong.manto.sdk.thread.a.b(new a(obj));
        }
    }

    public static void registMainListener(MantoAcrossMessage.Listener listener) {
        Set<MantoAcrossMessage.Listener> set = b;
        synchronized (set) {
            set.add(listener);
            MantoLog.d("MantoAcrossMessageCenter", "registMainListener size " + set.size());
        }
    }

    public static void register(MantoAcrossMessage mantoAcrossMessage) {
        if (mantoAcrossMessage.d == null) {
            return;
        }
        HashMap<String, MantoAcrossMessage> hashMap = a;
        synchronized (hashMap) {
            if (hashMap.get(mantoAcrossMessage.d) != null) {
                hashMap.remove(mantoAcrossMessage.d);
            }
            hashMap.put(mantoAcrossMessage.d, mantoAcrossMessage);
            MantoLog.d("MantoAcrossMessageCenter", "register events size " + hashMap.size());
        }
    }

    public static void unRegistMainListener(MantoAcrossMessage.Listener listener) {
        if (listener != null) {
            Set<MantoAcrossMessage.Listener> set = b;
            synchronized (set) {
                set.remove(listener);
                MantoLog.d("MantoAcrossMessageCenter", "unRegistMainListener size " + set.size());
            }
        }
    }

    public static void unregister(MantoAcrossMessage mantoAcrossMessage) {
        HashMap<String, MantoAcrossMessage> hashMap = a;
        synchronized (hashMap) {
            hashMap.remove(mantoAcrossMessage.d);
            MantoLog.d("MantoAcrossMessageCenter", "unregister events size " + hashMap.size());
        }
    }
}
