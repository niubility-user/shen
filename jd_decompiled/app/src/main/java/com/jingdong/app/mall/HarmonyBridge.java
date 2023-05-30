package com.jingdong.app.mall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArrayMap;
import androidx.annotation.Keep;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.jdsdk.security.DesCbcCrypto;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDDataUtil;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkBrowserHistoryHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSettingHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkJimiHelper;
import com.jingdong.common.deeplinkhelper.imhelper.JimiParameterBuilder;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.CmShareChannelInfo;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.login.DeviceFinger;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import jpbury.t;
import rx.Observable;
import rx.functions.Action1;

@Keep
/* loaded from: classes19.dex */
public class HarmonyBridge {
    private static final String DESKEY = "yc2JffcREheFQlYFIAY5f9sY7uflgBTo";
    private static final String PRODUCT_DETAIL = "productdetail";
    private static final String TAG = "HarmonyBridge";
    private static final int THREE = 3;
    private static final int TWO = 2;
    private JDLocation location;

    /* loaded from: classes19.dex */
    class a implements CallBackListener {
        a() {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(Bundle bundle, Map.Entry entry) {
        Object value = entry.getValue();
        String str = (String) entry.getKey();
        String name = value.getClass().getName();
        if (TextUtils.equals(name, IBinder.class.getName())) {
            if (Build.VERSION.SDK_INT >= 18) {
                bundle.putBinder(str, (IBinder) value);
            }
        } else if (TextUtils.equals(name, Bundle.class.getName())) {
            bundle.putBundle(str, (Bundle) value);
        } else if (TextUtils.equals(name, Byte.TYPE.getName())) {
            bundle.putByte(str, ((Byte) value).byteValue());
        } else if (TextUtils.equals(name, Integer.TYPE.getName())) {
            bundle.putInt(str, ((Integer) value).intValue());
        } else if (TextUtils.equals(name, String.class.getName())) {
            bundle.putString(str, (String) value);
        } else if (TextUtils.equals(name, Boolean.TYPE.getName())) {
            bundle.putBoolean(str, ((Boolean) value).booleanValue());
        } else if (TextUtils.equals(name, byte[].class.getName())) {
            bundle.putByteArray(str, (byte[]) value);
        } else if (TextUtils.equals(name, int[].class.getName())) {
            bundle.putIntArray(str, (int[]) value);
        } else if (TextUtils.equals(name, String[].class.getName())) {
            bundle.putStringArray(str, (String[]) value);
        } else if (TextUtils.equals(name, Parcelable.class.getName())) {
            bundle.putParcelable(str, (Parcelable) value);
        } else if (TextUtils.equals(name, Serializable[].class.getName())) {
            bundle.putSerializable(str, (Serializable) value);
        } else if (TextUtils.equals(name, Parcelable[].class.getName())) {
            bundle.putParcelableArray(str, (Parcelable[]) value);
        } else if (!TextUtils.equals(name, Long.class.getName()) && !TextUtils.equals(name, Long.TYPE.getName())) {
            if (TextUtils.equals(name, Float.class.getName())) {
                bundle.putFloat(str, ((Float) value).floatValue());
            }
        } else {
            bundle.putLong(str, ((Long) value).longValue());
        }
    }

    private JDJSONObject buildDefaultSkuParameters(Map<String, Object> map) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (checkIsHarmony()) {
            try {
                jDJSONObject.put("skuId", map.get("skuId"));
                if (map != null) {
                    Object obj = map.get("source");
                    if (obj instanceof SourceEntity) {
                        SourceEntity sourceEntity = (SourceEntity) obj;
                        jDJSONObject.put(CartConstant.KEY_SOURCE_TYPE, (Object) sourceEntity.getSourceType());
                        jDJSONObject.put(CartConstant.KEY_SOURCE_VALUE, (Object) sourceEntity.getSourceValue());
                    }
                    Object obj2 = map.get("sourceType");
                    if (obj2 instanceof String) {
                        jDJSONObject.put(CartConstant.KEY_SOURCE_TYPE, obj2);
                    }
                    Object obj3 = map.get("sourceValue");
                    if (obj2 instanceof String) {
                        jDJSONObject.put(CartConstant.KEY_SOURCE_VALUE, obj3);
                    }
                }
                AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
                if (addressGlobal != null) {
                    jDJSONObject.put("uAddrId", (Object) String.valueOf(addressGlobal.getId()));
                }
                boolean z = addressGlobal != null && (addressGlobal.getIsUserAddress().booleanValue() || map.get("storeId") != null);
                if (z && !TextUtils.isEmpty(addressGlobal.getLatitude())) {
                    jDJSONObject.put(PdLVBody.GCLAT, (Object) addressGlobal.getLatitude());
                }
                if (z && !TextUtils.isEmpty(addressGlobal.getLongitude())) {
                    jDJSONObject.put(PdLVBody.GCLNG, (Object) addressGlobal.getLongitude());
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(TAG, e2.getMessage());
                }
            }
            return jDJSONObject;
        }
        return jDJSONObject;
    }

