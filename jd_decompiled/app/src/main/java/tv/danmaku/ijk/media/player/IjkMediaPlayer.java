package tv.danmaku.ijk.media.player;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Rect;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.jd.dynamic.DYConstants;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.cronet.CronetConfigLoader;
import tv.danmaku.ijk.media.ext.config.NetConfigBean;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.ext.report.ReportConstant;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.annotations.AccessedByNative;
import tv.danmaku.ijk.media.player.annotations.CalledByNative;
import tv.danmaku.ijk.media.player.misc.IAndroidIO;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public final class IjkMediaPlayer extends AbstractMediaPlayer {
    public static final int AVERROR_HTTP_BAD_REQUEST = -808465656;
    public static final int AVERROR_HTTP_BUFFER_TIMEOUT = -9001;
    public static final int AVERROR_HTTP_FORBIDDEN = -858797304;
    public static final int AVERROR_HTTP_NOT_FOUND = -875574520;
    public static final int AVERROR_HTTP_OTHER_4XX = -1482175736;
    public static final int AVERROR_HTTP_SERVER_ERROR = -1482175992;
    public static final int AVERROR_HTTP_TIMEOUT_EXPIRE = 489;
    public static final int AVERROR_HTTP_UNAUTHORIZED = -825242872;
    public static final int AVERROR_UNKNOWN = -1313558101;
    public static final int ERROR_IJK = -10000;
    public static final int IJK_LOG_DEBUG = 3;
    public static final int IJK_LOG_DEFAULT = 1;
    public static final int IJK_LOG_ERROR = 6;
    public static final int IJK_LOG_FATAL = 7;
    public static final int IJK_LOG_INFO = 4;
    public static final int IJK_LOG_SILENT = 8;
    public static final int IJK_LOG_UNKNOWN = 0;
    public static final int IJK_LOG_VERBOSE = 2;
    public static final int IJK_LOG_WARN = 5;
    private static final int MEDIA_BUFFERING_UPDATE = 3;
    public static final int MEDIA_ERROR = 100;
    private static final int MEDIA_H2645_SEI = 991;
    public static final int MEDIA_INFO = 200;
    private static final int MEDIA_NOP = 0;
    private static final int MEDIA_PLAYBACK_COMPLETE = 2;
    private static final int MEDIA_POSITION_UPDATE = 7;
    public static final int MEDIA_PREPARED = 1;
    private static final int MEDIA_SEEK_COMPLETE = 4;
    protected static final int MEDIA_SET_VIDEO_SAR = 10001;
    public static final int MEDIA_SET_VIDEO_SIZE = 5;
    private static final int MEDIA_TIMED_TEXT = 99;
    public static final int OPT_CATEGORY_CODEC = 2;
    public static final int OPT_CATEGORY_FORMAT = 1;
    public static final int OPT_CATEGORY_PLAYER = 4;
    public static final int OPT_CATEGORY_SWS = 3;
    public static final int SDL_FCC_RV16 = 909203026;
    public static final int SDL_FCC_RV32 = 842225234;
    public static final int SDL_FCC_YV12 = 842094169;
    private static final String TAG = "tv.danmaku.ijk.media.player.IjkMediaPlayer";
    public static boolean hasLoadCronet;
    private String mDataSource;
    private EventHandler mEventHandler;
    private Set<IjkEventListener> mEventListeners;
    @AccessedByNative
    private int mListenerContext;
    private boolean mLoop;
    private int mLoopCount;
    @AccessedByNative
    private long mNativeAndroidIO;
    @AccessedByNative
    private long mNativeMediaDataSource;
    @AccessedByNative
    private long mNativeMediaPlayer;
    @AccessedByNative
    private int mNativeSurfaceTexture;
    private OnControlMessageListener mOnControlMessageListener;
    private long mPrepareStartTime;
    private boolean mScreenOnWhilePlaying;
    private boolean mStayAwake;
    private SurfaceHolder mSurfaceHolder;
    private int mVideoHeight;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private PowerManager.WakeLock mWakeLock;
    private static final IMediaPlayer.OnMediaCodecSelectListener mDefaultMediaCodecSelectListener = new DefaultMediaCodecSelector();
    private static final IjkLibLoader sLocalLibLoader = new IjkLibLoader() { // from class: tv.danmaku.ijk.media.player.IjkMediaPlayer.1
        @Override // tv.danmaku.ijk.media.player.IjkLibLoader
        public void loadLibrary(String str) throws UnsatisfiedLinkError, SecurityException {
            System.loadLibrary(str);
        }
    };
    private static volatile boolean mIsLibLoaded = false;
    private static volatile boolean mIsNativeInitialized = false;

    /* loaded from: classes11.dex */
    public static class EventHandler extends Handler {
        private final WeakReference<IjkMediaPlayer> mWeakPlayer;

        public EventHandler(IjkMediaPlayer ijkMediaPlayer, Looper looper) {
            super(looper);
            this.mWeakPlayer = new WeakReference<>(ijkMediaPlayer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            IjkMediaPlayer ijkMediaPlayer = this.mWeakPlayer.get();
            if (ijkMediaPlayer != null) {
                if (ijkMediaPlayer.mNativeMediaPlayer != 0) {
                    ijkMediaPlayer.notifyIjkEventListeners(message);
                    int i2 = message.what;
                    if (i2 != 0) {
                        if (i2 == 1) {
                            HashMap<String, Object> parseExtInfo = MsgExtInfoUtil.parseExtInfo(message.obj);
                            ijkMediaPlayer.notifyOnInfo(message.what, message.arg1);
                            ijkMediaPlayer.notifyOnPrepared(SystemClock.elapsedRealtime() - ijkMediaPlayer.mPrepareStartTime);
                            ijkMediaPlayer.notifyOnExtInfo(message.what, message.arg1, message.arg2, parseExtInfo);
                            return;
                        } else if (i2 == 2) {
                            ijkMediaPlayer.stayAwake(false);
                            IjkMediaPlayer.access$510(ijkMediaPlayer);
                            if (!ijkMediaPlayer.mLoop) {
                                if (ijkMediaPlayer.mLoopCount > 0) {
                                    ijkMediaPlayer.notifyOnInfo(IMediaPlayer.MEDIA_INFO_JD_LOOP_COMPLETED, 0);
                                    ijkMediaPlayer.seekTo(0L);
                                    ijkMediaPlayer.start();
                                    return;
                                }
                                ijkMediaPlayer.notifyOnCompletion();
                                return;
                            }
                            ijkMediaPlayer.notifyOnInfo(IMediaPlayer.MEDIA_INFO_JD_LOOP_COMPLETED, 0);
                            ijkMediaPlayer.seekTo(0L);
                            ijkMediaPlayer.start();
                            return;
                        } else if (i2 == 3) {
                            long j2 = message.arg1;
                            if (j2 < 0) {
                                j2 = 0;
                            }
                            long duration = ijkMediaPlayer.getDuration();
                            long j3 = duration > 0 ? (j2 * 100) / duration : 0L;
                            if (duration - j2 < 800) {
                                j3 = 100;
                            }
                            ijkMediaPlayer.notifyOnBufferingUpdate((int) (j3 < 100 ? j3 : 100L));
                            return;
                        } else if (i2 == 4) {
                            ijkMediaPlayer.notifyOnSeekComplete();
                            return;
                        } else if (i2 == 5) {
                            if (message.arg1 == ijkMediaPlayer.mVideoWidth && message.arg2 == ijkMediaPlayer.mVideoHeight && ijkMediaPlayer.mVideoWidth != 0) {
                                return;
                            }
                            ijkMediaPlayer.mVideoWidth = message.arg1;
                            ijkMediaPlayer.mVideoHeight = message.arg2;
                            ijkMediaPlayer.notifyOnVideoSizeChanged(ijkMediaPlayer.mVideoWidth, ijkMediaPlayer.mVideoHeight, ijkMediaPlayer.mVideoSarNum, ijkMediaPlayer.mVideoSarDen);
                            return;
                        } else if (i2 == 99) {
                            if (message.obj == null) {
                                ijkMediaPlayer.notifyOnTimedText(null);
                                return;
                            } else {
                                ijkMediaPlayer.notifyOnTimedText(new IjkTimedText(new Rect(0, 0, 1, 1), (String) message.obj));
                                return;
                            }
                        } else if (i2 == 100) {
                            DebugLog.e(IjkMediaPlayer.TAG, "Error (" + message.arg1 + DYConstants.DY_REGEX_COMMA + message.arg2 + ")");
                            if (!ijkMediaPlayer.notifyOnError(message.arg1, message.arg2)) {
                                ijkMediaPlayer.notifyOnCompletion();
                            }
                            ijkMediaPlayer.stayAwake(false);
                            return;
                        } else if (i2 != 200) {
                            if (i2 == 991) {
                                Object obj = message.obj;
                                if (obj != null) {
                                    byte[] bArr = (byte[]) obj;
                                    ijkMediaPlayer.notifyOnSei(new String(bArr, 0, bArr.length));
                                    return;
                                }
                                ijkMediaPlayer.notifyOnSei(null);
                                return;
                            } else if (i2 != 10001) {
                                DebugLog.e(IjkMediaPlayer.TAG, "Unknown message type " + message.what);
                                return;
                            } else if (message.arg1 == ijkMediaPlayer.mVideoSarNum && message.arg2 == ijkMediaPlayer.mVideoSarDen && ijkMediaPlayer.mVideoSarNum != 0) {
                                return;
                            } else {
                                ijkMediaPlayer.mVideoSarNum = message.arg1;
                                ijkMediaPlayer.mVideoSarDen = message.arg2;
                                ijkMediaPlayer.notifyOnVideoSizeChanged(ijkMediaPlayer.mVideoWidth, ijkMediaPlayer.mVideoHeight, ijkMediaPlayer.mVideoSarNum, ijkMediaPlayer.mVideoSarDen);
                                return;
                            }
                        } else {
                            ijkMediaPlayer.notifyOnInfo(message.arg1, message.arg2);
                            int i3 = message.arg1;
                            if (i3 == 3) {
                                ijkMediaPlayer.notifyPlayEvent(9);
                            } else if (i3 == 10002) {
                                ijkMediaPlayer.notifyPlayEvent(11);
                            } else if (i3 == 10008) {
                                ijkMediaPlayer.notifyPlayEvent(10);
                            }
                            HashMap<String, Object> hashMap = new HashMap<>();
                            if (message.arg1 != 3) {
                                hashMap = MsgExtInfoUtil.parseExtInfo(message.obj);
                            }
                            if (message.arg1 == 10004) {
                                hashMap.put("videoCodec", message.obj);
                            }
                            if (message.arg1 == 3) {
                                hashMap.put(ReportConstant.FirstFrame.RENDER_TIME, message.obj);
                                String str = "test === renderTime: " + message.obj;
                            }
                            if (hashMap != null) {
                                ijkMediaPlayer.notifyOnExtInfo(message.what, message.arg1, message.arg2, hashMap);
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
            }
            DebugLog.w(IjkMediaPlayer.TAG, "IjkMediaPlayer went away with unhandled events");
        }
    }

    /* loaded from: classes11.dex */
    public interface OnControlMessageListener {
        String onControlResolveSegmentUrl(int i2);
    }

    public IjkMediaPlayer() {
        this(sLocalLibLoader);
    }

    public static native void _enableLogfile(boolean z);

    private native String _getAudioCodecInfo();

    private static native String _getColorFormatName(int i2);

    private native int _getLoopCount();

    private native Bundle _getMediaMeta();

    private native float _getPropertyFloat(int i2, float f2);

    private native long _getPropertyLong(int i2, long j2);

    private native String _getVideoCodecInfo();

    private native void _pause() throws IllegalStateException;

    private native void _release();

    private native void _reset(int i2, int i3);

    private native void _setAndroidIOCallback(IAndroidIO iAndroidIO) throws IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSource(String str, String[] strArr, String[] strArr2) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSource(IMediaDataSource iMediaDataSource) throws IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSourceFd(int i2) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setFrameAtTime(String str, long j2, long j3, int i2, int i3) throws IllegalArgumentException, IllegalStateException;

    private native void _setLoopCount(int i2);

    private native void _setOption(int i2, String str, long j2);

    private native void _setOption(int i2, String str, String str2);

    private native void _setPropertyFloat(int i2, float f2);

    private native void _setPropertyLong(int i2, long j2);

    private native void _setStreamSelected(int i2, boolean z);

    private native void _setVideoSurface(Surface surface);

    private native void _start() throws IllegalStateException;

    private native void _stop() throws IllegalStateException;

    static /* synthetic */ int access$510(IjkMediaPlayer ijkMediaPlayer) {
        int i2 = ijkMediaPlayer.mLoopCount;
        ijkMediaPlayer.mLoopCount = i2 - 1;
        return i2;
    }

    public static String getColorFormatName(int i2) {
        return _getColorFormatName(i2);
    }

    private static void initNativeOnce() {
        synchronized (IjkMediaPlayer.class) {
            if (!mIsNativeInitialized) {
                native_init();
                mIsNativeInitialized = true;
            }
        }
    }

    private void initPlayer(IjkLibLoader ijkLibLoader) {
        this.mPrepareStartTime = SystemClock.elapsedRealtime();
        loadLibrariesOnce(ijkLibLoader);
        initNativeOnce();
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(this, myLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(this, mainLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        native_setup(new WeakReference(this), JDPlayerConstant.CACHE_IO_THREAD_COUNT, 5);
    }

    public static void loadLibrariesOnce(IjkLibLoader ijkLibLoader) {
        NetConfigBean netConfigBean;
        synchronized (IjkMediaPlayer.class) {
            if (!hasLoadCronet && (netConfigBean = PlayerConfigLoader.netConfigBean) != null && netConfigBean.isQuicEnable() && CronetConfigLoader.quicEnable() && JDPlayerSdk.getCronetBridge().isCronetPrepared()) {
                boolean loadPluginPlayerSo = JDPlayerSdk.getCronetBridge().loadPluginPlayerSo();
                hasLoadCronet = loadPluginPlayerSo;
                mIsLibLoaded = loadPluginPlayerSo;
            }
            if (!mIsLibLoaded) {
                ijkLibLoader.loadLibrary("ijksdl");
                ijkLibLoader.loadLibrary("ijkplayer");
                mIsLibLoaded = true;
            }
        }
    }

    private native void native_finalize();

    private static native void native_init();

    private native void native_message_loop(Object obj);

    public static native void native_profileBegin(String str);

    public static native void native_profileEnd();

    public static native void native_setLogLevel(int i2);

    private native void native_setup(Object obj, int i2, int i3);

    public void notifyIjkEventListeners(Message message) {
        for (IjkEventListener ijkEventListener : this.mEventListeners) {
            int i2 = message.what;
            if (i2 == 1) {
                ijkEventListener.onEvent(this, 200, message.arg1, message.arg2, message.obj);
            } else if (i2 != 100) {
                if (i2 != 200) {
                    if (i2 == 700) {
                        ijkEventListener.onEvent(this, i2, message.arg1, message.arg2, message.obj);
                    } else if (i2 == 3) {
                        ijkEventListener.onEvent(this, 502, message.arg1, message.arg2, message.obj);
                    } else if (i2 == 4) {
                        ijkEventListener.onEvent(this, 600, message.arg1, message.arg2, message.obj);
                    } else if (i2 == 5) {
                        ijkEventListener.onEvent(this, 400, message.arg1, message.arg2, message.obj);
                    }
                }
                int i3 = message.arg1;
                if (i3 == 3) {
                    ijkEventListener.onEvent(this, 402, i3, message.arg2, message.obj);
                } else if (i3 == 701) {
                    ijkEventListener.onEvent(this, 500, i3, message.arg2, message.obj);
                } else if (i3 == 702) {
                    ijkEventListener.onEvent(this, 501, i3, message.arg2, message.obj);
                } else if (i3 == 10001) {
                    ijkEventListener.onEvent(this, 404, i3, message.arg2, message.obj);
                } else if (i3 == 10002) {
                    ijkEventListener.onEvent(this, 403, i3, message.arg2, message.obj);
                }
            } else {
                ijkEventListener.onEvent(this, 100, message.arg1, message.arg2, message.obj);
            }
        }
    }

    @CalledByNative
    private static boolean onNativeInvoke(Object obj, int i2, Bundle bundle) {
        DebugLog.ifmt(TAG, "onNativeInvoke %d, %s", Integer.valueOf(i2), bundle.toString());
        if (obj != null && (obj instanceof WeakReference)) {
            IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get();
            if (ijkMediaPlayer == null) {
                return false;
            }
            ijkMediaPlayer.notifyNativeInvoke(i2, bundle);
            if (i2 != 131079) {
                switch (i2) {
                    case IMediaPlayer.OnNativeInvokeListener.CTRL_WILL_TCP_OPEN /* 131073 */:
                    case IMediaPlayer.OnNativeInvokeListener.CTRL_DID_TCP_OPEN /* 131074 */:
                    case IMediaPlayer.OnNativeInvokeListener.CTRL_WILL_HTTP_OPEN /* 131075 */:
                        return true;
                    default:
                        return false;
                }
            }
            OnControlMessageListener onControlMessageListener = ijkMediaPlayer.mOnControlMessageListener;
            if (onControlMessageListener == null) {
                return false;
            }
            int i3 = bundle.getInt(IMediaPlayer.OnNativeInvokeListener.ARG_SEGMENT_INDEX, -1);
            if (i3 >= 0) {
                String onControlResolveSegmentUrl = onControlMessageListener.onControlResolveSegmentUrl(i3);
                if (onControlResolveSegmentUrl != null) {
                    bundle.putString("url", onControlResolveSegmentUrl);
                    return true;
                }
                throw new RuntimeException(new IOException("onNativeInvoke() = <NULL newUrl>"));
            }
            throw new InvalidParameterException("onNativeInvoke(invalid segment index)");
        }
        throw new IllegalStateException("<null weakThiz>.onNativeInvoke()");
    }

    @CalledByNative
    private static String onSelectCodec(Object obj, String str, int i2, int i3) {
        IjkMediaPlayer ijkMediaPlayer;
        if (obj == null || !(obj instanceof WeakReference) || (ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get()) == null) {
            return null;
        }
        ijkMediaPlayer.notifyMediaCodecSelect(ijkMediaPlayer, str, i2, i3);
        return mDefaultMediaCodecSelectListener.onMediaCodecSelect(ijkMediaPlayer, str, i2, i3);
    }

    @CalledByNative
    private static void postEventFromNative(Object obj, int i2, int i3, int i4, Object obj2) {
        IjkMediaPlayer ijkMediaPlayer;
        if (obj instanceof WeakReference) {
            WeakReference weakReference = (WeakReference) obj;
            if ((weakReference.get() instanceof IjkMediaPlayer) && (ijkMediaPlayer = (IjkMediaPlayer) weakReference.get()) != null) {
                if (i2 == 200 && i3 == 2) {
                    ijkMediaPlayer.start();
                }
                EventHandler eventHandler = ijkMediaPlayer.mEventHandler;
                if (eventHandler != null) {
                    ijkMediaPlayer.mEventHandler.sendMessage(eventHandler.obtainMessage(i2, i3, i4, obj2));
                }
            }
        }
    }

    @SuppressLint({"Wakelock"})
    public void stayAwake(boolean z) {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (z && !wakeLock.isHeld()) {
                this.mWakeLock.acquire();
            } else if (!z && this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
        }
        this.mStayAwake = z;
        updateSurfaceScreenOn();
    }

    private void updateSurfaceScreenOn() {
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.setKeepScreenOn(this.mScreenOnWhilePlaying && this.mStayAwake);
        }
    }

    public native void _prepareAsync() throws IllegalStateException;

    public final void addIjkEventListener(IjkEventListener ijkEventListener) {
        this.mEventListeners.add(ijkEventListener);
    }

    public void deselectTrack(int i2) {
        _setStreamSelected(i2, false);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        native_finalize();
    }

    public long getAsyncStatisticBufBackwards() {
        return _getPropertyLong(20201, 0L);
    }

    public long getAsyncStatisticBufCapacity() {
        return _getPropertyLong(20203, 0L);
    }

    public long getAsyncStatisticBufForwards() {
        return _getPropertyLong(20202, 0L);
    }

    public long getAudioCachedBytes() {
        return _getPropertyLong(20008, 0L);
    }

    public long getAudioCachedDuration() {
        return _getPropertyLong(20006, 0L);
    }

    public long getAudioCachedPackets() {
        return _getPropertyLong(20010, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native int getAudioSessionId();

    public long getBitRate() {
        return _getPropertyLong(20100, 0L);
    }

    public long getCacheStatisticCountBytes() {
        return _getPropertyLong(20208, 0L);
    }

    public long getCacheStatisticFileForwards() {
        return _getPropertyLong(20206, 0L);
    }

    public long getCacheStatisticFilePos() {
        return _getPropertyLong(20207, 0L);
    }

    public long getCacheStatisticPhysicalPos() {
        return _getPropertyLong(20205, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native long getCurrentPosition();

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public String getDataSource() {
        return this.mDataSource;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native long getDuration();

    public long getFileSize() {
        return _getPropertyLong(20209, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.mMediaPlayerName = "ijkplayer";
        String _getVideoCodecInfo = _getVideoCodecInfo();
        if (!TextUtils.isEmpty(_getVideoCodecInfo)) {
            String[] split = _getVideoCodecInfo.split(DYConstants.DY_REGEX_COMMA);
            if (split.length >= 2) {
                mediaInfo.mVideoDecoder = split[0];
                mediaInfo.mVideoDecoderImpl = split[1];
            } else if (split.length >= 1) {
                mediaInfo.mVideoDecoder = split[0];
                mediaInfo.mVideoDecoderImpl = "";
            }
        }
        String _getAudioCodecInfo = _getAudioCodecInfo();
        if (!TextUtils.isEmpty(_getAudioCodecInfo)) {
            String[] split2 = _getAudioCodecInfo.split(DYConstants.DY_REGEX_COMMA);
            if (split2.length >= 2) {
                mediaInfo.mAudioDecoder = split2[0];
                mediaInfo.mAudioDecoderImpl = split2[1];
            } else if (split2.length >= 1) {
                mediaInfo.mAudioDecoder = split2[0];
                mediaInfo.mAudioDecoderImpl = "";
            }
        }
        try {
            mediaInfo.mMeta = IjkMediaMeta.parse(_getMediaMeta());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return mediaInfo;
    }

    public Bundle getMediaMeta() {
        return _getMediaMeta();
    }

    public float getPropertyFloat(int i2, float f2) {
        return _getPropertyFloat(i2, f2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public long getPropertyLong(int i2) {
        return _getPropertyLong(i2, 0L);
    }

    public long getSeekLoadDuration() {
        return _getPropertyLong(20300, 0L);
    }

    public int getSelectedTrack(int i2) {
        long _getPropertyLong;
        if (i2 == 1) {
            _getPropertyLong = _getPropertyLong(20001, -1L);
        } else if (i2 == 2) {
            _getPropertyLong = _getPropertyLong(20002, -1L);
        } else if (i2 != 3) {
            return -1;
        } else {
            _getPropertyLong = _getPropertyLong(20011, -1L);
        }
        return (int) _getPropertyLong;
    }

    public float getSpeed(float f2) {
        return _getPropertyFloat(10003, 0.0f);
    }

    public long getTcpSpeed() {
        return _getPropertyLong(20200, 0L);
    }

    public long getTrafficStatisticByteCount() {
        return _getPropertyLong(20204, 0L);
    }

    public long getVideoCachedBytes() {
        return _getPropertyLong(20007, 0L);
    }

    public long getVideoCachedDuration() {
        return _getPropertyLong(20005, 0L);
    }

    public long getVideoCachedPackets() {
        return _getPropertyLong(20009, 0L);
    }

    public float getVideoDecodeFramesPerSecond() {
        return _getPropertyFloat(10001, 0.0f);
    }

    public int getVideoDecoder() {
        return (int) _getPropertyLong(20003, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public float getVideoOutputFramesPerSecond() {
        return _getPropertyFloat(10002, 0.0f);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoSarDen() {
        return this.mVideoSarDen;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoSarNum() {
        return this.mVideoSarNum;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public boolean isLooping() {
        return _getLoopCount() != 1;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native boolean isPlaying();

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void pause() {
        try {
            super.pause();
            stayAwake(false);
            _pause();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void prepareAsync() throws IllegalStateException {
        _prepareAsync();
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void release() {
        super.release();
        stayAwake(false);
        updateSurfaceScreenOn();
        resetListeners();
        _release();
    }

    public final void removeIjkEventListener(IjkEventListener ijkEventListener) {
        this.mEventListeners.remove(ijkEventListener);
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void reset() {
        super.reset();
        stayAwake(false);
        _reset(JDPlayerConstant.CACHE_IO_THREAD_COUNT, 5);
        this.mEventHandler.removeCallbacksAndMessages(null);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mLoop = false;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native void seekTo(long j2) throws IllegalStateException;

    public void selectTrack(int i2) {
        _setStreamSelected(i2, true);
    }

    public void setAndroidIOCallback(IAndroidIO iAndroidIO) throws IllegalArgumentException, SecurityException, IllegalStateException {
        _setAndroidIOCallback(iAndroidIO);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setAudioStreamType(int i2) {
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        setDataSource(context, uri, (Map<String, String>) null);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        _setVideoSurface(surfaceHolder != null ? surfaceHolder.getSurface() : null);
        updateSurfaceScreenOn();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setLoopCount(int i2) {
        if (i2 > 0) {
            this.mLoop = false;
        }
        this.mLoopCount = i2;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setLooping(boolean z) {
        this.mLoop = z;
    }

    public void setOnControlMessageListener(OnControlMessageListener onControlMessageListener) {
        this.mOnControlMessageListener = onControlMessageListener;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOption(int i2, String str, String str2) {
        _setOption(i2, str, str2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setPropertyFloat(int i2, float f2) {
        _setPropertyFloat(i2, f2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setPropertyLong(int i2, long j2) {
        _setPropertyLong(i2, j2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setScreenOnWhilePlaying(boolean z) {
        if (this.mScreenOnWhilePlaying != z) {
            if (z && this.mSurfaceHolder == null) {
                DebugLog.w(TAG, "setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder");
            }
            this.mScreenOnWhilePlaying = z;
            updateSurfaceScreenOn();
        }
    }

    public void setSpeed(float f2) {
        _setPropertyFloat(10003, f2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setSurface(Surface surface) {
        if (this.mScreenOnWhilePlaying && surface != null) {
            DebugLog.w(TAG, "setScreenOnWhilePlaying(true) is ineffective for Surface");
        }
        this.mSurfaceHolder = null;
        _setVideoSurface(surface);
        updateSurfaceScreenOn();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native void setVolume(float f2, float f3);

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @SuppressLint({"Wakelock"})
    public void setWakeMode(Context context, int i2) {
        boolean z;
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                z = true;
                this.mWakeLock.release();
            } else {
                z = false;
            }
            this.mWakeLock = null;
        } else {
            z = false;
        }
        PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i2 | SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, IjkMediaPlayer.class.getName());
        this.mWakeLock = newWakeLock;
        newWakeLock.setReferenceCounted(false);
        if (z) {
            this.mWakeLock.acquire();
        }
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void start() {
        try {
            super.start();
            stayAwake(true);
            _start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void stop() {
        try {
            super.stop();
            stayAwake(false);
            _stop();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public IjkMediaPlayer(IjkLibLoader ijkLibLoader) {
        this.mWakeLock = null;
        this.mPrepareStartTime = 0L;
        this.mLoop = false;
        this.mLoopCount = 0;
        this.mEventListeners = new HashSet();
        initPlayer(ijkLibLoader);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public float getPropertyFloat(int i2) {
        return _getPropertyFloat(i2, 0.0f);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public IjkTrackInfo[] getTrackInfo() {
        IjkMediaMeta parse;
        Bundle mediaMeta = getMediaMeta();
        if (mediaMeta == null || (parse = IjkMediaMeta.parse(mediaMeta)) == null || parse.mStreams == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<IjkMediaMeta.IjkStreamMeta> it = parse.mStreams.iterator();
        while (it.hasNext()) {
            IjkMediaMeta.IjkStreamMeta next = it.next();
            IjkTrackInfo ijkTrackInfo = new IjkTrackInfo(next);
            if (next.mType.equalsIgnoreCase("video")) {
                ijkTrackInfo.setTrackType(1);
            } else if (next.mType.equalsIgnoreCase("audio")) {
                ijkTrackInfo.setTrackType(2);
            } else if (next.mType.equalsIgnoreCase("timedtext")) {
                ijkTrackInfo.setTrackType(3);
            }
            arrayList.add(ijkTrackInfo);
        }
        return (IjkTrackInfo[]) arrayList.toArray(new IjkTrackInfo[arrayList.size()]);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @TargetApi(14)
    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        String scheme = uri.getScheme();
        if ("file".equals(scheme)) {
            setDataSource(uri.getPath());
        } else if ("content".equals(scheme) && "settings".equals(uri.getAuthority()) && (uri = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.getDefaultType(uri))) == null) {
            throw new FileNotFoundException("Failed to resolve default ringtone");
        } else {
            AssetFileDescriptor assetFileDescriptor = null;
            try {
                AssetFileDescriptor openAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
                if (openAssetFileDescriptor == null) {
                    if (openAssetFileDescriptor != null) {
                        openAssetFileDescriptor.close();
                        return;
                    }
                    return;
                }
                if (openAssetFileDescriptor.getDeclaredLength() < 0) {
                    setDataSource(openAssetFileDescriptor.getFileDescriptor());
                } else {
                    setDataSource(openAssetFileDescriptor.getFileDescriptor(), openAssetFileDescriptor.getStartOffset(), openAssetFileDescriptor.getDeclaredLength());
                }
                if (openAssetFileDescriptor != null) {
                    openAssetFileDescriptor.close();
                }
            } catch (Exception unused) {
                if (0 != 0) {
                    assetFileDescriptor.close();
                }
                DebugLog.d(TAG, "Couldn't open file on client side, trying server side");
                setDataSource(uri.toString(), map);
            } catch (Throwable th) {
                if (0 != 0) {
                    assetFileDescriptor.close();
                }
                throw th;
            }
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOption(int i2, String str, long j2) {
        _setOption(i2, str, j2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(String str) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.mDataSource = str;
        _setDataSource(str, null, null);
    }

    public void setDataSource(String str, Map<String, String> map) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append(":");
                if (!TextUtils.isEmpty(entry.getValue())) {
                    sb.append(entry.getValue());
                }
                sb.append("\r\n");
                setOption(1, "headers", sb.toString());
                setOption(1, "protocol_whitelist", "rtmp,ijklas,quics,quic,ijkio,async,cache,crypto,file,http,https,ijkhttphook,ijkinject,ijklivehook,ijklongurl,ijksegment,ijktcphook,pipe,rtp,tcp,tls,udp,ijkurlhook,data");
            }
        }
        setOption(4, "enable-position-notify", 1L);
        setDataSource(str);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @TargetApi(13)
    public void setDataSource(FileDescriptor fileDescriptor) throws IOException, IllegalArgumentException, IllegalStateException {
        if (Build.VERSION.SDK_INT < 12) {
            try {
                Field declaredField = fileDescriptor.getClass().getDeclaredField("descriptor");
                declaredField.setAccessible(true);
                _setDataSourceFd(declaredField.getInt(fileDescriptor));
                return;
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (NoSuchFieldException e3) {
                throw new RuntimeException(e3);
            }
        }
        ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        try {
            _setDataSourceFd(dup.getFd());
        } finally {
            dup.close();
        }
    }

    private void setDataSource(FileDescriptor fileDescriptor, long j2, long j3) throws IOException, IllegalArgumentException, IllegalStateException {
        setDataSource(fileDescriptor);
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) throws IllegalArgumentException, SecurityException, IllegalStateException {
        _setDataSource(iMediaDataSource);
    }
}
