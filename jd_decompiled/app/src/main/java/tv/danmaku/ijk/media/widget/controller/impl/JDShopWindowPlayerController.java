package tv.danmaku.ijk.media.widget.controller.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.controller.JDWindowPlayerController;
import tv.danmaku.ijk.media.widget.window.WindowPlayerControl;

/* loaded from: classes11.dex */
public class JDShopWindowPlayerController extends FrameLayout implements JDWindowPlayerController {
    private FrameLayout flFailed;
    private ImageView ivMute;
    private AnimationDrawable livingAnim;
    private final IMediaPlayer.OnPlayerEventListener mOnPlayerEventListener;
    private WindowPlayerControl playerControl;
    private RelativeLayout rlLoading;

    public JDShopWindowPlayerController(@NonNull Context context) {
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
        LayoutInflater.from(context).inflate(R.layout.ijkandvrplayer_view_player_window_controller_shop, this);
        findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDShopWindowPlayerController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDShopWindowPlayerController.this.playerControl == null) {
                    return;
                }
                JDShopWindowPlayerController.this.playerControl.close();
            }
        });
        this.rlLoading = (RelativeLayout) findViewById(R.id.rlLoading);
        this.flFailed = (FrameLayout) findViewById(R.id.flFailed);
        findViewById(R.id.tvRefresh).setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDShopWindowPlayerController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.ivMute);
        this.ivMute = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDShopWindowPlayerController.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDShopWindowPlayerController.this.playerControl == null) {
                    return;
                }
                JDShopWindowPlayerController.this.updateMuteIcon(JDShopWindowPlayerController.this.playerControl.mute());
            }
        });
        AnimationDrawable animationDrawable = (AnimationDrawable) ((ImageView) findViewById(R.id.ivLiving)).getBackground();
        this.livingAnim = animationDrawable;
        animationDrawable.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMuteIcon(boolean z) {
        this.ivMute.setImageResource(z ? R.drawable.ijkandvrplayer_player_voice_off : R.drawable.ijkandvrplayer_player_voice_on);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimationDrawable animationDrawable = this.livingAnim;
        if (animationDrawable != null) {
            animationDrawable.stop();
            this.livingAnim.setCallback(null);
            this.livingAnim = null;
        }
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
        updateMuteIcon(this.playerControl.isMute());
        if (this.playerControl.isPlaying()) {
            return;
        }
        changeLoadingState(true);
    }

    public JDShopWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDShopWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDShopWindowPlayerController.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i3) {
                if (i3 == 4) {
                    JDShopWindowPlayerController.this.flFailed.setVisibility(0);
                } else if (i3 == 6) {
                    if (JDShopWindowPlayerController.this.playerControl != null) {
                        JDShopWindowPlayerController jDShopWindowPlayerController = JDShopWindowPlayerController.this;
                        jDShopWindowPlayerController.updateMuteIcon(jDShopWindowPlayerController.playerControl.isMute());
                    }
                    JDShopWindowPlayerController.this.hiddenStateView();
                } else if (i3 == 7) {
                    JDShopWindowPlayerController.this.changeLoadingState(true);
                } else if (i3 != 8) {
                } else {
                    JDShopWindowPlayerController.this.changeLoadingState(false);
                }
            }
        };
        init(context);
    }

    @TargetApi(21)
    public JDShopWindowPlayerController(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mOnPlayerEventListener = new IMediaPlayer.OnPlayerEventListener() { // from class: tv.danmaku.ijk.media.widget.controller.impl.JDShopWindowPlayerController.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
            public void onPlayEvent(int i32) {
                if (i32 == 4) {
                    JDShopWindowPlayerController.this.flFailed.setVisibility(0);
                } else if (i32 == 6) {
                    if (JDShopWindowPlayerController.this.playerControl != null) {
                        JDShopWindowPlayerController jDShopWindowPlayerController = JDShopWindowPlayerController.this;
                        jDShopWindowPlayerController.updateMuteIcon(jDShopWindowPlayerController.playerControl.isMute());
                    }
                    JDShopWindowPlayerController.this.hiddenStateView();
                } else if (i32 == 7) {
                    JDShopWindowPlayerController.this.changeLoadingState(true);
                } else if (i32 != 8) {
                } else {
                    JDShopWindowPlayerController.this.changeLoadingState(false);
                }
            }
        };
        init(context);
    }
}
