package com.jingdong.manto.l;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes15.dex */
public class k {
    private Map<h, String> a;
    private Map<String, h> b;

    /* renamed from: c */
    private Map<Integer, String> f13234c;
    private j d;

    /* renamed from: e */
    private l f13235e;

    /* renamed from: f */
    private e f13236f;

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ h b;

        /* renamed from: c */
        final /* synthetic */ m f13237c;

        /* renamed from: com.jingdong.manto.l.k$a$a */
        /* loaded from: classes15.dex */
        class RunnableC0536a implements Runnable {
            RunnableC0536a() {
                a.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f13237c.b();
            }
        }

        a(String str, h hVar, m mVar) {
            k.this = r1;
            this.a = str;
            this.b = hVar;
            this.f13237c = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap a = k.this.a(this.a);
            if (a != null) {
                this.b.b(a);
                return;
            }
            String f2 = this.f13237c.f();
            k.this.a.put(this.b, f2);
            k.this.b.put(f2, this.b);
            this.b.onStart();
            this.f13237c.b.d.a(new RunnableC0536a());
        }
    }

    /* loaded from: classes15.dex */
    public class b extends com.jingdong.manto.l.c {

        /* renamed from: f */
        final /* synthetic */ Drawable f13238f;

        /* renamed from: g */
        final /* synthetic */ h f13239g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(k kVar, ImageView imageView, k kVar2, String str, Drawable drawable, h hVar) {
            super(imageView, kVar2, str);
            this.f13238f = drawable;
            this.f13239g = hVar;
        }

        @Override // com.jingdong.manto.l.c, com.jingdong.manto.l.h
        public void b(Bitmap bitmap) {
            super.b(bitmap);
            h hVar = this.f13239g;
            if (hVar != null) {
                hVar.b(bitmap);
            }
        }

        @Override // com.jingdong.manto.l.c, com.jingdong.manto.l.h
        public final void onStart() {
            super.onStart();
            if (c() != null && this.f13238f != null) {
                c().setImageDrawable(this.f13238f);
            }
            h hVar = this.f13239g;
            if (hVar != null) {
                hVar.onStart();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public static final class c {
        static final k a = new k(null);
    }

    private k() {
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.f13234c = new ConcurrentHashMap();
        this.f13235e = new d();
        this.f13236f = new com.jingdong.manto.l.b();
        this.d = new j(new MantoHandler(new com.jingdong.manto.sdk.thread.a("SimpleImageHandlerThread").b.getLooper()));
    }

    /* synthetic */ k(a aVar) {
        this();
    }

    private String a(ImageView imageView, String str, Drawable drawable, i iVar, g gVar, h hVar, String str2) {
        String str3;
        h hVar2;
        if (imageView == null) {
            return null;
        }
        if (imageView != null && (str3 = this.f13234c.get(Integer.valueOf(imageView.hashCode()))) != null && (hVar2 = this.b.get(str3)) != null) {
            String str4 = this.a.get(hVar2);
            if (!TextUtils.isEmpty(str4)) {
                this.b.remove(str4);
            }
        }
        if (TextUtils.isEmpty(str)) {
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
            return null;
        }
        b bVar = new b(this, imageView, this, str2, drawable, hVar);
        String a2 = a(bVar, str, iVar, gVar);
        if (bVar.d) {
            return a2;
        }
        this.f13234c.put(Integer.valueOf(imageView.hashCode()), n.a(bVar.a, a2));
        return a2;
    }

    private String a(h hVar, String str, i iVar, g gVar) {
        if (hVar != null) {
            if (!TextUtils.isEmpty(str)) {
                m mVar = new m(str, iVar, this, this.f13235e, this.f13236f, gVar, hVar.b());
                String d = mVar.d();
                a aVar = new a(str, hVar, mVar);
                if (com.jingdong.manto.sdk.thread.a.b()) {
                    aVar.run();
                    return d;
                }
                com.jingdong.manto.sdk.thread.a.a(aVar);
                return d;
            }
            hVar.a();
        }
        return null;
    }

    public static k d() {
        return c.a;
    }

    public final Bitmap a(String str) {
        Bitmap a2 = this.f13235e.a(str);
        if (a2 == null || a2.isRecycled()) {
            return null;
        }
        return a2;
    }

    public final Bitmap a(String str, g gVar) {
        FileInputStream fileInputStream;
        Bitmap a2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String a3 = n.a(str, null, gVar);
        Bitmap a4 = this.f13235e.a(a3);
        if (a4 != null) {
            return a4;
        }
        try {
            if (str.startsWith("file://")) {
                try {
                    fileInputStream = new FileInputStream(str.replaceFirst("file://", ""));
                } catch (FileNotFoundException unused) {
                    MantoUtils.qualityClose(null);
                    return null;
                }
            } else {
                fileInputStream = null;
            }
            try {
                InputStream b2 = this.f13236f.b(n.a(str));
                if (gVar != null) {
                    try {
                        a2 = gVar.a(b2);
                    } catch (Exception unused2) {
                        MantoUtils.qualityClose(b2);
                        return null;
                    }
                } else {
                    a2 = com.jingdong.manto.sdk.b.a(b2);
                }
                if (a2 != null) {
                    this.f13235e.a(a3, a2);
                }
                MantoUtils.qualityClose(b2);
                return a2;
            } catch (Throwable unused3) {
                MantoUtils.qualityClose(fileInputStream);
                return null;
            }
        } catch (Throwable unused4) {
            fileInputStream = null;
        }
    }

    public String a(ImageView imageView, String str, Drawable drawable, i iVar, h hVar, String str2) {
        return a(imageView, str, drawable, iVar, null, hVar, str2);
    }

    public Map<h, String> a() {
        return this.a;
    }

    public void a(int i2) {
        Map<Integer, String> map = this.f13234c;
        if (map != null) {
            map.remove(Integer.valueOf(i2));
        }
    }

    public Map<String, h> b() {
        return this.b;
    }

    public j c() {
        return this.d;
    }
}
