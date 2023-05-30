package tv.danmaku.ijk.media.widget.window;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.utils.PlayerSystemUtil;
import tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController;
import tv.danmaku.ijk.media.widget.controller.impl.DefaultWindowPlayerController;
import tv.danmaku.ijk.media.widget.controller.impl.JDLiveWindowPlayerController;
import tv.danmaku.ijk.media.widget.controller.impl.JDShopWindowPlayerController;
import tv.danmaku.ijk.media.widget.window.JDWindowPlayer;
import tv.danmaku.ijk.media.widget.window.WindowPlayerConfig;

/* loaded from: classes11.dex */
public class JDWindowPlayerManager implements WindowPlayerControl {
    public static final int ERROR_ADD_WINDOW = -2;
    public static final int ERROR_EMPTY_WINDOW_CONFIG = -5;
    public static final int ERROR_NON_PLAYER_VIEW = -4;
    public static final int ERROR_NO_PERMISSION = -1;
    public static final int ERROR_REMOVE_WINDOW = -3;
    private final JDWindowPlayer.JDWindowPlayerCallback mCallback = new JDWindowPlayer.JDWindowPlayerCallback() { // from class: tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager.1
        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayer.JDWindowPlayerCallback
        public void closeWindow() {
            JDWindowPlayerManager.this.close();
        }

        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayer.JDWindowPlayerCallback
        public boolean onWindowClick() {
            if (JDWindowPlayerManager.this.playWindowCallback != null) {
                return JDWindowPlayerManager.this.playWindowCallback.onWindowClick();
            }
            return false;
        }

        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayer.JDWindowPlayerCallback
        public void onWindowPosChange(int i2, int i3) {
            if (JDWindowPlayerManager.this.mWindowManager == null || JDWindowPlayerManager.this.windowPlayer == null) {
                return;
            }
            JDWindowPlayerManager.this.mWindowParams.x = i2;
            JDWindowPlayerManager.this.mWindowParams.y = i3;
            if (JDWindowPlayerManager.this.mConfig != null) {
                JDWindowPlayerManager.this.mConfig.getRect().x = i2;
                JDWindowPlayerManager.this.mConfig.getRect().y = i3;
            }
            JDWindowPlayerManager.this.mWindowManager.updateViewLayout(JDWindowPlayerManager.this.windowPlayer, JDWindowPlayerManager.this.mWindowParams);
        }
    };
    private WindowPlayerConfig mConfig;
    private WeakReference<Context> mContext;
    private ViewGroup mCurVideoView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private OnPlayWindowCallback playWindowCallback;
    private JDWindowPlayer windowPlayer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager$2  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType;

