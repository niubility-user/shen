package com.jingdong.manto.provider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jingdong.manto.pkg.db.entity.AppCommonKVDataEntity;
import com.jingdong.manto.pkg.db.entity.CardActivityEntity;
import com.jingdong.manto.pkg.db.entity.LocalExtAuthEntity;
import com.jingdong.manto.pkg.db.entity.MantoAuthEntity;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.pkg.db.entity.StorageEntity;

/* loaded from: classes16.dex */
public class b extends SQLiteOpenHelper {
    private static volatile b a;

    private b(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 11);
    }

    public static b a(Context context, String str) {
        if (a != null && !a.getDatabaseName().equals(str)) {
            synchronized (b.class) {
                try {
                    a.close();
                } catch (Exception unused) {
                }
                a = null;
            }
        }
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context, str);
                }
            }
        }
        return a;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        a.a(CardActivityEntity.class, sQLiteDatabase);
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN venderId Text;");
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN permissions INTEGER;");
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN configJson Text;");
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN apiWhiteList Text;");
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN apiBlackList Text;");
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        a.a(LocalExtAuthEntity.class, sQLiteDatabase);
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN templateId Text;");
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN templateVersion Text;");
    }

    private void g(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN subPkgInfos Text;");
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN zipUrl Text;");
        sQLiteDatabase.execSQL("ALTER TABLE pkgDetail ADD COLUMN charteredUrl Text;");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Class[] clsArr = {PkgDetailEntity.class, PkgCollectEntity.class, PkgHistoryEntity.class, MantoAuthEntity.class, StorageEntity.class, AppCommonKVDataEntity.class, LocalExtAuthEntity.class, CardActivityEntity.class};
        for (int i2 = 0; i2 < 8; i2++) {
            a.a(clsArr[i2], sQLiteDatabase);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        synchronized (this) {
            while (true) {
                i2++;
                if (i2 <= i3) {
                    switch (i2) {
                        case 4:
                            h(sQLiteDatabase);
                            break;
                        case 5:
                            c(sQLiteDatabase);
                            break;
                        case 6:
                            d(sQLiteDatabase);
                            break;
                        case 7:
                            e(sQLiteDatabase);
                            break;
                        case 8:
                            f(sQLiteDatabase);
                            break;
                        case 9:
                            g(sQLiteDatabase);
                            break;
                        case 10:
                            a(sQLiteDatabase);
                            break;
                        case 11:
                            b(sQLiteDatabase);
                            break;
                    }
                }
            }
        }
    }
}
