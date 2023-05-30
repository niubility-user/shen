package tv.danmaku.ijk.media.example.widget.media;

import android.view.View;
import android.widget.MediaController;

/* loaded from: classes11.dex */
public interface IMediaController {
    void hide();

    boolean isShowing();

    void setAnchorView(View view);

    void setEnabled(boolean z);

    void setMediaPlayer(MediaController.MediaPlayerControl mediaPlayerControl);

    void show();

    void show(int i2);

    void showOnce(View view);
}
