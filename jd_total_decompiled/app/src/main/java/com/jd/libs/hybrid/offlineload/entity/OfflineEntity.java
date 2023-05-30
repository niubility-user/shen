package com.jd.libs.hybrid.offlineload.entity;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.anotation.JSONField;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.OfflineDatabase;
import com.jd.libs.hybrid.offlineload.loader.RetryFailInfo;
import com.jd.libs.hybrid.offlineload.utils.DateUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;

@Entity(indices = {@Index({"documentUrl"}), @Index({"originalUrl"})}, tableName = "HybridOfflineEntity")
@Keep
/* loaded from: classes.dex */
public class OfflineEntity implements IInterfaceCheck, Comparable<OfflineEntity>, RetryFailInfo.RetryEntity {
    public static final String SQL_MIGRATE_6_TO_7_PART1 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `degradeType` INTEGER NOT NULL DEFAULT 2";
    public static final String SQL_MIGRATE_6_TO_7_PART2 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `fileRootPath` TEXT";
    public static final String SQL_MIGRATE_6_TO_7_PART3 = "UPDATE OR ABORT `HybridOfflineEntity` SET `fileRootPath` = `source_path`";
    public static final String SQL_MIGRATE_7_TO_8_PART1 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `minFileVer` TEXT DEFAULT '0'";
    public static final String SQL_MIGRATE_8_TO_9_PART1 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `bConfig` TEXT DEFAULT '0'";
    public static final String SQL_MIGRATE_8_TO_9_PART2 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `moduleCode` INTEGER NOT NULL DEFAULT 0";
    public static final String SQL_MIGRATE_9_TO_10_PART1 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `file_useZip` INTEGER DEFAULT 0";
    public static final String SQL_MIGRATE_9_TO_10_PART2 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `file_fileUrlZip` TEXT";
    public static final String SQL_MIGRATE_9_TO_10_PART3 = "ALTER TABLE HybridOfflineEntity ADD COLUMN `file_fileZipMd5` TEXT";
    private static final String TAG = "OfflineEntity";
    public static final String TYPE_BIZ_BUILD_IN = "3";
    public static final String TYPE_BIZ_ORDINARY = "1";
    public static final String TYPE_BIZ_SSR = "2";
    public static final String TYPE_ORIGIN_REGEXP = "2";
    public static final String TYPE_ORIGIN_URL = "1";
    private String appMax;
    private String appMin;
    @NonNull
    @PrimaryKey
    private String appid;
    @ColumnInfo(defaultValue = "0")
    private String bConfig;
    private int cacheable;
    private String checkType;
    @JSONField(deserialize = false, serialize = false)
    private long createTimestamp;
    private String documentDir;
    @Embedded(prefix = "document_")
    @JSONField(deserialize = false, serialize = false)
    private FileDetail documentFile;
    private String documentUrl;
    @Embedded(prefix = "file_")
    @JSONField(name = "file")
    private OfflineEntityInfo fileInfo;
    @JSONField(deserialize = false, serialize = false)
    private String fileRootPath;
    private int htmlPreload;
    private int htmlStatic;
    @JSONField(deserialize = false, serialize = false)
    private long lastVisitTimestamp;
    @JSONField(deserialize = false, serialize = false)
    private volatile String localPriorityInfo;
    @ColumnInfo(defaultValue = "0")
    private int moduleCode;
    private String name;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private String oldFileRootPath;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private FileDetail oldZipFile;
    @Nullable
    private String originalUrl;
    private String originalUrlType;
    private String patch_total;
    @JSONField(name = RemoteMessageConst.Notification.PRIORITY)
    @ColumnInfo(name = "serverPriority")
    private int serverPriority;
    private String sourceDir;
    @Embedded(prefix = "source_")
    @JSONField(deserialize = false, serialize = false)
    private FileDetail sourceFile;
    private String sourceRoot;
    private int ungentoken;
    @Embedded(prefix = "zip_")
    @JSONField(deserialize = false, serialize = false)
    private FileDetail zipFile;
    private String type = "1";
    @ColumnInfo(defaultValue = "2")
    @Deprecated
    private int degradeType = 2;
    @ColumnInfo(defaultValue = "0")
    private String minFileVer = "0";
    @JSONField(deserialize = false, serialize = false)
    private boolean available = false;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private int localPriority = 10;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private long lpLastIncreaseTime = 0;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private long lpLastDecreaseTime = 0;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private int lpIncreaseTimesEveryTimeGap = 0;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private volatile boolean lpInfoNeedChange = false;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private boolean newAdded = false;

