package tv.danmaku.ijk.media.example.widget.media;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.getkeepsafe.relinker.c;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.List;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkLibLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class MediaPlayerHelper {
    public static boolean isLoadJniOk;

    private static void applyCommonConfig(IjkMediaPlayer ijkMediaPlayer, IPlayerControl.PlayerOptions playerOptions) {
        IjkMediaPlayer.native_setLogLevel(JDPlayerSdk.debugEnable ? 4 : 6);
        ijkMediaPlayer.setOption(4, "mediacodec", playerOptions.isCouldMediaCodec ? 1L : 0L);
        ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", playerOptions.isCouldMediaCodec ? 1L : 0L);
        ijkMediaPlayer.setOption(4, "mediacodec-hevc", playerOptions.isCouldMediaCodec ? 1L : 0L);
        ijkMediaPlayer.setOption(4, "overlay-format", 842225234L);
        ijkMediaPlayer.setOption(4, "framedrop", 12L);
        ijkMediaPlayer.setOption(4, "start-on-prepared", 0L);
        ijkMediaPlayer.setOption(4, "packet-buffering", 1L);
        ijkMediaPlayer.setOption(4, "first-high-water-mark-ms", 100L);
        ijkMediaPlayer.setOption(4, "first-high-water-mark-ms", 100L);
        ijkMediaPlayer.setOption(1, "dns_cache_clear", 1L);
        ijkMediaPlayer.setOption(4, "next-high-water-mark-ms", 100L);
        ijkMediaPlayer.setOption(1, "http-detect-range-support", 0L);
        ijkMediaPlayer.setOption(2, "skip_loop_filter", 48L);
        int i2 = playerOptions.playContent;
        if (i2 == 1) {
            ijkMediaPlayer.setOption(4, "vn", 1L);
        } else if (i2 == 2) {
            ijkMediaPlayer.setOption(4, "an", 1L);
        }
        int i3 = playerOptions.probesize;
        if (i3 > 0) {
            ijkMediaPlayer.setOption(1, "probesize", i3);
        }
        ijkMediaPlayer.setOption(1, SwitchQueryFetcher.VIDEO_IPV6_SWITCH, playerOptions.isIpv6VideoPlay ? 1L : 0L);
        ijkMediaPlayer.setOption(1, "is_limit_probesize", playerOptions.isLimitProbesize ? 1L : 0L);
    }

    private static void applyCustomConfig(IjkMediaPlayer ijkMediaPlayer, IPlayerControl.PlayerOptions playerOptions) {
        List<IPlayerControl.CustomOptionEntity> list = playerOptions.customOptionList;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < playerOptions.customOptionList.size(); i2++) {
            IPlayerControl.CustomOptionEntity customOptionEntity = playerOptions.customOptionList.get(i2);
            if (customOptionEntity.valueIsNum) {
                DebugLog.d("MediaPlayerHelper", "addOption num opt:" + customOptionEntity.optCategory + " k:" + customOptionEntity.optName + " v:" + customOptionEntity.numValue);
                ijkMediaPlayer.setOption(customOptionEntity.optCategory, customOptionEntity.optName, customOptionEntity.numValue);
            } else {
                DebugLog.d("MediaPlayerHelper", "addOption str opt:" + customOptionEntity.optCategory + " K:" + customOptionEntity.optName + " v:" + customOptionEntity.strValue);
                ijkMediaPlayer.setOption(customOptionEntity.optCategory, customOptionEntity.optName, customOptionEntity.strValue);
            }
        }
    }

    private static void applyLasConfig(IjkMediaPlayer ijkMediaPlayer, IPlayerControl.PlayerOptions playerOptions) {
        if (!playerOptions.isLive || TextUtils.isEmpty(playerOptions.lasMPD)) {
            return;
        }
        ijkMediaPlayer.setOption(4, "iformat", "ijklas");
        ijkMediaPlayer.setOption(4, "find_stream_info", 0L);
        ijkMediaPlayer.setOption(1, "manifest_string", playerOptions.lasMPD);
        ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 0L);
        ijkMediaPlayer.setOption(1, "las_switch_mode", -1L);
        ijkMediaPlayer.setOption(1, "las-report", 1L);
        if (IjkMediaPlayer.hasLoadCronet) {
            ijkMediaPlayer.setOption(1, "service_port", JDPlayerConstant.LSQUIC_HOST + ":" + JDPlayerConstant.LSQUIC_PORT);
            ijkMediaPlayer.setOption(1, "las_use_quic", 0L);
        }
    }

    private static void applyLiveConfig(IjkMediaPlayer ijkMediaPlayer, IPlayerControl.PlayerOptions playerOptions) {
        if (playerOptions.isLive) {
            ijkMediaPlayer.setOption(4, "drop-frame-mode", playerOptions.liveDropMode);
            ijkMediaPlayer.setOption(1, "rtmp_live", 1L);
            ijkMediaPlayer.setOption(1, "rtmp_buffer", 1000L);
            ijkMediaPlayer.setOption(1, "timeout", 10000000L);
            int i2 = playerOptions.reconnectCount;
            if (i2 > 0) {
                ijkMediaPlayer.setOption(1, "max-reconnect-times", i2);
                ijkMediaPlayer.setOption(1, "reconnect", 1L);
            }
            ijkMediaPlayer.setOption(2, "threads", "1");
            ijkMediaPlayer.setOption(4, "infbuf", 0L);
            ijkMediaPlayer.setOption(4, "last-high-water-mark-ms", 500L);
            ijkMediaPlayer.setOption(4, "max-live-delay", playerOptions.maxLiveDelay);
            ijkMediaPlayer.setOption(4, "min-live-duration", playerOptions.minLiveDuration);
            ijkMediaPlayer.setOption(4, "drop-format-mode", 1L);
            ijkMediaPlayer.setOption(4, "drop-format-open-start-time", 1000L);
            ijkMediaPlayer.setOption(4, "drop-format-space-time", 1500L);
            ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1L);
            if (IjkMediaPlayer.hasLoadCronet) {
                ijkMediaPlayer.setOption(1, "service_port", JDPlayerConstant.LSQUIC_HOST + ":" + JDPlayerConstant.LSQUIC_PORT);
            }
            ijkMediaPlayer.setOption(4, "delay-report", 0L);
        }
    }

    private static void applyVodConfig(IjkMediaPlayer ijkMediaPlayer, IPlayerControl.PlayerOptions playerOptions) {
        if (playerOptions.isLive) {
            return;
        }
        ijkMediaPlayer.setOption(4, "drop-frame-mode", 0L);
        ijkMediaPlayer.setOption(4, "infbuf", 0L);
        ijkMediaPlayer.setOption(4, "max-buffer-size", 524288L);
        ijkMediaPlayer.setOption(1, "timeout", 15000000L);
        if (playerOptions.reconnectCount > 0) {
            ijkMediaPlayer.setOption(1, "reconnect", 1L);
            int i2 = 0;
            for (int i3 = playerOptions.reconnectCount - 1; i3 > 0; i3--) {
                double d = i2;
                double pow = Math.pow(2.0d, i3);
                Double.isNaN(d);
                i2 = (int) (d + pow);
            }
            ijkMediaPlayer.setOption(1, "reconnect_delay_max", i2);
        }
        ijkMediaPlayer.setOption(4, "last-high-water-mark-ms", 500L);
        ijkMediaPlayer.setOption(4, "max-live-delay", 0L);
        ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", playerOptions.isHWAccelAutoCompatResolution() ? 1L : 0L);
        ijkMediaPlayer.setOption(4, "soundtouch", 0L);
        if (playerOptions.enableAccurateSeek) {
            ijkMediaPlayer.setOption(4, "enable-accurate-seek", 1L);
        }
    }

    public static IMediaPlayer createPlayer(IPlayerControl.PlayerOptions playerOptions) {
        if (isLoadJniOk && !playerOptions.isForceAndroidPlayer) {
            IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
            setPlayerAVOptions(ijkMediaPlayer, playerOptions);
            return playerOptions.isUseTextureView ? new TextureMediaPlayer(ijkMediaPlayer) : ijkMediaPlayer;
        }
        DebugLog.e("MediaPlayerHelper", "ijk so load fail, create android media player");
        return new AndroidMediaPlayer();
    }

    public static void loadLibrariesOnce(final Context context) {
        IjkLibLoader ijkLibLoader;
        if (context != null) {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            ijkLibLoader = new IjkLibLoader() { // from class: tv.danmaku.ijk.media.example.widget.media.MediaPlayerHelper.1
                @Override // tv.danmaku.ijk.media.player.IjkLibLoader
                public void loadLibrary(String str) {
                    c.a(context, str);
                }
            };
        } else {
            ijkLibLoader = null;
        }
        loadLibrariesOnce(ijkLibLoader);
    }

    public static void loadLibrariesOnceSafe(Context context) {
        try {
            loadLibrariesOnce(context);
            isLoadJniOk = true;
        } catch (UnsatisfiedLinkError e2) {
            isLoadJniOk = false;
            e2.printStackTrace();
        }
    }

    protected static void setPlayerAVOptions(IjkMediaPlayer ijkMediaPlayer, IPlayerControl.PlayerOptions playerOptions) {
        applyCommonConfig(ijkMediaPlayer, playerOptions);
        applyLiveConfig(ijkMediaPlayer, playerOptions);
        applyLasConfig(ijkMediaPlayer, playerOptions);
        applyVodConfig(ijkMediaPlayer, playerOptions);
        applyCustomConfig(ijkMediaPlayer, playerOptions);
    }

    public static void loadLibrariesOnce(IjkLibLoader ijkLibLoader) {
        IjkMediaPlayer.loadLibrariesOnce(ijkLibLoader);
        isLoadJniOk = true;
    }
}
