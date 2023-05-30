package com.jd.manto.d;

import android.content.Context;
import android.text.TextUtils;
import com.jd.manto.map.Tools;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.ui.address.UnAddressConstants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.manto.sdk.api.ITrackReport;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class w implements ITrackReport {
    @Override // com.jingdong.manto.sdk.api.ITrackReport
    public void sendClickData(Context context, Map<String, String> map, Map<String, String> map2) {
        String str;
        JDMtaUtils.getMaInitCommonInfo(context);
        if (context == null || map == null) {
            return;
        }
        String str2 = map.get("page_name");
        String str3 = map.get("eventId");
        String str4 = map.get("eventParam");
        String str5 = map.get("pageID");
        String str6 = map.get("pageParam");
        String str7 = map.get("eventName");
        String str8 = map.get("jsonParam");
        String str9 = map.get("event_func");
        String str10 = map.get("pageClassName");
        String str11 = map.get("nextPageClassName");
        String str12 = map.get("shopID");
        String str13 = map.get("sku");
        String str14 = map.get("sku_tag");
        String str15 = map.get("order_id");
        String str16 = !TextUtils.isEmpty(str10) ? str10 : str2;
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setManto(true);
        jDLocationCacheOption.setSceneId("locService");
        jDLocationCacheOption.setBusinessId(Tools.JD_LOCATION_ID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        HashMap hashMap2 = new HashMap();
        String str17 = "0";
        if (location == null) {
            str = "0";
        } else {
            str = "" + location.getLat();
        }
        hashMap2.put("lat", str);
        if (location != null) {
            str17 = "" + location.getLng();
        }
        hashMap2.put(JDMtaUtils.LON, str17);
        JDMtaUtils.sendClickDataWithExt(context, str3, str4, str9, str5, str16, str6, str11, str8, str12, str15, str13, str14, hashMap, hashMap2);
        OKLog.e("MantoTrackReportImpl", "clickData: " + String.format("page_name:%s, eventId:%s, eventParam:%s, pageID:%s, page_param:%s, eventName:%s,event_func:%s, pageClassName:%s, nextPageClassName:%s, jsonParam:%s, shopID:%s, sku:%s, skuTag:%s", str16, str3, str4, str5, str6, str7, str9, str10, str11, str8, str12, str13, str14));
    }

    @Override // com.jingdong.manto.sdk.api.ITrackReport
    public void sendExposureData(Context context, Map<String, String> map, Map<String, String> map2) {
        String str;
        if (context == null || map == null) {
            return;
        }
        String str2 = map.get("eid");
        String str3 = map.get("ela");
        String str4 = map.get("ctp");
        String str5 = map.get("par");
        String str6 = map.get("page_id");
        String str7 = map.get("jsonParam");
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setManto(true);
        jDLocationCacheOption.setSceneId("locService");
        jDLocationCacheOption.setBusinessId(Tools.JD_LOCATION_ID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        HashMap hashMap2 = new HashMap();
        String str8 = "0";
        if (location == null) {
            str = "0";
        } else {
            str = "" + location.getLat();
        }
        hashMap2.put("lat", str);
        if (location != null) {
            str8 = "" + location.getLng();
        }
        hashMap2.put(JDMtaUtils.LON, str8);
        JDMtaUtils.sendExposureExtend(context, str2, str3, str6, str4, str5, str7, "", "", "", hashMap, hashMap2);
        OKLog.e("MantoTrackReportImpl", "exposure: " + String.format("eid:%s,ela:%s,ctp:%s,par:%s,page_id:%s,jsonParams:%s", str2, str3, str4, str5, str6, str7));
    }

    @Override // com.jingdong.manto.sdk.api.ITrackReport
    public void sendJDOrderInfo(Context context, Map<String, String> map, Map<String, String> map2) {
        String str;
        if (context == null || map == null) {
            return;
        }
        JDMtaUtils.getMaInitCommonInfo(context);
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        String str2 = map.get("sale_ord_id");
        String str3 = map.get("prod_id");
        String str4 = map.get("order_total_fee");
        String str5 = map.get("order_ts");
        String str6 = map.get("quantity");
        String str7 = map.get("sku_tag");
        String str8 = map.get("ord_type");
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setManto(true);
        jDLocationCacheOption.setSceneId("locService");
        jDLocationCacheOption.setBusinessId(Tools.JD_LOCATION_ID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        HashMap hashMap2 = new HashMap();
        String str9 = "0";
        if (location == null) {
            str = "0";
        } else {
            str = "" + location.getLat();
        }
        hashMap2.put("lat", str);
        if (location != null) {
            str9 = "" + location.getLng();
        }
        hashMap2.put(JDMtaUtils.LON, str9);
        JDMtaUtils.sendOrderExtend(context, str2, str4, str3, str6, "", hashMap, str8, hashMap2);
        OKLog.e("MantoTrackReportImpl", "sendJDOrderInfo: " + String.format("sale_ord_id:%s,prod_id:%s,order_total_fee:%s,order_ts:%s,quantity:%s,sku_tag:%s,ord_type:%s", str2, str3, str4, str5, str6, str7, str8));
    }

    @Override // com.jingdong.manto.sdk.api.ITrackReport
    public void sendPagePv(Context context, Map<String, String> map, Map<String, String> map2) {
        String str;
        JDMtaUtils.getMaInitCommonInfo(context);
        String str2 = map.get("page_id");
        String str3 = map.get("page_name");
        String str4 = map.get("page_param");
        String str5 = map.get(UnAddressConstants.INTENT_SHOP_ID);
        OKLog.e("MantoTrackReportImpl", "PV" + String.format("page_id:%s, page_name:%s, page_param:%s, shop_id:%s", str2, str3, str4, str5));
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setManto(true);
        jDLocationCacheOption.setSceneId("locService");
        jDLocationCacheOption.setBusinessId(Tools.JD_LOCATION_ID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        HashMap hashMap2 = new HashMap();
        String str6 = "0";
        if (location == null) {
            str = "0";
        } else {
            str = "" + location.getLat();
        }
        hashMap2.put("lat", str);
        if (location != null) {
            str6 = "" + location.getLng();
        }
        hashMap2.put(JDMtaUtils.LON, str6);
        JDMtaUtils.sendPVExtend(context, str3, str4, str2, !TextUtils.isEmpty(str5) ? str5 : "", "", "", "", hashMap, hashMap2);
    }
}
