package tv.danmaku.ijk.media.player;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

/* loaded from: classes11.dex */
public abstract class AbstractMediaPlayer implements IMediaPlayer {
    private long prepareCost;
    private final List<IMediaPlayer.OnPreparedListener> mOnPreparedListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnCompletionListener> mOnCompletionListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnBufferingUpdateListener> mOnBufferingUpdateListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnSeekCompleteListener> mOnSeekCompleteListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnVideoSizeChangedListener> mOnVideoSizeChangedListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnErrorListener> mOnErrorListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnInfoListener> mOnInfoListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnTimedTextListener> mOnTimedTextListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnExtInfoListener> mOnExtInfoListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnSeiListener> mOnSeiListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnNativeInvokeListener> mOnNativeInvokeListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnPlayerEventListener> mOnPlayerEventListeners = new CopyOnWriteArrayList();
    private final List<IMediaPlayer.OnMediaCodecSelectListener> mOnMediaCodecSelectListeners = new CopyOnWriteArrayList();
    private final List<IPlayerControl.OnPlayerStateListener> mOnPlayerStateListeners = new CopyOnWriteArrayList();
    private final List<IPlayerControl.OnPlayerExtInfoListener> mOnPlayerExtInfoListeners = new CopyOnWriteArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i2, int i3) {
        if (this.mOnMediaCodecSelectListeners.isEmpty()) {
            return;
        }
        Iterator<IMediaPlayer.OnMediaCodecSelectListener> it = this.mOnMediaCodecSelectListeners.iterator();
        while (it.hasNext()) {
            it.next().onMediaCodecSelect(iMediaPlayer, str, i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean notifyNativeInvoke(int i2, Bundle bundle) {
        if (this.mOnNativeInvokeListeners.isEmpty()) {
            return false;
        }
        Iterator<IMediaPlayer.OnNativeInvokeListener> it = this.mOnNativeInvokeListeners.iterator();
        while (it.hasNext()) {
            it.next().onNativeInvoke(i2, bundle);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnBufferingUpdate(int i2) {
        if (this.mOnBufferingUpdateListeners.isEmpty()) {
            return;
        }
        Iterator<IMediaPlayer.OnBufferingUpdateListener> it = this.mOnBufferingUpdateListeners.iterator();
        while (it.hasNext()) {
            it.next().onBufferingUpdate(this, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnCompletion() {
        if (!this.mOnCompletionListeners.isEmpty()) {
            Iterator<IMediaPlayer.OnCompletionListener> it = this.mOnCompletionListeners.iterator();
            while (it.hasNext()) {
                it.next().onCompletion(this);
            }
        }
        notifyPlayEvent(3);
        notifyPlayState(2, 0L, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean notifyOnError(int i2, int i3) {
        if (this.mOnErrorListeners.isEmpty()) {
            return false;
        }
        notifyPlayEvent(4);
        Iterator<IMediaPlayer.OnErrorListener> it = this.mOnErrorListeners.iterator();
        while (it.hasNext()) {
            it.next().onError(this, i2, i3);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnExtInfo(int i2, int i3, int i4, HashMap<String, Object> hashMap) {
        if (!this.mOnExtInfoListeners.isEmpty()) {
            Iterator<IMediaPlayer.OnExtInfoListener> it = this.mOnExtInfoListeners.iterator();
            while (it.hasNext()) {
                it.next().onExtInfo(this, i2, i3, i4, hashMap);
            }
        }
        if (this.mOnPlayerExtInfoListeners.isEmpty()) {
            return;
        }
        Iterator<IPlayerControl.OnPlayerExtInfoListener> it2 = this.mOnPlayerExtInfoListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onExtInfo(i2, i3, i4, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean notifyOnInfo(int i2, int i3) {
        notifyPlayState(4, 0L, null, new int[]{i2, i3});
        if (this.mOnInfoListeners.isEmpty()) {
            return false;
        }
        Iterator<IMediaPlayer.OnInfoListener> it = this.mOnInfoListeners.iterator();
        while (it.hasNext()) {
            it.next().onInfo(this, i2, i3);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnPrepared(long j2) {
        this.prepareCost = j2;
        if (!this.mOnPreparedListeners.isEmpty()) {
            Iterator<IMediaPlayer.OnPreparedListener> it = this.mOnPreparedListeners.iterator();
            while (it.hasNext()) {
                it.next().onPrepared(this);
            }
        }
        notifyPlayEvent(6);
        notifyPlayState(1, j2, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnSeekComplete() {
        notifyPlayState(5, 0L, null, null);
        if (this.mOnSeekCompleteListeners.isEmpty()) {
            return;
        }
        Iterator<IMediaPlayer.OnSeekCompleteListener> it = this.mOnSeekCompleteListeners.iterator();
        while (it.hasNext()) {
            it.next().onSeekComplete(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnSei(String str) {
        if (this.mOnSeiListeners.isEmpty()) {
            return;
        }
        Iterator<IMediaPlayer.OnSeiListener> it = this.mOnSeiListeners.iterator();
        while (it.hasNext()) {
            it.next().onSeiText(this, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnTimedText(IjkTimedText ijkTimedText) {
        if (this.mOnTimedTextListeners.isEmpty()) {
            return;
        }
        Iterator<IMediaPlayer.OnTimedTextListener> it = this.mOnTimedTextListeners.iterator();
        while (it.hasNext()) {
            it.next().onTimedText(this, ijkTimedText);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyOnVideoSizeChanged(int i2, int i3, int i4, int i5) {
        if (this.mOnVideoSizeChangedListeners.isEmpty()) {
            return;
        }
        Iterator<IMediaPlayer.OnVideoSizeChangedListener> it = this.mOnVideoSizeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoSizeChanged(this, i2, i3, i4, i5);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void notifyPlayEvent(int i2) {
        if (this.mOnPlayerEventListeners.isEmpty()) {
            return;
        }
        Iterator<IMediaPlayer.OnPlayerEventListener> it = this.mOnPlayerEventListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlayEvent(i2);
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void notifyPlayState(int i2, long j2, int[] iArr, int[] iArr2) {
        if (this.mOnPlayerStateListeners.isEmpty()) {
            return;
        }
        for (IPlayerControl.OnPlayerStateListener onPlayerStateListener : this.mOnPlayerStateListeners) {
            switch (i2) {
                case 0:
                    onPlayerStateListener.onCreatePlayer();
                    break;
                case 1:
                    onPlayerStateListener.onPrepared(j2);
                    break;
                case 2:
                    onPlayerStateListener.onCompletion();
                    break;
                case 3:
                    if (iArr != null && iArr.length > 1) {
                        onPlayerStateListener.onError(iArr[0], iArr[1]);
                        break;
                    }
                    break;
                case 4:
                    if (iArr2 != null && iArr2.length > 1) {
                        onPlayerStateListener.onInfo(iArr2[0], iArr2[1]);
                        break;
                    }
                    break;
                case 5:
                    onPlayerStateListener.onSeekComplete();
                    break;
                case 6:
                    if (onPlayerStateListener instanceof IPlayerControl.OnPlayerStateExtListener) {
                        ((IPlayerControl.OnPlayerStateExtListener) onPlayerStateListener).onPreDrawReusePlayer();
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (onPlayerStateListener instanceof IPlayerControl.OnPlayerStateExtListener) {
                        ((IPlayerControl.OnPlayerStateExtListener) onPlayerStateListener).onPreDrawStart(this.prepareCost);
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void pause() {
        notifyPlayEvent(2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void release() {
        notifyPlayEvent(14);
        resetListeners();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void reset() {
        notifyPlayEvent(13);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void resetListeners() {
        this.mOnPreparedListeners.clear();
        this.mOnBufferingUpdateListeners.clear();
        this.mOnCompletionListeners.clear();
        this.mOnSeekCompleteListeners.clear();
        this.mOnVideoSizeChangedListeners.clear();
        this.mOnErrorListeners.clear();
        this.mOnExtInfoListeners.clear();
        this.mOnInfoListeners.clear();
        this.mOnTimedTextListeners.clear();
        this.mOnNativeInvokeListeners.clear();
        this.mOnMediaCodecSelectListeners.clear();
        this.mOnPlayerEventListeners.clear();
        this.mOnPlayerStateListeners.clear();
        this.mOnPlayerExtInfoListeners.clear();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) {
        throw new UnsupportedOperationException();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnBufferingUpdateListener(IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        if (this.mOnBufferingUpdateListeners.contains(onBufferingUpdateListener)) {
            return;
        }
        this.mOnBufferingUpdateListeners.add(onBufferingUpdateListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        if (this.mOnCompletionListeners.contains(onCompletionListener)) {
            return;
        }
        this.mOnCompletionListeners.add(onCompletionListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        if (this.mOnErrorListeners.contains(onErrorListener)) {
            return;
        }
        this.mOnErrorListeners.add(onErrorListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnExtInfoListener(IMediaPlayer.OnExtInfoListener onExtInfoListener) {
        if (onExtInfoListener == null || this.mOnExtInfoListeners.contains(onExtInfoListener)) {
            return;
        }
        this.mOnExtInfoListeners.add(onExtInfoListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        if (onInfoListener == null || this.mOnInfoListeners.contains(onInfoListener)) {
            return;
        }
        this.mOnInfoListeners.add(onInfoListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnMediaCodecSelectListener(IMediaPlayer.OnMediaCodecSelectListener onMediaCodecSelectListener) {
        if (this.mOnMediaCodecSelectListeners.contains(onMediaCodecSelectListener)) {
            return;
        }
        this.mOnMediaCodecSelectListeners.add(onMediaCodecSelectListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnNativeInvokeListener(IMediaPlayer.OnNativeInvokeListener onNativeInvokeListener) {
        if (this.mOnNativeInvokeListeners.contains(onNativeInvokeListener)) {
            return;
        }
        this.mOnNativeInvokeListeners.add(onNativeInvokeListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnPlayerEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener) {
        if (this.mOnPlayerEventListeners.contains(onPlayerEventListener)) {
            return;
        }
        this.mOnPlayerEventListeners.add(onPlayerEventListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        if (this.mOnPlayerStateListeners.contains(onPlayerStateListener)) {
            return;
        }
        this.mOnPlayerStateListeners.add(onPlayerStateListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        if (this.mOnPreparedListeners.contains(onPreparedListener)) {
            return;
        }
        this.mOnPreparedListeners.add(onPreparedListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnSeekCompleteListener(IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        if (this.mOnSeekCompleteListeners.contains(onSeekCompleteListener)) {
            return;
        }
        this.mOnSeekCompleteListeners.add(onSeekCompleteListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public final void setOnSeiListener(IMediaPlayer.OnSeiListener onSeiListener) {
        if (onSeiListener == null || this.mOnSeiListeners.contains(onSeiListener)) {
            return;
        }
        this.mOnSeiListeners.add(onSeiListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnTimedTextListener(IMediaPlayer.OnTimedTextListener onTimedTextListener) {
        if (onTimedTextListener == null || this.mOnTimedTextListeners.contains(onTimedTextListener)) {
            return;
        }
        this.mOnTimedTextListeners.add(onTimedTextListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setOnVideoSizeChangedListener(IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (this.mOnVideoSizeChangedListeners.contains(onVideoSizeChangedListener)) {
            return;
        }
        this.mOnVideoSizeChangedListeners.add(onVideoSizeChangedListener);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void start() {
        notifyPlayEvent(1);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void stop() {
        notifyPlayEvent(5);
    }
}
