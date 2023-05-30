package tv.danmaku.ijk.media.alpha.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.alpha.IAlphaVideoView;
import tv.danmaku.ijk.media.alpha.IFetchResource;
import tv.danmaku.ijk.media.alpha.OnResourceClickListener;
import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;

/* loaded from: classes11.dex */
public class AlphaVideoDialog extends Dialog {
    private static final String TAG = AlphaVideoDialog.class.getSimpleName();
    private IAlphaListener alphaListener;
    private IAlphaVideoView.AlphaOptions alphaOptions;
    private String dir;
    private IFetchResource fetchResourceListener;
    private final String fileName;
    private int gravity;
    private int height;
    private boolean isFullScreen;
    private IPlayerControl.PlayerOptions playerOptions;
    private RectF position;
    private OnResourceClickListener resourceClickListener;
    private FrameLayout rootView;
    private final Handler uiHandler;
    private AlphaVideoView videoView;
    private int width;

    /* loaded from: classes11.dex */
    public static class Builder {
        private IAlphaListener alphaListener;
        private Context context;
        private IFetchResource fetchResourceListener;
        private int gravity;
        private int height;
        private RectF position;
        private OnResourceClickListener resourceClickListener;
        private int width;
        private IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        private IAlphaVideoView.AlphaOptions alphaOptions = new IAlphaVideoView.AlphaOptions();
        private boolean isFullScreen = true;

        public Builder(Context context) {
            this.context = context;
        }

        public AlphaVideoDialog create() {
            AlphaVideoDialog alphaVideoDialog = new AlphaVideoDialog(this.context);
            alphaVideoDialog.playerOptions = this.playerOptions;
            alphaVideoDialog.alphaOptions = this.alphaOptions;
            alphaVideoDialog.alphaListener = this.alphaListener;
            alphaVideoDialog.fetchResourceListener = this.fetchResourceListener;
            alphaVideoDialog.resourceClickListener = this.resourceClickListener;
            alphaVideoDialog.isFullScreen = this.isFullScreen;
            alphaVideoDialog.position = this.position;
            alphaVideoDialog.width = this.width;
            alphaVideoDialog.height = this.height;
            alphaVideoDialog.gravity = this.gravity;
            return alphaVideoDialog;
        }

        public Builder setAlphaListener(IAlphaListener iAlphaListener) {
            this.alphaListener = iAlphaListener;
            return this;
        }

        public Builder setAlphaOptions(IAlphaVideoView.AlphaOptions alphaOptions) {
            this.alphaOptions = alphaOptions;
            return this;
        }

        public Builder setFetchResourceListener(IFetchResource iFetchResource) {
            this.fetchResourceListener = iFetchResource;
            return this;
        }

        public Builder setFullScreen(boolean z) {
            this.isFullScreen = z;
            return this;
        }

        public Builder setGravity(int i2) {
            this.gravity = i2;
            return this;
        }

        public Builder setHeight(int i2) {
            this.height = i2;
            return this;
        }

        public Builder setPlayerOptions(IPlayerControl.PlayerOptions playerOptions) {
            this.playerOptions = playerOptions;
            return this;
        }

        public Builder setPosition(RectF rectF) {
            this.position = rectF;
            return this;
        }

        public Builder setResourceClickListener(OnResourceClickListener onResourceClickListener) {
            this.resourceClickListener = onResourceClickListener;
            return this;
        }

        public Builder setWidth(int i2) {
            this.width = i2;
            return this;
        }
    }

    public AlphaVideoDialog(@NonNull Context context) {
        this(context, R.style.ijkandvrplayerTransparentDialog);
    }

    private void initVideoView() {
        int i2;
        AlphaVideoView alphaVideoView = this.videoView;
        if (alphaVideoView != null) {
            if (!this.isFullScreen) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) alphaVideoView.getLayoutParams();
                int i3 = this.width;
                if (i3 > 0 && (i2 = this.height) > 0) {
                    layoutParams.width = i3;
                    layoutParams.height = i2;
                    layoutParams.gravity = this.gravity;
                } else {
                    RectF rectF = this.position;
                    if (rectF != null && !rectF.isEmpty()) {
                        layoutParams.width = (int) this.position.width();
                        layoutParams.height = (int) this.position.height();
                        RectF rectF2 = this.position;
                        layoutParams.topMargin = (int) rectF2.top;
                        layoutParams.leftMargin = (int) rectF2.left;
                    }
                }
                this.videoView.setLayoutParams(layoutParams);
            }
            IPlayerControl.PlayerOptions playerOptions = this.playerOptions;
            if (playerOptions != null) {
                this.videoView.setPlayerOptions(playerOptions);
            } else {
                this.videoView.setAlphaOption(this.alphaOptions);
            }
            this.videoView.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateExtListener() { // from class: tv.danmaku.ijk.media.alpha.widget.AlphaVideoDialog.1
                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onCompletion() {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onCreatePlayer() {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public boolean onError(int i4, int i5) {
                    return false;
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public boolean onInfo(int i4, int i5) {
                    return false;
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateExtListener
                public void onPreDrawReusePlayer() {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateExtListener
                public void onPreDrawStart(long j2) {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onPrepared(long j2) {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onSeekComplete() {
                }
            });
            this.videoView.setAlphaListener(this.alphaListener);
            this.videoView.setFetchResources(this.fetchResourceListener);
            this.videoView.setOnResourceClickListener(this.resourceClickListener);
        }
    }

    private void initView() {
        this.rootView = (FrameLayout) findViewById(R.id.rlContainer);
        this.videoView = (AlphaVideoView) findViewById(R.id.alpha_videoview);
        initVideoView();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        AlphaVideoView alphaVideoView = this.videoView;
        if (alphaVideoView != null) {
            alphaVideoView.suspend();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.requestFeature(1);
            setContentView(R.layout.ijkandvrplayer_dialog_alphaplayer);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setLayout(-1, -1);
            window.setDimAmount(0.0f);
            initView();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }

    public void startPlay(String str) {
        show();
        if (this.videoView == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.videoView.setVideoPath(str);
    }

    public void stopPlay() {
        AlphaVideoView alphaVideoView = this.videoView;
        if (alphaVideoView != null) {
            alphaVideoView.suspend();
        }
        dismiss();
    }

    public AlphaVideoDialog(@NonNull Context context, int i2) {
        super(context, i2);
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.fileName = "vapx.mp4";
    }
}
