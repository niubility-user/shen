package com.jingdong.manto.pkg.db.entity;

import androidx.annotation.NonNull;
import com.jingdong.manto.provider.db.anno.Table;

@Table(primaryKeys = {"key", "appType"}, value = "tb_storage")
/* loaded from: classes16.dex */
public class StorageEntity {
    public String appId;
    @NonNull
    public String appType;
    public String data;
    public int dataSize;
    public String dataType;
    @NonNull
    public String key;
    public String userId;

    public StorageEntity() {
    }

    public StorageEntity(@NonNull String str, String str2, @NonNull String str3, int i2, String str4) {
        this.key = str;
        this.data = str2;
        this.dataType = str3;
        this.dataSize = i2;
        this.appType = str4;
    }
}
