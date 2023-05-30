package com.getkeepsafe.relinker;

import android.content.Context;
import android.util.Log;
import com.getkeepsafe.relinker.c;
import com.getkeepsafe.relinker.g.i;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes12.dex */
public class d {
    protected final Set<String> a;
    protected final c.b b;

    /* renamed from: c */
    protected final c.a f1047c;
    protected boolean d;

    /* renamed from: e */
    protected boolean f1048e;

    /* renamed from: f */
    protected c.d f1049f;

    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f1050g;

        /* renamed from: h */
        final /* synthetic */ String f1051h;

        /* renamed from: i */
        final /* synthetic */ String f1052i;

        /* renamed from: j */
        final /* synthetic */ c.InterfaceC0027c f1053j;

        a(Context context, String str, String str2, c.InterfaceC0027c interfaceC0027c) {
            d.this = r1;
            this.f1050g = context;
            this.f1051h = str;
            this.f1052i = str2;
            this.f1053j = interfaceC0027c;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                d.this.g(this.f1050g, this.f1051h, this.f1052i);
                this.f1053j.success();
            } catch (com.getkeepsafe.relinker.b e2) {
                this.f1053j.failure(e2);
            } catch (UnsatisfiedLinkError e3) {
                this.f1053j.failure(e3);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements FilenameFilter {

        /* renamed from: g */
        final /* synthetic */ String f1055g;

        b(d dVar, String str) {
            this.f1055g = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith(this.f1055g);
        }
    }

    public d() {
        this(new e(), new com.getkeepsafe.relinker.a());
    }

    public void g(Context context, String str, String str2) {
        i iVar;
        if (this.a.contains(str) && !this.d) {
            i("%s already loaded previously!", str);
            return;
        }
        try {
            this.b.loadLibrary(str);
            this.a.add(str);
            i("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e2) {
            i("Loading the library normally failed: %s", Log.getStackTraceString(e2));
            i("%s (%s) was not loaded normally, re-linking...", str, str2);
            File d = d(context, str, str2);
            if (!d.exists() || this.d) {
                if (this.d) {
                    i("Forcing a re-link of %s (%s)...", str, str2);
                }
                b(context, str, str2);
                this.f1047c.a(context, this.b.supportedAbis(), this.b.mapLibraryName(str), d, this);
            }
            try {
                if (this.f1048e) {
                    i iVar2 = null;
                    try {
                        iVar = new i(d);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        List<String> h2 = iVar.h();
                        iVar.close();
                        Iterator<String> it = h2.iterator();
                        while (it.hasNext()) {
                            e(context, this.b.unmapLibraryName(it.next()));
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        iVar2 = iVar;
                        iVar2.close();
                        throw th;
                    }
                }
            } catch (IOException unused) {
            }
            this.b.loadPath(d.getAbsolutePath());
            this.a.add(str);
            i("%s (%s) was re-linked!", str, str2);
        }
    }

    protected void b(Context context, String str, String str2) {
        File c2 = c(context);
        File d = d(context, str, str2);
        File[] listFiles = c2.listFiles(new b(this, this.b.mapLibraryName(str)));
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            if (this.d || !file.getAbsolutePath().equals(d.getAbsolutePath())) {
                file.delete();
            }
        }
    }

    protected File c(Context context) {
        return context.getDir("lib", 0);
    }

    protected File d(Context context, String str, String str2) {
        String mapLibraryName = this.b.mapLibraryName(str);
        if (f.a(str2)) {
            return new File(c(context), mapLibraryName);
        }
        return new File(c(context), mapLibraryName + OrderISVUtil.MONEY_DECIMAL + str2);
    }

    public void e(Context context, String str) {
        f(context, str, null, null);
    }

    public void f(Context context, String str, String str2, c.InterfaceC0027c interfaceC0027c) {
        if (context != null) {
            if (!f.a(str)) {
                i("Beginning load of %s...", str);
                if (interfaceC0027c == null) {
                    g(context, str, str2);
                    return;
                } else {
                    new Thread(new a(context, str, str2, interfaceC0027c)).start();
                    return;
                }
            }
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        throw new IllegalArgumentException("Given context is null");
    }

    public void h(String str) {
        c.d dVar = this.f1049f;
        if (dVar != null) {
            dVar.log(str);
        }
    }

    public void i(String str, Object... objArr) {
        h(String.format(Locale.US, str, objArr));
    }

    protected d(c.b bVar, c.a aVar) {
        this.a = new HashSet();
        if (bVar == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (aVar != null) {
            this.b = bVar;
            this.f1047c = aVar;
            return;
        }
        throw new IllegalArgumentException("Cannot pass null library installer");
    }
}
