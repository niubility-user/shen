package c.t.m.g;

import android.content.Context;
import android.location.GnssClock;
import android.location.GnssMeasurement;
import android.location.GnssMeasurementsEvent;
import android.location.GnssNavigationMessage;
import android.location.GnssStatus;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.RequiresApi;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.tencent.tencentmap.lbssdk.service.GTime;
import com.tencent.tencentmap.lbssdk.service.TxRtkSvr;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class q6 implements i6 {
    public final Context a;
    public BufferedWriter d;

    /* renamed from: e  reason: collision with root package name */
    public BufferedWriter f639e;

    /* renamed from: f  reason: collision with root package name */
    public BufferedWriter f640f;

    /* renamed from: g  reason: collision with root package name */
    public BufferedWriter f641g;

    /* renamed from: h  reason: collision with root package name */
    public File f642h;

    /* renamed from: i  reason: collision with root package name */
    public File f643i;

    /* renamed from: j  reason: collision with root package name */
    public File f644j;

    /* renamed from: k  reason: collision with root package name */
    public File f645k;
    public String b = "";

    /* renamed from: c  reason: collision with root package name */
    public com.tencent.tencentmap.lbssdk.service.a f638c = new com.tencent.tencentmap.lbssdk.service.a();

    /* renamed from: l  reason: collision with root package name */
    public int f646l = 0;

    /* renamed from: m  reason: collision with root package name */
    public int f647m = 0;

    /* renamed from: n  reason: collision with root package name */
    public int f648n = 0;
    public int o = 0;
    public boolean p = true;
    public boolean q = true;
    public ReentrantLock r = new ReentrantLock();

    public q6(Context context) {
        this.a = context;
    }

    public final int A() {
        File file = new File(this.a.getExternalFilesDir("dgnss"), String.format("%s_%s.nlp", "gnss_log", new SimpleDateFormat("yyy_MM_dd_HH_mm_ss").format(new Date())));
        String absolutePath = file.getAbsolutePath();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            BufferedWriter bufferedWriter2 = this.f640f;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e2) {
                    o("unable to close all file streams.", e2);
                    return 0;
                }
            }
            this.f644j = file;
            this.f640f = bufferedWriter;
            return 1;
        } catch (IOException e3) {
            o("Could not open file: ".concat(String.valueOf(absolutePath)), e3);
            return 0;
        }
    }

    public final int B() {
        File file = new File(this.a.getExternalFilesDir("dgnss"), String.format("%s_%s.nma", "gnss_log", new SimpleDateFormat("yyy_MM_dd_HH_mm_ss").format(new Date())));
        String absolutePath = file.getAbsolutePath();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            BufferedWriter bufferedWriter2 = this.f641g;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e2) {
                    o("unable to close all file streams.", e2);
                    return 0;
                }
            }
            this.f645k = file;
            this.f641g = bufferedWriter;
            return 1;
        } catch (IOException e3) {
            o("Could not open file: ".concat(String.valueOf(absolutePath)), e3);
            return 0;
        }
    }

    public final int C() {
        File file = new File(this.a.getExternalFilesDir("dgnss"), String.format("%s_%s.pos", "gnss_log", new SimpleDateFormat("yyy_MM_dd_HH_mm_ss").format(new Date())));
        String absolutePath = file.getAbsolutePath();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            BufferedWriter bufferedWriter2 = this.f639e;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e2) {
                    o("unable to close all file streams.", e2);
                    return 0;
                }
            }
            this.f643i = file;
            this.f639e = bufferedWriter;
            return 1;
        } catch (IOException e3) {
            o("could not open file: ".concat(String.valueOf(absolutePath)), e3);
            return 0;
        }
    }

    public void D() {
        if (this.q) {
            this.f646l = z();
        }
        if (this.q) {
            this.f647m = C();
        }
        if (this.q) {
            this.f648n = A();
        }
        if (this.q) {
            this.o = B();
        }
    }

    @Override // c.t.m.g.i6
    public void a(int i2) {
        s("Gnss Navigation Message Status Changed");
    }

    @Override // c.t.m.g.i6
    @RequiresApi(api = 26)
    public void a(GnssStatus gnssStatus) {
    }

    @Override // c.t.m.g.i6
    public void b(int i2) {
    }

    @Override // c.t.m.g.i6
    @RequiresApi(api = 24)
    public void b(GnssMeasurementsEvent gnssMeasurementsEvent) {
        gnssMeasurementsEvent.getMeasurements().size();
        GnssClock clock = gnssMeasurementsEvent.getClock();
        if (this.d != null) {
            for (GnssMeasurement gnssMeasurement : gnssMeasurementsEvent.getMeasurements()) {
                i(clock, gnssMeasurement);
                try {
                    if (x(clock, gnssMeasurement) == 0) {
                        m("Problem writing to file.");
                    }
                } catch (IOException e2) {
                    o("Problem writing to file.", e2);
                }
            }
        }
        int i2 = 0;
        int size = gnssMeasurementsEvent.getMeasurements().size();
        j5.e("txgpos", "get gnss satellites: ".concat(String.valueOf(size)));
        this.r.lock();
        try {
            this.b = "";
            for (GnssMeasurement gnssMeasurement2 : gnssMeasurementsEvent.getMeasurements()) {
                if (this.p) {
                    i(clock, gnssMeasurement2);
                }
                j(clock, gnssMeasurement2, this.f638c);
                i2++;
                if (i2 == size) {
                    if (this.p) {
                        this.b += ",TXEPOCHEND\n";
                    }
                    this.f638c.D = 1;
                } else if (this.p) {
                    this.b += ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
                }
                d(this.b, this.f638c);
            }
        } finally {
            this.r.unlock();
        }
    }

    @Override // c.t.m.g.i6
    public void c(GnssNavigationMessage gnssNavigationMessage) {
    }

    public final synchronized int d(String str, com.tencent.tencentmap.lbssdk.service.a aVar) {
        return TxRtkSvr.jni_upd_android_data(str.getBytes().length, str.getBytes(), aVar, 2);
    }

    @RequiresApi(api = 24)
    public final synchronized String e(GnssClock gnssClock) {
        Object[] objArr;
        objArr = new Object[10];
        objArr[0] = Long.valueOf(SystemClock.elapsedRealtime());
        objArr[1] = Long.valueOf(gnssClock.getTimeNanos());
        objArr[2] = gnssClock.hasLeapSecond() ? Integer.valueOf(gnssClock.getLeapSecond()) : "";
        objArr[3] = gnssClock.hasTimeUncertaintyNanos() ? Double.valueOf(gnssClock.getTimeUncertaintyNanos()) : "";
        objArr[4] = Long.valueOf(gnssClock.getFullBiasNanos());
        objArr[5] = gnssClock.hasBiasNanos() ? Double.valueOf(gnssClock.getBiasNanos()) : "";
        objArr[6] = gnssClock.hasBiasUncertaintyNanos() ? Double.valueOf(gnssClock.getBiasUncertaintyNanos()) : "";
        objArr[7] = gnssClock.hasDriftNanosPerSecond() ? Double.valueOf(gnssClock.getDriftNanosPerSecond()) : "";
        objArr[8] = gnssClock.hasDriftUncertaintyNanosPerSecond() ? Double.valueOf(gnssClock.getDriftUncertaintyNanosPerSecond()) : "";
        objArr[9] = gnssClock.getHardwareClockDiscontinuityCount() + DYConstants.DY_REGEX_COMMA;
        return String.format("Raw,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", objArr);
    }

    public void f() {
        y();
        w();
        u();
        q();
    }

    public void g(long j2) {
    }

    public void h(long j2, String str) {
        BufferedWriter bufferedWriter = this.f641g;
        if (bufferedWriter == null) {
            return;
        }
        try {
            bufferedWriter.write(str);
            this.f641g.newLine();
            this.f641g.flush();
        } catch (IOException e2) {
            o("Problem writing to file.", e2);
        }
    }

    @RequiresApi(api = 24)
    public final synchronized void i(GnssClock gnssClock, GnssMeasurement gnssMeasurement) {
        String e2 = e(gnssClock);
        String p = p(gnssClock, gnssMeasurement);
        String l2 = Long.toString(System.currentTimeMillis());
        this.b = e2 + p + DYConstants.DY_REGEX_COMMA + GTime.jni_getCurrSow() + DYConstants.DY_REGEX_COMMA + l2;
    }

    @RequiresApi(api = 24)
    public final synchronized void j(GnssClock gnssClock, GnssMeasurement gnssMeasurement, com.tencent.tencentmap.lbssdk.service.a aVar) {
        k(gnssClock, aVar);
        r(gnssClock, gnssMeasurement, aVar);
    }

    @RequiresApi(api = 24)
    public final void k(GnssClock gnssClock, com.tencent.tencentmap.lbssdk.service.a aVar) {
        aVar.D = 0;
        aVar.a = SystemClock.elapsedRealtime();
        aVar.f17988c = gnssClock.getTimeNanos();
        aVar.b = gnssClock.hasLeapSecond() ? gnssClock.getLeapSecond() : 0;
        aVar.d = gnssClock.hasTimeUncertaintyNanos() ? gnssClock.getTimeUncertaintyNanos() : 0.0d;
        aVar.f17989e = gnssClock.getFullBiasNanos();
        aVar.f17990f = gnssClock.hasBiasNanos() ? gnssClock.getBiasNanos() : 0.0d;
        aVar.f17991g = gnssClock.hasBiasUncertaintyNanos() ? gnssClock.getBiasUncertaintyNanos() : 0.0d;
        aVar.f17992h = gnssClock.hasDriftNanosPerSecond() ? gnssClock.getDriftNanosPerSecond() : 0.0d;
        aVar.f17993i = gnssClock.hasDriftUncertaintyNanosPerSecond() ? gnssClock.getDriftUncertaintyNanosPerSecond() : 0.0d;
        aVar.f17994j = gnssClock.getHardwareClockDiscontinuityCount();
    }

    public void l(Location location) {
        if (location.getProvider().equals("gps")) {
            String format = this.p ? String.format(Locale.US, "Fix,%s,%f,%f,%f,%f,%f,%f,%d,%d%n", location.getProvider(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getSpeed()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Long.valueOf(System.currentTimeMillis()), Long.valueOf(location.getTime())) : "";
            this.f638c.H = location.getLatitude();
            this.f638c.I = location.getLongitude();
            this.f638c.J = location.getAltitude();
            this.f638c.K = location.getSpeed();
            this.f638c.M = location.getAccuracy();
            this.f638c.L = location.getBearing();
            this.f638c.F = location.getTime();
            this.f638c.E = 1;
            BufferedWriter bufferedWriter = this.f639e;
            if (bufferedWriter != null && this.f647m != 0) {
                try {
                    bufferedWriter.write(format);
                    this.f639e.newLine();
                    this.f639e.flush();
                } catch (IOException e2) {
                    o("Problem writing to file.", e2);
                }
            }
            this.r.lock();
            try {
                TxRtkSvr.jni_upd_android_data(format.getBytes().length, format.getBytes(), this.f638c, 1);
            } finally {
            }
        }
        if (location.getProvider().equals("network")) {
            String format2 = this.p ? String.format(Locale.US, "NLP,%s,%f,%f,%f,%f,%f,%f,%d,%d%n", location.getProvider(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Double.valueOf(location.getAltitude()), Float.valueOf(location.getSpeed()), Float.valueOf(location.getAccuracy()), Float.valueOf(location.getBearing()), Long.valueOf(System.currentTimeMillis()), Long.valueOf(location.getTime())) : "";
            this.f638c.H = location.getLatitude();
            this.f638c.I = location.getLongitude();
            this.f638c.J = location.getAltitude();
            this.f638c.K = location.getSpeed();
            this.f638c.M = location.getAccuracy();
            this.f638c.L = location.getBearing();
            this.f638c.F = location.getTime();
            this.f638c.E = 0;
            BufferedWriter bufferedWriter2 = this.f640f;
            if (bufferedWriter2 != null && this.f648n != 0) {
                try {
                    bufferedWriter2.write(format2);
                    this.f640f.newLine();
                    this.f640f.flush();
                } catch (IOException e3) {
                    o("Problem writing to file.", e3);
                }
            }
            this.r.lock();
            try {
                TxRtkSvr.jni_upd_android_data(format2.getBytes().length, format2.getBytes(), this.f638c, 0);
            } finally {
            }
        }
    }

    public final void m(String str) {
        if (j5.a) {
            j5.e("GnssLogger", str);
        }
    }

    public void n(String str, int i2, Bundle bundle) {
        s(str + "Not Implemented");
    }

    public final void o(String str, Exception exc) {
        if (j5.a) {
            j5.e("GnssLogger", str);
        }
    }

    @RequiresApi(api = 24)
    public final synchronized String p(GnssClock gnssClock, GnssMeasurement gnssMeasurement) {
        return t(gnssClock, gnssMeasurement) + v(gnssClock, gnssMeasurement);
    }

    public final void q() {
        BufferedWriter bufferedWriter;
        if (this.f644j == null || this.f648n == 0 || (bufferedWriter = this.f640f) == null) {
            return;
        }
        try {
            bufferedWriter.flush();
            this.f640f.close();
            this.f640f = null;
        } catch (IOException e2) {
            o("unable to close all file streams.", e2);
        }
    }

    @RequiresApi(api = 24)
    public final void r(GnssClock gnssClock, GnssMeasurement gnssMeasurement, com.tencent.tencentmap.lbssdk.service.a aVar) {
        aVar.f17995k = gnssMeasurement.getSvid();
        aVar.f17997m = gnssMeasurement.getTimeOffsetNanos();
        aVar.f17998n = gnssMeasurement.getState();
        aVar.o = gnssMeasurement.getReceivedSvTimeNanos();
        aVar.p = gnssMeasurement.getReceivedSvTimeUncertaintyNanos();
        aVar.q = gnssMeasurement.getCn0DbHz();
        aVar.r = gnssMeasurement.getPseudorangeRateMetersPerSecond();
        aVar.s = gnssMeasurement.getPseudorangeRateUncertaintyMetersPerSecond();
        aVar.t = gnssMeasurement.getAccumulatedDeltaRangeState();
        aVar.u = gnssMeasurement.getAccumulatedDeltaRangeMeters();
        aVar.v = gnssMeasurement.getAccumulatedDeltaRangeUncertaintyMeters();
        aVar.w = gnssMeasurement.hasCarrierFrequencyHz() ? gnssMeasurement.getCarrierFrequencyHz() : 0.0f;
        aVar.x = gnssMeasurement.hasCarrierCycles() ? gnssMeasurement.getCarrierCycles() : 0L;
        double d = 0.0d;
        aVar.y = gnssMeasurement.hasCarrierPhase() ? gnssMeasurement.getCarrierPhase() : 0.0d;
        aVar.z = gnssMeasurement.hasCarrierPhaseUncertainty() ? gnssMeasurement.getCarrierPhaseUncertainty() : 0.0d;
        aVar.A = gnssMeasurement.getMultipathIndicator();
        aVar.B = gnssMeasurement.hasSnrInDb() ? gnssMeasurement.getSnrInDb() : 0.0d;
        aVar.f17996l = gnssMeasurement.getConstellationType();
        if (Build.VERSION.SDK_INT >= 26 && gnssMeasurement.hasAutomaticGainControlLevelDb()) {
            d = gnssMeasurement.getAutomaticGainControlLevelDb();
        }
        aVar.C = d;
    }

    public final void s(String str) {
    }

    @RequiresApi(api = 24)
    public final synchronized String t(GnssClock gnssClock, GnssMeasurement gnssMeasurement) {
        return String.format("%s,%s,%s,%s,%s,%s,", Integer.valueOf(gnssMeasurement.getSvid()), Double.valueOf(gnssMeasurement.getTimeOffsetNanos()), Integer.valueOf(gnssMeasurement.getState()), Long.valueOf(gnssMeasurement.getReceivedSvTimeNanos()), Long.valueOf(gnssMeasurement.getReceivedSvTimeUncertaintyNanos()), Double.valueOf(gnssMeasurement.getCn0DbHz()));
    }

    public final void u() {
        BufferedWriter bufferedWriter;
        if (this.f645k == null || this.o == 0 || (bufferedWriter = this.f641g) == null) {
            return;
        }
        try {
            bufferedWriter.flush();
            this.f641g.close();
            this.f641g = null;
        } catch (IOException e2) {
            o("unable to close all file streams.", e2);
        }
    }

    @RequiresApi(api = 24)
    public final synchronized String v(GnssClock gnssClock, GnssMeasurement gnssMeasurement) {
        String valueOf;
        valueOf = gnssMeasurement.hasCarrierFrequencyHz() ? String.valueOf(gnssMeasurement.getCarrierFrequencyHz()) : "";
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", Double.valueOf(gnssMeasurement.getPseudorangeRateMetersPerSecond()), Double.valueOf(gnssMeasurement.getPseudorangeRateUncertaintyMetersPerSecond()), Integer.valueOf(gnssMeasurement.getAccumulatedDeltaRangeState()), Double.valueOf(gnssMeasurement.getAccumulatedDeltaRangeMeters()), Double.valueOf(gnssMeasurement.getAccumulatedDeltaRangeUncertaintyMeters()), valueOf, gnssMeasurement.hasCarrierCycles() ? String.valueOf(gnssMeasurement.getCarrierCycles()) : "", gnssMeasurement.hasCarrierPhase() ? String.valueOf(gnssMeasurement.getCarrierPhase()) : "", gnssMeasurement.hasCarrierPhaseUncertainty() ? String.valueOf(gnssMeasurement.getCarrierPhaseUncertainty()) : "", Integer.valueOf(gnssMeasurement.getMultipathIndicator()), gnssMeasurement.hasSnrInDb() ? String.valueOf(gnssMeasurement.getSnrInDb()) : "", Integer.valueOf(gnssMeasurement.getConstellationType()), (Build.VERSION.SDK_INT < 26 || !gnssMeasurement.hasAutomaticGainControlLevelDb()) ? "" : String.valueOf(gnssMeasurement.getAutomaticGainControlLevelDb()), valueOf, gnssClock.hasLeapSecond() ? String.valueOf(gnssClock.getLeapSecond()) : "");
    }

    public final void w() {
        BufferedWriter bufferedWriter;
        if (this.f643i == null || this.f647m == 0 || (bufferedWriter = this.f639e) == null) {
            return;
        }
        try {
            bufferedWriter.flush();
            this.f639e.close();
            this.f639e = null;
        } catch (IOException e2) {
            o("unable to close all file streams.", e2);
        }
    }

    public final synchronized int x(GnssClock gnssClock, GnssMeasurement gnssMeasurement) {
        int i2;
        if (this.f646l == 0) {
            i2 = 0;
        } else {
            this.d.write(this.b);
            this.d.newLine();
            this.d.flush();
            i2 = 1;
        }
        return i2;
    }

    public final void y() {
        BufferedWriter bufferedWriter;
        if (this.f642h == null || this.f646l == 0 || (bufferedWriter = this.d) == null) {
            return;
        }
        try {
            bufferedWriter.flush();
            this.d.close();
            this.d = null;
        } catch (IOException e2) {
            o("unable to close all file streams.", e2);
        }
    }

    public final int z() {
        File file = new File(this.a.getExternalFilesDir("dgnss"), String.format("%s_%s.raw", "gnss_log", new SimpleDateFormat("yyy_MM_dd_HH_mm_ss").format(new Date())));
        String absolutePath = file.getAbsolutePath();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            BufferedWriter bufferedWriter2 = this.d;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e2) {
                    o("unable to close all file streams.", e2);
                    return 0;
                }
            }
            this.f642h = file;
            this.d = bufferedWriter;
            return 1;
        } catch (IOException e3) {
            o("could not open file: ".concat(String.valueOf(absolutePath)), e3);
            return 0;
        }
    }
}
