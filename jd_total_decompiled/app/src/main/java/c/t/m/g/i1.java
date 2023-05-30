package c.t.m.g;

import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.location.GnssNavigationMessage;
import android.location.GnssStatus;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.tencentmap.lbssdk.service.RegTxGposListener;
import com.tencent.tencentmap.lbssdk.service.TxRtkSvr;
import java.io.File;

/* loaded from: classes.dex */
public class i1 {
    public b6 a;
    public q6 b;

    /* renamed from: c  reason: collision with root package name */
    public String f487c;
    public b d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f488e = false;

    /* loaded from: classes.dex */
    public class a implements t5 {
        public a(i1 i1Var) {
        }

        @Override // c.t.m.g.t5
        public void a(String str, String str2) {
        }
    }

    /* loaded from: classes.dex */
    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public final void a(Message message) {
            switch (message.what) {
                case 6001:
                    if (Build.VERSION.SDK_INT >= 26) {
                        i1.this.b.a((GnssStatus) message.obj);
                        return;
                    }
                    return;
                case 6002:
                    if (Build.VERSION.SDK_INT >= 24) {
                        i1.this.b.c((GnssNavigationMessage) message.obj);
                        return;
                    }
                    return;
                case 6003:
                    i1.this.b.a(message.arg1);
                    return;
                case 6004:
                    if (Build.VERSION.SDK_INT >= 24) {
                        i1.this.b.b((GnssMeasurementsEvent) message.obj);
                        return;
                    }
                    return;
                case 6005:
                    i1.this.b.b(message.arg1);
                    return;
                case 6006:
                    t0 t0Var = (t0) message.obj;
                    i1.this.b.h(t0Var.b(), t0Var.a());
                    return;
                case R2.dimen.dp_459 /* 6007 */:
                    Location location = (Location) message.obj;
                    i1.this.b.l(location);
                    if (i1.this.a.f324h && location.getProvider().equals("gps")) {
                        if (i1.this.a.b) {
                            i1.this.a.f322f = SystemClock.elapsedRealtimeNanos();
                            i1.this.a.f323g = i1.this.a.f322f - i1.this.a.f321e;
                            i1.this.b.g(i1.this.a.f323g);
                        }
                        i1.this.a.f324h = false;
                        return;
                    }
                    return;
                case R2.dimen.dp_46 /* 6008 */:
                    l0 l0Var = (l0) message.obj;
                    if (i1.this.a.b) {
                        i1.this.b.n(l0Var.b(), l0Var.c(), l0Var.a());
                        return;
                    }
                    return;
                case R2.dimen.dp_460 /* 6009 */:
                    if (i1.this.b != null) {
                        i1.this.b.f();
                    }
                    TxRtkSvr.jni_stop_txgpos();
                    return;
                default:
                    return;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            try {
                if (i1.this.f488e) {
                    a(message);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public i1(Context context) {
        this.a = null;
        this.b = null;
        if (j5.a) {
            try {
                j5.b(context, new File(context.getExternalFilesDir("data").getAbsolutePath() + "/s_l"));
                j5.c(new a(this));
            } catch (Throwable unused) {
            }
        }
        File externalFilesDir = context.getExternalFilesDir("dgnss");
        externalFilesDir.getClass();
        this.f487c = externalFilesDir.getAbsolutePath();
        this.b = new q6(context);
        this.a = new b6(context, this.b);
    }

    public static boolean d(Location location) {
        Bundle extras;
        String string;
        return (location == null || (extras = location.getExtras()) == null || (string = extras.getString("gnss_source")) == null || string.isEmpty() || !string.equals(TencentLocation.BEIDOU_PROVIDER)) ? false : true;
    }

    public static boolean f(q5 q5Var) {
        Bundle extra;
        String string;
        return (q5Var == null || (extra = q5Var.getExtra()) == null || (string = extra.getString("gnss_source")) == null || string.isEmpty() || !string.equals(TencentLocation.BEIDOU_PROVIDER)) ? false : true;
    }

    public void a() {
        if (this.f488e) {
            this.f488e = false;
            t.k(this.d, R2.dimen.dp_460);
        }
    }

    public void b(int i2, int i3, int i4, Object obj) {
        t.l(this.d, i2, i3, i4, obj);
    }

    public void c(com.tencent.tencentmap.lbssdk.service.b bVar, Looper looper) {
        if (this.f488e) {
            return;
        }
        this.f488e = true;
        this.d = new b(looper);
        this.b.D();
        TxRtkSvr.jni_set_ntrip_mode(1);
        RegTxGposListener.registTxGposListener(bVar);
        g();
    }

    public final int g() {
        if (j5.a) {
            TxRtkSvr.jni_settrace_path(3, this.f487c + "/txgpos_%Y_%m_%d_%h_%M_%S.trace");
            TxRtkSvr.jni_setlogger_path(this.f487c + "/txgpos_%Y_%m_%d_%h_%M_%S.log");
            TxRtkSvr.jni_setsol_path(this.f487c + "/txgpos_%Y_%m_%d_%h_%M_%S.sol");
        }
        return TxRtkSvr.jni_init_txgpos();
    }
}
