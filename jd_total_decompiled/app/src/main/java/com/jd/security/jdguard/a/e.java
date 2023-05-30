package com.jd.security.jdguard.a;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes17.dex */
public class e {
    private static final String b = "e";

    /* renamed from: c  reason: collision with root package name */
    private static final Map<Character, String> f6875c;
    private f a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes17.dex */
    public class a<K, V> implements Map.Entry<K, V> {

        /* renamed from: g  reason: collision with root package name */
        private final K f6876g;

        /* renamed from: h  reason: collision with root package name */
        private V f6877h;

        a(e eVar, K k2, V v) {
            this.f6876g = k2;
            this.f6877h = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f6876g;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f6877h;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f6877h;
            this.f6877h = v;
            return v2;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f6875c = hashMap;
        hashMap.put('!', "%21");
        hashMap.put('\'', "%27");
        hashMap.put('(', "%28");
        hashMap.put(')', "%29");
        hashMap.put('*', "%2A");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(f fVar) {
        this.a = fVar;
    }

    private void a(List<Map.Entry<String, String>> list, Uri.Builder builder, boolean z) {
        if (list == null || builder == null || builder.build() == null) {
            return;
        }
        String encodedQuery = builder.build().getEncodedQuery();
        ArrayList arrayList = new ArrayList();
        b(arrayList, encodedQuery);
        for (Map.Entry<String, String> entry : arrayList) {
            try {
                c(list, new a(this, URLDecoder.decode(entry.getKey(), "utf-8"), URLDecoder.decode(entry.getValue(), "utf-8")));
            } catch (Throwable th) {
                com.jd.security.jdguard.f.d.e(th);
                return;
            }
        }
    }

    private void b(List<Map.Entry<String, String>> list, String str) {
        a aVar;
        if (list == null || TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split(ContainerUtils.FIELD_DELIMITER)) {
            String[] split = str2.split(ContainerUtils.KEY_VALUE_DELIMITER, 2);
            if (split.length >= 1) {
                if (split.length == 1) {
                    aVar = new a(this, split[0], "");
                } else {
                    aVar = new a(this, split[0], split[1]);
                }
                c(list, aVar);
            }
        }
    }

    private void c(List<Map.Entry<String, String>> list, Map.Entry<String, String> entry) {
        if (TextUtils.isEmpty(entry.getKey())) {
            return;
        }
        list.add(entry);
    }

    private String d() throws Exception {
        f fVar = this.a;
        if (fVar != null) {
            if (TextUtils.isEmpty(fVar.getPath())) {
                return "/";
            }
            return "" + this.a.getPath();
        }
        throw new Exception(b + "baseString candyOriginalMaterial is null");
    }

    private void e(List<Map.Entry<String, String>> list) {
        Collections.sort(list, new Comparator() { // from class: com.jd.security.jdguard.a.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((String) ((Map.Entry) obj).getValue()).compareTo((String) ((Map.Entry) obj2).getValue());
                return compareTo;
            }
        });
        Collections.sort(list, new Comparator() { // from class: com.jd.security.jdguard.a.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((String) ((Map.Entry) obj).getKey()).compareTo((String) ((Map.Entry) obj2).getKey());
                return compareTo;
            }
        });
    }

    private String f(String str) {
        String str2;
        try {
            str2 = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            str2 = null;
        }
        return p(str2);
    }

    private boolean g() {
        String contentType = this.a.getContentType();
        return contentType != null && contentType.contains("application/x-www-form-urlencoded");
    }

    private Uri.Builder h() throws Exception {
        f fVar = this.a;
        if (fVar != null) {
            if (fVar.c() != null) {
                return Uri.parse(this.a.c().toASCIIString()).buildUpon();
            }
            throw new Exception(b + "finalURI is null");
        }
        throw new Exception(b + "CandyOriginalMaterial is null");
    }

    private String j(List<Map.Entry<String, String>> list) {
        String str = "";
        for (Map.Entry<String, String> entry : list) {
            str = str + entry.getKey() + ContainerUtils.KEY_VALUE_DELIMITER + entry.getValue() + ContainerUtils.FIELD_DELIMITER;
        }
        return str.endsWith(ContainerUtils.FIELD_DELIMITER) ? str.subSequence(0, str.length() - 1).toString() : str;
    }

    private List<Map.Entry<String, String>> k(List<Map.Entry<String, String>> list) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : list) {
            arrayList.add(new a(this, f(entry.getKey()), f(entry.getValue())));
        }
        return arrayList;
    }

    private String o(Uri.Builder builder) throws Exception {
        byte[] bArr;
        if (builder != null) {
            String d = d();
            if (!TextUtils.isEmpty(d)) {
                ArrayList arrayList = new ArrayList();
                a(arrayList, builder, false);
                byte[] a2 = this.a.a();
                if (g() && a2 != null) {
                    a(arrayList, Uri.parse("/?" + new String(a2)).buildUpon(), true);
                }
                List<Map.Entry<String, String>> k2 = k(arrayList);
                e(k2);
                String str = this.a.b() + LangUtils.SINGLE_SPACE + d + LangUtils.SINGLE_SPACE + j(k2);
                if (g()) {
                    bArr = str.getBytes();
                } else if (a2 == null) {
                    bArr = str.getBytes();
                } else {
                    byte[] bytes = str.getBytes();
                    byte[] bArr2 = new byte[bytes.length + a2.length];
                    System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
                    System.arraycopy(a2, 0, bArr2, bytes.length, a2.length);
                    bArr = bArr2;
                }
                if (bArr.length == 0) {
                    return null;
                }
                return com.jd.security.jdguard.b.d().A(bArr);
            }
            throw new Exception(b + "getParametersSignature normalizedURI is null");
        }
        throw new Exception(b + "getParametersSignature builder is null");
    }

    private String p(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (char c2 : str.toCharArray()) {
            String str2 = f6875c.get(Character.valueOf(c2));
            if (str2 == null) {
                str2 = Character.valueOf(c2);
            }
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, String> i() throws Exception {
        if (this.a == null) {
            return null;
        }
        String o = o(h());
        HashMap hashMap = new HashMap();
        if (o != null) {
            hashMap.put("jdgs", o);
        } else {
            com.jd.security.jdguard.f.d.e(new Throwable("header gen failed -> null header"));
            hashMap.put("jdgs", DYConstants.DY_NULL_STR);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public URI l() throws Exception {
        return URI.create(h().toString());
    }
}
