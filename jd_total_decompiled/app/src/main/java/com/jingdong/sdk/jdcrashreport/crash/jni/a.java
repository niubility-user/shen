package com.jingdong.sdk.jdcrashreport.crash.jni;

import android.os.Process;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.b.c;
import com.jingdong.sdk.jdcrashreport.b.j;
import com.jingdong.sdk.jdcrashreport.b.o;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.b.w;
import com.jingdong.sdk.jdcrashreport.b.x;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import com.jingdong.sdk.jdcrashreport.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes7.dex */
public class a implements w.d<Boolean, Boolean> {
    private static boolean a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.jdcrashreport.crash.jni.a$a  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public class C0716a implements JDCrashReportListener {
        C0716a(a aVar) {
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onEnd(int i2, String str, CrashInfo crashInfo) {
            o.b(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))));
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onError(int i2, String str, CrashInfo crashInfo) {
            j.d(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))), crashInfo);
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onStart(CrashInfo crashInfo) {
        }
    }

    private a() {
        a = true;
    }

    private String c(String str) {
        return x.c(Long.parseLong(str.substring(12, str.indexOf(".txt"))));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:(1:126)(19:61|(2:62|(3:66|(3:78|79|80)(3:68|69|(3:75|76|77)(3:71|72|73))|74)(1:125))|83|(2:84|(3:86|(1:96)(3:88|89|91)|92)(0))|98|99|(1:101)(1:121)|102|(1:104)|105|(1:107)|108|(1:110)|111|(1:113)|114|(1:116)(1:120)|117|118)|97|98|99|(0)(0)|102|(0)|105|(0)|108|(0)|111|(0)|114|(0)(0)|117|118) */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01ab, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01ac, code lost:
        com.jingdong.sdk.jdcrashreport.b.r.g("NativeCrashHandleTask", r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x025e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0202  */
    @Override // com.jingdong.sdk.jdcrashreport.b.w.d
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Boolean a(Boolean bool) {
        File[] fileArr;
        int i2;
        int i3;
        Throwable th;
        IOException iOException;
        BufferedReader bufferedReader;
        String readLine;
        JDCrashReportListener Y;
        String str;
        String str2;
        a aVar = this;
        boolean z = false;
        if (!bool.booleanValue()) {
            a = false;
            return Boolean.FALSE;
        }
        File[] h2 = j.h();
        int length = h2.length;
        int i4 = 0;
        while (i4 < length) {
            File file = h2[i4];
            if (!file.getName().startsWith("nativeCrash") || !file.getName().endsWith(".txt")) {
                fileArr = h2;
                i2 = length;
                i3 = i4;
            } else if (file.isFile() && file.canRead()) {
                try {
                    String c2 = aVar.c(file.getName());
                    CrashInfo createCrashInfo = CrashInfo.createCrashInfo();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader2 = null;
                    try {
                        try {
                            bufferedReader = new BufferedReader(new FileReader(file));
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        fileArr = h2;
                        i2 = length;
                        i3 = i4;
                    }
                    try {
                        try {
                            createCrashInfo.busiType = "native";
                            String str3 = "";
                            String str4 = str3;
                            int i5 = 0;
                            while (true) {
                                readLine = bufferedReader.readLine();
                                fileArr = h2;
                                i2 = length;
                                i3 = i4;
                                if (readLine == null) {
                                    break;
                                }
                                try {
                                    if (readLine.startsWith("PageInfo:")) {
                                        break;
                                    }
                                    if (i5 == 0) {
                                        str = str4;
                                        createCrashInfo.crashType = readLine.substring(0, readLine.indexOf(", "));
                                    } else if (i5 == 1) {
                                        createCrashInfo.clientVersion = "";
                                        createCrashInfo.buildCode = "";
                                        String[] split = readLine.split(LangUtils.SINGLE_SPACE);
                                        if (split != null) {
                                            str = str4;
                                            if (split.length == 4) {
                                                createCrashInfo.clientVersion = split[1];
                                                createCrashInfo.buildCode = split[3];
                                            }
                                        } else {
                                            str = str4;
                                        }
                                        createCrashInfo.clientVersion = "";
                                        createCrashInfo.buildCode = "";
                                    } else if (i5 != 2) {
                                        if (i5 == 3) {
                                            createCrashInfo.crashLine = readLine;
                                        }
                                        str = str4;
                                    } else {
                                        str4 = readLine.substring(readLine.indexOf("current process: ") + 17, readLine.indexOf(", "));
                                        str2 = readLine.substring(readLine.indexOf("crash pid = ") + 12, readLine.indexOf(", crash tid"));
                                        sb.append(readLine);
                                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                        i5++;
                                        str3 = str2;
                                        h2 = fileArr;
                                        length = i2;
                                        i4 = i3;
                                    }
                                    str2 = str3;
                                    str4 = str;
                                    sb.append(readLine);
                                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                    i5++;
                                    str3 = str2;
                                    h2 = fileArr;
                                    length = i2;
                                    i4 = i3;
                                } catch (Exception e3) {
                                    e = e3;
                                    aVar = this;
                                    bufferedReader2 = bufferedReader;
                                    r.g("NativeCrashHandleTask", e);
                                    if (bufferedReader2 != null) {
                                    }
                                    o.b(file);
                                    i4 = i3 + 1;
                                    h2 = fileArr;
                                    length = i2;
                                    z = false;
                                } catch (Throwable th3) {
                                    th = th3;
                                    th = th;
                                    bufferedReader2 = bufferedReader;
                                    if (bufferedReader2 != null) {
                                        try {
                                            bufferedReader2.close();
                                        } catch (IOException e4) {
                                            r.g("NativeCrashHandleTask", e4);
                                        }
                                    }
                                    o.b(file);
                                    throw th;
                                }
                            }
                            String str5 = str4;
                            if (createCrashInfo.buildCode.equals(String.valueOf(d.M())) && createCrashInfo.clientVersion.equals(d.L())) {
                                if (readLine != null && readLine.startsWith("PageInfo:")) {
                                    StringBuilder sb2 = new StringBuilder(readLine.replace("PageInfo:", ""));
                                    while (true) {
                                        String readLine2 = bufferedReader.readLine();
                                        if (readLine2 == null || "UserMap:".equals(readLine2)) {
                                            break;
                                        } else if (readLine2.startsWith("CurrentPageInfo:")) {
                                            createCrashInfo.currentPageInfo = readLine2.replace("CurrentPageInfo:", "");
                                        } else if (readLine2.startsWith("isForeground:")) {
                                            createCrashInfo.isForeground = readLine2.replace("isForeground:", "");
                                        } else {
                                            sb2.append(readLine2);
                                            sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                        }
                                    }
                                    createCrashInfo.pageInfo = String.valueOf(sb2);
                                    while (true) {
                                        String readLine3 = bufferedReader.readLine();
                                        if (readLine3 != null) {
                                            if (!TextUtils.isEmpty(readLine3)) {
                                                try {
                                                    String[] split2 = readLine3.split(" <<:>> ");
                                                    createCrashInfo.feedback.put(split2[0], split2[1]);
                                                } catch (Throwable th4) {
                                                    r.g("NativeCrashHandleTask", th4);
                                                }
                                            }
                                        }
                                    }
                                    bufferedReader.close();
                                    o.b(file);
                                    if (!createCrashInfo.crashType.contains("SIGSEGV")) {
                                        createCrashInfo.msgType = "1";
                                    } else {
                                        createCrashInfo.msgType = "2";
                                    }
                                    createCrashInfo.crashStack = sb.toString();
                                    if (!TextUtils.isEmpty(c2)) {
                                        createCrashInfo.crashTime = c2;
                                    }
                                    if (TextUtils.isEmpty(createCrashInfo.isForeground)) {
                                        createCrashInfo.isForeground = String.valueOf(true);
                                    }
                                    createCrashInfo.feedback.put("isForegroundRunning", createCrashInfo.isForeground);
                                    if (!TextUtils.isEmpty(str5)) {
                                        createCrashInfo.processName = str5;
                                        createCrashInfo.feedback.put("processName", str5);
                                    }
                                    if (!TextUtils.isEmpty(str3)) {
                                        createCrashInfo.feedback.put("processId", str3);
                                    }
                                    o.b(file);
                                    Y = d.Y();
                                    if (Y != null) {
                                        aVar = this;
                                        Y = new C0716a(aVar);
                                    } else {
                                        aVar = this;
                                    }
                                    createCrashInfo.feedback.put("cache", DYConstants.DY_TRUE);
                                    j.c(createCrashInfo, Y);
                                } else {
                                    createCrashInfo.isForeground = String.valueOf(createCrashInfo.processName.equals(c.a(Process.myPid()).trim()));
                                    createCrashInfo.currentPageInfo = "";
                                    createCrashInfo.pageInfo = "";
                                }
                                bufferedReader.close();
                                o.b(file);
                                if (!createCrashInfo.crashType.contains("SIGSEGV")) {
                                }
                                createCrashInfo.crashStack = sb.toString();
                                if (!TextUtils.isEmpty(c2)) {
                                }
                                if (TextUtils.isEmpty(createCrashInfo.isForeground)) {
                                }
                                createCrashInfo.feedback.put("isForegroundRunning", createCrashInfo.isForeground);
                                if (!TextUtils.isEmpty(str5)) {
                                }
                                if (!TextUtils.isEmpty(str3)) {
                                }
                                o.b(file);
                                Y = d.Y();
                                if (Y != null) {
                                }
                                createCrashInfo.feedback.put("cache", DYConstants.DY_TRUE);
                                j.c(createCrashInfo, Y);
                            }
                            aVar = this;
                        } catch (Exception e5) {
                            e = e5;
                            fileArr = h2;
                            i2 = length;
                            i3 = i4;
                        }
                        try {
                            o.b(file);
                        } catch (Exception e6) {
                            e = e6;
                            bufferedReader2 = bufferedReader;
                            r.g("NativeCrashHandleTask", e);
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e7) {
                                    iOException = e7;
                                    r.g("NativeCrashHandleTask", iOException);
                                    o.b(file);
                                    i4 = i3 + 1;
                                    h2 = fileArr;
                                    length = i2;
                                    z = false;
                                }
                            }
                            o.b(file);
                            i4 = i3 + 1;
                            h2 = fileArr;
                            length = i2;
                            z = false;
                        }
                        try {
                            bufferedReader.close();
                        } catch (IOException e8) {
                            iOException = e8;
                            r.g("NativeCrashHandleTask", iOException);
                            o.b(file);
                            i4 = i3 + 1;
                            h2 = fileArr;
                            length = i2;
                            z = false;
                        }
                        o.b(file);
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Throwable unused) {
                    fileArr = h2;
                    i2 = length;
                    i3 = i4;
                    o.b(file);
                }
            } else {
                fileArr = h2;
                i2 = length;
                i3 = i4;
                o.b(file);
            }
            i4 = i3 + 1;
            h2 = fileArr;
            length = i2;
            z = false;
        }
        a = z;
        return Boolean.TRUE;
    }

    public static a a() {
        if (d.E() && !a) {
            return new a();
        }
        return null;
    }
}
