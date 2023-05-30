package com.jd.jdsec.a.j;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {
    private static String a = "eventId,refer,brand,devicename,cpuid,cputype,cpufrequency,freediskspace,mcc,mnc,country,timeZone,ssid,bssid,isoCountryCode,rssi,linkSpeed,routerIp,dns1,dns2,netmask,makePhoneAvailable,magnetometerAvailable,headingAvailable,frontCamera,backCamera,headsetOn,nfcEnable,screenScale,sdCid,btMac,simSerialNumber,dpi,abi,bluetoothName,wifiList,ua,processcount,processlist,appcount,af,sof,sl,rc,isOnThePhone,vpnConnect,batteryLevel,batteryVoltage,batteryStatus,batteryHealth,brightness,wifiEnable,network,emulator,isRoot,isHooked,isMoreOpen,isDebug,isCloudEnv,isModifier,isMalicious,extkey,client,clientversion,osVersion,unionId,subunionId,androidId,imei,memSize,model,bootloader,display,isPipeExist,isQEmuDriverExist,board,device,sensors,hardware,carrierName,fingerprint,fcgn-brand,fcgn-fingerprint,vapp,maxCpuFrequency,minCpuFrequency,screen,platform,totalDiskSpace,slan,ulan,buildInfo,Lock_awake_receiver,ipAddress,lach,physicalcpu,xtbb,mobileCountryCode,DNS,fcgn-ncpu,osversiontags,wifissidlist,wifilinkspeed,isfakegps,haspipes,firstinstalltime,lastupdatetime,appname,packagename,signaturehash,manufacture,lastoaid,oaid,radioversion,realscreensize,romname,statusbarheight,wifirssi,fingerprintavailable,gpsavailable,hasqemudriverfile,hassdcard";

    private static List a() {
        try {
            return com.jd.jdsec.a.l.e.e(new JSONArray(com.jd.jdsec.a.l.d.c("fixInfo-old", "")));
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    private static JSONArray b(String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            String[] split = str.replace("[", DYConstants.DY_REGEX_COMMA).replace("]", DYConstants.DY_REGEX_COMMA).replace(DYConstants.DY_REGEX_COMMA, "").split(LangUtils.SINGLE_SPACE, Integer.MAX_VALUE);
            Pattern compile = Pattern.compile("[0-9]*");
            JSONArray jSONArray2 = new JSONArray();
            for (int i2 = 0; i2 < split.length; i2++) {
                if (!compile.matcher(split[i2]).matches()) {
                    if (split[i2] != null && !split[i2].equals("")) {
                        jSONArray2.put(split[i2]);
                    }
                    jSONArray2.put("0");
                } else {
                    if (jSONArray2.length() > 0) {
                        jSONArray.put(jSONArray2.join(DYConstants.DY_REGEX_COMMA));
                    }
                    jSONArray2 = new JSONArray();
                }
            }
            if (jSONArray.length() < 60) {
                c(jSONArray);
            }
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "procCurlyFreqData: " + e2.getMessage());
        }
        return jSONArray;
    }

    private static JSONArray c(JSONArray jSONArray) {
        for (int i2 = 0; i2 < 60; i2++) {
            try {
                if (i2 >= jSONArray.length()) {
                    jSONArray.put("0");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONArray;
    }

    private static void d(JSONArray jSONArray, List list) {
        try {
            c cVar = new c();
            float a2 = cVar.a("0");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    String valueOf = String.valueOf(jSONArray.opt(i2));
                    if (valueOf.equals("0")) {
                        list.add(Float.valueOf(a2));
                    } else {
                        list.add(Float.valueOf(cVar.a(valueOf)));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "featureEncodingData: " + e3.getMessage());
        }
    }

    public static void e(JSONObject jSONObject) {
        String str;
        String optString;
        com.jd.jdsec.a.l.d.e("fixInfo-new", "");
        try {
            JSONArray jSONArray = new JSONArray();
            float a2 = new c().a("0");
            String[] split = a.split(DYConstants.DY_REGEX_COMMA, Integer.MAX_VALUE);
            for (int i2 = 0; i2 < split.length; i2++) {
                try {
                    str = split[i2];
                    optString = jSONObject.optString(str);
                } catch (Exception e2) {
                    com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "fix\u5b58\u50a8\u65f6Float.parseFloat\u9519\u8bef: " + e2.getMessage());
                    jSONArray.put(i2, (double) a2);
                }
                if (!optString.equals("") && !optString.equals("NaN")) {
                    if ("unionId,memSize,maxCpuFrequency,minCpuFrequency,totalDiskSpace,physicalcpu,batteryLevel,cpufrequency,freediskspace,dpi,brightness".contains(str)) {
                        if (optString.contains("GHz")) {
                            optString = optString.replace("GHz", "");
                        }
                        jSONArray.put(i2, Float.parseFloat(optString));
                    } else {
                        jSONArray.put(i2, r6.a(optString));
                    }
                }
                jSONArray.put(i2, a2);
            }
            com.jd.jdsec.a.l.d.e("fixInfo-new", jSONArray.toString());
        } catch (Exception e3) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "fix\u5b58\u50a8\u65f6\u9519\u8bef: " + e3.getMessage());
        }
    }

    private static List f() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 8; i2++) {
            try {
                String c2 = com.jd.jdsec.a.l.d.c("List" + i2, "");
                if (!c2.equals("") && !c2.equals("[]")) {
                    if (i2 != 2 && i2 != 3) {
                        if (i2 != 6 && i2 != 7) {
                            d(g(c2), arrayList);
                        }
                        h(g(c2), arrayList);
                    } else {
                        d(b(c2), arrayList);
                    }
                }
                com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "\u9ad8\u9891\u6570\u636e\u4e3a\u7a7a");
                return new ArrayList();
            } catch (Exception e2) {
                com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "getHighFreqInSp" + e2.getMessage());
            }
        }
        return arrayList;
    }

    private static JSONArray g(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            String[] split = str.replace("[", "").replace("]", "").replace(DYConstants.DY_REGEX_COMMA, "").split(LangUtils.SINGLE_SPACE, Integer.MAX_VALUE);
            for (int i2 = 0; i2 < split.length; i2++) {
                if (split[i2].equals("")) {
                    split[i2] = "0";
                }
                if (i2 % 2 == 1) {
                    if (split[i2].length() == 0) {
                        sb.append("0");
                    } else {
                        sb.append(split[i2]);
                    }
                    sb.append(DYConstants.DY_REGEX_COMMA);
                }
            }
            JSONArray jSONArray = new JSONArray((Collection) Arrays.asList(sb.toString().split(DYConstants.DY_REGEX_COMMA)));
            c(jSONArray);
            return jSONArray;
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "procHighFreqData: " + e2.getMessage());
            return null;
        }
    }

    private static void h(JSONArray jSONArray, List list) {
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            Object opt = jSONArray.opt(i2);
            if (opt instanceof Float) {
                list.add(opt);
            } else {
                try {
                    list.add(Float.valueOf(Float.parseFloat(String.valueOf(opt))));
                } catch (Exception unused) {
                    list.add(Float.valueOf(0.0f));
                }
            }
        }
    }

    public static float[] i() {
        List a2;
        List f2;
        ArrayList arrayList = new ArrayList();
        float[] fArr = new float[602];
        try {
            a2 = a();
            f2 = f();
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.DataHandler", "\u6a21\u578b\u5165\u53c2-error" + e2.getMessage());
        }
        if (!a2.isEmpty() && !f2.isEmpty()) {
            arrayList.addAll(a2);
            arrayList.addAll(f2);
            com.jd.jdsec.a.l.e.g(arrayList, fArr);
            com.jd.jdsec.a.l.b.e("feature encoding\u540elist\u662f", Arrays.toString(fArr));
            com.jd.jdsec.a.l.b.e("feature encoding\u540elist\u7684\u957f\u5ea6\u662f", String.valueOf(fArr.length));
            return fArr;
        }
        return null;
    }
}
