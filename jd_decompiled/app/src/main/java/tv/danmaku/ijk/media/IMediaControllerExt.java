package tv.danmaku.ijk.media;

import android.widget.ImageView;
import android.widget.ProgressBar;
import tv.danmaku.ijk.media.example.widget.media.IMediaController;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;
import tv.danmaku.ijk.media.widget.controller.OnControllerListener;

/* loaded from: classes11.dex */
public interface IMediaControllerExt extends IMediaController {
    void changeOrientation(boolean z);

    void forceHide();

    void forceShow();

    void release();

    void setControllerOptions(JDPlayerView jDPlayerView, JDControllerOptions jDControllerOptions);

    void setOnControllerListener(OnControllerListener onControllerListener);

    void showNonWifiTip();

    boolean toggleMute(boolean z);

    void togglePanelVisible(boolean z);

    void updateCommonUI(ImageView imageView, ProgressBar progressBar);

    void updateFullBtn(boolean z);

    void updateFullMode(boolean z);

    void updateOrientationSensor(boolean z);
}
