package com.jingdong.aura.a.a;

import com.jd.dynamic.DYConstants;
import com.jingdong.aura.core.util.h;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class c {
    private static c b;
    private List<a> a;

    /* loaded from: classes4.dex */
    public static class a {
        public String a;
        public List<String> b;

        public a() {
            if (com.jingdong.aura.a.b.c.f() == 2) {
                this.b = new ArrayList();
            } else {
                this.b = new CopyOnWriteArrayList();
            }
        }
    }

    private c() {
        if (com.jingdong.aura.a.b.c.f() == 2) {
            this.a = new ArrayList();
        } else {
            this.a = new CopyOnWriteArrayList();
        }
    }

    public static synchronized c a() {
        synchronized (c.class) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
            return b;
        }
        return b;
    }

    public a b(String str) {
        if (h.a(str)) {
            return null;
        }
        for (a aVar : this.a) {
            if (!h.a(aVar.a) && aVar.a.equals(str)) {
                return aVar;
            }
        }
        a aVar2 = new a();
        aVar2.a = new String(str);
        this.a.add(aVar2);
        return aVar2;
    }

    public String a(String str) {
        List<a> list = this.a;
        if (list == null || list.size() == 0 || str == null) {
            return null;
        }
        for (a aVar : this.a) {
            Iterator<String> it = aVar.b.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next())) {
                    return aVar.a;
                }
            }
        }
        return null;
    }

    public static void b() {
        List<String> a2 = com.jingdong.aura.a.a.a.c().a();
        if (a2 == null || a2.isEmpty()) {
            return;
        }
        for (String str : a2) {
            long c2 = com.jingdong.aura.a.b.l.h.c(new File(com.jingdong.aura.a.b.k.b.b(), str));
            if (c2 > 0) {
                String str2 = null;
                try {
                    String str3 = File.separator;
                    str2 = String.format("%s%s%s%s%s%d%s%s", com.jingdong.aura.a.b.k.b.b().getAbsolutePath(), str3, str, str3, "package_", Long.valueOf(c2), str3, "manual.ini");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                File file = new File(str2);
                if (file.exists()) {
                    a(str, file, "manualComponents");
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:151:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void a(String str, File file, String str2) {
        Throwable th;
        FileInputStream fileInputStream;
        if (file == null) {
            return;
        }
        String str3 = null;
        str3 = null;
        str3 = null;
        FileInputStream fileInputStream2 = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream2 != null) {
                }
                throw th;
            }
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                str3 = properties.get(str2).toString();
                fileInputStream.close();
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (str3 != null) {
                    return;
                }
                return;
            }
            if (str3 != null || str3.isEmpty()) {
                return;
            }
            String[] split = str3.trim().split(DYConstants.DY_REGEX_COMMA);
            a b2 = a().b(str);
            if (b2 != null) {
                b2.b.clear();
                for (String str4 : split) {
                    b2.b.add(str4);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }
}
