package tv.danmaku.ijk.media.widget.controller.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController;
import tv.danmaku.ijk.media.widget.window.WindowPlayerControl;

/* loaded from: classes11.dex */
public class DefaultWindowPlayerController extends FrameLayout implements JDWindowPlayerController {
    private FrameLayout flFailed;
    private final IMediaPlayer.OnPlayerEventListener mOnPlayerEventListener;
    private WindowPlayerControl playerControl;
    private RelativeLayout rlLoading;

    public DefaultWindowPlayerController(@NonNull Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeLoadingState(boolean z) {
        this.rlLoading.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hiddenStateView() {
        this.rlLoading.setVisibility(8);
        this.flFailed.setVisibility(8);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ijkandvrplayer_view_player_window_controller_default, this);
        findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.DefaultWindowPlayerController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DefaultWindowPlayerController.this.playerControl == null) {
                    return;
                }
                DefaultWindowPlayerController.this.playerControl.close();
            }
        });
        this.rlLoading = (RelativeLayout) findViewById(R.id.rlLoading);
        this.flFailed = (FrameLayout) findViewById(R.id.flFailed);
        findViewById(R.id.tvRefresh).setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.DefaultWindowPlayerController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    @Override // tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController
    public void setAnchorView(ViewGroup viewGroup) {
        if (getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        viewGroup.addView(this, new FrameLayout.LayoutParams(-1, -1));
        hiddenStateView();
    }

    @Override // tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController
    public void setWindowPlayer(WindowPlayerControl windowPlayerControl) {
        if (windowPlayerControl == null) {
            return;
        }
        this.playerControl = windowPlayerControl;
        windowPlayerControl.setOnPlayerEventListener(this.mOnPlayerEventListener);
    }

    public DefaultWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DefaultWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.DefaultWindowPlayerController.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i3) {
                if (i3 == 4) {
                    DefaultWindowPlayerController.this.flFailed.setVisibility(0);
                } else if (i3 == 6) {
                    DefaultWindowPlayerController.this.hiddenStateView();
                } else if (i3 == 7) {
                    DefaultWindowPlayerController.this.changeLoadingState(true);
                } else if (i3 != 8) {
                } else {
                    DefaultWindowPlayerController.this.changeLoadingState(false);
                }
            }
        };
        init(context);
    }

    @TargetApi(21)
    public DefaultWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.DefaultWindowPlayerController.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i32) {
                if (i32 == 4) {
                    DefaultWindowPlayerController.this.flFailed.setVisibility(0);
                } else if (i32 == 6) {
                    DefaultWindowPlayerController.this.hiddenStateView();
                } else if (i32 == 7) {
                    DefaultWindowPlayerController.this.changeLoadingState(true);
                } else if (i32 != 8) {
                } else {
                    DefaultWindowPlayerController.this.changeLoadingState(false);
                }
            }
        };
        init(context);
    }
}
