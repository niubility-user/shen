package com.jd.stat.common.process;

import android.os.Process;
import com.jd.stat.common.b.g;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.regex.Pattern;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes18.dex */
public class a {
    public static String[] a = {"^/data/app/com.google.android.webview.*", "^/data/app/com.android.webview.*'", "^/data/app/com.android.chrome.*", "^/data/app/com.google.ar.core.*", "^/data/data/com.lbe.security.*", "^/data/(data|app|app-lib)/com.tencent.mtt.*", "^/data/user/\\d{1,3}/com.tencent.mm.*", "^/data/(data|app|app-lib)/com.tencent.mm.*", "^/data/user/\\d{1,3}/com.tencent.mobileqq.*", "^/data/(data|app|app-lib)/com.tencent.mobileqq.*", "^/data/user/\\d{1,3}/com.tencent.tbs.*", "^/data/(data|app|app-lib)/com.tencent.tbs.*", "^/data/(data|app|app-lib)/com.qzone.*", "^/data/lbe/.*"};

    /* renamed from: com.jd.stat.common.process.a$a  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    public interface InterfaceC0218a {
        String a();

        void a(Throwable th);

        boolean a(String[] strArr);

        void b();
    }

    public static String a() {
        return a(Process.myPid());
    }

    public static void a(Collection<InterfaceC0218a> collection) {
        a(Process.myPid(), collection);
    }

    public static void a(int i2, Collection<InterfaceC0218a> collection) {
        LinkedList linkedList = new LinkedList(collection);
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(String.format(Locale.ENGLISH, "/proc/%d/maps", Integer.valueOf(i2))));
                do {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = readLine.split(LangUtils.SINGLE_SPACE);
                        if (split.length > 0) {
                            Iterator it = linkedList.iterator();
                            while (it.hasNext()) {
                                if (((InterfaceC0218a) it.next()).a(split)) {
                                    it.remove();
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        Iterator it2 = linkedList.iterator();
                        while (it2.hasNext()) {
                            ((InterfaceC0218a) it2.next()).a(e);
                        }
                        g.a(bufferedReader);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        g.a(bufferedReader);
                        throw th;
                    }
                } while (linkedList.size() != 0);
                Iterator<InterfaceC0218a> it3 = collection.iterator();
                while (it3.hasNext()) {
                    it3.next().b();
                }
                g.a(bufferedReader2);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String a(int i2) {
        boolean z;
        HashSet hashSet = new HashSet();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(String.format(Locale.ENGLISH, "/proc/%d/maps", Integer.valueOf(i2))));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String[] split = readLine.split(LangUtils.SINGLE_SPACE);
                    if (split.length > 0) {
                        String str = split[split.length - 1];
                        if (str.endsWith(".so") && str.startsWith("/data") && !str.contains(f.f19954c)) {
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
                } catch (Exception unused) {
                    bufferedReader = bufferedReader2;
                    g.a(bufferedReader);
                    return hashSet.toString();
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    g.a(bufferedReader);
                    throw th;
                }
            }
            g.a(bufferedReader2);
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return hashSet.toString();
    }
}
