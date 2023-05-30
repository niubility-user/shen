package tv.danmaku.ijk.media.ext.auth.bean;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class AuthPlayInfo implements Serializable {
    private final String playMode;
    private final String playType;
    private final String playerType;

    public AuthPlayInfo(String str, String str2, String str3) {
        this.playType = str;
        this.playerType = str2;
        this.playMode = str3;
    }

    public String getPlayMode() {
        return this.playMode;
    }

    public String getPlayType() {
        return this.playType;
    }

    public String getPlayerType() {
        return this.playerType;
    }
}
