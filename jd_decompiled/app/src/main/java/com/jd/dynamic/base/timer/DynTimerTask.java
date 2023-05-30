package com.jd.dynamic.base.timer;

import android.content.Intent;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.timer.TimerRequest;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.concurrent.ScheduledFuture;

/* loaded from: classes13.dex */
public class DynTimerTask implements Runnable {
    public static final int STATE_FINISH = 2;
    public static final int STATE_IDLE = 0;
    public static final int STATE_RUNNING = 1;

    /* renamed from: g */
    private final TimerManager f2048g;

    /* renamed from: h */
    private TimerRequest f2049h;

    /* renamed from: j */
    private long f2051j;

    /* renamed from: k */
    private String f2052k;

    /* renamed from: l */
    private boolean f2053l;

    /* renamed from: m */
    private ScheduledFuture<Void> f2054m;

    /* renamed from: n */
    private String f2055n;

    /* renamed from: i */
    private int f2050i = 0;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;

    public DynTimerTask(TimerRequest timerRequest, TimerManager timerManager) {
        this.f2049h = timerRequest;
        this.f2053l = timerRequest.getStartValue() < timerRequest.getEndValue();
        this.f2051j = timerRequest.getStartValue();
        if (timerRequest.getType() == TimerRequest.TimerType.END_TIME) {
            this.f2051j = timerRequest.getEndValue() - System.currentTimeMillis();
        }
        this.f2048g = timerManager;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x006c, code lost:
        if (r10.equals("S") == false) goto L65;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(int r21, long r22, long r24, long r26, long r28) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.timer.DynTimerTask.a(int, long, long, long, long):java.lang.String");
    }

    private void a() {
        long endValue = this.f2049h.getEndValue() - System.currentTimeMillis();
        this.f2051j = endValue;
        if (endValue <= 0) {
            this.f2051j = 0L;
            this.f2050i = 2;
            cancel(false);
        }
    }

    private String b(int i2, long j2, long j3, long j4, long j5, int i3, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 72:
                if (str.equals(DYConstants.LETTER_H)) {
                    c2 = 0;
                    break;
                }
                break;
            case 83:
                if (str.equals("S")) {
                    c2 = 1;
                    break;
                }
                break;
            case 100:
                if (str.equals("d")) {
                    c2 = 2;
                    break;
                }
                break;
            case 109:
                if (str.equals("m")) {
                    c2 = 3;
                    break;
                }
                break;
            case 115:
                if (str.equals("s")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return String.format(DYConstants.FORMAT_P_0 + i3 + "d", Long.valueOf(j4));
            case 1:
                return String.format(DYConstants.FORMAT_P_0 + i3 + "d", Long.valueOf(j5));
            case 2:
                return String.format(DYConstants.FORMAT_P_0 + i3 + "d", Integer.valueOf(i2));
            case 3:
                return String.format(DYConstants.FORMAT_P_0 + i3 + "d", Long.valueOf(j3));
            case 4:
                return String.format(DYConstants.FORMAT_P_0 + i3 + "d", Long.valueOf(j2));
            default:
                return "";
        }
    }

    private void c(String str, int i2, long j2, long j3, long j4, long j5, StringBuilder sb, StringBuilder sb2, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (sb2.length() <= 0 || TextUtils.equals(str2, str)) {
            sb2.append(str);
            return;
        }
        sb.append(b(i2, j2, j3, j4, j5, sb2.length(), str));
        sb2.delete(0, sb2.length());
    }

    private void d() {
        boolean z = this.f2053l;
        long j2 = this.f2051j;
        if (z) {
            long interval = j2 + this.f2049h.getInterval();
            this.f2051j = interval;
            if (interval < this.f2049h.getEndValue()) {
                return;
            }
        } else {
            long interval2 = j2 - this.f2049h.getInterval();
            this.f2051j = interval2;
            if (interval2 > this.f2049h.getEndValue()) {
                return;
            }
        }
        this.f2051j = this.f2049h.getEndValue();
        this.f2050i = 2;
        cancel(false);
    }

