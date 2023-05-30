package tv.danmaku.ijk.media.example.widget.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.cronet.CronetConfigLoader;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IRenderView;
import tv.danmaku.ijk.media.ext.cache.JDPlayerVideoCache;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.ext.mta.PlayerMtaReport;
import tv.danmaku.ijk.media.ext.pool.JDPlayerManager;
import tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor;
import tv.danmaku.ijk.media.player.AbstractMediaPlayer;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.JDPlayerHelper;
import tv.danmaku.ijk.media.player.MediaPlayerProxy;
import tv.danmaku.ijk.media.player.datasource.RawDataSourceProvider;
import tv.danmaku.ijk.media.player.threadpool.VideoPlayerThreadManager;
import tv.danmaku.ijk.media.utils.DebugLog;
import tv.danmaku.ijk.media.utils.MediaInfoUtil;
import tv.danmaku.ijk.media.utils.PlayerAudioHelper;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class JDVideoView extends IJDVideoPlayer {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_PLAYBACK_COMPLETED = 5;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    protected static String TAG = "JDVideoView";
    private static boolean isForceAndroidPlayer;
    private final AtomicBoolean bIsForceRelease;
    private final Runnable expireRunnable;
    private Long expireTime;
    private SimpleDraweeView imgCover;
    private boolean inActive;
    private boolean isCouldOpenVideoTextureCreate;
    private boolean isCouldStartOnRendered;
    private float lb;
    private final PlayerNetworkUtil.NetChangeListener liveNetListener;
    private float lt;
    private Context mAppContext;
    protected IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private String mBusinessId;
    protected IMediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    protected IMediaPlayer.OnErrorListener mErrorListener;
    protected final HashMap<String, String> mHeaders;
    private InfoHudViewHolder mHudViewHolder;
    protected IMediaPlayer.OnInfoListener mInfoListener;
    private IMediaController mMediaController;
    private PlayDurationStatistics mPlayDurationStatistics;
    protected IPlayerControl.PlayerOptions mPlayerOptions;
    protected IMediaPlayer.OnPreparedListener mPreparedListener;
    public IRenderView mRenderView;
    private IRenderView.IRenderCallback mSHCallback;
    protected IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    private long mSeekStartTime;
    private int mSeekWhenPrepared;
    protected IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    protected int mSurfaceHeight;
    private IRenderView.ISurfaceHolder mSurfaceHolder;
    private SurfaceHolder.Callback mSurfaceHolderCallback;
    protected int mSurfaceWidth;
    private int mTargetState;
    protected Uri mUri;
    protected int mVideoHeight;
    private int mVideoRotationDegree;
    protected int mVideoSarDen;
    protected int mVideoSarNum;
    protected int mVideoWidth;
    private String originUrl;
    private AudioManager.OnAudioFocusChangeListener outOnAudioFocusChangeListener;
    private Paint paint;
    private Path path;
    private PlayerAudioHelper playerAudioHelper;
    private PlayerPerformMonitor playerMonitor;
    private PlayerMtaReport playerMtaReport;
    private boolean preDrawAutoPlay;
    private RawDataSourceProvider rawDataSourceProvider;
    private float rb;
    private float rt;
    private int tag;
    private TextureRenderView textureRenderView;

    public JDVideoView(Context context) {
        super(context);
        this.mHeaders = new HashMap<>();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mBusinessId = "";
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.originUrl = "";
        this.preDrawAutoPlay = false;
        this.inActive = true;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.tag = -1;
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.2
            {
                JDVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JDVideoView.this.getContext() != null) {
                    JDVideoView jDVideoView = JDVideoView.this;
                    if (jDVideoView.mErrorListener == null) {
                        return;
                    }
                    jDVideoView.suspend();
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    jDVideoView2.mErrorListener.onError(((IJDVideoPlayer) jDVideoView2).mMediaPlayer, -10000, 489);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.3
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                JDVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                JDVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                JDVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                JDVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] OnVideoSizeChanged: mVideoWidth " + JDVideoView.this.mVideoWidth + " mVideoHeight " + JDVideoView.this.mVideoHeight + " mVideoSarNum " + JDVideoView.this.mVideoSarNum + " mVideoSarDen " + JDVideoView.this.mVideoSarDen + ", width = " + i2 + ", height = " + i3);
                JDVideoView jDVideoView = JDVideoView.this;
                if (jDVideoView.mVideoWidth == 0 || jDVideoView.mVideoHeight == 0) {
                    return;
                }
                Iterator it = ((IJDVideoPlayer) jDVideoView).mOnVideoSizeChangedListeners.iterator();
                while (it.hasNext()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    ((IPlayerControl.OnVideoSizeChangedListener) it.next()).onVideoSizeChanged(jDVideoView2.mVideoWidth, jDVideoView2.mVideoHeight);
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IRenderView iRenderView = jDVideoView3.mRenderView;
                if (iRenderView != null) {
                    iRenderView.setVideoSize(jDVideoView3.mVideoWidth, jDVideoView3.mVideoHeight);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.mRenderView.setVideoSampleAspectRatio(jDVideoView4.mVideoSarNum, jDVideoView4.mVideoSarDen);
                }
                JDVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.4
            {
                JDVideoView.this = this;
            }

            /* JADX WARN: Code restructure failed: missing block: B:98:0x00a3, code lost:
                if (r0.mSurfaceHeight == r0.mVideoHeight) goto L99;
             */
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer r5) {
                /*
                    Method dump skipped, instructions count: 285
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.example.widget.media.JDVideoView.AnonymousClass4.onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer):void");
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.5
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                JDVideoView jDVideoView;
                IPlayerControl.PlayerOptions playerOptions;
                JDVideoView.this.mPlayDurationStatistics.complete();
                JDVideoView.this.mCurrentState = 5;
                JDVideoView.this.mTargetState = 5;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (PlayerStringUtils.canPreSetLoop(JDVideoView.this.mUri) || (playerOptions = (jDVideoView = JDVideoView.this).mPlayerOptions) == null || !playerOptions.loop) {
                    return;
                }
                jDVideoView.start();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.6
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i3) {
                if (i2 == 3) {
                    IRenderView iRenderView = JDVideoView.this.mRenderView;
                    if (iRenderView != null) {
                        iRenderView.getView().setVisibility(0);
                    }
                    if (JDVideoView.this.imgCover != null && JDVideoView.this.imgCover.getParent() != null && (JDVideoView.this.imgCover.getParent() instanceof ViewGroup)) {
                        ((ViewGroup) JDVideoView.this.imgCover.getParent()).removeView(JDVideoView.this.imgCover);
                    }
                    if (!JDVideoView.this.isCouldStartOnRendered) {
                        JDVideoView.this.pause();
                    }
                    IPlayerControl.PlayerOptions playerOptions = JDVideoView.this.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay || JDVideoView.this.getIjkMediaPlayer() == null) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i2 == 10005) {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                    return true;
                } else if (i2 == 10008) {
                    IPlayerControl.PlayerOptions playerOptions2 = JDVideoView.this.mPlayerOptions;
                    if (playerOptions2 == null || !playerOptions2.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i2 != 10100) {
                    if (i2 != 30003) {
                        if (i2 == 901) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i2 == 902) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i2 == 10001) {
                            JDVideoView.this.mVideoRotationDegree = i3;
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i3);
                            IRenderView iRenderView2 = JDVideoView.this.mRenderView;
                            if (iRenderView2 != null) {
                                iRenderView2.setVideoRotation(i3);
                                return true;
                            }
                            return true;
                        } else if (i2 == 10002) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i2 != 10304) {
                            if (i2 != 10305) {
                                switch (i2) {
                                    case 700:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                        return true;
                                    case 701:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                        return true;
                                    case 702:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                        return true;
                                    case 703:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i3);
                                        return true;
                                    default:
                                        switch (i2) {
                                            case 800:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                                return true;
                                            case 801:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                                return true;
                                            case 802:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                                return true;
                                            default:
                                                DebugLog.d(JDVideoView.TAG, "unknown media info:" + i2 + " extra:" + i3);
                                                return true;
                                        }
                                }
                            }
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i3);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                    return true;
                } else {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                    return true;
                }
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.7
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i3) {
                IPlayerControl.PlayerOptions playerOptions;
                IPlayerControl.PlayerOptions playerOptions2;
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] Error: " + i2 + DYConstants.DY_REGEX_COMMA + i3);
                JDVideoView.this.mPlayDurationStatistics.error();
                JDVideoView.this.mCurrentState = -1;
                JDVideoView.this.mTargetState = -1;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (JDVideoView.this.getContext() != null && PlayerNetworkUtil.isNetworkConnected(JDVideoView.this.getContext()) && (playerOptions2 = JDVideoView.this.mPlayerOptions) != null && playerOptions2.useCache && !playerOptions2.sharePlayer && i2 != -1) {
                    DebugLog.d(JDVideoView.TAG, "Error: === remove cache, retry");
                    JDPlayerVideoCache.getInstance().getRawUri(JDVideoView.this.originUrl);
                    JDVideoView jDVideoView = JDVideoView.this;
                    jDVideoView.mPlayerOptions.useCache = false;
                    if (!TextUtils.isEmpty(jDVideoView.originUrl)) {
                        JDVideoView jDVideoView2 = JDVideoView.this;
                        jDVideoView2.setVideoURI(Uri.parse(jDVideoView2.originUrl));
                        return true;
                    }
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IPlayerControl.PlayerOptions playerOptions3 = jDVideoView3.mPlayerOptions;
                if (playerOptions3 != null && playerOptions3.isCouldMediaCodec && i3 == -1009 && jDVideoView3.mUri != null) {
                    DebugLog.d(JDVideoView.TAG, "Error: === disable mediacodec, retry");
                    JDVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.setVideoPath(jDVideoView4.originUrl);
                    return true;
                }
                if (CronetConfigLoader.quicEnable()) {
                    JDVideoView jDVideoView5 = JDVideoView.this;
                    if (jDVideoView5.mUri != null && PlayerStringUtils.isQuicProtocol(jDVideoView5.originUrl)) {
                        DebugLog.d(JDVideoView.TAG, "Error:  === disable quic, retry");
                        CronetConfigLoader.quicEnable = false;
                        JDVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(JDVideoView.this.mUri.toString())));
                        return true;
                    }
                }
                if (MediaInfoUtil.isDnsError(i3) && (playerOptions = JDVideoView.this.mPlayerOptions) != null && playerOptions.isAdvanceDns) {
                    playerOptions.isAdvanceDns = false;
                    DebugLog.d(JDVideoView.TAG, "Error:  === dns error quic, retry");
                    JDPlayerHelper.getInstance().addStreamHostIp(JDVideoView.this.originUrl, JDVideoView.this.mPlayerOptions, false);
                    if (!TextUtils.isEmpty(JDVideoView.this.originUrl)) {
                        JDVideoView jDVideoView6 = JDVideoView.this;
                        jDVideoView6.setVideoURI(Uri.parse(jDVideoView6.originUrl));
                        return true;
                    }
                }
                if (i3 == -858797304 && JDVideoView.this.expireTime != null) {
                    i3 = 489;
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                    ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayState(3, 0L, new int[]{i2, i3}, null);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.8
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2) {
                JDVideoView.this.mCurrentBufferPercentage = i2;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.9
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (JDVideoView.this.mHudViewHolder != null) {
                    JDVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - JDVideoView.this.mSeekStartTime);
                }
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.10
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || JDVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                JDVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.11
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3, int i4) {
                IRenderView renderView = iSurfaceHolder.getRenderView();
                JDVideoView jDVideoView = JDVideoView.this;
                if (renderView != jDVideoView.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                jDVideoView.mSurfaceWidth = i3;
                jDVideoView.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = jDVideoView.mTargetState == 3;
                if (JDVideoView.this.mRenderView.shouldWaitForResize()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    if (jDVideoView2.mVideoWidth != i3 || jDVideoView2.mVideoHeight != i4) {
                        z = false;
                    }
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (JDVideoView.this.mSeekWhenPrepared != 0) {
                        JDVideoView jDVideoView3 = JDVideoView.this;
                        jDVideoView3.seekTo(jDVideoView3.mSeekWhenPrepared);
                    }
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    IPlayerControl.PlayerOptions playerOptions = jDVideoView4.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.sharePlayer) {
                        jDVideoView4.start();
                    }
                }
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i2, i3, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated");
                JDVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer == null) {
                    if (JDVideoView.this.isCouldOpenVideoTextureCreate) {
                        JDVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                JDVideoView jDVideoView = JDVideoView.this;
                jDVideoView.bindSurfaceHolder(((IJDVideoPlayer) jDVideoView).mMediaPlayer, iSurfaceHolder);
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                if (JDPlayerSdk.debugEnable) {
                    DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: " + Thread.currentThread().getStackTrace().toString());
                }
                JDVideoView.this.mSurfaceHolder = null;
                JDVideoView.this.releaseWithoutStop();
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context, null);
    }

    public void addPlayerListeners() {
        resetListener();
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return;
        }
        iMediaPlayer.setOnPreparedListener(this.mPreparedListener);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
        this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
        this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
        this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
        this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
        this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
        Iterator<IPlayerControl.OnPlayerStateListener> it = this.mOnPlayerStateListeners.iterator();
        while (it.hasNext()) {
            this.mMediaPlayer.setOnPlayerStateListener(it.next());
        }
        Iterator<IMediaPlayer.OnPlayerEventListener> it2 = this.mOnPlayerEventListeners.iterator();
        while (it2.hasNext()) {
            this.mMediaPlayer.setOnPlayerEventListener(it2.next());
        }
    }

    private void attachMediaController() {
        IMediaController iMediaController;
        if (this.mMediaPlayer == null || (iMediaController = this.mMediaController) == null) {
            return;
        }
        iMediaController.setMediaPlayer(this);
        this.mMediaController.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
        this.mMediaController.setEnabled(isInPlaybackState());
    }

    public void bindSurfaceHolder(IMediaPlayer iMediaPlayer, IRenderView.ISurfaceHolder iSurfaceHolder) {
        if (iMediaPlayer == null) {
            return;
        }
        if (iSurfaceHolder == null) {
            iMediaPlayer.setDisplay(null);
        } else {
            iSurfaceHolder.bindToMediaPlayer(iMediaPlayer);
        }
    }

    private void checkAudioFocus() {
        Context context;
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions == null || !playerOptions.isRequestAudioFocus || (context = this.mAppContext) == null) {
            return;
        }
        if (this.playerAudioHelper == null) {
            this.playerAudioHelper = new PlayerAudioHelper(context, this.outOnAudioFocusChangeListener);
        }
        if (this.mPlayerOptions.volume == 0.0f) {
            this.playerAudioHelper.abandonAudioFocus();
        } else {
            this.playerAudioHelper.requestAudioFocus();
        }
    }

    public static JDVideoView createAndroidVideoView(Context context) {
        isForceAndroidPlayer = true;
        return new JDVideoView(context);
    }

    public void createPlayerMonitor() {
        IPlayerControl.PlayerOptions playerOptions;
        if (this.playerMonitor == null && (playerOptions = this.mPlayerOptions) != null && playerOptions.enableReport) {
            String str = playerOptions.playTypeId;
            if (TextUtils.isEmpty(str)) {
                str = this.mBusinessId;
            }
            String str2 = str;
            if (this.playerMonitor == null) {
                this.playerMonitor = new PlayerPerformMonitor(getContext(), str2, this.originUrl, this.mUri, this.mMediaPlayer, this.mPlayerOptions);
            }
        }
    }

    public void createPlayerMtaReport(boolean z) {
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions == null || !playerOptions.isEnableMta()) {
            return;
        }
        if (this.playerMtaReport == null) {
            this.playerMtaReport = new PlayerMtaReport(getContext(), this.mMediaPlayer, this.mPlayerOptions, this.originUrl);
        }
        this.playerMtaReport.enableReport(z);
    }

    private void initVideoView(Context context, AttributeSet attributeSet) {
        if (isForceAndroidPlayer) {
            isForceAndroidPlayer = false;
            MediaPlayerHelper.isLoadJniOk = false;
        } else {
            MediaPlayerHelper.loadLibrariesOnceSafe(context);
        }
        this.mAppContext = context.getApplicationContext();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDVideoView);
            setCorner(obtainStyledAttributes.getDimension(R.styleable.JDVideoView_leftTop, 0.0f), obtainStyledAttributes.getDimension(R.styleable.JDVideoView_rightTop, 0.0f), obtainStyledAttributes.getDimension(R.styleable.JDVideoView_leftBottom, 0.0f), obtainStyledAttributes.getDimension(R.styleable.JDVideoView_rightBottom, 0.0f));
            obtainStyledAttributes.recycle();
        }
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mPlayDurationStatistics = new PlayDurationStatistics();
    }

    private boolean isInPlaybackState() {
        int i2;
        return (this.mMediaPlayer == null || (i2 = this.mCurrentState) == -1 || i2 == 0 || i2 == 1) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x0186 A[Catch: IllegalArgumentException -> 0x018e, IOException -> 0x01b1, TRY_LEAVE, TryCatch #2 {IOException -> 0x01b1, IllegalArgumentException -> 0x018e, blocks: (B:124:0x00a4, B:126:0x00aa, B:129:0x00af, B:136:0x015e, B:138:0x0165, B:140:0x016f, B:141:0x0182, B:143:0x0186, B:130:0x011f, B:132:0x0125, B:134:0x0129, B:135:0x0159), top: B:162:0x00a4 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:165:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void openVideo() {
        /*
            Method dump skipped, instructions count: 506
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.example.widget.media.JDVideoView.openVideo():void");
    }

    public void releaseWithoutStop() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.setDisplay(null);
        }
    }

    private void toggleMediaControlsVisibility() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    public void addHeaders(String str, String str2) {
        this.mHeaders.put(str, LangUtils.SINGLE_SPACE + str2.trim());
    }

    public void addOnStatisticsStateListener(IPlayerControl.OnStatisticsStateListener onStatisticsStateListener) {
        PlayDurationStatistics playDurationStatistics = this.mPlayDurationStatistics;
        if (playDurationStatistics != null) {
            playDurationStatistics.addOnStatisticsStateListener(onStatisticsStateListener);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void addSurfaceHolderCallback(SurfaceHolder.Callback callback) {
        this.mSurfaceHolderCallback = callback;
    }

    public void clearHeader() {
        HashMap<String, String> hashMap = this.mHeaders;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Path path = this.path;
        if (path != null && this.paint != null) {
            if (Build.VERSION.SDK_INT > 26) {
                canvas.clipPath(path);
                super.draw(canvas);
                return;
            }
            canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.paint, 31);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            super.draw(canvas);
            this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(this.path, this.paint);
            this.paint.setXfermode(null);
            canvas.restore();
            return;
        }
        super.draw(canvas);
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void forceRelase(boolean z) {
        this.bIsForceRelease.set(true);
        this.inActive = true;
        releaseInThread(z);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public IjkMediaPlayer getIjkMediaPlayer() {
        return JDPlayerHelper.getIjkMediaPlayer(this.mMediaPlayer);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public String getOriginUrl() {
        return this.originUrl;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public int getPlayState() {
        return this.mCurrentState;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public IPlayerControl.PlayerOptions getPlayerOptions() {
        return this.mPlayerOptions;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public long getTcpSpeed() {
        IjkMediaPlayer ijkMediaPlayer = getIjkMediaPlayer();
        if (ijkMediaPlayer == null) {
            return -1L;
        }
        return ijkMediaPlayer.getPropertyLong(20200);
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public float getVolume() {
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions != null) {
            return playerOptions.volume;
        }
        return 0.0f;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void initRenders() {
        TextureRenderView textureRenderView;
        this.isCouldOpenVideoTextureCreate = true;
        if (this.mPlayerOptions == null) {
            return;
        }
        DebugLog.d(TAG, "[" + this.tag + "] initRenders");
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions.isUseTextureView) {
            if ((!playerOptions.usePreDraw && !playerOptions.sharePlayer) || (textureRenderView = this.textureRenderView) == null) {
                textureRenderView = new TextureRenderView(getContext());
            }
            if (this.mMediaPlayer != null) {
                textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                textureRenderView.setAspectRatio(this.mPlayerOptions.aspectRatio);
            }
            setRenderView(textureRenderView);
            return;
        }
        setRenderView(new SurfaceRenderView(getContext()));
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        boolean z = (i2 == 4 || i2 == 24 || i2 == 25 || i2 == 164 || i2 == 82 || i2 == 5 || i2 == 6) ? false : true;
        if (isInPlaybackState() && z && this.mMediaController != null) {
            if (i2 == 79 || i2 == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                } else {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            } else if (i2 == 126) {
                if (!this.mMediaPlayer.isPlaying()) {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            } else if (i2 != 86 && i2 != 127) {
                toggleMediaControlsVisibility();
            } else {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                }
                return true;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        Path path = this.path;
        if (path == null) {
            return;
        }
        path.reset();
        Path path2 = this.path;
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        float f2 = this.lt;
        float f3 = this.rt;
        float f4 = this.rb;
        float f5 = this.lb;
        path2.addRoundRect(rectF, new float[]{f2, f2, f3, f3, f4, f4, f5, f5}, Path.Direction.CCW);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        DebugLog.d(TAG, "[" + this.tag + "] pause");
        if (this.inActive) {
            this.isCouldStartOnRendered = false;
            if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
                this.mCurrentState = 4;
            }
            this.mTargetState = 4;
            this.mPlayDurationStatistics.pause();
        }
    }

    public void preDraw(String str, IPlayerControl.PlayerOptions playerOptions) {
        if (getTag() != null && (getTag() instanceof Integer)) {
            this.tag = ((Integer) getTag()).intValue();
        }
        this.mPlayerOptions = playerOptions;
        PlayerConfigLoader.getInstance().updateOptShareState(this.mPlayerOptions);
        IPlayerControl.PlayerOptions playerOptions2 = this.mPlayerOptions;
        if (playerOptions2 != null) {
            if (playerOptions2.usePreDraw || playerOptions2.sharePlayer) {
                DebugLog.d(TAG, "[" + this.tag + "] preDraw: " + str);
                if (this.mMediaPlayer != null && (!str.equals(this.originUrl) || Build.VERSION.SDK_INT < 23)) {
                    JDPlayerManager.getInstance().releasePlayer(str);
                    suspend();
                    resetTextureView();
                }
                this.originUrl = str;
                JDPlayerManager.getInstance().preparePlay(this, str, this.mPlayerOptions, this.mHeaders, new JDPlayerManager.JDPlayerManagerCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.1
                    {
                        JDVideoView.this = this;
                    }

                    @Override // tv.danmaku.ijk.media.ext.pool.JDPlayerManager.JDPlayerManagerCallback
                    public void onError(int i2, String str2) {
                        DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] preDraw# onError, code=" + i2);
                        JDVideoView jDVideoView = JDVideoView.this;
                        IMediaPlayer.OnErrorListener onErrorListener = jDVideoView.mErrorListener;
                        if (onErrorListener != null) {
                            onErrorListener.onError(((IJDVideoPlayer) jDVideoView).mMediaPlayer, 1, 0);
                        }
                    }

                    @Override // tv.danmaku.ijk.media.ext.pool.JDPlayerManager.JDPlayerManagerCallback
                    public void onPlayerAvailable(IMediaPlayer iMediaPlayer, boolean z, Uri uri) {
                        DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] preDraw# onPlayerAvailable, isReUse=" + z + " ,finalUrl=" + uri);
                        JDVideoView jDVideoView = JDVideoView.this;
                        jDVideoView.mUri = uri;
                        ((IJDVideoPlayer) jDVideoView).mMediaPlayer = iMediaPlayer;
                        JDVideoView jDVideoView2 = JDVideoView.this;
                        IPlayerControl.PlayerOptions playerOptions3 = jDVideoView2.mPlayerOptions;
                        if (playerOptions3 != null) {
                            jDVideoView2.setVolume(playerOptions3.volume);
                        }
                        JDVideoView.this.initRenders();
                        JDVideoView.this.createPlayerMtaReport(false);
                        if (!z) {
                            JDVideoView.this.mCurrentState = 1;
                        } else if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                            JDVideoView jDVideoView3 = JDVideoView.this;
                            jDVideoView3.mCurrentState = ((IJDVideoPlayer) jDVideoView3).mMediaPlayer.isPlaying() ? 3 : 4;
                        }
                        JDVideoView.this.addPlayerListeners();
                        JDVideoView.this.createPlayerMonitor();
                    }

                    @Override // tv.danmaku.ijk.media.ext.pool.JDPlayerManager.JDPlayerManagerCallback
                    public void onRenderAvailable(TextureRenderView textureRenderView) {
                        JDVideoView.this.textureRenderView = textureRenderView;
                    }
                });
            }
        }
    }

    public void preDrawReset() {
        IMediaPlayer iMediaPlayer;
        DebugLog.d(TAG, "[" + this.tag + "] preDrawReset");
        this.preDrawAutoPlay = false;
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions == null || !playerOptions.isUsePreDraw() || (iMediaPlayer = this.mMediaPlayer) == null) {
            return;
        }
        iMediaPlayer.seekTo(0L);
        this.mMediaPlayer.pause();
        this.mMediaPlayer.notifyPlayEvent(17);
        PlayerMtaReport playerMtaReport = this.playerMtaReport;
        if (playerMtaReport != null) {
            playerMtaReport.enableReport(false);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void release() {
        releaseInThread(false);
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void releaseInThread(boolean z) {
        if (this.inActive) {
            this.isCouldOpenVideoTextureCreate = false;
            releaseInThread(true, z, true);
        }
    }

    public void resetListener() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer instanceof AbstractMediaPlayer) {
            iMediaPlayer.resetListeners();
        }
        IMediaPlayer iMediaPlayer2 = this.mMediaPlayer;
        if (iMediaPlayer2 instanceof MediaPlayerProxy) {
            iMediaPlayer2.resetListeners();
        }
    }

    public void resetTextureView() {
        DebugLog.e(TAG, "[" + this.tag + "] resetTextureView");
        this.preDrawAutoPlay = false;
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView == null || iRenderView.getView() == null || this.mRenderView.getView().getParent() == null) {
            return;
        }
        ((ViewGroup) this.mRenderView.getView().getParent()).removeView(this.mRenderView.getView());
        this.mRenderView.removeRenderCallback(this.mSHCallback);
        this.mRenderView.release();
        this.mRenderView = null;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void resume() {
        DebugLog.d(TAG, "[" + this.tag + "] resume");
        this.isCouldOpenVideoTextureCreate = true;
        openVideo();
        start();
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void retry(boolean z) {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return;
        }
        if (z) {
            int currentPosition = (int) iMediaPlayer.getCurrentPosition();
            if (currentPosition < 2) {
                currentPosition = 2;
            }
            this.mSeekWhenPrepared = currentPosition;
        }
        suspend();
        initRenders();
        requestLayout();
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i2) {
        if (isInPlaybackState()) {
            this.mSeekStartTime = SystemClock.elapsedRealtime();
            if (i2 < 2) {
                i2 = 2;
            }
            int duration = getDuration();
            int i3 = duration / 1000;
            if (i3 > 1000) {
                i3 = 1000;
            }
            if (i3 < 50) {
                i3 = 50;
            }
            if (duration > 0 && i2 + i3 > duration) {
                i2 = duration - i3;
            }
            this.mMediaPlayer.seekTo(i2);
            this.mSeekWhenPrepared = 0;
            return;
        }
        if (i2 <= 2) {
            i2 = 0;
        }
        this.mSeekWhenPrepared = i2;
    }

    public void setActive(boolean z) {
        this.inActive = z;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setAspectRatio(@IPlayerControl.AspectRatioType int i2) {
        if (this.mPlayerOptions == null) {
            return;
        }
        DebugLog.d(TAG, "[" + this.tag + "] setAspectRatio: " + i2);
        this.mPlayerOptions.setAspectRatio(i2);
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            iRenderView.setAspectRatio(this.mPlayerOptions.aspectRatio);
            requestLayout();
        }
    }

    public void setBusinessId(String str) {
        this.mBusinessId = str;
    }

    public void setCorner(float f2, float f3, float f4, float f5) {
        if (f2 == 0.0f && f3 == 0.0f && f4 == 0.0f && f5 == 0.0f) {
            this.path = null;
            return;
        }
        setWillNotDraw(false);
        this.lt = f2;
        this.rt = f3;
        this.lb = f4;
        this.rb = f5;
        this.paint = new Paint();
        Path path = new Path();
        this.path = path;
        path.reset();
        this.path.addRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), new float[]{f2, f2, f3, f3, f5, f5, f4, f4}, Path.Direction.CCW);
        invalidate();
    }

    public void setImgCover(String str, ImageView.ScaleType scaleType) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.imgCover == null) {
            this.imgCover = new SimpleDraweeView(getContext());
        }
        this.imgCover.setScaleType(scaleType);
        if (this.imgCover.getParent() != null && (this.imgCover.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.imgCover.getParent()).removeView(this.imgCover);
        }
        addView(this.imgCover, 0, new FrameLayout.LayoutParams(-1, -1));
        JDImageUtils.displayImage(str, this.imgCover, new JDDisplayImageOptions());
    }

    public void setLocalPath(RawDataSourceProvider rawDataSourceProvider) {
        this.rawDataSourceProvider = rawDataSourceProvider;
        setVideoURI(null);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setMediaController(IMediaController iMediaController) {
        IMediaController iMediaController2 = this.mMediaController;
        if (iMediaController2 != null) {
            iMediaController2.hide();
        }
        this.mMediaController = iMediaController;
        attachMediaController();
    }

    public void setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.outOnAudioFocusChangeListener = onAudioFocusChangeListener;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setOnPlayerEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.setOnPlayerEventListener(onPlayerEventListener);
        } else {
            super.setOnPlayerEventListener(onPlayerEventListener);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.setOnPlayerStateListener(onPlayerStateListener);
        } else {
            super.setOnPlayerStateListener(onPlayerStateListener);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setPlayerOptions(IPlayerControl.PlayerOptions playerOptions) {
        DebugLog.d(TAG, "[" + this.tag + "] setPlayerOptions");
        if (playerOptions == null) {
            return;
        }
        this.mPlayerOptions = playerOptions;
        if (playerOptions.sharePlayer && !PlayerConfigLoader.getInstance().enableShare) {
            resetTextureView();
            this.mPlayerOptions.sharePlayer = false;
        }
        IPlayerControl.PlayerOptions playerOptions2 = this.mPlayerOptions;
        if (playerOptions2 != null && !playerOptions2.sharePlayer) {
            IRenderView iRenderView = this.mRenderView;
            if (iRenderView == null) {
                DebugLog.d(TAG, "[" + this.tag + "] setPlayerOptions# initRenders");
                initRenders();
            } else {
                iRenderView.setAspectRatio(playerOptions2.aspectRatio);
                DebugLog.d(TAG, "[" + this.tag + "] setPlayerOptions# requestLayout");
                requestLayout();
            }
        }
        if (playerOptions.isShowHubView && this.mHudViewHolder == null) {
            this.mHudViewHolder = new InfoHudViewHolder(getContext(), this);
        }
        IPlayerControl.PlayerOptions playerOptions3 = this.mPlayerOptions;
        if (playerOptions3 != null) {
            this.expireTime = playerOptions3.expireTime;
        }
    }

    protected void setRenderView(IRenderView iRenderView) {
        int i2;
        int i3;
        this.isCouldOpenVideoTextureCreate = true;
        if (this.mRenderView != null) {
            DebugLog.d(TAG, "[" + this.tag + "] setRenderView === mRenderView!=null, removeView");
            IMediaPlayer iMediaPlayer = this.mMediaPlayer;
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallbacks();
            this.mRenderView = null;
            removeView(view);
        }
        if (iRenderView == null) {
            return;
        }
        if (iRenderView.getView() != null && iRenderView.getView().getParent() != null) {
            DebugLog.d(TAG, "[" + this.tag + "] setRenderView === remove from parent");
            ((ViewGroup) iRenderView.getView().getParent()).removeView(iRenderView.getView());
        }
        this.mRenderView = iRenderView;
        iRenderView.setAspectRatio(this.mPlayerOptions.aspectRatio);
        int i4 = this.mVideoWidth;
        if (i4 > 0 && (i3 = this.mVideoHeight) > 0) {
            iRenderView.setVideoSize(i4, i3);
        }
        int i5 = this.mVideoSarNum;
        if (i5 > 0 && (i2 = this.mVideoSarDen) > 0) {
            iRenderView.setVideoSampleAspectRatio(i5, i2);
        }
        View view2 = this.mRenderView.getView();
        view2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        addView(view2);
        this.mRenderView.addRenderCallback(this.mSHCallback);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setSpeed(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 2.0f) {
            f2 = 2.0f;
        }
        if (getIjkMediaPlayer() != null) {
            getIjkMediaPlayer().setSpeed(f2);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setVideoPath(String str) {
        if (getTag() != null && (getTag() instanceof Integer)) {
            this.tag = ((Integer) getTag()).intValue();
        }
        DebugLog.d(TAG, "[" + this.tag + "] setVideoPath: " + str);
        if (str == null) {
            str = "";
        }
        String trim = str.trim();
        this.originUrl = JDPlayerHelper.getInstance().updateProtocol(trim, this.mPlayerOptions);
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions.sharePlayer && !playerOptions.usePreDraw) {
            preDraw(trim, playerOptions);
        } else {
            setVideoURI(JDPlayerHelper.getInstance().generateUrl(trim, this.mPlayerOptions, this.mHeaders));
        }
    }

    public void setVideoPathWithoutOpen(String str) {
        this.originUrl = str;
        this.mUri = JDPlayerHelper.getInstance().generateUrl(str, this.mPlayerOptions, this.mHeaders);
        this.mSeekWhenPrepared = 0;
        DebugLog.d(TAG, "[" + this.tag + "] setVideoURI");
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setVideoURI(Uri uri) {
        this.isCouldOpenVideoTextureCreate = true;
        this.mUri = uri;
        if (uri != null && TextUtils.isEmpty(this.originUrl)) {
            this.originUrl = uri.toString();
        }
        this.mSeekWhenPrepared = 0;
        Uri uri2 = this.mUri;
        if (uri2 != null && uri2.toString().startsWith(JDPlayerConstant.LIVE_HOOK_HEAD)) {
            PlayerNetworkUtil.registerNetworkCallback(this.liveNetListener);
        }
        JDPlayerHelper.getInstance().addUAHeader(this.mPlayerOptions, this.mHeaders);
        DebugLog.d(TAG, "[" + this.tag + "] setVideoURI");
        openVideo();
        requestLayout();
        invalidate();
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setVolume(float f2) {
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions != null) {
            playerOptions.setVolume(f2);
        }
        checkAudioFocus();
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            float f3 = this.mPlayerOptions.volume;
            iMediaPlayer.setVolume(f3, f3);
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public void start() {
        DebugLog.d(TAG, "[" + this.tag + "] start");
        this.isCouldStartOnRendered = true;
        this.isCouldOpenVideoTextureCreate = true;
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            this.mPlayDurationStatistics.start();
        }
        this.mTargetState = 3;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void suspend() {
        DebugLog.d(TAG, "[" + this.tag + "] suspend");
        this.isCouldOpenVideoTextureCreate = false;
        release(true);
    }

    private void release(boolean z) {
        releaseInThread(z, true);
    }

    public void releaseInThread(boolean z, boolean z2) {
        releaseInThread(z, z2, true);
    }

    public void releaseInThread(boolean z, boolean z2, boolean z3) {
        IRenderView.IRenderCallback iRenderCallback;
        PlayerAudioHelper playerAudioHelper;
        if (this.inActive) {
            DebugLog.d(TAG, "[" + this.tag + "] releaseInThread");
            if (!PlayerConfigLoader.getInstance().enableShare) {
                z3 = true;
            }
            if (z3) {
                this.mPlayDurationStatistics.release();
                IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
                if (playerOptions != null && (playerOptions.isUsePreDraw() || this.mPlayerOptions.isSharePlayer())) {
                    JDPlayerManager.getInstance().releasePlayer(this.originUrl);
                }
                IMediaPlayer iMediaPlayer = this.mMediaPlayer;
                if (iMediaPlayer == null) {
                    return;
                }
                iMediaPlayer.setDisplay(null);
                if (this.mMediaPlayer.isPlaying()) {
                    this.mMediaPlayer.pause();
                }
                if (getHandler() != null && this.expireRunnable != null) {
                    getHandler().removeCallbacks(this.expireRunnable);
                }
                if (z2) {
                    this.mMediaPlayer.notifyPlayEvent(14);
                    final IMediaPlayer iMediaPlayer2 = this.mMediaPlayer;
                    VideoPlayerThreadManager.addTask(new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.12
                        {
                            JDVideoView.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            iMediaPlayer2.release();
                        }
                    });
                } else {
                    this.mMediaPlayer.release();
                }
                this.mMediaPlayer = null;
                if (this.mPlayerOptions.isRequestAudioFocus && (playerAudioHelper = this.playerAudioHelper) != null) {
                    playerAudioHelper.release();
                    this.playerAudioHelper = null;
                }
                resetTextureView();
                resetListener();
                this.mCurrentState = 0;
                if (z) {
                    this.mTargetState = 0;
                }
            } else {
                IRenderView iRenderView = this.mRenderView;
                if (iRenderView != null && (iRenderCallback = this.mSHCallback) != null) {
                    iRenderView.removeRenderCallback(iRenderCallback);
                }
            }
            PlayerNetworkUtil.unregisterNetworkCallback(this.liveNetListener);
            this.playerMonitor = null;
            this.playerMtaReport = null;
        }
    }

    public JDVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHeaders = new HashMap<>();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mBusinessId = "";
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.originUrl = "";
        this.preDrawAutoPlay = false;
        this.inActive = true;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.tag = -1;
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.2
            {
                JDVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JDVideoView.this.getContext() != null) {
                    JDVideoView jDVideoView = JDVideoView.this;
                    if (jDVideoView.mErrorListener == null) {
                        return;
                    }
                    jDVideoView.suspend();
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    jDVideoView2.mErrorListener.onError(((IJDVideoPlayer) jDVideoView2).mMediaPlayer, -10000, 489);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.3
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                JDVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                JDVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                JDVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                JDVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] OnVideoSizeChanged: mVideoWidth " + JDVideoView.this.mVideoWidth + " mVideoHeight " + JDVideoView.this.mVideoHeight + " mVideoSarNum " + JDVideoView.this.mVideoSarNum + " mVideoSarDen " + JDVideoView.this.mVideoSarDen + ", width = " + i2 + ", height = " + i3);
                JDVideoView jDVideoView = JDVideoView.this;
                if (jDVideoView.mVideoWidth == 0 || jDVideoView.mVideoHeight == 0) {
                    return;
                }
                Iterator it = ((IJDVideoPlayer) jDVideoView).mOnVideoSizeChangedListeners.iterator();
                while (it.hasNext()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    ((IPlayerControl.OnVideoSizeChangedListener) it.next()).onVideoSizeChanged(jDVideoView2.mVideoWidth, jDVideoView2.mVideoHeight);
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IRenderView iRenderView = jDVideoView3.mRenderView;
                if (iRenderView != null) {
                    iRenderView.setVideoSize(jDVideoView3.mVideoWidth, jDVideoView3.mVideoHeight);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.mRenderView.setVideoSampleAspectRatio(jDVideoView4.mVideoSarNum, jDVideoView4.mVideoSarDen);
                }
                JDVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.4
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer r5) {
                /*
                    Method dump skipped, instructions count: 285
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.example.widget.media.JDVideoView.AnonymousClass4.onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer):void");
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.5
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                JDVideoView jDVideoView;
                IPlayerControl.PlayerOptions playerOptions;
                JDVideoView.this.mPlayDurationStatistics.complete();
                JDVideoView.this.mCurrentState = 5;
                JDVideoView.this.mTargetState = 5;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (PlayerStringUtils.canPreSetLoop(JDVideoView.this.mUri) || (playerOptions = (jDVideoView = JDVideoView.this).mPlayerOptions) == null || !playerOptions.loop) {
                    return;
                }
                jDVideoView.start();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.6
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i3) {
                if (i2 == 3) {
                    IRenderView iRenderView = JDVideoView.this.mRenderView;
                    if (iRenderView != null) {
                        iRenderView.getView().setVisibility(0);
                    }
                    if (JDVideoView.this.imgCover != null && JDVideoView.this.imgCover.getParent() != null && (JDVideoView.this.imgCover.getParent() instanceof ViewGroup)) {
                        ((ViewGroup) JDVideoView.this.imgCover.getParent()).removeView(JDVideoView.this.imgCover);
                    }
                    if (!JDVideoView.this.isCouldStartOnRendered) {
                        JDVideoView.this.pause();
                    }
                    IPlayerControl.PlayerOptions playerOptions = JDVideoView.this.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay || JDVideoView.this.getIjkMediaPlayer() == null) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i2 == 10005) {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                    return true;
                } else if (i2 == 10008) {
                    IPlayerControl.PlayerOptions playerOptions2 = JDVideoView.this.mPlayerOptions;
                    if (playerOptions2 == null || !playerOptions2.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i2 != 10100) {
                    if (i2 != 30003) {
                        if (i2 == 901) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i2 == 902) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i2 == 10001) {
                            JDVideoView.this.mVideoRotationDegree = i3;
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i3);
                            IRenderView iRenderView2 = JDVideoView.this.mRenderView;
                            if (iRenderView2 != null) {
                                iRenderView2.setVideoRotation(i3);
                                return true;
                            }
                            return true;
                        } else if (i2 == 10002) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i2 != 10304) {
                            if (i2 != 10305) {
                                switch (i2) {
                                    case 700:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                        return true;
                                    case 701:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                        return true;
                                    case 702:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                        return true;
                                    case 703:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i3);
                                        return true;
                                    default:
                                        switch (i2) {
                                            case 800:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                                return true;
                                            case 801:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                                return true;
                                            case 802:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                                return true;
                                            default:
                                                DebugLog.d(JDVideoView.TAG, "unknown media info:" + i2 + " extra:" + i3);
                                                return true;
                                        }
                                }
                            }
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i3);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                    return true;
                } else {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                    return true;
                }
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.7
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i3) {
                IPlayerControl.PlayerOptions playerOptions;
                IPlayerControl.PlayerOptions playerOptions2;
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] Error: " + i2 + DYConstants.DY_REGEX_COMMA + i3);
                JDVideoView.this.mPlayDurationStatistics.error();
                JDVideoView.this.mCurrentState = -1;
                JDVideoView.this.mTargetState = -1;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (JDVideoView.this.getContext() != null && PlayerNetworkUtil.isNetworkConnected(JDVideoView.this.getContext()) && (playerOptions2 = JDVideoView.this.mPlayerOptions) != null && playerOptions2.useCache && !playerOptions2.sharePlayer && i2 != -1) {
                    DebugLog.d(JDVideoView.TAG, "Error: === remove cache, retry");
                    JDPlayerVideoCache.getInstance().getRawUri(JDVideoView.this.originUrl);
                    JDVideoView jDVideoView = JDVideoView.this;
                    jDVideoView.mPlayerOptions.useCache = false;
                    if (!TextUtils.isEmpty(jDVideoView.originUrl)) {
                        JDVideoView jDVideoView2 = JDVideoView.this;
                        jDVideoView2.setVideoURI(Uri.parse(jDVideoView2.originUrl));
                        return true;
                    }
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IPlayerControl.PlayerOptions playerOptions3 = jDVideoView3.mPlayerOptions;
                if (playerOptions3 != null && playerOptions3.isCouldMediaCodec && i3 == -1009 && jDVideoView3.mUri != null) {
                    DebugLog.d(JDVideoView.TAG, "Error: === disable mediacodec, retry");
                    JDVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.setVideoPath(jDVideoView4.originUrl);
                    return true;
                }
                if (CronetConfigLoader.quicEnable()) {
                    JDVideoView jDVideoView5 = JDVideoView.this;
                    if (jDVideoView5.mUri != null && PlayerStringUtils.isQuicProtocol(jDVideoView5.originUrl)) {
                        DebugLog.d(JDVideoView.TAG, "Error:  === disable quic, retry");
                        CronetConfigLoader.quicEnable = false;
                        JDVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(JDVideoView.this.mUri.toString())));
                        return true;
                    }
                }
                if (MediaInfoUtil.isDnsError(i3) && (playerOptions = JDVideoView.this.mPlayerOptions) != null && playerOptions.isAdvanceDns) {
                    playerOptions.isAdvanceDns = false;
                    DebugLog.d(JDVideoView.TAG, "Error:  === dns error quic, retry");
                    JDPlayerHelper.getInstance().addStreamHostIp(JDVideoView.this.originUrl, JDVideoView.this.mPlayerOptions, false);
                    if (!TextUtils.isEmpty(JDVideoView.this.originUrl)) {
                        JDVideoView jDVideoView6 = JDVideoView.this;
                        jDVideoView6.setVideoURI(Uri.parse(jDVideoView6.originUrl));
                        return true;
                    }
                }
                if (i3 == -858797304 && JDVideoView.this.expireTime != null) {
                    i3 = 489;
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                    ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayState(3, 0L, new int[]{i2, i3}, null);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.8
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2) {
                JDVideoView.this.mCurrentBufferPercentage = i2;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.9
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (JDVideoView.this.mHudViewHolder != null) {
                    JDVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - JDVideoView.this.mSeekStartTime);
                }
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.10
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || JDVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                JDVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.11
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3, int i4) {
                IRenderView renderView = iSurfaceHolder.getRenderView();
                JDVideoView jDVideoView = JDVideoView.this;
                if (renderView != jDVideoView.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                jDVideoView.mSurfaceWidth = i3;
                jDVideoView.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = jDVideoView.mTargetState == 3;
                if (JDVideoView.this.mRenderView.shouldWaitForResize()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    if (jDVideoView2.mVideoWidth != i3 || jDVideoView2.mVideoHeight != i4) {
                        z = false;
                    }
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (JDVideoView.this.mSeekWhenPrepared != 0) {
                        JDVideoView jDVideoView3 = JDVideoView.this;
                        jDVideoView3.seekTo(jDVideoView3.mSeekWhenPrepared);
                    }
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    IPlayerControl.PlayerOptions playerOptions = jDVideoView4.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.sharePlayer) {
                        jDVideoView4.start();
                    }
                }
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i2, i3, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated");
                JDVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer == null) {
                    if (JDVideoView.this.isCouldOpenVideoTextureCreate) {
                        JDVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                JDVideoView jDVideoView = JDVideoView.this;
                jDVideoView.bindSurfaceHolder(((IJDVideoPlayer) jDVideoView).mMediaPlayer, iSurfaceHolder);
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                if (JDPlayerSdk.debugEnable) {
                    DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: " + Thread.currentThread().getStackTrace().toString());
                }
                JDVideoView.this.mSurfaceHolder = null;
                JDVideoView.this.releaseWithoutStop();
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context, attributeSet);
    }

    public JDVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHeaders = new HashMap<>();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mBusinessId = "";
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.originUrl = "";
        this.preDrawAutoPlay = false;
        this.inActive = true;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.tag = -1;
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.2
            {
                JDVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JDVideoView.this.getContext() != null) {
                    JDVideoView jDVideoView = JDVideoView.this;
                    if (jDVideoView.mErrorListener == null) {
                        return;
                    }
                    jDVideoView.suspend();
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    jDVideoView2.mErrorListener.onError(((IJDVideoPlayer) jDVideoView2).mMediaPlayer, -10000, 489);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.3
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i22, int i3, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                JDVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                JDVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                JDVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                JDVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] OnVideoSizeChanged: mVideoWidth " + JDVideoView.this.mVideoWidth + " mVideoHeight " + JDVideoView.this.mVideoHeight + " mVideoSarNum " + JDVideoView.this.mVideoSarNum + " mVideoSarDen " + JDVideoView.this.mVideoSarDen + ", width = " + i22 + ", height = " + i3);
                JDVideoView jDVideoView = JDVideoView.this;
                if (jDVideoView.mVideoWidth == 0 || jDVideoView.mVideoHeight == 0) {
                    return;
                }
                Iterator it = ((IJDVideoPlayer) jDVideoView).mOnVideoSizeChangedListeners.iterator();
                while (it.hasNext()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    ((IPlayerControl.OnVideoSizeChangedListener) it.next()).onVideoSizeChanged(jDVideoView2.mVideoWidth, jDVideoView2.mVideoHeight);
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IRenderView iRenderView = jDVideoView3.mRenderView;
                if (iRenderView != null) {
                    iRenderView.setVideoSize(jDVideoView3.mVideoWidth, jDVideoView3.mVideoHeight);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.mRenderView.setVideoSampleAspectRatio(jDVideoView4.mVideoSarNum, jDVideoView4.mVideoSarDen);
                }
                JDVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.4
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer r5) {
                /*
                    Method dump skipped, instructions count: 285
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.example.widget.media.JDVideoView.AnonymousClass4.onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer):void");
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.5
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                JDVideoView jDVideoView;
                IPlayerControl.PlayerOptions playerOptions;
                JDVideoView.this.mPlayDurationStatistics.complete();
                JDVideoView.this.mCurrentState = 5;
                JDVideoView.this.mTargetState = 5;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (PlayerStringUtils.canPreSetLoop(JDVideoView.this.mUri) || (playerOptions = (jDVideoView = JDVideoView.this).mPlayerOptions) == null || !playerOptions.loop) {
                    return;
                }
                jDVideoView.start();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.6
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i22, int i3) {
                if (i22 == 3) {
                    IRenderView iRenderView = JDVideoView.this.mRenderView;
                    if (iRenderView != null) {
                        iRenderView.getView().setVisibility(0);
                    }
                    if (JDVideoView.this.imgCover != null && JDVideoView.this.imgCover.getParent() != null && (JDVideoView.this.imgCover.getParent() instanceof ViewGroup)) {
                        ((ViewGroup) JDVideoView.this.imgCover.getParent()).removeView(JDVideoView.this.imgCover);
                    }
                    if (!JDVideoView.this.isCouldStartOnRendered) {
                        JDVideoView.this.pause();
                    }
                    IPlayerControl.PlayerOptions playerOptions = JDVideoView.this.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay || JDVideoView.this.getIjkMediaPlayer() == null) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i22 == 10005) {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                    return true;
                } else if (i22 == 10008) {
                    IPlayerControl.PlayerOptions playerOptions2 = JDVideoView.this.mPlayerOptions;
                    if (playerOptions2 == null || !playerOptions2.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i22 != 10100) {
                    if (i22 != 30003) {
                        if (i22 == 901) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i22 == 902) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i22 == 10001) {
                            JDVideoView.this.mVideoRotationDegree = i3;
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i3);
                            IRenderView iRenderView2 = JDVideoView.this.mRenderView;
                            if (iRenderView2 != null) {
                                iRenderView2.setVideoRotation(i3);
                                return true;
                            }
                            return true;
                        } else if (i22 == 10002) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i22 != 10304) {
                            if (i22 != 10305) {
                                switch (i22) {
                                    case 700:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                        return true;
                                    case 701:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                        return true;
                                    case 702:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                        return true;
                                    case 703:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i3);
                                        return true;
                                    default:
                                        switch (i22) {
                                            case 800:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                                return true;
                                            case 801:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                                return true;
                                            case 802:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                                return true;
                                            default:
                                                DebugLog.d(JDVideoView.TAG, "unknown media info:" + i22 + " extra:" + i3);
                                                return true;
                                        }
                                }
                            }
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i3);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                    return true;
                } else {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                    return true;
                }
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.7
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i22, int i3) {
                IPlayerControl.PlayerOptions playerOptions;
                IPlayerControl.PlayerOptions playerOptions2;
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] Error: " + i22 + DYConstants.DY_REGEX_COMMA + i3);
                JDVideoView.this.mPlayDurationStatistics.error();
                JDVideoView.this.mCurrentState = -1;
                JDVideoView.this.mTargetState = -1;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (JDVideoView.this.getContext() != null && PlayerNetworkUtil.isNetworkConnected(JDVideoView.this.getContext()) && (playerOptions2 = JDVideoView.this.mPlayerOptions) != null && playerOptions2.useCache && !playerOptions2.sharePlayer && i22 != -1) {
                    DebugLog.d(JDVideoView.TAG, "Error: === remove cache, retry");
                    JDPlayerVideoCache.getInstance().getRawUri(JDVideoView.this.originUrl);
                    JDVideoView jDVideoView = JDVideoView.this;
                    jDVideoView.mPlayerOptions.useCache = false;
                    if (!TextUtils.isEmpty(jDVideoView.originUrl)) {
                        JDVideoView jDVideoView2 = JDVideoView.this;
                        jDVideoView2.setVideoURI(Uri.parse(jDVideoView2.originUrl));
                        return true;
                    }
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IPlayerControl.PlayerOptions playerOptions3 = jDVideoView3.mPlayerOptions;
                if (playerOptions3 != null && playerOptions3.isCouldMediaCodec && i3 == -1009 && jDVideoView3.mUri != null) {
                    DebugLog.d(JDVideoView.TAG, "Error: === disable mediacodec, retry");
                    JDVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.setVideoPath(jDVideoView4.originUrl);
                    return true;
                }
                if (CronetConfigLoader.quicEnable()) {
                    JDVideoView jDVideoView5 = JDVideoView.this;
                    if (jDVideoView5.mUri != null && PlayerStringUtils.isQuicProtocol(jDVideoView5.originUrl)) {
                        DebugLog.d(JDVideoView.TAG, "Error:  === disable quic, retry");
                        CronetConfigLoader.quicEnable = false;
                        JDVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(JDVideoView.this.mUri.toString())));
                        return true;
                    }
                }
                if (MediaInfoUtil.isDnsError(i3) && (playerOptions = JDVideoView.this.mPlayerOptions) != null && playerOptions.isAdvanceDns) {
                    playerOptions.isAdvanceDns = false;
                    DebugLog.d(JDVideoView.TAG, "Error:  === dns error quic, retry");
                    JDPlayerHelper.getInstance().addStreamHostIp(JDVideoView.this.originUrl, JDVideoView.this.mPlayerOptions, false);
                    if (!TextUtils.isEmpty(JDVideoView.this.originUrl)) {
                        JDVideoView jDVideoView6 = JDVideoView.this;
                        jDVideoView6.setVideoURI(Uri.parse(jDVideoView6.originUrl));
                        return true;
                    }
                }
                if (i3 == -858797304 && JDVideoView.this.expireTime != null) {
                    i3 = 489;
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                    ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayState(3, 0L, new int[]{i22, i3}, null);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.8
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i22) {
                JDVideoView.this.mCurrentBufferPercentage = i22;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.9
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (JDVideoView.this.mHudViewHolder != null) {
                    JDVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - JDVideoView.this.mSeekStartTime);
                }
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.10
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || JDVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                JDVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.11
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i3, int i4) {
                IRenderView renderView = iSurfaceHolder.getRenderView();
                JDVideoView jDVideoView = JDVideoView.this;
                if (renderView != jDVideoView.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                jDVideoView.mSurfaceWidth = i3;
                jDVideoView.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = jDVideoView.mTargetState == 3;
                if (JDVideoView.this.mRenderView.shouldWaitForResize()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    if (jDVideoView2.mVideoWidth != i3 || jDVideoView2.mVideoHeight != i4) {
                        z = false;
                    }
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (JDVideoView.this.mSeekWhenPrepared != 0) {
                        JDVideoView jDVideoView3 = JDVideoView.this;
                        jDVideoView3.seekTo(jDVideoView3.mSeekWhenPrepared);
                    }
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    IPlayerControl.PlayerOptions playerOptions = jDVideoView4.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.sharePlayer) {
                        jDVideoView4.start();
                    }
                }
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i22, i3, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i3) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated");
                JDVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer == null) {
                    if (JDVideoView.this.isCouldOpenVideoTextureCreate) {
                        JDVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                JDVideoView jDVideoView = JDVideoView.this;
                jDVideoView.bindSurfaceHolder(((IJDVideoPlayer) jDVideoView).mMediaPlayer, iSurfaceHolder);
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                if (JDPlayerSdk.debugEnable) {
                    DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: " + Thread.currentThread().getStackTrace().toString());
                }
                JDVideoView.this.mSurfaceHolder = null;
                JDVideoView.this.releaseWithoutStop();
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context, attributeSet);
    }

    @TargetApi(21)
    public JDVideoView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mHeaders = new HashMap<>();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mBusinessId = "";
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.originUrl = "";
        this.preDrawAutoPlay = false;
        this.inActive = true;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.tag = -1;
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.2
            {
                JDVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JDVideoView.this.getContext() != null) {
                    JDVideoView jDVideoView = JDVideoView.this;
                    if (jDVideoView.mErrorListener == null) {
                        return;
                    }
                    jDVideoView.suspend();
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    jDVideoView2.mErrorListener.onError(((IJDVideoPlayer) jDVideoView2).mMediaPlayer, -10000, 489);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.3
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i22, int i32, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                JDVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                JDVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                JDVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                JDVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] OnVideoSizeChanged: mVideoWidth " + JDVideoView.this.mVideoWidth + " mVideoHeight " + JDVideoView.this.mVideoHeight + " mVideoSarNum " + JDVideoView.this.mVideoSarNum + " mVideoSarDen " + JDVideoView.this.mVideoSarDen + ", width = " + i22 + ", height = " + i32);
                JDVideoView jDVideoView = JDVideoView.this;
                if (jDVideoView.mVideoWidth == 0 || jDVideoView.mVideoHeight == 0) {
                    return;
                }
                Iterator it = ((IJDVideoPlayer) jDVideoView).mOnVideoSizeChangedListeners.iterator();
                while (it.hasNext()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    ((IPlayerControl.OnVideoSizeChangedListener) it.next()).onVideoSizeChanged(jDVideoView2.mVideoWidth, jDVideoView2.mVideoHeight);
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IRenderView iRenderView = jDVideoView3.mRenderView;
                if (iRenderView != null) {
                    iRenderView.setVideoSize(jDVideoView3.mVideoWidth, jDVideoView3.mVideoHeight);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.mRenderView.setVideoSampleAspectRatio(jDVideoView4.mVideoSarNum, jDVideoView4.mVideoSarDen);
                }
                JDVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.4
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer r5) {
                /*
                    Method dump skipped, instructions count: 285
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.example.widget.media.JDVideoView.AnonymousClass4.onPrepared(tv.danmaku.ijk.media.player.IMediaPlayer):void");
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.5
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                JDVideoView jDVideoView;
                IPlayerControl.PlayerOptions playerOptions;
                JDVideoView.this.mPlayDurationStatistics.complete();
                JDVideoView.this.mCurrentState = 5;
                JDVideoView.this.mTargetState = 5;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (PlayerStringUtils.canPreSetLoop(JDVideoView.this.mUri) || (playerOptions = (jDVideoView = JDVideoView.this).mPlayerOptions) == null || !playerOptions.loop) {
                    return;
                }
                jDVideoView.start();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.6
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i22, int i32) {
                if (i22 == 3) {
                    IRenderView iRenderView = JDVideoView.this.mRenderView;
                    if (iRenderView != null) {
                        iRenderView.getView().setVisibility(0);
                    }
                    if (JDVideoView.this.imgCover != null && JDVideoView.this.imgCover.getParent() != null && (JDVideoView.this.imgCover.getParent() instanceof ViewGroup)) {
                        ((ViewGroup) JDVideoView.this.imgCover.getParent()).removeView(JDVideoView.this.imgCover);
                    }
                    if (!JDVideoView.this.isCouldStartOnRendered) {
                        JDVideoView.this.pause();
                    }
                    IPlayerControl.PlayerOptions playerOptions = JDVideoView.this.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay || JDVideoView.this.getIjkMediaPlayer() == null) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i22 == 10005) {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                    return true;
                } else if (i22 == 10008) {
                    IPlayerControl.PlayerOptions playerOptions2 = JDVideoView.this.mPlayerOptions;
                    if (playerOptions2 == null || !playerOptions2.isUsePreDraw() || JDVideoView.this.preDrawAutoPlay) {
                        return true;
                    }
                    JDVideoView.this.pause();
                    return true;
                } else if (i22 != 10100) {
                    if (i22 != 30003) {
                        if (i22 == 901) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i22 == 902) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i22 == 10001) {
                            JDVideoView.this.mVideoRotationDegree = i32;
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i32);
                            IRenderView iRenderView2 = JDVideoView.this.mRenderView;
                            if (iRenderView2 != null) {
                                iRenderView2.setVideoRotation(i32);
                                return true;
                            }
                            return true;
                        } else if (i22 == 10002) {
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i22 != 10304) {
                            if (i22 != 10305) {
                                switch (i22) {
                                    case 700:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                        return true;
                                    case 701:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                        return true;
                                    case 702:
                                        if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                                            ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                        }
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                        return true;
                                    case 703:
                                        DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i32);
                                        return true;
                                    default:
                                        switch (i22) {
                                            case 800:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                                return true;
                                            case 801:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                                return true;
                                            case 802:
                                                DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                                return true;
                                            default:
                                                DebugLog.d(JDVideoView.TAG, "unknown media info:" + i22 + " extra:" + i32);
                                                return true;
                                        }
                                }
                            }
                            DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i32);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                    return true;
                } else {
                    DebugLog.d(JDVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                    return true;
                }
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.7
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i22, int i32) {
                IPlayerControl.PlayerOptions playerOptions;
                IPlayerControl.PlayerOptions playerOptions2;
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] Error: " + i22 + DYConstants.DY_REGEX_COMMA + i32);
                JDVideoView.this.mPlayDurationStatistics.error();
                JDVideoView.this.mCurrentState = -1;
                JDVideoView.this.mTargetState = -1;
                if (JDVideoView.this.mMediaController != null) {
                    JDVideoView.this.mMediaController.hide();
                }
                if (JDVideoView.this.getContext() != null && PlayerNetworkUtil.isNetworkConnected(JDVideoView.this.getContext()) && (playerOptions2 = JDVideoView.this.mPlayerOptions) != null && playerOptions2.useCache && !playerOptions2.sharePlayer && i22 != -1) {
                    DebugLog.d(JDVideoView.TAG, "Error: === remove cache, retry");
                    JDPlayerVideoCache.getInstance().getRawUri(JDVideoView.this.originUrl);
                    JDVideoView jDVideoView = JDVideoView.this;
                    jDVideoView.mPlayerOptions.useCache = false;
                    if (!TextUtils.isEmpty(jDVideoView.originUrl)) {
                        JDVideoView jDVideoView2 = JDVideoView.this;
                        jDVideoView2.setVideoURI(Uri.parse(jDVideoView2.originUrl));
                        return true;
                    }
                }
                JDVideoView jDVideoView3 = JDVideoView.this;
                IPlayerControl.PlayerOptions playerOptions3 = jDVideoView3.mPlayerOptions;
                if (playerOptions3 != null && playerOptions3.isCouldMediaCodec && i32 == -1009 && jDVideoView3.mUri != null) {
                    DebugLog.d(JDVideoView.TAG, "Error: === disable mediacodec, retry");
                    JDVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    jDVideoView4.setVideoPath(jDVideoView4.originUrl);
                    return true;
                }
                if (CronetConfigLoader.quicEnable()) {
                    JDVideoView jDVideoView5 = JDVideoView.this;
                    if (jDVideoView5.mUri != null && PlayerStringUtils.isQuicProtocol(jDVideoView5.originUrl)) {
                        DebugLog.d(JDVideoView.TAG, "Error:  === disable quic, retry");
                        CronetConfigLoader.quicEnable = false;
                        JDVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(JDVideoView.this.mUri.toString())));
                        return true;
                    }
                }
                if (MediaInfoUtil.isDnsError(i32) && (playerOptions = JDVideoView.this.mPlayerOptions) != null && playerOptions.isAdvanceDns) {
                    playerOptions.isAdvanceDns = false;
                    DebugLog.d(JDVideoView.TAG, "Error:  === dns error quic, retry");
                    JDPlayerHelper.getInstance().addStreamHostIp(JDVideoView.this.originUrl, JDVideoView.this.mPlayerOptions, false);
                    if (!TextUtils.isEmpty(JDVideoView.this.originUrl)) {
                        JDVideoView jDVideoView6 = JDVideoView.this;
                        jDVideoView6.setVideoURI(Uri.parse(jDVideoView6.originUrl));
                        return true;
                    }
                }
                if (i32 == -858797304 && JDVideoView.this.expireTime != null) {
                    i32 = 489;
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null) {
                    ((IJDVideoPlayer) JDVideoView.this).mMediaPlayer.notifyPlayState(3, 0L, new int[]{i22, i32}, null);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.8
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i22) {
                JDVideoView.this.mCurrentBufferPercentage = i22;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.9
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (JDVideoView.this.mHudViewHolder != null) {
                    JDVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - JDVideoView.this.mSeekStartTime);
                }
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.10
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || JDVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                JDVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.JDVideoView.11
            {
                JDVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i32, int i4) {
                IRenderView renderView = iSurfaceHolder.getRenderView();
                JDVideoView jDVideoView = JDVideoView.this;
                if (renderView != jDVideoView.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                jDVideoView.mSurfaceWidth = i32;
                jDVideoView.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = jDVideoView.mTargetState == 3;
                if (JDVideoView.this.mRenderView.shouldWaitForResize()) {
                    JDVideoView jDVideoView2 = JDVideoView.this;
                    if (jDVideoView2.mVideoWidth != i32 || jDVideoView2.mVideoHeight != i4) {
                        z = false;
                    }
                }
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (JDVideoView.this.mSeekWhenPrepared != 0) {
                        JDVideoView jDVideoView3 = JDVideoView.this;
                        jDVideoView3.seekTo(jDVideoView3.mSeekWhenPrepared);
                    }
                    JDVideoView jDVideoView4 = JDVideoView.this;
                    IPlayerControl.PlayerOptions playerOptions = jDVideoView4.mPlayerOptions;
                    if (playerOptions == null || !playerOptions.sharePlayer) {
                        jDVideoView4.start();
                    }
                }
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i22, i32, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i32) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceCreated");
                JDVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) JDVideoView.this).mMediaPlayer == null) {
                    if (JDVideoView.this.isCouldOpenVideoTextureCreate) {
                        JDVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                JDVideoView jDVideoView = JDVideoView.this;
                jDVideoView.bindSurfaceHolder(((IJDVideoPlayer) jDVideoView).mMediaPlayer, iSurfaceHolder);
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != JDVideoView.this.mRenderView) {
                    DebugLog.e(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                if (JDPlayerSdk.debugEnable) {
                    DebugLog.d(JDVideoView.TAG, "[" + JDVideoView.this.tag + "] onSurfaceDestroyed: " + Thread.currentThread().getStackTrace().toString());
                }
                JDVideoView.this.mSurfaceHolder = null;
                JDVideoView.this.releaseWithoutStop();
                if (JDVideoView.this.mSurfaceHolderCallback != null) {
                    JDVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context, attributeSet);
    }
}
