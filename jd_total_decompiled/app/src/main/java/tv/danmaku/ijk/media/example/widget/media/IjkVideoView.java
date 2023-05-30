package tv.danmaku.ijk.media.example.widget.media;

import android.annotation.TargetApi;
import android.content.Context;
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
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import com.jdcloud.vsr.JDTVSRRender;
import com.jingdong.common.utils.LangUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.cronet.CronetConfigLoader;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IRenderView;
import tv.danmaku.ijk.media.ext.cache.JDPlayerVideoCache;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.ext.identify.PlayerNetHeaderUtil;
import tv.danmaku.ijk.media.ext.mta.PlayerMtaReport;
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
import tv.danmaku.ijk.media.vsr.JDTVSRTextureView;

/* loaded from: classes11.dex */
public class IjkVideoView extends IJDVideoPlayer {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    public static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    public static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    protected static String TAG = "IjkVideoView";
    private static boolean isForceAndroidPlayer;
    private AtomicBoolean bIsForceRelease;
    private final Runnable expireRunnable;
    private Long expireTime;
    private boolean isCouldOpenVideoTextureCreate;
    private boolean isCouldStartOnRendered;
    private final PlayerNetworkUtil.NetChangeListener liveNetListener;
    private Context mAppContext;
    private IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private String mBusinessId;
    private IMediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private IMediaPlayer.OnErrorListener mErrorListener;
    private HashMap<String, String> mHeaders;
    private InfoHudViewHolder mHudViewHolder;
    private IMediaPlayer.OnInfoListener mInfoListener;
    private IMediaController mMediaController;
    private IMediaPlayer.OnExtInfoListener mOnExtInfoListener;
    private IMediaPlayer.OnPlayerEventListener mOnPlayerEventListener;
    protected IPlayerControl.OnPlayerExtInfoListener mOnPlayerExtInfoListener;
    private PlayDurationStatistics mPlayDurationStatistics;
    private IPlayerControl.OnPlayerStateListener mPlayerListener;
    private PlayerPerformMonitor mPlayerMonitor;
    private IPlayerControl.PlayerOptions mPlayerOptions;
    private long mPrepareStartTime;
    private IMediaPlayer.OnPreparedListener mPreparedListener;
    private IRenderView mRenderView;
    private IRenderView.IRenderCallback mSHCallback;
    private IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    private long mSeekStartTime;
    private int mSeekWhenPrepared;
    private IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private int mSurfaceHeight;
    private IRenderView.ISurfaceHolder mSurfaceHolder;
    private SurfaceHolder.Callback mSurfaceHolderCallback;
    private int mSurfaceWidth;
    private int mTargetState;
    private Uri mUri;
    private JDTVSRRender mVSRRender;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private IPlayerControl.OnVideoSizeChangedListener mVideoSizeChangedListener;
    private int mVideoWidth;
    private String originUrl;
    private AudioManager.OnAudioFocusChangeListener outOnAudioFocusChangeListener;
    private PlayerAudioHelper playerAudioHelper;
    private PlayerMtaReport playerMtaReport;
    private RawDataSourceProvider rawDataSourceProvider;
    private long setVideoPathTime;

