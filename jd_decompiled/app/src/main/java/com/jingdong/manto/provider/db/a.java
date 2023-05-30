package com.jingdong.manto.provider.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.manto.provider.db.anno.FieldFilter;
import com.jingdong.manto.provider.db.anno.PrimaryKey;
import com.jingdong.manto.provider.db.anno.Table;
import com.jingdong.manto.provider.db.anno.TableField;
import com.jingdong.manto.utils.MantoLog;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes16.dex */
public final class a {
    private static final Map<Class<?>, Uri> a = new HashMap();
    private static a b = new a();

    /* renamed from: c  reason: collision with root package name */
    private static Context f13996c;

    private a() {
    }

    public static Uri a(Class<?> cls) {
        Map<Class<?>, Uri> map = a;
        Uri uri = map.get(cls);
        if (uri == null) {
            Uri parse = Uri.parse("content://" + a(f13996c) + "/class:" + cls.getName());
            map.put(cls, parse);
            return parse;
        }
        return uri;
    }

    public static <E> E a(Class<E> cls, Cursor cursor) {
        int columnIndex;
        Object valueOf;
        E newInstance = cls.newInstance();
        Field[] declaredFields = cls.getDeclaredFields();
        for (int i2 = 0; i2 < declaredFields.length; i2++) {
            Field field = declaredFields[i2];
            field.setAccessible(true);
            TableField tableField = (TableField) field.getAnnotation(TableField.class);
            String name = (tableField == null || TextUtils.isEmpty(tableField.value())) ? field.getName() : tableField.value();
            Class<?> type = field.getType();
            FieldFilter fieldFilter = (FieldFilter) declaredFields[i2].getAnnotation(FieldFilter.class);
            if (8 != (declaredFields[i2].getModifiers() & 8) && ((fieldFilter == null || !fieldFilter.value()) && (columnIndex = cursor.getColumnIndex(name)) >= 0)) {
                if (Integer.TYPE == type || Integer.class == type) {
                    valueOf = Integer.valueOf(cursor.getInt(columnIndex));
                } else if (Short.TYPE == type || Short.class == type) {
                    valueOf = Short.valueOf(cursor.getShort(columnIndex));
                } else if (Float.TYPE == type || Float.class == type) {
                    valueOf = Float.valueOf(cursor.getFloat(columnIndex));
                } else if (Double.TYPE == type || Double.class == type) {
                    valueOf = Double.valueOf(cursor.getDouble(columnIndex));
                } else if (Boolean.TYPE == type || Boolean.class == type) {
                    valueOf = Boolean.valueOf(1 == cursor.getInt(columnIndex));
                } else {
                    valueOf = (Long.TYPE == type || Long.class == type) ? Long.valueOf(cursor.getLong(columnIndex)) : cursor.getString(columnIndex);
                }
                field.set(newInstance, valueOf);
            }
        }
        return newInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context) {
        return context.getPackageName() + ".manto";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void a(Class<?> cls, SQLiteDatabase sQLiteDatabase) {
        boolean z;
        String[] strArr;
        String str;
        Field[] declaredFields = cls.getDeclaredFields();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        int i2 = 0;
        for (int i3 = 0; i3 < declaredFields.length; i3++) {
            Class<?> type = declaredFields[i3].getType();
            String str2 = (Integer.TYPE == type || Short.TYPE == type || Integer.class == type || Short.class == type) ? " INTEGER" : (Float.TYPE == type || Double.TYPE == type || Float.class == type || Double.class == type) ? " FLOAT" : (Boolean.TYPE == type || Boolean.class == type) ? " BOOLEAN" : (Long.TYPE == type || Long.class == type) ? " LONG" : " TEXT";
            FieldFilter fieldFilter = (FieldFilter) declaredFields[i3].getAnnotation(FieldFilter.class);
            if (8 != (declaredFields[i3].getModifiers() & 8) && (fieldFilter == null || !fieldFilter.value())) {
                String name = declaredFields[i3].getName();
                TableField tableField = (TableField) declaredFields[i3].getAnnotation(TableField.class);
                PrimaryKey primaryKey = (PrimaryKey) declaredFields[i3].getAnnotation(PrimaryKey.class);
                if (tableField != null) {
                    name = TextUtils.isEmpty(tableField.value()) ? declaredFields[i3].getName() : tableField.value();
                }
                if (primaryKey != null) {
                    arrayList.add(new Pair(name, Boolean.valueOf(primaryKey.autoGenerate())));
                } else {
                    hashMap.put(name, str2);
                }
            }
        }
        String c2 = c(cls);
        String str3 = "CREATE TABLE IF NOT EXISTS " + c2 + "(";
        if (arrayList.size() > 1) {
            throw new RuntimeException("table " + c2 + " has more than one primary key.");
        }
        if (arrayList.size() == 1) {
            Pair pair = (Pair) arrayList.get(0);
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append((String) pair.first);
            sb.append(" INTEGER PRIMARY KEY ");
            sb.append(((Boolean) pair.second).booleanValue() ? "AUTOINCREMENT" : "");
            sb.append(DYConstants.DY_REGEX_COMMA);
            str3 = sb.toString();
            z = true;
        } else {
            z = false;
        }
        Table table = (Table) cls.getAnnotation(Table.class);
        if (table == null || table.primaryKeys().length <= 0) {
            strArr = null;
        } else {
            strArr = table.primaryKeys();
            if (z) {
                throw new RuntimeException("table " + c2 + " has more than one primary key.");
            }
        }
        int i4 = 0;
        for (Map.Entry entry : hashMap.entrySet()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append((String) entry.getKey());
            String str4 = LangUtils.SINGLE_SPACE;
            sb2.append(LangUtils.SINGLE_SPACE);
            sb2.append((String) entry.getValue());
            sb2.append(LangUtils.SINGLE_SPACE);
            int i5 = i4 + 1;
            if (i4 != hashMap.size() - 1) {
                str4 = DYConstants.DY_REGEX_COMMA;
            }
            sb2.append(str4);
            str3 = sb2.toString();
            i4 = i5;
        }
        if (strArr == null || strArr.length <= 0) {
            str = str3 + ")";
        } else {
            str = str3 + ", PRIMARY KEY(";
            while (i2 < strArr.length) {
                String str5 = strArr[i2];
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(str5);
                sb3.append(i2 != strArr.length - 1 ? DYConstants.DY_REGEX_COMMA : "))");
                str = sb3.toString();
                i2++;
            }
        }
        MantoLog.d("better", str);
        sQLiteDatabase.execSQL(str);
    }

    public static ContentValues b(Object obj) {
        Class<?> cls = obj.getClass();
        ContentValues contentValues = new ContentValues();
        Field[] declaredFields = cls.getDeclaredFields();
        for (int i2 = 0; i2 < declaredFields.length; i2++) {
            Field field = declaredFields[i2];
            field.setAccessible(true);
            TableField tableField = (TableField) field.getAnnotation(TableField.class);
            String name = (tableField == null || TextUtils.isEmpty(tableField.value())) ? field.getName() : tableField.value();
            Class<?> type = field.getType();
            FieldFilter fieldFilter = (FieldFilter) declaredFields[i2].getAnnotation(FieldFilter.class);
            if (8 != (declaredFields[i2].getModifiers() & 8) && (fieldFilter == null || !fieldFilter.value())) {
                try {
                    if (Integer.TYPE != type && Integer.class != type) {
                        if (Short.TYPE != type && Short.class != type) {
                            if (Float.TYPE != type && Float.class != type) {
                                if (Double.TYPE != type && Double.class != type) {
                                    if (Boolean.TYPE != type && Boolean.class != type) {
                                        if (Long.TYPE != type && Long.class != type) {
                                            if (field.get(obj) != null) {
                                                contentValues.put(name, field.get(obj).toString());
                                            }
                                        }
                                        contentValues.put(name, Long.valueOf(field.getLong(obj)));
                                    }
                                    contentValues.put(name, Boolean.valueOf(field.getBoolean(obj)));
                                }
                                contentValues.put(name, Double.valueOf(field.getDouble(obj)));
                            }
                            contentValues.put(name, Float.valueOf(field.getFloat(obj)));
                        }
                        contentValues.put(name, Short.valueOf(field.getShort(obj)));
                    }
                    contentValues.put(name, Integer.valueOf(field.getInt(obj)));
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return contentValues;
    }

    public static a b(Context context) {
        f13996c = context.getApplicationContext();
        return b;
    }

    public static String[] b(Class<?> cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < declaredFields.length; i2++) {
            declaredFields[i2].setAccessible(true);
            TableField tableField = (TableField) declaredFields[i2].getAnnotation(TableField.class);
            String name = (tableField == null || TextUtils.isEmpty(tableField.value())) ? declaredFields[i2].getName() : tableField.value();
            FieldFilter fieldFilter = (FieldFilter) declaredFields[i2].getAnnotation(FieldFilter.class);
            if (8 != (declaredFields[i2].getModifiers() & 8) && (fieldFilter == null || !fieldFilter.value())) {
                arrayList.add(name);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String c(Class<?> cls) {
        Table table = (Table) cls.getAnnotation(Table.class);
        return (table == null || TextUtils.isEmpty(table.value())) ? cls.getSimpleName() : table.value();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(Context context) {
        f13996c = context.getApplicationContext();
    }

    public final int a(Class cls, String str, String[] strArr) {
        if (cls != null) {
            Uri a2 = a((Class<?>) cls);
            Context context = f13996c;
            if (context != null && a2 != null) {
                return context.getContentResolver().delete(a2, str, strArr);
            }
        }
        return -1;
    }

    public final int a(Object obj) {
        if (obj != null) {
            Pair<String, String[]> c2 = c(obj);
            return a(obj.getClass(), (String) c2.first, (String[]) c2.second);
        }
        return -1;
    }

    public final int a(List list) {
        if (list != null && !list.isEmpty()) {
            Uri a2 = a(list.get(0).getClass());
            ContentValues[] contentValuesArr = new ContentValues[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                contentValuesArr[i2] = b(list.get(i2));
            }
            Context context = f13996c;
            if (context != null && a2 != null) {
                return context.getContentResolver().bulkInsert(a2, contentValuesArr);
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r9 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0034, code lost:
        if (r9 == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0036, code lost:
        r9.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003e  */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [E] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <E> E a(java.lang.Class<E> r8, java.lang.String r9, java.lang.String[] r10, java.lang.String r11) {
        /*
            r7 = this;
            android.net.Uri r1 = a(r8)
            android.content.Context r0 = com.jingdong.manto.provider.db.a.f13996c
            r6 = 0
            if (r0 == 0) goto L42
            if (r1 == 0) goto L42
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String[] r2 = b(r8)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r3 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r9 == 0) goto L2a
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L3a
            if (r10 == 0) goto L2a
            java.lang.Object r8 = a(r8, r9)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L3a
            r6 = r8
            goto L2a
        L28:
            r8 = move-exception
            goto L31
        L2a:
            if (r9 == 0) goto L42
            goto L36
        L2d:
            r8 = move-exception
            goto L3c
        L2f:
            r8 = move-exception
            r9 = r6
        L31:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L3a
            if (r9 == 0) goto L42
        L36:
            r9.close()
            goto L42
        L3a:
            r8 = move-exception
            r6 = r9
        L3c:
            if (r6 == 0) goto L41
            r6.close()
        L41:
            throw r8
        L42:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.provider.db.a.a(java.lang.Class, java.lang.String, java.lang.String[], java.lang.String):java.lang.Object");
    }

    public final void a(String str) {
        try {
            MantoLog.e("better", "resetDataBase: " + str);
            f13996c.getContentResolver().call(Uri.parse("content://" + a(f13996c)), "resetDB", str, (Bundle) null);
        } catch (Throwable th) {
            MantoLog.e("better", th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <E> List<E> b(Class<E> cls, String str, String[] strArr, String str2) {
        ArrayList arrayList = new ArrayList();
        Uri a2 = a((Class<?>) cls);
        Context context = f13996c;
        if (context != null && a2 != null) {
            Cursor cursor = null;
            try {
                try {
                    cursor = context.getContentResolver().query(a2, b((Class<?>) cls), str, strArr, str2);
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            arrayList.add(a(cls, cursor));
                        }
                    }
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return arrayList;
    }

    final Pair<String, String[]> c(Object obj) {
        Pair<String, String[]> pair = new Pair<>(null, null);
        if (obj != null) {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            HashMap hashMap = new HashMap();
            int i2 = 0;
            for (int i3 = 0; i3 < declaredFields.length; i3++) {
                FieldFilter fieldFilter = (FieldFilter) declaredFields[i3].getAnnotation(FieldFilter.class);
                if (8 != (declaredFields[i3].getModifiers() & 8) && (fieldFilter == null || !fieldFilter.value())) {
                    declaredFields[i3].setAccessible(true);
                    try {
                        Object obj2 = declaredFields[i3].get(obj);
                        if (obj2 != null) {
                            hashMap.put(declaredFields[i3].getName(), obj2.toString());
                        }
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (hashMap.isEmpty()) {
                return pair;
            }
            String str = new String();
            String[] strArr = new String[hashMap.size()];
            for (Map.Entry entry : hashMap.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append((String) entry.getKey());
                sb.append("=? ");
                sb.append(i2 != hashMap.size() - 1 ? "and " : "");
                str = sb.toString();
                strArr[i2] = (String) entry.getValue();
                i2++;
            }
            return new Pair<>(str, strArr);
        }
        return pair;
    }

    public final Uri d(Object obj) {
        if (obj != null) {
            Uri a2 = a(obj.getClass());
            Context context = f13996c;
            if (context != null && a2 != null) {
                return context.getContentResolver().insert(a2, b(obj));
            }
        }
        return null;
    }

    public final <E> List<E> d(Class<E> cls) {
        return b(cls, null, null, null);
    }

    public void e(Class<?> cls) {
        f13996c.getContentResolver().delete(a(cls), null, null);
    }
}
