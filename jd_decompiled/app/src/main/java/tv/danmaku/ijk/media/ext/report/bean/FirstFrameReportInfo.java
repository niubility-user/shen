package tv.danmaku.ijk.media.ext.report.bean;

import java.util.HashMap;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes11.dex */
public class FirstFrameReportInfo extends BasePlayerReportInfo {
    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public void clean() {
        super.clean();
    }

    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public HashMap<String, String> generateReportMap() {
        super.generateReportMap();
        return this.paramsMap;
    }

    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public String getChId() {
        return "1";
    }

    public void setAutoPlay(boolean z) {
        this.paramsMap.put("autoPlay", String.valueOf(z ? 1 : 0));
    }

    public void setBufferTime(long j2) {
        this.paramsMap.put(ReportConstant.FirstFrame.BUFFER_TIME, String.valueOf(j2));
    }

    public void setCacheMode(int i2) {
        this.paramsMap.put(ReportConstant.FirstFrame.CACHE_MODE, String.valueOf(i2));
    }

    public void setConnectionOpenTime(long j2) {
        this.paramsMap.put(ReportConstant.FirstFrame.CONNECTION_OPEN_TIME, String.valueOf(j2));
    }

    public void setDecodeTime(long j2) {
        if (j2 > 60000) {
            j2 = 0;
        }
        this.paramsMap.put(ReportConstant.FirstFrame.DECODE_TIME, String.valueOf(j2));
    }

    public void setDnsEndTime(long j2) {
        this.paramsMap.put(ReportConstant.FirstFrame.DNS_PARSE_TIME, String.valueOf(j2));
    }

    public void setDnsPrepareTime(long j2) {
        this.paramsMap.put(ReportConstant.FirstFrame.DNS_PREPARED_TIME, String.valueOf(j2));
    }

    public void setPreparedTime(long j2) {
        this.paramsMap.put(ReportConstant.FirstFrame.PREPARED_TIME, String.valueOf(j2));
    }

    public void setProbeStreamTime(long j2) {
        this.paramsMap.put(ReportConstant.FirstFrame.STREAM_PROBE_TIME, String.valueOf(j2));
    }

    public void setProtocolType(int i2) {
        this.paramsMap.put(ReportConstant.FirstFrame.PROTOCOL_TYPE, String.valueOf(i2));
    }

    public void setRealUseCache(boolean z) {
        this.paramsMap.put(ReportConstant.FirstFrame.REAL_USE_CACHE, String.valueOf(z));
    }

    public void setRenderTime(long j2, long j3, boolean z) {
        if (!z) {
            if (j2 < 0) {
                j2 = 0;
            }
            this.paramsMap.put(ReportConstant.FirstFrame.RENDER_TIME, String.valueOf(j2));
        }
        if (j3 <= 0) {
            j3 = 0;
        }
        this.paramsMap.put("total", String.valueOf(j3));
    }

    public void setTcpEndTime(long j2) {
        this.paramsMap.put(ReportConstant.FirstFrame.TCP_CONNECT_TIME, String.valueOf(j2));
    }

    public void setTcpSpeed(String str) {
        this.paramsMap.put("speed", str);
    }
}
