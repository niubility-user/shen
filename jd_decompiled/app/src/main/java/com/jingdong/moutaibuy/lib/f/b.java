package com.jingdong.moutaibuy.lib.f;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jd.framework.json.JDJSON;
import com.jdpay.net.http.HTTP;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.HashMap;

/* loaded from: classes16.dex */
public abstract class b {
    protected final String a;

    /* loaded from: classes16.dex */
    class a implements HttpGroup.OnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ c f14590g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f14591h;

        a(c cVar, int i2) {
            this.f14590g = cVar;
            this.f14591h = i2;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            c cVar = this.f14590g;
            if (cVar != null) {
                cVar.b(this.f14591h, b.this.c(httpResponse));
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            c cVar = this.f14590g;
            if (cVar != null) {
                cVar.d(this.f14591h, httpError);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* renamed from: com.jingdong.moutaibuy.lib.f.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public static class C0706b {
        public int a = -1;
        public int b = -1;

        /* renamed from: c  reason: collision with root package name */
        public String f14593c = "";
    }

    /* loaded from: classes16.dex */
    public interface c {
        void b(int i2, C0706b c0706b);

        void d(int i2, Exception exc);
    }

    public b(String str) {
        this.a = str;
    }

    public void a(int i2, HashMap<String, Object> hashMap, c cVar) {
        HttpSetting b = b();
        if (hashMap != null && !hashMap.isEmpty()) {
            for (String str : hashMap.keySet()) {
                b.putJsonParam(str, hashMap.get(str));
            }
        }
        b.setBusinessLayerCheckSwitch(false);
        b.setListener(new a(cVar, i2));
        HttpGroupUtils.getHttpGroupaAsynPool().add(b);
    }

    protected HttpSetting b() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId(this.a);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        HashMap hashMap = new HashMap();
        hashMap.put(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
        httpSetting.setHeaderMap(hashMap);
        return httpSetting;
    }

    public C0706b c(HttpResponse httpResponse) {
        if (TextUtils.isEmpty(httpResponse.getString())) {
            return null;
        }
        return (C0706b) JDJSON.parseObject(httpResponse.getString(), C0706b.class);
    }
}
