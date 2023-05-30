package com.jingdong.common.widget.video;

import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.jingdong.sdk.oklog.OKLog;

@Deprecated
/* loaded from: classes12.dex */
public class TotalReporterManager {
    private String articleId;
    private String playType;
    private long urlRequestCast;
    private long videoLength;
    private String videoUrl;
    private long loadCost = 0;
    private long startTime = -1;
    private long endTime = -1;

    /* loaded from: classes12.dex */
    public static class VBReportPlayInfoEntity {
        public String endTime;
        public String enterTime;
        public String roomNumber;
        public String source;
        public String startTime;
        public String videoLength;
        public String sdkType = "jdtv";
        public String playType = "3";
        public String videoType = "1";
    }

    public TotalReporterManager(String str, String str2, String str3, long j2) {
        this.urlRequestCast = 0L;
        this.articleId = str2;
        this.videoUrl = str;
        this.urlRequestCast = j2;
        this.playType = str3;
        if (OKLog.D) {
            OKLog.d("ReportSpeedUtil", "906 urlRequestCast : " + j2);
        }
    }

    public String getArticleId() {
        return this.articleId;
    }

    public void playerPrepared(long j2) {
        if (this.startTime == -1) {
            this.startTime = System.currentTimeMillis();
        }
        this.loadCost = j2;
        if (OKLog.D) {
            OKLog.d("ReportSpeedUtil", "906 playerPrepared : " + j2);
        }
    }

    public void reportPlayInfo(long j2) {
        long j3 = (this.startTime - this.urlRequestCast) - this.loadCost;
        VBReportPlayInfoEntity vBReportPlayInfoEntity = new VBReportPlayInfoEntity();
        vBReportPlayInfoEntity.roomNumber = this.articleId;
        vBReportPlayInfoEntity.source = this.videoUrl;
        vBReportPlayInfoEntity.playType = this.playType;
        vBReportPlayInfoEntity.videoLength = "" + this.videoLength;
        this.endTime = this.startTime + j2;
        vBReportPlayInfoEntity.enterTime = ExceptionReporter.formatMillis(j3);
        vBReportPlayInfoEntity.startTime = ExceptionReporter.formatMillis(this.startTime);
        vBReportPlayInfoEntity.endTime = ExceptionReporter.formatMillis(this.endTime);
        String jSONString = JDJSON.toJSONString(vBReportPlayInfoEntity);
        boolean z = OKLog.D;
        if (z) {
            if (z) {
                OKLog.d("ReportSpeedUtil", "906 vbReportPlayInfoEntity : " + jSONString);
            }
            if (OKLog.D) {
                OKLog.d("ReportSpeedUtil", "906 playDuration : " + j2 + " urlRequestCast:" + this.urlRequestCast + " loadCost:" + this.loadCost);
            }
        }
        ExceptionReporter.reportLive(JDNetworkConstant.LIVE_STUCK_ERRCODE, jSONString, "1");
    }

    public void setVideoLength(long j2) {
        this.videoLength = j2;
    }
}
