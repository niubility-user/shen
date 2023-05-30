package com.jingdong.common.unification.video.mta;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdma.JDMaInterface;
import com.jingdong.jdma.minterface.ClickInterfaceParam;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import com.tencent.connect.share.QzonePublish;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.ext.report.ReportConstant;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes6.dex */
public class NewVideoPlayMtaUtil {
    private static MaInitCommonInfo maInitCommonInfo;

    /* loaded from: classes6.dex */
    public static class SpeedUtil {
        private long avgSpeed;
        private long maxSpeed = -1;
        private long minSpeed = Long.MAX_VALUE;
        private int speedCount;
        private long totalSpeed;

        public long getAvgSpeed() {
            return this.avgSpeed;
        }

        public long getMaxSpeed() {
            return this.maxSpeed;
        }

        public long getMinSpeed() {
            return this.minSpeed;
        }

        public void setSpeed(long j2) {
            if (j2 == 0) {
                return;
            }
            if (this.maxSpeed < j2) {
                this.maxSpeed = j2;
            }
            if (this.minSpeed > j2) {
                this.minSpeed = j2;
            }
            int i2 = this.speedCount + 1;
            this.speedCount = i2;
            long j3 = this.totalSpeed + j2;
            this.totalSpeed = j3;
            this.avgSpeed = j3 / i2;
        }
    }

