package tv.danmaku.ijk.media.ext.report.bean;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import tv.danmaku.ijk.media.ext.report.ReportConstant;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes11.dex */
public class PlayStatusReportInfo extends BasePlayerReportInfo {
    private boolean enableMediaCodec;
    private long playEndTime;
    private long playStartTime;
    private long totalPauseTime;
    private final HashMap<String, String> newStuckInfo = new HashMap<>();
    private final HashMap<String, String> playTimeInfo = new HashMap<>();
    private final StringBuilder stuckInfoStr = new StringBuilder();
    private final StringBuilder pauseInfoStr = new StringBuilder();
    private final StringBuilder errorStr = new StringBuilder();

    public void appendLasPlayInfo(String str, long j2, long j3) {
        this.playTimeInfo.put(str, (this.playTimeInfo.get(str) != null ? this.playTimeInfo.get(str) : "") + j2 + "#" + j3 + CartConstant.KEY_YB_INFO_LINK);
    }

    public void appendLasStuckInfo(String str, long j2, long j3, int i2, int i3) {
        String str2 = this.newStuckInfo.get(str) != null ? this.newStuckInfo.get(str) : "";
        if (str2 == null || str2.getBytes().length / 1024 <= 10) {
            this.newStuckInfo.put(str, str2 + j2 + "#" + j3 + "#" + i2 + "#" + i3 + CartConstant.KEY_YB_INFO_LINK);
        }
    }

