package com.jingdong.common.widget.custom.livewidget.util;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.gson.Gson;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.widget.custom.livewidget.bean.VideoPerfEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes12.dex */
public class PerfReportUtil {
    public static final String BUSINESS_TYPE_RECOMMEND = "recommend";
    public static final String CH_ID_FRAME_LOAD = "1";
    public static final String CH_ID_PULL_FAILED = "3";
    public static final String CH_ID_STUCK = "2";
    public static final String JUMP_LIVE_ROOM_SOURCE = "1";
    public static final String JUMP_LIVE_ROOM_SOURCE2 = "2";
    public static final String TYPE_ID = "13";
    private String groupId;
    private long initTime;
    private long lastTime;
    private String liveIp;
    private String mRoomId;
    private String mSourceType;
    private boolean mHasReport = false;
    private boolean mReadyToReport = false;
    private VideoPerfEntity frameLoadEntity = new VideoPerfEntity();
    private HashSet<String> lotteryIds = new HashSet<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMddHHmm");
    private long mVideoStartTime = 0;
    private long mVideoStuckStartTime = 0;
    private ArrayMap<String, StuckInfo> mStuckTimes = new ArrayMap<>();
    private ArrayMap<String, StuckInfo> mVideoTimes = new ArrayMap<>();
    private final long MINUTS_VALUE = 60000;
    private boolean isVideoStart = false;
    private int mLiveStatus = -100;
    private long firstStuckStartTime = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class StuckInfo {
        int times;
        long totalDuration;

        private StuckInfo() {
            this.times = 0;
            this.totalDuration = 0L;
        }
    }

    private void addStuckMidTime(long j2, long j3, String str) {
        if (j2 < j3) {
            String format = this.dateFormat.format(new Date(j2));
            if (format.equals(str)) {
                return;
            }
            addStuckReport(format, 0L);
            addStuckMidTime(j2 + 60000, j3, str);
        }
    }

    private void addStuckReport(String str, long j2) {
        StuckInfo stuckInfo = this.mStuckTimes.get(str);
        if (stuckInfo == null) {
            StuckInfo stuckInfo2 = new StuckInfo();
            stuckInfo2.times = 1;
            stuckInfo2.totalDuration += j2;
            this.mStuckTimes.put(str, stuckInfo2);
            return;
        }
        stuckInfo.times++;
        stuckInfo.totalDuration += j2;
        this.mStuckTimes.put(str, stuckInfo);
    }

    private void addVideoMidTime(long j2, long j3, String str) {
        if (j2 < j3) {
            String format = this.dateFormat.format(new Date(j2));
            if (format.equals(str)) {
                return;
            }
            addVideoReport(format);
            addVideoMidTime(j2 + 60000, j3, str);
        }
    }

    private void addVideoReport(String str) {
        StuckInfo stuckInfo = this.mStuckTimes.get(str);
        if (stuckInfo == null) {
            this.mVideoTimes.put(str, new StuckInfo());
            return;
        }
        this.mVideoTimes.put(str, stuckInfo);
    }