        static {
            int[] iArr = new int[WindowPlayerConfig.WindowType.values().length];
            $SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType = iArr;
            try {
                iArr[WindowPlayerConfig.WindowType.JD_SHOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType[WindowPlayerConfig.WindowType.JD_LIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface OnPlayWindowCallback {
        boolean onClose(ViewGroup viewGroup);

        void onError(int i2, ViewGroup viewGroup);

        void onShow();

        boolean onWindowClick();

        void onWindowVisible(boolean z);
    }

    private JDWindowPlayerController createControllerByType(WindowPlayerConfig.WindowType windowType) {
        WeakReference<Context> weakReference = this.mContext;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        int i2 = AnonymousClass2.$SwitchMap$tv$danmaku$ijk$media$widget$window$WindowPlayerConfig$WindowType[windowType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return new DefaultWindowPlayerController(this.mContext.get());
            }
            return new JDLiveWindowPlayerController(this.mContext.get());
        }
        return new JDShopWindowPlayerController(this.mContext.get());
    }

    private void releaseVideoView() {
        JDWindowPlayer jDWindowPlayer = this.windowPlayer;
        if (jDWindowPlayer == null || jDWindowPlayer.getVideoView() == null) {
            return;
        }
        this.windowPlayer.releasePlayer();
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void close() {
        JDWindowPlayer jDWindowPlayer;
        OnPlayWindowCallback onPlayWindowCallback = this.playWindowCallback;
        if (onPlayWindowCallback != null) {
            JDWindowPlayer jDWindowPlayer2 = this.windowPlayer;
            if (onPlayWindowCallback.onClose(jDWindowPlayer2 != null ? jDWindowPlayer2.getVideoView() : null)) {
                releaseVideoView();
            }
        } else {
            releaseVideoView();
        }
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null && (jDWindowPlayer = this.windowPlayer) != null) {
            windowManager.removeView(jDWindowPlayer);
            this.mWindowManager = null;
            this.windowPlayer = null;
        }
        this.mWindowParams = null;
        this.mContext = null;
        this.mConfig = null;
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void hide() {
        JDWindowPlayer jDWindowPlayer;
        if (this.mWindowManager == null || (jDWindowPlayer = this.windowPlayer) == null) {
            return;
        }
        try {
            jDWindowPlayer.hide();
            this.mWindowManager.removeView(this.windowPlayer);
            OnPlayWindowCallback onPlayWindowCallback = this.playWindowCallback;
            if (onPlayWindowCallback != null) {
                onPlayWindowCallback.onWindowVisible(false);
            }
        } catch (Exception unused) {
            OnPlayWindowCallback onPlayWindowCallback2 = this.playWindowCallback;
            if (onPlayWindowCallback2 != null) {
                onPlayWindowCallback2.onError(-3, this.mCurVideoView);
            }
        }
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public boolean isMute() {
        JDWindowPlayer jDWindowPlayer = this.windowPlayer;
        if (jDWindowPlayer == null) {
            return false;
        }
        return jDWindowPlayer.isMute();
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public boolean isPlaying() {
        JDWindowPlayer jDWindowPlayer = this.windowPlayer;
        if (jDWindowPlayer == null) {
            return false;
        }
        return jDWindowPlayer.isPlaying();
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public boolean mute() {
        JDWindowPlayer jDWindowPlayer = this.windowPlayer;
        if (jDWindowPlayer == null) {
            return false;
        }
        return jDWindowPlayer.mute();
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void pause() {
        JDWindowPlayer jDWindowPlayer;
        if (this.mWindowManager == null || (jDWindowPlayer = this.windowPlayer) == null) {
            return;
        }
        jDWindowPlayer.pause();
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void reload() {
        JDWindowPlayer jDWindowPlayer = this.windowPlayer;
        if (jDWindowPlayer == null) {
            return;
        }
        jDWindowPlayer.reload();
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void resume() {
        JDWindowPlayer jDWindowPlayer;
        if (this.mWindowManager == null || (jDWindowPlayer = this.windowPlayer) == null) {
            return;
        }
        jDWindowPlayer.resume();
    }

    public void setOnPlayWindowCallback(OnPlayWindowCallback onPlayWindowCallback) {
        this.playWindowCallback = onPlayWindowCallback;
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void setOnPlayerEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        JDWindowPlayer jDWindowPlayer = this.windowPlayer;
        if (jDWindowPlayer == null || onPlayerEventListener == null) {
            return;
        }
        jDWindowPlayer.setOnPlayerEventListener(onPlayerEventListener);
    }

    public void show(@NonNull Context context, WindowPlayerConfig.WindowType windowType, String str, @NonNull IPlayerControl.PlayerOptions playerOptions) {
        if (context == null || this.windowPlayer != null) {
            return;
        }
        IjkVideoView ijkVideoView = new IjkVideoView(context);
        if (playerOptions != null) {
            ijkVideoView.setPlayerOptions(playerOptions);
        }
        ijkVideoView.setVideoPath(str);
        show(context, windowType, ijkVideoView);
    }

    public void show(@NonNull Context context, WindowPlayerConfig.WindowType windowType, @NonNull ViewGroup viewGroup) {
        show(context, new WindowPlayerConfig.Build().context(context).type(windowType).builder(), viewGroup);
    }

    public void show(@NonNull Context context, WindowPlayerConfig windowPlayerConfig, @NonNull ViewGroup viewGroup) {
        this.mCurVideoView = viewGroup;
        if (!PlayerSystemUtil.requestOverlayPermission(context)) {
            OnPlayWindowCallback onPlayWindowCallback = this.playWindowCallback;
            if (onPlayWindowCallback != null) {
                onPlayWindowCallback.onError(-1, viewGroup);
            }
        } else if (windowPlayerConfig == null) {
            OnPlayWindowCallback onPlayWindowCallback2 = this.playWindowCallback;
            if (onPlayWindowCallback2 != null) {
                onPlayWindowCallback2.onError(-5, viewGroup);
            }
        } else if (viewGroup == null) {
            OnPlayWindowCallback onPlayWindowCallback3 = this.playWindowCallback;
            if (onPlayWindowCallback3 != null) {
                onPlayWindowCallback3.onError(-4, viewGroup);
            }
        } else {
            this.mContext = new WeakReference<>(context);
            if (this.windowPlayer != null) {
                return;
            }
            this.mWindowManager = (WindowManager) context.getSystemService("window");
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mWindowParams = layoutParams;
            if (Build.VERSION.SDK_INT >= 26) {
                layoutParams.type = R2.attr.textIsDisplayable;
            } else {
                layoutParams.type = 2002;
            }
            layoutParams.flags = 16777256;
            layoutParams.format = -3;
            layoutParams.gravity = 51;
            this.mConfig = windowPlayerConfig;
            layoutParams.x = windowPlayerConfig.getRect().x;
            this.mWindowParams.y = this.mConfig.getRect().y;
            this.mWindowParams.width = PlayerSystemUtil.dip2px(context, this.mConfig.getRect().width);
            this.mWindowParams.height = PlayerSystemUtil.dip2px(context, this.mConfig.getRect().height);
            JDWindowPlayer jDWindowPlayer = new JDWindowPlayer(context);
            this.windowPlayer = jDWindowPlayer;
            jDWindowPlayer.setWindowPlayerCallback(this.mCallback);
            if (this.mConfig.isShowBorder()) {
                this.windowPlayer.showBorder(this.mConfig.getBorderWidth(), this.mConfig.getBorderRadius(), this.mConfig.getBorderColor());
                this.mWindowParams.format = 1;
            }
            this.windowPlayer.attachController(createControllerByType(this.mConfig.getType()));
            if (viewGroup != null) {
                this.windowPlayer.setVideoView(viewGroup, this.mConfig.isLive());
            }
            try {
                this.mWindowManager.addView(this.windowPlayer, this.mWindowParams);
                OnPlayWindowCallback onPlayWindowCallback4 = this.playWindowCallback;
                if (onPlayWindowCallback4 != null) {
                    onPlayWindowCallback4.onShow();
                }
            } catch (Exception unused) {
                OnPlayWindowCallback onPlayWindowCallback5 = this.playWindowCallback;
                if (onPlayWindowCallback5 != null) {
                    onPlayWindowCallback5.onError(-2, this.mCurVideoView);
                }
            }
        }
    }

    @Override // tv.danmaku.ijk.media.widget.window.WindowPlayerControl
    public void show() {
        JDWindowPlayer jDWindowPlayer;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (jDWindowPlayer = this.windowPlayer) == null) {
            return;
        }
        try {
            windowManager.addView(jDWindowPlayer, this.mWindowParams);
            this.windowPlayer.show();
            OnPlayWindowCallback onPlayWindowCallback = this.playWindowCallback;
            if (onPlayWindowCallback != null) {
                onPlayWindowCallback.onWindowVisible(true);
            }
        } catch (Exception unused) {
            OnPlayWindowCallback onPlayWindowCallback2 = this.playWindowCallback;
            if (onPlayWindowCallback2 != null) {
                onPlayWindowCallback2.onError(-2, this.mCurVideoView);
            }
        }
    }
}
