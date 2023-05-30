package com.jingdong.common.xbridge;

import android.text.TextUtils;
import android.util.Base64;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.jdmiaosha.preload.BottomNavigationConstant;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.security.JDUUIDEncHelper;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Random;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class AppUnitePlugin implements IBridgePlugin {
    private String encryptAddr(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String trim = Base64.encodeToString(str.getBytes("utf-8"), 2).trim();
            int length = trim.length() - 2;
            if (length <= 0) {
                return "";
            }
            int nextInt = new Random().nextInt(5) + 5;
            return (getRandomString(nextInt) + trim.substring(0, length) + nextInt + trim.substring(length)).trim();
        } catch (Exception unused) {
            return "";
        }
    }

    private String getRandomString(int i2) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            int nextInt = random.nextInt(3);
            if (nextInt == 0) {
                sb.append((char) Math.round((Math.random() * 25.0d) + 65.0d));
            } else if (nextInt == 1) {
                sb.append((char) Math.round((Math.random() * 25.0d) + 97.0d));
            } else if (nextInt == 2) {
                sb.append(new Random().nextInt(10));
            }
        }
        return sb.toString();
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        JDJSONArray parseArray;
        if ("getAppInfo".equals(str)) {
            if (TextUtils.isEmpty(str2)) {
                iBridgeCallback.onError("params is empty!");
            }
            try {
                parseArray = JDJSON.parseArray(str2);
            } catch (Exception e2) {
                iBridgeCallback.onError("parse error :" + e2.getMessage());
            }
            if (parseArray != null && parseArray.size() != 0) {
                JSONObject jSONObject = new JSONObject();
                for (Object obj : parseArray.toArray()) {
                    if (obj instanceof String) {
                        if ("uuid".equals(obj)) {
                            jSONObject.put("eufv", "1");
                        }
                        jSONObject.put((String) obj, get((String) obj, iBridgeWebView));
                    }
                }
                iBridgeCallback.onSuccess(jSONObject);
                return true;
            }
            iBridgeCallback.onError("key length is 0 ! ");
            return true;
        }
        return false;
    }

    public Object get(String str, IBridgeWebView iBridgeWebView) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1940613496:
                if (str.equals("networkType")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1914421335:
                if (str.equals("systemVersion")) {
                    c2 = 1;
                    break;
                }
                break;
            case -792929080:
                if (str.equals(Configuration.PARTNER)) {
                    c2 = 2;
                    break;
                }
                break;
            case -296308109:
                if (str.equals("un_area")) {
                    c2 = 3;
                    break;
                }
                break;
            case 96572:
                if (str.equals("aid")) {
                    c2 = 4;
                    break;
                }
                break;
            case 3403373:
                if (str.equals("oaid")) {
                    c2 = 5;
                    break;
                }
                break;
            case 3601339:
                if (str.equals("uuid")) {
                    c2 = 6;
                    break;
                }
                break;
            case 93997959:
                if (str.equals(JDNetCacheManager.BRAND_BIZKEY)) {
                    c2 = 7;
                    break;
                }
                break;
            case 104069929:
                if (str.equals(CustomThemeConstance.NAVI_MODEL)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 642691418:
                if (str.equals(BottomNavigationConstant.KEY_SYSTEM_NAME)) {
                    c2 = '\t';
                    break;
                }
                break;
            case 1141082317:
                if (str.equals("appBuild")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 1197075732:
                if (str.equals("cAddressGlobal")) {
                    c2 = 11;
                    break;
                }
                break;
            case 1484112759:
                if (str.equals("appVersion")) {
                    c2 = '\f';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return WebUtils.getNetworkType();
            case 1:
                return BaseInfo.getAndroidVersion();
            case 2:
                return Configuration.getProperty(Configuration.PARTNER);
            case 3:
                return LocManager.getCommonLbsParameter();
            case 4:
                return BaseInfo.getAndroidId();
            case 5:
                return BaseInfo.getOAID();
            case 6:
                JDUUIDEncHelper.EncryptResult encrypt = JDUUIDEncHelper.encrypt(StatisticsReportUtil.readDeviceUUID());
                if (encrypt != null) {
                    return encrypt.eu + "-" + encrypt.fv;
                }
                return "-";
            case 7:
                return BaseInfo.getDeviceBrand();
            case '\b':
                return BaseInfo.getDeviceModel();
            case '\t':
                return Configuration.getProperty("client");
            case '\n':
                return String.valueOf(BaseInfo.getAppVersionCode());
            case 11:
                String jSONObject = WebUtils.getAddressGlobalWithBaseSceneId() != null ? WebUtils.getAddressGlobalWithBaseSceneId().toOrderStr().toString() : "";
                if (iBridgeWebView != null) {
                    WebUtils.reportDeprecatedLBSBridge(WebUtils.getJDWebView(iBridgeWebView.getView()), "AppUnitePlugin#getAppInfo#cAddressGlobal");
                }
                return encryptAddr(jSONObject);
            case '\f':
                return BaseInfo.getAppVersionName();
            default:
                return "";
        }
    }
}
