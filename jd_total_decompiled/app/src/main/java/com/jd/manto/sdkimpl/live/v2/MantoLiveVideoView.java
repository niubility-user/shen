package com.jd.manto.sdkimpl.live.v2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.manto.R;
import com.jingdong.manto.utils.MantoLog;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;
import tv.danmaku.ijk.media.widget.custom.TipsView;

/* loaded from: classes17.dex */
public class MantoLiveVideoView extends FrameLayout implements MediaController.MediaPlayerControl {

    /* renamed from: g */
    private String f6797g;

    /* renamed from: h */
    private Context f6798h;

    /* renamed from: i */
    private JDVideoView f6799i;

    /* renamed from: j */
    private ImageView f6800j;

    /* renamed from: k */
    private ProgressBar f6801k;

    /* renamed from: l */
    private String f6802l;

    /* renamed from: m */
    private TipsView.OnTipsInvoke f6803m;

    /* renamed from: n */
    private IPlayerControl.PlayerOptions f6804n;
    boolean o;
    private e p;
    private WeakReference<Dialog> q;

    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {
        a() {
            MantoLiveVideoView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TextUtils.isEmpty(MantoLiveVideoView.this.f6802l)) {
                return;
            }
            MantoLiveVideoView mantoLiveVideoView = MantoLiveVideoView.this;
            mantoLiveVideoView.H(mantoLiveVideoView.f6802l);
        }
    }

    /* loaded from: classes17.dex */
    public class b implements TipsView.OnTipsInvoke {
        b() {
            MantoLiveVideoView.this = r1;
        }

        @Override // tv.danmaku.ijk.media.widget.custom.TipsView.OnTipsInvoke
        public void doRetry() {
            if (MantoLiveVideoView.this.f6799i != null) {
                MantoLiveVideoView.this.f6799i.resume();
            }
        }
    }

    /* loaded from: classes17.dex */
    public class c implements IPlayerControl.OnPlayerStateListener {

        /* renamed from: g */
        final /* synthetic */ IPlayerControl.OnPlayerStateListener f6806g;

        c(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
            MantoLiveVideoView.this = r1;
            this.f6806g = onPlayerStateListener;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            MantoLiveVideoView.this.L();
            MantoLiveVideoView.this.f6801k.setVisibility(8);
            this.f6806g.onCompletion();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            MantoLiveVideoView.this.L();
            MantoLiveVideoView.this.f6801k.setVisibility(0);
            this.f6806g.onCreatePlayer();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            MantoLiveVideoView.this.L();
            MantoLiveVideoView.this.f6801k.setVisibility(8);
            if (MantoLiveVideoView.this.getContext() != null) {
                if (!PlayerNetworkUtil.isNetworkAvailable(MantoLiveVideoView.this.getContext())) {
                    TipsView tipsView = new TipsView(MantoLiveVideoView.this.getContext());
                    TipsView.TipType tipType = TipsView.TipType.NET_ERROR;
                    MantoLiveVideoView mantoLiveVideoView = MantoLiveVideoView.this;
                    tipsView.showTip(tipType, mantoLiveVideoView, mantoLiveVideoView.f6803m);
                } else {
                    TipsView tipsView2 = new TipsView(MantoLiveVideoView.this.getContext());
                    TipsView.TipType tipType2 = TipsView.TipType.PLAY_ERROR;
                    MantoLiveVideoView mantoLiveVideoView2 = MantoLiveVideoView.this;
                    tipsView2.showTip(tipType2, mantoLiveVideoView2, mantoLiveVideoView2.f6803m);
                }
            }
            return this.f6806g.onError(i2, i3);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0009, code lost:
            if (r3 != 702) goto L37;
         */
        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean onInfo(int i2, int i3) {
            if (i2 != 3) {
                if (i2 == 701) {
                    MantoLiveVideoView.this.f6801k.setVisibility(0);
                }
                return this.f6806g.onInfo(i2, i3);
            }
            MantoLiveVideoView.this.K();
            MantoLiveVideoView.this.f6801k.setVisibility(8);
            return this.f6806g.onInfo(i2, i3);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            MantoLiveVideoView.this.f6801k.setVisibility(8);
            this.f6806g.onPrepared(j2);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
            this.f6806g.onSeekComplete();
        }
    }

    /* loaded from: classes17.dex */
    public class d implements IPlayerControl.OnStatisticsStateListener {
        final /* synthetic */ IPlayerControl.OnStatisticsStateListener a;

        d(MantoLiveVideoView mantoLiveVideoView, IPlayerControl.OnStatisticsStateListener onStatisticsStateListener) {
            this.a = onStatisticsStateListener;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
        public void pause() {
            this.a.pause();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
        public void start() {
            this.a.start();
        }
    }

    /* loaded from: classes17.dex */
    public static class e extends Handler {
        private WeakReference<MantoLiveVideoView> a;

        public e(MantoLiveVideoView mantoLiveVideoView) {
            this.a = new WeakReference<>(mantoLiveVideoView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MantoLiveVideoView mantoLiveVideoView;
            if (message.what == 1 && (mantoLiveVideoView = this.a.get()) != null) {
                mantoLiveVideoView.N();
                mantoLiveVideoView.p.sendEmptyMessageDelayed(1, 1000L);
            }
        }
    }

    public MantoLiveVideoView(@NonNull Context context) {
        this(context, null);
    }

    private void A(Context context) {
        this.f6798h = context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.manto_live_player_view, this);
        this.f6799i = (JDVideoView) inflate.findViewById(R.id.videoView);
        this.f6800j = (ImageView) inflate.findViewById(R.id.ivPlay);
        this.f6801k = (ProgressBar) findViewById(R.id.progressBar);
        setClickable(true);
        this.f6800j.setOnClickListener(new a());
        this.f6803m = new b();
    }

    private void F() {
        if (this.o) {
            Dialog y = y();
            Activity x = x();
            if (y == null) {
                if (x != null) {
                    MantoLog.d(this.f6797g, "setScreenOffActivity");
                    this.o = false;
                    x.getWindow().clearFlags(128);
                    return;
                }
                return;
            }
            MantoLog.d(this.f6797g, "setScreenOffDialog");
            this.o = false;
            if (x == null || x.isFinishing()) {
                return;
            }
            y.getWindow().clearFlags(128);
        }
    }

    private void G() {
        if (this.o) {
            return;
        }
        Dialog y = y();
        Activity x = x();
        if (y == null) {
            if (x != null) {
                MantoLog.d(this.f6797g, "setScreenOnActivity");
                this.o = true;
                x.getWindow().addFlags(128);
                return;
            }
            return;
        }
        MantoLog.d(this.f6797g, "setScreenOnDialog");
        this.o = true;
        if (x == null || x.isFinishing()) {
            return;
        }
        y.getWindow().addFlags(128);
    }

    public void K() {
        if (this.p == null) {
            this.p = new e(this);
        }
        this.p.removeMessages(1);
        this.p.sendEmptyMessageDelayed(1, 1000L);
    }

    public void L() {
        e eVar = this.p;
        if (eVar != null) {
            eVar.removeMessages(1);
        }
        F();
    }

    private Activity x() {
        Context context = getContext();
        if (context == null || !(context instanceof Activity)) {
            return null;
        }
        return (Activity) context;
    }

    private Dialog y() {
        WeakReference<Dialog> weakReference = this.q;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public void B() {
        L();
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            jDVideoView.release();
        }
    }

    public void C(Dialog dialog) {
        this.o = false;
        if (dialog == null) {
            this.q = null;
        } else {
            this.q = new WeakReference<>(dialog);
        }
    }

    public void D(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView == null) {
            return;
        }
        jDVideoView.setOnPlayerStateListener(new c(onPlayerStateListener));
    }

    public void E(IPlayerControl.PlayerOptions playerOptions) {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            this.f6804n = playerOptions;
            jDVideoView.setPlayerOptions(playerOptions);
        }
    }

    public void H(String str) {
        ImageView imageView = this.f6800j;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        L();
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            jDVideoView.suspend();
            this.f6799i.setVideoPathWithoutOpen(str);
            this.f6799i.initRenders();
        }
    }

    public void I(String str) {
        ImageView imageView = this.f6800j;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        L();
        this.f6802l = str;
    }

    public void J(float f2) {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView == null || this.f6804n == null) {
            return;
        }
        jDVideoView.setVolume(f2);
    }

    public void M() {
        L();
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            jDVideoView.suspend();
        }
    }

    public void N() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView == null || !jDVideoView.isPlaying()) {
            return;
        }
        G();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.canPause();
        }
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.canSeekBackward();
        }
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.canSeekForward();
        }
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.getAudioSessionId();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.getBufferPercentage();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.getDuration();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            return jDVideoView.isPlaying();
        }
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            jDVideoView.pause();
        }
    }

    public void resume() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            jDVideoView.retry(false);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i2) {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            jDVideoView.seekTo(i2);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView != null) {
            jDVideoView.start();
        }
    }

    public void v(IPlayerControl.OnStatisticsStateListener onStatisticsStateListener) {
        JDVideoView jDVideoView = this.f6799i;
        if (jDVideoView == null) {
            return;
        }
        jDVideoView.addOnStatisticsStateListener(new d(this, onStatisticsStateListener));
    }

    public void w(View view) {
        if (view instanceof JDVideoView) {
            this.f6799i = (JDVideoView) view;
            addView(view, 1, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public JDVideoView z(boolean z) {
        JDVideoView jDVideoView;
        if (z && (jDVideoView = this.f6799i) != null && jDVideoView.getParent() != null) {
            ((ViewGroup) this.f6799i.getParent()).removeView(this.f6799i);
        }
        return this.f6799i;
    }

    public MantoLiveVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MantoLiveVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f6797g = MantoLiveVideoView.class.getName();
        this.q = null;
        A(context);
    }
}
