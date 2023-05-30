package com.jd.fireeye.a.c;

import android.os.Process;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes13.dex */
public class d {
    public static String[] a = {"^/data/app/com.google.android.webview.*", "^/data/app/com.android.webview.*'", "^/data/app/com.android.chrome.*", "^/data/app/com.google.ar.core.*", "^/data/data/com.lbe.security.*", "^/data/(data|app|app-lib)/com.tencent.mtt.*", "^/data/(data|app|app-lib)/com.jingdong.app.*", "^/data/(data|app|app-lib)/com.jd.app.*", "^/data/user/\\d{1,3}/com.tencent.mm.*", "^/data/(data|app|app-lib)/com.tencent.mm.*", "^/data/user/\\d{1,3}/com.tencent.mobileqq.*", "^/data/(data|app|app-lib)/com.tencent.mobileqq.*", "^/data/user/\\d{1,3}/com.tencent.tbs.*", "^/data/(data|app|app-lib)/com.tencent.tbs.*", "^/data/(data|app|app-lib)/com.qzone.*", "^/data/lbe/.*"};

    public static String a() {
        return a(Process.myPid());
    }

    public static String a(int i2) {
        boolean z;
        HashSet hashSet = new HashSet();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(String.format(Locale.ENGLISH, "/proc/%d/maps", Integer.valueOf(i2))));
            try {
                for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                    String[] split = readLine.split(LangUtils.SINGLE_SPACE);
                    if (split.length > 0) {
                        String str = split[split.length - 1];
                        if (str.endsWith(".so") && str.startsWith("/data") && !str.contains(jd.wjlogin_sdk.util.f.f19954c)) {
                            String[] strArr = a;
                            int length = strArr.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length) {
                                    z = false;
                                    break;
                                } else if (Pattern.matches(strArr[i3], str)) {
                                    z = true;
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                            if (!z) {
                                hashSet.add(str);
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                f.a(bufferedReader);
                throw th;
            }
            bufferedReader = bufferedReader2;
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        f.a(bufferedReader);
        return hashSet.toString();
    }
}
