package tv.danmaku.ijk.media.ext.pool;

import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.opengl.GLES20;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.example.widget.media.TextureRenderView;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.JDPlayerHelper;
import tv.danmaku.ijk.media.utils.DebugLog;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;

/* loaded from: classes11.dex */
public class JDPlayerManager {
    private static final int MAX_POOL_SIZE = 4;
    private static final String TAG = "JDPlayerManager";
    private static JDPlayerManager mInstance;
    private static CopyOnWriteArrayList<JDPlayStateInfo> playerCache;

    /* loaded from: classes11.dex */
    public interface JDPlayerManagerCallback {
        void onError(int i2, String str);

        void onPlayerAvailable(IMediaPlayer iMediaPlayer, boolean z, Uri uri);

        void onRenderAvailable(TextureRenderView textureRenderView);
    }

    private JDPlayerManager() {
        playerCache = new CopyOnWriteArrayList<>();
    }

    public static JDPlayerManager getInstance() {
        if (mInstance == null) {
            mInstance = new JDPlayerManager();
        }
        return mInstance;
    }

    private JDPlayStateInfo getPlayStateInfo(String str) {
        JDPlayStateInfo jDPlayStateInfo = new JDPlayStateInfo(str);
        if (TextUtils.isEmpty(str) || playerCache.isEmpty() || !playerCache.contains(jDPlayStateInfo)) {
            return null;
        }
        CopyOnWriteArrayList<JDPlayStateInfo> copyOnWriteArrayList = playerCache;
        return copyOnWriteArrayList.get(copyOnWriteArrayList.indexOf(jDPlayStateInfo));
    }

    public IJDVideoPlayer getActivePlayer(String str) {
        DebugLog.d(TAG, "getActivePlayer, size = " + playerCache.size());
        JDPlayStateInfo playStateInfo = getPlayStateInfo(str);
        if (playStateInfo != null) {
            return playStateInfo.getMediaPlayer();
        }
        return null;
    }

    public TextureRenderView getRenderView(String str) {
        JDPlayStateInfo playStateInfo = getPlayStateInfo(str);
        if (playStateInfo == null || playStateInfo.getSurfaceTexture() == null) {
            return null;
        }
        return playStateInfo.getSurfaceTexture();
    }

