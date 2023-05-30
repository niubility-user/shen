package com.jd.libs.hybrid.offlineload.debug;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.offlineload.utils.CommonFileUtils;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

/* loaded from: classes16.dex */
public class JDHybridDebugHelper {

    /* loaded from: classes16.dex */
    public interface Callback<T> {
        void onResult(T t);
    }

    private JDHybridDebugHelper() {
    }

    private static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    private static String b(File file) {
        StringBuilder sb = new StringBuilder();
        if (!file.isDirectory()) {
            sb.append(file.getAbsolutePath());
            sb.append(" is not a Directory");
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            return sb.toString();
        }
        c(file, 0, sb);
        return sb.toString();
    }

    private static void c(File file, int i2, StringBuilder sb) {
        if (!file.isDirectory()) {
            sb.append(file.getAbsolutePath());
            sb.append(" is not a Directory");
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            return;
        }
        sb.append(a(i2));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("/");
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                c(file2, i2 + 1, sb);
            } else {
                d(file2, i2 + 1, sb);
            }
        }
    }

    private static void d(File file, int i2, StringBuilder sb) {
        sb.append(a(i2));
        sb.append("+--");
        sb.append(file.getName());
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
    }

    public static void getAllBuildInConfig(final Callback<HashMap<String, String>> callback) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.3
            /* JADX WARN: Removed duplicated region for block: B:21:0x004b  */
            /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r4 = this;
                    r0 = 0
                    android.content.Context r1 = com.jd.libs.hybrid.base.HybridSettings.getAppContext()     // Catch: java.lang.Throwable -> L40
                    com.jd.libs.hybrid.offlineload.db.OfflineDatabase r1 = com.jd.libs.hybrid.offlineload.db.OfflineDatabase.getInstance(r1)     // Catch: java.lang.Throwable -> L40
                    com.jd.libs.hybrid.offlineload.db.BuildInOfflineEntityDao r1 = r1.getBuildInDao()     // Catch: java.lang.Throwable -> L40
                    java.util.List r1 = r1.getAll()     // Catch: java.lang.Throwable -> L40
                    if (r1 == 0) goto L47
                    boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L40
                    if (r2 != 0) goto L47
                    java.util.HashMap r2 = new java.util.HashMap     // Catch: java.lang.Throwable -> L40
                    int r3 = r1.size()     // Catch: java.lang.Throwable -> L40
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L40
                    java.util.Iterator r0 = r1.iterator()     // Catch: java.lang.Throwable -> L3e
                L26:
                    boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L3e
                    if (r1 == 0) goto L46
                    java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L3e
                    com.jd.libs.hybrid.offlineload.entity.OfflineEntity r1 = (com.jd.libs.hybrid.offlineload.entity.OfflineEntity) r1     // Catch: java.lang.Throwable -> L3e
                    java.lang.String r3 = r1.getAppid()     // Catch: java.lang.Throwable -> L3e
                    java.lang.String r1 = com.jd.framework.json.JDJSON.toJSONString(r1)     // Catch: java.lang.Throwable -> L3e
                    r2.put(r3, r1)     // Catch: java.lang.Throwable -> L3e
                    goto L26
                L3e:
                    r0 = move-exception
                    goto L43
                L40:
                    r1 = move-exception
                    r2 = r0
                    r0 = r1
                L43:
                    r0.printStackTrace()
                L46:
                    r0 = r2
                L47:
                    com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper$Callback r1 = com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.Callback.this
                    if (r1 == 0) goto L4e
                    r1.onResult(r0)
                L4e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.AnonymousClass3.run():void");
            }
        });
    }

    public static void getAllCommonConfig(final Callback<HashMap<String, String>> callback) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.2
            /* JADX WARN: Removed duplicated region for block: B:21:0x004b  */
            /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r4 = this;
                    r0 = 0
                    android.content.Context r1 = com.jd.libs.hybrid.base.HybridSettings.getAppContext()     // Catch: java.lang.Throwable -> L40
                    com.jd.libs.hybrid.offlineload.db.CommonFileDatabase r1 = com.jd.libs.hybrid.offlineload.db.CommonFileDatabase.getInstance(r1)     // Catch: java.lang.Throwable -> L40
                    com.jd.libs.hybrid.offlineload.db.CommonEntityDao r1 = r1.getDao()     // Catch: java.lang.Throwable -> L40
                    java.util.List r1 = r1.getAll()     // Catch: java.lang.Throwable -> L40
                    if (r1 == 0) goto L47
                    boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L40
                    if (r2 != 0) goto L47
                    java.util.HashMap r2 = new java.util.HashMap     // Catch: java.lang.Throwable -> L40
                    int r3 = r1.size()     // Catch: java.lang.Throwable -> L40
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L40
                    java.util.Iterator r0 = r1.iterator()     // Catch: java.lang.Throwable -> L3e
                L26:
                    boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L3e
                    if (r1 == 0) goto L46
                    java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L3e
                    com.jd.libs.hybrid.offlineload.entity.CommonEntity r1 = (com.jd.libs.hybrid.offlineload.entity.CommonEntity) r1     // Catch: java.lang.Throwable -> L3e
                    java.lang.String r3 = r1.getId()     // Catch: java.lang.Throwable -> L3e
                    java.lang.String r1 = com.jd.framework.json.JDJSON.toJSONString(r1)     // Catch: java.lang.Throwable -> L3e
                    r2.put(r3, r1)     // Catch: java.lang.Throwable -> L3e
                    goto L26
                L3e:
                    r0 = move-exception
                    goto L43
                L40:
                    r1 = move-exception
                    r2 = r0
                    r0 = r1
                L43:
                    r0.printStackTrace()
                L46:
                    r0 = r2
                L47:
                    com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper$Callback r1 = com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.Callback.this
                    if (r1 == 0) goto L4e
                    r1.onResult(r0)
                L4e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.AnonymousClass2.run():void");
            }
        });
    }

    public static void getAllOfflineConfig(final Callback<HashMap<String, String>> callback) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.1
            /* JADX WARN: Removed duplicated region for block: B:21:0x004b  */
            /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r4 = this;
                    r0 = 0
                    android.content.Context r1 = com.jd.libs.hybrid.base.HybridSettings.getAppContext()     // Catch: java.lang.Throwable -> L40
                    com.jd.libs.hybrid.offlineload.db.OfflineDatabase r1 = com.jd.libs.hybrid.offlineload.db.OfflineDatabase.getInstance(r1)     // Catch: java.lang.Throwable -> L40
                    com.jd.libs.hybrid.offlineload.db.OfflineEntityDao r1 = r1.getDao()     // Catch: java.lang.Throwable -> L40
                    java.util.List r1 = r1.getAll()     // Catch: java.lang.Throwable -> L40
                    if (r1 == 0) goto L47
                    boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L40
                    if (r2 != 0) goto L47
                    java.util.HashMap r2 = new java.util.HashMap     // Catch: java.lang.Throwable -> L40
                    int r3 = r1.size()     // Catch: java.lang.Throwable -> L40
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L40
                    java.util.Iterator r0 = r1.iterator()     // Catch: java.lang.Throwable -> L3e
                L26:
                    boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L3e
                    if (r1 == 0) goto L46
                    java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L3e
                    com.jd.libs.hybrid.offlineload.entity.OfflineEntity r1 = (com.jd.libs.hybrid.offlineload.entity.OfflineEntity) r1     // Catch: java.lang.Throwable -> L3e
                    java.lang.String r3 = r1.getAppid()     // Catch: java.lang.Throwable -> L3e
                    java.lang.String r1 = com.jd.framework.json.JDJSON.toJSONString(r1)     // Catch: java.lang.Throwable -> L3e
                    r2.put(r3, r1)     // Catch: java.lang.Throwable -> L3e
                    goto L26
                L3e:
                    r0 = move-exception
                    goto L43
                L40:
                    r1 = move-exception
                    r2 = r0
                    r0 = r1
                L43:
                    r0.printStackTrace()
                L46:
                    r0 = r2
                L47:
                    com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper$Callback r1 = com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.Callback.this
                    if (r1 == 0) goto L4e
                    r1.onResult(r0)
                L4e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.AnonymousClass1.run():void");
            }
        });
    }

    public static String getEntityDirFiles(int i2, final String str) {
        if (TextUtils.isEmpty(str)) {
            return "id is empty";
        }
        String str2 = null;
        if (i2 == 1) {
            str2 = OfflineFileUtils.HYBRID_OFFLINE_FILE_DIR;
        } else if (i2 == 2) {
            str2 = OfflineFileUtils.HYBRID_OFFLINE_BUILDIN_FILE_DIR;
        } else if (i2 == 3) {
            str2 = CommonFileUtils.HYBRID_COMMON_FILE_DIR;
        }
        if (TextUtils.isEmpty(str2)) {
            return "type error";
        }
        String dir = FileUtils.getDir(HybridSettings.getAppContext(), str2);
        if (TextUtils.isEmpty(dir)) {
            return "get path error";
        }
        File file = new File(dir);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.4
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str3) {
                    return str3.startsWith(str) && new File(file2, str3).isDirectory();
                }
            });
            if (listFiles != null && listFiles.length != 0) {
                StringBuilder sb = new StringBuilder();
                for (File file2 : listFiles) {
                    sb.append(file2.getAbsolutePath());
                    sb.append(":\n");
                    sb.append(b(file2));
                }
                return sb.toString();
            }
            return "cannot find folder of id: " + str + ", maybe it is in old path (sdcard).";
        }
        return "new root path " + dir + " is not existed!";
    }

    public static String getEntityZipFile(int i2, final String str) {
        if (TextUtils.isEmpty(str)) {
            return "id is empty";
        }
        String str2 = null;
        if (i2 == 1) {
            str2 = OfflineFileUtils.HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR;
        } else if (i2 == 2) {
            str2 = OfflineFileUtils.HYBRID_OFFLINE_BUILDIN_ZIP_DIR;
        }
        if (TextUtils.isEmpty(str2)) {
            return "type error";
        }
        String dir = FileUtils.getDir(HybridSettings.getAppContext(), str2);
        if (TextUtils.isEmpty(dir)) {
            return "get path error";
        }
        File file = new File(dir);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.5
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str3) {
                    return str3.startsWith(str) && new File(file2, str3).isFile();
                }
            });
            if (listFiles != null && listFiles.length != 0) {
                StringBuilder sb = new StringBuilder();
                for (File file2 : listFiles) {
                    sb.append("found zip file of id(");
                    sb.append(str);
                    sb.append(") in:\n");
                    sb.append(file2.getAbsolutePath());
                }
                return sb.toString();
            }
            return "cannot find zip file of id: " + str + ", maybe it is in old path (sdcard).";
        }
        return "new root path " + dir + " is not existed!";
    }

    public static String getFileRootDirInfo() {
        String dir = FileUtils.getDir(HybridSettings.getAppContext(), OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR);
        return !TextUtils.isEmpty(dir) ? b(new File(dir)) : "get path error";
    }
}
