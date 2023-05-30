package com.jingdong.app.mall.navigationbar;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.navigationbar.entity.NaviBubbleSp;
import com.jingdong.app.mall.navigationbar.entity.NavigationBubbleEntity;
import com.jingdong.app.mall.navigationbar.entity.NavigationFrequencyRuleResult;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConfig;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.navigationbar.NavigationMaterialShowEntity;
import com.jingdong.common.unification.navigationbar.NavigationParam;
import com.jingdong.common.unification.navigationbar.db.NavigationDBController;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.unification.navigationbar.newbar.StateController;
import com.jingdong.common.unification.navigationbar.newbar.TabShowNew;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.builderimpl.DefaultRouterBuilder;
import com.jingdong.common.unification.router.module.JDNavigationModule;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class NavigationBarUtil {
    public static boolean ANGLE_APP_FLAG = false;
    public static String BUBBLE_KEY = "NavigationBubbleKey";
    public static final String EFFECT_DIR = "navigation_effect";
    public static String EFFECT_KEY = "NavigationEffectKey";
    private static boolean ICON_ANGLE_FLAG = false;
    public static boolean ICON_APP_FLAG = false;
    private static boolean ICON_BUBBLE_FLAG = false;
    public static final String ICON_DIR = "icon_effect";
    public static String ICON_KEY = "NavigationIconKey";
    public static final int NAVIGATION_BUBBLE = 1;
    public static final int NAVIGATION_EFFECT = 3;
    private static final String SP_ANGLE_SWITCH = "navigationAngleSwitch";
    private static final String SP_RED_POINT_SWITCH = "navigationRedPointSwitch";
    public static final String TAG = "NavigationBarUtil";
    private static int always = 0;
    public static boolean angleRequested = false;
    private static boolean isCanShowBubbleAndIcon = false;
    private static int never = 3;
    private static int oneDay = 1;
    private static long oneDaySeconds = 86400000;
    private static int oneWeek = 2;
    private static long oneWeekSeconds = 604800000;
    private static boolean showIconAndAngle = false;
    private static String showRedPointSwitch = "";
    private static volatile HashSet<String> NAVIGATION_FRAGMENT_VALID_CACHE = new HashSet<>(5);
    public static Map<String, String> angleSet = new ConcurrentHashMap();
    private static boolean isHaveRecordCombination = false;

    /* loaded from: classes4.dex */
    public class a extends JDSimpleImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ NavigationBubbleEntity f11324g;

        /* renamed from: h */
        final /* synthetic */ NavigationButton f11325h;

        /* renamed from: i */
        final /* synthetic */ JDJSONObject f11326i;

        /* renamed from: com.jingdong.app.mall.navigationbar.NavigationBarUtil$a$a */
        /* loaded from: classes4.dex */
        public class RunnableC0360a implements Runnable {

            /* renamed from: g */
            final /* synthetic */ Bitmap f11327g;

            /* renamed from: h */
            final /* synthetic */ Handler f11328h;

            /* renamed from: com.jingdong.app.mall.navigationbar.NavigationBarUtil$a$a$a */
            /* loaded from: classes4.dex */
            class RunnableC0361a implements Runnable {

                /* renamed from: g */
                final /* synthetic */ boolean f11330g;

                /* renamed from: h */
                final /* synthetic */ NavigationParam f11331h;

                RunnableC0361a(boolean z, NavigationParam navigationParam) {
                    RunnableC0360a.this = r1;
                    this.f11330g = z;
                    this.f11331h = navigationParam;
                }

                @Override // java.lang.Runnable
                public void run() {
                    int i2;
                    if (a.this.f11325h.getButtonState()) {
                        if (this.f11330g || (i2 = this.f11331h.dynamicType) == 1 || i2 == 3) {
                            boolean unused = NavigationBarUtil.showIconAndAngle = true;
                            NavigationBarUtil.showAngleByIcon(a.this.f11325h, "");
                        }
                        a.this.f11325h.setDefault(false);
                        NavigationConfig navigationConfig = new NavigationConfig(a.this.f11325h.getNavigationId(), com.jingdong.app.mall.navigationbar.c.G().F(a.this.f11325h.getNavigationId()), "", a.this.f11324g.bucketType);
                        navigationConfig.setCurrentMode(com.jingdong.app.mall.navigationbar.e.l().f11385c);
                        navigationConfig.setNewIconState(false);
                        NavigationBase.getInstance().setOrUpdateNavigationConfig(navigationConfig);
                    }
                }
            }

            RunnableC0360a(Bitmap bitmap, Handler handler) {
                a.this = r1;
                this.f11327g = bitmap;
                this.f11328h = handler;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j2;
                if (a.this.f11325h != null) {
                    NavigationParam navigationParam = new NavigationParam();
                    a aVar = a.this;
                    NavigationBubbleEntity navigationBubbleEntity = aVar.f11324g;
                    int i2 = navigationBubbleEntity.dynamicType;
                    navigationParam.dynamicType = i2;
                    navigationParam.shapeType = navigationBubbleEntity.shapeType;
                    String str = navigationBubbleEntity.angleText;
                    navigationParam.angleText = str;
                    int i3 = navigationBubbleEntity.bucketType;
                    navigationParam.bucketType = i3;
                    if (i3 != 1 && (i2 == 1 || i2 == 3)) {
                        str = "";
                    }
                    boolean showAngleByIcon = NavigationBarUtil.showAngleByIcon(aVar.f11325h, str);
                    NavigationButton navigationButton = a.this.f11325h;
                    if (navigationButton != null && navigationButton.getStateController() != null) {
                        a.this.f11325h.getStateController().lablePosition = a.this.f11324g.position;
                    }
                    a aVar2 = a.this;
                    aVar2.f11325h.changeTabIconFromBubble(this.f11327g, aVar2.f11324g.iconSize, aVar2.f11326i.toJSONString(), navigationParam);
                    NavigationBarUtil.recordMaterialShow(a.this.f11324g);
                    NavigationConfig navigationConfig = new NavigationConfig(a.this.f11325h.getNavigationId(), com.jingdong.app.mall.navigationbar.c.G().F(a.this.f11325h.getNavigationId()), str, a.this.f11324g.bucketType);
                    navigationConfig.setCurrentMode(com.jingdong.app.mall.navigationbar.e.l().f11385c);
                    navigationConfig.setNewIconState(true);
                    NavigationBase.getInstance().setOrUpdateNavigationConfig(navigationConfig);
                    long j3 = a.this.f11324g.disappearTiming;
                    if (j3 > 0) {
                        int i4 = navigationParam.dynamicType;
                        if (i4 > 0) {
                            if (i4 == 1) {
                                j2 = NavigationConstants.ANIMATION_TIME_SKU_ROTATION;
                            } else if (i4 == 2) {
                                j3 = j3 + NavigationConstants.ANIMATION_TIME_ICON_ROTATION + NavigationConstants.ANIMATION_TIME_ICON_SCALE;
                                j2 = NavigationConstants.ANIMATION_TIME_ICON_SCALE_DELAY;
                            } else if (i4 == 3) {
                                j3 = j3 + NavigationConstants.ANIMATION_TIME_SKU_ROTATION + NavigationConstants.ANIMATION_TIME_ICON_ROTATION + NavigationConstants.ANIMATION_TIME_ICON_SCALE;
                                j2 = NavigationConstants.ANIMATION_TIME_ICON_SCALE_DELAY;
                            }
                            j3 += j2;
                        }
                        this.f11328h.postDelayed(new RunnableC0361a(showAngleByIcon, navigationParam), j3);
                    }
                }
            }
        }

        a(NavigationBubbleEntity navigationBubbleEntity, NavigationButton navigationButton, JDJSONObject jDJSONObject) {
            this.f11324g = navigationBubbleEntity;
            this.f11325h = navigationButton;
            this.f11326i = jDJSONObject;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            if (Log.D) {
                Log.d(NavigationBarUtil.TAG, "changeView-bubbleIv_onLoadingComplete_" + Thread.currentThread().getName());
            }
            long currentTimeMillis = System.currentTimeMillis();
            NavigationBubbleEntity navigationBubbleEntity = this.f11324g;
            if (currentTimeMillis < navigationBubbleEntity.startTime || currentTimeMillis > navigationBubbleEntity.endTime) {
                return;
            }
            NavigationBarUtil.putBubbleEntityToSp(navigationBubbleEntity, NavigationBarUtil.ICON_KEY);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new RunnableC0360a(bitmap, handler), this.f11324g.showTiming);
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            super.onLoadingFailed(str, view, jDFailReason);
            if (Log.D) {
                Log.d(NavigationBarUtil.TAG, "changeView-bubbleIv_onLoadingFailed_" + Thread.currentThread().getName());
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements HttpGroup.OnAllAndPauseListener {

        /* renamed from: g */
        final /* synthetic */ NavigationBubbleEntity f11333g;

        /* renamed from: h */
        final /* synthetic */ NavigationButton f11334h;

        /* renamed from: i */
        final /* synthetic */ File f11335i;

        /* renamed from: j */
        final /* synthetic */ String f11336j;

        /* renamed from: k */
        final /* synthetic */ String f11337k;

        b(NavigationBubbleEntity navigationBubbleEntity, NavigationButton navigationButton, File file, String str, String str2) {
            this.f11333g = navigationBubbleEntity;
            this.f11334h = navigationButton;
            this.f11335i = file;
            this.f11336j = str;
            this.f11337k = str2;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            File saveFile = httpResponse.getSaveFile();
            if (saveFile == null || !saveFile.exists()) {
                return;
            }
            String md5 = FileUtils.getMD5(saveFile.getAbsolutePath());
            if (Log.D) {
                String str = NavigationBarUtil.TAG;
                Log.d(str, "downIconLottie_\u4e0b\u8f7d_onEnd=" + Thread.currentThread().getName());
                Log.d(str, "downIconLottie_onEnd-localLottieMD5=" + md5 + " file.getAbsolutePath()=" + saveFile.getAbsolutePath());
            }
            if (!TextUtils.isEmpty(md5) && this.f11333g.iconUrlMd5.equals(md5)) {
                if (this.f11334h != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    NavigationBubbleEntity navigationBubbleEntity = this.f11333g;
                    if (currentTimeMillis < navigationBubbleEntity.startTime || currentTimeMillis > navigationBubbleEntity.endTime) {
                        return;
                    }
                    NavigationBarUtil.putBubbleEntityToSp(navigationBubbleEntity, NavigationBarUtil.ICON_KEY);
                    NavigationBarUtil.showAngleByIcon(this.f11334h, this.f11333g.angleText);
                    NavigationButton navigationButton = this.f11334h;
                    String absolutePath = this.f11335i.getAbsolutePath();
                    NavigationBubbleEntity navigationBubbleEntity2 = this.f11333g;
                    navigationButton.playLottieFromBubble(absolutePath, navigationBubbleEntity2.iconSize, this.f11336j, navigationBubbleEntity2.bucketType);
                    NavigationBarUtil.recordMaterialShow(this.f11333g);
                    return;
                }
                return;
            }
            NavigationBarUtil.clearEffectDirectory(this.f11337k);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
        public void onPause() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* loaded from: classes4.dex */
    public class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ NavigationBubbleEntity f11338g;

        /* renamed from: h */
        final /* synthetic */ StateController f11339h;

        /* renamed from: i */
        final /* synthetic */ String f11340i;

        /* renamed from: j */
        final /* synthetic */ NavigationButton f11341j;

        c(NavigationBubbleEntity navigationBubbleEntity, StateController stateController, String str, NavigationButton navigationButton) {
            this.f11338g = navigationBubbleEntity;
            this.f11339h = stateController;
            this.f11340i = str;
            this.f11341j = navigationButton;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!TextUtils.isEmpty(this.f11338g.angleText)) {
                StateController stateController = this.f11339h;
                String str = this.f11340i;
                NavigationBubbleEntity navigationBubbleEntity = this.f11338g;
                stateController.setButtonLabel(str, navigationBubbleEntity.id, navigationBubbleEntity.impr, navigationBubbleEntity.bucketType);
            }
            this.f11341j.setRedPointDefault();
        }
    }

    /* loaded from: classes4.dex */
    public class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ StateController f11342g;

        /* renamed from: h */
        final /* synthetic */ NavigationBubbleEntity f11343h;

        /* renamed from: i */
        final /* synthetic */ NavigationButton f11344i;

        d(StateController stateController, NavigationBubbleEntity navigationBubbleEntity, NavigationButton navigationButton) {
            this.f11342g = stateController;
            this.f11343h = navigationBubbleEntity;
            this.f11344i = navigationButton;
        }

        @Override // java.lang.Runnable
        public void run() {
            StateController stateController = this.f11342g;
            NavigationBubbleEntity navigationBubbleEntity = this.f11343h;
            stateController.setButtonLabel("", navigationBubbleEntity.id, navigationBubbleEntity.impr, navigationBubbleEntity.bucketType);
            NavigationConfig navigationConfig = new NavigationConfig(this.f11344i.getNavigationId(), com.jingdong.app.mall.navigationbar.c.G().F(this.f11344i.getNavigationId()), "", this.f11343h.bucketType);
            navigationConfig.setCurrentMode(com.jingdong.app.mall.navigationbar.e.l().f11385c);
            NavigationBase.getInstance().setOrUpdateNavigationConfig(navigationConfig);
        }
    }

    /* loaded from: classes4.dex */
    public class e implements Runnable {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.navigationbar.f.a f11345g;

        /* renamed from: h */
        final /* synthetic */ NavigationBubbleEntity f11346h;

        e(com.jingdong.app.mall.navigationbar.f.a aVar, NavigationBubbleEntity navigationBubbleEntity) {
            this.f11345g = aVar;
            this.f11346h = navigationBubbleEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f11345g.showNavigationBubble(true, this.f11346h, true);
        }
    }

    /* loaded from: classes4.dex */
    public class f implements Runnable {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.navigationbar.f.a f11347g;

        /* renamed from: h */
        final /* synthetic */ NavigationBubbleEntity f11348h;

        f(com.jingdong.app.mall.navigationbar.f.a aVar, NavigationBubbleEntity navigationBubbleEntity) {
            this.f11347g = aVar;
            this.f11348h = navigationBubbleEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f11347g.showNavigationBubble(true, this.f11348h, false);
        }
    }

    /* loaded from: classes4.dex */
    public class g implements HttpGroup.OnAllAndPauseListener {

        /* renamed from: g */
        final /* synthetic */ NavigationBubbleEntity f11349g;

        /* renamed from: h */
        final /* synthetic */ com.jingdong.app.mall.navigationbar.f.b f11350h;

        /* renamed from: i */
        final /* synthetic */ String f11351i;

        g(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.b bVar, String str) {
            this.f11349g = navigationBubbleEntity;
            this.f11350h = bVar;
            this.f11351i = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            File saveFile = httpResponse.getSaveFile();
            if (saveFile == null || !saveFile.exists()) {
                return;
            }
            String md5 = FileUtils.getMD5(saveFile.getAbsolutePath());
            if (Log.D) {
                String str = NavigationBarUtil.TAG;
                Log.d(str, "handleEffect_\u4e0b\u8f7d_onEnd=" + Thread.currentThread().getName());
                Log.d(str, "handleEffect_onEnd-localLottieMD5=" + md5 + " file.getAbsolutePath()=" + saveFile.getAbsolutePath());
            }
            if (!TextUtils.isEmpty(md5) && this.f11349g.lottieUrlMd5.equals(md5)) {
                NavigationBarUtil.dealEffectRule(this.f11349g, this.f11350h, saveFile.getAbsolutePath());
            } else {
                NavigationBarUtil.clearEffectDirectory(this.f11351i);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
        public void onPause() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* loaded from: classes4.dex */
    public class h implements Runnable {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.navigationbar.f.b f11352g;

        /* renamed from: h */
        final /* synthetic */ String f11353h;

        /* renamed from: i */
        final /* synthetic */ NavigationBubbleEntity f11354i;

        h(com.jingdong.app.mall.navigationbar.f.b bVar, String str, NavigationBubbleEntity navigationBubbleEntity) {
            this.f11352g = bVar;
            this.f11353h = str;
            this.f11354i = navigationBubbleEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f11352g.showNavigationEffect(true, this.f11353h, this.f11354i.triggerMode);
        }
    }

    /* loaded from: classes4.dex */
    public class i implements Runnable {

        /* renamed from: g */
        final /* synthetic */ NavigationButton f11355g;

        i(NavigationButton navigationButton) {
            this.f11355g = navigationButton;
        }

        @Override // java.lang.Runnable
        public void run() {
            TabShowNew tabShowNew = this.f11355g.getTabShowNew();
            if (tabShowNew != null) {
                tabShowNew.setIsShowRedPoint(Boolean.valueOf(NavigationBarUtil.showRedPointSwitch()));
            }
            NavigationConfig navigationConfig = new NavigationConfig(this.f11355g.getNavigationId(), "find", NavigationBarUtil.showRedPointSwitch());
            navigationConfig.setCurrentMode(com.jingdong.app.mall.navigationbar.e.l().f11385c);
            NavigationBase.getInstance().setOrUpdateNavigationConfig(navigationConfig);
        }
    }

    public static boolean angleSwitch() {
        return TextUtils.equals(SharedPreferencesUtil.getString(SP_ANGLE_SWITCH, ""), "1");
    }

    public static void changeTabView() {
        NavigationButton currentButtonByFunctionId;
        if (ICON_APP_FLAG) {
            return;
        }
        ICON_APP_FLAG = true;
        NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
        if (JDElderModeUtils.isElderMode() || L == null || !L.canShowIcon() || (currentButtonByFunctionId = getCurrentButtonByFunctionId(L.position)) == null) {
            return;
        }
        NaviBubbleSp naviBubbleFromCache = getNaviBubbleFromCache(ICON_KEY);
        if (naviBubbleFromCache == null) {
            changeView(L, currentButtonByFunctionId);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(naviBubbleFromCache.id) && naviBubbleFromCache.id.equals(L.id)) {
            if (isCanShowMaterial(L, currentTimeMillis)) {
                changeView(L, currentButtonByFunctionId);
            }
        } else if (!L.isCloseAntiInterference) {
            if (currentTimeMillis - naviBubbleFromCache.lastShowTime < oneDaySeconds) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (simpleDateFormat.format(new Date(currentTimeMillis)).equals(simpleDateFormat.format(new Date(naviBubbleFromCache.lastShowTime)))) {
                    return;
                }
                changeView(L, currentButtonByFunctionId);
                return;
            }
            changeView(L, currentButtonByFunctionId);
        } else {
            changeView(L, currentButtonByFunctionId);
        }
    }

    public static void changeView(NavigationBubbleEntity navigationBubbleEntity, NavigationButton navigationButton) {
        String str;
        if (navigationBubbleEntity == null || navigationButton == null) {
            return;
        }
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("name", (Object) (TextUtils.isEmpty(navigationBubbleEntity.position) ? "" : navigationBubbleEntity.position));
            String str2 = "-100";
            jDJSONObject.put("sourceid", (Object) (TextUtils.isEmpty(navigationBubbleEntity.id) ? "-100" : navigationBubbleEntity.id));
            jDJSONObject.put("marketpic", (Object) (TextUtils.isEmpty(navigationBubbleEntity.iconUrl) ? "-100" : navigationBubbleEntity.iconUrl.endsWith(FileService.CACHE_EXT_NAME_JSON) ? "1" : "0"));
            if (TextUtils.isEmpty(navigationBubbleEntity.iconSize + "")) {
                str = "-100";
            } else {
                str = navigationBubbleEntity.iconSize + "";
            }
            jDJSONObject.put("bigmarketpic", (Object) str);
            jDJSONObject.put("type", (Object) "1");
            jDJSONObject.put("iconstyle", (Object) (navigationBubbleEntity.shapeType + ""));
            jDJSONObject.put("animationstyle", (Object) (navigationBubbleEntity.dynamicType + ""));
            if (!TextUtils.isEmpty(navigationBubbleEntity.impr)) {
                str2 = navigationBubbleEntity.impr;
            }
            jDJSONObject.put("biinfo", (Object) str2);
            jDJSONObject.put("isshow", (Object) (navigationBubbleEntity.bucketType + ""));
            String str3 = navigationBubbleEntity.iconUrl;
            if (str3 != null) {
                if (str3.endsWith(FileService.CACHE_EXT_NAME_JSON)) {
                    downIconLottie(navigationBubbleEntity, navigationButton, jDJSONObject.toJSONString());
                } else {
                    JDImageUtils.loadImage(navigationBubbleEntity.iconUrl, new a(navigationBubbleEntity, navigationButton, jDJSONObject));
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                Log.e("changeTabView", "changeView=" + e2.toString());
            }
        }
    }

    public static void clearEffectDirectory(String str) {
        File[] listFiles;
        try {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    if (file2.exists() && file2.isFile()) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void dealEffectRule(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.b bVar, String str) {
        NaviBubbleSp naviBubbleFromCache = getNaviBubbleFromCache(EFFECT_KEY);
        if (naviBubbleFromCache == null) {
            showNavigationEffect(navigationBubbleEntity, bVar, str);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(naviBubbleFromCache.id) && naviBubbleFromCache.id.equals(navigationBubbleEntity.id)) {
            if (!TextUtils.isEmpty(naviBubbleFromCache.dataVersion) && naviBubbleFromCache.dataVersion.equals(navigationBubbleEntity.dataVersion)) {
                if (currentTimeMillis >= navigationBubbleEntity.startTime && currentTimeMillis <= navigationBubbleEntity.endTime && currentTimeMillis > naviBubbleFromCache.endNotShowTime) {
                    showNavigationEffect(navigationBubbleEntity, bVar, str);
                    return;
                } else {
                    notShowNavigationEffect(bVar);
                    return;
                }
            }
            showNavigationEffect(navigationBubbleEntity, bVar, str);
            return;
        }
        showNavigationEffect(navigationBubbleEntity, bVar, str);
    }

    private static void downIconLottie(NavigationBubbleEntity navigationBubbleEntity, NavigationButton navigationButton, String str) {
        try {
            if (TextUtils.isEmpty(navigationBubbleEntity.iconUrl) || TextUtils.isEmpty(navigationBubbleEntity.iconUrlMd5)) {
                return;
            }
            String str2 = navigationBubbleEntity.iconUrl.hashCode() + "";
            if (Log.D) {
                Log.d(TAG, "downIconLottie_fileName=" + str2);
            }
            String str3 = JdSdk.getInstance().getApplication().getFilesDir().getAbsolutePath() + "/" + ICON_DIR;
            if (Log.D) {
                Log.d(TAG, "downIconLottie_localLottiePath=" + str3);
            }
            File file = new File(str3 + "/" + str2);
            if (file.exists()) {
                if (Log.D) {
                    Log.d(TAG, "downIconLottie_localLottieFile=" + file.getAbsolutePath());
                }
                if (navigationButton != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis >= navigationBubbleEntity.startTime && currentTimeMillis <= navigationBubbleEntity.endTime) {
                        putBubbleEntityToSp(navigationBubbleEntity, ICON_KEY);
                        showAngleByIcon(navigationButton, navigationBubbleEntity.angleText);
                        navigationButton.playLottieFromBubble(file.getAbsolutePath(), navigationBubbleEntity.iconSize, str, navigationBubbleEntity.bucketType);
                        recordMaterialShow(navigationBubbleEntity);
                        return;
                    }
                    return;
                }
                return;
            }
            clearEffectDirectory(str3);
            FileGuider fileGuider = new FileGuider();
            fileGuider.setSpace(1);
            fileGuider.setChildDirName(ICON_DIR);
            fileGuider.setImmutable(false);
            fileGuider.setFileName(str2);
            fileGuider.setMode(1);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(navigationBubbleEntity.iconUrl);
            httpSetting.setCacheMode(2);
            httpSetting.setListener(new b(navigationBubbleEntity, navigationButton, file, str, str3));
            httpSetting.setType(500);
            httpSetting.setSavePath(fileGuider);
            httpSetting.setBreakpointTransmission(false);
            httpSetting.setAttempts(1);
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception unused) {
        }
    }

    public static int getBubbleState() {
        MainFrameActivity b2 = com.jingdong.app.mall.n.a.a().b();
        return b2 != null ? b2.getBubbleState() ? 1 : 0 : com.jingdong.app.mall.navigationbar.c.G().L() == null ? -1 : 0;
    }

    public static NavigationButton getCurrentButtonByFunctionId(String str) {
        List<NavigationButton> list;
        if (TextUtils.isEmpty(str) || (list = NavigationBase.getInstance().buttons) == null || list.size() <= 0) {
            return null;
        }
        NavigationButton navigationButton = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (com.jingdong.app.mall.navigationbar.c.G().M(str, null) == list.get(i2).getNavigationId()) {
                navigationButton = list.get(i2);
            }
        }
        return navigationButton;
    }

    private static NaviBubbleSp getNaviBubbleFromCache(String str) {
        try {
            String string = SharedPreferencesUtil.getString(str, null);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            String[] split = string.split("#");
            if (split.length < 7) {
                return null;
            }
            NaviBubbleSp naviBubbleSp = new NaviBubbleSp();
            naviBubbleSp.id = split[0];
            naviBubbleSp.dataVersion = split[1];
            naviBubbleSp.startTime = parseStringToLong(split[2]);
            naviBubbleSp.endTime = parseStringToLong(split[3]);
            naviBubbleSp.beginNotShowTime = parseStringToLong(split[4]);
            naviBubbleSp.endNotShowTime = parseStringToLong(split[5]);
            naviBubbleSp.lastShowTime = parseStringToLong(split[6]);
            return naviBubbleSp;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(new Exception("getNaviBubbleFromCache-null-" + e2));
            return null;
        }
    }

    public static String getShowRedPointSwitchSp() {
        return showRedPointSwitch;
    }

    private static int handleDetail(boolean z, NavigationBubbleEntity navigationBubbleEntity) {
        MainFrameActivity b2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if ((MainFrameActivity.W && z) || JDElderModeUtils.isElderMode() || (b2 = com.jingdong.app.mall.n.a.a().b()) == null) {
            return 0;
        }
        if (!z) {
            int i8 = navigationBubbleEntity.bubbleType;
            if (i8 == 0) {
                notShow(navigationBubbleEntity, b2);
            } else if (i8 == 1 || i8 == 2 || (i8 == 4 && ((i6 = navigationBubbleEntity.combinationTypes) == 2 || i6 == 3))) {
                notShowNew(navigationBubbleEntity, b2);
            }
            return 0;
        } else if (getBubbleState() == 1) {
            return 1;
        } else {
            if (b2.getBubbleShowIndex(navigationBubbleEntity.position) == -1) {
                return 0;
            }
            NaviBubbleSp naviBubbleFromCache = getNaviBubbleFromCache(BUBBLE_KEY);
            if (naviBubbleFromCache == null) {
                int i9 = navigationBubbleEntity.bubbleType;
                if (i9 == 0) {
                    show(navigationBubbleEntity, b2);
                    return 1;
                }
                if (i9 != 1 && i9 != 2) {
                    if (i9 != 4) {
                        return 1;
                    }
                    int i10 = navigationBubbleEntity.combinationTypes;
                    if (i10 != 2 && i10 != 3) {
                        return 1;
                    }
                }
                showNew(navigationBubbleEntity, b2);
                return 1;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!TextUtils.isEmpty(naviBubbleFromCache.id) && naviBubbleFromCache.id.equals(navigationBubbleEntity.id)) {
                if (isCanShowMaterial(navigationBubbleEntity, currentTimeMillis)) {
                    int i11 = navigationBubbleEntity.bubbleType;
                    if (i11 == 0) {
                        show(navigationBubbleEntity, b2);
                    } else if (i11 == 1 || i11 == 2 || (i11 == 4 && ((i5 = navigationBubbleEntity.combinationTypes) == 2 || i5 == 3))) {
                        showNew(navigationBubbleEntity, b2);
                    }
                    i7 = 1;
                    return i7;
                }
                int i12 = navigationBubbleEntity.bubbleType;
                if (i12 == 0) {
                    notShow(navigationBubbleEntity, b2);
                } else if (i12 == 1 || i12 == 2 || (i12 == 4 && ((i4 = navigationBubbleEntity.combinationTypes) == 2 || i4 == 3))) {
                    notShowNew(navigationBubbleEntity, b2);
                }
                return i7;
            } else if (!navigationBubbleEntity.isCloseAntiInterference) {
                if (currentTimeMillis - naviBubbleFromCache.lastShowTime < oneDaySeconds) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (simpleDateFormat.format(new Date(currentTimeMillis)).equals(simpleDateFormat.format(new Date(naviBubbleFromCache.lastShowTime)))) {
                        int i13 = navigationBubbleEntity.bubbleType;
                        if (i13 == 0) {
                            notShow(navigationBubbleEntity, b2);
                        } else if (i13 == 1 || i13 == 2 || (i13 == 4 && ((i3 = navigationBubbleEntity.combinationTypes) == 2 || i3 == 3))) {
                            notShowNew(navigationBubbleEntity, b2);
                        }
                        return i7;
                    }
                    int i14 = navigationBubbleEntity.bubbleType;
                    if (i14 == 0) {
                        show(navigationBubbleEntity, b2);
                    } else if (i14 == 1 || i14 == 2 || (i14 == 4 && ((i2 = navigationBubbleEntity.combinationTypes) == 2 || i2 == 3))) {
                        showNew(navigationBubbleEntity, b2);
                    }
                    i7 = 1;
                    return i7;
                }
                int i15 = navigationBubbleEntity.bubbleType;
                if (i15 == 0) {
                    show(navigationBubbleEntity, b2);
                    return 1;
                }
                if (i15 != 1 && i15 != 2) {
                    if (i15 != 4) {
                        return 1;
                    }
                    int i16 = navigationBubbleEntity.combinationTypes;
                    if (i16 != 2 && i16 != 3) {
                        return 1;
                    }
                }
                showNew(navigationBubbleEntity, b2);
                return 1;
            } else {
                int i17 = navigationBubbleEntity.bubbleType;
                if (i17 == 0) {
                    show(navigationBubbleEntity, b2);
                    return 1;
                }
                if (i17 != 1 && i17 != 2) {
                    if (i17 != 4) {
                        return 1;
                    }
                    int i18 = navigationBubbleEntity.combinationTypes;
                    if (i18 != 2 && i18 != 3) {
                        return 1;
                    }
                }
                showNew(navigationBubbleEntity, b2);
                return 1;
            }
        }
    }

    public static void handleEffect(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.b bVar) {
        try {
            if (Log.D) {
                Log.d(TAG, "handleEffect_isElderMode=" + JDElderModeUtils.isElderMode());
            }
            if (navigationBubbleEntity == null || JDElderModeUtils.isElderMode() || TextUtils.isEmpty(navigationBubbleEntity.lottieUrl) || TextUtils.isEmpty(navigationBubbleEntity.lottieUrlMd5)) {
                return;
            }
            String str = navigationBubbleEntity.lottieUrl.hashCode() + "";
            if (Log.D) {
                Log.d(TAG, "handleEffect_fileName=" + str);
            }
            String str2 = JdSdk.getInstance().getApplication().getFilesDir().getAbsolutePath() + "/" + EFFECT_DIR;
            if (Log.D) {
                Log.d(TAG, "handleEffect_localLottiePath=" + str2);
            }
            File file = new File(str2 + "/" + str);
            if (file.exists()) {
                if (Log.D) {
                    Log.d(TAG, "handleEffect_localLottieFile=" + file.getAbsolutePath());
                }
                dealEffectRule(navigationBubbleEntity, bVar, file.getAbsolutePath());
                return;
            }
            clearEffectDirectory(str2);
            FileGuider fileGuider = new FileGuider();
            fileGuider.setSpace(1);
            fileGuider.setChildDirName(EFFECT_DIR);
            fileGuider.setImmutable(false);
            fileGuider.setFileName(str);
            fileGuider.setMode(1);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(navigationBubbleEntity.lottieUrl);
            httpSetting.setCacheMode(2);
            httpSetting.setListener(new g(navigationBubbleEntity, bVar, str2));
            httpSetting.setType(500);
            httpSetting.setSavePath(fileGuider);
            httpSetting.setBreakpointTransmission(false);
            httpSetting.setAttempts(1);
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception unused) {
        }
    }

    public static void handleFrequencyRule(NavigationFrequencyRuleResult navigationFrequencyRuleResult) {
        if (navigationFrequencyRuleResult == null) {
            return;
        }
        if (Log.D) {
            Log.d(TAG, "handleFrequencyRule=" + Thread.currentThread().getName());
        }
        NavigationDBController.insertOrUpdateFrequencyRuleData(navigationFrequencyRuleResult.result);
    }

    public static int handleNewBubble(boolean z) {
        try {
            NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
            if (L == null) {
                return -1;
            }
            if ("0".equals(L.bubbleSwitch)) {
                return getBubbleState();
            }
            int handleDetail = handleDetail(z, L);
            if (isCanShowBubbleAndIcon && z && handleDetail == 1) {
                changeTabView();
                isCanShowBubbleAndIcon = false;
            }
            return handleDetail;
        } catch (Exception e2) {
            if (OKLog.E) {
                Log.e("handleNewBubble", "bubble=" + e2.toString());
                return 0;
            }
            return 0;
        }
    }

    public static int handleNewBubbleByMySelf(boolean z) {
        try {
            NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
            if (L == null) {
                return -1;
            }
            if (z || L.bubbleType != 0) {
                return handleDetail(z, L);
            }
            return 0;
        } catch (Exception e2) {
            if (OKLog.E) {
                Log.e("handleNewBubbleByMySelf", "bubble=" + e2.toString());
                return 0;
            }
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0057 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isAuraLoadSuccess(String str) {
        Fragment fragment;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean contains = NAVIGATION_FRAGMENT_VALID_CACHE.contains(str);
        if (Log.D) {
            Log.d(TAG, "isAuraLoadSuccess-NAVIGATION_FRAGMENT_VALID_CACHE=" + NAVIGATION_FRAGMENT_VALID_CACHE + " ifContainClassName=" + contains);
        }
        if (contains) {
            return true;
        }
        Fragment fragment2 = null;
        try {
            fragment = (Fragment) JdSdk.getInstance().getApplication().getClassLoader().loadClass(str).newInstance();
            try {
                NAVIGATION_FRAGMENT_VALID_CACHE.add(str);
            } catch (Exception unused) {
                fragment2 = fragment;
                fragment = fragment2;
                if (fragment == null) {
                }
            }
        } catch (Exception unused2) {
        }
        return fragment == null;
    }

    private static boolean isCanShowMaterial(NavigationBubbleEntity navigationBubbleEntity, long j2) {
        int i2;
        List<NavigationMaterialShowEntity> queryMaterialShowDataByIdAndEffectiveTime;
        List<NavigationMaterialShowEntity> queryMaterialShowDataByIdAndTime;
        List<NavigationMaterialShowEntity> queryMaterialShowDataByIdAndTime2;
        List<NavigationMaterialShowEntity> queryMaterialShowDataByIdAndTime3;
        int i3 = navigationBubbleEntity.customDays;
        boolean z = true;
        if (i3 <= 0 || navigationBubbleEntity.customTimes <= 0 ? (i2 = navigationBubbleEntity.frequency) != always && (i2 != oneDay ? !(i2 != oneWeek ? (i2 == -1 || i2 == 3) && ((queryMaterialShowDataByIdAndEffectiveTime = NavigationDBController.queryMaterialShowDataByIdAndEffectiveTime(navigationBubbleEntity.id, navigationBubbleEntity.startTime, navigationBubbleEntity.endTime)) == null || queryMaterialShowDataByIdAndEffectiveTime.size() <= 0) : (queryMaterialShowDataByIdAndTime = NavigationDBController.queryMaterialShowDataByIdAndTime(navigationBubbleEntity.id, j2 - oneWeekSeconds)) == null || queryMaterialShowDataByIdAndTime.size() <= 0) : !((queryMaterialShowDataByIdAndTime2 = NavigationDBController.queryMaterialShowDataByIdAndTime(navigationBubbleEntity.id, j2 - oneDaySeconds)) == null || queryMaterialShowDataByIdAndTime2.size() <= 0)) : (queryMaterialShowDataByIdAndTime3 = NavigationDBController.queryMaterialShowDataByIdAndTime(navigationBubbleEntity.id, j2 - (i3 * oneDaySeconds))) == null || queryMaterialShowDataByIdAndTime3.size() >= navigationBubbleEntity.customTimes) {
            z = false;
        }
        if (Log.D) {
            Log.d(TAG, "isCanShowMaterial=" + z);
        }
        return z;
    }

    public static boolean isCombination(NavigationBubbleEntity navigationBubbleEntity) {
        int i2;
        return navigationBubbleEntity != null && navigationBubbleEntity.bubbleType == 4 && ((i2 = navigationBubbleEntity.combinationTypes) == 1 || i2 == 2 || i2 == 3);
    }

    public static boolean isIconCom() {
        NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
        return L != null && L.bubbleType == 4 && L.combinationTypes > 0;
    }

    private static void notShow(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.a aVar) {
        if (aVar != null) {
            aVar.showNavigationBubble(false, navigationBubbleEntity, false);
        }
    }

    private static void notShowNavigationEffect(com.jingdong.app.mall.navigationbar.f.b bVar) {
        if (bVar != null) {
            bVar.showNavigationEffect(false, "", 0);
        }
    }

    private static void notShowNew(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.a aVar) {
        if (aVar != null) {
            aVar.showNavigationBubble(false, navigationBubbleEntity, true);
        }
    }

    private static long parseStringToLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static void putBubbleEntityToSp(NavigationBubbleEntity navigationBubbleEntity, String str) {
        long j2;
        long j3;
        long j4;
        if (navigationBubbleEntity == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(navigationBubbleEntity.id);
        sb.append("#");
        sb.append(navigationBubbleEntity.dataVersion);
        sb.append("#");
        sb.append(navigationBubbleEntity.startTime);
        sb.append("#");
        sb.append(navigationBubbleEntity.endTime);
        sb.append("#");
        int i2 = navigationBubbleEntity.frequency;
        long j5 = 0;
        if (i2 == always) {
            j4 = navigationBubbleEntity.startTime;
        } else if (i2 == oneDay) {
            j5 = navigationBubbleEntity.startTime;
            while (j5 < currentTimeMillis) {
                j5 += oneDaySeconds;
            }
            j4 = j5 - oneDaySeconds;
        } else {
            if (i2 == oneWeek) {
                long j6 = navigationBubbleEntity.startTime;
                while (j6 < currentTimeMillis) {
                    j6 += oneDaySeconds;
                }
                j2 = j6 - oneDaySeconds;
                j3 = oneWeekSeconds + j2;
            } else if (i2 == -1 || i2 == 3) {
                j2 = navigationBubbleEntity.startTime;
                j3 = -1;
            } else {
                j4 = 0;
            }
            long j7 = j3;
            j4 = j2;
            j5 = j7;
        }
        long j8 = navigationBubbleEntity.endTime;
        if (j5 > j8) {
            j5 = j8;
        }
        sb.append(j4);
        sb.append("#");
        sb.append(j5);
        sb.append("#");
        sb.append(currentTimeMillis);
        SharedPreferencesUtil.putString(str, sb.toString());
    }

    public static void recordMaterialShow(NavigationBubbleEntity navigationBubbleEntity) {
        if (navigationBubbleEntity != null) {
            NavigationMaterialShowEntity navigationMaterialShowEntity = new NavigationMaterialShowEntity();
            if (isCombination(navigationBubbleEntity) && isHaveRecordCombination) {
                return;
            }
            isHaveRecordCombination = true;
            navigationMaterialShowEntity.material_id = navigationBubbleEntity.id;
            navigationMaterialShowEntity.material_type = navigationBubbleEntity.bubbleType;
            int i2 = navigationBubbleEntity.combinationTypes;
            if (i2 == -1 || i2 == 0) {
                i2 = -1;
            }
            navigationMaterialShowEntity.combination_type = i2;
            navigationMaterialShowEntity.material_position = navigationBubbleEntity.position;
            navigationMaterialShowEntity.material_show_time = System.currentTimeMillis();
            NavigationDBController.insertOrUpdateMaterialShowData(navigationMaterialShowEntity);
        }
    }

    public static boolean redPointSwitch() {
        return TextUtils.equals(SharedPreferencesUtil.getString(SP_RED_POINT_SWITCH, ""), "1");
    }

    public static void setAngleSwitch(String str) {
        SharedPreferencesUtil.putString(SP_ANGLE_SWITCH, str);
    }

    public static void setRedPointSwitch(String str) {
        SharedPreferencesUtil.putString(SP_RED_POINT_SWITCH, str);
    }

    public static void setShowRedPointSwitch(String str) {
        showRedPointSwitch = str;
    }

    private static void show(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.a aVar) {
        if (navigationBubbleEntity == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < navigationBubbleEntity.startTime || currentTimeMillis > navigationBubbleEntity.endTime) {
            return;
        }
        putBubbleEntityToSp(navigationBubbleEntity, BUBBLE_KEY);
        if (aVar != null) {
            new Handler(Looper.getMainLooper()).postDelayed(new f(aVar, navigationBubbleEntity), navigationBubbleEntity.showTiming);
        }
    }

    public static boolean showAngle(NavigationButton navigationButton, String str) {
        NavigationBubbleEntity L;
        if (navigationButton == null || (L = com.jingdong.app.mall.navigationbar.c.G().L()) == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < L.startTime || currentTimeMillis > L.endTime) {
            return false;
        }
        putBubbleEntityToSp(L, BUBBLE_KEY);
        StateController stateController = navigationButton.getStateController();
        stateController.lablePosition = L.position;
        if (stateController != null) {
            String buttonLabel = stateController.getButtonLabel();
            if (Log.D) {
                Log.d(TAG, "showAngle-entity=" + L + " temp=" + buttonLabel + " label=" + str);
            }
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(buttonLabel)) {
                return false;
            }
            if (L.dynamicType == 2 && !TextUtils.isEmpty(str)) {
                NavigationParam navigationParam = new NavigationParam();
                navigationParam.dynamicType = L.dynamicType;
                navigationParam.shapeType = L.shapeType;
                navigationParam.angleText = L.angleText;
                navigationParam.bucketType = L.bucketType;
                navigationButton.setRedPointNavigationParam(navigationParam);
                new Handler(Looper.getMainLooper()).post(new c(L, stateController, str, navigationButton));
            } else {
                NavigationParam navigationParam2 = new NavigationParam();
                navigationParam2.dynamicType = L.dynamicType;
                navigationParam2.shapeType = L.shapeType;
                navigationParam2.angleText = L.angleText;
                navigationParam2.bucketType = L.bucketType;
                stateController.setNavigationParam(navigationParam2);
                stateController.setButtonLabel(str, L.id, L.impr, L.bucketType);
            }
            recordMaterialShow(L);
            if (L.bubbleType == 3) {
                Handler handler = new Handler(Looper.getMainLooper());
                long j2 = L.disappearTiming;
                if (j2 > 0) {
                    if (L.dynamicType == 2 && !TextUtils.isEmpty(str)) {
                        j2 = j2 + NavigationConstants.ANIMATION_TIME_ICON_ROTATION + NavigationConstants.ANIMATION_TIME_ICON_SCALE;
                    }
                    handler.postDelayed(new d(stateController, L, navigationButton), j2);
                }
            }
        }
        NavigationConfig navigationConfig = new NavigationConfig(navigationButton.getNavigationId(), com.jingdong.app.mall.navigationbar.c.G().F(navigationButton.getNavigationId()), str, L.bucketType);
        navigationConfig.setCurrentMode(com.jingdong.app.mall.navigationbar.e.l().f11385c);
        NavigationBase.getInstance().setOrUpdateNavigationConfig(navigationConfig);
        return true;
    }

    public static boolean showAngleByIcon(NavigationButton navigationButton, String str) {
        if (showIconAndAngle) {
            showIconAndAngle = false;
            return showAngle(navigationButton, str);
        }
        return false;
    }

    public static void showIconAndAngle() {
        if (ICON_ANGLE_FLAG) {
            return;
        }
        ICON_ANGLE_FLAG = true;
        NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
        if (L != null && angleSwitch() && !TextUtils.isEmpty(L.angleText) && L.canShowIcon()) {
            if (UnCustomThemeHelper.getInstance().getSkinType() == 0 || UnCustomThemeHelper.getInstance().getSkinType() == 2) {
                showIconAndAngle = true;
                changeTabView();
            }
        }
    }

    public static void showIconAndBubble() {
        NavigationBubbleEntity L;
        if (ICON_BUBBLE_FLAG) {
            return;
        }
        ICON_BUBBLE_FLAG = true;
        if ((UnCustomThemeHelper.getInstance().getSkinType() == 0 || UnCustomThemeHelper.getInstance().getSkinType() == 2) && (L = com.jingdong.app.mall.navigationbar.c.G().L()) != null && L.canShowBubble() && L.canShowIcon()) {
            if (TextUtils.equals("1", L.bubbleSwitch)) {
                isCanShowBubbleAndIcon = true;
                return;
            }
            handleNewBubbleByMySelf(true);
            changeTabView();
        }
    }

    private static void showNavigationEffect(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.b bVar, String str) {
        if (navigationBubbleEntity == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < navigationBubbleEntity.startTime || currentTimeMillis > navigationBubbleEntity.endTime || navigationBubbleEntity.triggerMode <= 0) {
            return;
        }
        putBubbleEntityToSp(navigationBubbleEntity, EFFECT_KEY);
        if (bVar != null) {
            new Handler(Looper.getMainLooper()).postDelayed(new h(bVar, str, navigationBubbleEntity), navigationBubbleEntity.showTiming);
        }
    }

    private static void showNew(NavigationBubbleEntity navigationBubbleEntity, com.jingdong.app.mall.navigationbar.f.a aVar) {
        if (navigationBubbleEntity == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < navigationBubbleEntity.startTime || currentTimeMillis > navigationBubbleEntity.endTime) {
            return;
        }
        putBubbleEntityToSp(navigationBubbleEntity, BUBBLE_KEY);
        if (aVar != null) {
            new Handler(Looper.getMainLooper()).postDelayed(new e(aVar, navigationBubbleEntity), navigationBubbleEntity.showTiming);
        }
    }

    public static void showOtherAngle() {
        if (Log.D) {
            Log.d(TAG, "showOtherAngle_angleSet" + angleSet.size());
        }
        if (angleSet.size() <= 0) {
            return;
        }
        if (angleSwitch()) {
            angleSet.clear();
            return;
        }
        for (Map.Entry<String, String> entry : angleSet.entrySet()) {
            ((DefaultRouterBuilder) ((DefaultRouterBuilder) JDRouter.to(JDNavigationModule.TAG, "showNavigationLabel").putString("functionId", entry.getKey())).putString(Constant.KEY_PROMOTION_LABEL, entry.getValue())).jump(JdSdk.getInstance().getApplicationContext());
        }
        angleSet.clear();
    }

    public static boolean showRedPointSwitch() {
        return TextUtils.equals(showRedPointSwitch, "1");
    }

    public static void showRedPont() {
        NavigationButton currentButtonByFunctionId;
        Handler handler;
        NaviBubbleSp naviBubbleFromCache;
        if (redPointSwitch() && (currentButtonByFunctionId = getCurrentButtonByFunctionId("find")) != null) {
            if (currentButtonByFunctionId.getStateController() == null || TextUtils.isEmpty(currentButtonByFunctionId.getStateController().getButtonLabel())) {
                NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
                if (L != null && TextUtils.equals(L.position, "find") && angleSwitch() && !TextUtils.isEmpty(L.angleText) && (naviBubbleFromCache = getNaviBubbleFromCache(BUBBLE_KEY)) != null) {
                    if (!((TextUtils.isEmpty(naviBubbleFromCache.dataVersion) || !naviBubbleFromCache.dataVersion.equals(L.dataVersion)) ? false : !isCanShowMaterial(L, System.currentTimeMillis()))) {
                        return;
                    }
                }
                MainFrameActivity b2 = com.jingdong.app.mall.n.a.a().b();
                if (b2 == null || (handler = b2.getHandler()) == null) {
                    return;
                }
                handler.post(new i(currentButtonByFunctionId));
            }
        }
    }

    public static void showAngle() {
        NavigationButton currentButtonByFunctionId;
        if (ANGLE_APP_FLAG) {
            return;
        }
        ANGLE_APP_FLAG = true;
        NavigationBubbleEntity L = com.jingdong.app.mall.navigationbar.c.G().L();
        if (L == null || !angleSwitch() || (currentButtonByFunctionId = getCurrentButtonByFunctionId(L.position)) == null) {
            return;
        }
        NaviBubbleSp naviBubbleFromCache = getNaviBubbleFromCache(BUBBLE_KEY);
        if (naviBubbleFromCache == null) {
            showAngle(currentButtonByFunctionId, L.angleText);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(naviBubbleFromCache.id) && naviBubbleFromCache.id.equals(L.id)) {
            if (isCanShowMaterial(L, currentTimeMillis)) {
                showAngle(currentButtonByFunctionId, L.angleText);
            }
        } else if (!L.isCloseAntiInterference) {
            if (currentTimeMillis - naviBubbleFromCache.lastShowTime < oneDaySeconds) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (simpleDateFormat.format(new Date(currentTimeMillis)).equals(simpleDateFormat.format(new Date(naviBubbleFromCache.lastShowTime)))) {
                    return;
                }
                showAngle(currentButtonByFunctionId, L.angleText);
                return;
            }
            showAngle(currentButtonByFunctionId, L.angleText);
        } else {
            showAngle(currentButtonByFunctionId, L.angleText);
        }
    }
}
