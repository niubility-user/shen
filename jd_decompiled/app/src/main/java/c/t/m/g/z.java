package c.t.m.g;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class z extends s {

    /* renamed from: n */
    public o0 f796n;
    public i o;

    /* renamed from: k */
    public long f793k = Final.FIVE_SECOND;
    public CopyOnWriteArrayList<c4> p = new CopyOnWriteArrayList<>();

    /* renamed from: l */
    public g0 f794l = new g0();

    /* renamed from: m */
    public q f795m = new q();

    public z() {
        z();
    }

    public static int o(Context context) {
        SensorManager sensorManager;
        try {
            sensorManager = (SensorManager) context.getSystemService("sensor");
        } catch (Throwable unused) {
        }
        if (sensorManager == null) {
            return 2;
        }
        boolean z = true;
        Sensor defaultSensor = sensorManager.getDefaultSensor(1);
        Sensor defaultSensor2 = sensorManager.getDefaultSensor(4);
        boolean z2 = (defaultSensor == null || defaultSensor.getName().toLowerCase(Locale.ENGLISH).contains("pseudo")) ? false : true;
        if (defaultSensor2 == null || defaultSensor2.getName().toLowerCase(Locale.ENGLISH).contains("pseudo")) {
            z = false;
        }
        if (z2 && z) {
            if (defaultSensor.getMinDelay() <= 43478) {
                return defaultSensor2.getMinDelay() <= 43478 ? 0 : 3;
            }
            return 3;
        }
        return 2;
    }

    public static int x(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return 1;
        }
        int o = o(context);
        if (o != 0) {
            return o;
        }
        return 0;
    }

    @Override // c.t.m.g.q0
    public int a(Looper looper) {
        try {
            Looper looper2 = l() == null ? null : l().getLooper();
            if (looper2 == null) {
                return -1;
            }
            i iVar = new i();
            this.o = iVar;
            iVar.a(3, 25, 0.8f, c2.a, c2.b, j6.d, j6.f503e);
            q(this.f794l, looper2);
            q(this.f795m, looper2);
            q(this.f796n, looper2);
            g(1001, 2000L);
            t1.a("ArMgrImpl", "ar listeners size = " + this.p.size());
            return 0;
        } catch (Throwable th) {
            t1.b("ArMgrImpl", "startupSubPro error.", th);
            return th instanceof UnsatisfiedLinkError ? 5 : -1;
        }
    }

    @Override // c.t.m.g.q0
    public String b() {
        return "ArMgrImpl";
    }

    @Override // c.t.m.g.q0
    public void d() {
        t(this.f794l, this.f795m, this.f796n);
        i iVar = this.o;
        if (iVar != null) {
            iVar.h();
        }
        this.o = null;
        t1.a("ArMgrImpl", "status : [shutdown]");
    }

    @Override // c.t.m.g.s
    public void f(Message message) {
        if (k() != null && message.what == 1001) {
            g(1001, this.f793k);
            double[] e2 = this.o.e(System.currentTimeMillis());
            if (e2 != null) {
                e1 e1Var = new e1();
                e1Var.f(e2);
                e1Var.g(this.o.d());
                r(e1Var);
            }
        }
    }

    @Override // c.t.m.g.s
    public int n() {
        int x = c() ? 4 : x(y3.a());
        if (x == 0) {
            x = super.n();
            if (x < 0) {
                x = 100;
            }
            if (x != 0) {
                super.e(200L);
            }
        }
        t1.a("ArMgrImpl", "startup : " + x + ", ar : " + v3.b() + ", common lib: " + w5.a());
        return x;
    }

    public final long p(long j2, long j3, long j4) {
        return Math.max(j3, Math.min(j2, j4));
    }

    public final void q(b0 b0Var, Looper looper) {
        if (b0Var != null) {
            b0Var.g(looper);
        }
    }

    public final void r(n3 n3Var) {
        Iterator<c4> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().a(n3Var);
        }
    }

    public void s(c4 c4Var) {
        if (this.p.contains(c4Var)) {
            return;
        }
        this.p.add(c4Var);
        t1.a("ArMgrImpl", "addArListener:" + c4Var.getClass().getSimpleName() + DYConstants.DY_REGEX_AT + Integer.toHexString(c4Var.hashCode()));
    }

    public final void t(b0... b0VarArr) {
        for (b0 b0Var : b0VarArr) {
            if (b0Var != null) {
                b0Var.c();
            }
        }
    }

    public boolean u(String str, String str2) {
        try {
            t1.a("ArMgrImpl", "setArData:" + str + DYConstants.DY_REGEX_COMMA + str2);
            if ("set_is_d".equals(str)) {
                l1.a = Integer.parseInt(str2) == 1;
            } else if ("set_ar_detect_cycle".equals(str)) {
                this.f793k = p(Long.parseLong(str2), 1000L, 15000L);
            } else if ("set_ar_model_tran_p".equals(str)) {
                return w(c2.a, str2);
            } else {
                if ("set_ar_speed_model".equals(str)) {
                    return w(c2.b, str2);
                }
                if ("set_ar_svm_coefs".equals(str)) {
                    return w(j6.d, str2);
                }
                if ("set_ar_svm_bias".equals(str)) {
                    return v(j6.f503e, str2);
                }
                if ("set_ar_lr_coefs".equals(str)) {
                    return w(u5.a, str2);
                }
                if ("set_ar_lr_bias".equals(str)) {
                    return v(u5.b, str2);
                }
                if ("set_ar_open_available_checker".equals(str)) {
                    k4.f527i = Boolean.parseBoolean(str2);
                } else if (!"set_ar_register_gps_type".equals(str)) {
                    return false;
                } else {
                    this.f795m.k(Integer.parseInt(str2));
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public final boolean v(double[] dArr, String str) {
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        if (split.length != dArr.length) {
            return false;
        }
        double[] c2 = g1.a().c(dArr.length);
        for (int i2 = 0; i2 < c2.length; i2++) {
            c2[i2] = Double.parseDouble(split[i2]);
        }
        System.arraycopy(c2, 0, dArr, 0, dArr.length);
        g1.a().b(c2);
        return true;
    }

    public final boolean w(double[][] dArr, String str) {
        String[] split = str.split(";");
        if (split.length != dArr.length) {
            return false;
        }
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, dArr.length, dArr[0].length);
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split(DYConstants.DY_REGEX_COMMA);
            if (split2.length != dArr[i2].length) {
                return false;
            }
            for (int i3 = 0; i3 < split2.length; i3++) {
                dArr2[i2][i3] = Double.parseDouble(split2[i3]);
            }
        }
        c.d(dArr, dArr2);
        return true;
    }

    public void y(c4 c4Var) {
        String str;
        if (c4Var == null) {
            this.p.clear();
            str = "removeArListener:clear all";
        } else {
            this.p.remove(c4Var);
            str = "removeArListener:" + c4Var.getClass().getSimpleName() + DYConstants.DY_REGEX_AT + Integer.toHexString(c4Var.hashCode());
        }
        t1.a("ArMgrImpl", str);
    }

    public final void z() {
        t1.a("ArMgrImpl", "set ar default settings.");
        for (Map.Entry<String, String> entry : c2.a().entrySet()) {
            u(entry.getKey(), entry.getValue());
        }
    }
}
