package com.jingdong.sdk.jdcrashreport.e.d;

import android.os.Process;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.d;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
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
    */
    public static a a(String str) {
        BufferedReader bufferedReader;
        String str2;
        int i2;
        String str3;
        int i3;
        BufferedReader bufferedReader2;
        int i4;
        int i5;
        String str4;
        String str5;
        BufferedReader bufferedReader3;
        String str6;
        int i6;
        int i7;
        String str7;
        int i8;
        String str8;
        int i9;
        String str9;
        String str10;
        String str11;
        String str12 = "";
        r.b("JDCrashReport", "parse native tombstone file");
        int myPid = Process.myPid();
        String packageName = d.I().getPackageName();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        try {
            try {
                bufferedReader2 = new BufferedReader(new FileReader(str));
                int i10 = 1;
                str2 = "";
                i2 = myPid;
                str3 = packageName;
                char c2 = '\uffff';
                i4 = -100;
                i5 = -100;
                boolean z = false;
                int i11 = 0;
                str4 = str2;
                str5 = str4;
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String trim = readLine.trim();
                        if (z) {
                            if (trim.startsWith("Crash time: ")) {
                                Matcher matcher = a.matcher(trim);
                                if (matcher.matches()) {
                                    str11 = str12;
                                    try {
                                        if (matcher.groupCount() == i10) {
                                            r.b("JDCrashReport", "find crash time");
                                            str12 = c(matcher.group(i10));
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        i3 = i11;
                                        str12 = str11;
                                        r.c("JDCrashReport", "parse native tombstone file '" + str + "' content failed", e);
                                        if (bufferedReader2 != null) {
                                            try {
                                                bufferedReader2.close();
                                            } catch (Exception unused) {
                                            }
                                        }
                                        str10 = str12;
                                        str9 = str4;
                                        str7 = str5;
                                        str6 = str2;
                                        i8 = i3;
                                        i9 = i2;
                                        str8 = str3;
                                        i7 = i4;
                                        i6 = i5;
                                        if (str10.length() <= 0) {
                                        }
                                        r.b("JDCrashReport", "parse native tombstone file failed");
                                        return null;
                                    }
                                } else {
                                    str11 = str12;
                                }
                            } else {
                                str11 = str12;
                                if (trim.startsWith("pid: ")) {
                                    Matcher matcher2 = b.matcher(trim);
                                    if (matcher2.matches() && matcher2.groupCount() == 4) {
                                        r.b("JDCrashReport", "find process info");
                                        i2 = Integer.parseInt(matcher2.group(1));
                                        i11 = Integer.parseInt(matcher2.group(2));
                                        str5 = matcher2.group(3);
                                        str3 = matcher2.group(4);
                                        str4 = String.format("pid: %s (%s), tid: %s (%s)", Integer.valueOf(i2), str3, Integer.valueOf(i11), str5);
                                        str12 = str11;
                                    }
                                } else if (trim.startsWith("signal ")) {
                                    r.b("JDCrashReport", "find signal info");
                                    sb.append(String.format("Fatal %s\n", trim));
                                    Matcher matcher3 = f14948c.matcher(trim);
                                    if (matcher3.matches() && matcher3.groupCount() == 3) {
                                        str2 = matcher3.group(1);
                                        if (matcher3.group(2) != null) {
                                            i4 = Integer.parseInt(matcher3.group(2));
                                        }
                                        if (matcher3.group(3) != null) {
                                            i5 = Integer.parseInt(matcher3.group(3));
                                        }
                                    }
                                    str12 = str11;
                                    c2 = 1;
                                } else {
                                    if (trim.startsWith("backtrace:") && d.matcher(trim).matches()) {
                                        r.b("JDCrashReport", "find backtrace");
                                        sb2.append(trim);
                                        sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                        str12 = str11;
                                        c2 = 2;
                                    }
                                    if (c2 == 1) {
                                        if (c2 == 2) {
                                            if (trim.length() > 0) {
                                                sb2.append("    ");
                                                sb2.append(trim);
                                                sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                            }
                                            str12 = str11;
                                            c2 = '\uffff';
                                        }
                                        str12 = str11;
                                    } else {
                                        if (trim.length() > 0) {
                                            sb.append("    ");
                                            sb.append(trim);
                                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                            str12 = str11;
                                        }
                                        str12 = str11;
                                        c2 = '\uffff';
                                    }
                                }
                            }
                            if (c2 == 1) {
                            }
                        } else {
                            str11 = str12;
                            if (trim.startsWith("*** *** *** *** *** *** *** ***")) {
                                str12 = str11;
                                i10 = 1;
                                z = true;
                            } else {
                                str12 = str11;
                            }
                        }
                        i10 = 1;
                    } catch (Exception e3) {
                        e = e3;
                        i3 = i11;
                    }
                }
                str10 = str12;
                try {
                    bufferedReader2.close();
                } catch (Exception unused2) {
                }
                str9 = str4;
                str7 = str5;
                str6 = str2;
                i9 = i2;
                str8 = str3;
                i7 = i4;
                i6 = i5;
                i8 = i11;
            } catch (Exception e4) {
                e = e4;
                str2 = "";
                i2 = myPid;
                str3 = packageName;
                i3 = 0;
                bufferedReader2 = null;
                i4 = -100;
                i5 = -100;
                str4 = str2;
                str5 = str4;
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
                if (bufferedReader != null) {
                }
                throw th;
            }
            if (str10.length() <= 0 && str9.length() > 0 && sb2.length() > 0) {
                return new a(str10, str9, i9, str8, i8, str7, sb.toString(), sb2.toString(), i7, i6, str6);
            }
            r.b("JDCrashReport", "parse native tombstone file failed");
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = bufferedReader3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0158: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:71:0x0158 */
    /* JADX WARN: Removed duplicated region for block: B:81:0x015b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static com.jingdong.sdk.jdcrashreport.e.a.a b(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        r.b("JDCrashReport", "parse trace tombstone file");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(str));
                char c2 = '\uffff';
                String str2 = "";
                String str3 = str2;
                int i2 = 0;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            String trim = readLine.trim();
                            if (str3.length() == 0 && trim.startsWith("Crash time: ")) {
                                Matcher matcher = a.matcher(trim);
                                if (matcher.matches()) {
                                    r.b("JDCrashReport", "find crash time");
                                    str3 = c(matcher.group(1));
                                } else {
                                    r.b("JDCrashReport", "parse crash time failed");
                                    try {
                                        bufferedReader.close();
                                    } catch (Exception unused) {
                                    }
                                    return null;
                                }
                            } else if (i2 == 0 && trim.startsWith("pid: ")) {
                                Matcher matcher2 = f14949e.matcher(trim);
                                if (matcher2.matches()) {
                                    r.b("JDCrashReport", "find pid");
                                    i2 = Integer.parseInt(matcher2.group(1));
                                    String group = matcher2.group(2);
                                    sb.append(String.format(Locale.getDefault(), "----- pid %d at %s -----", Integer.valueOf(i2), str3));
                                    sb.append('\n');
                                    str2 = group;
                                } else {
                                    r.b("JDCrashReport", "parse pid failed");
                                    try {
                                        bufferedReader.close();
                                    } catch (Exception unused2) {
                                    }
                                    return null;
                                }
                            } else {
                                if (trim.startsWith("--- --- --- --- --- --- --- ---")) {
                                    r.b("JDCrashReport", "find trace start");
                                } else if (!trim.startsWith("+++ +++ +++ +++ +++ +++ +++ +++")) {
                                    if (c2 == 3) {
                                        sb.append(trim);
                                        sb.append('\n');
                                        if (sb2.length() == 0 && trim.startsWith("\"main\"")) {
                                            r.b("JDCrashReport", "found main thread trace");
                                            sb2.append(trim);
                                            sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                            c2 = 4;
                                        }
                                    } else if (c2 == 4) {
                                        sb2.append(trim);
                                        sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                        sb.append(trim);
                                        sb.append('\n');
                                        if (trim.trim().length() == 0) {
                                        }
                                    }
                                }
                                c2 = 3;
                            }
                        }
                        try {
                            bufferedReader.close();
                        } catch (Exception unused3) {
                        }
                        if (str3.length() > 0 && i2 > 0 && sb.length() > 0) {
                            return new com.jingdong.sdk.jdcrashreport.e.a.a(i2, str2, str3, sb.toString(), sb2.toString());
                        }
                        r.b("JDCrashReport", "parse trace tombstone file failed!");
                        return null;
                    } catch (Exception e2) {
                        e = e2;
                        r.c("JDCrashReport", String.format(Locale.getDefault(), "parse trace tombstone file exception, %s", e.getMessage()), e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception unused4) {
                            }
                        }
                        return null;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader3 != null) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader3 = bufferedReader2;
            if (bufferedReader3 != null) {
                try {
                    bufferedReader3.close();
                } catch (Exception unused5) {
                }
            }
            throw th;
        }
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
