package com.jingdong.app.mall;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.airbnb.lottie.LottieListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.db.AppStateType;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.category.JDNewCategoryFragment;
import com.jingdong.app.mall.common.view.JDCommonHostFragment;
import com.jingdong.app.mall.faxianV2.FaxianMainHostFragment;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.jdvideo.view.JDVideoHostFragment;
import com.jingdong.app.mall.localreminder.ReminderManager;
import com.jingdong.app.mall.messagecenter.view.manager.MessageRefreshHelper;
import com.jingdong.app.mall.navigationbar.JDNavigationFragment;
import com.jingdong.app.mall.navigationbar.NavigationBarUtil;
import com.jingdong.app.mall.navigationbar.entity.NavigationBubbleEntity;
import com.jingdong.app.mall.nfc.NfcIntentHandleActivity;
import com.jingdong.app.mall.personel.home.JDPersonalHostFragment;
import com.jingdong.app.mall.preload.JDPluginPreLoader;
import com.jingdong.app.mall.shopping.JDShopingCartHostFragment;
import com.jingdong.app.mall.update.UpdateInitialization;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.ScrollableTabActivity;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeepLinkMessagePush;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.gray.GrayModelListener;
import com.jingdong.common.gray.JDGrayModelUtils;
import com.jingdong.common.jdreactFramework.JDReactManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdate;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeMyJDModule;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.lbs.GpsChangedReceiver;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.MobileLoginUtil;
import com.jingdong.common.messagecenter.NewBadgeUtil;
import com.jingdong.common.messagecenter.StationMessageUtils;
import com.jingdong.common.network.NetworkSetting;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.ui.address.eu.EuShippingUtils;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationSkinChangeManager;
import com.jingdong.common.unification.navigationbar.NavigationTabLocationEntry;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.unification.navigationbar.newbar.StateController;
import com.jingdong.common.unification.navigationbar.newbar.TabShowNew;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;
import com.jingdong.common.unification.uniwidget.JDLottieAnimationView;
import com.jingdong.common.utils.CacheTimeUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ConfigurationChangedManager;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.FireEyeUtils;
import com.jingdong.common.utils.GlobalInitialization;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.JDNfcAdapterUtil;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.RtcSdkConfig;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.TimerUntil;
import com.jingdong.common.utils.UnLottieUtils;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.personal.AmountValueManager;
import com.jingdong.common.widget.custom.IActivityReenter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.DefaultStyleListener;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetManager;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.IJankCustomInfo;
import com.jingdong.sdk.utils.DPIUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes19.dex */
public class MainFrameActivity extends MyActivity implements ScrollableTabActivity, IMainActivity, com.jingdong.app.mall.navigationbar.f.a, com.jingdong.app.mall.navigationbar.f.b, IJankCustomInfo {
    private static StateController Q;
    private static TabShowNew R;
    private static TabShowNew S;
    private static ArrayList<String> T;
    private static int U;
    private static boolean V;
    public static boolean W;
    private static com.jingdong.app.mall.ad.d X;
    private static TabShowNew Y;
    private static TabShowNew Z;
    private static StateController a0;
    public static boolean b0;
    private int A;
    public JDTabFragment B;
    private GpsChangedReceiver C;
    private TextView D;
    private View E;
    private View F;
    private View G;
    private SimpleDraweeView H;
    private LinearLayout I;
    private View J;
    private View K;
    private boolean P;

    /* renamed from: k */
    private boolean f7590k;

    /* renamed from: l */
    private long f7591l;

    /* renamed from: n */
    private View f7593n;
    private SimpleDraweeView o;
    private ImageView p;
    private JDLottieAnimationView q;
    private View u;
    private View v;
    public BaseFragment y;
    public JDNavigationFragment z;

    /* renamed from: g */
    private final String f7586g = MainFrameActivity.class.getSimpleName();

    /* renamed from: h */
    private boolean f7587h = true;

    /* renamed from: i */
    private Runnable f7588i = null;

    /* renamed from: j */
    private boolean f7589j = false;

    /* renamed from: m */
    private boolean f7592m = false;
    private Runnable r = null;
    private Runnable s = null;
    private com.jingdong.app.mall.nfc.b t = null;
    private boolean w = false;
    private boolean x = false;
    private BroadcastReceiver L = new k();
    private boolean M = true;
    private Runnable N = new l0();
    private Runnable O = new a();

