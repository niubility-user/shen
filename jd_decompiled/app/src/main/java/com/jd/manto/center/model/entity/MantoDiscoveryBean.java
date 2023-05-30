package com.jd.manto.center.model.entity;

import com.jingdong.common.baseRecycleAdapter.entity.MultiItemEntity;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoDiscoveryBean implements MultiItemEntity {
    public String appId;
    public String appName;
    public String appType;
    public List<Banner> banner;
    public String desc;
    public String logoUrl;
    public String recommendText;
    public int type;

    @Override // com.jingdong.common.baseRecycleAdapter.entity.MultiItemEntity
    public int getItemType() {
        return this.type;
    }
}
