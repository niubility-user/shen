package com.jingdong.manto.q.u;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.q.r;
import com.jingdong.manto.utils.m;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidget;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClientFactory;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.QbSdk;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class d {

    /* renamed from: c  reason: collision with root package name */
    private static String[] f14115c;
    private static Map<String, com.jingdong.manto.q.u.a> d = new ConcurrentHashMap();

    /* renamed from: e  reason: collision with root package name */
    private static Map<String, com.jingdong.manto.q.u.a> f14116e = new ConcurrentHashMap();
    private r a;
    boolean b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements IEmbeddedWidgetClientFactory {
        a() {
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClientFactory
        public IEmbeddedWidgetClient createWidgetClient(String str, Map<String, String> map, IEmbeddedWidget iEmbeddedWidget) {
            if (str != null) {
                str = str.toUpperCase();
            }
            com.jingdong.manto.q.u.a aVar = (com.jingdong.manto.q.u.a) d.f14116e.get(str);
            if (aVar != null) {
                return aVar.a(d.this.a.getContext(), map);
            }
            return null;
        }
    }

    static {
        f fVar = new f();
        d.put(fVar.b, fVar);
        f14116e.put(fVar.f14126c, fVar);
        c cVar = new c();
        d.put(cVar.b, cVar);
        f14116e.put(cVar.f14114c, cVar);
        b bVar = new b();
        d.put(bVar.b, bVar);
        f14116e.put(bVar.f14113c, bVar);
        f14115c = new String[]{fVar.f14126c.toLowerCase(), cVar.f14114c.toLowerCase(), bVar.f14113c.toLowerCase()};
    }

    public d(r rVar) {
        this.b = false;
        this.a = rVar;
        if (!TextUtils.equals("1", m.a("sameLayer", "0"))) {
            this.b = false;
            return;
        }
        if (this.a.getX5WebViewExtension() != null) {
            SharedPreferences.Editor edit = this.a.getContext().getSharedPreferences("tbs_public_settings", 0).edit();
            edit.putInt("MTT_CORE_EMBEDDED_WIDGET_ENABLE", 1);
            edit.apply();
        }
        b();
    }

    private void b() {
        try {
            Object x5WebViewExtension = this.a.getX5WebViewExtension();
            if (x5WebViewExtension == null || !(x5WebViewExtension instanceof IX5WebViewExtension)) {
                return;
            }
            this.b = ((IX5WebViewExtension) x5WebViewExtension).registerEmbeddedWidget(f14115c, new a());
        } catch (Throwable unused) {
            this.b = false;
        }
    }

    public static boolean c() {
        int tbsVersion = QbSdk.getTbsVersion(com.jingdong.a.g());
        return tbsVersion <= 0 || tbsVersion >= 45750;
    }

    public View a(String str, int i2) {
        return d.get(str).a(i2);
    }

    public void a(String str, int i2, View view, int i3, int i4, boolean z) {
        d.get(str).a(i2, view, i3, i4, z);
    }

    public void a(String str, int i2, float[] fArr, int i3) {
        d.get(str).a(i2, fArr, i3);
    }

    public boolean a(String str) {
        return TextUtils.equals("1", m.a("sameLayer", "0")) && this.b && Build.VERSION.SDK_INT >= 20 && this.a.getX5WebViewExtension() != null && d.containsKey(str);
    }

    public void b(String str, int i2) {
        d.get(str).b(i2);
    }
}
