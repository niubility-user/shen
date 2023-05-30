package com.jingdong.common.web;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.DownloadAdapter;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.xwin.http.StreamRequest;
import com.jd.libs.xwin.http.a;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.math.BigDecimal;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class DownloadAdapterImpl extends DownloadAdapter {
    @Override // com.jd.libs.hybrid.adapter.DownloadAdapter
    public void downloadFile(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, String str3, String str4, final a.InterfaceC0173a interfaceC0173a) {
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setImmutable(true);
        fileGuider.setChildDirName(str3);
        fileGuider.setFileName(str4);
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        if (!TextUtils.isEmpty(str2)) {
            httpSetting.setReferer(str2);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("Connection", "keep-alive");
        hashMap2.put(HttpHeaders.ACCEPT_ENCODING, "gzip");
        if (z) {
            String setting = HybridSDK.getSetting("userAgent");
            if (!TextUtils.isEmpty(setting)) {
                hashMap2.put("User-Agent", setting);
            }
        }
        if (z2) {
            String cookieString = HybridBase.getInstance().getCookieString(str);
            if (!TextUtils.isEmpty(cookieString)) {
                hashMap2.put("Cookie", cookieString);
            }
        }
        if (hashMap != null && hashMap.size() > 0) {
            hashMap2.putAll(hashMap);
        }
        httpSetting.setHeaderMap(hashMap2);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.web.DownloadAdapterImpl.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                a.InterfaceC0173a interfaceC0173a2 = interfaceC0173a;
                if (interfaceC0173a2 != null) {
                    interfaceC0173a2.onSusses(httpResponse.getStatusCode(), httpResponse.getHeaderFields(), httpResponse.getSaveFile().getAbsolutePath());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                a.InterfaceC0173a interfaceC0173a2 = interfaceC0173a;
                if (interfaceC0173a2 != null) {
                    interfaceC0173a2.onError(httpError.getResponseCode(), httpError.getHttpResponse().getHeaderFields(), "code is not 200");
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
                a.InterfaceC0173a interfaceC0173a2 = interfaceC0173a;
                if (interfaceC0173a2 == null || i2 <= 0) {
                    return;
                }
                interfaceC0173a2.onProgress(new BigDecimal(i3).multiply(new BigDecimal(100)).divide(new BigDecimal(i2), 0, 4).intValue());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                a.InterfaceC0173a interfaceC0173a2 = interfaceC0173a;
                if (interfaceC0173a2 != null) {
                    interfaceC0173a2.onStart();
                }
            }
        });
        httpSetting.setType(500);
        httpSetting.setIncompatibleWithOkHttp(true);
        httpSetting.setIgnoreCharset(true);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setNoAttempts(true);
        httpSetting.setIgnoreRedirect(true);
        httpSetting.setPriority(500);
        httpSetting.setIsExclusiveTask(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jd.libs.hybrid.adapter.DownloadAdapter
    public void downloadStream(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, final StreamRequest.StreamListener streamListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        if (!TextUtils.isEmpty(str2)) {
            httpSetting.setReferer(str2);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("Connection", "keep-alive");
        hashMap2.put(HttpHeaders.ACCEPT_ENCODING, "gzip");
        if (z) {
            String setting = HybridSDK.getSetting("userAgent");
            if (!TextUtils.isEmpty(setting)) {
                hashMap2.put("User-Agent", setting);
            }
        }
        if (z2) {
            String cookieString = HybridBase.getInstance().getCookieString(str);
            if (!TextUtils.isEmpty(cookieString)) {
                hashMap2.put("Cookie", cookieString);
            }
        }
        if (hashMap != null && hashMap.size() > 0) {
            hashMap2.putAll(hashMap);
        }
        httpSetting.setHeaderMap(hashMap2);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.web.DownloadAdapterImpl.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                StreamRequest.StreamListener streamListener2 = streamListener;
                if (streamListener2 != null) {
                    streamListener2.onConnect(httpResponse.getStatusCode(), httpResponse.getHeaderFields(), httpResponse.getInputStream());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                StreamRequest.StreamListener streamListener2 = streamListener;
                if (streamListener2 != null) {
                    streamListener2.onError(httpError.getResponseCode(), httpError.getHttpResponse().getHeaderFields(), "code is not 200");
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                StreamRequest.StreamListener streamListener2 = streamListener;
                if (streamListener2 != null) {
                    streamListener2.onStart();
                }
            }
        });
        httpSetting.setType(500);
        httpSetting.setPriority(10000);
        httpSetting.setNoAttempts(true);
        httpSetting.setIncompatibleWithOkHttp(true);
        httpSetting.setIgnoreCharset(true);
        httpSetting.setIgnoreRedirect(true);
        httpSetting.setRetrieveInputStreamFlag(true);
        httpSetting.setIsExclusiveTask(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
