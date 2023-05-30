package com.jingdong.common.unification.video.player;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.common.videoplayer.ITrafficDialog;
import com.jingdong.common.videoplayer.VideoPlayerController;
import com.jingdong.jdsdk.utils.NetUtils;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.widget.uniform.inter.IJDOnItemVideoPlayerClick;

/* loaded from: classes6.dex */
public class ItemVideoPlayerController {
    private long duration;
    private boolean enableTimeDisplay;
    private ImageView itemAudioIcon;
    private TextView itemVideoTime;
    private AnimationDrawable mAudioIconAnim;
    private Context mContext;
    private IJDOnItemVideoPlayerClick onItemVideoPlayerClick;
    private ImageView playBtn;
    private View rootView;

    public ItemVideoPlayerController(@NonNull Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.ijkandvrplayer_un_item_video_player_ctrl, (ViewGroup) null);
        this.rootView = inflate;
        this.itemVideoTime = (TextView) inflate.findViewById(R.id.itemVideoTime);
        this.itemAudioIcon = (ImageView) this.rootView.findViewById(R.id.audioIcon);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.mContext.getResources().getDrawable(R.drawable.ijkandvrplayer_living);
        this.mAudioIconAnim = animationDrawable;
        this.itemAudioIcon.setImageDrawable(animationDrawable);
        this.playBtn = (ImageView) this.rootView.findViewById(R.id.playBtn);
        this.rootView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.video.player.ItemVideoPlayerController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ItemVideoPlayerController.this.clickVideo();
            }
        });
    }

    public void clickVideo() {
        if (NetUtils.isNetworkAvailable()) {
            if (VideoPlayerController.canShowDialog(this.mContext)) {
                VideoPlayerController.createTrafficDailog(this.mContext, new ITrafficDialog() { // from class: com.jingdong.common.unification.video.player.ItemVideoPlayerController.2
                    @Override // com.jingdong.common.videoplayer.ITrafficDialog
                    public void cancel() {
                        if (ItemVideoPlayerController.this.onItemVideoPlayerClick != null) {
                            ItemVideoPlayerController.this.onItemVideoPlayerClick.trafficDialogCancel();
                        }
                    }

                    @Override // com.jingdong.common.videoplayer.ITrafficDialog
                    public void confirm() {
                        if (ItemVideoPlayerController.this.onItemVideoPlayerClick != null) {
                            ItemVideoPlayerController.this.onItemVideoPlayerClick.trafficDialogConfirm();
                        }
                    }

                    @Override // com.jingdong.common.videoplayer.ITrafficDialog
                    public void onCheckedChanged() {
                        if (ItemVideoPlayerController.this.onItemVideoPlayerClick != null) {
                            ItemVideoPlayerController.this.onItemVideoPlayerClick.trafficDialogCheckChanged();
                        }
                    }
                }).show();
                return;
            }
            IJDOnItemVideoPlayerClick iJDOnItemVideoPlayerClick = this.onItemVideoPlayerClick;
            if (iJDOnItemVideoPlayerClick != null) {
                iJDOnItemVideoPlayerClick.clickVideo();
            }
        }
    }

    public void completion(long j2) {
        if (j2 <= 0) {
            j2 = this.duration;
        }
        setProgress(j2, 0L);
        this.playBtn.setVisibility(0);
        if (this.enableTimeDisplay) {
            this.mAudioIconAnim.stop();
        }
    }

    public void enableTimeDisplay(boolean z) {
        this.enableTimeDisplay = z;
        this.itemVideoTime.setVisibility(z ? 0 : 8);
        this.itemAudioIcon.setVisibility(z ? 0 : 8);
    }

    public void error() {
        this.playBtn.setVisibility(8);
        if (this.enableTimeDisplay) {
            this.mAudioIconAnim.stop();
        }
    }

    public View getView() {
        return this.rootView;
    }

    public void setDuration(long j2) {
        this.duration = j2;
    }

    public void setOnItemVideoPlayerClick(IJDOnItemVideoPlayerClick iJDOnItemVideoPlayerClick) {
        this.onItemVideoPlayerClick = iJDOnItemVideoPlayerClick;
    }

    public void setProgress(long j2, long j3) {
        if (this.enableTimeDisplay) {
            StringBuilder sb = new StringBuilder();
            if (j2 < j3) {
                sb.append(0);
            } else {
                sb.append((j2 - j3) / 1000);
            }
            sb.append(this.mContext.getResources().getString(R.string.un_second));
            this.itemVideoTime.setText(sb.toString());
        }
    }

    public void start() {
        this.playBtn.setVisibility(8);
        if (this.enableTimeDisplay) {
            this.itemAudioIcon.setVisibility(0);
            this.mAudioIconAnim.start();
        }
    }

    public void stop() {
        this.playBtn.setVisibility(0);
        if (this.enableTimeDisplay) {
            this.mAudioIconAnim.stop();
        }
    }
}
