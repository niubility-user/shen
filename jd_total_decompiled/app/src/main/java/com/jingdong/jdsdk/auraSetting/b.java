package com.jingdong.jdsdk.auraSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes14.dex */
class b implements d {

    /* renamed from: i  reason: collision with root package name */
    static String f12873i = "key_switch_default_value_1";

    /* renamed from: j  reason: collision with root package name */
    static String f12874j = "key_switch_default_value_2";

    /* renamed from: k  reason: collision with root package name */
    static String f12875k = "key_switch_default_value_3";

    /* renamed from: l  reason: collision with root package name */
    static String f12876l = "key_switch_max_value";

    /* renamed from: m  reason: collision with root package name */
    static String f12877m = "key_switch_min_value";
    private List<a> a = new ArrayList();
    private Map<String, Long> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private Map<Integer, a> f12878c = new HashMap();
    private Map<String, a> d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private Map<String, a> f12879e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private Map<Long, a> f12880f = new HashMap();

    /* renamed from: g  reason: collision with root package name */
    private String[] f12881g = new String[0];

    /* renamed from: h  reason: collision with root package name */
    private List<String> f12882h = new ArrayList();

    /* loaded from: classes14.dex */
    private class a {
        public String a;
        public long b;

        /* renamed from: c  reason: collision with root package name */
        public String f12883c;

        public a(b bVar, int i2, String libName, long j2, String str2) {
            this.a = libName;
            this.b = j2;
            this.f12883c = str2;
        }
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String[] a() {
        return this.f12881g;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long b() {
        return this.b.get(f12877m).longValue();
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long c(long j2) {
        return j2 & 8070450532247928832L;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long d(int i2) {
        if (this.f12878c.get(Integer.valueOf(i2)) == null) {
            return 0L;
        }
        return this.f12878c.get(Integer.valueOf(i2)).b;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long e(String str) {
        Map<String, a> map = this.d;
        if (map != null && map.containsKey(str)) {
            return this.d.get(str).b;
        }
        return 0L;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long f() {
        return this.b.get(f12876l).longValue();
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String g(long j2) {
        if (this.f12880f.get(Long.valueOf(j2)) == null) {
            return null;
        }
        return this.f12880f.get(Long.valueOf(j2)).a;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public List<String> getBundleDownloadOrder() {
        return this.f12882h;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String getBundleNameFromBundleId(int i2) {
        if (this.f12878c.get(Integer.valueOf(i2)) == null) {
            return null;
        }
        return this.f12878c.get(Integer.valueOf(i2)).a;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String getBundleNameFromUpdateID(String str) {
        if (this.f12879e.get(str) == null) {
            return null;
        }
        return this.f12879e.get(str).a;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String getUpdateIdFromBundleName(String str) {
        if (this.d.get(str) == null) {
            return null;
        }
        return this.d.get(str).f12883c;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long h(long j2) {
        long c2 = c(j2);
        long longValue = this.b.get(f12874j).longValue();
        if (0 == c2 || 1152921504606846976L == c2) {
            return this.b.get(f12873i).longValue();
        }
        if (4611686018427387904L == c2) {
            return this.b.get(f12874j).longValue();
        }
        return AuraBundleInfos.AURA_MASK_SWITCH_TYPE_3 == c2 ? this.b.get(f12875k).longValue() : longValue;
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public Set<String> i() {
        return this.f12879e.keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(String str) {
        this.f12882h.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(int i2, String libName, long j2, String str2) {
        a aVar = new a(this, i2, libName, j2, str2);
        this.a.add(aVar);
        this.f12878c.put(Integer.valueOf(i2), aVar);
        this.d.put(libName, aVar);
        this.f12879e.put(str2, aVar);
        this.f12880f.put(Long.valueOf(j2), aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(String str, long j2) {
        this.b.put(str, Long.valueOf(j2));
    }
}
