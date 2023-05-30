package com.jd.hybrid.downloader;

import android.content.Context;
import android.os.Process;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.xwin.http.a;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes13.dex */
public class a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.hybrid.downloader.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public class C0085a implements a.InterfaceC0173a {
        final /* synthetic */ FileRequest a;
        final /* synthetic */ e b;

        C0085a(a aVar, FileRequest fileRequest, e eVar) {
            this.a = fileRequest;
            this.b = eVar;
        }

        @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
        public void onError(int i2, Map<String, List<String>> map, String str) {
            this.b.onError(new FileError(i2, str));
        }

        @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
        public void onProgress(int i2) {
            this.b.onProgress(100, i2);
        }

        @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
        public void onStart() {
            if (Log.isDebug()) {
                Log.xLogD("XCache", com.jd.hybrid.downloader.p.b.d(System.currentTimeMillis()) + " \u5f00\u59cb\u4e0b\u8f7d\uff1aurl=" + this.a.getUrl());
            }
            this.b.onStart();
        }

        @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
        public void onSusses(int i2, Map<String, List<String>> map, String str) {
            FileResponse fileResponse = new FileResponse(i2);
            HashMap hashMap = null;
            if (map != null) {
                try {
                    if (!map.isEmpty()) {
                        hashMap = new HashMap(map.size());
                        for (String str2 : map.keySet()) {
                            List<String> list = map.get(str2);
                            if (list != null && !list.isEmpty()) {
                                StringBuilder sb = new StringBuilder(list.get(0));
                                for (int i3 = 1; i3 < list.size(); i3++) {
                                    sb.append(DYConstants.DY_REGEX_COMMA);
                                    sb.append(list.get(i3));
                                }
                                hashMap.put(str2, sb.toString());
                            }
                        }
                    }
                } catch (Exception e2) {
                    Log.e("BaseNoRetryDownloader", e2);
                    onError(i2, map, e2.getMessage());
                    return;
                }
            }
            fileResponse.setHeaders(hashMap);
            fileResponse.setData(new File(str));
            this.b.onEnd(fileResponse);
        }
    }

    public static void a(Context context, FileRequest fileRequest) {
        new a().b(context, fileRequest);
    }

    public void b(Context context, FileRequest fileRequest) {
        Process.setThreadPriority(19);
        C0085a c0085a = new C0085a(this, fileRequest, fileRequest.getResponseListener());
        if ("1".equals(HybridBase.getInstance().getSetting(HybridBase.SWITCH_DOWNLOAD_ADAPTER))) {
            HybridBase.getInstance().downloadFile(fileRequest.getUrl(), fileRequest.getReferer(), null, false, false, fileRequest.relativeDirPath, fileRequest.fileName, c0085a);
            return;
        }
        com.jd.libs.xwin.http.a aVar = new com.jd.libs.xwin.http.a(fileRequest.getUrl());
        aVar.setReferer(fileRequest.getReferer());
        aVar.c(fileRequest.getSavePath());
        aVar.setMethod(fileRequest.getMethod());
        aVar.b(c0085a);
        aVar.run();
    }
}
