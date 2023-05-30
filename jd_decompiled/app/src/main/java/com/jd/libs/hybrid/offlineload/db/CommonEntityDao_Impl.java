package com.jd.libs.hybrid.offlineload.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jd.libs.hybrid.offlineload.db.converter.RoomDateConverts;
import com.jd.libs.hybrid.offlineload.entity.CommonEntity;
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.List;

/* loaded from: classes16.dex */
public final class CommonEntityDao_Impl implements CommonEntityDao {
    private final RoomDatabase a;
    private final EntityInsertionAdapter<CommonEntity> b;

    /* renamed from: c  reason: collision with root package name */
    private final RoomDateConverts f5956c = new RoomDateConverts();
    private final EntityDeletionOrUpdateAdapter<CommonEntity> d;

    /* renamed from: e  reason: collision with root package name */
    private final EntityDeletionOrUpdateAdapter<CommonEntity> f5957e;

    /* renamed from: f  reason: collision with root package name */
    private final SharedSQLiteStatement f5958f;

    public CommonEntityDao_Impl(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<CommonEntity>(roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.CommonEntityDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `HybridOfflineCommonEntity` (`id`,`url`,`versionCode`,`md5`,`bConfigCommon`,`available`,`createTimestamp`,`headersMap`,`localfile_path`,`localfile_lastModified`,`localfile_totalSpace`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CommonEntity commonEntity) {
                if (commonEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, commonEntity.getId());
                }
                if (commonEntity.getUrl() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, commonEntity.getUrl());
                }
                supportSQLiteStatement.bindLong(3, commonEntity.getVersionCode());
                if (commonEntity.getMd5() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, commonEntity.getMd5());
                }
                if (commonEntity.getBConfigCommon() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, commonEntity.getBConfigCommon());
                }
                supportSQLiteStatement.bindLong(6, commonEntity.isAvailable() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, commonEntity.getCreateTimestamp());
                String fromStringMap = CommonEntityDao_Impl.this.f5956c.fromStringMap(commonEntity.getHeadersMap());
                if (fromStringMap == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, fromStringMap);
                }
                FileDetail fileDetail = commonEntity.getFileDetail();
                if (fileDetail != null) {
                    if (fileDetail.getPath() == null) {
                        supportSQLiteStatement.bindNull(9);
                    } else {
                        supportSQLiteStatement.bindString(9, fileDetail.getPath());
                    }
                    supportSQLiteStatement.bindLong(10, fileDetail.getLastModified());
                    supportSQLiteStatement.bindLong(11, fileDetail.getTotalSpace());
                    return;
                }
                supportSQLiteStatement.bindNull(9);
                supportSQLiteStatement.bindNull(10);
                supportSQLiteStatement.bindNull(11);
            }
        };
        this.d = new EntityDeletionOrUpdateAdapter<CommonEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.CommonEntityDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `HybridOfflineCommonEntity` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CommonEntity commonEntity) {
                if (commonEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, commonEntity.getId());
                }
            }
        };
        this.f5957e = new EntityDeletionOrUpdateAdapter<CommonEntity>(roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.CommonEntityDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `HybridOfflineCommonEntity` SET `id` = ?,`url` = ?,`versionCode` = ?,`md5` = ?,`bConfigCommon` = ?,`available` = ?,`createTimestamp` = ?,`headersMap` = ?,`localfile_path` = ?,`localfile_lastModified` = ?,`localfile_totalSpace` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CommonEntity commonEntity) {
                if (commonEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, commonEntity.getId());
                }
                if (commonEntity.getUrl() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, commonEntity.getUrl());
                }
                supportSQLiteStatement.bindLong(3, commonEntity.getVersionCode());
                if (commonEntity.getMd5() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, commonEntity.getMd5());
                }
                if (commonEntity.getBConfigCommon() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, commonEntity.getBConfigCommon());
                }
                supportSQLiteStatement.bindLong(6, commonEntity.isAvailable() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, commonEntity.getCreateTimestamp());
                String fromStringMap = CommonEntityDao_Impl.this.f5956c.fromStringMap(commonEntity.getHeadersMap());
                if (fromStringMap == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, fromStringMap);
                }
                FileDetail fileDetail = commonEntity.getFileDetail();
                if (fileDetail != null) {
                    if (fileDetail.getPath() == null) {
                        supportSQLiteStatement.bindNull(9);
                    } else {
                        supportSQLiteStatement.bindString(9, fileDetail.getPath());
                    }
                    supportSQLiteStatement.bindLong(10, fileDetail.getLastModified());
                    supportSQLiteStatement.bindLong(11, fileDetail.getTotalSpace());
                } else {
                    supportSQLiteStatement.bindNull(9);
                    supportSQLiteStatement.bindNull(10);
                    supportSQLiteStatement.bindNull(11);
                }
                if (commonEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, commonEntity.getId());
                }
            }
        };
        this.f5958f = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.CommonEntityDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM HybridOfflineCommonEntity";
            }
        };
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public void delete(CommonEntity commonEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.d.handle(commonEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public void deleteAll() {
        this.a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f5958f.acquire();
        this.a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.f5958f.release(acquire);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00cd  */
    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.jd.libs.hybrid.offlineload.entity.CommonEntity> getAll() {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.CommonEntityDao_Impl.getAll():java.util.List");
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public CommonEntity getById(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `localfile_path`, `localfile_lastModified`, `localfile_totalSpace`, `HybridOfflineCommonEntity`.`id` AS `id`, `HybridOfflineCommonEntity`.`url` AS `url`, `HybridOfflineCommonEntity`.`versionCode` AS `versionCode`, `HybridOfflineCommonEntity`.`md5` AS `md5`, `HybridOfflineCommonEntity`.`bConfigCommon` AS `bConfigCommon`, `HybridOfflineCommonEntity`.`available` AS `available`, `HybridOfflineCommonEntity`.`createTimestamp` AS `createTimestamp`, `HybridOfflineCommonEntity`.`headersMap` AS `headersMap` FROM HybridOfflineCommonEntity WHERE id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        CommonEntity commonEntity = null;
        FileDetail fileDetail = null;
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "localfile_path");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "localfile_lastModified");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "localfile_totalSpace");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "bConfigCommon");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "headersMap");
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow));
                    try {
                        fileDetail.setLastModified(query.getLong(columnIndexOrThrow2));
                        fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow3));
                    } catch (Throwable th) {
                        th = th;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                }
                CommonEntity commonEntity2 = new CommonEntity();
                commonEntity2.setId(query.getString(columnIndexOrThrow4));
                commonEntity2.setUrl(query.getString(columnIndexOrThrow5));
                commonEntity2.setVersionCode(query.getInt(columnIndexOrThrow6));
                commonEntity2.setMd5(query.getString(columnIndexOrThrow7));
                commonEntity2.setBConfigCommon(query.getString(columnIndexOrThrow8));
                commonEntity2.setAvailable(query.getInt(columnIndexOrThrow9) != 0);
                commonEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow10));
                try {
                    commonEntity2.setHeadersMap(this.f5956c.fromString(query.getString(columnIndexOrThrow11)));
                    commonEntity2.setFileDetail(fileDetail);
                    commonEntity = commonEntity2;
                } catch (Throwable th2) {
                    th = th2;
                    query.close();
                    acquire.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return commonEntity;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public CommonEntity getByUrl(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `localfile_path`, `localfile_lastModified`, `localfile_totalSpace`, `HybridOfflineCommonEntity`.`id` AS `id`, `HybridOfflineCommonEntity`.`url` AS `url`, `HybridOfflineCommonEntity`.`versionCode` AS `versionCode`, `HybridOfflineCommonEntity`.`md5` AS `md5`, `HybridOfflineCommonEntity`.`bConfigCommon` AS `bConfigCommon`, `HybridOfflineCommonEntity`.`available` AS `available`, `HybridOfflineCommonEntity`.`createTimestamp` AS `createTimestamp`, `HybridOfflineCommonEntity`.`headersMap` AS `headersMap` FROM HybridOfflineCommonEntity WHERE url=? LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        CommonEntity commonEntity = null;
        FileDetail fileDetail = null;
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "localfile_path");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "localfile_lastModified");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "localfile_totalSpace");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "bConfigCommon");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "headersMap");
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow));
                    try {
                        fileDetail.setLastModified(query.getLong(columnIndexOrThrow2));
                        fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow3));
                    } catch (Throwable th) {
                        th = th;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                }
                CommonEntity commonEntity2 = new CommonEntity();
                commonEntity2.setId(query.getString(columnIndexOrThrow4));
                commonEntity2.setUrl(query.getString(columnIndexOrThrow5));
                commonEntity2.setVersionCode(query.getInt(columnIndexOrThrow6));
                commonEntity2.setMd5(query.getString(columnIndexOrThrow7));
                commonEntity2.setBConfigCommon(query.getString(columnIndexOrThrow8));
                commonEntity2.setAvailable(query.getInt(columnIndexOrThrow9) != 0);
                commonEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow10));
                try {
                    commonEntity2.setHeadersMap(this.f5956c.fromString(query.getString(columnIndexOrThrow11)));
                    commonEntity2.setFileDetail(fileDetail);
                    commonEntity = commonEntity2;
                } catch (Throwable th2) {
                    th = th2;
                    query.close();
                    acquire.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return commonEntity;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public CommonEntity getOneAvailableByUrl(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `localfile_path`, `localfile_lastModified`, `localfile_totalSpace`, `HybridOfflineCommonEntity`.`id` AS `id`, `HybridOfflineCommonEntity`.`url` AS `url`, `HybridOfflineCommonEntity`.`versionCode` AS `versionCode`, `HybridOfflineCommonEntity`.`md5` AS `md5`, `HybridOfflineCommonEntity`.`bConfigCommon` AS `bConfigCommon`, `HybridOfflineCommonEntity`.`available` AS `available`, `HybridOfflineCommonEntity`.`createTimestamp` AS `createTimestamp`, `HybridOfflineCommonEntity`.`headersMap` AS `headersMap` FROM HybridOfflineCommonEntity WHERE url=? AND available = 1 LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        CommonEntity commonEntity = null;
        FileDetail fileDetail = null;
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "localfile_path");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "localfile_lastModified");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "localfile_totalSpace");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "bConfigCommon");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "headersMap");
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow));
                    try {
                        fileDetail.setLastModified(query.getLong(columnIndexOrThrow2));
                        fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow3));
                    } catch (Throwable th) {
                        th = th;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                }
                CommonEntity commonEntity2 = new CommonEntity();
                commonEntity2.setId(query.getString(columnIndexOrThrow4));
                commonEntity2.setUrl(query.getString(columnIndexOrThrow5));
                commonEntity2.setVersionCode(query.getInt(columnIndexOrThrow6));
                commonEntity2.setMd5(query.getString(columnIndexOrThrow7));
                commonEntity2.setBConfigCommon(query.getString(columnIndexOrThrow8));
                commonEntity2.setAvailable(query.getInt(columnIndexOrThrow9) != 0);
                commonEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow10));
                try {
                    commonEntity2.setHeadersMap(this.f5956c.fromString(query.getString(columnIndexOrThrow11)));
                    commonEntity2.setFileDetail(fileDetail);
                    commonEntity = commonEntity2;
                } catch (Throwable th2) {
                    th = th2;
                    query.close();
                    acquire.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return commonEntity;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public void save(List<CommonEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public void update(CommonEntity commonEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f5957e.handle(commonEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public void delete(List<CommonEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.d.handleMultiple(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public void save(CommonEntity... commonEntityArr) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(commonEntityArr);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public void update(List<CommonEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f5957e.handleMultiple(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public CommonEntity getByUrl(String str, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `localfile_path`, `localfile_lastModified`, `localfile_totalSpace`, `HybridOfflineCommonEntity`.`id` AS `id`, `HybridOfflineCommonEntity`.`url` AS `url`, `HybridOfflineCommonEntity`.`versionCode` AS `versionCode`, `HybridOfflineCommonEntity`.`md5` AS `md5`, `HybridOfflineCommonEntity`.`bConfigCommon` AS `bConfigCommon`, `HybridOfflineCommonEntity`.`available` AS `available`, `HybridOfflineCommonEntity`.`createTimestamp` AS `createTimestamp`, `HybridOfflineCommonEntity`.`headersMap` AS `headersMap` FROM HybridOfflineCommonEntity WHERE url=? AND versionCode=? LIMIT 1", 2);
        boolean z = true;
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        this.a.assertNotSuspendingTransaction();
        CommonEntity commonEntity = null;
        FileDetail fileDetail = null;
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "localfile_path");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "localfile_lastModified");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "localfile_totalSpace");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "bConfigCommon");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "headersMap");
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow));
                    try {
                        fileDetail.setLastModified(query.getLong(columnIndexOrThrow2));
                        fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow3));
                    } catch (Throwable th) {
                        th = th;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                }
                CommonEntity commonEntity2 = new CommonEntity();
                commonEntity2.setId(query.getString(columnIndexOrThrow4));
                commonEntity2.setUrl(query.getString(columnIndexOrThrow5));
                commonEntity2.setVersionCode(query.getInt(columnIndexOrThrow6));
                commonEntity2.setMd5(query.getString(columnIndexOrThrow7));
                commonEntity2.setBConfigCommon(query.getString(columnIndexOrThrow8));
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                commonEntity2.setAvailable(z);
                commonEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow10));
                try {
                    commonEntity2.setHeadersMap(this.f5956c.fromString(query.getString(columnIndexOrThrow11)));
                    commonEntity2.setFileDetail(fileDetail);
                    commonEntity = commonEntity2;
                } catch (Throwable th2) {
                    th = th2;
                    query.close();
                    acquire.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return commonEntity;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonEntityDao
    public CommonEntity getOneAvailableByUrl(String str, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `localfile_path`, `localfile_lastModified`, `localfile_totalSpace`, `HybridOfflineCommonEntity`.`id` AS `id`, `HybridOfflineCommonEntity`.`url` AS `url`, `HybridOfflineCommonEntity`.`versionCode` AS `versionCode`, `HybridOfflineCommonEntity`.`md5` AS `md5`, `HybridOfflineCommonEntity`.`bConfigCommon` AS `bConfigCommon`, `HybridOfflineCommonEntity`.`available` AS `available`, `HybridOfflineCommonEntity`.`createTimestamp` AS `createTimestamp`, `HybridOfflineCommonEntity`.`headersMap` AS `headersMap` FROM HybridOfflineCommonEntity WHERE url=? AND versionCode=? AND available = 1 LIMIT 1", 2);
        boolean z = true;
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        this.a.assertNotSuspendingTransaction();
        CommonEntity commonEntity = null;
        FileDetail fileDetail = null;
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "localfile_path");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "localfile_lastModified");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "localfile_totalSpace");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "url");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "versionCode");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "bConfigCommon");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "headersMap");
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow) || !query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3)) {
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow));
                    try {
                        fileDetail.setLastModified(query.getLong(columnIndexOrThrow2));
                        fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow3));
                    } catch (Throwable th) {
                        th = th;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                }
                CommonEntity commonEntity2 = new CommonEntity();
                commonEntity2.setId(query.getString(columnIndexOrThrow4));
                commonEntity2.setUrl(query.getString(columnIndexOrThrow5));
                commonEntity2.setVersionCode(query.getInt(columnIndexOrThrow6));
                commonEntity2.setMd5(query.getString(columnIndexOrThrow7));
                commonEntity2.setBConfigCommon(query.getString(columnIndexOrThrow8));
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                commonEntity2.setAvailable(z);
                commonEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow10));
                try {
                    commonEntity2.setHeadersMap(this.f5956c.fromString(query.getString(columnIndexOrThrow11)));
                    commonEntity2.setFileDetail(fileDetail);
                    commonEntity = commonEntity2;
                } catch (Throwable th2) {
                    th = th2;
                    query.close();
                    acquire.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return commonEntity;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
