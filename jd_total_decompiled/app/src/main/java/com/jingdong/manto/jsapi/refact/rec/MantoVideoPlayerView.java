package com.jingdong.manto.jsapi.refact.rec;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes15.dex */
public class MantoVideoPlayerView extends FrameLayout {
    private int currentPosition;
    private MediaPlayer.OnErrorListener errorListener;
    private boolean isPause;
    private final ImageView mImageView;
    private final VideoView mVideoView;
    private String videoPath;

    public MantoVideoPlayerView(@NonNull Context context) {
        this(context, null);
    }

    public MantoVideoPlayerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MantoVideoPlayerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isPause = false;
        LayoutInflater.from(context).inflate(R.layout.manto_video_play_view, this);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        this.mVideoView = videoView;
        this.mImageView = (ImageView) findViewById(R.id.imgView);
        videoView.setVisibility(8);
    }

    public void pause() {
        MantoLog.e("better", "isPlay: " + this.mVideoView.isPlaying());
        VideoView videoView = this.mVideoView;
        if (videoView == null || !videoView.isPlaying()) {
            return;
        }
        this.currentPosition = this.mVideoView.getCurrentPosition();
        this.isPause = true;
        reset();
    }

    public void play(final String str, MediaPlayer.OnErrorListener onErrorListener) {
        this.videoPath = str;
        this.errorListener = onErrorListener;
        this.isPause = false;
        this.mVideoView.setVideoPath(str);
        this.mVideoView.start();
        if (this.mVideoView.getVisibility() != 0) {
            this.mVideoView.setVisibility(0);
        }
        this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoVideoPlayerView.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                MantoVideoPlayerView.this.mVideoView.setVideoPath(str);
                MantoVideoPlayerView.this.mVideoView.start();
            }
        });
        if (onErrorListener != null) {
            this.mVideoView.setOnErrorListener(onErrorListener);
        }
    }

    public void reset() {
        VideoView videoView = this.mVideoView;
        if (videoView != null) {
            videoView.stopPlayback();
            this.mVideoView.setOnErrorListener(null);
            this.mVideoView.setOnCompletionListener(null);
        }
    }

    public void resume() {
        if (this.mVideoView == null || !this.isPause) {
            return;
        }
        play(this.videoPath, this.errorListener);
        this.mVideoView.seekTo(this.currentPosition);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        this.mVideoView.setVisibility(i2);
        this.mImageView.setVisibility(i2);
    }
}
