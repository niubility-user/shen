package com.jd.manto.sdkimpl.live.v2;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.dynamic.DYConstants;
import com.jd.manto.d.b0.c;
import com.jd.manto.sdkimpl.live.v2.a;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.common.permission.FloatPermissionManager;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.permission.UphoneCallback;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.jsapi.refact.live.ILiveInterface;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.platform.business.personal.R2;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager;
import tv.danmaku.ijk.media.widget.window.WindowPlayerConfig;

/* loaded from: classes17.dex */
public class MantoLivePlayerV2 extends MantoLiveVideoView implements com.jd.manto.d.b0.a {
    private boolean A;
    private IPlayerControl.PlayerOptions B;
    private boolean C;
    private boolean D;
    private boolean E;
    private c.d F;
    private JDWindowPlayerManager G;
    private String H;
    boolean I;
    boolean J;
    boolean K;
    private String L;
    private JDVideoView M;
    private boolean N;
    IPlayerControl.OnPlayerStateListener O;
    IPlayerControl.OnStatisticsStateListener P;
    boolean Q;
    boolean R;
    AppLifeCycle.Listener S;
    private String r;
    private String s;
    private String t;
    private boolean u;
    private com.jd.manto.sdkimpl.live.v2.a v;
    private boolean w;
    public ILiveInterface x;
    public String y;
    public int z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements IPlayerControl.OnPlayerStateListener {
        a() {
            MantoLivePlayerV2.this = r1;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            MantoLog.i(MantoLivePlayerV2.this.r, "onCompletion:");
            MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
            ILiveInterface iLiveInterface = mantoLivePlayerV2.x;
            if (iLiveInterface != null) {
                iLiveInterface.onLivePlayerEvent(mantoLivePlayerV2.z, 2003);
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            MantoLog.i(MantoLivePlayerV2.this.r, "onCreatePlayer");
            MantoLivePlayerV2.this.s = null;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            MantoLog.i(MantoLivePlayerV2.this.r, "onError:" + i2 + DYConstants.DY_REGEX_COMMA + i3);
            switch (i2) {
                case IjkMediaPlayer.AVERROR_HTTP_SERVER_ERROR /* -1482175992 */:
                    MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface = mantoLivePlayerV2.x;
                    if (iLiveInterface != null) {
                        iLiveInterface.onLivePlayerEvent(mantoLivePlayerV2.z, 4100);
                        return false;
                    }
                    return false;
                case IjkMediaPlayer.AVERROR_HTTP_OTHER_4XX /* -1482175736 */:
                    MantoLivePlayerV2 mantoLivePlayerV22 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface2 = mantoLivePlayerV22.x;
                    if (iLiveInterface2 != null) {
                        iLiveInterface2.onLivePlayerEvent(mantoLivePlayerV22.z, R2.color.pd_color_333333);
                        return false;
                    }
                    return false;
                case -1094995529:
                    MantoLivePlayerV2 mantoLivePlayerV23 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface3 = mantoLivePlayerV23.x;
                    if (iLiveInterface3 != null) {
                        iLiveInterface3.onLivePlayerEvent(mantoLivePlayerV23.z, R2.color.keyboard_color_action_text_high_light_dark);
                        return false;
                    }
                    return false;
                case IjkMediaPlayer.AVERROR_HTTP_NOT_FOUND /* -875574520 */:
                    MantoLivePlayerV2 mantoLivePlayerV24 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface4 = mantoLivePlayerV24.x;
                    if (iLiveInterface4 != null) {
                        iLiveInterface4.onLivePlayerEvent(mantoLivePlayerV24.z, R2.color.libpaipaireplacemodel_paipai_color_252525);
                        return false;
                    }
                    return false;
                case IjkMediaPlayer.AVERROR_HTTP_FORBIDDEN /* -858797304 */:
                    MantoLivePlayerV2 mantoLivePlayerV25 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface5 = mantoLivePlayerV25.x;
                    if (iLiveInterface5 != null) {
                        iLiveInterface5.onLivePlayerEvent(mantoLivePlayerV25.z, R2.color.libpaipaireplacemodel_paipai_color_d7d7d7);
                        return false;
                    }
                    return false;
                case IjkMediaPlayer.AVERROR_HTTP_UNAUTHORIZED /* -825242872 */:
                    MantoLivePlayerV2 mantoLivePlayerV26 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface6 = mantoLivePlayerV26.x;
                    if (iLiveInterface6 != null) {
                        iLiveInterface6.onLivePlayerEvent(mantoLivePlayerV26.z, R2.color.libpdstyleinfoview_color_ff3826);
                        return false;
                    }
                    return false;
                case IjkMediaPlayer.AVERROR_HTTP_BAD_REQUEST /* -808465656 */:
                    MantoLivePlayerV2 mantoLivePlayerV27 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface7 = mantoLivePlayerV27.x;
                    if (iLiveInterface7 != null) {
                        iLiveInterface7.onLivePlayerEvent(mantoLivePlayerV27.z, R2.color.message_pop_bg_color);
                        return false;
                    }
                    return false;
                case -541478725:
                    MantoLivePlayerV2 mantoLivePlayerV28 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface8 = mantoLivePlayerV28.x;
                    if (iLiveInterface8 != null) {
                        iLiveInterface8.onLivePlayerEvent(mantoLivePlayerV28.z, R2.color.keyboard_color_action_text_high_light);
                        return false;
                    }
                    return false;
                case -2005:
                    MantoLivePlayerV2 mantoLivePlayerV29 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface9 = mantoLivePlayerV29.x;
                    if (iLiveInterface9 != null) {
                        iLiveInterface9.onLivePlayerEvent(mantoLivePlayerV29.z, R2.color.keyboard_color_action_text);
                        return false;
                    }
                    return false;
                case JDRiskHandleError.CODE_SDK_NOT_INIT /* -2004 */:
                    MantoLivePlayerV2 mantoLivePlayerV210 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface10 = mantoLivePlayerV210.x;
                    if (iLiveInterface10 != null) {
                        iLiveInterface10.onLivePlayerEvent(mantoLivePlayerV210.z, R2.color.key_step_logger_analyser_bg_color);
                        return false;
                    }
                    return false;
                case IMediaPlayer.MEDIA_ERROR_UNSUPPORTED /* -1010 */:
                    MantoLivePlayerV2 mantoLivePlayerV211 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface11 = mantoLivePlayerV211.x;
                    if (iLiveInterface11 != null) {
                        iLiveInterface11.onLivePlayerEvent(mantoLivePlayerV211.z, 3007);
                        return false;
                    }
                    return false;
                case -1009:
                    MantoLivePlayerV2 mantoLivePlayerV212 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface12 = mantoLivePlayerV212.x;
                    if (iLiveInterface12 != null) {
                        iLiveInterface12.onLivePlayerEvent(mantoLivePlayerV212.z, R2.color.jrtxt_main_title_color);
                        return false;
                    }
                    return false;
                case IMediaPlayer.MEDIA_ERROR_MALFORMED /* -1007 */:
                    MantoLivePlayerV2 mantoLivePlayerV213 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface13 = mantoLivePlayerV213.x;
                    if (iLiveInterface13 != null) {
                        iLiveInterface13.onLivePlayerEvent(mantoLivePlayerV213.z, 3006);
                        return false;
                    }
                    return false;
                case -1004:
                    MantoLivePlayerV2 mantoLivePlayerV214 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface14 = mantoLivePlayerV214.x;
                    if (iLiveInterface14 != null) {
                        iLiveInterface14.onLivePlayerEvent(mantoLivePlayerV214.z, 3005);
                        return false;
                    }
                    return false;
                case -1001:
                    MantoLivePlayerV2 mantoLivePlayerV215 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface15 = mantoLivePlayerV215.x;
                    if (iLiveInterface15 != null) {
                        iLiveInterface15.onLivePlayerEvent(mantoLivePlayerV215.z, 4001);
                        return false;
                    }
                    return false;
                case IMediaPlayer.MEDIA_ERROR_TIMED_OUT /* -110 */:
                    MantoLivePlayerV2 mantoLivePlayerV216 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface16 = mantoLivePlayerV216.x;
                    if (iLiveInterface16 != null) {
                        iLiveInterface16.onLivePlayerEvent(mantoLivePlayerV216.z, R2.attr.textAppearanceHeadline6);
                        return false;
                    }
                    return false;
                case -5:
                    MantoLivePlayerV2 mantoLivePlayerV217 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface17 = mantoLivePlayerV217.x;
                    if (iLiveInterface17 != null) {
                        iLiveInterface17.onLivePlayerEvent(mantoLivePlayerV217.z, R2.color.keyboard_color_action_text_dark);
                        return false;
                    }
                    return false;
                case 1:
                    MantoLivePlayerV2 mantoLivePlayerV218 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface18 = mantoLivePlayerV218.x;
                    if (iLiveInterface18 != null) {
                        iLiveInterface18.onLivePlayerEvent(mantoLivePlayerV218.z, 3001);
                        return false;
                    }
                    return false;
                case 100:
                    MantoLivePlayerV2 mantoLivePlayerV219 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface19 = mantoLivePlayerV219.x;
                    if (iLiveInterface19 != null) {
                        iLiveInterface19.onLivePlayerEvent(mantoLivePlayerV219.z, 3002);
                        return false;
                    }
                    return false;
                case 300:
                    MantoLivePlayerV2 mantoLivePlayerV220 = MantoLivePlayerV2.this;
                    ILiveInterface iLiveInterface20 = mantoLivePlayerV220.x;
                    if (iLiveInterface20 != null) {
                        iLiveInterface20.onLivePlayerEvent(mantoLivePlayerV220.z, 3003);
                        return false;
                    }
                    return false;
                default:
                    return false;
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            MantoLog.i(MantoLivePlayerV2.this.r, "onInfo:" + i2 + DYConstants.DY_REGEX_COMMA + i3);
            if (i2 == 3) {
                MantoLog.i(MantoLivePlayerV2.this.r, "\u5f00\u59cb\u6e32\u67d3\u89c6\u9891\u7b2c\u4e00\u5e27\u753b\u9762");
                MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
                ILiveInterface iLiveInterface = mantoLivePlayerV2.x;
                if (iLiveInterface != null) {
                    iLiveInterface.onLivePlayerEvent(mantoLivePlayerV2.z, 2006);
                    return false;
                }
                return false;
            } else if (i2 == 802) {
                MantoLog.i(MantoLivePlayerV2.this.r, "\u5143\u6570\u636e\u66f4\u65b0");
                MantoLivePlayerV2 mantoLivePlayerV22 = MantoLivePlayerV2.this;
                ILiveInterface iLiveInterface2 = mantoLivePlayerV22.x;
                if (iLiveInterface2 != null) {
                    iLiveInterface2.onLivePlayerEvent(mantoLivePlayerV22.z, R2.attr.textAppearanceHeadline5);
                    return false;
                }
                return false;
            } else if (i2 == 902) {
                MantoLivePlayerV2 mantoLivePlayerV23 = MantoLivePlayerV2.this;
                ILiveInterface iLiveInterface3 = mantoLivePlayerV23.x;
                if (iLiveInterface3 != null) {
                    iLiveInterface3.onLivePlayerEvent(mantoLivePlayerV23.z, R2.attr.textAppearanceHeadline6);
                    return false;
                }
                return false;
            } else if (i2 == 10002) {
                MantoLivePlayerV2 mantoLivePlayerV24 = MantoLivePlayerV2.this;
                ILiveInterface iLiveInterface4 = mantoLivePlayerV24.x;
                if (iLiveInterface4 != null) {
                    iLiveInterface4.onLivePlayerEvent(mantoLivePlayerV24.z, R2.attr.titleTextAppearance);
                    return false;
                }
                return false;
            } else if (i2 == 10303) {
                MantoLivePlayerV2 mantoLivePlayerV25 = MantoLivePlayerV2.this;
                ILiveInterface iLiveInterface5 = mantoLivePlayerV25.x;
                if (iLiveInterface5 != null) {
                    iLiveInterface5.onLivePlayerEvent(mantoLivePlayerV25.z, 3008);
                    return false;
                }
                return false;
            } else if (i2 != 10304) {
                switch (i2) {
                    case 700:
                        MantoLivePlayerV2 mantoLivePlayerV26 = MantoLivePlayerV2.this;
                        ILiveInterface iLiveInterface6 = mantoLivePlayerV26.x;
                        if (iLiveInterface6 != null) {
                            iLiveInterface6.onLivePlayerEvent(mantoLivePlayerV26.z, 2007);
                            return false;
                        }
                        return false;
                    case 701:
                        MantoLog.i(MantoLivePlayerV2.this.r, "\u5f00\u59cb\u7f13\u51b2");
                        MantoLivePlayerV2 mantoLivePlayerV27 = MantoLivePlayerV2.this;
                        ILiveInterface iLiveInterface7 = mantoLivePlayerV27.x;
                        if (iLiveInterface7 != null) {
                            iLiveInterface7.onLivePlayerEvent(mantoLivePlayerV27.z, 2004);
                            return false;
                        }
                        return false;
                    case 702:
                        MantoLog.i(MantoLivePlayerV2.this.r, "\u7ed3\u675f\u7f13\u51b2");
                        MantoLivePlayerV2 mantoLivePlayerV28 = MantoLivePlayerV2.this;
                        ILiveInterface iLiveInterface8 = mantoLivePlayerV28.x;
                        if (iLiveInterface8 != null) {
                            iLiveInterface8.onLivePlayerEvent(mantoLivePlayerV28.z, 2005);
                            return false;
                        }
                        return false;
                    default:
                        return false;
                }
            } else {
                MantoLivePlayerV2 mantoLivePlayerV29 = MantoLivePlayerV2.this;
                ILiveInterface iLiveInterface9 = mantoLivePlayerV29.x;
                if (iLiveInterface9 != null) {
                    iLiveInterface9.onLivePlayerEvent(mantoLivePlayerV29.z, R2.attr.titleTextColor);
                    return false;
                }
                return false;
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            MantoLog.i(MantoLivePlayerV2.this.r, "onPrepared:" + j2);
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
            MantoLog.i("MantoVideoPlayer", "onSeekComplete:");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements IPlayerControl.OnStatisticsStateListener {
        b() {
            MantoLivePlayerV2.this = r1;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
        public void pause() {
            String unused = MantoLivePlayerV2.this.r;
            MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
            ILiveInterface iLiveInterface = mantoLivePlayerV2.x;
            if (iLiveInterface != null) {
                iLiveInterface.onLivePlayerEvent(mantoLivePlayerV2.z, 2002);
            }
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnStatisticsStateListener
        public void start() {
            String unused = MantoLivePlayerV2.this.r;
            MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
            ILiveInterface iLiveInterface = mantoLivePlayerV2.x;
            if (iLiveInterface != null) {
                iLiveInterface.onLivePlayerEvent(mantoLivePlayerV2.z, 2001);
            }
            if (MantoLivePlayerV2.this.F != null) {
                c.d dVar = MantoLivePlayerV2.this.F;
                MantoLivePlayerV2 mantoLivePlayerV22 = MantoLivePlayerV2.this;
                dVar.b(mantoLivePlayerV22, mantoLivePlayerV22.I, mantoLivePlayerV22.J);
            }
        }
    }

    /* loaded from: classes17.dex */
    public class c implements a.c {
        c() {
            MantoLivePlayerV2.this = r1;
        }

        @Override // com.jd.manto.sdkimpl.live.v2.a.c
        public void onChange(boolean z, int i2) {
            MantoLivePlayerV2.this.u = z;
            MantoLog.i(MantoLivePlayerV2.this.r, "onChange:" + z + DYConstants.DY_REGEX_COMMA + i2);
            String str = DYConstants.DY_SCROLL_VERTICAL;
            if (i2 == 6) {
                str = DYConstants.DY_SCROLL_HORIZONTAL;
            }
            MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
            ILiveInterface iLiveInterface = mantoLivePlayerV2.x;
            if (iLiveInterface != null) {
                iLiveInterface.onLivePlayerFullScreenChange(mantoLivePlayerV2.z, z, str);
            }
        }
    }

    /* loaded from: classes17.dex */
    public class d implements UphoneCallback {
        d() {
            MantoLivePlayerV2.this = r1;
        }

        @Override // com.jingdong.common.permission.UphoneCallback
        public void invoke(boolean z, String str) {
            if (z) {
                MantoLivePlayerV2.this.N = true;
                AppLifeCycle.add(MantoLivePlayerV2.this.L, MantoLivePlayerV2.this.S);
                return;
            }
            if (MantoLivePlayerV2.this.F != null) {
                MantoLivePlayerV2.this.F.clear();
            }
            MantoLivePlayerV2.this.K = false;
        }

        @Override // com.jingdong.common.permission.UphoneCallback
        public void onIgnored() {
            if (MantoLivePlayerV2.this.F != null) {
                MantoLivePlayerV2.this.F.clear();
            }
            MantoLivePlayerV2.this.K = false;
        }
    }

    /* loaded from: classes17.dex */
    public class e implements JDWindowPlayerManager.OnPlayWindowCallback {
        e() {
            MantoLivePlayerV2.this = r1;
        }

        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager.OnPlayWindowCallback
        public boolean onClose(ViewGroup viewGroup) {
            boolean z;
            MantoLog.d(MantoLivePlayerV2.this.r, "onClose");
            MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
            mantoLivePlayerV2.R = false;
            if (mantoLivePlayerV2.Q) {
                mantoLivePlayerV2.w(viewGroup);
            } else if (!mantoLivePlayerV2.K) {
                if (mantoLivePlayerV2.F != null) {
                    MantoLivePlayerV2.this.F.onClose();
                }
                z = true;
                MantoLivePlayerV2 mantoLivePlayerV22 = MantoLivePlayerV2.this;
                mantoLivePlayerV22.K = false;
                mantoLivePlayerV22.Q = false;
                return z;
            } else {
                mantoLivePlayerV2.w(viewGroup);
                MantoLivePlayerV2 mantoLivePlayerV23 = MantoLivePlayerV2.this;
                mantoLivePlayerV23.E = mantoLivePlayerV23.D;
                MantoLivePlayerV2.this.D = false;
                MantoLivePlayerV2.this.h0(false);
            }
            z = false;
            MantoLivePlayerV2 mantoLivePlayerV222 = MantoLivePlayerV2.this;
            mantoLivePlayerV222.K = false;
            mantoLivePlayerV222.Q = false;
            return z;
        }

        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager.OnPlayWindowCallback
        public void onError(int i2, ViewGroup viewGroup) {
        }

        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager.OnPlayWindowCallback
        public void onShow() {
            MantoLog.d(MantoLivePlayerV2.this.r, "onShow:");
            MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
            mantoLivePlayerV2.R = true;
            AppLifeCycle.add(mantoLivePlayerV2.L, MantoLivePlayerV2.this.S);
        }

        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager.OnPlayWindowCallback
        public boolean onWindowClick() {
            MantoLog.d(MantoLivePlayerV2.this.r, "onWindowClick");
            MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
            mantoLivePlayerV2.Q = true;
            mantoLivePlayerV2.R = false;
            if (mantoLivePlayerV2.F != null) {
                MantoLivePlayerV2.this.F.a(MantoLivePlayerV2.this.H);
            }
            return true;
        }

        @Override // tv.danmaku.ijk.media.widget.window.JDWindowPlayerManager.OnPlayWindowCallback
        public void onWindowVisible(boolean z) {
            MantoLog.d(MantoLivePlayerV2.this.r, "onWindowVisible :" + z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f extends AppLifeCycle.Listener {
        f() {
            MantoLivePlayerV2.this = r1;
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppCreate() {
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppDestroy() {
            AppLifeCycle.remove(MantoLivePlayerV2.this.L, this);
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppPause() {
            if (MantoLivePlayerV2.this.G != null) {
                MantoLivePlayerV2 mantoLivePlayerV2 = MantoLivePlayerV2.this;
                if (mantoLivePlayerV2.R) {
                    mantoLivePlayerV2.Q = false;
                    mantoLivePlayerV2.G.close();
                    AppLifeCycle.remove(MantoLivePlayerV2.this.L, this);
                }
            }
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppResume() {
            if (MantoLivePlayerV2.this.N) {
                if (FloatPermissionManager.getInstance().checkPermission(MantoLivePlayerV2.this.getContext())) {
                    MantoLivePlayerV2.this.g0();
                } else if (MantoLivePlayerV2.this.F != null) {
                    MantoLivePlayerV2.this.F.clear();
                }
                AppLifeCycle.remove(MantoLivePlayerV2.this.L, this);
                MantoLivePlayerV2.this.N = false;
            }
        }
    }

    public MantoLivePlayerV2(Activity activity) {
        super(activity);
        this.r = MantoLivePlayerV2.class.getName();
        this.D = true;
        this.E = true;
        this.I = false;
        this.J = false;
        this.K = false;
        this.O = new a();
        this.P = new b();
        this.Q = false;
        this.R = false;
        this.S = new f();
        d0(activity);
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(true);
        this.B = playerOptions;
        playerOptions.setCouldMediaCodec(true);
        this.B.setEnableAccurateSeek(true);
        this.B.setEnableReport(false);
        this.B.showControllerOnStart(false);
        this.B.setReconnectCount(5);
        this.B.setPlayTypeId("78");
        D(this.O);
        v(this.P);
    }

    private int c0(String str) {
        return (!"contain".equals(str) && "fillCrop".equals(str)) ? 1 : 0;
    }

    private void d0(Activity activity) {
        if (activity == null) {
            return;
        }
        com.jd.manto.sdkimpl.live.v2.a aVar = new com.jd.manto.sdkimpl.live.v2.a(activity, this);
        this.v = aVar;
        aVar.j(new c());
    }

    private void e0() {
        if (!isPlaying()) {
            c.d dVar = this.F;
            if (dVar != null) {
                dVar.clear();
            }
            this.K = false;
        } else if (this.R) {
        } else {
            if (FloatPermissionManager.getInstance().checkPermission(getContext())) {
                g0();
                return;
            }
            FloatPermissionManager.getInstance().applyFloatWindow(getContext(), "\u4eac\u4e1c\u9700\u8981\u60a8\u6388\u6743\u60ac\u6d6e\u7a97\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u5728\u4eac\u4e1c\u8bbf\u95ee\u671f\u95f4\u80fd\u4f53\u9a8c\u66f4\u4f18\u7684\u89c6\u9891\u4f53\u9a8c", PermissionHelper.generateBundle("manto", "manto", "impl", true), new d());
        }
    }

    public void g0() {
        JDWindowPlayerManager jDWindowPlayerManager = new JDWindowPlayerManager();
        this.G = jDWindowPlayerManager;
        jDWindowPlayerManager.setOnPlayWindowCallback(new e());
        this.M = z(true);
        this.G.show(getContext(), WindowPlayerConfig.WindowType.JD_LIVE, this.M);
    }

    public void h0(boolean z) {
        float f2 = z ? 1.0f : 0.0f;
        this.B.setVolume(f2);
        J(f2);
    }

    @Override // com.jd.manto.d.b0.a
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.B.setPlayerReportInfoEntity(new PlayerReportInfoEntity("", this.L));
        E(this.B);
        this.t = str;
        if (this.w) {
            H(str);
            this.s = null;
        } else {
            I(str);
            this.s = str;
        }
        this.A = false;
    }

    @Override // com.jd.manto.d.b0.a
    public void b(String str) {
        this.B.setAspectRatio(c0(str));
    }

    @Override // com.jd.manto.d.b0.a
    public void c() {
        this.v.g();
    }

    @Override // com.jd.manto.d.b0.a
    public void d(String str) {
        this.y = str;
    }

    @Override // com.jd.manto.d.b0.a
    public synchronized void destroy() {
        JDWindowPlayerManager jDWindowPlayerManager = this.G;
        if (jDWindowPlayerManager != null && this.R) {
            this.Q = false;
            jDWindowPlayerManager.close();
        }
        c.d dVar = this.F;
        if (dVar != null) {
            dVar.clear();
        }
        f0();
        com.jd.manto.sdkimpl.live.v2.a aVar = this.v;
        if (aVar != null) {
            aVar.g();
            this.v = null;
        }
        B();
    }

    @Override // com.jd.manto.d.b0.a
    public void e(int i2) {
        this.z = i2;
    }

    @Override // com.jd.manto.d.b0.a
    public void f() {
        if (this.u) {
            return;
        }
        this.v.f();
    }

    public void f0() {
        com.jd.manto.sdkimpl.live.v2.a aVar = this.v;
        if (aVar != null) {
            aVar.h();
        }
    }

    @Override // com.jd.manto.d.b0.a
    public void g(ILiveInterface iLiveInterface) {
        this.x = iLiveInterface;
    }

    @Override // com.jd.manto.d.b0.a
    public String getData() {
        return this.y;
    }

    @Override // com.jd.manto.d.b0.a
    public void h(boolean z) {
        this.D = z;
        h0(z);
        this.B.setIsRequestAudioFocus(true);
    }

    @Override // com.jd.manto.d.b0.a
    public void i() {
        if (this.I) {
            this.Q = false;
            e0();
        }
    }

    @Override // com.jd.manto.d.b0.a
    public void j(boolean z, boolean z2) {
        this.I = z;
        this.J = z2;
    }

    @Override // com.jd.manto.d.b0.a
    public void k(String str) {
        this.H = str;
    }

    @Override // com.jd.manto.d.b0.a
    public void l() {
        if (this.J) {
            e0();
            this.K = true;
            return;
        }
        this.E = this.D;
        this.D = false;
        h0(false);
    }

    @Override // com.jd.manto.d.b0.a
    public void m(c.d dVar) {
        this.F = dVar;
    }

    @Override // com.jd.manto.d.b0.a
    public void n() {
        JDWindowPlayerManager jDWindowPlayerManager = this.G;
        if (jDWindowPlayerManager != null && this.R) {
            this.Q = true;
            jDWindowPlayerManager.close();
        }
        boolean z = this.E;
        if (z != this.D) {
            this.D = z;
            h0(true);
        }
    }

    @Override // com.jd.manto.d.b0.a
    public void pauseIfPlaying() {
        if (isPlaying()) {
            pause();
        }
    }

    @Override // com.jd.manto.d.b0.a
    public void playIfNotPlaying() {
        if (isPlaying()) {
            return;
        }
        if (!TextUtils.isEmpty(this.s)) {
            H(this.s);
            this.s = null;
        } else if (!this.C && !this.A) {
            start();
        } else {
            resume();
            this.C = false;
        }
        this.A = false;
    }

    @Override // com.jd.manto.sdkimpl.live.v2.MantoLiveVideoView, com.jd.manto.d.b0.a
    public void resume() {
        if (this.A) {
            playIfNotPlaying();
        }
        super.resume();
    }

    @Override // com.jd.manto.d.b0.a
    public void setAppId(String str) {
        this.L = str;
    }

    @Override // com.jd.manto.d.b0.a
    public void setAutoPlay(boolean z) {
        this.w = z;
    }

    @Override // com.jd.manto.d.b0.a
    public void setOrientation(int i2) {
        com.jd.manto.sdkimpl.live.v2.a aVar = this.v;
        if (aVar != null) {
            aVar.i(i2);
        }
    }

    @Override // com.jd.manto.d.b0.a
    public synchronized void stop() {
        this.A = true;
        this.s = this.t;
        super.M();
    }
}
