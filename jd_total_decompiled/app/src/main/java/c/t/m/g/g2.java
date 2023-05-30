package c.t.m.g;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public final class g2 extends PhoneStateListener {
    public volatile boolean a;
    public final y4 b;

    /* renamed from: c  reason: collision with root package name */
    public byte[] f434c = new byte[0];
    public CellLocation d = null;

    /* renamed from: e  reason: collision with root package name */
    public SignalStrength f435e = null;

    /* renamed from: f  reason: collision with root package name */
    public ServiceState f436f = null;

    /* renamed from: g  reason: collision with root package name */
    public long f437g;

    /* renamed from: h  reason: collision with root package name */
    public HandlerThread f438h;

    /* renamed from: i  reason: collision with root package name */
    public b f439i;

    /* loaded from: classes.dex */
    public final class b extends Handler {
        public volatile boolean a;

        public b(Looper looper) {
            super(looper);
            this.a = false;
            this.a = false;
        }

        public final void a() {
            this.a = true;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (g2.this.a) {
                synchronized (g2.this.f434c) {
                    if (g2.this.f439i != null && !this.a) {
                        sendEmptyMessageDelayed(0, Final.HALF_MINUTE);
                    }
                }
                g2.this.l(s5.k(g2.this.b));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public y4 f440g;

        /* renamed from: h  reason: collision with root package name */
        public f f441h;

        public c(y4 y4Var) {
            this.f440g = y4Var;
        }

        public void a(f fVar) {
            this.f441h = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            y4 y4Var = this.f440g;
            f fVar = this.f441h;
            if (fVar != null) {
                y4Var.f(fVar);
            }
        }
    }

    public g2(y4 y4Var) {
        this.b = y4Var;
    }

    public final void a() {
        this.d = null;
        this.f435e = null;
        this.f436f = null;
    }

    public final void b(int i2) {
        try {
            this.b.l().listen(this, i2);
        } catch (Exception unused) {
        }
    }

    public final void c(Handler handler) {
        f f2;
        if (this.a) {
            return;
        }
        this.a = true;
        h();
        CellLocation k2 = s5.k(this.b);
        if (e(k2) && (f2 = f.f(this.b, k2, null)) != null) {
            this.d = k2;
            this.b.f(f2);
        }
        b(273);
    }

    public final boolean e(CellLocation cellLocation) {
        if (cellLocation == null) {
            return false;
        }
        try {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            if (gsmCellLocation.getCid() == 0) {
                if (gsmCellLocation.getLac() == 0) {
                    return false;
                }
            }
        } catch (ClassCastException unused) {
        }
        return s5.a(cellLocation) >= 0 && !s5.h(this.d, cellLocation) && i(cellLocation);
    }

    public final void h() {
        synchronized (this.f434c) {
            HandlerThread handlerThread = new HandlerThread("CellProvider");
            this.f438h = handlerThread;
            handlerThread.start();
            b bVar = new b(this.f438h.getLooper());
            this.f439i = bVar;
            bVar.sendEmptyMessageDelayed(0, 3000L);
        }
    }

    public final boolean i(CellLocation cellLocation) {
        f f2 = f.f(this.b, cellLocation, null);
        if (f2 == null) {
            return true;
        }
        return s5.i(f2);
    }

    public final void k() {
        if (this.a && this.d != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f437g > 2000) {
                this.f437g = currentTimeMillis;
                m();
            }
        }
    }

    public final void l(CellLocation cellLocation) {
        onCellLocationChanged(cellLocation);
    }

    public final void m() {
        f f2 = f.f(this.b, this.d, this.f435e);
        synchronized (this.f434c) {
            if (this.f439i != null && f2 != null) {
                c cVar = new c(this.b);
                cVar.a(f2);
                this.f439i.post(cVar);
            }
        }
    }

    public final void n() {
        int i2;
        boolean g2;
        if (this.a) {
            ServiceState serviceState = this.f436f;
            int i3 = 0;
            if (serviceState != null) {
                if (serviceState.getState() == 0) {
                    i2 = 1;
                } else if (this.f436f.getState() == 1) {
                    i2 = 0;
                }
                TelephonyManager l2 = this.b.l();
                g2 = s5.g(this.b.a);
                boolean z = l2 == null && l2.getSimState() == 5;
                if (!g2 && z) {
                    i3 = i2;
                }
                Message message = new Message();
                message.what = R2.id.decode_failed;
                message.arg1 = R2.drawable.x_dialog_bg2;
                message.arg2 = i3;
                this.b.f(message);
            }
            i2 = -1;
            TelephonyManager l22 = this.b.l();
            g2 = s5.g(this.b.a);
            if (l22 == null) {
            }
            if (!g2) {
                i3 = i2;
            }
            Message message2 = new Message();
            message2.what = R2.id.decode_failed;
            message2.arg1 = R2.drawable.x_dialog_bg2;
            message2.arg2 = i3;
            this.b.f(message2);
        }
    }

    public final void o() {
        if (this.a) {
            this.a = false;
            b(0);
            synchronized (this.f434c) {
                b bVar = this.f439i;
                if (bVar != null) {
                    bVar.a();
                    this.f439i.removeCallbacksAndMessages(null);
                    this.f439i = null;
                }
                HandlerThread handlerThread = this.f438h;
                if (handlerThread != null) {
                    handlerThread.quit();
                    this.f438h = null;
                }
                a();
                this.f437g = 0L;
            }
        }
    }

    @Override // android.telephony.PhoneStateListener
    public final void onCellLocationChanged(CellLocation cellLocation) {
        super.onCellLocationChanged(cellLocation);
        if (!e(cellLocation)) {
            new StringBuilder("onCellLocationChanged: illegal cell or same cell ").append(cellLocation);
            return;
        }
        this.d = cellLocation;
        k();
    }

    @Override // android.telephony.PhoneStateListener
    public final void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        if (serviceState == null) {
            return;
        }
        ServiceState serviceState2 = this.f436f;
        if (serviceState2 == null || serviceState2.getState() != serviceState.getState()) {
            this.f436f = serviceState;
            n();
        }
    }

    @Override // android.telephony.PhoneStateListener
    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        if (signalStrength == null) {
            return;
        }
        try {
            SignalStrength signalStrength2 = this.f435e;
            int G = this.b.c().G();
            if (signalStrength2 == null || s5.f(G, signalStrength2, signalStrength)) {
                this.f435e = signalStrength;
                k();
            }
        } catch (Exception unused) {
        }
    }
}