    private void parseLpInfo() {
        if (!TextUtils.isEmpty(this.localPriorityInfo)) {
            JDJSONObject parseObject = JDJSON.parseObject(this.localPriorityInfo);
            this.localPriority = parseObject.optInt("localPriority", 0);
            this.lpLastIncreaseTime = parseObject.optLong("lpLastIncreaseTime", 0L);
            this.lpLastDecreaseTime = parseObject.optLong("lpLastDecreaseTime", 0L);
            this.lpIncreaseTimesEveryTimeGap = parseObject.optInt("lpIncreaseTimesEveryTimeGap", 0);
        }
        this.lpInfoNeedChange = false;
    }

    public static void resetDbUrl(OfflineEntity offlineEntity) {
        if (offlineEntity == null) {
            return;
        }
        String documentUrl = offlineEntity.getDocumentUrl();
        if (!TextUtils.isEmpty(documentUrl)) {
            try {
                offlineEntity.setDocumentUrl(HybridUrlUtils.excludeQuery(documentUrl));
            } catch (Exception e2) {
                Log.e(TAG, e2);
                OfflineExceptionUtils.reportDownloadCodeError("resetDbUrl-document", offlineEntity.getAppid(), null, e2);
            }
        }
        if (offlineEntity.isRegexpMatch()) {
            return;
        }
        String originalUrl = offlineEntity.getOriginalUrl();
        if (TextUtils.isEmpty(originalUrl)) {
            return;
        }
        try {
            offlineEntity.setOriginalUrl(HybridUrlUtils.excludeQuery(originalUrl));
        } catch (Exception e3) {
            Log.e(TAG, e3);
            OfflineExceptionUtils.reportDownloadCodeError("resetDbUrl-origin", offlineEntity.getAppid(), null, e3);
        }
    }

