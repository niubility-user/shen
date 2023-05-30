package com.jingdong.app.mall.navigationbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.framework.json.JDJSONObject;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.inter.OnSkinElementsChangeListener;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.navigationbar.entity.NavigationBubbleEntity;
import com.jingdong.app.mall.privacy.JDPrivacyAgreeEvent;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.gray.GrayModelListener;
import com.jingdong.common.gray.JDGrayModelUtils;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.customtheme.inter.OnThemeChangeListener;
import com.jingdong.common.unification.navigationbar.INavigationPage;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConfig;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.navigationbar.NavigationSkinChangeManager;
import com.jingdong.common.unification.navigationbar.newbar.INavigationShow;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.unification.navigationbar.newbar.NavigationGroup;
import com.jingdong.common.unification.navigationbar.newbar.StateController;
import com.jingdong.common.unification.navigationbar.newbar.TabShowNew;
import com.jingdong.common.unification.navigationbar.theme.INavigationChangeState;
import com.jingdong.common.unification.navigationbar.theme.INavigationTheme;
import com.jingdong.common.unification.navigationbar.theme.NavThemeEntity;
import com.jingdong.common.unification.navigationbar.theme.NavigationThemeController;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.bmode.util.JDBModeManager;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.eldermode.util.OnElderModeChangeListener;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/* loaded from: classes4.dex */
public class JDNavigationFragment extends BaseFragment implements INavigationTheme, DeepDarkChangeManager.OnUIModeChangeListener, OnElderModeChangeListener, OnSkinElementsChangeListener, GrayModelListener {

    /* renamed from: g */
    protected NavigationGroup f11297g;

    /* renamed from: h */
    protected NavigationGroup f11298h;

    /* renamed from: m */
    private ImageView f11303m;

    /* renamed from: n */
    private ImageView f11304n;
    private INavigationPage o;
    private BroadcastReceiver p;

    /* renamed from: i */
    private List<NavigationButton> f11299i = new ArrayList();

    /* renamed from: j */
    private List<NavigationButton> f11300j = new ArrayList();

    /* renamed from: k */
    private Stack<Integer> f11301k = new Stack<>();

