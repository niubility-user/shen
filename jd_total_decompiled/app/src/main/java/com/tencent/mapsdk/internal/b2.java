package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.tencent.map.sdk.comps.offlinemap.OfflineCity;
import com.tencent.map.sdk.comps.offlinemap.OfflineItem;
import com.tencent.map.sdk.comps.offlinemap.OfflineItemController;
import com.tencent.map.sdk.comps.offlinemap.OfflineMapComponent;
import com.tencent.map.sdk.comps.offlinemap.OfflineMapSyncedListener;
import com.tencent.map.sdk.comps.offlinemap.OfflineProvince;
import com.tencent.map.sdk.comps.offlinemap.OfflineStatusChangedListener;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.map.tools.net.NetManager;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateReq;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateRsp;
import com.tencent.mapsdk.core.components.protocol.jce.conf.SCFileUpdateRsp;
import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.internal.u3;
import com.tencent.mapsdk.internal.v3;
import com.tencent.mapsdk.internal.w3;
import com.tencent.mapsdk.internal.z1;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes9.dex */
public class b2 extends n1 implements OfflineMapComponent, z1.d {
    public static final String q = "key_offline_map_opened_cities";
    public static final String r = "key_offline_map_config_version";
    public static final String s = "key_offline_map_config_md5";
    public static final String t = "key_offline_map_config_url";
    private static final String u = "key_offline_map_items_state";
    public static final String v = "sdk_offline_city_ver.json";
    public static final String w = "offline_city_list.json";

    /* renamed from: c */
    private hc f16270c;
    private boolean d;

    /* renamed from: e */
    private List<OfflineItem> f16271e = new ArrayList();

    /* renamed from: f */
    private List<OfflineItem> f16272f = new ArrayList();

    /* renamed from: g */
    private Map<String, c> f16273g = new HashMap();

    /* renamed from: h */
    private File f16274h;

    /* renamed from: i */
    private File f16275i;

    /* renamed from: j */
    private String f16276j;

    /* renamed from: k */
    private c2 f16277k;

    /* renamed from: l */
    private Map<a2, z1> f16278l;

    /* renamed from: m */
    private volatile Callback<List<OfflineItem>> f16279m;

    /* renamed from: n */
    private OfflineMapSyncedListener f16280n;
    private volatile boolean o;
    private boolean p;

