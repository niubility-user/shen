package com.jd.hybrid.downloader.o;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jd.hybrid.downloader.p.b;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.HybridDataStore;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.SharedPreferenceUtils;
import com.jd.libs.hybrid.base.util.VersionUtils;
import com.jd.libs.xwin.http.BreakPointHelper;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a extends HybridDataStore<com.jd.hybrid.downloader.m.a> {

    /* renamed from: e  reason: collision with root package name */
    public int f2696e;

    /* renamed from: f  reason: collision with root package name */
    private SharedPreferences f2697f;

    /* renamed from: g  reason: collision with root package name */
    private ConcurrentHashMap<String, List<com.jd.hybrid.downloader.m.a>> f2698g;

    public a() {
        super("xCache");
        this.f2696e = 10;
        SharedPreferences createPreference = SharedPreferenceUtils.createPreference(HybridSettings.getAppContext(), "xCache_delayTime");
        this.f2697f = createPreference;
        if (createPreference != null) {
            this.f2696e = createPreference.getInt("delay_time", 10);
        }
        h();
    }

    private void b(com.jd.hybrid.downloader.m.a aVar) {
        String[] list;
        if (aVar.filePath != null) {
            File file = new File(aVar.filePath);
            if (file.exists()) {
                b.a(file);
                if (file.getParent() != null) {
                    File file2 = new File(file.getParent());
                    if (file2.exists() && file2.isDirectory() && (list = file2.list()) != null && list.length == 0) {
                        b.a(file2);
                    }
                }
            }
            aVar.filePath = null;
        }
    }

    private void f(String str, Map<String, com.jd.hybrid.downloader.m.a> map) {
        LinkedList linkedList = new LinkedList();
        Map<String, V> map2 = this.f5899c;
        if (map2 != 0 && map2.size() > 0) {
            BreakPointHelper init = BreakPointHelper.breakPointSwitch ? BreakPointHelper.getInstance().init(HybridSettings.getAppContext()) : null;
            for (com.jd.hybrid.downloader.m.a aVar : this.f5899c.values()) {
                com.jd.hybrid.downloader.m.a aVar2 = map.get(aVar.id);
                if (TextUtils.isEmpty(aVar.source)) {
                    linkedList.add(aVar.id);
                    b(aVar);
                    if (init != null) {
                        init.removeId(aVar.id);
                    }
                } else if (aVar.source.equals(str)) {
                    if (aVar2 == null) {
                        linkedList.add(aVar.id);
                        b(aVar);
                        if (init != null) {
                            init.removeId(aVar.id);
                        }
                    } else {
                        aVar2.cover(aVar);
                    }
                }
            }
        }
        delete(linkedList);
        save(map);
        h();
        Log.d("XCache", "End of merging server configuration !");
    }

    private synchronized void h() {
        ConcurrentHashMap<String, List<com.jd.hybrid.downloader.m.a>> concurrentHashMap = this.f2698g;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        Map<String, V> map = this.f5899c;
        if (map != 0) {
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                i((com.jd.hybrid.downloader.m.a) it.next());
            }
        }
    }

    private void i(com.jd.hybrid.downloader.m.a aVar) {
        int i2 = aVar.project_priority;
        if (i2 == 2) {
            l("t_project", aVar);
        } else if (i2 == 1) {
            l("s_project", aVar);
        } else {
            List<String> list = aVar.demand_classes;
            if (list == null || list.size() <= 0) {
                return;
            }
            Iterator<String> it = aVar.demand_classes.iterator();
            while (it.hasNext()) {
                l(it.next(), aVar);
            }
        }
    }

    private void l(String str, com.jd.hybrid.downloader.m.a aVar) {
        if (this.f2698g == null) {
            this.f2698g = new ConcurrentHashMap<>();
        }
        List<com.jd.hybrid.downloader.m.a> list = this.f2698g.get(str);
        if (list == null) {
            list = new LinkedList<>();
            this.f2698g.put(str, list);
        }
        list.add(aVar);
    }

    private void n(com.jd.hybrid.downloader.m.a aVar, com.jd.hybrid.downloader.m.a aVar2, String str) {
        if (this.f2698g == null) {
            this.f2698g = new ConcurrentHashMap<>();
        }
        List<com.jd.hybrid.downloader.m.a> list = this.f2698g.get(str);
        if (list == null) {
            list = new LinkedList<>();
            this.f2698g.put(str, list);
        }
        list.remove(aVar);
        list.add(aVar2);
    }

    public com.jd.hybrid.downloader.m.b c(String str) {
        return (com.jd.hybrid.downloader.m.a) get(str);
    }

    public synchronized List<com.jd.hybrid.downloader.m.a> d(String str) {
        ConcurrentHashMap<String, List<com.jd.hybrid.downloader.m.a>> concurrentHashMap = this.f2698g;
        if (concurrentHashMap != null) {
            return concurrentHashMap.get(str);
        }
        return null;
    }

    public com.jd.hybrid.downloader.m.a e(String str) {
        return (com.jd.hybrid.downloader.m.a) this.f5899c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.libs.hybrid.base.util.HybridDataStore
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public com.jd.hybrid.downloader.m.a a(JSONObject jSONObject) throws JSONException {
        com.jd.hybrid.downloader.m.a fromJson = new com.jd.hybrid.downloader.m.a().fromJson(jSONObject);
        if (VersionUtils.isAppVersionBetween(HybridSettings.getAppContext(), fromJson.app_min, fromJson.app_max)) {
            if (fromJson.filePath != null && new File(fromJson.filePath).exists()) {
                fromJson.status = 1;
            } else {
                fromJson.filePath = null;
            }
            return fromJson;
        }
        return null;
    }

    public synchronized void j(String str, String str2) {
        int optInt;
        try {
        } catch (JSONException unused) {
            deleteAll();
        }
        if (TextUtils.isEmpty(str2)) {
            f(str, new HashMap());
            return;
        }
        JSONObject jSONObject = new JSONObject(str2);
        JSONArray optJSONArray = jSONObject.has(DynamicPrepareFetcher.KEY_PREPARE_MODULES) ? jSONObject.optJSONArray(DynamicPrepareFetcher.KEY_PREPARE_MODULES) : null;
        if (jSONObject.has("delay_time") && (optInt = jSONObject.optInt("delay_time")) > 0) {
            this.f2696e = optInt;
            SharedPreferenceUtils.commitInt(this.f2697f, "delay_time", optInt);
        }
        if (optJSONArray != null && optJSONArray.length() != 0) {
            k(str, optJSONArray);
            return;
        }
        f(str, new HashMap());
    }

    public synchronized void k(String str, JSONArray jSONArray) {
        try {
            HashMap hashMap = new HashMap();
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("nameSpace");
                        JSONArray optJSONArray = optJSONObject.optJSONArray("files");
                        if (optJSONArray != null) {
                            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                                if (optJSONObject2 != null) {
                                    com.jd.hybrid.downloader.m.a fromJson = new com.jd.hybrid.downloader.m.a().fromJson(optJSONObject2);
                                    fromJson.nameSpace = optString;
                                    fromJson.source = str;
                                    if (VersionUtils.isAppVersionBetween(HybridSettings.getAppContext(), fromJson.app_min, fromJson.app_max)) {
                                        hashMap.put(fromJson.id, fromJson);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            f(str, hashMap);
        } catch (JSONException unused) {
            deleteAll();
        }
    }

    public synchronized void m(com.jd.hybrid.downloader.m.a aVar) {
        com.jd.hybrid.downloader.m.a aVar2 = (com.jd.hybrid.downloader.m.a) this.f5899c.get(aVar.id);
        if (aVar2 != null && aVar2.version_code == aVar.version_code) {
            save(aVar.id, aVar);
            int i2 = aVar.project_priority;
            if (i2 == 2) {
                n(aVar2, aVar, "t_project");
            } else if (i2 == 1) {
                n(aVar2, aVar, "s_project");
            } else {
                List<String> list = aVar.demand_classes;
                if (list != null && list.size() > 0) {
                    Iterator<String> it = aVar.demand_classes.iterator();
                    while (it.hasNext()) {
                        n(aVar2, aVar, it.next());
                    }
                }
            }
        } else {
            Log.d("XCache", "Configuration information has been updated ! id=" + aVar.id);
            b.b(aVar.filePath);
        }
    }
}