    private void updateLpInfo() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("localPriority", (Object) Integer.valueOf(this.localPriority));
        jDJSONObject.put("lpLastIncreaseTime", (Object) Long.valueOf(this.lpLastIncreaseTime));
        jDJSONObject.put("lpLastDecreaseTime", (Object) Long.valueOf(this.lpLastDecreaseTime));
        jDJSONObject.put("lpIncreaseTimesEveryTimeGap", (Object) Integer.valueOf(this.lpIncreaseTimesEveryTimeGap));
        this.localPriorityInfo = jDJSONObject.toJSONString();
        this.lpInfoNeedChange = false;
    }

    public synchronized void calculateLpEveryTimeGap() {
        boolean z;
        long j2 = this.lastVisitTimestamp;
        long j3 = this.createTimestamp;
        boolean z2 = j2 >= j3;
        long max = Math.max(j3, j2);
        long j4 = this.lpLastDecreaseTime;
        if (max < j4) {
            max = j4;
            z = true;
        } else {
            z = false;
        }
        if (max == 0) {
            Log.d("OfflineEntity-Priority", "Error! lastTime is 0 when trying to recalculate priority for id " + this.appid);
            return;
        }
        int howManyDaysElapsed = DateUtils.howManyDaysElapsed(max, System.currentTimeMillis());
        if ((z && howManyDaysElapsed > 0) || ((!z && !z2 && howManyDaysElapsed > 0) || (!z && z2 && howManyDaysElapsed > 1))) {
            int i2 = this.localPriority;
            int i3 = howManyDaysElapsed - ((z || !z2) ? 0 : 1);
            this.localPriority = Math.max(i2 - i3, 0);
            this.lpLastDecreaseTime = System.currentTimeMillis();
            this.lpInfoNeedChange = true;
            Log.d("OfflineEntity-Priority", "calculateLpEveryTimeGap: Decreased local priority (-" + i3 + ") for id " + this.appid + ", old LP = " + i2 + ", new LP = " + this.localPriority + ", time = " + max + ", elapsed day = " + howManyDaysElapsed);
            StringBuilder sb = new StringBuilder();
            sb.append("calculateLpEveryTimeGap: id = ");
            sb.append(this.appid);
            sb.append(", SP = ");
            sb.append(getServerPriority());
            sb.append(", LP = ");
            sb.append(getLocalPriority());
            sb.append(", P = ");
            sb.append(getPriority());
            Log.d("OfflineEntity-Priority", sb.toString());
        } else {
            Log.d("OfflineEntity-Priority", "calculateLpEveryTimeGap: still in time gap, SKIP decreasing local priority for id " + this.appid + ", last time = " + max);
        }
    }

    public void copyLocalFileInfoFromOld(OfflineEntity offlineEntity) {
        if (offlineEntity != null) {
            setFileRootPath(offlineEntity.getFileRootPath());
            setDocumentFile(offlineEntity.getDocumentFile());
            setSourceFile(offlineEntity.getSourceFile());
            setZipFile(offlineEntity.getZipFile());
            setAvailable(offlineEntity.isAvailable());
        }
    }

    public void copyLocalInfoFromOld(OfflineEntity offlineEntity) {
        if (offlineEntity != null) {
            this.createTimestamp = offlineEntity.createTimestamp;
            this.lastVisitTimestamp = offlineEntity.lastVisitTimestamp;
            this.localPriorityInfo = offlineEntity.localPriorityInfo;
            this.localPriority = offlineEntity.localPriority;
            this.lpLastIncreaseTime = offlineEntity.lpLastIncreaseTime;
            this.lpLastDecreaseTime = offlineEntity.lpLastDecreaseTime;
            this.lpIncreaseTimesEveryTimeGap = offlineEntity.lpIncreaseTimesEveryTimeGap;
        }
    }

    public void copyLocalZipInfoFromOld(OfflineEntity offlineEntity) {
        if (offlineEntity == null) {
            return;
        }
        this.zipFile = offlineEntity.getZipFile();
    }

    public void deleteDb() {
        OfflineDatabase.getInstance(HybridSettings.getAppContext()).getDao().delete(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && (obj instanceof OfflineEntity)) {
            OfflineEntity offlineEntity = (OfflineEntity) obj;
            String str = this.appid;
            if (str == null ? offlineEntity.appid == null : str.equals(offlineEntity.appid)) {
                String str2 = this.documentUrl;
                if (str2 == null ? offlineEntity.documentUrl == null : str2.equals(offlineEntity.documentUrl)) {
                    OfflineEntityInfo offlineEntityInfo = this.fileInfo;
                    OfflineEntityInfo offlineEntityInfo2 = offlineEntity.fileInfo;
                    return offlineEntityInfo != null ? offlineEntityInfo.equals(offlineEntityInfo2) : offlineEntityInfo2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public String getAppMax() {
        return this.appMax;
    }

    public String getAppMin() {
        return this.appMin;
    }

    public String getAppid() {
        return this.appid;
    }

    public String getBConfig() {
        return this.bConfig;
    }

    public int getCacheable() {
        return this.cacheable;
    }

    public String getCheckType() {
        return this.checkType;
    }

    public long getCreateTimestamp() {
        return this.createTimestamp;
    }

    @Deprecated
    public int getDegradeType() {
        return this.degradeType;
    }

    public String getDocumentDir() {
        return this.documentDir;
    }

    public FileDetail getDocumentFile() {
        return this.documentFile;
    }

    public String getDocumentUrl() {
        return this.documentUrl;
    }

    public OfflineEntityInfo getFileInfo() {
        return this.fileInfo;
    }

    public String getFileRootPath() {
        return this.fileRootPath;
    }

    public int getHtmlPreload() {
        return this.htmlPreload;
    }

    public int getHtmlStatic() {
        return this.htmlStatic;
    }

    public long getLastVisitTimestamp() {
        return this.lastVisitTimestamp;
    }

    public int getLocalPriority() {
        return this.localPriority;
    }

    public String getLocalPriorityInfo() {
        if (this.lpInfoNeedChange) {
            updateLpInfo();
        }
        return this.localPriorityInfo;
    }

    public String getMinFileVer() {
        return this.minFileVer;
    }

    public int getModuleCode() {
        return this.moduleCode;
    }

    public String getName() {
        return this.name;
    }

    public String getOldFileRootPath() {
        return this.oldFileRootPath;
    }

    public FileDetail getOldZipFile() {
        return this.oldZipFile;
    }

    @Nullable
    public String getOriginalUrl() {
        if (TextUtils.isEmpty(this.originalUrl)) {
            return null;
        }
        return this.originalUrl;
    }

    public String getOriginalUrlType() {
        return this.originalUrlType;
    }

    public String getPatchUrl(int i2) {
        return this.fileInfo.getUrl() + "-" + i2 + "-" + this.fileInfo.getVersionCode();
    }

    public String getPatch_total() {
        return this.patch_total;
    }

    public float getPriority() {
        float f2 = HybridSettings.SP_RATIO;
        return (this.serverPriority * f2) + (this.localPriority * (1.0f - f2));
    }

    @Override // com.jd.libs.hybrid.offlineload.loader.RetryFailInfo.RetryEntity
    public String getRetryKey() {
        return getDocumentUrl() + CartConstant.KEY_YB_INFO_LINK + getFileInfo().getVersionCode() + CartConstant.KEY_YB_INFO_LINK + getFileInfo().getUrl();
    }

    public int getServerPriority() {
        return this.serverPriority;
    }

    public String getShowUrl() {
        return TextUtils.isEmpty(this.originalUrl) ? this.documentUrl : this.originalUrl;
    }

    public String getSourceDir() {
        return this.sourceDir;
    }

    public FileDetail getSourceFile() {
        return this.sourceFile;
    }

    public String getSourceRoot() {
        return this.sourceRoot;
    }

    public String getType() {
        return this.type;
    }

    public int getUngentoken() {
        return this.ungentoken;
    }

    public FileDetail getZipFile() {
        return this.zipFile;
    }

    public boolean hasUnzipFileChanged() {
        String fileRootPath = getFileRootPath();
        if (TextUtils.isEmpty(fileRootPath)) {
            return true;
        }
        File file = new File(fileRootPath);
        if (file.isDirectory() && file.exists()) {
            return isValidSSrBiz() ? getSourceFile() == null || getSourceFile().exists() : getDocumentFile() == null || getDocumentFile().exists() || getSourceFile() == null || getSourceFile().exists();
        }
        return true;
    }

    public int hashCode() {
        String str = this.appid;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.documentUrl;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        OfflineEntityInfo offlineEntityInfo = this.fileInfo;
        return hashCode2 + (offlineEntityInfo != null ? offlineEntityInfo.hashCode() : 0);
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean isBuildInBiz() {
        return "3".equals(this.type);
    }

    public boolean isNewAdded() {
        return this.newAdded;
    }

    public boolean isOrdinaryBiz() {
        return "1".equals(this.type);
    }

    public boolean isPatch(int i2) {
        OfflineEntityInfo offlineEntityInfo;
        return i2 >= 0 && (offlineEntityInfo = this.fileInfo) != null && offlineEntityInfo.getVersionCode() > 0 && this.fileInfo.getPatchTotal() > 0 && this.fileInfo.getVersionCode() > i2 && this.fileInfo.getPatchTotal() >= this.fileInfo.getVersionCode() - i2 && !TextUtils.isEmpty(this.fileInfo.getUrl());
    }

    public boolean isPatchOf(OfflineEntity offlineEntity) {
        if (offlineEntity == null || offlineEntity.getFileInfo() == null || offlineEntity.getZipFile() == null || offlineEntity.getZipFile().exists()) {
            return false;
        }
        return isPatch(offlineEntity.getFileInfo().getVersionCode());
    }

    public boolean isRegexpMatch() {
        return "2".equals(this.originalUrlType) && !TextUtils.isEmpty(this.originalUrl);
    }

    public boolean isSSrBiz() {
        return "2".equals(this.type);
    }

    public boolean isValidBuildInBiz() {
        OfflineEntityInfo offlineEntityInfo;
        return (TextUtils.isEmpty(this.appid) || !"3".equals(this.type) || TextUtils.isEmpty(this.documentUrl) || TextUtils.isEmpty(this.documentDir) || TextUtils.isEmpty(this.originalUrl) || (offlineEntityInfo = this.fileInfo) == null || !offlineEntityInfo.useful()) ? false : true;
    }

    public boolean isValidOrdinaryBiz() {
        OfflineEntityInfo offlineEntityInfo;
        return (TextUtils.isEmpty(this.appid) || !"1".equals(this.type) || TextUtils.isEmpty(this.documentUrl) || TextUtils.isEmpty(this.documentDir) || (offlineEntityInfo = this.fileInfo) == null || !offlineEntityInfo.useful()) ? false : true;
    }

    public boolean isValidSSrBiz() {
        OfflineEntityInfo offlineEntityInfo;
        return "2".equals(this.type) && !TextUtils.isEmpty(this.originalUrl) && (offlineEntityInfo = this.fileInfo) != null && offlineEntityInfo.useful();
    }

    public void markVisited() {
        this.lastVisitTimestamp = System.currentTimeMillis();
    }

    public boolean needCheckLatest() {
        return TextUtils.isEmpty(this.checkType) || "1".equals(this.checkType);
    }

    public void setAppMax(String str) {
        this.appMax = str;
    }

    public void setAppMin(String str) {
        this.appMin = str;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public void setAvailable(boolean z) {
        this.available = z;
    }

    public void setBConfig(String str) {
        this.bConfig = str;
    }

    public void setCacheable(int i2) {
        this.cacheable = i2;
    }

    public void setCheckType(String str) {
        this.checkType = str;
    }

    public void setCreateTime() {
        if (this.createTimestamp == 0) {
            this.createTimestamp = System.currentTimeMillis();
        }
    }

    public void setCreateTimestamp(long j2) {
        this.createTimestamp = j2;
    }

    @Deprecated
    public void setDegradeType(int i2) {
        this.degradeType = i2;
    }

    public void setDocumentDir(String str) {
        this.documentDir = str;
    }

    public void setDocumentFile(FileDetail fileDetail) {
        this.documentFile = fileDetail;
    }

    public void setDocumentUrl(String str) {
        this.documentUrl = str;
    }

    public void setFileInfo(OfflineEntityInfo offlineEntityInfo) {
        this.fileInfo = offlineEntityInfo;
    }

    public void setFileRootPath(String str) {
        this.fileRootPath = str;
    }

    public void setHtmlPreload(int i2) {
        this.htmlPreload = i2;
    }

    public void setHtmlStatic(int i2) {
        this.htmlStatic = i2;
    }

    public void setLastVisitTimestamp(long j2) {
        this.lastVisitTimestamp = j2;
    }

    public void setLocalPriority(int i2) {
        this.localPriority = i2;
        this.lpInfoNeedChange = true;
    }

    public void setLocalPriorityInfo(String str) {
        this.localPriorityInfo = str;
        parseLpInfo();
    }

    public void setMinFileVer(String str) {
        this.minFileVer = str;
    }

    public void setModuleCode(int i2) {
        this.moduleCode = i2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNewAdded(boolean z) {
        this.newAdded = z;
    }

    public void setOldFileRootPath(String str) {
        this.oldFileRootPath = str;
    }

    public void setOldZipFile(FileDetail fileDetail) {
        this.oldZipFile = fileDetail;
    }

    public void setOriginalUrl(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.originalUrl = str;
    }

    public void setOriginalUrlType(String str) {
        this.originalUrlType = str;
    }

    public void setPatchVersionCode(int i2) {
        OfflineEntityInfo offlineEntityInfo = this.fileInfo;
        if (offlineEntityInfo != null) {
            offlineEntityInfo.setPatchVersionCode(i2);
        }
    }

    public void setPatch_total(String str) {
        this.patch_total = str;
    }

    public void setServerPriority(int i2) {
        this.serverPriority = i2;
    }

    public void setSourceDir(String str) {
        this.sourceDir = str;
    }

    public void setSourceFile(FileDetail fileDetail) {
        this.sourceFile = fileDetail;
    }

    public void setSourceRoot(String str) {
        this.sourceRoot = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUngentoken(int i2) {
        this.ungentoken = i2;
    }

    public void setZipFile(FileDetail fileDetail) {
        this.zipFile = fileDetail;
    }

    @NonNull
    public String toString() {
        return String.format("{AppId: %s}", this.appid);
    }

    public synchronized void tryIncreaseLpWhenVisited() {
        if (!DateUtils.isSameDay(this.lpLastIncreaseTime, System.currentTimeMillis())) {
            this.lpIncreaseTimesEveryTimeGap = 0;
            this.lpInfoNeedChange = true;
            Log.d("OfflineEntity-Priority", "tryIncreaseLpWhenVisited: Elapsed time exceed time-gap, reset times of increase to 0 for id " + this.appid + ", lastTime = " + this.lpLastIncreaseTime);
        }
        int i2 = this.lpIncreaseTimesEveryTimeGap;
        if (i2 < 3) {
            this.lpIncreaseTimesEveryTimeGap = i2 + 1;
            this.localPriority = Math.min(this.localPriority + 1, 20);
            this.lpLastIncreaseTime = System.currentTimeMillis();
            this.lpInfoNeedChange = true;
            Log.d("OfflineEntity-Priority", "tryIncreaseLpWhenVisited: Increased LP for id " + this.appid + ", new LP = " + this.localPriority + ", increase times today = " + this.lpIncreaseTimesEveryTimeGap);
            Log.d("OfflineEntity-Priority", "tryIncreaseLpWhenVisited: id = " + this.appid + ", SP = " + getServerPriority() + ", LP = " + getLocalPriority() + ", P = " + getPriority());
        } else {
            Log.d("OfflineEntity-Priority", "tryIncreaseLpWhenVisited: LP had been increased already " + this.lpIncreaseTimesEveryTimeGap + " times in time-gap, no need to increase for id " + this.appid);
        }
    }

    public void updateDb() {
        OfflineDatabase.getInstance(HybridSettings.getAppContext()).getDao().update(this);
    }

    @Override // com.jd.libs.hybrid.base.entity.IInterfaceCheck
    public boolean useful() {
        return isValidOrdinaryBiz() || isValidBuildInBiz() || isValidSSrBiz();
    }

    @Override // java.lang.Comparable
    public int compareTo(OfflineEntity offlineEntity) {
        return (int) ((offlineEntity.getPriority() * 1000.0f) - (getPriority() * 1000.0f));
    }
}
