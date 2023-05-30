package tv.danmaku.ijk.media.ext.report.bean;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import tv.danmaku.ijk.media.example.BuildConfig;
import tv.danmaku.ijk.media.ext.report.ReportConstant;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes11.dex */
public class PlayerCommonInfo implements Serializable {
    public static final String TYPE_ID = "14";
    public HashMap<String, String> paramsMap = new HashMap<String, String>() { // from class: tv.danmaku.ijk.media.ext.report.bean.PlayerCommonInfo.1
        {
            put("typeId", "14");
            put(ReportConstant.CommonInfo.PLAYER_VERSION, BuildConfig.SDK_VERSION);
        }
    };

    private void addParamsMap(String str, String str2) {
        this.paramsMap.put(str, str2);
    }

    public PlayerCommonInfo buildContainer(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        addParamsMap(ReportConstant.CommonInfo.STREAM_CONTAINER_TYPE, str);
        return this;
    }

    public PlayerCommonInfo buildDecodeType(String str) {
        if ("hevc".equals(str)) {
            str = "h265";
        }
        addParamsMap(ReportConstant.CommonInfo.DECODER_TYPE, str);
        return this;
    }

    public PlayerCommonInfo buildLiveId(String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        addParamsMap(ReportConstant.CommonInfo.LIVE_ID, str);
        return this;
    }

    public PlayerCommonInfo buildPlayMode(boolean z) {
        addParamsMap(ReportConstant.CommonInfo.PLAY_MODE, z ? "1" : "0");
        return this;
    }

    public PlayerCommonInfo buildPlayType(IMediaPlayer iMediaPlayer) {
        String str;
        if (iMediaPlayer != null) {
            if (iMediaPlayer instanceof IjkMediaPlayer) {
                str = "ijkplayer";
            } else if (iMediaPlayer instanceof AndroidMediaPlayer) {
                str = "mediaplayer";
            }
            addParamsMap(ReportConstant.CommonInfo.PLAYER_TYPE, str);
            return this;
        }
        str = "unknown";
        addParamsMap(ReportConstant.CommonInfo.PLAYER_TYPE, str);
        return this;
    }

    public PlayerCommonInfo buildPlayUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        addParamsMap(ReportConstant.CommonInfo.PLAY_URL, str);
        return this;
    }

    public PlayerCommonInfo buildSessionId(String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        addParamsMap("sessionId", str);
        return this;
    }

    public PlayerCommonInfo buildStreamIp(String str) {
        addParamsMap("streamIp", str);
        return this;
    }

    public PlayerCommonInfo buildPlayType(String str) {
        addParamsMap(ReportConstant.CommonInfo.PLAY_TYPE, str);
        return this;
    }
}
