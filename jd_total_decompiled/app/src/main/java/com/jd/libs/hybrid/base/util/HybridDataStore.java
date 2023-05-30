package com.jd.libs.hybrid.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.entity.IClone;
import com.jd.libs.hybrid.base.entity.IJsonfy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class HybridDataStore<V extends IJsonfy<V> & IClone<V>> {
    private final SharedPreferences a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    protected final Map<String, V> f5899c;
    protected String d = "HybridDataStore";

    public HybridDataStore(String str) {
        IJsonfy a;
        Context appContext = HybridSettings.getAppContext();
        String str2 = "jdhybrid_" + str;
        this.b = str2;
        SharedPreferences createPreference = SharedPreferenceUtils.createPreference(appContext, str2);
        this.a = createPreference;
        if (createPreference != null) {
            Map<String, ?> all = createPreference.getAll();
            if (all != null && !all.isEmpty()) {
                this.f5899c = new ConcurrentHashMap(all.size());
                for (String str3 : all.keySet()) {
                    try {
                        String string = this.a.getString(str3, null);
                        if (!TextUtils.isEmpty(string) && (a = a(new JSONObject(string))) != null) {
                            this.f5899c.put(str3, a);
                        }
                    } catch (Exception e2) {
                        Log.e(this.d, e2);
                        ExceptionUtils.reportError("DataStoreErr", "error in crating data obj", "HybridDataStore#parseSingle", ExceptionUtils.getStackStringFromException(e2));
                    }
                }
                return;
            }
            this.f5899c = new ConcurrentHashMap();
            return;
        }
        this.f5899c = new ConcurrentHashMap();
    }

    /* JADX WARN: Incorrect return type in method signature: (Lorg/json/JSONObject;)TV; */
    protected abstract IJsonfy a(JSONObject jSONObject) throws JSONException;

    public boolean contains(String str) {
        Map<String, V> map = this.f5899c;
        return map != null && map.containsKey(str);
    }

    public void delete(String str) {
        synchronized (this) {
            if (Log.isDebug()) {
                Log.d(this.d, "**** Delete config in " + this.b + ": " + str);
            }
            this.f5899c.remove(str);
            SharedPreferenceUtils.putString(this.a, str, null);
        }
    }

    public void deleteAll() {
        synchronized (this) {
            Iterator<String> it = this.f5899c.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (Log.isDebug()) {
                    Log.d(this.d, "**** Delete config in " + this.b + ": " + next);
                }
                it.remove();
                SharedPreferenceUtils.putString(this.a, next, null);
            }
            SharedPreferenceUtils.apply(this.a);
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/String;)TV; */
    @Nullable
    public IJsonfy get(String str) {
        IJsonfy iJsonfy = (IJsonfy) this.f5899c.get(str);
        if (iJsonfy != null) {
            try {
                return (IJsonfy) ((IClone) iJsonfy).publicClone();
            } catch (CloneNotSupportedException e2) {
                Log.e(this.d, e2);
                ExceptionUtils.reportError("DataStoreErr", "error in get data obj", "HybridDataStore#get", ExceptionUtils.getStackStringFromException(e2));
                return null;
            }
        }
        return null;
    }

    @Nullable
    public Map<String, V> getAll() {
        int size = this.f5899c.size();
        if (size > 0) {
            HashMap hashMap = new HashMap(size);
            for (String str : this.f5899c.keySet()) {
                IJsonfy iJsonfy = (IJsonfy) this.f5899c.get(str);
                if (iJsonfy != null) {
                    try {
                        hashMap.put(str, (IJsonfy) ((IClone) iJsonfy).publicClone());
                    } catch (CloneNotSupportedException e2) {
                        Log.e(this.d, e2);
                        ExceptionUtils.reportError("DataStoreErr", "error in get all data objs", "HybridDataStore#getAll", ExceptionUtils.getStackStringFromException(e2));
                    }
                }
            }
            return hashMap;
        }
        return null;
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/String;TV;)Z */
    public boolean save(String str, IJsonfy iJsonfy) {
        if (iJsonfy == null) {
            return false;
        }
        try {
            IJsonfy iJsonfy2 = (IJsonfy) ((IClone) iJsonfy).publicClone();
            JSONObject json = iJsonfy2.toJson();
            String jSONObject = json != null ? json.toString() : null;
            if (!TextUtils.isEmpty(jSONObject)) {
                synchronized (this) {
                    if (Log.isDebug()) {
                        boolean containsKey = this.f5899c.containsKey(str);
                        String str2 = this.d;
                        StringBuilder sb = new StringBuilder();
                        sb.append("**** ");
                        sb.append(!containsKey ? "Save new" : "Update");
                        sb.append(" config in ");
                        sb.append(this.b);
                        sb.append(": ");
                        sb.append(jSONObject);
                        Log.d(str2, sb.toString());
                    }
                    this.f5899c.put(str, iJsonfy2);
                    SharedPreferenceUtils.putString(this.a, str, jSONObject);
                }
                return true;
            }
        } catch (Exception e2) {
            Log.e(this.d, e2);
            ExceptionUtils.reportError("DataStoreErr", "error in save data obj", "HybridDataStore#save", ExceptionUtils.getStackStringFromException(e2));
        }
        return false;
    }

    public int size() {
        Map<String, V> map = this.f5899c;
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    public void delete(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        synchronized (this) {
            for (String str : list) {
                if (Log.isDebug()) {
                    Log.d(this.d, "**** Delete config in " + this.b + ": " + str);
                }
                this.f5899c.remove(str);
                SharedPreferenceUtils.putString(this.a, str, null);
            }
            SharedPreferenceUtils.apply(this.a);
        }
    }

    public boolean save(Map<String, V> map) {
        if (map != null && !map.isEmpty()) {
            try {
                synchronized (this) {
                    for (String str : map.keySet()) {
                        IJsonfy iJsonfy = (IJsonfy) map.get(str);
                        if (iJsonfy != null) {
                            IJsonfy iJsonfy2 = (IJsonfy) ((IClone) iJsonfy).publicClone();
                            JSONObject json = iJsonfy2.toJson();
                            String jSONObject = json != null ? json.toString() : null;
                            if (!TextUtils.isEmpty(jSONObject)) {
                                if (Log.isDebug()) {
                                    boolean containsKey = this.f5899c.containsKey(str);
                                    String str2 = this.d;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("**** ");
                                    sb.append(!containsKey ? "Save new" : "Update");
                                    sb.append(" config in ");
                                    sb.append(this.b);
                                    sb.append(": ");
                                    sb.append(jSONObject);
                                    Log.d(str2, sb.toString());
                                }
                                this.f5899c.put(str, iJsonfy2);
                                SharedPreferenceUtils.putString(this.a, str, jSONObject);
                            }
                        }
                    }
                    SharedPreferenceUtils.apply(this.a);
                }
                return true;
            } catch (Exception e2) {
                Log.e(this.d, e2);
                ExceptionUtils.reportError("DataStoreErr", "error in save data obj", "HybridDataStore#save", ExceptionUtils.getStackStringFromException(e2));
            }
        }
        return false;
    }

    public void delete(Map<String, V> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        synchronized (this) {
            for (String str : map.keySet()) {
                if (Log.isDebug()) {
                    Log.d(this.d, "**** Delete config in " + this.b + ": " + str);
                }
                this.f5899c.remove(str);
                SharedPreferenceUtils.putString(this.a, str, null);
            }
            SharedPreferenceUtils.apply(this.a);
        }
    }
}
