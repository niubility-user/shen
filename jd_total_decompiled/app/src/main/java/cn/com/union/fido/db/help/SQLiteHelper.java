package cn.com.union.fido.db.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes.dex */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fidounion.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteHelper mInstance;
    private final String CREATE_TABLE_AUTHENTICATION;
    private final String CREATE_TABLE_SIGNCOUNTER;
    private final String CREATE_TABLE_USERKEY;
    public final String TABLE_AUTHENTICATION;
    public final String TABLE_SIGNCOUNTER;
    public final String TABLE_USERKEY;

    private SQLiteHelper(Context context) {
        this(context, DATABASE_NAME, null, 1);
    }

    private SQLiteHelper(Context context, int i2) {
        this(context, DATABASE_NAME, null, i2);
    }

    private SQLiteHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
        super(context, str, cursorFactory, i2);
        this.TABLE_USERKEY = "userkey";
        this.TABLE_AUTHENTICATION = "authentication";
        this.TABLE_SIGNCOUNTER = "signcounter";
        this.CREATE_TABLE_USERKEY = "CREATE TABLE userkey(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,keyhandle VARCHAR(200) NOT NULL,pubkey VARCHAR(200) NOT NULL,privkey VARCHAR(200) NOT NULL) ";
        this.CREATE_TABLE_AUTHENTICATION = "CREATE TABLE authentication(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, callerID VARCHAR, appID VARCHAR, keyHandle VARCHAR, keyID VARCHAR, currentTimestamp VARCHAR, status VARCHAR, aaid VARCHAR, userName VARCHAR)";
        this.CREATE_TABLE_SIGNCOUNTER = "CREATE TABLE signcounter(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, aaid VARCHAR, keyID VARCHAR, userName VARCHAR,signCounter INTEGER)";
    }

    public static synchronized SQLiteHelper getInstance(Context context) {
        SQLiteHelper sQLiteHelper;
        synchronized (SQLiteHelper.class) {
            if (mInstance == null) {
                mInstance = new SQLiteHelper(context);
            }
            sQLiteHelper = mInstance;
        }
        return sQLiteHelper;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE userkey(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,keyhandle VARCHAR(200) NOT NULL,pubkey VARCHAR(200) NOT NULL,privkey VARCHAR(200) NOT NULL) ");
        sQLiteDatabase.execSQL("CREATE TABLE authentication(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, callerID VARCHAR, appID VARCHAR, keyHandle VARCHAR, keyID VARCHAR, currentTimestamp VARCHAR, status VARCHAR, aaid VARCHAR, userName VARCHAR)");
        sQLiteDatabase.execSQL("CREATE TABLE signcounter(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, aaid VARCHAR, keyID VARCHAR, userName VARCHAR,signCounter INTEGER)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public void recreateAuthenticationTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE authentication(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, callerID VARCHAR, appID VARCHAR, keyHandle VARCHAR, keyID VARCHAR, currentTimestamp VARCHAR, status VARCHAR, aaid VARCHAR, userName VARCHAR)");
        sQLiteDatabase.execSQL("CREATE TABLE authentication(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, callerID VARCHAR, appID VARCHAR, keyHandle VARCHAR, keyID VARCHAR, currentTimestamp VARCHAR, status VARCHAR, aaid VARCHAR, userName VARCHAR)");
    }

    public void recreateSigncounterTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE signcounter(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, aaid VARCHAR, keyID VARCHAR, userName VARCHAR,signCounter INTEGER)");
        sQLiteDatabase.execSQL("CREATE TABLE signcounter(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, aaid VARCHAR, keyID VARCHAR, userName VARCHAR,signCounter INTEGER)");
    }

    public void recreateUserKeyTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS userkey");
        sQLiteDatabase.execSQL("CREATE TABLE userkey(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,keyhandle VARCHAR(200) NOT NULL,pubkey VARCHAR(200) NOT NULL,privkey VARCHAR(200) NOT NULL) ");
    }
}
