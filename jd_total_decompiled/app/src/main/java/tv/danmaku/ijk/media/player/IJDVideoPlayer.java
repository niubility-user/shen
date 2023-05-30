package tv.danmaku.ijk.media.player;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.MediaController;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public abstract class IJDVideoPlayer extends FrameLayout implements IPlayerControl, MediaController.MediaPlayerControl {
    protected IMediaPlayer mMediaPlayer;
    protected final List<IMediaPlayer.OnPlayerEventListener> mOnPlayerEventListeners;
    protected final List<IPlayerControl.OnPlayerStateListener> mOnPlayerStateListeners;
    protected final List<IPlayerControl.OnVideoSizeChangedListener> mOnVideoSizeChangedListeners;

    public IJDVideoPlayer(@NonNull Context context) {
        super(context);
        this.mMediaPlayer = null;
        this.mOnPlayerEventListeners = new CopyOnWriteArrayList();
        this.mOnVideoSizeChangedListeners = new CopyOnWriteArrayList();
        this.mOnPlayerStateListeners = new CopyOnWriteArrayList();
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public abstract void forceRelase(boolean z);

    public int getAudioSessionId() {
        return 0;
    }

    public abstract IjkMediaPlayer getIjkMediaPlayer();

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl
    public IMediaPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    public int getPlayState() {
        return 0;
    }

    public float getVolume() {
        return 0.0f;
    }

    public abstract void initRenders();

    public abstract void releaseInThread(boolean z);

    public void removeOnVideoSizeChangedListener(IPlayerControl.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (this.mOnVideoSizeChangedListeners.contains(onVideoSizeChangedListener)) {
            this.mOnVideoSizeChangedListeners.remove(onVideoSizeChangedListener);
        }
    }

    public void removePlayerEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        if (this.mOnPlayerEventListeners.contains(onPlayerEventListener)) {
            this.mOnPlayerEventListeners.remove(onPlayerEventListener);
        }
    }

    public void removePlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        if (this.mOnPlayerStateListeners.contains(onPlayerStateListener)) {
            this.mOnPlayerStateListeners.remove(onPlayerStateListener);
        }
    }

    public void retry(boolean z) {
    }

    public abstract void setAspectRatio(@IPlayerControl.AspectRatioType int i2);

    public void setOnPlayerEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        if (this.mOnPlayerEventListeners.contains(onPlayerEventListener)) {
            return;
        }
        this.mOnPlayerEventListeners.add(onPlayerEventListener);
    }

    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        if (this.mOnPlayerStateListeners.contains(onPlayerStateListener)) {
            return;
        }
        this.mOnPlayerStateListeners.add(onPlayerStateListener);
    }

    public void setOnVideoSizeChangedListener(IPlayerControl.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (this.mOnVideoSizeChangedListeners.contains(onVideoSizeChangedListener)) {
            return;
        }
        this.mOnVideoSizeChangedListeners.add(onVideoSizeChangedListener);
    }

    public void setSpeed(float f2) {
    }

    public abstract void setVolume(float f2);

    public IJDVideoPlayer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMediaPlayer = null;
        this.mOnPlayerEventListeners = new CopyOnWriteArrayList();
        this.mOnVideoSizeChangedListeners = new CopyOnWriteArrayList();
        this.mOnPlayerStateListeners = new CopyOnWriteArrayList();
    }

    public IJDVideoPlayer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMediaPlayer = null;
        this.mOnPlayerEventListeners = new CopyOnWriteArrayList();
        this.mOnVideoSizeChangedListeners = new CopyOnWriteArrayList();
        this.mOnPlayerStateListeners = new CopyOnWriteArrayList();
    }

    @TargetApi(21)
    public IJDVideoPlayer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mMediaPlayer = null;
        this.mOnPlayerEventListeners = new CopyOnWriteArrayList();
        this.mOnVideoSizeChangedListeners = new CopyOnWriteArrayList();
        this.mOnPlayerStateListeners = new CopyOnWriteArrayList();
    }
}
