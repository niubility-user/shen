package com.tencent.smtt.utils;

import android.content.Context;
import com.jd.dynamic.DYConstants;
import com.tencent.smtt.sdk.TbsLogReport;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class r {
    private static r a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f17986c = false;
    private Map<String, Long> b = new HashMap();

    private r() {
    }

    public static r a() {
        if (a == null) {
            synchronized (TbsLogReport.class) {
                if (a == null) {
                    a = new r();
                }
            }
        }
        return a;
    }

    private boolean a(long j2) {
        return j2 <= 100000 && j2 > 0;
    }

    private long b(String str) {
        Long l2 = this.b.get(str);
        if (l2 != null) {
            return l2.longValue();
        }
        return 0L;
    }

    public void a(Context context) {
        if (this.f17986c) {
            return;
        }
        TbsLog.i("TbsTimeRecorder", b());
        this.f17986c = true;
        TbsLogReport tbsLogReport = TbsLogReport.getInstance(context);
        tbsLogReport.eventReport(TbsLogReport.EventType.TYPE_CORE_LOAD_PERFORMANCE, tbsLogReport.tbsLogInfo());
    }

    public void a(String str) {
        this.b.put(str, Long.valueOf(System.currentTimeMillis()));
    }

    public String b() {
        long b = b("init_tbs_end") - b("init_tbs_Start");
        long b2 = b("preinit_finish") - b("preinit_start");
        long b3 = b("create_webview_end") - b("create_webview_start");
        long b4 = b("core_load_end") - b("core_load_start");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("initX5Environment: ");
        if (!a(b)) {
            b = -1;
        }
        sb.append(b);
        sb.append(DYConstants.DY_REGEX_COMMA);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append("preInit: ");
        if (!a(b2)) {
            b2 = -1;
        }
        sb3.append(b2);
        sb3.append(DYConstants.DY_REGEX_COMMA);
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb4);
        sb5.append("webview: ");
        if (!a(b3)) {
            b3 = -1;
        }
        sb5.append(b3);
        sb5.append(DYConstants.DY_REGEX_COMMA);
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(sb6);
        sb7.append("coreLoadCost: ");
        if (!a(b4)) {
            b4 = -1;
        }
        sb7.append(b4);
        return sb7.toString();
    }
}
