package com.jingdong.common.jdreactFramework.utils.video;

import android.content.Context;
import android.view.View;
import com.jingdong.sdk.oklog.OKLog;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;

/* loaded from: classes5.dex */
public class LoopVideoViewHolder {
    private static final String TAG = VideoRecordActivity.TAG;
    private IjkVideoView mIjkVideoView;
    private VideoDisplayListener mVideoDisplayListener;

    /* loaded from: classes5.dex */
    public interface VideoDisplayListener {
        void onDisplay();
    }

    public LoopVideoViewHolder(Context context) {
        initView(context);
    }

    private void initView(Context context) {
        IjkVideoView ijkVideoView = new IjkVideoView(context);
        this.mIjkVideoView = ijkVideoView;
        ijkVideoView.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.jdreactFramework.utils.video.LoopVideoViewHolder.1
            {
                LoopVideoViewHolder.this = this;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                LoopVideoViewHolder.this.mIjkVideoView.seekTo(0);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (OKLog.D) {
                    OKLog.d(LoopVideoViewHolder.TAG, "onInfo mediaInfo:" + i2 + " degree:" + i3);
                }
                if (i2 != 3 || LoopVideoViewHolder.this.mVideoDisplayListener == null) {
                    return true;
                }
                LoopVideoViewHolder.this.mVideoDisplayListener.onDisplay();
                LoopVideoViewHolder.this.mVideoDisplayListener = null;
                return true;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                if (OKLog.D) {
                    OKLog.d(LoopVideoViewHolder.TAG, "real duration " + LoopVideoViewHolder.this.mIjkVideoView.getDuration());
                }
                LoopVideoViewHolder.this.mIjkVideoView.postDelayed(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.video.LoopVideoViewHolder.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (LoopVideoViewHolder.this.mVideoDisplayListener != null) {
                            LoopVideoViewHolder.this.mVideoDisplayListener.onDisplay();
                            LoopVideoViewHolder.this.mVideoDisplayListener = null;
                        }
                    }
                }, 200L);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
                LoopVideoViewHolder.this.mIjkVideoView.start();
            }
        });
    }

    public View getView() {
        return this.mIjkVideoView;
    }

    public void pause() {
        this.mIjkVideoView.suspend();
    }

    public void release() {
        this.mIjkVideoView.release();
    }

    public void resume() {
        this.mIjkVideoView.resume();
    }

    public void setVideoDisplayListener(VideoDisplayListener videoDisplayListener) {
        this.mVideoDisplayListener = videoDisplayListener;
    }

    public void setVideoPath(String str) {
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        if (OKLog.D) {
            playerOptions.setDebugLog(true);
        }
        this.mIjkVideoView.setPlayerOptions(playerOptions);
        this.mIjkVideoView.setVideoPath(str);
    }
}
