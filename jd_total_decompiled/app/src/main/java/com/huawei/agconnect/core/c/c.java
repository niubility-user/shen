package com.huawei.agconnect.core.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import com.huawei.agconnect.core.ServiceDiscovery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class c {
    private final Context a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class b implements Serializable, Comparator<Map.Entry<String, Integer>> {
        private b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
            return entry.getValue().intValue() - entry2.getValue().intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context) {
        this.a = context;
    }

    private <T extends com.huawei.agconnect.core.b> T a(String str) {
        StringBuilder sb;
        String localizedMessage;
        try {
            Class<?> cls = Class.forName(str);
            if (com.huawei.agconnect.core.b.class.isAssignableFrom(cls)) {
                return (T) Class.forName(str).newInstance();
            }
            String str2 = cls + " must extends from ServiceRegistrar.";
            return null;
        } catch (ClassNotFoundException e2) {
            String str3 = "Can not found service class, " + e2.getMessage();
            return null;
        } catch (IllegalAccessException e3) {
            sb = new StringBuilder();
            sb.append("instantiate service class exception ");
            localizedMessage = e3.getLocalizedMessage();
            sb.append(localizedMessage);
            sb.toString();
            return null;
        } catch (InstantiationException e4) {
            sb = new StringBuilder();
            sb.append("instantiate service class exception ");
            localizedMessage = e4.getLocalizedMessage();
            sb.append(localizedMessage);
            sb.toString();
            return null;
        }
    }

    private List<String> c() {
        StringBuilder sb;
        ArrayList arrayList = new ArrayList();
        Bundle d = d();
        if (d == null) {
            return arrayList;
        }
        HashMap hashMap = new HashMap(10);
        for (String str : d.keySet()) {
            if ("com.huawei.agconnect.core.ServiceRegistrar".equals(d.getString(str))) {
                String[] split = str.split(":");
                if (split.length == 2) {
                    try {
                        hashMap.put(split[0], Integer.valueOf(split[1]));
                    } catch (NumberFormatException e2) {
                        sb = new StringBuilder();
                        sb.append("registrar configuration format error:");
                        str = e2.getMessage();
                    }
                } else if (split.length == 1) {
                    hashMap.put(split[0], 1000);
                } else {
                    sb = new StringBuilder();
                    sb.append("registrar configuration error, ");
                    sb.append(str);
                    sb.toString();
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList2, new b());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(((Map.Entry) it.next()).getKey());
        }
        return arrayList;
    }

    private Bundle d() {
        ServiceInfo serviceInfo;
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            serviceInfo = packageManager.getServiceInfo(new ComponentName(this.a, ServiceDiscovery.class), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            String str = "get ServiceDiscovery exception." + e2.getLocalizedMessage();
        }
        if (serviceInfo == null) {
            return null;
        }
        return serviceInfo.metaData;
    }

    public List<com.huawei.agconnect.core.a> b() {
        List<String> c2 = c();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = c2.iterator();
        while (it.hasNext()) {
            com.huawei.agconnect.core.b a2 = a(it.next());
            if (a2 != null) {
                a2.b(this.a);
                List<com.huawei.agconnect.core.a> a3 = a2.a(this.a);
                if (a3 != null) {
                    arrayList.addAll(a3);
                }
            }
        }
        String str = "services:" + Integer.valueOf(arrayList.size());
        return arrayList;
    }
}
