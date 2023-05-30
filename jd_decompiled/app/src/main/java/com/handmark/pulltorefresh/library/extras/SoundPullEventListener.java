package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class SoundPullEventListener<V extends View> implements PullToRefreshBase.OnPullEventListener<V> {
    private final Context mContext;
    private MediaPlayer mCurrentMediaPlayer;
    private final HashMap<PullToRefreshBase.State, Integer> mSoundMap = new HashMap<>();

    public SoundPullEventListener(Context context) {
        this.mContext = context;
    }

    private void playSound(int i2) {
        MediaPlayer mediaPlayer = this.mCurrentMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mCurrentMediaPlayer.release();
        }
        MediaPlayer create = MediaPlayer.create(this.mContext, i2);
        this.mCurrentMediaPlayer = create;
        if (create != null) {
            create.start();
        }
    }

    public void addSoundEvent(PullToRefreshBase.State state, int i2) {
        this.mSoundMap.put(state, Integer.valueOf(i2));
    }

    public void clearSounds() {
        this.mSoundMap.clear();
    }

    public MediaPlayer getCurrentMediaPlayer() {
        return this.mCurrentMediaPlayer;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener
    public final void onPullEvent(PullToRefreshBase<V> pullToRefreshBase, PullToRefreshBase.State state, PullToRefreshBase.Mode mode) {
        Integer num = this.mSoundMap.get(state);
        if (num != null) {
            playSound(num.intValue());
        }
    }
}
