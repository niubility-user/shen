package com.jd.libs.hybrid.offlineload.db;

import android.text.TextUtils;
import com.jd.libs.hybrid.base.util.HybridDataStore;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.loader.RetryFailInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class OfflineDataStore extends HybridDataStore<OfflineModule> {

    /* renamed from: h  reason: collision with root package name */
    private static volatile OfflineDataStore f5959h;

    /* renamed from: e  reason: collision with root package name */
    private List<OfflineModule> f5960e;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, OfflineModule> f5961f;

    /* renamed from: g  reason: collision with root package name */
    private Map<String, List<OfflineModule>> f5962g;

    public OfflineDataStore() {
        super("offlineData");
        this.d = "OfflineDataStore";
    }

    private OfflineModule b(OfflineModule offlineModule) {
        OfflineModule offlineModule2 = (OfflineModule) get(offlineModule.getAppid());
        if (offlineModule2 != null) {
            if (offlineModule.getFileInfo().getVersionCode() > offlineModule2.getFileInfo().getVersionCode()) {
                return offlineModule;
            }
            boolean hasUnzipFileChanged = offlineModule2.hasUnzipFileChanged();
            if ((!offlineModule2.isAvailable() || hasUnzipFileChanged) && !RetryFailInfo.hasInOverRetry(offlineModule2)) {
                return offlineModule2;
            }
            return null;
        }
        return null;
    }

    private OfflineModule c(OfflineModule offlineModule, String str) {
        List<OfflineModule> list = this.f5962g.get(str);
        OfflineModule offlineModule2 = null;
        if (list != null) {
            Iterator<OfflineModule> it = list.iterator();
            while (it.hasNext()) {
                OfflineModule next = it.next();
                if (offlineModule.getAppid().equals(next.getAppid())) {
                    if (next.getFileInfo().getVersionCode() > offlineModule.getFileInfo().getVersionCode()) {
                        offlineModule2 = next;
                    } else {
                        boolean hasUnzipFileChanged = offlineModule.hasUnzipFileChanged();
                        if ((!offlineModule.isAvailable() || hasUnzipFileChanged) && !RetryFailInfo.hasInOverRetry(offlineModule)) {
                            offlineModule2 = offlineModule;
                        }
                    }
                    it.remove();
                }
            }
        }
        return offlineModule2;
    }

    private void e(OfflineModule offlineModule) {
        if (offlineModule.getDownloadTiming() != null && offlineModule.getDownloadTiming().length != 0) {
            for (String str : offlineModule.getDownloadTiming()) {
                List<OfflineModule> list = this.f5962g.get(str);
                if (list == null) {
                    list = new LinkedList<>();
                    this.f5962g.put(str, list);
                }
                list.add(offlineModule);
            }
            return;
        }
        List<OfflineModule> list2 = this.f5962g.get("");
        if (list2 == null) {
            list2 = new LinkedList<>();
            this.f5962g.put("", list2);
        }
        list2.add(offlineModule);
    }

    public static OfflineDataStore getInstance() {
        if (f5959h == null) {
            synchronized (OfflineDataStore.class) {
                if (f5959h == null) {
                    f5959h = new OfflineDataStore();
                }
            }
        }
        return f5959h;
    }

    public synchronized void classifyDownloads(Map<String, OfflineModule> map) {
        if (this.f5960e == null) {
            this.f5960e = new LinkedList();
        }
        if (this.f5961f == null) {
            this.f5961f = new HashMap();
        }
        if (this.f5962g == null) {
            this.f5962g = new ConcurrentHashMap();
        }
        this.f5960e.clear();
        this.f5961f.clear();
        this.f5962g.clear();
        if (map != null && !map.isEmpty()) {
            for (OfflineModule offlineModule : map.values()) {
                int level = offlineModule.getLevel();
                if (level == 1) {
                    this.f5961f.put(offlineModule.getAppid(), offlineModule);
                } else if (level != 2) {
                    e(offlineModule);
                } else {
                    this.f5960e.add(offlineModule);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.libs.hybrid.base.util.HybridDataStore
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public OfflineModule a(JSONObject jSONObject) throws JSONException {
        return new OfflineModule().fromJson(jSONObject);
    }

    public void delete(OfflineModule offlineModule) {
        if (offlineModule != null) {
            super.delete(offlineModule.getAppid());
        }
    }

    public synchronized List<OfflineModule> getALevelListAndRemove(String str) {
        List<OfflineModule> remove;
        Map<String, List<OfflineModule>> map = this.f5962g;
        if (map == null || (remove = map.remove(str)) == null || remove.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<OfflineModule> it = remove.iterator();
        while (it.hasNext()) {
            OfflineModule b = b(it.next());
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public OfflineModule getByRegexpUrl(String str, int i2) {
        Map<String, V> map;
        if (!TextUtils.isEmpty(str) && (map = this.f5899c) != 0 && !map.isEmpty()) {
            try {
                for (OfflineModule offlineModule : this.f5899c.values()) {
                    if (!offlineModule.isShared() && offlineModule.isRegexpMatch() && HybridUrlUtils.isRegexpMatched(offlineModule.getOriginalUrl(), str)) {
                        if (i2 < 0) {
                            return offlineModule.publicClone();
                        }
                        if (i2 == offlineModule.getFileInfo().getVersionCode()) {
                            return offlineModule.publicClone();
                        }
                    }
                }
            } catch (CloneNotSupportedException e2) {
                Log.e(this.d, e2);
            }
        }
        return null;
    }

    public OfflineModule getByUrl(String str, int i2) {
        Map<String, V> map;
        if (!TextUtils.isEmpty(str) && (map = this.f5899c) != 0 && !map.isEmpty()) {
            try {
                for (OfflineModule offlineModule : this.f5899c.values()) {
                    if (!offlineModule.isShared() && str.equalsIgnoreCase(offlineModule.getOriginalUrl())) {
                        if (i2 < 0) {
                            return offlineModule.publicClone();
                        }
                        if (i2 == offlineModule.getFileInfo().getVersionCode()) {
                            return offlineModule.publicClone();
                        }
                    }
                }
            } catch (CloneNotSupportedException e2) {
                Log.e(this.d, e2);
            }
        }
        return null;
    }

    public synchronized List<OfflineModule> getSLevelListAndRemove() {
        Map<String, OfflineModule> map = this.f5961f;
        this.f5961f = null;
        if (map == null || map.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<OfflineModule> it = map.values().iterator();
        while (it.hasNext()) {
            OfflineModule b = b(it.next());
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public OfflineModule getSharedByRegexpUrl(String str) {
        Map<String, V> map;
        if (!TextUtils.isEmpty(str) && (map = this.f5899c) != 0 && !map.isEmpty()) {
            try {
                for (OfflineModule offlineModule : this.f5899c.values()) {
                    if (offlineModule.isShared() && offlineModule.isRegexpMatch() && HybridUrlUtils.isRegexpMatched(offlineModule.getOriginalUrl(), str)) {
                        return offlineModule.publicClone();
                    }
                }
            } catch (CloneNotSupportedException e2) {
                Log.e(this.d, e2);
            }
        }
        return null;
    }

    public synchronized List<OfflineModule> getTLevelListAndRemove() {
        List<OfflineModule> list = this.f5960e;
        this.f5960e = null;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<OfflineModule> it = list.iterator();
        while (it.hasNext()) {
            OfflineModule b = b(it.next());
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public synchronized OfflineModule removeDownloadModule(OfflineModule offlineModule) {
        Map<String, OfflineModule> map;
        OfflineModule offlineModule2 = null;
        if (this.f5962g == null) {
            return null;
        }
        String[] downloadTiming = offlineModule.getDownloadTiming();
        if (downloadTiming != null && downloadTiming.length > 0) {
            for (String str : downloadTiming) {
                offlineModule2 = c(offlineModule, str);
            }
        } else {
            offlineModule2 = c(offlineModule, "");
        }
        if (offlineModule2 == null && (map = this.f5961f) != null) {
            offlineModule2 = map.remove(offlineModule.getAppid());
        }
        return offlineModule2;
    }

    public boolean save(OfflineModule offlineModule) {
        if (offlineModule != null) {
            return super.save(offlineModule.getAppid(), offlineModule);
        }
        return false;
    }

    public void delete(Collection<OfflineModule> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        HashMap hashMap = new HashMap(collection.size());
        for (OfflineModule offlineModule : collection) {
            hashMap.put(offlineModule.getAppid(), offlineModule);
        }
        super.delete(hashMap);
    }

    public boolean save(Collection<OfflineModule> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        HashMap hashMap = new HashMap(collection.size());
        for (OfflineModule offlineModule : collection) {
            hashMap.put(offlineModule.getAppid(), offlineModule);
        }
        return super.save(hashMap);
    }
}
