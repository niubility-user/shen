package com.jd.android.sdk.coreinfo.deviceUtil;

import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.util.CommonUtil;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class c {
    private static Map<String, String> a = null;
    private static String b = "";

    public static String a() {
        if (TextUtils.isEmpty(b)) {
            String a2 = a("ro.miui.ui.version.name");
            if (!TextUtils.isEmpty(a2) && !"".equals(a2)) {
                b = "XiaoMi/MIUI/".concat(String.valueOf(a2));
            } else {
                String a3 = a("ro.build.version.emui");
                if (!TextUtils.isEmpty(a3) && !"".equals(a3)) {
                    b = "HuaWei/EMOTION/".concat(String.valueOf(a3));
                } else {
                    String a4 = a("ro.lenovo.series");
                    if (!TextUtils.isEmpty(a4) && !"".equals(a4)) {
                        b = "Lenovo/VIBE/" + a("ro.build.version.incremental");
                    } else {
                        String a5 = a("ro.build.nubia.rom.name");
                        if (!TextUtils.isEmpty(a5) && !"".equals(a5)) {
                            b = "Zte/NUBIA/" + a5 + CartConstant.KEY_YB_INFO_LINK + a("ro.build.nubia.rom.code");
                        } else {
                            String a6 = a("ro.meizu.product.model");
                            if (!TextUtils.isEmpty(a6) && !"".equals(a6)) {
                                b = "Meizu/FLYME/" + a("ro.build.display.id");
                            } else {
                                String a7 = a("ro.build.version.opporom");
                                if (!TextUtils.isEmpty(a7) && !"".equals(a7)) {
                                    b = "Oppo/COLOROS/".concat(String.valueOf(a7));
                                } else {
                                    String a8 = a("ro.vivo.os.build.display.id");
                                    if (!TextUtils.isEmpty(a8) && !"".equals(a8)) {
                                        b = "vivo/FUNTOUCH/".concat(String.valueOf(a8));
                                    } else {
                                        String a9 = a("ro.aa.romver");
                                        if (!TextUtils.isEmpty(a9) && !"".equals(a9)) {
                                            b = "htc/" + a9 + "/" + a("ro.build.description");
                                        } else {
                                            String a10 = a("ro.lewa.version");
                                            if (!TextUtils.isEmpty(a10) && !"".equals(a10)) {
                                                b = "tcl/" + a10 + "/" + a("ro.build.display.id");
                                            } else {
                                                String a11 = a("ro.gn.gnromvernumber");
                                                if (!TextUtils.isEmpty(a11) && !"".equals(a11)) {
                                                    b = "amigo/" + a11 + "/" + a("ro.build.display.id");
                                                } else {
                                                    String a12 = a("ro.build.tyd.kbstyle_version");
                                                    if (!TextUtils.isEmpty(a12) && !"".equals(a12)) {
                                                        b = "dido/".concat(String.valueOf(a12));
                                                    } else {
                                                        b = a("ro.build.fingerprint") + "/" + a("ro.build.rom.moduleID");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return CommonUtil.ensureNotEmpty(b);
    }

    private static String a(String str) {
        if (str.trim().equals("")) {
            return "";
        }
        if (a == null) {
            a = new HashMap();
            ArrayList<String> a2 = a(new String[]{"/system/bin/sh", "-c", "getprop"});
            if (a2 != null && a2.size() > 0) {
                Logger.d("CoreInfo.RomNameUtil", "Successfully get 'getprop' list.");
                Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                Iterator<String> it = a2.iterator();
                while (it.hasNext()) {
                    Matcher matcher = compile.matcher(it.next());
                    if (matcher.find()) {
                        a.put(matcher.group(1), matcher.group(2));
                    }
                }
                Logger.d("CoreInfo.RomNameUtil", "System properties number: " + a.size());
            }
        }
        return a.containsKey(str) ? a.get(str) : "";
    }

    private static ArrayList<String> a(String[] strArr) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = null;
                }
            }
            bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                try {
                    String readLine2 = bufferedReader2.readLine();
                    if (readLine2 != null) {
                        arrayList.add(readLine2);
                    } else {
                        try {
                            break;
                        } catch (IOException e2) {
                            Logger.e("CoreInfo.RomNameUtil", "RomNameUtil.exec() exception", e2);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        Logger.e("CoreInfo.RomNameUtil", "RomNameUtil.exec() exception", th);
                        return null;
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                Logger.e("CoreInfo.RomNameUtil", "RomNameUtil.exec() exception", e3);
                            }
                        }
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e4) {
                                Logger.e("CoreInfo.RomNameUtil", "RomNameUtil.exec() exception", e4);
                            }
                        }
                    }
                }
            }
            bufferedReader.close();
            try {
                bufferedReader2.close();
            } catch (IOException e5) {
                Logger.e("CoreInfo.RomNameUtil", "RomNameUtil.exec() exception", e5);
            }
            try {
                bufferedReader.close();
            } catch (IOException e6) {
                Logger.e("CoreInfo.RomNameUtil", "RomNameUtil.exec() exception", e6);
            }
            try {
                bufferedReader2.close();
            } catch (IOException e7) {
                Logger.e("CoreInfo.RomNameUtil", "RomNameUtil.exec() exception", e7);
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            bufferedReader2 = null;
        }
    }
}
