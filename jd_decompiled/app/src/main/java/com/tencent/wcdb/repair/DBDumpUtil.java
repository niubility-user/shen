package com.tencent.wcdb.repair;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class DBDumpUtil {
    private static final String TAG = "WCDB.DBDumpUtil";

    /* loaded from: classes9.dex */
    public interface ExecuteSqlCallback {
        String preExecute(String str);
    }

    public static String buildColumnsString(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            sb.append(arrayList.get(i2));
            if (i2 != arrayList.size() - 1) {
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
        }
        sb.append(")");
        String sb2 = sb.toString();
        System.out.println(sb2);
        return sb2;
    }

    public static boolean doRecoveryDb(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        return doRecoveryDb(sQLiteDatabase, str, str2, str3, null, null, null, true);
    }

    public static ArrayList<String> getColumnNamesFromSql(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] split = str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")).trim().split(DYConstants.DY_REGEX_COMMA);
        for (int i2 = 0; i2 < split.length; i2++) {
            split[i2] = split[i2].trim();
            arrayList.add(split[i2].substring(0, split[i2].indexOf(LangUtils.SINGLE_SPACE)));
        }
        return arrayList;
    }

    public static String getTableNameFromSql(String str) {
        if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        String[] split = str.split("\\s");
        if (split == null || split.length <= 1) {
            return null;
        }
        return split[2].replaceAll("\"", "");
    }

    private static native boolean nativeDumpDB(String str, String str2, String str3);

    private static native boolean nativeIsSqlComplete(String str);

    /* JADX WARN: Removed duplicated region for block: B:44:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] readFromFile(java.lang.String r9) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r9)
            boolean r1 = r0.exists()
            r2 = 0
            r3 = 1
            java.lang.String r4 = "WCDB.DBDumpUtil"
            r5 = 0
            if (r1 != 0) goto L1a
            java.lang.Object[] r0 = new java.lang.Object[r3]
            r0[r2] = r9
            java.lang.String r9 = "readFromFile error, file is not exit, path = %s"
            com.tencent.wcdb.support.Log.w(r4, r9, r0)
            return r5
        L1a:
            long r6 = r0.length()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            int r1 = (int) r6     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            byte[] r0 = new byte[r1]     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            int r7 = r6.read(r0)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            if (r7 == r1) goto L4c
            java.lang.String r0 = "readFromFile error, size is not equal, path = %s, file length is %d, count is %d"
            r8 = 3
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            r8[r2] = r9     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            r8[r3] = r9     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            r9 = 2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            r8[r9] = r1     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            com.tencent.wcdb.support.Log.w(r4, r0, r8)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> L6e
            r6.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r9 = move-exception
            r9.printStackTrace()
        L4b:
            return r5
        L4c:
            r6.close()     // Catch: java.io.IOException -> L50
            goto L54
        L50:
            r9 = move-exception
            r9.printStackTrace()
        L54:
            return r0
        L55:
            r9 = move-exception
            goto L5b
        L57:
            r9 = move-exception
            goto L70
        L59:
            r9 = move-exception
            r6 = r5
        L5b:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L6e
            if (r6 == 0) goto L68
            r6.close()     // Catch: java.io.IOException -> L64
            goto L68
        L64:
            r9 = move-exception
            r9.printStackTrace()
        L68:
            java.lang.String r9 = "readFromFile failed!"
            com.tencent.wcdb.support.Log.e(r4, r9)
            return r5
        L6e:
            r9 = move-exception
            r5 = r6
        L70:
            if (r5 == 0) goto L7a
            r5.close()     // Catch: java.io.IOException -> L76
            goto L7a
        L76:
            r0 = move-exception
            r0.printStackTrace()
        L7a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.repair.DBDumpUtil.readFromFile(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x00e1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean doRecoveryDb(com.tencent.wcdb.database.SQLiteDatabase r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.util.List<java.lang.String> r21, java.util.List<java.lang.String> r22, com.tencent.wcdb.repair.DBDumpUtil.ExecuteSqlCallback r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 519
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.repair.DBDumpUtil.doRecoveryDb(com.tencent.wcdb.database.SQLiteDatabase, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List, com.tencent.wcdb.repair.DBDumpUtil$ExecuteSqlCallback, boolean):boolean");
    }
}
