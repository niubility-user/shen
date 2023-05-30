package com.jd.dynamic.b.e.a;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.Pair;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.entity.QueryTemplatesEntity;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.o;
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
public class b {
    private static final HashMap<String, List<Template>> a = new HashMap<>();
    private static final Object b = new Object();

    /* renamed from: c */
    private static HashMap<String, Long> f1732c;
    private static LruCache<String, ResultEntity> d;

    /* renamed from: e */
    private static Object f1733e;

    /* loaded from: classes13.dex */
    public static class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

    static {
        new AtomicBoolean(false);
        f1732c = new HashMap<>();
        d = new LruCache<>(8);
        f1733e = new Object();
    }

    public static Template a(String str, String str2) {
        synchronized (b) {
            List<Template> list = a.get(str);
            if (m.I(list)) {
                for (Template template : list) {
                    if (template != null && template.isNewValid() && !TextUtils.isEmpty(template.bizField) && template.bizField.equals(str2)) {
                        return template;
                    }
                }
            }
            return null;
        }
    }

    public static String b() {
        StringBuilder sb = new StringBuilder();
        sb.append(DynamicSdk.getEngine().getContext().getFilesDir().getAbsolutePath());
        String str = File.separator;
        sb.append(str);
        sb.append(DynamicSdk.getEngine().getCacheDir());
        sb.append(str);
        sb.append("zipcache");
        return sb.toString();
    }

    public static void c(String str, long j2) {
        if (TextUtils.isEmpty(str) || j2 <= 0) {
            return;
        }
        f1732c.put(str, Long.valueOf(j2));
    }

    public static void d(String str, QueryTemplatesEntity queryTemplatesEntity) {
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
                    String o = o(str);
                    m.R(o);
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(o));
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

