package tv.danmaku.ijk.media.widget.window;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public interface WindowPlayerControl {
    void close();

    void hide();

    boolean isMute();

    boolean isPlaying();

    boolean mute();

    void pause();

    void reload();

    void resume();

    void setOnPlayerEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener);

    void show();
}
