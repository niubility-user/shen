package com.jingdong.manto.widget.input;

import android.text.TextUtils;
import com.jingdong.manto.m.z0.g;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.input.z.c;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class k {
    public static final k b = new k();
    public final Map<Integer, com.jingdong.manto.widget.input.c> a = new HashMap();

    /* loaded from: classes16.dex */
    public static class a implements n.c0 {
        final int a;
        final k b;

        /* renamed from: com.jingdong.manto.widget.input.k$a$a */
        /* loaded from: classes16.dex */
        class RunnableC0700a implements Runnable {
            RunnableC0700a() {
                a.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                aVar.b.a.remove(Integer.valueOf(aVar.a));
            }
        }

        public a(k kVar, int i2) {
            this.b = kVar;
            this.a = i2;
        }

        @Override // com.jingdong.manto.q.n.c0
        public final void onDestroy() {
            MantoUtils.runOnUiThread(new RunnableC0700a());
        }
    }

    /* loaded from: classes16.dex */
    public static class b implements com.jingdong.manto.widget.input.z.c {
        final WeakReference a;
        final int b;

        /* renamed from: c */
        final String f14472c;
        com.jingdong.manto.widget.input.c d;

        public b(k kVar, WeakReference weakReference, com.jingdong.manto.widget.input.c cVar, int i2, String str) {
            this.a = weakReference;
            this.b = i2;
            this.f14472c = str;
            this.d = cVar;
        }

        @Override // com.jingdong.manto.widget.input.z.c
        public final void a(String str, int i2, c.a aVar) {
            try {
                com.jingdong.manto.q.n nVar = (com.jingdong.manto.q.n) this.a.get();
                if (nVar != null) {
                    JSONObject put = new JSONObject().put("value", str).put("inputId", this.b).put("cursor", i2).put("keyCode", (int) ((com.jingdong.manto.widget.input.z.d) this.d.g()).getLastKeyPressed());
                    if (c.a.CHANGED.equals(aVar)) {
                        g.h hVar = new g.h();
                        hVar.a(nVar.h(), nVar.hashCode());
                        hVar.f13315c = put.put("data", this.f14472c).toString();
                        hVar.a();
                        return;
                    }
                    String str2 = null;
                    if (aVar.equals(c.a.COMPLETE)) {
                        str2 = "onKeyboardComplete";
                    } else if (aVar.equals(c.a.CONFIRM)) {
                        str2 = "onKeyboardConfirm";
                    }
                    if (TextUtils.isEmpty(str2)) {
                        return;
                    }
                    nVar.a(str2, put.toString(), 0);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes16.dex */
    public static class c implements com.jingdong.manto.widget.input.z.h {
        final WeakReference<com.jingdong.manto.q.n> a;
        final int b;

        public c(k kVar, WeakReference<com.jingdong.manto.q.n> weakReference, int i2) {
            this.a = weakReference;
            this.b = i2;
        }

        @Override // com.jingdong.manto.widget.input.z.h
        public final void a(int i2) {
            MantoLog.e("InputJsApiHandler", "onReceiveHeight: =====>" + i2);
            try {
                com.jingdong.manto.q.n nVar = this.a.get();
                if (nVar != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("inputId", this.b);
                    jSONObject.put("height", MantoDensityUtils.pixel2dip(i2));
                    nVar.a("onKeyboardShow", jSONObject.toString(), 0);
                }
            } catch (Exception unused) {
            }
        }
    }

    private k() {
    }
}
