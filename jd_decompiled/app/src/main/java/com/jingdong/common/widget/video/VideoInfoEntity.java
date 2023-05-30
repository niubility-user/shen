package com.jingdong.common.widget.video;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.connect.share.QzonePublish;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes12.dex */
public class VideoInfoEntity {
    private String articleId;
    private long avgSpeed;
    private String chId;
    private String dockingId;
    private int errCode;
    private String errMsg;
    private long firstPlayTime;
    private long initTime;
    private int isError;
    private int lostFramesCnt;
    private String lostFramesInfo;
    private String mark;
    private long maxSpeed;
    private long minSpeed;
    private String occurTime;
    private String pageId;
    private transient HashMap<String, String> paramsMap;
    private long playDuration;
    private String playType;
    private String projectId;
    private String referPageId;
    private String roomNumber;
    private String sessionId;
    private String sku;
    private String source;
    private int status;
    private String storageSource;
    private int stuckCnt;
    private long stuckTime;
    private String typeId;
    private long videoDuration;
    private int videoType;

    /* loaded from: classes12.dex */
    public static class Builder {
        private String articleId;
        private String chId;
        private String pageId;
        private String playType;
        private String projectId;
        private String referPageId;
        private String roomNumber;
        private String sku;
        private String source;
        private String typeId;

        public Builder(String str, String str2, String str3, String str4) {
            this.roomNumber = str;
            this.playType = str2;
            this.source = str3;
            this.pageId = str4;
        }

        public Builder articleId(String str) {
            this.articleId = str;
            return this;
        }

        public VideoInfoEntity build() {
            VideoInfoEntity videoInfoEntity = new VideoInfoEntity();
            videoInfoEntity.setRoomNumber(this.roomNumber);
            videoInfoEntity.setPlayType(this.playType);
            videoInfoEntity.setSource(this.source);
            videoInfoEntity.setPageId(this.pageId);
            String str = this.sku;
            if (str != null) {
                videoInfoEntity.setSku(str);
            }
            String str2 = this.articleId;
            if (str2 != null) {
                videoInfoEntity.setArticleId(str2);
            }
            String str3 = this.referPageId;
            if (str3 != null) {
                videoInfoEntity.setReferPageId(str3);
            }
            String str4 = this.projectId;
            if (str4 != null) {
                videoInfoEntity.setProjectId(str4);
            }
            videoInfoEntity.setVideoType(1);
            Map paramFromUrl = VideoInfoEntity.getParamFromUrl(this.source);
            String str5 = (String) paramFromUrl.get("dockingId");
            if (str5 == null) {
                str5 = "-1";
            }
            videoInfoEntity.setDockingId(str5);
            String str6 = (String) paramFromUrl.get("storageSource");
            if (str6 == null) {
                str6 = "3";
            }
            videoInfoEntity.setStorageSource(str6);
            if (TextUtils.isEmpty(this.typeId)) {
                videoInfoEntity.setTypeId("4");
            }
            if (TextUtils.isEmpty(this.chId)) {
                videoInfoEntity.setChId("3");
            }
            return videoInfoEntity;
        }

        public Builder chId(String str) {
            this.chId = str;
            return this;
        }

        public Builder projectId(String str) {
            this.projectId = str;
            return this;
        }

        public Builder referPageId(String str) {
            this.referPageId = str;
            return this;
        }

        public Builder sku(String str) {
            this.sku = str;
            return this;
        }

