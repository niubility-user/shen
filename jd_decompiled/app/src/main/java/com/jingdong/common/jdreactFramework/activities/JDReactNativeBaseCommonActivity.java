package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.utils.JDReactEvent;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;

/* loaded from: classes5.dex */
public class JDReactNativeBaseCommonActivity extends JDReactNativeBaseActivity implements View.OnClickListener {
    private static final String EXTRA_MONITOR = "monitor";
    private static final String MODULENAME_DAOJIA = "JDReactDaoJia";
    private static final String TAG = "JDReactNativeCommonActivity";
    private static Point outSize;
    SimpleDraweeView llBtnBack;
    private int mPageHeight;
    private int mPageWidth;
    private boolean monitor;
    private String reactBundle;
    private boolean reactIsHidden;
    private String reactModule;
    private String reactParams;
    LinearLayout reactRootViewHolder;
    private String reactTitle;
    RelativeLayout reactparent;
    RelativeLayout rlTop;
    TextView titleText;
    private boolean transparentEnable;
    private boolean init = false;
    private int orientation = 1;

    public static int getAppHeight(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                return point2.y;
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        if (outSize == null) {
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(activity);
                }
            }
        }
        return outSize.y;
    }

    public static int getAppWidth(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                return point2.x;
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        if (outSize == null) {
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(activity);
                }
            }
        }
        return outSize.x;
    }

    public static Display getDefaultDisplay(Context context) {
        return ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
    }

    public static void getPxSize(Context context) {
        Display defaultDisplay = getDefaultDisplay(context);
        Point point2 = new Point();
        outSize = point2;
        defaultDisplay.getSize(point2);
    }

    private static boolean isGalaxyFold() {
        return "SM-F9000".equals(BaseInfo.getDeviceModel());
    }

    private boolean isHuawei() {
        return "HUAWEI".equalsIgnoreCase(BaseInfo.getDeviceManufacture()) || "HONOR".equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public void clearImageMemory() {
        Fresco.clearMemoryCache();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public Fragment createMFragement(String str) {
        Fragment createMFragment;
        if (isFinishing() || (createMFragment = JDReactAuraHelper.getInstance().createMFragment()) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        createMFragment.setArguments(bundle);
        return createMFragment;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public void enablePV(boolean z) {
        setUseBasePV(z);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public String getRNTitle() {
        return this.reactTitle;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactModuleEntity getReactEntity() {
        Bundle extras;
        Intent intent = getIntent();
        Bundle bundle = null;
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.reactIsHidden = extras.getBoolean("ishidden", true);
            Bundle bundle2 = null;
            for (String str : extras.keySet()) {
                if ("title".equals(str)) {
                    this.reactTitle = extras.getString("title");
                } else if (JDReactConstant.IntentConstant.MODULE_NAME.equals(str)) {
                    this.reactModule = extras.getString(JDReactConstant.IntentConstant.MODULE_NAME);
                } else if ("appname".equals(str)) {
                    this.reactBundle = extras.getString("appname");
                } else if ("ishidden".equals(str)) {
                    this.reactIsHidden = extras.getBoolean("ishidden", true);
                } else if ("param".equals(str)) {
                    String string = extras.getString("param", null);
                    this.reactParams = string;
                    if (!TextUtils.isEmpty(string)) {
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        try {
                            JDJSONObject parseObject = JDJSON.parseObject(this.reactParams);
                            for (String str2 : parseObject.keySet()) {
                                Object obj = parseObject.get(str2);
                                if (str2 != null && str2.equals("transparentenable")) {
                                    if (obj instanceof Boolean) {
                                        this.transparentEnable = ((Boolean) obj).booleanValue();
                                    } else if (obj instanceof String) {
                                        String str3 = (String) obj;
                                        if (!TextUtils.isEmpty(str3) && DYConstants.DY_TRUE.equals(str3)) {
                                            this.transparentEnable = true;
                                        }
                                    }
                                    bundle2.putBoolean(str2, this.transparentEnable);
                                } else if (obj != null) {
                                    if (obj instanceof Boolean) {
                                        bundle2.putBoolean(str2, ((Boolean) obj).booleanValue());
                                    } else if (obj instanceof String) {
                                        bundle2.putString(str2, String.valueOf(obj));
                                    } else if (obj instanceof Byte) {
                                        bundle2.putByte(str2, ((Byte) obj).byteValue());
                                    } else if (obj instanceof Float) {
                                        bundle2.putFloat(str2, ((Float) obj).floatValue());
                                    } else if (obj instanceof Integer) {
                                        bundle2.putInt(str2, ((Integer) obj).intValue());
                                    } else if (obj instanceof Short) {
                                        bundle2.putShort(str2, ((Short) obj).shortValue());
                                    } else if (obj instanceof Double) {
                                        bundle2.putDouble(str2, ((Double) obj).doubleValue());
                                    } else if (obj instanceof Long) {
                                        bundle2.putLong(str2, ((Long) obj).longValue());
                                    } else {
                                        bundle2.putString(str2, obj.toString());
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            OKLog.e(TAG, e2);
                        }
                    }
                } else if (!TextUtils.isEmpty(str) && !"pluginName".equals(str) && !"params".equals(str) && !"pluginPath".equals(str) && !"jumpFrom".equals(str) && !"version".equals(str) && !LoginConstans.JUMP_DES.equals(str)) {
                    try {
                        Object obj2 = extras.get(str);
                        if (obj2 != null) {
                            if (bundle2 == null) {
                                bundle2 = new Bundle();
                            }
                            if (str != null && str.equals("transparentenable")) {
                                if (obj2 instanceof Boolean) {
                                    this.transparentEnable = ((Boolean) obj2).booleanValue();
                                } else if (obj2 instanceof String) {
                                    String str4 = (String) obj2;
                                    if (!TextUtils.isEmpty(str4) && DYConstants.DY_TRUE.equals(str4)) {
                                        this.transparentEnable = true;
                                    }
                                }
                                bundle2.putBoolean(str, this.transparentEnable);
                            } else if (obj2 instanceof Boolean) {
                                bundle2.putBoolean(str, ((Boolean) obj2).booleanValue());
                            } else if (obj2 instanceof String) {
                                bundle2.putString(str, String.valueOf(obj2));
                            } else if (obj2 instanceof Byte) {
                                bundle2.putByte(str, ((Byte) obj2).byteValue());
                            } else if (obj2 instanceof Float) {
                                bundle2.putFloat(str, ((Float) obj2).floatValue());
                            } else if (obj2 instanceof Integer) {
                                bundle2.putInt(str, ((Integer) obj2).intValue());
                            } else if (obj2 instanceof Short) {
                                bundle2.putShort(str, ((Short) obj2).shortValue());
                            } else if (obj2 instanceof Double) {
                                bundle2.putDouble(str, ((Double) obj2).doubleValue());
                            } else if (obj2 instanceof Long) {
                                bundle2.putLong(str, ((Long) obj2).longValue());
                            } else {
                                bundle2.putString(str, obj2.toString());
                            }
                        }
                    } catch (Exception e3) {
                        OKLog.e(TAG, e3);
                    }
                }
            }
            bundle = bundle2;
        }
        String str5 = this.reactModule;
        if (str5 != null && str5.equals("JDReactCollectJDBeans")) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("sdk_version", Build.VERSION.SDK_INT);
        }
        JDReactModuleEntity jDReactModuleEntity = new JDReactModuleEntity(this.reactBundle, this.reactModule, bundle);
        jDReactModuleEntity.setTransparentEnable(this.transparentEnable);
        return jDReactModuleEntity;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public int getRootViewHolderId() {
        return 0;
    }

    public int getStatusBarHeight() {
        Resources resources = getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        int dimension = identifier > 0 ? (int) resources.getDimension(identifier) : 0;
        JLog.e(TAG, dimension + "");
        return dimension;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean isDebug() {
        return CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean isHiden() {
        return this.reactIsHidden;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean launchActivityWithOpenapp(String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        startActivity(intent);
        finish();
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean launchMpage(String str) {
        CommonBridge.goToMWithUrlNotInFrame(this, str);
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.llBtnBack) {
            finish();
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        int i2;
        super.onConfigurationChanged(configuration);
        int statusBarHeight = getStatusBarHeight();
        if (!isTransparentenable()) {
            JLog.d(ReactConstants.TAG_DISPLAY_METRICS, "0");
            i2 = 0;
        } else {
            JLog.d(ReactConstants.TAG_DISPLAY_METRICS, statusBarHeight + "");
            i2 = statusBarHeight;
        }
        int appHeight = getAppHeight(this);
        int appWidth = getAppWidth(this);
        int i3 = configuration.orientation;
        if (i3 == 0) {
            i3 = ((isGalaxyFold() || isHuawei()) && appHeight < appWidth) ? 2 : 1;
        }
        JLog.d(ReactConstants.TAG_DISPLAY_METRICS, "newOrientation =" + i3 + " orientation = " + this.orientation);
        JLog.d(ReactConstants.TAG_DISPLAY_METRICS, "added mPageWidth =" + this.mPageWidth + " mPageHeight:" + this.mPageHeight + " width:" + appWidth + " height:" + appHeight);
        if (i3 == this.orientation && (Math.abs(appHeight - this.mPageWidth) >= 130 || Math.abs(appWidth - this.mPageHeight) >= 130)) {
            int abs = Math.abs(appWidth - this.mPageWidth);
            if (appHeight == this.mPageHeight && appWidth == this.mPageWidth) {
                return;
            }
            final WritableMap createMap = Arguments.createMap();
            createMap.putInt("screenWidth", appWidth);
            if ((abs == 696 || isGalaxyFold()) && appWidth - this.mPageWidth > 0) {
                statusBarHeight = 0;
            }
            JLog.d(ReactConstants.TAG_DISPLAY_METRICS, "resize postDelay");
            createMap.putInt("screenHeight", (i2 + appHeight) - statusBarHeight);
            createMap.putBoolean("force", isForceRefresh());
            post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    JLog.d(ReactConstants.TAG_DISPLAY_METRICS, "resize now");
                    JDReactNativeBaseCommonActivity.this.sendEvent("screenSizeChanged", createMap);
                }
            }, 200);
            this.mPageHeight = appHeight;
            this.mPageWidth = appWidth;
            return;
        }
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("screenOrientation", i3);
        sendEvent("screenRotate", createMap2);
        this.orientation = i3;
        this.mPageWidth = appWidth;
        this.mPageHeight = appHeight;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPageHeight = getAppHeight(this);
        this.mPageWidth = getAppWidth(this);
        JLog.e("kris", "init mPageWidth =" + this.mPageWidth + " mPageHeight:" + this.mPageHeight);
        this.monitor = getIntent().getBooleanExtra(EXTRA_MONITOR, true);
        if (TextUtils.equals(this.reactModule, MODULENAME_DAOJIA)) {
            this.monitor = true;
        }
        if (this.monitor) {
            EventBus.getDefault().register(this);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RelativeLayout relativeLayout = this.reactparent;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
        }
        if (this.monitor) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        Bundle bundle;
        if (baseEvent == null || (bundle = baseEvent.getBundle()) == null) {
            return;
        }
        try {
            if (JDReactEvent.TYPE_JDREACT_EVENT.equals(baseEvent.getType())) {
                String string = bundle.getString("name");
                String string2 = bundle.getString("moduleName");
                String string3 = bundle.getString("url");
                String string4 = bundle.getString("params");
                if (TextUtils.equals(getReactEntity().getmModuleName(), string2) && !TextUtils.isEmpty(string)) {
                    WritableMap createMap = Arguments.createMap();
                    if (!TextUtils.isEmpty(string3)) {
                        createMap.putString("url", string3);
                    }
                    if (!TextUtils.isEmpty(string4)) {
                        createMap.putString("params", string4);
                    }
                    if (!TextUtils.isEmpty(string2)) {
                        createMap.putString("moduleName", string2);
                    }
                    sendEvent(string, createMap);
                }
            } else if (TextUtils.equals(JDReactEvent.TYPE_JDREACT_EVENT_NEW, baseEvent.getType())) {
                String string5 = bundle.getString("eventName");
                String string6 = bundle.getString("moduleName");
                String string7 = bundle.getString("params");
                if ((TextUtils.isEmpty(string6) || TextUtils.equals(getReactEntity().getmModuleName(), string6)) && !TextUtils.isEmpty(string5)) {
                    WritableMap createMap2 = Arguments.createMap();
                    if (!TextUtils.isEmpty(string7)) {
                        createMap2.putString("params", string7);
                    }
                    if (!TextUtils.isEmpty(string6)) {
                        createMap2.putString("moduleName", string6);
                    }
                    sendEvent(string5, createMap2);
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 24 && i2 != 18) {
                OKLog.d("JDReact", "not N");
            } else {
                OKLog.d("JDReact", "in N");
                if (bundle != null) {
                    bundle.clear();
                }
            }
        } catch (Exception unused) {
        }
    }

    public void setbundleParam(String str, String str2, boolean z) {
        this.reactBundle = str;
        this.reactModule = str2;
        this.reactIsHidden = z;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean showLoading() {
        return true;
    }
}
