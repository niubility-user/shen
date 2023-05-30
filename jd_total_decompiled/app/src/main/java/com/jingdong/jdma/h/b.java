package com.jingdong.jdma.h;

import android.text.TextUtils;
import com.jingdong.jdma.common.utils.LogUtil;
import java.net.URLEncoder;

/* loaded from: classes12.dex */
public abstract class b implements Runnable {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12757c;
    private String d;

    /* loaded from: classes12.dex */
    class a extends com.jingdong.jdma.c.e {
        a() {
        }

        @Override // com.jingdong.jdma.c.a
        public void a(com.jingdong.jdma.bean.b.b bVar) {
            b.this.a(bVar.a());
        }

        @Override // com.jingdong.jdma.c.a
        public void a(com.jingdong.jdma.bean.b.c.a aVar) {
            b.this.b();
        }
    }

    public b(String str, String str2, String str3, String str4) {
        this.f12757c = "0.0";
        this.d = "";
        this.a = str;
        this.b = str2;
        this.f12757c = str3;
        this.d = str4;
    }

    public abstract void a(String str);

    public abstract void b();

    @Override // java.lang.Runnable
    public void run() {
        String str;
        try {
            if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
                com.jingdong.jdma.c.c cVar = new com.jingdong.jdma.c.c();
                StringBuilder sb = new StringBuilder();
                sb.append("item=");
                sb.append(URLEncoder.encode(this.b, "utf-8"));
                sb.append("&v=");
                sb.append(URLEncoder.encode(this.f12757c, "utf-8"));
                sb.append("&key=");
                if (TextUtils.isEmpty(this.d)) {
                    str = com.jingdong.jdsdk.a.a.a;
                } else {
                    str = "a_" + this.d;
                }
                sb.append(str);
                LogUtil.w("---\u7b56\u7565\u8bf7\u6c42url=" + this.a + ",body=" + sb.toString());
                com.jingdong.jdma.bean.b.a aVar = new com.jingdong.jdma.bean.b.a();
                aVar.d(this.a);
                aVar.c("POST");
                aVar.a(sb.toString());
                cVar.a(aVar, new a());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
