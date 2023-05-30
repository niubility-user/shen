package com.jingdong.sdk.jdcrashreport.e.d;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class b {
    private static final Pattern a = Pattern.compile("^Crash time: '(.+)'$");
    private static final Pattern b = Pattern.compile("pid: (\\d+), tid: (\\d+), name: (\\S+)\\s\\s>>> (\\S+) <<<");

    /* renamed from: c  reason: collision with root package name */
    private static final Pattern f14948c = Pattern.compile("^(signal (\\d+) .*), code ([-\\d]*) .*$");
    private static final Pattern d = Pattern.compile("^backtrace:$");

    /* renamed from: e  reason: collision with root package name */
    private static final Pattern f14949e = Pattern.compile("pid: ([0-9]+)\\s\\s>>> (.+) <<<");

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0224: MOVE (r9 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:89:0x0224 */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x016a A[Catch: Exception -> 0x0197, all -> 0x0223, TryCatch #0 {all -> 0x0223, blocks: (B:5:0x003c, B:7:0x0042, B:9:0x0048, B:12:0x0052, B:15:0x0060, B:17:0x0066, B:51:0x015a, B:53:0x0160, B:54:0x016a, B:56:0x0171, B:74:0x01ca, B:20:0x0079, B:22:0x0083, B:24:0x008f, B:26:0x0096, B:27:0x00d4, B:29:0x00dc, B:31:0x00fc, B:33:0x0103, B:35:0x010f, B:36:0x0119, B:38:0x0120, B:40:0x012d, B:42:0x0136, B:44:0x0142, B:58:0x017f), top: B:98:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0227 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.sdk.jdcrashreport.e.d.a a(java.lang.String r33) {
        /*
            Method dump skipped, instructions count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.e.d.b.a(java.lang.String):com.jingdong.sdk.jdcrashreport.e.d.a");
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0158: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:71:0x0158 */
    /* JADX WARN: Removed duplicated region for block: B:81:0x015b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.sdk.jdcrashreport.e.a.a b(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.e.d.b.b(java.lang.String):com.jingdong.sdk.jdcrashreport.e.a.a");
    }

    private static String c(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat2.format(simpleDateFormat.parse(str));
        } catch (Exception unused) {
            return simpleDateFormat2.format(new Date(System.currentTimeMillis()));
        }
    }
}
