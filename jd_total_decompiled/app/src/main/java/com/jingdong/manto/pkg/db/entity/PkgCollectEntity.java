package com.jingdong.manto.pkg.db.entity;

import androidx.annotation.NonNull;
import com.jingdong.manto.provider.db.anno.Table;

@Table(primaryKeys = {"appId", "type"}, value = "pkgCollect")
/* loaded from: classes16.dex */
public class PkgCollectEntity {
    @NonNull
    public String appId;
    public String description;
    public boolean favorite;
    public long lastOpenTime;
    public String logo;
    public String name;
    @NonNull
    public String type;
    public String venderName;
    public String venderType;

    public PkgCollectEntity() {
    }

    public PkgCollectEntity(@NonNull String str, @NonNull String str2, String str3, String str4, boolean z, long j2) {
        this.appId = str;
        this.type = str2;
        this.name = str3;
        this.logo = str4;
        this.favorite = z;
        this.lastOpenTime = j2;
    }

    public boolean isDebugPkg() {
        return "2".equalsIgnoreCase(this.type);
    }
}
