package com.jd.skin.lib;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.TextView;
import com.jd.framework.json.JDJSON;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.skin.lib.Utils.ConstancesUtils;
import com.jd.skin.lib.Utils.FileUtils;
import com.jd.skin.lib.Utils.JDSkinSharedPreferencesUtils;
import com.jd.skin.lib.bean.ResourceItems;
import com.jd.skin.lib.controller.JDSkinDataController;
import com.jd.skin.lib.db.AppStateType;
import com.jd.skin.lib.db.JDSelectCodeRunnable;
import com.jd.skin.lib.db.JDSelectCodesRunnable;
import com.jd.skin.lib.db.JDSkinDBController;
import com.jd.skin.lib.db.ResultCallback;
import com.jd.skin.lib.db.ResultListCallback;
import com.jd.skin.lib.db.SelectType;
import com.jd.skin.lib.inter.OnResultCompletListener;
import com.jd.skin.lib.inter.OnSkinElementsChangeListener;
import com.jd.skin.lib.net.RequestCurrentData;
import com.jd.skin.lib.receiver.LoginAndExitReceiver;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDSkinSDK {
    private static HashMap<String, ResourceItems> localContainer = new HashMap<>();
    private static volatile JDSkinSDK mSkinSDK;
    private LoginAndExitReceiver loginAndExitReceiver;
    private Context mContext;
    private boolean isNeedLogin = false;
    private boolean isLogin = false;
    private String appCode = "";
    private final String[] brandgradient_01 = {"#FF404F", JDDarkUtil.COLOR_FA2C19};
    private final String[] brandgradient_01_dark = {"#FF4C5B", "#FA3725"};
    private LruCache<String, ResourceItems> mLruCache = new LruCache<>(102400);
    private boolean isGetDateFromLocal = false;

    private JDSkinSDK() {
    }

    private void checkIsInitSdk() {
        if (this.mContext == null) {
            try {
                throw new Exception("JDSkinSDK should be init first !");
            } catch (Exception unused) {
            }
        }
    }

    private String getBaseResData(String str, int i2, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            boolean isDarkMode = z ? isDarkMode() : false;
            LruCache<String, ResourceItems> lruCache = this.mLruCache;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = "Dark";
            sb.append(isDarkMode ? "Dark" : "");
            ResourceItems resourceItems = lruCache.get(sb.toString());
            if (resourceItems == null) {
                resourceItems = JDSkinDBController.queryDataByCode(str, isDarkMode);
                if (resourceItems == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(isDarkMode ? "Dark" : "");
                    resourceItems = getResFromLocal(sb2.toString());
                }
                LruCache<String, ResourceItems> lruCache2 = this.mLruCache;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                if (!isDarkMode) {
                    str2 = "";
                }
                sb3.append(str2);
                lruCache2.put(sb3.toString(), resourceItems);
            }
            if (resourceItems != null) {
                if (i2 == 0) {
                    return resourceItems.getBgColor();
                }
                return i2 == 1 ? resourceItems.getFontSize() : "";
            }
            return "";
        } catch (Exception unused) {
            return null;
        }
    }

    private void getData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        checkIsInitSdk();
        RequestCurrentData.getInstance().requestData(str, new OnResultCompletListener() { // from class: com.jd.skin.lib.JDSkinSDK.1
            {
                JDSkinSDK.this = this;
            }

            @Override // com.jd.skin.lib.inter.OnResultCompletListener
            public void complet(boolean z, String str2) {
                JDSkinDataController.getInstance().saveData(z, str2);
            }
        });
    }

    public static JDSkinSDK getInstance() {
        if (mSkinSDK == null) {
            synchronized (JDSkinSDK.class) {
                if (mSkinSDK == null) {
                    mSkinSDK = new JDSkinSDK();
                }
            }
        }
        return mSkinSDK;
    }

    private ResourceItems getResFromLocal(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HashMap<String, ResourceItems> hashMap = localContainer;
        if (hashMap != null && hashMap.size() > 0 && localContainer.containsKey(str)) {
            return localContainer.get(str);
        }
        try {
            JSONArray optJSONArray = new JSONObject(FileUtils.getJson(this.mContext, "base.json")).optJSONArray("materiaList");
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    ResourceItems resourceItems = (ResourceItems) JDJSON.parseObject(optJSONArray.optJSONObject(i2).toString(), ResourceItems.class);
                    localContainer.put(resourceItems.getCode(), resourceItems);
                }
            }
            if (localContainer.size() > 0 && localContainer.containsKey(str)) {
                return localContainer.get(str);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private String getSkinId() {
        return "0";
    }

    private boolean isAllowRequest() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - JDSkinSharedPreferencesUtils.getLong(getContext(), ConstancesUtils.SP_CURRENT_SKIN_LAST_REQUEST_TIME, currentTimeMillis).longValue() > 600000) {
            JDSkinSharedPreferencesUtils.putLong(getContext(), ConstancesUtils.SP_CURRENT_SKIN_LAST_REQUEST_TIME, currentTimeMillis);
            return true;
        }
        return false;
    }

    public void clearLruCache() {
        LruCache<String, ResourceItems> lruCache = this.mLruCache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }

    public String getAppId() {
        return this.appCode;
    }

    public void getAsyncResByCode(final String str, final ResultCallback resultCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        final boolean isDarkMode = isDarkMode();
        LruCache<String, ResourceItems> lruCache = this.mLruCache;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(isDarkMode ? "Dark" : "");
        ResourceItems resourceItems = lruCache.get(sb.toString());
        if (resourceItems == null && resultCallback != null) {
            JDSkinDataController.getInstance().getDataBaseDealExecutorService().execute(new JDSelectCodeRunnable(SelectType.CODE, str, isDarkMode, new ResultCallback() { // from class: com.jd.skin.lib.JDSkinSDK.2
                {
                    JDSkinSDK.this = this;
                }

                @Override // com.jd.skin.lib.db.ResultCallback
                public void result(boolean z, ResourceItems resourceItems2) {
                    if (z && resourceItems2 != null) {
                        ResultCallback resultCallback2 = resultCallback;
                        if (resultCallback2 != null) {
                            resultCallback2.result(z, resourceItems2);
                            LruCache lruCache2 = JDSkinSDK.this.mLruCache;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str);
                            sb2.append(isDarkMode ? "Dark" : "");
                            lruCache2.put(sb2.toString(), resourceItems2);
                            return;
                        }
                        return;
                    }
                    ResultCallback resultCallback3 = resultCallback;
                    if (resultCallback3 != null) {
                        resultCallback3.result(false, null);
                    }
                }
            }));
        } else if (resultCallback != null) {
            resultCallback.result(true, resourceItems);
        }
    }

    public void getAsyncResByCodes(List<String> list, final ResultListCallback resultListCallback) {
        if (list == null) {
            return;
        }
        JDSkinDataController.getInstance().getDataBaseDealExecutorService().execute(new JDSelectCodesRunnable(SelectType.CODE, list, isDarkMode(), new ResultListCallback() { // from class: com.jd.skin.lib.JDSkinSDK.3
            {
                JDSkinSDK.this = this;
            }

            @Override // com.jd.skin.lib.db.ResultListCallback
            public void result(boolean z, Map<String, ResourceItems> map) {
                if (!z || map == null) {
                    return;
                }
                ResultListCallback resultListCallback2 = resultListCallback;
                if (resultListCallback2 != null) {
                    resultListCallback2.result(z, map);
                } else if (resultListCallback2 != null) {
                    resultListCallback2.result(false, null);
                }
            }
        }));
    }

    public String[] getBrandgradient() {
        return isDarkMode() ? this.brandgradient_01_dark : this.brandgradient_01;
    }

    public String getColor(String str) {
        return getBaseResData(str, 0, true);
    }

    public Context getContext() {
        return this.mContext;
    }

    public void getCustom(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        getData(str);
    }

    public String getFontSize(String str) {
        return getBaseResData(str, 1, true);
    }

    public boolean getIsNeedLogined() {
        return this.isNeedLogin;
    }

    public boolean getLoginState() {
        return this.isLogin;
    }

    public ResourceItems getResByCode(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            boolean isDarkMode = isDarkMode();
            LruCache<String, ResourceItems> lruCache = this.mLruCache;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = "Dark";
            sb.append(isDarkMode ? "Dark" : "");
            ResourceItems resourceItems = lruCache.get(sb.toString());
            if (resourceItems == null && (resourceItems = JDSkinDBController.queryDataByCode(str, isDarkMode)) != null) {
                LruCache<String, ResourceItems> lruCache2 = this.mLruCache;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                if (!isDarkMode) {
                    str2 = "";
                }
                sb2.append(str2);
                lruCache2.put(sb2.toString(), resourceItems);
            }
            return resourceItems;
        } catch (Exception unused) {
            return null;
        }
    }

    public void getResData(AppStateType appStateType) {
        if (this.mContext == null || this.appCode == null) {
            return;
        }
        if (AppStateType.APP_START == appStateType) {
            if (OKLog.D) {
                OKLog.d("JDSkinSDK", "JDSkinSDK--requestData-->" + appStateType);
            }
            JDSkinSharedPreferencesUtils.putLong(getContext(), ConstancesUtils.SP_CURRENT_SKIN_LAST_REQUEST_TIME, System.currentTimeMillis());
            getData(getSkinId());
        } else if ((AppStateType.APP_CHANGE_ACTIVITY == appStateType || AppStateType.APP_TO_BEFORE == appStateType) && isAllowRequest()) {
            if (OKLog.D) {
                OKLog.d("JDSkinSDK", "JDSkinSDK--requestData-->" + appStateType);
            }
            getData(getSkinId());
        }
    }

    public String getSkinUpdateTime() {
        return JDSkinSharedPreferencesUtils.getString(this.mContext, ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME, "0");
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public boolean isDafaultSkin() {
        return "0".equals(JDSkinSharedPreferencesUtils.getString(this.mContext, ConstancesUtils.SP_CURRENT_SKIN_ID, "0"));
    }

    public boolean isDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == DeepDarkChangeManager.MODE_DARK;
    }

    public void registerLoginAndExitReceiver(Context context) {
        this.loginAndExitReceiver = new LoginAndExitReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.in");
        intentFilter.addAction("com.jingdong.action.user.login.out");
        intentFilter.addAction("com.jingdong.action.user.switch");
        context.registerReceiver(this.loginAndExitReceiver, intentFilter);
    }

    public void removeNotifyUpdateListener(OnSkinElementsChangeListener onSkinElementsChangeListener) {
        JDSkinDataController.getInstance().removeSkinElementsChangeListener(onSkinElementsChangeListener);
    }

    public void reset() {
        clearLruCache();
        JDSkinDataController.getInstance().reset();
    }

    public JDSkinSDK setAppID(String str) {
        this.appCode = str;
        return mSkinSDK;
    }

    public void setData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        checkIsInitSdk();
        JDSkinDataController.getInstance().saveData(true, str);
    }

    public void setFontAndSize(String str, TextView textView) {
        try {
            if (TextUtils.isEmpty(str) || textView == null) {
                return;
            }
            boolean isDarkMode = isDarkMode();
            LruCache<String, ResourceItems> lruCache = this.mLruCache;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = "Dark";
            sb.append(isDarkMode ? "Dark" : "");
            ResourceItems resourceItems = lruCache.get(sb.toString());
            if (resourceItems == null) {
                resourceItems = JDSkinDBController.queryDataByCode(str, isDarkMode);
                if (resourceItems == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(isDarkMode ? "Dark" : "");
                    resourceItems = getResFromLocal(sb2.toString());
                }
                LruCache<String, ResourceItems> lruCache2 = this.mLruCache;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                if (!isDarkMode) {
                    str2 = "";
                }
                sb3.append(str2);
                lruCache2.put(sb3.toString(), resourceItems);
            }
            if (resourceItems != null) {
                if (str.contains("bold") || str.contains("Bold")) {
                    textView.setTypeface(Typeface.DEFAULT, 1);
                }
                if (!TextUtils.isEmpty(resourceItems.getFontSize())) {
                    textView.setTextSize(Integer.parseInt(resourceItems.getFontSize()));
                }
                String font = resourceItems.getFont();
                if (TextUtils.isEmpty(font)) {
                    return;
                }
                FontsUtil.changeTextFont(textView, Integer.parseInt(font));
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDSkinSDK", "JDSkinSDK--setFontAndSize-->" + e2.toString());
            }
        }
    }

    public JDSkinSDK setIsNeedLogined(boolean z) {
        this.isNeedLogin = z;
        return mSkinSDK;
    }

    public JDSkinSDK setLoginState(boolean z) {
        this.isLogin = z;
        return mSkinSDK;
    }

    public void setNotifyUpdateListener(OnSkinElementsChangeListener onSkinElementsChangeListener) {
        JDSkinDataController.getInstance().setOnSkinElementsChangeListener(onSkinElementsChangeListener);
    }

    public void unregisterLoginAndExitReceiver(Context context) {
        LoginAndExitReceiver loginAndExitReceiver = this.loginAndExitReceiver;
        if (loginAndExitReceiver != null) {
            context.unregisterReceiver(loginAndExitReceiver);
        }
    }

    public String getColor(String str, boolean z) {
        return getBaseResData(str, 0, z);
    }
}
