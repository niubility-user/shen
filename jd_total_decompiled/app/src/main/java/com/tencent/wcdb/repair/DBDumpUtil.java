package com.tencent.wcdb.repair;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.support.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
    */
    public static byte[] readFromFile(String str) {
        FileInputStream fileInputStream;
        int length;
        File file = new File(str);
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            Log.w(TAG, "readFromFile error, file is not exit, path = %s", str);
            return null;
        }
        try {
            length = (int) file.length();
            fileInputStream = new FileInputStream(file);
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            if (fileInputStream2 != null) {
            }
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[length];
                int read = fileInputStream.read(bArr);
                if (read != length) {
                    Log.w(TAG, "readFromFile error, size is not equal, path = %s, file length is %d, count is %d", str, Integer.valueOf(length), Integer.valueOf(read));
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return null;
                }
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
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
        } catch (Exception e6) {
            e = e6;
            e.printStackTrace();
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            Log.e(TAG, "readFromFile failed!");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x00e1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean doRecoveryDb(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, List<String> list, List<String> list2, ExecuteSqlCallback executeSqlCallback, boolean z) {
        boolean z2;
        String str4;
        boolean z3;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            if (nativeDumpDB(str, str2, str3)) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(str3));
                    sQLiteDatabase.execSQL("PRAGMA foreign_keys=OFF;");
                    sQLiteDatabase.beginTransaction();
                    try {
                        try {
                            HashMap hashMap = new HashMap();
                            boolean z4 = false;
                            int i2 = 0;
                            int i3 = 0;
                            String str5 = null;
                            int i4 = 0;
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    try {
                                        break;
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                } else if (z4) {
                                    z2 = z4;
                                    str5 = str5 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + readLine;
                                    if (str5.endsWith(";") && nativeIsSqlComplete(str5)) {
                                        readLine = str5;
                                        if (list2 == null && list2.size() > 0) {
                                            str4 = getTableNameFromSql(readLine);
                                            Iterator<String> it = list2.iterator();
                                            while (true) {
                                                if (!it.hasNext()) {
                                                    z3 = true;
                                                    break;
                                                } else if (str4.equals(it.next())) {
                                                    z3 = false;
                                                    break;
                                                }
                                            }
                                            str5 = readLine;
                                        } else {
                                            if (list != null || list.size() <= 0) {
                                                str5 = readLine;
                                                str4 = null;
                                            } else {
                                                str4 = getTableNameFromSql(readLine);
                                                Iterator<String> it2 = list.iterator();
                                                while (it2.hasNext()) {
                                                    if (str4.equals(it2.next())) {
                                                        str5 = "";
                                                        z3 = true;
                                                        break;
                                                    }
                                                }
                                                str5 = readLine;
                                            }
                                            z3 = false;
                                        }
                                        if (!z3) {
                                            Log.i(TAG, "filter table %s", str4);
                                            z4 = false;
                                        } else {
                                            try {
                                                if (str5.startsWith("CREATE TABLE")) {
                                                    hashMap.put(str4, buildColumnsString(getColumnNamesFromSql(str5)));
                                                } else if (str5.startsWith("INSERT INTO")) {
                                                    String str6 = (String) hashMap.get(str4);
                                                    if (!TextUtils.isEmpty(str6)) {
                                                        StringBuilder sb = new StringBuilder("INSERT INTO ");
                                                        sb.append("\"");
                                                        sb.append(str4);
                                                        sb.append("\"");
                                                        String sb2 = sb.toString();
                                                        sb.append(str6);
                                                        str5 = str5.replace(sb2, sb.toString());
                                                    }
                                                }
                                                String preExecute = executeSqlCallback != null ? executeSqlCallback.preExecute(str5) : null;
                                                if (!TextUtils.isEmpty(preExecute)) {
                                                    str5 = preExecute;
                                                }
                                                i3++;
                                                sQLiteDatabase.execSQL(str5);
                                                i4++;
                                                if (i4 >= 100) {
                                                    sQLiteDatabase.setTransactionSuccessful();
                                                    sQLiteDatabase.endTransaction();
                                                    sQLiteDatabase.beginTransaction();
                                                    i4 = 0;
                                                }
                                            } catch (Exception unused) {
                                                i2++;
                                            }
                                            z4 = false;
                                            str5 = null;
                                        }
                                    }
                                    z4 = z2;
                                } else {
                                    z2 = z4;
                                    if (!readLine.startsWith("INSERT") && !readLine.startsWith("CREATE TABLE")) {
                                        z4 = z2;
                                    }
                                    if (readLine.endsWith(";") && nativeIsSqlComplete(readLine)) {
                                        if (list2 == null) {
                                        }
                                        if (list != null) {
                                        }
                                        str5 = readLine;
                                        str4 = null;
                                        z3 = false;
                                        if (!z3) {
                                        }
                                    }
                                    if (TextUtils.isEmpty(str5)) {
                                        str5 = readLine;
                                    } else {
                                        str5 = str5 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + readLine;
                                    }
                                    z4 = true;
                                }
                            }
                            bufferedReader.close();
                            if (i3 > i2) {
                                if (sQLiteDatabase.inTransaction()) {
                                    sQLiteDatabase.setTransactionSuccessful();
                                }
                                if (sQLiteDatabase.inTransaction()) {
                                    sQLiteDatabase.endTransaction();
                                }
                                if (z) {
                                    File file = new File(str3);
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    File file2 = new File(str);
                                    if (file2.exists()) {
                                        file2.delete();
                                    }
                                }
                                Log.i(TAG, "restore : %d , fail:%d ", Integer.valueOf(i3), Integer.valueOf(i2));
                                return true;
                            }
                            return false;
                        } catch (Throwable th) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (IOException unused2) {
                        Log.w(TAG, "I/O error in read sql file ");
                        try {
                            bufferedReader.close();
                            return false;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            return false;
                        }
                    }
                } catch (FileNotFoundException unused3) {
                    Log.w(TAG, "SQL file '%s' not found", str3);
                    return false;
                }
            }
            return false;
        }
        Log.w(TAG, "Database is not open");
        return false;
    }
}
