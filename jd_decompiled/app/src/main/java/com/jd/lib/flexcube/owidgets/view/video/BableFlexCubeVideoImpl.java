package com.jd.lib.flexcube.owidgets.view.video;

import android.content.Context;
import android.view.View;
import com.jd.lib.flexcube.iwidget.entity.material.FlexVideoModel;
import com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService;
import com.jd.lib.flexcube.iwidget.ui.video.FlexPlayerStateListener;
import com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2;
import com.jingdong.common.utils.JDSettingUtils;
import com.jingdong.jdsdk.utils.NetUtils;

/* loaded from: classes15.dex */
public class BableFlexCubeVideoImpl implements FlexCubeVideoService {
    private FlexPlayerStateListener mPlayerStateListener;
    private BabelVideoPlayerViewV2 player;

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public int getCurrentPosition() {
        BabelVideoPlayerViewV2 babelVideoPlayerViewV2 = this.player;
        if (babelVideoPlayerViewV2 != null) {
            return babelVideoPlayerViewV2.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public int getTotalDuration() {
        BabelVideoPlayerViewV2 babelVideoPlayerViewV2 = this.player;
        if (babelVideoPlayerViewV2 != null) {
            return babelVideoPlayerViewV2.getDuration();
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public View getVideoView(FlexVideoModel flexVideoModel, FlexPlayerStateListener flexPlayerStateListener, Context context) {
        FlexPlayerStateListener flexPlayerStateListener2 = this.mPlayerStateListener;
        if (flexPlayerStateListener2 != null) {
            flexPlayerStateListener2.release();
            this.player = null;
        }
        this.mPlayerStateListener = flexPlayerStateListener;
        if (flexVideoModel != null) {
            BabelVideoPlayerViewV2 createPlayer = BabelVideoHandlerV2.createPlayer(context, flexVideoModel);
            this.player = createPlayer;
            if (createPlayer != null) {
                createPlayer.setPlayerStateListener(new BabelVideoPlayerViewV2.PlayerStateListenerV2() { // from class: com.jd.lib.flexcube.owidgets.view.video.BableFlexCubeVideoImpl.1
                    @Override // com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.PlayerStateListenerV2
                    public void onCompletion() {
                        if (BableFlexCubeVideoImpl.this.mPlayerStateListener != null) {
                            BableFlexCubeVideoImpl.this.mPlayerStateListener.onCompletion();
                        }
                    }

                    @Override // com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.PlayerStateListenerV2
                    public boolean onError(int i2, int i3) {
                        if (BableFlexCubeVideoImpl.this.mPlayerStateListener != null) {
                            BableFlexCubeVideoImpl.this.mPlayerStateListener.onError(i2, i3);
                            return false;
                        }
                        return false;
                    }

                    @Override // com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.PlayerStateListenerV2
                    public void onPlayState(int i2, int i3) {
                        if (BableFlexCubeVideoImpl.this.mPlayerStateListener != null) {
                            BableFlexCubeVideoImpl.this.mPlayerStateListener.onPlayState(i2, i3);
                        }
                    }

                    @Override // com.jd.lib.flexcube.owidgets.view.video.BabelVideoPlayerViewV2.PlayerStateListenerV2
                    public void release() {
                        if (BableFlexCubeVideoImpl.this.mPlayerStateListener != null) {
                            BableFlexCubeVideoImpl.this.mPlayerStateListener.release();
                        }
                    }
                });
            }
            return this.player;
        }
        return null;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public float getVolume(View view) {
        if (view instanceof BabelVideoPlayerViewV2) {
            return ((BabelVideoPlayerViewV2) view).getVolume();
        }
        return -1.0f;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public boolean isSupportAutoPlay() {
        return JDSettingUtils.isWifiVideoAutoPlay();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public boolean isWifi() {
        return NetUtils.isWifi();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public void jumpToVideoActivity(FlexVideoModel flexVideoModel, Context context) {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public void releasePlayer(View view) {
        if (view instanceof BabelVideoPlayerViewV2) {
            ((BabelVideoPlayerViewV2) view).releasePlayer();
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService
    public void setVolume(View view, float f2) {
        if (view instanceof BabelVideoPlayerViewV2) {
            ((BabelVideoPlayerViewV2) view).setVolume(f2);
        }
    }
}