    public SurfaceTexture newSurfaceTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        SurfaceTexture surfaceTexture = new SurfaceTexture(iArr[0]);
        surfaceTexture.detachFromGLContext();
        return surfaceTexture;
    }

    public void notifyOtherPause(String str) {
    }

    public void pause(String str) {
        IJDVideoPlayer activePlayer;
        if (TextUtils.isEmpty(str) || (activePlayer = getActivePlayer(str)) == null) {
            return;
        }
        activePlayer.pause();
    }

    public void preparePlay(JDVideoView jDVideoView, String str, IPlayerControl.PlayerOptions playerOptions, HashMap<String, String> hashMap, JDPlayerManagerCallback jDPlayerManagerCallback) {
        IMediaPlayer iMediaPlayer;
        if (playerOptions == null) {
            if (jDPlayerManagerCallback != null) {
                jDPlayerManagerCallback.onError(-1, "PlayerOptions can not be null");
                return;
            }
            return;
        }
        TextureRenderView textureRenderView = null;
        if (playerOptions.isSharePlayer()) {
            IMediaPlayer mediaPlayer = getActivePlayer(str) != null ? getActivePlayer(str).getMediaPlayer() : null;
            textureRenderView = getRenderView(str);
            iMediaPlayer = mediaPlayer;
        } else {
            iMediaPlayer = null;
        }
        Uri generateUrl = JDPlayerHelper.getInstance().generateUrl(str, playerOptions, hashMap);
        try {
            if (textureRenderView == null || iMediaPlayer == null) {
                TextureRenderView textureRenderView2 = new TextureRenderView(jDVideoView.getContext().getApplicationContext());
                if (jDPlayerManagerCallback != null) {
                    try {
                        jDPlayerManagerCallback.onRenderAvailable(textureRenderView2);
                    } catch (IOException e2) {
                        e = e2;
                        textureRenderView = textureRenderView2;
                        e.printStackTrace();
                        if (jDPlayerManagerCallback != null) {
                            jDPlayerManagerCallback.onError(-1, "IOException");
                        }
                        saveInstance(str, playerOptions.getPreDrawKeycode(), jDVideoView, textureRenderView);
                    }
                }
                IMediaPlayer createIdlePlayer = JDPlayerHelper.getInstance().createIdlePlayer(playerOptions, generateUrl);
                if (jDVideoView.getTag() != null) {
                    DebugLog.d(TAG, "[" + ((Integer) jDVideoView.getTag()) + "] preparePlay, create player");
                }
                JDPlayerHelper.getInstance().setDataSource(createIdlePlayer, null, playerOptions, hashMap, generateUrl, str);
                if (jDPlayerManagerCallback != null) {
                    jDPlayerManagerCallback.onPlayerAvailable(createIdlePlayer, false, generateUrl);
                }
                createIdlePlayer.notifyPlayEvent(0);
                createIdlePlayer.notifyPlayState(0, 0L, null, null);
                createIdlePlayer.setAudioStreamType(3);
                createIdlePlayer.setScreenOnWhilePlaying(true);
                if (PlayerStringUtils.canPreSetLoop(generateUrl)) {
                    createIdlePlayer.setLooping(playerOptions.isLoop());
                    createIdlePlayer.setLoopCount(playerOptions.getLoopCount());
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    textureRenderView2.setExtraTexture(newSurfaceTexture());
                }
                textureRenderView2.getSurfaceHolder().bindToMediaPlayer(createIdlePlayer);
                createIdlePlayer.prepareAsync();
                textureRenderView = textureRenderView2;
            } else {
                if (jDPlayerManagerCallback != null) {
                    jDPlayerManagerCallback.onRenderAvailable(textureRenderView);
                }
                if (jDPlayerManagerCallback != null) {
                    jDPlayerManagerCallback.onPlayerAvailable(iMediaPlayer, true, generateUrl);
                }
            }
        } catch (IOException e3) {
            e = e3;
        }
        saveInstance(str, playerOptions.getPreDrawKeycode(), jDVideoView, textureRenderView);
    }

    public void releaseAll(int i2) {
        releaseExcludeUrl(i2, null);
    }

    public void releaseExcludeUrl(int i2, String str) {
        String str2 = "releaseExcludeUrl, size = " + playerCache.size();
        if (playerCache.isEmpty()) {
            return;
        }
        try {
            Iterator<JDPlayStateInfo> it = playerCache.iterator();
            while (it.hasNext()) {
                JDPlayStateInfo next = it.next();
                if (TextUtils.isEmpty(str) || !b.a(next.getOriginUrl(), str)) {
                    if (next.getKeycode() != i2) {
                        String str3 = "release player, not current page, input pageCode=" + i2 + ", current pageCode=" + next.getKeycode();
                    } else {
                        if (next.getMediaPlayer() != null) {
                            next.getMediaPlayer().forceRelase(true);
                        }
                        if (next.getSurfaceTexture() != null) {
                            next.getSurfaceTexture().release();
                        }
                        playerCache.remove(next);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String str4 = "releaseExcludeUrl, (after)size = " + playerCache.size();
    }

    public void releasePlayer(String str) {
        if (TextUtils.isEmpty(str) || getActivePlayer(str) == null) {
            return;
        }
        playerCache.remove(new JDPlayStateInfo(str));
        DebugLog.d(TAG, "releasePlayer, size = " + playerCache.size());
    }

    public void resume(String str) {
        IJDVideoPlayer activePlayer;
        if (TextUtils.isEmpty(str) || (activePlayer = getActivePlayer(str)) == null) {
            return;
        }
        activePlayer.start();
    }

    public void saveInstance(String str, int i2, IJDVideoPlayer iJDVideoPlayer, TextureRenderView textureRenderView) {
        JDPlayStateInfo jDPlayStateInfo = new JDPlayStateInfo(str, i2, new WeakReference(iJDVideoPlayer), textureRenderView);
        if (playerCache.contains(jDPlayStateInfo)) {
            return;
        }
        playerCache.add(jDPlayStateInfo);
        DebugLog.d(TAG, "saveInstance, size = " + playerCache.size() + ", save url = " + str);
    }
}
