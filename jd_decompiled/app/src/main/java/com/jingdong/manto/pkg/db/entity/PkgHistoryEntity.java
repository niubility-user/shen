package com.jingdong.manto.pkg.db.entity;

import androidx.annotation.NonNull;
import com.jingdong.manto.provider.db.anno.FieldFilter;
import com.jingdong.manto.provider.db.anno.Table;

@Table(primaryKeys = {"appId", "type"}, value = "pkgHistory")
/* loaded from: classes16.dex */
public class PkgHistoryEntity {
    @NonNull
    public String appId;
    @FieldFilter
    public boolean favorite;
    public String logo;
    public String name;
    @NonNull
    public String type;
}
