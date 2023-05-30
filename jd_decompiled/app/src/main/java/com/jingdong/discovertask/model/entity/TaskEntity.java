package com.jingdong.discovertask.model.entity;

import com.jingdong.common.entity.JumpEntity;
import java.util.List;

/* loaded from: classes12.dex */
public class TaskEntity extends BaseTaskEntity {
    public DiscTaskDetailEntity data;

    /* loaded from: classes12.dex */
    public static class DiscAssistInfoEntity {
        public String assistTime;
        public String headUrl;
        public int isNewUser;
        public String nickName;
    }

    /* loaded from: classes12.dex */
    public static class DiscBaseTaskEntity {
        public DiscTaskItemInfoEntity assistTaskDetail;
        public int curSignState;
        public String floatDesc;
        public String iconUrl;
        public int maxTimes;
        public int mtaTaskPosition;
        public DiscSignTaskDetailEntity signDetail;
        public DiscTaskItemInfoEntity simpleRecordInfo;
        public String taskId;
        public String taskName;
        public String taskSubTitle;
        public String taskSubTitleExt;
        public String taskTitle;
        public int taskType;
        public int times;
    }

    /* loaded from: classes12.dex */
    public static class DiscSignTaskDetailEntity {
        public List<DiscTaskItemInfoEntity> discTaskItemInfos;
        public int maxSignTimes;
    }

    /* loaded from: classes12.dex */
    public static class DiscTaskDetailEntity {
        public List<DiscBaseTaskEntity> discTasks;
        public String entranceDesc;
        public String entranceImg;
        public JumpEntity ruleJump;
        public String ruleUrl;
        public int show;
        public String title;
    }

    /* loaded from: classes12.dex */
    public static class DiscTaskItemInfoEntity {
        public List<JumpEntity> assistJumps;
        public int curStep;
        public List<DiscAssistInfoEntity> discAssistInfos;
        public String inviteId;
        public String itemDesc;
        public String itemId;
        public int itemStatus;
        public int jPeas;
        public int order;
        public int receiveJPeas;
        public int rewardLevel;
        public int rewardStatus;
    }
}
