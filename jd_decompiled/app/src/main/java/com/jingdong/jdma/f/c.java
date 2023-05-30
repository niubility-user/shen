package com.jingdong.jdma.f;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdma.common.utils.LogUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    public static boolean d;

    /* renamed from: e  reason: collision with root package name */
    private static c f12725e;
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12726c;

    /* loaded from: classes12.dex */
    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            HashMap hashMap = new HashMap();
            hashMap.put("errorDes", this.a);
            try {
                c.this.a(this.b, hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes12.dex */
    class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ HashMap b;

        b(String str, HashMap hashMap) {
            this.a = str;
            this.b = hashMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                c.this.a(this.a, this.b);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.jdma.f.c$c  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class C0492c implements com.jingdong.jdma.c.a {
        C0492c(c cVar) {
        }

        @Override // com.jingdong.jdma.c.a
        public void a() {
        }

        @Override // com.jingdong.jdma.c.a
        public void a(com.jingdong.jdma.bean.b.b bVar) {
        }

        @Override // com.jingdong.jdma.c.a
        public void a(com.jingdong.jdma.bean.b.c.a aVar) {
        }
    }

    private c() {
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.a = str;
    }

    public static c a() {
        if (f12725e == null) {
            synchronized (c.class) {
                if (f12725e == null) {
                    f12725e = new c();
                }
            }
        }
        return f12725e;
    }

    public void b(String str, HashMap<String, String> hashMap) {
        com.jingdong.jdma.c.d.a().a(1, new b(str, hashMap));
    }

    public void a(String str) {
        this.f12726c = str;
    }

    public void a(String str, String str2) {
        com.jingdong.jdma.c.d.a().a(1, new a(str2, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, HashMap<String, String> hashMap) {
        if (TextUtils.isEmpty(this.a)) {
            return;
        }
        JSONObject a2 = com.jingdong.jdma.bean.a.b().a();
        try {
            a2.put("appStatus", com.jingdong.jdma.common.utils.c.f12679i ? "foreGround" : "backGround");
            a2.put("eventId", str);
            a2.put("appVersion", this.b);
            a2.put("appBuild", this.f12726c);
            if (hashMap != null) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                        a2.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (LogUtil.isDebug()) {
            LogUtil.d("JDMAMonitorLog", "reportInstantly-----v=" + a2.toString());
        }
        String concat = "t=".concat("jdma_sdk_log").concat(ContainerUtils.FIELD_DELIMITER).concat("v=").concat(a2.toString());
        com.jingdong.jdma.c.c cVar = new com.jingdong.jdma.c.c();
        com.jingdong.jdma.bean.b.a aVar = new com.jingdong.jdma.bean.b.a();
        aVar.d(this.a);
        aVar.c("POST");
        aVar.a(concat);
        cVar.a(aVar, new C0492c(this));
    }
}