        public Builder typeId(String str) {
            this.typeId = str;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public static class LostFramesInfo {
        public int ad;
        public String as;
        public int disad;
        public int disvd;
        public String ms;
        public String ts;
        public int vd;
    }

    public static VideoInfoEntity create(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull String str5, @NonNull String str6) {
        VideoInfoEntity videoInfoEntity = new VideoInfoEntity(str, str2, str3, str4, str5, str6);
        Map<String, String> paramFromUrl = getParamFromUrl(str3);
        String str7 = paramFromUrl.get("dockingId");
        if (str7 == null) {
            str7 = "-1";
        }
        videoInfoEntity.setDockingId(str7);
        String str8 = paramFromUrl.get("storageSource");
        if (str8 == null) {
            str8 = "3";
        }
        videoInfoEntity.setStorageSource(str8);
        return videoInfoEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> getParamFromUrl(String str) {
        int indexOf;
        String[] split;
        String[] split2;
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf("?")) == -1) {
            return hashMap;
        }
        String substring = str.substring(indexOf + 1);
        if (!TextUtils.isEmpty(substring) && (split = substring.split(ContainerUtils.FIELD_DELIMITER)) != null && split.length > 0) {
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2) && (split2 = str2.split(ContainerUtils.KEY_VALUE_DELIMITER)) != null && split2.length >= 2) {
                    hashMap.put(split2[0], split2[1]);
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setArticleId(String str) {
        this.articleId = str;
        this.paramsMap.put("articleId", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChId(String str) {
        this.chId = str;
        this.paramsMap.put("chId", str);
    }

    private void setIsError(int i2) {
        this.isError = i2;
        this.paramsMap.put("isError", "" + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayType(String str) {
        this.playType = str;
        this.paramsMap.put(ReportConstant.CommonInfo.PLAY_TYPE, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProjectId(String str) {
        this.projectId = str;
        this.paramsMap.put("projectId", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferPageId(String str) {
        this.referPageId = str;
        this.paramsMap.put("referPageId", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRoomNumber(String str) {
        this.roomNumber = str;
        this.paramsMap.put("roomNumber", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSku(String str) {
        this.sku = str;
        this.paramsMap.put("sku", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSource(String str) {
        this.source = str;
        this.paramsMap.put("source", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypeId(String str) {
        this.typeId = str;
        this.paramsMap.put("typeId", str);
    }

    public String getArticleId() {
        return this.articleId;
    }

    public long getAvgSpeed() {
        return this.avgSpeed;
    }

    public String getChId() {
        return this.chId;
    }

    public String getDockingId() {
        return this.dockingId;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public long getFirstPlayTime() {
        return this.firstPlayTime;
    }

    public long getInitTime() {
        return this.initTime;
    }

    public int getIsError() {
        return this.isError;
    }

    public int getLostFramesCnt() {
        return this.lostFramesCnt;
    }

    public String getLostFramesInfo() {
        return this.lostFramesInfo;
    }

    public String getMark() {
        return this.mark;
    }

    public long getMaxSpeed() {
        return this.maxSpeed;
    }

    public long getMinSpeed() {
        return this.minSpeed;
    }

    public String getOccurTime() {
        return this.occurTime;
    }

    public String getPageId() {
        return this.pageId;
    }

    public HashMap<String, String> getParamsMap() {
        return this.paramsMap;
    }

    public long getPlayDuration() {
        return this.playDuration;
    }

    public String getPlayType() {
        return this.playType;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getReferPageId() {
        return this.referPageId;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getSku() {
        return this.sku;
    }

    public String getSource() {
        return this.source;
    }

    public int getStatus() {
        return this.status;
    }

    public String getStorageSource() {
        return this.storageSource;
    }

    public int getStuckCnt() {
        return this.stuckCnt;
    }

    public long getStuckTime() {
        return this.stuckTime;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public long getVideoDuration() {
        return this.videoDuration;
    }

    public int getVideoType() {
        return this.videoType;
    }

    public void setAvgSpeed(long j2) {
        this.avgSpeed = j2;
        this.paramsMap.put("avgSpeed", "" + j2);
    }

    public void setDockingId(String str) {
        this.dockingId = str;
        this.paramsMap.put("dockingId", str);
    }

    public void setErrCode(int i2) {
        this.errCode = i2;
        this.paramsMap.put("errCode", "" + i2);
        setIsError(1);
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
        this.paramsMap.put("errMsg", str);
    }

    public void setFirstPlayTime(long j2) {
        this.firstPlayTime = j2;
        this.paramsMap.put("firstPlayTime", "" + j2);
    }

    public void setInitTime(long j2) {
        this.initTime = j2;
        this.paramsMap.put("initTime", "" + j2);
    }

    public void setLostFramesCnt(int i2) {
        this.lostFramesCnt = i2;
        this.paramsMap.put("lostFramesCnt", "" + i2);
    }

    public void setLostFramesInfo(String str) {
        this.lostFramesInfo = str;
        this.paramsMap.put("lostFramesInfo", str);
    }

    public void setMark(String str) {
        this.mark = str;
        this.paramsMap.put("mark", str);
    }

    public void setMaxSpeed(long j2) {
        this.maxSpeed = j2;
        this.paramsMap.put("maxSpeed", "" + j2);
    }

    public void setMinSpeed(long j2) {
        this.minSpeed = j2;
        this.paramsMap.put("minSpeed", "" + j2);
    }

    public void setOccurTime(String str) {
        this.occurTime = str;
        this.paramsMap.put("occurTime", "" + str);
    }

    public void setPageId(String str) {
        this.pageId = str;
        this.paramsMap.put("pageId", str);
    }

    public void setPlayDuration(long j2) {
        this.playDuration = j2;
        this.paramsMap.put("playDuration", "" + j2);
    }

    public void setSessionId(String str) {
        this.sessionId = str;
        this.paramsMap.put("sessionId", str);
    }

    public void setStatus(int i2) {
        this.status = i2;
        this.paramsMap.put("status", "" + i2);
    }

    public void setStorageSource(String str) {
        this.storageSource = str;
        this.paramsMap.put("storageSource", str);
    }

    public void setStuckCnt(int i2) {
        this.stuckCnt = i2;
        this.paramsMap.put("stuckCnt", "" + i2);
    }

    public void setStuckTime(long j2) {
        this.stuckTime = j2;
        this.paramsMap.put("stuckTime", "" + j2);
    }

    public void setVideoDuration(long j2) {
        this.videoDuration = j2;
        this.paramsMap.put(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, "" + j2);
    }

    public void setVideoType(int i2) {
        this.videoType = i2;
        this.paramsMap.put("videoType", "" + i2);
    }

    private VideoInfoEntity() {
        this.videoDuration = -1L;
        this.playDuration = -1L;
        this.status = 2;
        this.stuckCnt = 0;
        this.stuckTime = 0L;
        this.isError = 0;
        this.firstPlayTime = -1L;
        this.initTime = -1L;
        this.videoType = 1;
        this.paramsMap = new HashMap<>();
    }

    public static VideoInfoEntity create(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @NonNull String str9, @NonNull String str10) {
        VideoInfoEntity videoInfoEntity = new VideoInfoEntity(str, str2, str3, str4, str5, str6, str7, str8, str9, str10);
        Map<String, String> paramFromUrl = getParamFromUrl(str3);
        String str11 = paramFromUrl.get("dockingId");
        if (str11 == null) {
            str11 = "-1";
        }
        videoInfoEntity.setDockingId(str11);
        String str12 = paramFromUrl.get("storageSource");
        if (str12 == null) {
            str12 = "3";
        }
        videoInfoEntity.setStorageSource(str12);
        return videoInfoEntity;
    }

    private VideoInfoEntity(String str, String str2, String str3, String str4, String str5, String str6) {
        this.videoDuration = -1L;
        this.playDuration = -1L;
        this.status = 2;
        this.stuckCnt = 0;
        this.stuckTime = 0L;
        this.isError = 0;
        this.firstPlayTime = -1L;
        this.initTime = -1L;
        this.videoType = 1;
        this.paramsMap = new HashMap<>();
        setRoomNumber(str);
        setPlayType(str2);
        setSource(str3);
        if (str4 != null) {
            setSku(str4);
        }
        setTypeId(str5);
        setChId(str6);
        setVideoType(1);
    }

    private VideoInfoEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.videoDuration = -1L;
        this.playDuration = -1L;
        this.status = 2;
        this.stuckCnt = 0;
        this.stuckTime = 0L;
        this.isError = 0;
        this.firstPlayTime = -1L;
        this.initTime = -1L;
        this.videoType = 1;
        this.paramsMap = new HashMap<>();
        setRoomNumber(str);
        setPlayType(str2);
        setSource(str3);
        setPageId(str4);
        if (str5 != null) {
            setSku(str5);
        }
        if (str6 != null) {
            setArticleId(str6);
        }
        if (str7 != null) {
            setReferPageId(str7);
        }
        if (str8 != null) {
            setProjectId(str8);
        }
        setTypeId(str9);
        setChId(str10);
        setVideoType(1);
    }
}
