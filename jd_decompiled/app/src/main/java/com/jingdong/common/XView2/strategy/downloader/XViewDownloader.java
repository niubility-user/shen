package com.jingdong.common.XView2.strategy.downloader;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.jd.hybrid.downloader.FileRequest;
import com.jd.libs.xwin.http.a;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class XViewDownloader {
    private static final String TAG = "XViewDownloader";

    /* loaded from: classes5.dex */
    private static class NoRetryDownloadResponse {
        private final long contentLength;
        private final Map<String, String> headerParams;
        private final InputStream inputStream;

        public NoRetryDownloadResponse(InputStream inputStream, long j2, Map<String, List<String>> map) {
            this.inputStream = inputStream;
            this.contentLength = j2;
            this.headerParams = filterHeadersMap(map);
        }

        private Map<String, String> filterHeadersMap(Map<String, List<String>> map) {
            if (map == null || map.isEmpty()) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (String str : map.keySet()) {
                if (!TextUtils.isEmpty(str) && map.get(str) != null) {
                    hashMap.put(str, map.get(str).get(0));
                }
            }
            return hashMap;
        }
    }

    public static void executeAction(Context context, FileRequest fileRequest) {
        new XViewDownloader().performRequest(context, fileRequest);
    }

    public void performRequest(Context context, final FileRequest fileRequest) {
        Process.setThreadPriority(19);
        a aVar = new a(fileRequest.getUrl());
        aVar.setAllowRedirect(true);
        aVar.setReferer(fileRequest.getReferer());
        aVar.c(fileRequest.getSavePath());
        fileRequest.getResponseListener();
        aVar.b(new a.InterfaceC0173a
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0029: INVOKE 
              (r3v2 'aVar' com.jd.libs.xwin.http.a)
              (wrap: com.jd.libs.xwin.http.a$a : 0x0026: CONSTRUCTOR 
              (r2v0 'this' com.jingdong.common.XView2.strategy.downloader.XViewDownloader A[IMMUTABLE_TYPE, THIS])
              (r4v0 'fileRequest' com.jd.hybrid.downloader.FileRequest A[DONT_INLINE])
              (r0 I:com.jd.hybrid.downloader.e A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.XView2.strategy.downloader.XViewDownloader, com.jd.hybrid.downloader.FileRequest, com.jd.hybrid.downloader.e):void (m), WRAPPED] (LINE:7) call: com.jingdong.common.XView2.strategy.downloader.XViewDownloader.1.<init>(com.jingdong.common.XView2.strategy.downloader.XViewDownloader, com.jd.hybrid.downloader.FileRequest, com.jd.hybrid.downloader.e):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jd.libs.xwin.http.a.b(com.jd.libs.xwin.http.a$a):void A[MD:(com.jd.libs.xwin.http.a$a):void (m)] (LINE:7) in method: com.jingdong.common.XView2.strategy.downloader.XViewDownloader.performRequest(android.content.Context, com.jd.hybrid.downloader.FileRequest):void, file: classes5.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            r3 = 19
            android.os.Process.setThreadPriority(r3)
            com.jd.libs.xwin.http.a r3 = new com.jd.libs.xwin.http.a
            java.lang.String r0 = r4.getUrl()
            r3.<init>(r0)
            r0 = 1
            r3.setAllowRedirect(r0)
            java.lang.String r0 = r4.getReferer()
            r3.setReferer(r0)
            java.lang.String r0 = r4.getSavePath()
            r3.c(r0)
            com.jd.hybrid.downloader.e r0 = r4.getResponseListener()
            com.jingdong.common.XView2.strategy.downloader.XViewDownloader$1 r1 = new com.jingdong.common.XView2.strategy.downloader.XViewDownloader$1
            r1.<init>()
            r3.b(r1)
            r3.run()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.strategy.downloader.XViewDownloader.performRequest(android.content.Context, com.jd.hybrid.downloader.FileRequest):void");
    }
}
