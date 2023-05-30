package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.ui.address.UnAddressConstants;
import com.jingdong.jdma.JDMaInterface;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdma.minterface.ClickInterfaceParam;
import com.jingdong.jdma.minterface.OrderInterfaceParam;
import com.jingdong.jdsdk.mta.JDMtaCacheTable;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeMtaReportListener implements NativeMtaReportListener, JDFlutterCall {
    private static final String HAS_PV_EXTEND = "hasPvExtend";
    public static final String MTACHANNEL = "com.jd.jdflutter/mta";
    private static final String TAG = "JDReactNativeMtaReportListener";

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void getJDV(JDCallback jDCallback, JDCallback jDCallback2) {
        if (jDCallback != null) {
            jDCallback.invoke(JDMtaUtils.getJdv());
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("sendClickData")) {
            sendClickData(hashMap);
        } else if (str.equals("sendOrderDataWithExt")) {
            sendOrderDataWithExt(hashMap);
        } else if (str.equals("sendCommonData")) {
            sendCommonData(hashMap);
        } else if (str.equals("sendCommonDataWithExt")) {
            sendCommonDataWithExt(hashMap);
        } else if (str.equals("sendPvData")) {
            sendPvData(hashMap);
        } else if (str.equals("sendVirtualOrderData")) {
            sendVirtualOrderData(hashMap);
        } else if (str.equals("savePageInfoWithSKU")) {
            savePageInfoWithSKU(hashMap);
        } else if (str.equals("sendExposureData")) {
            sendExposureData(hashMap);
        } else if (str.equals("sendClickDataWithJsonParam")) {
            JLog.e(TAG, "sendClickDataWithJsonParam");
            sendClickDataWithJsonParam(hashMap);
        } else if (str.equals("sendExposureDataWithJsonParam")) {
            JLog.e(TAG, "sendExposureDataWithJsonParam");
            sendExposureDataWithJsonParam(hashMap);
        } else if (str.equals("setUserProperty")) {
            JLog.e(TAG, "setUserProperty");
            setUserProperty(hashMap);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void removeUserProperty(ArrayList<Object> arrayList) {
        String[] strArr;
        if (arrayList == null) {
            return;
        }
        try {
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        } catch (Exception unused) {
            strArr = null;
        }
        if (strArr != null) {
            JDMtaUtils.removeUserProperty(strArr);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void savePageInfoWithSKU(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        try {
            String str = null;
            String str2 = hashMap.containsKey("order_type") ? (String) hashMap.get("order_type") : null;
            if (hashMap.containsKey("skuId")) {
                str = (String) hashMap.get("skuId");
            } else if (hashMap.containsKey("sku")) {
                str = (String) hashMap.get("sku");
            }
            JLog.d(TAG, "savePageInfoWithSKU data =" + hashMap);
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                JDMtaUtils.onSaveProductOrderPage(str2);
                return;
            }
            JDMtaUtils.onSaveProductOrderPage(str);
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendClickData(HashMap hashMap) {
        String str;
        if (hashMap == null) {
            return;
        }
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(JDReactNativeLBSListener.BUSINESSID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        JDMtaUtils.getMaInitCommonInfo(JDReactHelper.newInstance().getApplicationContext());
        String str2 = (String) hashMap.get("pageClassName");
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        String str3 = "";
        if (location != null) {
            str = location.getLat() + "";
        } else {
            str = "";
        }
        clickInterfaceParam.lat = str;
        if (location != null) {
            str3 = location.getLng() + "";
        }
        clickInterfaceParam.lon = str3;
        clickInterfaceParam.event_id = (String) hashMap.get("eventId");
        String str4 = (String) hashMap.get("eventId");
        clickInterfaceParam.event_param = (String) hashMap.get("eventParam");
        clickInterfaceParam.page_name = str2;
        clickInterfaceParam.page_param = (String) hashMap.get("pageParam");
        clickInterfaceParam.page_id = (String) hashMap.get("pageID");
        clickInterfaceParam.shop = (String) hashMap.get("shopID");
        clickInterfaceParam.pin = LoginUserBase.getLoginUserName();
        String str5 = (String) hashMap.get("nextPageClassName");
        if (str5 != null) {
            if (str5.endsWith("ProductDetailNewActivity")) {
                str5 = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            clickInterfaceParam.next_page_name = str5;
        }
        if ("LifeInquiry_CheckBill,LifeBill_PayNow,PhoneChargeCardOrderDetail_BuyAgain,LifeOrderDetail_BuyAgain,".contains(((String) hashMap.get("eventId")) + DYConstants.DY_REGEX_COMMA)) {
            JDMtaUtils.onClickWithPageId(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("eventId"), (String) hashMap.get("pageClassName"), (String) hashMap.get("eventParam"), (String) hashMap.get("pageParam"), (String) hashMap.get("pageID"));
        } else {
            JDMaInterface.sendClickData(JDReactHelper.newInstance().getApplicationContext(), JDMtaUtils.maInitCommonInfo, clickInterfaceParam);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendClickDataExtend(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        HashMap hashMap2 = null;
        try {
            if (hashMap.get("ext") instanceof Map) {
                hashMap2 = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
        }
        HashMap hashMap3 = hashMap2;
        HashMap hashMap4 = new HashMap();
        hashMap4.put(BaseEvent.SCENE, "quick");
        if ((hashMap.get("quick") instanceof Boolean) && !((Boolean) hashMap.get("quick")).booleanValue()) {
            hashMap4.remove(BaseEvent.SCENE);
        }
        JDMtaUtils.sendClickDataWithExt(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("eventId"), (String) hashMap.get("eventParam"), (String) hashMap.get("event_func"), (String) hashMap.get("pageID"), (String) hashMap.get("pageClassName"), (String) hashMap.get("pageParam"), (String) hashMap.get("nextPageClassName"), (String) hashMap.get("jsonParam"), (String) hashMap.get("shopID"), (String) hashMap.get("order_id"), (String) hashMap.get("sku"), (String) hashMap.get("sku_tag"), hashMap3, hashMap4);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendClickDataWithJsonParam(HashMap hashMap) {
        String str;
        if (hashMap == null) {
            return;
        }
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(JDReactNativeLBSListener.BUSINESSID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        JDMtaUtils.getMaInitCommonInfo(JDReactHelper.newInstance().getApplicationContext());
        String str2 = (String) hashMap.get("pageClassName");
        ClickInterfaceParam clickInterfaceParam = new ClickInterfaceParam();
        String str3 = "";
        if (location != null) {
            str = location.getLat() + "";
        } else {
            str = "";
        }
        clickInterfaceParam.lat = str;
        if (location != null) {
            str3 = location.getLng() + "";
        }
        clickInterfaceParam.lon = str3;
        clickInterfaceParam.event_id = (String) hashMap.get("eventId");
        String str4 = (String) hashMap.get("eventId");
        clickInterfaceParam.event_param = (String) hashMap.get("eventParam");
        clickInterfaceParam.page_name = str2;
        clickInterfaceParam.page_param = (String) hashMap.get("pageParam");
        clickInterfaceParam.page_id = (String) hashMap.get("pageID");
        clickInterfaceParam.shop = (String) hashMap.get("shopID");
        clickInterfaceParam.pin = LoginUserBase.getLoginUserName();
        String str5 = (String) hashMap.get("nextPageClassName");
        if (str5 != null) {
            if (str5.endsWith("ProductDetailNewActivity")) {
                str5 = "com.jingdong.app.mall.product.ProductDetailNewLastActivity";
            }
            clickInterfaceParam.next_page_name = str5;
        }
        if ("LifeInquiry_CheckBill,LifeBill_PayNow,PhoneChargeCardOrderDetail_BuyAgain,LifeOrderDetail_BuyAgain,".contains(((String) hashMap.get("eventId")) + DYConstants.DY_REGEX_COMMA)) {
            JDMtaUtils.onClickWithPageId(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("eventId"), (String) hashMap.get("pageClassName"), (String) hashMap.get("eventParam"), (String) hashMap.get("pageParam"), (String) hashMap.get("pageID"));
            return;
        }
        HashMap hashMap2 = null;
        try {
            if (hashMap.get("ext") instanceof Map) {
                hashMap2 = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
        }
        JDMtaUtils.sendClickDataWithExt(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("eventId"), (String) hashMap.get("eventParam"), (String) hashMap.get("event_func"), (String) hashMap.get("pageID"), (String) hashMap.get("pageClassName"), (String) hashMap.get("pageParam"), (String) hashMap.get("nextPageClassName"), (String) hashMap.get("jsonParam"), (String) hashMap.get("shopID"), (String) hashMap.get("order_id"), (String) hashMap.get("sku"), (String) hashMap.get("sku_tag"), hashMap2);
        JLog.d(TAG, "sendClickDataWithJsonParam :   eventId=>" + ((String) hashMap.get("eventId")) + " eventParam=>" + ((String) hashMap.get("eventParam")) + " event_func=>" + ((String) hashMap.get("event_func")) + " pageID=>" + ((String) hashMap.get("pageID")) + " pageClassName=>" + ((String) hashMap.get("pageClassName")) + " pageParam=>" + ((String) hashMap.get("pageParam")) + " nextPageClassName=>" + ((String) hashMap.get("nextPageClassName")) + " jsonParam=>" + ((String) hashMap.get("json_param")) + " shopID=>" + ((String) hashMap.get("shopID")) + " order_id=>" + ((String) hashMap.get("order_id")) + "  sku=>" + ((String) hashMap.get("sku")) + " sku_tag=>" + ((String) hashMap.get("sku_tag")));
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendCommonData(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        JDMtaUtils.sendCommonData(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("event_id"), (String) hashMap.get("event_param"), (String) hashMap.get("event_func"), (String) hashMap.get("page"), (String) hashMap.get("page_param"), (String) hashMap.get("next_class_name"), (String) hashMap.get("next_page_param"), (String) hashMap.get("page_id"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID));
        JLog.d(TAG, "Send mta common buried data :   event_id=>" + ((String) hashMap.get("event_id")) + " event_param=>" + ((String) hashMap.get("event_param")) + " event_func=>" + ((String) hashMap.get("event_func")) + " page=>" + ((String) hashMap.get("page")) + " page_param=>" + ((String) hashMap.get("page_param")) + " next_class_name=>" + ((String) hashMap.get("next_class_name")) + " next_page_param=>" + ((String) hashMap.get("next_page_param")) + " page_id=>" + ((String) hashMap.get("page_id")) + " shop_id=>" + ((String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID)));
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendCommonDataExtend(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        HashMap hashMap2 = null;
        try {
            if (hashMap.get("ext") instanceof Map) {
                hashMap2 = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
        }
        HashMap hashMap3 = hashMap2;
        HashMap hashMap4 = new HashMap();
        hashMap4.put(BaseEvent.SCENE, "quick");
        if ((hashMap.get("quick") instanceof Boolean) && !((Boolean) hashMap.get("quick")).booleanValue()) {
            hashMap4.remove(BaseEvent.SCENE);
        }
        JDMtaUtils.sendCommonDataWithExt(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("event_id"), (String) hashMap.get("event_param"), (String) hashMap.get("event_func"), (String) hashMap.get("page"), (String) hashMap.get("page_param"), (String) hashMap.get("next_class_name"), (String) hashMap.get("next_page_param"), (String) hashMap.get("page_id"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), hashMap3, hashMap4);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendCommonDataWithExt(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        if (hashMap.containsKey("ext")) {
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = (HashMap) hashMap.get("ext");
            if (hashMap3 != null) {
                Iterator it = hashMap3.entrySet().iterator();
                while (it.hasNext()) {
                    String str = (String) ((Map.Entry) it.next()).getKey();
                    if (!TextUtils.isEmpty(str)) {
                        String str2 = (String) hashMap3.get(str);
                        hashMap2.put(str, str2);
                        JLog.d(TAG, "Send mta common buried data :  key=" + str + "  value=" + str2);
                    }
                }
                if (hashMap2.size() > 0) {
                    JDMtaUtils.sendCommonDataWithExt(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("event_id"), (String) hashMap.get("event_param"), (String) hashMap.get("event_func"), (String) hashMap.get("page"), (String) hashMap.get("page_param"), (String) hashMap.get("next_class_name"), (String) hashMap.get("next_page_param"), (String) hashMap.get("page_id"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), hashMap2);
                    JLog.d(TAG, "Send mta common buried data :   event_id=>" + ((String) hashMap.get("event_id")) + " event_param=>" + ((String) hashMap.get("event_param")) + " event_func=>" + ((String) hashMap.get("event_func")) + " page=>" + ((String) hashMap.get("page")) + " page_param=>" + ((String) hashMap.get("page_param")) + " next_class_name=>" + ((String) hashMap.get("next_class_name")) + " next_page_param=>" + ((String) hashMap.get("next_page_param")) + " page_id=>" + ((String) hashMap.get("page_id")) + " shop_id=>" + ((String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID)));
                    return;
                }
            }
        }
        sendCommonData(hashMap);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendExposureData(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        JDMtaUtils.sendExposureData(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("page"), (String) hashMap.get("page_id"), (String) hashMap.get("page_param"), (String) hashMap.get("event_id"), (String) hashMap.get("event_param"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), (String) hashMap.get("order_id"), (String) hashMap.get("sku_tag"));
        JLog.d(TAG, "Send mta exposure buried data :   page=>" + hashMap.get("page") + " page_id=>" + hashMap.get("page_id") + " page_param=>" + hashMap.get("page_param") + " event_id=>" + hashMap.get("event_id") + " event_param=>" + hashMap.get("event_param") + " shop_id=>" + hashMap.get(UnAddressConstants.INTENT_SHOP_ID) + " order_id=>" + hashMap.get("order_id") + " sku_tag=>" + hashMap.get("sku_tag"));
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendExposureDataWithJsonParam(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        HashMap hashMap2 = null;
        try {
            if (hashMap.get("ext") instanceof Map) {
                hashMap2 = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
        }
        JDMtaUtils.sendExposureDataWithExt(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("event_id"), (String) hashMap.get("event_param"), (String) hashMap.get("page_id"), (String) hashMap.get("page_name"), (String) hashMap.get("page_param"), (String) hashMap.get("json_param"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), (String) hashMap.get("order_id"), (String) hashMap.get("sku_tag"), hashMap2);
        JLog.d(TAG, "Send mta exposure buried data :   page_name=>" + ((String) hashMap.get("page_name")) + " page_id=>" + ((String) hashMap.get("page_id")) + " page_param=>" + ((String) hashMap.get("page_param")) + " event_id=>" + ((String) hashMap.get("event_id")) + " event_param=>" + ((String) hashMap.get("event_param")) + " shop_id=>" + ((String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID)) + " order_id=>" + ((String) hashMap.get("order_id")) + " sku_tag=>" + ((String) hashMap.get("sku_tag")) + " json_param=>" + ((String) hashMap.get("json_param")));
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendExposureExtend(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        HashMap hashMap2 = null;
        try {
            if (hashMap.get("ext") instanceof Map) {
                hashMap2 = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
        }
        HashMap hashMap3 = hashMap2;
        HashMap hashMap4 = new HashMap();
        hashMap4.put(BaseEvent.SCENE, "quick");
        if ((hashMap.get("quick") instanceof Boolean) && !((Boolean) hashMap.get("quick")).booleanValue()) {
            hashMap4.remove(BaseEvent.SCENE);
        }
        JDMtaUtils.sendExposureExtend(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("event_id"), (String) hashMap.get("event_param"), (String) hashMap.get("page_id"), (String) hashMap.get("page_name"), (String) hashMap.get("page_param"), (String) hashMap.get("json_param"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), (String) hashMap.get("order_id"), (String) hashMap.get("sku_tag"), hashMap3, hashMap4);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendOrderDataWithExt(HashMap hashMap) {
        String str;
        if (hashMap == null) {
            return;
        }
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(JDReactNativeLBSListener.BUSINESSID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        JDMtaUtils.getMaInitCommonInfo(JDReactHelper.newInstance().getApplicationContext());
        OrderInterfaceParam orderInterfaceParam = new OrderInterfaceParam();
        String str2 = "";
        if (location != null) {
            str = location.getLat() + "";
        } else {
            str = "";
        }
        orderInterfaceParam.lat = str;
        if (location != null) {
            str2 = location.getLng() + "";
        }
        orderInterfaceParam.lon = str2;
        orderInterfaceParam.sale_ord_id = (String) hashMap.get("sale_ord_id");
        orderInterfaceParam.prod_id = (String) hashMap.get("prod_id");
        orderInterfaceParam.pin = LoginUserBase.getUserPin();
        orderInterfaceParam.order_total_fee = (String) hashMap.get("order_total_fee");
        orderInterfaceParam.order_ts = (String) hashMap.get("order_ts");
        orderInterfaceParam.quantity = (String) hashMap.get("quantity");
        orderInterfaceParam.sku_tag = (String) hashMap.get("sku_tag");
        orderInterfaceParam.ord_type = (String) hashMap.get("ord_type");
        try {
            if (hashMap.get("ext") instanceof Map) {
                orderInterfaceParam.map = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
            orderInterfaceParam.map = null;
        }
        JLog.d(TAG, "Send mta order buried data :   sale_ord_id=>" + orderInterfaceParam.sale_ord_id + " prod_id=>" + orderInterfaceParam.prod_id + " order_total_fee=>" + orderInterfaceParam.order_total_fee + " order_ts=>" + orderInterfaceParam.order_ts + " quantity=>" + orderInterfaceParam.quantity + " sku_tag=>" + orderInterfaceParam.sku_tag + " ord_type=>" + orderInterfaceParam.ord_type);
        JDMaInterface.sendOrderData(JDReactHelper.newInstance().getApplicationContext(), JDMtaUtils.maInitCommonInfo, orderInterfaceParam);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendOrderExtend(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        HashMap hashMap2 = null;
        try {
            if (hashMap.get("ext") instanceof Map) {
                hashMap2 = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
        }
        HashMap hashMap3 = hashMap2;
        HashMap hashMap4 = new HashMap();
        hashMap4.put(BaseEvent.SCENE, "quick");
        if ((hashMap.get("quick") instanceof Boolean) && !((Boolean) hashMap.get("quick")).booleanValue()) {
            hashMap4.remove(BaseEvent.SCENE);
        }
        String str = (String) hashMap.get("orderId");
        String str2 = (String) hashMap.get("factPrice");
        String str3 = (String) hashMap.get("order_type");
        String str4 = (String) hashMap.get("skuId");
        String str5 = (String) hashMap.get("count");
        if (TextUtils.isEmpty(str4)) {
            JDMtaUtils.onSaveProductOrderPage(str3);
            JDMtaUtils.sendOrderExtend(JDReactHelper.newInstance().getApplicationContext(), str + "", str2, "s_" + str3, str5, (String) hashMap.get("eventID"), hashMap3, str3, hashMap4);
            JDMtaCacheTable.delete("s_" + str3);
            return;
        }
        JDMtaUtils.onSaveProductOrderPage(str4);
        JDMtaUtils.sendOrderExtend(JDReactHelper.newInstance().getApplicationContext(), str + "", str2, "s_" + str4, str5, (String) hashMap.get("eventID"), hashMap3, str3, hashMap4);
        JDMtaCacheTable.delete("s_" + str4);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendPVExtend(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        HashMap hashMap2 = null;
        try {
            if (hashMap.get("ext") instanceof Map) {
                hashMap2 = (HashMap) hashMap.get("ext");
            }
        } catch (Exception unused) {
        }
        HashMap hashMap3 = hashMap2;
        HashMap hashMap4 = new HashMap();
        hashMap4.put(BaseEvent.SCENE, "quick");
        if ((hashMap.get("quick") instanceof Boolean) && !((Boolean) hashMap.get("quick")).booleanValue()) {
            hashMap4.remove(BaseEvent.SCENE);
        }
        JDMtaUtils.sendPVExtend(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("page"), (String) hashMap.get("page_param"), (String) hashMap.get("page_id"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), (String) hashMap.get("sku"), (String) hashMap.get("sku_tag"), (String) hashMap.get("click_url"), hashMap3, hashMap4);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendPvData(HashMap hashMap) {
        HashMap hashMap2;
        String str = DYConstants.DY_FALSE;
        if (hashMap == null) {
            return;
        }
        try {
            HashMap hashMap3 = hashMap.get("ext") instanceof Map ? (HashMap) hashMap.get("ext") : null;
            if (hashMap3 != null && hashMap3.containsKey(HAS_PV_EXTEND) && hashMap3.get(HAS_PV_EXTEND) != null) {
                str = (String) hashMap3.get(HAS_PV_EXTEND);
            }
            hashMap2 = hashMap3;
        } catch (Exception unused) {
            hashMap2 = null;
        }
        if (DYConstants.DY_TRUE.equals(str)) {
            JDMtaUtils.sendPagePv(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("page"), (String) hashMap.get("page_param"), (String) hashMap.get("page_id"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), "", "", "", hashMap2);
        } else {
            JDMtaUtils.sendPagePv(JDReactHelper.newInstance().getApplicationContext(), (String) hashMap.get("page"), (String) hashMap.get("page_param"), (String) hashMap.get("page_id"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID));
        }
        JLog.d(TAG, "Send mta pv buried data :   page=>" + ((String) hashMap.get("page")) + " page_param=>" + ((String) hashMap.get("page_param")) + " page_id=>" + ((String) hashMap.get("page_id")) + " shop_id=>" + ((String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID)));
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void sendVirtualOrderData(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        try {
            String str = (String) hashMap.get("orderId");
            String str2 = (String) hashMap.get("factPrice");
            String str3 = (String) hashMap.get("order_type");
            String str4 = (String) hashMap.get("skuId");
            int doubleValue = (int) ((Double) hashMap.get("count")).doubleValue();
            String str5 = (String) hashMap.get("eventID");
            JLog.d(TAG, "sendVirtualOrderData data =" + hashMap);
            if (TextUtils.isEmpty(str4)) {
                JDMtaUtils.onSaveProductOrderPage(str3);
                JDMtaUtils.sendOrderDatas(AbstractJDReactInitialHelper.getCurrentMyActivity(), str + "", str2, "s_" + str3, doubleValue + "", false, str5);
                JDMtaCacheTable.delete("s_" + str3);
            } else {
                JDMtaUtils.onSaveProductOrderPage(str4);
                JDMtaUtils.sendOrderDatas(AbstractJDReactInitialHelper.getCurrentMyActivity(), str + "", str2, "s_" + str4, doubleValue + "", false, str5);
                JDMtaCacheTable.delete("s_" + str4);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener
    public void setUserProperty(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        JDMtaUtils.setUserProperty(hashMap);
    }
}
