package com.jingdong.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkHelper;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.db.AppStateType;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.home.ChannelInfo;
import com.jingdong.app.mall.mocker.MockerUtils;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.frame.ILogoutListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.IPauseListener;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.frame.Record;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.kepler.DragView;
import com.jingdong.common.kepler.KeplerJumpUtils;
import com.jingdong.common.kepler.ThirdAppSkuInfo;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.paidTraffic.PaidTrafficHelper;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.unification.statusbar.AndroidWorkaround;
import com.jingdong.common.unification.statusbar.IStatusController;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;
import com.jingdong.common.unification.uniutil.UnAndroidUtils;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.CacheTimeUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CompatOThemeUtils;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.common.utils.NightModeUtil;
import com.jingdong.common.utils.base.BaseArchUtil;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.jdsdk.JDSoftReference;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.e.a;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.lib.monitor.MonitorInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
/* loaded from: classes5.dex */
public class BaseActivity extends FragmentActivity implements IMyActivity, IStatusController {
    public static final String DISPOSEABLE_UNABLE_ANIM = "disposableUdnableAnim";
    public static final String ISFROMNF = "isFromNF";
    public static final int OVERLAY_PERMISSION_REQUEST_CODE = 169;
    private static AlertDialog.Builder hintDialogBuilder;
    public static final String[] mIgnoreModels = {"MI-ONE Plus"};
    private LocalActivityManager activityManager;
    private PermissionHelper.AlertWindowRequestCallBack alertWindowRequestCallBack;
    private AssetManager assetManager;
    private ChannelInfo channelInfo;
    private int counter;
    private DragView dragView;
    protected boolean finishAfterSuperOnCreate;
    private FrameLayout imageViewLayout;
    public boolean isDisposeableUnableExitAnim;
    private boolean isPrevNotInRecord;
    private boolean jump;
    private RelativeLayout layout;
    public IBackKeyTriggerListener mBackKeyTriggerListener;
    public IConfigurationChangedListener mConfigurationChangedListener;
    protected HttpGroupWithNPS mHttpGroupWithNPS;
    private ImageView mTitleBack;
    private Thread mUiThread;
    private View model;
    private boolean needResetResourceConfig;
    private FrameLayout noNetTip;
    private Resources resources;
    private ViewGroup rootFrameLayout;
    private ViewGroup rootView;
    private SharedPreferences sharedPreferences;
    private KeplerJumpUtils.ShowOrHideCallback showOrHideCallback;
    private JDSoftReference<Activity> softReference;
    public boolean statusBarDarkModeEnable;
    private long stopTime;
    private ViewGroup subRootView;
    private Resources.Theme theme;
    private WatermarkHelper watermarkHelper;
    protected int yDistance;
    private final String TAG = BaseActivity.class.getSimpleName();
    private JDHandler handler = new JDHandler();
    private ArrayList<IDestroyListener> destroyListenerList = new ArrayList<>();
    private ArrayList<IPauseListener> pauseListenerList = new ArrayList<>();
    private ArrayList<IResumeListener> resumeListenerList = new ArrayList<>();
    protected ArrayList<ILogoutListener> logoutListenerList = new ArrayList<>();
    protected ArrayList<Record> recordList = new ArrayList<>();
    private ArrayList<Record> singleInstanceRecordList = new ArrayList<>();
    private HashMap<String, Integer> recordIdAndRadioId = new HashMap<>();
    private boolean isCanResend = true;
    protected boolean isUseBasePV = true;
    protected boolean needSetOrientation = true;
    protected boolean isSpecial = false;
    protected boolean isOnNetwork = true;
    protected int type = 1;
    public boolean needCheckNet = true;
    protected boolean isFromNF = false;
    protected String page_id = "";
    protected String shop_id = "";
    private boolean touchFlag = true;
    public boolean canAddWatermark = true;
    public boolean activityManagedByNumController = true;
    public boolean manualResume = false;
    public boolean statusBarTransparentEnable = false;
    public boolean statusBarTintEnable = true;
    public boolean isNavigationBarShade = false;
    protected boolean isNeedKeplerFlowDragView = true;
    private int guideResourceId = 0;
    private Intent mData = new Intent();

    /* loaded from: classes5.dex */
    public interface IBackKeyTriggerListener {
        void backKeyTrigger();
    }

    /* loaded from: classes5.dex */
    public interface IConfigurationChangedListener {
        void onConfigurationChanged(Configuration configuration);
    }

