package com.huawei.hms.adapter.sysobs;

import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public final class SystemManager {
    private static SystemManager a = new SystemManager();
    private static final Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static SystemNotifier f1205c = new a();

    private SystemManager() {
    }

    public static SystemManager getInstance() {
        return a;
    }

    public static SystemNotifier getSystemNotifier() {
        return f1205c;
    }

    public void notifyNoticeResult(int i2) {
        f1205c.notifyNoticeObservers(i2);
    }

    public void notifyResolutionResult(Intent intent, String str) {
        f1205c.notifyObservers(intent, str);
    }

    public void notifyUpdateResult(int i2) {
        f1205c.notifyObservers(i2);
    }

    /* loaded from: classes12.dex */
    class a implements SystemNotifier {
        private final List<SystemObserver> a = new ArrayList();

        a() {
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void notifyNoticeObservers(int i2) {
            synchronized (SystemManager.b) {
                Iterator<SystemObserver> it = this.a.iterator();
                while (it.hasNext()) {
                    if (it.next().onNoticeResult(i2)) {
                        it.remove();
                    }
                }
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void notifyObservers(Intent intent, String str) {
            synchronized (SystemManager.b) {
                Iterator<SystemObserver> it = this.a.iterator();
                while (it.hasNext()) {
                    if (it.next().onSolutionResult(intent, str)) {
                        it.remove();
                    }
                }
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void registerObserver(SystemObserver systemObserver) {
            if (systemObserver == null || this.a.contains(systemObserver)) {
                return;
            }
            synchronized (SystemManager.b) {
                this.a.add(systemObserver);
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void unRegisterObserver(SystemObserver systemObserver) {
            synchronized (SystemManager.b) {
                this.a.remove(systemObserver);
            }
        }

        @Override // com.huawei.hms.adapter.sysobs.SystemNotifier
        public void notifyObservers(int i2) {
            synchronized (SystemManager.b) {
                Iterator<SystemObserver> it = this.a.iterator();
                while (it.hasNext()) {
                    if (it.next().onUpdateResult(i2)) {
                        it.remove();
                    }
                }
            }
        }
    }
}
