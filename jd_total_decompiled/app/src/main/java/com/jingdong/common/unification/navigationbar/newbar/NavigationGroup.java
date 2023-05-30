package com.jingdong.common.unification.navigationbar.newbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jd.skin.lib.bean.ResourceItems;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConfig;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.navigationbar.RadioStateDrawable;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.builderimpl.DefaultRouterBuilder;
import com.jingdong.common.unification.router.module.JDNavigationModule;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class NavigationGroup extends LinearLayout implements View.OnClickListener {
    private static final long DELAY_TIME = 100;
    private View.OnClickListener buttonOnClick;
    private List<NavigationButton> buttons;
    private int checkId;
    private long clickTime;
    private Context context;
    private View lastClickView;
    private LinearLayout.LayoutParams layoutParams;
    private NavigationButton oldClick;
    private View.OnTouchListener onTouchListener;

    public NavigationGroup(Context context) {
        super(context);
        this.checkId = NavigationBase.getInstance().mCurrentIndex;
        this.clickTime = 0L;
        this.context = context;
    }

    private void initButtons(INavigationShow iNavigationShow, final Activity activity) {
        int i2;
        OKLog.d("NavigationTest", "initButtons===>");
        removeAllViews();
        if (activity != null) {
            i2 = Math.min(DPIUtil.getAppWidth(activity), DPIUtil.getAppHeight(activity)) / this.buttons.size();
            RadioStateDrawable.screen_width = DPIUtil.getAppWidth(activity);
        } else {
            i2 = 0;
        }
        if (i2 == 0 || RadioStateDrawable.screen_width == 0 || activity == null) {
            if (this.context == null) {
                this.context = JdSdk.getInstance().getApplicationContext();
            }
            i2 = Math.min(DpiUtil.getWidth(this.context), DpiUtil.getHeight(this.context)) / this.buttons.size();
            RadioStateDrawable.screen_width = DpiUtil.getWidth(this.context);
        }
        RadioStateDrawable.width = i2;
        boolean isShowAnim = isShowAnim();
        if (Log.D) {
            Log.d("NavigationGroup", "showAnim=" + isShowAnim);
        }
        for (int i3 = 0; i3 < this.buttons.size(); i3++) {
            final NavigationButton navigationButton = this.buttons.get(i3);
            int navigationId = navigationButton.getNavigationId();
            navigationButton.isShowAnim(isShowAnim);
            navigationButton.setId(navigationId);
            navigationButton.setPadding(0, 0, 0, 0);
            if (iNavigationShow != null) {
                iNavigationShow.show(navigationButton);
            }
            if (navigationButton.bigIconTag) {
                this.layoutParams = new LinearLayout.LayoutParams(i2, getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_big_icon_height));
            } else {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_icon_height));
                this.layoutParams = layoutParams;
                layoutParams.gravity = 80;
            }
            navigationButton.setClipChildren(false);
            navigationButton.setLayoutParams(this.layoutParams);
            final View findViewById = navigationButton.findViewById(R.id.navigation_click_part);
            findViewById.setId(navigationId);
            findViewById.setSelected(false);
            findViewById.setContentDescription(navigationButton.getLabel());
            findViewById.setOnClickListener(this);
            findViewById.setOnTouchListener(this.onTouchListener);
            ViewCompat.setAccessibilityDelegate(findViewById, new AccessibilityDelegateCompat() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationGroup.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setSelected(view.isSelected());
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View view, int i4, Bundle bundle) {
                    if (i4 == 16) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "performAccessibilityAction-id=" + view.getId() + " oldPage=" + NavigationBase.getInstance().oldPage);
                        }
                        if (view.getId() == NavigationBase.getInstance().oldPage) {
                            ((DefaultRouterBuilder) JDRouter.to(JDNavigationModule.TAG, "refreshNavigationPage").putInt(CustomThemeConstance.NAVI_NAVIGATION_ID, view.getId())).jump(activity);
                        }
                    }
                    return super.performAccessibilityAction(view, i4, bundle);
                }
            });
            StateController stateController = navigationButton.getStateController();
            if (stateController != null) {
                stateController.setNumListener(new IRefreshNumListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationGroup.2
                    @Override // com.jingdong.common.unification.navigationbar.newbar.IRefreshNumListener
                    public void refreshNavigationNum(String str) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "refreshNavigationNum_naviClick=" + findViewById + " button.getLabel()=" + navigationButton.getLabel() + " num=" + str);
                        }
                        if (findViewById == null || TextUtils.isEmpty(str)) {
                            return;
                        }
                        findViewById.setContentDescription(navigationButton.getLabel() + str);
                    }
                });
                stateController.setLabelListener(new IRefreshLabelListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationGroup.3
                    @Override // com.jingdong.common.unification.navigationbar.newbar.IRefreshLabelListener
                    public void refreshNavigationLabel(String str) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "refreshNavigationLabel_naviClick=" + findViewById + " button.getLabel()=" + navigationButton.getLabel() + " label=" + str);
                        }
                        if (findViewById == null || TextUtils.isEmpty(str)) {
                            return;
                        }
                        findViewById.setContentDescription(navigationButton.getLabel() + str);
                    }
                });
            }
            addView(navigationButton, i3);
            navigationButton.setDefault(false);
            if (this.checkId == navigationId) {
                navigationButton.setClick(true);
                findViewById.setSelected(true);
                NavigationButton navigationButton2 = this.oldClick;
                if (navigationButton2 == null || navigationButton2.getNavigationId() != navigationButton.getNavigationId()) {
                    if (navigationButton.getAction() != null) {
                        navigationButton.getAction().run();
                    }
                    View.OnClickListener onClickListener = this.buttonOnClick;
                    if (onClickListener != null) {
                        onClickListener.onClick(navigationButton);
                    }
                }
                this.oldClick = navigationButton;
                this.lastClickView = findViewById;
            }
            navigationButton.isAdd();
        }
    }

    private void initImmButtons(INavigationShow iNavigationShow, final Activity activity) {
        int i2;
        removeAllViews();
        if (activity != null) {
            i2 = Math.min(DPIUtil.getAppWidth(activity), DPIUtil.getAppHeight(activity)) / this.buttons.size();
            RadioStateDrawable.screen_width = DPIUtil.getAppWidth(activity);
        } else {
            i2 = 0;
        }
        if (i2 == 0 || RadioStateDrawable.screen_width == 0 || activity == null) {
            if (this.context == null) {
                this.context = JdSdk.getInstance().getApplicationContext();
            }
            i2 = Math.min(DpiUtil.getWidth(this.context), DpiUtil.getHeight(this.context)) / this.buttons.size();
            RadioStateDrawable.screen_width = DpiUtil.getWidth(this.context);
        }
        RadioStateDrawable.width = i2;
        boolean isShowAnim = isShowAnim();
        if (Log.D) {
            Log.d("NavigationGroup", "showAnim=" + isShowAnim);
        }
        for (int i3 = 0; i3 < this.buttons.size(); i3++) {
            final NavigationButton navigationButton = this.buttons.get(i3);
            int navigationId = navigationButton.getNavigationId();
            navigationButton.isShowAnim(isShowAnim);
            navigationButton.setId(navigationId);
            navigationButton.setPadding(0, 0, 0, 0);
            if (iNavigationShow != null) {
                iNavigationShow.show(navigationButton);
            }
            if (navigationButton.bigIconTag) {
                this.layoutParams = new LinearLayout.LayoutParams(i2, getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_big_icon_height));
            } else {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_icon_height));
                this.layoutParams = layoutParams;
                layoutParams.gravity = 80;
            }
            navigationButton.setClipChildren(false);
            navigationButton.setLayoutParams(this.layoutParams);
            final View findViewById = navigationButton.findViewById(R.id.navigation_click_part);
            findViewById.setId(navigationId);
            findViewById.setSelected(false);
            findViewById.setContentDescription(navigationButton.getLabel());
            findViewById.setOnClickListener(this);
            findViewById.setOnTouchListener(this.onTouchListener);
            ViewCompat.setAccessibilityDelegate(findViewById, new AccessibilityDelegateCompat() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationGroup.4
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setSelected(view.isSelected());
                    accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View view, int i4, Bundle bundle) {
                    if (i4 == 16) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "performAccessibilityAction-id=" + view.getId() + " oldPage=" + NavigationBase.getInstance().oldPage);
                        }
                        if (view.getId() == NavigationBase.getInstance().oldPage) {
                            ((DefaultRouterBuilder) JDRouter.to(JDNavigationModule.TAG, "refreshNavigationPage").putInt(CustomThemeConstance.NAVI_NAVIGATION_ID, view.getId())).jump(activity);
                        }
                    }
                    return super.performAccessibilityAction(view, i4, bundle);
                }
            });
            StateController stateController = navigationButton.getStateController();
            if (stateController != null) {
                stateController.setNumListener(new IRefreshNumListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationGroup.5
                    @Override // com.jingdong.common.unification.navigationbar.newbar.IRefreshNumListener
                    public void refreshNavigationNum(String str) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "refreshNavigationNum_naviClick=" + findViewById + " button.getLabel()=" + navigationButton.getLabel() + " num=" + str);
                        }
                        if (findViewById == null || TextUtils.isEmpty(str)) {
                            return;
                        }
                        findViewById.setContentDescription(navigationButton.getLabel() + str);
                    }
                });
                stateController.setLabelListener(new IRefreshLabelListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationGroup.6
                    @Override // com.jingdong.common.unification.navigationbar.newbar.IRefreshLabelListener
                    public void refreshNavigationLabel(String str) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "refreshNavigationLabel_naviClick=" + findViewById + " button.getLabel()=" + navigationButton.getLabel() + " label=" + str);
                        }
                        if (findViewById == null || TextUtils.isEmpty(str)) {
                            return;
                        }
                        findViewById.setContentDescription(navigationButton.getLabel() + str);
                    }
                });
            }
            addView(navigationButton, i3);
            navigationButton.setDefault(false);
            if (this.checkId == navigationId) {
                navigationButton.setClick(true);
                findViewById.setSelected(true);
                NavigationButton navigationButton2 = this.oldClick;
                if (navigationButton2 == null || navigationButton2.getNavigationId() != navigationButton.getNavigationId()) {
                    if (navigationButton.getAction() != null) {
                        navigationButton.getAction().run();
                    }
                    View.OnClickListener onClickListener = this.buttonOnClick;
                    if (onClickListener != null) {
                        onClickListener.onClick(navigationButton);
                    }
                }
                this.oldClick = navigationButton;
                this.lastClickView = findViewById;
            }
            navigationButton.isAdd();
        }
    }

    private boolean isShowAnim() {
        List<NavigationButton> list = this.buttons;
        if (list != null) {
            for (NavigationButton navigationButton : list) {
                if (Log.D) {
                    Log.d("NavigationGroup", "isElderMode=" + JDElderModeUtils.isElderMode());
                }
                if (JDElderModeUtils.isElderMode()) {
                    ResourceItems resourceItems = navigationButton.getResourceItems();
                    if (resourceItems == null || TextUtils.isEmpty(resourceItems.getLocalResource()) || !resourceItems.getLocalResource().endsWith(FileService.CACHE_EXT_NAME_JSON)) {
                        return false;
                    }
                } else {
                    NavigationInfo navigationInfo = navigationButton.getNavigationInfo();
                    if (navigationInfo == null || TextUtils.isEmpty(navigationInfo.lottiePath) || !navigationInfo.lottiePath.endsWith(FileService.CACHE_EXT_NAME_JSON)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public int getCheckId() {
        return this.checkId;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(2, 1);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (OKLog.D) {
            OKLog.d("NavigationGroup", "onClick-delay " + (currentTimeMillis - this.clickTime));
        }
        long j2 = currentTimeMillis - this.clickTime;
        if (j2 < DELAY_TIME) {
            if (j2 < 0) {
                this.clickTime = currentTimeMillis;
                return;
            }
            return;
        }
        this.clickTime = currentTimeMillis;
        NavigationBase.getInstance().mCurrentIndex = view.getId();
        int id = view.getId();
        this.checkId = id;
        NavigationButton navigationButton = this.oldClick;
        if (navigationButton == null || id != navigationButton.getId()) {
            View view2 = this.lastClickView;
            if (view2 != null) {
                view2.setSelected(false);
            }
            view.setSelected(true);
            this.lastClickView = view;
            setCheck(this.checkId, true);
            Context context = this.context;
            if (context != null) {
                JDRouter.to(context, "router://JDNavigationModule/showNavigationEffectWhenClick").open();
            }
        }
    }

    public void setButtonOnClickListner(View.OnClickListener onClickListener) {
        this.buttonOnClick = onClickListener;
    }

    public void setCheck(int i2, boolean z) {
        setCheck(i2, z, true);
    }

    public void setImmNavigationButton(List<NavigationButton> list, INavigationShow iNavigationShow, Activity activity) {
        this.buttons = list;
        initImmButtons(iNavigationShow, activity);
    }

    public void setNavigationButton(List<NavigationButton> list, INavigationShow iNavigationShow, Activity activity) {
        this.buttons = list;
        initButtons(iNavigationShow, activity);
    }

    public void setOnButtonTouch(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public void setOnlyCheckState(int i2) {
        NavigationButton navigationButton = this.oldClick;
        if (navigationButton == null || navigationButton.getNavigationId() != i2) {
            this.checkId = i2;
            NavigationButton navigationButton2 = null;
            for (int i3 = 0; i3 < this.buttons.size(); i3++) {
                if (i2 == this.buttons.get(i3).getNavigationId()) {
                    navigationButton2 = this.buttons.get(i3);
                }
            }
            NavigationButton navigationButton3 = this.oldClick;
            if (navigationButton3 != null) {
                if (navigationButton3.isNeedChangeDefaultIcon()) {
                    this.oldClick.setDefault(false);
                } else {
                    this.oldClick.setDefault(true);
                }
            }
            if (navigationButton2 != null) {
                navigationButton2.setClick(true);
                this.oldClick = navigationButton2;
                View.OnClickListener onClickListener = this.buttonOnClick;
                if (onClickListener != null) {
                    onClickListener.onClick(navigationButton2);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setCheck(int i2, boolean z, boolean z2) {
        boolean z3;
        NavigationButton navigationButton;
        NavigationButton navigationButton2;
        int i3;
        NavigationButton navigationButton3;
        NavigationConfig navigationConfig;
        boolean z4;
        try {
            z4 = NavigationBase.getInstance().getBubbleState() == 1;
            NavigationConstants.BUBBLE_STUTE_BEFOR = z4;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("NavigationGroup", "NavigationGroup==" + e2);
            }
        }
        if (z2 && z4) {
            NavigationBase.getInstance().hideBubble(false);
            z3 = true;
            navigationButton = this.oldClick;
            if (navigationButton == null && navigationButton.getNavigationId() == i2) {
                return;
            }
            NavigationBase.getInstance().setIsJumpFromClick(i2, z);
            this.checkId = i2;
            navigationButton2 = null;
            for (i3 = 0; i3 < this.buttons.size(); i3++) {
                NavigationButton navigationButton4 = this.buttons.get(i3);
                if (i2 == navigationButton4.getNavigationId()) {
                    navigationButton2 = this.buttons.get(i3);
                } else {
                    if (Log.D) {
                        Log.d("navigation-default", i3 + "   buttonState:" + navigationButton4.getButtonState() + "  canHideIcon:" + z3);
                    }
                    if (z3 && navigationButton4.getButtonState()) {
                        if (Log.D) {
                            Log.d("navigation-default", "\u6267\u884cdefault");
                        }
                        navigationButton4.setDefault(false);
                    }
                }
            }
            navigationButton3 = this.oldClick;
            if (navigationButton3 != null) {
                if (navigationButton3.isNeedChangeDefaultIcon()) {
                    this.oldClick.setDefault(false);
                } else {
                    this.oldClick.setDefault(true);
                }
            }
            if (navigationButton2 == null) {
                NavigationInfo navigationInfo = navigationButton2.getNavigationInfo();
                TabShowNew tabShowNew = navigationButton2.getTabShowNew();
                if (navigationInfo != null) {
                    StateController stateController = navigationButton2.getStateController();
                    if (stateController != null) {
                        navigationInfo.angleLabel = stateController.getButtonLabel();
                    } else {
                        navigationInfo.angleLabel = "";
                    }
                    navigationInfo.hasAngle = !TextUtils.isEmpty(navigationInfo.angleLabel);
                    if (tabShowNew != null) {
                        navigationInfo.hasRedPoint = (tabShowNew.getIsShowRedPoint() != null && tabShowNew.getIsShowRedPoint().booleanValue()) || (tabShowNew.getIsShowNew() != null && tabShowNew.getIsShowNew().booleanValue());
                    } else {
                        navigationInfo.hasRedPoint = false;
                    }
                    navigationInfo.hasMarketPic = navigationButton2.getButtonState();
                }
                navigationButton2.setClick(true);
                try {
                    if (NavigationBase.getInstance().navigationConfigList == null || NavigationBase.getInstance().navigationConfigList.size() <= 0) {
                        navigationConfig = null;
                    } else {
                        navigationConfig = null;
                        for (NavigationConfig navigationConfig2 : NavigationBase.getInstance().navigationConfigList) {
                            if (navigationConfig2 != null && i2 == navigationConfig2.getNavigationId()) {
                                navigationConfig = navigationConfig2;
                            }
                        }
                    }
                    if (OKLog.D) {
                        OKLog.d("NavigationGroup", "tempConfig=" + navigationConfig);
                    }
                    if (tabShowNew != null && tabShowNew.getIsShowRedPoint() != null && tabShowNew.getIsShowRedPoint().booleanValue()) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "setCheck-\u7ea2\u70b9\u6d88\u5931");
                        }
                        tabShowNew.setIsShowRedPoint(Boolean.FALSE);
                        if (navigationConfig != null) {
                            navigationConfig.setShowRedPoint(false);
                        }
                    }
                    StateController stateController2 = navigationButton2.getStateController();
                    if (stateController2 != null && !TextUtils.isEmpty(stateController2.getButtonLabel())) {
                        if (OKLog.D) {
                            OKLog.d("NavigationGroup", "setCheck-\u89d2\u6807\u6d88\u5931");
                        }
                        stateController2.setButtonLabel("");
                        if (navigationConfig != null) {
                            navigationConfig.setLabel("");
                        }
                    }
                    stateController2.setNavigationParam(null);
                    if (navigationConfig != null) {
                        navigationConfig.setNewIconState(false);
                    }
                    if (OKLog.D) {
                        OKLog.d("NavigationGroup", "navigationConfigList=" + NavigationBase.getInstance().navigationConfigList);
                    }
                } catch (Exception e3) {
                    if (OKLog.E) {
                        OKLog.e("NavigationGroup", "setCheck-\u70b9\u51fbtab\uff0c\u7ea2\u70b9\u548c\u6587\u5b57\u89d2\u6807\u6d88\u5931=" + e3);
                    }
                }
                if (navigationButton2.getAction() != null) {
                    navigationButton2.getAction().run();
                }
                this.oldClick = navigationButton2;
                View.OnClickListener onClickListener = this.buttonOnClick;
                if (onClickListener != null) {
                    onClickListener.onClick(navigationButton2);
                    return;
                }
                return;
            }
            return;
        }
        z3 = false;
        navigationButton = this.oldClick;
        if (navigationButton == null) {
        }
        NavigationBase.getInstance().setIsJumpFromClick(i2, z);
        this.checkId = i2;
        navigationButton2 = null;
        while (i3 < this.buttons.size()) {
        }
        navigationButton3 = this.oldClick;
        if (navigationButton3 != null) {
        }
        if (navigationButton2 == null) {
        }
    }

    public NavigationGroup(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.checkId = NavigationBase.getInstance().mCurrentIndex;
        this.clickTime = 0L;
        this.context = context;
    }

    public NavigationGroup(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.checkId = NavigationBase.getInstance().mCurrentIndex;
        this.clickTime = 0L;
        this.context = context;
    }

    @TargetApi(21)
    public NavigationGroup(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.checkId = NavigationBase.getInstance().mCurrentIndex;
        this.clickTime = 0L;
        this.context = context;
    }
}
