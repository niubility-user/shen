package tv.danmaku.ijk.media.pha;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jd.libs.xwidget.b;
import com.jd.xbridge.base.IBridgeCallback;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.jdsdk.utils.NetUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.MediaPlayerHelper;
import tv.danmaku.ijk.media.ext.cache.JDPlayerVideoCache;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.ext.report.PlayerPerformMonitor;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.JDPlayerHelper;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;

/* loaded from: classes11.dex */
public class JDHybridVideoView extends b {
    private static final String DOM_UNIFORM_ID = "hybrid_xsl_id";
    private static final String TAG = "JDHybridVideoView";
    private static float curVolume;
    private static String idx;
    private static IjkMediaPlayer ijkMediaPlayer;
    private static PlayerHandler playerHandler;
    private Context mContext;
    private boolean needStart = false;

    /* loaded from: classes11.dex */
    private static class PlayerHandler extends Handler {
        public static final int MSG_CREATE = 1;
        public static final int MSG_PAUSE = 4;
        public static final int MSG_RELEASE = 7;
        public static final int MSG_SEEK = 6;
        public static final int MSG_START = 3;
        public static final int MSG_STOP = 5;
        public static final int MSG_SURFACE_AVAILABLE = 2;
        public static final int MSG_UPDATE_PROGRESS = 17;
        boolean autoPlay;
        private final HashMap<String, Object> dataList;
        int errCode;
        boolean isComplete;
        boolean isLive;
        String originUrl;
        private PlayerPerformMonitor performMonitor;
        String playType;
        int seekStart;
        private final WeakReference<JDHybridVideoView> videoViewWeakRef;

        public PlayerHandler(@NonNull Looper looper, JDHybridVideoView jDHybridVideoView) {
            super(looper);
            this.dataList = new HashMap<>();
            this.seekStart = 0;
            this.isLive = false;
            this.autoPlay = false;
            this.isComplete = false;
            this.originUrl = "";
            this.videoViewWeakRef = new WeakReference<>(jDHybridVideoView);
        }