    public static void e(String str, Template template) {
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

    public static void f(boolean z) {
        j();
        File file = new File(DynamicSdk.getEngine().getContext().getFilesDir().getAbsolutePath() + File.separator + DynamicSdk.getEngine().getCacheDir());
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles(new a());
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

    public static boolean g(String str) {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(o(str)));
            } catch (IOException unused) {
            } catch (ClassNotFoundException unused2) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                QueryTemplatesEntity queryTemplatesEntity = (QueryTemplatesEntity) objectInputStream.readObject();
                if (queryTemplatesEntity != null) {
                    List<Template> data = queryTemplatesEntity.getData();
                    if (m.I(data)) {
                        for (Template template : data) {
                            if (template != null) {
                                template.systemCode = str;
                            }
                        }
                        synchronized (b) {
                            a.put(str, data);
                        }
                        try {
                            objectInputStream.close();
                        } catch (IOException unused3) {
                        }
                        return true;
                    }
                }
                objectInputStream.close();
            } catch (IOException unused4) {
                objectInputStream2 = objectInputStream;
                if (objectInputStream2 == null) {
                    return false;
                }
                objectInputStream2.close();
                return false;
            } catch (ClassNotFoundException unused5) {
                objectInputStream2 = objectInputStream;
                if (objectInputStream2 == null) {
                    return false;
                }
                objectInputStream2.close();
                return false;
            } catch (Throwable th2) {
                th = th2;
                objectInputStream2 = objectInputStream;
                if (objectInputStream2 != null) {
                    try {
                        objectInputStream2.close();
                    } catch (IOException unused6) {
                    }
                }
                throw th;
            }
        } catch (IOException unused7) {
            return false;
        }
    }

    public static Pair<Boolean, String> h(String str, Template template) {
        if (TextUtils.isEmpty(str)) {
            return new Pair<>(Boolean.FALSE, "verify_template_error_path_empty");
        }
        File file = new File(str);
        if (file.exists()) {
            if (template.isDownloadZip() || !file.isFile()) {
                return (template.isDownloadZip() && file.isDirectory()) ? new Pair<>(Boolean.TRUE, "") : new Pair<>(Boolean.FALSE, "");
            } else if (TextUtils.isEmpty(template.fileMd5)) {
                return com.jd.dynamic.b.a.b.o().K() ? new Pair<>(Boolean.FALSE, "verify_template_error_template_md5_null") : new Pair<>(Boolean.TRUE, "");
            } else {
                String c2 = p.c(str);
                if (file.length() < 0) {
                    return new Pair<>(Boolean.FALSE, "verify_template_error_file_length_threshold md5:" + c2);
                } else if (TextUtils.equals(template.fileMd5, c2)) {
                    return new Pair<>(Boolean.TRUE, "");
                } else {
                    return new Pair<>(Boolean.FALSE, "verify_template_error_md5_not_equal md5:" + c2);
                }
            }
        }
        return new Pair<>(Boolean.FALSE, "verify_template_error_file_not_exist");
    }

    public static Template i(String str, String str2) {
        synchronized (b) {
            List<Template> list = a.get(str);
            if (m.I(list)) {
                for (Template template : list) {
                    if (template != null && template.isNewValid() && !TextUtils.isEmpty(template.businessCode) && template.businessCode.equals(str2)) {
                        return template;
                    }
                }
            }
            return null;
        }
    }

    public static void j() {
        synchronized (f1733e) {
            d.evictAll();
        }
    }

    public static void k(String str, QueryTemplatesEntity queryTemplatesEntity) {
        List<Template> data;
        File[] listFiles;
        if (queryTemplatesEntity == null || (data = queryTemplatesEntity.getData()) == null || data.size() <= 0) {
            return;
        }
        for (Template template : data) {
            String p = p(str, template.businessCode);
            if (template.isDownloadZip()) {
                File file = new File(p);
                if (o.p(file) && (listFiles = file.listFiles()) != null && listFiles.length >= 1) {
                    for (File file2 : listFiles) {
                        if (!o.p(file2) || !TextUtils.equals(template.pckVersion, o.k(file2.getAbsolutePath()))) {
                            o.q(file2);
                        }
                    }
                }
            } else {
                String b2 = p.b(template.getDownloadFileName(), template.getDownloadFileName());
                if (!TextUtils.isEmpty(b2)) {
                    File file3 = new File(p, b2);
                    if (file3.exists() && !((Boolean) h(file3.getAbsolutePath(), template).first).booleanValue()) {
                        o.q(file3);
                    }
                }
            }
        }
    }

    public static boolean l(String str) {
        try {
            Long l2 = f1732c.get(str);
            if (!Boolean.valueOf(v.d(str + "-hasSaveFetchFile", false)).booleanValue() || l2 == null || System.currentTimeMillis() >= l2.longValue()) {
                return true;
            }
            File file = new File(m(str));
            if (file.exists() && file.isDirectory()) {
                return file.list().length <= 0;
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public static String m(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(DynamicSdk.getEngine().getContext().getFilesDir().getAbsolutePath());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(DynamicSdk.getEngine().getCacheDir());
        sb.append(str2);
        sb.append(str);
        return sb.toString();
    }

    public static String n(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(DynamicSdk.getEngine().getCacheDir());
        String str3 = File.separator;
        sb.append(str3);
        sb.append(str);
        sb.append(str3);
        sb.append(str2);
        return sb.toString();
    }

    public static String o(String str) {
        return m(str) + File.separator + "queryTemplateJson";
    }

    public static String p(String str, String str2) {
        return m(str) + File.separator + str2;
    }

    public static String q(String str) {
        return b() + File.separator + str;
    }

    public static String r(String str, String str2) {
        return str + ContainerUtils.FIELD_DELIMITER + str2;
    }

    public static boolean s(String str) {
        boolean I;
        synchronized (b) {
            I = m.I(a.get(str));
        }
        return I;
    }

    public static boolean t(String str, String str2) {
        Template a2;
        String dynamicStatus = DynamicSdk.getDynamicStatus();
        if (!TextUtils.equals(dynamicStatus, "0")) {
            com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str);
            if (!TextUtils.equals(g2.b, "0") || g2.b(str2) || g2.e(str2)) {
                try {
                    if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                        return false;
                    }
                    HashMap<String, List<Template>> hashMap = a;
                    if (!hashMap.containsKey(str)) {
                        g(str);
                    }
                    if (!hashMap.containsKey(str) || (a2 = a(str, str2)) == null) {
                        return false;
                    }
                    return ((Boolean) h(new File(p(str, a2.businessCode), p.b(a2.getDownloadUrl(), a2.getDownloadFileName())).getAbsolutePath(), a2).first).booleanValue();
                } catch (Exception e2) {
                    DYConstants.DYLog("exception is : " + e2.getMessage());
                    return false;
                }
            }
        }
        DynamicMtaUtil.uploadDowngradeTemplateMta(str, str2, "", dynamicStatus, null, null);
        return false;
    }

    public static Pair<Boolean, String> u(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return new Pair<>(Boolean.FALSE, "verify_template_error_path_empty");
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            if (TextUtils.isEmpty(str2)) {
                return com.jd.dynamic.b.a.b.o().K() ? new Pair<>(Boolean.FALSE, "verify_template_error_template_md5_null") : new Pair<>(Boolean.TRUE, "");
            }
            String c2 = p.c(str);
            if (file.length() < 0) {
                return new Pair<>(Boolean.FALSE, "verify_template_error_file_length_threshold md5:" + c2);
            } else if (TextUtils.equals(c2, str2)) {
                return new Pair<>(Boolean.TRUE, "");
            } else {
                return new Pair<>(Boolean.FALSE, "verify_template_error_md5_not_equal md5:" + c2);
            }
        }
        return new Pair<>(Boolean.FALSE, "verify_template_error_file_not_exist");
    }

    public static ResultEntity v(String str) {
        return null;
    }

    public static boolean w(String str, String str2) {
        return com.jd.dynamic.b.i.a.e(str, str2);
    }
}
