package com.jingdong.app.mall.home.video;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.widget.video.IjkVideoViewWithReport;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;

/* loaded from: classes4.dex */
public class SimpleVideoView extends RelativeLayout {

    /* renamed from: g */
    private IjkVideoViewWithReport f10993g;

    /* renamed from: h */
    private IPlayerControl.OnPlayerStateListener f10994h;

    /* renamed from: i */
    private ImageView f10995i;

    /* renamed from: j */
    private ImageView f10996j;

    /* renamed from: k */
    private ImageView f10997k;

    /* renamed from: l */
    private ImageView f10998l;

    /* renamed from: m */
    private View f10999m;

    /* renamed from: n */
    private String f11000n;
    private boolean o;
    private boolean p;
    private boolean q;
    private f r;
    private boolean s;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            SimpleVideoView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SimpleVideoView.this.r != null) {
                SimpleVideoView.this.r.onCloseClick();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
            SimpleVideoView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!SimpleVideoView.this.p || TextUtils.isEmpty(SimpleVideoView.this.f11000n)) {
                if (SimpleVideoView.this.f10993g.isPlaying()) {
                    SimpleVideoView.this.u();
                    return;
                } else {
                    SimpleVideoView.this.E();
                    return;
                }
            }
            SimpleVideoView.this.x();
        }
    }

    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
            SimpleVideoView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SimpleVideoView.this.o(!r2.o);
            if (SimpleVideoView.this.r != null) {
                SimpleVideoView.this.r.a(SimpleVideoView.this.o);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {
        d() {
            SimpleVideoView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TextUtils.isEmpty(SimpleVideoView.this.f11000n)) {
                return;
            }
            SimpleVideoView.this.x();
        }
    }

    /* loaded from: classes4.dex */
    public class e implements IPlayerControl.OnPlayerStateListener {
        e() {
            SimpleVideoView.this = r1;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            if (SimpleVideoView.this.f10994h != null) {
                SimpleVideoView.this.f10994h.onCompletion();
            }
            if (SimpleVideoView.this.s) {
                SimpleVideoView.this.f10998l.setVisibility(0);
            }
            SimpleVideoView.this.f10996j.setImageResource(R.drawable.video_activity_simple_play_btn);
            SimpleVideoView.this.p = true;
            SimpleVideoView.this.a();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            SimpleVideoView.this.f10999m.setVisibility(0);
            SimpleVideoView.this.f10998l.setVisibility(8);
            if (SimpleVideoView.this.f10994h != null) {
                SimpleVideoView.this.f10994h.onCreatePlayer();
            }
            SimpleVideoView.this.p = false;
            SimpleVideoView.this.w();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            if (SimpleVideoView.this.f10994h != null) {
                SimpleVideoView.this.f10994h.onError(i2, i3);
            }
            SimpleVideoView.this.f10999m.setVisibility(8);
            SimpleVideoView.this.f10998l.setVisibility(0);
            SimpleVideoView.this.f10996j.setImageResource(R.drawable.video_activity_simple_play_btn);
            SimpleVideoView.this.p = true;
            return true;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            if (i2 == 3) {
                SimpleVideoView.this.f10999m.setVisibility(8);
            } else if (i2 == 701) {
                SimpleVideoView.this.f10999m.setVisibility(0);
            } else if (i2 == 702) {
                SimpleVideoView.this.f10999m.setVisibility(8);
            }
            if (SimpleVideoView.this.f10994h != null) {
                SimpleVideoView.this.f10994h.onInfo(i2, i3);
                return true;
            }
            return true;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            if (SimpleVideoView.this.f10994h != null) {
                SimpleVideoView.this.f10994h.onPrepared(j2);
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
            if (SimpleVideoView.this.f10994h != null) {
                SimpleVideoView.this.f10994h.onSeekComplete();
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface f {
        void a(boolean z);

        void onCloseClick();
    }

    public SimpleVideoView(Context context) {
        super(context);
        this.o = false;
        this.p = false;
        this.q = false;
        this.s = true;
        t(context);
    }

    public void a() {
        if (this.q) {
            this.q = false;
            ((AudioManager) getContext().getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    private void t(Context context) {
        RelativeLayout.inflate(context, R.layout.view_activity_simple_video, this);
        this.f10993g = (IjkVideoViewWithReport) findViewById(R.id.video_activity_player);
        this.f10995i = (ImageView) findViewById(R.id.video_activity_close);
        this.f10996j = (ImageView) findViewById(R.id.video_activity_play);
        this.f10997k = (ImageView) findViewById(R.id.video_activity_voice);
        this.f10998l = (ImageView) findViewById(R.id.video_activity_refresh_btn);
        this.f10999m = findViewById(R.id.video_activity_loading_view);
        this.f10995i.setOnClickListener(new a());
        this.f10996j.setOnClickListener(new b());
        this.f10996j.setVisibility(8);
        this.f10997k.setOnClickListener(new c());
        this.f10998l.setOnClickListener(new d());
        this.f10998l.setVisibility(8);
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        playerOptions.setVolume(this.o ? 1.0f : 0.0f);
        playerOptions.setIpv6VideoPlay(SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.VIDEO_IPV6_SWITCH, false));
        playerOptions.setIsRequestAudioFocus(false);
        o(this.o);
        this.f10993g.setPlayerOptions(new IPlayerControl.PlayerOptions(false));
        this.f10993g.setOnPlayerStateListener(new e());
    }

    public void w() {
        if (!this.q && this.o) {
            this.q = true;
            ((AudioManager) getContext().getSystemService("audio")).requestAudioFocus(null, 3, 2);
        }
    }

    public void A(int i2) {
        this.f10996j.setVisibility(i2);
    }

    public void B(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        this.f10993g.setReportParams(str, str2, str3, RecommendMtaUtils.Home_PageId, str4, "", "", "");
    }

    public void C(f fVar) {
        this.r = fVar;
    }

    public void D(String str) {
        this.f10998l.setVisibility(8);
        this.f11000n = str;
        this.f10993g.setVideoPath(str);
        this.f10996j.setImageResource(R.drawable.video_activity_simple_pause_btn);
    }

    public void E() {
        this.f10998l.setVisibility(8);
        this.f10993g.start();
        this.f10996j.setImageResource(R.drawable.video_activity_simple_pause_btn);
    }

    public void o(boolean z) {
        this.o = z;
        this.f10997k.setImageResource(z ? R.drawable.video_activity_simple_voice_on : R.drawable.video_activity_simple_voice_off);
        if (this.f10993g.getPlayerOptions() != null) {
            this.f10993g.setVolume(z ? 1.0f : 0.0f);
        }
        w();
    }

    public boolean p() {
        return this.p;
    }

    public FrameLayout q() {
        return (FrameLayout) findViewById(R.id.video_activity_layer_parent);
    }

    public FrameLayout r() {
        return (FrameLayout) findViewById(R.id.video_activity_video_cover_parent);
    }

    public void s() {
        this.f10993g.initRenders();
    }

    public void u() {
        this.f10993g.pause();
        this.f10996j.setImageResource(R.drawable.video_activity_simple_play_btn);
    }

    public void v() {
        this.f10993g.releaseInThread(true);
        a();
    }

    public void x() {
        this.f10993g.suspend();
        s();
        this.f10996j.setImageResource(R.drawable.video_activity_simple_pause_btn);
    }

    public void y(boolean z) {
        this.s = z;
    }

    public void z(IPlayerControl.OnPlayerStateListener onPlayerStateListener) {
        this.f10994h = onPlayerStateListener;
    }

    public SimpleVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = false;
        this.p = false;
        this.q = false;
        this.s = true;
        t(context);
    }

    public SimpleVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.o = false;
        this.p = false;
        this.q = false;
        this.s = true;
        t(context);
    }
}