        private void createPlayer(final Map<String, String> map) {
            this.isLive = DYConstants.DY_TRUE.equals(map.get("islive"));
            if (!TextUtils.isEmpty(map.get("playtype"))) {
                this.playType = map.get("playtype");
            }
            IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(this.isLive);
            playerOptions.setUseTextureView(false);
            if ("cover".equals(map.get("object-fit"))) {
                playerOptions.setCouldMediaCodec(false);
            } else {
                playerOptions.setCouldMediaCodec(true);
            }
            this.originUrl = map.get("src");
            Uri generateUrl = JDPlayerHelper.getInstance().generateUrl(this.originUrl, playerOptions, new HashMap<>());
            IjkMediaPlayer unused = JDHybridVideoView.ijkMediaPlayer = (IjkMediaPlayer) MediaPlayerHelper.createPlayer(playerOptions);
            PlayerConfigLoader.getInstance().applyConfig(JDHybridVideoView.ijkMediaPlayer, playerOptions, generateUrl);
            JDHybridVideoView.ijkMediaPlayer.setLooping(DYConstants.DY_TRUE.equals(map.get(JDPureVideoManager.SourceKey.LOOP)));
            if ("cover".equals(map.get("object-fit")) && !TextUtils.isEmpty(map.get("width")) && !TextUtils.isEmpty(map.get("height"))) {
                JDHybridVideoView.ijkMediaPlayer.setOption(4, "container-width", Integer.parseInt(map.get("width")));
                JDHybridVideoView.ijkMediaPlayer.setOption(4, "container-height", Integer.parseInt(map.get("height")));
            }
            float unused2 = JDHybridVideoView.curVolume = 1.0f;
            if (DYConstants.DY_TRUE.equals(map.get("muted"))) {
                float unused3 = JDHybridVideoView.curVolume = 0.0f;
            }
            JDHybridVideoView.ijkMediaPlayer.setVolume(JDHybridVideoView.curVolume, JDHybridVideoView.curVolume);
            if (!TextUtils.isEmpty(map.get("initial-time"))) {
                this.seekStart = Integer.parseInt(map.get("initial-time"));
            }
            JDHybridVideoView.ijkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() { // from class: tv.danmaku.ijk.media.pha.JDHybridVideoView.PlayerHandler.1
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    if (JDHybridVideoView.ijkMediaPlayer != null) {
                        if (PlayerHandler.this.seekStart > 0) {
                            JDHybridVideoView.ijkMediaPlayer.seekTo(PlayerHandler.this.seekStart);
                        }
                        PlayerHandler.this.dataList.clear();
                        PlayerHandler.this.dataList.put("duration", Long.valueOf(JDHybridVideoView.ijkMediaPlayer.getDuration()));
                        PlayerHandler.this.dataList.put("width", Integer.valueOf(JDHybridVideoView.ijkMediaPlayer.getVideoWidth()));
                        PlayerHandler.this.dataList.put("height", Integer.valueOf(JDHybridVideoView.ijkMediaPlayer.getVideoHeight()));
                        PlayerHandler playerHandler = PlayerHandler.this;
                        playerHandler.nativeInvoke("onPlayState", "prepared", playerHandler.dataList);
                        PlayerHandler.this.autoPlay = b.a(map.get("autoplay"), DYConstants.DY_TRUE);
                        if (PlayerHandler.this.autoPlay) {
                            JDHybridVideoView.ijkMediaPlayer.start();
                        }
                    }
                }
            });
            JDHybridVideoView.ijkMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() { // from class: tv.danmaku.ijk.media.pha.JDHybridVideoView.PlayerHandler.2
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
                public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i3) {
                    PlayerHandler.this.errCode = JDHybridVideoView.mapErrorCodeToHybrid(i3);
                    PlayerHandler.this.dataList.clear();
                    PlayerHandler.this.dataList.put("code", Integer.valueOf(PlayerHandler.this.errCode));
                    PlayerHandler.this.dataList.put("msg", "empty");
                    PlayerHandler playerHandler = PlayerHandler.this;
                    playerHandler.nativeInvoke("onPlayState", "error", playerHandler.dataList);
                    return true;
                }
            });
            JDHybridVideoView.ijkMediaPlayer.setOnPlayerEventListener(new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.pha.JDHybridVideoView.PlayerHandler.3
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
                public void onPlayEvent(int i2) {
                    if (i2 == 0) {
                        PlayerHandler.this.nativeInvoke("onPlayState", "idle", null);
                    } else if (i2 == 1) {
                        PlayerHandler.this.nativeInvoke("onPlayState", "playing", null);
                    } else if (i2 == 2) {
                        PlayerHandler.this.nativeInvoke("onPlayState", "pause", null);
                    } else {
                        if (i2 != 3) {
                            if (i2 == 5) {
                                PlayerHandler.this.nativeInvoke("onPlayState", "stop", null);
                                return;
                            } else if (i2 != 12) {
                                return;
                            }
                        }
                        PlayerHandler playerHandler = PlayerHandler.this;
                        playerHandler.isComplete = true;
                        playerHandler.nativeInvoke("onPlayState", "completed", null);
                    }
                }
            });
            JDHybridVideoView.ijkMediaPlayer.setOnVideoSizeChangedListener(new IMediaPlayer.OnVideoSizeChangedListener() { // from class: tv.danmaku.ijk.media.pha.JDHybridVideoView.PlayerHandler.4
                int preHeight;
                int preWidth;

                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5) {
                    if (this.preHeight != i3) {
                        PlayerHandler.this.dataList.clear();
                        PlayerHandler.this.dataList.put("width", Integer.valueOf(i2));
                        PlayerHandler.this.dataList.put("height", Integer.valueOf(i3));
                        PlayerHandler playerHandler = PlayerHandler.this;
                        playerHandler.nativeInvoke("onInfo", "onVideoSizeChange", playerHandler.dataList);
                    }
                    this.preHeight = i3;
                    this.preWidth = i2;
                }
            });
            JDHybridVideoView.ijkMediaPlayer.setOnInfoListener(new IMediaPlayer.OnInfoListener() { // from class: tv.danmaku.ijk.media.pha.JDHybridVideoView.PlayerHandler.5
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
                public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i3) {
                    if (i2 == 3) {
                        PlayerHandler.this.nativeInvoke("onInfo", "loadeddata", null);
                        PlayerHandler playerHandler = PlayerHandler.this;
                        if (playerHandler.isLive) {
                            return false;
                        }
                        playerHandler.sendEmptyMessageDelayed(17, 1000L);
                        return false;
                    } else if (i2 == 701) {
                        PlayerHandler.this.nativeInvoke("onInfo", "buffering", null);
                        return false;
                    } else if (i2 != 702) {
                        return false;
                    } else {
                        PlayerHandler.this.nativeInvoke("onInfo", "bufferEnd", null);
                        return false;
                    }
                }
            });
            try {
                if (!DYConstants.DY_TRUE.equals(map.get("usecache"))) {
                    JDHybridVideoView.ijkMediaPlayer.setDataSource(generateUrl.toString());
                } else {
                    JDPlayerVideoCache.getInstance().setDataSource(JDHybridVideoView.ijkMediaPlayer, this.videoViewWeakRef.get().mContext, generateUrl, this.originUrl, new HashMap());
                }
                if (this.performMonitor == null) {
                    this.performMonitor = new PlayerPerformMonitor(this.videoViewWeakRef.get().mContext, this.playType, this.originUrl, generateUrl, JDHybridVideoView.ijkMediaPlayer, playerOptions);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void nativeInvoke(String str, Object obj, HashMap<String, Object> hashMap) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (hashMap != null && hashMap.size() > 0) {
                    for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("eventName", str);
                jSONObject2.put("what", obj);
                jSONObject2.put("extra", jSONObject.toString());
                String format = String.format("%s && %s('%s')", "onPlayerCallback", "onPlayerCallback", JDHybridVideoView.string2JsStr(jSONObject2.toString()));
                String str2 = "sxj[" + JDHybridVideoView.idx + "]";
                String str3 = "nativeInvoke = " + format;
                WeakReference<JDHybridVideoView> weakReference = this.videoViewWeakRef;
                if (weakReference == null || weakReference.get() == null) {
                    return;
                }
                this.videoViewWeakRef.get().callJS(format, new IBridgeCallback() { // from class: tv.danmaku.ijk.media.pha.JDHybridVideoView.PlayerHandler.6
                    @Override // com.jd.xbridge.base.IBridgeCallback
                    public void onError(@Nullable String str4) {
                    }

                    @Override // com.jd.xbridge.base.IBridgeCallback
                    public void onSuccess(@Nullable Object obj2) {
                    }
                });
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        private void release() {
            this.isComplete = false;
            this.autoPlay = false;
            this.isLive = false;
            this.originUrl = "";
            this.playType = "";
            this.seekStart = 0;
            this.errCode = 0;
            PlayerPerformMonitor playerPerformMonitor = this.performMonitor;
            if (playerPerformMonitor != null) {
                playerPerformMonitor.release();
                this.performMonitor = null;
            }
            if (JDHybridVideoView.ijkMediaPlayer != null) {
                JDHybridVideoView.ijkMediaPlayer.setSurface(null);
                JDHybridVideoView.ijkMediaPlayer.stop();
                JDHybridVideoView.ijkMediaPlayer.release();
                IjkMediaPlayer unused = JDHybridVideoView.ijkMediaPlayer = null;
            }
        }

        private void setSurface(Surface surface) {
            if (JDHybridVideoView.ijkMediaPlayer == null) {
                return;
            }
            try {
                JDHybridVideoView.ijkMediaPlayer.setSurface(surface);
                JDHybridVideoView.ijkMediaPlayer.prepareAsync();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 17) {
                if (JDHybridVideoView.ijkMediaPlayer != null) {
                    this.dataList.clear();
                    this.dataList.put("totalTime", Long.valueOf(JDHybridVideoView.ijkMediaPlayer.getDuration()));
                    this.dataList.put("playTime", Long.valueOf(JDHybridVideoView.ijkMediaPlayer.getCurrentPosition()));
                    nativeInvoke("onInfo", "onProgress", this.dataList);
                    sendEmptyMessageDelayed(17, 1000L);
                    return;
                }
                return;
            }
            switch (i2) {
                case 1:
                    Object obj = message.obj;
                    if (obj instanceof Map) {
                        createPlayer((Map) obj);
                        return;
                    }
                    return;
                case 2:
                    Object obj2 = message.obj;
                    if (obj2 instanceof Surface) {
                        setSurface((Surface) obj2);
                        return;
                    }
                    return;
                case 3:
                    break;
                case 4:
                    if (JDHybridVideoView.ijkMediaPlayer != null) {
                        JDHybridVideoView.ijkMediaPlayer.pause();
                        return;
                    }
                    return;
                case 5:
                case 7:
                    release();
                    return;
                case 6:
                    if (JDHybridVideoView.ijkMediaPlayer != null) {
                        JDHybridVideoView.ijkMediaPlayer.seekTo(message.arg1);
                        break;
                    }
                    break;
                default:
                    return;
            }
            if (JDHybridVideoView.ijkMediaPlayer != null) {
                JDHybridVideoView.ijkMediaPlayer.start();
            }
        }
    }

    private boolean checkValid(JDHPlayerJSEvent jDHPlayerJSEvent) {
        return jDHPlayerJSEvent != null && jDHPlayerJSEvent.tagId.equals(idx);
    }

    public static int mapErrorCodeToHybrid(int i2) {
        switch (i2) {
            case IjkMediaPlayer.AVERROR_HTTP_SERVER_ERROR /* -1482175992 */:
            case IjkMediaPlayer.AVERROR_HTTP_OTHER_4XX /* -1482175736 */:
            case IjkMediaPlayer.AVERROR_HTTP_NOT_FOUND /* -875574520 */:
            case IjkMediaPlayer.AVERROR_HTTP_FORBIDDEN /* -858797304 */:
            case IjkMediaPlayer.AVERROR_HTTP_UNAUTHORIZED /* -825242872 */:
            case IjkMediaPlayer.AVERROR_HTTP_BAD_REQUEST /* -808465656 */:
            case -2005:
            case JDRiskHandleError.CODE_SDK_NOT_INIT /* -2004 */:
            case -1003:
            case -1002:
            case -1001:
                return 2;
            case -1381258232:
            case -1330794744:
                return 4;
            case -541478725:
            case -5:
                return 1;
            default:
                return 3;
        }
    }

    public static String string2JsStr(String str) {
        return TextUtils.isEmpty(str) ? str : str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\\n").replace("\r", "\\r").replace("\f", "\\\f").replace("\u2028", "\\u2028").replace("\u2029", "\\u2029");
    }

    @Override // com.jd.libs.xwidget.b
    public void callNative(String str, IBridgeCallback iBridgeCallback) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            JDHPlayerJSEvent jDHPlayerJSEvent = new JDHPlayerJSEvent(jSONObject.optString(DOM_UNIFORM_ID), jSONObject.optString("functionName"), jSONObject.optString("value"));
            String str3 = "JDHybridVideoView[" + idx + "]";
            String str4 = "js call native, params = " + str;
            if (!checkValid(jDHPlayerJSEvent) || ijkMediaPlayer == null) {
                return;
            }
            Message obtainMessage = playerHandler.obtainMessage();
            String str5 = jDHPlayerJSEvent.funName;
            str5.hashCode();
            char c2 = '\uffff';
            switch (str5.hashCode()) {
                case -1386733151:
                    if (str5.equals(JDHPlayerJSEvent.GET_CURRENTSRC)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1313037295:
                    if (str5.equals(JDHPlayerJSEvent.GET_PLAYBACKRATE)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1180327186:
                    if (str5.equals(JDHPlayerJSEvent.IS_LOOP)) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1073342556:
                    if (str5.equals(JDHPlayerJSEvent.IS_PLAYING)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -906224877:
                    if (str5.equals(JDHPlayerJSEvent.PLAY_SEEK)) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -321287432:
                    if (str5.equals(JDHPlayerJSEvent.IS_PAUSED)) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -39033168:
                    if (str5.equals(JDHPlayerJSEvent.PLAY_CUR_DURATION)) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 3363353:
                    if (str5.equals("mute")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 3443508:
                    if (str5.equals("play")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 3540994:
                    if (str5.equals("stop")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 85887754:
                    if (str5.equals(JDHPlayerJSEvent.PLAY_DURATION)) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case 106440182:
                    if (str5.equals("pause")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 646291629:
                    if (str5.equals(JDHPlayerJSEvent.IS_AUTOPLAY)) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 670514716:
                    if (str5.equals(JDHPlayerJSEvent.PLAY_SET_VOLUME)) {
                        c2 = '\r';
                        break;
                    }
                    break;
                case 885131792:
                    if (str5.equals(JDHPlayerJSEvent.PLAY_GET_VOLUME)) {
                        c2 = 14;
                        break;
                    }
                    break;
                case 1090594823:
                    if (str5.equals("release")) {
                        c2 = 15;
                        break;
                    }
                    break;
                case 1714085202:
                    if (str5.equals(JDHPlayerJSEvent.GET_NETTYPE)) {
                        c2 = 16;
                        break;
                    }
                    break;
                case 1952610386:
                    if (str5.equals(JDHPlayerJSEvent.GET_ERROR)) {
                        c2 = 17;
                        break;
                    }
                    break;
                case 2058057648:
                    if (str5.equals(JDHPlayerJSEvent.IS_ENDED)) {
                        c2 = 18;
                        break;
                    }
                    break;
                case 2065669729:
                    if (str5.equals(JDHPlayerJSEvent.IS_MUTED)) {
                        c2 = 19;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    iBridgeCallback.onSuccess(playerHandler.originUrl);
                    break;
                case 1:
                    iBridgeCallback.onSuccess(Float.valueOf(ijkMediaPlayer.getSpeed(0.0f)));
                    break;
                case 2:
                    iBridgeCallback.onSuccess(Boolean.valueOf(ijkMediaPlayer.isLooping()));
                    break;
                case 3:
                    IjkMediaPlayer ijkMediaPlayer2 = ijkMediaPlayer;
                    if (ijkMediaPlayer2 != null) {
                        iBridgeCallback.onSuccess(Boolean.valueOf(ijkMediaPlayer2.isPlaying()));
                        break;
                    }
                    break;
                case 4:
                    try {
                        obtainMessage.what = 6;
                        obtainMessage.arg1 = Integer.parseInt(jDHPlayerJSEvent.args);
                        break;
                    } catch (NumberFormatException unused) {
                        break;
                    }
                case 5:
                    iBridgeCallback.onSuccess(Boolean.valueOf(!ijkMediaPlayer.isPlaying()));
                    break;
                case 6:
                    IjkMediaPlayer ijkMediaPlayer3 = ijkMediaPlayer;
                    if (ijkMediaPlayer3 != null) {
                        iBridgeCallback.onSuccess(Long.valueOf(ijkMediaPlayer3.getCurrentPosition()));
                        break;
                    }
                    break;
                case 7:
                    if (ijkMediaPlayer != null) {
                        float f2 = Boolean.parseBoolean(jDHPlayerJSEvent.args) ? 0.0f : 1.0f;
                        curVolume = f2;
                        ijkMediaPlayer.setVolume(f2, f2);
                        break;
                    }
                    break;
                case '\b':
                    obtainMessage.what = 3;
                    break;
                case '\t':
                    obtainMessage.what = 5;
                    break;
                case '\n':
                    iBridgeCallback.onSuccess(Long.valueOf(ijkMediaPlayer.getDuration()));
                    break;
                case 11:
                    obtainMessage.what = 4;
                    break;
                case '\f':
                    iBridgeCallback.onSuccess(Boolean.valueOf(playerHandler.autoPlay));
                    break;
                case '\r':
                    try {
                        float parseFloat = Float.parseFloat(jDHPlayerJSEvent.args);
                        curVolume = parseFloat;
                        ijkMediaPlayer.setVolume(parseFloat, parseFloat);
                        break;
                    } catch (NullPointerException | NumberFormatException e2) {
                        e2.printStackTrace();
                        break;
                    }
                case 14:
                    iBridgeCallback.onSuccess(Float.valueOf(curVolume));
                    break;
                case 15:
                    obtainMessage.what = 7;
                    break;
                case 16:
                    if (PlayerNetworkUtil.isMobileNet()) {
                        str2 = "3g";
                    } else {
                        str2 = NetUtils.isWifi() ? "wifi" : "";
                    }
                    iBridgeCallback.onSuccess(str2);
                    break;
                case 17:
                    iBridgeCallback.onSuccess(Integer.valueOf(playerHandler.errCode));
                    break;
                case 18:
                    iBridgeCallback.onSuccess(Boolean.valueOf(playerHandler.isComplete));
                    break;
                case 19:
                    iBridgeCallback.onSuccess(Boolean.valueOf(curVolume == 0.0f));
                    break;
            }
            playerHandler.sendMessage(obtainMessage);
        } catch (Exception e3) {
            String str6 = "Error on callNative(String, IBridgeCallback): " + e3;
        }
    }

    @Override // com.jd.libs.xwidget.b
    public void getData(Context context, Map<String, String> map) {
        if (map == null || !map.containsKey(DOM_UNIFORM_ID)) {
            return;
        }
        this.mContext = context;
        playerHandler = new PlayerHandler(Looper.getMainLooper(), this);
        idx = map.get(DOM_UNIFORM_ID);
        if (map.size() > 0) {
            String str = "JDHybridVideoView[" + idx + "]";
            String str2 = "getData, create player, " + map;
        }
        MediaPlayerHelper.loadLibrariesOnceSafe(this.mContext);
        Message obtainMessage = playerHandler.obtainMessage();
        obtainMessage.obj = map;
        obtainMessage.what = 1;
        playerHandler.sendMessage(obtainMessage);
    }

    @Override // com.jd.libs.xwidget.b, com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onDeactive() {
        String str = "JDHybridVideoView[" + idx + "]";
        super.onDeactive();
        Message obtainMessage = playerHandler.obtainMessage();
        obtainMessage.what = 7;
        playerHandler.sendMessage(obtainMessage);
    }

    @Override // com.jd.libs.xwidget.b, com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onDestroy() {
        String str = "JDHybridVideoView[" + idx + "]";
    }

    @Override // com.jd.libs.xwidget.b, com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onRectChanged(Rect rect) {
        super.onRectChanged(rect);
    }

    @Override // com.jd.libs.xwidget.b, com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onSurfaceCreated(Surface surface) {
        String str = "JDHybridVideoView[" + idx + "]";
        String str2 = "onSurfaceCreated: " + surface.toString();
        Message obtainMessage = playerHandler.obtainMessage();
        obtainMessage.obj = surface;
        obtainMessage.what = 2;
        playerHandler.sendMessage(obtainMessage);
    }

    @Override // com.jd.libs.xwidget.b, com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onSurfaceDestroyed(Surface surface) {
        String str = "JDHybridVideoView[" + idx + "]";
        Message obtainMessage = playerHandler.obtainMessage();
        obtainMessage.what = 7;
        playerHandler.sendMessage(obtainMessage);
    }

    @Override // com.jd.libs.xwidget.b, com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onVisibilityChanged(boolean z) {
        super.onVisibilityChanged(z);
        if (ijkMediaPlayer == null) {
            return;
        }
        Message obtainMessage = playerHandler.obtainMessage();
        if (z && this.needStart) {
            obtainMessage.what = 3;
            this.needStart = false;
        } else if (!z && ijkMediaPlayer.isPlaying()) {
            obtainMessage.what = 4;
            this.needStart = true;
        }
        playerHandler.sendMessage(obtainMessage);
    }
}