    private void e() {
        boolean z = this.f2053l;
        long j2 = this.f2051j;
        if (z) {
            long step = j2 + this.f2049h.getStep();
            this.f2051j = step;
            if (step < this.f2049h.getEndValue()) {
                return;
            }
        } else {
            long step2 = j2 - this.f2049h.getStep();
            this.f2051j = step2;
            if (step2 > this.f2049h.getEndValue()) {
                return;
            }
        }
        this.f2051j = this.f2049h.getEndValue();
        this.f2050i = 2;
        cancel(false);
    }

    private void f() {
        this.f2052k = String.valueOf(this.f2051j);
        if (!TextUtils.isEmpty(this.f2049h.getFormatType())) {
            this.f2052k = g();
        }
        Intent intent = new Intent(this.f2049h.getTimerId());
        intent.putExtra("currentValue", this.f2052k);
        intent.putExtra(XView2Constants.STATE, this.f2050i);
        LocalBroadcastManager.getInstance(DynamicSdk.getEngine().getContext()).sendBroadcast(intent);
        t.e("DynTimerTask", "sendResult", toString());
    }

    private String g() {
        if (TextUtils.isEmpty(this.f2055n)) {
            String formatType = this.f2049h.getFormatType();
            this.f2055n = formatType;
            if (!TextUtils.isEmpty(formatType)) {
                for (int i2 = 0; i2 < this.f2055n.length(); i2++) {
                    String valueOf = String.valueOf(this.f2055n.charAt(i2));
                    if (!TextUtils.isEmpty(valueOf)) {
                        valueOf.hashCode();
                        char c2 = '\uffff';
                        switch (valueOf.hashCode()) {
                            case 72:
                                if (valueOf.equals(DYConstants.LETTER_H)) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                            case 100:
                                if (valueOf.equals("d")) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case 109:
                                if (valueOf.equals("m")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case 115:
                                if (valueOf.equals("s")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                        }
                        switch (c2) {
                            case 0:
                                this.p = true;
                                continue;
                            case 1:
                                this.o = true;
                                continue;
                            case 2:
                                this.q = true;
                                continue;
                            case 3:
                                this.r = true;
                                continue;
                        }
                    }
                }
            }
        }
        long j2 = this.f2051j;
        int i3 = (int) (j2 / 86400000);
        long j3 = i3;
        long j4 = j2 - (86400000 * j3);
        long j5 = j4 / 1000;
        long j6 = j5 % 60;
        long j7 = (j5 / 60) % 60;
        long j8 = j5 / 3600;
        long j9 = ((j4 - (3600000 * j8)) - (60000 * j7)) - (j6 * 1000);
        if (j8 <= 0) {
            j8 = 0;
        }
        if (!this.o && i3 > 0) {
            j8 += j3 * 24;
        }
        long j10 = j8;
        if (!this.p && j10 > 0) {
            j7 += j10 * 60;
        }
        if (!this.q && j7 > 0) {
            j6 += 60 * j7;
        }
        if (!this.r && j6 > 0) {
            j9 += 1000 * j6;
        }
        long j11 = j9;
        return TextUtils.isEmpty(this.f2055n) ? String.valueOf(j11) : a(i3, j6, j7, j10, j11);
    }

    public boolean cancel(boolean z) {
        this.f2050i = 2;
        ScheduledFuture<Void> scheduledFuture = this.f2054m;
        boolean cancel = scheduledFuture != null ? scheduledFuture.cancel(true) : false;
        t.c("DynTimerTask", "cancel", toString());
        if (z) {
            f();
        }
        TimerManager timerManager = this.f2048g;
        if (timerManager != null) {
            timerManager.onTimerFinish(this.f2049h.getTimerId());
        }
        return cancel;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f2050i == 0) {
            this.f2050i = 1;
            f();
            return;
        }
        if (this.f2049h.getEndValue() != this.f2049h.getStartValue()) {
            if (this.f2049h.getType() == TimerRequest.TimerType.TIME) {
                d();
            } else if (this.f2049h.getType() == TimerRequest.TimerType.END_TIME) {
                a();
            } else {
                e();
            }
        }
        f();
    }

    public void setScheduledFuture(ScheduledFuture<Void> scheduledFuture) {
        this.f2054m = scheduledFuture;
    }

    public String toString() {
        return "DynTimerTask{ID=" + this.f2049h.getTimerId() + ", state=" + this.f2050i + ", strValue=" + this.f2052k + ", isPlus=" + this.f2053l + '}';
    }
}
