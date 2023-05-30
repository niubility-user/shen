package com.jd.hybrid.downloader;

import com.google.common.net.HttpHeaders;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jd.hybrid.downloader.p.a;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.common.entity.MobileChannelModel;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class k extends b {
    private com.jd.hybrid.downloader.m.a a;
    private final h b;

    public k(h hVar) {
        this.b = hVar;
    }

    private void a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("success", str);
            jSONObject.put(VerifyTracker.KEY_TIMES, 1);
            jSONObject.put("useDefault", false);
            if (DYConstants.DY_TRUE.equals(str)) {
                jSONObject.put("hostname", com.jd.hybrid.downloader.p.b.f(this.a.url));
            }
            jSONObject.put(HttpHeaders.ReferrerPolicyValues.ORIGIN, this.a.originalUrl);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        a.b bVar = new a.b();
        bVar.a = jSONObject.toString();
        bVar.b = "828";
        bVar.f2708c = MobileChannelModel.TYPE_CHANNEL_IMAGEGROUP;
        com.jd.hybrid.downloader.p.a.a(bVar);
    }

    private void c(File file) {
        try {
            k.a.a.a aVar = new k.a.a.a(file);
            String extractFilePath = getExtractFilePath(file);
            aVar.c(extractFilePath);
            File file2 = new File(extractFilePath);
            if (file2.exists() && file2.isDirectory() && file2.length() > 0) {
                this.a.onDownloaded(extractFilePath);
            } else {
                this.a.status = -1;
                Log.d("XCache", "dest file is unavailable!");
            }
            com.jd.hybrid.downloader.p.b.a(file);
        } catch (IOException e2) {
            this.a.status = -1;
            Log.d("XCache", "unzip throw exception:" + e2.getMessage());
        }
    }

    private String getExtractFilePath(File file) {
        return file.getParent() + File.separator + this.a.id;
    }

    public void b(com.jd.hybrid.downloader.m.a aVar) {
        this.a = aVar;
    }

    @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
    public void onEnd(FileResponse<File> fileResponse) {
        File data = fileResponse.getData();
        a.C0087a c0087a = new a.C0087a();
        c0087a.a = this.a.getId();
        c0087a.b = c.e(data);
        c0087a.f2702g = 4;
        com.jd.hybrid.downloader.m.a aVar = this.a;
        c0087a.f2706k = aVar.version_code;
        c0087a.f2699c = "0";
        c0087a.f2703h = aVar.url;
        c0087a.f2700e = false;
        if (Log.isDebug()) {
            Log.xLogD("XCache", com.jd.hybrid.downloader.p.b.d(System.currentTimeMillis()) + " \u4e0b\u8f7d\u6210\u529f\uff1aurl=" + this.a.url);
        }
        if (com.jd.hybrid.downloader.n.b.d(data, this.a.md5)) {
            c(data);
            if (this.a.status != 1) {
                c0087a.f2701f = "-1";
            }
        } else {
            c0087a.f2701f = "-2";
            this.a.status = -1;
            Log.d("XCache", "MD5 verification failed !");
            com.jd.hybrid.downloader.p.b.a(data);
        }
        this.b.notifyChange(this.a);
        com.jd.hybrid.downloader.p.a.b(c0087a);
        com.jd.hybrid.downloader.m.a aVar2 = this.a;
        if (aVar2.url.equals(aVar2.originalUrl)) {
            return;
        }
        a(DYConstants.DY_TRUE);
    }

    @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
    public void onError(FileError fileError) {
        super.onError(fileError);
        if (Log.isDebug()) {
            Log.xLogD("XCache", com.jd.hybrid.downloader.p.b.d(System.currentTimeMillis()) + " \u4e0b\u8f7d\u5931\u8d25\uff1aurl=" + this.a.url);
        }
        this.b.notifyChange(this.a);
        a.C0087a c0087a = new a.C0087a();
        c0087a.a = this.a.getId();
        c0087a.f2702g = 4;
        com.jd.hybrid.downloader.m.a aVar = this.a;
        c0087a.f2706k = aVar.version_code;
        c0087a.f2699c = "-1";
        c0087a.f2703h = aVar.url;
        c0087a.f2700e = false;
        com.jd.hybrid.downloader.p.a.b(c0087a);
        com.jd.hybrid.downloader.m.a aVar2 = this.a;
        aVar2.status = -1;
        if (!aVar2.url.equals(aVar2.originalUrl)) {
            a(DYConstants.DY_FALSE);
        }
        j.l().t(this.a);
    }
}