    /* renamed from: l */
    private int f11302l = -1;
    private boolean q = false;
    private INavigationShow r = new m();
    private INavigationShow s = new n();
    private View.OnClickListener t = new a(this);
    private View.OnTouchListener u = new b();

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a(JDNavigationFragment jDNavigationFragment) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Log.D) {
                Log.d("JDNavigationFragment", "Click Nav ID = " + view.getId());
            }
            if (NavigationBase.getInstance().getBubbleState() == 1) {
                NavigationBase.getInstance().handleBubble(false);
            }
            com.jingdong.app.mall.navigationbar.d.a().c(view.getId(), false);
            com.jingdong.app.mall.navigationbar.e.l().o();
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnTouchListener {
        b() {
            JDNavigationFragment.this = r1;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1 && view.getId() == NavigationBase.getInstance().oldPage) {
                JDNavigationFragment jDNavigationFragment = JDNavigationFragment.this;
                jDNavigationFragment.o = ((MainFrameActivity) jDNavigationFragment.thisActivity).getTabFragment();
                if (JDNavigationFragment.this.o != null) {
                    JDNavigationFragment.this.o.clickNavigation(NavigationBase.getInstance().oldPage, view.getId(), NavigationBase.getInstance().getmUrl(view.getId()));
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes4.dex */
    class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f11306g;

        c(int i2) {
            JDNavigationFragment.this = r1;
            this.f11306g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (NavigationBase.getInstance().navigationCurrentMode == 2) {
                    return;
                }
                JDNavigationFragment.this.v(false, true);
                JDNavigationFragment.this.s(false);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("Navigation", "onUIModeChanged-i=" + this.f11306g + LangUtils.SINGLE_SPACE + e2.toString());
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f11308g;

        d(int i2) {
            JDNavigationFragment.this = r1;
            this.f11308g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDNavigationFragment.this.q(false, this.f11308g == 1 ? "1" : "0", true);
            EventBus.getDefault().post(new BaseEvent(NavigationConstants.NAVIGATION_MODE_CHANGE, this.f11308g != 1 ? "0" : "1"));
            try {
                MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
                if (b != null && this.f11308g == 1) {
                    if (OKLog.D) {
                        OKLog.d("JDNavigationFragment", "onElderModeChanged--setBubbleViewGone");
                    }
                    b.setBubbleViewGone();
                    b.setNewBubbleViewGone();
                }
                JDNavigationFragment.this.s(false);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("JDNavigationFragment", e2);
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    class e implements Runnable {
        e() {
            JDNavigationFragment.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OKLog.D) {
                OKLog.d("JDNavigationFragment", "\u5e95\u90e8\u5bfc\u822a\u6362\u80a4PaaS\u5316\u76d1\u542c" + Thread.currentThread().getName() + " isElderMode=" + JDElderModeUtils.isElderMode());
            }
            JDNavigationFragment.this.q(false, "1", true);
        }
    }

    /* loaded from: classes4.dex */
    class f implements Runnable {
        f() {
            JDNavigationFragment.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (JDNavigationFragment.this.f11297g != null) {
                JDGrayModelUtils.getInstance().setViewGray(JDNavigationFragment.this.f11297g, JDGrayModelUtils.getInstance().isGrayModel());
            }
            if (JDNavigationFragment.this.f11303m != null) {
                JDGrayModelUtils.getInstance().setViewGray(JDNavigationFragment.this.f11303m, JDGrayModelUtils.getInstance().isGrayModel());
            }
        }
    }

    /* loaded from: classes4.dex */
    class g implements View.OnTouchListener {
        g(JDNavigationFragment jDNavigationFragment) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (Log.D) {
                Log.d("JDNavigationFragment", "radio " + motionEvent.getAction());
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes4.dex */
    public class h implements OnThemeChangeListener {

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
                h.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (JDElderModeUtils.isElderMode()) {
                        return;
                    }
                    String curVersionMoudle = UnCustomThemeHelper.getInstance().getCurVersionMoudle();
                    String str = NavigationBase.getInstance().navigationCurrentMode + "";
                    if (Log.D) {
                        Log.d("JDNavigationFragment", "themeChange-versionMoudle=" + curVersionMoudle + " navigationCurrentMode=" + str);
                    }
                    if (TextUtils.isEmpty(curVersionMoudle) || TextUtils.isEmpty(str) || curVersionMoudle.equals(str)) {
                        if (Log.D) {
                            Log.d("JDNavigationFragment", "\u5e95\u90e8\u5bfc\u822a\u81ea\u5b9a\u4e49\u6362\u80a4\u76d1\u542c" + Thread.currentThread().getName() + "_isElderMode=" + JDElderModeUtils.isElderMode());
                        }
                        String config = JDMobileConfig.getInstance().getConfig("navigation", "refreshNaviAuto", "value");
                        if (Log.D) {
                            Log.d("JDNavigationFragment", "refreshNaviAuto=" + config);
                        }
                        if ("1".equals(config) && com.jingdong.app.mall.navigationbar.c.G().U()) {
                            JDNavigationFragment.this.v(true, true);
                        } else {
                            JDNavigationFragment.this.v(false, true);
                        }
                        JDNavigationFragment.this.s(false);
                    }
                } catch (Exception e2) {
                    if (Log.E) {
                        Log.e("JDNavigationFragment", "setOnThemeChangeListener=" + e2);
                    }
                }
            }
        }

        h() {
            JDNavigationFragment.this = r1;
        }

        @Override // com.jingdong.common.unification.customtheme.inter.OnThemeChangeListener
        public void themeChange() {
            BaseActivity baseActivity = JDNavigationFragment.this.thisActivity;
            if (baseActivity != null) {
                baseActivity.getHandler().post(new a());
            }
        }
    }

    /* loaded from: classes4.dex */
    public class i extends BroadcastReceiver {

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
                i.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                int l2 = JDNavigationFragment.this.l();
                String currentMode = JDBModeUtils.getCurrentMode();
                if (Log.D) {
                    Log.d("JDNavigationFragment", "onModeChange-\u5728\u7b2c\u51e0\u4e2a\u5751\u4f4d=" + (l2 + 1) + " currentMode=" + currentMode + " navigationCurrentMode=" + NavigationBase.getInstance().navigationCurrentMode);
                }
                if (NavigationBase.getInstance().navigationCurrentMode == 1 || !(l2 == 1 || l2 == 2)) {
                    JDNavigationFragment.this.q(false, currentMode, true);
                    EventBus.getDefault().post(new BaseEvent(NavigationConstants.NAVIGATION_MODE_CHANGE, currentMode));
                    MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
                    if (b != null) {
                        b.setBubbleViewGone();
                        b.setNewBubbleViewGone();
                    }
                    String curVersionMoudle = UnCustomThemeHelper.getInstance().getCurVersionMoudle();
                    if (Log.D) {
                        Log.d("JDNavigationFragment", "onModeChange-versionMoudle=" + curVersionMoudle);
                    }
                    if (!TextUtils.isEmpty(currentMode) && !TextUtils.isEmpty(curVersionMoudle) && !currentMode.equals(curVersionMoudle)) {
                        ThemeTitleDataController.getInstance().getThemeTitleData(0);
                    }
                    JDNavigationFragment.this.s(false);
                }
            }
        }

        i() {
            JDNavigationFragment.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BaseActivity baseActivity;
            try {
                HashMap hashMap = (HashMap) intent.getSerializableExtra(JDBModeManager.KEY_EXTRA);
                String str = hashMap != null ? (String) hashMap.get(JDBModeManager.KEY_EXTRA_MAP_PARAM_TO_FINAL) : "";
                if (Log.D) {
                    Log.d("JDNavigationFragment", "\u5e95\u90e8\u5bfc\u822a\u7248\u672c\u6a21\u5f0f\u53d8\u5316\u5e7f\u64ad_" + Thread.currentThread().getName() + "_extra=" + hashMap + "_finalParam=" + str);
                }
                if (JDBModeManager.ACTION_MODE_SWITCH.equals(intent.getAction())) {
                    if (!"1".equals(str) || (baseActivity = JDNavigationFragment.this.thisActivity) == null) {
                        return;
                    }
                    baseActivity.getHandler().post(new a());
                } else if (JDBModeManager.ACTION_MODE_SWITCH_SPECIAL.equals(intent.getAction())) {
                    JDNavigationFragment.this.q = "0".equals(str);
                }
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e("JDNavigationFragment", "modeBroadcastReceiver=" + e2);
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    public class j implements Runnable {
        j() {
            JDNavigationFragment.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDNavigationFragment jDNavigationFragment = JDNavigationFragment.this;
            com.jingdong.app.mall.navigationbar.c G = com.jingdong.app.mall.navigationbar.c.G();
            JDNavigationFragment jDNavigationFragment2 = JDNavigationFragment.this;
            jDNavigationFragment.f11299i = G.l(jDNavigationFragment2.thisActivity, jDNavigationFragment2.f11303m, NavigationBase.getInstance().navigationCurrentMode + "");
            JDNavigationFragment.this.k(NavigationBase.getInstance().mCurrentIndex);
            if (Log.D) {
                Log.d("JDNavigationFragment", "refreshNavigation=====" + CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ORDER_NATIVE, ""));
            }
            ThemeTitleDataController.getInstance().getThemeTitleData(0);
            JDNavigationFragment.this.s(false);
        }
    }

    /* loaded from: classes4.dex */
    public class k implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f11315g;

        k(boolean z) {
            JDNavigationFragment.this = r1;
            this.f11315g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDNavigationFragment.this.v(this.f11315g, true);
            JDNavigationFragment.this.s(false);
        }
    }

    /* loaded from: classes4.dex */
    public class l implements Runnable {

        /* renamed from: g */
        final /* synthetic */ NavigationConfig f11317g;

        /* renamed from: h */
        final /* synthetic */ NavigationButton f11318h;

        /* renamed from: i */
        final /* synthetic */ NavigationBubbleEntity f11319i;

        /* renamed from: j */
        final /* synthetic */ boolean f11320j;

        l(JDNavigationFragment jDNavigationFragment, NavigationConfig navigationConfig, NavigationButton navigationButton, NavigationBubbleEntity navigationBubbleEntity, boolean z) {
            this.f11317g = navigationConfig;
            this.f11318h = navigationButton;
            this.f11319i = navigationBubbleEntity;
            this.f11320j = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OKLog.D) {
                OKLog.d("JDNavigationFragment", "recoverIconAbnormity-navigationConfig=" + this.f11317g + "-" + Thread.currentThread() + " navigationCurrentMode=" + NavigationBase.getInstance().navigationCurrentMode);
            }
            if (NavigationBase.getInstance().navigationCurrentMode != this.f11317g.getCurrentMode()) {
                return;
            }
            TabShowNew tabShowNew = this.f11318h.getTabShowNew();
            if (tabShowNew != null) {
                tabShowNew.setIsShowRedPoint(Boolean.valueOf(this.f11317g.isShowRedPoint()));
            }
            if (this.f11317g.isNewIconState()) {
                if ((UnCustomThemeHelper.getInstance().getSkinType() != 0 && UnCustomThemeHelper.getInstance().getSkinType() != 2) || this.f11319i.isIconAndBubbleCom()) {
                    return;
                }
                int i2 = NavigationBase.getInstance().navigationCurrentMode;
                NavigationBubbleEntity navigationBubbleEntity = this.f11319i;
                if (i2 != navigationBubbleEntity.currentMode) {
                    return;
                }
                NavigationBarUtil.changeView(navigationBubbleEntity, this.f11318h);
            } else if (this.f11320j) {
                if (this.f11318h.getNavigationId() == NavigationBase.getInstance().mCurrentIndex) {
                    this.f11318h.setClick(true);
                } else {
                    this.f11318h.setDefault(false);
                }
            }
            StateController stateController = this.f11318h.getStateController();
            if (stateController != null) {
                stateController.lablePosition = this.f11317g.getFunctionId();
                stateController.setButtonLabel(this.f11317g.getLabel(), this.f11317g.getBucketType());
            }
        }
    }

    /* loaded from: classes4.dex */
    public class m implements INavigationShow {
        m() {
            JDNavigationFragment.this = r1;
        }

        @Override // com.jingdong.common.unification.navigationbar.newbar.INavigationShow
        public void show(NavigationButton navigationButton) {
            int id = navigationButton.getId();
            if (Log.D) {
                Log.d("navigationDraw", "id  " + id);
            }
            if (id == 2) {
                MainFrameActivity.setFaxianShowNew(navigationButton.getTabShowNew());
            }
            if (id == 3) {
                MainFrameActivity.setCarStateController(navigationButton.getStateController());
                MainFrameActivity.validatCartIcon();
            }
            if (id == 4) {
                MainFrameActivity.setTabShowNew(navigationButton.getTabShowNew());
            }
            JDNavigationFragment.this.t(navigationButton);
        }
    }

    /* loaded from: classes4.dex */
    public class n implements INavigationShow {
        n() {
            JDNavigationFragment.this = r1;
        }

        @Override // com.jingdong.common.unification.navigationbar.newbar.INavigationShow
        public void show(NavigationButton navigationButton) {
            int id = navigationButton.getId();
            if (id == 2) {
                MainFrameActivity.setImmFaXianShowNew(navigationButton.getTabShowNew());
            }
            if (id == 3) {
                MainFrameActivity.setImmCarStateController(navigationButton.getStateController());
                MainFrameActivity.validatCartIcon();
            }
            if (id == 4) {
                MainFrameActivity.setImmTabShowNew(navigationButton.getTabShowNew());
            }
            JDNavigationFragment.this.t(navigationButton);
        }
    }

    /* loaded from: classes4.dex */
    public class o implements Runnable {

        /* renamed from: g */
        final /* synthetic */ NavigationConfig f11321g;

        /* renamed from: h */
        final /* synthetic */ NavigationButton f11322h;

        /* renamed from: i */
        final /* synthetic */ NavigationBubbleEntity f11323i;

        o(JDNavigationFragment jDNavigationFragment, NavigationConfig navigationConfig, NavigationButton navigationButton, NavigationBubbleEntity navigationBubbleEntity) {
            this.f11321g = navigationConfig;
            this.f11322h = navigationButton;
            this.f11323i = navigationBubbleEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            StateController stateController;
            if (OKLog.D) {
                OKLog.d("JDNavigationFragment", "recoverRedPointOrLabelInfo-navigationConfig=" + this.f11321g + "-" + Thread.currentThread() + " navigationCurrentMode=" + NavigationBase.getInstance().navigationCurrentMode);
            }
            if (NavigationBase.getInstance().navigationCurrentMode != this.f11321g.getCurrentMode()) {
                return;
            }
            if (this.f11321g.isShowRedPoint()) {
                TabShowNew tabShowNew = this.f11322h.getTabShowNew();
                if (tabShowNew != null) {
                    tabShowNew.setIsShowRedPoint(Boolean.valueOf(this.f11321g.isShowRedPoint()));
                }
            } else if (TextUtils.isEmpty(this.f11321g.getLabel())) {
            } else {
                NavigationBubbleEntity navigationBubbleEntity = this.f11323i;
                if ((navigationBubbleEntity != null && TextUtils.equals(navigationBubbleEntity.position, this.f11321g.getFunctionId()) && this.f11323i.isIconAndAngleCom() && ((UnCustomThemeHelper.getInstance().getSkinType() != 0 && UnCustomThemeHelper.getInstance().getSkinType() != 2) || TextUtils.isEmpty(this.f11323i.iconUrl) || this.f11323i.iconUrl.endsWith(FileService.CACHE_EXT_NAME_JSON))) || (stateController = this.f11322h.getStateController()) == null) {
                    return;
                }
                stateController.lablePosition = this.f11321g.getFunctionId();
                stateController.setButtonLabel(this.f11321g.getLabel(), this.f11321g.getBucketType());
            }
        }
    }

    private NavigationButton m(String str) {
        List<NavigationButton> list;
        if (TextUtils.isEmpty(str) || (list = this.f11300j) == null || list.size() <= 0) {
            return null;
        }
        NavigationButton navigationButton = null;
        for (int i2 = 0; i2 < this.f11300j.size(); i2++) {
            if (com.jingdong.app.mall.navigationbar.c.G().M(str, null) == this.f11300j.get(i2).getNavigationId()) {
                navigationButton = this.f11300j.get(i2);
            }
        }
        return navigationButton;
    }

    public static JDNavigationFragment r(int i2) {
        JDNavigationFragment jDNavigationFragment = new JDNavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("lastIndex", i2);
        Log.e("TestIndex", "navigation==newInstance>" + i2);
        jDNavigationFragment.setArguments(bundle);
        return jDNavigationFragment;
    }

    public void s(boolean z) {
        NavigationButton currentButtonByFunctionId;
        BaseActivity baseActivity;
        try {
            NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
            if (L == null) {
                return;
            }
            if (z) {
                currentButtonByFunctionId = m(L.position);
            } else {
                currentButtonByFunctionId = NavigationBarUtil.getCurrentButtonByFunctionId(L.position);
            }
            NavigationButton navigationButton = currentButtonByFunctionId;
            if (navigationButton == null || NavigationBase.getInstance().navigationConfigList == null || NavigationBase.getInstance().navigationConfigList.size() <= 0) {
                return;
            }
            for (NavigationConfig navigationConfig : NavigationBase.getInstance().navigationConfigList) {
                if (navigationConfig != null && navigationConfig.getNavigationId() == navigationButton.getNavigationId() && (baseActivity = this.thisActivity) != null && baseActivity.getHandler() != null) {
                    this.thisActivity.getHandler().post(new l(this, navigationConfig, navigationButton, L, z));
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDNavigationFragment", e2);
            }
        }
    }

    public void t(NavigationButton navigationButton) {
        BaseActivity baseActivity;
        try {
            NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
            if (navigationButton == null || NavigationBase.getInstance().navigationConfigList == null || NavigationBase.getInstance().navigationConfigList.size() <= 0) {
                return;
            }
            for (NavigationConfig navigationConfig : NavigationBase.getInstance().navigationConfigList) {
                if (navigationConfig != null && navigationConfig.getNavigationId() == navigationButton.getNavigationId() && (baseActivity = this.thisActivity) != null && baseActivity.getHandler() != null) {
                    this.thisActivity.getHandler().post(new o(this, navigationConfig, navigationButton, L));
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDNavigationFragment", e2);
            }
        }
    }

    public void A(boolean z) {
        try {
            if (OKLog.D) {
                OKLog.d("JDNavigationFragment", "isUseImmSkin=" + z + " index=" + NavigationBase.getInstance().mCurrentIndex);
            }
            if (z) {
                if (!o()) {
                    n();
                }
                if (!o() || JDElderModeUtils.isElderMode()) {
                    return;
                }
                NavigationGroup navigationGroup = this.f11297g;
                if (navigationGroup != null && navigationGroup.getVisibility() == 0) {
                    this.f11297g.setVisibility(8);
                }
                NavigationGroup navigationGroup2 = this.f11298h;
                if (navigationGroup2 != null && navigationGroup2.getVisibility() == 8) {
                    this.f11298h.setVisibility(0);
                    s(true);
                    this.f11298h.setOnlyCheckState(NavigationBase.getInstance().mCurrentIndex);
                }
                ImageView imageView = this.f11303m;
                if (imageView != null && imageView.getVisibility() == 0) {
                    this.f11303m.setVisibility(8);
                }
                ImageView imageView2 = this.f11304n;
                if (imageView2 == null || imageView2.getVisibility() != 8) {
                    return;
                }
                this.f11304n.setVisibility(0);
                return;
            }
            NavigationGroup navigationGroup3 = this.f11297g;
            if (navigationGroup3 != null && navigationGroup3.getVisibility() == 8) {
                this.f11297g.setVisibility(0);
                s(false);
                this.f11297g.setOnlyCheckState(NavigationBase.getInstance().mCurrentIndex);
            }
            NavigationGroup navigationGroup4 = this.f11298h;
            if (navigationGroup4 != null && navigationGroup4.getVisibility() == 0) {
                this.f11298h.setVisibility(8);
            }
            ImageView imageView3 = this.f11304n;
            if (imageView3 != null && imageView3.getVisibility() == 0) {
                this.f11304n.setVisibility(8);
            }
            ImageView imageView4 = this.f11303m;
            if (imageView4 == null || imageView4.getVisibility() != 8) {
                return;
            }
            this.f11303m.setVisibility(0);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.theme.INavigationTheme
    public void changeTheme(List<NavThemeEntity> list, String str) {
        com.jingdong.app.mall.navigationbar.c.G().g(this.thisActivity, list, str);
    }

    @Override // com.jingdong.common.unification.navigationbar.theme.INavigationTheme
    public void changeToDefault() {
        v(false, false);
    }

    public void h(Integer num) {
        if (Log.D) {
            Log.d("JDNavigationFragment", "radioId -->> " + num);
        }
        if (com.jingdong.app.mall.navigationbar.c.G().S() && NavigationBase.getInstance().mCurrentIndex == num.intValue()) {
            com.jingdong.app.mall.navigationbar.c.p = true;
        }
        NavigationBase.getInstance().mCurrentIndex = num.intValue();
        if (this.f11297g.getCheckId() != num.intValue()) {
            this.f11302l = this.f11297g.getCheckId();
            this.f11297g.setCheck(num.intValue(), false);
            if (Log.D) {
                Log.d("JDNavigationFragment", "old -->> " + this.f11302l);
            }
        }
    }

    public void j() {
        this.f11301k.clear();
    }

    public synchronized void k(int i2) {
        this.f11297g.setOnButtonTouch(this.u);
        this.f11297g.setButtonOnClickListner(this.t);
        this.f11297g.setNavigationButton(this.f11299i, this.r, this.thisActivity);
        boolean z = true;
        if (NavigationBarUtil.isIconCom() && (UnCustomThemeHelper.getInstance().getSkinType() == 0 || UnCustomThemeHelper.getInstance().getSkinType() == 2)) {
            z = false;
        }
        this.f11297g.setCheck(i2, false, z);
    }

    public int l() {
        List<NavigationButton> list = NavigationBase.getInstance().buttons;
        if (list != null && list.size() > 0) {
            int i2 = NavigationBase.getInstance().mCurrentIndex;
            if (Log.D) {
                Log.d("JDNavigationFragment", "navigationButtons=" + list.size() + " navigationId=" + i2);
            }
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (i2 == list.get(i3).getNavigationId()) {
                    return i3;
                }
            }
        }
        return -1;
    }

    public synchronized void n() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("navigation", "immersiveNaviWithB", "value", "1");
            if (Log.D) {
                Log.d("JDNavigationFragment", "immNaviInit()-immNaviSwitch=" + config);
            }
            if ("1".equals(config) && !JDElderModeUtils.isElderMode()) {
                this.f11300j = com.jingdong.app.mall.navigationbar.c.G().n(this.thisActivity, this.f11304n, "2");
                if (o()) {
                    this.f11298h.setOnButtonTouch(this.u);
                    this.f11298h.setButtonOnClickListner(this.t);
                    this.f11298h.setImmNavigationButton(this.f11300j, this.s, this.thisActivity);
                } else {
                    ExceptionReporter.reportExceptionToBugly(new Exception("JDNavigationFragment_immButtons=" + this.f11300j));
                }
                NavigationGroup navigationGroup = this.f11298h;
                if (navigationGroup != null && navigationGroup.getVisibility() == 0) {
                    if (Log.D) {
                        Log.d("JDNavigationFragment", "immNaviInit()-index=" + NavigationBase.getInstance().mCurrentIndex);
                    }
                    this.f11298h.setOnlyCheckState(NavigationBase.getInstance().mCurrentIndex);
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDNavigationFragment", e2);
            }
        }
    }

    public boolean o() {
        List<NavigationButton> list = this.f11300j;
        return list != null && list.size() >= 5;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (Log.D) {
            Log.d("JDNavigationFragment", "onConfigurationChanged");
        }
        v(false, false);
        NavigationGroup navigationGroup = this.f11298h;
        if (navigationGroup == null || navigationGroup.getVisibility() != 0) {
            return;
        }
        this.f11298h.setOnlyCheckState(NavigationBase.getInstance().mCurrentIndex);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new JDPrivacyAgreeEvent();
        EventBus.getDefault().register(this);
        com.jingdong.app.mall.navigationbar.c.p = true;
        if (com.jingdong.app.mall.navigationbar.c.G().S()) {
            NavigationBase.getInstance().mCurrentIndex = 0;
        }
        if (getArguments() != null) {
            if (Log.D) {
                Log.d("JDNavigationFragment", "onCreate: mCurrentIndex1=" + NavigationBase.getInstance().mCurrentIndex);
            }
            Log.e("TestIndex", "navigation==before>" + NavigationBase.getInstance().mCurrentIndex);
            NavigationBase.getInstance().mCurrentIndex = getArguments().getInt("lastIndex");
            Log.e("TestIndex", "navigation==after>" + NavigationBase.getInstance().mCurrentIndex);
            if (Log.D) {
                Log.d("JDNavigationFragment", "onCreate: mCurrentIndex2=" + NavigationBase.getInstance().mCurrentIndex);
            }
        }
        if (Log.D) {
            Log.d("JDNavigationFragment", "onCreate: savedInstanceState=" + bundle);
        }
        this.needRemoveviewOnStop = false;
        String currentMode = JDBModeUtils.getCurrentMode();
        q(true, currentMode, false);
        NavigationThemeController.getInstance().theme = this;
        String str = "";
        if ("2".equals(currentMode)) {
            str = CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ORDER_NATIVE_B, "");
        } else if ("0".equals(currentMode)) {
            str = CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ORDER_NATIVE, "");
        }
        String navigationIds = UnCustomThemeHelper.getInstance().getNavigationIds();
        String navigationMtaParamValue = UnCustomThemeHelper.getInstance().getNavigationMtaParamValue();
        if (Log.D) {
            Log.d("JDNavigationFragment", "nativeOrder=" + str + " remoteOrder=" + navigationIds + " mtaParamValue=" + navigationMtaParamValue);
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("isRefresh", (Object) "0");
        if (!TextUtils.isEmpty(str) && str.equals(navigationIds) && !TextUtils.isEmpty(navigationMtaParamValue)) {
            JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", navigationMtaParamValue, "", "", "", jDJSONObject.toJSONString(), null);
        } else if ("2".equals(currentMode)) {
            JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", "Home_Discover_Game_Shopcart_MyJD", "", "", "", jDJSONObject.toJSONString(), null);
        } else if ("1".equals(currentMode)) {
            JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", "Home_Discover_Shopcart_MyJD", "", "", "", jDJSONObject.toJSONString(), null);
        } else {
            JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", "Home_New_Discover_Shopcart_MyJD", "", "", "", jDJSONObject.toJSONString(), null);
        }
        UnCustomThemeHelper.getInstance().setOnThemeChangeListener(new h());
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(this);
        JDElderModeUtils.addElderModeChangeListener(this);
        JDSkinSDK.getInstance().setNotifyUpdateListener(this);
        this.p = new i();
        if (this.thisActivity != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(JDBModeManager.ACTION_MODE_SWITCH);
            intentFilter.addAction(JDBModeManager.ACTION_MODE_SWITCH_SPECIAL);
            LocalBroadcastManager.getInstance(this.thisActivity).registerReceiver(this.p, intentFilter);
        }
        JDGrayModelUtils.getInstance().addListener(this);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        setIsUseBasePV(false);
        View inflate = layoutInflater.inflate(R.layout.app_jd_navigation_xml, (ViewGroup) null);
        NavigationGroup navigationGroup = (NavigationGroup) inflate.findViewById(R.id.bottomMenu);
        this.f11297g = navigationGroup;
        navigationGroup.setOnTouchListener(new g(this));
        this.f11298h = (NavigationGroup) inflate.findViewById(R.id.immBottomMenu);
        this.f11303m = (BottomCropImage) inflate.findViewById(R.id.navigation_bg);
        this.f11304n = (BottomCropImage) inflate.findViewById(R.id.navigation_imm_bg);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        UnCustomThemeHelper.getInstance().removeThemeChangeListener();
        DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(this);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override // com.jingdong.sdk.eldermode.util.OnElderModeChangeListener
    public void onElderModeChanged(int i2) {
        BaseActivity baseActivity;
        if (OKLog.D) {
            OKLog.d("Navigation", "onElderModeChanged-mode=" + i2 + " _" + Thread.currentThread().getName() + " needSkipCurrentModeSwitch=" + this.q);
        }
        if (this.q || (baseActivity = this.thisActivity) == null) {
            return;
        }
        baseActivity.getHandler().post(new d(i2));
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent == null) {
            return;
        }
        if (Log.D) {
            Log.d("JDNavigationFragment", baseEvent.getType());
        }
        if (baseEvent instanceof JDPrivacyAgreeEvent) {
            if (Log.D) {
                Log.d("JDNavigationFragment", "JDPrivacyAgreeEvent");
            }
            com.jingdong.app.mall.navigationbar.c.G().f();
            UnStatusBarTintUtil.setStatusBar4Base(getActivity(), 1);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.jingdong.common.gray.GrayModelListener
    public void onModelChanged() {
        if (OKLog.D) {
            OKLog.d("JDNavigationFragment", "\u5bfc\u822a\u4e00\u952e\u7f6e\u7070=" + JDGrayModelUtils.getInstance().isGrayModel());
        }
        try {
            BaseActivity baseActivity = this.thisActivity;
            if (baseActivity != null) {
                baseActivity.getHandler().post(new f());
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("JDNavigationFragment", e2);
            }
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
    public void onUIModeChanged(int i2) {
        if (OKLog.D) {
            OKLog.d("Navigation", "onUIModeChanged-i=" + i2 + LangUtils.SINGLE_SPACE + DeepDarkChangeManager.getInstance().getUIMode() + " navigationCurrentMode=" + NavigationBase.getInstance().navigationCurrentMode);
        }
        BaseActivity baseActivity = this.thisActivity;
        if (baseActivity != null) {
            baseActivity.getHandler().post(new c(i2));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public synchronized void q(boolean z, String str, boolean z2) {
        if (Log.D) {
            Log.d("JDNavigationFragment", "------navigationInit--------mode=" + str);
        }
        String navigationOrder = NavigationBase.getInstance().getNavigationOrder();
        this.f11299i = com.jingdong.app.mall.navigationbar.c.G().o(this.thisActivity, this.f11303m, z, str);
        if (Log.D) {
            Log.d("JDNavigationFragment", "navigationInit: mCurrentIndex3=" + NavigationBase.getInstance().mCurrentIndex);
        }
        if (Log.D) {
            Log.d("JDNavigationFragment", "navigationInit: button=" + this.f11299i.get(0).getNavigationInfo());
        }
        k(NavigationBase.getInstance().mCurrentIndex);
        String navigationOrder2 = NavigationBase.getInstance().getNavigationOrder();
        if (Log.D) {
            Log.d("JDNavigationFragment", "oldNaviOrder=" + navigationOrder + " nowNaviOrder=" + navigationOrder2);
        }
        if (!TextUtils.isEmpty(navigationOrder) && !TextUtils.isEmpty(navigationOrder2) && !navigationOrder.equals(navigationOrder2)) {
            String navigationIds = UnCustomThemeHelper.getInstance().getNavigationIds();
            String navigationMtaParamValue = UnCustomThemeHelper.getInstance().getNavigationMtaParamValue();
            if (Log.D) {
                Log.d("JDNavigationFragment", "remoteOrder=" + navigationIds + " mtaParamValue=" + navigationMtaParamValue);
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("isRefresh", (Object) (z2 ? "1" : "0"));
            if (!TextUtils.isEmpty(navigationIds) && navigationOrder2.equals(navigationIds) && !TextUtils.isEmpty(navigationMtaParamValue)) {
                JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", navigationMtaParamValue, "", "", "", jDJSONObject.toJSONString(), null);
            } else if ("2".equals(str)) {
                JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", "Home_Discover_Game_Shopcart_MyJD", "", "", "", jDJSONObject.toJSONString(), null);
            } else if ("1".equals(str)) {
                JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", "Home_Discover_Shopcart_MyJD", "", "", "", jDJSONObject.toJSONString(), null);
            } else {
                JDMtaUtils.sendExposureDataWithExt(this.thisActivity, "NavigationBar_ButtonsExpo", "Home_New_Discover_Shopcart_MyJD", "", "", "", jDJSONObject.toJSONString(), null);
            }
        }
        if ("2".equals(str)) {
            n();
        }
    }

    @Override // com.jd.skin.lib.inter.OnSkinElementsChangeListener
    public void skinChange() {
        BaseActivity baseActivity;
        if (!JDElderModeUtils.isElderMode() || (baseActivity = this.thisActivity) == null) {
            return;
        }
        baseActivity.getHandler().post(new e());
    }

    public void u(int i2) {
        NavigationGroup navigationGroup = this.f11297g;
        if (navigationGroup != null && navigationGroup.getVisibility() == 0) {
            if (Log.D) {
                Log.d("JDNavigationFragment", "bottomRadioGroup-reductionNavigation-index=" + i2);
            }
            this.f11297g.setOnlyCheckState(i2);
        }
        NavigationGroup navigationGroup2 = this.f11298h;
        if (navigationGroup2 == null || navigationGroup2.getVisibility() != 0) {
            return;
        }
        if (Log.D) {
            Log.d("JDNavigationFragment", "immNavigationGroup-reductionNavigation-index=" + i2);
        }
        this.f11298h.setOnlyCheckState(i2);
    }

    public synchronized void v(boolean z, boolean z2) {
        q(z, NavigationBase.getInstance().navigationCurrentMode + "", z2);
        NavigationSkinChangeManager.getInstance().notifyNavigationSkinChanged();
        int i2 = NavigationBase.getInstance().mCurrentIndex;
        if (i2 == 2 && !TextUtils.isEmpty(this.f11299i.get(i2).mUrl)) {
            z(i2);
        }
        com.jingdong.app.mall.navigationbar.d.a().c(i2, false);
    }

    public synchronized void w(List<NavigationButton> list, String str) {
        Drawable createFromPath;
        this.f11299i = list;
        if (list != null && list.size() == 5) {
            if (!TextUtils.isEmpty(str) && (createFromPath = Drawable.createFromPath(str)) != null) {
                this.f11303m.setImageDrawable(createFromPath);
            }
            k(NavigationBase.getInstance().mCurrentIndex);
        }
    }

    public synchronized void x() {
        BaseActivity baseActivity = this.thisActivity;
        if (baseActivity != null) {
            baseActivity.getHandler().post(new j());
        }
    }

    public synchronized void y(boolean z) {
        BaseActivity baseActivity = this.thisActivity;
        if (baseActivity != null) {
            baseActivity.getHandler().post(new k(z));
        }
    }

    public void z(int i2) {
        if (Log.D) {
            Log.d("JDNavigationFragment", "setCurrentTab() index = " + i2);
        }
        this.f11297g.setCheck(i2, false);
        NavigationBase.getInstance().mCurrentIndex = i2;
    }

    @Override // com.jingdong.common.unification.navigationbar.theme.INavigationTheme
    public void changeTheme(NavThemeEntity navThemeEntity, String str, INavigationChangeState iNavigationChangeState) {
        com.jingdong.app.mall.navigationbar.c.G().h(this.thisActivity, navThemeEntity, str, iNavigationChangeState);
    }
}
