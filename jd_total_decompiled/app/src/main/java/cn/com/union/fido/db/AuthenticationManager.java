package cn.com.union.fido.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.com.union.fido.bean.db.AuthenticationEntity;
import cn.com.union.fido.db.help.SQLiteHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AuthenticationManager {
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    public AuthenticationManager(Context context) {
        SQLiteHelper sQLiteHelper = SQLiteHelper.getInstance(context);
        this.helper = sQLiteHelper;
        this.db = sQLiteHelper.getWritableDatabase();
    }

    public void add(AuthenticationEntity authenticationEntity) {
        this.db.beginTransaction();
        try {
            this.db.execSQL("INSERT INTO authentication VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{authenticationEntity.getCallerID(), authenticationEntity.getAppID(), authenticationEntity.getKeyHandle(), authenticationEntity.getKeyID(), authenticationEntity.getCurrentTimestamp(), authenticationEntity.getStatus(), authenticationEntity.getAaid(), authenticationEntity.getUserName()});
            this.db.setTransactionSuccessful();
        } finally {
            this.db.endTransaction();
        }
    }

    public int delEntityByAaid(String str) {
        try {
            SQLiteDatabase sQLiteDatabase = this.db;
            this.helper.getClass();
            return sQLiteDatabase.delete("authentication", "aaid = ?", new String[]{str});
        } catch (Throwable unused) {
            return -1;
        }
    }

    public int delEntityByAaid(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = this.db;
        this.helper.getClass();
        return sQLiteDatabase.delete("authentication", "aaid = ? and keyID = ?", new String[]{str, str2});
    }

    public int delEntityByAppID(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = this.db;
        this.helper.getClass();
        return sQLiteDatabase.delete("authentication", "appID = ? and keyID = ?", new String[]{str, str2});
    }

    public int delEntityByUserName(String str) {
        try {
            SQLiteDatabase sQLiteDatabase = this.db;
            this.helper.getClass();
            return sQLiteDatabase.delete("authentication", "userName = ?", new String[]{str});
        } catch (Throwable unused) {
            return -1;
        }
    }

    public List<AuthenticationEntity> getAll() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.db.rawQuery("SELECT * FROM authentication", null);
        while (rawQuery.moveToNext()) {
            AuthenticationEntity authenticationEntity = new AuthenticationEntity();
            authenticationEntity.setCallerID(rawQuery.getString(rawQuery.getColumnIndex("callerID")));
            authenticationEntity.setAppID(rawQuery.getString(rawQuery.getColumnIndex("appID")));
            authenticationEntity.setKeyHandle(rawQuery.getString(rawQuery.getColumnIndex("keyHandle")));
            authenticationEntity.setKeyID(rawQuery.getString(rawQuery.getColumnIndex("keyID")));
            authenticationEntity.setCurrentTimestamp(rawQuery.getString(rawQuery.getColumnIndex("currentTimestamp")));
            authenticationEntity.setStatus(rawQuery.getString(rawQuery.getColumnIndex("status")));
            authenticationEntity.setAaid(rawQuery.getString(rawQuery.getColumnIndex("aaid")));
            authenticationEntity.setUserName(rawQuery.getString(rawQuery.getColumnIndex("userName")));
            arrayList.add(authenticationEntity);
        }
        rawQuery.close();
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0022, code lost:
        if (r6 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0019, code lost:
        if (r6 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        r6.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getCountByUserName(String str) {
        Cursor cursor;
        try {
            cursor = this.db.rawQuery("SELECT count(*) FROM authentication WHERE userName = ?", new String[]{str});
            try {
                r0 = cursor.moveToNext() ? cursor.getInt(0) : -1;
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            cursor = null;
        }
    }

    public AuthenticationEntity getEntity(String str, String str2) {
        Cursor cursor;
        AuthenticationEntity authenticationEntity;
        try {
            authenticationEntity = new AuthenticationEntity();
            cursor = this.db.rawQuery("SELECT * FROM authentication WHERE appID = ? and keyID = ? and status = ? ", new String[]{str, str2, "ready"});
        } catch (Throwable unused) {
            cursor = null;
        }
        try {
            if (cursor.moveToNext()) {
                authenticationEntity.setAppID(str);
                authenticationEntity.setCallerID(cursor.getString(cursor.getColumnIndex("callerID")));
                authenticationEntity.setAppID(cursor.getString(cursor.getColumnIndex("appID")));
                authenticationEntity.setKeyHandle(cursor.getString(cursor.getColumnIndex("keyHandle")));
                authenticationEntity.setKeyID(str2);
                authenticationEntity.setCurrentTimestamp(cursor.getString(cursor.getColumnIndex("currentTimestamp")));
                authenticationEntity.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                authenticationEntity.setAaid(cursor.getString(cursor.getColumnIndex("aaid")));
                authenticationEntity.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            }
            if (cursor != null) {
                cursor.close();
            }
            return authenticationEntity;
        } catch (Throwable unused2) {
            if (cursor != null) {
                cursor.close();
                return null;
            }
            return null;
        }
    }

    public Map<String, List<String>> getRegistrations(String str, String str2) {
        SQLiteDatabase sQLiteDatabase = this.db;
        this.helper.getClass();
        Cursor query = sQLiteDatabase.query("authentication", new String[]{"appID", "keyID"}, "callerID = ? and aaid = ? and status = ?", new String[]{str, str2, "ready"}, null, null, null);
        HashMap hashMap = new HashMap();
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("appID"));
            String string2 = query.getString(query.getColumnIndex("keyID"));
            if (hashMap.get(string) == null) {
                hashMap.put(string, new ArrayList());
            }
            ((List) hashMap.get(string)).add(string2);
        }
        query.close();
        return hashMap;
    }

    public void updateStatus(AuthenticationEntity authenticationEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", authenticationEntity.getStatus());
        SQLiteDatabase sQLiteDatabase = this.db;
        this.helper.getClass();
        sQLiteDatabase.update("authentication", contentValues, "aaid = ? and keyID = ?", new String[]{authenticationEntity.getAaid(), authenticationEntity.getKeyID()});
    }
}