    public IjkVideoView(Context context) {
        super(context);
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.originUrl = "";
        this.mBusinessId = "";
        this.mPrepareStartTime = 0L;
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.mOnExtInfoListener = new IMediaPlayer.OnExtInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.2
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnExtInfoListener
            public void onExtInfo(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, HashMap<String, Object> hashMap) {
                IjkVideoView.this.onExtInfo(iMediaPlayer, i2, i3, i4, hashMap);
            }
        };
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.3
            {
                IjkVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                IjkVideoView.this.suspend();
                if (IjkVideoView.this.mErrorListener == null) {
                    return;
                }
                IjkVideoView.this.mErrorListener.onError(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer, -10000, 489);
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.4
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || IjkVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                IjkVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.5
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(IjkVideoView.TAG, "OnVideoSizeChanged: mVideoWidth " + IjkVideoView.this.mVideoWidth + " mVideoHeight " + IjkVideoView.this.mVideoHeight + " mVideoSarNum " + IjkVideoView.this.mVideoSarNum + " mVideoSarDen " + IjkVideoView.this.mVideoSarDen + ", width = " + i2 + ", height = " + i3);
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mVideoSizeChangedListener != null) {
                    IjkVideoView.this.mVideoSizeChangedListener.onVideoSizeChanged(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.enableVsr(ijkVideoView.mVideoWidth, IjkVideoView.this.mVideoHeight);
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateLoadCost(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.playerAudioHelper != null && IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isLive) {
                    IjkVideoView.this.playerAudioHelper.enhanceAudio(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer);
                }
                IjkVideoView.this.mCurrentState = 2;
                DebugLog.d(IjkVideoView.TAG, "onPrepared " + (elapsedRealtime - IjkVideoView.this.mPrepareStartTime));
                if (IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mPlayDurationStatistics.start();
                    if ((!MediaPlayerHelper.isLoadJniOk || IjkVideoView.this.mPlayerOptions.isForceAndroidPlayer) && ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.start();
                    }
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onPrepared(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                if (iMediaPlayer != null) {
                    IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                    IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                }
                int i2 = IjkVideoView.this.mSeekWhenPrepared;
                if (i2 != 0) {
                    IjkVideoView.this.seekTo(i2);
                }
                if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mTargetState = 3;
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 3) {
                        IjkVideoView.this.start();
                    }
                } else if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 3) {
                            if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && !((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.isPlaying()) {
                                IjkVideoView.this.start();
                            }
                            if (IjkVideoView.this.mMediaController != null && (IjkVideoView.this.mPlayerOptions == null || IjkVideoView.this.mPlayerOptions.showControllerOnStart)) {
                                IjkVideoView.this.mMediaController.show();
                            }
                        } else if (!IjkVideoView.this.isPlaying() && ((i2 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null)) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
                IjkVideoView.this.postDelayed(new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (IjkVideoView.this.mRenderView == null || IjkVideoView.this.mRenderView.getView().getVisibility() == 0) {
                            return;
                        }
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                }, 200L);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.7
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPlayDurationStatistics.complete();
                IjkVideoView.this.mCurrentState = 5;
                IjkVideoView.this.mTargetState = 5;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onCompletion();
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.8
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i3) {
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onInfo(i2, i3);
                }
                if (i2 == 3) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                    if (IjkVideoView.this.isCouldStartOnRendered) {
                        return true;
                    }
                    IjkVideoView.this.pause();
                    return true;
                }
                if (i2 != 10005) {
                    if (i2 == 10100) {
                        DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                        return true;
                    } else if (i2 != 30003) {
                        if (i2 == 901) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i2 == 902) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i2 == 10001) {
                            IjkVideoView.this.mVideoRotationDegree = i3;
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i3);
                            if (IjkVideoView.this.mRenderView != null) {
                                IjkVideoView.this.mRenderView.setVideoRotation(i3);
                                return true;
                            }
                            return true;
                        } else if (i2 == 10002) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i2 == 10304) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                            return true;
                        } else if (i2 != 10305) {
                            switch (i2) {
                                case 700:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                    return true;
                                case 701:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                    return true;
                                case 702:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                    return true;
                                case 703:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i3);
                                    return true;
                                default:
                                    switch (i2) {
                                        case 800:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                            return true;
                                        case 801:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                            return true;
                                        case 802:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                            return true;
                                        default:
                                            DebugLog.d(IjkVideoView.TAG, "unknown media info:" + i2 + " extra:" + i3);
                                            return true;
                                    }
                            }
                        } else {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i3);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                }
                DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.9
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i3) {
                DebugLog.d(IjkVideoView.TAG, "Error: " + i2 + DYConstants.DY_REGEX_COMMA + i3);
                IjkVideoView.this.mPlayDurationStatistics.error();
                IjkVideoView.this.mCurrentState = -1;
                IjkVideoView.this.mTargetState = -1;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.getContext() == null || !PlayerNetworkUtil.isNetworkConnected(IjkVideoView.this.getContext()) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.useCache || i2 == -1) {
                    if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isCouldMediaCodec && i3 == -1009 && IjkVideoView.this.mUri != null) {
                        IjkVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.setVideoURI(ijkVideoView.mUri);
                        return true;
                    } else if (CronetConfigLoader.quicEnable() && IjkVideoView.this.mUri != null && PlayerStringUtils.isQuicProtocol(IjkVideoView.this.originUrl)) {
                        CronetConfigLoader.quicEnable = false;
                        IjkVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(IjkVideoView.this.mUri.toString())));
                        return true;
                    } else if (!MediaInfoUtil.isDnsError(i3) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.isAdvanceDns) {
                        if (i3 == -858797304 && IjkVideoView.this.expireTime != null) {
                            i3 = 489;
                        }
                        if (IjkVideoView.this.mPlayerListener == null || IjkVideoView.this.mPlayerListener.onError(i2, i3)) {
                        }
                        return true;
                    } else {
                        IjkVideoView.this.mPlayerOptions.isAdvanceDns = false;
                        JDPlayerHelper.getInstance().addStreamHostIp(IjkVideoView.this.originUrl, IjkVideoView.this.mPlayerOptions, false);
                        IjkVideoView ijkVideoView2 = IjkVideoView.this;
                        ijkVideoView2.setVideoURI(Uri.parse(ijkVideoView2.originUrl));
                        return true;
                    }
                }
                JDPlayerVideoCache.getInstance().getRawUri(IjkVideoView.this.originUrl);
                IjkVideoView.this.mPlayerOptions.useCache = false;
                IjkVideoView ijkVideoView3 = IjkVideoView.this;
                ijkVideoView3.setVideoURI(Uri.parse(ijkVideoView3.originUrl));
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.10
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2) {
                IjkVideoView.this.mCurrentBufferPercentage = i2;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.11
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - IjkVideoView.this.mSeekStartTime);
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onSeekComplete();
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.12
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3, int i4) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceChanged");
                IjkVideoView.this.mSurfaceWidth = i3;
                IjkVideoView.this.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 3;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i3 || IjkVideoView.this.mVideoHeight != i4)) {
                    z = false;
                }
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i2, i3, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceCreated");
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer == null) {
                    if (IjkVideoView.this.isCouldOpenVideoTextureCreate) {
                        IjkVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.bindSurfaceHolder(((IJDVideoPlayer) ijkVideoView).mMediaPlayer, iSurfaceHolder);
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceDestroyed");
                IjkVideoView.this.mSurfaceHolder = null;
                IjkVideoView.this.releaseWithoutStop();
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context);
    }

    private void addUAHeader() {
        if (PlayerNetHeaderUtil.canUse()) {
            addHeaders("User-Agent", PlayerNetHeaderUtil.generateUA());
            PlayerNetHeaderUtil.PlayerType playerType = PlayerNetHeaderUtil.PlayerType.IJK_SH;
            IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
            if (playerOptions != null) {
                if (playerOptions.unVideo) {
                    playerType = PlayerNetHeaderUtil.PlayerType.IJK_BJ;
                } else if (playerOptions.isForceAndroidPlayer) {
                    playerType = PlayerNetHeaderUtil.PlayerType.MEDIA_SH;
                }
            }
            addHeaders("Referer", PlayerNetHeaderUtil.generateReferer(playerType));
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

    public static IjkVideoView createAndroidVideoView(Context context) {
        isForceAndroidPlayer = true;
        return new IjkVideoView(context);
    }

    public void enableVsr(int i2, int i3) {
        boolean z = i2 <= 720 || i3 <= 720;
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView == null || !(iRenderView instanceof JDTVSRTextureView)) {
            return;
        }
        boolean enableVsr = ((JDTVSRTextureView) iRenderView).enableVsr(z);
        PlayerPerformMonitor playerPerformMonitor = this.mPlayerMonitor;
        if (playerPerformMonitor != null) {
            playerPerformMonitor.updateVsrInfo(enableVsr);
        }
    }

    private void generateRealUrl() {
        Uri uri = this.mUri;
        if (uri != null) {
            IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
            if (playerOptions == null || !playerOptions.isForceAndroidPlayer) {
                String uri2 = uri.toString();
                IPlayerControl.PlayerOptions playerOptions2 = this.mPlayerOptions;
                if (playerOptions2 != null && playerOptions2.isLive && PlayerStringUtils.isLiveDomain(uri2) && CronetConfigLoader.quicEnable() && !PlayerStringUtils.isRtmpStream(uri2) && IjkMediaPlayer.hasLoadCronet) {
                    if (uri2.startsWith("https")) {
                        uri2 = uri2.replaceFirst("https", "quics");
                    } else if (uri2.startsWith("http")) {
                        uri2 = uri2.replaceFirst("http", "quic");
                    }
                }
                IPlayerControl.PlayerOptions playerOptions3 = this.mPlayerOptions;
                if (playerOptions3 != null && playerOptions3.reconnectCount > 0 && PlayerStringUtils.isValidUrl(uri2)) {
                    if (!this.mPlayerOptions.isLive) {
                        uri2 = JDPlayerConstant.HTTP_HOOK_HEAD + uri2;
                    } else if (!PlayerStringUtils.isRtmpStream(uri2)) {
                        PlayerNetworkUtil.registerNetworkCallback(this.liveNetListener);
                        uri2 = JDPlayerConstant.LIVE_HOOK_HEAD + uri2;
                    }
                }
                this.mUri = Uri.parse(uri2);
            }
        }
    }

    private void initVideoView(Context context) {
        if (isForceAndroidPlayer) {
            isForceAndroidPlayer = false;
            MediaPlayerHelper.isLoadJniOk = false;
        } else {
            MediaPlayerHelper.loadLibrariesOnceSafe(context);
        }
        this.mAppContext = context.getApplicationContext();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mPlayDurationStatistics = new PlayDurationStatistics();
    }

    private void initVsr() {
        JDTVSRRender jDTVSRRender = new JDTVSRRender();
        this.mVSRRender = jDTVSRRender;
        jDTVSRRender.init(new JDTVSRRender.VSRCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.1
            {
                IjkVideoView.this = this;
            }

            @Override // com.jdcloud.vsr.JDTVSRRender.VSRCallback
            public void onError(int i2, String str) {
                DebugLog.d(IjkVideoView.TAG, "vsr[onError] === errorCode:" + i2 + ", errorMsg:" + str);
            }

            @Override // com.jdcloud.vsr.JDTVSRRender.VSRCallback
            public void onEvent(int i2, String str) {
                DebugLog.d(IjkVideoView.TAG, "vsr[onEvent] === eventId:" + i2 + ", msg:" + str);
            }
        });
    }

    private String insertIp(String str) {
        if (TextUtils.isEmpty(this.mPlayerOptions.host) || TextUtils.isEmpty(this.mPlayerOptions.ip)) {
            return str;
        }
        if (str.startsWith(JDPlayerConstant.LIVE_RTMP_HEAD + this.mPlayerOptions.host)) {
            this.mPlayerOptions.addCustomOption(1, "rtmp_tcurl", str);
            IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
            return IjkUtils.replaceOnce(str, playerOptions.host, playerOptions.ip);
        }
        if (!str.startsWith("http://" + this.mPlayerOptions.host)) {
            if (!str.startsWith("https://" + this.mPlayerOptions.host)) {
                return str;
            }
        }
        IPlayerControl.PlayerOptions playerOptions2 = this.mPlayerOptions;
        String replaceOnce = IjkUtils.replaceOnce(str, playerOptions2.host, playerOptions2.ip);
        addHeaders(HttpHeaders.HOST, this.mPlayerOptions.host);
        return replaceOnce;
    }

    private boolean isInPlaybackState() {
        int i2;
        return (this.mMediaPlayer == null || (i2 = this.mCurrentState) == -1 || i2 == 0 || i2 == 1) ? false : true;
    }

    public void openVideo() {
        DebugLog.d(TAG, "openVideo 0");
        if (this.bIsForceRelease.get() || this.mSurfaceHolder == null) {
            return;
        }
        if (this.mUri == null && this.rawDataSourceProvider == null) {
            return;
        }
        DebugLog.d(TAG, "openVideo 1");
        release(false);
        generateRealUrl();
        this.isCouldStartOnRendered = true;
        IPlayerControl.OnPlayerStateListener onPlayerStateListener = this.mPlayerListener;
        if (onPlayerStateListener != null) {
            onPlayerStateListener.onCreatePlayer();
        }
        checkAudioFocus();
        try {
            this.mMediaPlayer = MediaPlayerHelper.createPlayer(this.mPlayerOptions);
            PlayerConfigLoader.getInstance().applyConfig(getIjkMediaPlayer(), this.mPlayerOptions, this.mUri);
            this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
            this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
            this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
            this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
            this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
            this.mMediaPlayer.setOnExtInfoListener(this.mOnExtInfoListener);
            IMediaPlayer.OnPlayerEventListener onPlayerEventListener = this.mOnPlayerEventListener;
            if (onPlayerEventListener != null) {
                this.mMediaPlayer.setOnPlayerEventListener(onPlayerEventListener);
            }
            this.mCurrentBufferPercentage = 0;
            if (this.mPlayerOptions.enableMta) {
                if (this.playerMtaReport == null) {
                    this.playerMtaReport = new PlayerMtaReport(getContext(), this.mMediaPlayer, this.mPlayerOptions, this.originUrl);
                }
                this.playerMtaReport.enableReport(true);
            }
            RawDataSourceProvider rawDataSourceProvider = this.rawDataSourceProvider;
            if (rawDataSourceProvider != null) {
                this.mMediaPlayer.setDataSource(rawDataSourceProvider);
            } else {
                IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
                if (playerOptions.useCache && !playerOptions.isLive && !playerOptions.isForceAndroidPlayer && !PlayerStringUtils.isLocalFile(this.mUri) && getIjkMediaPlayer() != null) {
                    this.mUri = JDPlayerVideoCache.getInstance().setDataSource(getIjkMediaPlayer(), this.mAppContext, this.mUri, this.originUrl, this.mHeaders);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                    this.mMediaPlayer.setDataSource(this.mAppContext, this.mUri, this.mHeaders);
                } else {
                    this.mMediaPlayer.setDataSource(this.mUri.toString());
                }
            }
            IPlayerControl.PlayerOptions playerOptions2 = this.mPlayerOptions;
            if (playerOptions2.enableReport) {
                String str = playerOptions2.playTypeId;
                if (TextUtils.isEmpty(str)) {
                    str = this.mBusinessId;
                }
                this.mPlayerMonitor = new PlayerPerformMonitor(getContext(), str, this.originUrl, this.mUri, this.mMediaPlayer, this.mPlayerOptions);
            }
            bindSurfaceHolder(this.mMediaPlayer, this.mSurfaceHolder);
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.setLooping(this.mPlayerOptions.loop);
            this.mPrepareStartTime = SystemClock.elapsedRealtime();
            this.mMediaPlayer.prepareAsync();
            Long l2 = this.expireTime;
            if (l2 != null && l2.longValue() >= 0) {
                getHandler().postDelayed(this.expireRunnable, this.expireTime.longValue() * 1000);
            }
            InfoHudViewHolder infoHudViewHolder = this.mHudViewHolder;
            if (infoHudViewHolder != null) {
                infoHudViewHolder.setMediaPlayer(getIjkMediaPlayer());
            }
            this.mCurrentState = 1;
            attachMediaController();
        } catch (IOException e2) {
            DebugLog.w(TAG, "Unable to open content: " + this.mUri, e2);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        } catch (IllegalArgumentException e3) {
            DebugLog.w(TAG, "Unable to open content: " + this.mUri, e3);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        }
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            float f2 = this.mPlayerOptions.volume;
            if (f2 >= 0.0f) {
                iMediaPlayer.setVolume(f2, f2);
            }
        }
        if (this.mRenderView == null || PlayerConfigLoader.getInstance().enableVrs(this.mPlayerOptions)) {
            return;
        }
        this.mRenderView.getView().setVisibility(4);
    }

    public void releaseWithoutStop() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.setDisplay(null);
        }
    }

    private void setRenderView(IRenderView iRenderView) {
        int i2;
        int i3;
        this.isCouldOpenVideoTextureCreate = true;
        if (this.mRenderView != null) {
            IMediaPlayer iMediaPlayer = this.mMediaPlayer;
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallback(this.mSHCallback);
            this.mRenderView = null;
            removeView(view);
        }
        if (iRenderView == null) {
            return;
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

    private void toggleMediaControlsVisibility() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    public void addHeaders(String str, String str2) {
        if (this.mHeaders == null) {
            this.mHeaders = new HashMap<>();
        }
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

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer, android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer, android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return true;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer, android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return true;
    }

    public void clearHeader() {
        HashMap<String, String> hashMap = this.mHeaders;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void forceRelase(boolean z) {
        this.bIsForceRelease.set(true);
        releaseInThread(z);
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer, android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
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
        IMediaPlayer internalMediaPlayer;
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return null;
        }
        if (iMediaPlayer instanceof IjkMediaPlayer) {
            return (IjkMediaPlayer) iMediaPlayer;
        }
        if ((iMediaPlayer instanceof MediaPlayerProxy) && (internalMediaPlayer = ((MediaPlayerProxy) iMediaPlayer).getInternalMediaPlayer()) != null && (internalMediaPlayer instanceof IjkMediaPlayer)) {
            return (IjkMediaPlayer) internalMediaPlayer;
        }
        return null;
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
        this.isCouldOpenVideoTextureCreate = true;
        if (this.mPlayerOptions == null) {
            return;
        }
        if (PlayerConfigLoader.getInstance().enableVrs(this.mPlayerOptions)) {
            JDTVSRTextureView jDTVSRTextureView = new JDTVSRTextureView(getContext());
            if (this.mVSRRender == null) {
                initVsr();
            }
            jDTVSRTextureView.setVSRRender(this.mVSRRender);
            if (this.mMediaPlayer != null) {
                jDTVSRTextureView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                jDTVSRTextureView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                jDTVSRTextureView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                jDTVSRTextureView.setAspectRatio(this.mPlayerOptions.aspectRatio);
            }
            setRenderView(jDTVSRTextureView);
        } else if (this.mPlayerOptions.isUseTextureView) {
            TextureRenderView textureRenderView = new TextureRenderView(getContext());
            if (this.mMediaPlayer != null) {
                textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                textureRenderView.setAspectRatio(this.mPlayerOptions.aspectRatio);
            }
            setRenderView(textureRenderView);
        } else {
            setRenderView(new SurfaceRenderView(getContext()));
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl, android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    protected void onExtInfo(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, HashMap<String, Object> hashMap) {
        IPlayerControl.OnPlayerExtInfoListener onPlayerExtInfoListener = this.mOnPlayerExtInfoListener;
        if (onPlayerExtInfoListener != null) {
            onPlayerExtInfoListener.onExtInfo(i2, i3, i4, hashMap);
        }
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

    public void pause() {
        this.isCouldStartOnRendered = false;
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
        this.mPlayDurationStatistics.pause();
    }

    public void release() {
        releaseInThread(false);
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void releaseInThread(boolean z) {
        this.isCouldOpenVideoTextureCreate = false;
        releaseInThread(true, z);
    }

    public void resume() {
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

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setAspectRatio(@IPlayerControl.AspectRatioType int i2) {
        IPlayerControl.PlayerOptions playerOptions = this.mPlayerOptions;
        if (playerOptions == null) {
            return;
        }
        playerOptions.setAspectRatio(i2);
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            iRenderView.setAspectRatio(this.mPlayerOptions.aspectRatio);
            requestLayout();
        }
    }

    public void setBusinessId(String str) {
        this.mBusinessId = str;
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
        if (onPlayerEventListener == null) {
            return;
        }
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            this.mOnPlayerEventListener = onPlayerEventListener;
        } else {
            iMediaPlayer.setOnPlayerEventListener(onPlayerEventListener);
        }
    }

    public void setOnPlayerExtInfoListener(IPlayerControl.OnPlayerExtInfoListener onPlayerExtInfoListener) {
        this.mOnPlayerExtInfoListener = onPlayerExtInfoListener;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer, tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        this.mPlayerListener = onPlayerStateListener;
    }

    @Override // tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setOnVideoSizeChangedListener(IPlayerControl.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (onVideoSizeChangedListener != null) {
            this.mVideoSizeChangedListener = onVideoSizeChangedListener;
        }
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public void setPlayerOptions(IPlayerControl.PlayerOptions playerOptions) {
        this.mPlayerOptions = playerOptions;
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView == null) {
            initRenders();
        } else {
            iRenderView.setAspectRatio(playerOptions.aspectRatio);
            requestLayout();
        }
        if (playerOptions.isShowHubView && this.mHudViewHolder == null) {
            this.mHudViewHolder = new InfoHudViewHolder(getContext(), this);
        }
        this.expireTime = this.mPlayerOptions.expireTime;
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

    public void setVideoPath(String str) {
        IPlayerControl.PlayerOptions playerOptions;
        if (str == null) {
            str = "";
        }
        this.originUrl = JDPlayerHelper.getInstance().updateProtocol(str, this.mPlayerOptions);
        this.setVideoPathTime = System.currentTimeMillis();
        if (str.contains("adaptationSet") && (playerOptions = this.mPlayerOptions) != null && playerOptions.isLive) {
            playerOptions.setLasMPD(str);
            setVideoURI(Uri.parse("ijklas:"));
            return;
        }
        String trim = str.trim();
        addUAHeader();
        JDPlayerHelper.getInstance().addStreamHostIp(trim, this.mPlayerOptions, true);
        setVideoURI(IjkUtils.parseVideoPath(insertIp(trim)));
    }

    public void setVideoPathWithoutOpen(String str) {
        this.originUrl = str;
        this.mUri = JDPlayerHelper.getInstance().generateUrl(str, this.mPlayerOptions, this.mHeaders);
        this.mSeekWhenPrepared = 0;
        DebugLog.d(TAG, "setVideoURI");
    }

    public void setVideoURI(Uri uri) {
        this.isCouldOpenVideoTextureCreate = true;
        this.mUri = uri;
        if (uri != null && TextUtils.isEmpty(this.originUrl)) {
            this.originUrl = uri.toString();
        }
        this.mSeekWhenPrepared = 0;
        JDPlayerHelper.getInstance().addUAHeader(this.mPlayerOptions, this.mHeaders);
        DebugLog.d(TAG, "setVideoURI");
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

    public void start() {
        this.isCouldStartOnRendered = true;
        this.isCouldOpenVideoTextureCreate = true;
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            this.mPlayDurationStatistics.start();
        }
        this.mTargetState = 3;
    }

    public void suspend() {
        this.isCouldOpenVideoTextureCreate = false;
        releaseInThread(false, true);
    }

    private void release(boolean z) {
        releaseInThread(z, true);
    }

    private void releaseInThread(boolean z, boolean z2) {
        PlayerAudioHelper playerAudioHelper;
        this.mPlayDurationStatistics.release();
        if (this.mMediaPlayer == null) {
            return;
        }
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            iRenderView.release();
            this.mVSRRender = null;
        }
        this.mMediaPlayer.setDisplay(null);
        if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
        }
        if (getHandler() != null && this.expireRunnable != null) {
            getHandler().removeCallbacks(this.expireRunnable);
        }
        if (z2) {
            this.mMediaPlayer.notifyPlayEvent(14);
            final IMediaPlayer iMediaPlayer = this.mMediaPlayer;
            VideoPlayerThreadManager.addTask(new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.13
                {
                    IjkVideoView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    iMediaPlayer.release();
                }
            });
        } else {
            this.mMediaPlayer.release();
        }
        IMediaPlayer iMediaPlayer2 = this.mMediaPlayer;
        if (iMediaPlayer2 instanceof AbstractMediaPlayer) {
            iMediaPlayer2.resetListeners();
        }
        IMediaPlayer iMediaPlayer3 = this.mMediaPlayer;
        if (iMediaPlayer3 instanceof MediaPlayerProxy) {
            iMediaPlayer3.resetListeners();
        }
        PlayerNetworkUtil.unregisterNetworkCallback(this.liveNetListener);
        PlayerPerformMonitor playerPerformMonitor = this.mPlayerMonitor;
        if (playerPerformMonitor != null) {
            playerPerformMonitor.release();
            this.mPlayerMonitor = null;
        }
        this.mMediaPlayer = null;
        this.playerMtaReport = null;
        this.mCurrentState = 0;
        if (z) {
            this.mTargetState = 0;
        }
        if (!this.mPlayerOptions.isRequestAudioFocus || (playerAudioHelper = this.playerAudioHelper) == null) {
            return;
        }
        playerAudioHelper.release();
        this.playerAudioHelper = null;
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.originUrl = "";
        this.mBusinessId = "";
        this.mPrepareStartTime = 0L;
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.mOnExtInfoListener = new IMediaPlayer.OnExtInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.2
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnExtInfoListener
            public void onExtInfo(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, HashMap<String, Object> hashMap) {
                IjkVideoView.this.onExtInfo(iMediaPlayer, i2, i3, i4, hashMap);
            }
        };
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.3
            {
                IjkVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                IjkVideoView.this.suspend();
                if (IjkVideoView.this.mErrorListener == null) {
                    return;
                }
                IjkVideoView.this.mErrorListener.onError(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer, -10000, 489);
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.4
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || IjkVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                IjkVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.5
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(IjkVideoView.TAG, "OnVideoSizeChanged: mVideoWidth " + IjkVideoView.this.mVideoWidth + " mVideoHeight " + IjkVideoView.this.mVideoHeight + " mVideoSarNum " + IjkVideoView.this.mVideoSarNum + " mVideoSarDen " + IjkVideoView.this.mVideoSarDen + ", width = " + i2 + ", height = " + i3);
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mVideoSizeChangedListener != null) {
                    IjkVideoView.this.mVideoSizeChangedListener.onVideoSizeChanged(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.enableVsr(ijkVideoView.mVideoWidth, IjkVideoView.this.mVideoHeight);
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateLoadCost(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.playerAudioHelper != null && IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isLive) {
                    IjkVideoView.this.playerAudioHelper.enhanceAudio(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer);
                }
                IjkVideoView.this.mCurrentState = 2;
                DebugLog.d(IjkVideoView.TAG, "onPrepared " + (elapsedRealtime - IjkVideoView.this.mPrepareStartTime));
                if (IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mPlayDurationStatistics.start();
                    if ((!MediaPlayerHelper.isLoadJniOk || IjkVideoView.this.mPlayerOptions.isForceAndroidPlayer) && ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.start();
                    }
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onPrepared(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                if (iMediaPlayer != null) {
                    IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                    IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                }
                int i2 = IjkVideoView.this.mSeekWhenPrepared;
                if (i2 != 0) {
                    IjkVideoView.this.seekTo(i2);
                }
                if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mTargetState = 3;
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 3) {
                        IjkVideoView.this.start();
                    }
                } else if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 3) {
                            if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && !((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.isPlaying()) {
                                IjkVideoView.this.start();
                            }
                            if (IjkVideoView.this.mMediaController != null && (IjkVideoView.this.mPlayerOptions == null || IjkVideoView.this.mPlayerOptions.showControllerOnStart)) {
                                IjkVideoView.this.mMediaController.show();
                            }
                        } else if (!IjkVideoView.this.isPlaying() && ((i2 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null)) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
                IjkVideoView.this.postDelayed(new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (IjkVideoView.this.mRenderView == null || IjkVideoView.this.mRenderView.getView().getVisibility() == 0) {
                            return;
                        }
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                }, 200L);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.7
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPlayDurationStatistics.complete();
                IjkVideoView.this.mCurrentState = 5;
                IjkVideoView.this.mTargetState = 5;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onCompletion();
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.8
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i3) {
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onInfo(i2, i3);
                }
                if (i2 == 3) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                    if (IjkVideoView.this.isCouldStartOnRendered) {
                        return true;
                    }
                    IjkVideoView.this.pause();
                    return true;
                }
                if (i2 != 10005) {
                    if (i2 == 10100) {
                        DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                        return true;
                    } else if (i2 != 30003) {
                        if (i2 == 901) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i2 == 902) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i2 == 10001) {
                            IjkVideoView.this.mVideoRotationDegree = i3;
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i3);
                            if (IjkVideoView.this.mRenderView != null) {
                                IjkVideoView.this.mRenderView.setVideoRotation(i3);
                                return true;
                            }
                            return true;
                        } else if (i2 == 10002) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i2 == 10304) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                            return true;
                        } else if (i2 != 10305) {
                            switch (i2) {
                                case 700:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                    return true;
                                case 701:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                    return true;
                                case 702:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                    return true;
                                case 703:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i3);
                                    return true;
                                default:
                                    switch (i2) {
                                        case 800:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                            return true;
                                        case 801:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                            return true;
                                        case 802:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                            return true;
                                        default:
                                            DebugLog.d(IjkVideoView.TAG, "unknown media info:" + i2 + " extra:" + i3);
                                            return true;
                                    }
                            }
                        } else {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i3);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                }
                DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.9
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i3) {
                DebugLog.d(IjkVideoView.TAG, "Error: " + i2 + DYConstants.DY_REGEX_COMMA + i3);
                IjkVideoView.this.mPlayDurationStatistics.error();
                IjkVideoView.this.mCurrentState = -1;
                IjkVideoView.this.mTargetState = -1;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.getContext() == null || !PlayerNetworkUtil.isNetworkConnected(IjkVideoView.this.getContext()) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.useCache || i2 == -1) {
                    if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isCouldMediaCodec && i3 == -1009 && IjkVideoView.this.mUri != null) {
                        IjkVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.setVideoURI(ijkVideoView.mUri);
                        return true;
                    } else if (CronetConfigLoader.quicEnable() && IjkVideoView.this.mUri != null && PlayerStringUtils.isQuicProtocol(IjkVideoView.this.originUrl)) {
                        CronetConfigLoader.quicEnable = false;
                        IjkVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(IjkVideoView.this.mUri.toString())));
                        return true;
                    } else if (!MediaInfoUtil.isDnsError(i3) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.isAdvanceDns) {
                        if (i3 == -858797304 && IjkVideoView.this.expireTime != null) {
                            i3 = 489;
                        }
                        if (IjkVideoView.this.mPlayerListener == null || IjkVideoView.this.mPlayerListener.onError(i2, i3)) {
                        }
                        return true;
                    } else {
                        IjkVideoView.this.mPlayerOptions.isAdvanceDns = false;
                        JDPlayerHelper.getInstance().addStreamHostIp(IjkVideoView.this.originUrl, IjkVideoView.this.mPlayerOptions, false);
                        IjkVideoView ijkVideoView2 = IjkVideoView.this;
                        ijkVideoView2.setVideoURI(Uri.parse(ijkVideoView2.originUrl));
                        return true;
                    }
                }
                JDPlayerVideoCache.getInstance().getRawUri(IjkVideoView.this.originUrl);
                IjkVideoView.this.mPlayerOptions.useCache = false;
                IjkVideoView ijkVideoView3 = IjkVideoView.this;
                ijkVideoView3.setVideoURI(Uri.parse(ijkVideoView3.originUrl));
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.10
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2) {
                IjkVideoView.this.mCurrentBufferPercentage = i2;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.11
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - IjkVideoView.this.mSeekStartTime);
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onSeekComplete();
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.12
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3, int i4) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceChanged");
                IjkVideoView.this.mSurfaceWidth = i3;
                IjkVideoView.this.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 3;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i3 || IjkVideoView.this.mVideoHeight != i4)) {
                    z = false;
                }
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i2, i3, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceCreated");
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer == null) {
                    if (IjkVideoView.this.isCouldOpenVideoTextureCreate) {
                        IjkVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.bindSurfaceHolder(((IJDVideoPlayer) ijkVideoView).mMediaPlayer, iSurfaceHolder);
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceDestroyed");
                IjkVideoView.this.mSurfaceHolder = null;
                IjkVideoView.this.releaseWithoutStop();
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.originUrl = "";
        this.mBusinessId = "";
        this.mPrepareStartTime = 0L;
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.mOnExtInfoListener = new IMediaPlayer.OnExtInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.2
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnExtInfoListener
            public void onExtInfo(IMediaPlayer iMediaPlayer, int i22, int i3, int i4, HashMap<String, Object> hashMap) {
                IjkVideoView.this.onExtInfo(iMediaPlayer, i22, i3, i4, hashMap);
            }
        };
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.3
            {
                IjkVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                IjkVideoView.this.suspend();
                if (IjkVideoView.this.mErrorListener == null) {
                    return;
                }
                IjkVideoView.this.mErrorListener.onError(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer, -10000, 489);
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.4
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || IjkVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                IjkVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.5
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i22, int i3, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(IjkVideoView.TAG, "OnVideoSizeChanged: mVideoWidth " + IjkVideoView.this.mVideoWidth + " mVideoHeight " + IjkVideoView.this.mVideoHeight + " mVideoSarNum " + IjkVideoView.this.mVideoSarNum + " mVideoSarDen " + IjkVideoView.this.mVideoSarDen + ", width = " + i22 + ", height = " + i3);
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mVideoSizeChangedListener != null) {
                    IjkVideoView.this.mVideoSizeChangedListener.onVideoSizeChanged(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.enableVsr(ijkVideoView.mVideoWidth, IjkVideoView.this.mVideoHeight);
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateLoadCost(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.playerAudioHelper != null && IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isLive) {
                    IjkVideoView.this.playerAudioHelper.enhanceAudio(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer);
                }
                IjkVideoView.this.mCurrentState = 2;
                DebugLog.d(IjkVideoView.TAG, "onPrepared " + (elapsedRealtime - IjkVideoView.this.mPrepareStartTime));
                if (IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mPlayDurationStatistics.start();
                    if ((!MediaPlayerHelper.isLoadJniOk || IjkVideoView.this.mPlayerOptions.isForceAndroidPlayer) && ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.start();
                    }
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onPrepared(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                if (iMediaPlayer != null) {
                    IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                    IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                }
                int i22 = IjkVideoView.this.mSeekWhenPrepared;
                if (i22 != 0) {
                    IjkVideoView.this.seekTo(i22);
                }
                if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mTargetState = 3;
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 3) {
                        IjkVideoView.this.start();
                    }
                } else if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 3) {
                            if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && !((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.isPlaying()) {
                                IjkVideoView.this.start();
                            }
                            if (IjkVideoView.this.mMediaController != null && (IjkVideoView.this.mPlayerOptions == null || IjkVideoView.this.mPlayerOptions.showControllerOnStart)) {
                                IjkVideoView.this.mMediaController.show();
                            }
                        } else if (!IjkVideoView.this.isPlaying() && ((i22 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null)) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
                IjkVideoView.this.postDelayed(new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (IjkVideoView.this.mRenderView == null || IjkVideoView.this.mRenderView.getView().getVisibility() == 0) {
                            return;
                        }
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                }, 200L);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.7
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPlayDurationStatistics.complete();
                IjkVideoView.this.mCurrentState = 5;
                IjkVideoView.this.mTargetState = 5;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onCompletion();
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.8
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i22, int i3) {
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onInfo(i22, i3);
                }
                if (i22 == 3) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                    if (IjkVideoView.this.isCouldStartOnRendered) {
                        return true;
                    }
                    IjkVideoView.this.pause();
                    return true;
                }
                if (i22 != 10005) {
                    if (i22 == 10100) {
                        DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                        return true;
                    } else if (i22 != 30003) {
                        if (i22 == 901) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i22 == 902) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i22 == 10001) {
                            IjkVideoView.this.mVideoRotationDegree = i3;
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i3);
                            if (IjkVideoView.this.mRenderView != null) {
                                IjkVideoView.this.mRenderView.setVideoRotation(i3);
                                return true;
                            }
                            return true;
                        } else if (i22 == 10002) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i22 == 10304) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                            return true;
                        } else if (i22 != 10305) {
                            switch (i22) {
                                case 700:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                    return true;
                                case 701:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                    return true;
                                case 702:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                    return true;
                                case 703:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i3);
                                    return true;
                                default:
                                    switch (i22) {
                                        case 800:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                            return true;
                                        case 801:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                            return true;
                                        case 802:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                            return true;
                                        default:
                                            DebugLog.d(IjkVideoView.TAG, "unknown media info:" + i22 + " extra:" + i3);
                                            return true;
                                    }
                            }
                        } else {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i3);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                }
                DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.9
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i22, int i3) {
                DebugLog.d(IjkVideoView.TAG, "Error: " + i22 + DYConstants.DY_REGEX_COMMA + i3);
                IjkVideoView.this.mPlayDurationStatistics.error();
                IjkVideoView.this.mCurrentState = -1;
                IjkVideoView.this.mTargetState = -1;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.getContext() == null || !PlayerNetworkUtil.isNetworkConnected(IjkVideoView.this.getContext()) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.useCache || i22 == -1) {
                    if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isCouldMediaCodec && i3 == -1009 && IjkVideoView.this.mUri != null) {
                        IjkVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.setVideoURI(ijkVideoView.mUri);
                        return true;
                    } else if (CronetConfigLoader.quicEnable() && IjkVideoView.this.mUri != null && PlayerStringUtils.isQuicProtocol(IjkVideoView.this.originUrl)) {
                        CronetConfigLoader.quicEnable = false;
                        IjkVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(IjkVideoView.this.mUri.toString())));
                        return true;
                    } else if (!MediaInfoUtil.isDnsError(i3) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.isAdvanceDns) {
                        if (i3 == -858797304 && IjkVideoView.this.expireTime != null) {
                            i3 = 489;
                        }
                        if (IjkVideoView.this.mPlayerListener == null || IjkVideoView.this.mPlayerListener.onError(i22, i3)) {
                        }
                        return true;
                    } else {
                        IjkVideoView.this.mPlayerOptions.isAdvanceDns = false;
                        JDPlayerHelper.getInstance().addStreamHostIp(IjkVideoView.this.originUrl, IjkVideoView.this.mPlayerOptions, false);
                        IjkVideoView ijkVideoView2 = IjkVideoView.this;
                        ijkVideoView2.setVideoURI(Uri.parse(ijkVideoView2.originUrl));
                        return true;
                    }
                }
                JDPlayerVideoCache.getInstance().getRawUri(IjkVideoView.this.originUrl);
                IjkVideoView.this.mPlayerOptions.useCache = false;
                IjkVideoView ijkVideoView3 = IjkVideoView.this;
                ijkVideoView3.setVideoURI(Uri.parse(ijkVideoView3.originUrl));
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.10
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i22) {
                IjkVideoView.this.mCurrentBufferPercentage = i22;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.11
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - IjkVideoView.this.mSeekStartTime);
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onSeekComplete();
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.12
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i3, int i4) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceChanged");
                IjkVideoView.this.mSurfaceWidth = i3;
                IjkVideoView.this.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 3;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i3 || IjkVideoView.this.mVideoHeight != i4)) {
                    z = false;
                }
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i22, i3, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceCreated");
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer == null) {
                    if (IjkVideoView.this.isCouldOpenVideoTextureCreate) {
                        IjkVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.bindSurfaceHolder(((IJDVideoPlayer) ijkVideoView).mMediaPlayer, iSurfaceHolder);
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceDestroyed");
                IjkVideoView.this.mSurfaceHolder = null;
                IjkVideoView.this.releaseWithoutStop();
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context);
    }

    @TargetApi(21)
    public IjkVideoView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.originUrl = "";
        this.mBusinessId = "";
        this.mPrepareStartTime = 0L;
        this.mSeekStartTime = 0L;
        this.isCouldOpenVideoTextureCreate = true;
        this.isCouldStartOnRendered = true;
        this.expireTime = null;
        this.bIsForceRelease = new AtomicBoolean(false);
        this.mOnExtInfoListener = new IMediaPlayer.OnExtInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.2
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnExtInfoListener
            public void onExtInfo(IMediaPlayer iMediaPlayer, int i22, int i32, int i4, HashMap<String, Object> hashMap) {
                IjkVideoView.this.onExtInfo(iMediaPlayer, i22, i32, i4, hashMap);
            }
        };
        this.expireRunnable = new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.3
            {
                IjkVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                IjkVideoView.this.suspend();
                if (IjkVideoView.this.mErrorListener == null) {
                    return;
                }
                IjkVideoView.this.mErrorListener.onError(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer, -10000, 489);
            }
        };
        this.liveNetListener = new PlayerNetworkUtil.NetChangeListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.4
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.utils.PlayerNetworkUtil.NetChangeListener
            public void onNetworkChange(boolean z, boolean z2) {
                if (!z || IjkVideoView.this.getIjkMediaPlayer() == null) {
                    return;
                }
                IjkVideoView.this.getIjkMediaPlayer().setPropertyLong(20211, 1L);
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.5
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i22, int i32, int i4, int i5) {
                if (iMediaPlayer == null) {
                    return;
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
                DebugLog.d(IjkVideoView.TAG, "OnVideoSizeChanged: mVideoWidth " + IjkVideoView.this.mVideoWidth + " mVideoHeight " + IjkVideoView.this.mVideoHeight + " mVideoSarNum " + IjkVideoView.this.mVideoSarNum + " mVideoSarDen " + IjkVideoView.this.mVideoSarDen + ", width = " + i22 + ", height = " + i32);
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mVideoSizeChangedListener != null) {
                    IjkVideoView.this.mVideoSizeChangedListener.onVideoSizeChanged(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.enableVsr(ijkVideoView.mVideoWidth, IjkVideoView.this.mVideoHeight);
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateLoadCost(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.playerAudioHelper != null && IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isLive) {
                    IjkVideoView.this.playerAudioHelper.enhanceAudio(((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer);
                }
                IjkVideoView.this.mCurrentState = 2;
                DebugLog.d(IjkVideoView.TAG, "onPrepared " + (elapsedRealtime - IjkVideoView.this.mPrepareStartTime));
                if (IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mPlayDurationStatistics.start();
                    if ((!MediaPlayerHelper.isLoadJniOk || IjkVideoView.this.mPlayerOptions.isForceAndroidPlayer) && ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.start();
                    }
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onPrepared(elapsedRealtime - IjkVideoView.this.mPrepareStartTime);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                if (iMediaPlayer != null) {
                    IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                    IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                }
                int i22 = IjkVideoView.this.mSeekWhenPrepared;
                if (i22 != 0) {
                    IjkVideoView.this.seekTo(i22);
                }
                if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isStartOnPrepared) {
                    IjkVideoView.this.mTargetState = 3;
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 3) {
                        IjkVideoView.this.start();
                    }
                } else if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                    if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                        if (IjkVideoView.this.mTargetState == 3) {
                            if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && !((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.isPlaying()) {
                                IjkVideoView.this.start();
                            }
                            if (IjkVideoView.this.mMediaController != null && (IjkVideoView.this.mPlayerOptions == null || IjkVideoView.this.mPlayerOptions.showControllerOnStart)) {
                                IjkVideoView.this.mMediaController.show();
                            }
                        } else if (!IjkVideoView.this.isPlaying() && ((i22 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null)) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
                IjkVideoView.this.postDelayed(new Runnable() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (IjkVideoView.this.mRenderView == null || IjkVideoView.this.mRenderView.getView().getVisibility() == 0) {
                            return;
                        }
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                }, 200L);
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.7
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPlayDurationStatistics.complete();
                IjkVideoView.this.mCurrentState = 5;
                IjkVideoView.this.mTargetState = 5;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onCompletion();
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.8
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i22, int i32) {
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onInfo(i22, i32);
                }
                if (i22 == 3) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.getView().setVisibility(0);
                    }
                    if (IjkVideoView.this.isCouldStartOnRendered) {
                        return true;
                    }
                    IjkVideoView.this.pause();
                    return true;
                }
                if (i22 != 10005) {
                    if (i22 == 10100) {
                        DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE:");
                        return true;
                    } else if (i22 != 30003) {
                        if (i22 == 901) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                            return true;
                        } else if (i22 == 902) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                            return true;
                        } else if (i22 == 10001) {
                            IjkVideoView.this.mVideoRotationDegree = i32;
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i32);
                            if (IjkVideoView.this.mRenderView != null) {
                                IjkVideoView.this.mRenderView.setVideoRotation(i32);
                                return true;
                            }
                            return true;
                        } else if (i22 == 10002) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                            return true;
                        } else if (i22 == 10304) {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_TCP_CONNECTED:");
                            return true;
                        } else if (i22 != 10305) {
                            switch (i22) {
                                case 700:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                                    return true;
                                case 701:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(7);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_START:");
                                    return true;
                                case 702:
                                    if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(8);
                                    }
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BUFFERING_END:");
                                    return true;
                                case 703:
                                    DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i32);
                                    return true;
                                default:
                                    switch (i22) {
                                        case 800:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                            return true;
                                        case 801:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                            return true;
                                        case 802:
                                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                            return true;
                                        default:
                                            DebugLog.d(IjkVideoView.TAG, "unknown media info:" + i22 + " extra:" + i32);
                                            return true;
                                    }
                            }
                        } else {
                            DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_IO_ERR_RET: " + i32);
                            return true;
                        }
                    } else if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null) {
                        ((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer.notifyPlayEvent(12);
                    }
                }
                DebugLog.d(IjkVideoView.TAG, "MEDIA_INFO_OPEN_INPUT:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.9
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i22, int i32) {
                DebugLog.d(IjkVideoView.TAG, "Error: " + i22 + DYConstants.DY_REGEX_COMMA + i32);
                IjkVideoView.this.mPlayDurationStatistics.error();
                IjkVideoView.this.mCurrentState = -1;
                IjkVideoView.this.mTargetState = -1;
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.getContext() == null || !PlayerNetworkUtil.isNetworkConnected(IjkVideoView.this.getContext()) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.useCache || i22 == -1) {
                    if (IjkVideoView.this.mPlayerOptions != null && IjkVideoView.this.mPlayerOptions.isCouldMediaCodec && i32 == -1009 && IjkVideoView.this.mUri != null) {
                        IjkVideoView.this.mPlayerOptions.setCouldMediaCodec(false);
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.setVideoURI(ijkVideoView.mUri);
                        return true;
                    } else if (CronetConfigLoader.quicEnable() && IjkVideoView.this.mUri != null && PlayerStringUtils.isQuicProtocol(IjkVideoView.this.originUrl)) {
                        CronetConfigLoader.quicEnable = false;
                        IjkVideoView.this.setVideoURI(Uri.parse(PlayerToolsUtil.getOriginUrl(IjkVideoView.this.mUri.toString())));
                        return true;
                    } else if (!MediaInfoUtil.isDnsError(i32) || IjkVideoView.this.mPlayerOptions == null || !IjkVideoView.this.mPlayerOptions.isAdvanceDns) {
                        if (i32 == -858797304 && IjkVideoView.this.expireTime != null) {
                            i32 = 489;
                        }
                        if (IjkVideoView.this.mPlayerListener == null || IjkVideoView.this.mPlayerListener.onError(i22, i32)) {
                        }
                        return true;
                    } else {
                        IjkVideoView.this.mPlayerOptions.isAdvanceDns = false;
                        JDPlayerHelper.getInstance().addStreamHostIp(IjkVideoView.this.originUrl, IjkVideoView.this.mPlayerOptions, false);
                        IjkVideoView ijkVideoView2 = IjkVideoView.this;
                        ijkVideoView2.setVideoURI(Uri.parse(ijkVideoView2.originUrl));
                        return true;
                    }
                }
                JDPlayerVideoCache.getInstance().getRawUri(IjkVideoView.this.originUrl);
                IjkVideoView.this.mPlayerOptions.useCache = false;
                IjkVideoView ijkVideoView3 = IjkVideoView.this;
                ijkVideoView3.setVideoURI(Uri.parse(ijkVideoView3.originUrl));
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.10
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i22) {
                IjkVideoView.this.mCurrentBufferPercentage = i22;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.11
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (IjkVideoView.this.mHudViewHolder != null) {
                    IjkVideoView.this.mHudViewHolder.updateSeekCost(elapsedRealtime - IjkVideoView.this.mSeekStartTime);
                }
                if (IjkVideoView.this.mPlayerListener != null) {
                    IjkVideoView.this.mPlayerListener.onSeekComplete();
                }
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: tv.danmaku.ijk.media.example.widget.media.IjkVideoView.12
            {
                IjkVideoView.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i32, int i4) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceChanged");
                IjkVideoView.this.mSurfaceWidth = i32;
                IjkVideoView.this.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 3;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i32 || IjkVideoView.this.mVideoHeight != i4)) {
                    z = false;
                }
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceChanged(iSurfaceHolder.getSurfaceHolder(), i22, i32, i4);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i22, int i32) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceCreated");
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (((IJDVideoPlayer) IjkVideoView.this).mMediaPlayer == null) {
                    if (IjkVideoView.this.isCouldOpenVideoTextureCreate) {
                        IjkVideoView.this.openVideo();
                        return;
                    }
                    return;
                }
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.bindSurfaceHolder(((IJDVideoPlayer) ijkVideoView).mMediaPlayer, iSurfaceHolder);
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceCreated(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    DebugLog.e(IjkVideoView.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                    return;
                }
                DebugLog.d(IjkVideoView.TAG, "onSurfaceDestroyed");
                IjkVideoView.this.mSurfaceHolder = null;
                IjkVideoView.this.releaseWithoutStop();
                if (IjkVideoView.this.mSurfaceHolderCallback != null) {
                    IjkVideoView.this.mSurfaceHolderCallback.surfaceDestroyed(iSurfaceHolder.getSurfaceHolder());
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IRenderView.IRenderCallback
            public void onSurfaceTextureUpdated() {
            }
        };
        this.mSurfaceHolderCallback = null;
        initVideoView(context);
    }
}
