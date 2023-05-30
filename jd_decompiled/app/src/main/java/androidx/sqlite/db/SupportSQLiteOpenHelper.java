package androidx.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.sqlite.db.SupportSQLiteCompat;
import com.tencent.wcdb.database.SQLiteDatabaseConfiguration;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public interface SupportSQLiteOpenHelper extends Closeable {

    /* loaded from: classes.dex */
    public static abstract class Callback {
        private static final String TAG = "SupportSQLite";
        public final int version;

        public Callback(int i2) {
            this.version = i2;
        }

        private void deleteDatabaseFile(String str) {
            if (str.equalsIgnoreCase(SQLiteDatabaseConfiguration.MEMORY_DB_PATH) || str.trim().length() == 0) {
                return;
            }
            String str2 = "deleting the database file: " + str;
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    SupportSQLiteCompat.Api16Impl.deleteDatabase(new File(str));
                } else if (!new File(str).delete()) {
                    String str3 = "Could not delete the database file " + str;
                }
            } catch (Exception unused) {
            }
        }

        public void onConfigure(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onCorruption(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            String str = "Corruption reported by sqlite on database: " + supportSQLiteDatabase.getPath();
            if (!supportSQLiteDatabase.isOpen()) {
                deleteDatabaseFile(supportSQLiteDatabase.getPath());
                return;
            }
            List<Pair<String, String>> list = null;
            try {
                try {
                    list = supportSQLiteDatabase.getAttachedDbs();
                } finally {
                    if (list != null) {
                        Iterator<Pair<String, String>> it = list.iterator();
                        while (it.hasNext()) {
                            deleteDatabaseFile((String) it.next().second);
                        }
                    } else {
                        deleteDatabaseFile(supportSQLiteDatabase.getPath());
                    }
                }
            } catch (SQLiteException unused) {
            }
            try {
                supportSQLiteDatabase.close();
            } catch (IOException unused2) {
            }
        }

        public abstract void onCreate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase);

        public void onDowngrade(@NonNull SupportSQLiteDatabase supportSQLiteDatabase, int i2, int i3) {
            throw new SQLiteException("Can't downgrade database from version " + i2 + " to " + i3);
        }

        public void onOpen(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public abstract void onUpgrade(@NonNull SupportSQLiteDatabase supportSQLiteDatabase, int i2, int i3);
    }

    /* loaded from: classes.dex */
    public static class Configuration {
        @NonNull
        public final Callback callback;
        @NonNull
        public final Context context;
        @Nullable
        public final String name;
        public final boolean useNoBackupDirectory;

        /* loaded from: classes.dex */
        public static class Builder {
            Callback mCallback;
            Context mContext;
            String mName;
            boolean mUseNoBackupDirectory;

            Builder(@NonNull Context context) {
                this.mContext = context;
            }

            @NonNull
            public Configuration build() {
                if (this.mCallback != null) {
                    if (this.mContext != null) {
                        if (this.mUseNoBackupDirectory && TextUtils.isEmpty(this.mName)) {
                            throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                        }
                        return new Configuration(this.mContext, this.mName, this.mCallback, this.mUseNoBackupDirectory);
                    }
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }
                throw new IllegalArgumentException("Must set a callback to create the configuration.");
            }

            @NonNull
            public Builder callback(@NonNull Callback callback) {
                this.mCallback = callback;
                return this;
            }

            @NonNull
            public Builder name(@Nullable String str) {
                this.mName = str;
                return this;
            }

            @NonNull
            public Builder noBackupDirectory(boolean z) {
                this.mUseNoBackupDirectory = z;
                return this;
            }
        }

        Configuration(@NonNull Context context, @Nullable String str, @NonNull Callback callback) {
            this(context, str, callback, false);
        }

        @NonNull
        public static Builder builder(@NonNull Context context) {
            return new Builder(context);
        }

        Configuration(@NonNull Context context, @Nullable String str, @NonNull Callback callback, boolean z) {
            this.context = context;
            this.name = str;
            this.callback = callback;
            this.useNoBackupDirectory = z;
        }
    }

    /* loaded from: classes.dex */
    public interface Factory {
        @NonNull
        SupportSQLiteOpenHelper create(@NonNull Configuration configuration);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    @Nullable
    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    @RequiresApi(api = 16)
    void setWriteAheadLoggingEnabled(boolean z);
}
