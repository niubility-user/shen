package tv.danmaku.ijk.media.ext.cache.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes11.dex */
public class DBSourceInfoStorage extends SQLiteOpenHelper implements SourceInfoStorage {
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_VIDEO_PATH = "videoPath";
    private static final String CREATE_SQL = "CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,playkey TEXT NOT NULL,playTime INTEGER,playCount INTEGER,videoPath TEXT,indexPath TEXT,fileSize INTEGER);";
    private static final String TABLE = "SourceInfo";
    private static final String TAG = DBSourceInfoStorage.class.getSimpleName();
    private static final String COLUMN_KEY = "playkey";
    private static final String COLUMN_PLAY_TIME = "playTime";
    private static final String COLUMN_PLAY_COUNT = "playCount";
    private static final String COLUMN_INDEX_PATH = "indexPath";
    private static final String COLUMN_FILE_SIZE = "fileSize";
    private static final String[] ALL_COLUMNS = {"_id", COLUMN_KEY, COLUMN_PLAY_TIME, COLUMN_PLAY_COUNT, "videoPath", COLUMN_INDEX_PATH, COLUMN_FILE_SIZE};

    public DBSourceInfoStorage(Context context, String str) {
        super(context, str + ".db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    private SourceInfo convert(Cursor cursor) {
        return new SourceInfo(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KEY)), cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PLAY_TIME)), cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAY_COUNT)), cursor.getString(cursor.getColumnIndexOrThrow("videoPath")), cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INDEX_PATH)), cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_FILE_SIZE)));
    }

    @Override // tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage
    public void clearCache() {
        try {
            getWritableDatabase().execSQL("DELETE FROM SourceInfo");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage
    public void delete(SourceInfo sourceInfo) {
        if (sourceInfo == null) {
            return;
        }
        try {
            getWritableDatabase().delete(TABLE, "playkey=?", new String[]{sourceInfo.key});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage
    public SourceInfo get(String str) {
        Throwable th;
        Cursor cursor;
        SourceInfo sourceInfo = null;
        try {
            cursor = getReadableDatabase().query(TABLE, ALL_COLUMNS, "playkey=?", new String[]{str}, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        sourceInfo = convert(cursor);
                    }
                } catch (Exception unused) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return sourceInfo;
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(CREATE_SQL);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    @Override // tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage
    public void release() {
        try {
            close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage
    public void save(String str, String str2, String str3, long j2) {
        SourceInfo sourceInfo = new SourceInfo();
        sourceInfo.key = str;
        sourceInfo.playTime = System.currentTimeMillis();
        sourceInfo.videoPath = str2;
        sourceInfo.indexPath = str3;
        sourceInfo.fileSize = j2;
        save(sourceInfo);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r1 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0031, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
        return r0;
     */
    @Override // tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<tv.danmaku.ijk.media.ext.cache.storage.SourceInfo> get() {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            java.lang.String r3 = "select * from SourceInfo order by playTime DESC"
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            if (r1 == 0) goto L26
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
        L15:
            boolean r2 = r1.isAfterLast()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            if (r2 != 0) goto L26
            tv.danmaku.ijk.media.ext.cache.storage.SourceInfo r2 = r4.convert(r1)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            r0.add(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            r1.moveToNext()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            goto L15
        L26:
            if (r1 == 0) goto L34
            goto L31
        L29:
            r0 = move-exception
            goto L35
        L2b:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L29
            if (r1 == 0) goto L34
        L31:
            r1.close()
        L34:
            return r0
        L35:
            if (r1 == 0) goto L3a
            r1.close()
        L3a:
            goto L3c
        L3b:
            throw r0
        L3c:
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.ext.cache.storage.DBSourceInfoStorage.get():java.util.ArrayList");
    }

    private ContentValues convert(SourceInfo sourceInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_KEY, sourceInfo.key);
        contentValues.put(COLUMN_PLAY_TIME, Long.valueOf(sourceInfo.playTime));
        contentValues.put(COLUMN_PLAY_COUNT, Integer.valueOf(sourceInfo.playCount));
        contentValues.put("videoPath", sourceInfo.videoPath);
        contentValues.put(COLUMN_INDEX_PATH, sourceInfo.indexPath);
        contentValues.put(COLUMN_FILE_SIZE, Long.valueOf(sourceInfo.fileSize));
        return contentValues;
    }

    @Override // tv.danmaku.ijk.media.ext.cache.storage.SourceInfoStorage
    public void save(SourceInfo sourceInfo) {
        if (sourceInfo == null) {
            return;
        }
        boolean z = get(sourceInfo.key) != null;
        try {
            ContentValues convert = convert(sourceInfo);
            if (z) {
                getWritableDatabase().update(TABLE, convert, "playkey=?", new String[]{sourceInfo.key});
            } else {
                getWritableDatabase().insert(TABLE, null, convert);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
