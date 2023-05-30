package com.jingdong.manto.pkg.b;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.z;
import com.jingdong.sdk.jweb.JWebResourceResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public interface h<T> {

    /* loaded from: classes16.dex */
    public static class a implements h<JWebResourceResponse> {
        @Override // com.jingdong.manto.pkg.b.h
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public JWebResourceResponse a(String str, InputStream inputStream) {
            JWebResourceResponse jWebResourceResponse = new JWebResourceResponse(z.d(str), "utf-8", inputStream);
            jWebResourceResponse.setStatusCodeAndReasonPhrase(200, IMantoBaseModule.SUCCESS);
            return jWebResourceResponse;
        }
    }

    /* loaded from: classes16.dex */
    public static class b implements h<InputStream> {
        @Override // com.jingdong.manto.pkg.b.h
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public InputStream a(String str, InputStream inputStream) {
            return inputStream;
        }
    }

    /* loaded from: classes16.dex */
    public static class c implements h<String> {
        @Override // com.jingdong.manto.pkg.b.h
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public String a(String str, InputStream inputStream) {
            return MantoStringUtils.convertStreamToString(inputStream);
        }
    }

    /* loaded from: classes16.dex */
    public static class d {
        static final Map<Class, h> a;

        static {
            HashMap hashMap = new HashMap(4);
            a = hashMap;
            hashMap.put(InputStream.class, new b());
            hashMap.put(String.class, new c());
            hashMap.put(JWebResourceResponse.class, new a());
        }
    }

    T a(String str, InputStream inputStream);
}
