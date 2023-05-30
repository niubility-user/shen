package tv.danmaku.ijk.media.example.widget.media;

import android.net.Uri;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import androidx.core.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.alpha.IAlphaVideoView;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.ext.report.bean.PlayerPerformInfo;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public interface IPlayerControl {
    public static final int IO_ERR_RET_FORBID = -104;
    public static final int MEDIA_INFO_IO_ERR_RET = 10305;
    public static final int MEDIA_INFO_TCP_CONNECTED = 10304;

    /* loaded from: classes.dex */
    public @interface AspectRatioType {
    }

    /* loaded from: classes.dex */
    public @interface LiveDropMode {
    }

    /* loaded from: classes11.dex */
    public interface OnPlayerExtInfoListener {
        void onExtInfo(int i2, int i3, int i4, HashMap<String, Object> hashMap);
    }

    /* loaded from: classes11.dex */
    public interface OnPlayerStateExtListener extends OnPlayerStateListener {
        public static final int STATE_PREDRAW_REUSE = 6;
        public static final int STATE_PREDRAW_START = 7;

        void onPreDrawReusePlayer();

        void onPreDrawStart(long j2);
    }

    /* loaded from: classes11.dex */
    public interface OnPlayerStateListener {
        public static final int STATE_COMPLETE = 2;
        public static final int STATE_CREATE = 0;
        public static final int STATE_ERROR = 3;
        public static final int STATE_INFO = 4;
        public static final int STATE_PREPARE = 1;
        public static final int STATE_SEEK_COMPLETE = 5;

        void onCompletion();

        void onCreatePlayer();

        boolean onError(int i2, int i3);

        boolean onInfo(int i2, int i3);

        void onPrepared(long j2);

        void onSeekComplete();
    }

    /* loaded from: classes11.dex */
    public interface OnStatisticsStateListener {
        void pause();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(int i2, int i3);
    }

    /* loaded from: classes.dex */
    public @interface PlayContent {
    }

    void addSurfaceHolderCallback(SurfaceHolder.Callback callback);

    int getBufferPercentage();

    int getCurrentPosition();

    int getDuration();

    IMediaPlayer getMediaPlayer();

    String getOriginUrl();

    PlayerOptions getPlayerOptions();

    long getTcpSpeed();

    boolean isPlaying();

    void pause();

    void release();

    void resume();

    void seekTo(int i2);

    void setMediaController(IMediaController iMediaController);

    void setOnPlayerStateListener(OnPlayerStateListener onPlayerStateListener);

    void setPlayerOptions(PlayerOptions playerOptions);

    void setVideoPath(String str);

    void setVideoURI(Uri uri);

    void start();

    void suspend();

    /* loaded from: classes11.dex */
    public static class CustomOptionEntity {
        public long numValue;
        public int optCategory;
        public String optName;
        public String strValue;
        public boolean valueIsNum;

        private CustomOptionEntity(int i2, String str, long j2) {
            this.optCategory = i2;
            this.optName = str;
            this.numValue = j2;
            this.valueIsNum = true;
        }

        private CustomOptionEntity(int i2, String str, String str2) {
            this.optCategory = i2;
            this.optName = str;
            this.strValue = str2;
            this.valueIsNum = false;
        }
    }

    /* loaded from: classes11.dex */
    public static class PlayerOptions {
        public static final int AR_16_9_FIT_PARENT = 4;
        public static final int AR_4_3_FIT_PARENT = 5;
        public static final int AR_ASPECT_FILL_PARENT = 1;
        public static final int AR_ASPECT_FIT_PARENT = 0;
        public static final int AR_ASPECT_WRAP_CONTENT = 2;
        public static final int AR_MATCH_PARENT = 3;
        public static final int LIVE_DROP_NONE = 0;
        public static final int LIVE_KEEP_DROP = 1;
        public static final int LIVE_KEEP_SPEED = 2;
        public static final int PLAY_AUDIO = 1;
        public static final int PLAY_AV = 0;
        public static final int PLAY_VIDEO = 2;
        protected boolean alpha;
        protected IAlphaVideoView.AlphaOptions alphaOptions;
        protected String host;
        protected String ip;
        protected boolean isLive;
        protected boolean isLocalFile;
        protected String lasMPD;
        protected String playTypeId;
        public PlayerPerformInfo playerPerformInfo;
        protected boolean isCouldMediaCodec = true;
        protected boolean isStartOnPrepared = true;
        protected boolean showControllerOnStart = true;
        protected int maxLiveDelay = 3000;
        protected int minLiveDuration = 500;
        protected boolean isUseTextureView = true;
        protected int aspectRatio = 1;
        protected boolean isDebugLog = false;
        protected boolean isShowHubView = false;
        protected long seekAtStart = 0;
        protected float volume = -1.0f;
        protected boolean isForceAndroidPlayer = false;
        protected boolean enableAccurateSeek = false;
        protected boolean isRequestAudioFocus = true;
        protected int probesize = -1;
        protected boolean isIpv6VideoPlay = true;
        protected boolean isLimitProbesize = false;
        protected boolean isAdvanceDns = true;
        protected boolean loop = false;
        protected int loopCount = 0;
        protected boolean unVideo = false;
        protected boolean useCache = false;
        protected boolean usePreDraw = false;
        protected boolean sharePlayer = false;
        protected int preDrawKeycode = -1;
        protected int reconnectCount = 0;
        protected boolean enableReport = true;
        protected boolean enableMta = false;
        protected int playContent = 0;
        protected int liveDropMode = 0;
        protected boolean isHWAccelAutoCompatResolution = false;
        protected List<CustomOptionEntity> customOptionList = null;
        protected PlayerReportInfoEntity playerReportInfoEntity = null;
        protected Long expireTime = null;

        public PlayerOptions(boolean z) {
            setLive(z);
        }

        public PlayerOptions addCustomOption(int i2, String str, long j2) {
            if (this.customOptionList == null) {
                this.customOptionList = new ArrayList();
            }
            if (i2 == 1 && "is_advance_dns".equals(str)) {
                this.isAdvanceDns = j2 > 0;
                DebugLog.d(IjkVideoView.TAG, "addCustomOption isAdvanceDns " + this.isAdvanceDns);
                return this;
            }
            this.customOptionList.add(new CustomOptionEntity(i2, str, j2));
            return this;
        }

        public PlayerOptions enableMta(boolean z) {
            this.enableMta = z;
            return this;
        }

        public IAlphaVideoView.AlphaOptions getAlphaOptions() {
            return this.alphaOptions;
        }

        public int getAspectRatio() {
            return this.aspectRatio;
        }

        public List<CustomOptionEntity> getCustomOptionList() {
            return this.customOptionList;
        }

        public Pair<String, String> getHostIp() {
            return new Pair<>(this.host, this.ip);
        }

        public boolean getIsAdvanceDns() {
            return this.isAdvanceDns;
        }

        public boolean getIsLive() {
            return this.isLive;
        }

        public String getLasMPD() {
            return this.lasMPD;
        }

        public int getLiveDropMode() {
            return this.liveDropMode;
        }

        public int getLoopCount() {
            return this.loopCount;
        }

        public String getPlayTypeId() {
            return this.playTypeId;
        }

        public PlayerReportInfoEntity getPlayerReportInfoEntity() {
            return this.playerReportInfoEntity;
        }

        public int getPreDrawKeycode() {
            return this.preDrawKeycode;
        }

        public int getReconnectCount() {
            return this.reconnectCount;
        }

        public boolean isAlpha() {
            return this.alpha;
        }

        public boolean isCouldMediaCodec() {
            return this.isCouldMediaCodec;
        }

        public boolean isEnableMta() {
            return this.enableMta;
        }

        public boolean isForceAndroidPlayer() {
            return this.isForceAndroidPlayer;
        }

        public boolean isHWAccelAutoCompatResolution() {
            return this.isHWAccelAutoCompatResolution;
        }

        public boolean isLive() {
            return this.isLive;
        }

        public boolean isLoop() {
            return this.loop;
        }

        public boolean isMute() {
            return this.volume == 0.0f;
        }

        public boolean isSharePlayer() {
            return this.sharePlayer;
        }

        public boolean isStartOnPrepared() {
            return this.isStartOnPrepared;
        }

        public boolean isUnVideo() {
            return this.unVideo;
        }

        public boolean isUseCache() {
            return this.useCache;
        }

        public boolean isUsePreDraw() {
            return this.usePreDraw;
        }

        public PlayerOptions setAlphaOption(IAlphaVideoView.AlphaOptions alphaOptions) {
            this.alphaOptions = alphaOptions;
            return this;
        }

        public PlayerOptions setAspectRatio(@AspectRatioType int i2) {
            this.aspectRatio = i2;
            return this;
        }

        public PlayerOptions setCouldMediaCodec(boolean z) {
            this.isCouldMediaCodec = z;
            return this;
        }

        public PlayerOptions setDebugLog(boolean z) {
            this.isDebugLog = z;
            return this;
        }

        public PlayerOptions setEnableAccurateSeek(boolean z) {
            this.enableAccurateSeek = z;
            return this;
        }

        public PlayerOptions setEnableReport(boolean z) {
            this.enableReport = z;
            return this;
        }

        public PlayerOptions setExpireTime(Long l2) {
            this.expireTime = l2;
            return this;
        }

        public PlayerOptions setHWAccelAutoCompatResolution(boolean z) {
            this.isHWAccelAutoCompatResolution = z;
            return this;
        }

        public PlayerOptions setHostIp(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.host = str;
                this.ip = str2;
            }
            return this;
        }

        public PlayerOptions setIpv6VideoPlay(boolean z) {
            this.isIpv6VideoPlay = z;
            return this;
        }

        public PlayerOptions setIsAdvanceDns(boolean z) {
            this.isAdvanceDns = z;
            return this;
        }

        public PlayerOptions setIsForceAndroidPlayer(boolean z) {
            this.isForceAndroidPlayer = z;
            return this;
        }

        public PlayerOptions setIsLimitProbesize(boolean z) {
            this.isLimitProbesize = z;
            return this;
        }

        public PlayerOptions setIsRequestAudioFocus(boolean z) {
            this.isRequestAudioFocus = z;
            return this;
        }

        public PlayerOptions setLasMPD(String str) {
            if (this.isLive) {
                this.lasMPD = str;
            }
            return this;
        }

        public PlayerOptions setLive(boolean z) {
            this.isLive = z;
            this.isLimitProbesize = z;
            this.isAdvanceDns = z;
            return this;
        }

        public PlayerOptions setLiveDropMode(@LiveDropMode int i2) {
            this.liveDropMode = i2;
            return this;
        }

        public PlayerOptions setLocalFile(boolean z) {
            this.isLocalFile = z;
            return this;
        }

        public PlayerOptions setLoop(boolean z) {
            if (this.isLive) {
                this.loop = false;
            } else {
                this.loop = z;
            }
            return this;
        }

        public PlayerOptions setLoopCount(int i2) {
            if (this.isLive) {
                this.loopCount = 0;
            } else {
                this.loopCount = i2;
            }
            return this;
        }

        public PlayerOptions setMaxLiveDelay(int i2) {
            this.maxLiveDelay = i2;
            return this;
        }

        public PlayerOptions setMinLiveDuration(int i2) {
            this.minLiveDuration = i2;
            return this;
        }

        public PlayerOptions setPlayContent(@PlayContent int i2) {
            if (i2 == 2) {
                this.isRequestAudioFocus = false;
            }
            this.playContent = i2;
            return this;
        }

        public PlayerOptions setPlayTypeId(String str) {
            this.playTypeId = str;
            return this;
        }

        public PlayerOptions setPlayerPerformInfo(PlayerPerformInfo playerPerformInfo) {
            this.playerPerformInfo = playerPerformInfo;
            return this;
        }

        public PlayerOptions setPlayerReportInfoEntity(PlayerReportInfoEntity playerReportInfoEntity) {
            this.playerReportInfoEntity = playerReportInfoEntity;
            this.enableMta = true;
            return this;
        }

        public PlayerOptions setPreDrawKeycode(int i2) {
            this.preDrawKeycode = i2;
            return this;
        }

        public PlayerOptions setReconnectCount(int i2) {
            this.reconnectCount = Math.min(i2, 4);
            return this;
        }

        @Deprecated
        public PlayerOptions setSeekAtStart(long j2) {
            this.seekAtStart = j2;
            return this;
        }

        public PlayerOptions setSharePlayer(boolean z) {
            this.sharePlayer = z;
            return this;
        }

        public PlayerOptions setShowHubView(boolean z) {
            this.isShowHubView = z;
            return this;
        }

        public PlayerOptions setStartOnPrepared(boolean z) {
            this.isStartOnPrepared = z;
            return this;
        }

        public PlayerOptions setUnVideo(boolean z) {
            this.unVideo = z;
            return this;
        }

        public PlayerOptions setUseCache(boolean z) {
            if (!this.isLive) {
                this.useCache = z;
            }
            return this;
        }

        public PlayerOptions setUsePreDraw(boolean z) {
            if (!this.isLive) {
                this.usePreDraw = z;
            }
            return this;
        }

        public PlayerOptions setUseTextureView(boolean z) {
            this.isUseTextureView = z;
            return this;
        }

        public PlayerOptions setVolume(float f2) {
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            this.volume = f2;
            return this;
        }

        public PlayerOptions setprobesize(int i2) {
            this.probesize = i2;
            return this;
        }

        public PlayerOptions showControllerOnStart(boolean z) {
            this.showControllerOnStart = z;
            return this;
        }

        public PlayerOptions useAlpha(boolean z) {
            this.alpha = z;
            if (this.alphaOptions == null) {
                this.alphaOptions = new IAlphaVideoView.AlphaOptions();
            }
            return this;
        }

        public PlayerOptions addCustomOption(int i2, String str, String str2) {
            if (this.customOptionList == null) {
                this.customOptionList = new ArrayList();
            }
            this.customOptionList.add(new CustomOptionEntity(i2, str, str2));
            return this;
        }

        public PlayerOptions addCustomOption(String str) {
            if (TextUtils.isEmpty(str)) {
                return this;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String[] strArr = {"format", "codec", "sws", PlayerReportInfoEntity.PAGE_ID};
                int[] iArr = {1, 2, 3, 4};
                for (int i2 = 0; i2 < 4; i2++) {
                    if (jSONObject.has(strArr[i2])) {
                        addCustomOption(jSONObject.optJSONObject(strArr[i2]), iArr[i2]);
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return this;
        }

        private void addCustomOption(JSONObject jSONObject, int i2) {
            if (jSONObject == null) {
                return;
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys != null && keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(optString)) {
                    try {
                        addCustomOption(i2, next, Long.parseLong(optString));
                    } catch (NumberFormatException e2) {
                        addCustomOption(i2, next, optString);
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
}
