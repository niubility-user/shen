package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import androidx.annotation.MainThread;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.tools.json.JsonUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class e6 {

    /* renamed from: l */
    private static final String f16437l = "stData";
    private int a;
    private List<v6> b;

    /* renamed from: c */
    private String f16438c;
    private String d;

    /* renamed from: e */
    private String f16439e;

    /* renamed from: f */
    private String f16440f;

    /* renamed from: g */
    private File f16441g;

    /* renamed from: h */
    private File f16442h;

    /* renamed from: i */
    private volatile boolean f16443i;

    /* renamed from: j */
    private boolean f16444j;

    /* renamed from: k */
    private h f16445k;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ e a;

        public a(e eVar) {
            e6.this = r1;
            this.a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            g gVar = g.READ;
            i.a(gVar).a(f.START).a(e6.this.f16441g).a(e6.this.f16445k);
            if (!e6.this.f16441g.exists()) {
                i.a(gVar).a(f.END).a(e6.this.f16441g).a(false).a(e6.this.f16445k);
                e eVar = this.a;
                if (eVar != null) {
                    eVar.a(null);
                    return;
                }
                return;
            }
            i.a(gVar).a(f.PROCESSING).a(e6.this.f16441g).a(e6.this.f16445k);
            byte[] h2 = fa.h(e6.this.f16441g);
            i.a(gVar).a(f.END).a(e6.this.f16441g).a(h2 != null).a(e6.this.f16445k);
            e eVar2 = this.a;
            if (eVar2 != null) {
                eVar2.a(h2);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements e<byte[]> {

        /* loaded from: classes9.dex */
        public class a implements e<Boolean> {
            public a() {
                b.this = r1;
            }

            @Override // com.tencent.mapsdk.internal.e6.e
            public void a(Boolean bool) {
                e6.this.a(bool.booleanValue());
            }
        }

        public b() {
            e6.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.e6.e
        public void a(byte[] bArr) {
            if (bArr == null || bArr.length <= 0) {
                return;
            }
            e6.this.a(bArr, new a());
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public final /* synthetic */ byte[] a;
        public final /* synthetic */ e b;

        public c(byte[] bArr, e eVar) {
            e6.this = r1;
            this.a = bArr;
            this.b = eVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:44:0x00e6  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 281
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.e6.c.run():void");
        }
    }

    /* loaded from: classes9.dex */
    public class d implements e<Boolean> {
        public d() {
            e6.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.e6.e
        public void a(Boolean bool) {
            e6.this.a(bool.booleanValue());
            if (bool.booleanValue()) {
                e6.this.b.clear();
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface e<T> {
        void a(T t);
    }

    /* loaded from: classes9.dex */
    public enum f {
        START,
        PROCESSING,
        END,
        CANCEL
    }

    /* loaded from: classes9.dex */
    public enum g {
        CREATE,
        READ,
        UPLOAD,
        UPLOAD_END,
        WRITE,
        TRANSLATE_BYTE
    }

    /* loaded from: classes9.dex */
    public interface h {
        void a(i iVar);
    }

    /* loaded from: classes9.dex */
    public static class i {
        private g a;
        private f b;

        /* renamed from: c */
        private i f16453c;
        private boolean d;

        /* renamed from: e */
        private byte[] f16454e;

        /* renamed from: f */
        private Object f16455f;

        public static i a(g gVar) {
            i iVar = new i();
            iVar.a = gVar;
            return iVar;
        }

        public i a(f fVar) {
            this.b = fVar;
            return this;
        }

        public i a(h hVar) {
            if (hVar != null) {
                hVar.a(this);
            }
            return this;
        }

        public i a(i iVar) {
            this.f16453c = iVar;
            return this;
        }

        public i a(Object obj) {
            this.f16455f = obj;
            return this;
        }

        public i a(boolean z) {
            this.d = z;
            return this;
        }

        public i a(byte[] bArr) {
            this.f16454e = bArr;
            return this;
        }

        public boolean a(g gVar, f fVar) {
            i iVar = this.f16453c;
            if (iVar != null) {
                return iVar.b(gVar, fVar);
            }
            return false;
        }

        public byte[] a() {
            return this.f16454e;
        }

        public f b() {
            return this.b;
        }

        public boolean b(g gVar) {
            i iVar = this.f16453c;
            if (iVar != null) {
                return iVar.c(gVar);
            }
            return false;
        }

        public boolean b(g gVar, f fVar) {
            return fVar == this.b && gVar == this.a;
        }

        public g c() {
            return this.a;
        }

        public boolean c(g gVar) {
            return gVar == this.a;
        }

        public Object d() {
            return this.f16455f;
        }

        public boolean e() {
            return this.d;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("StatisticState{");
            stringBuffer.append("mState=");
            stringBuffer.append(this.a);
            stringBuffer.append(", mStage=");
            stringBuffer.append(this.b);
            stringBuffer.append(", mParentState=");
            stringBuffer.append(this.f16453c);
            stringBuffer.append(", mResult=");
            stringBuffer.append(this.d);
            stringBuffer.append(", mData=");
            if (this.f16454e == null) {
                stringBuffer.append(DYConstants.DY_NULL_STR);
            } else {
                stringBuffer.append('[');
                int i2 = 0;
                while (i2 < this.f16454e.length) {
                    stringBuffer.append(i2 == 0 ? "" : ", ");
                    stringBuffer.append((int) this.f16454e[i2]);
                    i2++;
                }
                stringBuffer.append(']');
            }
            stringBuffer.append(", mTag=");
            stringBuffer.append(this.f16455f);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    public e6(o1 o1Var) {
        this(o1Var, true);
    }

    public e6(o1 o1Var, boolean z) {
        this.f16444j = z;
        this.b = new ArrayList();
        this.d = o1Var.q().g();
        this.f16439e = "";
        this.f16440f = "";
        if (o1Var.r() != null) {
            this.f16439e = o1Var.r().getSubKey();
            this.f16440f = o1Var.r().getSubId();
        }
        String str = o1Var.getContext().getFilesDir().getAbsolutePath() + File.separator + f16437l + CartConstant.KEY_YB_INFO_LINK + va.a(this.d);
        this.f16438c = str;
        fa.a(str);
        this.f16441g = new File(this.f16438c);
        this.f16442h = new File(this.f16438c + DYConstants.TEMP_NAME_PREFIX);
    }

    private List<v6> a(File file) {
        ArrayList arrayList = new ArrayList();
        List<String> g2 = fa.g(file);
        if (g2 != null && !g2.isEmpty()) {
            Iterator<String> it = g2.iterator();
            while (it.hasNext()) {
                List list = null;
                try {
                    list = JsonUtils.parseToList(new JSONArray(it.next()), v6.class, new Object[0]);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (list != null) {
                    arrayList.addAll(list);
                }
            }
        }
        return arrayList;
    }

    private void a(e<byte[]> eVar) {
        a(new a(eVar));
    }

    private void a(Runnable runnable) {
        if (this.f16443i) {
            return;
        }
        if (this.f16444j) {
            new Thread(runnable).start();
        } else {
            runnable.run();
        }
    }

    public void a(boolean z) {
        g gVar = g.UPLOAD_END;
        i a2 = i.a(gVar);
        f fVar = f.START;
        a2.a(fVar).a(this.f16445k);
        byte[] h2 = fa.h(this.f16442h);
        i a3 = i.a(gVar).a(f.PROCESSING).a(z).a(h2).a(this.f16442h).a(this.f16445k);
        if (z) {
            fa.d(this.f16441g);
            if (h2 != null && h2.length > 0) {
                fa.b(this.f16442h, this.f16441g);
            }
        } else if (h2 != null && h2.length > 0) {
            ArrayList arrayList = new ArrayList();
            List<v6> a4 = a(this.f16441g);
            List<v6> a5 = a(this.f16442h);
            arrayList.addAll(a4);
            arrayList.addAll(a5);
            fa.d(this.f16441g);
            fa.d(this.f16442h);
            byte[] a6 = a(arrayList, a3);
            g gVar2 = g.WRITE;
            i.a(gVar2).a(fVar).a(a3).a(a6).a(this.f16441g).a(this.f16445k);
            i.a(gVar2).a(f.END).a(a3).a(fa.b(this.f16441g, a6)).a(this.f16445k);
        }
        i.a(gVar).a(f.END).a(this.f16445k);
    }

    public void a(byte[] bArr, e<Boolean> eVar) {
        a(new c(bArr, eVar));
    }

    private byte[] a(List<v6> list, i iVar) {
        i a2;
        g gVar = g.TRANSLATE_BYTE;
        i.a(gVar).a(f.START).a(iVar).a(this.f16445k);
        byte[] bArr = null;
        boolean z = false;
        if (list == null || list.isEmpty()) {
            a2 = i.a(gVar).a(f.END).a(iVar);
        } else {
            i.a(gVar).a(f.PROCESSING).a(list).a(iVar).a(this.f16445k);
            String collectionToJson = JsonUtils.collectionToJson(new ArrayList(list));
            if (!TextUtils.isEmpty(collectionToJson)) {
                try {
                    bArr = collectionToJson.getBytes("UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    bArr = collectionToJson.getBytes();
                }
            }
            a2 = i.a(g.TRANSLATE_BYTE).a(f.END).a(bArr).a(collectionToJson).a(iVar);
            if (bArr != null) {
                z = true;
            }
        }
        a2.a(z).a(this.f16445k);
        return bArr;
    }

    private void d() {
        a(new b());
    }

    public v6 a() {
        return a(System.currentTimeMillis());
    }

    public v6 a(long j2) {
        i.a(g.CREATE).a(Long.valueOf(j2)).a(this.f16445k);
        return new v6(j2);
    }

    public void a(h hVar) {
        this.f16445k = hVar;
    }

    @MainThread
    public void a(v6 v6Var) {
        this.a--;
        if (v6Var != null) {
            synchronized (this) {
                this.b.add(v6Var);
            }
        }
        if (this.a == 0 && !this.b.isEmpty()) {
            a(a(this.b, (i) null), new d());
        }
    }

    public String b() {
        return this.f16438c;
    }

    @MainThread
    public void c() {
        if (this.a == 0) {
            d();
        }
        this.a++;
    }
}
