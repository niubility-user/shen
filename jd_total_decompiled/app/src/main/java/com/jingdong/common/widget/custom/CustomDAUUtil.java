package com.jingdong.common.widget.custom;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.messagecenter.NotificationMessageSummary;

/* loaded from: classes12.dex */
public class CustomDAUUtil {

    /* loaded from: classes12.dex */
    public static class DAUEntity {
        private String articleChannel;
        private String categoryIII;
        private final String id;
        private boolean isHideFloor;
        private boolean isInstation;
        private String keyword;
        private String logId;
        private String mtestId;
        private String testId;

        public DAUEntity(String str) {
            this.id = str;
        }

        public DAUEntity setArticleChannel(String str) {
            this.articleChannel = str;
            return this;
        }

        public DAUEntity setCategoryIII(String str) {
            this.categoryIII = str;
            return this;
        }

        public DAUEntity setHideFloor(boolean z) {
            this.isHideFloor = z;
            return this;
        }

        public DAUEntity setInstation(boolean z) {
            this.isInstation = z;
            return this;
        }

        public DAUEntity setKeyword(String str) {
            this.keyword = str;
            return this;
        }

        public DAUEntity setLogId(String str) {
            this.logId = str;
            return this;
        }

        public DAUEntity setMtestId(String str) {
            this.mtestId = str;
            return this;
        }

        public DAUEntity setTestId(String str) {
            this.testId = str;
            return this;
        }
    }

    public static void toTargetPage(Context context, String str, DAUEntity dAUEntity) {
        if (dAUEntity != null) {
            Bundle bundle = new Bundle();
            bundle.putString("id", dAUEntity.id);
            bundle.putString(NotificationMessageSummary.TEST_ID, dAUEntity.testId);
            bundle.putString("channel", dAUEntity.articleChannel);
            bundle.putBoolean("isInstation", dAUEntity.isInstation);
            bundle.putBoolean("isHideFloor", dAUEntity.isHideFloor);
            bundle.putString("logId", dAUEntity.logId);
            bundle.putString("keyword", dAUEntity.keyword);
            bundle.putString("mtestId", dAUEntity.mtestId);
            bundle.putString("categoryIII", dAUEntity.categoryIII);
            JumpUtil.execJumpByDes(str, context, bundle);
            return;
        }
        throw new NullPointerException("DAUEntity can't be null");
    }
}
