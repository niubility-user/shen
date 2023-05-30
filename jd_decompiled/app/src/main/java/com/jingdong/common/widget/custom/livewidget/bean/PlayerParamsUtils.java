package com.jingdong.common.widget.custom.livewidget.bean;

/* loaded from: classes12.dex */
public class PlayerParamsUtils {

    /* loaded from: classes12.dex */
    public static class PlayerParamsEntity {
        public boolean isReplay;
        public boolean mMutePlay;
        public int playMode;
        public String playerOptionJsonStr;
        public String replayerOptionJsonStr;
        public String url;

        private PlayerParamsEntity(String str, boolean z, String str2, String str3) {
            this.url = str;
            this.playerOptionJsonStr = str2;
            this.isReplay = z;
            this.replayerOptionJsonStr = str3;
        }
    }

    public static PlayerParamsEntity buildLiveParams(String str, boolean z, String str2, String str3, boolean z2) {
        PlayerParamsEntity playerParamsEntity = new PlayerParamsEntity(str, z, str2, str3);
        playerParamsEntity.playMode = 10003;
        playerParamsEntity.mMutePlay = z2;
        return playerParamsEntity;
    }
}
