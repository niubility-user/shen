package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.navigationbar.JDNavigationFragment;
import com.jingdong.app.mall.navigationbar.NavigationBarUtil;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConfig;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.unification.navigationbar.newbar.StateController;
import com.jingdong.common.unification.navigationbar.newbar.TabShowNew;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDNavigationModule implements IJDModule {
    private static final String KEY_ACTIVITY_ENDTIME = "activityEndTime";
    private static final String KEY_ACTIVITY_ONLINE = "activityOnLine";
    private static final String KEY_NAVIGATION = "navigation";
    private static final String KEY_SHOWORHIDENAVI = "showOrHideNavi";
    private static final String KEY_SWITCH = "switch";
    private static final String KEY_TIME = "time";
    private static final String KEY_VALUE = "value";
    public static final String TAG = "JDNavigationModule";
    public static final String VALUE_DISABLE = "0";
    public static final String VALUE_ENABLE = "1";
    public static final String VALUE_SHOWORHIDENAVI_DISABLE = "0";
    public static final String VALUE_SHOWORHIDENAVI_ENABLE = "1";
    private static boolean isAlreadyRefreshNavigation;

    public void reductionNavigation(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        MainFrameActivity b;
        JDNavigationFragment navigationFragment;
        try {
            if (jSONObject == null) {
                if (callBackListener != null) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                return;
            }
            int optInt = jSONObject.optInt(CustomThemeConstance.NAVI_NAVIGATION_ID);
            if (NavigationBase.getInstance().mCurrentIndex != optInt || (b = com.jingdong.app.mall.n.a.a().b()) == null || (navigationFragment = b.getNavigationFragment()) == null) {
                return;
            }
            navigationFragment.u(optInt);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void refreshNavigation(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        MainFrameActivity b;
        try {
            String config = JDMobileConfig.getInstance().getConfig("navigation", KEY_ACTIVITY_ENDTIME, "time");
            long currentTimeMillis = System.currentTimeMillis();
            if (OKLog.D) {
                OKLog.d(TAG, "JDNavigationModule--refreshNavigation-activityEndTime=" + config + " curTime=" + currentTimeMillis + " isElderMode=" + JDElderModeUtils.isElderMode() + " isAlreadyRefreshNavigation=" + isAlreadyRefreshNavigation);
            }
            if (!TextUtils.isEmpty(config) && !isAlreadyRefreshNavigation && currentTimeMillis > Long.parseLong(config) && !JDElderModeUtils.isElderMode()) {
                isAlreadyRefreshNavigation = true;
                MainFrameActivity b2 = com.jingdong.app.mall.n.a.a().b();
                if (b2 == null || b2.getNavigationFragment() == null) {
                    return;
                }
                b2.getNavigationFragment().x();
                return;
            }
            String config2 = JDMobileConfig.getInstance().getConfig("navigation", KEY_ACTIVITY_ONLINE, "value");
            if (OKLog.D) {
                OKLog.d(TAG, "JDNavigationModule--refreshNavigation-activityOnLine=" + config2 + " isElderMode=" + JDElderModeUtils.isElderMode());
            }
            if (!"1".equals(config2) || JDElderModeUtils.isElderMode() || (b = com.jingdong.app.mall.n.a.a().b()) == null || b.getNavigationFragment() == null) {
                return;
            }
            b.getNavigationFragment().y(true);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void refreshNavigationPage(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            if (jSONObject == null) {
                if (callBackListener != null) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                return;
            }
            int optInt = jSONObject.optInt(CustomThemeConstance.NAVI_NAVIGATION_ID);
            MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
            if (b != null) {
                JDTabFragment tabFragment = b.getTabFragment();
                if (OKLog.D) {
                    OKLog.d(TAG, "JDNavigationModule--refreshNavigationPage-iPage=" + tabFragment + " navigationId=" + optInt);
                }
                if (tabFragment != null) {
                    tabFragment.clickNavigation(NavigationBase.getInstance().oldPage, optInt, NavigationBase.getInstance().getmUrl(optInt));
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void setCheckedNavigationButton(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
            if (b == null || b.getNavigationFragment() == null) {
                return;
            }
            b.getNavigationFragment().z(jSONObject.optInt("index"));
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void setNaviImmersiveWithB(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            if (jSONObject == null) {
                if (callBackListener != null) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                return;
            }
            int optInt = jSONObject.optInt("sourceFrom");
            MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
            if (OKLog.D) {
                OKLog.d(TAG, "JDNavigationModule--navigationCurrentMode====" + NavigationBase.getInstance().navigationCurrentMode + "====sourceFrom====" + optInt);
            }
            if (OKLog.D) {
                OKLog.d(TAG, "JDNavigationModule--mCurrentIndex====" + NavigationBase.getInstance().mCurrentIndex + "====sourceFrom====" + optInt);
            }
            if (b == null || NavigationBase.getInstance().navigationCurrentMode != 2) {
                return;
            }
            boolean optBoolean = jSONObject.optBoolean("isUseImmSkin", false);
            if (OKLog.D) {
                OKLog.d(TAG, "JDNavigationModule--setNaviImmersiveWithB-isUseImmSkin=" + optBoolean);
            }
            if (!optBoolean || optInt == NavigationBase.getInstance().mCurrentIndex) {
                b.setNaviImm(optBoolean);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void setNavigationImm(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (jSONObject != null || callBackListener == null) {
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void setNavigationNormal(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
            if (b != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "JDNavigationModule--setNavigationNormal");
                }
                b.setNaviImm(false);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void setNavigationVisibility(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            String config = JDMobileConfig.getInstance().getConfig("navigation", KEY_SHOWORHIDENAVI, KEY_SWITCH, "1");
            if (OKLog.D) {
                OKLog.d(TAG, "JDNavigationModule--setNavigationVisibility-switchValue=" + config);
            }
            if ("1".equals(config)) {
                if (jSONObject == null) {
                    if (callBackListener != null) {
                        JDRouterUtil.callBackError(callBackListener, 703);
                        return;
                    }
                    return;
                }
                MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
                if (b != null) {
                    boolean optBoolean = jSONObject.optBoolean(XView2Constants.ISVISIBLE, false);
                    int optInt = jSONObject.optInt("caller");
                    if (OKLog.D) {
                        OKLog.d(TAG, "JDNavigationModule--setNavigationVisibility-isVisible=" + optBoolean);
                    }
                    b.setNavigationVisibility(optBoolean, optInt);
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }

    public void showNavigationEffectWhenClick(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
            if (b != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "JDNavigationModule--showNavigationEffectWhenClick");
                }
                b.showNavigationEffectWhenClick();
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void showNavigationLabel(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        MainFrameActivity b;
        Handler handler;
        try {
            if (jSONObject == null) {
                if (callBackListener != null) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                return;
            }
            final String optString = jSONObject.optString("functionId");
            final String optString2 = jSONObject.optString(Constant.KEY_PROMOTION_LABEL);
            if (TextUtils.isEmpty(optString) || NavigationBarUtil.angleSwitch()) {
                return;
            }
            if (!NavigationBarUtil.angleRequested) {
                NavigationBarUtil.angleSet.put(optString, optString2);
                if (Log.D) {
                    Log.d("Navigation-switch", "add angle " + optString2);
                    return;
                }
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "showNavigationLabel-functionId=" + optString + " label=" + optString2);
            }
            List<NavigationButton> list = NavigationBase.getInstance().buttons;
            if (list == null || list.size() <= 0 || (b = com.jingdong.app.mall.n.a.a().b()) == null || (handler = b.getHandler()) == null) {
                return;
            }
            for (final NavigationButton navigationButton : list) {
                if (navigationButton != null && com.jingdong.app.mall.navigationbar.c.G().M(optString, null) == navigationButton.getNavigationId()) {
                    handler.post(new Runnable() { // from class: com.jingdong.common.unification.router.module.JDNavigationModule.2
                        @Override // java.lang.Runnable
                        public void run() {
                            StateController stateController = navigationButton.getStateController();
                            if (OKLog.D) {
                                OKLog.d(JDNavigationModule.TAG, "showNavigationLabel-stateController=" + stateController + "-" + Thread.currentThread());
                            }
                            if (stateController != null) {
                                stateController.lablePosition = optString;
                                stateController.setButtonLabel(optString2);
                            }
                            NavigationConfig navigationConfig = new NavigationConfig(navigationButton.getNavigationId(), optString, optString2);
                            navigationConfig.setCurrentMode(NavigationBase.getInstance().navigationCurrentMode);
                            NavigationBase.getInstance().setOrUpdateNavigationConfig(navigationConfig);
                        }
                    });
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void showNavigationRedPoint(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        MainFrameActivity b;
        Handler handler;
        try {
            if (jSONObject == null) {
                if (callBackListener != null) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                return;
            }
            final String optString = jSONObject.optString("functionId");
            if (OKLog.D) {
                OKLog.d(TAG, "showNavigationRedPoint-functionId=" + optString);
            }
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            final boolean optBoolean = jSONObject.optBoolean("isShowRedPoint", false);
            if (TextUtils.equals(optString, "find") && NavigationBarUtil.redPointSwitch()) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "showNavigationRedPoint-isShowRedPoint=" + optBoolean);
            }
            List<NavigationButton> list = NavigationBase.getInstance().buttons;
            if (list == null || list.size() <= 0 || (b = com.jingdong.app.mall.n.a.a().b()) == null || (handler = b.getHandler()) == null) {
                return;
            }
            for (final NavigationButton navigationButton : list) {
                if (navigationButton != null && com.jingdong.app.mall.navigationbar.c.G().M(optString, null) == navigationButton.getNavigationId()) {
                    handler.post(new Runnable() { // from class: com.jingdong.common.unification.router.module.JDNavigationModule.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TabShowNew tabShowNew = navigationButton.getTabShowNew();
                            if (OKLog.D) {
                                OKLog.d(JDNavigationModule.TAG, "showNavigationRedPoint-tabShowNew=" + tabShowNew + "-" + Thread.currentThread());
                            }
                            if (tabShowNew != null) {
                                tabShowNew.setIsShowRedPoint(Boolean.valueOf(optBoolean));
                            }
                            NavigationConfig navigationConfig = new NavigationConfig(navigationButton.getNavigationId(), optString, optBoolean);
                            navigationConfig.setCurrentMode(NavigationBase.getInstance().navigationCurrentMode);
                            NavigationBase.getInstance().setOrUpdateNavigationConfig(navigationConfig);
                        }
                    });
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void updateNavigationIcon(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            if (jSONObject == null) {
                if (callBackListener != null) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                return;
            }
            int optInt = jSONObject.optInt(CustomThemeConstance.NAVI_NAVIGATION_ID);
            String optString = jSONObject.optString("iconPath");
            String optString2 = jSONObject.optString(CustomThemeConstance.NAVI_LOTTIE_PATH);
            if (OKLog.D) {
                OKLog.d(TAG, "updateNavigationIcon-navigationId=" + optInt + " iconPath=" + optString + " lottiePath=" + optString2);
            }
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            if (!new File(optString).exists()) {
                if (OKLog.D) {
                    OKLog.d(TAG, "updateNavigationIcon-\u6587\u4ef6\u4e0d\u5b58\u5728");
                    return;
                }
                return;
            }
            List<NavigationButton> list = NavigationBase.getInstance().buttons;
            if (list != null && list.size() > 0) {
                for (NavigationButton navigationButton : list) {
                    if (navigationButton != null && navigationButton.getNavigationId() == optInt) {
                        boolean updateIcon = navigationButton.updateIcon(optString, optString2);
                        if (OKLog.D) {
                            OKLog.d(TAG, "updateNavigationIcon-isSetSuccess=" + updateIcon);
                        }
                        if (callBackListener != null && updateIcon) {
                            callBackListener.onComplete();
                        }
                    }
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }
}
