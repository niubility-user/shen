package androidx.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SQLiteCopyOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    @Nullable
    private final String mCopyFromAssetPath;
    @Nullable
    private final File mCopyFromFile;
    @NonNull
    private final SupportSQLiteOpenHelper.Factory mDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteCopyOpenHelperFactory(@Nullable String str, @Nullable File file, @NonNull SupportSQLiteOpenHelper.Factory factory) {
        this.mCopyFromAssetPath = str;
        this.mCopyFromFile = file;
        this.mDelegate = factory;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new SQLiteCopyOpenHelper(configuration.context, this.mCopyFromAssetPath, this.mCopyFromFile, configuration.callback.version, this.mDelegate.create(configuration));
    }
}
