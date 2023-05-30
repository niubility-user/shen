package tv.danmaku.ijk.media.widget.controller.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController;
import tv.danmaku.ijk.media.widget.window.WindowPlayerControl;

/* loaded from: classes11.dex */
public class JDLiveWindowPlayerController extends FrameLayout implements JDWindowPlayerController {
    private final IMediaPlayer.OnPlayerEventListener mOnPlayerEventListener;
    private WindowPlayerControl playerControl;

    public JDLiveWindowPlayerController(@NonNull Context context) {
        this(context, null);
    }

    private void changeLoadingState(boolean z) {
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ijkandvrplayer_view_player_window_controller_live, this);
        findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDLiveWindowPlayerController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDLiveWindowPlayerController.this.playerControl == null) {
                    return;
                }
                JDLiveWindowPlayerController.this.playerControl.close();
            }
        });
    }

    @Override // tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController
    public void setAnchorView(ViewGroup viewGroup) {
        if (getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        viewGroup.addView(this, new FrameLayout.LayoutParams(-1, -1));
    }

    @Override // tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController
    public void setWindowPlayer(WindowPlayerControl windowPlayerControl) {
        this.playerControl = windowPlayerControl;
        windowPlayerControl.setOnPlayerEventListener(this.mOnPlayerEventListener);
        changeLoadingState(true);
    }

    public JDLiveWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDLiveWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDLiveWindowPlayerController.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i3) {
            }
        };
        init(context);
    }

    @TargetApi(21)
    public JDLiveWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDLiveWindowPlayerController.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i32) {
            }
        };
        init(context);
    }
}
