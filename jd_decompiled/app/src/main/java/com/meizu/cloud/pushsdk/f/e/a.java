package com.meizu.cloud.pushsdk.f.e;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes14.dex */
public class a implements d {

    /* renamed from: f  reason: collision with root package name */
    private static final String f15914f = "a";
    private SQLiteDatabase a;
    private final b b;

    /* renamed from: c  reason: collision with root package name */
    private final String[] f15915c = {"id", "eventData", "dateCreated"};
    private long d = -1;

    /* renamed from: e  reason: collision with root package name */
    private final int f15916e;

    public a(Context context, int i2) {
        this.b = b.f(context, b(context));
        i();
        this.f15916e = i2;
    }

    private String b(Context context) {
        String str = null;
        try {
            str = (String) Class.forName("com.meizu.cloud.utils.ProcessUtils").getDeclaredMethod("getCurrentProcessName", Context.class).invoke(null, context);
        } catch (Exception e2) {
            DebugLogger.e(f15914f, "getCurrentProcessName error " + e2.getMessage());
        }
        if (TextUtils.isEmpty(str)) {
            return "PushEvents.db";
        }
        return str + CartConstant.KEY_YB_INFO_LINK + "PushEvents.db";
    }

    public static Map<String, String> e(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            HashMap hashMap = (HashMap) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return hashMap;
        } catch (IOException | ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] f(Map<String, String> map) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public void a(com.meizu.cloud.pushsdk.f.b.a aVar) {
        g(aVar);
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public boolean a() {
        return h();
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public boolean a(long j2) {
        int i2;
        if (h()) {
            i2 = this.a.delete("events", "id=" + j2, null);
        } else {
            i2 = -1;
        }
        com.meizu.cloud.pushsdk.f.g.c.e(f15914f, "Removed event from database: " + j2, new Object[0]);
        return i2 == 1;
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public long b() {
        if (h()) {
            return DatabaseUtils.queryNumEntries(this.a, "events");
        }
        return 0L;
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public com.meizu.cloud.pushsdk.f.c.c c() {
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        for (Map<String, Object> map : c(this.f15916e)) {
            com.meizu.cloud.pushsdk.f.b.c cVar = new com.meizu.cloud.pushsdk.f.b.c();
            cVar.c((Map) map.get("eventData"));
            linkedList.add((Long) map.get("id"));
            arrayList.add(cVar);
        }
        return new com.meizu.cloud.pushsdk.f.c.c(arrayList, linkedList);
    }

    public List<Map<String, Object>> c(int i2) {
        return d(null, "id ASC LIMIT " + i2);
    }

    public List<Map<String, Object>> d(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (h()) {
            Cursor query = this.a.query("events", this.f15915c, str, null, null, null, str2);
            query.moveToFirst();
            while (!query.isAfterLast()) {
                HashMap hashMap = new HashMap(4);
                hashMap.put("id", Long.valueOf(query.getLong(0)));
                hashMap.put("eventData", e(query.getBlob(1)));
                hashMap.put("dateCreated", query.getString(2));
                query.moveToNext();
                arrayList.add(hashMap);
            }
            query.close();
        }
        return arrayList;
    }

    public long g(com.meizu.cloud.pushsdk.f.b.a aVar) {
        if (h()) {
            byte[] f2 = f(aVar.a());
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("eventData", f2);
            this.d = this.a.insert("events", null, contentValues);
        }
        com.meizu.cloud.pushsdk.f.g.c.e(f15914f, "Added event to database: " + this.d, new Object[0]);
        return this.d;
    }

    public boolean h() {
        SQLiteDatabase sQLiteDatabase = this.a;
        return sQLiteDatabase != null && sQLiteDatabase.isOpen();
    }

    public void i() {
        if (h()) {
            return;
        }
        try {
            SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
            this.a = writableDatabase;
            writableDatabase.enableWriteAheadLogging();
        } catch (Exception e2) {
            com.meizu.cloud.pushsdk.f.g.c.f(f15914f, " open database error " + e2.getMessage(), new Object[0]);
        }
    }
}
