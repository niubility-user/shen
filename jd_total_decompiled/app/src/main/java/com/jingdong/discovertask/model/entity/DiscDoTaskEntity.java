package com.jingdong.discovertask.model.entity;

import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes12.dex */
public class DiscDoTaskEntity extends BaseTaskEntity {
    public DiscDoTaskResultEntity data;

    /* loaded from: classes12.dex */
    public static class DiscDoTaskResultEntity {
        public DiscTimeModelConfigEntity discTimeModelConfigVo;
    }

    /* loaded from: classes12.dex */
    public static class DiscTimeModelConfigEntity {
        public JumpEntity iconJump;
        public String iconUrl;
        public int mType;
        public long suspendDuration;
        public long waitDuration;
    }
}
