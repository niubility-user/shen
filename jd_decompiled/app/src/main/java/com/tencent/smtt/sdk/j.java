package com.tencent.smtt.sdk;

import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerClient;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings;
import com.tencent.smtt.sdk.SystemWebViewClient;

/* loaded from: classes9.dex */
public class j extends ServiceWorkerController {
    @Override // com.tencent.smtt.sdk.ServiceWorkerController
    public ServiceWorkerWebSettings getServiceWorkerWebSettings() {
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        android.webkit.ServiceWorkerController.getInstance().getServiceWorkerWebSettings();
        return new ServiceWorkerWebSettings
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: RETURN 
              (wrap: com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings : 0x0012: CONSTRUCTOR 
              (r2v0 'this' com.tencent.smtt.sdk.j A[IMMUTABLE_TYPE, THIS])
              (r0 I:android.webkit.ServiceWorkerWebSettings A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.tencent.smtt.sdk.j, android.webkit.ServiceWorkerWebSettings):void (m), WRAPPED] call: com.tencent.smtt.sdk.j.1.<init>(com.tencent.smtt.sdk.j, android.webkit.ServiceWorkerWebSettings):void type: CONSTRUCTOR)
             in method: com.tencent.smtt.sdk.j.getServiceWorkerWebSettings():com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings, file: classes9.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 24
            if (r0 >= r1) goto L8
            r0 = 0
            return r0
        L8:
            android.webkit.ServiceWorkerController r0 = android.webkit.ServiceWorkerController.getInstance()
            android.webkit.ServiceWorkerWebSettings r0 = r0.getServiceWorkerWebSettings()
            com.tencent.smtt.sdk.j$1 r1 = new com.tencent.smtt.sdk.j$1
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.getServiceWorkerWebSettings():com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings");
    }

    @Override // com.tencent.smtt.sdk.ServiceWorkerController
    public void setServiceWorkerClient(final ServiceWorkerClient serviceWorkerClient) {
        if (Build.VERSION.SDK_INT >= 24) {
            android.webkit.ServiceWorkerController.getInstance().setServiceWorkerClient(new android.webkit.ServiceWorkerClient() { // from class: com.tencent.smtt.sdk.j.2
                {
                    j.this = this;
                }

                @Override // android.webkit.ServiceWorkerClient
                public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
                    com.tencent.smtt.export.external.interfaces.WebResourceResponse shouldInterceptRequest = serviceWorkerClient.shouldInterceptRequest(new SystemWebViewClient.e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), webResourceRequest.isRedirect(), webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
                    if (shouldInterceptRequest == null) {
                        return null;
                    }
                    WebResourceResponse webResourceResponse = new WebResourceResponse(shouldInterceptRequest.getMimeType(), shouldInterceptRequest.getEncoding(), shouldInterceptRequest.getData());
                    webResourceResponse.setResponseHeaders(shouldInterceptRequest.getResponseHeaders());
                    int statusCode = shouldInterceptRequest.getStatusCode();
                    String reasonPhrase = shouldInterceptRequest.getReasonPhrase();
                    if (statusCode != webResourceResponse.getStatusCode() || (reasonPhrase != null && !reasonPhrase.equals(webResourceResponse.getReasonPhrase()))) {
                        webResourceResponse.setStatusCodeAndReasonPhrase(statusCode, reasonPhrase);
                    }
                    return webResourceResponse;
                }
            });
        }
    }
}
