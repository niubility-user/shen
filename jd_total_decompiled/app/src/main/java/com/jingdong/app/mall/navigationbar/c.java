package com.jingdong.app.mall.navigationbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.bean.ResourceItems;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.app.mall.category.JDNewCategoryFragment;
import com.jingdong.app.mall.common.view.JDCommonHostFragment;
import com.jingdong.app.mall.faxianV2.FaxianMainHostFragment;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.jdvideo.view.JDVideoHostFragment;
import com.jingdong.app.mall.navigationbar.JDMFragment;
import com.jingdong.app.mall.navigationbar.entity.NavigationBubbleEntity;
import com.jingdong.app.mall.personel.home.JDPersonalHostFragment;
import com.jingdong.app.mall.shopping.JDShopingCartHostFragment;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.customtheme.entity.ImageInfoEntity;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.navigationbar.NavigationTabLocationEntry;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.unification.navigationbar.theme.INavigationChangeState;
import com.jingdong.common.unification.navigationbar.theme.NavThemeEntity;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.sdk.bmode.util.JDBModeManager;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: j  reason: collision with root package name */
    private static c f11357j;

    /* renamed from: k  reason: collision with root package name */
    public static final String f11358k = NavigationConstants.LABEL_NAME_HOME;

    /* renamed from: l  reason: collision with root package name */
    public static final String f11359l;

    /* renamed from: m  reason: collision with root package name */
    public static final String f11360m;

    /* renamed from: n  reason: collision with root package name */
    public static final String f11361n;
    public static Map<Integer, JDCommonHostFragment> o;
    public static boolean p;
    private SharedPreferences a;
    private Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private Object f11362c = new Object();
    public boolean d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f11363e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f11364f;

    /* renamed from: g  reason: collision with root package name */
    public boolean f11365g;

    /* renamed from: h  reason: collision with root package name */
    private NavigationBubbleEntity f11366h;

    /* renamed from: i  reason: collision with root package name */
    BroadcastReceiver f11367i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11368g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ NavigationInfo f11369h;

        a(int i2, NavigationInfo navigationInfo) {
            this.f11368g = i2;
            this.f11369h = navigationInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Log.D) {
                Log.d("NavigationOptHelper", "NAVIGATION_Live, start" + this.f11368g);
            }
            NavigationInfo navigationInfo = this.f11369h;
            if (navigationInfo == null) {
                navigationInfo = UnCustomThemeHelper.getInstance().getNavigationInfoByNavigationId(this.f11368g);
            }
            if (navigationInfo != null) {
                if (!TextUtils.isEmpty(navigationInfo.model)) {
                    String str = navigationInfo.model;
                    if (NavigationBarUtil.isAuraLoadSuccess(str)) {
                        c.this.l0(str, this.f11368g, navigationInfo.functionId);
                    }
                } else if (TextUtils.isEmpty(navigationInfo.url)) {
                } else {
                    Bundle bundle = new Bundle();
                    JDMFragment.JDMM jdmm = new JDMFragment.JDMM();
                    bundle.putInt("com.360buy:navigationFlag", this.f11368g);
                    bundle.putString("url", navigationInfo.url.trim());
                    bundle.putBoolean("isTopBarGone", true);
                    bundle.putBoolean(MBaseKeyNames.KEY_SWITCH_IMMERSIVE_OPEN, true);
                    bundle.putBoolean("isUseRightBtn", false);
                    bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, false);
                    bundle.putBoolean(MKeyNames.NEED_CHECK_NATIVE, false);
                    bundle.putBoolean(MKeyNames.SHOW_ERROR_VIEW, true);
                    jdmm.k(bundle);
                    com.jingdong.app.mall.basic.a.c(jdmm);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements ILogin {
        b(c cVar) {
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            Log.d("Navigation", "\u767b\u5f55\u6210\u529f");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.navigationbar.c$c  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class RunnableC0362c implements Runnable {
        RunnableC0362c(c cVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            NavigationBase.getInstance().mCurrentIndex = 0;
            Bundle bundle = new Bundle();
            JDTaskModule jDTaskModule = new JDTaskModule() { // from class: com.jingdong.app.mall.home.JDHomeManager$JDHomeTM

                /* renamed from: g  reason: collision with root package name */
                private JDHomeFragment f8548g;

                /* renamed from: h  reason: collision with root package name */
                private h f8549h = new h();

                {
                    this.a = true;
                }

                @Override // com.jingdong.app.mall.basic.JDTaskModule
                public void a() {
                    JDHomeFragment z0 = JDHomeFragment.z0();
                    this.f8548g = z0;
                    if (z0 == null) {
                        return;
                    }
                    z0.setMoveTaskBack(true);
                    if (this.f8548g.getArguments() == null) {
                        c().putInt("com.360buy:navigationFlag", 0);
                        this.f8548g.setArguments(c());
                    }
                }

                @Override // com.jingdong.app.mall.basic.JDTaskModule
                public void b() {
                    this.f8548g.z1(false, this.f8549h);
                    j(this.f8548g, 0);
                }
            };
            jDTaskModule.k(bundle);
            com.jingdong.app.mall.basic.a.c(jDTaskModule);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11371g;

        d(c cVar, int i2) {
            this.f11371g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AuraFragmentHelper.getInstance().isFragmentAvailable(AuraBundleInfos.getBundleNameFromBundleId(26), "com.jd.lib.category.JDCategoryFragment")) {
                NavigationBase.getInstance().mCurrentIndex = this.f11371g;
                Bundle bundle = new Bundle();
                JDNewCategoryFragment.JDNewCategoryTM jDNewCategoryTM = new JDNewCategoryFragment.JDNewCategoryTM();
                jDNewCategoryTM.k(bundle);
                com.jingdong.app.mall.basic.a.c(jDNewCategoryTM);
                return;
            }
            DeepLinkMHelper.startWebActivity(JdSdk.getInstance().getApplicationContext(), "https://so.m.jd.com/category/all.html");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.S()) {
                c.this.k0();
            } else if (AuraFragmentHelper.getInstance().isFragmentAvailable(AuraBundleInfos.getBundleNameFromBundleId(45), "com.jd.lib.cart.JDShoppingCartFragment")) {
                NavigationBase.getInstance().mCurrentIndex = 3;
                Bundle bundle = new Bundle();
                String W = c.this.W("cart");
                if (Log.D) {
                    Log.d("NavigationOptHelper", "needCartInnerParam-map=" + W);
                }
                if (!TextUtils.isEmpty(W)) {
                    bundle.putString("cartExtParam", W);
                }
                JDShopingCartHostFragment.JDShoppingCartTM jDShoppingCartTM = new JDShopingCartHostFragment.JDShoppingCartTM();
                jDShoppingCartTM.k(bundle);
                com.jingdong.app.mall.basic.a.c(jDShoppingCartTM);
            } else {
                DeepLinkCartHelper.startCartMain(JdSdk.getInstance().getApplicationContext(), new Bundle());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AuraFragmentHelper.getInstance().isFragmentAvailable("com.jd.lib.Discovery", "com.jd.lib.Discovery.view.fragment.FaxianMainFeedsFragment")) {
                NavigationBase.getInstance().mCurrentIndex = 2;
                Bundle bundle = new Bundle();
                Map Y = c.this.Y("find");
                if (Y != null) {
                    bundle.putString("sourceId", (String) Y.get("sourceId"));
                    bundle.putString("sourceType", (String) Y.get("sourceType"));
                    bundle.putString("bubbleType", (String) Y.get("bubbleType"));
                    bundle.putString("bubbleText", (String) Y.get("bubbleText"));
                    bundle.putString("angleText", (String) Y.get("angleText"));
                }
                String X = c.this.X("find");
                if (Log.D) {
                    Log.d("NavigationOptHelper", "needFindInnerParam-jsonStr=" + X);
                }
                if (!TextUtils.isEmpty(X)) {
                    bundle.putString("findExtParam", X);
                }
                com.jingdong.app.mall.basic.a.c(FaxianMainHostFragment.h(bundle));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.S()) {
                c.this.k0();
            } else if (AuraFragmentHelper.getInstance().isFragmentAvailable("com.jd.lib.personal", "com.jd.lib.personal.view.fragment.JDPersonalFragment")) {
                NavigationBase.getInstance().mCurrentIndex = 4;
                Bundle bundle = new Bundle();
                JDPersonalHostFragment.JDPersonalTM jDPersonalTM = new JDPersonalHostFragment.JDPersonalTM();
                jDPersonalTM.k(bundle);
                com.jingdong.app.mall.basic.a.c(jDPersonalTM);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h implements Runnable {
        h(c cVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Log.D) {
                Log.d("NavigationOptHelper", "NAVIGATION_VIDEO, start");
            }
            if (AuraFragmentHelper.getInstance().isFragmentAvailable(AuraBundleInfos.getBundleNameFromBundleId(44), "com.jd.lib.videolife.view.fragment.VideoLifeFragment")) {
                NavigationBase.getInstance().mCurrentIndex = 6;
                Bundle bundle = new Bundle();
                JDVideoHostFragment.JDVideoTM jDVideoTM = new JDVideoHostFragment.JDVideoTM();
                if (Log.D) {
                    Log.d("NavigationOptHelper", "NAVIGATION_VIDEO, args = " + bundle);
                }
                jDVideoTM.k(bundle);
                com.jingdong.app.mall.basic.a.c(jDVideoTM);
                if (Log.D) {
                    Log.d("NavigationOptHelper", "NAVIGATION_VIDEO. entered plugin fragment");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class i implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11375g;

        i(int i2) {
            this.f11375g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Log.D) {
                Log.d("NavigationOptHelper", "NAVIGATION_NEW, start");
            }
            if (NavigationBarUtil.isAuraLoadSuccess("com.jd.lib.NewProduct.jdtabnewproduct.fragment.NewProductFragment")) {
                c.this.l0("com.jd.lib.NewProduct.jdtabnewproduct.fragment.NewProductFragment", this.f11375g, "new");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class j implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ NavigationInfo f11377g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f11378h;

        j(c cVar, NavigationInfo navigationInfo, int i2) {
            this.f11377g = navigationInfo;
            this.f11378h = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            NavigationInfo navigationInfo = this.f11377g;
            if (navigationInfo != null && !TextUtils.isEmpty(navigationInfo.model)) {
                NavigationBase.getInstance().mCurrentIndex = 9;
                Bundle bundle = new Bundle();
                bundle.putInt("com.360buy:navigationFlag", 9);
                bundle.putBoolean("isFromBVersionTab", true);
                bundle.putString("packageName", this.f11377g.model);
                bundle.putString("sourceValue", "Bversion27");
                JDCommonHostFragment.JDCommonTM jDCommonTM = new JDCommonHostFragment.JDCommonTM();
                jDCommonTM.k(bundle);
                com.jingdong.app.mall.basic.a.c(jDCommonTM);
                return;
            }
            NavigationInfo navigationInfo2 = this.f11377g;
            String str = (navigationInfo2 == null || TextUtils.isEmpty(navigationInfo2.url)) ? "https://h5.m.jd.com/pb/014191170/3xqnDaZFojhK1F5pn9T82zbxeg9e/index.html" : this.f11377g.url;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Bundle bundle2 = new Bundle();
            JDMFragment.JDMM jdmm = new JDMFragment.JDMM();
            bundle2.putInt("com.360buy:navigationFlag", this.f11378h);
            bundle2.putString("url", str.trim());
            bundle2.putBoolean("isTopBarGone", true);
            bundle2.putBoolean(MBaseKeyNames.KEY_SWITCH_IMMERSIVE_OPEN, true);
            bundle2.putBoolean("isUseRightBtn", false);
            bundle2.putBoolean(MBaseKeyNames.IS_NEED_SHARE, false);
            bundle2.putBoolean(MKeyNames.NEED_CHECK_NATIVE, false);
            bundle2.putBoolean(MKeyNames.SHOW_ERROR_VIEW, true);
            jdmm.k(bundle2);
            com.jingdong.app.mall.basic.a.c(jdmm);
        }
    }

    static {
        String str = NavigationConstants.LABEL_NAME_CATEGORY;
        f11359l = NavigationConstants.LABEL_NAME_FAXIAN;
        f11360m = NavigationConstants.LABEL_NAME_SHOPPINGCAR;
        f11361n = NavigationConstants.LABEL_NAME_MYJD;
        o = new HashMap();
        p = false;
    }

    private c() {
        this.a = null;
        this.a = CommonBase.getJdSharedPreferences();
    }

    private List<NavigationButton> D(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : Arrays.asList("index_elder,find_elder,cart_elder,home_elder".split(DYConstants.DY_REGEX_COMMA))) {
            NavigationButton m2 = m(context, str);
            if (Log.D) {
                Log.d("Navigation", "getElderNaviBar-customNaviBtn=" + m2 + " functionId=" + str);
            }
            if (m2 == null) {
                return null;
            }
            arrayList.add(m2);
        }
        NavigationBase.getInstance().navigationCurrentMode = 1;
        this.a.edit().putString(NavigationConstants.NAVIGATION_ELDER_ORDER_NATIVE, "index_elder,find_elder,cart_elder,home_elder").commit();
        return arrayList;
    }

    public static synchronized c G() {
        c cVar;
        synchronized (c.class) {
            if (f11357j == null) {
                f11357j = new c();
            }
            cVar = f11357j;
        }
        return cVar;
    }

    private List<NavigationButton> K(Context context, List<NavThemeEntity> list) {
        if (list == null || list.size() != 5) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NavThemeEntity navThemeEntity : list) {
            if (!navThemeEntity.usAble()) {
                return null;
            }
            NavigationButton navigationButton = new NavigationButton(context, navThemeEntity.navigationId, "", navThemeEntity.offPath, navThemeEntity.onPath, navThemeEntity.isBig);
            navigationButton.setButtonAction(new com.jingdong.app.mall.navigationbar.a(navThemeEntity.navigationId));
            arrayList.add(navigationButton);
        }
        NavigationBase.getInstance().buttons = arrayList;
        return NavigationBase.getInstance().buttons;
    }

    private boolean R(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                if (!new File(str2).exists() || !new File(str).exists()) {
                    if (Log.D) {
                        Log.d("Navigation", "iconCheck_new File(offPath).exists()=" + new File(str2).exists());
                        Log.d("Navigation", "iconCheck_new File(onPath).exists()=" + new File(str).exists());
                    }
                    return false;
                }
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            if (Log.D) {
                Log.d("Navigation", "iconCheck_bitmap_onPath=" + options.outWidth + LangUtils.SINGLE_SPACE + options.outHeight);
            }
            if (options.outWidth != -1 && options.outHeight != -1) {
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str2, options2);
                if (Log.D) {
                    Log.d("Navigation", "iconCheck_bitmap_offPath=" + options2.outWidth + LangUtils.SINGLE_SPACE + options2.outHeight);
                }
                if (options2.outWidth != -1 && options2.outHeight != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String W(String str) {
        NavigationBubbleEntity L;
        if (Log.D) {
            Log.d("NavigationOptHelper", "needCartInnerParam=" + this.f11363e);
        }
        if (this.f11363e && (L = L()) != null && TextUtils.equals(L.position, str) && !TextUtils.isEmpty(L.updatePrice)) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("updatePrice", (Object) L.updatePrice);
            return jDJSONObject.toJSONString();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String X(String str) {
        NavigationBubbleEntity L;
        if (Log.D) {
            Log.d("NavigationOptHelper", "needInnerParam=" + this.d);
        }
        if ((this.d || this.f11365g) && (L = L()) != null && TextUtils.equals(L.position, str) && !TextUtils.isEmpty(L.inner)) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("inner", (Object) L.inner);
            return jDJSONObject.toJSONString();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> Y(String str) {
        NavigationBubbleEntity L;
        if ((!this.d && !this.f11365g) || (L = L()) == null || !TextUtils.equals(L.position, str) || TextUtils.isEmpty(L.sourceId) || TextUtils.isEmpty(L.contentType)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("sourceId", L.sourceId);
        hashMap.put("sourceType", L.contentType);
        if (L.bubbleType == 4 && L.combinationTypes > 0) {
            hashMap.put("bubbleType", L.bubbleType + DYConstants.DY_REGEX_COMMA + L.combinationTypes);
        } else {
            hashMap.put("bubbleType", L.bubbleType + "");
        }
        if (!TextUtils.isEmpty(L.bubbleText)) {
            hashMap.put("bubbleText", L.bubbleText);
        }
        if (!TextUtils.isEmpty(L.angleText)) {
            hashMap.put("angleText", L.angleText);
        }
        return hashMap;
    }

    private String Z(String str) {
        NavigationBubbleEntity L;
        if (Log.D) {
            Log.d("NavigationOptHelper", "needNewInnerParam=" + this.f11364f);
        }
        if (this.f11364f && (L = L()) != null && TextUtils.equals(L.position, str) && !TextUtils.isEmpty(L.inner)) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("inner", (Object) L.inner);
            return jDJSONObject.toJSONString();
        }
        return null;
    }

    private List<NavigationButton> c0(Context context, NavigationButton navigationButton) {
        List<NavigationButton> u;
        if (navigationButton == null || (u = u(context)) == null || u.size() <= 0) {
            return null;
        }
        int size = u.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            u.get(i3).setIsDefaultIcon(false);
            if (navigationButton.getNavigationId() == u.get(i3).getNavigationId()) {
                i2 = i3;
            }
        }
        u.remove(i2);
        u.add(i2, navigationButton);
        return u;
    }

    public static void d0() {
        f11357j = null;
        JDHomeFragment.s1();
        JDNewCategoryFragment.b();
        FaxianMainHostFragment.k();
        JDShopingCartHostFragment.c();
        JDPersonalHostFragment.f();
        JDVideoHostFragment.c();
        JDCommonHostFragment.c();
        JDMFragment.m();
    }

    private void h0(Context context, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        ImageInfoEntity naviBgByModel = UnCustomThemeHelper.getInstance().getNaviBgByModel(true);
        if (naviBgByModel != null) {
            Drawable createFromPath = BitmapDrawable.createFromPath(naviBgByModel.localPath);
            if (createFromPath != null) {
                imageView.setImageDrawable(createFromPath);
                return;
            } else {
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.main_navigation_bg_dark_ground_glass));
                return;
            }
        }
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.main_navigation_bg_dark_ground_glass));
    }

    private boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("#");
    }

    private boolean j(String str) {
        List asList;
        if (TextUtils.isEmpty(str) || (asList = Arrays.asList(str.split(DYConstants.DY_REGEX_COMMA))) == null) {
            return false;
        }
        Iterator it = asList.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty((String) it.next())) {
                return false;
            }
        }
        return true;
    }

    private NavigationButton k(Context context, String str, boolean z) {
        NavigationInfo N;
        if (z) {
            N = UnCustomThemeHelper.getInstance().getNavigationInfoByFunctionId(str, true);
        } else {
            N = N(str);
        }
        if (Log.D) {
            Log.d("Navigation", "createCustomNaviBtn_navigationInfo=" + N);
        }
        if (N == null) {
            return null;
        }
        int M = M(str, N);
        if (Log.D) {
            Log.d("Navigation", "createCustomNaviBtn_navigationId=" + M + " functionId=" + str);
        }
        if (M == -1) {
            return null;
        }
        if (N != null && !TextUtils.isEmpty(N.model)) {
            if (Log.D) {
                Log.d("Navigation", "createCustomNaviBtn_modelAndroid=" + N.model);
                Log.d("Navigation", "createCustomNaviBtn_isAuraLoadSuccess()=" + NavigationBarUtil.isAuraLoadSuccess(N.model));
            }
            if (!NavigationBarUtil.isAuraLoadSuccess(N.model)) {
                return null;
            }
        }
        if (N == null || TextUtils.isEmpty(N.cutLabelName) || TextUtils.isEmpty(N.tabNameSelected) || (i(N.labelColor) && i(N.optLabelColor))) {
            String r = r(N, false);
            String r2 = r(N, true);
            if (Log.D) {
                Log.d("Navigation", "createCustomNaviBtn_offPath=" + r + " onPath=" + r2);
            }
            if (R(r2, r)) {
                NavigationButton navigationButton = new NavigationButton(context, M, H(str, N), r, r2, q(N), N);
                navigationButton.setButtonAction(new com.jingdong.app.mall.navigationbar.a(M, N));
                return navigationButton;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k0() {
        NavigationBase.getInstance().mCurrentIndex = 0;
        DeepLinkLoginHelper.startLoginActivity(JdSdk.getInstance().getApplicationContext(), null, new b(this), "navigation-browse-mode");
    }

    private NavigationButton m(Context context, String str) {
        ResourceItems resByCode = JDSkinSDK.getInstance().getResByCode(str);
        if (OKLog.D) {
            OKLog.d("Navigation", "createElderNaviBtn_resByCode=" + resByCode);
        }
        if (resByCode == null) {
            return null;
        }
        int E = E(str);
        if (OKLog.D) {
            OKLog.d("Navigation", "createElderNaviBtn_navigationId=" + E + " functionId=" + str);
        }
        if (E == -1) {
            return null;
        }
        String localBgImage = resByCode.getLocalBgImage();
        String localgImageSelected = resByCode.getLocalgImageSelected();
        if (OKLog.D) {
            OKLog.d("Navigation", "createElderNaviBtn_offPath=" + localBgImage + " onPath=" + localgImageSelected);
        }
        if (R(localgImageSelected, localBgImage)) {
            NavigationButton navigationButton = new NavigationButton(context, E, B(str), localBgImage, localgImageSelected, false, resByCode);
            navigationButton.setButtonAction(new com.jingdong.app.mall.navigationbar.a(E));
            return navigationButton;
        }
        return null;
    }

    private NavigationButton p(Context context, int i2, String str, int i3, int i4, String str2) {
        NavigationButton navigationButton = new NavigationButton(context, i2, str, i3, i4, str2);
        navigationButton.setButtonAction(new com.jingdong.app.mall.navigationbar.a(i2));
        return navigationButton;
    }

    private boolean q(NavigationInfo navigationInfo) {
        return navigationInfo != null && navigationInfo.displayType == 1;
    }

    private String r(NavigationInfo navigationInfo, boolean z) {
        return navigationInfo != null ? z ? navigationInfo.optLabelImagePath : navigationInfo.labelImagePath : "";
    }

    private List<NavigationButton> s(Context context, String str, String str2, boolean z) {
        ArrayList arrayList = new ArrayList();
        List asList = Arrays.asList(str.split(DYConstants.DY_REGEX_COMMA));
        if (asList.size() == 5) {
            Iterator it = asList.iterator();
            while (it.hasNext()) {
                NavigationButton k2 = k(context, (String) it.next(), z);
                if (k2 == null) {
                    return null;
                }
                arrayList.add(k2);
            }
        }
        if ("0".equals(str2)) {
            NavigationBase.getInstance().navigationCurrentMode = 0;
            this.a.edit().putString(NavigationConstants.NAVIGATION_ORDER_NATIVE, str).commit();
        } else if ("2".equals(str2)) {
            NavigationBase.getInstance().navigationCurrentMode = 2;
            this.a.edit().putString(NavigationConstants.NAVIGATION_ORDER_NATIVE_B, str).commit();
        }
        return arrayList;
    }

    public List<NavigationButton> A(Context context) {
        NavigationBase.getInstance().navigationCurrentMode = 1;
        this.a.edit().putString(NavigationConstants.NAVIGATION_ELDER_ORDER_NATIVE, "index_elder,find_elder,cart_elder,home_elder").commit();
        ArrayList arrayList = new ArrayList();
        arrayList.add(p(context, 0, f11358k, R.drawable.main_navigation_home_elder, R.drawable.main_navigation_home_elder_focus, C(0, false)));
        arrayList.add(p(context, 2, "\u770b\u4e00\u770b", R.drawable.main_navigation_look_elder, R.drawable.main_navigation_look_elder_focus, C(2, false)));
        arrayList.add(p(context, 3, f11360m, R.drawable.main_navigation_cart_elder, R.drawable.main_navigation_cart_elder_focus, C(3, false)));
        arrayList.add(p(context, 4, f11361n, R.drawable.main_navigation_my_elder, R.drawable.main_navigation_my_elder_focus, C(4, false)));
        return arrayList;
    }

    public String B(String str) {
        if ("index_elder".equals(str)) {
            return NavigationConstants.LABEL_NAME_HOME;
        }
        if ("find_elder".equals(str)) {
            return "\u770b\u4e00\u770b";
        }
        if ("cart_elder".equals(str)) {
            return NavigationConstants.LABEL_NAME_SHOPPINGCAR;
        }
        return "home_elder".equals(str) ? NavigationConstants.LABEL_NAME_MYJD : "";
    }

    public String C(int i2, boolean z) {
        return "";
    }

    public int E(String str) {
        if ("index_elder".equals(str)) {
            return 0;
        }
        if ("find_elder".equals(str)) {
            return 2;
        }
        if ("cart_elder".equals(str)) {
            return 3;
        }
        return "home_elder".equals(str) ? 4 : -1;
    }

    public String F(int i2) {
        if (i2 == 0) {
            return "index";
        }
        if (2 == i2) {
            return "find";
        }
        if (1 == i2) {
            return "category";
        }
        if (3 == i2) {
            return "cart";
        }
        if (4 == i2) {
            return "home";
        }
        if (6 == i2) {
            return "video";
        }
        if (7 == i2) {
            return "search";
        }
        if (8 == i2) {
            return "new";
        }
        if (9 == i2) {
            return "game";
        }
        NavigationInfo O = O(i2);
        return O != null ? O.functionId : "";
    }

    public String H(String str, NavigationInfo navigationInfo) {
        if (navigationInfo != null && !TextUtils.isEmpty(navigationInfo.labelName)) {
            return navigationInfo.labelName;
        }
        if ("index".equals(str)) {
            return NavigationConstants.LABEL_NAME_HOME;
        }
        if ("find".equals(str)) {
            return NavigationConstants.LABEL_NAME_FAXIAN;
        }
        if ("category".equals(str)) {
            return NavigationConstants.LABEL_NAME_CATEGORY;
        }
        if ("cart".equals(str)) {
            return NavigationConstants.LABEL_NAME_SHOPPINGCAR;
        }
        if ("home".equals(str)) {
            return NavigationConstants.LABEL_NAME_MYJD;
        }
        return "video".equals(str) ? NavigationConstants.LABEL_NAME_VIDEO : "search".equals(str) ? "\u641c\u7d22" : "new".equals(str) ? NavigationConstants.LABEL_NAME_NEW : "game".equals(str) ? "\u9886\u73b0\u91d1" : "";
    }

    public String I(int i2) {
        return i2 != 0 ? i2 != 9 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "navigation_lottie/user_imm.json" : "navigation_lottie/cart_imm.json" : "navigation_lottie/discover_imm.json" : "navigation_lottie/game.json" : "navigation_lottie/home_imm.json";
    }

    public String J(int i2) {
        return i2 != 0 ? i2 != 9 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "navigation_lottie/user_b.json" : "navigation_lottie/cart_b.json" : "navigation_lottie/discover_b.json" : "navigation_lottie/game.json" : "navigation_lottie/home_b.json";
    }

    public NavigationBubbleEntity L() {
        return this.f11366h;
    }

    public int M(String str, NavigationInfo navigationInfo) {
        if ("index".equals(str)) {
            return 0;
        }
        if ("find".equals(str)) {
            return 2;
        }
        if ("category".equals(str)) {
            return 1;
        }
        if ("cart".equals(str)) {
            return 3;
        }
        if ("home".equals(str)) {
            return 4;
        }
        if ("video".equals(str)) {
            return 6;
        }
        if ("search".equals(str)) {
            return 7;
        }
        if ("new".equals(str)) {
            return 8;
        }
        if ("game".equals(str)) {
            return 9;
        }
        if (navigationInfo == null) {
            navigationInfo = N(str);
        }
        if (navigationInfo != null) {
            return navigationInfo.navigationId;
        }
        return -1;
    }

    public NavigationInfo N(String str) {
        return UnCustomThemeHelper.getInstance().getNavigationInfoByFunctionId(str);
    }

    public NavigationInfo O(int i2) {
        return UnCustomThemeHelper.getInstance().getNavigationInfoByNavigationId(i2);
    }

    public NavigationTabLocationEntry P(int i2) {
        List<NavigationButton> list = NavigationBase.getInstance().buttons;
        if (list == null || i2 >= list.size()) {
            return null;
        }
        return NavigationBase.getInstance().buttons.get(i2).getIconLocation();
    }

    public Runnable Q(int i2, NavigationInfo navigationInfo) {
        switch (i2) {
            case 0:
                return new RunnableC0362c(this);
            case 1:
            case 7:
                return new d(this, i2);
            case 2:
                return new f();
            case 3:
                return new e();
            case 4:
                return new g();
            case 5:
            default:
                return new a(i2, navigationInfo);
            case 6:
                return new h(this);
            case 8:
                return new i(i2);
            case 9:
                return new j(this, navigationInfo, i2);
        }
    }

    public boolean S() {
        return !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
    }

    public boolean T() {
        try {
            return true ^ UnCustomThemeHelper.getInstance().customThemeEnable();
        } catch (Throwable unused) {
            return true;
        }
    }

    public boolean U() {
        NavigationButton navigationButton;
        List<NavigationButton> list = NavigationBase.getInstance().buttons;
        return (list == null || list.size() != 5 || (navigationButton = list.get(2)) == null || navigationButton.getNavigationId() == NavigationBase.getInstance().mCurrentIndex) ? false : true;
    }

    public boolean V() {
        MainFrameActivity b2 = com.jingdong.app.mall.n.a.a().b();
        if (b2 == null) {
            return false;
        }
        return b2.getChangeVersionShowing();
    }

    public void a0(int i2) {
        if (com.jingdong.app.mall.n.a.a().b() != null) {
            com.jingdong.app.mall.n.a.a().b().getNavigationFragment().h(Integer.valueOf(i2));
        }
    }

    public void b0(Context context) {
        if (context == null) {
            return;
        }
        if (this.f11367i == null) {
            this.f11367i = new NavigationChangeVersionReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(JDBModeManager.ACTION_B_MODE_DID_CLICK_CONFIRM_BUTTON_NOTI);
        LocalBroadcastManager.getInstance(context).registerReceiver(this.f11367i, intentFilter);
    }

    public boolean e(int i2) {
        List<NavigationButton> list = NavigationBase.getInstance().buttons;
        if (list == null || i2 >= list.size()) {
            return false;
        }
        return NavigationBase.getInstance().buttons.get(i2).bigIconTag;
    }

    public int e0(ImageView imageView, Context context) {
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1 && NavigationBase.getInstance().navigationCurrentMode == 2) {
            f0(imageView, context);
            return 0;
        }
        ImageInfoEntity naviBg = UnCustomThemeHelper.getInstance().getNaviBg();
        if (naviBg != null) {
            if (Log.D) {
                Log.d("Navigation", "setCustomNaviBg-entity=" + naviBg.localPath);
            }
            Drawable createFromPath = BitmapDrawable.createFromPath(naviBg.localPath);
            if (createFromPath != null) {
                imageView.setImageDrawable(createFromPath);
                NavigationBase.getInstance().isUseDefaultBg = false;
                return 1;
            }
            f0(imageView, context);
            return 0;
        }
        f0(imageView, context);
        return 0;
    }

    public synchronized void f() {
        if (S()) {
            return;
        }
        if (NavigationBase.getInstance().buttons == null || NavigationBase.getInstance().buttons.size() == 3) {
            try {
                p = true;
                com.jingdong.app.mall.n.a.a().b().getNavigationFragment().v(false, false);
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void f0(ImageView imageView, Context context) {
        if (imageView == null || context == null) {
            return;
        }
        if (JDElderModeUtils.isElderMode()) {
            if (DeepDarkChangeManager.getInstance().getUIMode() == 1 && NavigationBase.getInstance().navigationCurrentMode != 2) {
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.b74));
            } else {
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.a8a));
            }
        } else if (DeepDarkChangeManager.getInstance().getUIMode() == 1 && NavigationBase.getInstance().navigationCurrentMode != 2) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.main_navigation_bg_dark_ground_glass));
        } else {
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.main_navigation_bg_ground_glass));
        }
        NavigationBase.getInstance().isUseDefaultBg = true;
    }

    public void g(Context context, List<NavThemeEntity> list, String str) {
        MainFrameActivity b2;
        JDNavigationFragment navigationFragment;
        if (T()) {
            if (((list == null) || (context == null)) || list.size() != 5 || (b2 = com.jingdong.app.mall.n.a.a().b()) == null || (navigationFragment = b2.getNavigationFragment()) == null) {
                return;
            }
            navigationFragment.w(K(context, list), str);
        }
    }

    public int g0(ImageView imageView, Context context) {
        ResourceItems resByCode = JDSkinSDK.getInstance().getResByCode("navigation_elder_bgImage");
        if (resByCode != null) {
            Drawable createFromPath = BitmapDrawable.createFromPath(resByCode.getLocalBgImage());
            if (createFromPath != null) {
                imageView.setImageDrawable(createFromPath);
                return 1;
            }
            f0(imageView, context);
            return 0;
        }
        f0(imageView, context);
        return 0;
    }

    public void h(Context context, NavThemeEntity navThemeEntity, String str, INavigationChangeState iNavigationChangeState) {
        JDNavigationFragment navigationFragment;
        if (navThemeEntity == null || !T() || !R(navThemeEntity.onPath, navThemeEntity.offPath)) {
            if (iNavigationChangeState != null) {
                iNavigationChangeState.result(false);
                return;
            }
            return;
        }
        MainFrameActivity b2 = com.jingdong.app.mall.n.a.a().b();
        if (b2 == null || (navigationFragment = b2.getNavigationFragment()) == null) {
            return;
        }
        NavigationButton navigationButton = new NavigationButton(context, navThemeEntity.navigationId, "", navThemeEntity.offPath, navThemeEntity.onPath, navThemeEntity.isBig);
        navigationButton.setIsDefaultIcon(false);
        navigationButton.setButtonAction(new com.jingdong.app.mall.navigationbar.a(navThemeEntity.navigationId));
        navigationFragment.w(c0(context, navigationButton), str);
        if (iNavigationChangeState != null) {
            iNavigationChangeState.result(true);
        }
    }

    public void i0(NavigationBubbleEntity navigationBubbleEntity) {
        this.f11366h = navigationBubbleEntity;
    }

    public void j0(boolean z) {
        MainFrameActivity b2 = com.jingdong.app.mall.n.a.a().b();
        if (b2 == null || b2.isFinishing()) {
            return;
        }
        if (z) {
            com.jingdong.app.mall.n.a.a().b().showChangeVersionBubble();
        } else {
            com.jingdong.app.mall.n.a.a().b().dismissChangeVersionBubble();
        }
    }

    public List<NavigationButton> l(Context context, ImageView imageView, String str) {
        if (context == null) {
            context = JdSdk.getInstance().getApplication();
        }
        synchronized (this.b) {
            if (Log.D) {
                Log.d("Navigation", "createDefNaviBar-mode=" + str);
            }
            if (S()) {
                NavigationBase.getInstance().buttons = u(context);
                f0(imageView, context);
                return NavigationBase.getInstance().buttons;
            }
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            if ("1".equals(str)) {
                NavigationBase.getInstance().buttons = y(context);
                f0(imageView, context);
                if (NavigationBase.getInstance().mCurrentIndex == 1 || NavigationBase.getInstance().mCurrentIndex > 4) {
                    NavigationBase.getInstance().mCurrentIndex = 0;
                }
                return NavigationBase.getInstance().buttons;
            }
            if ("0".equals(str)) {
                NavigationBase.getInstance().buttons = u(context);
            } else if ("2".equals(str)) {
                NavigationBase.getInstance().buttons = x(context);
            }
            f0(imageView, context);
            return NavigationBase.getInstance().buttons;
        }
    }

    public void l0(String str, int i2, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        NavigationBase.getInstance().mCurrentIndex = i2;
        Bundle bundle = new Bundle();
        bundle.putInt("com.360buy:navigationFlag", i2);
        bundle.putString("packageName", str);
        String Z = Z(str2);
        if (Log.D) {
            Log.d("NavigationOptHelper", "needNewInnerParam-jsonStr=" + Z);
        }
        if (!TextUtils.isEmpty(Z)) {
            bundle.putString("newExtParam", Z);
        }
        JDCommonHostFragment.JDCommonTM jDCommonTM = new JDCommonHostFragment.JDCommonTM();
        jDCommonTM.k(bundle);
        com.jingdong.app.mall.basic.a.c(jDCommonTM);
    }

    public void m0(Context context) {
        if (context == null || this.f11367i == null) {
            return;
        }
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this.f11367i);
    }

    public List<NavigationButton> n(Context context, ImageView imageView, String str) {
        if (context == null) {
            context = JdSdk.getInstance().getApplication();
        }
        synchronized (this.f11362c) {
            String string = this.a.getString(NavigationConstants.NAVIGATION_ORDER_NATIVE_B, "");
            if (Log.D) {
                Log.d("Navigation", "createImmNaviBar-order=" + string);
            }
            if (j(string)) {
                List<NavigationButton> s = s(context, string, str, true);
                if ((s == null || s.size() <= 0) && "index,find,game,cart,home".equals(string)) {
                    s = t(context);
                }
                h0(context, imageView);
                return s;
            }
            h0(context, imageView);
            return t(context);
        }
    }

    public List<NavigationButton> o(Context context, ImageView imageView, boolean z, String str) {
        if (context == null) {
            context = JdSdk.getInstance().getApplication();
        }
        synchronized (this.b) {
            if (Log.D) {
                Log.d("Navigation", "createNaviBar-mode=" + str);
            }
            if (S()) {
                NavigationBase.getInstance().buttons = u(context);
                f0(imageView, context);
                return NavigationBase.getInstance().buttons;
            }
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            boolean z2 = true;
            if ("1".equals(str)) {
                NavigationBase.getInstance().buttons = D(context);
                if (NavigationBase.getInstance().buttons != null && NavigationBase.getInstance().buttons.size() > 0) {
                    g0(imageView, context);
                    if (NavigationBase.getInstance().mCurrentIndex != 1 || NavigationBase.getInstance().mCurrentIndex > 4) {
                        NavigationBase.getInstance().mCurrentIndex = 0;
                    }
                    return NavigationBase.getInstance().buttons;
                }
                NavigationBase.getInstance().buttons = y(context);
                f0(imageView, context);
                if (NavigationBase.getInstance().mCurrentIndex != 1) {
                }
                NavigationBase.getInstance().mCurrentIndex = 0;
                return NavigationBase.getInstance().buttons;
            }
            String str2 = "";
            String curVersionMoudle = UnCustomThemeHelper.getInstance().getCurVersionMoudle();
            if (Log.D) {
                Log.d("Navigation", "createNaviBar-versionMoudle=" + curVersionMoudle);
            }
            if ("0".equals(str)) {
                if (z) {
                    if (!TextUtils.isEmpty(curVersionMoudle) && "0".equals(curVersionMoudle)) {
                        str2 = UnCustomThemeHelper.getInstance().getNavigationIds();
                    }
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "index,find,new,cart,home";
                    }
                } else {
                    str2 = this.a.getString(NavigationConstants.NAVIGATION_ORDER_NATIVE, "");
                }
            } else if ("2".equals(str)) {
                if (!TextUtils.isEmpty(curVersionMoudle) && "2".equals(curVersionMoudle)) {
                    str2 = UnCustomThemeHelper.getInstance().getNavigationIds();
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = "index,find,game,cart,home";
                }
            }
            if (Log.D) {
                Log.d("Navigation", "createNaviBar-isReadRemoteValue=" + z + " remoteOrder=" + UnCustomThemeHelper.getInstance().getNavigationIds() + " order=" + str2 + " checkNavigationOrder=" + j(str2) + " mode=" + str);
            }
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(curVersionMoudle) || !str.equals(curVersionMoudle)) {
                z2 = false;
            }
            if (j(str2) && z2) {
                NavigationBase.getInstance().buttons = s(context, str2, str, false);
                if (NavigationBase.getInstance().buttons != null && NavigationBase.getInstance().buttons.size() > 0) {
                    e0(imageView, context);
                }
                if ("0".equals(str)) {
                    NavigationBase.getInstance().buttons = u(context);
                } else if ("2".equals(str)) {
                    NavigationBase.getInstance().buttons = x(context);
                }
                f0(imageView, context);
            } else {
                if ("0".equals(str)) {
                    NavigationBase.getInstance().buttons = u(context);
                } else if ("2".equals(str)) {
                    NavigationBase.getInstance().buttons = x(context);
                }
                f0(imageView, context);
            }
            return NavigationBase.getInstance().buttons;
        }
    }

    public List<NavigationButton> t(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(p(context, 0, f11358k, R.drawable.c96, R.drawable.c96, I(0)));
        arrayList.add(p(context, 2, f11359l, R.drawable.c95, R.drawable.c95, I(2)));
        arrayList.add(p(context, 9, "\u9886\u73b0\u91d1", R.drawable.c8w, R.drawable.c8w, I(9)));
        arrayList.add(p(context, 3, f11360m, R.drawable.c94, R.drawable.c94, I(3)));
        arrayList.add(p(context, 4, f11361n, R.drawable.c97, R.drawable.c97, I(4)));
        return arrayList;
    }

    public List<NavigationButton> u(Context context) {
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            return w(context);
        }
        return v(context);
    }

    public List<NavigationButton> v(Context context) {
        NavigationBase.getInstance().navigationCurrentMode = 0;
        this.a.edit().putString(NavigationConstants.NAVIGATION_ORDER_NATIVE, "index,find,new,cart,home").commit();
        ArrayList arrayList = new ArrayList();
        arrayList.add(p(context, 0, f11358k, R.drawable.a88, R.drawable.a87, NavigationBase.getLottieJsonByNavigationId(0, false)));
        if (!S()) {
            arrayList.add(p(context, 2, f11359l, R.drawable.a86, R.drawable.a85, NavigationBase.getLottieJsonByNavigationId(2, false)));
            arrayList.add(p(context, 8, NavigationConstants.LABEL_NAME_NEW, R.drawable.bjq, R.drawable.bjo, NavigationBase.getLottieJsonByNavigationId(8, false)));
        }
        arrayList.add(p(context, 3, f11360m, R.drawable.a82, R.drawable.a81, NavigationBase.getLottieJsonByNavigationId(3, false)));
        arrayList.add(p(context, 4, f11361n, R.drawable.a8_, R.drawable.a89, NavigationBase.getLottieJsonByNavigationId(4, false)));
        return arrayList;
    }

    public List<NavigationButton> w(Context context) {
        NavigationBase.getInstance().navigationCurrentMode = 0;
        this.a.edit().putString(NavigationConstants.NAVIGATION_ORDER_NATIVE, "index,find,new,cart,home").commit();
        ArrayList arrayList = new ArrayList();
        arrayList.add(p(context, 0, f11358k, R.drawable.b8g, R.drawable.b8f, NavigationBase.getLottieJsonByNavigationId(0, true)));
        if (!S()) {
            arrayList.add(p(context, 2, f11359l, R.drawable.b8e, R.drawable.b8d, NavigationBase.getLottieJsonByNavigationId(2, true)));
            arrayList.add(p(context, 8, NavigationConstants.LABEL_NAME_NEW, R.drawable.bjr, R.drawable.bjp, NavigationBase.getLottieJsonByNavigationId(8, true)));
        }
        arrayList.add(p(context, 3, f11360m, R.drawable.b8a, R.drawable.b8_, NavigationBase.getLottieJsonByNavigationId(3, true)));
        arrayList.add(p(context, 4, f11361n, R.drawable.b8i, R.drawable.b8h, NavigationBase.getLottieJsonByNavigationId(4, true)));
        return arrayList;
    }

    public List<NavigationButton> x(Context context) {
        NavigationBase.getInstance().navigationCurrentMode = 2;
        this.a.edit().putString(NavigationConstants.NAVIGATION_ORDER_NATIVE_B, "index,find,game,cart,home").commit();
        ArrayList arrayList = new ArrayList();
        arrayList.add(p(context, 0, f11358k, R.drawable.c8y, R.drawable.c8x, J(0)));
        arrayList.add(p(context, 2, f11359l, R.drawable.c8v, R.drawable.c8u, J(2)));
        arrayList.add(p(context, 9, "\u9886\u73b0\u91d1", R.drawable.c8w, R.drawable.c8w, J(9)));
        arrayList.add(p(context, 3, f11360m, R.drawable.c8t, R.drawable.c8s, J(3)));
        arrayList.add(p(context, 4, f11361n, R.drawable.c90, R.drawable.c8z, J(4)));
        return arrayList;
    }

    public List<NavigationButton> y(Context context) {
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            return z(context);
        }
        return A(context);
    }

    public List<NavigationButton> z(Context context) {
        NavigationBase.getInstance().navigationCurrentMode = 1;
        this.a.edit().putString(NavigationConstants.NAVIGATION_ELDER_ORDER_NATIVE, "index_elder,find_elder,cart_elder,home_elder").commit();
        ArrayList arrayList = new ArrayList();
        arrayList.add(p(context, 0, f11358k, R.drawable.main_navigation_home_elder_dark, R.drawable.main_navigation_home_elder_focus_dark, C(0, true)));
        arrayList.add(p(context, 2, "\u770b\u4e00\u770b", R.drawable.main_navigation_look_elder_dark, R.drawable.main_navigation_look_elder_focus_dark, C(2, true)));
        arrayList.add(p(context, 3, f11360m, R.drawable.main_navigation_cart_elder_dark, R.drawable.main_navigation_cart_elder_focus_dark, C(3, true)));
        arrayList.add(p(context, 4, f11361n, R.drawable.main_navigation_my_elder_dark, R.drawable.main_navigation_my_elder_focus_dark, C(4, true)));
        return arrayList;
    }
}
