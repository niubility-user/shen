package com.jingdong.common.utils;

import android.content.Context;
import androidx.annotation.NonNull;

/* loaded from: classes6.dex */
public class UnRefreshSoundPlay {
    private static UnRefreshSoundPlay play;
    private UnSoundPlayer soundPlayer;

    private UnRefreshSoundPlay(@NonNull Context context) {
    }

    public static UnRefreshSoundPlay getInstance(Context context) {
        UnRefreshSoundPlay unRefreshSoundPlay;
        UnRefreshSoundPlay unRefreshSoundPlay2 = play;
        if (unRefreshSoundPlay2 != null) {
            return unRefreshSoundPlay2;
        }
        synchronized (UnRefreshSoundPlay.class) {
            if (play == null) {
                play = new UnRefreshSoundPlay(context);
            }
            unRefreshSoundPlay = play;
        }
        return unRefreshSoundPlay;
    }

    public void playRefreshEnd() {
        UnSoundPlayer unSoundPlayer = this.soundPlayer;
        if (unSoundPlayer != null) {
            unSoundPlayer.play(2);
        }
    }

    public void playRefreshStart() {
        UnSoundPlayer unSoundPlayer = this.soundPlayer;
        if (unSoundPlayer != null) {
            unSoundPlayer.play(1);
        }
    }
}