    /* loaded from: classes9.dex */
    public class a extends ba.c<Object> {
        public a() {
            b2.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        public void callback(Object obj) {
            if (b2.this.f16279m != null) {
                b2.this.f16279m.callback(b2.this.getOfflineItemList());
                b2.this.f16279m = null;
            }
            b2.this.o = false;
            if (b2.this.f16280n != null) {
                b2.this.f16280n.onSynced(b2.this.p);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b extends ba.i<Object> {
        public final /* synthetic */ o1 b;

        public b(o1 o1Var) {
            b2.this = r1;
            this.b = o1Var;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            b2.this.p = false;
            if (!b2.this.d) {
                b2.this.d(this.b);
                return null;
            }
            b2 b2Var = b2.this;
            b2Var.p = b2Var.j();
            b2 b2Var2 = b2.this;
            b2Var2.p = b2Var2.i();
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends JsonComposer {
        public String a;
        public int b;

        /* renamed from: c */
        public boolean f16282c;

        private c() {
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    private z1 a(OfflineItem offlineItem, OfflineStatusChangedListener offlineStatusChangedListener) {
        List<OfflineItem> list;
        o1 mapContext = getMapContext();
        if (offlineItem == null || (list = this.f16271e) == null || this.f16277k == null || mapContext == null) {
            ma.g(la.u, "\u65e0\u6548\u914d\u7f6e config:" + this.f16277k + "|item:" + offlineItem);
            return null;
        }
        boolean z = false;
        Iterator<OfflineItem> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next() == offlineItem) {
                z = true;
                break;
            }
        }
        if (z) {
            a2 a2 = this.f16277k.a(offlineItem);
            if (a2 != null) {
                z1 z1Var = this.f16278l.get(a2);
                if (z1Var == null) {
                    z1 z1Var2 = new z1(mapContext, this.f16276j, offlineItem, a2, this.f16270c, offlineStatusChangedListener);
                    this.f16278l.put(a2, z1Var2);
                    z1Var = z1Var2;
                }
                z1Var.a(offlineStatusChangedListener);
                z1Var.a(this);
                ma.c(la.u, "\u83b7\u5f97\u79bb\u7ebf\u57ce\u5e02[" + offlineItem.getName() + "]\u7684\u914d\u7f6e\u6210\u529f\uff01");
                return z1Var;
            }
        } else {
            ma.g(la.u, "\u65e0\u6548\u57ce\u5e02\uff1a" + offlineItem);
        }
        return null;
    }

    private void c(o1 o1Var) {
        String a2 = this.f16270c.a(q, "");
        if (this.d || !TextUtils.isEmpty(a2)) {
            v6 w2 = o1Var.w();
            if (w2 != null) {
                w2.m().b();
            }
            if (this.o) {
                return;
            }
            this.o = true;
            ba.a((ba.i) new b(o1Var)).a((ba.d.b) null, (ba.c<ba.d.b>) new a());
        }
    }

    public void d(o1 o1Var) {
        Iterator<OfflineItem> it = g().iterator();
        while (it.hasNext()) {
            z1 a2 = a(it.next(), (OfflineStatusChangedListener) null);
            if (a2 != null) {
                a2.a(o1Var);
            }
        }
    }

    private List<OfflineItem> g() {
        List<OfflineItem> list;
        ArrayList arrayList = new ArrayList();
        String[] split = this.f16270c.a(q, "").split(DYConstants.DY_REGEX_COMMA);
        if (split.length != 0 && (list = this.f16271e) != null) {
            for (OfflineItem offlineItem : list) {
                if (Arrays.binarySearch(split, offlineItem.getPinyin()) >= 0) {
                    arrayList.add(offlineItem);
                }
            }
        }
        return arrayList;
    }

    private void h() {
        c2 c2Var = this.f16277k;
        if (c2Var == null || c2Var.f16358e == null || this.f16271e.isEmpty()) {
            return;
        }
        ma.c(la.u, "\u6dfb\u52a0item\u7684\u6570\u636e\u72b6\u6001");
        Set<String> keySet = this.f16273g.keySet();
        for (OfflineItem offlineItem : this.f16271e) {
            Iterator<a2> it = this.f16277k.f16358e.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (offlineItem.getPinyin().equals(it.next().f16220c)) {
                        offlineItem.setSize(r5.d);
                        Iterator<String> it2 = keySet.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (offlineItem.getPinyin().equals(it2.next())) {
                                    z1 a2 = a(offlineItem, (OfflineStatusChangedListener) null);
                                    if (a2 != null) {
                                        offlineItem.setUpgrade(a2.checkInvalidate());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ma.c(la.u, "\u6dfb\u52a0item\u7684\u6570\u636e\u72b6\u6001\u5b8c\u6210\uff01\uff01");
    }

    private synchronized void h(String str) {
        OfflineItem a2;
        List<OfflineItem> list;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Object nextValue = new JSONTokener(str).nextValue();
        if (nextValue instanceof JSONArray) {
            List<y1> parseToList = JsonUtils.parseToList((JSONArray) nextValue, y1.class, new Object[0]);
            if (!parseToList.isEmpty()) {
                this.f16271e = new ArrayList();
                this.f16272f = new ArrayList();
                for (y1 y1Var : parseToList) {
                    if (y1Var.b.startsWith(sh.f17255i)) {
                        a2 = y1Var.a();
                        this.f16272f.add(a2);
                        list = this.f16271e;
                    } else {
                        List<y1> list2 = y1Var.f17486c;
                        if (list2 == null || list2.isEmpty()) {
                            a2 = y1Var.a((OfflineProvince) null);
                            this.f16272f.add(a2);
                            list = this.f16271e;
                        } else {
                            ArrayList arrayList = new ArrayList();
                            OfflineProvince a3 = y1Var.a(arrayList);
                            this.f16272f.add(a3);
                            Iterator<y1> it = y1Var.f17486c.iterator();
                            while (it.hasNext()) {
                                OfflineCity a4 = it.next().a(a3);
                                this.f16271e.add(a4);
                                arrayList.add(a4);
                            }
                        }
                    }
                    list.add(a2);
                }
                h();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0065 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean i() {
        String str;
        if (!this.f16275i.exists()) {
            ma.c(la.u, "\u8bf7\u6c42\u79bb\u7ebf\u57ce\u5e02\u5217\u8868...");
            u3.a downloadOfflineMapCityList = ((y2) ((m3) l2.a(m3.class)).d()).downloadOfflineMapCityList(this.f16276j);
            downloadOfflineMapCityList.charset = "utf-8";
            if (downloadOfflineMapCityList.available()) {
                ma.c(la.u, "\u79bb\u7ebf\u57ce\u5e02\u5217\u8868\u4e0b\u8f7d\u6210\u529f");
                w3.a aVar = new w3.a(downloadOfflineMapCityList);
                if (aVar.available()) {
                    h(aVar.a());
                    str = "\u79bb\u7ebf\u57ce\u5e02\u5217\u8868\u89e3\u6790\u6210\u529f";
                }
            }
            if (this.f16271e == null) {
                ma.c(la.u, "\u83b7\u5f97\u79bb\u7ebf\u57ce\u5e02\u5217\u8868\u6210\u529f\uff01");
                return true;
            }
            return false;
        }
        h(new String(fa.h(this.f16275i)));
        str = "\u79bb\u7ebf\u57ce\u5e02\u5217\u8868\u4f7f\u7528\u7f13\u5b58";
        ma.c(la.u, str);
        if (this.f16271e == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x01f4 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean j() {
        String str;
        o1 mapContext = getMapContext();
        if (mapContext == null) {
            return false;
        }
        int b2 = this.f16270c.b(r);
        String d = this.f16270c.d(s);
        ma.c(la.u, "\u68c0\u67e5\u79bb\u7ebf\u914d\u7f6e\u66f4\u65b0, \u5f53\u524dv:" + b2 + "|md5:" + d + "obj:" + this);
        ArrayList<FileUpdateReq> arrayList = new ArrayList<>();
        FileUpdateReq fileUpdateReq = new FileUpdateReq(v, b2, d);
        arrayList.add(fileUpdateReq);
        v3.a<SCFileUpdateRsp> checkUpdate = ((y2) ((m3) l2.a(m3.class)).d()).checkUpdate(b7.F(), b7.A(), b7.N(), b7.G(), mapContext.q().b(), arrayList, mapContext.q().b(), mapContext.y(), "", mapContext.n(), "");
        ma.c(la.u, "\u79bb\u7ebf\u914d\u7f6e\u8bf7\u6c42\u66f4\u65b0\u7ed3\u675f\uff1a" + checkUpdate.toHumanString());
        if (checkUpdate.available()) {
            FileUpdateRsp fileUpdateRsp = checkUpdate.a().vItems.get(0);
            String a2 = this.f16274h.exists() ? va.a(this.f16274h) : null;
            if (!this.f16274h.exists() || (fileUpdateRsp != null && v.equals(fileUpdateRsp.sName) && fileUpdateRsp.iVersion != fileUpdateReq.iVersion && !TextUtils.isEmpty(fileUpdateRsp.sUpdateUrl) && fileUpdateRsp.iFileSize != 0 && fileUpdateRsp.iFileUpdated != 0 && !TextUtils.isEmpty(fileUpdateRsp.sMd5) && !fileUpdateRsp.sMd5.equals(a2))) {
                String str2 = fileUpdateRsp.sUpdateUrl;
                String str3 = fileUpdateRsp.sMd5;
                int i2 = fileUpdateRsp.iVersion;
                if (fileUpdateRsp.iFileUpdated == 0 && !this.f16274h.exists()) {
                    str2 = this.f16270c.a(t, "");
                    str3 = this.f16270c.a(s, "");
                    i2 = this.f16270c.a(r, 0);
                }
                if (TextUtils.isEmpty(str2)) {
                    ma.g(la.u, "\u79bb\u7ebf\u914d\u7f6e\u6587\u4ef6\u7684\u8bf7\u6c42\u94fe\u63a5\u4e3a\u7a7a\uff01");
                    return false;
                }
                if (NetManager.getInstance().builder().url(str2).downloadTo(this.f16274h).available()) {
                    if (va.a(this.f16274h).equals(str3)) {
                        ma.c(la.u, "\u79bb\u7ebf\u914d\u7f6e\u6587\u4ef6\u4e0b\u8f7d\u6210\u529f");
                        this.f16270c.b(r, i2);
                        this.f16270c.b(s, str3);
                        this.f16270c.b(t, str2);
                    } else {
                        ma.c(la.u, "\u79bb\u7ebf\u914d\u7f6e\u6587\u4ef6MD5\u6821\u9a8c\u5931\u8d25");
                        fa.d(this.f16274h);
                    }
                }
                if (this.f16274h.exists()) {
                    ma.g(la.u, "\u79bb\u7ebf\u914d\u7f6e\u6587\u4ef6\u4e0d\u5b58\u5728\uff01");
                } else {
                    try {
                        Object nextValue = new JSONTokener(new String(fa.h(this.f16274h))).nextValue();
                        if (nextValue instanceof JSONObject) {
                            this.f16277k = (c2) JsonUtils.parseToModel((JSONObject) nextValue, c2.class, new Object[0]);
                            ma.c(la.u, "\u521b\u5efa\u79bb\u7ebf\u914d\u7f6e\u6587\u4ef6\u5bf9\u8c61\u6570\u636e\uff1a" + this.f16277k);
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                if (this.f16277k == null) {
                    ma.c(la.u, "\u83b7\u5f97\u79bb\u7ebf\u914d\u7f6e\u6210\u529f\uff01");
                    return true;
                }
                return false;
            }
            str = "\u8df3\u8fc7\u66f4\u65b0";
        } else {
            str = "\u79bb\u7ebf\u5730\u56fe\u914d\u7f6e\u8bf7\u6c42\u9519\u8bef\uff1a" + checkUpdate.toHumanString();
        }
        ma.c(la.u, str);
        if (this.f16274h.exists()) {
        }
        if (this.f16277k == null) {
        }
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void a(Context context) {
        super.a(context);
        this.f16276j = lc.b(context).e();
        this.f16274h = new File(this.f16276j, v);
        this.f16275i = new File(this.f16276j, w);
        this.f16278l = new HashMap();
    }

    @Override // com.tencent.mapsdk.internal.z1.d
    public void a(OfflineItem offlineItem, int i2) {
        if (offlineItem == null) {
            return;
        }
        String pinyin = offlineItem.getPinyin();
        c cVar = this.f16273g.get(pinyin);
        if (cVar == null) {
            cVar = new c(null);
            this.f16273g.put(pinyin, cVar);
        }
        cVar.a = pinyin;
        cVar.b = i2;
        offlineItem.setPercentage(i2);
    }

    @Override // com.tencent.mapsdk.internal.z1.d
    public void a(OfflineItem offlineItem, boolean z) {
        String sb;
        StringBuilder sb2;
        String str;
        String a2 = this.f16270c.a(q, "");
        ma.c(la.u, "\u5f53\u524d\u5f00\u542f\u57ce\u5e02IDS\uff1a" + a2);
        String[] split = a2.split(DYConstants.DY_REGEX_COMMA);
        int binarySearch = Arrays.binarySearch(split, offlineItem.getPinyin());
        if (z) {
            if (binarySearch >= 0) {
                return;
            }
            sb = a2 + offlineItem.getPinyin() + DYConstants.DY_REGEX_COMMA;
            sb2 = new StringBuilder();
            str = "\u65b0\u589e\u5f00\u542f\u57ce\u5e02IDS\uff1a";
        } else if (binarySearch < 0) {
            return;
        } else {
            StringBuilder sb3 = new StringBuilder();
            for (String str2 : split) {
                if (!str2.equals(offlineItem.getPinyin())) {
                    sb3.append(str2);
                    sb3.append(DYConstants.DY_REGEX_COMMA);
                }
            }
            sb = sb3.toString();
            sb2 = new StringBuilder();
            str = "\u5269\u4f59\u5f00\u542f\u57ce\u5e02IDS\uff1a";
        }
        sb2.append(str);
        sb2.append(sb);
        ma.c(la.u, sb2.toString());
        this.f16270c.b(q, sb);
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void a(o1 o1Var) {
        super.a(o1Var);
        this.d = o1Var.r().isOfflineMapEnable();
        hc a2 = jc.a(e(), o1Var.q().j());
        this.f16270c = a2;
        String a3 = a2.a(u, "");
        ma.c(la.u, "\u83b7\u53d6\u6301\u4e45\u5316\u72b6\u6001, json\uff1a" + a3);
        if (!TextUtils.isEmpty(a3)) {
            try {
                for (c cVar : JsonUtils.parseToList(new JSONArray(a3), c.class, new Object[0])) {
                    this.f16273g.put(cVar.a, cVar);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        c(o1Var);
    }

    @Override // com.tencent.mapsdk.internal.z1.d
    public void b(OfflineItem offlineItem, boolean z) {
        if (offlineItem == null) {
            return;
        }
        String pinyin = offlineItem.getPinyin();
        c cVar = this.f16273g.get(pinyin);
        if (cVar == null) {
            cVar = new c(null);
            this.f16273g.put(pinyin, cVar);
        }
        cVar.a = pinyin;
        cVar.f16282c = z;
        offlineItem.setUpgrade(z);
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void b(o1 o1Var) {
        super.b(o1Var);
        if (this.o) {
            this.f16279m = null;
            this.o = false;
        }
        if (this.f16273g.isEmpty()) {
            return;
        }
        String collectionToJson = JsonUtils.collectionToJson(this.f16273g.values());
        ma.c(la.u, "\u4fdd\u5b58\u6301\u4e45\u5316\u72b6\u6001, json\uff1a" + collectionToJson);
        this.f16270c.b(u, collectionToJson);
    }

    @Override // com.tencent.mapsdk.internal.n1
    public void f() {
        super.f();
        for (Map.Entry<a2, z1> entry : this.f16278l.entrySet()) {
            z1 value = entry.getValue();
            if (value != null) {
                value.a();
            }
            entry.setValue(null);
        }
        this.f16278l.clear();
        this.f16279m = null;
        this.f16280n = null;
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineMapComponent
    public OfflineItemController getOfflineItemController(OfflineItem offlineItem, OfflineStatusChangedListener offlineStatusChangedListener) {
        if (this.d) {
            return a(offlineItem, offlineStatusChangedListener);
        }
        return null;
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineMapComponent
    public synchronized List<OfflineItem> getOfflineItemList() {
        for (OfflineItem offlineItem : this.f16271e) {
            c cVar = this.f16273g.get(offlineItem.getPinyin());
            if (cVar != null) {
                offlineItem.setPercentage(cVar.b);
                offlineItem.setUpgrade(cVar.f16282c);
            }
        }
        return this.f16272f;
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineMapComponent
    public void getOfflineItemList(Callback<List<OfflineItem>> callback) {
        this.f16279m = callback;
        if (this.o) {
            return;
        }
        c(getMapContext());
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineMapComponent
    public boolean isOfflineMapEnable() {
        return this.d;
    }

    @Override // com.tencent.map.sdk.comps.offlinemap.OfflineMapComponent
    public void syncLatestData(OfflineMapSyncedListener offlineMapSyncedListener) {
        this.f16280n = offlineMapSyncedListener;
        if (this.o) {
            return;
        }
        c(getMapContext());
    }
}
