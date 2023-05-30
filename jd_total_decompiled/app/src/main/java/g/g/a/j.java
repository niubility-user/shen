package g.g.a;

import android.content.Context;
import android.content.IntentFilter;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jdai.tts.NetUtiles.NetWorkChangReceiver;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes18.dex */
public class j {
    private static Long q = 0L;
    private Context a;

    /* renamed from: c  reason: collision with root package name */
    private m f19619c;
    private h d;

    /* renamed from: e  reason: collision with root package name */
    private g f19620e;

    /* renamed from: f  reason: collision with root package name */
    private NetWorkChangReceiver f19621f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f19622g;
    private k b = null;

    /* renamed from: h  reason: collision with root package name */
    private boolean f19623h = false;

    /* renamed from: i  reason: collision with root package name */
    private g.g.a.o.c f19624i = null;

    /* renamed from: j  reason: collision with root package name */
    private boolean f19625j = false;

    /* renamed from: k  reason: collision with root package name */
    private g.g.a.o.b f19626k = g.g.a.o.b.Idle;

    /* renamed from: l  reason: collision with root package name */
    private g.g.a.a.a f19627l = null;

    /* renamed from: m  reason: collision with root package name */
    private int f19628m = R2.id.rn_redbox_report_label;

    /* renamed from: n  reason: collision with root package name */
    private boolean f19629n = false;
    d o = new d();
    d p = new d();

    /* loaded from: classes18.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[m.values().length];
            a = iArr;
            try {
                iArr[m.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[m.MIX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[m.OFFLINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes18.dex */
    class b implements com.jdai.tts.NetUtiles.b {
        b(j jVar) {
        }
    }

    /* loaded from: classes18.dex */
    class c implements g.g.a.o.d {
        c() {
        }

        @Override // g.g.a.o.d
        public void a(String str) {
            j.this.b.onPlayResume(str);
        }

        @Override // g.g.a.o.d
        public void b(String str) {
            j.this.b.onPlayPause(str);
        }

        @Override // g.g.a.o.d
        public void c(String str) {
            j.this.b.onPlayStart(str);
        }

        @Override // g.g.a.o.d
        public void d(String str, double d) {
            j.this.b.onPlayProgressChanged(str, d);
        }

        @Override // g.g.a.o.d
        public void e(String str) {
        }

        @Override // g.g.a.o.d
        public void f(String str) {
            j.this.b.onPlayFinish(str);
        }

        @Override // g.g.a.o.d
        public void onError(String str, l lVar) {
            j.this.b.onError(str, lVar);
        }
    }

    /* loaded from: classes18.dex */
    class d implements i {
        d() {
        }

        @Override // g.g.a.i
        public int a(String str, byte[] bArr, int i2, int i3, double d, String str2, l lVar) {
            f.c("TTSEngineMix", "onRecvData txtID=" + str + ", reqIndex=" + i2 + ", retIndex=" + i3 + ", audioPCM len=" + bArr.length + ", process=" + d + ", err=" + lVar);
            if (i3 == 1 && lVar == l.OK_NO) {
                j.this.b.onSynthesizeFirstPackage(str);
            }
            g.g.a.b bVar = new g.g.a.b();
            bVar.l(str);
            bVar.g(lVar);
            bVar.k(i2);
            bVar.i(bArr);
            bVar.h(i3);
            bVar.j(d);
            if (!j.this.f19625j) {
                if (j.this.f19626k != g.g.a.o.b.Stop) {
                    j.this.b.onSynthesizeDataArrived(str, bArr, i3, d, str2);
                }
            } else {
                f.c("TTSEngineMix", "onRecvData PlayerSatus=" + j.this.f19626k);
                if (j.this.f19626k != g.g.a.o.b.Stop) {
                    f.c("TTSEngineMix", "onRecvData txtID=" + str);
                    j.this.f19624i.f(bVar);
                }
            }
            if (i3 >= 0 || lVar != l.OK_NO) {
                return 0;
            }
            j.this.b.onSynthesizeFinish(str);
            return 0;
        }

        @Override // g.g.a.i
        public int onEnd(String str) {
            return 0;
        }

        @Override // g.g.a.i
        public void onError(String str, l lVar) {
            j.this.b.onError(str, lVar);
        }

        @Override // g.g.a.i
        public int onStart(String str) {
            j.this.b.onSynthesizeStart(str);
            return 0;
        }

        @Override // g.g.a.i
        public void onTry(String str, l lVar) {
            j.this.b.onTry(str, lVar);
        }
    }

