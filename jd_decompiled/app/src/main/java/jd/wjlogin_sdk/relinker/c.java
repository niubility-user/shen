package jd.wjlogin_sdk.relinker;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import jd.wjlogin_sdk.relinker.b;
import jd.wjlogin_sdk.relinker.e.f;

/* loaded from: classes.dex */
public class c {

    /* renamed from: g */
    private static final String f19847g = "lib";
    protected final Set<String> a;
    protected final b.InterfaceC0850b b;

    /* renamed from: c */
    protected final b.a f19848c;
    protected boolean d;

    /* renamed from: e */
    protected boolean f19849e;

    /* renamed from: f */
    protected b.d f19850f;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f19851c;
        final /* synthetic */ b.c d;

        a(Context context, String str, String str2, b.c cVar) {
            c.this = r1;
            this.a = context;
            this.b = str;
            this.f19851c = str2;
            this.d = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                c.this.d(this.a, this.b, this.f19851c);
                this.d.a();
            } catch (UnsatisfiedLinkError e2) {
                this.d.a(e2);
            } catch (MissingLibraryException e3) {
                this.d.a(e3);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements FilenameFilter {
        final /* synthetic */ String a;

        b(String str) {
            c.this = r1;
            this.a = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith(this.a);
        }
    }

    public c() {
        this(new d(), new jd.wjlogin_sdk.relinker.a());
    }

    public void d(Context context, String str, String str2) {
        if (this.a.contains(str) && !this.d) {
            a("%s already loaded previously!", str);
            return;
        }
        try {
            this.b.a(str);
            this.a.add(str);
            a("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e2) {
            a("Loading the library normally failed: %s", Log.getStackTraceString(e2));
            a("%s (%s) was not loaded normally, re-linking...", str, str2);
            File b2 = b(context, str, str2);
            if (!b2.exists() || this.d) {
                if (this.d) {
                    a("Forcing a re-link of %s (%s)...", str, str2);
                }
                a(context, str, str2);
                this.f19848c.a(context, this.b.a(), this.b.d(str), b2, this);
            }
            try {
                if (this.f19849e) {
                    Iterator<String> it = new f(b2).b().iterator();
                    while (it.hasNext()) {
                        a(context, this.b.b(it.next()));
                    }
                }
            } catch (IOException unused) {
            }
            this.b.c(b2.getAbsolutePath());
            this.a.add(str);
            a("%s (%s) was re-linked!", str, str2);
        }
    }

    public c b() {
        this.f19849e = true;
        return this;
    }

    public void c(Context context, String str, String str2) {
        a(context, str, str2, (b.c) null);
    }

    protected c(b.InterfaceC0850b interfaceC0850b, b.a aVar) {
        this.a = new HashSet();
        if (interfaceC0850b == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (aVar != null) {
            this.b = interfaceC0850b;
            this.f19848c = aVar;
            return;
        }
        throw new IllegalArgumentException("Cannot pass null library installer");
    }

    public c a(b.d dVar) {
        this.f19850f = dVar;
        return this;
    }

    protected File b(Context context, String str, String str2) {
        String d = this.b.d(str);
        if (TextUtils.isEmpty(str2)) {
            return new File(a(context), d);
        }
        return new File(a(context), d + OrderISVUtil.MONEY_DECIMAL + str2);
    }

    public c a() {
        this.d = true;
        return this;
    }

    public void a(Context context, String str) {
        a(context, str, (String) null, (b.c) null);
    }

    public void a(Context context, String str, b.c cVar) {
        a(context, str, (String) null, cVar);
    }

    public void a(Context context, String str, String str2, b.c cVar) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                a("Beginning load of %s...", str);
                if (cVar == null) {
                    d(context, str, str2);
                    return;
                } else {
                    new Thread(new a(context, str, str2, cVar)).start();
                    return;
                }
            }
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        throw new IllegalArgumentException("Given context is null");
    }

    protected File a(Context context) {
        return context.getDir(f19847g, 0);
    }

    protected void a(Context context, String str, String str2) {
        File a2 = a(context);
        File b2 = b(context, str, str2);
        File[] listFiles = a2.listFiles(new b(this.b.d(str)));
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            if (this.d || !file.getAbsolutePath().equals(b2.getAbsolutePath())) {
                file.delete();
            }
        }
    }

    public void a(String str, Object... objArr) {
        a(String.format(Locale.US, str, objArr));
    }

    public void a(String str) {
        b.d dVar = this.f19850f;
        if (dVar != null) {
            dVar.a(str);
        }
    }
}
