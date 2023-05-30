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
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo;
import com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public final class TestOfflineEntityDao_Impl implements TestOfflineEntityDao {
    private final RoomDatabase a;
    private final EntityInsertionAdapter<TestOfflineEntity> b;

    /* renamed from: c  reason: collision with root package name */
    private final EntityDeletionOrUpdateAdapter<TestOfflineEntity> f5967c;
    private final EntityDeletionOrUpdateAdapter<TestOfflineEntity> d;

    /* renamed from: e  reason: collision with root package name */
    private final SharedSQLiteStatement f5968e;

    public TestOfflineEntityDao_Impl(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<TestOfflineEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `HybridTestOfflineEntity` (`appid`,`moduleCode`,`type`,`name`,`documentUrl`,`originalUrl`,`originalUrlType`,`patch_total`,`documentDir`,`sourceRoot`,`sourceDir`,`appMin`,`appMax`,`serverPriority`,`checkType`,`fileRootPath`,`htmlPreload`,`htmlStatic`,`cacheable`,`ungentoken`,`bConfig`,`degradeType`,`minFileVer`,`available`,`createTimestamp`,`lastVisitTimestamp`,`localPriorityInfo`,`file_url`,`file_version`,`file_versionCode`,`file_md5`,`file_fileType`,`file_password`,`file_patchTotal`,`file_fileUrlZip`,`file_fileZipMd5`,`file_useZip`,`document_path`,`document_lastModified`,`document_totalSpace`,`source_path`,`source_lastModified`,`source_totalSpace`,`zip_path`,`zip_lastModified`,`zip_totalSpace`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TestOfflineEntity testOfflineEntity) {
                if (testOfflineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, testOfflineEntity.getAppid());
                }
                supportSQLiteStatement.bindLong(2, testOfflineEntity.getModuleCode());
                if (testOfflineEntity.getType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, testOfflineEntity.getType());
                }
                if (testOfflineEntity.getName() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, testOfflineEntity.getName());
                }
                if (testOfflineEntity.getDocumentUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, testOfflineEntity.getDocumentUrl());
                }
                if (testOfflineEntity.getOriginalUrl() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, testOfflineEntity.getOriginalUrl());
                }
                if (testOfflineEntity.getOriginalUrlType() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, testOfflineEntity.getOriginalUrlType());
                }
                if (testOfflineEntity.getPatch_total() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, testOfflineEntity.getPatch_total());
                }
                if (testOfflineEntity.getDocumentDir() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, testOfflineEntity.getDocumentDir());
                }
                if (testOfflineEntity.getSourceRoot() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, testOfflineEntity.getSourceRoot());
                }
                if (testOfflineEntity.getSourceDir() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, testOfflineEntity.getSourceDir());
                }
                if (testOfflineEntity.getAppMin() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, testOfflineEntity.getAppMin());
                }
                if (testOfflineEntity.getAppMax() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, testOfflineEntity.getAppMax());
                }
                supportSQLiteStatement.bindLong(14, testOfflineEntity.getServerPriority());
                if (testOfflineEntity.getCheckType() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, testOfflineEntity.getCheckType());
                }
                if (testOfflineEntity.getFileRootPath() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, testOfflineEntity.getFileRootPath());
                }
                supportSQLiteStatement.bindLong(17, testOfflineEntity.getHtmlPreload());
                supportSQLiteStatement.bindLong(18, testOfflineEntity.getHtmlStatic());
                supportSQLiteStatement.bindLong(19, testOfflineEntity.getCacheable());
                supportSQLiteStatement.bindLong(20, testOfflineEntity.getUngentoken());
                if (testOfflineEntity.getBConfig() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, testOfflineEntity.getBConfig());
                }
                supportSQLiteStatement.bindLong(22, testOfflineEntity.getDegradeType());
                if (testOfflineEntity.getMinFileVer() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, testOfflineEntity.getMinFileVer());
                }
                supportSQLiteStatement.bindLong(24, testOfflineEntity.isAvailable() ? 1L : 0L);
                supportSQLiteStatement.bindLong(25, testOfflineEntity.getCreateTimestamp());
                supportSQLiteStatement.bindLong(26, testOfflineEntity.getLastVisitTimestamp());
                if (testOfflineEntity.getLocalPriorityInfo() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, testOfflineEntity.getLocalPriorityInfo());
                }
                OfflineEntityInfo fileInfo = testOfflineEntity.getFileInfo();
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
                FileDetail documentFile = testOfflineEntity.getDocumentFile();
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
                FileDetail sourceFile = testOfflineEntity.getSourceFile();
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
                FileDetail zipFile = testOfflineEntity.getZipFile();
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
        this.f5967c = new EntityDeletionOrUpdateAdapter<TestOfflineEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `HybridTestOfflineEntity` WHERE `appid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TestOfflineEntity testOfflineEntity) {
                if (testOfflineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, testOfflineEntity.getAppid());
                }
            }
        };
        this.d = new EntityDeletionOrUpdateAdapter<TestOfflineEntity>(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `HybridTestOfflineEntity` SET `appid` = ?,`moduleCode` = ?,`type` = ?,`name` = ?,`documentUrl` = ?,`originalUrl` = ?,`originalUrlType` = ?,`patch_total` = ?,`documentDir` = ?,`sourceRoot` = ?,`sourceDir` = ?,`appMin` = ?,`appMax` = ?,`serverPriority` = ?,`checkType` = ?,`fileRootPath` = ?,`htmlPreload` = ?,`htmlStatic` = ?,`cacheable` = ?,`ungentoken` = ?,`bConfig` = ?,`degradeType` = ?,`minFileVer` = ?,`available` = ?,`createTimestamp` = ?,`lastVisitTimestamp` = ?,`localPriorityInfo` = ?,`file_url` = ?,`file_version` = ?,`file_versionCode` = ?,`file_md5` = ?,`file_fileType` = ?,`file_password` = ?,`file_patchTotal` = ?,`file_fileUrlZip` = ?,`file_fileZipMd5` = ?,`file_useZip` = ?,`document_path` = ?,`document_lastModified` = ?,`document_totalSpace` = ?,`source_path` = ?,`source_lastModified` = ?,`source_totalSpace` = ?,`zip_path` = ?,`zip_lastModified` = ?,`zip_totalSpace` = ? WHERE `appid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TestOfflineEntity testOfflineEntity) {
                if (testOfflineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, testOfflineEntity.getAppid());
                }
                supportSQLiteStatement.bindLong(2, testOfflineEntity.getModuleCode());
                if (testOfflineEntity.getType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, testOfflineEntity.getType());
                }
                if (testOfflineEntity.getName() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, testOfflineEntity.getName());
                }
                if (testOfflineEntity.getDocumentUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, testOfflineEntity.getDocumentUrl());
                }
                if (testOfflineEntity.getOriginalUrl() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, testOfflineEntity.getOriginalUrl());
                }
                if (testOfflineEntity.getOriginalUrlType() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, testOfflineEntity.getOriginalUrlType());
                }
                if (testOfflineEntity.getPatch_total() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, testOfflineEntity.getPatch_total());
                }
                if (testOfflineEntity.getDocumentDir() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, testOfflineEntity.getDocumentDir());
                }
                if (testOfflineEntity.getSourceRoot() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, testOfflineEntity.getSourceRoot());
                }
                if (testOfflineEntity.getSourceDir() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, testOfflineEntity.getSourceDir());
                }
                if (testOfflineEntity.getAppMin() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, testOfflineEntity.getAppMin());
                }
                if (testOfflineEntity.getAppMax() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, testOfflineEntity.getAppMax());
                }
                supportSQLiteStatement.bindLong(14, testOfflineEntity.getServerPriority());
                if (testOfflineEntity.getCheckType() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, testOfflineEntity.getCheckType());
                }
                if (testOfflineEntity.getFileRootPath() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, testOfflineEntity.getFileRootPath());
                }
                supportSQLiteStatement.bindLong(17, testOfflineEntity.getHtmlPreload());
                supportSQLiteStatement.bindLong(18, testOfflineEntity.getHtmlStatic());
                supportSQLiteStatement.bindLong(19, testOfflineEntity.getCacheable());
                supportSQLiteStatement.bindLong(20, testOfflineEntity.getUngentoken());
                if (testOfflineEntity.getBConfig() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, testOfflineEntity.getBConfig());
                }
                supportSQLiteStatement.bindLong(22, testOfflineEntity.getDegradeType());
                if (testOfflineEntity.getMinFileVer() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, testOfflineEntity.getMinFileVer());
                }
                supportSQLiteStatement.bindLong(24, testOfflineEntity.isAvailable() ? 1L : 0L);
                supportSQLiteStatement.bindLong(25, testOfflineEntity.getCreateTimestamp());
                supportSQLiteStatement.bindLong(26, testOfflineEntity.getLastVisitTimestamp());
                if (testOfflineEntity.getLocalPriorityInfo() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, testOfflineEntity.getLocalPriorityInfo());
                }
                OfflineEntityInfo fileInfo = testOfflineEntity.getFileInfo();
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
                FileDetail documentFile = testOfflineEntity.getDocumentFile();
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
                FileDetail sourceFile = testOfflineEntity.getSourceFile();
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
                FileDetail zipFile = testOfflineEntity.getZipFile();
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
                if (testOfflineEntity.getAppid() == null) {
                    supportSQLiteStatement.bindNull(47);
                } else {
                    supportSQLiteStatement.bindString(47, testOfflineEntity.getAppid());
                }
            }
        };
        this.f5968e = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM HybridTestOfflineEntity";
            }
        };
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public void delete(TestOfflineEntity testOfflineEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f5967c.handle(testOfflineEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public void deleteAll() {
        this.a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f5968e.acquire();
        this.a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.f5968e.release(acquire);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0254 A[Catch: all -> 0x0484, TryCatch #0 {all -> 0x0484, blocks: (B:5:0x006b, B:6:0x0176, B:8:0x017c, B:10:0x0184, B:12:0x018a, B:14:0x0190, B:16:0x0196, B:18:0x019c, B:20:0x01a2, B:22:0x01a8, B:24:0x01ae, B:26:0x01b4, B:35:0x0211, B:37:0x0217, B:39:0x021d, B:44:0x024e, B:46:0x0254, B:48:0x025c, B:54:0x0278, B:55:0x029b, B:57:0x02a1, B:59:0x02a9, B:66:0x02c5, B:67:0x02e5, B:71:0x03f6, B:43:0x022d, B:30:0x01bf, B:34:0x020e), top: B:82:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02a1 A[Catch: all -> 0x0484, TryCatch #0 {all -> 0x0484, blocks: (B:5:0x006b, B:6:0x0176, B:8:0x017c, B:10:0x0184, B:12:0x018a, B:14:0x0190, B:16:0x0196, B:18:0x019c, B:20:0x01a2, B:22:0x01a8, B:24:0x01ae, B:26:0x01b4, B:35:0x0211, B:37:0x0217, B:39:0x021d, B:44:0x024e, B:46:0x0254, B:48:0x025c, B:54:0x0278, B:55:0x029b, B:57:0x02a1, B:59:0x02a9, B:66:0x02c5, B:67:0x02e5, B:71:0x03f6, B:43:0x022d, B:30:0x01bf, B:34:0x020e), top: B:82:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x03f3  */
    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<TestOfflineEntity> getAll() {
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
        int columnIndexOrThrow14;
        OfflineEntityInfo offlineEntityInfo;
        ArrayList arrayList;
        FileDetail fileDetail;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        FileDetail fileDetail2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        FileDetail fileDetail3;
        int i20;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `file_url`, `file_version`, `file_versionCode`, `file_md5`, `file_fileType`, `file_password`, `file_patchTotal`, `file_fileUrlZip`, `file_fileZipMd5`, `file_useZip`, `document_path`, `document_lastModified`, `document_totalSpace`, `source_path`, `source_lastModified`, `source_totalSpace`, `zip_path`, `zip_lastModified`, `zip_totalSpace`, `HybridTestOfflineEntity`.`appid` AS `appid`, `HybridTestOfflineEntity`.`moduleCode` AS `moduleCode`, `HybridTestOfflineEntity`.`type` AS `type`, `HybridTestOfflineEntity`.`name` AS `name`, `HybridTestOfflineEntity`.`documentUrl` AS `documentUrl`, `HybridTestOfflineEntity`.`originalUrl` AS `originalUrl`, `HybridTestOfflineEntity`.`originalUrlType` AS `originalUrlType`, `HybridTestOfflineEntity`.`patch_total` AS `patch_total`, `HybridTestOfflineEntity`.`documentDir` AS `documentDir`, `HybridTestOfflineEntity`.`sourceRoot` AS `sourceRoot`, `HybridTestOfflineEntity`.`sourceDir` AS `sourceDir`, `HybridTestOfflineEntity`.`appMin` AS `appMin`, `HybridTestOfflineEntity`.`appMax` AS `appMax`, `HybridTestOfflineEntity`.`serverPriority` AS `serverPriority`, `HybridTestOfflineEntity`.`checkType` AS `checkType`, `HybridTestOfflineEntity`.`fileRootPath` AS `fileRootPath`, `HybridTestOfflineEntity`.`htmlPreload` AS `htmlPreload`, `HybridTestOfflineEntity`.`htmlStatic` AS `htmlStatic`, `HybridTestOfflineEntity`.`cacheable` AS `cacheable`, `HybridTestOfflineEntity`.`ungentoken` AS `ungentoken`, `HybridTestOfflineEntity`.`bConfig` AS `bConfig`, `HybridTestOfflineEntity`.`degradeType` AS `degradeType`, `HybridTestOfflineEntity`.`minFileVer` AS `minFileVer`, `HybridTestOfflineEntity`.`available` AS `available`, `HybridTestOfflineEntity`.`createTimestamp` AS `createTimestamp`, `HybridTestOfflineEntity`.`lastVisitTimestamp` AS `lastVisitTimestamp`, `HybridTestOfflineEntity`.`localPriorityInfo` AS `localPriorityInfo` FROM HybridTestOfflineEntity", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "file_url");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_version");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "file_versionCode");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_md5");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_fileType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "file_password");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "file_patchTotal");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "file_fileUrlZip");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "file_fileZipMd5");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "file_useZip");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "document_path");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "document_lastModified");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "document_totalSpace");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "source_path");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "source_lastModified");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "source_totalSpace");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "zip_path");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "zip_lastModified");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "zip_totalSpace");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "appid");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, JDReactConstant.ModuleCode);
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "documentUrl");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "patch_total");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "documentDir");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "sourceRoot");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "sourceDir");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "serverPriority");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "checkType");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "fileRootPath");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "htmlPreload");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "htmlStatic");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "cacheable");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "ungentoken");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "degradeType");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "minFileVer");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "lastVisitTimestamp");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "localPriorityInfo");
            int i21 = columnIndexOrThrow14;
            ArrayList arrayList2 = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow) && query.isNull(columnIndexOrThrow2) && query.isNull(columnIndexOrThrow3) && query.isNull(columnIndexOrThrow4) && query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                    arrayList = arrayList2;
                    offlineEntityInfo = null;
                    if (query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13)) {
                        i2 = columnIndexOrThrow;
                        i3 = columnIndexOrThrow2;
                        i4 = columnIndexOrThrow3;
                        i5 = i21;
                        fileDetail = null;
                        if (query.isNull(i5)) {
                            i6 = columnIndexOrThrow15;
                        } else {
                            i6 = columnIndexOrThrow15;
                            if (query.isNull(i6)) {
                                i7 = i3;
                                i8 = columnIndexOrThrow16;
                                if (!query.isNull(i8)) {
                                    i11 = columnIndexOrThrow12;
                                    fileDetail2 = new FileDetail();
                                    i10 = columnIndexOrThrow13;
                                    fileDetail2.setPath(query.getString(i5));
                                    i13 = columnIndexOrThrow4;
                                    i9 = columnIndexOrThrow5;
                                    fileDetail2.setLastModified(query.getLong(i6));
                                    fileDetail2.setTotalSpace(query.getLong(i8));
                                    i12 = columnIndexOrThrow17;
                                    if (query.isNull(i12)) {
                                        i14 = i8;
                                        i15 = columnIndexOrThrow18;
                                    } else {
                                        i15 = columnIndexOrThrow18;
                                        if (query.isNull(i15)) {
                                            i14 = i8;
                                            i16 = columnIndexOrThrow19;
                                            if (!query.isNull(i16)) {
                                                i19 = i13;
                                                fileDetail3 = new FileDetail();
                                                i18 = i5;
                                                fileDetail3.setPath(query.getString(i12));
                                                i17 = i6;
                                                fileDetail3.setLastModified(query.getLong(i15));
                                                fileDetail3.setTotalSpace(query.getLong(i16));
                                                TestOfflineEntity testOfflineEntity = new TestOfflineEntity();
                                                int i22 = columnIndexOrThrow20;
                                                int i23 = i16;
                                                testOfflineEntity.setAppid(query.getString(i22));
                                                int i24 = columnIndexOrThrow21;
                                                testOfflineEntity.setModuleCode(query.getInt(i24));
                                                int i25 = columnIndexOrThrow22;
                                                testOfflineEntity.setType(query.getString(i25));
                                                int i26 = columnIndexOrThrow23;
                                                testOfflineEntity.setName(query.getString(i26));
                                                int i27 = columnIndexOrThrow24;
                                                testOfflineEntity.setDocumentUrl(query.getString(i27));
                                                int i28 = columnIndexOrThrow25;
                                                testOfflineEntity.setOriginalUrl(query.getString(i28));
                                                int i29 = columnIndexOrThrow26;
                                                testOfflineEntity.setOriginalUrlType(query.getString(i29));
                                                int i30 = columnIndexOrThrow27;
                                                testOfflineEntity.setPatch_total(query.getString(i30));
                                                int i31 = columnIndexOrThrow28;
                                                testOfflineEntity.setDocumentDir(query.getString(i31));
                                                int i32 = columnIndexOrThrow29;
                                                testOfflineEntity.setSourceRoot(query.getString(i32));
                                                int i33 = columnIndexOrThrow30;
                                                testOfflineEntity.setSourceDir(query.getString(i33));
                                                int i34 = columnIndexOrThrow31;
                                                testOfflineEntity.setAppMin(query.getString(i34));
                                                int i35 = columnIndexOrThrow32;
                                                testOfflineEntity.setAppMax(query.getString(i35));
                                                int i36 = columnIndexOrThrow33;
                                                testOfflineEntity.setServerPriority(query.getInt(i36));
                                                int i37 = columnIndexOrThrow34;
                                                testOfflineEntity.setCheckType(query.getString(i37));
                                                int i38 = columnIndexOrThrow35;
                                                testOfflineEntity.setFileRootPath(query.getString(i38));
                                                int i39 = columnIndexOrThrow36;
                                                testOfflineEntity.setHtmlPreload(query.getInt(i39));
                                                int i40 = columnIndexOrThrow37;
                                                testOfflineEntity.setHtmlStatic(query.getInt(i40));
                                                int i41 = columnIndexOrThrow38;
                                                testOfflineEntity.setCacheable(query.getInt(i41));
                                                int i42 = columnIndexOrThrow39;
                                                testOfflineEntity.setUngentoken(query.getInt(i42));
                                                int i43 = columnIndexOrThrow40;
                                                testOfflineEntity.setBConfig(query.getString(i43));
                                                int i44 = columnIndexOrThrow41;
                                                testOfflineEntity.setDegradeType(query.getInt(i44));
                                                int i45 = columnIndexOrThrow42;
                                                testOfflineEntity.setMinFileVer(query.getString(i45));
                                                i20 = columnIndexOrThrow43;
                                                if (query.getInt(i20) == 0) {
                                                    columnIndexOrThrow43 = i20;
                                                    z = true;
                                                } else {
                                                    columnIndexOrThrow43 = i20;
                                                    z = false;
                                                }
                                                testOfflineEntity.setAvailable(z);
                                                int i46 = columnIndexOrThrow44;
                                                int i47 = i12;
                                                testOfflineEntity.setCreateTimestamp(query.getLong(i46));
                                                int i48 = columnIndexOrThrow45;
                                                int i49 = i15;
                                                testOfflineEntity.setLastVisitTimestamp(query.getLong(i48));
                                                int i50 = columnIndexOrThrow46;
                                                testOfflineEntity.setLocalPriorityInfo(query.getString(i50));
                                                testOfflineEntity.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity.setDocumentFile(fileDetail);
                                                testOfflineEntity.setSourceFile(fileDetail2);
                                                testOfflineEntity.setZipFile(fileDetail3);
                                                ArrayList arrayList3 = arrayList;
                                                arrayList3.add(testOfflineEntity);
                                                arrayList2 = arrayList3;
                                                columnIndexOrThrow46 = i50;
                                                columnIndexOrThrow2 = i7;
                                                columnIndexOrThrow12 = i11;
                                                columnIndexOrThrow16 = i14;
                                                columnIndexOrThrow4 = i19;
                                                columnIndexOrThrow17 = i47;
                                                columnIndexOrThrow18 = i49;
                                                columnIndexOrThrow13 = i10;
                                                columnIndexOrThrow5 = i9;
                                                columnIndexOrThrow15 = i17;
                                                columnIndexOrThrow44 = i46;
                                                columnIndexOrThrow45 = i48;
                                                i21 = i18;
                                                columnIndexOrThrow19 = i23;
                                                columnIndexOrThrow20 = i22;
                                                columnIndexOrThrow21 = i24;
                                                columnIndexOrThrow22 = i25;
                                                columnIndexOrThrow23 = i26;
                                                columnIndexOrThrow24 = i27;
                                                columnIndexOrThrow25 = i28;
                                                columnIndexOrThrow26 = i29;
                                                columnIndexOrThrow27 = i30;
                                                columnIndexOrThrow28 = i31;
                                                columnIndexOrThrow29 = i32;
                                                columnIndexOrThrow30 = i33;
                                                columnIndexOrThrow31 = i34;
                                                columnIndexOrThrow32 = i35;
                                                columnIndexOrThrow33 = i36;
                                                columnIndexOrThrow34 = i37;
                                                columnIndexOrThrow35 = i38;
                                                columnIndexOrThrow36 = i39;
                                                columnIndexOrThrow37 = i40;
                                                columnIndexOrThrow38 = i41;
                                                columnIndexOrThrow39 = i42;
                                                columnIndexOrThrow40 = i43;
                                                columnIndexOrThrow41 = i44;
                                                columnIndexOrThrow42 = i45;
                                                columnIndexOrThrow = i2;
                                                columnIndexOrThrow3 = i4;
                                            } else {
                                                i19 = i13;
                                                i18 = i5;
                                                i17 = i6;
                                                fileDetail3 = null;
                                                TestOfflineEntity testOfflineEntity2 = new TestOfflineEntity();
                                                int i222 = columnIndexOrThrow20;
                                                int i232 = i16;
                                                testOfflineEntity2.setAppid(query.getString(i222));
                                                int i242 = columnIndexOrThrow21;
                                                testOfflineEntity2.setModuleCode(query.getInt(i242));
                                                int i252 = columnIndexOrThrow22;
                                                testOfflineEntity2.setType(query.getString(i252));
                                                int i262 = columnIndexOrThrow23;
                                                testOfflineEntity2.setName(query.getString(i262));
                                                int i272 = columnIndexOrThrow24;
                                                testOfflineEntity2.setDocumentUrl(query.getString(i272));
                                                int i282 = columnIndexOrThrow25;
                                                testOfflineEntity2.setOriginalUrl(query.getString(i282));
                                                int i292 = columnIndexOrThrow26;
                                                testOfflineEntity2.setOriginalUrlType(query.getString(i292));
                                                int i302 = columnIndexOrThrow27;
                                                testOfflineEntity2.setPatch_total(query.getString(i302));
                                                int i312 = columnIndexOrThrow28;
                                                testOfflineEntity2.setDocumentDir(query.getString(i312));
                                                int i322 = columnIndexOrThrow29;
                                                testOfflineEntity2.setSourceRoot(query.getString(i322));
                                                int i332 = columnIndexOrThrow30;
                                                testOfflineEntity2.setSourceDir(query.getString(i332));
                                                int i342 = columnIndexOrThrow31;
                                                testOfflineEntity2.setAppMin(query.getString(i342));
                                                int i352 = columnIndexOrThrow32;
                                                testOfflineEntity2.setAppMax(query.getString(i352));
                                                int i362 = columnIndexOrThrow33;
                                                testOfflineEntity2.setServerPriority(query.getInt(i362));
                                                int i372 = columnIndexOrThrow34;
                                                testOfflineEntity2.setCheckType(query.getString(i372));
                                                int i382 = columnIndexOrThrow35;
                                                testOfflineEntity2.setFileRootPath(query.getString(i382));
                                                int i392 = columnIndexOrThrow36;
                                                testOfflineEntity2.setHtmlPreload(query.getInt(i392));
                                                int i402 = columnIndexOrThrow37;
                                                testOfflineEntity2.setHtmlStatic(query.getInt(i402));
                                                int i412 = columnIndexOrThrow38;
                                                testOfflineEntity2.setCacheable(query.getInt(i412));
                                                int i422 = columnIndexOrThrow39;
                                                testOfflineEntity2.setUngentoken(query.getInt(i422));
                                                int i432 = columnIndexOrThrow40;
                                                testOfflineEntity2.setBConfig(query.getString(i432));
                                                int i442 = columnIndexOrThrow41;
                                                testOfflineEntity2.setDegradeType(query.getInt(i442));
                                                int i452 = columnIndexOrThrow42;
                                                testOfflineEntity2.setMinFileVer(query.getString(i452));
                                                i20 = columnIndexOrThrow43;
                                                if (query.getInt(i20) == 0) {
                                                }
                                                testOfflineEntity2.setAvailable(z);
                                                int i462 = columnIndexOrThrow44;
                                                int i472 = i12;
                                                testOfflineEntity2.setCreateTimestamp(query.getLong(i462));
                                                int i482 = columnIndexOrThrow45;
                                                int i492 = i15;
                                                testOfflineEntity2.setLastVisitTimestamp(query.getLong(i482));
                                                int i502 = columnIndexOrThrow46;
                                                testOfflineEntity2.setLocalPriorityInfo(query.getString(i502));
                                                testOfflineEntity2.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity2.setDocumentFile(fileDetail);
                                                testOfflineEntity2.setSourceFile(fileDetail2);
                                                testOfflineEntity2.setZipFile(fileDetail3);
                                                ArrayList arrayList32 = arrayList;
                                                arrayList32.add(testOfflineEntity2);
                                                arrayList2 = arrayList32;
                                                columnIndexOrThrow46 = i502;
                                                columnIndexOrThrow2 = i7;
                                                columnIndexOrThrow12 = i11;
                                                columnIndexOrThrow16 = i14;
                                                columnIndexOrThrow4 = i19;
                                                columnIndexOrThrow17 = i472;
                                                columnIndexOrThrow18 = i492;
                                                columnIndexOrThrow13 = i10;
                                                columnIndexOrThrow5 = i9;
                                                columnIndexOrThrow15 = i17;
                                                columnIndexOrThrow44 = i462;
                                                columnIndexOrThrow45 = i482;
                                                i21 = i18;
                                                columnIndexOrThrow19 = i232;
                                                columnIndexOrThrow20 = i222;
                                                columnIndexOrThrow21 = i242;
                                                columnIndexOrThrow22 = i252;
                                                columnIndexOrThrow23 = i262;
                                                columnIndexOrThrow24 = i272;
                                                columnIndexOrThrow25 = i282;
                                                columnIndexOrThrow26 = i292;
                                                columnIndexOrThrow27 = i302;
                                                columnIndexOrThrow28 = i312;
                                                columnIndexOrThrow29 = i322;
                                                columnIndexOrThrow30 = i332;
                                                columnIndexOrThrow31 = i342;
                                                columnIndexOrThrow32 = i352;
                                                columnIndexOrThrow33 = i362;
                                                columnIndexOrThrow34 = i372;
                                                columnIndexOrThrow35 = i382;
                                                columnIndexOrThrow36 = i392;
                                                columnIndexOrThrow37 = i402;
                                                columnIndexOrThrow38 = i412;
                                                columnIndexOrThrow39 = i422;
                                                columnIndexOrThrow40 = i432;
                                                columnIndexOrThrow41 = i442;
                                                columnIndexOrThrow42 = i452;
                                                columnIndexOrThrow = i2;
                                                columnIndexOrThrow3 = i4;
                                            }
                                        } else {
                                            i14 = i8;
                                        }
                                    }
                                    i16 = columnIndexOrThrow19;
                                    i19 = i13;
                                    fileDetail3 = new FileDetail();
                                    i18 = i5;
                                    fileDetail3.setPath(query.getString(i12));
                                    i17 = i6;
                                    fileDetail3.setLastModified(query.getLong(i15));
                                    fileDetail3.setTotalSpace(query.getLong(i16));
                                    TestOfflineEntity testOfflineEntity22 = new TestOfflineEntity();
                                    int i2222 = columnIndexOrThrow20;
                                    int i2322 = i16;
                                    testOfflineEntity22.setAppid(query.getString(i2222));
                                    int i2422 = columnIndexOrThrow21;
                                    testOfflineEntity22.setModuleCode(query.getInt(i2422));
                                    int i2522 = columnIndexOrThrow22;
                                    testOfflineEntity22.setType(query.getString(i2522));
                                    int i2622 = columnIndexOrThrow23;
                                    testOfflineEntity22.setName(query.getString(i2622));
                                    int i2722 = columnIndexOrThrow24;
                                    testOfflineEntity22.setDocumentUrl(query.getString(i2722));
                                    int i2822 = columnIndexOrThrow25;
                                    testOfflineEntity22.setOriginalUrl(query.getString(i2822));
                                    int i2922 = columnIndexOrThrow26;
                                    testOfflineEntity22.setOriginalUrlType(query.getString(i2922));
                                    int i3022 = columnIndexOrThrow27;
                                    testOfflineEntity22.setPatch_total(query.getString(i3022));
                                    int i3122 = columnIndexOrThrow28;
                                    testOfflineEntity22.setDocumentDir(query.getString(i3122));
                                    int i3222 = columnIndexOrThrow29;
                                    testOfflineEntity22.setSourceRoot(query.getString(i3222));
                                    int i3322 = columnIndexOrThrow30;
                                    testOfflineEntity22.setSourceDir(query.getString(i3322));
                                    int i3422 = columnIndexOrThrow31;
                                    testOfflineEntity22.setAppMin(query.getString(i3422));
                                    int i3522 = columnIndexOrThrow32;
                                    testOfflineEntity22.setAppMax(query.getString(i3522));
                                    int i3622 = columnIndexOrThrow33;
                                    testOfflineEntity22.setServerPriority(query.getInt(i3622));
                                    int i3722 = columnIndexOrThrow34;
                                    testOfflineEntity22.setCheckType(query.getString(i3722));
                                    int i3822 = columnIndexOrThrow35;
                                    testOfflineEntity22.setFileRootPath(query.getString(i3822));
                                    int i3922 = columnIndexOrThrow36;
                                    testOfflineEntity22.setHtmlPreload(query.getInt(i3922));
                                    int i4022 = columnIndexOrThrow37;
                                    testOfflineEntity22.setHtmlStatic(query.getInt(i4022));
                                    int i4122 = columnIndexOrThrow38;
                                    testOfflineEntity22.setCacheable(query.getInt(i4122));
                                    int i4222 = columnIndexOrThrow39;
                                    testOfflineEntity22.setUngentoken(query.getInt(i4222));
                                    int i4322 = columnIndexOrThrow40;
                                    testOfflineEntity22.setBConfig(query.getString(i4322));
                                    int i4422 = columnIndexOrThrow41;
                                    testOfflineEntity22.setDegradeType(query.getInt(i4422));
                                    int i4522 = columnIndexOrThrow42;
                                    testOfflineEntity22.setMinFileVer(query.getString(i4522));
                                    i20 = columnIndexOrThrow43;
                                    if (query.getInt(i20) == 0) {
                                    }
                                    testOfflineEntity22.setAvailable(z);
                                    int i4622 = columnIndexOrThrow44;
                                    int i4722 = i12;
                                    testOfflineEntity22.setCreateTimestamp(query.getLong(i4622));
                                    int i4822 = columnIndexOrThrow45;
                                    int i4922 = i15;
                                    testOfflineEntity22.setLastVisitTimestamp(query.getLong(i4822));
                                    int i5022 = columnIndexOrThrow46;
                                    testOfflineEntity22.setLocalPriorityInfo(query.getString(i5022));
                                    testOfflineEntity22.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity22.setDocumentFile(fileDetail);
                                    testOfflineEntity22.setSourceFile(fileDetail2);
                                    testOfflineEntity22.setZipFile(fileDetail3);
                                    ArrayList arrayList322 = arrayList;
                                    arrayList322.add(testOfflineEntity22);
                                    arrayList2 = arrayList322;
                                    columnIndexOrThrow46 = i5022;
                                    columnIndexOrThrow2 = i7;
                                    columnIndexOrThrow12 = i11;
                                    columnIndexOrThrow16 = i14;
                                    columnIndexOrThrow4 = i19;
                                    columnIndexOrThrow17 = i4722;
                                    columnIndexOrThrow18 = i4922;
                                    columnIndexOrThrow13 = i10;
                                    columnIndexOrThrow5 = i9;
                                    columnIndexOrThrow15 = i17;
                                    columnIndexOrThrow44 = i4622;
                                    columnIndexOrThrow45 = i4822;
                                    i21 = i18;
                                    columnIndexOrThrow19 = i2322;
                                    columnIndexOrThrow20 = i2222;
                                    columnIndexOrThrow21 = i2422;
                                    columnIndexOrThrow22 = i2522;
                                    columnIndexOrThrow23 = i2622;
                                    columnIndexOrThrow24 = i2722;
                                    columnIndexOrThrow25 = i2822;
                                    columnIndexOrThrow26 = i2922;
                                    columnIndexOrThrow27 = i3022;
                                    columnIndexOrThrow28 = i3122;
                                    columnIndexOrThrow29 = i3222;
                                    columnIndexOrThrow30 = i3322;
                                    columnIndexOrThrow31 = i3422;
                                    columnIndexOrThrow32 = i3522;
                                    columnIndexOrThrow33 = i3622;
                                    columnIndexOrThrow34 = i3722;
                                    columnIndexOrThrow35 = i3822;
                                    columnIndexOrThrow36 = i3922;
                                    columnIndexOrThrow37 = i4022;
                                    columnIndexOrThrow38 = i4122;
                                    columnIndexOrThrow39 = i4222;
                                    columnIndexOrThrow40 = i4322;
                                    columnIndexOrThrow41 = i4422;
                                    columnIndexOrThrow42 = i4522;
                                    columnIndexOrThrow = i2;
                                    columnIndexOrThrow3 = i4;
                                } else {
                                    i11 = columnIndexOrThrow12;
                                    i10 = columnIndexOrThrow13;
                                    i13 = columnIndexOrThrow4;
                                    i9 = columnIndexOrThrow5;
                                    i12 = columnIndexOrThrow17;
                                    fileDetail2 = null;
                                    if (query.isNull(i12)) {
                                    }
                                    i16 = columnIndexOrThrow19;
                                    i19 = i13;
                                    fileDetail3 = new FileDetail();
                                    i18 = i5;
                                    fileDetail3.setPath(query.getString(i12));
                                    i17 = i6;
                                    fileDetail3.setLastModified(query.getLong(i15));
                                    fileDetail3.setTotalSpace(query.getLong(i16));
                                    TestOfflineEntity testOfflineEntity222 = new TestOfflineEntity();
                                    int i22222 = columnIndexOrThrow20;
                                    int i23222 = i16;
                                    testOfflineEntity222.setAppid(query.getString(i22222));
                                    int i24222 = columnIndexOrThrow21;
                                    testOfflineEntity222.setModuleCode(query.getInt(i24222));
                                    int i25222 = columnIndexOrThrow22;
                                    testOfflineEntity222.setType(query.getString(i25222));
                                    int i26222 = columnIndexOrThrow23;
                                    testOfflineEntity222.setName(query.getString(i26222));
                                    int i27222 = columnIndexOrThrow24;
                                    testOfflineEntity222.setDocumentUrl(query.getString(i27222));
                                    int i28222 = columnIndexOrThrow25;
                                    testOfflineEntity222.setOriginalUrl(query.getString(i28222));
                                    int i29222 = columnIndexOrThrow26;
                                    testOfflineEntity222.setOriginalUrlType(query.getString(i29222));
                                    int i30222 = columnIndexOrThrow27;
                                    testOfflineEntity222.setPatch_total(query.getString(i30222));
                                    int i31222 = columnIndexOrThrow28;
                                    testOfflineEntity222.setDocumentDir(query.getString(i31222));
                                    int i32222 = columnIndexOrThrow29;
                                    testOfflineEntity222.setSourceRoot(query.getString(i32222));
                                    int i33222 = columnIndexOrThrow30;
                                    testOfflineEntity222.setSourceDir(query.getString(i33222));
                                    int i34222 = columnIndexOrThrow31;
                                    testOfflineEntity222.setAppMin(query.getString(i34222));
                                    int i35222 = columnIndexOrThrow32;
                                    testOfflineEntity222.setAppMax(query.getString(i35222));
                                    int i36222 = columnIndexOrThrow33;
                                    testOfflineEntity222.setServerPriority(query.getInt(i36222));
                                    int i37222 = columnIndexOrThrow34;
                                    testOfflineEntity222.setCheckType(query.getString(i37222));
                                    int i38222 = columnIndexOrThrow35;
                                    testOfflineEntity222.setFileRootPath(query.getString(i38222));
                                    int i39222 = columnIndexOrThrow36;
                                    testOfflineEntity222.setHtmlPreload(query.getInt(i39222));
                                    int i40222 = columnIndexOrThrow37;
                                    testOfflineEntity222.setHtmlStatic(query.getInt(i40222));
                                    int i41222 = columnIndexOrThrow38;
                                    testOfflineEntity222.setCacheable(query.getInt(i41222));
                                    int i42222 = columnIndexOrThrow39;
                                    testOfflineEntity222.setUngentoken(query.getInt(i42222));
                                    int i43222 = columnIndexOrThrow40;
                                    testOfflineEntity222.setBConfig(query.getString(i43222));
                                    int i44222 = columnIndexOrThrow41;
                                    testOfflineEntity222.setDegradeType(query.getInt(i44222));
                                    int i45222 = columnIndexOrThrow42;
                                    testOfflineEntity222.setMinFileVer(query.getString(i45222));
                                    i20 = columnIndexOrThrow43;
                                    if (query.getInt(i20) == 0) {
                                    }
                                    testOfflineEntity222.setAvailable(z);
                                    int i46222 = columnIndexOrThrow44;
                                    int i47222 = i12;
                                    testOfflineEntity222.setCreateTimestamp(query.getLong(i46222));
                                    int i48222 = columnIndexOrThrow45;
                                    int i49222 = i15;
                                    testOfflineEntity222.setLastVisitTimestamp(query.getLong(i48222));
                                    int i50222 = columnIndexOrThrow46;
                                    testOfflineEntity222.setLocalPriorityInfo(query.getString(i50222));
                                    testOfflineEntity222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity222.setDocumentFile(fileDetail);
                                    testOfflineEntity222.setSourceFile(fileDetail2);
                                    testOfflineEntity222.setZipFile(fileDetail3);
                                    ArrayList arrayList3222 = arrayList;
                                    arrayList3222.add(testOfflineEntity222);
                                    arrayList2 = arrayList3222;
                                    columnIndexOrThrow46 = i50222;
                                    columnIndexOrThrow2 = i7;
                                    columnIndexOrThrow12 = i11;
                                    columnIndexOrThrow16 = i14;
                                    columnIndexOrThrow4 = i19;
                                    columnIndexOrThrow17 = i47222;
                                    columnIndexOrThrow18 = i49222;
                                    columnIndexOrThrow13 = i10;
                                    columnIndexOrThrow5 = i9;
                                    columnIndexOrThrow15 = i17;
                                    columnIndexOrThrow44 = i46222;
                                    columnIndexOrThrow45 = i48222;
                                    i21 = i18;
                                    columnIndexOrThrow19 = i23222;
                                    columnIndexOrThrow20 = i22222;
                                    columnIndexOrThrow21 = i24222;
                                    columnIndexOrThrow22 = i25222;
                                    columnIndexOrThrow23 = i26222;
                                    columnIndexOrThrow24 = i27222;
                                    columnIndexOrThrow25 = i28222;
                                    columnIndexOrThrow26 = i29222;
                                    columnIndexOrThrow27 = i30222;
                                    columnIndexOrThrow28 = i31222;
                                    columnIndexOrThrow29 = i32222;
                                    columnIndexOrThrow30 = i33222;
                                    columnIndexOrThrow31 = i34222;
                                    columnIndexOrThrow32 = i35222;
                                    columnIndexOrThrow33 = i36222;
                                    columnIndexOrThrow34 = i37222;
                                    columnIndexOrThrow35 = i38222;
                                    columnIndexOrThrow36 = i39222;
                                    columnIndexOrThrow37 = i40222;
                                    columnIndexOrThrow38 = i41222;
                                    columnIndexOrThrow39 = i42222;
                                    columnIndexOrThrow40 = i43222;
                                    columnIndexOrThrow41 = i44222;
                                    columnIndexOrThrow42 = i45222;
                                    columnIndexOrThrow = i2;
                                    columnIndexOrThrow3 = i4;
                                }
                            }
                        }
                        i7 = i3;
                        i8 = columnIndexOrThrow16;
                        i11 = columnIndexOrThrow12;
                        fileDetail2 = new FileDetail();
                        i10 = columnIndexOrThrow13;
                        fileDetail2.setPath(query.getString(i5));
                        i13 = columnIndexOrThrow4;
                        i9 = columnIndexOrThrow5;
                        fileDetail2.setLastModified(query.getLong(i6));
                        fileDetail2.setTotalSpace(query.getLong(i8));
                        i12 = columnIndexOrThrow17;
                        if (query.isNull(i12)) {
                        }
                        i16 = columnIndexOrThrow19;
                        i19 = i13;
                        fileDetail3 = new FileDetail();
                        i18 = i5;
                        fileDetail3.setPath(query.getString(i12));
                        i17 = i6;
                        fileDetail3.setLastModified(query.getLong(i15));
                        fileDetail3.setTotalSpace(query.getLong(i16));
                        TestOfflineEntity testOfflineEntity2222 = new TestOfflineEntity();
                        int i222222 = columnIndexOrThrow20;
                        int i232222 = i16;
                        testOfflineEntity2222.setAppid(query.getString(i222222));
                        int i242222 = columnIndexOrThrow21;
                        testOfflineEntity2222.setModuleCode(query.getInt(i242222));
                        int i252222 = columnIndexOrThrow22;
                        testOfflineEntity2222.setType(query.getString(i252222));
                        int i262222 = columnIndexOrThrow23;
                        testOfflineEntity2222.setName(query.getString(i262222));
                        int i272222 = columnIndexOrThrow24;
                        testOfflineEntity2222.setDocumentUrl(query.getString(i272222));
                        int i282222 = columnIndexOrThrow25;
                        testOfflineEntity2222.setOriginalUrl(query.getString(i282222));
                        int i292222 = columnIndexOrThrow26;
                        testOfflineEntity2222.setOriginalUrlType(query.getString(i292222));
                        int i302222 = columnIndexOrThrow27;
                        testOfflineEntity2222.setPatch_total(query.getString(i302222));
                        int i312222 = columnIndexOrThrow28;
                        testOfflineEntity2222.setDocumentDir(query.getString(i312222));
                        int i322222 = columnIndexOrThrow29;
                        testOfflineEntity2222.setSourceRoot(query.getString(i322222));
                        int i332222 = columnIndexOrThrow30;
                        testOfflineEntity2222.setSourceDir(query.getString(i332222));
                        int i342222 = columnIndexOrThrow31;
                        testOfflineEntity2222.setAppMin(query.getString(i342222));
                        int i352222 = columnIndexOrThrow32;
                        testOfflineEntity2222.setAppMax(query.getString(i352222));
                        int i362222 = columnIndexOrThrow33;
                        testOfflineEntity2222.setServerPriority(query.getInt(i362222));
                        int i372222 = columnIndexOrThrow34;
                        testOfflineEntity2222.setCheckType(query.getString(i372222));
                        int i382222 = columnIndexOrThrow35;
                        testOfflineEntity2222.setFileRootPath(query.getString(i382222));
                        int i392222 = columnIndexOrThrow36;
                        testOfflineEntity2222.setHtmlPreload(query.getInt(i392222));
                        int i402222 = columnIndexOrThrow37;
                        testOfflineEntity2222.setHtmlStatic(query.getInt(i402222));
                        int i412222 = columnIndexOrThrow38;
                        testOfflineEntity2222.setCacheable(query.getInt(i412222));
                        int i422222 = columnIndexOrThrow39;
                        testOfflineEntity2222.setUngentoken(query.getInt(i422222));
                        int i432222 = columnIndexOrThrow40;
                        testOfflineEntity2222.setBConfig(query.getString(i432222));
                        int i442222 = columnIndexOrThrow41;
                        testOfflineEntity2222.setDegradeType(query.getInt(i442222));
                        int i452222 = columnIndexOrThrow42;
                        testOfflineEntity2222.setMinFileVer(query.getString(i452222));
                        i20 = columnIndexOrThrow43;
                        if (query.getInt(i20) == 0) {
                        }
                        testOfflineEntity2222.setAvailable(z);
                        int i462222 = columnIndexOrThrow44;
                        int i472222 = i12;
                        testOfflineEntity2222.setCreateTimestamp(query.getLong(i462222));
                        int i482222 = columnIndexOrThrow45;
                        int i492222 = i15;
                        testOfflineEntity2222.setLastVisitTimestamp(query.getLong(i482222));
                        int i502222 = columnIndexOrThrow46;
                        testOfflineEntity2222.setLocalPriorityInfo(query.getString(i502222));
                        testOfflineEntity2222.setFileInfo(offlineEntityInfo);
                        testOfflineEntity2222.setDocumentFile(fileDetail);
                        testOfflineEntity2222.setSourceFile(fileDetail2);
                        testOfflineEntity2222.setZipFile(fileDetail3);
                        ArrayList arrayList32222 = arrayList;
                        arrayList32222.add(testOfflineEntity2222);
                        arrayList2 = arrayList32222;
                        columnIndexOrThrow46 = i502222;
                        columnIndexOrThrow2 = i7;
                        columnIndexOrThrow12 = i11;
                        columnIndexOrThrow16 = i14;
                        columnIndexOrThrow4 = i19;
                        columnIndexOrThrow17 = i472222;
                        columnIndexOrThrow18 = i492222;
                        columnIndexOrThrow13 = i10;
                        columnIndexOrThrow5 = i9;
                        columnIndexOrThrow15 = i17;
                        columnIndexOrThrow44 = i462222;
                        columnIndexOrThrow45 = i482222;
                        i21 = i18;
                        columnIndexOrThrow19 = i232222;
                        columnIndexOrThrow20 = i222222;
                        columnIndexOrThrow21 = i242222;
                        columnIndexOrThrow22 = i252222;
                        columnIndexOrThrow23 = i262222;
                        columnIndexOrThrow24 = i272222;
                        columnIndexOrThrow25 = i282222;
                        columnIndexOrThrow26 = i292222;
                        columnIndexOrThrow27 = i302222;
                        columnIndexOrThrow28 = i312222;
                        columnIndexOrThrow29 = i322222;
                        columnIndexOrThrow30 = i332222;
                        columnIndexOrThrow31 = i342222;
                        columnIndexOrThrow32 = i352222;
                        columnIndexOrThrow33 = i362222;
                        columnIndexOrThrow34 = i372222;
                        columnIndexOrThrow35 = i382222;
                        columnIndexOrThrow36 = i392222;
                        columnIndexOrThrow37 = i402222;
                        columnIndexOrThrow38 = i412222;
                        columnIndexOrThrow39 = i422222;
                        columnIndexOrThrow40 = i432222;
                        columnIndexOrThrow41 = i442222;
                        columnIndexOrThrow42 = i452222;
                        columnIndexOrThrow = i2;
                        columnIndexOrThrow3 = i4;
                    }
                    fileDetail = new FileDetail();
                    i2 = columnIndexOrThrow;
                    fileDetail.setPath(query.getString(columnIndexOrThrow11));
                    i3 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                    fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                    i5 = i21;
                    if (query.isNull(i5)) {
                    }
                    i7 = i3;
                    i8 = columnIndexOrThrow16;
                    i11 = columnIndexOrThrow12;
                    fileDetail2 = new FileDetail();
                    i10 = columnIndexOrThrow13;
                    fileDetail2.setPath(query.getString(i5));
                    i13 = columnIndexOrThrow4;
                    i9 = columnIndexOrThrow5;
                    fileDetail2.setLastModified(query.getLong(i6));
                    fileDetail2.setTotalSpace(query.getLong(i8));
                    i12 = columnIndexOrThrow17;
                    if (query.isNull(i12)) {
                    }
                    i16 = columnIndexOrThrow19;
                    i19 = i13;
                    fileDetail3 = new FileDetail();
                    i18 = i5;
                    fileDetail3.setPath(query.getString(i12));
                    i17 = i6;
                    fileDetail3.setLastModified(query.getLong(i15));
                    fileDetail3.setTotalSpace(query.getLong(i16));
                    TestOfflineEntity testOfflineEntity22222 = new TestOfflineEntity();
                    int i2222222 = columnIndexOrThrow20;
                    int i2322222 = i16;
                    testOfflineEntity22222.setAppid(query.getString(i2222222));
                    int i2422222 = columnIndexOrThrow21;
                    testOfflineEntity22222.setModuleCode(query.getInt(i2422222));
                    int i2522222 = columnIndexOrThrow22;
                    testOfflineEntity22222.setType(query.getString(i2522222));
                    int i2622222 = columnIndexOrThrow23;
                    testOfflineEntity22222.setName(query.getString(i2622222));
                    int i2722222 = columnIndexOrThrow24;
                    testOfflineEntity22222.setDocumentUrl(query.getString(i2722222));
                    int i2822222 = columnIndexOrThrow25;
                    testOfflineEntity22222.setOriginalUrl(query.getString(i2822222));
                    int i2922222 = columnIndexOrThrow26;
                    testOfflineEntity22222.setOriginalUrlType(query.getString(i2922222));
                    int i3022222 = columnIndexOrThrow27;
                    testOfflineEntity22222.setPatch_total(query.getString(i3022222));
                    int i3122222 = columnIndexOrThrow28;
                    testOfflineEntity22222.setDocumentDir(query.getString(i3122222));
                    int i3222222 = columnIndexOrThrow29;
                    testOfflineEntity22222.setSourceRoot(query.getString(i3222222));
                    int i3322222 = columnIndexOrThrow30;
                    testOfflineEntity22222.setSourceDir(query.getString(i3322222));
                    int i3422222 = columnIndexOrThrow31;
                    testOfflineEntity22222.setAppMin(query.getString(i3422222));
                    int i3522222 = columnIndexOrThrow32;
                    testOfflineEntity22222.setAppMax(query.getString(i3522222));
                    int i3622222 = columnIndexOrThrow33;
                    testOfflineEntity22222.setServerPriority(query.getInt(i3622222));
                    int i3722222 = columnIndexOrThrow34;
                    testOfflineEntity22222.setCheckType(query.getString(i3722222));
                    int i3822222 = columnIndexOrThrow35;
                    testOfflineEntity22222.setFileRootPath(query.getString(i3822222));
                    int i3922222 = columnIndexOrThrow36;
                    testOfflineEntity22222.setHtmlPreload(query.getInt(i3922222));
                    int i4022222 = columnIndexOrThrow37;
                    testOfflineEntity22222.setHtmlStatic(query.getInt(i4022222));
                    int i4122222 = columnIndexOrThrow38;
                    testOfflineEntity22222.setCacheable(query.getInt(i4122222));
                    int i4222222 = columnIndexOrThrow39;
                    testOfflineEntity22222.setUngentoken(query.getInt(i4222222));
                    int i4322222 = columnIndexOrThrow40;
                    testOfflineEntity22222.setBConfig(query.getString(i4322222));
                    int i4422222 = columnIndexOrThrow41;
                    testOfflineEntity22222.setDegradeType(query.getInt(i4422222));
                    int i4522222 = columnIndexOrThrow42;
                    testOfflineEntity22222.setMinFileVer(query.getString(i4522222));
                    i20 = columnIndexOrThrow43;
                    if (query.getInt(i20) == 0) {
                    }
                    testOfflineEntity22222.setAvailable(z);
                    int i4622222 = columnIndexOrThrow44;
                    int i4722222 = i12;
                    testOfflineEntity22222.setCreateTimestamp(query.getLong(i4622222));
                    int i4822222 = columnIndexOrThrow45;
                    int i4922222 = i15;
                    testOfflineEntity22222.setLastVisitTimestamp(query.getLong(i4822222));
                    int i5022222 = columnIndexOrThrow46;
                    testOfflineEntity22222.setLocalPriorityInfo(query.getString(i5022222));
                    testOfflineEntity22222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity22222.setDocumentFile(fileDetail);
                    testOfflineEntity22222.setSourceFile(fileDetail2);
                    testOfflineEntity22222.setZipFile(fileDetail3);
                    ArrayList arrayList322222 = arrayList;
                    arrayList322222.add(testOfflineEntity22222);
                    arrayList2 = arrayList322222;
                    columnIndexOrThrow46 = i5022222;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow16 = i14;
                    columnIndexOrThrow4 = i19;
                    columnIndexOrThrow17 = i4722222;
                    columnIndexOrThrow18 = i4922222;
                    columnIndexOrThrow13 = i10;
                    columnIndexOrThrow5 = i9;
                    columnIndexOrThrow15 = i17;
                    columnIndexOrThrow44 = i4622222;
                    columnIndexOrThrow45 = i4822222;
                    i21 = i18;
                    columnIndexOrThrow19 = i2322222;
                    columnIndexOrThrow20 = i2222222;
                    columnIndexOrThrow21 = i2422222;
                    columnIndexOrThrow22 = i2522222;
                    columnIndexOrThrow23 = i2622222;
                    columnIndexOrThrow24 = i2722222;
                    columnIndexOrThrow25 = i2822222;
                    columnIndexOrThrow26 = i2922222;
                    columnIndexOrThrow27 = i3022222;
                    columnIndexOrThrow28 = i3122222;
                    columnIndexOrThrow29 = i3222222;
                    columnIndexOrThrow30 = i3322222;
                    columnIndexOrThrow31 = i3422222;
                    columnIndexOrThrow32 = i3522222;
                    columnIndexOrThrow33 = i3622222;
                    columnIndexOrThrow34 = i3722222;
                    columnIndexOrThrow35 = i3822222;
                    columnIndexOrThrow36 = i3922222;
                    columnIndexOrThrow37 = i4022222;
                    columnIndexOrThrow38 = i4122222;
                    columnIndexOrThrow39 = i4222222;
                    columnIndexOrThrow40 = i4322222;
                    columnIndexOrThrow41 = i4422222;
                    columnIndexOrThrow42 = i4522222;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow3 = i4;
                }
                offlineEntityInfo = new OfflineEntityInfo();
                arrayList = arrayList2;
                offlineEntityInfo.setUrl(query.getString(columnIndexOrThrow));
                offlineEntityInfo.setVersion(query.getString(columnIndexOrThrow2));
                offlineEntityInfo.setVersionCode(query.getInt(columnIndexOrThrow3));
                offlineEntityInfo.setMd5(query.getString(columnIndexOrThrow4));
                offlineEntityInfo.setFileType(query.getString(columnIndexOrThrow5));
                offlineEntityInfo.setPassword(query.getString(columnIndexOrThrow6));
                offlineEntityInfo.setPatchTotal(query.getInt(columnIndexOrThrow7));
                offlineEntityInfo.setFileUrlZip(query.getString(columnIndexOrThrow8));
                offlineEntityInfo.setFileZipMd5(query.getString(columnIndexOrThrow9));
                offlineEntityInfo.setUseZip(query.getInt(columnIndexOrThrow10) != 0);
                if (query.isNull(columnIndexOrThrow11)) {
                    i2 = columnIndexOrThrow;
                    i3 = columnIndexOrThrow2;
                    i4 = columnIndexOrThrow3;
                    i5 = i21;
                    fileDetail = null;
                    if (query.isNull(i5)) {
                    }
                    i7 = i3;
                    i8 = columnIndexOrThrow16;
                    i11 = columnIndexOrThrow12;
                    fileDetail2 = new FileDetail();
                    i10 = columnIndexOrThrow13;
                    fileDetail2.setPath(query.getString(i5));
                    i13 = columnIndexOrThrow4;
                    i9 = columnIndexOrThrow5;
                    fileDetail2.setLastModified(query.getLong(i6));
                    fileDetail2.setTotalSpace(query.getLong(i8));
                    i12 = columnIndexOrThrow17;
                    if (query.isNull(i12)) {
                    }
                    i16 = columnIndexOrThrow19;
                    i19 = i13;
                    fileDetail3 = new FileDetail();
                    i18 = i5;
                    fileDetail3.setPath(query.getString(i12));
                    i17 = i6;
                    fileDetail3.setLastModified(query.getLong(i15));
                    fileDetail3.setTotalSpace(query.getLong(i16));
                    TestOfflineEntity testOfflineEntity222222 = new TestOfflineEntity();
                    int i22222222 = columnIndexOrThrow20;
                    int i23222222 = i16;
                    testOfflineEntity222222.setAppid(query.getString(i22222222));
                    int i24222222 = columnIndexOrThrow21;
                    testOfflineEntity222222.setModuleCode(query.getInt(i24222222));
                    int i25222222 = columnIndexOrThrow22;
                    testOfflineEntity222222.setType(query.getString(i25222222));
                    int i26222222 = columnIndexOrThrow23;
                    testOfflineEntity222222.setName(query.getString(i26222222));
                    int i27222222 = columnIndexOrThrow24;
                    testOfflineEntity222222.setDocumentUrl(query.getString(i27222222));
                    int i28222222 = columnIndexOrThrow25;
                    testOfflineEntity222222.setOriginalUrl(query.getString(i28222222));
                    int i29222222 = columnIndexOrThrow26;
                    testOfflineEntity222222.setOriginalUrlType(query.getString(i29222222));
                    int i30222222 = columnIndexOrThrow27;
                    testOfflineEntity222222.setPatch_total(query.getString(i30222222));
                    int i31222222 = columnIndexOrThrow28;
                    testOfflineEntity222222.setDocumentDir(query.getString(i31222222));
                    int i32222222 = columnIndexOrThrow29;
                    testOfflineEntity222222.setSourceRoot(query.getString(i32222222));
                    int i33222222 = columnIndexOrThrow30;
                    testOfflineEntity222222.setSourceDir(query.getString(i33222222));
                    int i34222222 = columnIndexOrThrow31;
                    testOfflineEntity222222.setAppMin(query.getString(i34222222));
                    int i35222222 = columnIndexOrThrow32;
                    testOfflineEntity222222.setAppMax(query.getString(i35222222));
                    int i36222222 = columnIndexOrThrow33;
                    testOfflineEntity222222.setServerPriority(query.getInt(i36222222));
                    int i37222222 = columnIndexOrThrow34;
                    testOfflineEntity222222.setCheckType(query.getString(i37222222));
                    int i38222222 = columnIndexOrThrow35;
                    testOfflineEntity222222.setFileRootPath(query.getString(i38222222));
                    int i39222222 = columnIndexOrThrow36;
                    testOfflineEntity222222.setHtmlPreload(query.getInt(i39222222));
                    int i40222222 = columnIndexOrThrow37;
                    testOfflineEntity222222.setHtmlStatic(query.getInt(i40222222));
                    int i41222222 = columnIndexOrThrow38;
                    testOfflineEntity222222.setCacheable(query.getInt(i41222222));
                    int i42222222 = columnIndexOrThrow39;
                    testOfflineEntity222222.setUngentoken(query.getInt(i42222222));
                    int i43222222 = columnIndexOrThrow40;
                    testOfflineEntity222222.setBConfig(query.getString(i43222222));
                    int i44222222 = columnIndexOrThrow41;
                    testOfflineEntity222222.setDegradeType(query.getInt(i44222222));
                    int i45222222 = columnIndexOrThrow42;
                    testOfflineEntity222222.setMinFileVer(query.getString(i45222222));
                    i20 = columnIndexOrThrow43;
                    if (query.getInt(i20) == 0) {
                    }
                    testOfflineEntity222222.setAvailable(z);
                    int i46222222 = columnIndexOrThrow44;
                    int i47222222 = i12;
                    testOfflineEntity222222.setCreateTimestamp(query.getLong(i46222222));
                    int i48222222 = columnIndexOrThrow45;
                    int i49222222 = i15;
                    testOfflineEntity222222.setLastVisitTimestamp(query.getLong(i48222222));
                    int i50222222 = columnIndexOrThrow46;
                    testOfflineEntity222222.setLocalPriorityInfo(query.getString(i50222222));
                    testOfflineEntity222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity222222.setDocumentFile(fileDetail);
                    testOfflineEntity222222.setSourceFile(fileDetail2);
                    testOfflineEntity222222.setZipFile(fileDetail3);
                    ArrayList arrayList3222222 = arrayList;
                    arrayList3222222.add(testOfflineEntity222222);
                    arrayList2 = arrayList3222222;
                    columnIndexOrThrow46 = i50222222;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow16 = i14;
                    columnIndexOrThrow4 = i19;
                    columnIndexOrThrow17 = i47222222;
                    columnIndexOrThrow18 = i49222222;
                    columnIndexOrThrow13 = i10;
                    columnIndexOrThrow5 = i9;
                    columnIndexOrThrow15 = i17;
                    columnIndexOrThrow44 = i46222222;
                    columnIndexOrThrow45 = i48222222;
                    i21 = i18;
                    columnIndexOrThrow19 = i23222222;
                    columnIndexOrThrow20 = i22222222;
                    columnIndexOrThrow21 = i24222222;
                    columnIndexOrThrow22 = i25222222;
                    columnIndexOrThrow23 = i26222222;
                    columnIndexOrThrow24 = i27222222;
                    columnIndexOrThrow25 = i28222222;
                    columnIndexOrThrow26 = i29222222;
                    columnIndexOrThrow27 = i30222222;
                    columnIndexOrThrow28 = i31222222;
                    columnIndexOrThrow29 = i32222222;
                    columnIndexOrThrow30 = i33222222;
                    columnIndexOrThrow31 = i34222222;
                    columnIndexOrThrow32 = i35222222;
                    columnIndexOrThrow33 = i36222222;
                    columnIndexOrThrow34 = i37222222;
                    columnIndexOrThrow35 = i38222222;
                    columnIndexOrThrow36 = i39222222;
                    columnIndexOrThrow37 = i40222222;
                    columnIndexOrThrow38 = i41222222;
                    columnIndexOrThrow39 = i42222222;
                    columnIndexOrThrow40 = i43222222;
                    columnIndexOrThrow41 = i44222222;
                    columnIndexOrThrow42 = i45222222;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow3 = i4;
                }
                fileDetail = new FileDetail();
                i2 = columnIndexOrThrow;
                fileDetail.setPath(query.getString(columnIndexOrThrow11));
                i3 = columnIndexOrThrow2;
                i4 = columnIndexOrThrow3;
                fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                i5 = i21;
                if (query.isNull(i5)) {
                }
                i7 = i3;
                i8 = columnIndexOrThrow16;
                i11 = columnIndexOrThrow12;
                fileDetail2 = new FileDetail();
                i10 = columnIndexOrThrow13;
                fileDetail2.setPath(query.getString(i5));
                i13 = columnIndexOrThrow4;
                i9 = columnIndexOrThrow5;
                fileDetail2.setLastModified(query.getLong(i6));
                fileDetail2.setTotalSpace(query.getLong(i8));
                i12 = columnIndexOrThrow17;
                if (query.isNull(i12)) {
                }
                i16 = columnIndexOrThrow19;
                i19 = i13;
                fileDetail3 = new FileDetail();
                i18 = i5;
                fileDetail3.setPath(query.getString(i12));
                i17 = i6;
                fileDetail3.setLastModified(query.getLong(i15));
                fileDetail3.setTotalSpace(query.getLong(i16));
                TestOfflineEntity testOfflineEntity2222222 = new TestOfflineEntity();
                int i222222222 = columnIndexOrThrow20;
                int i232222222 = i16;
                testOfflineEntity2222222.setAppid(query.getString(i222222222));
                int i242222222 = columnIndexOrThrow21;
                testOfflineEntity2222222.setModuleCode(query.getInt(i242222222));
                int i252222222 = columnIndexOrThrow22;
                testOfflineEntity2222222.setType(query.getString(i252222222));
                int i262222222 = columnIndexOrThrow23;
                testOfflineEntity2222222.setName(query.getString(i262222222));
                int i272222222 = columnIndexOrThrow24;
                testOfflineEntity2222222.setDocumentUrl(query.getString(i272222222));
                int i282222222 = columnIndexOrThrow25;
                testOfflineEntity2222222.setOriginalUrl(query.getString(i282222222));
                int i292222222 = columnIndexOrThrow26;
                testOfflineEntity2222222.setOriginalUrlType(query.getString(i292222222));
                int i302222222 = columnIndexOrThrow27;
                testOfflineEntity2222222.setPatch_total(query.getString(i302222222));
                int i312222222 = columnIndexOrThrow28;
                testOfflineEntity2222222.setDocumentDir(query.getString(i312222222));
                int i322222222 = columnIndexOrThrow29;
                testOfflineEntity2222222.setSourceRoot(query.getString(i322222222));
                int i332222222 = columnIndexOrThrow30;
                testOfflineEntity2222222.setSourceDir(query.getString(i332222222));
                int i342222222 = columnIndexOrThrow31;
                testOfflineEntity2222222.setAppMin(query.getString(i342222222));
                int i352222222 = columnIndexOrThrow32;
                testOfflineEntity2222222.setAppMax(query.getString(i352222222));
                int i362222222 = columnIndexOrThrow33;
                testOfflineEntity2222222.setServerPriority(query.getInt(i362222222));
                int i372222222 = columnIndexOrThrow34;
                testOfflineEntity2222222.setCheckType(query.getString(i372222222));
                int i382222222 = columnIndexOrThrow35;
                testOfflineEntity2222222.setFileRootPath(query.getString(i382222222));
                int i392222222 = columnIndexOrThrow36;
                testOfflineEntity2222222.setHtmlPreload(query.getInt(i392222222));
                int i402222222 = columnIndexOrThrow37;
                testOfflineEntity2222222.setHtmlStatic(query.getInt(i402222222));
                int i412222222 = columnIndexOrThrow38;
                testOfflineEntity2222222.setCacheable(query.getInt(i412222222));
                int i422222222 = columnIndexOrThrow39;
                testOfflineEntity2222222.setUngentoken(query.getInt(i422222222));
                int i432222222 = columnIndexOrThrow40;
                testOfflineEntity2222222.setBConfig(query.getString(i432222222));
                int i442222222 = columnIndexOrThrow41;
                testOfflineEntity2222222.setDegradeType(query.getInt(i442222222));
                int i452222222 = columnIndexOrThrow42;
                testOfflineEntity2222222.setMinFileVer(query.getString(i452222222));
                i20 = columnIndexOrThrow43;
                if (query.getInt(i20) == 0) {
                }
                testOfflineEntity2222222.setAvailable(z);
                int i462222222 = columnIndexOrThrow44;
                int i472222222 = i12;
                testOfflineEntity2222222.setCreateTimestamp(query.getLong(i462222222));
                int i482222222 = columnIndexOrThrow45;
                int i492222222 = i15;
                testOfflineEntity2222222.setLastVisitTimestamp(query.getLong(i482222222));
                int i502222222 = columnIndexOrThrow46;
                testOfflineEntity2222222.setLocalPriorityInfo(query.getString(i502222222));
                testOfflineEntity2222222.setFileInfo(offlineEntityInfo);
                testOfflineEntity2222222.setDocumentFile(fileDetail);
                testOfflineEntity2222222.setSourceFile(fileDetail2);
                testOfflineEntity2222222.setZipFile(fileDetail3);
                ArrayList arrayList32222222 = arrayList;
                arrayList32222222.add(testOfflineEntity2222222);
                arrayList2 = arrayList32222222;
                columnIndexOrThrow46 = i502222222;
                columnIndexOrThrow2 = i7;
                columnIndexOrThrow12 = i11;
                columnIndexOrThrow16 = i14;
                columnIndexOrThrow4 = i19;
                columnIndexOrThrow17 = i472222222;
                columnIndexOrThrow18 = i492222222;
                columnIndexOrThrow13 = i10;
                columnIndexOrThrow5 = i9;
                columnIndexOrThrow15 = i17;
                columnIndexOrThrow44 = i462222222;
                columnIndexOrThrow45 = i482222222;
                i21 = i18;
                columnIndexOrThrow19 = i232222222;
                columnIndexOrThrow20 = i222222222;
                columnIndexOrThrow21 = i242222222;
                columnIndexOrThrow22 = i252222222;
                columnIndexOrThrow23 = i262222222;
                columnIndexOrThrow24 = i272222222;
                columnIndexOrThrow25 = i282222222;
                columnIndexOrThrow26 = i292222222;
                columnIndexOrThrow27 = i302222222;
                columnIndexOrThrow28 = i312222222;
                columnIndexOrThrow29 = i322222222;
                columnIndexOrThrow30 = i332222222;
                columnIndexOrThrow31 = i342222222;
                columnIndexOrThrow32 = i352222222;
                columnIndexOrThrow33 = i362222222;
                columnIndexOrThrow34 = i372222222;
                columnIndexOrThrow35 = i382222222;
                columnIndexOrThrow36 = i392222222;
                columnIndexOrThrow37 = i402222222;
                columnIndexOrThrow38 = i412222222;
                columnIndexOrThrow39 = i422222222;
                columnIndexOrThrow40 = i432222222;
                columnIndexOrThrow41 = i442222222;
                columnIndexOrThrow42 = i452222222;
                columnIndexOrThrow = i2;
                columnIndexOrThrow3 = i4;
            }
            ArrayList arrayList4 = arrayList2;
            query.close();
            roomSQLiteQuery.release();
            return arrayList4;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public int getAllCount() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM HybridTestOfflineEntity", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0243 A[Catch: all -> 0x03c2, TryCatch #0 {all -> 0x03c2, blocks: (B:9:0x0077, B:11:0x017b, B:13:0x0181, B:15:0x0187, B:17:0x018d, B:19:0x0193, B:21:0x0199, B:23:0x019f, B:25:0x01a5, B:27:0x01ab, B:29:0x01b1, B:38:0x020e, B:40:0x0214, B:42:0x021a, B:47:0x023d, B:49:0x0243, B:51:0x024b, B:57:0x025c, B:58:0x0278, B:60:0x027e, B:62:0x0286, B:68:0x0295, B:69:0x02af, B:73:0x038e, B:46:0x0223, B:33:0x01bc, B:37:0x020b), top: B:84:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x027e A[Catch: all -> 0x03c2, TryCatch #0 {all -> 0x03c2, blocks: (B:9:0x0077, B:11:0x017b, B:13:0x0181, B:15:0x0187, B:17:0x018d, B:19:0x0193, B:21:0x0199, B:23:0x019f, B:25:0x01a5, B:27:0x01ab, B:29:0x01b1, B:38:0x020e, B:40:0x0214, B:42:0x021a, B:47:0x023d, B:49:0x0243, B:51:0x024b, B:57:0x025c, B:58:0x0278, B:60:0x027e, B:62:0x0286, B:68:0x0295, B:69:0x02af, B:73:0x038e, B:46:0x0223, B:33:0x01bc, B:37:0x020b), top: B:84:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x038d  */
    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TestOfflineEntity getById(String str) {
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
        int columnIndexOrThrow14;
        TestOfflineEntity testOfflineEntity;
        int i2;
        OfflineEntityInfo offlineEntityInfo;
        FileDetail fileDetail;
        int i3;
        int i4;
        FileDetail fileDetail2;
        int i5;
        int i6;
        int i7;
        FileDetail fileDetail3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `file_url`, `file_version`, `file_versionCode`, `file_md5`, `file_fileType`, `file_password`, `file_patchTotal`, `file_fileUrlZip`, `file_fileZipMd5`, `file_useZip`, `document_path`, `document_lastModified`, `document_totalSpace`, `source_path`, `source_lastModified`, `source_totalSpace`, `zip_path`, `zip_lastModified`, `zip_totalSpace`, `HybridTestOfflineEntity`.`appid` AS `appid`, `HybridTestOfflineEntity`.`moduleCode` AS `moduleCode`, `HybridTestOfflineEntity`.`type` AS `type`, `HybridTestOfflineEntity`.`name` AS `name`, `HybridTestOfflineEntity`.`documentUrl` AS `documentUrl`, `HybridTestOfflineEntity`.`originalUrl` AS `originalUrl`, `HybridTestOfflineEntity`.`originalUrlType` AS `originalUrlType`, `HybridTestOfflineEntity`.`patch_total` AS `patch_total`, `HybridTestOfflineEntity`.`documentDir` AS `documentDir`, `HybridTestOfflineEntity`.`sourceRoot` AS `sourceRoot`, `HybridTestOfflineEntity`.`sourceDir` AS `sourceDir`, `HybridTestOfflineEntity`.`appMin` AS `appMin`, `HybridTestOfflineEntity`.`appMax` AS `appMax`, `HybridTestOfflineEntity`.`serverPriority` AS `serverPriority`, `HybridTestOfflineEntity`.`checkType` AS `checkType`, `HybridTestOfflineEntity`.`fileRootPath` AS `fileRootPath`, `HybridTestOfflineEntity`.`htmlPreload` AS `htmlPreload`, `HybridTestOfflineEntity`.`htmlStatic` AS `htmlStatic`, `HybridTestOfflineEntity`.`cacheable` AS `cacheable`, `HybridTestOfflineEntity`.`ungentoken` AS `ungentoken`, `HybridTestOfflineEntity`.`bConfig` AS `bConfig`, `HybridTestOfflineEntity`.`degradeType` AS `degradeType`, `HybridTestOfflineEntity`.`minFileVer` AS `minFileVer`, `HybridTestOfflineEntity`.`available` AS `available`, `HybridTestOfflineEntity`.`createTimestamp` AS `createTimestamp`, `HybridTestOfflineEntity`.`lastVisitTimestamp` AS `lastVisitTimestamp`, `HybridTestOfflineEntity`.`localPriorityInfo` AS `localPriorityInfo` FROM HybridTestOfflineEntity WHERE appid=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "file_url");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_version");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "file_versionCode");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_md5");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_fileType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "file_password");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "file_patchTotal");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "file_fileUrlZip");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "file_fileZipMd5");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "file_useZip");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "document_path");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "document_lastModified");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "document_totalSpace");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "source_path");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "source_lastModified");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "source_totalSpace");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "zip_path");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "zip_lastModified");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "zip_totalSpace");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "appid");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, JDReactConstant.ModuleCode);
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "documentUrl");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "patch_total");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "documentDir");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "sourceRoot");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "sourceDir");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "serverPriority");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "checkType");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "fileRootPath");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "htmlPreload");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "htmlStatic");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "cacheable");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "ungentoken");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "degradeType");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "minFileVer");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "lastVisitTimestamp");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "localPriorityInfo");
            if (query.moveToFirst()) {
                if (query.isNull(columnIndexOrThrow) && query.isNull(columnIndexOrThrow2) && query.isNull(columnIndexOrThrow3) && query.isNull(columnIndexOrThrow4) && query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                    i2 = columnIndexOrThrow46;
                    offlineEntityInfo = null;
                    if (query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13)) {
                        fileDetail = null;
                        if (query.isNull(columnIndexOrThrow14)) {
                            i3 = columnIndexOrThrow15;
                        } else {
                            i3 = columnIndexOrThrow15;
                            if (query.isNull(i3)) {
                                i4 = columnIndexOrThrow16;
                                if (!query.isNull(i4)) {
                                    fileDetail2 = new FileDetail();
                                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                                    fileDetail2.setLastModified(query.getLong(i3));
                                    fileDetail2.setTotalSpace(query.getLong(i4));
                                    i5 = columnIndexOrThrow17;
                                    if (query.isNull(i5)) {
                                        i6 = columnIndexOrThrow18;
                                    } else {
                                        i6 = columnIndexOrThrow18;
                                        if (query.isNull(i6)) {
                                            i7 = columnIndexOrThrow19;
                                            if (!query.isNull(i7)) {
                                                fileDetail3 = new FileDetail();
                                                fileDetail3.setPath(query.getString(i5));
                                                fileDetail3.setLastModified(query.getLong(i6));
                                                fileDetail3.setTotalSpace(query.getLong(i7));
                                                TestOfflineEntity testOfflineEntity2 = new TestOfflineEntity();
                                                testOfflineEntity2.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity2.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity2.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity2.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity2.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity2.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity2.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity2.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity2.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity2.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity2.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity2.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity2.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity2.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity2.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity2.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity2.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity2.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity2.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity2.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity2.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity2.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity2.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity2.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity2.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity2.setLocalPriorityInfo(query.getString(i2));
                                                testOfflineEntity2.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity2.setDocumentFile(fileDetail);
                                                testOfflineEntity2.setSourceFile(fileDetail2);
                                                testOfflineEntity2.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity2;
                                            } else {
                                                fileDetail3 = null;
                                                TestOfflineEntity testOfflineEntity22 = new TestOfflineEntity();
                                                testOfflineEntity22.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity22.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity22.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity22.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity22.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity22.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity22.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity22.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity22.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity22.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity22.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity22.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity22.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity22.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity22.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity22.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity22.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity22.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity22.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity22.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity22.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity22.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity22.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity22.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity22.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity22.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity22.setLocalPriorityInfo(query.getString(i2));
                                                testOfflineEntity22.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity22.setDocumentFile(fileDetail);
                                                testOfflineEntity22.setSourceFile(fileDetail2);
                                                testOfflineEntity22.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity22;
                                            }
                                        }
                                    }
                                    i7 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i5));
                                    fileDetail3.setLastModified(query.getLong(i6));
                                    fileDetail3.setTotalSpace(query.getLong(i7));
                                    TestOfflineEntity testOfflineEntity222 = new TestOfflineEntity();
                                    testOfflineEntity222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity222.setLocalPriorityInfo(query.getString(i2));
                                    testOfflineEntity222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity222.setDocumentFile(fileDetail);
                                    testOfflineEntity222.setSourceFile(fileDetail2);
                                    testOfflineEntity222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity222;
                                } else {
                                    i5 = columnIndexOrThrow17;
                                    fileDetail2 = null;
                                    if (query.isNull(i5)) {
                                    }
                                    i7 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i5));
                                    fileDetail3.setLastModified(query.getLong(i6));
                                    fileDetail3.setTotalSpace(query.getLong(i7));
                                    TestOfflineEntity testOfflineEntity2222 = new TestOfflineEntity();
                                    testOfflineEntity2222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity2222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity2222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity2222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity2222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity2222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity2222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity2222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity2222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity2222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity2222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity2222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity2222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity2222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity2222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity2222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity2222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity2222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity2222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity2222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity2222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity2222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity2222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity2222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity2222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity2222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity2222.setLocalPriorityInfo(query.getString(i2));
                                    testOfflineEntity2222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity2222.setDocumentFile(fileDetail);
                                    testOfflineEntity2222.setSourceFile(fileDetail2);
                                    testOfflineEntity2222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity2222;
                                }
                            }
                        }
                        i4 = columnIndexOrThrow16;
                        fileDetail2 = new FileDetail();
                        fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                        fileDetail2.setLastModified(query.getLong(i3));
                        fileDetail2.setTotalSpace(query.getLong(i4));
                        i5 = columnIndexOrThrow17;
                        if (query.isNull(i5)) {
                        }
                        i7 = columnIndexOrThrow19;
                        fileDetail3 = new FileDetail();
                        fileDetail3.setPath(query.getString(i5));
                        fileDetail3.setLastModified(query.getLong(i6));
                        fileDetail3.setTotalSpace(query.getLong(i7));
                        TestOfflineEntity testOfflineEntity22222 = new TestOfflineEntity();
                        testOfflineEntity22222.setAppid(query.getString(columnIndexOrThrow20));
                        testOfflineEntity22222.setModuleCode(query.getInt(columnIndexOrThrow21));
                        testOfflineEntity22222.setType(query.getString(columnIndexOrThrow22));
                        testOfflineEntity22222.setName(query.getString(columnIndexOrThrow23));
                        testOfflineEntity22222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                        testOfflineEntity22222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                        testOfflineEntity22222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                        testOfflineEntity22222.setPatch_total(query.getString(columnIndexOrThrow27));
                        testOfflineEntity22222.setDocumentDir(query.getString(columnIndexOrThrow28));
                        testOfflineEntity22222.setSourceRoot(query.getString(columnIndexOrThrow29));
                        testOfflineEntity22222.setSourceDir(query.getString(columnIndexOrThrow30));
                        testOfflineEntity22222.setAppMin(query.getString(columnIndexOrThrow31));
                        testOfflineEntity22222.setAppMax(query.getString(columnIndexOrThrow32));
                        testOfflineEntity22222.setServerPriority(query.getInt(columnIndexOrThrow33));
                        testOfflineEntity22222.setCheckType(query.getString(columnIndexOrThrow34));
                        testOfflineEntity22222.setFileRootPath(query.getString(columnIndexOrThrow35));
                        testOfflineEntity22222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                        testOfflineEntity22222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                        testOfflineEntity22222.setCacheable(query.getInt(columnIndexOrThrow38));
                        testOfflineEntity22222.setUngentoken(query.getInt(columnIndexOrThrow39));
                        testOfflineEntity22222.setBConfig(query.getString(columnIndexOrThrow40));
                        testOfflineEntity22222.setDegradeType(query.getInt(columnIndexOrThrow41));
                        testOfflineEntity22222.setMinFileVer(query.getString(columnIndexOrThrow42));
                        testOfflineEntity22222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                        testOfflineEntity22222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                        testOfflineEntity22222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                        testOfflineEntity22222.setLocalPriorityInfo(query.getString(i2));
                        testOfflineEntity22222.setFileInfo(offlineEntityInfo);
                        testOfflineEntity22222.setDocumentFile(fileDetail);
                        testOfflineEntity22222.setSourceFile(fileDetail2);
                        testOfflineEntity22222.setZipFile(fileDetail3);
                        testOfflineEntity = testOfflineEntity22222;
                    }
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow11));
                    fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                    fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i4 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i3));
                    fileDetail2.setTotalSpace(query.getLong(i4));
                    i5 = columnIndexOrThrow17;
                    if (query.isNull(i5)) {
                    }
                    i7 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i5));
                    fileDetail3.setLastModified(query.getLong(i6));
                    fileDetail3.setTotalSpace(query.getLong(i7));
                    TestOfflineEntity testOfflineEntity222222 = new TestOfflineEntity();
                    testOfflineEntity222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity222222.setLocalPriorityInfo(query.getString(i2));
                    testOfflineEntity222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity222222.setDocumentFile(fileDetail);
                    testOfflineEntity222222.setSourceFile(fileDetail2);
                    testOfflineEntity222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity222222;
                }
                i2 = columnIndexOrThrow46;
                offlineEntityInfo = new OfflineEntityInfo();
                offlineEntityInfo.setUrl(query.getString(columnIndexOrThrow));
                offlineEntityInfo.setVersion(query.getString(columnIndexOrThrow2));
                offlineEntityInfo.setVersionCode(query.getInt(columnIndexOrThrow3));
                offlineEntityInfo.setMd5(query.getString(columnIndexOrThrow4));
                offlineEntityInfo.setFileType(query.getString(columnIndexOrThrow5));
                offlineEntityInfo.setPassword(query.getString(columnIndexOrThrow6));
                offlineEntityInfo.setPatchTotal(query.getInt(columnIndexOrThrow7));
                offlineEntityInfo.setFileUrlZip(query.getString(columnIndexOrThrow8));
                offlineEntityInfo.setFileZipMd5(query.getString(columnIndexOrThrow9));
                offlineEntityInfo.setUseZip(query.getInt(columnIndexOrThrow10) != 0);
                if (query.isNull(columnIndexOrThrow11)) {
                    fileDetail = null;
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i4 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i3));
                    fileDetail2.setTotalSpace(query.getLong(i4));
                    i5 = columnIndexOrThrow17;
                    if (query.isNull(i5)) {
                    }
                    i7 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i5));
                    fileDetail3.setLastModified(query.getLong(i6));
                    fileDetail3.setTotalSpace(query.getLong(i7));
                    TestOfflineEntity testOfflineEntity2222222 = new TestOfflineEntity();
                    testOfflineEntity2222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity2222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity2222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity2222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity2222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity2222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity2222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity2222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity2222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity2222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity2222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity2222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity2222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity2222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity2222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity2222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity2222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity2222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity2222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity2222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity2222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity2222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity2222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity2222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity2222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity2222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity2222222.setLocalPriorityInfo(query.getString(i2));
                    testOfflineEntity2222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity2222222.setDocumentFile(fileDetail);
                    testOfflineEntity2222222.setSourceFile(fileDetail2);
                    testOfflineEntity2222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity2222222;
                }
                fileDetail = new FileDetail();
                fileDetail.setPath(query.getString(columnIndexOrThrow11));
                fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                if (query.isNull(columnIndexOrThrow14)) {
                }
                i4 = columnIndexOrThrow16;
                fileDetail2 = new FileDetail();
                fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                fileDetail2.setLastModified(query.getLong(i3));
                fileDetail2.setTotalSpace(query.getLong(i4));
                i5 = columnIndexOrThrow17;
                if (query.isNull(i5)) {
                }
                i7 = columnIndexOrThrow19;
                fileDetail3 = new FileDetail();
                fileDetail3.setPath(query.getString(i5));
                fileDetail3.setLastModified(query.getLong(i6));
                fileDetail3.setTotalSpace(query.getLong(i7));
                TestOfflineEntity testOfflineEntity22222222 = new TestOfflineEntity();
                testOfflineEntity22222222.setAppid(query.getString(columnIndexOrThrow20));
                testOfflineEntity22222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                testOfflineEntity22222222.setType(query.getString(columnIndexOrThrow22));
                testOfflineEntity22222222.setName(query.getString(columnIndexOrThrow23));
                testOfflineEntity22222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                testOfflineEntity22222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                testOfflineEntity22222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                testOfflineEntity22222222.setPatch_total(query.getString(columnIndexOrThrow27));
                testOfflineEntity22222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                testOfflineEntity22222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                testOfflineEntity22222222.setSourceDir(query.getString(columnIndexOrThrow30));
                testOfflineEntity22222222.setAppMin(query.getString(columnIndexOrThrow31));
                testOfflineEntity22222222.setAppMax(query.getString(columnIndexOrThrow32));
                testOfflineEntity22222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                testOfflineEntity22222222.setCheckType(query.getString(columnIndexOrThrow34));
                testOfflineEntity22222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                testOfflineEntity22222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                testOfflineEntity22222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                testOfflineEntity22222222.setCacheable(query.getInt(columnIndexOrThrow38));
                testOfflineEntity22222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                testOfflineEntity22222222.setBConfig(query.getString(columnIndexOrThrow40));
                testOfflineEntity22222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                testOfflineEntity22222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                testOfflineEntity22222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                testOfflineEntity22222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                testOfflineEntity22222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                testOfflineEntity22222222.setLocalPriorityInfo(query.getString(i2));
                testOfflineEntity22222222.setFileInfo(offlineEntityInfo);
                testOfflineEntity22222222.setDocumentFile(fileDetail);
                testOfflineEntity22222222.setSourceFile(fileDetail2);
                testOfflineEntity22222222.setZipFile(fileDetail3);
                testOfflineEntity = testOfflineEntity22222222;
            } else {
                testOfflineEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return testOfflineEntity;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x024d A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0288 A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0397  */
    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TestOfflineEntity getByUrl(String str) {
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
        int columnIndexOrThrow14;
        TestOfflineEntity testOfflineEntity;
        int i2;
        OfflineEntityInfo offlineEntityInfo;
        FileDetail fileDetail;
        int i3;
        int i4;
        FileDetail fileDetail2;
        int i5;
        int i6;
        int i7;
        FileDetail fileDetail3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `file_url`, `file_version`, `file_versionCode`, `file_md5`, `file_fileType`, `file_password`, `file_patchTotal`, `file_fileUrlZip`, `file_fileZipMd5`, `file_useZip`, `document_path`, `document_lastModified`, `document_totalSpace`, `source_path`, `source_lastModified`, `source_totalSpace`, `zip_path`, `zip_lastModified`, `zip_totalSpace`, `HybridTestOfflineEntity`.`appid` AS `appid`, `HybridTestOfflineEntity`.`moduleCode` AS `moduleCode`, `HybridTestOfflineEntity`.`type` AS `type`, `HybridTestOfflineEntity`.`name` AS `name`, `HybridTestOfflineEntity`.`documentUrl` AS `documentUrl`, `HybridTestOfflineEntity`.`originalUrl` AS `originalUrl`, `HybridTestOfflineEntity`.`originalUrlType` AS `originalUrlType`, `HybridTestOfflineEntity`.`patch_total` AS `patch_total`, `HybridTestOfflineEntity`.`documentDir` AS `documentDir`, `HybridTestOfflineEntity`.`sourceRoot` AS `sourceRoot`, `HybridTestOfflineEntity`.`sourceDir` AS `sourceDir`, `HybridTestOfflineEntity`.`appMin` AS `appMin`, `HybridTestOfflineEntity`.`appMax` AS `appMax`, `HybridTestOfflineEntity`.`serverPriority` AS `serverPriority`, `HybridTestOfflineEntity`.`checkType` AS `checkType`, `HybridTestOfflineEntity`.`fileRootPath` AS `fileRootPath`, `HybridTestOfflineEntity`.`htmlPreload` AS `htmlPreload`, `HybridTestOfflineEntity`.`htmlStatic` AS `htmlStatic`, `HybridTestOfflineEntity`.`cacheable` AS `cacheable`, `HybridTestOfflineEntity`.`ungentoken` AS `ungentoken`, `HybridTestOfflineEntity`.`bConfig` AS `bConfig`, `HybridTestOfflineEntity`.`degradeType` AS `degradeType`, `HybridTestOfflineEntity`.`minFileVer` AS `minFileVer`, `HybridTestOfflineEntity`.`available` AS `available`, `HybridTestOfflineEntity`.`createTimestamp` AS `createTimestamp`, `HybridTestOfflineEntity`.`lastVisitTimestamp` AS `lastVisitTimestamp`, `HybridTestOfflineEntity`.`localPriorityInfo` AS `localPriorityInfo` FROM HybridTestOfflineEntity WHERE documentUrl=? OR (originalUrl=? AND originalUrlType='1') LIMIT 1", 2);
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
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "file_url");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_version");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "file_versionCode");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_md5");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_fileType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "file_password");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "file_patchTotal");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "file_fileUrlZip");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "file_fileZipMd5");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "file_useZip");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "document_path");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "document_lastModified");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "document_totalSpace");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "source_path");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "source_lastModified");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "source_totalSpace");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "zip_path");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "zip_lastModified");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "zip_totalSpace");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "appid");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, JDReactConstant.ModuleCode);
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "documentUrl");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "patch_total");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "documentDir");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "sourceRoot");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "sourceDir");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "serverPriority");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "checkType");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "fileRootPath");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "htmlPreload");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "htmlStatic");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "cacheable");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "ungentoken");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "degradeType");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "minFileVer");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "lastVisitTimestamp");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "localPriorityInfo");
            if (query.moveToFirst()) {
                if (query.isNull(columnIndexOrThrow) && query.isNull(columnIndexOrThrow2) && query.isNull(columnIndexOrThrow3) && query.isNull(columnIndexOrThrow4) && query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                    i2 = columnIndexOrThrow46;
                    offlineEntityInfo = null;
                    if (query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13)) {
                        fileDetail = null;
                        if (query.isNull(columnIndexOrThrow14)) {
                            i3 = columnIndexOrThrow15;
                        } else {
                            i3 = columnIndexOrThrow15;
                            if (query.isNull(i3)) {
                                i4 = columnIndexOrThrow16;
                                if (!query.isNull(i4)) {
                                    fileDetail2 = new FileDetail();
                                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                                    fileDetail2.setLastModified(query.getLong(i3));
                                    fileDetail2.setTotalSpace(query.getLong(i4));
                                    i5 = columnIndexOrThrow17;
                                    if (query.isNull(i5)) {
                                        i6 = columnIndexOrThrow18;
                                    } else {
                                        i6 = columnIndexOrThrow18;
                                        if (query.isNull(i6)) {
                                            i7 = columnIndexOrThrow19;
                                            if (!query.isNull(i7)) {
                                                fileDetail3 = new FileDetail();
                                                fileDetail3.setPath(query.getString(i5));
                                                fileDetail3.setLastModified(query.getLong(i6));
                                                fileDetail3.setTotalSpace(query.getLong(i7));
                                                TestOfflineEntity testOfflineEntity2 = new TestOfflineEntity();
                                                testOfflineEntity2.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity2.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity2.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity2.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity2.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity2.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity2.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity2.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity2.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity2.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity2.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity2.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity2.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity2.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity2.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity2.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity2.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity2.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity2.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity2.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity2.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity2.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity2.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity2.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity2.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity2.setLocalPriorityInfo(query.getString(i2));
                                                testOfflineEntity2.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity2.setDocumentFile(fileDetail);
                                                testOfflineEntity2.setSourceFile(fileDetail2);
                                                testOfflineEntity2.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity2;
                                            } else {
                                                fileDetail3 = null;
                                                TestOfflineEntity testOfflineEntity22 = new TestOfflineEntity();
                                                testOfflineEntity22.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity22.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity22.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity22.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity22.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity22.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity22.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity22.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity22.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity22.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity22.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity22.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity22.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity22.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity22.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity22.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity22.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity22.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity22.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity22.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity22.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity22.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity22.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity22.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity22.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity22.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity22.setLocalPriorityInfo(query.getString(i2));
                                                testOfflineEntity22.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity22.setDocumentFile(fileDetail);
                                                testOfflineEntity22.setSourceFile(fileDetail2);
                                                testOfflineEntity22.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity22;
                                            }
                                        }
                                    }
                                    i7 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i5));
                                    fileDetail3.setLastModified(query.getLong(i6));
                                    fileDetail3.setTotalSpace(query.getLong(i7));
                                    TestOfflineEntity testOfflineEntity222 = new TestOfflineEntity();
                                    testOfflineEntity222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity222.setLocalPriorityInfo(query.getString(i2));
                                    testOfflineEntity222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity222.setDocumentFile(fileDetail);
                                    testOfflineEntity222.setSourceFile(fileDetail2);
                                    testOfflineEntity222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity222;
                                } else {
                                    i5 = columnIndexOrThrow17;
                                    fileDetail2 = null;
                                    if (query.isNull(i5)) {
                                    }
                                    i7 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i5));
                                    fileDetail3.setLastModified(query.getLong(i6));
                                    fileDetail3.setTotalSpace(query.getLong(i7));
                                    TestOfflineEntity testOfflineEntity2222 = new TestOfflineEntity();
                                    testOfflineEntity2222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity2222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity2222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity2222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity2222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity2222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity2222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity2222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity2222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity2222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity2222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity2222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity2222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity2222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity2222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity2222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity2222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity2222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity2222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity2222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity2222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity2222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity2222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity2222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity2222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity2222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity2222.setLocalPriorityInfo(query.getString(i2));
                                    testOfflineEntity2222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity2222.setDocumentFile(fileDetail);
                                    testOfflineEntity2222.setSourceFile(fileDetail2);
                                    testOfflineEntity2222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity2222;
                                }
                            }
                        }
                        i4 = columnIndexOrThrow16;
                        fileDetail2 = new FileDetail();
                        fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                        fileDetail2.setLastModified(query.getLong(i3));
                        fileDetail2.setTotalSpace(query.getLong(i4));
                        i5 = columnIndexOrThrow17;
                        if (query.isNull(i5)) {
                        }
                        i7 = columnIndexOrThrow19;
                        fileDetail3 = new FileDetail();
                        fileDetail3.setPath(query.getString(i5));
                        fileDetail3.setLastModified(query.getLong(i6));
                        fileDetail3.setTotalSpace(query.getLong(i7));
                        TestOfflineEntity testOfflineEntity22222 = new TestOfflineEntity();
                        testOfflineEntity22222.setAppid(query.getString(columnIndexOrThrow20));
                        testOfflineEntity22222.setModuleCode(query.getInt(columnIndexOrThrow21));
                        testOfflineEntity22222.setType(query.getString(columnIndexOrThrow22));
                        testOfflineEntity22222.setName(query.getString(columnIndexOrThrow23));
                        testOfflineEntity22222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                        testOfflineEntity22222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                        testOfflineEntity22222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                        testOfflineEntity22222.setPatch_total(query.getString(columnIndexOrThrow27));
                        testOfflineEntity22222.setDocumentDir(query.getString(columnIndexOrThrow28));
                        testOfflineEntity22222.setSourceRoot(query.getString(columnIndexOrThrow29));
                        testOfflineEntity22222.setSourceDir(query.getString(columnIndexOrThrow30));
                        testOfflineEntity22222.setAppMin(query.getString(columnIndexOrThrow31));
                        testOfflineEntity22222.setAppMax(query.getString(columnIndexOrThrow32));
                        testOfflineEntity22222.setServerPriority(query.getInt(columnIndexOrThrow33));
                        testOfflineEntity22222.setCheckType(query.getString(columnIndexOrThrow34));
                        testOfflineEntity22222.setFileRootPath(query.getString(columnIndexOrThrow35));
                        testOfflineEntity22222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                        testOfflineEntity22222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                        testOfflineEntity22222.setCacheable(query.getInt(columnIndexOrThrow38));
                        testOfflineEntity22222.setUngentoken(query.getInt(columnIndexOrThrow39));
                        testOfflineEntity22222.setBConfig(query.getString(columnIndexOrThrow40));
                        testOfflineEntity22222.setDegradeType(query.getInt(columnIndexOrThrow41));
                        testOfflineEntity22222.setMinFileVer(query.getString(columnIndexOrThrow42));
                        testOfflineEntity22222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                        testOfflineEntity22222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                        testOfflineEntity22222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                        testOfflineEntity22222.setLocalPriorityInfo(query.getString(i2));
                        testOfflineEntity22222.setFileInfo(offlineEntityInfo);
                        testOfflineEntity22222.setDocumentFile(fileDetail);
                        testOfflineEntity22222.setSourceFile(fileDetail2);
                        testOfflineEntity22222.setZipFile(fileDetail3);
                        testOfflineEntity = testOfflineEntity22222;
                    }
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow11));
                    fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                    fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i4 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i3));
                    fileDetail2.setTotalSpace(query.getLong(i4));
                    i5 = columnIndexOrThrow17;
                    if (query.isNull(i5)) {
                    }
                    i7 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i5));
                    fileDetail3.setLastModified(query.getLong(i6));
                    fileDetail3.setTotalSpace(query.getLong(i7));
                    TestOfflineEntity testOfflineEntity222222 = new TestOfflineEntity();
                    testOfflineEntity222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity222222.setLocalPriorityInfo(query.getString(i2));
                    testOfflineEntity222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity222222.setDocumentFile(fileDetail);
                    testOfflineEntity222222.setSourceFile(fileDetail2);
                    testOfflineEntity222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity222222;
                }
                i2 = columnIndexOrThrow46;
                offlineEntityInfo = new OfflineEntityInfo();
                offlineEntityInfo.setUrl(query.getString(columnIndexOrThrow));
                offlineEntityInfo.setVersion(query.getString(columnIndexOrThrow2));
                offlineEntityInfo.setVersionCode(query.getInt(columnIndexOrThrow3));
                offlineEntityInfo.setMd5(query.getString(columnIndexOrThrow4));
                offlineEntityInfo.setFileType(query.getString(columnIndexOrThrow5));
                offlineEntityInfo.setPassword(query.getString(columnIndexOrThrow6));
                offlineEntityInfo.setPatchTotal(query.getInt(columnIndexOrThrow7));
                offlineEntityInfo.setFileUrlZip(query.getString(columnIndexOrThrow8));
                offlineEntityInfo.setFileZipMd5(query.getString(columnIndexOrThrow9));
                offlineEntityInfo.setUseZip(query.getInt(columnIndexOrThrow10) != 0);
                if (query.isNull(columnIndexOrThrow11)) {
                    fileDetail = null;
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i4 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i3));
                    fileDetail2.setTotalSpace(query.getLong(i4));
                    i5 = columnIndexOrThrow17;
                    if (query.isNull(i5)) {
                    }
                    i7 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i5));
                    fileDetail3.setLastModified(query.getLong(i6));
                    fileDetail3.setTotalSpace(query.getLong(i7));
                    TestOfflineEntity testOfflineEntity2222222 = new TestOfflineEntity();
                    testOfflineEntity2222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity2222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity2222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity2222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity2222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity2222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity2222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity2222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity2222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity2222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity2222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity2222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity2222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity2222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity2222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity2222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity2222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity2222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity2222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity2222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity2222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity2222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity2222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity2222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity2222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity2222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity2222222.setLocalPriorityInfo(query.getString(i2));
                    testOfflineEntity2222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity2222222.setDocumentFile(fileDetail);
                    testOfflineEntity2222222.setSourceFile(fileDetail2);
                    testOfflineEntity2222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity2222222;
                }
                fileDetail = new FileDetail();
                fileDetail.setPath(query.getString(columnIndexOrThrow11));
                fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                if (query.isNull(columnIndexOrThrow14)) {
                }
                i4 = columnIndexOrThrow16;
                fileDetail2 = new FileDetail();
                fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                fileDetail2.setLastModified(query.getLong(i3));
                fileDetail2.setTotalSpace(query.getLong(i4));
                i5 = columnIndexOrThrow17;
                if (query.isNull(i5)) {
                }
                i7 = columnIndexOrThrow19;
                fileDetail3 = new FileDetail();
                fileDetail3.setPath(query.getString(i5));
                fileDetail3.setLastModified(query.getLong(i6));
                fileDetail3.setTotalSpace(query.getLong(i7));
                TestOfflineEntity testOfflineEntity22222222 = new TestOfflineEntity();
                testOfflineEntity22222222.setAppid(query.getString(columnIndexOrThrow20));
                testOfflineEntity22222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                testOfflineEntity22222222.setType(query.getString(columnIndexOrThrow22));
                testOfflineEntity22222222.setName(query.getString(columnIndexOrThrow23));
                testOfflineEntity22222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                testOfflineEntity22222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                testOfflineEntity22222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                testOfflineEntity22222222.setPatch_total(query.getString(columnIndexOrThrow27));
                testOfflineEntity22222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                testOfflineEntity22222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                testOfflineEntity22222222.setSourceDir(query.getString(columnIndexOrThrow30));
                testOfflineEntity22222222.setAppMin(query.getString(columnIndexOrThrow31));
                testOfflineEntity22222222.setAppMax(query.getString(columnIndexOrThrow32));
                testOfflineEntity22222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                testOfflineEntity22222222.setCheckType(query.getString(columnIndexOrThrow34));
                testOfflineEntity22222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                testOfflineEntity22222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                testOfflineEntity22222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                testOfflineEntity22222222.setCacheable(query.getInt(columnIndexOrThrow38));
                testOfflineEntity22222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                testOfflineEntity22222222.setBConfig(query.getString(columnIndexOrThrow40));
                testOfflineEntity22222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                testOfflineEntity22222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                testOfflineEntity22222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                testOfflineEntity22222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                testOfflineEntity22222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                testOfflineEntity22222222.setLocalPriorityInfo(query.getString(i2));
                testOfflineEntity22222222.setFileInfo(offlineEntityInfo);
                testOfflineEntity22222222.setDocumentFile(fileDetail);
                testOfflineEntity22222222.setSourceFile(fileDetail2);
                testOfflineEntity22222222.setZipFile(fileDetail3);
                testOfflineEntity = testOfflineEntity22222222;
            } else {
                testOfflineEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return testOfflineEntity;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x024d A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0288 A[Catch: all -> 0x03cc, TryCatch #0 {all -> 0x03cc, blocks: (B:12:0x0081, B:14:0x0185, B:16:0x018b, B:18:0x0191, B:20:0x0197, B:22:0x019d, B:24:0x01a3, B:26:0x01a9, B:28:0x01af, B:30:0x01b5, B:32:0x01bb, B:41:0x0218, B:43:0x021e, B:45:0x0224, B:50:0x0247, B:52:0x024d, B:54:0x0255, B:60:0x0266, B:61:0x0282, B:63:0x0288, B:65:0x0290, B:71:0x029f, B:72:0x02b9, B:76:0x0398, B:49:0x022d, B:36:0x01c6, B:40:0x0215), top: B:87:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0397  */
    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TestOfflineEntity getOneAvailableByUrl(String str) {
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
        int columnIndexOrThrow14;
        TestOfflineEntity testOfflineEntity;
        int i2;
        OfflineEntityInfo offlineEntityInfo;
        FileDetail fileDetail;
        int i3;
        int i4;
        FileDetail fileDetail2;
        int i5;
        int i6;
        int i7;
        FileDetail fileDetail3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `file_url`, `file_version`, `file_versionCode`, `file_md5`, `file_fileType`, `file_password`, `file_patchTotal`, `file_fileUrlZip`, `file_fileZipMd5`, `file_useZip`, `document_path`, `document_lastModified`, `document_totalSpace`, `source_path`, `source_lastModified`, `source_totalSpace`, `zip_path`, `zip_lastModified`, `zip_totalSpace`, `HybridTestOfflineEntity`.`appid` AS `appid`, `HybridTestOfflineEntity`.`moduleCode` AS `moduleCode`, `HybridTestOfflineEntity`.`type` AS `type`, `HybridTestOfflineEntity`.`name` AS `name`, `HybridTestOfflineEntity`.`documentUrl` AS `documentUrl`, `HybridTestOfflineEntity`.`originalUrl` AS `originalUrl`, `HybridTestOfflineEntity`.`originalUrlType` AS `originalUrlType`, `HybridTestOfflineEntity`.`patch_total` AS `patch_total`, `HybridTestOfflineEntity`.`documentDir` AS `documentDir`, `HybridTestOfflineEntity`.`sourceRoot` AS `sourceRoot`, `HybridTestOfflineEntity`.`sourceDir` AS `sourceDir`, `HybridTestOfflineEntity`.`appMin` AS `appMin`, `HybridTestOfflineEntity`.`appMax` AS `appMax`, `HybridTestOfflineEntity`.`serverPriority` AS `serverPriority`, `HybridTestOfflineEntity`.`checkType` AS `checkType`, `HybridTestOfflineEntity`.`fileRootPath` AS `fileRootPath`, `HybridTestOfflineEntity`.`htmlPreload` AS `htmlPreload`, `HybridTestOfflineEntity`.`htmlStatic` AS `htmlStatic`, `HybridTestOfflineEntity`.`cacheable` AS `cacheable`, `HybridTestOfflineEntity`.`ungentoken` AS `ungentoken`, `HybridTestOfflineEntity`.`bConfig` AS `bConfig`, `HybridTestOfflineEntity`.`degradeType` AS `degradeType`, `HybridTestOfflineEntity`.`minFileVer` AS `minFileVer`, `HybridTestOfflineEntity`.`available` AS `available`, `HybridTestOfflineEntity`.`createTimestamp` AS `createTimestamp`, `HybridTestOfflineEntity`.`lastVisitTimestamp` AS `lastVisitTimestamp`, `HybridTestOfflineEntity`.`localPriorityInfo` AS `localPriorityInfo` FROM HybridTestOfflineEntity WHERE (documentUrl=? OR originalUrl=?) AND available = 1 LIMIT 1", 2);
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
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "file_url");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_version");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "file_versionCode");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_md5");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_fileType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "file_password");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "file_patchTotal");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "file_fileUrlZip");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "file_fileZipMd5");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "file_useZip");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "document_path");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "document_lastModified");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "document_totalSpace");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "source_path");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "source_lastModified");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "source_totalSpace");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "zip_path");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "zip_lastModified");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "zip_totalSpace");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "appid");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, JDReactConstant.ModuleCode);
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "documentUrl");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "patch_total");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "documentDir");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "sourceRoot");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "sourceDir");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "serverPriority");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "checkType");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "fileRootPath");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "htmlPreload");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "htmlStatic");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "cacheable");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "ungentoken");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "degradeType");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "minFileVer");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "lastVisitTimestamp");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "localPriorityInfo");
            if (query.moveToFirst()) {
                if (query.isNull(columnIndexOrThrow) && query.isNull(columnIndexOrThrow2) && query.isNull(columnIndexOrThrow3) && query.isNull(columnIndexOrThrow4) && query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                    i2 = columnIndexOrThrow46;
                    offlineEntityInfo = null;
                    if (query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13)) {
                        fileDetail = null;
                        if (query.isNull(columnIndexOrThrow14)) {
                            i3 = columnIndexOrThrow15;
                        } else {
                            i3 = columnIndexOrThrow15;
                            if (query.isNull(i3)) {
                                i4 = columnIndexOrThrow16;
                                if (!query.isNull(i4)) {
                                    fileDetail2 = new FileDetail();
                                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                                    fileDetail2.setLastModified(query.getLong(i3));
                                    fileDetail2.setTotalSpace(query.getLong(i4));
                                    i5 = columnIndexOrThrow17;
                                    if (query.isNull(i5)) {
                                        i6 = columnIndexOrThrow18;
                                    } else {
                                        i6 = columnIndexOrThrow18;
                                        if (query.isNull(i6)) {
                                            i7 = columnIndexOrThrow19;
                                            if (!query.isNull(i7)) {
                                                fileDetail3 = new FileDetail();
                                                fileDetail3.setPath(query.getString(i5));
                                                fileDetail3.setLastModified(query.getLong(i6));
                                                fileDetail3.setTotalSpace(query.getLong(i7));
                                                TestOfflineEntity testOfflineEntity2 = new TestOfflineEntity();
                                                testOfflineEntity2.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity2.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity2.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity2.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity2.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity2.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity2.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity2.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity2.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity2.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity2.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity2.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity2.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity2.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity2.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity2.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity2.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity2.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity2.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity2.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity2.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity2.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity2.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity2.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity2.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity2.setLocalPriorityInfo(query.getString(i2));
                                                testOfflineEntity2.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity2.setDocumentFile(fileDetail);
                                                testOfflineEntity2.setSourceFile(fileDetail2);
                                                testOfflineEntity2.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity2;
                                            } else {
                                                fileDetail3 = null;
                                                TestOfflineEntity testOfflineEntity22 = new TestOfflineEntity();
                                                testOfflineEntity22.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity22.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity22.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity22.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity22.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity22.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity22.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity22.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity22.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity22.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity22.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity22.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity22.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity22.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity22.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity22.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity22.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity22.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity22.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity22.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity22.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity22.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity22.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity22.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity22.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity22.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity22.setLocalPriorityInfo(query.getString(i2));
                                                testOfflineEntity22.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity22.setDocumentFile(fileDetail);
                                                testOfflineEntity22.setSourceFile(fileDetail2);
                                                testOfflineEntity22.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity22;
                                            }
                                        }
                                    }
                                    i7 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i5));
                                    fileDetail3.setLastModified(query.getLong(i6));
                                    fileDetail3.setTotalSpace(query.getLong(i7));
                                    TestOfflineEntity testOfflineEntity222 = new TestOfflineEntity();
                                    testOfflineEntity222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity222.setLocalPriorityInfo(query.getString(i2));
                                    testOfflineEntity222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity222.setDocumentFile(fileDetail);
                                    testOfflineEntity222.setSourceFile(fileDetail2);
                                    testOfflineEntity222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity222;
                                } else {
                                    i5 = columnIndexOrThrow17;
                                    fileDetail2 = null;
                                    if (query.isNull(i5)) {
                                    }
                                    i7 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i5));
                                    fileDetail3.setLastModified(query.getLong(i6));
                                    fileDetail3.setTotalSpace(query.getLong(i7));
                                    TestOfflineEntity testOfflineEntity2222 = new TestOfflineEntity();
                                    testOfflineEntity2222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity2222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity2222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity2222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity2222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity2222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity2222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity2222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity2222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity2222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity2222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity2222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity2222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity2222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity2222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity2222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity2222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity2222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity2222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity2222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity2222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity2222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity2222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity2222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity2222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity2222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity2222.setLocalPriorityInfo(query.getString(i2));
                                    testOfflineEntity2222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity2222.setDocumentFile(fileDetail);
                                    testOfflineEntity2222.setSourceFile(fileDetail2);
                                    testOfflineEntity2222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity2222;
                                }
                            }
                        }
                        i4 = columnIndexOrThrow16;
                        fileDetail2 = new FileDetail();
                        fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                        fileDetail2.setLastModified(query.getLong(i3));
                        fileDetail2.setTotalSpace(query.getLong(i4));
                        i5 = columnIndexOrThrow17;
                        if (query.isNull(i5)) {
                        }
                        i7 = columnIndexOrThrow19;
                        fileDetail3 = new FileDetail();
                        fileDetail3.setPath(query.getString(i5));
                        fileDetail3.setLastModified(query.getLong(i6));
                        fileDetail3.setTotalSpace(query.getLong(i7));
                        TestOfflineEntity testOfflineEntity22222 = new TestOfflineEntity();
                        testOfflineEntity22222.setAppid(query.getString(columnIndexOrThrow20));
                        testOfflineEntity22222.setModuleCode(query.getInt(columnIndexOrThrow21));
                        testOfflineEntity22222.setType(query.getString(columnIndexOrThrow22));
                        testOfflineEntity22222.setName(query.getString(columnIndexOrThrow23));
                        testOfflineEntity22222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                        testOfflineEntity22222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                        testOfflineEntity22222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                        testOfflineEntity22222.setPatch_total(query.getString(columnIndexOrThrow27));
                        testOfflineEntity22222.setDocumentDir(query.getString(columnIndexOrThrow28));
                        testOfflineEntity22222.setSourceRoot(query.getString(columnIndexOrThrow29));
                        testOfflineEntity22222.setSourceDir(query.getString(columnIndexOrThrow30));
                        testOfflineEntity22222.setAppMin(query.getString(columnIndexOrThrow31));
                        testOfflineEntity22222.setAppMax(query.getString(columnIndexOrThrow32));
                        testOfflineEntity22222.setServerPriority(query.getInt(columnIndexOrThrow33));
                        testOfflineEntity22222.setCheckType(query.getString(columnIndexOrThrow34));
                        testOfflineEntity22222.setFileRootPath(query.getString(columnIndexOrThrow35));
                        testOfflineEntity22222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                        testOfflineEntity22222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                        testOfflineEntity22222.setCacheable(query.getInt(columnIndexOrThrow38));
                        testOfflineEntity22222.setUngentoken(query.getInt(columnIndexOrThrow39));
                        testOfflineEntity22222.setBConfig(query.getString(columnIndexOrThrow40));
                        testOfflineEntity22222.setDegradeType(query.getInt(columnIndexOrThrow41));
                        testOfflineEntity22222.setMinFileVer(query.getString(columnIndexOrThrow42));
                        testOfflineEntity22222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                        testOfflineEntity22222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                        testOfflineEntity22222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                        testOfflineEntity22222.setLocalPriorityInfo(query.getString(i2));
                        testOfflineEntity22222.setFileInfo(offlineEntityInfo);
                        testOfflineEntity22222.setDocumentFile(fileDetail);
                        testOfflineEntity22222.setSourceFile(fileDetail2);
                        testOfflineEntity22222.setZipFile(fileDetail3);
                        testOfflineEntity = testOfflineEntity22222;
                    }
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow11));
                    fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                    fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i4 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i3));
                    fileDetail2.setTotalSpace(query.getLong(i4));
                    i5 = columnIndexOrThrow17;
                    if (query.isNull(i5)) {
                    }
                    i7 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i5));
                    fileDetail3.setLastModified(query.getLong(i6));
                    fileDetail3.setTotalSpace(query.getLong(i7));
                    TestOfflineEntity testOfflineEntity222222 = new TestOfflineEntity();
                    testOfflineEntity222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity222222.setLocalPriorityInfo(query.getString(i2));
                    testOfflineEntity222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity222222.setDocumentFile(fileDetail);
                    testOfflineEntity222222.setSourceFile(fileDetail2);
                    testOfflineEntity222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity222222;
                }
                i2 = columnIndexOrThrow46;
                offlineEntityInfo = new OfflineEntityInfo();
                offlineEntityInfo.setUrl(query.getString(columnIndexOrThrow));
                offlineEntityInfo.setVersion(query.getString(columnIndexOrThrow2));
                offlineEntityInfo.setVersionCode(query.getInt(columnIndexOrThrow3));
                offlineEntityInfo.setMd5(query.getString(columnIndexOrThrow4));
                offlineEntityInfo.setFileType(query.getString(columnIndexOrThrow5));
                offlineEntityInfo.setPassword(query.getString(columnIndexOrThrow6));
                offlineEntityInfo.setPatchTotal(query.getInt(columnIndexOrThrow7));
                offlineEntityInfo.setFileUrlZip(query.getString(columnIndexOrThrow8));
                offlineEntityInfo.setFileZipMd5(query.getString(columnIndexOrThrow9));
                offlineEntityInfo.setUseZip(query.getInt(columnIndexOrThrow10) != 0);
                if (query.isNull(columnIndexOrThrow11)) {
                    fileDetail = null;
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i4 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i3));
                    fileDetail2.setTotalSpace(query.getLong(i4));
                    i5 = columnIndexOrThrow17;
                    if (query.isNull(i5)) {
                    }
                    i7 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i5));
                    fileDetail3.setLastModified(query.getLong(i6));
                    fileDetail3.setTotalSpace(query.getLong(i7));
                    TestOfflineEntity testOfflineEntity2222222 = new TestOfflineEntity();
                    testOfflineEntity2222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity2222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity2222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity2222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity2222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity2222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity2222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity2222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity2222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity2222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity2222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity2222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity2222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity2222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity2222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity2222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity2222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity2222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity2222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity2222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity2222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity2222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity2222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity2222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity2222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity2222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity2222222.setLocalPriorityInfo(query.getString(i2));
                    testOfflineEntity2222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity2222222.setDocumentFile(fileDetail);
                    testOfflineEntity2222222.setSourceFile(fileDetail2);
                    testOfflineEntity2222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity2222222;
                }
                fileDetail = new FileDetail();
                fileDetail.setPath(query.getString(columnIndexOrThrow11));
                fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                if (query.isNull(columnIndexOrThrow14)) {
                }
                i4 = columnIndexOrThrow16;
                fileDetail2 = new FileDetail();
                fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                fileDetail2.setLastModified(query.getLong(i3));
                fileDetail2.setTotalSpace(query.getLong(i4));
                i5 = columnIndexOrThrow17;
                if (query.isNull(i5)) {
                }
                i7 = columnIndexOrThrow19;
                fileDetail3 = new FileDetail();
                fileDetail3.setPath(query.getString(i5));
                fileDetail3.setLastModified(query.getLong(i6));
                fileDetail3.setTotalSpace(query.getLong(i7));
                TestOfflineEntity testOfflineEntity22222222 = new TestOfflineEntity();
                testOfflineEntity22222222.setAppid(query.getString(columnIndexOrThrow20));
                testOfflineEntity22222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                testOfflineEntity22222222.setType(query.getString(columnIndexOrThrow22));
                testOfflineEntity22222222.setName(query.getString(columnIndexOrThrow23));
                testOfflineEntity22222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                testOfflineEntity22222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                testOfflineEntity22222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                testOfflineEntity22222222.setPatch_total(query.getString(columnIndexOrThrow27));
                testOfflineEntity22222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                testOfflineEntity22222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                testOfflineEntity22222222.setSourceDir(query.getString(columnIndexOrThrow30));
                testOfflineEntity22222222.setAppMin(query.getString(columnIndexOrThrow31));
                testOfflineEntity22222222.setAppMax(query.getString(columnIndexOrThrow32));
                testOfflineEntity22222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                testOfflineEntity22222222.setCheckType(query.getString(columnIndexOrThrow34));
                testOfflineEntity22222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                testOfflineEntity22222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                testOfflineEntity22222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                testOfflineEntity22222222.setCacheable(query.getInt(columnIndexOrThrow38));
                testOfflineEntity22222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                testOfflineEntity22222222.setBConfig(query.getString(columnIndexOrThrow40));
                testOfflineEntity22222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                testOfflineEntity22222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                testOfflineEntity22222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                testOfflineEntity22222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                testOfflineEntity22222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                testOfflineEntity22222222.setLocalPriorityInfo(query.getString(i2));
                testOfflineEntity22222222.setFileInfo(offlineEntityInfo);
                testOfflineEntity22222222.setDocumentFile(fileDetail);
                testOfflineEntity22222222.setSourceFile(fileDetail2);
                testOfflineEntity22222222.setZipFile(fileDetail3);
                testOfflineEntity = testOfflineEntity22222222;
            } else {
                testOfflineEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return testOfflineEntity;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public void save(List<TestOfflineEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public void update(TestOfflineEntity testOfflineEntity) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.d.handle(testOfflineEntity);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public void delete(List<TestOfflineEntity> list) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.f5967c.handleMultiple(list);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public void save(TestOfflineEntity... testOfflineEntityArr) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(testOfflineEntityArr);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    public void update(List<TestOfflineEntity> list) {
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
    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TestOfflineEntity getByUrl(String str, int i2) {
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
        int columnIndexOrThrow14;
        TestOfflineEntity testOfflineEntity;
        int i3;
        OfflineEntityInfo offlineEntityInfo;
        FileDetail fileDetail;
        int i4;
        int i5;
        FileDetail fileDetail2;
        int i6;
        int i7;
        int i8;
        FileDetail fileDetail3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `file_url`, `file_version`, `file_versionCode`, `file_md5`, `file_fileType`, `file_password`, `file_patchTotal`, `file_fileUrlZip`, `file_fileZipMd5`, `file_useZip`, `document_path`, `document_lastModified`, `document_totalSpace`, `source_path`, `source_lastModified`, `source_totalSpace`, `zip_path`, `zip_lastModified`, `zip_totalSpace`, `HybridTestOfflineEntity`.`appid` AS `appid`, `HybridTestOfflineEntity`.`moduleCode` AS `moduleCode`, `HybridTestOfflineEntity`.`type` AS `type`, `HybridTestOfflineEntity`.`name` AS `name`, `HybridTestOfflineEntity`.`documentUrl` AS `documentUrl`, `HybridTestOfflineEntity`.`originalUrl` AS `originalUrl`, `HybridTestOfflineEntity`.`originalUrlType` AS `originalUrlType`, `HybridTestOfflineEntity`.`patch_total` AS `patch_total`, `HybridTestOfflineEntity`.`documentDir` AS `documentDir`, `HybridTestOfflineEntity`.`sourceRoot` AS `sourceRoot`, `HybridTestOfflineEntity`.`sourceDir` AS `sourceDir`, `HybridTestOfflineEntity`.`appMin` AS `appMin`, `HybridTestOfflineEntity`.`appMax` AS `appMax`, `HybridTestOfflineEntity`.`serverPriority` AS `serverPriority`, `HybridTestOfflineEntity`.`checkType` AS `checkType`, `HybridTestOfflineEntity`.`fileRootPath` AS `fileRootPath`, `HybridTestOfflineEntity`.`htmlPreload` AS `htmlPreload`, `HybridTestOfflineEntity`.`htmlStatic` AS `htmlStatic`, `HybridTestOfflineEntity`.`cacheable` AS `cacheable`, `HybridTestOfflineEntity`.`ungentoken` AS `ungentoken`, `HybridTestOfflineEntity`.`bConfig` AS `bConfig`, `HybridTestOfflineEntity`.`degradeType` AS `degradeType`, `HybridTestOfflineEntity`.`minFileVer` AS `minFileVer`, `HybridTestOfflineEntity`.`available` AS `available`, `HybridTestOfflineEntity`.`createTimestamp` AS `createTimestamp`, `HybridTestOfflineEntity`.`lastVisitTimestamp` AS `lastVisitTimestamp`, `HybridTestOfflineEntity`.`localPriorityInfo` AS `localPriorityInfo` FROM HybridTestOfflineEntity WHERE (documentUrl=? OR (originalUrl=? AND originalUrlType='1')) AND file_versionCode= ? LIMIT 1", 3);
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
        acquire.bindLong(3, i2);
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "file_url");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_version");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "file_versionCode");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_md5");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_fileType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "file_password");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "file_patchTotal");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "file_fileUrlZip");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "file_fileZipMd5");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "file_useZip");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "document_path");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "document_lastModified");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "document_totalSpace");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "source_path");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "source_lastModified");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "source_totalSpace");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "zip_path");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "zip_lastModified");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "zip_totalSpace");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "appid");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, JDReactConstant.ModuleCode);
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "documentUrl");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "patch_total");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "documentDir");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "sourceRoot");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "sourceDir");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "serverPriority");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "checkType");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "fileRootPath");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "htmlPreload");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "htmlStatic");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "cacheable");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "ungentoken");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "degradeType");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "minFileVer");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "lastVisitTimestamp");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "localPriorityInfo");
            if (query.moveToFirst()) {
                if (query.isNull(columnIndexOrThrow) && query.isNull(columnIndexOrThrow2) && query.isNull(columnIndexOrThrow3) && query.isNull(columnIndexOrThrow4) && query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                    i3 = columnIndexOrThrow46;
                    offlineEntityInfo = null;
                    if (query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13)) {
                        fileDetail = null;
                        if (query.isNull(columnIndexOrThrow14)) {
                            i4 = columnIndexOrThrow15;
                        } else {
                            i4 = columnIndexOrThrow15;
                            if (query.isNull(i4)) {
                                i5 = columnIndexOrThrow16;
                                if (!query.isNull(i5)) {
                                    fileDetail2 = new FileDetail();
                                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                                    fileDetail2.setLastModified(query.getLong(i4));
                                    fileDetail2.setTotalSpace(query.getLong(i5));
                                    i6 = columnIndexOrThrow17;
                                    if (query.isNull(i6)) {
                                        i7 = columnIndexOrThrow18;
                                    } else {
                                        i7 = columnIndexOrThrow18;
                                        if (query.isNull(i7)) {
                                            i8 = columnIndexOrThrow19;
                                            if (!query.isNull(i8)) {
                                                fileDetail3 = new FileDetail();
                                                fileDetail3.setPath(query.getString(i6));
                                                fileDetail3.setLastModified(query.getLong(i7));
                                                fileDetail3.setTotalSpace(query.getLong(i8));
                                                TestOfflineEntity testOfflineEntity2 = new TestOfflineEntity();
                                                testOfflineEntity2.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity2.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity2.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity2.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity2.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity2.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity2.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity2.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity2.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity2.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity2.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity2.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity2.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity2.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity2.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity2.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity2.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity2.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity2.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity2.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity2.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity2.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity2.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity2.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity2.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity2.setLocalPriorityInfo(query.getString(i3));
                                                testOfflineEntity2.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity2.setDocumentFile(fileDetail);
                                                testOfflineEntity2.setSourceFile(fileDetail2);
                                                testOfflineEntity2.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity2;
                                            } else {
                                                fileDetail3 = null;
                                                TestOfflineEntity testOfflineEntity22 = new TestOfflineEntity();
                                                testOfflineEntity22.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity22.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity22.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity22.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity22.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity22.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity22.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity22.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity22.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity22.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity22.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity22.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity22.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity22.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity22.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity22.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity22.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity22.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity22.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity22.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity22.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity22.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity22.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity22.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity22.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity22.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity22.setLocalPriorityInfo(query.getString(i3));
                                                testOfflineEntity22.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity22.setDocumentFile(fileDetail);
                                                testOfflineEntity22.setSourceFile(fileDetail2);
                                                testOfflineEntity22.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity22;
                                            }
                                        }
                                    }
                                    i8 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i6));
                                    fileDetail3.setLastModified(query.getLong(i7));
                                    fileDetail3.setTotalSpace(query.getLong(i8));
                                    TestOfflineEntity testOfflineEntity222 = new TestOfflineEntity();
                                    testOfflineEntity222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity222.setLocalPriorityInfo(query.getString(i3));
                                    testOfflineEntity222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity222.setDocumentFile(fileDetail);
                                    testOfflineEntity222.setSourceFile(fileDetail2);
                                    testOfflineEntity222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity222;
                                } else {
                                    i6 = columnIndexOrThrow17;
                                    fileDetail2 = null;
                                    if (query.isNull(i6)) {
                                    }
                                    i8 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i6));
                                    fileDetail3.setLastModified(query.getLong(i7));
                                    fileDetail3.setTotalSpace(query.getLong(i8));
                                    TestOfflineEntity testOfflineEntity2222 = new TestOfflineEntity();
                                    testOfflineEntity2222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity2222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity2222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity2222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity2222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity2222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity2222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity2222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity2222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity2222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity2222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity2222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity2222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity2222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity2222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity2222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity2222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity2222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity2222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity2222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity2222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity2222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity2222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity2222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity2222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity2222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity2222.setLocalPriorityInfo(query.getString(i3));
                                    testOfflineEntity2222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity2222.setDocumentFile(fileDetail);
                                    testOfflineEntity2222.setSourceFile(fileDetail2);
                                    testOfflineEntity2222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity2222;
                                }
                            }
                        }
                        i5 = columnIndexOrThrow16;
                        fileDetail2 = new FileDetail();
                        fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                        fileDetail2.setLastModified(query.getLong(i4));
                        fileDetail2.setTotalSpace(query.getLong(i5));
                        i6 = columnIndexOrThrow17;
                        if (query.isNull(i6)) {
                        }
                        i8 = columnIndexOrThrow19;
                        fileDetail3 = new FileDetail();
                        fileDetail3.setPath(query.getString(i6));
                        fileDetail3.setLastModified(query.getLong(i7));
                        fileDetail3.setTotalSpace(query.getLong(i8));
                        TestOfflineEntity testOfflineEntity22222 = new TestOfflineEntity();
                        testOfflineEntity22222.setAppid(query.getString(columnIndexOrThrow20));
                        testOfflineEntity22222.setModuleCode(query.getInt(columnIndexOrThrow21));
                        testOfflineEntity22222.setType(query.getString(columnIndexOrThrow22));
                        testOfflineEntity22222.setName(query.getString(columnIndexOrThrow23));
                        testOfflineEntity22222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                        testOfflineEntity22222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                        testOfflineEntity22222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                        testOfflineEntity22222.setPatch_total(query.getString(columnIndexOrThrow27));
                        testOfflineEntity22222.setDocumentDir(query.getString(columnIndexOrThrow28));
                        testOfflineEntity22222.setSourceRoot(query.getString(columnIndexOrThrow29));
                        testOfflineEntity22222.setSourceDir(query.getString(columnIndexOrThrow30));
                        testOfflineEntity22222.setAppMin(query.getString(columnIndexOrThrow31));
                        testOfflineEntity22222.setAppMax(query.getString(columnIndexOrThrow32));
                        testOfflineEntity22222.setServerPriority(query.getInt(columnIndexOrThrow33));
                        testOfflineEntity22222.setCheckType(query.getString(columnIndexOrThrow34));
                        testOfflineEntity22222.setFileRootPath(query.getString(columnIndexOrThrow35));
                        testOfflineEntity22222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                        testOfflineEntity22222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                        testOfflineEntity22222.setCacheable(query.getInt(columnIndexOrThrow38));
                        testOfflineEntity22222.setUngentoken(query.getInt(columnIndexOrThrow39));
                        testOfflineEntity22222.setBConfig(query.getString(columnIndexOrThrow40));
                        testOfflineEntity22222.setDegradeType(query.getInt(columnIndexOrThrow41));
                        testOfflineEntity22222.setMinFileVer(query.getString(columnIndexOrThrow42));
                        testOfflineEntity22222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                        testOfflineEntity22222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                        testOfflineEntity22222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                        testOfflineEntity22222.setLocalPriorityInfo(query.getString(i3));
                        testOfflineEntity22222.setFileInfo(offlineEntityInfo);
                        testOfflineEntity22222.setDocumentFile(fileDetail);
                        testOfflineEntity22222.setSourceFile(fileDetail2);
                        testOfflineEntity22222.setZipFile(fileDetail3);
                        testOfflineEntity = testOfflineEntity22222;
                    }
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow11));
                    fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                    fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i5 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i4));
                    fileDetail2.setTotalSpace(query.getLong(i5));
                    i6 = columnIndexOrThrow17;
                    if (query.isNull(i6)) {
                    }
                    i8 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i6));
                    fileDetail3.setLastModified(query.getLong(i7));
                    fileDetail3.setTotalSpace(query.getLong(i8));
                    TestOfflineEntity testOfflineEntity222222 = new TestOfflineEntity();
                    testOfflineEntity222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity222222.setLocalPriorityInfo(query.getString(i3));
                    testOfflineEntity222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity222222.setDocumentFile(fileDetail);
                    testOfflineEntity222222.setSourceFile(fileDetail2);
                    testOfflineEntity222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity222222;
                }
                i3 = columnIndexOrThrow46;
                offlineEntityInfo = new OfflineEntityInfo();
                offlineEntityInfo.setUrl(query.getString(columnIndexOrThrow));
                offlineEntityInfo.setVersion(query.getString(columnIndexOrThrow2));
                offlineEntityInfo.setVersionCode(query.getInt(columnIndexOrThrow3));
                offlineEntityInfo.setMd5(query.getString(columnIndexOrThrow4));
                offlineEntityInfo.setFileType(query.getString(columnIndexOrThrow5));
                offlineEntityInfo.setPassword(query.getString(columnIndexOrThrow6));
                offlineEntityInfo.setPatchTotal(query.getInt(columnIndexOrThrow7));
                offlineEntityInfo.setFileUrlZip(query.getString(columnIndexOrThrow8));
                offlineEntityInfo.setFileZipMd5(query.getString(columnIndexOrThrow9));
                offlineEntityInfo.setUseZip(query.getInt(columnIndexOrThrow10) != 0);
                if (query.isNull(columnIndexOrThrow11)) {
                    fileDetail = null;
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i5 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i4));
                    fileDetail2.setTotalSpace(query.getLong(i5));
                    i6 = columnIndexOrThrow17;
                    if (query.isNull(i6)) {
                    }
                    i8 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i6));
                    fileDetail3.setLastModified(query.getLong(i7));
                    fileDetail3.setTotalSpace(query.getLong(i8));
                    TestOfflineEntity testOfflineEntity2222222 = new TestOfflineEntity();
                    testOfflineEntity2222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity2222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity2222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity2222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity2222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity2222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity2222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity2222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity2222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity2222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity2222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity2222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity2222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity2222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity2222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity2222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity2222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity2222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity2222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity2222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity2222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity2222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity2222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity2222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity2222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity2222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity2222222.setLocalPriorityInfo(query.getString(i3));
                    testOfflineEntity2222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity2222222.setDocumentFile(fileDetail);
                    testOfflineEntity2222222.setSourceFile(fileDetail2);
                    testOfflineEntity2222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity2222222;
                }
                fileDetail = new FileDetail();
                fileDetail.setPath(query.getString(columnIndexOrThrow11));
                fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                if (query.isNull(columnIndexOrThrow14)) {
                }
                i5 = columnIndexOrThrow16;
                fileDetail2 = new FileDetail();
                fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                fileDetail2.setLastModified(query.getLong(i4));
                fileDetail2.setTotalSpace(query.getLong(i5));
                i6 = columnIndexOrThrow17;
                if (query.isNull(i6)) {
                }
                i8 = columnIndexOrThrow19;
                fileDetail3 = new FileDetail();
                fileDetail3.setPath(query.getString(i6));
                fileDetail3.setLastModified(query.getLong(i7));
                fileDetail3.setTotalSpace(query.getLong(i8));
                TestOfflineEntity testOfflineEntity22222222 = new TestOfflineEntity();
                testOfflineEntity22222222.setAppid(query.getString(columnIndexOrThrow20));
                testOfflineEntity22222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                testOfflineEntity22222222.setType(query.getString(columnIndexOrThrow22));
                testOfflineEntity22222222.setName(query.getString(columnIndexOrThrow23));
                testOfflineEntity22222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                testOfflineEntity22222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                testOfflineEntity22222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                testOfflineEntity22222222.setPatch_total(query.getString(columnIndexOrThrow27));
                testOfflineEntity22222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                testOfflineEntity22222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                testOfflineEntity22222222.setSourceDir(query.getString(columnIndexOrThrow30));
                testOfflineEntity22222222.setAppMin(query.getString(columnIndexOrThrow31));
                testOfflineEntity22222222.setAppMax(query.getString(columnIndexOrThrow32));
                testOfflineEntity22222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                testOfflineEntity22222222.setCheckType(query.getString(columnIndexOrThrow34));
                testOfflineEntity22222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                testOfflineEntity22222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                testOfflineEntity22222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                testOfflineEntity22222222.setCacheable(query.getInt(columnIndexOrThrow38));
                testOfflineEntity22222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                testOfflineEntity22222222.setBConfig(query.getString(columnIndexOrThrow40));
                testOfflineEntity22222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                testOfflineEntity22222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                testOfflineEntity22222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                testOfflineEntity22222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                testOfflineEntity22222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                testOfflineEntity22222222.setLocalPriorityInfo(query.getString(i3));
                testOfflineEntity22222222.setFileInfo(offlineEntityInfo);
                testOfflineEntity22222222.setDocumentFile(fileDetail);
                testOfflineEntity22222222.setSourceFile(fileDetail2);
                testOfflineEntity22222222.setZipFile(fileDetail3);
                testOfflineEntity = testOfflineEntity22222222;
            } else {
                testOfflineEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return testOfflineEntity;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0254 A[Catch: all -> 0x03d3, TryCatch #0 {all -> 0x03d3, blocks: (B:13:0x0088, B:15:0x018c, B:17:0x0192, B:19:0x0198, B:21:0x019e, B:23:0x01a4, B:25:0x01aa, B:27:0x01b0, B:29:0x01b6, B:31:0x01bc, B:33:0x01c2, B:42:0x021f, B:44:0x0225, B:46:0x022b, B:51:0x024e, B:53:0x0254, B:55:0x025c, B:61:0x026d, B:62:0x0289, B:64:0x028f, B:66:0x0297, B:72:0x02a6, B:73:0x02c0, B:77:0x039f, B:50:0x0234, B:37:0x01cd, B:41:0x021c), top: B:88:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x028f A[Catch: all -> 0x03d3, TryCatch #0 {all -> 0x03d3, blocks: (B:13:0x0088, B:15:0x018c, B:17:0x0192, B:19:0x0198, B:21:0x019e, B:23:0x01a4, B:25:0x01aa, B:27:0x01b0, B:29:0x01b6, B:31:0x01bc, B:33:0x01c2, B:42:0x021f, B:44:0x0225, B:46:0x022b, B:51:0x024e, B:53:0x0254, B:55:0x025c, B:61:0x026d, B:62:0x0289, B:64:0x028f, B:66:0x0297, B:72:0x02a6, B:73:0x02c0, B:77:0x039f, B:50:0x0234, B:37:0x01cd, B:41:0x021c), top: B:88:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x039e  */
    @Override // com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public TestOfflineEntity getOneAvailableByUrl(String str, int i2) {
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
        int columnIndexOrThrow14;
        TestOfflineEntity testOfflineEntity;
        int i3;
        OfflineEntityInfo offlineEntityInfo;
        FileDetail fileDetail;
        int i4;
        int i5;
        FileDetail fileDetail2;
        int i6;
        int i7;
        int i8;
        FileDetail fileDetail3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `file_url`, `file_version`, `file_versionCode`, `file_md5`, `file_fileType`, `file_password`, `file_patchTotal`, `file_fileUrlZip`, `file_fileZipMd5`, `file_useZip`, `document_path`, `document_lastModified`, `document_totalSpace`, `source_path`, `source_lastModified`, `source_totalSpace`, `zip_path`, `zip_lastModified`, `zip_totalSpace`, `HybridTestOfflineEntity`.`appid` AS `appid`, `HybridTestOfflineEntity`.`moduleCode` AS `moduleCode`, `HybridTestOfflineEntity`.`type` AS `type`, `HybridTestOfflineEntity`.`name` AS `name`, `HybridTestOfflineEntity`.`documentUrl` AS `documentUrl`, `HybridTestOfflineEntity`.`originalUrl` AS `originalUrl`, `HybridTestOfflineEntity`.`originalUrlType` AS `originalUrlType`, `HybridTestOfflineEntity`.`patch_total` AS `patch_total`, `HybridTestOfflineEntity`.`documentDir` AS `documentDir`, `HybridTestOfflineEntity`.`sourceRoot` AS `sourceRoot`, `HybridTestOfflineEntity`.`sourceDir` AS `sourceDir`, `HybridTestOfflineEntity`.`appMin` AS `appMin`, `HybridTestOfflineEntity`.`appMax` AS `appMax`, `HybridTestOfflineEntity`.`serverPriority` AS `serverPriority`, `HybridTestOfflineEntity`.`checkType` AS `checkType`, `HybridTestOfflineEntity`.`fileRootPath` AS `fileRootPath`, `HybridTestOfflineEntity`.`htmlPreload` AS `htmlPreload`, `HybridTestOfflineEntity`.`htmlStatic` AS `htmlStatic`, `HybridTestOfflineEntity`.`cacheable` AS `cacheable`, `HybridTestOfflineEntity`.`ungentoken` AS `ungentoken`, `HybridTestOfflineEntity`.`bConfig` AS `bConfig`, `HybridTestOfflineEntity`.`degradeType` AS `degradeType`, `HybridTestOfflineEntity`.`minFileVer` AS `minFileVer`, `HybridTestOfflineEntity`.`available` AS `available`, `HybridTestOfflineEntity`.`createTimestamp` AS `createTimestamp`, `HybridTestOfflineEntity`.`lastVisitTimestamp` AS `lastVisitTimestamp`, `HybridTestOfflineEntity`.`localPriorityInfo` AS `localPriorityInfo` FROM HybridTestOfflineEntity WHERE (documentUrl=? OR originalUrl=?) AND file_versionCode= ? AND available = 1 LIMIT 1", 3);
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
        acquire.bindLong(3, i2);
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "file_url");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "file_version");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "file_versionCode");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_md5");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_fileType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "file_password");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "file_patchTotal");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "file_fileUrlZip");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "file_fileZipMd5");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "file_useZip");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "document_path");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "document_lastModified");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "document_totalSpace");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "source_path");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "source_lastModified");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "source_totalSpace");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "zip_path");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "zip_lastModified");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "zip_totalSpace");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "appid");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, JDReactConstant.ModuleCode);
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "documentUrl");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "originalUrl");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "originalUrlType");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "patch_total");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "documentDir");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "sourceRoot");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "sourceDir");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "appMin");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "appMax");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "serverPriority");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "checkType");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "fileRootPath");
            int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "htmlPreload");
            int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "htmlStatic");
            int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "cacheable");
            int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "ungentoken");
            int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, "bConfig");
            int columnIndexOrThrow41 = CursorUtil.getColumnIndexOrThrow(query, "degradeType");
            int columnIndexOrThrow42 = CursorUtil.getColumnIndexOrThrow(query, "minFileVer");
            int columnIndexOrThrow43 = CursorUtil.getColumnIndexOrThrow(query, Constant.KEY_PROMOTION_AVAILABLE);
            int columnIndexOrThrow44 = CursorUtil.getColumnIndexOrThrow(query, "createTimestamp");
            int columnIndexOrThrow45 = CursorUtil.getColumnIndexOrThrow(query, "lastVisitTimestamp");
            int columnIndexOrThrow46 = CursorUtil.getColumnIndexOrThrow(query, "localPriorityInfo");
            if (query.moveToFirst()) {
                if (query.isNull(columnIndexOrThrow) && query.isNull(columnIndexOrThrow2) && query.isNull(columnIndexOrThrow3) && query.isNull(columnIndexOrThrow4) && query.isNull(columnIndexOrThrow5) && query.isNull(columnIndexOrThrow6) && query.isNull(columnIndexOrThrow7) && query.isNull(columnIndexOrThrow8) && query.isNull(columnIndexOrThrow9) && query.isNull(columnIndexOrThrow10)) {
                    i3 = columnIndexOrThrow46;
                    offlineEntityInfo = null;
                    if (query.isNull(columnIndexOrThrow11) && query.isNull(columnIndexOrThrow12) && query.isNull(columnIndexOrThrow13)) {
                        fileDetail = null;
                        if (query.isNull(columnIndexOrThrow14)) {
                            i4 = columnIndexOrThrow15;
                        } else {
                            i4 = columnIndexOrThrow15;
                            if (query.isNull(i4)) {
                                i5 = columnIndexOrThrow16;
                                if (!query.isNull(i5)) {
                                    fileDetail2 = new FileDetail();
                                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                                    fileDetail2.setLastModified(query.getLong(i4));
                                    fileDetail2.setTotalSpace(query.getLong(i5));
                                    i6 = columnIndexOrThrow17;
                                    if (query.isNull(i6)) {
                                        i7 = columnIndexOrThrow18;
                                    } else {
                                        i7 = columnIndexOrThrow18;
                                        if (query.isNull(i7)) {
                                            i8 = columnIndexOrThrow19;
                                            if (!query.isNull(i8)) {
                                                fileDetail3 = new FileDetail();
                                                fileDetail3.setPath(query.getString(i6));
                                                fileDetail3.setLastModified(query.getLong(i7));
                                                fileDetail3.setTotalSpace(query.getLong(i8));
                                                TestOfflineEntity testOfflineEntity2 = new TestOfflineEntity();
                                                testOfflineEntity2.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity2.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity2.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity2.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity2.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity2.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity2.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity2.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity2.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity2.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity2.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity2.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity2.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity2.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity2.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity2.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity2.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity2.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity2.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity2.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity2.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity2.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity2.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity2.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity2.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity2.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity2.setLocalPriorityInfo(query.getString(i3));
                                                testOfflineEntity2.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity2.setDocumentFile(fileDetail);
                                                testOfflineEntity2.setSourceFile(fileDetail2);
                                                testOfflineEntity2.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity2;
                                            } else {
                                                fileDetail3 = null;
                                                TestOfflineEntity testOfflineEntity22 = new TestOfflineEntity();
                                                testOfflineEntity22.setAppid(query.getString(columnIndexOrThrow20));
                                                testOfflineEntity22.setModuleCode(query.getInt(columnIndexOrThrow21));
                                                testOfflineEntity22.setType(query.getString(columnIndexOrThrow22));
                                                testOfflineEntity22.setName(query.getString(columnIndexOrThrow23));
                                                testOfflineEntity22.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                                testOfflineEntity22.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                                testOfflineEntity22.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                                testOfflineEntity22.setPatch_total(query.getString(columnIndexOrThrow27));
                                                testOfflineEntity22.setDocumentDir(query.getString(columnIndexOrThrow28));
                                                testOfflineEntity22.setSourceRoot(query.getString(columnIndexOrThrow29));
                                                testOfflineEntity22.setSourceDir(query.getString(columnIndexOrThrow30));
                                                testOfflineEntity22.setAppMin(query.getString(columnIndexOrThrow31));
                                                testOfflineEntity22.setAppMax(query.getString(columnIndexOrThrow32));
                                                testOfflineEntity22.setServerPriority(query.getInt(columnIndexOrThrow33));
                                                testOfflineEntity22.setCheckType(query.getString(columnIndexOrThrow34));
                                                testOfflineEntity22.setFileRootPath(query.getString(columnIndexOrThrow35));
                                                testOfflineEntity22.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                                testOfflineEntity22.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                                testOfflineEntity22.setCacheable(query.getInt(columnIndexOrThrow38));
                                                testOfflineEntity22.setUngentoken(query.getInt(columnIndexOrThrow39));
                                                testOfflineEntity22.setBConfig(query.getString(columnIndexOrThrow40));
                                                testOfflineEntity22.setDegradeType(query.getInt(columnIndexOrThrow41));
                                                testOfflineEntity22.setMinFileVer(query.getString(columnIndexOrThrow42));
                                                testOfflineEntity22.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                                testOfflineEntity22.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                                testOfflineEntity22.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                                testOfflineEntity22.setLocalPriorityInfo(query.getString(i3));
                                                testOfflineEntity22.setFileInfo(offlineEntityInfo);
                                                testOfflineEntity22.setDocumentFile(fileDetail);
                                                testOfflineEntity22.setSourceFile(fileDetail2);
                                                testOfflineEntity22.setZipFile(fileDetail3);
                                                testOfflineEntity = testOfflineEntity22;
                                            }
                                        }
                                    }
                                    i8 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i6));
                                    fileDetail3.setLastModified(query.getLong(i7));
                                    fileDetail3.setTotalSpace(query.getLong(i8));
                                    TestOfflineEntity testOfflineEntity222 = new TestOfflineEntity();
                                    testOfflineEntity222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity222.setLocalPriorityInfo(query.getString(i3));
                                    testOfflineEntity222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity222.setDocumentFile(fileDetail);
                                    testOfflineEntity222.setSourceFile(fileDetail2);
                                    testOfflineEntity222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity222;
                                } else {
                                    i6 = columnIndexOrThrow17;
                                    fileDetail2 = null;
                                    if (query.isNull(i6)) {
                                    }
                                    i8 = columnIndexOrThrow19;
                                    fileDetail3 = new FileDetail();
                                    fileDetail3.setPath(query.getString(i6));
                                    fileDetail3.setLastModified(query.getLong(i7));
                                    fileDetail3.setTotalSpace(query.getLong(i8));
                                    TestOfflineEntity testOfflineEntity2222 = new TestOfflineEntity();
                                    testOfflineEntity2222.setAppid(query.getString(columnIndexOrThrow20));
                                    testOfflineEntity2222.setModuleCode(query.getInt(columnIndexOrThrow21));
                                    testOfflineEntity2222.setType(query.getString(columnIndexOrThrow22));
                                    testOfflineEntity2222.setName(query.getString(columnIndexOrThrow23));
                                    testOfflineEntity2222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                                    testOfflineEntity2222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                                    testOfflineEntity2222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                                    testOfflineEntity2222.setPatch_total(query.getString(columnIndexOrThrow27));
                                    testOfflineEntity2222.setDocumentDir(query.getString(columnIndexOrThrow28));
                                    testOfflineEntity2222.setSourceRoot(query.getString(columnIndexOrThrow29));
                                    testOfflineEntity2222.setSourceDir(query.getString(columnIndexOrThrow30));
                                    testOfflineEntity2222.setAppMin(query.getString(columnIndexOrThrow31));
                                    testOfflineEntity2222.setAppMax(query.getString(columnIndexOrThrow32));
                                    testOfflineEntity2222.setServerPriority(query.getInt(columnIndexOrThrow33));
                                    testOfflineEntity2222.setCheckType(query.getString(columnIndexOrThrow34));
                                    testOfflineEntity2222.setFileRootPath(query.getString(columnIndexOrThrow35));
                                    testOfflineEntity2222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                                    testOfflineEntity2222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                                    testOfflineEntity2222.setCacheable(query.getInt(columnIndexOrThrow38));
                                    testOfflineEntity2222.setUngentoken(query.getInt(columnIndexOrThrow39));
                                    testOfflineEntity2222.setBConfig(query.getString(columnIndexOrThrow40));
                                    testOfflineEntity2222.setDegradeType(query.getInt(columnIndexOrThrow41));
                                    testOfflineEntity2222.setMinFileVer(query.getString(columnIndexOrThrow42));
                                    testOfflineEntity2222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                                    testOfflineEntity2222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                                    testOfflineEntity2222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                                    testOfflineEntity2222.setLocalPriorityInfo(query.getString(i3));
                                    testOfflineEntity2222.setFileInfo(offlineEntityInfo);
                                    testOfflineEntity2222.setDocumentFile(fileDetail);
                                    testOfflineEntity2222.setSourceFile(fileDetail2);
                                    testOfflineEntity2222.setZipFile(fileDetail3);
                                    testOfflineEntity = testOfflineEntity2222;
                                }
                            }
                        }
                        i5 = columnIndexOrThrow16;
                        fileDetail2 = new FileDetail();
                        fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                        fileDetail2.setLastModified(query.getLong(i4));
                        fileDetail2.setTotalSpace(query.getLong(i5));
                        i6 = columnIndexOrThrow17;
                        if (query.isNull(i6)) {
                        }
                        i8 = columnIndexOrThrow19;
                        fileDetail3 = new FileDetail();
                        fileDetail3.setPath(query.getString(i6));
                        fileDetail3.setLastModified(query.getLong(i7));
                        fileDetail3.setTotalSpace(query.getLong(i8));
                        TestOfflineEntity testOfflineEntity22222 = new TestOfflineEntity();
                        testOfflineEntity22222.setAppid(query.getString(columnIndexOrThrow20));
                        testOfflineEntity22222.setModuleCode(query.getInt(columnIndexOrThrow21));
                        testOfflineEntity22222.setType(query.getString(columnIndexOrThrow22));
                        testOfflineEntity22222.setName(query.getString(columnIndexOrThrow23));
                        testOfflineEntity22222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                        testOfflineEntity22222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                        testOfflineEntity22222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                        testOfflineEntity22222.setPatch_total(query.getString(columnIndexOrThrow27));
                        testOfflineEntity22222.setDocumentDir(query.getString(columnIndexOrThrow28));
                        testOfflineEntity22222.setSourceRoot(query.getString(columnIndexOrThrow29));
                        testOfflineEntity22222.setSourceDir(query.getString(columnIndexOrThrow30));
                        testOfflineEntity22222.setAppMin(query.getString(columnIndexOrThrow31));
                        testOfflineEntity22222.setAppMax(query.getString(columnIndexOrThrow32));
                        testOfflineEntity22222.setServerPriority(query.getInt(columnIndexOrThrow33));
                        testOfflineEntity22222.setCheckType(query.getString(columnIndexOrThrow34));
                        testOfflineEntity22222.setFileRootPath(query.getString(columnIndexOrThrow35));
                        testOfflineEntity22222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                        testOfflineEntity22222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                        testOfflineEntity22222.setCacheable(query.getInt(columnIndexOrThrow38));
                        testOfflineEntity22222.setUngentoken(query.getInt(columnIndexOrThrow39));
                        testOfflineEntity22222.setBConfig(query.getString(columnIndexOrThrow40));
                        testOfflineEntity22222.setDegradeType(query.getInt(columnIndexOrThrow41));
                        testOfflineEntity22222.setMinFileVer(query.getString(columnIndexOrThrow42));
                        testOfflineEntity22222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                        testOfflineEntity22222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                        testOfflineEntity22222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                        testOfflineEntity22222.setLocalPriorityInfo(query.getString(i3));
                        testOfflineEntity22222.setFileInfo(offlineEntityInfo);
                        testOfflineEntity22222.setDocumentFile(fileDetail);
                        testOfflineEntity22222.setSourceFile(fileDetail2);
                        testOfflineEntity22222.setZipFile(fileDetail3);
                        testOfflineEntity = testOfflineEntity22222;
                    }
                    fileDetail = new FileDetail();
                    fileDetail.setPath(query.getString(columnIndexOrThrow11));
                    fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                    fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i5 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i4));
                    fileDetail2.setTotalSpace(query.getLong(i5));
                    i6 = columnIndexOrThrow17;
                    if (query.isNull(i6)) {
                    }
                    i8 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i6));
                    fileDetail3.setLastModified(query.getLong(i7));
                    fileDetail3.setTotalSpace(query.getLong(i8));
                    TestOfflineEntity testOfflineEntity222222 = new TestOfflineEntity();
                    testOfflineEntity222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity222222.setLocalPriorityInfo(query.getString(i3));
                    testOfflineEntity222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity222222.setDocumentFile(fileDetail);
                    testOfflineEntity222222.setSourceFile(fileDetail2);
                    testOfflineEntity222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity222222;
                }
                i3 = columnIndexOrThrow46;
                offlineEntityInfo = new OfflineEntityInfo();
                offlineEntityInfo.setUrl(query.getString(columnIndexOrThrow));
                offlineEntityInfo.setVersion(query.getString(columnIndexOrThrow2));
                offlineEntityInfo.setVersionCode(query.getInt(columnIndexOrThrow3));
                offlineEntityInfo.setMd5(query.getString(columnIndexOrThrow4));
                offlineEntityInfo.setFileType(query.getString(columnIndexOrThrow5));
                offlineEntityInfo.setPassword(query.getString(columnIndexOrThrow6));
                offlineEntityInfo.setPatchTotal(query.getInt(columnIndexOrThrow7));
                offlineEntityInfo.setFileUrlZip(query.getString(columnIndexOrThrow8));
                offlineEntityInfo.setFileZipMd5(query.getString(columnIndexOrThrow9));
                offlineEntityInfo.setUseZip(query.getInt(columnIndexOrThrow10) != 0);
                if (query.isNull(columnIndexOrThrow11)) {
                    fileDetail = null;
                    if (query.isNull(columnIndexOrThrow14)) {
                    }
                    i5 = columnIndexOrThrow16;
                    fileDetail2 = new FileDetail();
                    fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                    fileDetail2.setLastModified(query.getLong(i4));
                    fileDetail2.setTotalSpace(query.getLong(i5));
                    i6 = columnIndexOrThrow17;
                    if (query.isNull(i6)) {
                    }
                    i8 = columnIndexOrThrow19;
                    fileDetail3 = new FileDetail();
                    fileDetail3.setPath(query.getString(i6));
                    fileDetail3.setLastModified(query.getLong(i7));
                    fileDetail3.setTotalSpace(query.getLong(i8));
                    TestOfflineEntity testOfflineEntity2222222 = new TestOfflineEntity();
                    testOfflineEntity2222222.setAppid(query.getString(columnIndexOrThrow20));
                    testOfflineEntity2222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                    testOfflineEntity2222222.setType(query.getString(columnIndexOrThrow22));
                    testOfflineEntity2222222.setName(query.getString(columnIndexOrThrow23));
                    testOfflineEntity2222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                    testOfflineEntity2222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                    testOfflineEntity2222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                    testOfflineEntity2222222.setPatch_total(query.getString(columnIndexOrThrow27));
                    testOfflineEntity2222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                    testOfflineEntity2222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                    testOfflineEntity2222222.setSourceDir(query.getString(columnIndexOrThrow30));
                    testOfflineEntity2222222.setAppMin(query.getString(columnIndexOrThrow31));
                    testOfflineEntity2222222.setAppMax(query.getString(columnIndexOrThrow32));
                    testOfflineEntity2222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                    testOfflineEntity2222222.setCheckType(query.getString(columnIndexOrThrow34));
                    testOfflineEntity2222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                    testOfflineEntity2222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                    testOfflineEntity2222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                    testOfflineEntity2222222.setCacheable(query.getInt(columnIndexOrThrow38));
                    testOfflineEntity2222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                    testOfflineEntity2222222.setBConfig(query.getString(columnIndexOrThrow40));
                    testOfflineEntity2222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                    testOfflineEntity2222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                    testOfflineEntity2222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                    testOfflineEntity2222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                    testOfflineEntity2222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                    testOfflineEntity2222222.setLocalPriorityInfo(query.getString(i3));
                    testOfflineEntity2222222.setFileInfo(offlineEntityInfo);
                    testOfflineEntity2222222.setDocumentFile(fileDetail);
                    testOfflineEntity2222222.setSourceFile(fileDetail2);
                    testOfflineEntity2222222.setZipFile(fileDetail3);
                    testOfflineEntity = testOfflineEntity2222222;
                }
                fileDetail = new FileDetail();
                fileDetail.setPath(query.getString(columnIndexOrThrow11));
                fileDetail.setLastModified(query.getLong(columnIndexOrThrow12));
                fileDetail.setTotalSpace(query.getLong(columnIndexOrThrow13));
                if (query.isNull(columnIndexOrThrow14)) {
                }
                i5 = columnIndexOrThrow16;
                fileDetail2 = new FileDetail();
                fileDetail2.setPath(query.getString(columnIndexOrThrow14));
                fileDetail2.setLastModified(query.getLong(i4));
                fileDetail2.setTotalSpace(query.getLong(i5));
                i6 = columnIndexOrThrow17;
                if (query.isNull(i6)) {
                }
                i8 = columnIndexOrThrow19;
                fileDetail3 = new FileDetail();
                fileDetail3.setPath(query.getString(i6));
                fileDetail3.setLastModified(query.getLong(i7));
                fileDetail3.setTotalSpace(query.getLong(i8));
                TestOfflineEntity testOfflineEntity22222222 = new TestOfflineEntity();
                testOfflineEntity22222222.setAppid(query.getString(columnIndexOrThrow20));
                testOfflineEntity22222222.setModuleCode(query.getInt(columnIndexOrThrow21));
                testOfflineEntity22222222.setType(query.getString(columnIndexOrThrow22));
                testOfflineEntity22222222.setName(query.getString(columnIndexOrThrow23));
                testOfflineEntity22222222.setDocumentUrl(query.getString(columnIndexOrThrow24));
                testOfflineEntity22222222.setOriginalUrl(query.getString(columnIndexOrThrow25));
                testOfflineEntity22222222.setOriginalUrlType(query.getString(columnIndexOrThrow26));
                testOfflineEntity22222222.setPatch_total(query.getString(columnIndexOrThrow27));
                testOfflineEntity22222222.setDocumentDir(query.getString(columnIndexOrThrow28));
                testOfflineEntity22222222.setSourceRoot(query.getString(columnIndexOrThrow29));
                testOfflineEntity22222222.setSourceDir(query.getString(columnIndexOrThrow30));
                testOfflineEntity22222222.setAppMin(query.getString(columnIndexOrThrow31));
                testOfflineEntity22222222.setAppMax(query.getString(columnIndexOrThrow32));
                testOfflineEntity22222222.setServerPriority(query.getInt(columnIndexOrThrow33));
                testOfflineEntity22222222.setCheckType(query.getString(columnIndexOrThrow34));
                testOfflineEntity22222222.setFileRootPath(query.getString(columnIndexOrThrow35));
                testOfflineEntity22222222.setHtmlPreload(query.getInt(columnIndexOrThrow36));
                testOfflineEntity22222222.setHtmlStatic(query.getInt(columnIndexOrThrow37));
                testOfflineEntity22222222.setCacheable(query.getInt(columnIndexOrThrow38));
                testOfflineEntity22222222.setUngentoken(query.getInt(columnIndexOrThrow39));
                testOfflineEntity22222222.setBConfig(query.getString(columnIndexOrThrow40));
                testOfflineEntity22222222.setDegradeType(query.getInt(columnIndexOrThrow41));
                testOfflineEntity22222222.setMinFileVer(query.getString(columnIndexOrThrow42));
                testOfflineEntity22222222.setAvailable(query.getInt(columnIndexOrThrow43) == 0);
                testOfflineEntity22222222.setCreateTimestamp(query.getLong(columnIndexOrThrow44));
                testOfflineEntity22222222.setLastVisitTimestamp(query.getLong(columnIndexOrThrow45));
                testOfflineEntity22222222.setLocalPriorityInfo(query.getString(i3));
                testOfflineEntity22222222.setFileInfo(offlineEntityInfo);
                testOfflineEntity22222222.setDocumentFile(fileDetail);
                testOfflineEntity22222222.setSourceFile(fileDetail2);
                testOfflineEntity22222222.setZipFile(fileDetail3);
                testOfflineEntity = testOfflineEntity22222222;
            } else {
                testOfflineEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return testOfflineEntity;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }
}