    public void appendPauseInfo(long j2, long j3, int i2) {
        if (j2 == j3) {
            return;
        }
        this.totalPauseTime += j3 - j2;
        StringBuilder sb = this.pauseInfoStr;
        sb.append(j2);
        sb.append("#");
        sb.append(j3);
        sb.append("#");
        sb.append(i2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
    }

    public void appendStuckInfo(long j2, long j3, int i2, int i3) {
        if (this.stuckInfoStr.toString().getBytes().length / 1024 > 10) {
            return;
        }
        StringBuilder sb = this.stuckInfoStr;
        sb.append(j2);
        sb.append("#");
        sb.append(j3);
        sb.append("#");
        sb.append(i2);
        sb.append("#");
        sb.append(i3);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
    }

    public void buildMediaInfo(IjkMediaMeta ijkMediaMeta) {
        if (ijkMediaMeta == null) {
            return;
        }
        IjkMediaMeta.IjkStreamMeta ijkStreamMeta = ijkMediaMeta.mAudioStream;
        if (ijkStreamMeta != null) {
            this.paramsMap.put(ReportConstant.PlayStatus.MediaInfo.AUDIO_CODEC_NAME, ijkStreamMeta.getCodecShortNameInline());
            this.paramsMap.put(ReportConstant.PlayStatus.MediaInfo.AUDIO_CONTAINER, ijkMediaMeta.mAudioStream.getCodecShortNameInline());
            this.paramsMap.put(ReportConstant.PlayStatus.MediaInfo.AUDIO_SAMPLE_RATE, String.valueOf(ijkMediaMeta.mAudioStream.mSampleRate));
            this.paramsMap.put(ReportConstant.PlayStatus.MediaInfo.AUDIO_BIT_RATE, String.valueOf(ijkMediaMeta.mAudioStream.mBitrate));
            this.paramsMap.put(ReportConstant.PlayStatus.MediaInfo.AUDIO_TRACK, ijkMediaMeta.mAudioStream.getChannelLayoutInline());
        }
        IjkMediaMeta.IjkStreamMeta ijkStreamMeta2 = ijkMediaMeta.mVideoStream;
        if (ijkStreamMeta2 != null) {
            this.paramsMap.put(ReportConstant.PlayStatus.MediaInfo.VIDEO_CODEC_NAME, ijkStreamMeta2.getCodecShortNameInline());
        }
    }

    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public void clean() {
        this.newStuckInfo.clear();
        this.playTimeInfo.clear();
        this.pauseInfoStr.setLength(0);
        this.stuckInfoStr.setLength(0);
        this.errorStr.setLength(0);
        this.totalPauseTime = 0L;
        this.playStartTime = 0L;
        this.playEndTime = 0L;
        this.enableMediaCodec = false;
    }

    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public HashMap<String, String> generateReportMap() {
        super.generateReportMap();
        if (this.playStartTime == 0) {
            long j2 = this.playEndTime;
            this.playStartTime = j2;
            setPlayStartTime(j2);
            this.paramsMap.put(ReportConstant.CommonInfo.DECODER_TYPE, "unknown");
        }
        this.paramsMap.put("endTime", String.valueOf(this.playEndTime));
        long j3 = (this.playEndTime - this.playStartTime) - this.totalPauseTime;
        long j4 = j3 >= 0 ? j3 : 0L;
        this.paramsMap.put("duration", String.valueOf(j4 / 1000));
        this.paramsMap.put(ReportConstant.PlayStatus.DURATION_MILL, String.valueOf(j4));
        this.paramsMap.put(ReportConstant.PlayStatus.LAS_STUCK_INFO, JDJSON.toJSONString(this.newStuckInfo));
        this.paramsMap.put(ReportConstant.PlayStatus.LAS_PLAYTIME_INFO, JDJSON.toJSONString(this.playTimeInfo));
        StringBuilder sb = new StringBuilder();
        sb.append("{\"stuckInfo\":\"");
        if (this.stuckInfoStr.length() > 0) {
            sb.append(this.stuckInfoStr.substring(0, r1.length() - 1));
        }
        sb.append("\",\"pauseInfo\":\"");
        if (this.pauseInfoStr.length() > 0) {
            sb.append(this.pauseInfoStr.substring(0, r1.length() - 1));
        }
        sb.append("\"}");
        this.paramsMap.put(ReportConstant.PlayStatus.STUCK_INFO, sb.toString());
        if (TextUtils.isEmpty(this.errorStr)) {
            this.errorStr.append("{\"errorCode\":\"0\",\"errorMsg\":\"\"}");
        }
        this.paramsMap.put("errCode", this.errorStr.toString());
        return this.paramsMap;
    }

    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public String getChId() {
        return "2";
    }

    public long getPlayStartTime() {
        return this.playStartTime;
    }

    public void setByteCount(long j2) {
        this.paramsMap.put("byteCount", String.valueOf(j2));
    }

    public void setEndTime(long j2) {
        this.playEndTime = j2;
    }

    public void setErrCode(int i2, String str) {
        StringBuilder sb = this.errorStr;
        sb.append("{\"errorCode\":\"");
        sb.append(i2);
        sb.append("\",\"errorMsg\":\"");
        sb.append(str);
        sb.append("\"}");
    }

    public void setFinalDecoder(int i2) {
        int i3 = 2;
        if (i2 != 1) {
            i3 = i2 != 2 ? -1 : 0;
        } else if (this.enableMediaCodec) {
            i3 = 1;
        }
        this.paramsMap.put(ReportConstant.PlayStatus.DECODER_MODE, String.valueOf(i3));
    }

    public void setLasSwitchCount(long j2) {
        this.paramsMap.put(ReportConstant.PlayStatus.LAS_SWITCH_COUNT, String.valueOf(j2));
    }

    public void setLiveDropMode(int i2) {
        this.paramsMap.put(ReportConstant.PlayStatus.LIVE_DROP_MODE, String.valueOf(i2));
    }

    public void setOptionDecoder(boolean z) {
        this.enableMediaCodec = z;
    }

    public void setOriSize(String str) {
        this.paramsMap.put(ReportConstant.PlayStatus.VIDEO_ORIGIN_SIZE, str);
    }

    public void setPlayStartTime(long j2) {
        this.playStartTime = j2;
        this.paramsMap.put("startTime", String.valueOf(j2));
    }

    public void setRenderType(boolean z) {
        int i2 = z ? 2 : 1;
        if (this.paramsMap.containsKey(ReportConstant.PlayStatus.RENDER_TYPE)) {
            return;
        }
        this.paramsMap.put(ReportConstant.PlayStatus.RENDER_TYPE, String.valueOf(i2));
    }
}
