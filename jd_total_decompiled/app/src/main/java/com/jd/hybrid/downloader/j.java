package com.jd.hybrid.downloader;

import android.text.TextUtils;
import com.jd.hybrid.downloader.p.a;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.xwin.http.BreakPointHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes13.dex */
public final class j implements h {

    /* renamed from: h */
    private static j f2686h;
    private com.jd.hybrid.downloader.o.a b;

    /* renamed from: c */
    private Map<String, i> f2687c;

    /* renamed from: g */
    private BreakPointHelper f2690g;
    private boolean a = false;
    private List<String> d = new ArrayList();

    /* renamed from: e */
    private boolean f2688e = false;

    /* renamed from: f */
    private boolean f2689f = false;

    /* loaded from: classes13.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ long f2691g;

        a(long j2) {
            j.this = r1;
            this.f2691g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(this.f2691g * 1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Log.d("XCache", "start download for 'S' level");
            com.jd.hybrid.downloader.o.a aVar = j.this.b;
            j.this.b.getClass();
            List<com.jd.hybrid.downloader.m.a> d = aVar.d("s_project");
            j.this.f2689f = true;
            if (d != null) {
                j.this.d(d, null);
            }
        }
    }

    private j() {
        f();
    }

    public synchronized void d(List<com.jd.hybrid.downloader.m.a> list, String str) {
        c f2 = c.f();
        if (f2 != null) {
            for (com.jd.hybrid.downloader.m.a aVar : list) {
                if (TextUtils.isEmpty(str) || str.equals(aVar.source)) {
                    try {
                        if (m(aVar)) {
                            aVar.url = aVar.originalUrl;
                            f2.b(g(aVar));
                            Log.d("XCache", "start request for app, id = " + aVar.id);
                        }
                    } catch (Exception e2) {
                        Log.e("XCache", e2);
                    }
                }
            }
        }
    }

    private void e(String str) {
        c f2;
        com.jd.hybrid.downloader.m.a e2 = this.b.e(str);
        e2.url = e2.originalUrl;
        if (e2 == null || !m(e2) || (f2 = c.f()) == null) {
            return;
        }
        f2.b(g(e2));
    }

    private synchronized com.jd.hybrid.downloader.o.a f() {
        if (HybridSettings.getAppContext() != null && this.b == null) {
            this.b = new com.jd.hybrid.downloader.o.a();
            this.f2690g = BreakPointHelper.breakPointSwitch ? BreakPointHelper.getInstance().init(HybridSettings.getAppContext()) : null;
        }
        return this.b;
    }

    private d g(com.jd.hybrid.downloader.m.a aVar) {
        String c2;
        String str = "xcache" + File.separator + aVar.nameSpace;
        BreakPointHelper breakPointHelper = this.f2690g;
        if (breakPointHelper != null) {
            String filePath = breakPointHelper.getFilePath(aVar.id, aVar.url);
            c2 = TextUtils.isEmpty(filePath) ? com.jd.hybrid.downloader.p.b.c(aVar.url) : com.jd.hybrid.downloader.p.b.e(filePath);
        } else {
            c2 = com.jd.hybrid.downloader.p.b.c(aVar.url);
        }
        d dVar = new d("xcache:" + aVar.id, aVar.url, str, c2, false);
        dVar.o(TextUtils.isEmpty(String.valueOf(aVar.project_priority)) ? 0 : aVar.project_priority);
        dVar.n(aVar.id);
        dVar.k(true);
        k kVar = new k(this);
        try {
            kVar.b(aVar.publicClone());
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
        }
        dVar.l(kVar);
        aVar.status = 2;
        Log.d("XCache", "start request for app, id = " + aVar.id);
        return dVar;
    }

    private com.jd.hybrid.downloader.m.b i(String str) {
        com.jd.hybrid.downloader.m.b c2 = this.b.c(str);
        if (c2 != null && (c2.getStatus() == 0 || c2.getStatus() == -1)) {
            e(c2.getId());
        }
        if (Log.isDebug()) {
            if (c2 == null) {
                Log.d("XCache", "getFiles failed");
            } else {
                int status = c2.getStatus();
                if (status == -1) {
                    Log.d("XCache", "download failed, retry download, id = " + c2.getId());
                } else if (status != 0) {
                    Log.d("XCache", "status =" + c2.getStatus());
                } else {
                    Log.d("XCache", "getFiles --> File not downloaded, download now");
                }
            }
        }
        return c2;
    }

    private void j(String str, String str2, String str3) {
        a.c cVar = new a.c();
        cVar.a = str;
        cVar.b = str2;
        cVar.f2709c = str3;
        com.jd.hybrid.downloader.p.a.c(cVar);
    }

    public static j l() {
        if (f2686h == null) {
            synchronized (j.class) {
                if (f2686h == null) {
                    f2686h = new j();
                }
            }
        }
        return f2686h;
    }

    private boolean m(com.jd.hybrid.downloader.m.a aVar) {
        if (aVar.filePath != null && new File(aVar.filePath).exists()) {
            Log.d("XCache", "The app has been downloaded, id " + aVar.getId());
            return false;
        } else if (aVar.status != 2) {
            aVar.filePath = null;
            return true;
        } else {
            return false;
        }
    }

    private void r(long j2) {
        if (this.f2688e) {
            return;
        }
        this.f2688e = true;
        Log.d("XCache", "download delay: " + j2);
        new Thread(new a(j2), "XCache-download-delay").start();
    }

    private void s(String str) {
        if (this.a) {
            com.jd.hybrid.downloader.o.a aVar = this.b;
            aVar.getClass();
            List<com.jd.hybrid.downloader.m.a> d = aVar.d("t_project");
            if (d != null) {
                d(d, str);
            }
        }
    }

    public synchronized boolean h(String str, String str2, i iVar) {
        if (f() == null) {
            return false;
        }
        if (this.a && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            com.jd.hybrid.downloader.m.b i2 = i(str2);
            if (i2 != null && iVar != null) {
                if (i2.getStatus() != 0 && i2.getStatus() != 2) {
                    iVar.update(i2);
                    j(str, str2, i2.getStatus() == 1 ? "0" : "1");
                    return true;
                }
                if (this.f2687c == null) {
                    this.f2687c = new HashMap();
                }
                this.f2687c.put(str2, iVar);
                return true;
            }
            j(str, str2, "1");
            return false;
        }
        j(str, str2, "1");
        return false;
    }

    public com.jd.hybrid.downloader.m.b k(String str, String str2) {
        if (f() == null) {
            return null;
        }
        String str3 = "1";
        if (this.a && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            com.jd.hybrid.downloader.m.b i2 = i(str2);
            if (i2 != null && i2.getStatus() == 1) {
                str3 = "0";
            }
            j(str, str2, str3);
            return i2;
        }
        j(str, str2, "1");
        return null;
    }

    public synchronized void n(String str, String str2, i iVar) {
        if (f() == null) {
            return;
        }
        if (this.a) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (this.f2687c == null) {
                    this.f2687c = new HashMap();
                }
                this.f2687c.put(str2, iVar);
            }
        }
    }

    @Override // com.jd.hybrid.downloader.h
    public synchronized void notifyChange(com.jd.hybrid.downloader.m.b bVar) {
        if (f() == null) {
            return;
        }
        this.b.m((com.jd.hybrid.downloader.m.a) bVar);
        i iVar = null;
        Map<String, i> map = this.f2687c;
        if (map != null && map.containsKey(bVar.getId())) {
            iVar = this.f2687c.remove(bVar.getId());
        }
        if (iVar != null) {
            iVar.update(bVar);
            Log.d("XCache", "Observer not found, id = " + bVar.getId());
        } else {
            Log.d("XCache", "notify download result to App");
        }
        Log.d("XCache", "send download notification, id = " + bVar.getId() + " , status = " + bVar.getStatus());
    }

    public synchronized void o(String str, String str2) {
        if (f() == null) {
            return;
        }
        boolean equals = "1".equals(HybridBase.getInstance().getSetting(HybridSDK.SWITCH_XCACHE));
        this.a = equals;
        if (equals && "switchQuery".equals(str)) {
            Log.d("XCache", "onConfigLoaded:" + str2);
            this.b.j(str, str2);
            s(str);
            r((long) this.b.f2696e);
        }
    }

    public synchronized void p(String str, JSONArray jSONArray) {
        if (f() == null) {
            return;
        }
        boolean equals = "1".equals(HybridBase.getInstance().getSetting(HybridSDK.SWITCH_XCACHE));
        this.a = equals;
        if (equals && !TextUtils.isEmpty(str)) {
            Log.d("XCache", "onConfigLoaded:" + jSONArray);
            this.b.k(str, jSONArray);
            s(str);
            if (this.f2689f) {
                com.jd.hybrid.downloader.o.a aVar = this.b;
                aVar.getClass();
                List<com.jd.hybrid.downloader.m.a> d = aVar.d("s_project");
                if (d != null) {
                    d(d, str);
                }
            }
        }
    }

    public void q(String str) {
        List<com.jd.hybrid.downloader.m.a> d;
        c f2;
        if (f() == null || !this.a || (d = this.b.d(str)) == null || (f2 = c.f()) == null) {
            return;
        }
        for (com.jd.hybrid.downloader.m.a aVar : d) {
            if (m(aVar)) {
                aVar.url = aVar.originalUrl;
                f2.b(g(aVar));
                Log.d("XCache", "start request for app, id = " + aVar.id);
            }
        }
    }

    public void t(com.jd.hybrid.downloader.m.a aVar) {
        c f2;
        if (f() == null || TextUtils.isEmpty(aVar.url)) {
            return;
        }
        String str = aVar.id + CartConstant.KEY_YB_INFO_LINK + aVar.version_code;
        if (this.d.contains(str) || (f2 = c.f()) == null) {
            return;
        }
        aVar.url = com.jd.hybrid.downloader.p.b.i(aVar.url);
        this.d.add(str);
        f2.b(g(aVar));
        if (Log.isDebug()) {
            Log.xLogD("XCache", com.jd.hybrid.downloader.p.b.d(System.currentTimeMillis()) + " \u4e0b\u8f7d\u91cd\u8bd5\uff1aurl=" + aVar.url);
        }
    }
}
