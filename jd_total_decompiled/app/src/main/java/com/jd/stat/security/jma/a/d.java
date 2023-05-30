package com.jd.stat.security.jma.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.react.uimanager.ViewProps;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.stat.common.MonitorService;
import com.jd.stat.common.NativeInfo;
import com.jd.stat.common.j;
import com.jd.stat.common.k;
import com.jd.stat.common.m;
import com.jd.stat.common.n;
import com.jd.stat.common.r;
import com.jd.stat.common.s;
import com.jd.stat.common.t;
import com.jd.stat.common.u;
import com.jd.stat.common.v;
import com.jd.stat.common.w;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.connect.common.Constants;
import com.tencent.map.geolocation.TencentLocationListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"MissingPermission"})
/* loaded from: classes18.dex */
public class d extends b {
    private boolean c(String str) {
        com.jd.stat.security.b d;
        if (TextUtils.isEmpty(a())) {
            return false;
        }
        String b = b();
        if (!TextUtils.isEmpty(b) && (d = com.jd.stat.security.d.a().d(b)) != null && TextUtils.equals(d.e(), a()) && d.d() == 2) {
            String str2 = "fix:" + str;
            if (d.a(str2)) {
                return com.jd.stat.security.c.a(str2, a());
            }
            return false;
        }
        return false;
    }