    private void addVideoeDataWithExt(long j2, long j3) {
        VideoPerfEntity videoPerfEntity = new VideoPerfEntity();
        videoPerfEntity.put("typeId", "13");
        videoPerfEntity.put("chId", "2");
        videoPerfEntity.put("businessType", "recommend");
        videoPerfEntity.put("occurTime", getOccurTime());
        videoPerfEntity.put("startTime", String.valueOf(j2));
        videoPerfEntity.put("endTime", String.valueOf(j3));
        videoPerfEntity.put(VideoPerfEntity.FIELD_ROOM_ID, TextUtils.isEmpty(this.groupId) ? ReporterManager.getInstance().getRoomNumber() : this.groupId);
        videoPerfEntity.put("status", this.mLiveStatus);
        videoPerfEntity.put("streamIp", TextUtils.isEmpty(this.liveIp) ? "-100" : this.liveIp);
        double d = j3 - j2;
        Double.isNaN(d);
        videoPerfEntity.put("duration", Math.round(d / 1000.0d) + "");
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, StuckInfo> entry : this.mVideoTimes.entrySet()) {
            StuckInfo value = entry.getValue();
            if (value != null) {
                if (z) {
                    sb.append(entry.getKey());
                    sb.append("#");
                    sb.append(value.times);
                    sb.append("#");
                    sb.append(value.totalDuration);
                    z = false;
                } else {
                    sb.append(CartConstant.KEY_YB_INFO_LINK);
                    sb.append(entry.getKey());
                    sb.append("#");
                    sb.append(value.times);
                    sb.append("#");
                    sb.append(value.totalDuration);
                }
            }
        }
        videoPerfEntity.put(VideoPerfEntity.FIELD_PRV, sb.toString());
        boolean isNeedReport = PerformanceReporter.getIsNeedReport(JdSdk.getInstance().getApplicationContext(), "13", "2");
        if (Log.D) {
            Log.e("Arthur", "Stuck: isNeededReport = " + isNeedReport);
        }
        if (isNeedReport) {
            PerformanceReporter.reportData(videoPerfEntity.getReportData());
            this.mStuckTimes.clear();
            this.mVideoTimes.clear();
            this.mVideoStartTime = 0L;
            this.mVideoStuckStartTime = 0L;
            this.mLiveStatus = -100;
        }
    }

    private static String formatMicroSecond(BigDecimal bigDecimal) {
        return new DecimalFormat("#########0.000000").format(bigDecimal);
    }

    private static String getOccurTime() {
        return formatMicroSecond(new BigDecimal(System.currentTimeMillis()).divide(BigDecimal.valueOf(1000L), 6, 4));
    }

    public boolean addLotteryId(String str) {
        if (this.lotteryIds.contains(str)) {
            return false;
        }
        this.lotteryIds.add(str);
        return true;
    }

    public void clearLotteryId() {
        HashSet<String> hashSet = this.lotteryIds;
        if (hashSet != null) {
            hashSet.clear();
        }
    }

    public void reportPullDataError(String str) {
        VideoPerfEntity videoPerfEntity = new VideoPerfEntity();
        videoPerfEntity.put("typeId", "13");
        videoPerfEntity.put("chId", "3");
        videoPerfEntity.put("businessType", "recommend");
        videoPerfEntity.put("occurTime", getOccurTime());
        videoPerfEntity.put(VideoPerfEntity.FIELD_ROOM_ID, TextUtils.isEmpty(this.mRoomId) ? "-100" : this.mRoomId);
        if (TextUtils.isEmpty(str)) {
            str = "-100";
        }
        videoPerfEntity.put("errCode", str);
        videoPerfEntity.put("streamIp", TextUtils.isEmpty(this.liveIp) ? "-100" : this.liveIp);
        boolean isNeedReport = PerformanceReporter.getIsNeedReport(JdSdk.getInstance().getApplicationContext(), "13", "3");
        if (Log.D) {
            Log.e("Arthur", "Pull error: isNeededReport = " + isNeedReport);
        }
        if (isNeedReport) {
            if (Log.D) {
                Log.e("Arthur", "Pull error: " + new Gson().toJson(videoPerfEntity.getReportData()));
            }
            PerformanceReporter.reportData(videoPerfEntity.getReportData());
        }
    }

    public void reset() {
        this.mHasReport = false;
        this.mReadyToReport = false;
    }

    public void setEndStuckTime() {
        if (this.isVideoStart) {
            long j2 = this.mVideoStuckStartTime;
            long currentTimeMillis = System.currentTimeMillis();
            if (this.firstStuckStartTime + 15000 < currentTimeMillis) {
                long j3 = this.mVideoStuckStartTime;
                if (currentTimeMillis >= j3 && j3 != 0) {
                    String format = this.dateFormat.format(new Date(j2));
                    String format2 = this.dateFormat.format(new Date(currentTimeMillis));
                    if (format.equals(format2)) {
                        addStuckReport(format, 0L);
                    } else {
                        addStuckReport(format, 0L);
                        addStuckReport(format2, Math.abs(currentTimeMillis - j2));
                        addStuckMidTime(j2 + 60000, currentTimeMillis, format2);
                    }
                }
            }
            this.mVideoStuckStartTime = 0L;
        }
    }

    public void setLiveUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String host = new URL(str).getHost();
            String ipByHost = ExceptionReporter.getIpByHost(host);
            this.liveIp = ipByHost;
            if (TextUtils.isEmpty(ipByHost)) {
                return;
            }
            this.liveIp = this.liveIp.replace(host, "").replace("[", "").replace("]", "").replace("/", "").replaceAll("\\\"", "").replace("\\", "");
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
    }

    public void setReadyToReport(boolean z) {
        this.mReadyToReport = z;
    }

    public void setRoomId(String str) {
        this.mRoomId = str;
    }

    public void setSourceType(String str, long j2) {
        this.mSourceType = str;
        this.initTime = j2;
        this.lastTime = j2;
        tryReportInfo();
    }

    public void setStartStuckTime() {
        if (this.isVideoStart) {
            this.mVideoStuckStartTime = System.currentTimeMillis();
        }
    }

    public void setVideoEndTime() {
        OKLog.d("PerfReportUtil", "setVideoEndTime");
        if (this.isVideoStart) {
            long j2 = this.mVideoStartTime;
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis > j2) {
                String format = this.dateFormat.format(new Date(j2));
                String format2 = this.dateFormat.format(new Date(currentTimeMillis));
                if (format.equals(format2)) {
                    addVideoReport(format);
                } else {
                    addVideoReport(format);
                    addVideoReport(format2);
                    addVideoMidTime(j2 + 60000, currentTimeMillis, format2);
                }
                addVideoeDataWithExt(j2, currentTimeMillis);
            }
        }
        this.isVideoStart = false;
    }

    public void setVideoLoadTime(int i2, long j2) {
        this.frameLoadEntity.put("param" + i2, this.initTime == 0 ? 0L : j2 - this.lastTime);
        this.lastTime = j2;
        if (i2 == 7) {
            VideoPerfEntity videoPerfEntity = this.frameLoadEntity;
            long j3 = this.initTime;
            videoPerfEntity.put("total", j3 != 0 ? j2 - j3 : 0L);
        }
    }

    public void setVideoStartTime(int i2, String str) {
        if (this.isVideoStart) {
            return;
        }
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
        this.dateFormat2.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
        this.mLiveStatus = i2;
        this.groupId = str;
        this.mVideoStartTime = System.currentTimeMillis();
        this.firstStuckStartTime = System.currentTimeMillis();
        this.isVideoStart = true;
    }

    public void tryReportInfo() {
        if (this.mHasReport || TextUtils.isEmpty(this.mSourceType) || !this.mReadyToReport) {
            return;
        }
        this.frameLoadEntity.put("typeId", "13");
        this.frameLoadEntity.put("chId", "1");
        this.frameLoadEntity.put("businessType", "recommend");
        this.frameLoadEntity.put("occurTime", getOccurTime());
        this.frameLoadEntity.put(VideoPerfEntity.FIELD_ROOM_ID, TextUtils.isEmpty(this.mRoomId) ? "-100" : this.mRoomId);
        this.frameLoadEntity.put(VideoPerfEntity.FIELD_SOURCE_TYPE, this.mSourceType);
        this.frameLoadEntity.put("streamIp", TextUtils.isEmpty(this.liveIp) ? "-100" : this.liveIp);
        if (!this.frameLoadEntity.containsKey("param2")) {
            this.frameLoadEntity.put("param2", "0");
        }
        if (!this.frameLoadEntity.containsKey("param3")) {
            this.frameLoadEntity.put("param3", "0");
        }
        if (!this.frameLoadEntity.containsKey("param4")) {
            this.frameLoadEntity.put("param4", "0");
        }
        if (!this.frameLoadEntity.containsKey("param5")) {
            this.frameLoadEntity.put("param5", "0");
        }
        if (!this.frameLoadEntity.containsKey("param6")) {
            this.frameLoadEntity.put("param6", "0");
        }
        VideoPerfEntity videoPerfEntity = this.frameLoadEntity;
        double videoSpeed = ReportSpeedHelp.getDefault().getVideoSpeed();
        Double.isNaN(videoSpeed);
        videoPerfEntity.put("speed", Math.round(videoSpeed / 1000.0d));
        if (PerformanceReporter.getIsNeedReport(JdSdk.getInstance().getApplicationContext(), "13", "1")) {
            PerformanceReporter.reportData(new HashMap(this.frameLoadEntity.getReportData()));
        }
        this.mSourceType = null;
        this.initTime = 0L;
        this.lastTime = 0L;
        this.frameLoadEntity.clear();
        this.mHasReport = true;
    }
}
