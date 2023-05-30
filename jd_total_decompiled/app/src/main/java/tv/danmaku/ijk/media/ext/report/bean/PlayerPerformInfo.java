package tv.danmaku.ijk.media.ext.report.bean;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class PlayerPerformInfo implements Serializable {
    private final String liveId;

    public PlayerPerformInfo(String str) {
        this.liveId = str;
    }

    public String getLiveId() {
        return this.liveId;
    }
}
