package com.jd.hybrid.downloader;

import android.content.Context;
import com.jd.hybrid.downloader.g;
import com.jd.jdcache.util.UrlHelper;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes13.dex */
public class c {

    /* renamed from: c */
    private static c f2667c;
    private Context a;
    private Map<com.jd.hybrid.downloader.d, FileRequest> b;

    /* loaded from: classes13.dex */
    public static class b {
        private Context a;

        public c b() {
            return new c(this.a);
        }

        private b(Context context) {
            this.a = context;
        }
    }

    /* renamed from: com.jd.hybrid.downloader.c$c */
    /* loaded from: classes13.dex */
    public static class C0086c extends FileError {
        public float fileSizeInKB;

        public C0086c(int i2, float f2, String str) {
            super(i2, str);
            this.fileSizeInKB = f2;
        }
    }

    /* loaded from: classes13.dex */
    public class d implements e<File> {
        private final com.jd.hybrid.downloader.d a;
        private final com.jd.hybrid.downloader.b b;

        private void a() {
            c.this.b.remove(this.a);
        }

        @Override // com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            Log.d("DownloadClient", "Download complete " + this.a.i());
            com.jd.hybrid.downloader.n.a c2 = this.a.c();
            if (c2 != null && this.a.j()) {
                File data = fileResponse.getData();
                if (!c2.a(data)) {
                    Log.d("DownloadClient", "File check fail, at: " + data.getAbsolutePath());
                    float e2 = c.e(data);
                    data.delete();
                    onError(new C0086c(fileResponse.getStatusCode(), e2, "\u6587\u4ef6\u6821\u9a8c\u5931\u8d25"));
                    return;
                }
            }
            a();
            com.jd.hybrid.downloader.b bVar = this.b;
            if (bVar != null) {
                bVar.onEnd(fileResponse);
            }
        }

        @Override // com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            Log.e("DownloadClient", "Download error (" + this.a.i() + ") Error: code = " + fileError.getStatusCode() + ", msg = " + fileError.getMessage());
            Log.e("DownloadClient", fileError);
            a();
            com.jd.hybrid.downloader.b bVar = this.b;
            if (bVar != null) {
                bVar.onError(fileError);
            }
        }

        @Override // com.jd.hybrid.downloader.e
        public void onProgress(int i2, int i3) {
            com.jd.hybrid.downloader.b bVar = this.b;
            if (bVar != null) {
                bVar.onProgress(i2, i3);
            }
        }

        @Override // com.jd.hybrid.downloader.e
        public void onStart() {
            Log.d("DownloadClient", "Download start " + this.a.i());
            com.jd.hybrid.downloader.b bVar = this.b;
            if (bVar != null) {
                bVar.onStart();
            }
        }

        private d(FileRequest fileRequest, com.jd.hybrid.downloader.d dVar) {
            c.this = r1;
            this.a = dVar;
            this.b = dVar.b();
        }
    }

    private void d(FileRequest fileRequest, int i2) {
        if (fileRequest != null) {
            g.h(this.a).e(new g.c(fileRequest, i2));
        }
    }

    public static float e(File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    return ((float) file.length()) / 1024.0f;
                }
            } catch (Exception e2) {
                Log.e("DownloadClient", e2);
            }
        }
        return 0.0f;
    }

    public static c f() {
        if (f2667c == null) {
            Log.e("DownloadClient", "Hybrid SDK is not initialized!");
        }
        return f2667c;
    }

    public static void g(b bVar) {
        synchronized (com.jd.hybrid.downloader.d.class) {
            if (f2667c == null) {
                f2667c = bVar.b();
            }
        }
    }

    public static b h(Context context) {
        return new b(context);
    }

    public FileRequest b(com.jd.hybrid.downloader.d dVar) {
        if (this.b.containsKey(dVar)) {
            Log.d("DownloadClient", "Existed download request, priority = " + dVar.f() + ", url: " + dVar.i());
            return this.b.get(dVar);
        }
        try {
            FileRequest fileRequest = new FileRequest(UrlHelper.METHOD_HEAD.equals(dVar.h()) ? 261 : 257, dVar.i());
            fileRequest.setReferer("X-" + dVar.e());
            fileRequest.setResponseListener(new d(fileRequest, dVar));
            fileRequest.setSavePath(f.b(HybridSettings.getAppContext(), true, dVar.g(), dVar.d()).getPath());
            fileRequest.fileName = dVar.d();
            fileRequest.relativeDirPath = dVar.g();
            this.b.put(dVar, fileRequest);
            d(fileRequest, dVar.f());
            Log.d("DownloadClient", "Add to download queue, priority = " + dVar.f() + ", url: " + dVar.i());
            return fileRequest;
        } catch (Exception unused) {
            if (Log.isDebug()) {
                Log.xLogD("XCache", "addDownloader: request is null");
            }
            return null;
        }
    }

    public List<FileRequest> c(List<com.jd.hybrid.downloader.d> list, boolean z) {
        if (list.size() > 1 && !z) {
            Collections.sort(list);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<com.jd.hybrid.downloader.d> it = list.iterator();
        while (it.hasNext()) {
            FileRequest b2 = b(it.next());
            if (b2 != null) {
                arrayList.add(b2);
            }
        }
        return arrayList;
    }

    private c(Context context) {
        this.b = new ConcurrentHashMap();
        this.a = context.getApplicationContext();
    }
}
