package com.jingdong.common.ui.address;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.jdsdk.security.DesCbcCrypto;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.address.UnAddressSelectView;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.address.listener.OnAddressCoverageListener;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.utils.HostUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class UnAddressSelectHelper {
    private static final String TAG = "UnAddressSelectHelper";
    private String action;
    private Context context;
    private Handler handler;
    private boolean isOldAddress;
    private boolean mIsDestroy;
    private UnAddressSelectView.OnAddressLoadListener onAddressLoadListener;
    private UnAddressSelectView.OnUnAddressListener onUnAddressListener;
    private int pluginVersion;
    private String saveBusiness;
    private String sceneId;
    private UnAddressSelectView selectView;
    private ShopParam shopParam;
    private String sku;
    private String source;
    private int type;

    public UnAddressSelectHelper(Context context, int i2) {
        this.action = "usualAddress";
        this.context = context;
        this.type = i2;
        UnAddressSelectView unAddressSelectView = new UnAddressSelectView(context);
        this.selectView = unAddressSelectView;
        this.onAddressLoadListener = unAddressSelectView.onAddressLoadListener;
        this.handler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseAddress(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            post(new Runnable() { // from class: com.jingdong.common.ui.address.UnAddressSelectHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    if (UnAddressSelectHelper.this.onAddressLoadListener != null) {
                        UnAddressSelectHelper.this.onAddressLoadListener.onComplete(false, null);
                    }
                }
            });
        } else if (!jDJSONObject.isNull("usualAddress")) {
            final ArrayList<UnAddressInfo> list = toList(jDJSONObject.optJSONArray("usualAddress"), jDJSONObject.optBoolean("isEncrypt"));
            ShopParam shopParam = this.shopParam;
            if (shopParam == null) {
                post(new Runnable() { // from class: com.jingdong.common.ui.address.UnAddressSelectHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (UnAddressSelectHelper.this.onAddressLoadListener != null) {
                            UnAddressSelectView.OnAddressLoadListener onAddressLoadListener = UnAddressSelectHelper.this.onAddressLoadListener;
                            ArrayList arrayList = list;
                            onAddressLoadListener.onComplete(arrayList != null && arrayList.size() > 0, list);
                        }
                    }
                });
            } else {
                UnAddressSelectUtils.multAddreessDeliveryArea(list, shopParam, new OnAddressCoverageListener() { // from class: com.jingdong.common.ui.address.UnAddressSelectHelper.4
                    @Override // com.jingdong.common.ui.address.listener.OnAddressCoverageListener
                    public void onResult(final List<UnAddressInfo> list2) {
                        UnAddressSelectHelper.this.post(new Runnable() { // from class: com.jingdong.common.ui.address.UnAddressSelectHelper.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (UnAddressSelectHelper.this.onAddressLoadListener != null) {
                                    UnAddressSelectView.OnAddressLoadListener onAddressLoadListener = UnAddressSelectHelper.this.onAddressLoadListener;
                                    List list3 = list2;
                                    onAddressLoadListener.onComplete(list3 != null && list3.size() > 0, list2);
                                }
                            }
                        });
                    }
                });
            }
        } else {
            post(new Runnable() { // from class: com.jingdong.common.ui.address.UnAddressSelectHelper.5
                @Override // java.lang.Runnable
                public void run() {
                    if (UnAddressSelectHelper.this.onAddressLoadListener != null) {
                        UnAddressSelectHelper.this.onAddressLoadListener.onComplete(false, null);
                    }
                }
            });
        }
    }

    private ArrayList<UnAddressInfo> toList(JDJSONArray jDJSONArray, boolean z) {
        String generateKey;
        ArrayList<UnAddressInfo> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            UnAddressInfo unAddressInfo = new UnAddressInfo();
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                unAddressInfo.addressId = optJSONObject.optLong("id");
                unAddressInfo.provinceId = optJSONObject.optInt(JDWeatherActionKey.PROVINCE_ID);
                unAddressInfo.cityId = optJSONObject.optInt(JDWeatherActionKey.CITY_ID);
                unAddressInfo.countyId = optJSONObject.optInt("countyId");
                unAddressInfo.townId = optJSONObject.optInt(JDWeatherActionKey.TOWN_ID);
                try {
                    unAddressInfo.lat = Double.valueOf(optJSONObject.optString(PdLVBody.GCLAT)).doubleValue();
                    unAddressInfo.lng = Double.valueOf(optJSONObject.optString(PdLVBody.GCLNG)).doubleValue();
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
                unAddressInfo.provinceName = optJSONObject.getString("provinceName");
                unAddressInfo.cityName = optJSONObject.getString("cityName");
                unAddressInfo.countyName = optJSONObject.getString("areaName");
                unAddressInfo.townName = optJSONObject.getString("townName");
                boolean optBoolean = optJSONObject.optBoolean("isDesCbc");
                String optString = optJSONObject.optString("fullAddress");
                String optString2 = optJSONObject.optString("addressDetail");
                try {
                    generateKey = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);
                } catch (Exception e3) {
                    OKLog.e(TAG, e3);
                }
                if (z) {
                    if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(generateKey)) {
                        if (optBoolean) {
                            optString = DesCbcCrypto.decrypt(optString, generateKey, (byte[]) null);
                        } else {
                            optString = DesCommonUtils.decryptThreeDESECB(optString, generateKey);
                        }
                    }
                    optString = "";
                }
                if (z) {
                    try {
                        String generateKey2 = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);
                        if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(generateKey2)) {
                            if (optBoolean) {
                                optString2 = DesCbcCrypto.decrypt(optString2, generateKey2, (byte[]) null);
                            } else {
                                optString2 = DesCommonUtils.decryptThreeDESECB(optString2, generateKey2);
                            }
                        }
                    } catch (Exception e4) {
                        OKLog.e(TAG, e4);
                    }
                    optString2 = "";
                }
                unAddressInfo.isUserAddress = true;
                unAddressInfo.detailAddress = optString2;
                unAddressInfo.fullAddress = optString;
                if (TextUtils.isEmpty(optString)) {
                    unAddressInfo.fourAddress = unAddressInfo.provinceName + unAddressInfo.cityName + unAddressInfo.countyName + unAddressInfo.townName;
                } else {
                    unAddressInfo.fourAddress = unAddressInfo.fullAddress.replace(unAddressInfo.detailAddress, "");
                }
                if (unAddressInfo.addressId != 0) {
                    arrayList.add(unAddressInfo);
                }
            }
        }
        return arrayList;
    }

    public void destroy() {
        this.mIsDestroy = true;
    }

    public UnAddressSelectView getSelectView() {
        return this.selectView;
    }

    public void initData(String str, ShopParam shopParam, int i2) {
        initData(str, shopParam, "", i2);
    }

    public void post(Runnable runnable) {
        if (runnable != null) {
            this.handler.post(runnable);
        }
    }

    public void requestAddress() {
        UnAddressSelectView.OnAddressLoadListener onAddressLoadListener;
        if (this.mIsDestroy || (onAddressLoadListener = this.onAddressLoadListener) == null) {
            return;
        }
        onAddressLoadListener.onStart();
        HttpSetting httpSetting = new HttpSetting();
        if (this.type == 2) {
            httpSetting.setFunctionId("thirdAddress");
        } else {
            httpSetting.setFunctionId("publicThirdAddress");
            if (this.type == 4) {
                httpSetting.putJsonParam("channel", "1");
            } else {
                httpSetting.putJsonParam("channel", "1");
            }
        }
        if (!TextUtils.isEmpty(this.sku)) {
            httpSetting.putJsonParam("skuId", this.sku);
        }
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("pluginVersion", Integer.valueOf(this.pluginVersion));
        httpSetting.putJsonParam("isReturnStock", Boolean.TRUE);
        httpSetting.putJsonParam("pluginVersion", Integer.valueOf(this.pluginVersion));
        httpSetting.putJsonParam("action", this.action);
        httpSetting.putJsonParam(JDWeatherActionKey.PROVINCE_ID, "0");
        httpSetting.putJsonParam(JDWeatherActionKey.CITY_ID, "0");
        httpSetting.putJsonParam("countyId", "0");
        httpSetting.putJsonParam(JDWeatherActionKey.TOWN_ID, "o");
        if (this.isOldAddress) {
            httpSetting.putJsonParam("changeUsual", "1");
        }
        httpSetting.setHost(HostUtils.getWareHost());
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.ui.address.UnAddressSelectHelper.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (UnAddressSelectHelper.this.mIsDestroy) {
                    return;
                }
                UnAddressSelectHelper.this.parseAddress(httpResponse.getFastJsonObject());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                UnAddressSelectHelper.this.post(new Runnable() { // from class: com.jingdong.common.ui.address.UnAddressSelectHelper.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (UnAddressSelectHelper.this.onAddressLoadListener != null) {
                            UnAddressSelectHelper.this.onAddressLoadListener.onComplete(false, null);
                        }
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public UnAddressSelectHelper setOnAddressLoadListener(UnAddressSelectView.OnAddressLoadListener onAddressLoadListener) {
        this.onAddressLoadListener = onAddressLoadListener;
        return this;
    }

    public UnAddressSelectHelper setOnUnAddressListener(UnAddressSelectView.OnUnAddressListener onUnAddressListener) {
        this.onUnAddressListener = onUnAddressListener;
        UnAddressSelectView unAddressSelectView = this.selectView;
        if (unAddressSelectView != null) {
            unAddressSelectView.setUnAddressListener(onUnAddressListener);
        }
        return this;
    }

    public UnAddressSelectHelper setSaveBusiness(String str) {
        this.saveBusiness = str;
        UnAddressSelectView unAddressSelectView = this.selectView;
        if (unAddressSelectView != null) {
            unAddressSelectView.setSaveBusiness(str);
        }
        return this;
    }

    public UnAddressSelectHelper setSceneId(String str) {
        this.sceneId = str;
        UnAddressSelectView unAddressSelectView = this.selectView;
        if (unAddressSelectView != null) {
            unAddressSelectView.setSceneId(str);
        }
        return this;
    }

    public UnAddressSelectHelper setSource(String str) {
        this.source = str;
        UnAddressSelectView unAddressSelectView = this.selectView;
        if (unAddressSelectView != null) {
            unAddressSelectView.setSource(str);
        }
        return this;
    }

    public void initData(String str, ShopParam shopParam, String str2, int i2) {
        this.sku = str;
        this.shopParam = shopParam;
        this.pluginVersion = i2;
        UnAddressSelectView unAddressSelectView = this.selectView;
        if (unAddressSelectView != null) {
            unAddressSelectView.initData(shopParam, this.type);
        }
        UnAddressSelectView unAddressSelectView2 = this.selectView;
        if (unAddressSelectView2 != null) {
            unAddressSelectView2.setSaveBusiness(str2);
        }
        requestAddress();
    }

    public UnAddressSelectHelper(Context context, int i2, boolean z) {
        this(context, i2, z, "");
    }

    public UnAddressSelectHelper(Context context, int i2, boolean z, String str) {
        this.action = "usualAddress";
        this.context = context;
        this.type = i2;
        UnAddressSelectView unAddressSelectView = new UnAddressSelectView(context, z, str);
        this.selectView = unAddressSelectView;
        this.onAddressLoadListener = unAddressSelectView.onAddressLoadListener;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public UnAddressSelectHelper(int i2) {
        this.action = "usualAddress";
        this.type = i2;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public UnAddressSelectHelper(Context context, int i2, UnAddressSelectView unAddressSelectView) {
        this.action = "usualAddress";
        this.context = context;
        this.selectView = unAddressSelectView;
        this.type = i2;
        this.onAddressLoadListener = unAddressSelectView.onAddressLoadListener;
        this.handler = new Handler(Looper.getMainLooper());
    }
}
