package com.jingdong.manto.l;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.jingdong.manto.l.j;
import com.jingdong.manto.utils.MantoUtils;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes15.dex */
public class m implements j.a {
    final e a;
    final k b;

    /* renamed from: c  reason: collision with root package name */
    final String f13240c;
    private final i d;

    /* renamed from: e  reason: collision with root package name */
    private final l f13241e;

    /* renamed from: f  reason: collision with root package name */
    private final g f13242f;

    /* renamed from: g  reason: collision with root package name */
    private final String f13243g;

    /* renamed from: h  reason: collision with root package name */
    boolean f13244h = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ Bitmap a;

        a(Bitmap bitmap) {
            this.a = bitmap;
        }

        @Override // java.lang.Runnable
        public void run() {
            m.this.a(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ Bitmap a;

        b(Bitmap bitmap) {
            this.a = bitmap;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmap = this.a;
            if (bitmap != null && !bitmap.isRecycled()) {
                m.this.a(this.a);
                return;
            }
            h remove = m.this.b.b().remove(m.this.f());
            if (remove != null) {
                remove.a();
                m.this.b.a().remove(remove);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements Runnable {
        final /* synthetic */ String a;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                m mVar = m.this;
                mVar.f13244h = false;
                mVar.c();
            }
        }

        c(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            OutputStream outputStream;
            BufferedInputStream bufferedInputStream = null;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(m.this.f13240c).openConnection();
                outputStream = m.this.a.a(this.a);
                if (outputStream != null) {
                    try {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpURLConnection.getInputStream());
                        try {
                            byte[] bArr = new byte[16384];
                            while (true) {
                                int read = bufferedInputStream2.read(bArr, 0, 16384);
                                if (read == -1) {
                                    break;
                                }
                                outputStream.write(bArr, 0, read);
                            }
                            outputStream.flush();
                            MantoUtils.qualityClose(outputStream);
                            MantoUtils.qualityClose(bufferedInputStream2);
                        } catch (Throwable unused) {
                            bufferedInputStream = bufferedInputStream2;
                            MantoUtils.qualityClose(outputStream);
                            MantoUtils.qualityClose(bufferedInputStream);
                            m.this.b.c().a(new a());
                        }
                    } catch (Throwable unused2) {
                    }
                }
            } catch (Throwable unused3) {
                outputStream = null;
            }
            m.this.b.c().a(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(String str, i iVar, k kVar, l lVar, e eVar, g gVar, String str2) {
        this.f13240c = str;
        this.d = iVar;
        this.b = kVar;
        this.f13241e = lVar;
        this.a = eVar;
        this.f13242f = gVar;
        this.f13243g = str2;
    }

    private Bitmap a(InputStream inputStream) {
        Bitmap a2;
        try {
            g gVar = this.f13242f;
            if (gVar != null) {
                a2 = gVar.a(inputStream);
            } else {
                a2 = com.jingdong.manto.sdk.b.a(inputStream);
                MantoUtils.qualityClose(inputStream);
            }
            return a2;
        } finally {
            MantoUtils.qualityClose(inputStream);
        }
    }

    private void b(Bitmap bitmap) {
        if (this.d != null && bitmap != null && !bitmap.isRecycled()) {
            Bitmap a2 = this.d.a(bitmap);
            if (a2 != bitmap) {
                this.f13241e.a(bitmap);
            }
            bitmap = a2;
        }
        this.f13241e.a(d(), bitmap);
        com.jingdong.manto.sdk.thread.a.a(new b(bitmap));
    }

    private Bitmap e() {
        InputStream inputStream;
        String str = this.f13240c;
        Bitmap bitmap = null;
        if (str == null || !str.startsWith("file://")) {
            String a2 = n.a(this.f13240c);
            InputStream b2 = this.a.b(a2);
            if (b2 == null) {
                if (this.f13244h) {
                    com.jingdong.manto.sdk.thread.a.b(new c(a2));
                } else {
                    this.b.c().a(a2);
                    this.b.c().b(a2);
                }
            }
            inputStream = b2;
        } else {
            try {
                inputStream = new FileInputStream(this.f13240c.replaceFirst("file://", ""));
            } catch (Throwable th) {
                th.printStackTrace();
                inputStream = null;
            }
        }
        if (inputStream != null) {
            try {
                bitmap = a(inputStream);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            if (bitmap == null || bitmap.isRecycled()) {
                throw new IOException();
            }
        }
        return bitmap;
    }

    @Override // com.jingdong.manto.l.j.a
    public final void a() {
        h remove = this.b.b().remove(f());
        if (remove != null) {
            this.b.a().remove(remove);
        }
    }

    final void a(Bitmap bitmap) {
        h remove = this.b.b().remove(f());
        if (remove != null) {
            remove.b(bitmap);
            this.b.a().remove(remove);
        }
    }

    @Override // com.jingdong.manto.l.j.a
    public final void b() {
        Bitmap a2 = this.f13241e.a(d());
        if (a2 != null && !a2.isRecycled()) {
            com.jingdong.manto.sdk.thread.a.a(new a(a2));
            return;
        }
        j c2 = this.b.c();
        String a3 = n.a(this.f13240c);
        if (TextUtils.isEmpty(a3) || !c2.f13233c.containsKey(a3)) {
            if (!TextUtils.isEmpty(a3)) {
                c2.f13233c.put(a3, Boolean.TRUE);
            }
            c();
        } else if (TextUtils.isEmpty(a3)) {
        } else {
            List<j.a> list = c2.b.get(a3);
            if (list == null) {
                list = new LinkedList<>();
                c2.b.put(a3, list);
            }
            list.add(this);
        }
    }

    final void c() {
        String a2 = n.a(this.f13240c);
        j c2 = this.b.c();
        try {
            Bitmap e2 = e();
            if (e2 == null || e2.isRecycled()) {
                return;
            }
            c2.b(a2);
            c2.a(a2, this);
            b(e2);
            c2.c(a2);
        } catch (Exception unused) {
            c2.b(a2);
            c2.a(a2);
            b(null);
        } catch (Throwable unused2) {
            c2.b(a2);
            c2.a(a2, this);
            c2.c(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String d() {
        return n.a(this.f13240c, this.d, this.f13242f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String f() {
        return n.a(this.f13243g, d());
    }
}
