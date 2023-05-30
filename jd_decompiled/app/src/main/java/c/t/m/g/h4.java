package c.t.m.g;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;

/* loaded from: classes.dex */
public final class h4 {
    public volatile boolean a;

    /* renamed from: c  reason: collision with root package name */
    public final y4 f467c;

    /* renamed from: f  reason: collision with root package name */
    public HandlerThread f469f;

    /* renamed from: g  reason: collision with root package name */
    public b f470g;

    /* renamed from: h  reason: collision with root package name */
    public c f471h;

    /* renamed from: i  reason: collision with root package name */
    public SignalStrength f472i;
    public final byte[] b = new byte[0];
    public f d = null;

    /* renamed from: e  reason: collision with root package name */
    public ServiceState f468e = null;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h4.this.f471h = new c();
        }
    }

    /* loaded from: classes.dex */
    public final class b extends Handler {
        public volatile boolean a;

        public b(Looper looper) {
            super(looper);
            this.a = false;
            this.a = false;
        }

        public /* synthetic */ b(h4 h4Var, Looper looper, a aVar) {
            this(looper);
        }

        public final void a() {
            this.a = true;
        }

        @Override // android.os.Handler
        @SuppressLint({"NewApi", "MissingPermission"})
        public final void handleMessage(Message message) {
            if (h4.this.a) {
                if ((h4.this.d == null || !h4.this.d.k(3000L)) && h4.this.f467c.l() != null) {
                    f g2 = f.g(h4.this.f467c, s5.b(h4.this.f467c));
                    if (!g2.q()) {
                        g2 = f.f(h4.this.f467c, f.p, h4.this.f472i);
                        if (g2 != null) {
                            g2.q();
                        }
                    }
                    h4.this.h(g2, 2);
                }
                synchronized (h4.this.b) {
                    if (h4.this.f470g != null && !this.a) {
                        t.d(h4.this.f470g, 0, Final.HALF_MINUTE);
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public final class c extends PhoneStateListener {
        public c() {
            b(R2.attr.listPreferredItemPaddingLeft);
        }

        public final void a() {
            b(0);
        }

        public final void b(int i2) {
            try {
                h4.this.f467c.l().listen(this, i2);
                o4.o("cell", "lCS");
            } catch (Throwable th) {
                StringBuilder sb = new StringBuilder("listenCellState: failed! flags=");
                sb.append(i2);
                sb.append(th.toString());
            }
        }

        @Override // android.telephony.PhoneStateListener
        @SuppressLint({"NewApi"})
        public final void onCellInfoChanged(List<CellInfo> list) {
            if (list != null) {
                list.size();
            }
            h4.this.h(f.g(h4.this.f467c, list), 0);
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellLocationChanged(CellLocation cellLocation) {
            new StringBuilder("CellLocationThreadName = ").append(Thread.currentThread().getName());
            super.onCellLocationChanged(cellLocation);
            h4.this.h(f.f(h4.this.f467c, cellLocation, h4.this.f472i), 1);
        }

        @Override // android.telephony.PhoneStateListener
        public final void onServiceStateChanged(ServiceState serviceState) {
            super.onServiceStateChanged(serviceState);
            if (serviceState == null) {
                return;
            }
            try {
                ServiceState serviceState2 = h4.this.f468e;
                if (serviceState2 == null || serviceState2.getState() != serviceState.getState()) {
                    h4.this.f468e = serviceState;
                    h4.this.e();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            h4.this.f472i = signalStrength;
        }
    }

    public h4(y4 y4Var) {
        this.f467c = y4Var;
        u0.b = 0L;
    }

    public final void e() {
        int i2;
        boolean g2;
        if (this.a) {
            ServiceState serviceState = this.f468e;
            int i3 = 0;
            if (serviceState != null) {
                if (serviceState.getState() == 0) {
                    i2 = 1;
                } else if (this.f468e.getState() == 1) {
                    i2 = 0;
                }
                TelephonyManager l2 = this.f467c.l();
                g2 = s5.g(this.f467c.a);
                boolean z = l2 == null && l2.getSimState() == 5;
                if (!g2 && z) {
                    i3 = i2;
                }
                Message message = new Message();
                message.what = R2.id.decode_failed;
                message.arg1 = R2.drawable.x_dialog_bg2;
                message.arg2 = i3;
                this.f467c.f(message);
            }
            i2 = -1;
            TelephonyManager l22 = this.f467c.l();
            g2 = s5.g(this.f467c.a);
            if (l22 == null) {
            }
            if (!g2) {
                i3 = i2;
            }
            Message message2 = new Message();
            message2.what = R2.id.decode_failed;
            message2.arg1 = R2.drawable.x_dialog_bg2;
            message2.arg2 = i3;
            this.f467c.f(message2);
        }
    }

    @SuppressLint({"NewApi"})
    public final void f(Handler handler, boolean z) {
        if (this.a) {
            return;
        }
        a aVar = null;
        f.i(null, 0L);
        u0.b = 0L;
        HandlerThread handlerThread = new HandlerThread("new_cell_provider");
        this.f469f = handlerThread;
        if (handler != null) {
            try {
                handlerThread.start();
                this.f470g = new b(this, this.f469f.getLooper(), aVar);
            } catch (Throwable unused) {
                this.f470g = new b(this, handler.getLooper(), aVar);
            }
            this.a = true;
            if (!z) {
                t.k(this.f470g, 0);
            }
            this.f470g.postDelayed(new a(), 1000L);
        }
    }

    public final void g(f fVar) {
        if (!this.a || fVar == null || this.f467c == null) {
            return;
        }
        synchronized (this) {
            f fVar2 = this.d;
            if (fVar2 != null) {
                fVar.j(fVar2.n());
            }
            this.d = fVar;
            new StringBuilder("notify cell:").append(fVar.toString());
            this.f467c.f(fVar);
        }
    }

    public final void h(f fVar, int i2) {
        List<String> list;
        List<String> list2;
        List<String> list3;
        if (this.d == null && fVar != null && fVar.q()) {
            StringBuilder sb = new StringBuilder("First! src:");
            sb.append(i2);
            sb.append(",info:");
            sb.append(fVar.toString());
            g(fVar);
            return;
        }
        f fVar2 = this.d;
        if (i2 == 0) {
            if (fVar == null || !fVar.q()) {
                return;
            }
            new StringBuilder("onCellInfoChanged").append(fVar.toString());
            if (fVar2 == null || (list = fVar2.f413m) == null || !list.containsAll(fVar.f413m)) {
                o4.o("CELL", "src=0,info=" + fVar.r());
                g(fVar);
                return;
            }
            StringBuilder sb2 = new StringBuilder("onCellInfoChanged Tencentcell size");
            sb2.append(fVar2.f413m.size());
            sb2.append("same + TencentCell:");
            sb2.append(fVar2.toString());
        } else if (i2 == 1) {
            if (fVar == null || !fVar.q()) {
                return;
            }
            new StringBuilder("onCellLocationChanged").append(fVar.toString());
            if (fVar2 != null && (list2 = fVar2.f413m) != null && list2.contains(fVar.l())) {
                new StringBuilder("mTencentCellInfo:contains cell location").append(fVar2.toString());
                return;
            }
            o4.o("CELL", "src=1,info=" + fVar.r());
            g(fVar);
        } else if (i2 == 2 && fVar != null && fVar.q()) {
            new StringBuilder("timer callback").append(fVar.toString());
            if (fVar2 == null || (list3 = fVar2.f413m) == null || !list3.containsAll(fVar.f413m)) {
                o4.o("CELL", "src=2,info=" + fVar.r());
                g(fVar);
                return;
            }
            StringBuilder sb3 = new StringBuilder("timer callback Tencentcell size");
            sb3.append(fVar2.f413m.size());
            sb3.append("same + TencentCell:");
            sb3.append(fVar2.toString());
        }
    }

    public final void k() {
        if (this.a) {
            this.a = false;
            u0.b = 0L;
            synchronized (this.b) {
                c cVar = this.f471h;
                if (cVar != null) {
                    cVar.a();
                }
                b bVar = this.f470g;
                if (bVar != null) {
                    bVar.a();
                    this.f470g.removeCallbacksAndMessages(null);
                    this.f470g = null;
                }
                HandlerThread handlerThread = this.f469f;
                if (handlerThread != null) {
                    handlerThread.quit();
                    this.f469f = null;
                }
                this.d = null;
                this.f468e = null;
                this.f471h = null;
                this.f472i = null;
                f.i(null, 0L);
            }
        }
    }
}
