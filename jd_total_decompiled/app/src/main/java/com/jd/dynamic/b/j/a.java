package com.jd.dynamic.b.j;

import android.content.Context;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.lib.expv2.c.i;
import com.jd.dynamic.lib.expv2.h;
import com.jd.dynamic.lib.utils.o;
import com.jd.dynamic.lib.utils.p;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class a {
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f1772c;
    private static final int d;
    private final Object a = new Object();
    private final HashMap<Integer, String> b = new HashMap<>();

    /* renamed from: com.jd.dynamic.b.j.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static final class C0070a extends com.jd.dynamic.b.j.c {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ File f1773j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f1774k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f1775l;

        C0070a(File file, a aVar, String str, String str2) {
            this.f1773j = file;
            this.f1774k = str;
            this.f1775l = str2;
        }

        @Override // com.jd.dynamic.b.g.c
        public void execute() {
            f fVar = f.b;
            String str = this.f1774k;
            String str2 = this.f1775l;
            String o = o.o(this.f1773j);
            Intrinsics.checkExpressionValueIsNotNull(o, "ZipUtils.readTxtByRead(it)");
            fVar.a(str, str2, o);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b extends com.jd.dynamic.b.j.c {

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f1777k;

        b(String str) {
            this.f1777k = str;
        }

        @Override // com.jd.dynamic.b.g.c
        public void execute() {
            i j2 = a.this.j(this.f1777k);
            if (j2 != null) {
                DYConstants.DYLog("xpj22 to items exp is " + this.f1777k + " node is " + j2);
                e.f1789l.d(this.f1777k, j2);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c extends com.jd.dynamic.b.j.c {

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f1779k;

        c(String str) {
            this.f1779k = str;
        }

        @Override // com.jd.dynamic.b.g.c
        public void execute() {
            i j2 = a.this.j(this.f1779k);
            if (j2 != null) {
                DYConstants.DYLog("xpj22 to normal exp is " + this.f1779k + " node is " + j2);
                e.f1789l.a(this.f1779k, j2);
            }
        }
    }

    /* loaded from: classes13.dex */
    public final class d {
        @Nullable
        private d a;
        private boolean b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f1780c;

        @Nullable
        public final d a() {
            return this.a;
        }

        public final void b(@Nullable d dVar) {
            this.a = dVar;
        }

        public final void c(boolean z) {
            this.b = z;
        }

        public final void d(boolean z) {
            this.f1780c = z;
        }

        public final boolean e() {
            return this.b;
        }

        public final boolean f() {
            return this.f1780c;
        }
    }

    /* loaded from: classes13.dex */
    public final class e {
        private static final ReentrantReadWriteLock a;
        private static final ReentrantReadWriteLock.ReadLock b;

        /* renamed from: c  reason: collision with root package name */
        private static final ReentrantReadWriteLock.WriteLock f1781c;
        private static final ReentrantReadWriteLock d;

        /* renamed from: e  reason: collision with root package name */
        private static final ReentrantReadWriteLock.ReadLock f1782e;

        /* renamed from: f  reason: collision with root package name */
        private static final ReentrantReadWriteLock.WriteLock f1783f;

        /* renamed from: g  reason: collision with root package name */
        private static final HashMap<String, i> f1784g;

        /* renamed from: h  reason: collision with root package name */
        private static final HashMap<String, i> f1785h;

        /* renamed from: i  reason: collision with root package name */
        private static final ReentrantReadWriteLock.ReadLock f1786i;

        /* renamed from: j  reason: collision with root package name */
        private static final ReentrantReadWriteLock.WriteLock f1787j;

        /* renamed from: k  reason: collision with root package name */
        private static final ArrayList<String> f1788k;

        /* renamed from: l  reason: collision with root package name */
        public static final e f1789l = new e();

        static {
            ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
            a = reentrantReadWriteLock;
            b = reentrantReadWriteLock.readLock();
            f1781c = reentrantReadWriteLock.writeLock();
            ReentrantReadWriteLock reentrantReadWriteLock2 = new ReentrantReadWriteLock();
            d = reentrantReadWriteLock2;
            f1782e = reentrantReadWriteLock2.readLock();
            f1783f = reentrantReadWriteLock2.writeLock();
            f1784g = new HashMap<>();
            f1785h = new HashMap<>();
            f1786i = reentrantReadWriteLock2.readLock();
            f1787j = reentrantReadWriteLock2.writeLock();
            f1788k = new ArrayList<>();
        }

        private e() {
        }

        public final void a(@NotNull String str, @NotNull i iVar) {
            HashMap<String, i> hashMap = f1784g;
            if (hashMap.containsKey(str)) {
                return;
            }
            try {
                ReentrantReadWriteLock.WriteLock writeLock = f1781c;
                writeLock.lock();
                hashMap.put(str, iVar);
                writeLock.unlock();
            } catch (Throwable th) {
                f1781c.unlock();
                throw th;
            }
        }

        public final void b(@NotNull String str, @NotNull String str2) {
            try {
                ReentrantReadWriteLock.ReadLock readLock = f1786i;
                readLock.lock();
                f1788k.add(str + str2);
                readLock.unlock();
            } catch (Throwable th) {
                f1786i.unlock();
                throw th;
            }
        }

        public final boolean c(@NotNull String str) {
            return f1785h.containsKey(str);
        }

        public final void d(@NotNull String str, @NotNull i iVar) {
            HashMap<String, i> hashMap = f1785h;
            if (hashMap.containsKey(str)) {
                return;
            }
            try {
                ReentrantReadWriteLock.WriteLock writeLock = f1783f;
                writeLock.lock();
                hashMap.put(str, iVar);
                writeLock.unlock();
            } catch (Throwable th) {
                f1783f.unlock();
                throw th;
            }
        }

        public final boolean e(@NotNull String str) {
            return f1784g.containsKey(str);
        }

        public final boolean f(@NotNull String str, @NotNull String str2) {
            boolean z;
            try {
                f1787j.lock();
                Iterator<T> it = f1788k.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (Intrinsics.areEqual((String) it.next(), str + str2)) {
                        z = true;
                        break;
                    }
                }
                return z;
            } finally {
                f1787j.unlock();
            }
        }

        @Nullable
        public final i g(@NotNull String str) {
            try {
                ReentrantReadWriteLock.ReadLock readLock = b;
                readLock.lock();
                i iVar = f1784g.get(str);
                i h2 = iVar != null ? iVar.h() : null;
                readLock.unlock();
                return h2;
            } catch (Throwable th) {
                b.unlock();
                throw th;
            }
        }

        @Nullable
        public final i h(@NotNull String str) {
            try {
                ReentrantReadWriteLock.ReadLock readLock = f1782e;
                readLock.lock();
                i iVar = f1785h.get(str);
                i h2 = iVar != null ? iVar.h() : null;
                readLock.unlock();
                return h2;
            } catch (Throwable th) {
                f1782e.unlock();
                throw th;
            }
        }
    }

    /* loaded from: classes13.dex */
    public final class f {
        public static final f b = new f();
        private static HashMap<String, String> a = new HashMap<>();

        private f() {
        }

        public final void a(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            if (a.containsKey(str + str2)) {
                return;
            }
            a.put(str + str2, str3);
        }

        public final boolean b(@NotNull String str, @NotNull String str2) {
            return a.containsKey(str + str2);
        }
    }

    static {
        String[] strArr = {ViewProps.FLEX_DIRECTION, ViewProps.FLEX_WRAP, ViewProps.JUSTIFY_CONTENT, ViewProps.ALIGN_ITEMS, ViewProps.ALIGN_CONTENT, "marginLeft", "marginRight", "marginTop", "marginBottom", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "width", "height", ViewProps.FLEX_GROW, ViewProps.FLEX_SHRINK, ViewProps.ALIGN_SELF, "flexBasisPercent", ViewProps.MIN_WIDTH, "maxWidth", ViewProps.MIN_HEIGHT, "maxHeight", "layoutId", "alpha", "visibility", DYConstants.DY_BG_COLOR, "borderWidth", "borderColor", "borderRadius", DYConstants.DY_TEXT_SIZE, DYConstants.DY_TEXT_COLOR, "text", DYConstants.DY_TEXT_MAXLINES, DYConstants.DY_TEXT_ELLIPSIZE, DYConstants.DY_TEXT_STYLE, DYConstants.DY_GRAVITY, "scaleType", "src", "row", DYConstants.DY_ROW_REVERSE, "column", DYConstants.DY_COLUMN_REVERSE, "nowrap", "wrap", "wrap_reverse", DYConstants.DY_FLEX_START, DYConstants.DY_FLEX_END, DYConstants.DY_CENTER, "space_between", "space_around", DYConstants.DY_BASE_LINE, DYConstants.DY_STRETCH, DYConstants.DY_MATCH_PARENT, DYConstants.DY_WRAP_CONTENT, "auto", "none", "start", DYConstants.DY_MIDDLE, "end", "bold", "normal", "left", "right", DYConstants.DY_FIT, DYConstants.DY_FILL};
        f1772c = strArr;
        d = strArr.length;
    }

    private final d b(com.jd.dynamic.lib.dynamic.parser.f fVar, d dVar) {
        d a;
        d dVar2 = new d();
        if (dVar != null) {
            dVar2.b(dVar);
        }
        try {
            if (Intrinsics.areEqual(DYConstants.DY_ITEMS, c(fVar.f())) || ((a = dVar2.a()) != null && true == a.e())) {
                dVar2.c(true);
            }
            int f2 = fVar.f();
            int f3 = fVar.f();
            if (f2 > 0) {
                for (int i2 = 0; i2 < f2; i2++) {
                    fVar.f();
                    String c2 = c(fVar.f());
                    if (c2 != null && DynamicUtils.isElOrKnownSymbolNoFun(c2) != 0) {
                        if (dVar2.e()) {
                            g(c2);
                        } else {
                            i(c2);
                        }
                    }
                }
            }
            if (f3 > 0) {
                for (int i3 = 0; i3 < f3; i3++) {
                    b(fVar, dVar2);
                }
            }
            return dVar2;
        } catch (Exception unused) {
            dVar2.d(true);
            return dVar2;
        }
    }

    private final String c(int i2) {
        if (i2 < 0) {
            return null;
        }
        return i2 < d ? f1772c[i2] : this.b.get(Integer.valueOf(i2));
    }

    private final void d(com.jd.dynamic.lib.dynamic.parser.f fVar, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int f2 = fVar.f();
            String d2 = fVar.d(fVar.e());
            if (d2 != null) {
                this.b.put(Integer.valueOf(f2), d2);
            }
        }
    }

    private final void e(File file, String str, String str2) {
        if (!file.isDirectory()) {
            if (file.exists()) {
                f(new FileInputStream(file), str, str2);
                return;
            }
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            if ((!(listFiles.length == 0)) == true) {
                for (File file2 : listFiles) {
                    if (o.n(file2) && !f.b.b(str, str2)) {
                        com.jd.dynamic.b.g.e a = com.jd.dynamic.b.g.e.a();
                        Intrinsics.checkExpressionValueIsNotNull(a, "DYThreadManager.getInstance()");
                        a.c().b(new C0070a(file2, this, str, str2));
                    }
                    if (o.l(file2)) {
                        f(new FileInputStream(file2), str, str2);
                    }
                }
            }
        }
    }

    private final void f(InputStream inputStream, String str, String str2) {
        int available;
        byte[] bArr;
        String str3;
        if (inputStream == null) {
            str3 = "xpj22 stream is null";
        } else if (!e.f1789l.f(str, str2)) {
            com.jd.dynamic.lib.dynamic.parser.f fVar = new com.jd.dynamic.lib.dynamic.parser.f();
            try {
                try {
                    try {
                        available = inputStream.available();
                        bArr = new byte[available];
                    } catch (Throwable th) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    DYConstants.DYLog("xpj22  ignore is : " + e3.getMessage());
                    inputStream.close();
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            if (inputStream.read(bArr, 0, available) <= 0) {
                try {
                    inputStream.close();
                    return;
                } catch (IOException e5) {
                    e5.printStackTrace();
                    return;
                }
            }
            fVar.b(bArr);
            inputStream.close();
            int f2 = fVar.f();
            int f3 = fVar.f();
            if (f2 < 8) {
                return;
            }
            if (f3 > 0) {
                fVar.c(f2);
                synchronized (this.a) {
                    d(fVar, f3);
                    Unit unit = Unit.INSTANCE;
                }
            }
            fVar.c(8);
            if (b(fVar, null).f()) {
                return;
            }
            e.f1789l.b(str, str2);
            return;
        } else {
            str3 = "xpj22 this sys " + str + " biz " + str2 + " has parsed.";
        }
        DYConstants.DYLog(str3);
    }

    private final void g(String str) {
        if (e.f1789l.c(str)) {
            return;
        }
        com.jd.dynamic.b.g.e a = com.jd.dynamic.b.g.e.a();
        Intrinsics.checkExpressionValueIsNotNull(a, "DYThreadManager.getInstance()");
        a.c().b(new b(str));
    }

    private final void i(String str) {
        if (e.f1789l.e(str)) {
            return;
        }
        com.jd.dynamic.b.g.e a = com.jd.dynamic.b.g.e.a();
        Intrinsics.checkExpressionValueIsNotNull(a, "DYThreadManager.getInstance()");
        a.c().b(new c(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final i j(String str) {
        return new h().e(str);
    }

    public final void h(@NotNull String str, @NotNull String str2) {
        Template a = com.jd.dynamic.b.e.a.b.a(str, str2);
        if (a != null) {
            File file = new File(com.jd.dynamic.b.e.a.b.m(str) + File.separator + a.businessCode, p.b(a.getDownloadUrl(), a.getDownloadFileName()));
            if (file.exists()) {
                e(file, str, str2);
                return;
            }
        }
        Object g2 = com.jd.dynamic.b.i.a.b.g(str, str2);
        if (g2 != null) {
            if (!(g2 instanceof String)) {
                if (g2 instanceof File) {
                    e((File) g2, str, str2);
                    return;
                }
                return;
            }
            DynamicSdk.Engine engine = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
            Context context = engine.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
            f(context.getAssets().open((String) g2), str, str2);
        }
    }
}
