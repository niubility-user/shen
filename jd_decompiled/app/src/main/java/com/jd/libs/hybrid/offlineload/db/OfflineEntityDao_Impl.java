package com.jd.libs.hybrid.offlineload.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo;
import java.util.List;

/* loaded from: classes16.dex */
public final class OfflineEntityDao_Impl implements OfflineEntityDao {
    private final RoomDatabase a;
    private final EntityInsertionAdapter<OfflineEntity> b;

    /* renamed from: c  reason: collision with root package name */
    private final EntityDeletionOrUpdateAdapter<OfflineEntity> f5964c;
    private final EntityDeletionOrUpdateAdapter<OfflineEntity> d;

    /* renamed from: e  reason: collision with root package name */
    private final SharedSQLiteStatement f5965e;

    public OfflineEntityDao_Impl(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<OfflineEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `HybridOfflineEntity` (`appid`,`moduleCode`,`type`,`name`,`documentUrl`,`originalUrl`,`originalUrlType`,`patch_total`,`documentDir`,`sourceRoot`,`sourceDir`,`appMin`,`appMax`,`serverPriority`,`checkType`,`fileRootPath`,`htmlPreload`,`htmlStatic`,`cacheable`,`ungentoken`,`bConfig`,`degradeType`,`minFileVer`,`available`,`createTimestamp`,`lastVisitTimestamp`,`localPriorityInfo`,`file_url`,`file_version`,`file_versionCode`,`file_md5`,`file_fileType`,`file_password`,`file_patchTotal`,`file_fileUrlZip`,`file_fileZipMd5`,`file_useZip`,`document_path`,`document_lastModified`,`document_totalSpace`,`source_path`,`source_lastModified`,`source_totalSpace`,`zip_path`,`zip_lastModified`,`zip_totalSpace`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, OfflineEntity offlineEntity) {
                if (offlineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, offlineEntity.getAppid());
                }
                supportSQLiteStatement.bindLong(2, offlineEntity.getModuleCode());
                if (offlineEntity.getType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, offlineEntity.getType());
                }
                if (offlineEntity.getName() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, offlineEntity.getName());
                }
                if (offlineEntity.getDocumentUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, offlineEntity.getDocumentUrl());
                }
                if (offlineEntity.getOriginalUrl() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, offlineEntity.getOriginalUrl());
                }
                if (offlineEntity.getOriginalUrlType() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, offlineEntity.getOriginalUrlType());
                }
                if (offlineEntity.getPatch_total() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, offlineEntity.getPatch_total());
                }
                if (offlineEntity.getDocumentDir() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, offlineEntity.getDocumentDir());
                }
                if (offlineEntity.getSourceRoot() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, offlineEntity.getSourceRoot());
                }
                if (offlineEntity.getSourceDir() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, offlineEntity.getSourceDir());
                }
                if (offlineEntity.getAppMin() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, offlineEntity.getAppMin());
                }
                if (offlineEntity.getAppMax() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, offlineEntity.getAppMax());
                }
                supportSQLiteStatement.bindLong(14, offlineEntity.getServerPriority());
                if (offlineEntity.getCheckType() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, offlineEntity.getCheckType());
                }
                if (offlineEntity.getFileRootPath() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, offlineEntity.getFileRootPath());
                }
                supportSQLiteStatement.bindLong(17, offlineEntity.getHtmlPreload());
                supportSQLiteStatement.bindLong(18, offlineEntity.getHtmlStatic());
                supportSQLiteStatement.bindLong(19, offlineEntity.getCacheable());
                supportSQLiteStatement.bindLong(20, offlineEntity.getUngentoken());
                if (offlineEntity.getBConfig() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, offlineEntity.getBConfig());
                }
                supportSQLiteStatement.bindLong(22, offlineEntity.getDegradeType());
                if (offlineEntity.getMinFileVer() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, offlineEntity.getMinFileVer());
                }
                supportSQLiteStatement.bindLong(24, offlineEntity.isAvailable() ? 1L : 0L);
                supportSQLiteStatement.bindLong(25, offlineEntity.getCreateTimestamp());
                supportSQLiteStatement.bindLong(26, offlineEntity.getLastVisitTimestamp());
                if (offlineEntity.getLocalPriorityInfo() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, offlineEntity.getLocalPriorityInfo());
                }
                OfflineEntityInfo fileInfo = offlineEntity.getFileInfo();
                if (fileInfo != null) {
                    if (fileInfo.getUrl() == null) {
                        supportSQLiteStatement.bindNull(28);
                    } else {
                        supportSQLiteStatement.bindString(28, fileInfo.getUrl());
                    }
                    if (fileInfo.getVersion() == null) {
                        supportSQLiteStatement.bindNull(29);
                    } else {
                        supportSQLiteStatement.bindString(29, fileInfo.getVersion());
                    }
                    supportSQLiteStatement.bindLong(30, fileInfo.getVersionCode());
                    if (fileInfo.getMd5() == null) {
                        supportSQLiteStatement.bindNull(31);
                    } else {
                        supportSQLiteStatement.bindString(31, fileInfo.getMd5());
                    }
                    if (fileInfo.getFileType() == null) {
                        supportSQLiteStatement.bindNull(32);
                    } else {
                        supportSQLiteStatement.bindString(32, fileInfo.getFileType());
                    }
                    if (fileInfo.getPassword() == null) {
                        supportSQLiteStatement.bindNull(33);
                    } else {
                        supportSQLiteStatement.bindString(33, fileInfo.getPassword());
                    }
                    supportSQLiteStatement.bindLong(34, fileInfo.getPatchTotal());
                    if (fileInfo.getFileUrlZip() == null) {
                        supportSQLiteStatement.bindNull(35);
                    } else {
                        supportSQLiteStatement.bindString(35, fileInfo.getFileUrlZip());
                    }
                    if (fileInfo.getFileZipMd5() == null) {
                        supportSQLiteStatement.bindNull(36);
                    } else {
                        supportSQLiteStatement.bindString(36, fileInfo.getFileZipMd5());
                    }
                    supportSQLiteStatement.bindLong(37, fileInfo.getUseZip() ? 1L : 0L);
                } else {
                    supportSQLiteStatement.bindNull(28);
                    supportSQLiteStatement.bindNull(29);
                    supportSQLiteStatement.bindNull(30);
                    supportSQLiteStatement.bindNull(31);
                    supportSQLiteStatement.bindNull(32);
                    supportSQLiteStatement.bindNull(33);
                    supportSQLiteStatement.bindNull(34);
                    supportSQLiteStatement.bindNull(35);
                    supportSQLiteStatement.bindNull(36);
                    supportSQLiteStatement.bindNull(37);
                }
                FileDetail documentFile = offlineEntity.getDocumentFile();
                if (documentFile != null) {
                    if (documentFile.getPath() == null) {
                        supportSQLiteStatement.bindNull(38);
                    } else {
                        supportSQLiteStatement.bindString(38, documentFile.getPath());
                    }
                    supportSQLiteStatement.bindLong(39, documentFile.getLastModified());
                    supportSQLiteStatement.bindLong(40, documentFile.getTotalSpace());
                } else {
                    supportSQLiteStatement.bindNull(38);
                    supportSQLiteStatement.bindNull(39);
                    supportSQLiteStatement.bindNull(40);
                }
                FileDetail sourceFile = offlineEntity.getSourceFile();
                if (sourceFile != null) {
                    if (sourceFile.getPath() == null) {
                        supportSQLiteStatement.bindNull(41);
                    } else {
                        supportSQLiteStatement.bindString(41, sourceFile.getPath());
                    }
                    supportSQLiteStatement.bindLong(42, sourceFile.getLastModified());
                    supportSQLiteStatement.bindLong(43, sourceFile.getTotalSpace());
                } else {
                    supportSQLiteStatement.bindNull(41);
                    supportSQLiteStatement.bindNull(42);
                    supportSQLiteStatement.bindNull(43);
                }
                FileDetail zipFile = offlineEntity.getZipFile();
                if (zipFile != null) {
                    if (zipFile.getPath() == null) {
                        supportSQLiteStatement.bindNull(44);
                    } else {
                        supportSQLiteStatement.bindString(44, zipFile.getPath());
                    }
                    supportSQLiteStatement.bindLong(45, zipFile.getLastModified());
                    supportSQLiteStatement.bindLong(46, zipFile.getTotalSpace());
                    return;
                }
                supportSQLiteStatement.bindNull(44);
                supportSQLiteStatement.bindNull(45);
                supportSQLiteStatement.bindNull(46);
            }
        };
        this.f5964c = new EntityDeletionOrUpdateAdapter<OfflineEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `HybridOfflineEntity` WHERE `appid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, OfflineEntity offlineEntity) {
                if (offlineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, offlineEntity.getAppid());
                }
            }
        };
        this.d = new EntityDeletionOrUpdateAdapter<OfflineEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `HybridOfflineEntity` SET `appid` = ?,`moduleCode` = ?,`type` = ?,`name` = ?,`documentUrl` = ?,`originalUrl` = ?,`originalUrlType` = ?,`patch_total` = ?,`documentDir` = ?,`sourceRoot` = ?,`sourceDir` = ?,`appMin` = ?,`appMax` = ?,`serverPriority` = ?,`checkType` = ?,`fileRootPath` = ?,`htmlPreload` = ?,`htmlStatic` = ?,`cacheable` = ?,`ungentoken` = ?,`bConfig` = ?,`degradeType` = ?,`minFileVer` = ?,`available` = ?,`createTimestamp` = ?,`lastVisitTimestamp` = ?,`localPriorityInfo` = ?,`file_url` = ?,`file_version` = ?,`file_versionCode` = ?,`file_md5` = ?,`file_fileType` = ?,`file_password` = ?,`file_patchTotal` = ?,`file_fileUrlZip` = ?,`file_fileZipMd5` = ?,`file_useZip` = ?,`document_path` = ?,`document_lastModified` = ?,`document_totalSpace` = ?,`source_path` = ?,`source_lastModified` = ?,`source_totalSpace` = ?,`zip_path` = ?,`zip_lastModified` = ?,`zip_totalSpace` = ? WHERE `appid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, OfflineEntity offlineEntity) {
                if (offlineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, offlineEntity.getAppid());
                }
                supportSQLiteStatement.bindLong(2, offlineEntity.getModuleCode());
                if (offlineEntity.getType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, offlineEntity.getType());
                }
                if (offlineEntity.getName() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, offlineEntity.getName());
                }
                if (offlineEntity.getDocumentUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, offlineEntity.getDocumentUrl());
                }
                if (offlineEntity.getOriginalUrl() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, offlineEntity.getOriginalUrl());
                }
                if (offlineEntity.getOriginalUrlType() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, offlineEntity.getOriginalUrlType());
                }
                if (offlineEntity.getPatch_total() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, offlineEntity.getPatch_total());
                }
                if (offlineEntity.getDocumentDir() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, offlineEntity.getDocumentDir());
                }
                if (offlineEntity.getSourceRoot() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, offlineEntity.getSourceRoot());
                }
                if (offlineEntity.getSourceDir() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, offlineEntity.getSourceDir());
                }
                if (offlineEntity.getAppMin() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, offlineEntity.getAppMin());
                }
                if (offlineEntity.getAppMax() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, offlineEntity.getAppMax());
                }
                supportSQLiteStatement.bindLong(14, offlineEntity.getServerPriority());
                if (offlineEntity.getCheckType() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, offlineEntity.getCheckType());
                }
                if (offlineEntity.getFileRootPath() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, offlineEntity.getFileRootPath());
                }
                supportSQLiteStatement.bindLong(17, offlineEntity.getHtmlPreload());
                supportSQLiteStatement.bindLong(18, offlineEntity.getHtmlStatic());
                supportSQLiteStatement.bindLong(19, offlineEntity.getCacheable());
                supportSQLiteStatement.bindLong(20, offlineEntity.getUngentoken());
                if (offlineEntity.getBConfig() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, offlineEntity.getBConfig());
                }
                supportSQLiteStatement.bindLong(22, offlineEntity.getDegradeType());
                if (offlineEntity.getMinFileVer() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, offlineEntity.getMinFileVer());
                }
                supportSQLiteStatement.bindLong(24, offlineEntity.isAvailable() ? 1L : 0L);
                supportSQLiteStatement.bindLong(25, offlineEntity.getCreateTimestamp());
                supportSQLiteStatement.bindLong(26, offlineEntity.getLastVisitTimestamp());
                if (offlineEntity.getLocalPriorityInfo() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, offlineEntity.getLocalPriorityInfo());
                }
                OfflineEntityInfo fileInfo = offlineEntity.getFileInfo();
                if (fileInfo != null) {
                    if (fileInfo.getUrl() == null) {
                        supportSQLiteStatement.bindNull(28);
                    } else {
                        supportSQLiteStatement.bindString(28, fileInfo.getUrl());
                    }
                    if (fileInfo.getVersion() == null) {
                        supportSQLiteStatement.bindNull(29);
                    } else {
                        supportSQLiteStatement.bindString(29, fileInfo.getVersion());
                    }
                    supportSQLiteStatement.bindLong(30, fileInfo.getVersionCode());
                    if (fileInfo.getMd5() == null) {
                        supportSQLiteStatement.bindNull(31);
                    } else {
                        supportSQLiteStatement.bindString(31, fileInfo.getMd5());
                    }
                    if (fileInfo.getFileType() == null) {
                        supportSQLiteStatement.bindNull(32);
                    } else {
                        supportSQLiteStatement.bindString(32, fileInfo.getFileType());
                    }
                    if (fileInfo.getPassword() == null) {
                        supportSQLiteStatement.bindNull(33);
                    } else {
                        supportSQLiteStatement.bindString(33, fileInfo.getPassword());
                    }
                    supportSQLiteStatement.bindLong(34, fileInfo.getPatchTotal());
                    if (fileInfo.getFileUrlZip() == null) {
                        supportSQLiteStatement.bindNull(35);
                    } else {
                        supportSQLiteStatement.bindString(35, fileInfo.getFileUrlZip());
                    }
                    if (fileInfo.getFileZipMd5() == null) {
                        supportSQLiteStatement.bindNull(36);
                    } else {
                        supportSQLiteStatement.bindString(36, fileInfo.getFileZipMd5());
                    }
                    supportSQLiteStatement.bindLong(37, fileInfo.getUseZip() ? 1L : 0L);
                } else {
                    supportSQLiteStatement.bindNull(28);
                    supportSQLiteStatement.bindNull(29);
                    supportSQLiteStatement.bindNull(30);
                    supportSQLiteStatement.bindNull(31);
                    supportSQLiteStatement.bindNull(32);
                    supportSQLiteStatement.bindNull(33);
                    supportSQLiteStatement.bindNull(34);
                    supportSQLiteStatement.bindNull(35);
                    supportSQLiteStatement.bindNull(36);
                    supportSQLiteStatement.bindNull(37);
                }
                FileDetail documentFile = offlineEntity.getDocumentFile();
                if (documentFile != null) {
                    if (documentFile.getPath() == null) {
                        supportSQLiteStatement.bindNull(38);
                    } else {
                        supportSQLiteStatement.bindString(38, documentFile.getPath());
                    }
                    supportSQLiteStatement.bindLong(39, documentFile.getLastModified());
                    supportSQLiteStatement.bindLong(40, documentFile.getTotalSpace());
                } else {
                    supportSQLiteStatement.bindNull(38);
                    supportSQLiteStatement.bindNull(39);
                    supportSQLiteStatement.bindNull(40);
                }
                FileDetail sourceFile = offlineEntity.getSourceFile();
                if (sourceFile != null) {
                    if (sourceFile.getPath() == null) {
                        supportSQLiteStatement.bindNull(41);
                    } else {
                        supportSQLiteStatement.bindString(41, sourceFile.getPath());
                    }
                    supportSQLiteStatement.bindLong(42, sourceFile.getLastModified());
                    supportSQLiteStatement.bindLong(43, sourceFile.getTotalSpace());
                } else {
                    supportSQLiteStatement.bindNull(41);
                    supportSQLiteStatement.bindNull(42);
                    supportSQLiteStatement.bindNull(43);
                }
                FileDetail zipFile = offlineEntity.getZipFile();
                if (zipFile != null) {
                    if (zipFile.getPath() == null) {
                        supportSQLiteStatement.bindNull(44);
                    } else {
                        supportSQLiteStatement.bindString(44, zipFile.getPath());
                    }
                    supportSQLiteStatement.bindLong(45, zipFile.getLastModified());
                    supportSQLiteStatement.bindLong(46, zipFile.getTotalSpace());
                } else {
                    supportSQLiteStatement.bindNull(44);
                    supportSQLiteStatement.bindNull(45);
                    supportSQLiteStatement.bindNull(46);
                }
                if (offlineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(47);
                } else {
                    supportSQLiteStatement.bindString(47, offlineEntity.getAppid());
                }
            }
        };
        this.f5965e = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM HybridOfflineEntity";
            }
        };
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public void delete(OfflineEntity offlineEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f5964c.handle(offlineEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public void deleteAll() {
        this.a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f5965e.acquire();
        this.a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.f5965e.release(acquire);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0254 A[Catch: all -> 0x0484, TryCatch #0 {all -> 0x0484, blocks: (B:5:0x006b, B:6:0x0176, B:8:0x017c, B:10:0x0184, B:12:0x018a, B:14:0x0190, B:16:0x0196, B:18:0x019c, B:20:0x01a2, B:22:0x01a8, B:24:0x01ae, B:26:0x01b4, B:35:0x0211, B:37:0x0217, B:39:0x021d, B:44:0x024e, B:46:0x0254, B:48:0x025c, B:54:0x0278, B:55:0x029b, B:57:0x02a1, B:59:0x02a9, B:66:0x02c5, B:67:0x02e5, B:71:0x03f6, B:43:0x022d, B:30:0x01bf, B:34:0x020e), top: B:82:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02a1 A[Catch: all -> 0x0484, TryCatch #0 {all -> 0x0484, blocks: (B:5:0x006b, B:6:0x0176, B:8:0x017c, B:10:0x0184, B:12:0x018a, B:14:0x0190, B:16:0x0196, B:18:0x019c, B:20:0x01a2, B:22:0x01a8, B:24:0x01ae, B:26:0x01b4, B:35:0x0211, B:37:0x0217, B:39:0x021d, B:44:0x024e, B:46:0x0254, B:48:0x025c, B:54:0x0278, B:55:0x029b, B:57:0x02a1, B:59:0x02a9, B:66:0x02c5, B:67:0x02e5, B:71:0x03f6, B:43:0x022d, B:30:0x01bf, B:34:0x020e), top: B:82:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x03f3  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.jd.libs.hybrid.offlineload.entity.OfflineEntity> getAll() {
        /*
            Method dump skipped, instructions count: 1170
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getAll():java.util.List");
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public int getAllCount() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM HybridOfflineEntity", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0254 A[Catch: all -> 0x0484, TryCatch #0 {all -> 0x0484, blocks: (B:5:0x006b, B:6:0x0176, B:8:0x017c, B:10:0x0184, B:12:0x018a, B:14:0x0190, B:16:0x0196, B:18:0x019c, B:20:0x01a2, B:22:0x01a8, B:24:0x01ae, B:26:0x01b4, B:35:0x0211, B:37:0x0217, B:39:0x021d, B:44:0x024e, B:46:0x0254, B:48:0x025c, B:54:0x0278, B:55:0x029b, B:57:0x02a1, B:59:0x02a9, B:66:0x02c5, B:67:0x02e5, B:71:0x03f6, B:43:0x022d, B:30:0x01bf, B:34:0x020e), top: B:82:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02a1 A[Catch: all -> 0x0484, TryCatch #0 {all -> 0x0484, blocks: (B:5:0x006b, B:6:0x0176, B:8:0x017c, B:10:0x0184, B:12:0x018a, B:14:0x0190, B:16:0x0196, B:18:0x019c, B:20:0x01a2, B:22:0x01a8, B:24:0x01ae, B:26:0x01b4, B:35:0x0211, B:37:0x0217, B:39:0x021d, B:44:0x024e, B:46:0x0254, B:48:0x025c, B:54:0x0278, B:55:0x029b, B:57:0x02a1, B:59:0x02a9, B:66:0x02c5, B:67:0x02e5, B:71:0x03f6, B:43:0x022d, B:30:0x01bf, B:34:0x020e), top: B:82:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x03f3  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.jd.libs.hybrid.offlineload.entity.OfflineEntity> getAllSSrBiz() {
        /*
            Method dump skipped, instructions count: 1170
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getAllSSrBiz():java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0259 A[Catch: all -> 0x0489, TryCatch #0 {all -> 0x0489, blocks: (B:5:0x0072, B:6:0x017d, B:8:0x0183, B:10:0x0189, B:12:0x018f, B:14:0x0195, B:16:0x019b, B:18:0x01a1, B:20:0x01a7, B:22:0x01ad, B:24:0x01b3, B:26:0x01b9, B:35:0x0216, B:37:0x021c, B:39:0x0222, B:44:0x0253, B:46:0x0259, B:48:0x0261, B:54:0x027c, B:55:0x029e, B:57:0x02a4, B:59:0x02ac, B:66:0x02c8, B:67:0x02e8, B:71:0x03f9, B:43:0x0232, B:30:0x01c4, B:34:0x0213), top: B:82:0x0072 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02a4 A[Catch: all -> 0x0489, TryCatch #0 {all -> 0x0489, blocks: (B:5:0x0072, B:6:0x017d, B:8:0x0183, B:10:0x0189, B:12:0x018f, B:14:0x0195, B:16:0x019b, B:18:0x01a1, B:20:0x01a7, B:22:0x01ad, B:24:0x01b3, B:26:0x01b9, B:35:0x0216, B:37:0x021c, B:39:0x0222, B:44:0x0253, B:46:0x0259, B:48:0x0261, B:54:0x027c, B:55:0x029e, B:57:0x02a4, B:59:0x02ac, B:66:0x02c8, B:67:0x02e8, B:71:0x03f9, B:43:0x0232, B:30:0x01c4, B:34:0x0213), top: B:82:0x0072 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x03f6  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.jd.libs.hybrid.offlineload.entity.OfflineEntity> getAllSSrBizByVersion(int r57) {
        /*
            Method dump skipped, instructions count: 1175
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getAllSSrBizByVersion(int):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0243 A[Catch: all -> 0x03c2, TryCatch #0 {all -> 0x03c2, blocks: (B:9:0x0077, B:11:0x017b, B:13:0x0181, B:15:0x0187, B:17:0x018d, B:19:0x0193, B:21:0x0199, B:23:0x019f, B:25:0x01a5, B:27:0x01ab, B:29:0x01b1, B:38:0x020e, B:40:0x0214, B:42:0x021a, B:47:0x023d, B:49:0x0243, B:51:0x024b, B:57:0x025c, B:58:0x0278, B:60:0x027e, B:62:0x0286, B:68:0x0295, B:69:0x02af, B:73:0x038e, B:46:0x0223, B:33:0x01bc, B:37:0x020b), top: B:84:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x027e A[Catch: all -> 0x03c2, TryCatch #0 {all -> 0x03c2, blocks: (B:9:0x0077, B:11:0x017b, B:13:0x0181, B:15:0x0187, B:17:0x018d, B:19:0x0193, B:21:0x0199, B:23:0x019f, B:25:0x01a5, B:27:0x01ab, B:29:0x01b1, B:38:0x020e, B:40:0x0214, B:42:0x021a, B:47:0x023d, B:49:0x0243, B:51:0x024b, B:57:0x025c, B:58:0x0278, B:60:0x027e, B:62:0x0286, B:68:0x0295, B:69:0x02af, B:73:0x038e, B:46:0x0223, B:33:0x01bc, B:37:0x020b), top: B:84:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x038d  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.libs.hybrid.offlineload.entity.OfflineEntity getById(java.lang.String r50) {
        /*
            Method dump skipped, instructions count: 974
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getById(java.lang.String):com.jd.libs.hybrid.offlineload.entity.OfflineEntity");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x024d A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0288 A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0397  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.libs.hybrid.offlineload.entity.OfflineEntity getByUrl(java.lang.String r50) {
        /*
            Method dump skipped, instructions count: 984
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getByUrl(java.lang.String):com.jd.libs.hybrid.offlineload.entity.OfflineEntity");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x024d A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0288 A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0397  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.libs.hybrid.offlineload.entity.OfflineEntity getOneAvailableByUrl(java.lang.String r50) {
        /*
            Method dump skipped, instructions count: 984
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getOneAvailableByUrl(java.lang.String):com.jd.libs.hybrid.offlineload.entity.OfflineEntity");
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public void save(List<OfflineEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public void update(OfflineEntity offlineEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.d.handle(offlineEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public void delete(List<OfflineEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f5964c.handleMultiple(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public void save(OfflineEntity... offlineEntityArr) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(offlineEntityArr);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    public void update(List<OfflineEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.d.handleMultiple(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0254 A[Catch: all -> 0x03d3, TryCatch #0 {all -> 0x03d3, blocks: (B:13:0x0088, B:15:0x018c, B:17:0x0192, B:19:0x0198, B:21:0x019e, B:23:0x01a4, B:25:0x01aa, B:27:0x01b0, B:29:0x01b6, B:31:0x01bc, B:33:0x01c2, B:42:0x021f, B:44:0x0225, B:46:0x022b, B:51:0x024e, B:53:0x0254, B:55:0x025c, B:61:0x026d, B:62:0x0289, B:64:0x028f, B:66:0x0297, B:72:0x02a6, B:73:0x02c0, B:77:0x039f, B:50:0x0234, B:37:0x01cd, B:41:0x021c), top: B:88:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x028f A[Catch: all -> 0x03d3, TryCatch #0 {all -> 0x03d3, blocks: (B:13:0x0088, B:15:0x018c, B:17:0x0192, B:19:0x0198, B:21:0x019e, B:23:0x01a4, B:25:0x01aa, B:27:0x01b0, B:29:0x01b6, B:31:0x01bc, B:33:0x01c2, B:42:0x021f, B:44:0x0225, B:46:0x022b, B:51:0x024e, B:53:0x0254, B:55:0x025c, B:61:0x026d, B:62:0x0289, B:64:0x028f, B:66:0x0297, B:72:0x02a6, B:73:0x02c0, B:77:0x039f, B:50:0x0234, B:37:0x01cd, B:41:0x021c), top: B:88:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x039e  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.libs.hybrid.offlineload.entity.OfflineEntity getByUrl(java.lang.String r50, int r51) {
        /*
            Method dump skipped, instructions count: 991
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getByUrl(java.lang.String, int):com.jd.libs.hybrid.offlineload.entity.OfflineEntity");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0254 A[Catch: all -> 0x03d3, TryCatch #0 {all -> 0x03d3, blocks: (B:13:0x0088, B:15:0x018c, B:17:0x0192, B:19:0x0198, B:21:0x019e, B:23:0x01a4, B:25:0x01aa, B:27:0x01b0, B:29:0x01b6, B:31:0x01bc, B:33:0x01c2, B:42:0x021f, B:44:0x0225, B:46:0x022b, B:51:0x024e, B:53:0x0254, B:55:0x025c, B:61:0x026d, B:62:0x0289, B:64:0x028f, B:66:0x0297, B:72:0x02a6, B:73:0x02c0, B:77:0x039f, B:50:0x0234, B:37:0x01cd, B:41:0x021c), top: B:88:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x028f A[Catch: all -> 0x03d3, TryCatch #0 {all -> 0x03d3, blocks: (B:13:0x0088, B:15:0x018c, B:17:0x0192, B:19:0x0198, B:21:0x019e, B:23:0x01a4, B:25:0x01aa, B:27:0x01b0, B:29:0x01b6, B:31:0x01bc, B:33:0x01c2, B:42:0x021f, B:44:0x0225, B:46:0x022b, B:51:0x024e, B:53:0x0254, B:55:0x025c, B:61:0x026d, B:62:0x0289, B:64:0x028f, B:66:0x0297, B:72:0x02a6, B:73:0x02c0, B:77:0x039f, B:50:0x0234, B:37:0x01cd, B:41:0x021c), top: B:88:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x039e  */
    @Override // com.jd.libs.hybrid.offlineload.db.OfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.libs.hybrid.offlineload.entity.OfflineEntity getOneAvailableByUrl(java.lang.String r50, int r51) {
        /*
            Method dump skipped, instructions count: 991
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.db.OfflineEntityDao_Impl.getOneAvailableByUrl(java.lang.String, int):com.jd.libs.hybrid.offlineload.entity.OfflineEntity");
    }
}
