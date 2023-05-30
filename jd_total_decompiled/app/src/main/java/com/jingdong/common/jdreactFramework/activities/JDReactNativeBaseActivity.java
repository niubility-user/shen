package com.jingdong.common.jdreactFramework.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import com.jd.verify.ShowCapCallback;
import com.jd.verify.Verify;
import com.jd.verify.VerifyPrivacyInfoProxy;
import com.jd.verify.model.IninVerifyInfo;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.app.mall.privacy.JDPrivacyDialogInfo;
import com.jingdong.app.mall.privacy.JDPrivacyDialogUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.JDReactPackage;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment;
import com.jingdong.common.jdreactFramework.broadcast.JDReactWxPayResultBroadcastReceiver;
import com.jingdong.common.jdreactFramework.helper.LoadExceptionListener;
import com.jingdong.common.jdreactFramework.helper.LoadListener;
import com.jingdong.common.jdreactFramework.helper.PermissionHelper;
import com.jingdong.common.jdreactFramework.helper.UIModeHelper;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeVerifyMoudle;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.apm.JDReactAPM;
import com.jingdong.common.login.LoginLocationUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.NewMessagRedManager;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class JDReactNativeBaseActivity extends BaseActivity implements View.OnClickListener, JDReactNativeVerifyMoudle.VerfyInterface, JDReactNativeBaseFragment.JDReactCallback, LoadExceptionListener, OnXViewActionListener, LoadListener {
    private static final int DELAY_SEND_MESSAGE = 1890;
    private static final int FREE_VERIFY_DIALOG = 1889;
    private static final int SHOW_VERIFY_DIALOG = 1888;
    private static final String TAG = "JDReactActivity";
    private JDReactModuleEntity entity;
    private JDCallback errorCB;
    private JDReactNativeBaseFragment mJDReactNativeBaseFragment;
    JDReactWxPayResultBroadcastReceiver mJDReactWxPayResultBroadcastReceiver;
    private LayoutSetup mLayoutSetup;
    ReactApplicationContext mReactApplicationContext;
    private JDCallback successCB;
    private UIModeHelper.Unregister unregister;

    /* renamed from: verify */
    private Verify f12339verify;
    private String shareUrl = "";
    private Handler mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.1
        {
            JDReactNativeBaseActivity.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1888:
                    JDReactNativeBaseActivity.this.freeVerifyDialog();
                    if (JDReactNativeBaseActivity.this.isFinishing()) {
                        return;
                    }
                    final VerifyData verifyData = (VerifyData) message.obj;
                    JDReactNativeBaseActivity.this.f12339verify = Verify.getInstance();
                    JDReactNativeBaseActivity.this.f12339verify.setPrivacyInfoProxy(new VerifyPrivacyInfoProxy() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // com.jd.verify.VerifyPrivacyInfoProxy
                        public String getPrivacyAndroidId() {
                            return BaseInfo.getAndroidId();
                        }

                        @Override // com.jd.verify.VerifyPrivacyInfoProxy
                        public String getPrivacyDeviceBrand() {
                            return BaseInfo.getDeviceBrand();
                        }

                        @Override // com.jd.verify.VerifyPrivacyInfoProxy
                        public String getPrivacyDeviceModel() {
                            return BaseInfo.getDeviceModel();
                        }

                        @Override // com.jd.verify.VerifyPrivacyInfoProxy
                        public String getPrivacyLatitude() {
                            try {
                                return LoginLocationUtil.getCacheLocation().getLat() + "";
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return "";
                            }
                        }

                        @Override // com.jd.verify.VerifyPrivacyInfoProxy
                        public String getPrivacyLongitude() {
                            try {
                                return LoginLocationUtil.getCacheLocation().getLng() + "";
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return "";
                            }
                        }

                        @Override // com.jd.verify.VerifyPrivacyInfoProxy
                        public String getPrivacyScreen() {
                            return BaseInfo.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + BaseInfo.getScreenWidth();
                        }

                        @Override // com.jd.verify.VerifyPrivacyInfoProxy
                        public String getPrivateOSRelease() {
                            return BaseInfo.getAndroidVersion();
                        }
                    });
                    JDReactNativeBaseActivity.this.f12339verify.setLoading(true);
                    if (!TextUtils.isEmpty(verifyData.session_id)) {
                        JDReactNativeBaseActivity.this.f12339verify.init(verifyData.session_id, JDReactNativeBaseActivity.this, verifyData.uuid, verifyData.countryCode, verifyData.account, new ShowCapCallback() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.1.2
                            {
                                AnonymousClass1.this = this;
                            }

                            @Override // com.jd.verify.CallBack
                            public void invalidSessiongId() {
                                JDCallback jDCallback = verifyData.error;
                                if (jDCallback != null) {
                                    jDCallback.invoke("invalid");
                                    verifyData.error = null;
                                }
                            }

                            @Override // com.jd.verify.ShowCapCallback
                            public void loadFail() {
                                JDCallback jDCallback = verifyData.error;
                                if (jDCallback != null) {
                                    jDCallback.invoke("loadFail");
                                    verifyData.error = null;
                                }
                            }

                            @Override // com.jd.verify.InnerCallBack
                            public void onFail(String str) {
                                verifyData.error.invoke(str);
                            }

                            @Override // com.jd.verify.SSLDialogCallback
                            public void onSSLError() {
                                JLog.d(JDReactNativeBaseActivity.TAG, "verify onSSLError");
                            }

                            @Override // com.jd.verify.InnerCallBack
                            public void onSuccess(IninVerifyInfo ininVerifyInfo) {
                                WritableMap createMap = Arguments.createMap();
                                if (ininVerifyInfo != null) {
                                    createMap.putInt("code", ininVerifyInfo.getCode());
                                    if (!TextUtils.isEmpty(ininVerifyInfo.getErrorMsg())) {
                                        createMap.putString("msg", ininVerifyInfo.getErrorMsg());
                                    } else {
                                        createMap.putString("msg", "");
                                    }
                                    if (!TextUtils.isEmpty(ininVerifyInfo.getFp())) {
                                        createMap.putString(WebPerfManager.FP, ininVerifyInfo.getFp());
                                    } else {
                                        createMap.putString(WebPerfManager.FP, "");
                                    }
                                    createMap.putInt("tp", ininVerifyInfo.getTp());
                                    if (!TextUtils.isEmpty(ininVerifyInfo.getSt())) {
                                        createMap.putString("st", ininVerifyInfo.getSt());
                                    } else {
                                        createMap.putString("st", "");
                                    }
                                    if (!TextUtils.isEmpty(ininVerifyInfo.getVt())) {
                                        createMap.putString("vt", ininVerifyInfo.getVt());
                                    } else {
                                        createMap.putString("vt", "");
                                    }
                                }
                                JDCallback jDCallback = verifyData.suc;
                                if (jDCallback != null) {
                                    jDCallback.invoke(createMap);
                                    verifyData.suc = null;
                                }
                            }

                            @Override // com.jd.verify.CallBack
                            public void showButton(int i2) {
                                JLog.d(JDReactNativeBaseActivity.TAG, "verify showButton type is " + i2);
                            }

                            @Override // com.jd.verify.ShowCapCallback
                            public void showCap() {
                                JLog.d(JDReactNativeBaseActivity.TAG, "verify showCap");
                            }
                        });
                        return;
                    }
                    JDCallback jDCallback = verifyData.error;
                    if (jDCallback != null) {
                        jDCallback.invoke("sessionId is empty");
                        verifyData.error = null;
                        return;
                    }
                    return;
                case 1889:
                    JDReactNativeBaseActivity.this.freeVerifyDialog();
                    return;
                case 1890:
                    NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
                    NewMessagRedManager.requestMessage(JDReactNativeBaseActivity.this.getHttpGroupaAsynPool());
                    return;
                default:
                    return;
            }
        }
    };

    /* loaded from: classes5.dex */
    public interface LayoutSetup {
        int getLayout();

        void initLayout(ReactRootView reactRootView, String str, boolean z, String str2, String str3);
    }

    /* loaded from: classes5.dex */
    public class VerifyData {
        String account;
        String countryCode;
        JDCallback error;
        String session_id;
        JDCallback suc;
        String uuid;

        public VerifyData() {
            JDReactNativeBaseActivity.this = r1;
        }
    }

    private void handleChargeCard(Intent intent) {
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("selectCouponLists");
        if (this.mJDReactNativeBaseFragment.getJDReact() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager().getCurrentReactContext() == null) {
            return;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("selectCouponLists", stringExtra);
        sendEvent("couponSelectBack", createMap);
    }

    private void handleChargeCity(Intent intent) {
        if (intent == null) {
            return;
        }
        long longExtra = intent.getLongExtra("charge_city_id", -1L);
        String stringExtra = intent.getStringExtra("charge_city_name");
        String stringExtra2 = intent.getStringExtra("charge_city_cityPinyinOne");
        if (this.mJDReactNativeBaseFragment.getJDReact() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager().getCurrentReactContext() == null) {
            return;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString(JDWeatherActionKey.CITY_ID, longExtra + "");
        createMap.putString("cityName", stringExtra);
        createMap.putString("cityPinyinOne", stringExtra2);
        sendEvent(JDReactConstant.MESSAGE_TAG_CHARGE_SELECT_CITY, createMap);
    }

    private void handleJDpayResult(Intent intent) {
        if (intent == null) {
            JDCallback jDCallback = this.errorCB;
            if (jDCallback != null) {
                jDCallback.invoke(new Object[0]);
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra("jdpay_Result");
        JDCallback jDCallback2 = this.successCB;
        if (jDCallback2 != null) {
            jDCallback2.invoke(stringExtra);
        }
    }

    @SuppressLint({HttpHeaders.RANGE})
    private void parsePhoneNumber(Intent intent) {
        String str;
        if (intent == null) {
            return;
        }
        Uri data = intent.getData();
        String str2 = "";
        if (data != null) {
            Cursor query = getContentResolver().query(data, null, null, null, null);
            if (query == null || !query.moveToFirst()) {
                str = "";
            } else {
                str2 = query.getString(query.getColumnIndex("display_name"));
                str = query.getString(query.getColumnIndex("data1"));
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
        } else {
            str = "";
        }
        if (this.mJDReactNativeBaseFragment.getJDReact() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager().getCurrentReactContext() == null) {
            return;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("name", str2);
        createMap.putString("number", str);
        sendEvent(JDReactConstant.MESSAGE_TAG_PICK_CONTACT, createMap);
    }

    @SuppressLint({HttpHeaders.RANGE})
    private void parsePhoneNumber2(Intent intent) {
        String str;
        String str2;
        if (intent == null) {
            return;
        }
        Uri data = intent.getData();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = null;
        String str3 = null;
        if (data != null) {
            Cursor query = contentResolver.query(data, new String[]{"display_name", "data1"}, null, null, null);
            str2 = null;
            if (query != null) {
                while (query.moveToNext()) {
                    str2 = query.getString(query.getColumnIndex("display_name"));
                    str3 = query.getString(query.getColumnIndex("data1"));
                }
                str = str3;
                cursor = query;
            } else {
                cursor = query;
                str = null;
            }
        } else {
            str = null;
            str2 = null;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (str != null) {
            str = str.trim().replaceAll(LangUtils.SINGLE_SPACE, "");
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (this.mJDReactNativeBaseFragment.getJDReact() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager() == null || this.mJDReactNativeBaseFragment.getJDReact().getReactManager().getCurrentReactContext() == null) {
            return;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("name", str2);
        createMap.putString("number", str);
        sendEvent(JDReactConstant.MESSAGE_TAG_PICK_CONTACT, createMap);
    }

    private void sendWeixinEvent(String str) {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, Arguments.createMap());
            this.mReactApplicationContext = null;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void addRootView(ReactRootView reactRootView, String str, boolean z, String str2, final String str3) {
        reactRootView.setEventListener(new ReactRootView.ReactRootViewEventListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.2
            {
                JDReactNativeBaseActivity.this = this;
            }

            @Override // com.facebook.react.ReactRootView.ReactRootViewEventListener
            public void onAttachedToReactInstance(ReactRootView reactRootView2) {
                JDReactAPM.getInstance().recordFsRenderTime(str3);
            }
        });
        initView(reactRootView, str, z, str2, str2);
    }

    public void changeStatusBarColor(final int i2) {
        if (isFinishing()) {
            return;
        }
        post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.5
            {
                JDReactNativeBaseActivity.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                UnStatusBarTintUtil.setBackgroundColor(JDReactNativeBaseActivity.this, i2);
            }
        });
    }

    public void clearCB() {
        this.successCB = null;
        this.errorCB = null;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void clearFresco() {
        clearImageMemory();
    }

    public abstract void clearImageMemory();

    public void closeXView() {
    }

    public abstract Fragment createMFragement(String str);

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public Fragment createWebFragement(String str) {
        return createMFragement(str);
    }

    public void destroyXView() {
    }

    public abstract void enablePV(boolean z);

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void enablePVMta(boolean z) {
        enablePV(z);
    }

    public boolean forceCloseXView() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactNativeVerifyMoudle.VerfyInterface
    public void freeVerify() {
        this.mHandler.removeMessages(1889);
        this.mHandler.sendEmptyMessage(1889);
    }

    public void freeVerifyDialog() {
        Verify verify2 = this.f12339verify;
        if (verify2 != null) {
            verify2.free();
            this.f12339verify = null;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public int getLayoutID() {
        return getLayoutResource();
    }

    public int getLayoutResource() {
        LayoutSetup layoutSetup = this.mLayoutSetup;
        if (layoutSetup != null) {
            return layoutSetup.getLayout();
        }
        return R.layout.jdreactnative_layout_common;
    }

    public abstract String getRNTitle();

    public abstract JDReactModuleEntity getReactEntity();

    public ReactPackage getReactPackage() {
        if (AbstractJDReactInitialHelper.getPackageManger() != null) {
            return AbstractJDReactInitialHelper.getPackageManger();
        }
        return new JDReactPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public ReactPackage getReactPackageManger() {
        return getReactPackage();
    }

    @Override // com.jingdong.common.BaseActivity
    public Map<String, String> getScreenShotPageParams() {
        ArrayMap arrayMap = new ArrayMap();
        if (!this.shareUrl.isEmpty()) {
            JLog.d(TAG, "getScreenShotPageParams set url" + this.shareUrl);
            arrayMap.put("shareUrl", this.shareUrl);
            return arrayMap;
        }
        Bundle bundle = this.entity.getmLaunchOptions();
        if (bundle == null || !bundle.containsKey("shareUrl")) {
            return null;
        }
        String str = (String) bundle.get("shareUrl");
        arrayMap.put("shareUrl", str);
        JLog.d(TAG, "getScreenShotPageParams set open app url" + str);
        return arrayMap;
    }

    public void initView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        LayoutSetup layoutSetup = this.mLayoutSetup;
        if (layoutSetup != null) {
            layoutSetup.initLayout(reactRootView, str, z, str2, str3);
            return;
        }
        ImageView imageView = (ImageView) findViewById(R.id.llBtnBack);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.reactRootViewHolder);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.parent);
        TextView textView = (TextView) findViewById(R.id.reactTitle);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.rlTop);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            if (relativeLayout2 != null) {
                if (z) {
                    relativeLayout2.setVisibility(8);
                } else {
                    relativeLayout2.setVisibility(0);
                }
            }
            if (imageView != null) {
                imageView.setVisibility(0);
                imageView.setOnClickListener(this);
            }
            if (linearLayout != null) {
                linearLayout.addView(reactRootView, -1, -1);
                return;
            }
            return;
        }
        finish();
    }

    public abstract boolean isDebug();

    public boolean isForceRefresh() {
        Bundle bundle;
        JDReactModuleEntity jDReactModuleEntity = this.entity;
        if (jDReactModuleEntity != null && (bundle = jDReactModuleEntity.getmLaunchOptions()) != null && bundle.containsKey("screenRefresh")) {
            Object obj = bundle.get("screenRefresh");
            if ((obj instanceof String) && DYConstants.DY_FALSE.equals((String) obj)) {
                return false;
            }
            if (obj instanceof Boolean) {
                return bundle.getBoolean("screenRefresh", true);
            }
        }
        return true;
    }

    public abstract boolean isHiden();

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public boolean isOpenLoadingView() {
        return showLoading();
    }

    public boolean isTransparentenable() {
        Bundle bundle;
        JDReactModuleEntity jDReactModuleEntity = this.entity;
        if (jDReactModuleEntity == null || (bundle = jDReactModuleEntity.getmLaunchOptions()) == null) {
            return false;
        }
        return bundle.getBoolean("transparentenable", false);
    }

    public abstract boolean launchActivityWithOpenapp(String str);

    public abstract boolean launchMpage(String str);

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void lunchOpenApp(String str) {
        launchActivityWithOpenapp(str);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void lunchWebPage(String str) {
        launchMpage(str);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        this.mJDReactNativeBaseFragment.onActivityForResult(i2, i3, intent);
        if (i3 == -1) {
            switch (i2) {
                case 1001:
                    parsePhoneNumber(intent);
                    return;
                case 1002:
                    handleChargeCity(intent);
                    return;
                case 1003:
                    handleChargeCard(intent);
                    return;
                case 1004:
                default:
                    super.onActivityResult(i2, i3, intent);
                    return;
                case 1005:
                    parsePhoneNumber2(intent);
                    return;
            }
        } else if (i3 == 1024) {
            if (i2 != 1004) {
                super.onActivityResult(i2, i3, intent);
                return;
            }
            handleJDpayResult(intent);
            clearCB();
        } else if (i3 == 0) {
            switch (i2) {
                case 1001:
                case 1002:
                case 1003:
                case 1005:
                    return;
                case 1004:
                default:
                    super.onActivityResult(i2, i3, intent);
                    return;
            }
        } else {
            super.onActivityResult(i2, i3, intent);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void onBackPressedCalled() {
        onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.llBtnBack) {
            finish();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        String str3;
        final String str4;
        JDPrivacyDialogInfo jDPrivacyDialogInfo;
        final String str5;
        JDReactModuleEntity reactEntity = getReactEntity();
        this.entity = reactEntity;
        String str6 = reactEntity.getmBundleName();
        String str7 = this.entity.getmModuleName();
        this.statusBarTransparentEnable = this.entity.getTransparentEnable();
        Bundle bundle2 = this.entity.getmLaunchOptions();
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (!this.statusBarTransparentEnable && JDReactHelper.newInstance().getUIModeHelper() != null) {
            this.statusBarDarkModeEnable = true;
            this.unregister = JDReactHelper.newInstance().getUIModeHelper().onRegisterUIModeChangeListener(new UIModeHelper.UIModeChangeListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.3
                {
                    JDReactNativeBaseActivity.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.helper.UIModeHelper.UIModeChangeListener
                public void onChange(int i2, String str8) {
                    JDReactNativeBaseActivity.this.statusBarRefresh();
                }
            });
        }
        setContentView(R.layout.jdreactnative_layout_main);
        String stringExtra = intent.getStringExtra("pluginPath");
        boolean booleanExtra = intent.getBooleanExtra("download_failed", false);
        boolean booleanExtra2 = intent.getBooleanExtra("force", false);
        String stringExtra2 = intent.getStringExtra("version");
        String stringExtra3 = intent.getStringExtra(JDReactConstant.COMMITID);
        JDReactHelper.newInstance().setJDReactHelperCallback(new JDReactExtendHelperCallback());
        boolean booleanExtra3 = intent.getBooleanExtra("localDebug", false);
        String stringExtra4 = intent.getStringExtra("destBundlePath");
        if (!TextUtils.isEmpty(stringExtra4)) {
            this.mJDReactNativeBaseFragment = JDReactNativeBaseFragment.newInstance(str6, str7, bundle2, true, getRNTitle(), false, stringExtra4);
        } else if (booleanExtra3) {
            this.mJDReactNativeBaseFragment = JDReactNativeBaseFragment.newInstance(str6, str7, bundle2, true, getRNTitle(), true, null);
        } else {
            boolean isDebug = isDebug();
            String rNTitle = getRNTitle();
            boolean isHiden = isHiden();
            str = stringExtra3;
            str2 = JDReactConstant.COMMITID;
            str3 = str6;
            str4 = stringExtra2;
            this.mJDReactNativeBaseFragment = JDReactNativeBaseFragment.newInstance(str6, str7, bundle2, isDebug, stringExtra2, booleanExtra, booleanExtra2, stringExtra, null, true, rNTitle, isHiden);
            this.mJDReactNativeBaseFragment.setParam(str2, str);
            this.mJDReactNativeBaseFragment.setLoadExceptionListener(this);
            this.mJDReactNativeBaseFragment.setH5Params(intent.getStringExtra("h5params"));
            this.mJDReactNativeBaseFragment.setJDReactCallback(this);
            this.mJDReactNativeBaseFragment.setLoadListener(this);
            jDPrivacyDialogInfo = new JDPrivacyDialogInfo();
            jDPrivacyDialogInfo.build("RN", str7, str7);
            str5 = str3;
            if (JDPrivacyDialogUtil.checkPrivacyDialog(this, jDPrivacyDialogInfo, new JDPrivacyDialogUtil.IDialogListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.4
                {
                    JDReactNativeBaseActivity.this = this;
                }

                @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
                public void onAgree() {
                    JDReactNativeBaseActivity.this.replaceView(str5, str4);
                }

                @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
                public void onDisagree() {
                    JDReactNativeBaseActivity.this.finish();
                }

                @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
                public void onNotice(String str8) {
                    super.onNotice(str8);
                }
            }) != null) {
                replaceView(str5, str4);
                return;
            }
            return;
        }
        str3 = str6;
        str = stringExtra3;
        str2 = JDReactConstant.COMMITID;
        str4 = stringExtra2;
        this.mJDReactNativeBaseFragment.setParam(str2, str);
        this.mJDReactNativeBaseFragment.setLoadExceptionListener(this);
        this.mJDReactNativeBaseFragment.setH5Params(intent.getStringExtra("h5params"));
        this.mJDReactNativeBaseFragment.setJDReactCallback(this);
        this.mJDReactNativeBaseFragment.setLoadListener(this);
        jDPrivacyDialogInfo = new JDPrivacyDialogInfo();
        jDPrivacyDialogInfo.build("RN", str7, str7);
        str5 = str3;
        if (JDPrivacyDialogUtil.checkPrivacyDialog(this, jDPrivacyDialogInfo, new JDPrivacyDialogUtil.IDialogListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity.4
            {
                JDReactNativeBaseActivity.this = this;
            }

            @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
            public void onAgree() {
                JDReactNativeBaseActivity.this.replaceView(str5, str4);
            }

            @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
            public void onDisagree() {
                JDReactNativeBaseActivity.this.finish();
            }

            @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
            public void onNotice(String str8) {
                super.onNotice(str8);
            }
        }) != null) {
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unRegistWXPayBroadcast();
        freeVerifyDialog();
        UIModeHelper.Unregister unregister = this.unregister;
        if (unregister != null) {
            unregister.unregister();
        }
        if (this.entity != null) {
            JDReactAPM.getInstance().destroy(this.entity.getmBundleName());
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && this.mJDReactNativeBaseFragment != null) {
            if (forceCloseXView()) {
                return true;
            }
            this.mJDReactNativeBaseFragment.onBackPressed();
            return false;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        JDReactNativeBaseFragment jDReactNativeBaseFragment;
        if (i2 == 82 && (jDReactNativeBaseFragment = this.mJDReactNativeBaseFragment) != null) {
            return jDReactNativeBaseFragment.onMenuKeyUp();
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.LoadExceptionListener
    public void onLoadException(int i2, String str, String str2, ReactRootView reactRootView) {
    }

    @Override // com.jingdong.common.jdreactFramework.helper.LoadListener
    public void onLoadFinish(String str, String str2, String str3) {
    }

    @Override // com.jingdong.common.jdreactFramework.helper.LoadListener
    public void onLoadStart(String str, String str2, String str3) {
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        PermissionHelper.dispatchPermissionResult(this, i2, strArr, iArr);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (overlapInputMethod()) {
            getWindow().setSoftInputMode(32);
        }
    }

    public boolean overlapInputMethod() {
        Bundle bundle;
        JDReactModuleEntity jDReactModuleEntity = this.entity;
        if (jDReactModuleEntity == null || (bundle = jDReactModuleEntity.getmLaunchOptions()) == null) {
            return false;
        }
        return bundle.getBoolean("overlayInput", false);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void refreshStatusBar(boolean z) {
        this.statusBarTransparentEnable = z;
        if (z) {
            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(16908290)).getChildAt(0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin - UnStatusBarTintUtil.getStatusBarHeight((Activity) this), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            viewGroup.setLayoutParams(marginLayoutParams);
        }
        UnStatusBarTintUtil.setStatusBar4Base(this, this.statusBarTransparentEnable ? 1 : 0);
    }

    public void registWXPayBroadcast(Context context) {
        unRegistWXPayBroadcast();
        this.mReactApplicationContext = (ReactApplicationContext) context;
        this.mJDReactWxPayResultBroadcastReceiver = new JDReactWxPayResultBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jd.wxPayResult");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mJDReactWxPayResultBroadcastReceiver, intentFilter);
    }

    protected void replaceView(String str, String str2) {
        JDMtaUtils.sendExposureData(this, null, "JDReact_Container", str, "JDReact_JSbundle_ModuleName", str2, "", "", "");
        getSupportFragmentManager().beginTransaction().add(R.id.main, this.mJDReactNativeBaseFragment).commitAllowingStateLoss();
        JDReactAPM.getInstance().recordStartTime(str);
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        JDReactNativeBaseFragment jDReactNativeBaseFragment = this.mJDReactNativeBaseFragment;
        if (jDReactNativeBaseFragment != null) {
            jDReactNativeBaseFragment.sendEvent(str, writableMap);
        }
    }

    public void setErrorCB(JDCallback jDCallback) {
        this.errorCB = jDCallback;
    }

    public void setLayoutSetup(LayoutSetup layoutSetup) {
        this.mLayoutSetup = layoutSetup;
    }

    public void setSuccessCB(JDCallback jDCallback) {
        this.successCB = jDCallback;
    }

    public void setUrl(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap == null || !hashMap.containsKey("shareUrl")) {
            return;
        }
        this.shareUrl = (String) hashMap.get("shareUrl");
        JLog.d(TAG, " setUrl data" + this.shareUrl);
    }

    public abstract boolean showLoading();

    @Override // com.jingdong.common.jdreactFramework.modules.JDReactNativeVerifyMoudle.VerfyInterface
    public void showVeriyDialog(JDCallback jDCallback, JDCallback jDCallback2, String str, String str2, String str3, String str4) {
        VerifyData verifyData = new VerifyData();
        verifyData.error = jDCallback;
        verifyData.suc = jDCallback2;
        verifyData.uuid = str;
        verifyData.session_id = str2;
        if (TextUtils.isEmpty(str4)) {
            str4 = "";
        }
        verifyData.account = str4;
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        verifyData.countryCode = str3;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1888, verifyData));
    }

    public void showXView(int i2, String str, boolean z, long j2, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    public void showXView(int i2, String str, boolean z, long j2, boolean z2, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    public void unRegistWXPayBroadcast() {
        unRegistWXPayBroadcast(null);
    }

    public void unRegistWXPayBroadcast(Intent intent) {
        if (this.mJDReactWxPayResultBroadcastReceiver != null) {
            if (intent != null && "com.jd.wxPayResult".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("errCode", 10);
                if (intExtra == 0) {
                    sendWeixinEvent("JDReactWXPaySuccess");
                } else if (intExtra == -2) {
                    sendWeixinEvent("JDReactWXPayCancel");
                } else {
                    sendWeixinEvent("JDReactWXPayFaild");
                }
            }
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mJDReactWxPayResultBroadcastReceiver);
            this.mJDReactWxPayResultBroadcastReceiver = null;
            this.successCB = null;
            this.errorCB = null;
        }
    }
}