    private static boolean checkIsHarmony() {
        return false;
    }

    public static String decrypt(String str) {
        if (checkIsHarmony()) {
            try {
                return DesCbcCrypto.decrypt(str, JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES), (byte[]) null);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                    return "";
                }
                return "";
            }
        }
        return "";
    }

    public static String getFinger() {
        return !checkIsHarmony() ? "" : DeviceFinger.getFinger(JdSdk.getInstance().getApplicationContext());
    }

    public static byte[] getUnIcon(String str) {
        if (checkIsHarmony()) {
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str.trim())) {
                throw new IllegalArgumentException("iconId must not be null");
            }
            Bitmap bitmap = UnIconConfigHelper.getBitmap(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public static StringBuffer getUserAgent() {
        if (checkIsHarmony()) {
            return WebViewHelper.getJdUaInfoEncryptUuid();
        }
        return null;
    }

    public static boolean getUserPlusStatus() {
        if (checkIsHarmony()) {
            return PersonalInfoManager.getInstance().isUserPlusStatus();
        }
        return false;
    }

    public static void goHome() {
        if (checkIsHarmony()) {
            e.b().startMainFrameActivity(JdSdk.getInstance().getApplicationContext());
        }
    }

    public static void goToWebActivity(String str) {
        if (!checkIsHarmony() || JdSdk.getInstance().getApplicationContext() == null || TextUtils.isEmpty(str)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        Bundle bundle = new Bundle();
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        DeepLinkCommonHelper.startWebActivity(JdSdk.getInstance().getApplicationContext(), bundle, true);
    }

    private void gotoShopCategory(Map<String, String> map) {
        Integer num;
        if (checkIsHarmony() && map != null) {
            String str = map.get("commendSkuid");
            String str2 = map.get("homeFloorExt");
            try {
                num = Integer.valueOf(map.get("categoryType"));
            } catch (Exception unused) {
                num = null;
            }
            String str3 = map.get("cname");
            String str4 = map.get("relateSkuIds");
            String str5 = map.get("cid");
            String str6 = map.get("productSkuId");
            DeepLinkJShopHomeHelper.JShopBundleBuilder from = DeepLinkJShopHomeHelper.JShopBundleBuilder.from(map.get("shopId"), map.get("venderId"), map.get("shopName"));
            from.addSkuId(str);
            from.addPageId("ProductDetailModule");
            from.addTargetPage("allProduct");
            from.addSource(new SourceEntity("shop_from_product_detail", map.get("skuId")));
            from.addComplexSource(DeepLinkJShopHomeHelper.JSHOP_FROM_KA_PRODUCT_DETAIL);
            from.addHomeFloorExt(str2);
            if (num != null && num.intValue() == 2) {
                from.addQueryWord(str3);
            } else if (num != null && num.intValue() == 3) {
                from.addQueryWord(str3);
                from.addSkus(str4);
            } else {
                from.addRecommendCateInfo(str5, str3, str6, "");
            }
            DeepLinkJShopHomeHelper.gotoJShopHome(JdSdk.getInstance().getApplicationContext(), from.build());
        }
    }

    public static boolean isJDAppAcceptPrivacy() {
        if (checkIsHarmony()) {
            return JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
        }
        return false;
    }

    public static void jumpWithDeepLink(String str, Map<String, Object> map) {
        if (!checkIsHarmony() || JdSdk.getInstance().getApplicationContext() == null || TextUtils.isEmpty(str)) {
            return;
        }
        final Bundle bundle = new Bundle();
        Observable.from(map.entrySet()).forEach(new Action1() { // from class: com.jingdong.app.mall.a
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                HarmonyBridge.a(bundle, (Map.Entry) obj);
            }
        });
        try {
            DeepLinkCommonHelper.startActivityDirect(JdSdk.getInstance().getApplicationContext(), str, bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void jumpWithExecJump(Object obj) {
        if (checkIsHarmony() && ActivityManagerUtil.getScreenManager().currentActivity() != null) {
            try {
                JumpUtil.execJump(ActivityManagerUtil.getScreenManager().currentActivity(), (JumpEntity) JDJSON.toJavaObject((JDJSON) JDJSON.toJSON(obj), JumpEntity.class), 0);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void jumpWithOpenAppUrl(String str) {
        if (checkIsHarmony()) {
            String str2 = "jumpWithOpenAppUrl url = " + str;
            if (JdSdk.getInstance().getApplicationContext() == null || TextUtils.isEmpty(str)) {
                return;
            }
            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(JdSdk.getInstance().getApplicationContext());
        }
    }

    public static void jumpWithRouterUrl(String str) {
        if (checkIsHarmony()) {
            String str2 = "jumpWithOpenAppUrl url = " + str;
            if (JdSdk.getInstance().getApplicationContext() == null || TextUtils.isEmpty(str)) {
                return;
            }
            JDRouter.build(JdSdk.getInstance().getApplicationContext(), str).open();
        }
    }

    public static void pdNowBuy(String str, String str2, int i2, String str3, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3, int i3, int i4, String str4) {
        if (checkIsHarmony()) {
            Bundle bundle = new Bundle();
            Context applicationContext = JdSdk.getInstance().getApplicationContext();
            bundle.putString(DeepLinkFillOrderHelper.LIVE_SOURCE_PT_KEY, str4);
            if (!TextUtils.isEmpty(str)) {
                bundle.putString(CartConstant.KEY_EXTFLAG, str);
            }
            if (JDRouterUtil.isRouterJump()) {
                DeepLinkFillOrderHelper.Builder put = DeepLinkFillOrderHelper.Builder.create().put("wareId", str2).put("wareNum", Integer.valueOf(i2)).put(CartConstant.KEY_DELIVERYID, str3).put("skuSource", Integer.valueOf(i4)).put(DeepLinkFillOrderHelper.LIVE_SOURCE_PT_KEY, str4).put("orderType", Integer.valueOf(i3)).put("sourceType", 2);
                if (!TextUtils.isEmpty(str)) {
                    put.put(CartConstant.KEY_EXTFLAG, str);
                }
                JDRouter.build(applicationContext, put.toURL()).extraObject("3cGiftPoolsId", arrayList).extraObject("ybList", arrayList2).extraObject("jdHomeServiceList", arrayList3).callBackListener(new a()).open();
            }
        }
    }

    public static void sendExposureData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        if (checkIsHarmony()) {
            JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplicationContext(), str, str2, str3, str4, str5, str6, str7, str8);
        }
    }

    public static void sendLoginInfo2Harmony(String str, String str2) {
        if (checkIsHarmony()) {
            try {
                Class.forName("com.jingdong.hmapp.mylib.utils.BridgeUtil").getDeclaredMethod("onAndroidAppLogin", String.class, String.class).invoke(null, str, str2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void sendLogoutState2Harmony() {
        if (checkIsHarmony()) {
            try {
                Class.forName("com.jingdong.hmapp.mylib.utils.BridgeUtil").getDeclaredMethod("onAndroidAppLogout", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void sendUUID2Harmony() {
        if (checkIsHarmony()) {
            try {
                Class.forName("com.jingdong.hmapp.mylib.utils.BridgeUtil").getDeclaredMethod("onReceiveAndroidUUID", String.class).invoke(null, StatisticsReportUtil.readDeviceUUID());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void shareLiveRoom(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String str9, String str10, String str11, String str12, String str13, String str14) {
        Activity currentActivity;
        if (checkIsHarmony() && (currentActivity = ActivityManagerUtil.getScreenManager().currentActivity()) != null) {
            ShareInfo shareInfo = new ShareInfo(str, str2, str3, str4, str5, str6, str7, null, "");
            shareInfo.setChannels(str12);
            shareInfo.setTitleTimeline(str);
            try {
                Method declaredMethod = shareInfo.getClass().getDeclaredMethod("setAbilityName", String.class);
                Method declaredMethod2 = shareInfo.getClass().getDeclaredMethod("setBundleName", String.class);
                declaredMethod.invoke(shareInfo, str13);
                declaredMethod2.invoke(shareInfo, str14);
            } catch (Exception unused) {
            }
            if (i2 == 1) {
                shareInfo.setMpIconUrl(str10);
                shareInfo.setMpPath(str11);
                shareInfo.setMpId(str9);
            }
            ShareUtil.panel(currentActivity, shareInfo);
        }
    }

    public static void shareProduct(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Activity currentActivity;
        if (checkIsHarmony() && (currentActivity = ActivityManagerUtil.getScreenManager().currentActivity()) != null) {
            ArrayList arrayList = new ArrayList();
            CmShareChannelInfo cmShareChannelInfo = new CmShareChannelInfo();
            cmShareChannelInfo.cmIconName = "\u5c4f\u5e55\u5171\u4eab";
            cmShareChannelInfo.cmCIconResId = R.drawable.hw_share_icon;
            cmShareChannelInfo.cmBizId = "hwpmgx";
            arrayList.add(cmShareChannelInfo);
            ShareInfo shareInfo = new ShareInfo(str, str2, str3, str4, "");
            StringBuilder sb = new StringBuilder();
            try {
                Field declaredField = ShareUtil.class.getDeclaredField("S_Hw_CaasShare");
                r4 = declaredField != null ? (String) declaredField.get(null) : null;
                Method declaredMethod = shareInfo.getClass().getDeclaredMethod("setAbilityName", String.class);
                Method declaredMethod2 = shareInfo.getClass().getDeclaredMethod("setBundleName", String.class);
                Method declaredMethod3 = shareInfo.getClass().getDeclaredMethod("setPrice", String.class);
                Method declaredMethod4 = shareInfo.getClass().getDeclaredMethod("setExtParams", String.class);
                declaredMethod.invoke(shareInfo, str6);
                declaredMethod2.invoke(shareInfo, str7);
                declaredMethod3.invoke(shareInfo, str5);
                declaredMethod4.invoke(shareInfo, str8);
            } catch (Exception unused) {
            }
            sb.append("Wxfriends");
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append("Wxmoments");
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append("QQfriends");
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append("QQzone");
            sb.append(DYConstants.DY_REGEX_COMMA);
            if (!TextUtils.isEmpty(r4)) {
                sb.append(r4);
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.append("Sinaweibo");
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append("CopyURL");
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(ShareUtil.S_JDFamily);
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(ShareUtil.S_JD_SAVE_IMG);
            shareInfo.setChannels(sb.toString());
            ShareUtil.panel(currentActivity, shareInfo);
        }
    }

    public static void startDongDong(String str, String str2, String str3, String str4) {
        if (checkIsHarmony()) {
            String str5 = " startDongDong = " + str + " productName = " + str2 + " productPrice = " + str3 + " productImageUrl = " + str4;
            DDParameterBuilder generateWithPin = DDParameterBuilder.generateWithPin();
            if (generateWithPin == null) {
                return;
            }
            generateWithPin.addFrom(DDParameterBuilder.ACTION_BROADCAST_PRODUCT_ASK).addSkuID(str).addProductName(str2).addProductPrice(str3).addProductImageUrl(str4).addEntry(null);
            DeeplinkDongDongHelper.getInstance().startDongDong(JdSdk.getInstance().getApplicationContext(), generateWithPin.getBundle());
        }
    }

    public static void startGiftPoolActivityWithResult(Map<String, Object> map, int i2) {
        Activity currentActivity;
        if (checkIsHarmony() && (currentActivity = ActivityManagerUtil.getScreenManager().currentActivity()) != null) {
            Bundle bundle = new Bundle();
            if (map != null) {
                try {
                    Field declaredField = bundle.getClass().getSuperclass().getDeclaredField("mMap");
                    declaredField.setAccessible(true);
                    if (Build.VERSION.SDK_INT >= 19) {
                        ArrayMap arrayMap = (ArrayMap) declaredField.get(bundle);
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String str = entry.getKey() + " == " + entry.getValue();
                            arrayMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Intent intent = new Intent(currentActivity, HarmonyBridgeActivity.class);
            intent.putExtras(bundle);
            currentActivity.startActivityForResult(intent, i2);
        }
    }

    public static void startJimi(String str, String str2, String str3) {
        JimiParameterBuilder generateWithPin;
        if (checkIsHarmony() && (generateWithPin = JimiParameterBuilder.generateWithPin()) != null) {
            generateWithPin.addSource(str).addSkuID(str2).addAction(JimiParameterBuilder.ACTION_BROADCAST_START_PLUGIN_JIMI);
            if (str3 != null) {
                generateWithPin.addVenderID(str3);
            }
            DeeplinkJimiHelper.getInstance().startJimiActivity(JdSdk.getInstance().getApplicationContext(), generateWithPin.getAllParameters());
        }
    }

    public static void startProductDetailActivity(String str, Map<String, Object> map) {
        if (checkIsHarmony() && !TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            if (map != null) {
                try {
                    Field declaredField = bundle.getClass().getSuperclass().getDeclaredField("mMap");
                    declaredField.setAccessible(true);
                    if (Build.VERSION.SDK_INT >= 19) {
                        ArrayMap arrayMap = (ArrayMap) declaredField.get(bundle);
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String str2 = entry.getKey() + " == " + entry.getValue();
                            arrayMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            bundle.putString("skuId", str);
            DeeplinkProductDetailHelper.startProductDetailFromOpenApp(JdSdk.getInstance().getApplicationContext(), bundle);
        }
    }

    public String buildWareBusinessParam(Map<String, Object> map) {
        AddressGlobal addressGlobal;
        if (checkIsHarmony()) {
            JDJSONObject buildDefaultSkuParameters = buildDefaultSkuParameters(map);
            buildDefaultSkuParameters.put("eventId", (Object) JDMtaUtils.getLastEventId());
            buildDefaultSkuParameters.put("pluginVersion", (Object) Integer.valueOf((int) PDUtils.pluginVersion));
            buildDefaultSkuParameters.put("fromType", (Object) 0);
            Boolean bool = Boolean.TRUE;
            buildDefaultSkuParameters.put("lego", (Object) bool);
            String authCodeTokenParam = PDDataUtil.getAuthCodeTokenParam();
            if (!TextUtils.isEmpty(authCodeTokenParam)) {
                buildDefaultSkuParameters.put("token", (Object) authCodeTokenParam);
                buildDefaultSkuParameters.put("pdbp", (Object) "0");
            }
            String businessId = AdvertUtils.getBusinessId();
            if (!TextUtils.isEmpty(businessId)) {
                buildDefaultSkuParameters.put("bizType", (Object) WebEntity.VALUE_ONEKEYLOGIN_KEPLER);
                buildDefaultSkuParameters.put("bizId", (Object) businessId);
            }
            boolean z = SharedPreferencesUtil.getBoolean("isDesCbc", false);
            buildDefaultSkuParameters.put("isDesCbc", (Object) Boolean.valueOf(z));
            if (LoginUserBase.hasLogin() && (addressGlobal = AddressUtil.getAddressGlobal()) != null && addressGlobal.getIsUserAddress().booleanValue() && !TextUtils.isEmpty(addressGlobal.getWhere())) {
                if (Log.D) {
                    Log.d(TAG, "global.getAddressDetail() = " + addressGlobal.getWhere());
                }
                String encryptThreeDESECB = PDUtils.encryptThreeDESECB(addressGlobal.getWhere(), z, "yc2JffcREheFQlYFIAY5f9sY7uflgBTo");
                if (!TextUtils.isEmpty(encryptThreeDESECB)) {
                    if (Log.D) {
                        Log.d(TAG, "address = " + encryptThreeDESECB);
                        Log.d(TAG, "decryptThreeDESECB address = " + PDUtils.decryptThreeDESECB(encryptThreeDESECB, z, "yc2JffcREheFQlYFIAY5f9sY7uflgBTo"));
                    }
                    buildDefaultSkuParameters.put("fullAddress", (Object) encryptThreeDESECB);
                }
            }
            if (this.location == null) {
                this.location = JDLocationCache.getInstance().getLocation(PDUtils.getLocationCacheOption());
            }
            JDLocation jDLocation = this.location;
            if (jDLocation != null) {
                buildDefaultSkuParameters.put(PdLVBody.LATITUDE, (Object) String.valueOf(jDLocation.getLat()));
                buildDefaultSkuParameters.put(PdLVBody.LONGITUDE, (Object) String.valueOf(this.location.getLng()));
            }
            if (map.get("locShopId") != null) {
                buildDefaultSkuParameters.put("locShopId", map.get("locShopId"));
            }
            buildDefaultSkuParameters.put("abTest800", (Object) bool);
            buildDefaultSkuParameters.put("plusClickCount", (Object) Integer.valueOf(SharedPreferencesUtil.getInt("plusClickCount", 0)));
            JDLocation jDLocation2 = this.location;
            if (jDLocation2 != null) {
                buildDefaultSkuParameters.put(JDWeatherActionKey.PROVINCE_ID, (Object) String.valueOf(jDLocation2.getProvinceId()));
                buildDefaultSkuParameters.put(JDWeatherActionKey.CITY_ID, (Object) Integer.valueOf(this.location.getCityId()));
                buildDefaultSkuParameters.put("districtId", (Object) Integer.valueOf(this.location.getDistrictId()));
                buildDefaultSkuParameters.put(JDWeatherActionKey.TOWN_ID, (Object) Integer.valueOf(this.location.getTownId()));
            }
            buildDefaultSkuParameters.put(JDNetCacheManager.BRAND_BIZKEY, (Object) BaseInfo.getDeviceBrand());
            buildDefaultSkuParameters.put(CustomThemeConstance.NAVI_MODEL, (Object) BaseInfo.getDeviceModel());
            buildDefaultSkuParameters.put("plusLandedFatigue", (Object) Integer.valueOf(PDUtils.isToday(SharedPreferencesUtil.getString("pd_plus_weary_day", "0")) ? SharedPreferencesUtil.getInt("pd_plus_weary_times", 0) : 0));
            buildDefaultSkuParameters.put("ocrFlag", (Object) CommonBase.getBooleanFromPreference(PersonalConstants.SP_SETTING_OCR, Boolean.FALSE));
            return buildDefaultSkuParameters.toJSONString();
        }
        return "";
    }

    public void startFeedBackActivity() {
        if (checkIsHarmony()) {
            Bundle bundle = new Bundle();
            bundle.putString("from", "shangxiang");
            DeepLinkSettingHelper.startFeedbackActivity(JdSdk.getInstance().getApplicationContext(), bundle);
        }
    }

    public void startMainActivity() {
        if (checkIsHarmony()) {
            try {
                DeepLinkCommonHelper.startActivity(JdSdk.getInstance().getApplicationContext(), DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, null, true, 67108864, false, "");
            } catch (Throwable th) {
                if (Log.E) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void startMessageActivity() {
        if (checkIsHarmony()) {
            JumpMessageActivityUtil.productDetailjumpToMessageCenter(JdSdk.getInstance().getApplicationContext());
        }
    }

    public void startScanActivity() {
        if (checkIsHarmony()) {
            if (JdSdk.getInstance().getApplicationContext() instanceof IMyActivity) {
                DeepLinkBrowserHistoryHelper.startBrowserHistoryActivity((IMyActivity) JdSdk.getInstance().getApplicationContext(), null);
            } else {
                DeepLinkBrowserHistoryHelper.startBrowserHistoryActivity2(JdSdk.getInstance().getApplicationContext(), null);
            }
        }
    }

    public void startSearchActivity() {
        if (checkIsHarmony()) {
            DeepLinkCommonHelper.startActivityDirect(JdSdk.getInstance().getApplicationContext(), DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, null);
        }
    }

    public void startShopActivity(Map<String, String> map) {
        if (checkIsHarmony() && PDUtils.isCanClick() && map != null) {
            String str = map.get("shopId");
            String str2 = map.get("skuId");
            String str3 = map.get("venderId");
            String str4 = map.get("name");
            String str5 = map.get("logo");
            String str6 = map.get(JshopConst.JSHOP_YU_SIGN_BOARD_URL);
            String str7 = map.get("categoryId");
            String str8 = map.get("sku");
            String str9 = map.get(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE);
            String str10 = map.get("getHomeFloorExt");
            try {
                DeepLinkJShopHomeHelper.JShopBundleBuilder from = DeepLinkJShopHomeHelper.JShopBundleBuilder.from(str, str3, str4);
                from.addLogoUrl(str5).addSignBoardUrl(str6).addSource(new SourceEntity("shop_from_product_detail", str2)).addTargetPage("home").addRecommendCateInfo(str7, "", str8, str9).addHomeFloorExt(str10);
                DeepLinkJShopHomeHelper.gotoJShopHome(JdSdk.getInstance().getApplicationContext(), from.build());
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
        }
    }

    public void startShoppingCartActivity() {
        if (checkIsHarmony()) {
            DeepLinkCommonHelper.startShoppingCartActivity(JdSdk.getInstance().getApplicationContext(), null, true);
        }
    }
}
