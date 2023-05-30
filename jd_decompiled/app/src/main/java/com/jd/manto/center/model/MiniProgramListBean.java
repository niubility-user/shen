package com.jd.manto.center.model;

import com.jingdong.common.baseRecycleAdapter.entity.MultiItemEntity;
import java.util.List;

/* loaded from: classes17.dex */
public class MiniProgramListBean {
    public List<MiniProgramBean> appList;
    public String code;
    public boolean success;
    public int totalPage;

    /* loaded from: classes17.dex */
    public static class MiniProgramBean implements MultiItemEntity {
        public String appId;
        public String appName;
        public String appType;
        public String desc;
        public int itemType;
        public String logoUrl;

        @Override // com.jingdong.common.baseRecycleAdapter.entity.MultiItemEntity
        public int getItemType() {
            return this.itemType;
        }
    }
}
