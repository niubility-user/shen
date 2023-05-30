package g.j.b.c;

import android.content.Context;
import android.text.TextUtils;
import com.coremedia.iso.boxes.PerformerBox;
import com.xiaomi.push.t0;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class d implements b {
    protected Context a;
    private HashMap<String, HashMap<String, g.j.b.a.d>> b;

    public d(Context context) {
        this.a = context;
    }

    public static String d(g.j.b.a.d dVar) {
        return String.valueOf(dVar.a) + "#" + dVar.b;
    }

    private String g(g.j.b.a.d dVar) {
        String str;
        int i2 = dVar.a;
        String str2 = dVar.b;
        if (i2 <= 0 || TextUtils.isEmpty(str2)) {
            str = "";
        } else {
            str = String.valueOf(i2) + "#" + str2;
        }
        File externalFilesDir = this.a.getExternalFilesDir(PerformerBox.TYPE);
        if (externalFilesDir == null) {
            g.j.a.a.a.c.D("cannot get folder when to write perf");
            return null;
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        return new File(externalFilesDir, str).getAbsolutePath();
    }

    private String h(g.j.b.a.d dVar) {
        String g2 = g(dVar);
        if (TextUtils.isEmpty(g2)) {
            return null;
        }
        for (int i2 = 0; i2 < 20; i2++) {
            String str = g2 + i2;
            if (t0.d(this.a, str)) {
                return str;
            }
        }
        return null;
    }

    @Override // g.j.b.c.e
    public void a() {
        t0.c(this.a, PerformerBox.TYPE, "perfUploading");
        File[] f2 = t0.f(this.a, "perfUploading");
        if (f2 == null || f2.length <= 0) {
            return;
        }
        for (File file : f2) {
            if (file != null) {
                List<String> e2 = g.e(this.a, file.getAbsolutePath());
                file.delete();
                e(e2);
            }
        }
    }

    @Override // g.j.b.c.f
    public void b() {
        HashMap<String, HashMap<String, g.j.b.a.d>> hashMap = this.b;
        if (hashMap == null) {
            return;
        }
        if (hashMap.size() > 0) {
            Iterator<String> it = this.b.keySet().iterator();
            while (it.hasNext()) {
                HashMap<String, g.j.b.a.d> hashMap2 = this.b.get(it.next());
                if (hashMap2 != null && hashMap2.size() > 0) {
                    g.j.b.a.d[] dVarArr = new g.j.b.a.d[hashMap2.size()];
                    hashMap2.values().toArray(dVarArr);
                    f(dVarArr);
                }
            }
        }
        this.b.clear();
    }

    @Override // g.j.b.c.b
    public void b(HashMap<String, HashMap<String, g.j.b.a.d>> hashMap) {
        this.b = hashMap;
    }

    @Override // g.j.b.c.f
    public void c(g.j.b.a.d dVar) {
        if ((dVar instanceof g.j.b.a.c) && this.b != null) {
            g.j.b.a.c cVar = (g.j.b.a.c) dVar;
            String d = d(cVar);
            String c2 = g.c(cVar);
            HashMap<String, g.j.b.a.d> hashMap = this.b.get(d);
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            g.j.b.a.c cVar2 = (g.j.b.a.c) hashMap.get(c2);
            if (cVar2 != null) {
                cVar.f19675i += cVar2.f19675i;
                cVar.f19676j += cVar2.f19676j;
            }
            hashMap.put(c2, cVar);
            this.b.put(d, hashMap);
        }
    }

    public void e(List<String> list) {
        throw null;
    }

    public void f(g.j.b.a.d[] dVarArr) {
        String h2 = h(dVarArr[0]);
        if (TextUtils.isEmpty(h2)) {
            return;
        }
        g.g(h2, dVarArr);
    }
}