    /* loaded from: classes19.dex */
    class a implements Runnable {
        a() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Log.D) {
                Log.d("Navigation_Bubble", "disappearRunnable");
            }
            MainFrameActivity.this.setNewBubbleViewGone();
        }
    }

    /* loaded from: classes19.dex */
    public class a0 implements DialogInterface.OnKeyListener {
        a0(MainFrameActivity mainFrameActivity) {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
            return true;
        }
    }

    /* loaded from: classes19.dex */
    public class b implements Animator.AnimatorListener {
        b() {
            MainFrameActivity.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            MainFrameActivity.this.E.setVisibility(8);
            MainFrameActivity.this.F.setVisibility(8);
            MainFrameActivity.this.w = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes19.dex */
    public class b0 implements Runnable {
        b0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FileService.clearCacheFiles();
            FileService.clearInternalCacheImages();
        }
    }

    /* loaded from: classes19.dex */
    public class c extends JDSimpleImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ NavigationBubbleEntity f7596g;

        /* renamed from: h */
        final /* synthetic */ float f7597h;

        /* loaded from: classes19.dex */
        class a implements Runnable {

            /* renamed from: g */
            final /* synthetic */ Bitmap f7599g;

            a(Bitmap bitmap) {
                c.this = r1;
                this.f7599g = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    c cVar = c.this;
                    if (cVar.f7596g.bucketType != 1) {
                        MainFrameActivity.this.f7593n.setVisibility(0);
                        if (MainFrameActivity.this.o != null && this.f7599g != null) {
                            MainFrameActivity.this.o.setBackground(new BitmapDrawable(this.f7599g));
                            MainFrameActivity.this.w = true;
                            MainFrameActivity.W = true;
                        }
                        if (MainFrameActivity.this.p != null) {
                            MainFrameActivity.this.p.setVisibility(0);
                        }
                        MainFrameActivity.this.f7593n.setX(c.this.f7597h);
                    }
                    NavigationBarUtil.recordMaterialShow(c.this.f7596g);
                    com.jingdong.app.mall.navigationbar.c.G().f11365g = true;
                    c cVar2 = c.this;
                    MainFrameActivity.this.u0(cVar2.f7596g);
                } catch (Exception e2) {
                    if (Log.D) {
                        Log.d("Navigation_Bubble", e2.toString());
                    }
                }
            }
        }

        c(NavigationBubbleEntity navigationBubbleEntity, float f2) {
            MainFrameActivity.this = r1;
            this.f7596g = navigationBubbleEntity;
            this.f7597h = f2;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            if (Log.D) {
                Log.d("Navigation_Bubble", "bubbleIv_onLoadingComplete_" + Thread.currentThread().getName());
            }
            MainFrameActivity.this.getHandler().post(new a(bitmap));
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            super.onLoadingFailed(str, view, jDFailReason);
            if (Log.D) {
                Log.d("Navigation_Bubble", "bubbleIv_onLoadingFailed_" + Thread.currentThread().getName());
            }
        }
    }

    /* loaded from: classes19.dex */
    public class c0 implements MessageQueue.IdleHandler {

        /* renamed from: g */
        final /* synthetic */ Integer f7601g;

        /* renamed from: h */
        final /* synthetic */ StateController f7602h;

        /* loaded from: classes19.dex */
        class a implements Runnable {
            a() {
                c0.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (Log.D) {
                    Log.d("Navigation", "validatCartIcon -->> carStateController.setNum=" + c0.this.f7601g);
                }
                c0 c0Var = c0.this;
                c0Var.f7602h.setNum(c0Var.f7601g);
                StateController immCarStateController = MainFrameActivity.getImmCarStateController();
                if (immCarStateController != null) {
                    immCarStateController.setNum(c0.this.f7601g);
                }
            }
        }

        c0(Integer num, StateController stateController) {
            this.f7601g = num;
            this.f7602h = stateController;
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            BaseApplication.getHandler().post(new a());
            return false;
        }
    }

    /* loaded from: classes19.dex */
    public class d implements View.OnClickListener {
        d() {
            MainFrameActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MainFrameActivity.this.getThisActivity() != null) {
                JDMtaUtils.onClickWithPageId(MainFrameActivity.this.getThisActivity(), "NavigationBar_BubbleClose", d.class.getSimpleName(), "NavigationBar_Main");
            }
            MainFrameActivity.this.getHandler().removeCallbacks(MainFrameActivity.this.N);
            MainFrameActivity.this.setBubbleViewGone();
        }
    }

    /* loaded from: classes19.dex */
    public class d0 implements DefaultStyleListener {
        d0(MainFrameActivity mainFrameActivity) {
        }

        @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.DefaultStyleListener
        public String getDdStyle(String str) {
            return com.jingdong.app.mall.im.business.i.a(str);
        }

        @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.DefaultStyleListener
        public String getStyle(String str, String str2) {
            return null;
        }
    }

    /* loaded from: classes19.dex */
    public class e implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ NavigationBubbleEntity f7605g;

        e(NavigationBubbleEntity navigationBubbleEntity) {
            MainFrameActivity.this = r1;
            this.f7605g = navigationBubbleEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MainFrameActivity.this.isRepeatClick() || TextUtils.isEmpty(this.f7605g.jumpUrl)) {
                return;
            }
            if (MainFrameActivity.this.getThisActivity() != null) {
                JDMtaUtils.onClickWithPageId(MainFrameActivity.this.getThisActivity(), "NavigationBar_Bubble", e.class.getSimpleName(), "NavigationBar_Main");
            }
            Activity thisActivity = MainFrameActivity.this.getThisActivity();
            NavigationBubbleEntity navigationBubbleEntity = this.f7605g;
            com.jingdong.app.mall.navigationbar.b.b(thisActivity, navigationBubbleEntity.jumpUrl, navigationBubbleEntity.jumpType);
        }
    }

    /* loaded from: classes19.dex */
    public class e0 implements Runnable {
        e0() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainFrameActivity.clearCache();
            NetworkSetting.networkSetting();
            ExceptionReporter.reportNetworkStatisticData();
            UpdateInitialization.getUpdateInitializationInstance().initNetwork();
            DeepLinkMessagePush.startPushService(MainFrameActivity.this, null);
            CacheTimeUtil.queryCacheTime();
            LocManager.getInstance().startLocation();
            CommonBase.getJdSharedPreferences().edit().putInt(Configuration.APP_START_COUNT, CommonBase.getJdSharedPreferences().getInt(Configuration.APP_START_COUNT, 0) + 1).apply();
            MainFrameActivity.this.q0();
            ReminderManager.getInstance().startAlarmReminder();
            MessageRefreshHelper.getInstance().registerMessage();
            com.jingdong.app.mall.l.a.a.e().h();
            com.jingdong.app.mall.utils.g.b();
            com.jingdong.app.mall.i.f.i();
            EuShippingUtils.requestAddress();
        }
    }

    /* loaded from: classes19.dex */
    public class f extends JDSimpleImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ NavigationBubbleEntity f7608g;

        /* renamed from: h */
        final /* synthetic */ boolean f7609h;

        /* renamed from: i */
        final /* synthetic */ NavigationTabLocationEntry f7610i;

        /* renamed from: j */
        final /* synthetic */ int f7611j;

        /* loaded from: classes19.dex */
        class a implements Runnable {

            /* renamed from: g */
            final /* synthetic */ Bitmap f7613g;

            a(Bitmap bitmap) {
                f.this = r1;
                this.f7613g = bitmap;
            }

            /* JADX WARN: Removed duplicated region for block: B:220:0x0199 A[Catch: Exception -> 0x0246, TryCatch #0 {Exception -> 0x0246, blocks: (B:165:0x0002, B:167:0x000c, B:169:0x0016, B:171:0x0020, B:173:0x0024, B:175:0x002d, B:177:0x0066, B:180:0x006c, B:182:0x0070, B:190:0x00bd, B:192:0x00d9, B:194:0x00dd, B:195:0x00ea, B:197:0x00f2, B:199:0x00fa, B:200:0x010b, B:202:0x0120, B:203:0x0144, B:205:0x014c, B:207:0x0154, B:209:0x016a, B:211:0x0179, B:218:0x0195, B:220:0x0199, B:222:0x01d7, B:224:0x01f8, B:226:0x020e, B:223:0x01f2, B:227:0x0218, B:213:0x017e, B:215:0x018c, B:217:0x0193, B:184:0x0089, B:187:0x008f, B:189:0x0093, B:229:0x022f), top: B:236:0x0002 }] */
            /* JADX WARN: Removed duplicated region for block: B:227:0x0218 A[Catch: Exception -> 0x0246, TryCatch #0 {Exception -> 0x0246, blocks: (B:165:0x0002, B:167:0x000c, B:169:0x0016, B:171:0x0020, B:173:0x0024, B:175:0x002d, B:177:0x0066, B:180:0x006c, B:182:0x0070, B:190:0x00bd, B:192:0x00d9, B:194:0x00dd, B:195:0x00ea, B:197:0x00f2, B:199:0x00fa, B:200:0x010b, B:202:0x0120, B:203:0x0144, B:205:0x014c, B:207:0x0154, B:209:0x016a, B:211:0x0179, B:218:0x0195, B:220:0x0199, B:222:0x01d7, B:224:0x01f8, B:226:0x020e, B:223:0x01f2, B:227:0x0218, B:213:0x017e, B:215:0x018c, B:217:0x0193, B:184:0x0089, B:187:0x008f, B:189:0x0093, B:229:0x022f), top: B:236:0x0002 }] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                Bitmap bitmap;
                int i2;
                int i3;
                int i4;
                int i5;
                try {
                    if (MainFrameActivity.this.E == null || MainFrameActivity.this.D == null || MainFrameActivity.this.F == null || (bitmap = this.f7613g) == null) {
                        return;
                    }
                    if (f.this.f7608g.bucketType != 1) {
                        int dip2px = DPIUtil.dip2px(bitmap.getWidth() / 3);
                        int dip2px2 = DPIUtil.dip2px(this.f7613g.getHeight() / 3);
                        MainFrameActivity.this.w = true;
                        MainFrameActivity.W = true;
                        ViewGroup.LayoutParams layoutParams = MainFrameActivity.this.I.getLayoutParams();
                        f fVar = f.this;
                        NavigationBubbleEntity navigationBubbleEntity = fVar.f7608g;
                        int i6 = navigationBubbleEntity.picSeparate;
                        if (i6 == 1 && ((i5 = navigationBubbleEntity.bubbleType) == 2 || (i5 == 4 && navigationBubbleEntity.combinationTypes == 3))) {
                            MainFrameActivity.this.G.setVisibility(0);
                            MainFrameActivity.this.D.setGravity(16);
                        } else if (i6 == 1 && ((i2 = navigationBubbleEntity.bubbleType) == 1 || (i2 == 4 && navigationBubbleEntity.combinationTypes == 2))) {
                            MainFrameActivity.this.G.setVisibility(8);
                            layoutParams.width = dip2px;
                            layoutParams.height = dip2px2;
                            ViewGroup.LayoutParams layoutParams2 = MainFrameActivity.this.D.getLayoutParams();
                            layoutParams2.width = dip2px;
                            layoutParams2.height = dip2px2;
                            MainFrameActivity.this.D.setGravity(17);
                        }
                        MainFrameActivity.this.E.setBackground(new BitmapDrawable(this.f7613g));
                        f fVar2 = f.this;
                        NavigationBubbleEntity navigationBubbleEntity2 = fVar2.f7608g;
                        String str = navigationBubbleEntity2.bubbleTextColor;
                        if (fVar2.f7609h) {
                            str = navigationBubbleEntity2.darkBubbleTextColor;
                        }
                        if (str != null) {
                            MainFrameActivity.this.D.setTextColor(Color.parseColor(str));
                        }
                        NavigationBubbleEntity navigationBubbleEntity3 = f.this.f7608g;
                        if (navigationBubbleEntity3.picSeparate == 1 && !TextUtils.isEmpty(navigationBubbleEntity3.bubbleText)) {
                            MainFrameActivity.this.D.setText(f.this.f7608g.bubbleText);
                        }
                        f fVar3 = f.this;
                        NavigationTabLocationEntry navigationTabLocationEntry = fVar3.f7610i;
                        int i7 = navigationTabLocationEntry.topX;
                        int i8 = navigationTabLocationEntry.topY;
                        int i9 = navigationTabLocationEntry.width;
                        int i10 = (i9 / 2) + i7;
                        if (fVar3.f7608g.picSeparate == 1) {
                            MainFrameActivity.this.F.setX(i10 - DPIUtil.dip2px(6.0f));
                            MainFrameActivity.this.F.setY(i8 - DPIUtil.dip2px(1.0f));
                        }
                        MainFrameActivity.this.E.setY((i8 - dip2px2) + (f.this.f7608g.picSeparate == 0 ? DPIUtil.dip2px(5.0f) : 0));
                        int i11 = f.this.f7611j;
                        if (i11 == 0) {
                            i4 = DPIUtil.dip2px(14.0f) + i7;
                            if (i4 + dip2px + DPIUtil.dip2px(14.0f) <= i7 + i9) {
                                i3 = dip2px / 2;
                                i4 = i10 - i3;
                            }
                            if (i4 >= 0) {
                                MainFrameActivity.this.E.setX(i4);
                                ViewGroup.LayoutParams layoutParams3 = MainFrameActivity.this.E.getLayoutParams();
                                layoutParams3.width = dip2px;
                                layoutParams3.height = dip2px2;
                                AnimatorSet animatorSet = new AnimatorSet();
                                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(MainFrameActivity.this.E, "alpha", 0.0f, 1.0f);
                                f fVar4 = f.this;
                                if (fVar4.f7608g.picSeparate == 1) {
                                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(MainFrameActivity.this.F, "alpha", 0.0f, 1.0f);
                                    animatorSet.setDuration(500L);
                                    animatorSet.play(ofFloat).with(ofFloat2);
                                } else {
                                    animatorSet.setDuration(500L);
                                    animatorSet.play(ofFloat);
                                }
                                animatorSet.start();
                                MainFrameActivity.this.E.setVisibility(0);
                                f fVar5 = f.this;
                                if (fVar5.f7608g.picSeparate == 1) {
                                    MainFrameActivity.this.F.setVisibility(0);
                                }
                            } else {
                                MainFrameActivity.this.E.setVisibility(8);
                                MainFrameActivity.this.F.setVisibility(8);
                                return;
                            }
                        } else if (i11 == 4) {
                            int dip2px3 = ((i9 + i7) - dip2px) - DPIUtil.dip2px(14.0f);
                            i4 = dip2px3 >= i7 + DPIUtil.dip2px(14.0f) ? i10 - (dip2px / 2) : dip2px3;
                            if (i4 >= 0) {
                            }
                        } else {
                            i3 = dip2px / 2;
                            i4 = i10 - i3;
                            if (i4 >= 0) {
                            }
                        }
                    }
                    NavigationBarUtil.recordMaterialShow(f.this.f7608g);
                    com.jingdong.app.mall.navigationbar.c.G().f11365g = true;
                    f fVar6 = f.this;
                    MainFrameActivity.this.u0(fVar6.f7608g);
                } catch (Exception e2) {
                    if (Log.D) {
                        Log.d("Navigation_Bubble", "onLoadingComplete" + e2.toString());
                    }
                }
            }
        }

        f(NavigationBubbleEntity navigationBubbleEntity, boolean z, NavigationTabLocationEntry navigationTabLocationEntry, int i2) {
            MainFrameActivity.this = r1;
            this.f7608g = navigationBubbleEntity;
            this.f7609h = z;
            this.f7610i = navigationTabLocationEntry;
            this.f7611j = i2;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
            if (Log.D) {
                Log.d("Navigation_Bubble", "bubbleIv_onLoadingCancelled_" + Thread.currentThread().getName());
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            if (Log.D) {
                Log.d("Navigation_Bubble", "bubbleIv_onLoadingComplete_" + Thread.currentThread().getName());
                Log.d("Navigation_Bubble", "bubbleIv_onLoadingComplete_" + bitmap.getWidth());
            }
            MainFrameActivity.this.getHandler().post(new a(bitmap));
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            if (Log.D) {
                Log.d("Navigation_Bubble", "bubbleIv_onLoadingFailed_" + Thread.currentThread().getName());
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            if (Log.D) {
                Log.d("Navigation_Bubble", "bubbleIv_onLoadingStarted_" + Thread.currentThread().getName());
            }
        }
    }

    /* loaded from: classes19.dex */
    public class f0 implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f7615g;

        /* renamed from: h */
        final /* synthetic */ int f7616h;

        f0(boolean z, int i2) {
            MainFrameActivity.this = r1;
            this.f7615g = z;
            this.f7616h = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (this.f7615g) {
                if (MainFrameActivity.this.u != null && MainFrameActivity.this.v != null) {
                    MainFrameActivity.this.u.setVisibility(0);
                    MainFrameActivity.this.v.setVisibility(0);
                    jDJSONObject.put("changetype", (Object) "1");
                }
            } else if (MainFrameActivity.this.u != null && MainFrameActivity.this.v != null) {
                MainFrameActivity.this.u.setVisibility(8);
                MainFrameActivity.this.v.setVisibility(8);
                MainFrameActivity.this.setBubbleViewGone();
                MainFrameActivity.this.setNewBubbleViewGone();
                if (MainFrameActivity.this.q != null && MainFrameActivity.this.q.getVisibility() == 0) {
                    MainFrameActivity.this.q.setVisibility(8);
                }
                jDJSONObject.put("changetype", (Object) "0");
            }
            jDJSONObject.put("caller", (Object) (this.f7616h + ""));
            if (OKLog.D) {
                OKLog.d(MainFrameActivity.this.f7586g, "setNavigationVisibility isVisible: " + this.f7615g + " json_param\uff1a" + jDJSONObject.toJSONString());
            }
            if (MainFrameActivity.this.getThisActivity() != null) {
                JDMtaUtils.sendExposureDataWithExt(MainFrameActivity.this.getThisActivity(), "NavigationBar_StateChange", "", "NavigationBar_Main", "", "", jDJSONObject.toJSONString(), null);
            }
        }
    }

    /* loaded from: classes19.dex */
    public class g implements LottieListener<Throwable> {
        g() {
            MainFrameActivity.this = r1;
        }

        @Override // com.airbnb.lottie.LottieListener
        /* renamed from: a */
        public void onResult(Throwable th) {
            if (Log.D) {
                Log.d("Navigation_Effect", "setFailureListener-" + th);
            }
            MainFrameActivity.this.x0();
        }
    }

    /* loaded from: classes19.dex */
    class g0 implements MessageQueue.IdleHandler {
        g0() {
            MainFrameActivity.this = r1;
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            MainFrameActivity.this.o0();
            return false;
        }
    }

    /* loaded from: classes19.dex */
    public class h implements Animator.AnimatorListener {
        h() {
            MainFrameActivity.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (Log.D) {
                Log.d("Navigation_Effect", "onAnimationEnd-" + Thread.currentThread().getName());
            }
            MainFrameActivity.this.x0();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes19.dex */
    public class h0 implements GrayModelListener {

        /* loaded from: classes19.dex */
        class a implements Runnable {
            a() {
                h0.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (MainFrameActivity.this.f7593n != null) {
                    JDGrayModelUtils.getInstance().setViewGray(MainFrameActivity.this.f7593n, JDGrayModelUtils.getInstance().isGrayModel());
                }
                if (MainFrameActivity.this.E != null) {
                    JDGrayModelUtils.getInstance().setViewGray(MainFrameActivity.this.E, JDGrayModelUtils.getInstance().isGrayModel());
                }
                if (MainFrameActivity.this.F != null) {
                    JDGrayModelUtils.getInstance().setViewGray(MainFrameActivity.this.F, JDGrayModelUtils.getInstance().isGrayModel());
                }
            }
        }

        h0() {
            MainFrameActivity.this = r1;
        }

        @Override // com.jingdong.common.gray.GrayModelListener
        public void onModelChanged() {
            if (OKLog.D) {
                OKLog.d(MainFrameActivity.this.f7586g, "\u5bfc\u822a\u6c14\u6ce1\u4e00\u952e\u7f6e\u7070=" + JDGrayModelUtils.getInstance().isGrayModel());
            }
            try {
                MainFrameActivity.this.getHandler().post(new a());
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(MainFrameActivity.this.f7586g, e2);
                }
            }
        }
    }

    /* loaded from: classes19.dex */
    public class i implements Runnable {
        i() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MainFrameActivity.this.q != null) {
                if (Log.D) {
                    Log.d("Navigation_Effect", "setEffectViewGone");
                }
                MainFrameActivity.this.q.setVisibility(8);
            }
        }
    }

    /* loaded from: classes19.dex */
    public class i0 implements View.OnTouchListener {
        i0(MainFrameActivity mainFrameActivity) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* loaded from: classes19.dex */
    public class j implements View.OnClickListener {
        j() {
            MainFrameActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MainFrameActivity.this.getThisActivity() != null) {
                MainFrameActivity.this.dismissChangeVersionBubble();
                JDRouter.to(MainFrameActivity.this.getThisActivity(), "router://JDMyJdModule/showModeSwitchPage").open();
                JDMtaUtils.onClickWithPageId(MainFrameActivity.this.getThisActivity(), "NavigationBar_BubbleToB", j.class.getSimpleName(), "NavigationBar_Main");
            }
        }
    }

    /* loaded from: classes19.dex */
    class j0 implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f7624g;

        /* renamed from: h */
        final /* synthetic */ NavigationBubbleEntity f7625h;

        j0(boolean z, NavigationBubbleEntity navigationBubbleEntity) {
            MainFrameActivity.this = r1;
            this.f7624g = z;
            this.f7625h = navigationBubbleEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f7624g) {
                MainFrameActivity.this.t0(this.f7625h);
            } else {
                MainFrameActivity.this.r0(this.f7625h);
            }
        }
    }

    /* loaded from: classes19.dex */
    class k extends BroadcastReceiver {
        k() {
            MainFrameActivity.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Log.D) {
                Log.d(MainFrameActivity.this.f7586g, "\u767b\u5f55\u72b6\u6001\u76d1\u542c-action=" + action);
            }
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (action.equals("com.jingdong.action.user.login.out") || action.equals("com.jingdong.action.user.login.in")) {
                MainFrameActivity.b0 = true;
            }
        }
    }

    /* loaded from: classes19.dex */
    class k0 implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f7627g;

        k0(boolean z) {
            MainFrameActivity.this = r1;
            this.f7627g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f7627g) {
                MainFrameActivity.this.setNewBubbleViewGone();
            } else {
                MainFrameActivity.this.setBubbleViewGone();
            }
        }
    }

    /* loaded from: classes19.dex */
    class l implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f7629g;

        l(String str) {
            MainFrameActivity.this = r1;
            this.f7629g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainFrameActivity.this.s0(this.f7629g);
        }
    }

    /* loaded from: classes19.dex */
    class l0 implements Runnable {
        l0() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Log.D) {
                Log.d("Navigation_Bubble", "disappearRunnable");
            }
            MainFrameActivity.this.setBubbleViewGone();
        }
    }

    /* loaded from: classes19.dex */
    class m implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f7632g;

        m(String str) {
            MainFrameActivity.this = r1;
            this.f7632g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainFrameActivity.this.s0(this.f7632g);
        }
    }

    /* loaded from: classes19.dex */
    public static class m0 implements BaseFrameUtil.KillStage {
        private static com.jingdong.app.mall.utils.o a;

        public m0(com.jingdong.app.mall.utils.o oVar) {
            a = oVar;
        }

        @Override // com.jingdong.common.BaseFrameUtil.KillStage
        public void run() {
            a.e(JdSdk.getInstance().getApplication());
        }
    }

    /* loaded from: classes19.dex */
    class n implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f7634g;

        n(String str) {
            MainFrameActivity.this = r1;
            this.f7634g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainFrameActivity.this.s0(this.f7634g);
        }
    }

    /* loaded from: classes19.dex */
    class o implements Runnable {
        o() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainFrameActivity.this.w0();
        }
    }

    /* loaded from: classes19.dex */
    class p implements Runnable {
        p() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainFrameActivity.this.w0();
        }
    }

    /* loaded from: classes19.dex */
    class q implements Runnable {

        /* loaded from: classes19.dex */
        class a implements JDLocationListener {
            a(q qVar) {
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
            }
        }

        q() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (System.currentTimeMillis() - MainFrameActivity.this.f7591l > Configuration.getIntegerProperty(Configuration.LEAVE_TIME_GAP).intValue()) {
                    GlobalInitialization.regDevice();
                }
                if (MainFrameActivity.this.f7587h || Build.VERSION.SDK_INT <= 28) {
                    return;
                }
                if (OKLog.D) {
                    OKLog.d("LBS", "\u540e\u53f0\u56de\u5230\u524d\u53f0 getAddress 43e9c1e4614a472bd98b9f7024499e90");
                }
                JDLocationOption jDLocationOption = new JDLocationOption();
                jDLocationOption.setBusinessId("43e9c1e4614a472bd98b9f7024499e90");
                JDLocationManager.getInstance().getAddress(jDLocationOption, new a(this));
                MainFrameActivity.this.f7587h = ProcessUtil.isForeground();
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d("Temp", " onResume()-->> " + e2.getMessage());
                }
            }
        }
    }

    /* loaded from: classes19.dex */
    class r implements Runnable {
        r() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
                if (currentMyActivity != null && (currentMyActivity instanceof MyActivity)) {
                    ReactNativeUpdate.getInstance().reactUnzipSo();
                    ReactNativeUpdate.getInstance().checkUpdate();
                }
                JDReactManager.updateConfig();
                if (MainFrameActivity.this.f7590k) {
                    return;
                }
                com.jingdong.app.mall.safemode.i.g().j();
                MainFrameActivity.this.f7590k = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes19.dex */
    public class s implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ JDDialog f7640g;

        s(JDDialog jDDialog) {
            MainFrameActivity.this = r1;
            this.f7640g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainFrameActivity.this.P = true;
            this.f7640g.dismiss();
        }
    }

    /* loaded from: classes19.dex */
    public class t implements View.OnClickListener {
        t() {
            MainFrameActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                MainFrameActivity.this.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes19.dex */
    public class u implements Runnable {

        /* renamed from: g */
        final /* synthetic */ OpenAppJumpController.Command f7643g;

        /* loaded from: classes19.dex */
        class a implements Runnable {

            /* renamed from: g */
            final /* synthetic */ String f7645g;

            /* renamed from: h */
            final /* synthetic */ Bundle f7646h;

            a(String str, Bundle bundle) {
                u.this = r1;
                this.f7645g = str;
                this.f7646h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                JumpUtil.execJumpByDes(this.f7645g, MainFrameActivity.this, this.f7646h);
            }
        }

        u(OpenAppJumpController.Command command) {
            MainFrameActivity.this = r1;
            this.f7643g = command;
        }

        @Override // java.lang.Runnable
        public void run() {
            String des = this.f7643g.getDes();
            Bundle outBundle = this.f7643g.getOutBundle();
            if (Log.D) {
                Log.d(MainFrameActivity.this.f7586g, "toTargetActivity des -->> " + des);
            }
            if (Log.D && outBundle != null) {
                Log.d(MainFrameActivity.this.f7586g, "bundle -->> " + outBundle);
                for (String str : outBundle.keySet()) {
                    Object obj = outBundle.get(str);
                    Log.d(MainFrameActivity.this.f7586g, "bundle key value -->> " + str + "\uff1a" + obj);
                }
            }
            if (!MainFrameActivity.this.f7589j) {
                MainFrameActivity.this.f7588i = new a(des, outBundle);
                return;
            }
            JumpUtil.execJumpByDes(des, MainFrameActivity.this, outBundle);
        }
    }

    /* loaded from: classes19.dex */
    public class v implements Runnable {
        v() {
            MainFrameActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainFrameActivity.this.B0();
        }
    }

    /* loaded from: classes19.dex */
    public class w implements MessageQueue.IdleHandler {
        w() {
            MainFrameActivity.this = r1;
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            if (CommonBase.getJdSharedPreferences().getBoolean(Constants.SHOW_COST, true) && Configuration.getBooleanProperty(Configuration.COST_HINT).booleanValue()) {
                MainFrameActivity.this.z0();
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes19.dex */
    public class x implements CompoundButton.OnCheckedChangeListener {
        x(MainFrameActivity mainFrameActivity) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton.getId() == R.id.not_show_again) {
                CommonBase.getJdSharedPreferences().edit().putBoolean(Constants.SHOW_COST, !z).apply();
            }
        }
    }

    /* loaded from: classes19.dex */
    public class y implements DialogInterface.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ AlertDialog f7650g;

        y(MainFrameActivity mainFrameActivity, AlertDialog alertDialog) {
            this.f7650g = alertDialog;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.f7650g.dismiss();
        }
    }

    /* loaded from: classes19.dex */
    public class z implements DialogInterface.OnClickListener {
        z(MainFrameActivity mainFrameActivity) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            BaseFrameUtil.exitAll();
        }
    }

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        T = arrayList;
        U = -1;
        V = false;
        W = false;
        arrayList.add(JDNewCategoryFragment.class.getName());
        T.add(JDShopingCartHostFragment.class.getName());
        T.add(JDPersonalHostFragment.class.getName());
        T.add(FaxianMainHostFragment.class.getName());
        T.add(JDVideoHostFragment.class.getName());
        T.add(JDCommonHostFragment.class.getName());
        b0 = false;
    }

    private void A0(Bundle bundle) {
        if (Log.D) {
            Log.d(this.f7586g, "showSearchActivity() -->> ");
        }
        DeepLinkProductListHelper.startSearchActivity(this, bundle);
    }

    public void B0() {
        try {
            if (JDPrivacyHelper.isAcceptPrivacy(this)) {
                int i2 = LoginUserBase.hasLogin() ? 3 : 1;
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(this, "com.jd.lib.incomingcall.service.DownloadIncomingStaffService"));
                intent.putExtra("DownloadType", i2);
                startService(intent);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(this.f7586g, "the service is launched in exception --->" + e2.getMessage());
                e2.printStackTrace();
            }
        }
    }

    private void C0() {
        try {
            HashMap hashMap = new HashMap(8);
            hashMap.put("errCode", "961");
            hashMap.put("errMsg", "createTwoMainFrame");
            hashMap.put("function", "hpnavigationbar");
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(this.f7586g, th);
            }
        }
    }

    private void b0() {
        try {
            if (X != null) {
                com.jingdong.app.mall.home.floor.ctrl.t.i.p().A(true);
                JDSharedCommandUtils.getInstance().setJdShareCommandWaitOutside();
                ((ViewStub) findViewById(R.id.splash_view_stub)).inflate();
                getSupportFragmentManager().beginTransaction().add(R.id.splash_fragment, new SplashFragment(), XView2Constants.SPLASHFRAGMENT).commit();
            }
            X = null;
        } catch (Exception unused) {
        }
    }

    private void c0() {
        if (!this.P && Settings.System.getInt(getContentResolver(), "always_finish_activities", 0) == 1) {
            JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(this, getString(R.string.always_finish_activities), getString(R.string.b6), getString(R.string.set_close));
            createJdDialogWithStyle2.setOnLeftButtonClickListener(new s(createJdDialogWithStyle2));
            createJdDialogWithStyle2.setOnRightButtonClickListener(new t());
            createJdDialogWithStyle2.show();
        }
    }

    public static void clearCache() {
        if (Log.D) {
            Log.d("Temp", "MainFrameActivity clearCache() -->> ");
        }
        com.jingdong.app.mall.utils.q.c().d(new b0());
    }

    private void d0() {
        int taskId = getTaskId();
        Log.e("checkCreateTwoEvent", "taskId = " + taskId);
        int i2 = U;
        if (i2 < 0 || taskId == i2 || V) {
            return;
        }
        C0();
        V = true;
    }

    private void e0() {
        boolean z2 = CommonBase.getJdSharedPreferences().getBoolean("personal_phone_msg_enabled_key", false);
        boolean z3 = CommonBase.getJdSharedPreferences().getBoolean("personal_phone_msg_key", false);
        boolean keySwitchState = ConfigUtil.getKeySwitchState("incomingcallEnabled");
        if (this.M && z2 && keySwitchState && z3) {
            this.M = false;
            com.jingdong.app.mall.utils.q.c().d(new v());
        }
    }

    private float f0(int i2) {
        float f2;
        int dip2px;
        int appWidth = DPIUtil.getAppWidth(this);
        if (appWidth != 0) {
            int i3 = appWidth / 5;
            if (i2 == 0) {
                float dip2px2 = (i3 * 0.5f) - DPIUtil.dip2px(38.0f);
                if (dip2px2 < 0.0f) {
                    return 0.0f;
                }
                return dip2px2;
            }
            if (i2 == 1) {
                f2 = i3 * 1.5f;
                dip2px = DPIUtil.dip2px(83.0f);
            } else if (i2 == 2) {
                f2 = i3 * 2.5f;
                dip2px = DPIUtil.dip2px(83.0f);
            } else if (i2 != 3) {
                if (i2 != 4) {
                    return -1.0f;
                }
                float f3 = i3 * 4.5f;
                return f3 + ((float) DPIUtil.dip2px(32.0f)) > ((float) appWidth) ? appWidth - DPIUtil.dip2px(115.0f) : f3 - DPIUtil.dip2px(83.0f);
            } else {
                f2 = i3 * 3.5f;
                dip2px = DPIUtil.dip2px(83.0f);
            }
            return f2 - dip2px;
        }
        return -1.0f;
    }

    private String g0(String str) {
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            bufferedReader2.close();
                            String sb2 = sb.toString();
                            try {
                                bufferedReader2.close();
                                return sb2;
                            } catch (IOException unused) {
                                return sb2;
                            }
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return "";
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static com.jingdong.app.mall.ad.d getAdObject() {
        return X;
    }

    public static StateController getCarStateController() {
        return Q;
    }

    public static TabShowNew getFaxianShowNew() {
        return S;
    }

    public static StateController getImmCarStateController() {
        return a0;
    }

    public static TabShowNew getImmFaXianShowNew() {
        return Z;
    }

    public static TabShowNew getImmTabShowNew() {
        return Y;
    }

    public static TabShowNew getTabShowNew() {
        return R;
    }

    private boolean h0() {
        Looper.myQueue().addIdleHandler(new w());
        validatCartIcon();
        return true;
    }

    private int i0(Bundle bundle, boolean z2) {
        try {
            int i2 = NavigationBase.getInstance().mCurrentIndex;
            if (bundle != null) {
                int i3 = bundle.getInt(RemoteMessageConst.TO, -1);
                if (Log.D) {
                    Log.d(this.f7586g, "1.goAction() to = " + i3 + " bundle = " + bundle);
                }
                bundle.remove(RemoteMessageConst.TO);
                if (i3 == -1) {
                    return 0;
                }
                i2 = i3;
            }
            if (i2 == 65793) {
                A0(null);
                i2 = 0;
            }
            this.A = i2;
            if (!z2) {
                getNavigationFragment().z(i2);
            }
            if (Log.D) {
                Log.d(this.f7586g, "goAction() to = " + i2 + " bundle = " + bundle);
            }
            return i2;
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
            return 0;
        }
    }

    private void j0() {
        ComponentName componentName = new ComponentName(this, NfcIntentHandleActivity.class);
        PackageManager packageManager = getPackageManager();
        if (JDNfcAdapterUtil.isNfcEnabled(this)) {
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        } else {
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
        }
    }

    private void k0() {
        this.f7593n = findViewById(R.id.navigation_bubble);
        this.F = findViewById(R.id.navigation_bubble_new_sub);
        this.E = findViewById(R.id.navigation_bubble_new);
        this.o = (SimpleDraweeView) findViewById(R.id.navigation_bubble_iv);
        this.D = (TextView) findViewById(R.id.tv_bubble_content);
        this.p = (ImageView) findViewById(R.id.navigation_bubble_disappear);
        this.H = (SimpleDraweeView) findViewById(R.id.navigation_bubble_iv_new_subscript);
        this.I = (LinearLayout) findViewById(R.id.ll_text_content);
        this.G = findViewById(R.id.spaceing);
        this.J = findViewById(R.id.navigation_bubble_changeVersion);
        this.K = findViewById(R.id.changeVersionButtonTr);
    }

    private void l0(Bundle bundle) {
        if (bundle != null && bundle.getParcelable("android:support:fragments") != null) {
            bundle.putParcelable("android:support:fragments", null);
        }
        BaseFrameUtil.getInstance().setMainFrameActivity(this);
        com.jingdong.app.mall.navigationbar.c.d0();
    }

    private void m0() {
        JDLottieAnimationView jDLottieAnimationView = (JDLottieAnimationView) findViewById(R.id.navigation_effect);
        this.q = jDLottieAnimationView;
        jDLottieAnimationView.setOnTouchListener(new i0(this));
    }

    public void o0() {
        com.jingdong.app.mall.utils.e.a();
        e0();
        com.jingdong.app.mall.personel.home.a.a();
        UnCustomThemeHelper.getInstance().setLogin(LoginUserBase.hasLogin());
        ThemeTitleDataController.getInstance().getThemeTitleData(0);
        JDSkinSDK.getInstance().getResData(AppStateType.APP_START);
        MobileLoginUtil.initAuthn(this);
        if (!SwitchQueryFetcher.isXTime()) {
            PuppetManager.getInstance().sync(this);
        }
        PuppetManager.getInstance().setDefaultStyleListener(new d0(this));
        RtcSdkConfig.getInstance().init();
        com.jingdong.app.mall.utils.q.c().d(new e0());
        FaxianMainHostFragment.e();
        p0();
        j0();
        com.jingdong.app.mall.p.a.a().c(getApplicationContext());
        JDSharedCommandUtils.getInstance().resumeForJDCommand(this);
        JDPluginPreLoader.getInstance().start();
        StationMessageUtils.addPageIdLiveDataObserver();
        com.jd.manto.a.j();
    }

    private void p0() {
        CartCommonUtil.preLoadCartConfig(this);
    }

    public void q0() {
        com.jingdong.app.mall.utils.o oVar = new com.jingdong.app.mall.utils.o();
        oVar.d(JdSdk.getInstance().getApplication());
        try {
            BaseFrameUtil.setKillStage(new m0(oVar));
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
        }
    }

    public void r0(NavigationBubbleEntity navigationBubbleEntity) {
        boolean z2 = DeepDarkChangeManager.getInstance().getUIMode() == 1;
        if (navigationBubbleEntity == null || TextUtils.isEmpty(navigationBubbleEntity.imgUrl)) {
            return;
        }
        int bubbleShowIndex = getBubbleShowIndex(navigationBubbleEntity.position);
        if (Log.D) {
            Log.d("Navigation_Bubble", "bubbleShowIndex=" + bubbleShowIndex);
        }
        if (bubbleShowIndex == -1) {
            return;
        }
        float f02 = f0(bubbleShowIndex);
        if (Log.D) {
            Log.d("Navigation_Bubble", "bubbleOffset=" + f02);
        }
        if (f02 == -1.0f || TextUtils.isEmpty(navigationBubbleEntity.imgUrl) || TextUtils.isEmpty(navigationBubbleEntity.darkImageUrl) || this.f7593n == null) {
            return;
        }
        JDImageUtils.loadImage(z2 ? navigationBubbleEntity.darkImageUrl : navigationBubbleEntity.imgUrl, new c(navigationBubbleEntity, f02));
        if (navigationBubbleEntity.disappearTiming != 0 && this.N != null) {
            getHandler().postDelayed(this.N, navigationBubbleEntity.disappearTiming);
        }
        this.p.setOnClickListener(new d());
        this.o.setOnClickListener(new e(navigationBubbleEntity));
    }

    public void s0(String str) {
        try {
            if (TextUtils.isEmpty(str) || this.q == null || !UnLottieUtils.enableLottie()) {
                return;
            }
            this.q.setVisibility(0);
            String g02 = g0(str);
            if (TextUtils.isEmpty(g02)) {
                return;
            }
            this.q.setFailureListener(new g());
            this.q.addAnimatorListener(new h());
            this.q.setAnimationFromJson(g02, System.currentTimeMillis() + "");
            this.q.playAnimation();
        } catch (Exception unused) {
            x0();
        }
    }

    public static void setAdObject(com.jingdong.app.mall.ad.d dVar) {
        X = dVar;
    }

    public static void setCarStateController(StateController stateController) {
        Q = stateController;
    }

    public static void setFaxianShowNew(TabShowNew tabShowNew) {
        S = tabShowNew;
    }

    public static void setImmCarStateController(StateController stateController) {
        a0 = stateController;
    }

    public static void setImmFaXianShowNew(TabShowNew tabShowNew) {
        Z = tabShowNew;
    }

    public static void setImmTabShowNew(TabShowNew tabShowNew) {
        Y = tabShowNew;
    }

    public static void setTabShowNew(TabShowNew tabShowNew) {
        R = tabShowNew;
    }

    public void t0(NavigationBubbleEntity navigationBubbleEntity) {
        boolean z2 = DeepDarkChangeManager.getInstance().getUIMode() == 1;
        if (navigationBubbleEntity == null || TextUtils.isEmpty(navigationBubbleEntity.imgUrl)) {
            return;
        }
        int bubbleShowIndex = getBubbleShowIndex(navigationBubbleEntity.position);
        if (Log.D) {
            Log.d("Navigation_Bubble", "bubbleShowIndex=" + bubbleShowIndex);
        }
        if (bubbleShowIndex == -1) {
            return;
        }
        boolean e2 = com.jingdong.app.mall.navigationbar.c.G().e(bubbleShowIndex);
        NavigationTabLocationEntry P = com.jingdong.app.mall.navigationbar.c.G().P(bubbleShowIndex);
        if (e2) {
            P.topY += DPIUtil.dip2px(15.0f);
        }
        if (Log.D) {
            Log.d("Navigation_Bubble", "bubbleOffset=" + P.toString());
        }
        if (P == null || TextUtils.isEmpty(navigationBubbleEntity.imgUrl) || TextUtils.isEmpty(navigationBubbleEntity.darkImageUrl)) {
            return;
        }
        int i2 = navigationBubbleEntity.picSeparate;
        if (i2 == 1) {
            if (TextUtils.isEmpty(navigationBubbleEntity.bubbleText) || TextUtils.isEmpty(navigationBubbleEntity.darkCornerUrl) || TextUtils.isEmpty(navigationBubbleEntity.cornerUrl)) {
                return;
            }
        } else if (i2 == -1 || i2 != 0) {
            return;
        }
        View view = this.E;
        if (view == null || this.F == null || this.H == null) {
            return;
        }
        view.setVisibility(4);
        if (navigationBubbleEntity.picSeparate == 1) {
            this.F.setVisibility(4);
            JDImageUtils.displayImage(z2 ? navigationBubbleEntity.darkCornerUrl : navigationBubbleEntity.cornerUrl, this.H);
        }
        JDImageUtils.loadImage(z2 ? navigationBubbleEntity.darkImageUrl : navigationBubbleEntity.imgUrl, new f(navigationBubbleEntity, z2, P, bubbleShowIndex));
        if (navigationBubbleEntity.disappearTiming != 0) {
            if ((navigationBubbleEntity.isIconAndBubbleCom() && navigationBubbleEntity.iconIsLottie()) || this.O == null) {
                return;
            }
            getHandler().postDelayed(this.O, navigationBubbleEntity.disappearTiming);
        }
    }

    public void u0(NavigationBubbleEntity navigationBubbleEntity) {
        String str;
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (navigationBubbleEntity != null) {
                jDJSONObject.put("name", (Object) (TextUtils.isEmpty(navigationBubbleEntity.position) ? "" : navigationBubbleEntity.position));
                int i2 = navigationBubbleEntity.bubbleType;
                String str2 = "-100";
                if (i2 == 0 || i2 == 1 || i2 == 2) {
                    str = navigationBubbleEntity.bubbleType + "";
                } else {
                    if (i2 == 4) {
                        int i3 = navigationBubbleEntity.combinationTypes;
                        if (i3 == 2) {
                            str = "1";
                        } else if (i3 == 3) {
                            str = "2";
                        }
                    }
                    str = "-100";
                }
                jDJSONObject.put("bubbletype", (Object) str);
                jDJSONObject.put("sourceid", (Object) (TextUtils.isEmpty(navigationBubbleEntity.id) ? "-100" : navigationBubbleEntity.id));
                jDJSONObject.put("type", (Object) "0");
                jDJSONObject.put("iconstyle", (Object) (navigationBubbleEntity.shapeType + ""));
                jDJSONObject.put("animationstyle", (Object) (navigationBubbleEntity.dynamicType + ""));
                if (!TextUtils.isEmpty(navigationBubbleEntity.impr)) {
                    str2 = navigationBubbleEntity.impr;
                }
                jDJSONObject.put("biinfo", (Object) str2);
                jDJSONObject.put("isshow", (Object) (navigationBubbleEntity.bucketType + ""));
                if (OKLog.D) {
                    OKLog.d("bubble_expo", jDJSONObject.toJSONString());
                }
                if (getThisActivity() != null) {
                    JDMtaUtils.sendExposureDataWithExt(getThisActivity(), "NavigationBar_BubbleExpo", "", "NavigationBar_Main", "", "", jDJSONObject.toJSONString(), null);
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("bubble_expo", e2.toString());
            }
        }
    }

    private void v0() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", DeepDarkChangeManager.getInstance().getUIMode());
            JDMtaUtils.sendExposureDataWithExt(getThisActivity(), "UnionControl_BlackStatus_Expo", "", "UnionControl_Black", "", "", jSONObject.toString(), null);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void validatCartIcon() {
        StateController carStateController = getCarStateController();
        if (Log.D) {
            Log.d("Navigation", "validatCartIcon -->> carStateController=" + carStateController);
        }
        if (carStateController == null) {
            return;
        }
        int productCount = ShoppingBaseController.getProductCount();
        if (Log.D) {
            Log.d("Navigation", "validatCartIcon -->> count=" + productCount);
        }
        Integer valueOf = productCount == 0 ? null : Integer.valueOf(productCount);
        if (BaseFrameUtil.getInstance().getMainFrameActivity() == null || BaseFrameUtil.getInstance().getMainFrameActivity().getHandler() == null) {
            return;
        }
        Looper.myQueue().addIdleHandler(new c0(valueOf, carStateController));
    }

    public void w0() {
        try {
            FireEyeUtils.reportFireEyeS(this, false);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void x0() {
        try {
            getHandler().post(new i());
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    private void y0() {
        try {
            Resources resources = getResources();
            android.content.res.Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
        }
    }

    public void z0() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setTitle(R.string.k0);
        View inflate = ImageUtil.inflate(R.layout.cost_alert, new RelativeLayout(this));
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.not_show_again);
        checkBox.setOnCheckedChangeListener(new x(this));
        checkBox.setChecked(false);
        create.setView(inflate);
        create.setButton(getText(R.string.ig), new y(this, create));
        create.setButton2(getText(R.string.b6), new z(this));
        create.setOnKeyListener(new a0(this));
        create.show();
    }

    public void checkTargetActivity() {
        if (Log.D) {
            Log.d(this.f7586g, "checkTargetActivity() -->> ");
        }
        Bundle extras = getIntent().getExtras();
        if (Log.D) {
            Log.d(this.f7586g, "checkTargetActivity() bundle -->> " + extras);
        }
        if (extras != null) {
            OpenAppJumpController.Command createCommand = OpenAppJumpController.createCommand(getIntent());
            if (Log.D) {
                Log.d(this.f7586g, "checkTargetActivity() command -->> " + createCommand);
            }
            if (createCommand != null) {
                toTargetActivity(createCommand);
            }
        }
    }

    public void dismissChangeVersionBubble() {
        try {
            this.x = false;
            View view = this.J;
            if (view == null || view.getVisibility() != 0) {
                return;
            }
            this.J.setVisibility(8);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        Class<?> cls;
        BaseActivity currentMyActivity = getCurrentMyActivity();
        if (Log.D) {
            Log.d(this.f7586g, " MainFrame -->> finish() ");
        }
        if (currentMyActivity != null && (cls = currentMyActivity.getClass()) != null) {
            String name = cls.getName();
            if (!TextUtils.isEmpty(name) && T.contains(name)) {
                toHomeActivity();
                return;
            }
        }
        super.finish();
    }

    public int getBubbleShowIndex(String str) {
        List<NavigationButton> list = NavigationBase.getInstance().buttons;
        if (list != null && list.size() > 0) {
            if (Log.D) {
                Log.d("Navigation_Bubble", "navigationButtons=" + list.size());
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (com.jingdong.app.mall.navigationbar.c.G().M(str, null) == list.get(i2).getNavigationId()) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public boolean getBubbleState() {
        return this.w;
    }

    public boolean getChangeVersionShowing() {
        return this.x;
    }

    public BaseFragment getCurrentFragment() {
        return this.y;
    }

    @Override // com.jingdong.common.frame.IMainActivity
    public /* bridge */ /* synthetic */ IMyActivity getCurrentMyActivity() {
        return super.getCurrentMyActivity();
    }

    @Override // com.jingdong.sdk.perfmonitor.IJankCustomInfo
    public Map<String, String> getJankCustomInfo() {
        return WebViewHelper.getWebViewJankInfo();
    }

    public JDNavigationFragment getNavigationFragment() {
        return this.z;
    }

    public JDTabFragment getTabFragment() {
        return this.B;
    }

    @Override // com.jingdong.common.BaseActivity, com.jingdong.common.frame.IMyActivity, com.jingdong.common.frame.IMainActivity
    public Activity getThisActivity() {
        return this;
    }

    @Override // com.jingdong.common.frame.IMainActivity
    public boolean isMainStop() {
        return this.f7592m;
    }

    public boolean isShow() {
        return this.f7589j;
    }

    @Override // android.app.Activity
    public void onActivityReenter(int i2, Intent intent) {
        List<Fragment> fragments;
        List<Fragment> fragments2;
        super.onActivityReenter(i2, intent);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager == null || (fragments = supportFragmentManager.getFragments()) == null) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment instanceof FaxianMainHostFragment) {
                FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                if (childFragmentManager == null || (fragments2 = childFragmentManager.getFragments()) == null) {
                    return;
                }
                for (Fragment fragment2 : fragments2) {
                    if (fragment2 instanceof IActivityReenter) {
                        ((IActivityReenter) fragment2).onActivityReenter(intent);
                    }
                }
                return;
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (Log.D) {
            Log.d(this.f7586g, "onActivityResult() -->> " + i2 + "```" + i3);
        }
        if (272 == i2 && -1 == i3) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.getBoolean("isShop")) {
                String string = extras.getString(JshopConst.JSHOP_SEARCH_KEYWORD);
                com.jingdong.app.mall.utils.s.b(this, string, new SourceEntity(SourceEntity.SOURCE_TYPE_SHOP_FROM_SEARCH, string));
                return;
            }
            if (extras != null) {
                extras.putInt("sortKey", 5);
                extras.putSerializable("source", new SourceEntity("search", extras.getString(JshopConst.JSHOP_SEARCH_KEYWORD)));
            }
            com.jingdong.app.mall.utils.s.l(this, extras);
            return;
        }
        if ((i2 >> 16) == 0 && getCurrentFragment() != null) {
            getCurrentFragment().onActivityResult(i2, i3, intent);
        }
        super.onActivityResult(i2, i3, intent);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(Fragment fragment) {
        if (Log.D) {
            Log.d(this.f7586g, "onAttachFragment.. -->> " + fragment.getClass());
        }
        com.jingdong.app.mall.home.s.a.b().m("mainFrameActivity", "onAttachFragment");
        super.onAttachFragment(fragment);
        try {
            this.y = (BaseFragment) fragment;
            com.jingdong.app.mall.n.a.a().g((BaseFragment) fragment);
            if (fragment.getId() == R.id.navigation_fragment) {
                this.z = (JDNavigationFragment) fragment;
            } else if (fragment instanceof JDTabFragment) {
                JDTabFragment jDTabFragment = (JDTabFragment) fragment;
                this.B = jDTabFragment;
                jDTabFragment.isNavigationTab = true;
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        com.jingdong.app.mall.home.s.a.b().l("mainFrameActivity", "onAttachFragment");
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(android.content.res.Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ConfigurationChangedManager.getInstance().notifyConfigurationChanged(configuration);
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        int i2;
        com.jingdong.app.mall.utils.d.l(false);
        com.jingdong.app.mall.utils.d.s();
        com.jingdong.app.mall.home.s.a.b().m("mainFrameActivity", "onCreate");
        setUseBasePV(false);
        com.jingdong.app.mall.n.a.a().c();
        CommonBase.getJdSharedPreferences().edit().remove("jd_no_image_switch").apply();
        l0(bundle);
        com.jingdong.app.mall.aura.j.g(JdSdk.getInstance().getApplication().getApplicationContext());
        this.needCheckNet = false;
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        if (intent.getBooleanExtra("from_launch", false)) {
            setTheme(R.style.f98uj);
        }
        setNetworkModel(false);
        TimerUntil.startTime = System.currentTimeMillis();
        y0();
        ActivityNumController.exitAllActivityBeyond4ClassName(MainFrameActivity.class.getSimpleName());
        com.jingdong.app.mall.basic.a.d(getSupportFragmentManager());
        getWindow().requestFeature(12);
        getWindow().requestFeature(13);
        this.finishAfterSuperOnCreate = com.jingdong.app.mall.safemode.i.g().m();
        if (bundle != null) {
            i2 = bundle.getInt("lastIndex");
            if ("1".equals(JDMobileConfig.getInstance().getConfig("SKIN", "mainFrameActivity", "savedInstanceState"))) {
                bundle.clear();
                bundle = null;
            }
        } else {
            i2 = -1;
        }
        super.onCreate(bundle);
        if (this.finishAfterSuperOnCreate) {
            com.jingdong.app.mall.safemode.i.g().n();
            Process.killProcess(Process.myPid());
            System.exit(0);
            return;
        }
        this.statusBarTransparentEnable = true;
        setContentView(R.layout.app_jd_fragment_activity);
        this.u = findViewById(R.id.unreal_navigation);
        this.v = findViewById(R.id.navigation_fragment);
        k0();
        m0();
        if (i2 != -1) {
            this.A = i2;
            NavigationBase.getInstance().mCurrentIndex = this.A;
            BaseFrameUtil.needStartImage = false;
        } else {
            i0(getIntent().getExtras(), true);
        }
        b0();
        JDNavigationFragment r2 = JDNavigationFragment.r(this.A);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment, r2, "JDNavigationBarFragment").commit();
        this.z = r2;
        if (BaseFrameUtil.needStartImage) {
            BaseFrameUtil.needStartImage = false;
            h0();
        } else {
            validatCartIcon();
        }
        com.jingdong.app.mall.utils.j.c().d();
        com.jingdong.app.mall.utils.j.c().f(new com.jingdong.app.mall.utils.r() { // from class: com.jingdong.app.mall.b
            @Override // com.jingdong.app.mall.utils.r
            public final void a(boolean z2) {
                com.jingdong.app.mall.utils.e.a();
            }
        });
        Looper.myQueue().addIdleHandler(new g0());
        UnCustomThemeHelper.getInstance().registerLoginAndExitReceiver(JdSdk.getInstance().getApplication());
        try {
            PersonalInfoManager.getInstance().registerLoginAndExitReceiver(JdSdk.getInstance().getApplication());
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(this.f7586g, "PersonalInfoManager register: " + e2.getMessage());
            }
        }
        JDElderModeUtils.registerLoginReceiver(JdSdk.getInstance().getApplication());
        try {
            AmountValueManager.getInstance().registerLoginAndExitReceiver(JdSdk.getInstance().getApplication());
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(this.f7586g, "AmountValueManager register: " + th.getMessage());
            }
        }
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(1, 2);
        }
        U = getTaskId();
        GpsChangedReceiver gpsChangedReceiver = new GpsChangedReceiver();
        this.C = gpsChangedReceiver;
        gpsChangedReceiver.regist(getApplicationContext());
        com.jingdong.app.mall.network.a.a().b(JdSdk.getInstance().getApplication());
        com.jingdong.app.mall.navigationbar.c.G().b0(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.out");
        intentFilter.addAction("com.jingdong.action.user.login.in");
        registerReceiver(this.L, intentFilter);
        JDGrayModelUtils.getInstance().addListener(new h0());
        com.jingdong.app.mall.home.s.a.b().l("mainFrameActivity", "onCreate");
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        com.jingdong.app.mall.navigationbar.c.G().m0(this);
        ConfigurationChangedManager.getInstance().clearConfigurationChangeListeners();
        DeepDarkChangeManager.getInstance().clearDeepDarkChangeListeners();
        NavigationSkinChangeManager.getInstance().clearNavigationSkinChangeListeners();
        com.jingdong.app.mall.n.a.a().d();
        try {
            com.jingdong.app.mall.home.floor.ctrl.t.i.p().k();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            UnCustomThemeHelper.getInstance().unregisterLoginAndExitReceiver(JdSdk.getInstance().getApplication());
        } catch (Exception unused) {
        }
        try {
            PersonalInfoManager.getInstance().unregisterLoginAndExitReceiver(JdSdk.getInstance().getApplication());
        } catch (Exception e3) {
            if (OKLog.D) {
                OKLog.d(this.f7586g, "PersonalInfoManager unregister: " + e3.getMessage());
            }
        }
        try {
            JDElderModeUtils.unregisterLoginReceiver(JdSdk.getInstance().getApplication());
            JDElderModeUtils.onDestroy();
        } catch (Exception e4) {
            if (OKLog.D) {
                OKLog.d(this.f7586g, "JDElderModeUtils unregister: " + e4.getMessage());
            }
        }
        try {
            AmountValueManager.getInstance().unregisterLoginAndExitReceiver(JdSdk.getInstance().getApplication());
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(this.f7586g, "AmountValueManager unregister: " + th.getMessage());
            }
        }
        com.jingdong.app.mall.l.a.a.d();
        com.jingdong.app.mall.utils.j.c().b();
        GpsChangedReceiver gpsChangedReceiver = this.C;
        if (gpsChangedReceiver != null) {
            gpsChangedReceiver.unRegist(getApplicationContext());
        }
        try {
            if ("1".equals(JDMobileConfig.getInstance().getConfig("SKIN", "lockScreen", "lockScreen")) && "1".equals(getApplication().getSharedPreferences(JDReactNativeMyJDModule.SCREEN_LOCK, 0).getString(JDReactNativeMyJDModule.SCREEN_LOCK_KEY, "0"))) {
                if (Log.D) {
                    Log.e(this.f7586g, "LockScreen-->stop");
                }
                com.jingdong.app.mall.lockscreen.a.a(this).c();
            }
        } catch (Exception e5) {
            if (Log.D) {
                Log.e(this.f7586g, "LockScreen-->" + e5.toString());
            }
        }
        BroadcastReceiver broadcastReceiver = this.L;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (Log.I) {
            Log.i(this.f7586g, "onSearchRequested++++main");
        }
        if (i2 == 84) {
            String simpleName = JDHomeFragment.class.getSimpleName();
            String simpleName2 = JDPersonalHostFragment.class.getSimpleName();
            BaseFragment currentFragment = getCurrentFragment();
            if (currentFragment == null) {
                return true;
            }
            String simpleName3 = currentFragment.getClass().getSimpleName();
            if (Log.D) {
                Log.d(this.f7586g, "currentActivity -->> " + simpleName3);
            }
            if (!simpleName.equals(simpleName3) && !simpleName2.equals(simpleName3)) {
                A0(null);
            }
            return true;
        } else if (i2 == 4) {
            if (removeXview2()) {
                removeBackKeyTriggerListener();
                return true;
            }
            BaseFragment baseFragment = this.y;
            if ((baseFragment instanceof JDHomeFragment) && baseFragment.onKeyDown(i2, keyEvent)) {
                return true;
            }
            BaseFragment baseFragment2 = this.y;
            if ((baseFragment2 instanceof JDNewCategoryFragment) && baseFragment2.onKeyDown(i2, keyEvent)) {
                return true;
            }
            BaseFragment baseFragment3 = this.y;
            if ((baseFragment3 instanceof JDShopingCartHostFragment) && baseFragment3.onKeyDown(i2, keyEvent)) {
                return true;
            }
            BaseFragment baseFragment4 = this.y;
            if ((baseFragment4 instanceof JDPersonalHostFragment) && baseFragment4.onKeyDown(i2, keyEvent)) {
                return true;
            }
            BaseFragment baseFragment5 = this.y;
            if ((baseFragment5 instanceof JDVideoHostFragment) && baseFragment5.onKeyDown(i2, keyEvent)) {
                return true;
            }
            BaseFragment baseFragment6 = this.y;
            if ((baseFragment6 instanceof JDCommonHostFragment) && baseFragment6.onKeyDown(i2, keyEvent)) {
                return true;
            }
            try {
                com.jingdong.app.mall.basic.a.a();
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
            try {
                getNavigationFragment().z(0);
            } catch (Throwable th) {
                if (Log.D) {
                    th.printStackTrace();
                }
            }
            return true;
        } else {
            return super.onKeyDown(i2, keyEvent);
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            return;
        }
        try {
            View view = this.v;
            int i2 = 0;
            if (view != null && (view.getVisibility() == 8 || this.v.getVisibility() == 4)) {
                setNavigationVisibility(true, 0);
            }
            if (Log.D) {
                Log.d(this.f7586g, "onNewIntent intent:" + intent);
                Log.d(this.f7586g, "onNewIntent intent:" + intent.getExtras());
            }
            Intent intent2 = getIntent();
            ActivityNumController.exitAllActivityBeyond4ClassName(MainFrameActivity.class.getSimpleName());
            if (intent2 != null && intent.getExtras() != null) {
                i2 = i0(intent.getExtras(), false);
                intent2.putExtras(intent.getExtras());
            }
            int intExtra = intent.getIntExtra("com.360buy:navigationId", i2);
            if (Log.D) {
                Log.d("navigation-click", " main " + intExtra + "  mc  " + this.A);
            }
            if (intExtra == 0) {
                JDHomeFragment z0 = JDHomeFragment.z0();
                if (z0 != null) {
                    z0.d1();
                }
            } else if (intExtra == 4) {
                JDPersonalHostFragment.c().e();
            }
            if (intExtra != this.A) {
                this.A = intExtra;
                getNavigationFragment().z(this.A);
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        com.jingdong.app.mall.nfc.b bVar;
        this.f7589j = false;
        this.f7591l = System.currentTimeMillis();
        super.onPause();
        if (!com.jingdong.app.mall.nfc.b.e() || (bVar = this.t) == null) {
            return;
        }
        bVar.a();
        if (Log.D) {
            Log.e(this.f7586g, "disableNfcAdapter");
        }
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        com.jingdong.app.mall.home.s.a.b().m("mainFrameActivity", "onResume");
        d0();
        this.f7592m = false;
        super.onResume();
        if (getWindow() != null && getWindow().getDecorView() != null) {
            getWindow().getDecorView().post(new o());
        } else {
            post(new p(), 100);
        }
        try {
            this.f7589j = true;
            com.jingdong.app.mall.utils.q.c().d(new q());
            if (this.f7588i != null) {
                getHandler().post(this.f7588i);
                this.f7588i = null;
            }
            if (this.r != null && !JDElderModeUtils.isElderMode()) {
                if (Log.D) {
                    Log.d("Navigation_Effect", "onResume-effectRunnable");
                }
                getHandler().post(this.r);
                this.r = null;
            }
            c0();
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("Temp", " onResume()-->> " + e2.getMessage());
            }
        }
        com.jingdong.app.mall.aura.e.h();
        com.jingdong.app.mall.utils.q.c().d(new r());
        if (JMA.needXposedDialog()) {
            JDDialogFactory.getInstance().createJdDialogWithStyle1(this, getString(R.string.hook_tip), getString(R.string.i_know)).show();
        }
        v0();
        NewBadgeUtil.clearPushBadge();
        if (com.jingdong.app.mall.nfc.b.e()) {
            if (this.t == null) {
                com.jingdong.app.mall.nfc.b bVar = new com.jingdong.app.mall.nfc.b(this);
                this.t = bVar;
                bVar.d();
            }
            this.t.b();
            if (Log.D) {
                Log.e(this.f7586g, "enableNfcAdapter");
            }
        }
        try {
            if ("1".equals(JDMobileConfig.getInstance().getConfig("SKIN", "lockScreen", "lockScreen")) && "1".equals(getApplication().getSharedPreferences(JDReactNativeMyJDModule.SCREEN_LOCK, 0).getString(JDReactNativeMyJDModule.SCREEN_LOCK_KEY, "0"))) {
                if (Log.D) {
                    Log.e(this.f7586g, "LockScreen-->open");
                }
                com.jingdong.app.mall.lockscreen.a.a(this).b();
            }
        } catch (Exception e3) {
            if (Log.D) {
                Log.e(this.f7586g, "LockScreen--->" + e3.toString());
            }
        }
        com.jingdong.jdpush_new.a.d();
        com.jingdong.app.mall.home.s.a.b().l("mainFrameActivity", "onResume");
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            if (!com.jingdong.app.mall.navigationbar.c.G().S()) {
                bundle.putInt("lastIndex", NavigationBase.getInstance().mCurrentIndex);
            }
            if (Log.D) {
                String str = "onSaveInstanceState: LAST_INDEX=" + NavigationBase.getInstance().mCurrentIndex;
            }
            bundle.putBoolean("isFromMainFrameInstance", true);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        com.jingdong.app.mall.n.a.a().e();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        if (Log.D) {
            Log.d(this.f7586g, " -->> onStop ");
        }
        com.jingdong.app.mall.n.a.a().f();
        this.f7592m = true;
        try {
            super.onStop();
            this.f7587h = ProcessUtil.isForeground();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (z2) {
            com.jingdong.app.mall.home.s.a.b().j();
        }
    }

    @Override // com.jingdong.common.frame.IMainActivity
    public void removeAllRecords(boolean z2) {
    }

    @Override // com.jingdong.common.BaseActivity
    public boolean removeGuideView() {
        return false;
    }

    public void setBubbleViewGone() {
        com.jingdong.app.mall.navigationbar.c.G().f11365g = false;
        if (this.f7593n == null || this.p == null) {
            return;
        }
        if (Log.D) {
            Log.d("Navigation_Bubble", "setBubbleViewGone");
        }
        this.f7593n.setVisibility(8);
        this.p.setVisibility(8);
        this.w = false;
    }

    public void setNaviImm(boolean z2) {
        try {
            if (this.z != null) {
                if (OKLog.D) {
                    OKLog.d("JDNavigationFragment", "setNaviImm_isUseImmSkin=" + z2);
                }
                this.z.A(z2);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(this.f7586g, e2);
            }
        }
    }

    public void setNavigationVisibility(boolean z2, int i2) {
        try {
            if (getHandler() != null) {
                getHandler().post(new f0(z2, i2));
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public void setNewBubbleViewGone() {
        com.jingdong.app.mall.navigationbar.c.G().f11365g = false;
        if (this.E == null || this.F == null) {
            return;
        }
        try {
            if (Log.D) {
                Log.d("Navigation_Bubble", "setBubbleViewGone");
            }
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.E, "alpha", 1.0f, 0.0f);
            if (this.F.getVisibility() == 0) {
                animatorSet.play(ofFloat).with(ObjectAnimator.ofFloat(this.F, "alpha", 1.0f, 0.0f));
            } else {
                animatorSet.play(ofFloat);
            }
            animatorSet.setDuration(500L);
            animatorSet.addListener(new b());
            animatorSet.start();
        } catch (Exception e2) {
            if (Log.E) {
                Log.d("Navigation_Bubble", "setNewBubbleViewGone" + e2.toString());
            }
            this.E.setVisibility(8);
            this.F.setVisibility(8);
            this.w = false;
        }
    }

    public void showChangeVersionBubble() {
        View view;
        try {
            if (this.x) {
                return;
            }
            this.x = true;
            if (com.jingdong.app.mall.navigationbar.c.G().f11365g) {
                setNewBubbleViewGone();
                setBubbleViewGone();
            }
            View view2 = this.J;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            int screenWidth = BaseInfo.getScreenWidth() / 5;
            if (screenWidth > 0 && (view = this.K) != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                layoutParams.setMargins(0, 0, (screenWidth / 2) - DpiUtil.dip2px(this, 9.0f), 0);
                this.K.setLayoutParams(layoutParams);
            }
            JDMtaUtils.sendExposureData(this, this, "NavigationBar_Main", "", "NavigationBar_BubbleToBExpo", "", "", "", "");
            View view3 = this.J;
            if (view3 != null) {
                view3.setOnClickListener(new j());
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.app.mall.navigationbar.f.a
    public void showNavigationBubble(boolean z2, NavigationBubbleEntity navigationBubbleEntity, boolean z3) {
        if (Log.D) {
            Log.d("Navigation_Bubble", "showNavigationBubble-isShowBubble=" + z2 + " entity=" + navigationBubbleEntity + " isShow=" + this.f7589j);
        }
        if (this.x) {
            return;
        }
        if (z2) {
            try {
                if (!JDElderModeUtils.isElderMode()) {
                    if (this.f7589j) {
                        getHandler().post(new j0(z3, navigationBubbleEntity));
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
        getHandler().post(new k0(z3));
    }

    @Override // com.jingdong.app.mall.navigationbar.f.b
    public void showNavigationEffect(boolean z2, String str, int i2) {
        if (Log.D) {
            Log.d("Navigation_Effect", "showNavigationEffect-isShowEffect=" + z2 + " isShow=" + this.f7589j + " localLottiePath=" + str + " triggerMode=" + i2);
        }
        if (z2) {
            try {
                if (!JDElderModeUtils.isElderMode()) {
                    if (i2 == 1) {
                        if (this.f7589j) {
                            getHandler().post(new l(str));
                        } else {
                            this.r = new m(str);
                        }
                    } else if (i2 == 2) {
                        this.s = new n(str);
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
        x0();
    }

    public void showNavigationEffectWhenClick() {
        if (Log.D) {
            Log.d("Navigation_Effect", "showNavigationEffectWhenClick-clickTabEffectRunnable=" + this.s + " isElderMode=" + JDElderModeUtils.isElderMode());
        }
        if (this.s == null || JDElderModeUtils.isElderMode()) {
            return;
        }
        getHandler().post(this.s);
        this.s = null;
    }

    public void toHomeActivity() {
        getNavigationFragment().z(0);
    }

    public void toShoppingCart() {
        getNavigationFragment().z(3);
    }

    public void toTargetActivity(OpenAppJumpController.Command command) {
        if (Log.D) {
            Log.d(this.f7586g, "toTargetActivity -->> ");
        }
        OpenAppJumpController.cps();
        getHandler().post(new u(command));
    }

    @Override // com.jingdong.common.frame.IMainActivity
    public void validateCartIcon() {
        validatCartIcon();
    }

    @Override // com.jingdong.common.frame.IMainActivity
    public void validateJdFaxianNewIcon(boolean z2) {
        FaxianMainHostFragment.o(z2);
    }

    @Override // com.jingdong.common.frame.IMainActivity
    public void validateNewIcon() {
    }
}