    private static void catchToastTip(final Activity activity, Intent intent) {
        boolean equals = TextUtils.equals(intent.getAction(), "android.intent.action.VIEW");
        final String str = StringUtil.not_find_other;
        if (equals) {
            String scheme = intent.getScheme();
            if (scheme != null && (scheme.equals("http") || scheme.equals("https"))) {
                str = StringUtil.not_find_browser;
            }
        } else if (TextUtils.equals(intent.getAction(), "android.intent.action.GET_CONTENT")) {
            str = StringUtil.not_find_gallery;
        } else if (TextUtils.equals(intent.getAction(), "android.media.action.IMAGE_CAPTURE")) {
            str = StringUtil.not_find_camera;
        }
        BaseActivity baseActivity = (BaseActivity) BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (baseActivity != null) {
            baseActivity.post(new Runnable() { // from class: com.jingdong.common.BaseActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    ToastUtils.shortToast(activity, str);
                }
            });
        }
    }

    private void clearHistoryRecord(Intent intent) {
        Record record = new Record();
        record.setIntent(intent);
        while (this.recordList.remove(record)) {
            if (OKLog.D) {
                OKLog.d(this.TAG, "clearHistoryRecord() r -->> " + record);
            }
        }
    }

    private void doJump() {
        this.jump = false;
        removeRecordTop();
    }

    private Integer findRadioId(String str) {
        return this.recordIdAndRadioId.get(str);
    }

    private Record findsingleInstanceRecord(Intent intent) {
        Iterator<Record> it = this.singleInstanceRecordList.iterator();
        while (it.hasNext()) {
            Record next = it.next();
            if (next.getIntent().getComponent().getClassName().equals(intent.getComponent().getClassName())) {
                return next;
            }
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private View getModel() {
        int dip2px = DPIUtil.dip2px(59.0f);
        if (this.isSpecial) {
            dip2px = this.yDistance;
        }
        int statusHeight = dip2px + getStatusHeight();
        if (this.model == null) {
            try {
                this.model = ImageUtil.inflate(R.layout.app_network_model, null);
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, th);
                }
            }
        }
        View view = this.model;
        if (view == null) {
            return null;
        }
        if (this.layout == null) {
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.app_network_model_layout);
            this.layout = relativeLayout;
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.BaseActivity.2
                {
                    BaseActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Intent intent = new Intent("android.settings.SETTINGS");
                    if (BaseActivity.this.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                        BaseActivity.this.startActivity(intent);
                    } else {
                        ToastUtils.shortToast(BaseActivity.this.getThisActivity(), "\u65e0\u6cd5\u8fdb\u5165\u624b\u673a\u7f51\u7edc\u8bbe\u7f6e");
                    }
                }
            });
        }
        if (this.noNetTip == null) {
            FrameLayout frameLayout = (FrameLayout) this.model.findViewById(R.id.un_base_no_net);
            this.noNetTip = frameLayout;
            frameLayout.setPadding(frameLayout.getPaddingLeft(), statusHeight, this.noNetTip.getPaddingRight(), this.noNetTip.getPaddingBottom());
        }
        return this.model;
    }

    private Integer getNavigationId(Intent intent) {
        if (isNavigation(intent)) {
            return Integer.valueOf(intent.getIntExtra("com.360buy:navigationId", 0));
        }
        return null;
    }

    private HashMap<String, Object> getTaskId(Intent intent) {
        return (HashMap) intent.getSerializableExtra("com.360buy:taskIdFlag");
    }

    private void handleKeplerInterveneReturn() {
        ThirdAppSkuInfo thirdAppSkuInfo = ThirdAppSkuInfo.getInstance();
        String skuId = thirdAppSkuInfo.getSkuId();
        if (OKLog.D) {
            OKLog.d(this.TAG, "skuId:" + skuId);
        }
        if (thirdAppSkuInfo.getSkuIdsSize() == 1 && !TextUtils.isEmpty(skuId) && isKeplerBackMiniProductControl() && !KeplerJumpUtils.isOpeningMiniProductPage) {
            openMiniDetailPage(skuId);
            return;
        }
        KeplerJumpUtils.keplerID = "";
        openHomePage();
    }

    private void handleOnResume() {
        ArrayList<IResumeListener> arrayList = this.resumeListenerList;
        if (arrayList == null) {
            return;
        }
        try {
            this.isCanResend = true;
            int size = arrayList.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int size2 = this.resumeListenerList.size();
                this.resumeListenerList.get(i2).onResume();
                if (size2 == this.resumeListenerList.size()) {
                    i2++;
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, e2);
            }
        }
        checkNetwork(this.type);
        NightModeUtil.setNightModeAlpha(this);
        if (this.isUseBasePV) {
            JDMtaUtils.sendPagePv(this, this, getPageParam(), this.page_id, this.shop_id);
            if (OKLog.D) {
                OKLog.d(this.TAG, "page_id==" + this.page_id);
                OKLog.d(this.TAG, "page_id==" + this.shop_id);
                OKLog.d(this.TAG, "getPageParam()==" + getPageParam());
            }
        }
    }

    private boolean hasActiveFragment(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            return false;
        }
        try {
            for (Fragment fragment : fragmentManager.getFragments()) {
                Iterator<Fragment> it = fragment.getChildFragmentManager().getFragments().iterator();
                while (it.hasNext()) {
                    if (hasActiveFragment(it.next().getFragmentManager())) {
                        return true;
                    }
                }
                if ((fragment instanceof BaseFragment) && fragment.isResumed() && !fragment.isHidden() && fragment.getUserVisibleHint() && ((BaseFragment) fragment).removeXview2()) {
                    OKLog.d(this.TAG, "getActiveFragment" + getClass().getName());
                    return true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    private void initHardAcclCheck() {
        Object obj;
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
            String deviceModel = BaseInfo.getDeviceModel();
            boolean z = false;
            for (String str : mIgnoreModels) {
                if (str.equalsIgnoreCase(deviceModel)) {
                    return;
                }
            }
            try {
                Bundle bundle = getPackageManager().getActivityInfo(getComponentName(), 128).metaData;
                if (bundle != null && (obj = bundle.get("hardwareAccelerated")) != null) {
                    if (!((Boolean) obj).booleanValue()) {
                        z = true;
                    }
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, e2);
                }
            }
            if (z || !shouldOpenHardwareAccelerate()) {
                return;
            }
            openHardAccl();
        }
    }

    private boolean isJump() {
        return this.jump;
    }

    private boolean isKeplerBackColdControl() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "keplerbackColdConfig", "keplerbackColdConfig", "0"), "1");
    }

    private boolean isKeplerBackControl() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "keplerConfig", "keplerbackControl", "0"), "1");
    }

    private boolean isKeplerBackLandControl() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "keplerbackColdConfig", "keplerbackLandConfig", "0"), "1");
    }

    private boolean isKeplerBackMiniProductControl() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "keplerbackConfig", "keplerbackConfig", "0"), "1");
    }

    private boolean isNavigation(Intent intent) {
        return intent.getBooleanExtra("com.360buy:navigationFlag", false);
    }

    private boolean isStatusBarHintTagDefault() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "baseConfig", "isStatusBarTagDefault");
        if (OKLog.D) {
            OKLog.d(this.TAG, "isStatusBarTagDefault:" + config);
        }
        return TextUtils.isEmpty(config) || TextUtils.equals(config, "1");
    }

    private void openHardAccl() {
        try {
            getWindow().setFlags(16777216, 16777216);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, e2);
            }
        }
    }

    private void openHomePage() {
        PaidTrafficHelper.unionBackMta(this, "99");
        passDataToHome();
        ThirdAppSkuInfo.getInstance().clearSkuId();
        KeplerJumpUtils.isOpeningMiniProductPage = false;
        try {
            DeepLinkCommonHelper.startActivity(this, DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, null, true, 67108864, false, "");
        } catch (Throwable th) {
            if (OKLog.E) {
                th.printStackTrace();
            }
        }
    }

    private void openMiniDetailPage(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        KeplerJumpUtils.isOpeningMiniProductPage = true;
        PaidTrafficHelper.unionBackMta(this, "0");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            String mParam = AdvertUtils.getMParam();
            String str2 = "";
            if (!TextUtils.isEmpty(mParam)) {
                str2 = new JSONObject(mParam).optString("jdv");
                if (OKLog.D) {
                    OKLog.d(this.TAG, "jdv : " + str2);
                }
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("sourceExt", 50);
            jSONObject.put("seedSource", "0");
            jSONObject.put("skus", jSONArray);
            jSONObject.put("jdv", str2);
            jSONObject.put("channel", "3");
            jSONObject2.put("param", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        new OpenAppJumpBuilder.Builder().scheme("openapp.jdmobile").host(OpenAppConstant.HOST_1).category("jump").des("JDFlutterView").map("dragBackEnable", "0").map("flutterRouterName", "JDFlutterMiniProduct").map("isFromFlutterVCEnable", "1").map("isShowNativeNavBar", "0").params(jSONObject2.toString()).build().jump(this);
        finish();
    }

    private void passDataToHome() {
        String str;
        if (this.channelInfo == null) {
            JSONObject jSONObject = new JSONObject();
            String str2 = "";
            try {
                String sk = AdvertUtils.getSk();
                if (!TextUtils.isEmpty(sk)) {
                    str2 = new JSONObject(sk).optString("source");
                }
            } catch (Exception unused) {
            }
            try {
                jSONObject.put("type", WebEntity.VALUE_ONEKEYLOGIN_KEPLER);
                jSONObject.put("subType", ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID);
                String skuId = ThirdAppSkuInfo.getInstance().getSkuId();
                boolean isEmpty = TextUtils.isEmpty(skuId);
                if (isEmpty) {
                    str = DYConstants.DY_NULL_STR;
                } else {
                    str = KeplerJumpUtils.isOpeningMiniProductPage ? "minidetail" : "detail";
                }
                if (isEmpty) {
                    skuId = DYConstants.DY_NULL_STR;
                }
                jSONObject.put("skuId", skuId);
                jSONObject.put("pay", "charge");
                if (TextUtils.isEmpty(str2)) {
                    str2 = DYConstants.DY_NULL_STR;
                }
                jSONObject.put("channel", str2);
                jSONObject.put("source", str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.channelInfo = new ChannelInfo(1, jSONObject);
        }
        ChannelInfo.saveChannelInfo(this.channelInfo);
    }

    private void setStatusBarTint() {
        UnStatusBarTintUtil.isBrowseMode = !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
        if (isStatusBarTintEnable()) {
            boolean z = false;
            if (statusBarTransparentEnable()) {
                if (this.statusBarDarkModeEnable && isAppDarkMode()) {
                    z = true;
                }
                UnStatusBarTintUtil.setStatusBar4Base(this, 1, z);
            } else {
                UnStatusBarTintUtil.setStatusBar4Base(this, statusBarHint(), this.statusBarDarkModeEnable && isAppDarkMode());
            }
            AndroidWorkaround.assistActivity(findViewById(16908290), this);
        }
    }

    private boolean shouldOpenHardwareAccelerate() {
        return ABTestUtils.useHardwareAccelerate();
    }

    private void startActivityForResultNoExceptionForFragment(Fragment fragment, Intent intent, int i2) {
        if (intent == null || fragment == null) {
            return;
        }
        try {
            fragment.startActivityForResult(intent, i2);
        } catch (ActivityNotFoundException e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, "startActivityForResultNoException", e2);
            }
            catchToastTip(this, intent);
        } catch (Exception e3) {
            if (OKLog.E) {
                OKLog.e(this.TAG, "startActivityForResultNoException", e3);
            }
        }
    }

    private void unionOpenHomePage() {
        KeplerJumpUtils.keplerID = "";
        if (isKeplerBackControl()) {
            openHomePage();
        }
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void addDestroyListener(IDestroyListener iDestroyListener) {
        synchronized (this) {
            ArrayList<IDestroyListener> arrayList = this.destroyListenerList;
            if (arrayList != null) {
                arrayList.add(iDestroyListener);
            }
        }
    }

    protected void addGuideImage(ViewGroup viewGroup) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "addGuideImage -->> ");
        }
        this.rootView = viewGroup;
        if (OKLog.D) {
            OKLog.d(this.TAG, "view -->> " + this.rootView);
        }
        if (this.rootView == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(this.TAG, "guideResourceId -->> " + this.guideResourceId);
        }
        if (this.guideResourceId != 0) {
            this.imageViewLayout = new FrameLayout(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 48;
            layoutParams.height = DPIUtil.getHeight();
            layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.guide_image_margin);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
            try {
                simpleDraweeView.setImageResource(this.guideResourceId);
                this.imageViewLayout.addView(simpleDraweeView, layoutParams);
                this.imageViewLayout.setBackgroundColor(getResources().getColor(R.color.slide_prompt_bg));
                this.imageViewLayout.getBackground().setAlpha(200);
                CommonBase.setIsGuided(getClass().getName());
                this.imageViewLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.BaseActivity.5
                    {
                        BaseActivity.this = this;
                    }

                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 1) {
                            BaseActivity.this.removeGuideView();
                        }
                        return true;
                    }
                });
                this.rootView.addView(this.imageViewLayout, new ViewGroup.LayoutParams(-1, -1));
                this.rootView.invalidate();
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, th);
                }
            }
        }
    }

    @Deprecated
    public void addHttpGroupWithNPSSetting(HttpSetting httpSetting) {
        getHttpGroupWithNPSGroup().add(httpSetting);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void addLogoutListener(ILogoutListener iLogoutListener) {
        synchronized (this) {
            ArrayList<ILogoutListener> arrayList = this.logoutListenerList;
            if (arrayList != null) {
                arrayList.add(iLogoutListener);
            }
        }
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void addPauseListener(IPauseListener iPauseListener) {
        synchronized (this) {
            ArrayList<IPauseListener> arrayList = this.pauseListenerList;
            if (arrayList != null) {
                arrayList.add(iPauseListener);
            }
        }
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void addResumeListener(IResumeListener iResumeListener) {
        synchronized (this) {
            ArrayList<IResumeListener> arrayList = this.resumeListenerList;
            if (arrayList != null) {
                arrayList.add(iResumeListener);
            }
        }
    }

    public synchronized void alert(int i2) {
        if (hintDialogBuilder == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            hintDialogBuilder = builder;
            builder.setTitle("\u63d0\u793a");
            hintDialogBuilder.setMessage(i2);
            hintDialogBuilder.setPositiveButton("\u786e\u5b9a", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.BaseActivity.7
                {
                    BaseActivity.this = this;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    dialogInterface.dismiss();
                }
            });
        }
        hintDialogBuilder.show();
    }

    public void attemptRunOnUiThread(Runnable runnable) {
        if (Thread.currentThread() != getUiThread()) {
            post(runnable);
        } else {
            runnable.run();
        }
    }

    public void canNotResend() {
        this.isCanResend = false;
    }

    public void checkNetwork() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "checkNetwork() isOnNetwork = " + this.isOnNetwork);
        }
        if (this.isOnNetwork) {
            if (NetUtils.isNetworkAvailable()) {
                if (OKLog.D) {
                    OKLog.d(this.TAG, "checkNetwork() hide model");
                }
                hideModel();
                return;
            }
            if (OKLog.D) {
                OKLog.d(this.TAG, "checkNetwork() show model");
            }
            showModel();
        }
    }

    public void clearJump() {
        if (isJump()) {
            this.jump = false;
        }
    }

    public HashMap<String, Object> createTaskId(Intent intent) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("className", intent.getComponent().getClassName());
        return hashMap;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        JMA.handleTouchEvent(this, motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        hideSoftInput();
        super.finish();
        if (this.isDisposeableUnableExitAnim) {
            this.isDisposeableUnableExitAnim = false;
            if (OKLog.D) {
                OKLog.d(this.TAG, "disposeable no exit anim");
            }
            int i2 = R.anim.nothing;
            overridePendingTransition(i2, i2);
        } else if (ABTestUtils.useUniActivityAnim()) {
            overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
        }
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        if (a.a()) {
            AssetManager assetManager = this.assetManager;
            return assetManager == null ? super.getAssets() : assetManager;
        }
        return super.getAssets();
    }

    public boolean getBooleanFromPreference(String str) {
        return this.sharedPreferences.getBoolean(str, false);
    }

    public BaseActivity getCurrentMyActivity() {
        return (BaseActivity) BaseFrameUtil.getInstance().getCurrentMyActivity();
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public Handler getHandler() {
        return this.handler;
    }

    @Deprecated
    public HttpGroup getHttpGroupWithNPSGroup() {
        HttpGroup httpGroup;
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null && (httpGroup = httpGroupWithNPS.getHttpGroup()) != null) {
            if (httpGroup.getHttpGroupSetting() != null) {
                httpGroup.getHttpGroupSetting().setMyActivity(this);
            }
            return httpGroup;
        }
        return getHttpGroupaAsynPool();
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public HttpGroup getHttpGroupaAsynPool() {
        return getHttpGroupaAsynPool(1000);
    }

    public Intent getJDData() {
        return this.mData;
    }

    public boolean getManualResume() {
        return this.manualResume;
    }

    public String getPageParam() {
        return "";
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public synchronized Resources getResources() {
        Resources resources;
        if (a.a()) {
            resources = this.resources;
            if (resources == null) {
                resources = super.getResources();
            }
        } else {
            resources = super.getResources();
        }
        if (this.needResetResourceConfig) {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            this.needResetResourceConfig = false;
        }
        return resources;
    }

    public ViewGroup getRootFrameLayout() {
        ViewGroup viewGroup = this.rootFrameLayout;
        if (viewGroup != null) {
            return viewGroup;
        }
        ViewGroup viewGroup2 = (ViewGroup) getWindow().peekDecorView();
        this.rootFrameLayout = viewGroup2;
        if (viewGroup2 == null) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
            }
            this.rootFrameLayout = getRootFrameLayout();
        }
        return this.rootFrameLayout;
    }

    public Map<String, String> getScreenShotPageParams() {
        return null;
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public String getServerConfigValue() {
        return ABTestUtils.useStatusBarTint() ? "1" : "0";
    }

    public int getStatusHeight() {
        int identifier;
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i2 = rect.top;
        if (i2 == 0) {
            try {
                Class<?> cls = Class.forName("com.android.internal.R$dimen");
                i2 = getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchFieldException e2) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, e2);
                }
            }
        }
        return (i2 != 0 || (identifier = getResources().getIdentifier("status_bar_height", "dimen", "android")) <= 0) ? i2 : getResources().getDimensionPixelSize(identifier);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public String getStringFromPreference(String str) {
        return this.sharedPreferences.getString(str, "");
    }

    public ViewGroup getSubRootView() {
        return this.subRootView;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        if (a.a()) {
            Resources.Theme theme = this.theme;
            return theme != null ? theme : super.getTheme();
        }
        return super.getTheme();
    }

    public Activity getThisActivity() {
        return this;
    }

    public Thread getUiThread() {
        return this.mUiThread;
    }

    public boolean handleInterveneReturn() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "handleInterveneReturn  isKeplerBackHome : " + isKeplerBackControl());
            OKLog.d(this.TAG, "handleInterveneReturn  isKeplerBackMini : " + isKeplerBackMiniProductControl());
        }
        if (TextUtils.equals("com.jd.lib.cashier.pay.view.CashierPayActivity", getClass().getCanonicalName())) {
            return false;
        }
        int numActivitiesInStack = ActivityManagerUtil.getScreenManager().getNumActivitiesInStack();
        if (OKLog.D) {
            OKLog.d(this.TAG, "handleInterveneReturn  numActivities : " + numActivitiesInStack);
            OKLog.d(this.TAG, "handleInterveneReturn  isKeplerBackColdControl : " + isKeplerBackColdControl());
        }
        if (!isKeplerBackColdControl() ? numActivitiesInStack != 1 : !(numActivitiesInStack == 1 && BaseFrameUtil.getInstance().getMainFrameActivity() == null)) {
            ThirdAppSkuInfo thirdAppSkuInfo = ThirdAppSkuInfo.getInstance();
            String skuId = thirdAppSkuInfo.getSkuId();
            if (OKLog.D) {
                OKLog.d(this.TAG, "handleInterveneReturn skuId:" + skuId);
            }
            String str = PaidTrafficHelper.skuid;
            if (((thirdAppSkuInfo.getSkuIdsSize() == 1 && !TextUtils.isEmpty(skuId)) || !TextUtils.isEmpty(str)) && isKeplerBackMiniProductControl() && !KeplerJumpUtils.isOpeningMiniProductPage) {
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(PaidTrafficHelper.adBackUrl4Activity)) {
                        if (PaidTrafficHelper.adBackUrl4Activity.toLowerCase().startsWith("http")) {
                            PaidTrafficHelper.unionBackMta(this, "8");
                            PaidTrafficHelper.toH5ByDeepLink(this, PaidTrafficHelper.adBackUrl4Activity);
                            finish();
                        }
                    } else {
                        int i2 = PaidTrafficHelper.targetPage;
                        if (i2 == 2) {
                            PaidTrafficHelper.unionBackMta(this, "2");
                            PaidTrafficHelper.toFlashBuy(this, str);
                            finish();
                        } else if (i2 == 3) {
                            PaidTrafficHelper.unionBackMta(this, "3");
                            PaidTrafficHelper.toH5(this, str, "https://prodev.m.jd.com/mall/active/3hLiHNu7cZt5Ub5mUB82mCoKpRJi/index.html?babelChannel=ttt3");
                            finish();
                        } else if (i2 == 4) {
                            PaidTrafficHelper.unionBackMta(this, "4");
                            PaidTrafficHelper.toRank(this, str);
                            finish();
                        } else if (i2 == 5) {
                            PaidTrafficHelper.unionBackMta(this, "5");
                            PaidTrafficHelper.toSuperMarket(this, str);
                            finish();
                        } else if (i2 == 6) {
                            PaidTrafficHelper.unionBackMta(this, "6");
                            PaidTrafficHelper.toH5(this, str, "https://h5.m.jd.com/rn/7ALV5RyyN8BspSqVT7tPMUjmVKe/index.html");
                            finish();
                        } else if (i2 == 7) {
                            PaidTrafficHelper.unionBackMta(this, "7");
                            PaidTrafficHelper.toNewSku(this, str);
                            finish();
                        } else if (AuraBundleConfig.getInstance().isBundlePrepared(AuraBundleInfos.getBundleNameFromBundleId(86))) {
                            openMiniDetailPage(str);
                        } else {
                            unionOpenHomePage();
                        }
                    }
                } else if (!TextUtils.isEmpty(skuId)) {
                    if (AuraBundleConfig.getInstance().isBundlePrepared(AuraBundleInfos.getBundleNameFromBundleId(86))) {
                        openMiniDetailPage(skuId);
                    } else {
                        unionOpenHomePage();
                    }
                } else {
                    unionOpenHomePage();
                }
                PaidTrafficHelper.skuid = "";
                PaidTrafficHelper.adBackUrl4Activity = "";
            } else {
                unionOpenHomePage();
            }
        }
        return false;
    }

    public boolean hasGrantedAlertWindow(boolean z, PermissionHelper.AlertWindowRequestCallBack alertWindowRequestCallBack) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        boolean canDrawOverlays = Settings.canDrawOverlays(this);
        if (!canDrawOverlays && z) {
            try {
                this.alertWindowRequestCallBack = alertWindowRequestCallBack;
                startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 169);
            } catch (Throwable unused) {
                this.alertWindowRequestCallBack = null;
                ToastUtils.shortToast(this, "\u8bf7\u524d\u5f80\u8bbe\u7f6e\u9875\u9762\u6253\u5f00\u5e94\u7528\u60ac\u6d6e\u7a97\u6743\u9650");
            }
        }
        return canDrawOverlays;
    }

    protected void hideModel() {
        ViewGroup rootFrameLayout = getRootFrameLayout();
        View model = getModel();
        if (model == null || rootFrameLayout.indexOfChild(model) == -1) {
            return;
        }
        rootFrameLayout.removeView(model);
        rootFrameLayout.invalidate();
    }

    public void hideSoftInput() {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    public boolean interceptJDShareCommand(HashMap<String, Object> hashMap) {
        return false;
    }

    public boolean isActivityInFrame() {
        Activity parent = getParent();
        return parent != null && (parent instanceof ScrollableTabActivity);
    }

    public boolean isAppDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 1;
    }

    public boolean isClearHistory(Intent intent) {
        return intent.getBooleanExtra("com.360buy:clearHistoryFlag", false);
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public boolean isDisplayCutout() {
        return UnAndroidUtils.isDisplayCutout();
    }

    public boolean isResend(Intent intent) {
        return intent.getBooleanExtra("com.360buy:resendFlag", false);
    }

    public boolean isSingleInstance(Intent intent) {
        return intent.getBooleanExtra("com.360buy:singleInstanceFlag", false);
    }

    public boolean isStatusBarTintEnable() {
        return UnStatusBarTintUtil.isEnable((Activity) this) && this.statusBarTintEnable;
    }

    public boolean isUseHistoryRecord(Intent intent) {
        return intent.getBooleanExtra("com.360buy:useHistoryFlag", false);
    }

    public void markJump() {
        this.jump = true;
    }

    public void noShowAgain() {
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 169 && this.alertWindowRequestCallBack != null) {
            if (Settings.canDrawOverlays(this)) {
                this.alertWindowRequestCallBack.onGranted();
            } else {
                this.alertWindowRequestCallBack.onDenied();
            }
            this.alertWindowRequestCallBack = null;
            return;
        }
        if (i2 == 1001 && i3 == 0) {
            BaseFrameUtil.exitAll();
        }
        if (i2 != 1215 || intent == null) {
            return;
        }
        String stringExtra = intent.hasExtra("sharedChannel") ? intent.getStringExtra("sharedChannel") : "";
        String stringExtra2 = intent.hasExtra("selectedChannel") ? intent.getStringExtra("selectedChannel") : "";
        String stringExtra3 = intent.hasExtra("sharedMsg") ? intent.getStringExtra("sharedMsg") : "";
        if (11 == i3) {
            onShareComplete(stringExtra);
        } else if (13 == i3) {
            onShareCancel();
        } else if (12 == i3) {
            onShareError(stringExtra3);
        } else if (14 == i3) {
            onShareBlock(stringExtra2, stringExtra3);
        } else if (15 == i3) {
            onShareClose();
        }
    }

    public void onAddProductCart(boolean z, int i2, String str) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "onBackPressed ");
        }
        if (handleInterveneReturn()) {
            return;
        }
        if (!KeplerJumpUtils.canShownRetainView()) {
            super.onStateNotSaved();
            super.onBackPressed();
        } else if (!KeplerJumpUtils.hasShownRetainView() && !KeplerJumpUtils.isIsRequestOpenRetainView()) {
            KeplerJumpUtils.openRetainView(this, getRootFrameLayout(), this.dragView);
        } else {
            KeplerJumpUtils.closeRetainView(this);
            super.onStateNotSaved();
            super.onBackPressed();
        }
    }

    protected void onClickEvent(String str, String str2, String str3) {
        if (OKLog.V) {
            OKLog.v(this.TAG, "onClickEvent clickId-->> " + str);
            OKLog.v(this.TAG, "onClickEvent event_param-->> " + str2);
            OKLog.v(this.TAG, "onClickEvent page_param-->> " + str3);
        }
        JDMtaUtils.onClick(getBaseContext(), str, getClass().getName(), str2, str3);
    }

    protected void onClickEventWithPageId(String str, String str2) {
        if (OKLog.W) {
            OKLog.w(this.TAG, "onClickEvent clickId-->> " + str);
            OKLog.w(this.TAG, "onClickEvent pageId-->> " + str2);
        }
        JDMtaUtils.onClickWithPageId(getBaseContext(), str, getClass().getSimpleName(), str2);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.fontScale > 1.0f) {
            configuration.setToDefaults();
            this.needResetResourceConfig = true;
        }
        if (OKLog.D) {
            OKLog.d("BaseActivity-configuration", "appHeight:" + com.jingdong.sdk.utils.DPIUtil.getAppHeight(this) + "  height:" + BaseInfo.getScreenHeight());
        }
        UnWidgetThemeController.getInstance().onConfigurationChanged(this);
        super.onConfigurationChanged(configuration);
        IConfigurationChangedListener iConfigurationChangedListener = this.mConfigurationChangedListener;
        if (iConfigurationChangedListener != null) {
            iConfigurationChangedListener.onConfigurationChanged(configuration);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.manualResume = true;
        UnStatusBarTintUtil.init(this);
        super.onCreate(bundle);
        try {
            PermissionHelper.onActivityCreate();
            if (this.finishAfterSuperOnCreate) {
                finish();
                return;
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(this.TAG, th);
            }
        }
        if (JdSdk.getInstance().getBuildConfigDebug()) {
            if (OKLog.D) {
                OKLog.d(this.TAG, "onCreate() -->> " + getClass().getName());
            }
            Boolean bool = Boolean.FALSE;
            Boolean booleanFromPreference = CommonBase.getBooleanFromPreference(JDReactConstant.SETTING_DEBUG_FLOAT_FLAG, bool);
            final Boolean booleanFromPreference2 = CommonBase.getBooleanFromPreference("setting_debug_mocker_asm_switch", bool);
            if (booleanFromPreference.booleanValue() || booleanFromPreference2.booleanValue()) {
                hasGrantedAlertWindow(true, new PermissionHelper.AlertWindowRequestCallBack() { // from class: com.jingdong.common.BaseActivity.1
                    {
                        BaseActivity.this = this;
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.AlertWindowRequestCallBack
                    public void onDenied() {
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.AlertWindowRequestCallBack
                    public void onGranted() {
                        try {
                            if (booleanFromPreference2.booleanValue()) {
                                MockerUtils.getInstance().initMocker(BaseActivity.this.getApplication(), 1);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
        }
        MonitorInfo.setRunStage(1);
        try {
            if (getIntent() != null && getIntent().hasExtra(DISPOSEABLE_UNABLE_ANIM) && getIntent().getBooleanExtra(DISPOSEABLE_UNABLE_ANIM, false)) {
                if (OKLog.D) {
                    OKLog.d(this.TAG, "disposeable no enter anim");
                }
                int i2 = R.anim.nothing;
                overridePendingTransition(i2, i2);
            } else if (ABTestUtils.useUniActivityAnim()) {
                overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, e2);
            }
            int i3 = R.anim.nothing;
            overridePendingTransition(i3, i3);
        }
        if (this.activityManagedByNumController) {
            JDSoftReference<Activity> jDSoftReference = new JDSoftReference<>(this);
            this.softReference = jDSoftReference;
            jDSoftReference.setTag(getClass().getName());
            ActivityNumController.addList(this.softReference);
        }
        com.jingdong.jdsdk.b.a.d("", toString() + String.format("(%d)", 0));
        BaseFrameUtil.getInstance().setCurMyActivity(this);
        if (this.needSetOrientation) {
            if (Build.VERSION.SDK_INT == 26 && getApplicationInfo().targetSdkVersion > 26 && CompatOThemeUtils.isTranslucentOrFloating(this)) {
                LogUtil.e("hit Android O non fullscreen restrict!!!");
            } else {
                setRequestedOrientation(1);
            }
        }
        this.mUiThread = Thread.currentThread();
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        } catch (Throwable th2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, th2);
            }
        }
        this.sharedPreferences = CommonBase.getJdSharedPreferences();
        if (getIntent() != null) {
            try {
                this.isFromNF = getIntent().getBooleanExtra(ISFROMNF, false);
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, e3);
                }
                this.isFromNF = false;
            }
        }
        initHardAcclCheck();
        AdvertUtils.dealFltOnPageStart(getClass().getSimpleName());
        if (getIntent() != null) {
            try {
                Class.forName("com.jingdong.common.XView.ShareGiftXViewHelper").getMethod("dealShareGiftOnCreate", BaseActivity.class, Bundle.class).invoke(null, this, getIntent().getExtras());
            } catch (Exception e4) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, e4);
                }
            }
        }
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
            ThemeTitleDataController.getInstance().getThemeTitleData(1);
            try {
                JDSkinSDK.getInstance().getResData(AppStateType.APP_CHANGE_ACTIVITY);
            } catch (Exception e5) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, e5);
                }
            }
        }
        BaseArchUtil.sendStartUpMta(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "onDestroy() TaskId = " + getTaskId() + " -->> " + getClass().getName());
        }
        super.onDestroy();
        this.alertWindowRequestCallBack = null;
        PermissionHelper.onActivityDestroy();
        if (this.activityManagedByNumController) {
            ActivityNumController.remove(this.softReference);
        }
        synchronized (this) {
            ArrayList<IDestroyListener> arrayList = this.destroyListenerList;
            if (arrayList != null) {
                Iterator<IDestroyListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onDestroy();
                }
                this.destroyListenerList = null;
            }
            this.pauseListenerList = null;
            this.resumeListenerList = null;
        }
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null) {
            httpGroupWithNPS.destory();
            this.mHttpGroupWithNPS = null;
        }
        this.showOrHideCallback = null;
        JMA.stopTrackView(getClass().getCanonicalName());
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(null);
        }
    }

    @Override // com.jingdong.jdsdk.network.dependency.IModalViewController
    public void onHideModal() {
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (removeGuideView() || removeXview2()) {
                return true;
            }
            if (i2 == 4 && this.isFromNF) {
                finish();
                if (BaseFrameUtil.getInstance().getMainFrameActivity() == null) {
                    BaseFrameUtil.getInstance().startMainFrameActivity(this);
                } else {
                    super.onKeyDown(i2, keyEvent);
                }
                return true;
            } else if (TextUtils.equals("com.jd.lib.jdflutter.JDFlutterMainActivity", getClass().getCanonicalName()) && handleInterveneReturn()) {
                return true;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.manualResume = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "onPause() TaskId = " + getTaskId() + " -->> " + getClass().getName());
        }
        super.onPause();
        JDMtaUtils.onPause();
        synchronized (this) {
            ArrayList<IPauseListener> arrayList = this.pauseListenerList;
            if (arrayList != null) {
                Iterator<IPauseListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onPause();
                }
            }
        }
        this.manualResume = false;
    }

    public void onRefresh() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        PermissionHelper.onRequestPermissionsResult(this, i2, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        com.jingdong.jdsdk.b.a.d("", toString() + String.format("(%d)", 1));
        if (OKLog.D) {
            OKLog.d(this.TAG, "onRestart() TaskId = " + getTaskId() + " -->> " + getClass().getName());
        }
        if (System.currentTimeMillis() - this.stopTime > 86400000) {
            if (OKLog.D) {
                OKLog.d(this.TAG, "onRestart() -->> update cache time");
            }
            CacheTimeUtil.queryCacheTime();
        }
        super.onRestart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "Resume() TaskId = " + getTaskId() + " -->> " + getClass().getName());
        }
        UnStatusBarTintUtil.init(this);
        BaseFrameUtil.getInstance().setCurMyActivity(this);
        super.onResume();
        JDMtaUtils.onResume(this);
        handleOnResume();
        JDSharedCommandUtils.getInstance().updateJDCommandContext(this);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (OKLog.D) {
            OKLog.d(this.TAG, "onSaveInstanceState() TaskId = " + getTaskId() + " -->> " + getClass().getName());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        if (OKLog.I) {
            OKLog.i("test", "onSearchRequested++++my");
            return true;
        }
        return true;
    }

    public void onShareBlock(String str, String str2) {
    }

    public void onShareCancel() {
    }

    public void onShareClose() {
    }

    public void onShareComplete(String str) {
    }

    public void onShareError(String str) {
    }

    @Override // com.jingdong.jdsdk.network.dependency.IModalViewController
    public void onShowModal() {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        showKeplerDragView();
        if (OKLog.D) {
            OKLog.d(this.TAG, "onStart() TaskId = " + getTaskId() + " -->> " + getClass().getName());
        }
        if (this.touchFlag) {
            JMA.addTrackView(this);
            this.touchFlag = false;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "onStop() TaskId = " + getTaskId() + " -->> " + getClass().getName());
        }
        this.stopTime = System.currentTimeMillis();
        super.onStop();
        if (this.isNeedKeplerFlowDragView) {
            KeplerJumpUtils.tryHideFlowBackView(getRootFrameLayout(), this.dragView);
            KeplerJumpUtils.closeRetainView(this);
        }
        JMA.flush();
    }

    @SuppressLint({"NewApi"})
    public void onTitleBack() {
        if (OKLog.D) {
            OKLog.d(this.TAG, " -->> mTitleBack onClick : ");
        }
        if (removeGuideView() || removeXview2()) {
            return;
        }
        if (this.isFromNF) {
            if (OKLog.D) {
                OKLog.d(this.TAG, " -->>mTitleBack onClick : isBackToHome ");
            }
            finish();
            if (BaseFrameUtil.getInstance().getMainFrameActivity() == null) {
                BaseFrameUtil.getInstance().startMainFrameActivity(this);
                return;
            }
            try {
                onBackPressed();
                return;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, e2);
                    return;
                }
                return;
            }
        }
        try {
            onBackPressed();
        } catch (Exception e3) {
            if (OKLog.E) {
                OKLog.e(this.TAG, e3);
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void post(Runnable runnable) {
        if (isFinishing()) {
            return;
        }
        this.handler.post(runnable);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void putBooleanToPreference(String str, Boolean bool) {
        this.sharedPreferences.edit().putBoolean(str, bool.booleanValue()).commit();
    }

    public void putStringToPreference(String str, String str2) {
        this.sharedPreferences.edit().putString(str, str2).commit();
    }

    public void refreshProductListByFilterData(Intent intent) {
    }

    public void removeBackKeyTriggerListener() {
        this.mBackKeyTriggerListener = null;
    }

    public void removeConfigurationChangedListener() {
        this.mConfigurationChangedListener = null;
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void removeDestroyListener(IDestroyListener iDestroyListener) {
        synchronized (this) {
            ArrayList<IDestroyListener> arrayList = this.destroyListenerList;
            if (arrayList != null) {
                arrayList.remove(iDestroyListener);
            }
        }
    }

    public boolean removeGuideView() {
        ViewGroup viewGroup;
        if (this.imageViewLayout == null || (viewGroup = this.rootView) == null || !viewGroup.isShown()) {
            return false;
        }
        this.rootView.removeView(this.imageViewLayout);
        this.rootView = null;
        this.imageViewLayout = null;
        return true;
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void removeLogoutListener(ILogoutListener iLogoutListener) {
        synchronized (this) {
            ArrayList<ILogoutListener> arrayList = this.logoutListenerList;
            if (arrayList != null) {
                arrayList.remove(iLogoutListener);
            }
        }
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void removePauseListener(IPauseListener iPauseListener) {
        synchronized (this) {
            ArrayList<IPauseListener> arrayList = this.pauseListenerList;
            if (arrayList != null) {
                arrayList.remove(iPauseListener);
            }
        }
    }

    public void removeRecordTop() {
        if (this.isPrevNotInRecord) {
            this.isPrevNotInRecord = false;
        } else if (this.recordList.isEmpty()) {
        } else {
            Record remove = this.recordList.remove(0);
            if (isSingleInstance(remove.getIntent())) {
                return;
            }
            this.activityManager.destroyActivity(remove.getId(), true);
        }
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void removeResumeListener(IResumeListener iResumeListener) {
        synchronized (this) {
            ArrayList<IResumeListener> arrayList = this.resumeListenerList;
            if (arrayList != null) {
                arrayList.remove(iResumeListener);
            }
        }
    }

    public boolean removeXview2() {
        if (hasActiveFragment(getSupportFragmentManager())) {
            OKLog.d(this.TAG, "hasActiveFragment");
            return true;
        }
        IBackKeyTriggerListener iBackKeyTriggerListener = this.mBackKeyTriggerListener;
        if (iBackKeyTriggerListener != null) {
            iBackKeyTriggerListener.backKeyTrigger();
            this.mBackKeyTriggerListener = null;
            return true;
        }
        return false;
    }

    public void setBackKeyTriggerListener(IBackKeyTriggerListener iBackKeyTriggerListener) {
        this.mBackKeyTriggerListener = iBackKeyTriggerListener;
    }

    protected void setBackground(View view, Drawable drawable) {
        if (view == null || drawable == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public void setConfigurationChangedListener(IConfigurationChangedListener iConfigurationChangedListener) {
        this.mConfigurationChangedListener = iConfigurationChangedListener;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i2) {
        try {
            super.setContentView(i2);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(this.TAG, th);
            }
            GlobalImageCache.getLruBitmapCache().clean();
            super.setContentView(i2);
        }
        setStatusBarTint();
        if (this.statusBarTintEnable) {
            return;
        }
        UnStatusBarTintUtil.cutout(this);
    }

    protected void setGuideResId(int i2) {
        this.guideResourceId = i2;
    }

    public void setJDData(Intent intent) {
        this.mData = intent;
    }

    public void setJDResult(int i2) {
        if (getJDData() != null) {
            setResult(i2, getJDData());
        } else {
            setResult(i2);
        }
    }

    public void setManualResume(boolean z) {
        this.manualResume = z;
    }

    public void setModelYDistance(int i2) {
        int dip2px = DPIUtil.dip2px(isActivityInFrame() ? 45.0f : 70.0f) - Math.abs(i2);
        int dip2px2 = isActivityInFrame() ? 0 : DPIUtil.dip2px(25.0f);
        if (dip2px < dip2px2) {
            dip2px = dip2px2;
        }
        this.yDistance = dip2px;
        if (OKLog.D) {
            OKLog.d(this.TAG, " view scroll from top distance == " + this.yDistance);
        }
        this.isSpecial = true;
        checkNetwork();
    }

    public void setNeedSetOrientation(boolean z) {
        this.needSetOrientation = z;
    }

    public void setNetworkModel(boolean z) {
        this.isOnNetwork = z;
    }

    public void setPageId(String str) {
        this.page_id = str;
    }

    public void setShopId(String str) {
        this.shop_id = str;
    }

    public void setSubRootView(ViewGroup viewGroup) {
        this.subRootView = viewGroup;
    }

    public void setTitleBack(ImageView imageView) {
        setTitleBack(imageView, null);
    }

    public void setUseBasePV(boolean z) {
        this.isUseBasePV = z;
    }

    public void showKeplerDragView() {
        if (this.isNeedKeplerFlowDragView) {
            if (this.showOrHideCallback == null) {
                this.showOrHideCallback = new KeplerJumpUtils.ShowOrHideCallback() { // from class: com.jingdong.common.BaseActivity.3
                    {
                        BaseActivity.this = this;
                    }

                    @Override // com.jingdong.common.kepler.KeplerJumpUtils.ShowOrHideCallback
                    public void showOrHide(boolean z) {
                        KeplerJumpUtils.tryHideFlowBackView(BaseActivity.this.getRootFrameLayout(), BaseActivity.this.dragView);
                        if (KeplerJumpUtils.canShownRetainView()) {
                            KeplerJumpUtils.closeRetainView(BaseActivity.this);
                        }
                    }
                };
            }
            KeplerJumpUtils.tryHideFlowBackView(getRootFrameLayout(), this.dragView);
            this.dragView = KeplerJumpUtils.tryShowFlowBackView(this, getRootFrameLayout(), this.showOrHideCallback);
        }
    }

    protected void showModel() {
        ViewGroup rootFrameLayout = getRootFrameLayout();
        View model = getModel();
        if (model != null && rootFrameLayout.indexOfChild(model) == -1) {
            rootFrameLayout.addView(model);
            rootFrameLayout.invalidate();
        }
    }

    public void startActivityForResultForFragment(Fragment fragment, Intent intent, int i2) {
        startActivityForResultNoExceptionForFragment(fragment, intent, i2);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void startActivityForResultNoException(Intent intent, int i2) {
        startActivityForResultNoExceptionStatic(this, intent, i2);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void startActivityForResultNoExceptionStatic(Activity activity, Intent intent, int i2) {
        if (intent == null || activity == null) {
            return;
        }
        try {
            activity.startActivityForResult(intent, i2);
        } catch (ActivityNotFoundException e2) {
            if (OKLog.E) {
                OKLog.e(this.TAG, "startActivityForResultNoException", e2);
            }
            catchToastTip(activity, intent);
            ExceptionReporter.reportExceptionToBugly(new RuntimeException("startActivityForResultNoException()", e2));
        } catch (Exception e3) {
            if (OKLog.E) {
                OKLog.e(this.TAG, "startActivityForResultNoException", e3);
            }
        }
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void startActivityInFrame(Intent intent) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "startActivityInFrame() -->> ");
        }
        startActivity(intent);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void startActivityInFrameWithNoNavigation(Intent intent) {
        intent.putExtra("com.360buy:navigationDisplayFlag", -1);
        startActivityInFrame(intent);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void startActivityNoException(Intent intent) {
        startActivityForResultNoException(intent, -1);
    }

    public void startSingleActivityInFrame(Intent intent) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "startSingleActivityInFrame() -->> ");
        }
        BaseActivity currentMyActivity = getCurrentMyActivity();
        if (currentMyActivity != null) {
            currentMyActivity.startActivityInFrame(intent);
        }
    }

    public void startSubActivity(Intent intent) {
        startActivity(intent);
    }

    public void startTaskActivityInFrame(Intent intent) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "startTaskActivityInFrame() -->> " + intent);
        }
        startTaskActivityInFrame(intent, createTaskId(intent));
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public int statusBarHint() {
        return isStatusBarHintTagDefault() ? 0 : -1;
    }

    public void statusBarRefresh() {
        if (!isStatusBarTintEnable() || statusBarTransparentEnable()) {
            return;
        }
        UnStatusBarTintUtil.refresh(this, isAppDarkMode());
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public boolean statusBarTransparentEnable() {
        return this.statusBarTransparentEnable;
    }

    protected void updateButtonEnable(final Button button, final boolean z) {
        if (button == null) {
            return;
        }
        post(new Runnable() { // from class: com.jingdong.common.BaseActivity.9
            {
                BaseActivity.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                button.setEnabled(z);
            }
        });
    }

    public boolean getBooleanFromPreference(String str, boolean z) {
        return this.sharedPreferences.getBoolean(str, z);
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public HttpGroup getHttpGroupaAsynPool(int i2) {
        HttpGroup httpGroupaAsynPool = HttpGroupUtils.getHttpGroupaAsynPool(i2);
        HttpGroupSetting httpGroupSetting = httpGroupaAsynPool.getHttpGroupSetting();
        httpGroupSetting.setMyActivity(this);
        httpGroupSetting.setProgressBarRootLayout(getSubRootView());
        return httpGroupaAsynPool;
    }

    public String getStringFromPreference(String str, String str2) {
        return this.sharedPreferences.getString(str, str2);
    }

    public void setTitleBack(ImageView imageView, final Runnable runnable) {
        this.mTitleBack = imageView;
        imageView.setVisibility(0);
        this.mTitleBack.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.BaseActivity.4
            {
                BaseActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
                BaseActivity.this.onTitleBack();
            }
        });
    }

    @Override // com.jingdong.common.frame.IMyActivity
    public void post(Runnable runnable, int i2) {
        if (isFinishing()) {
            return;
        }
        this.handler.postDelayed(runnable, i2);
    }

    protected void onClickEventWithPageId(String str, String str2, String str3, String str4) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "onClickEvent clickId-->> " + str);
            OKLog.d(this.TAG, "onClickEvent event_param-->> " + str2);
            OKLog.d(this.TAG, "onClickEvent page_param-->> " + str3);
            OKLog.d(this.TAG, "onClickEvent page_id-->> " + str4);
        }
        JDMtaUtils.sendCommonData(getBaseContext(), str, str2, "onClick", getClass().getSimpleName(), str3, "", "", str4, "");
    }

    public void startTaskActivityInFrame(Intent intent, HashMap<String, Object> hashMap) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "startTaskActivityInFrame() -->> " + intent + "|" + hashMap);
        }
        intent.putExtra("com.360buy:taskIdFlag", hashMap);
        intent.putExtra("com.360buy:navigationDisplayFlag", -1);
        startActivityInFrame(intent);
    }

    protected void onClickEvent(String str) {
        if (OKLog.I) {
            OKLog.i(this.TAG, "onClickEvent clickId-->> " + str);
        }
        JDMtaUtils.onClick(getBaseContext(), str, getClass().getSimpleName());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        try {
            super.setContentView(view);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(this.TAG, th);
            }
            GlobalImageCache.getLruBitmapCache().clean();
            super.setContentView(view);
        }
        setStatusBarTint();
    }

    protected void onClickEvent(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "onClickEvent clickId-->> " + str);
            OKLog.d(this.TAG, "onClickEvent value-->> " + str2);
        }
        JDMtaUtils.onClick(getBaseContext(), str, getClass().getName(), str2);
    }

    public void checkNetwork(int i2) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "checkNetwork() from = " + i2 + " -->>  needCheckNet " + this.needCheckNet);
        }
        if (this.needCheckNet) {
            checkNetwork();
        }
    }

    protected void addGuideImage(ViewGroup viewGroup, int i2, int i3, int i4, ImageView.ScaleType scaleType, boolean z) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "addGuideImage -->> ");
        }
        this.rootView = viewGroup;
        if (OKLog.D) {
            OKLog.d(this.TAG, "view -->> " + this.rootView);
        }
        if (this.rootView == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(this.TAG, "guideResourceId -->> " + this.guideResourceId);
        }
        if (this.guideResourceId != 0) {
            this.imageViewLayout = new FrameLayout(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
            layoutParams.gravity = i2;
            layoutParams.height = DPIUtil.getHeight();
            if (z) {
                layoutParams.topMargin = getStatusHeight();
            }
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
            try {
                simpleDraweeView.setImageResource(this.guideResourceId);
                if (scaleType != null) {
                    simpleDraweeView.setScaleType(scaleType);
                }
                this.imageViewLayout.addView(simpleDraweeView, layoutParams);
                this.imageViewLayout.setBackgroundColor(getResources().getColor(R.color.slide_prompt_bg));
                this.imageViewLayout.getBackground().setAlpha(200);
                CommonBase.setIsGuided(getClass().getName());
                this.imageViewLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.BaseActivity.6
                    {
                        BaseActivity.this = this;
                    }

                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 1) {
                            BaseActivity.this.removeGuideView();
                        }
                        return true;
                    }
                });
                this.rootView.addView(this.imageViewLayout, new ViewGroup.LayoutParams(-1, -1));
                this.rootView.invalidate();
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(this.TAG, th);
                }
            }
        }
    }
}
