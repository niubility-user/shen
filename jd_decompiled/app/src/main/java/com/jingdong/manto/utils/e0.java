package com.jingdong.manto.utils;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public class e0 {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static final class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) {
            Object obj = d0.a(classLoader, "pathList").get(classLoader);
            Field a = d0.a(obj, "nativeLibraryDirectories");
            File[] fileArr = (File[]) a.get(obj);
            ArrayList arrayList = new ArrayList(fileArr.length + 1);
            arrayList.add(file);
            for (File file2 : fileArr) {
                if (!file.equals(file2)) {
                    arrayList.add(file2);
                }
            }
            a.set(obj, arrayList.toArray(new File[0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static final class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) {
            Object obj = d0.a(classLoader, "pathList").get(classLoader);
            List list = (List) d0.a(obj, "nativeLibraryDirectories").get(obj);
            if (list == null) {
                list = new ArrayList(2);
            }
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (file.equals((File) it.next())) {
                    it.remove();
                    break;
                }
            }
            list.add(0, file);
            List list2 = (List) d0.a(obj, "systemNativeLibraryDirectories").get(obj);
            if (list2 == null) {
                list2 = new ArrayList(2);
            }
            ArrayList arrayList = new ArrayList(list.size() + list2.size() + 1);
            arrayList.addAll(list);
            arrayList.addAll(list2);
            d0.a(obj, "nativeLibraryPathElements").set(obj, (Object[]) d0.a(obj, "makePathElements", List.class, File.class, List.class).invoke(obj, arrayList, null, new ArrayList()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static final class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) {
            Object obj = d0.a(classLoader, "pathList").get(classLoader);
            List list = (List) d0.a(obj, "nativeLibraryDirectories").get(obj);
            if (list == null) {
                list = new ArrayList(2);
            }
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (file.equals((File) it.next())) {
                    it.remove();
                    break;
                }
            }
            list.add(0, file);
            List list2 = (List) d0.a(obj, "systemNativeLibraryDirectories").get(obj);
            if (list2 == null) {
                list2 = new ArrayList(2);
            }
            ArrayList arrayList = new ArrayList(list.size() + list2.size() + 1);
            arrayList.addAll(list);
            arrayList.addAll(list2);
            d0.a(obj, "nativeLibraryPathElements").set(obj, (Object[]) d0.a(obj, "makePathElements", List.class).invoke(obj, arrayList));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static final class d {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) {
            String path = file.getPath();
            Field a = d0.a(classLoader, "libPath");
            String[] split = ((String) a.get(classLoader)).split(":");
            StringBuilder sb = new StringBuilder(path);
            for (String str : split) {
                if (str != null && !path.equals(str)) {
                    sb.append(':');
                    sb.append(str);
                }
            }
            a.set(classLoader, sb.toString());
            Field a2 = d0.a(classLoader, "libraryPathElements");
            List list = (List) a2.get(classLoader);
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (path.equals((String) it.next())) {
                    it.remove();
                    break;
                }
            }
            list.add(0, path);
            a2.set(classLoader, list);
        }
    }

    private static void a(ClassLoader classLoader, File file) {
        if (file == null || !file.exists()) {
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        if ((i2 == 25 && Build.VERSION.PREVIEW_SDK_INT != 0) || i2 > 25) {
            try {
                c.b(classLoader, file);
                return;
            } catch (Throwable unused) {
                b.b(classLoader, file);
                return;
            }
        }
        if (i2 >= 23) {
            try {
                b.b(classLoader, file);
                return;
            } catch (Throwable unused2) {
            }
        } else if (i2 < 14) {
            d.b(classLoader, file);
            return;
        }
        a.b(classLoader, file);
    }

    public static boolean a(Context context, String str) {
        ClassLoader classLoader = context.getClass().getClassLoader();
        if (classLoader == null) {
            return false;
        }
        File file = new File(str);
        file.mkdir();
        try {
            a(classLoader, file);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
