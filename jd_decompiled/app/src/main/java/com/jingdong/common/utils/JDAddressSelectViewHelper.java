package com.jingdong.common.utils;

import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.jdsdk.security.DesCbcCrypto;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.entity.AddressBaseMode;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.ILocationClickStateListener;
import com.jingdong.common.ui.JDAddressSelectView;
import com.jingdong.common.ui.OnLocationViewShowListener;
import com.jingdong.common.ui.address.entity.TabInfoBean;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.openapi.OpenApiHelper;
import com.jingdong.sdk.platform.lib.utils.HostUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JDAddressSelectViewHelper {
    private static final String TAG = "JDAddressSelectViewHelper";
    private JDAddressSelectView.OnAddressLoadCompletedListener listener;
    private JDAddressSelectView mJDAddressSelectView;
    private FragmentActivity mMyActivity;
    private OnAddressListener mOnAddressListener;
    private int mType;
    private int pluginVersion;
    private int requestCode;
    private String saveBusiness;
    private String skuId;
    private String source;
    private String tabId;
    private String sceneId = "basicShoppingProcess";
    private boolean mIsDestroy = false;
    private JDAddressSelectView.AddressHelper addressHelper = new JDAddressSelectView.AddressHelper() { // from class: com.jingdong.common.utils.JDAddressSelectViewHelper.1
        @Override // com.jingdong.common.ui.JDAddressSelectView.AddressHelper
        public void close() {
            if (JDAddressSelectViewHelper.this.mOnAddressListener != null) {
                JDAddressSelectViewHelper.this.mOnAddressListener.onClose();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
        @Override // com.jingdong.common.ui.JDAddressSelectView.AddressHelper
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void loadAddress(int r14, java.lang.String r15, com.jingdong.common.entity.AddressGlobal r16, com.jingdong.common.ui.JDAddressSelectView.OnAddressLoadCompletedListener r17) {
            /*
                r13 = this;
                r0 = r13
                r3 = r14
                r1 = r16
                r2 = r17
                r4 = 11
                r5 = 1
                r6 = 0
                if (r3 != r5) goto L10
                java.lang.String r5 = "usualAddress"
            Le:
                r10 = 0
                goto L1a
            L10:
                r7 = 2
                if (r3 != r7) goto L16
                java.lang.String r5 = "getProvinces"
                goto Le
            L16:
                r5 = r15
                if (r3 != r4) goto Le
                r10 = 1
            L1a:
                com.jingdong.common.utils.JDAddressSelectViewHelper r6 = com.jingdong.common.utils.JDAddressSelectViewHelper.this
                com.jingdong.common.utils.JDAddressSelectViewHelper.access$002(r6, r2)
                if (r3 == r4) goto L62
                java.lang.String r4 = "directStock"
                boolean r4 = r5.equals(r4)
                if (r4 == 0) goto L2a
                goto L62
            L2a:
                com.jingdong.common.utils.JDAddressSelectViewHelper r2 = com.jingdong.common.utils.JDAddressSelectViewHelper.this
                java.lang.String r4 = com.jingdong.common.utils.JDAddressSelectViewHelper.access$200(r2)
                int r6 = r16.getIdProvince()
                java.lang.String r6 = java.lang.String.valueOf(r6)
                int r7 = r16.getIdCity()
                java.lang.String r7 = java.lang.String.valueOf(r7)
                int r8 = r16.getIdArea()
                java.lang.String r8 = java.lang.String.valueOf(r8)
                int r9 = r16.getIdTown()
                java.lang.String r9 = java.lang.String.valueOf(r9)
                boolean r11 = r1.isGangAoTai
                boolean r12 = r1.isForeignOverSea
                r1 = r2
                r2 = r5
                r3 = r14
                r5 = r6
                r6 = r7
                r7 = r8
                r8 = r9
                r9 = r10
                r10 = r11
                r11 = r12
                r1.queryThirdAddress(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
                goto L80
            L62:
                if (r2 == 0) goto L6f
                r5 = 1
                r6 = 11
                r7 = 0
                r8 = 0
                r9 = 0
                r4 = r17
                r4.onThirdAddressLoadCompleted(r5, r6, r7, r8, r9)
            L6f:
                com.jingdong.common.utils.JDAddressSelectViewHelper r2 = com.jingdong.common.utils.JDAddressSelectViewHelper.this
                com.jingdong.common.utils.JDAddressSelectViewHelper$OnAddressListener r2 = com.jingdong.common.utils.JDAddressSelectViewHelper.access$100(r2)
                if (r2 == 0) goto L80
                com.jingdong.common.utils.JDAddressSelectViewHelper r2 = com.jingdong.common.utils.JDAddressSelectViewHelper.this
                com.jingdong.common.utils.JDAddressSelectViewHelper$OnAddressListener r2 = com.jingdong.common.utils.JDAddressSelectViewHelper.access$100(r2)
                r2.onSelectCompleted(r14, r1, r10)
            L80:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.JDAddressSelectViewHelper.AnonymousClass1.loadAddress(int, java.lang.String, com.jingdong.common.entity.AddressGlobal, com.jingdong.common.ui.JDAddressSelectView$OnAddressLoadCompletedListener):void");
        }

        @Override // com.jingdong.common.ui.JDAddressSelectView.AddressHelper
        public void onAddressSelected(int i2, AddressGlobal addressGlobal) {
            if (OKLog.D) {
                OKLog.d(JDAddressSelectViewHelper.TAG, " onAddressSelected_flag -->>  " + i2);
            }
            if (JDAddressSelectViewHelper.this.mOnAddressListener != null) {
                JDAddressSelectViewHelper.this.mOnAddressListener.onAddressSelected(i2, addressGlobal);
            }
        }

        @Override // com.jingdong.common.ui.JDAddressSelectView.AddressHelper
        public void onHotCitySelected(String str, AddressGlobal addressGlobal) {
        }

        @Override // com.jingdong.common.ui.JDAddressSelectView.AddressHelper
        public void onTabSelected(String str) {
        }

        @Override // com.jingdong.common.ui.JDAddressSelectView.AddressHelper
        public boolean onThirdAddressHasNext(int i2, AddressBaseMode addressBaseMode) {
            return true;
        }

        @Override // com.jingdong.common.ui.JDAddressSelectView.AddressHelper
        public boolean onThirdAddressSelected(int i2, AddressBaseMode addressBaseMode) {
            return true;
        }
    };
    private JDHandler mJDHandler = new JDHandler();

    /* loaded from: classes6.dex */
    public static class AddressEntity {
        public int flag;
        public JDJSONObject jsonObject;
        public boolean success;

        public AddressEntity(int i2, boolean z, JDJSONObject jDJSONObject) {
            this.flag = i2;
            this.success = z;
            this.jsonObject = jDJSONObject;
        }
    }

    /* loaded from: classes6.dex */
    public interface OnAddressListener {
        void onAddressSelected(int i2, AddressGlobal addressGlobal);

        void onClose();

        void onLoadAddressed(boolean z, boolean z2, AddressEntity addressEntity);

        void onSelectCompleted(int i2, AddressGlobal addressGlobal, boolean z);
    }

    public JDAddressSelectViewHelper(FragmentActivity fragmentActivity, int i2) {
        this.mType = 2;
        this.mMyActivity = fragmentActivity;
        JDAddressSelectView jDAddressSelectView = new JDAddressSelectView(this.mMyActivity);
        this.mJDAddressSelectView = jDAddressSelectView;
        jDAddressSelectView.initData(i2, this.addressHelper);
        this.mType = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showData(AddressEntity addressEntity) {
        JDJSONObject jDJSONObject;
        if (this.mIsDestroy || addressEntity == null) {
            return;
        }
        if (OKLog.D) {
            String str = TAG;
            OKLog.d(str, "onFinish flag -->> " + addressEntity.flag);
            OKLog.d(str, "onFinish queryThirdAddressProvince isSucceed-->> " + addressEntity.success);
        }
        if (addressEntity.success && (jDJSONObject = addressEntity.jsonObject) != null) {
            if (!jDJSONObject.isNull("usualAddress")) {
                this.listener.onFullAddressLoadCompleted(true, toList(addressEntity.jsonObject.optJSONArray("usualAddress"), addressEntity.jsonObject.optBoolean("isEncrypt")));
                OnAddressListener onAddressListener = this.mOnAddressListener;
                if (onAddressListener != null) {
                    onAddressListener.onLoadAddressed(true, true, addressEntity);
                    return;
                }
                return;
            } else if (!addressEntity.jsonObject.isNull("addressList")) {
                ArrayList<AddressBaseMode> list = AddressBaseMode.toList(addressEntity.jsonObject.optJSONArray("addressList"), 0);
                try {
                    String string = addressEntity.jsonObject.getString("tabInfo");
                    if (!TextUtils.isEmpty(string)) {
                        if (OKLog.D) {
                            OKLog.d(TAG, " tabInfo -->>  " + string);
                        }
                        List parseArray = JDJSON.parseArray(string, TabInfoBean.class);
                        if (parseArray != null && parseArray.size() > 1 && parseArray.get(1) != null) {
                            this.tabId = ((TabInfoBean) parseArray.get(1)).getTabId();
                        }
                        JDAddressSelectView.OnAddressLoadCompletedListener onAddressLoadCompletedListener = this.listener;
                        int i2 = addressEntity.flag;
                        if (i2 == 1) {
                            i2 = 2;
                        }
                        onAddressLoadCompletedListener.onThirdAddressLoadCompleted(true, i2, list, new ArrayList<>(parseArray), null);
                    } else {
                        JDAddressSelectView.OnAddressLoadCompletedListener onAddressLoadCompletedListener2 = this.listener;
                        int i3 = addressEntity.flag;
                        onAddressLoadCompletedListener2.onThirdAddressLoadCompleted(true, i3 == 1 ? 2 : i3, list, null, null);
                    }
                    OnAddressListener onAddressListener2 = this.mOnAddressListener;
                    if (onAddressListener2 != null) {
                        onAddressListener2.onLoadAddressed(true, false, addressEntity);
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            } else if (addressEntity.jsonObject.optBoolean("toSkuDyInfo")) {
                JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
                if (jDAddressSelectView != null && !jDAddressSelectView.isClick) {
                    this.listener.onThirdAddressLoadCompleted(true, addressEntity.flag, null, null, null);
                    return;
                }
                this.listener.onThirdAddressLoadCompleted(true, 11, null, null, null);
                OnAddressListener onAddressListener3 = this.mOnAddressListener;
                if (onAddressListener3 != null) {
                    onAddressListener3.onSelectCompleted(addressEntity.flag, this.mJDAddressSelectView.getAddressGlobal(), false);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        this.listener.onThirdAddressLoadCompleted(false, addressEntity.flag, null, null, null);
        OnAddressListener onAddressListener4 = this.mOnAddressListener;
        if (onAddressListener4 != null) {
            onAddressListener4.onLoadAddressed(false, false, addressEntity);
        }
    }

    private ArrayList<AddressGlobal> toList(JDJSONArray jDJSONArray, boolean z) {
        String generateKey;
        ArrayList<AddressGlobal> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            AddressGlobal addressGlobal = new AddressGlobal();
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                addressGlobal.setId(optJSONObject.optLong("id"));
                addressGlobal.setIdProvince(optJSONObject.optInt(JDWeatherActionKey.PROVINCE_ID));
                addressGlobal.setIdCity(optJSONObject.optInt(JDWeatherActionKey.CITY_ID));
                addressGlobal.setIdArea(optJSONObject.optInt("countyId"));
                addressGlobal.setIdTown(optJSONObject.optInt(JDWeatherActionKey.TOWN_ID));
                addressGlobal.setLatitude(optJSONObject.optString(PdLVBody.GCLAT));
                addressGlobal.setLongitude(optJSONObject.optString(PdLVBody.GCLNG));
                addressGlobal.setProvinceName(optJSONObject.getString("provinceName"));
                addressGlobal.setCityName(optJSONObject.getString("cityName"));
                addressGlobal.setAreaName(optJSONObject.getString("areaName"));
                addressGlobal.setTownName(optJSONObject.getString("townName"));
                boolean optBoolean = optJSONObject.optBoolean("isDesCbc");
                String optString = optJSONObject.optString("fullAddress");
                String optString2 = optJSONObject.optString("addressDetail");
                try {
                    generateKey = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
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
                    } catch (Exception e3) {
                        OKLog.e(TAG, e3);
                    }
                    optString2 = "";
                }
                addressGlobal.setAddressDetail(optString2);
                addressGlobal.setWhere(optString);
                if (addressGlobal.getId() != 0) {
                    arrayList.add(addressGlobal);
                }
            }
        }
        return arrayList;
    }

    public boolean back() {
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView == null) {
            return false;
        }
        return jDAddressSelectView.back();
    }

    public void destroy() {
        this.mIsDestroy = true;
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView != null) {
            jDAddressSelectView.destroy();
        }
        this.listener = null;
        this.mOnAddressListener = null;
        this.mJDHandler = null;
    }

    public View getView() {
        return this.mJDAddressSelectView;
    }

    public void queryThirdAddress(String str, final int i2, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, boolean z3) {
        if (OKLog.D) {
            OKLog.d(TAG, " action -->>  " + str);
        }
        HttpSetting httpSetting = new HttpSetting();
        HttpGroup.OnAllListener onAllListener = new HttpGroup.OnAllListener() { // from class: com.jingdong.common.utils.JDAddressSelectViewHelper.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                final JDJSONObject fastJsonObject;
                if (JDAddressSelectViewHelper.this.mIsDestroy || (fastJsonObject = httpResponse.getFastJsonObject()) == null) {
                    return;
                }
                JDAddressSelectViewHelper.this.mJDHandler.post(new Runnable() { // from class: com.jingdong.common.utils.JDAddressSelectViewHelper.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JDAddressSelectViewHelper.this.mIsDestroy) {
                            return;
                        }
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        JDAddressSelectViewHelper.this.showData(new AddressEntity(i2, true, fastJsonObject));
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (JDAddressSelectViewHelper.this.mIsDestroy || JDAddressSelectViewHelper.this.mOnAddressListener == null) {
                    return;
                }
                JDAddressSelectViewHelper.this.mOnAddressListener.onLoadAddressed(false, false, new AddressEntity(i2, false, null));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        };
        if (this.mType == 2) {
            httpSetting.setFunctionId("thirdAddress");
        } else {
            httpSetting.setFunctionId("publicThirdAddress");
            if (this.mType == 4) {
                httpSetting.putJsonParam("channel", "1");
            } else {
                httpSetting.putJsonParam("channel", "2");
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            httpSetting.putJsonParam("skuId", str2);
        }
        if ((z3 || z2) && str.equals(JDAddressSelectView.OVRE_SEAS_ACTION)) {
            httpSetting.putJsonParam("tabId", this.tabId);
        }
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("isReturnStock", Boolean.TRUE);
        httpSetting.putJsonParam("pluginVersion", Integer.valueOf(this.pluginVersion));
        httpSetting.putJsonParam("action", str);
        httpSetting.putJsonParam(JDWeatherActionKey.PROVINCE_ID, str3);
        httpSetting.putJsonParam(JDWeatherActionKey.CITY_ID, str4);
        httpSetting.putJsonParam("countyId", str5);
        httpSetting.putJsonParam(JDWeatherActionKey.TOWN_ID, str6);
        if (z) {
            httpSetting.putJsonParam("changeUsual", "1");
        }
        httpSetting.setListener(onAllListener);
        httpSetting.setHost(HostUtils.getWareHost());
        httpSetting.setNotifyUser(true);
        FragmentActivity fragmentActivity = this.mMyActivity;
        if (fragmentActivity instanceof IMyActivity) {
            HttpGroup httpGroupaAsynPool = ((IMyActivity) fragmentActivity).getHttpGroupaAsynPool();
            if (httpGroupaAsynPool != null) {
                httpGroupaAsynPool.add(httpSetting);
                return;
            }
            return;
        }
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void refresh() {
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView != null) {
            jDAddressSelectView.refresh();
        }
    }

    public void setData(String str, int i2) {
        this.skuId = str;
        this.pluginVersion = i2;
    }

    public void setJMAHelper(JDAddressSelectView.JMAHelper jMAHelper) {
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView == null || jMAHelper == null) {
            return;
        }
        jDAddressSelectView.addJMAHelper(jMAHelper);
    }

    public void setLocationFunctionSwitch(boolean z, int i2) {
        this.mJDAddressSelectView.setLocationFunction(z, i2);
    }

    public void setLocationListener(ILocationClickStateListener iLocationClickStateListener) {
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView == null || iLocationClickStateListener == null) {
            return;
        }
        jDAddressSelectView.setLocationListener(iLocationClickStateListener);
    }

    public void setLocationViewShowListener(OnLocationViewShowListener onLocationViewShowListener) {
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView == null || onLocationViewShowListener == null) {
            return;
        }
        jDAddressSelectView.setLocationViewShowListener(onLocationViewShowListener);
    }

    public void setOnAddressListener(OnAddressListener onAddressListener) {
        this.mOnAddressListener = onAddressListener;
    }

    public JDAddressSelectViewHelper setRequestCode(int i2) {
        this.requestCode = i2;
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView != null) {
            jDAddressSelectView.setRequestCode(i2);
        }
        return this;
    }

    public JDAddressSelectViewHelper setSaveBusiness(String str) {
        this.saveBusiness = str;
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView != null) {
            jDAddressSelectView.setSaveBusiness(str);
        }
        return this;
    }

    public JDAddressSelectViewHelper setSceneId(String str) {
        this.sceneId = str;
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView != null) {
            jDAddressSelectView.setSceneId(str);
        }
        return this;
    }

    public JDAddressSelectViewHelper setSource(String str) {
        this.source = str;
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView != null) {
            jDAddressSelectView.setSource(str);
        }
        return this;
    }

    public void setStyle() {
        JDAddressSelectView jDAddressSelectView = this.mJDAddressSelectView;
        if (jDAddressSelectView != null) {
            jDAddressSelectView.setStyle();
        }
    }

    public void showAddress() {
        showAddress(false, false);
    }

    public void showAddress(boolean z) {
        showAddress(false, z);
    }

    public void showAddress(boolean z, boolean z2) {
        if (this.mJDAddressSelectView != null) {
            AddressGlobal addressGlobal = OpenApiHelper.getIAddressUtil().getAddressGlobal();
            this.mJDAddressSelectView.setTitleText(z2);
            this.mJDAddressSelectView.setResource(z);
            this.mJDAddressSelectView.showAddress(addressGlobal);
        }
    }

    public JDAddressSelectViewHelper(FragmentActivity fragmentActivity, int i2, boolean z) {
        this.mType = 2;
        this.mMyActivity = fragmentActivity;
        JDAddressSelectView jDAddressSelectView = new JDAddressSelectView(this.mMyActivity, z);
        this.mJDAddressSelectView = jDAddressSelectView;
        jDAddressSelectView.initData(i2, this.addressHelper);
        this.mType = i2;
    }
}
