package com.jd.libs.hybrid.offlineload.entity;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.jd.framework.json.anotation.JSONField;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.offlineload.db.converter.RoomDateConverts;
import com.jd.libs.hybrid.offlineload.loader.RetryFailInfo;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Map;

@TypeConverters({RoomDateConverts.class})
@Entity(indices = {@Index(unique = true, value = {"url"})}, tableName = "HybridOfflineCommonEntity")
@Keep
/* loaded from: classes.dex */
public class CommonEntity implements IInterfaceCheck, RetryFailInfo.RetryEntity {
    @ColumnInfo(defaultValue = "0")
    private String bConfigCommon;
    @JSONField(deserialize = false, serialize = false)
    private long createTimestamp;
    @Embedded(prefix = "localfile_")
    @JSONField(deserialize = false, serialize = false)
    private FileDetail fileDetail;
    @ColumnInfo(name = "headersMap")
    private Map<String, String> headersMap;
    @NonNull
    @PrimaryKey
    private String id;
    @JSONField(deserialize = false, serialize = false)
    @Ignore
    private boolean isHeaderRequest;
    private String md5;
    private String url;
    private int versionCode = -1;
    @JSONField(deserialize = false, serialize = false)
    private boolean available = false;

    public void copyLocalFileInfoFromOld(CommonEntity commonEntity) {
        if (commonEntity != null) {
            setFileDetail(commonEntity.getFileDetail());
            setAvailable(commonEntity.isAvailable());
        }
    }

    public void copyLocalInfoFromOld(CommonEntity commonEntity) {
        if (commonEntity != null) {
            this.createTimestamp = commonEntity.createTimestamp;
            this.headersMap = commonEntity.headersMap;
        }
    }

    public String getBConfigCommon() {
        return this.bConfigCommon;
    }

    public long getCreateTimestamp() {
        return this.createTimestamp;
    }

    public FileDetail getFileDetail() {
        return this.fileDetail;
    }

    public Map<String, String> getHeadersMap() {
        return this.headersMap;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    public String getMd5() {
        return this.md5;
    }

    @Override // com.jd.libs.hybrid.offlineload.loader.RetryFailInfo.RetryEntity
    public String getRetryKey() {
        return getUrl() + CartConstant.KEY_YB_INFO_LINK + getVersionCode();
    }

    public String getUrl() {
        return this.url;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean isFileChanged() {
        return getFileDetail() == null || getFileDetail().exists();
    }

    public boolean isHeaderRequest() {
        return this.isHeaderRequest;
    }

    public void setAvailable(boolean z) {
        this.available = z;
    }

    public void setBConfigCommon(String str) {
        this.bConfigCommon = str;
    }

    public void setCreateTime() {
        if (this.createTimestamp == 0) {
            this.createTimestamp = System.currentTimeMillis();
        }
    }

    public void setCreateTimestamp(long j2) {
        this.createTimestamp = j2;
    }

    public void setFileDetail(FileDetail fileDetail) {
        this.fileDetail = fileDetail;
    }

    public void setHeaderRequest(boolean z) {
        this.isHeaderRequest = z;
    }

    public void setHeadersMap(Map<String, String> map) {
        this.headersMap = map;
    }

    public void setId(@NonNull String str) {
        this.id = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVersionCode(int i2) {
        this.versionCode = i2;
    }

    @Override // com.jd.libs.hybrid.base.entity.IInterfaceCheck
    public boolean useful() {
        return (TextUtils.isEmpty(this.id) || TextUtils.isEmpty(this.url) || this.versionCode <= 0 || TextUtils.isEmpty(this.md5)) ? false : true;
    }
}
