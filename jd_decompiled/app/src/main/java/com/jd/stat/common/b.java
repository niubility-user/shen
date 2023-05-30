package com.jd.stat.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jump.OpenAppConstant;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b {
    private static long a;
    private static JSONObject b = new JSONObject();

    /* renamed from: c  reason: collision with root package name */
    private static String[] f6981c = {"openapp.jdmobile", OpenAppConstant.SCHEME_OPENAPP_1, "openjd", "jdpayopen", "jdpay"};
    private static String[] d = {"com.eonsun.root", "com.qihoo.permmgr", "com.firstapq", "cocoon.crimson.roots", "com.hostei.exsetup", "com.gmd.immersive", "com.goodmooddroid.gesturecontrol", "com.hroot.www.hrootnews", "com.kingroot.kinguser", "com.cafeteam.user", "chili.app.mi6tool", "com.pky.mifontinstaller", "com.kdfly.miuihelper", "app.greyshirts.sslcapture", "com.speedsoftware.rootexplorer", "com.apkol.root", "com.rootuninstaller.pro", "com.mgyun.shua.su", "com.cpu82.roottoolcase", "com.juxian.jxbestroot", "com.shuame.rootgenius", "com.myrootexplorer", "com.NBRootwifl.urtws", "com.vroot.hyn", "com.zhiqupk.root", "com.corner23.android.universalandroot", "com.skeleton.root", "com.fansapk.rootex", "com.xxnoroot", "com.jianjia.firewall", "cn.com.opda.android.update", "com.flamingo.cloudmachine", "com.baiyi_mobile.easyroot", "mobi.bihu.recovery", "com.qitu.market", "com.wmshua.wmroot", "cn.hd.mobileforce", "com.screeclibinvoke", "shouyou.luping.jingl", "com.smart.swkey", "com.kiuasgames.rootworld", "com.baidu.easyroot", "com.wksall", "com.genymotion.superuser", "com.qihoo.magic", "com.example.manyopen", "com.parallelspace.multipleaccounts.appclone", "com.excelliance.dualaid.vend", "com.duv.example", "com.wifi.box", "com.godinsec.godinsec_private_space", "com.lylm.dkzs", "com.zeyilinxin.wavatar", "com.youtang.youtang_private_space_multi", "com.mi.multifission", "com.sheep2.dkfs", "com.qihoo.magic.xposed", "com.cn.doubleapp", "com.rinzz.avatar", "com.excelliance.dualaid", "com.ssapk.smartapp", "com.wqhy.qqdkb", "com.bly.dkplat", "com.example.readidtest", "com.magic.app.reader01", "com.tyzhzxl.wxdkdsqd04", "com.tom.kaikaikai", "com.ludashi.dualspace", "com.rinzz.avataryouzi", "com.leaves.mulopen", "com.rinzz.wdf", "com.plda.dualapp", "com.sheep2.xyfs", "com.smallyin.Avaassis", "com.xuanmutech.fenkai", "com.jtjsb.yingyongfenshen", "com.huihui.multopen", "com.qihoo.magic_mutiple", "com.weishang.sfduokai", "com.keyuanmeng.wsfs", "cn.fingera.mutlapp", "com.tools.vaclone", "com.juying.Jixiaomi.fenshen", "com.svm.proteinbox_multi", "com.xunrui.gamesaggregator", "cn.com.vapp.nxfs", "io.virtualapp.luohe", "com.meta.app.fenshen", "com.multi.app", "com.soft.apk008v", "com.cyzen.toolbox", "com.lmiot.autotool", "com.omarea.vboot", "com.VTechno.androididchanger", "com.shyl.artifact", "com.doubee.ig", "name.caiyao.sporteditor", "zpp.wjy.xxsq", "com.placering51.wap", "com.lerist.fakelocation", "com.hy.hywatch.t1", "com.rong.xposed.fakelocation", "com.finger.location", "com.xingkong.kilolocation", "com.dingweizshou", "com.deniu.daniu", "com.txy.anywhere", "com.placeringqp.wap", "com.cxjz.kaitian.locationsssh", "com.yy.monidingwei", "net.wecare.home", "com.dtyh.locationfriend", "com.xiaoqiang.location", "com.ctbri.smartgraze", "com.cc.meader.xdfree.locate", "com.unking.weiguanai", "com.dhuiwqna.xewqqem", "com.kk.dingwei", "com.zhongan.papa", "com.pho.course", "com.kmflocation.dn", "com.findhim", "com.yy.kaitian.phonelocationfifth", "com.gps.appnew3", "com.yy.kaitian.phonelocationone", "com.ppkj.ppmonitor", "com.xcloudtech.locate", "com.lylm.virtualposition", "com.chinamobile.iot.find", "com.node.locationtrace", "com.akuan.friend", "xyz.wehere", "com.app.aenson", "cn.chinachip.qqwatch", "com.seeworld.immediateposition", "com.imibean.client", "com.gps.location.gpstracker", "com.yitong.pandabus.jingwei", "com.sadjoke.fake", "io.xudwoftencentmm", "io.xudwofdd", "com.felix.dingmock", "com.xiaoya.xndw", "com.perfay.blackcat.ui", "de.robv.android.xposed.installer", "com.devadvance.rootcloak2", "just.trust.me", "io.va.exposed", "me.weishu.exp", "com.topjohnwu.magisk", "org.itxtech.daedalus", "com.ansoft.utilitybox", "zone.bytesreverser.xposeddeviceidmaskerfree", "li.lingfeng.ltweaks", "com.bigsing.fakemap", "com.gnufabio.simeditor", "com.fakemygps.android", "eu.faircode.xlua", "com.thermatk.android.xf.fakegapps", "org.autojs.autojs", "com.tsdev.android", "com.touchspriteent.android", "com.touchsprite.android", "com.duokaiqi.virtual", "com.bly.dkplat", "com.example.timeshowfloating", "com.stardust.scriptdroid", "io.appium.android.apis", "io.appium.settings", "com.android.development", "jp.co.cyberagent.stf", "yk.juejin", "com.cyjh.mobileanjianen"};

    public static JSONObject a(Context context) {
        return new JSONObject();
    }

    public static JSONObject b(Context context) {
        Set<String> z = com.jd.stat.security.d.a().z();
        if (z != null && z.size() != 0) {
            return new JSONObject();
        }
        if (b == null) {
            b = new JSONObject();
        }
        try {
            b.put("appCount", "");
            b.put("appList", "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return b;
    }

    public static String c(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            PackageManager packageManager = context.getPackageManager();
            int i2 = 0;
            while (true) {
                String[] strArr = d;
                if (i2 >= strArr.length) {
                    break;
                }
                a(packageManager, strArr[i2], sb);
                i2++;
            }
            Set<String> D = com.jd.stat.security.d.a().D();
            if (D != null && !D.isEmpty()) {
                Iterator<String> it = D.iterator();
                while (it.hasNext()) {
                    a(packageManager, it.next(), sb);
                }
            }
        } catch (Exception unused) {
        }
        return sb.toString();
    }

    public static String d() {
        if (Build.VERSION.SDK_INT >= 26) {
            return "00";
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add("minicap");
        linkedList.add("minitouch");
        return com.jd.stat.common.process.b.a(linkedList);
    }

    public static int e(Context context) {
        return !com.jd.stat.security.d.a().t() ? -1 : -2;
    }

    public static JSONObject f(Context context) {
        if (!com.jd.stat.security.d.a().u()) {
            return new JSONObject();
        }
        try {
            HashMap hashMap = new HashMap();
            for (PackageInfo packageInfo : new ArrayList()) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                String str = packageInfo.packageName;
                if (TextUtils.equals("com.android.settings", str)) {
                    String a2 = a(context, str);
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("pg", str);
                        jSONObject.put("sg", a2);
                        com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "system settings = " + jSONObject);
                        return jSONObject;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (TextUtils.equals("com.android.bluetooth", str)) {
                    String a3 = a(context, str);
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("pg", str);
                        jSONObject2.put("sg", a3);
                        com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "system bluetooth = " + jSONObject2);
                        return jSONObject2;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (TextUtils.equals("com.android.webview", str)) {
                    String a4 = a(context, str);
                    try {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("pg", str);
                        jSONObject3.put("sg", a4);
                        com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "system webview = " + jSONObject3);
                        return jSONObject3;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                if (a(applicationInfo)) {
                    com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "system app pName = " + str);
                    String a5 = a(context, str);
                    if (hashMap.containsKey(a5)) {
                        List list = (List) hashMap.get(a5);
                        list.add(str);
                        hashMap.put(a5, list);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(str);
                        hashMap.put(a5, arrayList);
                    }
                }
            }
            try {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry entry : hashMap.entrySet()) {
                    hashMap2.put(entry.getKey(), Integer.valueOf(((List) entry.getValue()).size()));
                }
                Iterator<Map.Entry<String, Integer>> it = a(hashMap2).entrySet().iterator();
                if (it.hasNext()) {
                    String key = it.next().getKey();
                    String str2 = "";
                    List list2 = (List) hashMap.get(key);
                    if (list2 != null && !list2.isEmpty()) {
                        str2 = (String) list2.get(0);
                    }
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("pg", str2);
                    jSONObject4.put("sg", key);
                    com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "system 444444 = " + jSONObject4);
                    return jSONObject4;
                }
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "getAppList: end");
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        return new JSONObject();
    }

    private static void a(StringBuilder sb, String str, PackageInfo packageInfo, String str2) {
        sb.append(str);
        sb.append(str2);
        sb.append(packageInfo.versionName);
        sb.append(str2);
        sb.append(packageInfo.packageName);
        sb.append(str2);
        sb.append(com.jd.stat.common.b.g.a(packageInfo.firstInstallTime));
        sb.append(str2);
        sb.append(com.jd.stat.common.b.g.a(packageInfo.lastUpdateTime));
        sb.append(str2);
    }

    private static void a(PackageManager packageManager, String str, StringBuilder sb) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 8192);
            String charSequence = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            a(sb, charSequence, packageInfo, "###");
            sb.append("0");
            sb.append("$$$");
            com.jd.stat.common.b.b.b("appName ====== " + charSequence + "; suspPackage ====== " + ((Object) sb));
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", th);
        }
    }

    public static int d(Context context) {
        return !com.jd.stat.security.d.a().t() ? -1 : -2;
    }

    public static String b() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = com.jd.stat.security.c.a.getPackageManager().resolveActivity(intent, 0);
        return resolveActivity == null ? "" : com.jd.stat.common.b.g.g(resolveActivity.activityInfo.packageName);
    }

    public static String c() {
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = com.jd.stat.security.d.a().C().iterator();
        while (it.hasNext()) {
            File file = new File(it.next());
            if (file.exists()) {
                linkedList.add(file.getName());
            }
        }
        return com.jd.stat.common.b.g.a("###", linkedList);
    }

    public static Pair<String, String> a() {
        Intent intent = new Intent();
        Uri.Builder builder = new Uri.Builder();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String str : f6981c) {
            intent.setData(builder.scheme(str).build());
            List<ResolveInfo> queryIntentActivities = com.jd.stat.security.c.a.getPackageManager().queryIntentActivities(intent, 0);
            com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "queryIntentActivities called.");
            Iterator<ResolveInfo> it = queryIntentActivities.iterator();
            while (it.hasNext()) {
                String str2 = it.next().activityInfo.packageName;
                if (!TextUtils.equals(jd.wjlogin_sdk.util.f.f19954c, str2)) {
                    sb.append(str2);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                    sb2.append(str);
                    sb2.append(DYConstants.DY_REGEX_COMMA);
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb2.length() > 0) {
            sb2.deleteCharAt(sb2.length() - 1);
        }
        return new Pair<>(sb2.toString(), sb.toString());
    }

    public static Signature[] b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException unused) {
            com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", str + " not installed!");
            return null;
        }
    }

    private static String b(byte[] bArr) {
        try {
            return a(MessageDigest.getInstance("SHA1").digest(bArr));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static boolean a(ApplicationInfo applicationInfo) {
        int i2 = applicationInfo.flags;
        return ((i2 & 128) == 0 && (i2 & 1) == 0) ? false : true;
    }

    private static Map<String, Integer> a(HashMap<String, Integer> hashMap) {
        ArrayList arrayList = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: com.jd.stat.common.b.1
            @Override // java.util.Comparator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                return entry.getValue().intValue() - entry2.getValue().intValue();
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!arrayList.isEmpty()) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Map.Entry entry = (Map.Entry) arrayList.get(i2);
                if (i2 == arrayList.size() - 1) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return linkedHashMap;
    }

    public static String a(Context context, String str) {
        Signature[] b2;
        if (TextUtils.isEmpty(str)) {
            com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "pkName is null");
            return "";
        } else if (context == null) {
            com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "context is null");
            return "";
        } else {
            try {
                b2 = b(context, str);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (b2 != null && b2.length != 0) {
                Signature signature = b2[0];
                if (signature != null) {
                    String b3 = b(signature.toByteArray());
                    com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "getAppSign: signatureStr = " + b3);
                    return b3;
                }
                return "";
            }
            com.jd.stat.common.b.b.b("JDMob.AppAndFileGetter", "sign is null");
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toLowerCase());
        }
        return sb.toString();
    }
}
