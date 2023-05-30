package com.jd.libs.hybrid.offlineload.entity;

import androidx.annotation.Keep;
import androidx.room.Entity;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.offlineload.db.OfflineDatabase;

@Entity(inheritSuperIndices = true, tableName = "HybridBuildInOfflineEntity")
@Keep
/* loaded from: classes16.dex */
public class BuildInOfflineEntity extends OfflineEntity {
    public static final String SQL_MIGRATE_6_TO_7_PART1 = "CREATE TABLE IF NOT EXISTS `HybridBuildInOfflineEntity` (`appid` TEXT NOT NULL, `type` TEXT, `name` TEXT, `documentUrl` TEXT, `originalUrl` TEXT, `originalUrlType` TEXT, `patch_total` TEXT, `documentDir` TEXT, `sourceRoot` TEXT, `sourceDir` TEXT, `appMin` TEXT, `appMax` TEXT, `serverPriority` INTEGER NOT NULL, `checkType` TEXT, `fileRootPath` TEXT, `htmlPreload` INTEGER NOT NULL, `htmlStatic` INTEGER NOT NULL, `cacheable` INTEGER NOT NULL, `ungentoken` INTEGER NOT NULL, `degradeType` INTEGER NOT NULL DEFAULT 2, `available` INTEGER NOT NULL, `createTimestamp` INTEGER NOT NULL, `lastVisitTimestamp` INTEGER NOT NULL, `localPriorityInfo` TEXT, `file_url` TEXT, `file_version` TEXT, `file_versionCode` INTEGER, `file_md5` TEXT, `file_fileType` TEXT, `file_password` TEXT, `file_patchTotal` INTEGER, `document_path` TEXT, `document_lastModified` INTEGER, `document_totalSpace` INTEGER, `source_path` TEXT, `source_lastModified` INTEGER, `source_totalSpace` INTEGER, `zip_path` TEXT, `zip_lastModified` INTEGER, `zip_totalSpace` INTEGER, PRIMARY KEY(`appid`))";
    public static final String SQL_MIGRATE_6_TO_7_PART2 = "CREATE INDEX IF NOT EXISTS `index_HybridBuildInOfflineEntity_documentUrl` ON `HybridBuildInOfflineEntity` (`documentUrl`)";
    public static final String SQL_MIGRATE_6_TO_7_PART3 = "CREATE INDEX IF NOT EXISTS `index_HybridBuildInOfflineEntity_originalUrl` ON `HybridBuildInOfflineEntity` (`originalUrl`)";
    public static final String SQL_MIGRATE_7_TO_8_PART1 = "ALTER TABLE HybridBuildInOfflineEntity ADD COLUMN `minFileVer` TEXT DEFAULT '0'";
    public static final String SQL_MIGRATE_8_TO_9_PART1 = "ALTER TABLE HybridBuildInOfflineEntity ADD COLUMN `bConfig` TEXT DEFAULT '0'";
    public static final String SQL_MIGRATE_8_TO_9_PART2 = "ALTER TABLE HybridBuildInOfflineEntity ADD COLUMN `moduleCode` INTEGER NOT NULL DEFAULT 0";
    public static final String SQL_MIGRATE_9_TO_10_PART1 = "ALTER TABLE HybridBuildInOfflineEntity ADD COLUMN `file_useZip` INTEGER DEFAULT 0";
    public static final String SQL_MIGRATE_9_TO_10_PART2 = "ALTER TABLE HybridBuildInOfflineEntity ADD COLUMN `file_fileUrlZip` TEXT";
    public static final String SQL_MIGRATE_9_TO_10_PART3 = "ALTER TABLE HybridBuildInOfflineEntity ADD COLUMN `file_fileZipMd5` TEXT";

    @Override // com.jd.libs.hybrid.offlineload.entity.OfflineEntity
    public void deleteDb() {
        OfflineDatabase.getInstance(HybridSettings.getAppContext()).getBuildInDao().delete(this);
    }

    @Override // com.jd.libs.hybrid.offlineload.entity.OfflineEntity
    public void updateDb() {
        OfflineDatabase.getInstance(HybridSettings.getAppContext()).getBuildInDao().update(this);
    }

    @Override // com.jd.libs.hybrid.offlineload.entity.OfflineEntity, com.jd.libs.hybrid.base.entity.IInterfaceCheck
    public boolean useful() {
        return isValidBuildInBiz();
    }
}
