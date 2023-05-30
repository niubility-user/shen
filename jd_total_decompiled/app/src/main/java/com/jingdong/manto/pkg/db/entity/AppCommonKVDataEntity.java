package com.jingdong.manto.pkg.db.entity;

import androidx.annotation.NonNull;
import com.jingdong.manto.provider.db.anno.Table;

@Table(primaryKeys = {"key", "appType"}, value = "tb_commonKVData")
/* loaded from: classes16.dex */
public class AppCommonKVDataEntity {
    @NonNull
    public String appType;
    @NonNull
    public String key;
    public String userId;
    public String value;

    public AppCommonKVDataEntity() {
    }

    public AppCommonKVDataEntity(@NonNull String str, String str2, @NonNull String str3) {
        this.key = str;
        this.value = str2;
        this.appType = str3;
    }
}
