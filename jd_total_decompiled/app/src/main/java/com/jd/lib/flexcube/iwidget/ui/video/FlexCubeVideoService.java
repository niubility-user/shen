package com.jd.lib.flexcube.iwidget.ui.video;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.entity.material.FlexVideoModel;

/* loaded from: classes14.dex */
public interface FlexCubeVideoService {
    int getCurrentPosition();

    int getTotalDuration();

    View getVideoView(FlexVideoModel flexVideoModel, FlexPlayerStateListener flexPlayerStateListener, Context context);

    float getVolume(View view);

    boolean isSupportAutoPlay();

    boolean isWifi();

    void jumpToVideoActivity(FlexVideoModel flexVideoModel, Context context);

    void releasePlayer(View view);

    void setVolume(View view, float f2);
}
