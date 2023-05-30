package tv.danmaku.ijk.media.ext.report.bean;

import com.jd.framework.json.JDJSON;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class MediaDetailReportInfo extends BasePlayerReportInfo {
    private ArrayList<MediaDynamicInfo> dynamicInfo = new ArrayList<>();

    /* loaded from: classes11.dex */
    public static class MediaDynamicInfo implements Serializable {
        private String aBufferLen;
        private String audioCbDuration;
        private String displayDuration;
        private String enhanceRez;
        private String lastDelay;
        private String originRez;
        private String speed;
        private String timeStamp;
        private String vBitRate;
        private String vBufferLen;
        private String vDelay;
        private String vFrameRate;

        public String getAudioCbDuration() {
            return this.audioCbDuration;
        }

        public String getDisplayDuration() {
            return this.displayDuration;
        }

        public String getEnhanceRez() {
            return this.enhanceRez;
        }

        public String getLastDelay() {
            return this.lastDelay;
        }

        public String getOriginRez() {
            return this.originRez;
        }

        public String getSpeed() {
            return this.speed;
        }

        public String getTimeStamp() {
            return this.timeStamp;
        }

        public String getaBufferLen() {
            return this.aBufferLen;
        }

        public String getvBitRate() {
            return this.vBitRate;
        }

        public String getvBufferLen() {
            return this.vBufferLen;
        }

        public String getvDelay() {
            return this.vDelay;
        }

        public String getvFrameRate() {
            return this.vFrameRate;
        }

        public void setAudioCbDuration(String str) {
            this.audioCbDuration = str;
        }

        public void setDisplayDuration(String str) {
            this.displayDuration = str;
        }

        public void setEnhanceRez(String str) {
            this.enhanceRez = str;
        }

        public void setLastDelay(String str) {
            this.lastDelay = str;
        }

        public void setOriginRez(String str) {
            this.originRez = str;
        }

        public void setSpeed(String str) {
            this.speed = str;
        }

        public void setTimeStamp(String str) {
            this.timeStamp = str;
        }

        public void setaBufferLen(String str) {
            this.aBufferLen = str;
        }

        public void setvBitRate(String str) {
            this.vBitRate = str;
        }

        public void setvBufferLen(String str) {
            this.vBufferLen = str;
        }

        public void setvDelay(String str) {
            this.vDelay = str;
        }

        public void setvFrameRate(String str) {
            this.vFrameRate = str;
        }
    }

    public void addDynamicInfo(MediaDynamicInfo mediaDynamicInfo) {
        ArrayList<MediaDynamicInfo> arrayList = this.dynamicInfo;
        if (arrayList == null || mediaDynamicInfo == null) {
            return;
        }
        arrayList.add(mediaDynamicInfo);
    }

    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public HashMap<String, String> generateReportMap() {
        if (getDynamicInfo() != null && getDynamicInfo().size() > 0) {
            this.paramsMap.put("dynamicInfo", JDJSON.parseArray(JDJSON.toJSONString(getDynamicInfo())).toJSONString());
        }
        return super.generateReportMap();
    }

    @Override // tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo
    public String getChId() {
        return "3";
    }

    public ArrayList<MediaDynamicInfo> getDynamicInfo() {
        return this.dynamicInfo;
    }

    public void resetDynamicInfo() {
        if (this.dynamicInfo == null) {
            this.dynamicInfo = new ArrayList<>();
        }
        this.dynamicInfo.clear();
    }
}
