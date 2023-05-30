package com.jingdong.common.nytask;

import com.jingdong.common.entity.JumpEntity;
import com.jingdong.discovertask.model.entity.BaseTaskEntity;
import java.util.List;

/* loaded from: classes5.dex */
public class NYIconEntity extends BaseTaskEntity {
    public List<Data> data;

    /* loaded from: classes5.dex */
    public static class Data {
        public String activityId;
        public String id;
        public String indexImage;
        public String name;
        public JumpEntity ruleJump;
        public boolean show;
        public String source;
    }
}
