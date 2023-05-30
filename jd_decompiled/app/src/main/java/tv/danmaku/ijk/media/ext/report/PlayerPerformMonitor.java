package tv.danmaku.ijk.media.ext.report;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.smtt.sdk.ProxyConfig;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.HashMap;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.cache.JDPlayerVideoCache;
import tv.danmaku.ijk.media.ext.report.bean.FirstFrameReportInfo;
import tv.danmaku.ijk.media.ext.report.bean.MediaDetailReportInfo;
import tv.danmaku.ijk.media.ext.report.bean.PlayStatusReportInfo;
import tv.danmaku.ijk.media.ext.report.bean.PlayerCommonInfo;
import tv.danmaku.ijk.media.ext.report.bean.PlayerPerformInfo;
import tv.danmaku.ijk.media.player.AbstractMediaPlayer;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;
import tv.danmaku.ijk.media.utils.DebugLog;
import tv.danmaku.ijk.media.utils.MediaInfoUtil;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class PlayerPerformMonitor {
    private static final String TAG = "PlayerPerformMonitor";
    private long aCacheDur;
    private final Runnable collectDynamicInfoRunnable;
    private int dynamicCount;
    private boolean enableVsr;
    private Uri finalUrl;
    private FirstFrameReportInfo firstFrameReportInfo;
    boolean isAndroidPlayer;
    private long lasSwitchCount;
    private String lastDelay;
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnExtInfoListener mOnExtInfoListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnPlayerEventListener mOnPlayerEvent;
    private WeakReference<Context> mWeakContext;
    private Handler mainHandler;
    private MediaDetailReportInfo mediaDetailReportInfo;
    private IMediaPlayer mediaPlayer;
    private IPlayerControl.PlayerOptions options;
    private String originUrl;
    private long pauseStartTime;
    private String playType;
    private PlayerCommonInfo playerCommonInfo;
    private long preLasSwitchTime;
    private String sessionId;
    private long startPlayTimestamp;
    private long vCacheDur;
    private final PlayStatusReportInfo playStatusReportInfo = new PlayStatusReportInfo();
    private long trafficKBCount = 0;
    private String videoSizeStr = "";
    private int preLasIndex = 0;
    private boolean isRendered = false;
    private long bufferStartTime = 0;
    private IMediaPlayer.OnSeiListener mOnSeiListener = new IMediaPlayer.OnSeiListener() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.1
        {
            PlayerPerformMonitor.this = this;
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeiListener
        public void onSeiText(IMediaPlayer iMediaPlayer, String str) {
            if (str != null) {
                try {
                    DebugLog.i(PlayerPerformMonitor.TAG, "onSeiText content:" + str + " , ntp_offset: " + JDPlayerSdk.DIFF_TIME);
                    byte[] decode = Base64.decode(str, 0);
                    byte[] localTime = PlayerPerformMonitor.this.getLocalTime(System.currentTimeMillis() + JDPlayerSdk.DIFF_TIME);
                    byte[] bArr = new byte[decode.length + 1 + 2 + 12];
                    System.arraycopy(decode, 0, bArr, 0, decode.length);
                    byte[] bArr2 = {18, 0, 12, -1, -1, -1, -1};
                    System.arraycopy(localTime, 0, bArr2, 7, 8);
                    System.arraycopy(bArr2, 0, bArr, decode.length, 15);
                    PlayerPerformMonitor.this.lastDelay = Base64.encodeToString(bArr, 0);
                    PlayerPerformMonitor playerPerformMonitor = PlayerPerformMonitor.this;
                    playerPerformMonitor.vCacheDur = playerPerformMonitor.mediaPlayer.getPropertyLong(20005);
                    PlayerPerformMonitor playerPerformMonitor2 = PlayerPerformMonitor.this;
                    playerPerformMonitor2.aCacheDur = playerPerformMonitor2.mediaPlayer.getPropertyLong(20006);
                    DebugLog.i(PlayerPerformMonitor.TAG, "onSeiText content:" + str + " \uff0c lastDelay:" + PlayerPerformMonitor.this.lastDelay);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    };
    private IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.2
        private int preHeight;
        private int preWidth;

        {
            PlayerPerformMonitor.this = this;
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5) {
            if (i2 == 0 || i3 == 0) {
                return;
            }
            if (i2 == this.preWidth && i3 == this.preHeight) {
                return;
            }
            this.preWidth = i2;
            this.preHeight = i3;
            PlayerPerformMonitor.access$484(PlayerPerformMonitor.this, i2 + ProxyConfig.MATCH_ALL_SCHEMES + i3 + CartConstant.KEY_YB_INFO_LINK);
        }
    };
    private IMediaPlayer.OnNativeInvokeListener onNativeInvokeListener = new IMediaPlayer.OnNativeInvokeListener() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.3
        {
            PlayerPerformMonitor.this = this;
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnNativeInvokeListener
        public boolean onNativeInvoke(final int i2, final Bundle bundle) {
            if (PlayerPerformMonitor.this.mainHandler != null) {
                PlayerPerformMonitor.this.mainHandler.post(new Runnable() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (PlayerPerformMonitor.this.mWeakContext == null) {
                            return;
                        }
                        PlayerPerformMonitor.this.processNativeInvoke(i2, bundle);
                    }
                });
                return true;
            }
            return true;
        }
    };

    public PlayerPerformMonitor(Context context, String str, String str2, Uri uri, IMediaPlayer iMediaPlayer, IPlayerControl.PlayerOptions playerOptions) {
        this.isAndroidPlayer = false;
        Runnable runnable = new Runnable() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.4
            {
                PlayerPerformMonitor.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (PlayerPerformMonitor.this.mWeakContext != null && PlayerPerformMonitor.this.mainHandler != null) {
                        PlayerPerformMonitor.access$808(PlayerPerformMonitor.this);
                        if (PlayerPerformMonitor.this.mediaDetailReportInfo == null) {
                            PlayerPerformMonitor.this.mediaDetailReportInfo = new MediaDetailReportInfo();
                            if (PlayerPerformMonitor.this.playerCommonInfo != null) {
                                PlayerPerformMonitor.this.mediaDetailReportInfo.addCommonParams(PlayerPerformMonitor.this.playerCommonInfo.paramsMap);
                            }
                        }
                        PlayerPerformMonitor.this.mediaDetailReportInfo.addDynamicInfo(PlayerPerformMonitor.this.generateDynamicInfo());
                        if (PlayerPerformMonitor.this.dynamicCount > 9) {
                            PlayerPerformMonitor.this.reportDynamicInfo();
                        }
                        PlayerPerformMonitor.this.mainHandler.postDelayed(PlayerPerformMonitor.this.collectDynamicInfoRunnable, Final.HALF_MINUTE);
                    }
                } catch (Exception unused) {
                }
            }
        };
        this.collectDynamicInfoRunnable = runnable;
        this.mOnInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.5
            private long componentOpenTimestamp;
            private long decodeStartTime;

            {
                PlayerPerformMonitor.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer2, int i2, int i3) {
                Bundle mediaMeta;
                IjkMediaMeta parse;
                if (i2 == 1) {
                    this.componentOpenTimestamp = System.currentTimeMillis();
                    if (PlayerPerformMonitor.this.mediaPlayer != null && (PlayerPerformMonitor.this.mediaPlayer instanceof IjkMediaPlayer) && (mediaMeta = ((IjkMediaPlayer) PlayerPerformMonitor.this.mediaPlayer).getMediaMeta()) != null && (parse = IjkMediaMeta.parse(mediaMeta)) != null) {
                        if (PlayerPerformMonitor.this.playerCommonInfo != null) {
                            PlayerPerformMonitor.this.playerCommonInfo.buildContainer(parse.mFormat);
                        }
                        PlayerPerformMonitor.this.playStatusReportInfo.buildMediaInfo(parse);
                    }
                    if (PlayerPerformMonitor.this.firstFrameReportInfo == null || i3 < 0) {
                        return false;
                    }
                    PlayerPerformMonitor.this.firstFrameReportInfo.setPreparedTime(i3);
                    return false;
                } else if (i2 == 3) {
                    PlayerPerformMonitor.this.isRendered = true;
                    long currentTimeMillis = System.currentTimeMillis();
                    long j2 = currentTimeMillis - PlayerPerformMonitor.this.startPlayTimestamp;
                    if (PlayerPerformMonitor.this.firstFrameReportInfo != null && j2 <= 120000) {
                        PlayerPerformMonitor.this.firstFrameReportInfo.setRenderTime(currentTimeMillis - this.decodeStartTime, i3, PlayerPerformMonitor.this.isAndroidPlayer);
                        PlayerPerformMonitor.this.reportFirstFrame();
                    }
                    PlayerPerformMonitor.this.playStatusReportInfo.setPlayStartTime(System.currentTimeMillis());
                    if (PlayerPerformMonitor.this.mediaPlayer == null || !(PlayerPerformMonitor.this.mediaPlayer instanceof IjkMediaPlayer)) {
                        return false;
                    }
                    PlayerPerformMonitor.this.playStatusReportInfo.setFinalDecoder((int) PlayerPerformMonitor.this.mediaPlayer.getPropertyLong(20003));
                    return false;
                } else if (i2 == 30001) {
                    if (PlayerPerformMonitor.this.firstFrameReportInfo != null) {
                        PlayerPerformMonitor.this.firstFrameReportInfo.setDnsPrepareTime(i3);
                        return false;
                    }
                    return false;
                } else if (i2 == 701) {
                    PlayerPerformMonitor.this.bufferStartTime = System.currentTimeMillis();
                    return false;
                } else if (i2 == 702) {
                    if (!PlayerPerformMonitor.this.isRendered && PlayerPerformMonitor.this.firstFrameReportInfo != null) {
                        PlayerPerformMonitor.this.firstFrameReportInfo.setBufferTime(System.currentTimeMillis() - PlayerPerformMonitor.this.bufferStartTime);
                    }
                    if (PlayerPerformMonitor.this.isRendered) {
                        PlayerPerformMonitor.this.addStuckInfo(i3, System.currentTimeMillis());
                    }
                    PlayerPerformMonitor.this.bufferStartTime = 0L;
                    return false;
                } else {
                    switch (i2) {
                        case 10004:
                            this.decodeStartTime = System.currentTimeMillis();
                            if (PlayerPerformMonitor.this.firstFrameReportInfo != null) {
                                PlayerPerformMonitor.this.firstFrameReportInfo.setDecodeTime(this.decodeStartTime - this.componentOpenTimestamp);
                                return false;
                            }
                            return false;
                        case 10005:
                            if (PlayerPerformMonitor.this.firstFrameReportInfo != null) {
                                PlayerPerformMonitor.this.firstFrameReportInfo.setConnectionOpenTime(i3);
                                return false;
                            }
                            return false;
                        case 10006:
                            if (PlayerPerformMonitor.this.firstFrameReportInfo != null) {
                                PlayerPerformMonitor.this.firstFrameReportInfo.setProbeStreamTime(i3);
                                return false;
                            }
                            return false;
                        default:
                            return false;
                    }
                }
            }
        };
        this.mOnExtInfoListener = new IMediaPlayer.OnExtInfoListener() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.6
            {
                PlayerPerformMonitor.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnExtInfoListener
            public void onExtInfo(IMediaPlayer iMediaPlayer2, int i2, int i3, int i4, HashMap<String, Object> hashMap) {
                if (hashMap == null || i3 != 10004 || !hashMap.containsKey("videoCodec") || hashMap.get("videoCodec") == null || PlayerPerformMonitor.this.playerCommonInfo == null) {
                    return;
                }
                PlayerPerformMonitor.this.playerCommonInfo.buildDecodeType((String) hashMap.get("videoCodec"));
            }
        };
        this.mOnErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.7
            {
                PlayerPerformMonitor.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer2, int i2, int i3) {
                int i4;
                if (PlayerPerformMonitor.this.mediaPlayer != null) {
                    if (PlayerPerformMonitor.this.mediaPlayer instanceof IjkMediaPlayer) {
                        i4 = -1;
                        PlayerPerformMonitor.this.updateTrafficByteCount();
                    } else if (PlayerPerformMonitor.this.mediaPlayer instanceof AndroidMediaPlayer) {
                        i4 = -3;
                    }
                    PlayerPerformMonitor.this.playStatusReportInfo.setErrCode(i4, i2 + "#" + i3 + ":" + MediaInfoUtil.getFFmpegErrStrByCode(i2, i3));
                    PlayerPerformMonitor.this.reportPlayInfo();
                    return false;
                }
                i4 = 0;
                PlayerPerformMonitor.this.playStatusReportInfo.setErrCode(i4, i2 + "#" + i3 + ":" + MediaInfoUtil.getFFmpegErrStrByCode(i2, i3));
                PlayerPerformMonitor.this.reportPlayInfo();
                return false;
            }
        };
        this.mOnPlayerEvent = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor.8
            {
                PlayerPerformMonitor.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i2) {
                if (i2 == 1) {
                    if (PlayerPerformMonitor.this.firstFrameReportInfo == null) {
                        PlayerPerformMonitor.this.buildPlayerCommonInfo();
                    }
                    if (PlayerPerformMonitor.this.pauseStartTime == 0 || PlayerPerformMonitor.this.mWeakContext == null || PlayerPerformMonitor.this.mWeakContext.get() == null) {
                        return;
                    }
                    PlayerPerformMonitor.this.playStatusReportInfo.appendPauseInfo(PlayerPerformMonitor.this.pauseStartTime, System.currentTimeMillis(), PlayerNetworkUtil.getAPNType((Context) PlayerPerformMonitor.this.mWeakContext.get()).value());
                    PlayerPerformMonitor.this.pauseStartTime = 0L;
                } else if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 5) {
                            if (i2 != 12) {
                                if (i2 != 14) {
                                    return;
                                }
                            }
                        }
                        PlayerPerformMonitor.this.updateTrafficByteCount();
                        PlayerPerformMonitor.this.release();
                        return;
                    }
                    PlayerPerformMonitor.this.updateTrafficByteCount();
                    PlayerPerformMonitor.this.reportPlayInfo();
                    PlayerPerformMonitor.this.playStatusReportInfo.setPlayStartTime(System.currentTimeMillis());
                    if (PlayerPerformMonitor.this.mediaPlayer == null || !(PlayerPerformMonitor.this.mediaPlayer instanceof IjkMediaPlayer)) {
                        return;
                    }
                    PlayerPerformMonitor.this.playStatusReportInfo.setFinalDecoder((int) PlayerPerformMonitor.this.mediaPlayer.getPropertyLong(20003));
                } else {
                    PlayerPerformMonitor.this.pauseStartTime = System.currentTimeMillis();
                }
            }
        };
        if (iMediaPlayer == null || context == null) {
            return;
        }
        if (iMediaPlayer instanceof TextureMediaPlayer) {
            this.mediaPlayer = ((TextureMediaPlayer) iMediaPlayer).getInternalMediaPlayer();
        } else if (iMediaPlayer instanceof AbstractMediaPlayer) {
            this.mediaPlayer = iMediaPlayer;
        }
        this.mWeakContext = new WeakReference<>(context);
        this.options = playerOptions;
        this.playType = str;
        this.originUrl = str2;
        this.finalUrl = uri;
        this.sessionId = PlayerToolsUtil.MD5(str2 + System.currentTimeMillis());
        this.preLasSwitchTime = System.currentTimeMillis();
        this.startPlayTimestamp = System.currentTimeMillis();
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainHandler = handler;
        handler.postDelayed(runnable, Final.HALF_MINUTE);
        buildPlayerCommonInfo();
        registerListener();
        setCacheInfo(playerOptions.isUseCache(), JDPlayerVideoCache.getInstance().isPreLoad(), JDPlayerVideoCache.getInstance().isRealUseCache());
        if (iMediaPlayer instanceof AndroidMediaPlayer) {
            this.isAndroidPlayer = true;
        }
    }

    static /* synthetic */ String access$484(PlayerPerformMonitor playerPerformMonitor, Object obj) {
        String str = playerPerformMonitor.videoSizeStr + obj;
        playerPerformMonitor.videoSizeStr = str;
        return str;
    }

    static /* synthetic */ int access$808(PlayerPerformMonitor playerPerformMonitor) {
        int i2 = playerPerformMonitor.dynamicCount;
        playerPerformMonitor.dynamicCount = i2 + 1;
        return i2;
    }

    public void addStuckInfo(int i2, long j2) {
        IPlayerControl.PlayerOptions playerOptions;
        long j3 = this.bufferStartTime;
        if (j3 == 0 || j2 - j3 >= 20000) {
            return;
        }
        WeakReference<Context> weakReference = this.mWeakContext;
        if (weakReference != null && weakReference.get() != null && this.isRendered) {
            this.playStatusReportInfo.appendStuckInfo(this.bufferStartTime, j2, PlayerNetworkUtil.getAPNType(this.mWeakContext.get()).value(), i2);
            if (this.preLasIndex >= 0 && (playerOptions = this.options) != null && !TextUtils.isEmpty(playerOptions.getLasMPD())) {
                this.playStatusReportInfo.appendLasStuckInfo(String.valueOf(this.preLasIndex), this.bufferStartTime, j2, PlayerNetworkUtil.getAPNType(this.mWeakContext.get()).value(), i2);
            }
        }
        this.bufferStartTime = 0L;
    }

    private void buildPlayStatusInfo() {
        IPlayerControl.PlayerOptions playerOptions = this.options;
        if (playerOptions != null) {
            this.playStatusReportInfo.setOptionDecoder(playerOptions.isCouldMediaCodec());
            this.playStatusReportInfo.setLiveDropMode(this.options.getLiveDropMode());
        }
    }

    public void buildPlayerCommonInfo() {
        PlayerCommonInfo playerCommonInfo = new PlayerCommonInfo();
        this.playerCommonInfo = playerCommonInfo;
        playerCommonInfo.buildPlayType(this.playType).buildPlayMode(this.options.getIsLive()).buildPlayType(this.mediaPlayer).buildPlayUrl(this.originUrl).buildSessionId(this.sessionId);
        PlayerPerformInfo playerPerformInfo = this.options.playerPerformInfo;
        if (playerPerformInfo != null) {
            this.playerCommonInfo.buildLiveId(playerPerformInfo.getLiveId());
        }
        FirstFrameReportInfo firstFrameReportInfo = new FirstFrameReportInfo();
        this.firstFrameReportInfo = firstFrameReportInfo;
        firstFrameReportInfo.setAutoPlay(this.options.isStartOnPrepared());
        this.firstFrameReportInfo.setProtocolType(PlayerStringUtils.getProtocolType(this.finalUrl));
        buildPlayStatusInfo();
    }

    public MediaDetailReportInfo.MediaDynamicInfo generateDynamicInfo() {
        if (this.mediaPlayer == null) {
            return null;
        }
        MediaDetailReportInfo.MediaDynamicInfo mediaDynamicInfo = new MediaDetailReportInfo.MediaDynamicInfo();
        mediaDynamicInfo.setOriginRez(this.mediaPlayer.getVideoWidth() + ProxyConfig.MATCH_ALL_SCHEMES + this.mediaPlayer.getVideoHeight());
        if (this.enableVsr) {
            mediaDynamicInfo.setEnhanceRez((this.mediaPlayer.getVideoWidth() * 2) + ProxyConfig.MATCH_ALL_SCHEMES + (this.mediaPlayer.getVideoHeight() * 2));
        }
        mediaDynamicInfo.setSpeed(getSpeed());
        mediaDynamicInfo.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        if (this.mediaPlayer instanceof IjkMediaPlayer) {
            IPlayerControl.PlayerOptions playerOptions = this.options;
            if (playerOptions != null && playerOptions.isLive()) {
                if (!TextUtils.isEmpty(this.lastDelay)) {
                    mediaDynamicInfo.setLastDelay(this.lastDelay);
                    if (this.vCacheDur == 0) {
                        this.vCacheDur = this.mediaPlayer.getPropertyLong(20005);
                    }
                    if (this.aCacheDur == 0) {
                        this.aCacheDur = this.mediaPlayer.getPropertyLong(20006);
                    }
                } else {
                    this.vCacheDur = this.mediaPlayer.getPropertyLong(20005);
                    this.aCacheDur = this.mediaPlayer.getPropertyLong(20006);
                }
                mediaDynamicInfo.setvBufferLen(String.valueOf(this.vCacheDur));
                mediaDynamicInfo.setaBufferLen(String.valueOf(this.aCacheDur));
                mediaDynamicInfo.setAudioCbDuration(String.valueOf(this.mediaPlayer.getPropertyLong(22002)));
                mediaDynamicInfo.setDisplayDuration(String.valueOf(this.mediaPlayer.getPropertyLong(22001)));
            }
            mediaDynamicInfo.setvBitRate(String.valueOf(this.mediaPlayer.getPropertyLong(20100)));
            mediaDynamicInfo.setvFrameRate(String.format("%.2f", Float.valueOf(this.mediaPlayer.getPropertyFloat(10002))));
        }
        return mediaDynamicInfo;
    }

    private String getSpeed() {
        IMediaPlayer iMediaPlayer = this.mediaPlayer;
        if (iMediaPlayer == null || !(iMediaPlayer instanceof IjkMediaPlayer)) {
            return "";
        }
        double propertyLong = iMediaPlayer.getPropertyLong(20200);
        Double.isNaN(propertyLong);
        return String.valueOf(Math.round(propertyLong / 1024.0d));
    }

    public void processNativeInvoke(int i2, Bundle bundle) {
        PlayerCommonInfo playerCommonInfo;
        if (i2 == 1) {
            if (this.playerCommonInfo == null || bundle == null || TextUtils.isEmpty(bundle.getString("url"))) {
                return;
            }
            this.playerCommonInfo.buildPlayUrl(bundle.getString("url"));
        } else if (i2 == 6) {
            if (this.firstFrameReportInfo == null || bundle == null) {
                return;
            }
            this.firstFrameReportInfo.setDnsEndTime(bundle.getLong("dns_time", 0L));
        } else if (i2 == 77825) {
            int i3 = bundle.getInt("cur_rep_index");
            this.lasSwitchCount = bundle.getLong("rep_switch_cnt");
            String string = bundle.getString("cur_playing_url");
            if (!TextUtils.isEmpty(string) && (playerCommonInfo = this.playerCommonInfo) != null) {
                playerCommonInfo.buildPlayUrl(string);
            }
            int i4 = this.preLasIndex;
            if (i3 != i4) {
                this.playStatusReportInfo.appendLasPlayInfo(String.valueOf(i4), this.preLasSwitchTime, System.currentTimeMillis());
            }
            this.preLasSwitchTime = System.currentTimeMillis();
            this.preLasIndex = i3;
        } else if (i2 != 131074) {
            if (i2 == 131083 && bundle != null) {
                String string2 = bundle.getString("ip", "unknown");
                PlayerCommonInfo playerCommonInfo2 = this.playerCommonInfo;
                if (playerCommonInfo2 != null) {
                    playerCommonInfo2.buildStreamIp(string2);
                }
            }
        } else if (bundle != null) {
            String string3 = bundle.getString("ip", "unknown");
            long j2 = bundle.getLong("duration", 0L);
            PlayerCommonInfo playerCommonInfo3 = this.playerCommonInfo;
            if (playerCommonInfo3 != null) {
                playerCommonInfo3.buildStreamIp(string3);
            }
            FirstFrameReportInfo firstFrameReportInfo = this.firstFrameReportInfo;
            if (firstFrameReportInfo != null) {
                firstFrameReportInfo.setTcpEndTime(j2);
            }
        }
    }

    private void registerListener() {
        IMediaPlayer iMediaPlayer = this.mediaPlayer;
        if (iMediaPlayer == null || this.mWeakContext == null) {
            return;
        }
        iMediaPlayer.setOnInfoListener(this.mOnInfoListener);
        this.mediaPlayer.setOnExtInfoListener(this.mOnExtInfoListener);
        this.mediaPlayer.setOnErrorListener(this.mOnErrorListener);
        this.mediaPlayer.setOnNativeInvokeListener(this.onNativeInvokeListener);
        this.mediaPlayer.setOnPlayerEventListener(this.mOnPlayerEvent);
        this.mediaPlayer.setOnVideoSizeChangedListener(this.onVideoSizeChangedListener);
        this.mediaPlayer.setOnSeiListener(this.mOnSeiListener);
    }

    public void reportDynamicInfo() {
        if (this.mWeakContext == null || this.mediaDetailReportInfo == null) {
            return;
        }
        if (JDPlayerSdk.getInstance().isIsForeground()) {
            JDPlayerSdk.getPlayerReport().onPerfReport(this.mWeakContext, "14", this.mediaDetailReportInfo.getChId(), this.mediaDetailReportInfo.generateReportMap());
        }
        this.mediaDetailReportInfo.resetDynamicInfo();
        this.dynamicCount = 0;
    }

    public void reportFirstFrame() {
        FirstFrameReportInfo firstFrameReportInfo;
        if (this.mediaPlayer == null || this.mWeakContext == null || (firstFrameReportInfo = this.firstFrameReportInfo) == null) {
            return;
        }
        firstFrameReportInfo.setTcpSpeed(getSpeed());
        this.firstFrameReportInfo.addCommonParams(this.playerCommonInfo.paramsMap);
        JDPlayerSdk.getPlayerReport().onPerfReport(this.mWeakContext, "14", this.firstFrameReportInfo.getChId(), this.firstFrameReportInfo.generateReportMap());
        this.firstFrameReportInfo = null;
    }

    public void reportPlayInfo() {
        reportDynamicInfo();
        if (this.mWeakContext == null || this.playStatusReportInfo.getPlayStartTime() == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        PlayerCommonInfo playerCommonInfo = this.playerCommonInfo;
        if (playerCommonInfo != null) {
            this.playStatusReportInfo.addCommonParams(playerCommonInfo.paramsMap);
        }
        if (!TextUtils.isEmpty(this.videoSizeStr)) {
            this.playStatusReportInfo.setOriSize(this.videoSizeStr.substring(0, r5.length() - 1));
        }
        this.playStatusReportInfo.setRenderType(this.enableVsr);
        this.playStatusReportInfo.setByteCount(this.trafficKBCount);
        IPlayerControl.PlayerOptions playerOptions = this.options;
        if (playerOptions != null && !TextUtils.isEmpty(playerOptions.getLasMPD())) {
            this.playStatusReportInfo.appendLasPlayInfo(String.valueOf(this.preLasIndex), this.preLasSwitchTime, currentTimeMillis);
            this.playStatusReportInfo.setLasSwitchCount(this.lasSwitchCount);
        }
        long j2 = this.pauseStartTime;
        if (j2 > 0) {
            this.playStatusReportInfo.appendPauseInfo(j2, currentTimeMillis, PlayerNetworkUtil.getAPNType(this.mWeakContext.get()).value());
            this.pauseStartTime = 0L;
        }
        this.playStatusReportInfo.setEndTime(currentTimeMillis);
        JDPlayerSdk.getPlayerReport().onPerfReport(this.mWeakContext, "14", this.playStatusReportInfo.getChId(), this.playStatusReportInfo.generateReportMap());
        this.playStatusReportInfo.clean();
        this.videoSizeStr = "";
    }

    private void setCacheInfo(boolean z, boolean z2, boolean z3) {
        int i2 = z2 ? 2 : z ? 1 : 0;
        FirstFrameReportInfo firstFrameReportInfo = this.firstFrameReportInfo;
        if (firstFrameReportInfo != null) {
            firstFrameReportInfo.setCacheMode(i2);
            this.firstFrameReportInfo.setRealUseCache(z3);
        }
    }

    public void updateTrafficByteCount() {
        IMediaPlayer iMediaPlayer = this.mediaPlayer;
        if (iMediaPlayer == null || !(iMediaPlayer instanceof IjkMediaPlayer)) {
            return;
        }
        this.trafficKBCount = iMediaPlayer.getPropertyLong(20204) / 1024;
    }

    public byte[] getLocalTime(long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(0, j2);
        return allocate.array();
    }

    public void release() {
        reportPlayInfo();
        this.mWeakContext = null;
        this.mediaPlayer = null;
        this.options = null;
        this.isRendered = false;
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mainHandler = null;
        this.mOnErrorListener = null;
        this.mOnInfoListener = null;
        this.onNativeInvokeListener = null;
        this.mOnPlayerEvent = null;
        this.mOnExtInfoListener = null;
        this.onVideoSizeChangedListener = null;
        this.mOnSeiListener = null;
    }

    public void updateVsrInfo(boolean z) {
        this.enableVsr = z;
    }
}
