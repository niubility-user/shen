package com.tencent.wcdb;

import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.Parcel;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.tencent.wcdb.database.SQLiteAbortException;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteConstraintException;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteDatabaseCorruptException;
import com.tencent.wcdb.database.SQLiteDiskIOException;
import com.tencent.wcdb.database.SQLiteException;
import com.tencent.wcdb.database.SQLiteFullException;
import com.tencent.wcdb.database.SQLiteProgram;
import com.tencent.wcdb.database.SQLiteStatement;
import com.tencent.wcdb.support.Log;
import com.tencent.wcdb.support.OperationCanceledException;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Collator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public final class DatabaseUtils {
    private static final boolean DEBUG = false;
    private static final int EX_HAS_REPLY_HEADER = -128;
    public static final int STATEMENT_ABORT = 6;
    public static final int STATEMENT_ATTACH = 3;
    public static final int STATEMENT_BEGIN = 4;
    public static final int STATEMENT_COMMIT = 5;
    public static final int STATEMENT_DDL = 8;
    public static final int STATEMENT_OTHER = 99;
    public static final int STATEMENT_PRAGMA = 7;
    public static final int STATEMENT_SELECT = 1;
    public static final int STATEMENT_UNPREPARED = 9;
    public static final int STATEMENT_UPDATE = 2;
    private static final String TAG = "WCDB.DatabaseUtils";
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static Collator mColl = null;

    @Deprecated
    /* loaded from: classes9.dex */
    public static class InsertHelper {
        public static final int TABLE_INFO_PRAGMA_COLUMNNAME_INDEX = 1;
        public static final int TABLE_INFO_PRAGMA_DEFAULT_INDEX = 4;
        private HashMap<String, Integer> mColumns;
        private final SQLiteDatabase mDb;
        private final String mTableName;
        private String mInsertSQL = null;
        private SQLiteStatement mInsertStatement = null;
        private SQLiteStatement mReplaceStatement = null;
        private SQLiteStatement mPreparedStatement = null;

        public InsertHelper(SQLiteDatabase sQLiteDatabase, String str) {
            this.mDb = sQLiteDatabase;
            this.mTableName = str;
        }

        private void buildSQL() throws SQLException {
            StringBuilder sb = new StringBuilder(128);
            sb.append("INSERT INTO ");
            sb.append(this.mTableName);
            sb.append(" (");
            StringBuilder sb2 = new StringBuilder(128);
            sb2.append("VALUES (");
            Cursor cursor = null;
            try {
                cursor = this.mDb.rawQuery("PRAGMA table_info(" + this.mTableName + ")", null);
                this.mColumns = new HashMap<>(cursor.getCount());
                int i2 = 1;
                while (cursor.moveToNext()) {
                    String string = cursor.getString(1);
                    String string2 = cursor.getString(4);
                    this.mColumns.put(string, Integer.valueOf(i2));
                    sb.append("'");
                    sb.append(string);
                    sb.append("'");
                    if (string2 == null) {
                        sb2.append("?");
                    } else {
                        sb2.append("COALESCE(?, ");
                        sb2.append(string2);
                        sb2.append(")");
                    }
                    sb.append(i2 == cursor.getCount() ? ") " : ", ");
                    sb2.append(i2 == cursor.getCount() ? ");" : ", ");
                    i2++;
                }
                sb.append((CharSequence) sb2);
                this.mInsertSQL = sb.toString();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        private SQLiteStatement getStatement(boolean z) throws SQLException {
            if (z) {
                if (this.mReplaceStatement == null) {
                    if (this.mInsertSQL == null) {
                        buildSQL();
                    }
                    this.mReplaceStatement = this.mDb.compileStatement("INSERT OR REPLACE" + this.mInsertSQL.substring(6));
                }
                return this.mReplaceStatement;
            }
            if (this.mInsertStatement == null) {
                if (this.mInsertSQL == null) {
                    buildSQL();
                }
                this.mInsertStatement = this.mDb.compileStatement(this.mInsertSQL);
            }
            return this.mInsertStatement;
        }

        private long insertInternal(ContentValues contentValues, boolean z) {
            this.mDb.beginTransactionNonExclusive();
            try {
                SQLiteStatement statement = getStatement(z);
                statement.clearBindings();
                for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                    DatabaseUtils.bindObjectToProgram(statement, getColumnIndex(entry.getKey()), entry.getValue());
                }
                long executeInsert = statement.executeInsert();
                this.mDb.setTransactionSuccessful();
                return executeInsert;
            } catch (SQLException e2) {
                Log.e(DatabaseUtils.TAG, "Error inserting " + contentValues + " into table  " + this.mTableName, e2);
                return -1L;
            } finally {
                this.mDb.endTransaction();
            }
        }

        public void bind(int i2, double d) {
            this.mPreparedStatement.bindDouble(i2, d);
        }

        public void bindNull(int i2) {
            this.mPreparedStatement.bindNull(i2);
        }

        public void close() {
            SQLiteStatement sQLiteStatement = this.mInsertStatement;
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
                this.mInsertStatement = null;
            }
            SQLiteStatement sQLiteStatement2 = this.mReplaceStatement;
            if (sQLiteStatement2 != null) {
                sQLiteStatement2.close();
                this.mReplaceStatement = null;
            }
            this.mInsertSQL = null;
            this.mColumns = null;
        }

        public long execute() {
            SQLiteStatement sQLiteStatement = this.mPreparedStatement;
            if (sQLiteStatement != null) {
                try {
                    return sQLiteStatement.executeInsert();
                } catch (SQLException e2) {
                    Log.e(DatabaseUtils.TAG, "Error executing InsertHelper with table " + this.mTableName, e2);
                    return -1L;
                } finally {
                    this.mPreparedStatement = null;
                }
            }
            throw new IllegalStateException("you must prepare this inserter before calling execute");
        }

        public int getColumnIndex(String str) {
            getStatement(false);
            Integer num = this.mColumns.get(str);
            if (num != null) {
                return num.intValue();
            }
            throw new IllegalArgumentException("column '" + str + "' is invalid");
        }

        public long insert(ContentValues contentValues) {
            return insertInternal(contentValues, false);
        }

        public void prepareForInsert() {
            SQLiteStatement statement = getStatement(false);
            this.mPreparedStatement = statement;
            statement.clearBindings();
        }

        public void prepareForReplace() {
            SQLiteStatement statement = getStatement(true);
            this.mPreparedStatement = statement;
            statement.clearBindings();
        }

        public long replace(ContentValues contentValues) {
            return insertInternal(contentValues, true);
        }

        public void bind(int i2, float f2) {
            this.mPreparedStatement.bindDouble(i2, f2);
        }

        public void bind(int i2, long j2) {
            this.mPreparedStatement.bindLong(i2, j2);
        }

        public void bind(int i2, int i3) {
            this.mPreparedStatement.bindLong(i2, i3);
        }

        public void bind(int i2, boolean z) {
            this.mPreparedStatement.bindLong(i2, z ? 1L : 0L);
        }

        public void bind(int i2, byte[] bArr) {
            if (bArr == null) {
                this.mPreparedStatement.bindNull(i2);
            } else {
                this.mPreparedStatement.bindBlob(i2, bArr);
            }
        }

        public void bind(int i2, String str) {
            if (str == null) {
                this.mPreparedStatement.bindNull(i2);
            } else {
                this.mPreparedStatement.bindString(i2, str);
            }
        }
    }

    public static void appendEscapedSQLString(StringBuilder sb, String str) {
        sb.append('\'');
        if (str.indexOf(39) != -1) {
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt == '\'') {
                    sb.append('\'');
                }
                sb.append(charAt);
            }
        } else {
            sb.append(str);
        }
        sb.append('\'');
    }

    public static String[] appendSelectionArgs(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr.length == 0) {
            return strArr2;
        }
        String[] strArr3 = new String[strArr.length + strArr2.length];
        System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        System.arraycopy(strArr2, 0, strArr3, strArr.length, strArr2.length);
        return strArr3;
    }

    public static final void appendValueToSql(StringBuilder sb, Object obj) {
        if (obj == null) {
            sb.append("NULL");
        } else if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        } else {
            appendEscapedSQLString(sb, obj.toString());
        }
    }

    public static void bindObjectToProgram(SQLiteProgram sQLiteProgram, int i2, Object obj) {
        if (obj == null) {
            sQLiteProgram.bindNull(i2);
        } else if (!(obj instanceof Double) && !(obj instanceof Float)) {
            if (obj instanceof Number) {
                sQLiteProgram.bindLong(i2, ((Number) obj).longValue());
            } else if (obj instanceof Boolean) {
                if (((Boolean) obj).booleanValue()) {
                    sQLiteProgram.bindLong(i2, 1L);
                } else {
                    sQLiteProgram.bindLong(i2, 0L);
                }
            } else if (obj instanceof byte[]) {
                sQLiteProgram.bindBlob(i2, (byte[]) obj);
            } else {
                sQLiteProgram.bindString(i2, obj.toString());
            }
        } else {
            sQLiteProgram.bindDouble(i2, ((Number) obj).doubleValue());
        }
    }

    public static String concatenateWhere(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return "(" + str + ") AND (" + str2 + ")";
    }

    public static void createDbFromSqlStatements(Context context, String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, int i2, String str2) {
        SQLiteDatabase openOrCreateDatabase = com.tencent.wcdb.support.Context.openOrCreateDatabase(context, str, bArr, sQLiteCipherSpec, 0, null);
        for (String str3 : TextUtils.split(str2, ";\n")) {
            if (!TextUtils.isEmpty(str3)) {
                openOrCreateDatabase.execSQL(str3);
            }
        }
        openOrCreateDatabase.setVersion(i2);
        openOrCreateDatabase.close();
    }

    public static void cursorDoubleToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (!cursor.isNull(columnIndex)) {
            contentValues.put(str2, Double.valueOf(cursor.getDouble(columnIndex)));
        } else {
            contentValues.put(str2, (Double) null);
        }
    }

    public static void cursorDoubleToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Double.valueOf(cursor.getDouble(columnIndex)));
    }

    public static void cursorDoubleToCursorValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorDoubleToContentValues(cursor, str, contentValues, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x001f, code lost:
        if (r5.moveToPosition(r6) != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0025, code lost:
        if (r7.allocRow() != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0028, code lost:
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0029, code lost:
        if (r2 >= r1) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x002b, code lost:
        r3 = r5.getType(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x002f, code lost:
        if (r3 == 0) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0032, code lost:
        if (r3 == 1) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0035, code lost:
        if (r3 == 2) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0038, code lost:
        if (r3 == 4) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x003a, code lost:
        r3 = r5.getString(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x003e, code lost:
        if (r3 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0040, code lost:
        r3 = r7.putString(r3, r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0045, code lost:
        r3 = r7.putNull(r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x004a, code lost:
        r3 = r5.getBlob(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x004e, code lost:
        if (r3 == null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0050, code lost:
        r3 = r7.putBlob(r3, r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0055, code lost:
        r3 = r7.putNull(r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x005a, code lost:
        r3 = r7.putDouble(r5.getDouble(r2), r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0063, code lost:
        r3 = r7.putLong(r5.getLong(r2), r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x006c, code lost:
        r3 = r7.putNull(r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0070, code lost:
        if (r3 != false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0072, code lost:
        r7.freeLastRow();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0076, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0079, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x007f, code lost:
        if (r5.moveToNext() != false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0081, code lost:
        r5.moveToPosition(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0084, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void cursorFillWindow(com.tencent.wcdb.Cursor r5, int r6, com.tencent.wcdb.CursorWindow r7) {
        /*
            if (r6 < 0) goto L84
            int r0 = r5.getCount()
            if (r6 < r0) goto La
            goto L84
        La:
            int r0 = r5.getPosition()
            int r1 = r5.getColumnCount()
            r7.clear()
            r7.setStartPosition(r6)
            r7.setNumColumns(r1)
            boolean r2 = r5.moveToPosition(r6)
            if (r2 == 0) goto L81
        L21:
            boolean r2 = r7.allocRow()
            if (r2 != 0) goto L28
            goto L81
        L28:
            r2 = 0
        L29:
            if (r2 >= r1) goto L79
            int r3 = r5.getType(r2)
            if (r3 == 0) goto L6c
            r4 = 1
            if (r3 == r4) goto L63
            r4 = 2
            if (r3 == r4) goto L5a
            r4 = 4
            if (r3 == r4) goto L4a
            java.lang.String r3 = r5.getString(r2)
            if (r3 == 0) goto L45
            boolean r3 = r7.putString(r3, r6, r2)
            goto L70
        L45:
            boolean r3 = r7.putNull(r6, r2)
            goto L70
        L4a:
            byte[] r3 = r5.getBlob(r2)
            if (r3 == 0) goto L55
            boolean r3 = r7.putBlob(r3, r6, r2)
            goto L70
        L55:
            boolean r3 = r7.putNull(r6, r2)
            goto L70
        L5a:
            double r3 = r5.getDouble(r2)
            boolean r3 = r7.putDouble(r3, r6, r2)
            goto L70
        L63:
            long r3 = r5.getLong(r2)
            boolean r3 = r7.putLong(r3, r6, r2)
            goto L70
        L6c:
            boolean r3 = r7.putNull(r6, r2)
        L70:
            if (r3 != 0) goto L76
            r7.freeLastRow()
            goto L79
        L76:
            int r2 = r2 + 1
            goto L29
        L79:
            int r6 = r6 + 1
            boolean r2 = r5.moveToNext()
            if (r2 != 0) goto L21
        L81:
            r5.moveToPosition(r0)
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.DatabaseUtils.cursorFillWindow(com.tencent.wcdb.Cursor, int, com.tencent.wcdb.CursorWindow):void");
    }

    public static void cursorFloatToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Float.valueOf(cursor.getFloat(columnIndex)));
    }

    public static void cursorIntToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorIntToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorIntToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Integer.valueOf(cursor.getInt(columnIndex)));
    }

    public static void cursorLongToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorLongToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorLongToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Long.valueOf(cursor.getLong(columnIndex)));
    }

    public static int cursorPickFillWindowStartPosition(int i2, int i3) {
        return Math.max(i2 - (i3 / 3), 0);
    }

    public static void cursorRowToContentValues(Cursor cursor, ContentValues contentValues) {
        AbstractWindowedCursor abstractWindowedCursor = cursor instanceof AbstractWindowedCursor ? (AbstractWindowedCursor) cursor : null;
        String[] columnNames = cursor.getColumnNames();
        int length = columnNames.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (abstractWindowedCursor != null && abstractWindowedCursor.isBlob(i2)) {
                contentValues.put(columnNames[i2], cursor.getBlob(i2));
            } else {
                contentValues.put(columnNames[i2], cursor.getString(i2));
            }
        }
    }

    public static void cursorShortToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Short.valueOf(cursor.getShort(columnIndex)));
    }

    public static void cursorStringToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorStringToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorStringToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, cursor.getString(columnIndex));
    }

    public static void cursorStringToInsertHelper(Cursor cursor, String str, InsertHelper insertHelper, int i2) {
        insertHelper.bind(i2, cursor.getString(cursor.getColumnIndexOrThrow(str)));
    }

    public static void dumpCurrentRow(Cursor cursor) {
        dumpCurrentRow(cursor, System.out);
    }

    public static String dumpCurrentRowToString(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        dumpCurrentRow(cursor, sb);
        return sb.toString();
    }

    public static void dumpCursor(Cursor cursor) {
        dumpCursor(cursor, System.out);
    }

    public static String dumpCursorToString(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        dumpCursor(cursor, sb);
        return sb.toString();
    }

    private static char[] encodeHex(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            char[] cArr2 = DIGITS;
            cArr[i2] = cArr2[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = cArr2[bArr[i3] & 15];
        }
        return cArr;
    }

    private static int extractSqlCode(String str) {
        int i2 = 0;
        for (int i3 = 0; i3 < 3; i3++) {
            int charAt = str.charAt(i3);
            if (charAt >= 97 && charAt <= 122) {
                charAt = (charAt - 97) + 65;
            } else if (charAt >= 128) {
                return 0;
            }
            i2 |= (charAt & 127) << (i3 * 8);
        }
        return i2;
    }

    public static int findRowIdColumnIndex(String[] strArr) {
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (strArr[i2].equals("_id")) {
                return i2;
            }
        }
        return -1;
    }

    public static String getCollationKey(String str) {
        byte[] collationKeyInBytes = getCollationKeyInBytes(str);
        try {
            return new String(collationKeyInBytes, 0, getKeyLen(collationKeyInBytes), "ISO8859_1");
        } catch (Exception unused) {
            return "";
        }
    }

    private static byte[] getCollationKeyInBytes(String str) {
        if (mColl == null) {
            Collator collator = Collator.getInstance();
            mColl = collator;
            collator.setStrength(0);
        }
        return mColl.getCollationKey(str).toByteArray();
    }

    public static String getHexCollationKey(String str) {
        byte[] collationKeyInBytes = getCollationKeyInBytes(str);
        return new String(encodeHex(collationKeyInBytes), 0, getKeyLen(collationKeyInBytes) * 2);
    }

    private static int getKeyLen(byte[] bArr) {
        if (bArr[bArr.length - 1] != 0) {
            return bArr.length;
        }
        return bArr.length - 1;
    }

    public static int getSqlStatementType(String str) {
        String trim = str.trim();
        if (trim.length() < 3) {
            return 99;
        }
        switch (extractSqlCode(trim)) {
            case 4279873:
            case 5522756:
                return 9;
            case 4280912:
                return 7;
            case 4476485:
            case 5066563:
                return 5;
            case 4477013:
            case 4998468:
            case 5260626:
            case 5459529:
                return 2;
            case 4543043:
            case 5198404:
            case 5524545:
                return 8;
            case 4670786:
                return 4;
            case 4998483:
                return 1;
            case 5001042:
                return 6;
            case 5526593:
                return 3;
            default:
                return 99;
        }
    }

    public static int getTypeOfObject(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof byte[]) {
            return 4;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return 2;
        }
        return ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) ? 1 : 3;
    }

    public static long longForQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(str);
        try {
            return longForQuery(compileStatement, strArr);
        } finally {
            compileStatement.close();
        }
    }

    public static boolean objectEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean queryIsEmpty(SQLiteDatabase sQLiteDatabase, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("select exists(select 1 from ");
        sb.append(str);
        sb.append(")");
        return longForQuery(sQLiteDatabase, sb.toString(), null) == 0;
    }

    public static long queryNumEntries(SQLiteDatabase sQLiteDatabase, String str) {
        return queryNumEntries(sQLiteDatabase, str, null, null);
    }

    private static final int readExceptionCode(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == EX_HAS_REPLY_HEADER) {
            if (parcel.readInt() == 0) {
                Log.e(TAG, "Unexpected zero-sized Parcel reply header.");
                return 0;
            }
            return 0;
        }
        return readInt;
    }

    public static final void readExceptionFromParcel(Parcel parcel) {
        int readExceptionCode = readExceptionCode(parcel);
        if (readExceptionCode == 0) {
            return;
        }
        readExceptionFromParcel(parcel, parcel.readString(), readExceptionCode);
    }

    public static void readExceptionWithFileNotFoundExceptionFromParcel(Parcel parcel) throws FileNotFoundException {
        int readExceptionCode = readExceptionCode(parcel);
        if (readExceptionCode == 0) {
            return;
        }
        String readString = parcel.readString();
        if (readExceptionCode != 1) {
            readExceptionFromParcel(parcel, readString, readExceptionCode);
            return;
        }
        throw new FileNotFoundException(readString);
    }

    public static void readExceptionWithOperationApplicationExceptionFromParcel(Parcel parcel) throws OperationApplicationException {
        int readExceptionCode = readExceptionCode(parcel);
        if (readExceptionCode == 0) {
            return;
        }
        String readString = parcel.readString();
        if (readExceptionCode != 10) {
            readExceptionFromParcel(parcel, readString, readExceptionCode);
            return;
        }
        throw new OperationApplicationException(readString);
    }

    public static String sqlEscapeString(String str) {
        StringBuilder sb = new StringBuilder();
        appendEscapedSQLString(sb, str);
        return sb.toString();
    }

    public static String stringForQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(str);
        try {
            return stringForQuery(compileStatement, strArr);
        } finally {
            compileStatement.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void writeExceptionToParcel(android.os.Parcel r6, java.lang.Exception r7) {
        /*
            boolean r0 = r7 instanceof java.io.FileNotFoundException
            java.lang.String r1 = "Writing exception to parcel"
            java.lang.String r2 = "WCDB.DatabaseUtils"
            r3 = 0
            r4 = 1
            if (r0 == 0) goto Ld
            r0 = 1
        Lb:
            r5 = 0
            goto L4e
        Ld:
            boolean r0 = r7 instanceof java.lang.IllegalArgumentException
            if (r0 == 0) goto L14
            r0 = 2
        L12:
            r5 = 1
            goto L4e
        L14:
            boolean r0 = r7 instanceof java.lang.UnsupportedOperationException
            if (r0 == 0) goto L1a
            r0 = 3
            goto L12
        L1a:
            boolean r0 = r7 instanceof com.tencent.wcdb.database.SQLiteAbortException
            if (r0 == 0) goto L20
            r0 = 4
            goto L12
        L20:
            boolean r0 = r7 instanceof com.tencent.wcdb.database.SQLiteConstraintException
            if (r0 == 0) goto L26
            r0 = 5
            goto L12
        L26:
            boolean r0 = r7 instanceof com.tencent.wcdb.database.SQLiteDatabaseCorruptException
            if (r0 == 0) goto L2c
            r0 = 6
            goto L12
        L2c:
            boolean r0 = r7 instanceof com.tencent.wcdb.database.SQLiteFullException
            if (r0 == 0) goto L32
            r0 = 7
            goto L12
        L32:
            boolean r0 = r7 instanceof com.tencent.wcdb.database.SQLiteDiskIOException
            if (r0 == 0) goto L39
            r0 = 8
            goto L12
        L39:
            boolean r0 = r7 instanceof com.tencent.wcdb.database.SQLiteException
            if (r0 == 0) goto L40
            r0 = 9
            goto L12
        L40:
            boolean r0 = r7 instanceof android.content.OperationApplicationException
            if (r0 == 0) goto L47
            r0 = 10
            goto L12
        L47:
            boolean r0 = r7 instanceof com.tencent.wcdb.support.OperationCanceledException
            if (r0 == 0) goto L62
            r0 = 11
            goto Lb
        L4e:
            r6.writeInt(r0)
            java.lang.String r0 = r7.getMessage()
            r6.writeString(r0)
            if (r5 == 0) goto L61
            java.lang.Object[] r6 = new java.lang.Object[r4]
            r6[r3] = r7
            com.tencent.wcdb.support.Log.e(r2, r1, r6)
        L61:
            return
        L62:
            r6.writeException(r7)
            java.lang.Object[] r6 = new java.lang.Object[r4]
            r6[r3] = r7
            com.tencent.wcdb.support.Log.e(r2, r1, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.DatabaseUtils.writeExceptionToParcel(android.os.Parcel, java.lang.Exception):void");
    }

    public static void cursorIntToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (!cursor.isNull(columnIndex)) {
            contentValues.put(str2, Integer.valueOf(cursor.getInt(columnIndex)));
        } else {
            contentValues.put(str2, (Integer) null);
        }
    }

    public static void cursorLongToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (!cursor.isNull(columnIndex)) {
            contentValues.put(str2, Long.valueOf(cursor.getLong(columnIndex)));
        } else {
            contentValues.put(str2, (Long) null);
        }
    }

    public static void cursorStringToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        contentValues.put(str2, cursor.getString(cursor.getColumnIndexOrThrow(str)));
    }

    public static void dumpCurrentRow(Cursor cursor, PrintStream printStream) {
        String str;
        String[] columnNames = cursor.getColumnNames();
        printStream.println("" + cursor.getPosition() + " {");
        int length = columnNames.length;
        for (int i2 = 0; i2 < length; i2++) {
            try {
                str = cursor.getString(i2);
            } catch (SQLiteException unused) {
                str = "<unprintable>";
            }
            printStream.println("   " + columnNames[i2] + '=' + str);
        }
        printStream.println("}");
    }

    public static void dumpCursor(Cursor cursor, PrintStream printStream) {
        printStream.println(">>>>> Dumping cursor " + cursor);
        if (cursor != null) {
            int position = cursor.getPosition();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                dumpCurrentRow(cursor, printStream);
            }
            cursor.moveToPosition(position);
        }
        printStream.println("<<<<<");
    }

    public static long queryNumEntries(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        return queryNumEntries(sQLiteDatabase, str, str2, null);
    }

    public static long queryNumEntries(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            str3 = " where " + str2;
        }
        return longForQuery(sQLiteDatabase, "select count(*) from " + str + str3, strArr);
    }

    public static long longForQuery(SQLiteStatement sQLiteStatement, String[] strArr) {
        sQLiteStatement.bindAllArgsAsStrings(strArr);
        return sQLiteStatement.simpleQueryForLong();
    }

    private static final void readExceptionFromParcel(Parcel parcel, String str, int i2) {
        switch (i2) {
            case 2:
                throw new IllegalArgumentException(str);
            case 3:
                throw new UnsupportedOperationException(str);
            case 4:
                throw new SQLiteAbortException(str);
            case 5:
                throw new SQLiteConstraintException(str);
            case 6:
                throw new SQLiteDatabaseCorruptException(str);
            case 7:
                throw new SQLiteFullException(str);
            case 8:
                throw new SQLiteDiskIOException(str);
            case 9:
                throw new SQLiteException(str);
            case 10:
            default:
                parcel.readException(i2, str);
                return;
            case 11:
                throw new OperationCanceledException(str);
        }
    }

    public static String stringForQuery(SQLiteStatement sQLiteStatement, String[] strArr) {
        sQLiteStatement.bindAllArgsAsStrings(strArr);
        return sQLiteStatement.simpleQueryForString();
    }

    public static void createDbFromSqlStatements(Context context, String str, int i2, String str2) {
        createDbFromSqlStatements(context, str, null, null, i2, str2);
    }

    public static void dumpCurrentRow(Cursor cursor, StringBuilder sb) {
        String str;
        String[] columnNames = cursor.getColumnNames();
        sb.append("" + cursor.getPosition() + " {\n");
        int length = columnNames.length;
        for (int i2 = 0; i2 < length; i2++) {
            try {
                str = cursor.getString(i2);
            } catch (SQLiteException unused) {
                str = "<unprintable>";
            }
            sb.append("   " + columnNames[i2] + '=' + str + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        sb.append("}\n");
    }

    public static void dumpCursor(Cursor cursor, StringBuilder sb) {
        sb.append(">>>>> Dumping cursor " + cursor + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        if (cursor != null) {
            int position = cursor.getPosition();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                dumpCurrentRow(cursor, sb);
            }
            cursor.moveToPosition(position);
        }
        sb.append("<<<<<\n");
    }
}