    private static int changeJumpFrom(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return 14;
                }
                if (i2 == 5) {
                    return 13;
                }
                if (i2 != 6) {
                    return -1;
                }
            }
            return 11;
        }
        return 12;
    }

    private static String createSessionId(INewVideoMtaInfo iNewVideoMtaInfo) {
        try {
            return PlayerToolsUtil.MD5(String.valueOf(iNewVideoMtaInfo.getInitPlayerTime()).substring(0, 9));
        } catch (Exception unused) {
            return PlayerToolsUtil.MD5(String.valueOf(System.currentTimeMillis()).substring(0, 9));
        }
    }

    private static MaInitCommonInfo getMaInitCommonInfo() {
        MaInitCommonInfo maInitCommonInfo2 = maInitCommonInfo;
        if (maInitCommonInfo2 != null) {
            return maInitCommonInfo2;
        }
        try {
            Class<?> cls = Class.forName("com.jingdong.jdsdk.mta.JDMtaUtils");
            Field declaredField = cls.getDeclaredField("maInitCommonInfo");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(cls);
            if (obj instanceof MaInitCommonInfo) {
                maInitCommonInfo = (MaInitCommonInfo) obj;
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
        }
        return maInitCommonInfo;
    }

    private static HashMap<String, String> getMtaParam(INewVideoMtaInfo iNewVideoMtaInfo, int i2) {
        int i3;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("typeId", "4");
        hashMap.put("chId", "1");
        hashMap.put("roomNumber", iNewVideoMtaInfo.getVideoId());
        if (!TextUtils.isEmpty(iNewVideoMtaInfo.getSku())) {
            hashMap.put("sku", iNewVideoMtaInfo.getSku());
        }
        hashMap.put(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, (iNewVideoMtaInfo.getVideoDuration() / 1000) + "");
        if (iNewVideoMtaInfo.getPlayPosition() == 0) {
            hashMap.put("playDuration", "-1");
        } else {
            hashMap.put("playDuration", (iNewVideoMtaInfo.getPlayPosition() / 1000) + "");
        }
        if (iNewVideoMtaInfo.getPlayPosition() == 0) {
            i3 = 2;
        } else {
            i3 = iNewVideoMtaInfo.getVideoDuration() == iNewVideoMtaInfo.getPlayPosition() ? 0 : 1;
        }
        hashMap.put("status", i3 + "");
        hashMap.put("stuckCnt", iNewVideoMtaInfo.getStuckCnt() + "");
        if (iNewVideoMtaInfo.getStuckCnt() > 0) {
            hashMap.put("stuckTime", iNewVideoMtaInfo.getCatonTotalTime() + "");
        }
        hashMap.put("isError", iNewVideoMtaInfo.getIsError() + "");
        if (iNewVideoMtaInfo.getIsError() == 1) {
            hashMap.put("errorCode", iNewVideoMtaInfo.getErrorCode() + "");
            hashMap.put("errMsg", iNewVideoMtaInfo.getErrMsg());
        }
        if (!TextUtils.isEmpty(iNewVideoMtaInfo.getMark())) {
            hashMap.put("mark", iNewVideoMtaInfo.getMark());
        }
        hashMap.put(ReportConstant.CommonInfo.PLAY_TYPE, i2 + "");
        hashMap.put("occurTime", PlayerStringUtils.formatTime(System.currentTimeMillis()));
        Map paramFromUrl = getParamFromUrl(iNewVideoMtaInfo.getVideoUrl());
        if (paramFromUrl.containsKey("dockingId")) {
            hashMap.put("dockingId", (String) paramFromUrl.get("dockingId"));
        }
        if (paramFromUrl.containsKey("storageSource")) {
            hashMap.put("storageSource", (String) paramFromUrl.get("storageSource"));
        }
        hashMap.put("firstPlayTime", iNewVideoMtaInfo.getFirstPlayTime() + "");
        hashMap.put("initTime", iNewVideoMtaInfo.getInitTime() + "");
        hashMap.put("source", iNewVideoMtaInfo.getVideoUrl());
        hashMap.put("videoType", iNewVideoMtaInfo.getVideoType() + "");
        if (iNewVideoMtaInfo.getMaxSpeed() > -1) {
            hashMap.put("maxSpeed", iNewVideoMtaInfo.getMaxSpeed() + "");
        }
        if (iNewVideoMtaInfo.getMinSpeed() > -1) {
            hashMap.put("minSpeed", iNewVideoMtaInfo.getMinSpeed() + "");
        }
        if (iNewVideoMtaInfo.getAvgSpeed() > -1) {
            hashMap.put("avgSpeed", iNewVideoMtaInfo.getAvgSpeed() + "");
        }
        if (iNewVideoMtaInfo.getLostFramesCnt() > 0) {
            hashMap.put("lostFramesCnt", iNewVideoMtaInfo.getLostFramesCnt() + "");
        }
        if (!TextUtils.isEmpty(iNewVideoMtaInfo.getLostFramesInfo())) {
            hashMap.put("lostFramesInfo", iNewVideoMtaInfo.getLostFramesInfo());
        }
        hashMap.put("sessionId", createSessionId(iNewVideoMtaInfo));
        return hashMap;
    }

    private static Map getParamFromUrl(String str) {
        String[] split;
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str) && str.contains("?") && (split = str.substring(str.indexOf("?") + 1, str.length()).split(ContainerUtils.FIELD_DELIMITER)) != null && split.length > 0) {
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2)) {
                    String[] split2 = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                    if (split2.length >= 2) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            }
        }
        return hashMap;
    }

    public static void reportMtaClickEvent(Context context, JDJSONObject jDJSONObject) {
        if (context == null) {
            return;
        }
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        clickInterfaceParam.event_id = PlayerReportInfoEntity.EVENT_ID;
        clickInterfaceParam.page_id = PlayerReportInfoEntity.PAGE_ID;
        clickInterfaceParam.page_name = "JDVideoView";
        clickInterfaceParam.jsonParam = jDJSONObject.toString();
        JDMaInterface.sendClickData(context, getMaInitCommonInfo(), clickInterfaceParam);
    }

    public static void setCommonInfo(MaInitCommonInfo maInitCommonInfo2) {
        maInitCommonInfo = maInitCommonInfo2;
    }

    public static void upLoadMtaInfo(Context context, INewVideoMtaInfo iNewVideoMtaInfo) {
        int changeJumpFrom;
        if (context == null || iNewVideoMtaInfo == null || (changeJumpFrom = changeJumpFrom(iNewVideoMtaInfo.getJumpFrom())) == -1) {
            return;
        }
        JDPlayerSdk.getPlayerReport().onPerfReport(new WeakReference<>(context), "4", "1", getMtaParam(iNewVideoMtaInfo, changeJumpFrom));
    }
}
