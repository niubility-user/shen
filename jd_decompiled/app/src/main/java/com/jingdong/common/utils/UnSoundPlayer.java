package com.jingdong.common.utils;

import android.content.Context;
import android.media.SoundPool;
import com.jd.lib.un.global.theme.UnWidgetThemeController;

/* loaded from: classes6.dex */
public class UnSoundPlayer {
    private SoundPool soundPool;

    public UnSoundPlayer(Context context, Integer... numArr) {
        if (context == null || numArr == null) {
            return;
        }
        this.soundPool = new SoundPool(numArr.length, 1, 5);
        int i2 = 0;
        while (i2 < numArr.length) {
            i2++;
            this.soundPool.load(context, numArr[i2].intValue(), i2);
        }
    }

    public UnSoundPlayer load(Context context, int i2, int i3) {
        SoundPool soundPool = this.soundPool;
        if (soundPool == null) {
            return this;
        }
        soundPool.load(context, i2, i3);
        return this;
    }

    public void pause(int i2) {
        SoundPool soundPool = this.soundPool;
        if (soundPool == null) {
            return;
        }
        soundPool.pause(i2);
    }

    public int play(int i2) {
        return play(i2, 0.5f, false);
    }

    public void stop(int i2) {
        SoundPool soundPool = this.soundPool;
        if (soundPool == null) {
            return;
        }
        soundPool.stop(i2);
    }

    public int play(int i2, boolean z) {
        return play(i2, 0.5f, z);
    }

    public UnSoundPlayer load(String str, int i2) {
        SoundPool soundPool = this.soundPool;
        if (soundPool == null) {
            return this;
        }
        soundPool.load(str, i2);
        return this;
    }

    public int play(int i2, float f2, boolean z) {
        if (this.soundPool != null && UnWidgetThemeController.getInstance().isVoiceEnable()) {
            float f3 = f2 > 1.0f ? 1.0f : f2;
            try {
                return this.soundPool.play(i2, f3, f3, 1, z ? 1 : 0, 1.0f);
            } catch (Exception unused) {
            }
        }
        return -1;
    }

    public UnSoundPlayer(String... strArr) {
        if (strArr == null) {
            return;
        }
        this.soundPool = new SoundPool(strArr.length, 1, 5);
        int i2 = 0;
        while (i2 < strArr.length) {
            SoundPool soundPool = this.soundPool;
            String str = strArr[i2];
            i2++;
            soundPool.load(str, i2);
        }
    }

    public UnSoundPlayer(int i2) {
        this.soundPool = new SoundPool(i2, 1, 5);
    }
}
