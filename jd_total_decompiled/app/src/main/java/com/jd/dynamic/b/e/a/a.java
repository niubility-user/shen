package com.jd.dynamic.b.e.a;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.Pair;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.entity.QueryTemplatesEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import com.jd.dynamic.lib.utils.v;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes13.dex */
public class a {
    private static final HashMap<String, List<Template>> a = new HashMap<>();
    private static final Object b = new Object();

    /* renamed from: c */
    private static HashMap<String, Long> f1730c;
    private static LruCache<String, ViewNode> d;

    /* renamed from: e */
    private static Object f1731e;

    /* renamed from: com.jd.dynamic.b.e.a.a$a */
    /* loaded from: classes13.dex */
    public static class C0068a implements FileFilter {
        C0068a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

    static {
        new AtomicBoolean(false);
        f1730c = new HashMap<>();
        d = new LruCache<>(8);
        f1731e = new Object();
    }

    public static Template a(String str, String str2) {
        synchronized (b) {
            List<Template> list = a.get(str);
            if (m.I(list)) {
                for (Template template : list) {
                    if (template != null && template.isValid() && !TextUtils.isEmpty(template.bizField) && template.bizField.equals(str2)) {
                        return template;
                    }
                }
            }
            return null;
        }
    }

    public static void b() {
        synchronized (f1731e) {
            d.evictAll();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.jd.dynamic.entity.QueryTemplatesEntity] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.util.List, java.util.Collection, java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:187:0x0067 -> B:201:0x006a). Please submit an issue!!! */
    public static void c(String str) {
        ObjectInputStream objectInputStream = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
            objectInputStream = objectInputStream;
        }
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream(m(str)));
                try {
                    ?? r0 = (QueryTemplatesEntity) objectInputStream2.readObject();
                    ObjectInputStream objectInputStream3 = r0;
                    if (r0 != 0) {
                        ?? data = r0.getData();
                        boolean I = m.I(data);
                        objectInputStream3 = data;
                        if (I) {
                            for (Template template : data) {
                                if (template != null) {
                                    template.systemCode = str;
                                }
                            }
                            synchronized (b) {
                                a.put(str, data);
                            }
                            objectInputStream3 = data;
                        }
                    }
                    objectInputStream2.close();
                    objectInputStream = objectInputStream3;
                } catch (IOException e3) {
                    e = e3;
                    objectInputStream = objectInputStream2;
                    e.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                        objectInputStream = objectInputStream;
                    }
                } catch (ClassNotFoundException e4) {
                    e = e4;
                    objectInputStream = objectInputStream2;
                    e.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                        objectInputStream = objectInputStream;
                    }
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
            } catch (ClassNotFoundException e7) {
                e = e7;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void d(String str, long j2) {
        if (TextUtils.isEmpty(str) || j2 <= 0) {
            return;
        }
        f1730c.put(str, Long.valueOf(j2));
    }

    public static void e(String str, QueryTemplatesEntity queryTemplatesEntity) {
        t.c("CacheManager", "saveTemplates", "systemCode::" + str);
        if (TextUtils.isEmpty(str) || queryTemplatesEntity == null) {
            return;
        }
        List<Template> data = queryTemplatesEntity.getData();
        if (m.I(data)) {
            synchronized (b) {
                a.put(str, data);
            }
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                try {
                    String m2 = m(str);
                    m.R(m2);
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(m2));
                    try {
                        objectOutputStream2.writeObject(queryTemplatesEntity);
                        objectOutputStream2.flush();
                        objectOutputStream2.close();
                    } catch (IOException e2) {
                        e = e2;
                        objectOutputStream = objectOutputStream2;
                        e.printStackTrace();
                        t.e("CacheManager", "saveTemplates()", t.d(e));
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream = objectOutputStream2;
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void f(String str, Template template) {
        if (template == null || TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (b) {
            HashMap<String, List<Template>> hashMap = a;
            List<Template> list = hashMap.get(str);
            Template template2 = null;
            if (m.I(list)) {
                Iterator<Template> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Template next = it.next();
                    if (next != null && !TextUtils.isEmpty(next.businessName) && next.businessName.equals(template.businessName)) {
                        template2 = next;
                        break;
                    }
                }
                if (template2 != null) {
                    list.remove(template2);
                }
                list.add(template);
                a.put(str, list);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(template);
                hashMap.put(str, arrayList);
            }
        }
    }

    public static void g(boolean z) {
        b();
        File file = new File(DynamicSdk.getEngine().getContext().getFilesDir().getAbsolutePath() + File.separator + DynamicSdk.getEngine().getCacheDir());
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles(new C0068a());
            if (m.L(listFiles)) {
                SharedPreferences.Editor edit = v.b().edit();
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    edit.remove(name + "-nextFetchTime");
                    edit.remove(name + "-hasSaveFetchFile");
                }
                edit.commit();
            }
            if (z) {
                m.u(file);
            }
        }
        synchronized (b) {
            a.clear();
        }
    }

    public static Pair<Boolean, String> h(String str, Template template) {
        if (TextUtils.isEmpty(str)) {
            return new Pair<>(Boolean.FALSE, "verify_template_error_path_empty");
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            if (template == null) {
                return new Pair<>(Boolean.FALSE, "verify_template_error_template_null");
            }
            String c2 = p.c(str);
            if (file.length() < 0) {
                return new Pair<>(Boolean.FALSE, "verify_template_error_file_length_threshold md5:" + c2);
            } else if (TextUtils.isEmpty(template.fileMd5)) {
                return com.jd.dynamic.b.a.b.o().K() ? new Pair<>(Boolean.FALSE, "verify_template_error_template_md5_null") : new Pair<>(Boolean.TRUE, "");
            } else if (TextUtils.equals(c2, template.fileMd5)) {
                return new Pair<>(Boolean.TRUE, "");
            } else {
                return new Pair<>(Boolean.FALSE, "verify_template_error_md5_not_equal md5:" + c2);
            }
        }
        return new Pair<>(Boolean.FALSE, "verify_template_error_file_not_exist");
    }

    public static Template i(String str, String str2) {
        synchronized (b) {
            List<Template> list = a.get(str);
            if (m.I(list)) {
                for (Template template : list) {
                    if (template != null && template.isValid() && !TextUtils.isEmpty(template.businessCode) && template.businessCode.equals(str2)) {
                        return template;
                    }
                }
            }
            return null;
        }
    }

    public static boolean j(String str) {
        try {
            Long l2 = f1730c.get(str);
            if (!Boolean.valueOf(v.d(str + "-hasSaveFetchFile", false)).booleanValue() || l2 == null || System.currentTimeMillis() >= l2.longValue()) {
                return true;
            }
            File file = new File(k(str));
            if (file.exists() && file.isDirectory()) {
                return file.list().length <= 0;
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public static String k(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(DynamicSdk.getEngine().getContext().getFilesDir().getAbsolutePath());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(DynamicSdk.getEngine().getCacheDir());
        sb.append(str2);
        sb.append(str);
        return sb.toString();
    }

    public static String l(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(DynamicSdk.getEngine().getCacheDir());
        String str3 = File.separator;
        sb.append(str3);
        sb.append(str);
        sb.append(str3);
        sb.append(str2);
        return sb.toString();
    }

    public static String m(String str) {
        return k(str) + File.separator + "queryTemplateJson";
    }

    public static String n(String str, String str2) {
        return k(str) + File.separator + str2;
    }

    public static String o(String str, String str2) {
        return str + ContainerUtils.FIELD_DELIMITER + str2;
    }

    public static boolean p(String str) {
        boolean I;
        synchronized (b) {
            I = m.I(a.get(str));
        }
        return I;
    }

    public static ViewNode q(String str) {
        return null;
    }

    public static boolean r(String str, String str2) {
        Template a2;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            HashMap<String, List<Template>> hashMap = a;
            if (!hashMap.containsKey(str)) {
                c(str);
            }
            if (hashMap.containsKey(str) && (a2 = a(str, str2)) != null) {
                return ((Boolean) h(new File(n(str, a2.businessCode), p.b(a2.fullFileUrl, a2.fileObjectKey)).getAbsolutePath(), a2).first).booleanValue();
            }
        }
        return false;
    }
}
