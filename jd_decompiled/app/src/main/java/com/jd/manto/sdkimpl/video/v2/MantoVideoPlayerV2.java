package com.jd.manto.sdkimpl.video.v2;

import android.app.Activity;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.jsapi.refact.video.IVideoInterface;
import com.jingdong.manto.utils.MantoLog;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;

/* loaded from: classes17.dex */
public class MantoVideoPlayerV2 extends JDPlayerView implements com.jd.manto.d.c0.a {

    /* renamed from: g */
    private String f6816g;

    /* renamed from: h */
    private String f6817h;

    /* renamed from: i */
    private String f6818i;

    /* renamed from: j */
    private boolean f6819j;

    /* renamed from: k */
    private boolean f6820k;

    /* renamed from: l */
    public IVideoInterface f6821l;

    /* renamed from: m */
    public String f6822m;

    /* renamed from: n */
    public int f6823n;
    private boolean o;
    private IPlayerControl.PlayerOptions p;
    private JDControllerOptions.Builder q;
    private boolean r;
    private int s;
    private long t;
    private String u;
    private boolean v;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements IMediaPlayer.OnPlayerEventListener {
        a() {
            MantoVideoPlayerV2.this = r1;
        }

        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPlayerEventListener
        public void onPlayEvent(int i2) {
            MantoLog.d(MantoVideoPlayerV2.this.f6816g, "onPlayEvent :" + i2);
            if (i2 != 1) {
                if (i2 != 2) {
                    return;
                }
                MantoLog.i(MantoVideoPlayerV2.this.f6816g, "PLAYER_PAUSE");
                MantoVideoPlayerV2 mantoVideoPlayerV2 = MantoVideoPlayerV2.this;
                IVideoInterface iVideoInterface = mantoVideoPlayerV2.f6821l;
                if (iVideoInterface != null) {
                    iVideoInterface.onPause(mantoVideoPlayerV2.f6823n);
                }
                MantoVideoPlayerV2.this.t = 0L;
                return;
            }
            MantoLog.i(MantoVideoPlayerV2.this.f6816g, "PLAYER_START");
            MantoVideoPlayerV2 mantoVideoPlayerV22 = MantoVideoPlayerV2.this;
            IVideoInterface iVideoInterface2 = mantoVideoPlayerV22.f6821l;
            if (iVideoInterface2 != null) {
                iVideoInterface2.onPlay(mantoVideoPlayerV22.f6823n);
            }
            if (MantoVideoPlayerV2.this.s > 0) {
                MantoVideoPlayerV2 mantoVideoPlayerV23 = MantoVideoPlayerV2.this;
                mantoVideoPlayerV23.seekTo(mantoVideoPlayerV23.s);
                MantoVideoPlayerV2.this.s = 0;
                MantoVideoPlayerV2.this.o = false;
            }
            MantoVideoPlayerV2.this.t = System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements JDPlayerView.PlayerControllerCallback {
        b() {
            MantoVideoPlayerV2.this = r1;
        }

        @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
        public void onNetChanged() {
        }

        @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
        public void onOrientationChanged(boolean z, int i2) {
            MantoVideoPlayerV2.this.f6819j = z;
            MantoLog.i(MantoVideoPlayerV2.this.f6816g, "onChange:" + z + DYConstants.DY_REGEX_COMMA + i2);
            String str = DYConstants.DY_SCROLL_VERTICAL;
            if (i2 == 0 || i2 == 6) {
                str = DYConstants.DY_SCROLL_HORIZONTAL;
            }
            MantoVideoPlayerV2 mantoVideoPlayerV2 = MantoVideoPlayerV2.this;
            IVideoInterface iVideoInterface = mantoVideoPlayerV2.f6821l;
            if (iVideoInterface != null) {
                iVideoInterface.onFullScreenChange(mantoVideoPlayerV2.f6823n, z, str);
            }
        }

        @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
        public void onPlayBtnClick(boolean z) {
        }

        @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
        public void onProgressUpdate(boolean z, int i2, long j2, boolean z2) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - MantoVideoPlayerV2.this.t > 250) {
                MantoVideoPlayerV2.this.C();
                MantoVideoPlayerV2.this.t = currentTimeMillis;
            }
        }

        @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
        public void onVoiceBtnClick(boolean z) {
        }

        @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
        public void onVoiceStateChange(boolean z) {
        }

        @Override // tv.danmaku.ijk.media.widget.JDPlayerView.PlayerControllerCallback
        public void seekBarOnSeek(int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements IPlayerControl.OnPlayerStateListener {
        c() {
            MantoVideoPlayerV2.this = r1;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            MantoLog.i(MantoVideoPlayerV2.this.f6816g, "PLAYER_COMPLETED");
            MantoVideoPlayerV2 mantoVideoPlayerV2 = MantoVideoPlayerV2.this;
            IVideoInterface iVideoInterface = mantoVideoPlayerV2.f6821l;
            if (iVideoInterface != null) {
                iVideoInterface.onEnd(mantoVideoPlayerV2.f6823n);
            }
            MantoVideoPlayerV2.this.t = 0L;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            MantoLog.i(MantoVideoPlayerV2.this.f6816g, "onCreatePlayer");
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            MantoLog.i(MantoVideoPlayerV2.this.f6816g, "onError:" + i2 + DYConstants.DY_REGEX_COMMA + i3);
            MantoVideoPlayerV2 mantoVideoPlayerV2 = MantoVideoPlayerV2.this;
            IVideoInterface iVideoInterface = mantoVideoPlayerV2.f6821l;
            if (iVideoInterface != null) {
                iVideoInterface.onError(mantoVideoPlayerV2.f6823n, i2, i3);
                return true;
            }
            return true;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            MantoLog.i(MantoVideoPlayerV2.this.f6816g, "onInfo:" + i2 + DYConstants.DY_REGEX_COMMA + i3);
            if (i2 == 3) {
                MantoLog.i(MantoVideoPlayerV2.this.f6816g, "\u5f00\u59cb\u6e32\u67d3\u89c6\u9891\u7b2c\u4e00\u5e27\u753b\u9762");
                return true;
            } else if (i2 == 802) {
                MantoLog.i(MantoVideoPlayerV2.this.f6816g, "\u5143\u6570\u636e\u66f4\u65b0");
                MantoVideoPlayerV2 mantoVideoPlayerV2 = MantoVideoPlayerV2.this;
                IVideoInterface iVideoInterface = mantoVideoPlayerV2.f6821l;
                if (iVideoInterface != null) {
                    iVideoInterface.onLoadedMetaData(mantoVideoPlayerV2.f6823n);
                    return true;
                }
                return true;
            } else if (i2 == 701) {
                MantoLog.i(MantoVideoPlayerV2.this.f6816g, "\u5f00\u59cb\u7f13\u51b2");
                MantoVideoPlayerV2 mantoVideoPlayerV22 = MantoVideoPlayerV2.this;
                IVideoInterface iVideoInterface2 = mantoVideoPlayerV22.f6821l;
                if (iVideoInterface2 != null) {
                    iVideoInterface2.onLoadedStart(mantoVideoPlayerV22.f6823n);
                    return true;
                }
                return true;
            } else if (i2 != 702) {
                return true;
            } else {
                MantoLog.i(MantoVideoPlayerV2.this.f6816g, "\u7ed3\u675f\u7f13\u51b2");
                MantoVideoPlayerV2 mantoVideoPlayerV23 = MantoVideoPlayerV2.this;
                IVideoInterface iVideoInterface3 = mantoVideoPlayerV23.f6821l;
                if (iVideoInterface3 != null) {
                    iVideoInterface3.onLoadedData(mantoVideoPlayerV23.f6823n);
                    return true;
                }
                return true;
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            MantoLog.i(MantoVideoPlayerV2.this.f6816g, "onPrepared:" + j2);
            MantoVideoPlayerV2.this.f6817h = null;
            MantoVideoPlayerV2.this.r = true;
            MantoVideoPlayerV2.this.o = false;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
            MantoLog.i("MantoVideoPlayerV2", "onSeekComplete:");
            MantoVideoPlayerV2 mantoVideoPlayerV2 = MantoVideoPlayerV2.this;
            IVideoInterface iVideoInterface = mantoVideoPlayerV2.f6821l;
            if (iVideoInterface != null) {
                iVideoInterface.onSeeked(mantoVideoPlayerV2.f6823n);
            }
        }
    }

    public MantoVideoPlayerV2(Activity activity) {
        super(activity);
        this.f6816g = MantoVideoPlayerV2.class.getName();
        this.s = 0;
        this.t = 0L;
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        this.p = playerOptions;
        playerOptions.setCouldMediaCodec(true);
        this.p.setEnableAccurateSeek(true);
        this.p.setEnableReport(false);
        this.p.showControllerOnStart(false);
        this.p.setReconnectCount(5);
        this.p.setPlayTypeId("78");
        this.q = new JDControllerOptions.Builder().systemBarFlag(8192).enableMuteSwitch(true).enableReplay(true).enableFullSwitch(true).manualControlVisible(false).enableSpeedSwitch(true).activity(activity);
        addPlayerEventCallback(new a());
        addPlayerControllerCallback(new b());
        setPlayerStateListener(new c());
    }

    private int A(String str) {
        if ("contain".equals(str)) {
            return 0;
        }
        if ("cover".equals(str)) {
            return 1;
        }
        return DYConstants.DY_FILL.equals(str) ? 3 : 0;
    }

    public void B() {
        this.p.setPlayerReportInfoEntity(new PlayerReportInfoEntity("", this.u));
        setPlayerOptions(this.p, this.q.build());
        m(this.v);
    }

    public void C() {
        IVideoInterface iVideoInterface = this.f6821l;
        if (iVideoInterface != null) {
            iVideoInterface.timeUpdate(this.f6823n);
        }
    }

    @Override // com.jd.manto.d.c0.a
    public void a(String str) {
        k(str, 0);
    }

    @Override // com.jd.manto.d.c0.a
    public void b(String str) {
        this.p.setAspectRatio(A(str));
    }

    @Override // com.jd.manto.d.c0.a
    public void c() {
        requestScreenChange(1);
    }

    @Override // com.jd.manto.d.c0.a
    public void d(String str) {
        this.f6822m = str;
    }

    @Override // com.jd.manto.d.c0.a
    public synchronized void destroy() {
        if (this.f6819j) {
            requestScreenChange(1);
        }
        release();
        this.t = 0L;
        this.r = false;
    }

    @Override // com.jd.manto.d.c0.a
    public void e(int i2) {
        this.f6823n = i2;
    }

    @Override // com.jd.manto.d.c0.a
    public void f() {
        if (this.f6819j) {
            return;
        }
        requestScreenChange(0);
    }

    @Override // com.jd.manto.d.c0.a
    public void g(IVideoInterface iVideoInterface) {
        this.f6821l = iVideoInterface;
    }

    @Override // com.jd.manto.d.c0.a
    public String getData() {
        return this.f6822m;
    }

    @Override // com.jd.manto.d.c0.a
    public int getVideoDuration() {
        return super.getDuration();
    }

    @Override // com.jd.manto.d.c0.a
    public void h(boolean z) {
        if (this.f6819j) {
            return;
        }
        enableFullBtn(z);
    }

    @Override // com.jd.manto.d.c0.a
    public void i(int i2) {
        seekTo(i2);
    }

    @Override // com.jd.manto.d.c0.a
    public void j(String str) {
        setImgCover(str);
    }

    @Override // com.jd.manto.d.c0.a
    public void k(String str, int i2) {
        B();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i2 > 0) {
            this.f6818i = str;
            if (this.f6820k) {
                this.s = 0;
                setVideoPath(str, i2, JDPlayerView.PlayMode.AUTO_PLAY);
                this.f6817h = null;
            } else {
                this.s = i2;
                setVideoPath(str, JDPlayerView.PlayMode.CLICK_PLAY);
                this.f6817h = str;
            }
            this.o = false;
            return;
        }
        this.f6818i = str;
        if (this.f6820k) {
            setVideoPath(str, JDPlayerView.PlayMode.AUTO_PLAY);
            this.f6817h = null;
        } else {
            setVideoPath(str, JDPlayerView.PlayMode.CLICK_PLAY);
            this.f6817h = str;
        }
        this.o = false;
        this.s = 0;
    }

    @Override // com.jd.manto.d.c0.a
    public int l() {
        return super.getCurrentPosition();
    }

    @Override // com.jd.manto.d.c0.a
    public void m(boolean z) {
        this.v = z;
        if (this.r) {
            requestUnHandleTouchEvent(!z);
        } else {
            changeStartIconVisible(z ? 0 : 4);
        }
        forceChangeControlVisible(z ? 0 : 4, true);
    }

    @Override // com.jd.manto.d.c0.a
    public void n(boolean z) {
        this.p.setUseCache(z);
    }

    @Override // com.jd.manto.d.c0.a
    public void o(int i2) {
        this.q.enableNonWifiTip(i2 == 1);
    }

    @Override // com.jd.manto.d.c0.a
    public void p(boolean z) {
        this.q.enableSpeedSwitch(z);
    }

    @Override // com.jd.manto.d.c0.a
    public void pauseIfPlaying() {
        if (isPlaying()) {
            pause();
        }
    }

    @Override // com.jd.manto.d.c0.a
    public void playIfNotPlaying() {
        if (isPlaying()) {
            return;
        }
        if (!TextUtils.isEmpty(this.f6817h)) {
            setVideoPath(this.f6817h, JDPlayerView.PlayMode.AUTO_PLAY);
            int i2 = this.s;
            if (i2 > 0) {
                seekTo(i2);
                this.s = 0;
            }
            this.f6817h = null;
        } else if (this.o) {
            replay();
        } else {
            start();
        }
        this.o = false;
    }

    @Override // com.jd.manto.d.c0.a
    public void q(boolean z) {
        try {
            JDVideoView videoView = getVideoView(false);
            if (z) {
                this.p.setVolume(0.0f);
                if (this.r && videoView != null) {
                    videoView.setVolume(0.0f);
                }
            } else {
                this.p.setVolume(1.0f);
                if (this.r && videoView != null) {
                    videoView.setVolume(1.0f);
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jd.manto.d.c0.a
    public void setAppId(String str) {
        this.u = str;
    }

    @Override // com.jd.manto.d.c0.a
    public void setAutoPlay(boolean z) {
        this.f6820k = z;
    }

    @Override // tv.danmaku.ijk.media.widget.JDPlayerView, com.jd.manto.d.c0.a
    public void setLoop(boolean z) {
        this.p.setLoop(z);
        super.setLoop(z);
    }

    @Override // tv.danmaku.ijk.media.widget.JDPlayerView, com.jd.manto.d.c0.a
    public void setSpeed(float f2) {
        super.setSpeed(f2);
    }

    @Override // tv.danmaku.ijk.media.widget.JDPlayerView, com.jd.manto.d.c0.a
    public synchronized void stop() {
        this.o = true;
        this.f6817h = this.f6818i;
        super.stop();
    }
}
