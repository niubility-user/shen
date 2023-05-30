package com.tencent.wcdb.support;

import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.wcdb.DatabaseErrorHandler;
import com.tencent.wcdb.FileUtils;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteGlobal;
import java.io.File;

/* loaded from: classes9.dex */
public final class Context {
    public static final int MODE_ENABLE_WRITE_AHEAD_LOGGING = 8;

    static {
        SQLiteGlobal.loadLib();
    }

    private static File getDataDirFile(android.content.Context context) {
        if (context != null) {
            String str = context.getApplicationInfo().dataDir;
            if (str != null) {
                return new File(str);
            }
            return null;
        }
        throw new RuntimeException("Not supported in system context");
    }

    private static File getDatabasesDir(android.content.Context context) {
        File file = new File(getDataDirFile(context), "databases");
        return file.getPath().equals("databases") ? new File("/data/system") : file;
    }

    private static File makeFilename(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    public static SQLiteDatabase openOrCreateDatabase(android.content.Context context, String str, int i2, SQLiteDatabase.CursorFactory cursorFactory) {
        return openOrCreateDatabase(context, str, null, null, i2, cursorFactory, null, 0);
    }

    private static void setFilePermissionsFromMode(String str, int i2, int i3) {
        int i4 = i3 | R2.attr.badgeStyle;
        if ((i2 & 1) != 0) {
            i4 |= 4;
        }
        if ((i2 & 2) != 0) {
            i4 |= 2;
        }
        FileUtils.setPermissions(str, i4, -1, -1);
    }

    private static File validateFilePath(android.content.Context context, String str, boolean z) {
        File databasesDir;
        File makeFilename;
        char charAt = str.charAt(0);
        char c2 = File.separatorChar;
        if (charAt == c2) {
            databasesDir = new File(str.substring(0, str.lastIndexOf(c2)));
            makeFilename = new File(databasesDir, str.substring(str.lastIndexOf(File.separatorChar)));
        } else {
            databasesDir = getDatabasesDir(context);
            makeFilename = makeFilename(databasesDir, str);
        }
        if (z && !databasesDir.isDirectory() && databasesDir.mkdir()) {
            FileUtils.setPermissions(databasesDir.getPath(), 505, -1, -1);
        }
        return makeFilename;
    }

    public static SQLiteDatabase openOrCreateDatabase(android.content.Context context, String str, int i2, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openOrCreateDatabase(context, str, null, null, i2, cursorFactory, databaseErrorHandler, 0);
    }

    public static SQLiteDatabase openOrCreateDatabase(android.content.Context context, String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, int i2, SQLiteDatabase.CursorFactory cursorFactory) {
        return openOrCreateDatabase(context, str, bArr, sQLiteCipherSpec, i2, cursorFactory, null, 0);
    }

    public static SQLiteDatabase openOrCreateDatabase(android.content.Context context, String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, int i2, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openOrCreateDatabase(context, str, bArr, sQLiteCipherSpec, i2, cursorFactory, databaseErrorHandler, 0);
    }

    public static SQLiteDatabase openOrCreateDatabase(android.content.Context context, String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, int i2, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler, int i3) {
        File validateFilePath = validateFilePath(context, str, true);
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(validateFilePath.getPath(), bArr, sQLiteCipherSpec, cursorFactory, (i2 & 8) != 0 ? 805306368 : 268435456, databaseErrorHandler, i3);
        setFilePermissionsFromMode(validateFilePath.getPath(), i2, 0);
        return openDatabase;
    }
}
