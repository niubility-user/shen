package com.jd.libs.hybrid.preload.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jd.libs.hybrid.preload.db.converter.RoomJdJsonObjConverts;
import com.jd.libs.hybrid.preload.db.converter.RoomListStrConverts;
import com.jd.libs.hybrid.preload.db.converter.RoomMapConverts;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public final class PreloadInfoDao_Impl implements PreloadInfoDao {
    private final RoomDatabase a;
    private final EntityInsertionAdapter<PreloadInfoEntity> b;

    /* renamed from: c  reason: collision with root package name */
    private final RoomMapConverts f6129c = new RoomMapConverts();
    private final RoomJdJsonObjConverts d = new RoomJdJsonObjConverts();

    /* renamed from: e  reason: collision with root package name */
    private final RoomListStrConverts f6130e = new RoomListStrConverts();

    /* renamed from: f  reason: collision with root package name */
    private final EntityDeletionOrUpdateAdapter<PreloadInfoEntity> f6131f;

    /* renamed from: g  reason: collision with root package name */
    private final EntityDeletionOrUpdateAdapter<PreloadInfoEntity> f6132g;

    /* renamed from: h  reason: collision with root package name */
    private final SharedSQLiteStatement f6133h;

    public PreloadInfoDao_Impl(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<PreloadInfoEntity>(roomDatabase) { // from class: com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `HybridPreloadInfo` (`appid`,`url`,`originalUrl`,`requestUrl`,`requestType`,`functionId`,`method`,`header`,`params`,`body`,`extraKeys`,`mappings`,`urlParamsState`,`appMin`,`appMax`,`originalUrlType`,`bConfig`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PreloadInfoEntity preloadInfoEntity) {
                if (preloadInfoEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, preloadInfoEntity.getAppid());
                }
                if (preloadInfoEntity.getUrl() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, preloadInfoEntity.getUrl());
                }
                if (preloadInfoEntity.getOriginalUrl() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, preloadInfoEntity.getOriginalUrl());
                }
                if (preloadInfoEntity.getRequestUrl() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, preloadInfoEntity.getRequestUrl());
                }
                if (preloadInfoEntity.getRequestType() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, preloadInfoEntity.getRequestType());
                }
                if (preloadInfoEntity.getFunctionId() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, preloadInfoEntity.getFunctionId());
                }
                if (preloadInfoEntity.getMethod() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, preloadInfoEntity.getMethod());
                }
                String roomMapConverts = PreloadInfoDao_Impl.this.f6129c.toString(preloadInfoEntity.getHeader());
                if (roomMapConverts == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, roomMapConverts);
                }
                String roomMapConverts2 = PreloadInfoDao_Impl.this.f6129c.toString(preloadInfoEntity.getParams());
                if (roomMapConverts2 == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, roomMapConverts2);
                }
                String roomJdJsonObjConverts = PreloadInfoDao_Impl.this.d.toString(preloadInfoEntity.getBody());
                if (roomJdJsonObjConverts == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, roomJdJsonObjConverts);
                }
                String roomListStrConverts = PreloadInfoDao_Impl.this.f6130e.toString(preloadInfoEntity.getExtraKeys());
                if (roomListStrConverts == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, roomListStrConverts);
                }
                String roomMapConverts3 = PreloadInfoDao_Impl.this.f6129c.toString(preloadInfoEntity.getMappings());
                if (roomMapConverts3 == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, roomMapConverts3);
                }
                supportSQLiteStatement.bindLong(13, preloadInfoEntity.getUrlParamsState());
                if (preloadInfoEntity.getAppMin() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, preloadInfoEntity.getAppMin());
                }
                if (preloadInfoEntity.getAppMax() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, preloadInfoEntity.getAppMax());
                }
                if (preloadInfoEntity.getOriginalUrlType() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, preloadInfoEntity.getOriginalUrlType());
                }
                if (preloadInfoEntity.getBConfig() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, preloadInfoEntity.getBConfig());
                }
            }
        };
        this.f6131f = new EntityDeletionOrUpdateAdapter<PreloadInfoEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `HybridPreloadInfo` WHERE `appid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PreloadInfoEntity preloadInfoEntity) {
                if (preloadInfoEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, preloadInfoEntity.getAppid());
                }
            }
        };
        this.f6132g = new EntityDeletionOrUpdateAdapter<PreloadInfoEntity>(roomDatabase) { // from class: com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `HybridPreloadInfo` SET `appid` = ?,`url` = ?,`originalUrl` = ?,`requestUrl` = ?,`requestType` = ?,`functionId` = ?,`method` = ?,`header` = ?,`params` = ?,`body` = ?,`extraKeys` = ?,`mappings` = ?,`urlParamsState` = ?,`appMin` = ?,`appMax` = ?,`originalUrlType` = ?,`bConfig` = ? WHERE `appid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PreloadInfoEntity preloadInfoEntity) {
                if (preloadInfoEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, preloadInfoEntity.getAppid());
                }
                if (preloadInfoEntity.getUrl() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, preloadInfoEntity.getUrl());
                }
                if (preloadInfoEntity.getOriginalUrl() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, preloadInfoEntity.getOriginalUrl());
                }
                if (preloadInfoEntity.getRequestUrl() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, preloadInfoEntity.getRequestUrl());
                }
                if (preloadInfoEntity.getRequestType() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, preloadInfoEntity.getRequestType());
                }
                if (preloadInfoEntity.getFunctionId() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, preloadInfoEntity.getFunctionId());
                }
                if (preloadInfoEntity.getMethod() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, preloadInfoEntity.getMethod());
                }
                String roomMapConverts = PreloadInfoDao_Impl.this.f6129c.toString(preloadInfoEntity.getHeader());
                if (roomMapConverts == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, roomMapConverts);
                }
                String roomMapConverts2 = PreloadInfoDao_Impl.this.f6129c.toString(preloadInfoEntity.getParams());
                if (roomMapConverts2 == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, roomMapConverts2);
                }
                String roomJdJsonObjConverts = PreloadInfoDao_Impl.this.d.toString(preloadInfoEntity.getBody());
                if (roomJdJsonObjConverts == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, roomJdJsonObjConverts);
                }
                String roomListStrConverts = PreloadInfoDao_Impl.this.f6130e.toString(preloadInfoEntity.getExtraKeys());
                if (roomListStrConverts == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, roomListStrConverts);
                }
                String roomMapConverts3 = PreloadInfoDao_Impl.this.f6129c.toString(preloadInfoEntity.getMappings());
                if (roomMapConverts3 == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, roomMapConverts3);
                }
                supportSQLiteStatement.bindLong(13, preloadInfoEntity.getUrlParamsState());
                if (preloadInfoEntity.getAppMin() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, preloadInfoEntity.getAppMin());
                }
                if (preloadInfoEntity.getAppMax() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, preloadInfoEntity.getAppMax());
                }
                if (preloadInfoEntity.getOriginalUrlType() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, preloadInfoEntity.getOriginalUrlType());
                }
                if (preloadInfoEntity.getBConfig() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, preloadInfoEntity.getBConfig());
                }
                if (preloadInfoEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, preloadInfoEntity.getAppid());
                }
            }
        };
        this.f6133h = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM hybridpreloadinfo";
            }
        };
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public void delete(PreloadInfoEntity preloadInfoEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f6131f.handle(preloadInfoEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public void deleteAll() {
        this.a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f6133h.acquire();
        this.a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.f6133h.release(acquire);
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public List<PreloadInfoEntity> getAll() {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        PreloadInfoDao_Impl preloadInfoDao_Impl = this;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `HybridPreloadInfo`.`appid` AS `appid`, `HybridPreloadInfo`.`url` AS `url`, `HybridPreloadInfo`.`originalUrl` AS `originalUrl`, `HybridPreloadInfo`.`requestUrl` AS `requestUrl`, `HybridPreloadInfo`.`requestType` AS `requestType`, `HybridPreloadInfo`.`functionId` AS `functionId`, `HybridPreloadInfo`.`method` AS `method`, `HybridPreloadInfo`.`header` AS `header`, `HybridPreloadInfo`.`params` AS `params`, `HybridPreloadInfo`.`body` AS `body`, `HybridPreloadInfo`.`extraKeys` AS `extraKeys`, `HybridPreloadInfo`.`mappings` AS `mappings`, `HybridPreloadInfo`.`urlParamsState` AS `urlParamsState`, `HybridPreloadInfo`.`appMin` AS `appMin`, `HybridPreloadInfo`.`appMax` AS `appMax`, `HybridPreloadInfo`.`originalUrlType` AS `originalUrlType`, `HybridPreloadInfo`.`bConfig` AS `bConfig` FROM HybridPreloadInfo", 0);
        preloadInfoDao_Impl.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(preloadInfoDao_Impl.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "appid");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "url");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requestUrl");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requestType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "functionId");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "method");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "header");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "params");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "body");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "extraKeys");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "mappings");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "urlParamsState");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int i2 = columnIndexOrThrow13;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PreloadInfoEntity preloadInfoEntity = new PreloadInfoEntity();
                ArrayList arrayList2 = arrayList;
                preloadInfoEntity.setAppid(query.getString(columnIndexOrThrow));
                preloadInfoEntity.setUrl(query.getString(columnIndexOrThrow2));
                preloadInfoEntity.setOriginalUrl(query.getString(columnIndexOrThrow3));
                preloadInfoEntity.setRequestUrl(query.getString(columnIndexOrThrow4));
                preloadInfoEntity.setRequestType(query.getString(columnIndexOrThrow5));
                preloadInfoEntity.setFunctionId(query.getString(columnIndexOrThrow6));
                preloadInfoEntity.setMethod(query.getString(columnIndexOrThrow7));
                int i3 = columnIndexOrThrow;
                preloadInfoEntity.setHeader(preloadInfoDao_Impl.f6129c.toMap(query.getString(columnIndexOrThrow8)));
                preloadInfoEntity.setParams(preloadInfoDao_Impl.f6129c.toMap(query.getString(columnIndexOrThrow9)));
                preloadInfoEntity.setBody(preloadInfoDao_Impl.d.toJson(query.getString(columnIndexOrThrow10)));
                preloadInfoEntity.setExtraKeys(preloadInfoDao_Impl.f6130e.toArray(query.getString(columnIndexOrThrow11)));
                preloadInfoEntity.setMappings(preloadInfoDao_Impl.f6129c.toMap(query.getString(columnIndexOrThrow12)));
                int i4 = i2;
                preloadInfoEntity.setUrlParamsState(query.getInt(i4));
                i2 = i4;
                int i5 = columnIndexOrThrow14;
                preloadInfoEntity.setAppMin(query.getString(i5));
                int i6 = columnIndexOrThrow15;
                preloadInfoEntity.setAppMax(query.getString(i6));
                columnIndexOrThrow15 = i6;
                int i7 = columnIndexOrThrow16;
                preloadInfoEntity.setOriginalUrlType(query.getString(i7));
                columnIndexOrThrow16 = i7;
                int i8 = columnIndexOrThrow17;
                preloadInfoEntity.setBConfig(query.getString(i8));
                arrayList2.add(preloadInfoEntity);
                columnIndexOrThrow17 = i8;
                columnIndexOrThrow14 = i5;
                columnIndexOrThrow = i3;
                arrayList = arrayList2;
                preloadInfoDao_Impl = this;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public List<PreloadInfoEntity> getAllByUrlRegexp() {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        PreloadInfoDao_Impl preloadInfoDao_Impl = this;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `HybridPreloadInfo`.`appid` AS `appid`, `HybridPreloadInfo`.`url` AS `url`, `HybridPreloadInfo`.`originalUrl` AS `originalUrl`, `HybridPreloadInfo`.`requestUrl` AS `requestUrl`, `HybridPreloadInfo`.`requestType` AS `requestType`, `HybridPreloadInfo`.`functionId` AS `functionId`, `HybridPreloadInfo`.`method` AS `method`, `HybridPreloadInfo`.`header` AS `header`, `HybridPreloadInfo`.`params` AS `params`, `HybridPreloadInfo`.`body` AS `body`, `HybridPreloadInfo`.`extraKeys` AS `extraKeys`, `HybridPreloadInfo`.`mappings` AS `mappings`, `HybridPreloadInfo`.`urlParamsState` AS `urlParamsState`, `HybridPreloadInfo`.`appMin` AS `appMin`, `HybridPreloadInfo`.`appMax` AS `appMax`, `HybridPreloadInfo`.`originalUrlType` AS `originalUrlType`, `HybridPreloadInfo`.`bConfig` AS `bConfig` FROM HybridPreloadInfo WHERE originalUrlType='2' LIMIT 1", 0);
        preloadInfoDao_Impl.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(preloadInfoDao_Impl.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "appid");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "url");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requestUrl");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requestType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "functionId");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "method");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "header");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "params");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "body");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "extraKeys");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "mappings");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "urlParamsState");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int i2 = columnIndexOrThrow13;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PreloadInfoEntity preloadInfoEntity = new PreloadInfoEntity();
                ArrayList arrayList2 = arrayList;
                preloadInfoEntity.setAppid(query.getString(columnIndexOrThrow));
                preloadInfoEntity.setUrl(query.getString(columnIndexOrThrow2));
                preloadInfoEntity.setOriginalUrl(query.getString(columnIndexOrThrow3));
                preloadInfoEntity.setRequestUrl(query.getString(columnIndexOrThrow4));
                preloadInfoEntity.setRequestType(query.getString(columnIndexOrThrow5));
                preloadInfoEntity.setFunctionId(query.getString(columnIndexOrThrow6));
                preloadInfoEntity.setMethod(query.getString(columnIndexOrThrow7));
                int i3 = columnIndexOrThrow;
                preloadInfoEntity.setHeader(preloadInfoDao_Impl.f6129c.toMap(query.getString(columnIndexOrThrow8)));
                preloadInfoEntity.setParams(preloadInfoDao_Impl.f6129c.toMap(query.getString(columnIndexOrThrow9)));
                preloadInfoEntity.setBody(preloadInfoDao_Impl.d.toJson(query.getString(columnIndexOrThrow10)));
                preloadInfoEntity.setExtraKeys(preloadInfoDao_Impl.f6130e.toArray(query.getString(columnIndexOrThrow11)));
                preloadInfoEntity.setMappings(preloadInfoDao_Impl.f6129c.toMap(query.getString(columnIndexOrThrow12)));
                int i4 = i2;
                preloadInfoEntity.setUrlParamsState(query.getInt(i4));
                i2 = i4;
                int i5 = columnIndexOrThrow14;
                preloadInfoEntity.setAppMin(query.getString(i5));
                int i6 = columnIndexOrThrow15;
                preloadInfoEntity.setAppMax(query.getString(i6));
                columnIndexOrThrow15 = i6;
                int i7 = columnIndexOrThrow16;
                preloadInfoEntity.setOriginalUrlType(query.getString(i7));
                columnIndexOrThrow16 = i7;
                int i8 = columnIndexOrThrow17;
                preloadInfoEntity.setBConfig(query.getString(i8));
                arrayList2.add(preloadInfoEntity);
                columnIndexOrThrow17 = i8;
                columnIndexOrThrow14 = i5;
                columnIndexOrThrow = i3;
                arrayList = arrayList2;
                preloadInfoDao_Impl = this;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public PreloadInfoEntity getById(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        PreloadInfoEntity preloadInfoEntity;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `HybridPreloadInfo`.`appid` AS `appid`, `HybridPreloadInfo`.`url` AS `url`, `HybridPreloadInfo`.`originalUrl` AS `originalUrl`, `HybridPreloadInfo`.`requestUrl` AS `requestUrl`, `HybridPreloadInfo`.`requestType` AS `requestType`, `HybridPreloadInfo`.`functionId` AS `functionId`, `HybridPreloadInfo`.`method` AS `method`, `HybridPreloadInfo`.`header` AS `header`, `HybridPreloadInfo`.`params` AS `params`, `HybridPreloadInfo`.`body` AS `body`, `HybridPreloadInfo`.`extraKeys` AS `extraKeys`, `HybridPreloadInfo`.`mappings` AS `mappings`, `HybridPreloadInfo`.`urlParamsState` AS `urlParamsState`, `HybridPreloadInfo`.`appMin` AS `appMin`, `HybridPreloadInfo`.`appMax` AS `appMax`, `HybridPreloadInfo`.`originalUrlType` AS `originalUrlType`, `HybridPreloadInfo`.`bConfig` AS `bConfig` FROM HybridPreloadInfo WHERE appid=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "appid");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "url");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requestUrl");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requestType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "functionId");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "method");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "header");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "params");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "body");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "extraKeys");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "mappings");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "urlParamsState");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            if (query.moveToFirst()) {
                PreloadInfoEntity preloadInfoEntity2 = new PreloadInfoEntity();
                preloadInfoEntity2.setAppid(query.getString(columnIndexOrThrow));
                preloadInfoEntity2.setUrl(query.getString(columnIndexOrThrow2));
                preloadInfoEntity2.setOriginalUrl(query.getString(columnIndexOrThrow3));
                preloadInfoEntity2.setRequestUrl(query.getString(columnIndexOrThrow4));
                preloadInfoEntity2.setRequestType(query.getString(columnIndexOrThrow5));
                preloadInfoEntity2.setFunctionId(query.getString(columnIndexOrThrow6));
                preloadInfoEntity2.setMethod(query.getString(columnIndexOrThrow7));
                preloadInfoEntity2.setHeader(this.f6129c.toMap(query.getString(columnIndexOrThrow8)));
                preloadInfoEntity2.setParams(this.f6129c.toMap(query.getString(columnIndexOrThrow9)));
                preloadInfoEntity2.setBody(this.d.toJson(query.getString(columnIndexOrThrow10)));
                preloadInfoEntity2.setExtraKeys(this.f6130e.toArray(query.getString(columnIndexOrThrow11)));
                preloadInfoEntity2.setMappings(this.f6129c.toMap(query.getString(columnIndexOrThrow12)));
                preloadInfoEntity2.setUrlParamsState(query.getInt(columnIndexOrThrow13));
                preloadInfoEntity2.setAppMin(query.getString(columnIndexOrThrow14));
                preloadInfoEntity2.setAppMax(query.getString(columnIndexOrThrow15));
                preloadInfoEntity2.setOriginalUrlType(query.getString(columnIndexOrThrow16));
                preloadInfoEntity2.setBConfig(query.getString(columnIndexOrThrow17));
                preloadInfoEntity = preloadInfoEntity2;
            } else {
                preloadInfoEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return preloadInfoEntity;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public PreloadInfoEntity getOneByUrl(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        PreloadInfoEntity preloadInfoEntity;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `HybridPreloadInfo`.`appid` AS `appid`, `HybridPreloadInfo`.`url` AS `url`, `HybridPreloadInfo`.`originalUrl` AS `originalUrl`, `HybridPreloadInfo`.`requestUrl` AS `requestUrl`, `HybridPreloadInfo`.`requestType` AS `requestType`, `HybridPreloadInfo`.`functionId` AS `functionId`, `HybridPreloadInfo`.`method` AS `method`, `HybridPreloadInfo`.`header` AS `header`, `HybridPreloadInfo`.`params` AS `params`, `HybridPreloadInfo`.`body` AS `body`, `HybridPreloadInfo`.`extraKeys` AS `extraKeys`, `HybridPreloadInfo`.`mappings` AS `mappings`, `HybridPreloadInfo`.`urlParamsState` AS `urlParamsState`, `HybridPreloadInfo`.`appMin` AS `appMin`, `HybridPreloadInfo`.`appMax` AS `appMax`, `HybridPreloadInfo`.`originalUrlType` AS `originalUrlType`, `HybridPreloadInfo`.`bConfig` AS `bConfig` FROM HybridPreloadInfo WHERE url=? OR (originalUrl=? AND originalUrlType='1') LIMIT 1", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "appid");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "url");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "requestUrl");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "requestType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "functionId");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "method");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "header");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "params");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "body");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "extraKeys");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "mappings");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "urlParamsState");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            if (query.moveToFirst()) {
                PreloadInfoEntity preloadInfoEntity2 = new PreloadInfoEntity();
                preloadInfoEntity2.setAppid(query.getString(columnIndexOrThrow));
                preloadInfoEntity2.setUrl(query.getString(columnIndexOrThrow2));
                preloadInfoEntity2.setOriginalUrl(query.getString(columnIndexOrThrow3));
                preloadInfoEntity2.setRequestUrl(query.getString(columnIndexOrThrow4));
                preloadInfoEntity2.setRequestType(query.getString(columnIndexOrThrow5));
                preloadInfoEntity2.setFunctionId(query.getString(columnIndexOrThrow6));
                preloadInfoEntity2.setMethod(query.getString(columnIndexOrThrow7));
                preloadInfoEntity2.setHeader(this.f6129c.toMap(query.getString(columnIndexOrThrow8)));
                preloadInfoEntity2.setParams(this.f6129c.toMap(query.getString(columnIndexOrThrow9)));
                preloadInfoEntity2.setBody(this.d.toJson(query.getString(columnIndexOrThrow10)));
                preloadInfoEntity2.setExtraKeys(this.f6130e.toArray(query.getString(columnIndexOrThrow11)));
                preloadInfoEntity2.setMappings(this.f6129c.toMap(query.getString(columnIndexOrThrow12)));
                preloadInfoEntity2.setUrlParamsState(query.getInt(columnIndexOrThrow13));
                preloadInfoEntity2.setAppMin(query.getString(columnIndexOrThrow14));
                preloadInfoEntity2.setAppMax(query.getString(columnIndexOrThrow15));
                preloadInfoEntity2.setOriginalUrlType(query.getString(columnIndexOrThrow16));
                preloadInfoEntity2.setBConfig(query.getString(columnIndexOrThrow17));
                preloadInfoEntity = preloadInfoEntity2;
            } else {
                preloadInfoEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return preloadInfoEntity;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public void save(List<PreloadInfoEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public void update(PreloadInfoEntity preloadInfoEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f6132g.handle(preloadInfoEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao
    public void delete(List<PreloadInfoEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f6131f.handleMultiple(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }
}