    @Override // com.jd.stat.security.jma.a.g
    public JSONObject a(Context context) {
        com.jd.stat.common.b.e eVar = new com.jd.stat.common.b.e("fix");
        try {
            eVar.put(HybridSDK.OS_VERSION, Build.VERSION.RELEASE);
            eVar.put("screen", n.e(context));
            eVar.put("frontCameraAvailable", n.e());
            eVar.put("rearCameraAvailable", n.d());
            eVar.put("hasSDcard", n.f());
            eVar.put("isQEmuDriverExist", n.h());
            eVar.put("isPipeExist", n.g());
            eVar.put("tags", Build.TAGS);
            eVar.put("board", Build.BOARD);
            eVar.put("bootloader", Build.BOOTLOADER);
            eVar.put(NoStockRecommendHead.DEVICE, BaseInfo.getDeviceName());
            eVar.put(ViewProps.DISPLAY, BaseInfo.getOSName());
            eVar.put("fingerprint", BaseInfo.getOSFingerprint());
            eVar.put("hardware", Build.HARDWARE);
            eVar.put("sdkLevel", Build.VERSION.SDK_INT);
            eVar.put(TencentLocationListener.RADIO, n.x());
            eVar.put("sdCid", n.b());
            eVar.put("totalDiskSpace", n.b(context));
            eVar.put("memSize", n.c(context));
            eVar.put("wifiMac", c("wifiMac") ? BaseInfo.getWifiMacAddress() : com.jingdong.jdsdk.a.a.a);
            eVar.put("btMac", t.d(context));
            eVar.put("imei", com.jingdong.jdsdk.a.a.a);
            eVar.put("imsi", com.jd.stat.common.a.a.a(R2.drawable.button_q_01).e());
            eVar.put("imeiAndMeid", com.jingdong.jdsdk.a.a.a);
            eVar.put("cpuId", "");
            eVar.put("maxCpuFrequency", n.j());
            eVar.put("minCpuFrequency", n.k());
            eVar.put("cpuType", n.c());
            eVar.put("carrierName", BaseInfo.getNetworkOperatorName(context));
            eVar.put("phoneNumber", com.jd.stat.common.a.a.a(R2.drawable.button_p_right).e());
            eVar.put("sensors", n.w());
            eVar.put("buildInfo", n.n());
            eVar.put("macId", t.a(context));
            eVar.put("ipAddress", n.m());
            eVar.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
            eVar.put("mobileCountryCode", com.jd.stat.common.a.a.a(R2.drawable.button_p_02).e());
            eVar.put("mobileNetworkCode", com.jd.stat.common.a.a.a(8194).e());
            eVar.put("rearCameraFlashAvailable", n.h(context));
            eVar.put("isoCountryCode", com.jd.stat.common.a.a.a(R2.drawable.button_p_left_normal).e());
            eVar.put("canSendMail", true);
            eVar.put("appBundleIdentifier", context.getPackageName());
            eVar.put(Constants.PARAM_PLATFORM, BaseInfo.getDeviceModel());
            eVar.put("deviceName", BaseInfo.getDeviceName());
            eVar.put("currentTime", com.jd.stat.common.b.g.a());
            eVar.put("multiTouch", n.i(context));
            eVar.put("serial", BaseInfo.getHardwareSerialNo());
            eVar.put("simSerialNumber", com.jd.stat.common.a.a.a(R2.drawable.button_p_left_selected).e());
            eVar.put("physicalCpu", n.l());
            eVar.put("isRoot", n.p());
            eVar.put("rootConfirm", com.jd.stat.security.jma.a.a.d.a());
            eVar.put("rootSuspicious", com.jd.stat.security.jma.a.a.d.a(context));
            eVar.put("technology", MonitorService.f().b());
            String property = System.getProperty("java.vm.version");
            if (Integer.valueOf(property.substring(0, property.indexOf(OrderISVUtil.MONEY_DECIMAL))).intValue() >= 2) {
                eVar.put("javaModifierAbnormal", new JSONArray());
            } else {
                eVar.put("javaModifierAbnormal", com.jd.stat.security.jma.a.a.b.a());
            }
            eVar.put("virtualMemoryAbnormal", com.jd.stat.security.jma.a.a.b.a(context));
            eVar.put("hookmoduleDetect", com.jd.stat.security.jma.a.a.b.b(context));
            eVar.put("fileAbnormal", com.jd.stat.security.jma.a.a.b.b());
            eVar.put("wifiRouterMac", c("wifiRouterMac") ? BaseInfo.getWifiBSSID(context) : com.jingdong.jdsdk.a.a.a);
            eVar.put("proxyInfo", n.u());
            eVar.put("md5Infos", com.jd.stat.security.jma.a.a.b.c(context));
            eVar.put("ua", n.k(context));
            eVar.put("Lock_awake_receiver", com.jd.stat.common.c.e(context));
            eVar.put("nfcst", m.a(context));
            eVar.put("figst", m.b(context));
            eVar.put("efig", m.c(context));
            eVar.put("pct", com.jd.stat.common.b.e(context));
            eVar.put("muct", com.jd.stat.common.b.d(context));
            eVar.put("coct", m.d(context));
            eVar.put("sici", com.jd.stat.common.a.a.a(R2.drawable.button_p_right_normal).e());
            eVar.put("siopn", com.jd.stat.common.a.a.a(R2.drawable.button_p_right_selected).e());
            eVar.put("simulator", n.l(context));
            eVar.put("mnq", n.v());
            eVar.put("DNS", com.jingdong.jdsdk.a.a.a);
            eVar.put("rPList", com.jd.stat.common.b.c(context));
            eVar.put("wf", c("wf") ? v.a(context) : new JSONObject());
            eVar.put("jz", com.jingdong.jdsdk.a.a.a);
            eVar.put("vapp", com.jd.stat.common.process.a.a());
            eVar.put("abi", r.a());
            eVar.put("bhost", Build.HOST);
            eVar.put("buser", Build.USER);
            eVar.put(NotificationMessageSummary.B_TYPE, Build.TYPE);
            eVar.put("bid", Build.ID);
            eVar.put("fcgj", com.jd.stat.common.g.a(context));
            eVar.put("fcgn", com.jd.stat.common.g.a());
            eVar.put("oaid", com.jd.stat.security.c.i());
            Pair<String, String> a = com.jd.stat.common.b.a();
            eVar.put("scheme", a.first);
            eVar.put("xybns", a.second);
            eVar.put("lach", com.jd.stat.common.b.b());
            eVar.put("opt", s.a());
            eVar.put("fet", s.b());
            eVar.put("pt", NativeInfo.getProp("ril.subscription.types"));
            String[] a2 = com.jd.stat.common.i.a();
            eVar.put("uid", a2[0]);
            eVar.put(MiaoShaPublicConstants.KEY_GID, a2[1]);
            eVar.put("ctx", a2[2]);
            eVar.put("ispt", NativeInfo.getProp("ro.treble.enabled"));
            eVar.put("sb", BaseInfo.getDeviceManufacture());
            eVar.put("sdkversion", "2.5.8");
            com.jd.stat.common.b.c.a(eVar, w.c());
            eVar.put("slan", m.a());
            eVar.put("ulan", m.b());
            eVar.put("pex", k.a());
            eVar.put("ppac", k.b());
            eVar.put("tnt", k.c());
            eVar.put("networkInfo", k.a(context));
            eVar.put("apmi", MonitorService.f().e());
            eVar.put("ssp", com.jd.stat.common.b.c());
            eVar.put("mcmt", com.jd.stat.common.b.d());
            if (com.jd.stat.security.d.a().L()) {
                eVar.put("libModified", com.jd.stat.security.jma.a.a.b.d());
            }
            eVar.put("rm", n.q());
            eVar.put("xtqm", com.jd.stat.common.b.f(context));
            eVar.put("xtbb", n.y());
            eVar.put("sgt", n.n(context));
            eVar.put("adbe", m.e(context) + "");
            eVar.put("gmsl", com.jd.stat.common.c.d());
            eVar.put("bbd", n.r());
            eVar.put("ovsi", n.s());
            eVar.put("rmdv", n.t());
            eVar.put("atf", com.jd.stat.common.f.a(context));
            eVar.put("ptt", com.jd.stat.security.c.k() ? "1" : "0");
            if (com.jd.stat.security.d.a().O()) {
                eVar.put("hdid", j.a(context));
            }
            eVar.put("drmuuid", u.a());
            eVar.put("wef", u.c());
            String[] d = u.d();
            eVar.put("hrd", d[0]);
            eVar.put("rgf", d[1]);
            eVar.put("jerd", d[2]);
            eVar.put("fmd", u.e());
            eVar.put("frmd", u.f());
            eVar.put("vlmd", u.g());
            eVar.put("slf", u.h());
            eVar.put("cctm", eVar.a());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return eVar;
    }
}
