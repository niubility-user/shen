package tv.danmaku.ijk.media.widget.controller;

/* loaded from: classes11.dex */
public interface OnControllerListener {
    void onOrientationChanged(boolean z, int i2);

    void onPlayBtnClick(boolean z);

    void onProgressUpdate(boolean z, int i2, long j2, boolean z2);

    void onVoiceBtnClick(boolean z);

    void onVoiceStateChange(boolean z);

    void seekBarOnSeek(int i2);
}