    public j(Context context, m mVar) {
        this.f19619c = null;
        this.d = null;
        this.f19620e = null;
        this.f19622g = false;
        new LinkedBlockingQueue();
        new LinkedBlockingQueue();
        this.a = context;
        this.f19619c = mVar;
        if (mVar == m.ONLINE) {
            h hVar = this.d;
            if (hVar == null) {
                h hVar2 = new h(context);
                this.d = hVar2;
                hVar2.g(q);
            } else {
                hVar.e();
                this.d = null;
                this.d = new h(context);
            }
        }
        if (mVar == m.OFFLINE) {
            g gVar = this.f19620e;
            if (gVar == null) {
                this.f19620e = new g(context);
            } else {
                gVar.b();
                this.f19620e = null;
                this.f19620e = new g(context);
            }
        }
        if (mVar == m.MIX) {
            h hVar3 = this.d;
            if (hVar3 == null) {
                h hVar4 = new h(context);
                this.d = hVar4;
                hVar4.g(q);
            } else {
                hVar3.e();
                this.d = null;
                this.d = new h(context);
            }
            g gVar2 = this.f19620e;
            if (gVar2 == null) {
                this.f19620e = new g(context);
            } else {
                gVar2.b();
                this.f19620e = null;
                this.f19620e = new g(context);
            }
        }
        NetWorkChangReceiver netWorkChangReceiver = new NetWorkChangReceiver(context);
        this.f19621f = netWorkChangReceiver;
        netWorkChangReceiver.a(new b(this));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.a.registerReceiver(this.f19621f, intentFilter);
        this.f19622g = com.jdai.tts.NetUtiles.a.b(context);
        f.c("TTSEngineMix", "isNetConnect: " + this.f19622g);
    }

    public int e() {
        h hVar = this.d;
        if (hVar != null) {
            hVar.k();
            this.d.e();
            this.d = null;
        }
        g gVar = this.f19620e;
        if (gVar != null) {
            gVar.h();
            this.f19620e.b();
            this.d = null;
        }
        g.g.a.o.c cVar = this.f19624i;
        if (cVar != null) {
            cVar.g();
            return 0;
        }
        return 0;
    }

    public synchronized int f() {
        f.c("TTSEngineMix", "pause: ");
        this.f19626k = g.g.a.o.b.Pause;
        this.f19624i.i();
        return 0;
    }

    public synchronized int g() {
        f.c("TTSEngineMix", "resume: ");
        this.f19626k = g.g.a.o.b.Start;
        this.f19624i.k();
        return 0;
    }

    public void h(n nVar) {
        f.c("TTSEngineMix", "setParam: " + nVar.toString());
        int i2 = a.a[this.f19619c.ordinal()];
        if (i2 == 1) {
            this.d.h(nVar);
        } else if (i2 == 2) {
            this.d.h(nVar);
            this.f19620e.e(nVar);
        } else if (i2 == 3) {
            this.f19620e.e(nVar);
        }
        int intValue = Integer.valueOf(nVar.a("sr")).intValue();
        f.c("TTSEngineMix", "sr=" + intValue + ", sampleRate=" + this.f19628m + ", isPlayerInit=" + this.f19629n);
        if (intValue != this.f19628m || !this.f19629n) {
            this.f19628m = intValue;
            this.f19629n = true;
            g.g.a.o.c cVar = this.f19624i;
            if (cVar != null) {
                cVar.m();
                this.f19624i.g();
                this.f19624i = null;
            }
            f.c("TTSEngineMix", "new TTSBufPlayer");
            g.g.a.o.c cVar2 = new g.g.a.o.c(this.a, this.f19628m, 2, 4);
            this.f19624i = cVar2;
            cVar2.a(Integer.valueOf(nVar.a("playCacheNum")).intValue());
            this.f19624i.b("0", new c());
        }
        if (this.f19627l == null) {
            m mVar = this.f19619c;
            if (mVar == m.OFFLINE || mVar == m.MIX) {
                this.f19627l = new g.g.a.a.a(this.a, nVar.a(PairKey.APP_KEY));
            }
        }
    }

    public void i(k kVar) {
        this.b = kVar;
        h hVar = this.d;
        if (hVar != null) {
            hVar.i(this.o);
        }
        g gVar = this.f19620e;
        if (gVar != null) {
            gVar.f(this.p);
        }
    }

    public synchronized String j(String str) {
        String i2;
        if (str != null) {
            if (!str.isEmpty()) {
                m mVar = this.f19619c;
                m mVar2 = m.ONLINE;
                if ((mVar == mVar2 && str.length() <= 1024) || (this.f19619c == m.OFFLINE && str.length() <= 300)) {
                    this.f19625j = true;
                    this.f19626k = g.g.a.o.b.Start;
                    m mVar3 = this.f19619c;
                    if (mVar3 == mVar2) {
                        f.a("TTSEngineMix", "synthesize onLineEngine");
                        i2 = this.d.l(str);
                    } else if (mVar3 == m.OFFLINE) {
                        f.a("TTSEngineMix", "synthesize offLineEngine0");
                        i2 = this.f19620e.i(str);
                    } else if (this.f19623h) {
                        f.a("TTSEngineMix", "synthesize offLineEngine1");
                        i2 = this.d.l(str);
                    } else {
                        f.a("TTSEngineMix", "synthesize offLineEngine2");
                        i2 = this.f19620e.i(str);
                    }
                    return i2;
                }
                f.c("TTSEngineMix", "txt too long=" + str.length());
                return null;
            }
        }
        f.c("TTSEngineMix", "speak: txt err");
        this.b.onError("0", l.ERR_TEXTEMPTY_NO);
        return null;
    }

    public synchronized int k() {
        f.c("TTSEngineMix", "stop: ");
        this.f19626k = g.g.a.o.b.Stop;
        if (this.d != null) {
            f.c("TTSEngineMix", "onLineEngine stop:");
            this.d.k();
        }
        if (this.f19620e != null) {
            f.c("TTSEngineMix", "offLineEngine stop:");
            this.f19620e.h();
            f.c("TTSEngineMix", "offLineEngine stop2:");
        }
        this.f19624i.m();
        return 0;
    }
}
