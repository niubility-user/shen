package com.jingdong.jdsdk.network.utils;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes14.dex */
public class URLParamMap implements Map<String, String>, Serializable {
    private static final String TAG = "URLParamMap";
    private final String charset;
    private Map<String, String> map;

    public URLParamMap() {
        this.map = new HashMap();
        this.charset = "UTF-8";
    }

    private String mapToString() {
        Map<String, String> map = this.map;
        return map == null ? "" : map.toString();
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        throw new RuntimeException("Can't use putAll method");
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, String>> entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends String> map) {
        throw new RuntimeException("Can't use putAll method");
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    public String toString() {
        return "URLParamMap[" + mapToString() + "]";
    }

    @Override // java.util.Map
    public Collection<String> values() {
        return this.map.values();
    }

    @Override // java.util.Map
    public String get(Object obj) {
        return this.map.get(obj);
    }

    @Override // java.util.Map
    public String put(String str, String str2) {
        try {
            return this.map.put(str, URLEncoder.encode(str2, this.charset));
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // java.util.Map
    public String remove(Object obj) {
        return this.map.remove(obj);
    }

    public URLParamMap(String str) {
        this.map = new HashMap();
        this.charset = str;
    }

    public void put(Uri uri) {
        String uri2 = uri.toString();
        if (OKLog.D) {
            OKLog.d(TAG, "put() url -->> " + uri2);
        }
        List<UrlQuerySanitizer.ParameterValuePair> parameterList = new UrlQuerySanitizer(uri2).getParameterList();
        if (OKLog.D) {
            OKLog.d(TAG, "put() parameterList.size() -->> " + parameterList.size());
        }
        Iterator<UrlQuerySanitizer.ParameterValuePair> it = parameterList.iterator();
        while (it.hasNext()) {
            String str = it.next().mParameter;
            String queryParameter = uri.getQueryParameter(str);
            if (OKLog.D) {
                OKLog.d(TAG, "put() -->> key:" + str + ",value:" + queryParameter);
            }
            if (queryParameter != null) {
                put(str, queryParameter);
            }
        }
    }
}
