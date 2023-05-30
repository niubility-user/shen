package com.jingdong.app.discover;

import android.text.TextUtils;
import com.jingdong.common.database.table.FxContentIdTable;
import com.jingdong.common.task.Productor;
import com.jingdong.common.task.TaskManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class StateManager {
    private static HashSet<String> idSet;
    private static volatile boolean isLoading;
    private static HashMap<String, HashSet<String>> pageListIds = new HashMap<>();
    private static ReadWriteLock sDBReadWriteLock = new ReentrantReadWriteLock();
    private static ReadWriteLock sReadWriteLock = new ReentrantReadWriteLock();

    /* loaded from: classes.dex */
    class a implements Productor<Void> {
        a() {
        }

        @Override // com.jingdong.common.task.Productor
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void run() {
            StateManager.sDBReadWriteLock.readLock().lock();
            HashSet<String> ids = FxContentIdTable.getIds();
            StateManager.sReadWriteLock.writeLock().lock();
            HashSet unused = StateManager.idSet = ids;
            StateManager.sReadWriteLock.writeLock().unlock();
            FxContentIdTable.clearHistory();
            StateManager.sDBReadWriteLock.readLock().unlock();
            boolean unused2 = StateManager.isLoading = false;
            return null;
        }
    }

    /* loaded from: classes.dex */
    class b implements Productor<Void> {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // com.jingdong.common.task.Productor
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void run() {
            StateManager.sDBReadWriteLock.writeLock().lock();
            FxContentIdTable.insert(this.a);
            StateManager.sDBReadWriteLock.writeLock().unlock();
            StateManager.sReadWriteLock.writeLock().lock();
            if (StateManager.idSet != null && !StateManager.idSet.contains(this.a)) {
                StateManager.idSet.add(this.a);
            }
            StateManager.sReadWriteLock.writeLock().unlock();
            return null;
        }
    }

    @Deprecated
    public static void clearAll() {
        synchronized (pageListIds) {
            HashMap<String, HashSet<String>> hashMap = pageListIds;
            if (hashMap != null) {
                hashMap.clear();
            }
        }
    }

    public static boolean isInList(String str, String str2) {
        HashMap<String, HashSet<String>> hashMap;
        boolean z = false;
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && (hashMap = pageListIds) != null) {
            synchronized (hashMap) {
                HashSet<String> hashSet = pageListIds.get(str);
                if (hashSet == null || !hashSet.contains(str2)) {
                    if (hashSet == null) {
                        hashSet = new HashSet<>();
                        pageListIds.put(str, hashSet);
                    }
                    hashSet.add(str2);
                } else {
                    z = true;
                }
            }
        }
        return z;
    }

    public static boolean isReaded(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        sReadWriteLock.readLock().lock();
        HashSet<String> hashSet = idSet;
        if (hashSet != null && hashSet.contains(str)) {
            z = true;
        }
        if (idSet == null) {
            sReadWriteLock.readLock().unlock();
            sDBReadWriteLock.readLock().lock();
            boolean queryIdExist = FxContentIdTable.queryIdExist(str);
            sDBReadWriteLock.readLock().unlock();
            return queryIdExist;
        }
        sReadWriteLock.readLock().unlock();
        return z;
    }

    public static void loadReadedIds() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        TaskManager.executeTask(new a(), null);
    }

    public static void removePage(String str) {
        synchronized (pageListIds) {
            if (pageListIds != null && !TextUtils.isEmpty(str)) {
                pageListIds.remove(str);
            }
        }
    }

    public static boolean updateIdReaded(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        sReadWriteLock.writeLock().lock();
        HashSet<String> hashSet = idSet;
        if (hashSet != null && !hashSet.contains(str)) {
            idSet.add(str);
            z = true;
        }
        sReadWriteLock.writeLock().unlock();
        if (z || idSet == null) {
            TaskManager.executeTask(new b(str), null);
        }
        return z;
    }
}
