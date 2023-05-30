package com.jingdong.common.unification.filter.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import com.jingdong.common.unification.filter.filter.CommonFilter;
import com.jingdong.common.unification.filter.filter.CommonFilterGroup;
import com.jingdong.common.unification.filter.filter.CommonFilterRenderer;

/* loaded from: classes6.dex */
public class VideoSurfaceView extends GLSurfaceView {
    private MediaPlayer mMediaPlayer;
    CommonFilterRenderer mRenderer;

    public VideoSurfaceView(Context context, MediaPlayer mediaPlayer, int i2) {
        super(context);
        this.mMediaPlayer = null;
        setEGLContextClientVersion(2);
        this.mMediaPlayer = mediaPlayer;
        CommonFilterGroup commonFilterGroup = new CommonFilterGroup();
        commonFilterGroup.addFilter(new CommonExtTexFilter());
        commonFilterGroup.addFilter(new CommonFilter());
        CommonFilterRenderer commonFilterRenderer = new CommonFilterRenderer(commonFilterGroup, i2);
        this.mRenderer = commonFilterRenderer;
        commonFilterRenderer.setUpSurfaceTexture(this.mMediaPlayer);
        setRenderer(this.mRenderer);
    }

    public void setFilter(CommonFilter commonFilter) {
        this.mRenderer.setFilter(commonFilter);
    }

    public void setSourceSize(int i2, int i3) {
        this.mRenderer.setSourceSize(i2, i3);
    }
}
