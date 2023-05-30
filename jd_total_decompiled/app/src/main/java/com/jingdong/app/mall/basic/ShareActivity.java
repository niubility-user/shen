package com.jingdong.app.mall.basic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.basic.c.a;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.appshare.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.XView.IXView;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDGridView;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.HWShareHelper;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.ClickConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.jdshare.cell.ChannelAdapter;
import com.jingdong.sdk.jdshare.cell.ChannelItemSpaceDecoration;
import com.jingdong.sdk.jdshare.cell.CommonPanelView;
import com.jingdong.sdk.jdshare.utils.WeiboUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.functions.Action1;

/* loaded from: classes19.dex */
public class ShareActivity extends BaseActivity implements WbShareCallback, com.jingdong.c.a.a {
    private HWShareHelper F;
    private boolean G;
    private boolean H;

    /* renamed from: g  reason: collision with root package name */
    private BaseActivity f7950g;

    /* renamed from: h  reason: collision with root package name */
    private RelativeLayout f7951h;

    /* renamed from: i  reason: collision with root package name */
    private IXView f7952i;

    /* renamed from: j  reason: collision with root package name */
    private com.jingdong.c.a.c.f f7953j;

    /* renamed from: k  reason: collision with root package name */
    private ShareInfo f7954k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.c.a.c.g f7955l;

    /* renamed from: m  reason: collision with root package name */
    private com.jingdong.c.a.c.e f7956m;
    private long o;
    private long p;
    private String q;
    private Bitmap r;
    private Bitmap s;
    private Bitmap t;
    private Runnable v;
    private boolean w;

    /* renamed from: n  reason: collision with root package name */
    private String f7957n = "";
    private boolean u = false;
    private Runnable x = new k();
    private boolean y = false;
    private boolean z = false;
    private boolean A = false;
    private com.jingdong.c.a.c.a B = new com.jingdong.c.a.c.a(this);
    private long C = 0;
    private AtomicBoolean D = new AtomicBoolean(true);
    private AtomicBoolean E = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements AdapterView.OnItemClickListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            if (ShareActivity.this.P0()) {
                if (ShareActivity.this.f7953j.f12276c.a.size() > i2) {
                    ShareActivity shareActivity = ShareActivity.this;
                    shareActivity.f7957n = shareActivity.f7953j.f12276c.a.get(i2).a;
                }
                ShareActivity shareActivity2 = ShareActivity.this;
                shareActivity2.V0("Share_", shareActivity2.f7954k.getUrl(), "2_" + ShareActivity.this.f7956m.f12273c);
                ShareActivity.this.I0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a0 implements XViewCallBack {
        a0() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onCloseButtonClicked() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onError(int i2) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onStart() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewDisplayed() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewLoadingUrl(String str) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewReady() {
            ShareActivity.this.A = true;
            ShareActivity.this.A0();
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewRequest(XViewRequest xViewRequest) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewVisibleChanged(boolean z) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXVivewClosed() {
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShareActivity.this.h1();
            ShareActivity.this.f7951h.removeAllViews();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b0 implements Runnable {
        b0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ShareActivity.this.f7952i != null) {
                ShareActivity.this.f7952i.displayXView();
            } else {
                ShareActivity.this.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class c implements View.OnClickListener {

        /* loaded from: classes19.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                ShareActivity.this.e1();
            }
        }

        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a aVar = new a();
            if (LoginUserBase.hasLogin()) {
                ShareActivity.this.e1();
            } else {
                DeepLinkLoginHelper.startLoginActivity(ShareActivity.this, null, aVar, "ShareLottery");
            }
            ShareActivity.this.f7951h.removeAllViews();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class c0 implements CommonPanelView.i {
        c0() {
        }

        @Override // com.jingdong.sdk.jdshare.cell.CommonPanelView.i
        public void a(com.jingdong.c.a.c.b bVar) {
            if (ShareActivity.this.P0()) {
                OKLog.d("ShareActivity", "click: " + bVar.a);
                ShareActivity.this.f7957n = bVar.a;
                if (!bVar.f12266c) {
                    String str = "1_0";
                    if (TextUtils.equals(ShareActivity.this.f7957n, "JDFriends")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("1_0");
                        sb.append(bVar.f12269g ? "_2" : "_1");
                        str = sb.toString();
                    }
                    ShareActivity shareActivity = ShareActivity.this;
                    shareActivity.V0("Share_", shareActivity.f7954k.getUrl(), str);
                } else {
                    ShareActivity shareActivity2 = ShareActivity.this;
                    shareActivity2.V0("Share_", shareActivity2.f7953j.f12281i != null ? ShareActivity.this.f7953j.f12281i.optString("url") : "", "3_0");
                }
                ShareActivity.this.F0(bVar);
            }
        }

        @Override // com.jingdong.sdk.jdshare.cell.CommonPanelView.i
        public void b() {
            com.jingdong.sdk.jdshare.utils.g.m(ClickConstant.CLICK_SHARE_VALUE_SHAKE_HOME_CANCEL, "", "1_0_1", ShareActivity.this.f7953j.d, ShareActivity.this.f7954k.getShareSource());
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class d implements Animation.AnimationListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f7962g;

        d(boolean z) {
            this.f7962g = z;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            ShareActivity.this.f7951h.removeAllViews();
            ShareActivity.this.E.set(false);
            if (this.f7962g) {
                if (ShareActivity.this.f7955l.a == 0) {
                    ShareActivity.this.setResult(15, new Intent());
                }
                OKLog.d("ShareActivity", "finish: ");
                ShareActivity.super.finish();
                ShareActivity shareActivity = ShareActivity.this;
                int i2 = R.anim.nothing;
                shareActivity.overridePendingTransition(i2, i2);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            ShareActivity.this.E.set(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class d0 implements Runnable {
        d0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareActivity.this.q1();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ TranslateAnimation f7965g;

        e(TranslateAnimation translateAnimation) {
            this.f7965g = translateAnimation;
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareActivity.this.f7951h.getChildAt(0).startAnimation(this.f7965g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class e0 implements View.OnClickListener {
        e0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class f implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f7968g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ boolean f7969h;

        f(String str, boolean z) {
            this.f7968g = str;
            this.f7969h = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.jdshare.utils.i.d(ShareActivity.this.f7954k, this.f7968g, ShareActivity.this.G, this.f7969h, ShareActivity.this.f7953j.f12286n);
            OKLog.d("ShareActivity", "isMapShare = " + ShareActivity.this.G);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class f0 implements ChannelAdapter.c {
        f0() {
        }

        @Override // com.jingdong.sdk.jdshare.cell.ChannelAdapter.c
        public void a(com.jingdong.c.a.c.b bVar) {
            if (ShareActivity.this.P0()) {
                ShareActivity.this.f7957n = bVar.a;
                ShareActivity shareActivity = ShareActivity.this;
                shareActivity.V0("ShareQRCode_", shareActivity.f7954k.getUrl(), "1_0");
                ShareActivity.this.G0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareInfo shareInfo = ShareActivity.this.f7954k;
            ShareActivity shareActivity = ShareActivity.this;
            HWShareHelper.sendShareMsgInfo(shareInfo, shareActivity, shareActivity.f7953j.f12286n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class g0 implements View.OnClickListener {
        g0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class h implements Runnable {
        h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            WeiboUtils.doWBShare(ShareActivity.this.f7950g, ShareActivity.this.f7954k, ShareActivity.this.f7953j.f12286n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class h0 implements View.OnClickListener {
        h0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = "https://" + Configuration.getNewShareHost() + "/config1/promotion_share_Footer_utf8.html#/shareCenter";
            Bundle bundle = new Bundle();
            bundle.putString("action", RemoteMessageConst.TO);
            bundle.putString("url", str);
            JumpUtil.execJumpByDes("m", ShareActivity.this, bundle);
            com.jingdong.sdk.jdshare.utils.g.m("Share_GiftRule", ShareActivity.this.f7954k.getUrl(), ShareActivity.this.f7956m.f12273c, false, ShareActivity.this.f7954k.getShareSource());
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class i implements HttpGroup.OnCommonListener {
        i() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String str;
            OKLog.d("ShareActivity", "onEnd");
            if (httpResponse == null) {
                ShareActivity.this.f7955l.a = 12;
                ShareActivity.this.f7955l.b = "request jCommand failed";
                ShareActivity.this.y0();
                ShareActivity.this.finish();
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                ShareActivity.this.f7955l.a = 12;
                ShareActivity.this.f7955l.b = "request jCommand failed";
                ShareActivity.this.y0();
                ShareActivity.this.finish();
                return;
            }
            String optString = fastJsonObject.optString("code");
            String optString2 = fastJsonObject.optString("data");
            String optString3 = fastJsonObject.optString("eventParamJson");
            String optString4 = fastJsonObject.optString("message");
            if ("0".equals(optString) && !TextUtils.isEmpty(optString2)) {
                try {
                    ShareActivity.this.m1(optString2, optString3);
                    return;
                } catch (Throwable th) {
                    OKLog.d("ShareActivity", th.toString());
                    ShareActivity.this.finish();
                    return;
                }
            }
            if ("-2".equals(optString)) {
                str = "\u7f51\u7edc\u5f00\u5c0f\u5dee\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5";
            } else {
                str = TextUtils.isEmpty(optString4) ? "\u4eba\u591a\u62e5\u6324\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5" : "";
            }
            if (!TextUtils.isEmpty(str)) {
                optString4 = str;
            }
            ToastUtils.showToastInCenter(JdSdk.getInstance().getApplicationContext(), (byte) 1, optString4, 100);
            ShareActivity.this.f7955l.a = 12;
            ShareActivity.this.f7955l.b = "request jCommand failed";
            ShareActivity.this.y0();
            ShareActivity.this.finish();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            String str;
            JDJSONObject fastJsonObject;
            OKLog.d("ShareActivity", "jCommand: onError");
            if (httpError == null) {
                ShareActivity.this.finish();
                ToastUtils.showToastInCenter(JdSdk.getInstance().getApplicationContext(), (byte) 1, "\u7f51\u7edc\u5f00\u5c0f\u5dee\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", 100);
                return;
            }
            int errorCode = httpError.getErrorCode();
            int jsonCode = httpError.getJsonCode();
            HttpResponse httpResponse = httpError.getHttpResponse();
            String optString = (httpResponse == null || (fastJsonObject = httpResponse.getFastJsonObject()) == null) ? "" : fastJsonObject.optString("message");
            if (TextUtils.isEmpty(optString)) {
                optString = "\u7f51\u7edc\u5f00\u5c0f\u5dee\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5";
                if (jsonCode == 3) {
                    str = "\u767b\u5f55\u540e\u624d\u53ef\u751f\u6210\u53e3\u4ee4\u54e6~";
                } else if (errorCode != 3) {
                    ExceptionReporter.reportKeyShareException("generateKey", "netException", httpError.toString(), "" + errorCode);
                } else {
                    String str2 = "" + httpError.getJsonCode();
                    if (!"-2".equals(str2)) {
                        ExceptionReporter.reportKeyShareException("generateKey", "codeException", httpError.toString(), str2);
                        str = "\u4eba\u591a\u62e5\u6324\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5";
                    }
                }
                optString = str;
            }
            ExceptionReporter.reportKeyShareException("creatErrorApi", "codeException", httpError.toString(), "");
            ToastUtils.showToastInCenter(JdSdk.getInstance().getApplicationContext(), (byte) 1, optString, 100);
            ShareActivity.this.f7955l.a = 12;
            ShareActivity.this.f7955l.b = "request jCommand failed";
            ShareActivity.this.y0();
            ShareActivity.this.finish();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class i0 implements View.OnClickListener {
        i0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.sdk.jdshare.utils.g.m(ClickConstant.CLICK_SHARE_VALUE_SHAKE_HOME_CANCEL, "", "2_" + ShareActivity.this.f7956m.f12273c, false, ShareActivity.this.f7954k.getShareSource());
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class j implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f7977g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f7978h;

        /* loaded from: classes19.dex */
        class a implements a.c {
            a() {
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // com.jingdong.app.mall.basic.c.a.c
            public void a(String str) {
                String str2;
                OKLog.d("ShareActivity", "click key share dialog go share");
                if (str == null) {
                    ShareActivity.this.finish();
                    return;
                }
                str.hashCode();
                char c2 = '\uffff';
                switch (str.hashCode()) {
                    case -1897456180:
                        if (str.equals("QQzone")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1541301387:
                        if (str.equals("QQfriends")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -250999948:
                        if (str.equals("Wxfriends")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 347823071:
                        if (str.equals("Sinaweibo")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 1584365650:
                        if (str.equals("Wxmoments")) {
                            c2 = 4;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                    case 1:
                        str2 = "com.tencent.mobileqq";
                        break;
                    case 2:
                    case 4:
                        if (!ShareActivity.this.H) {
                            str2 = "com.tencent.mm";
                            break;
                        } else {
                            ToastUtils.showToastInCenter(ShareActivity.this.getApplicationContext(), "\u52a9\u529b\u4fe1\u606f\u5df2\u590d\u5236\u6210\u529f\uff0c\n\u8bf7\u5230\u5fae\u4fe1\u4e2d\u7c98\u8d34\u7ed9\u597d\u53cb\u5427\uff01", 3000);
                            str2 = "";
                            break;
                        }
                    case 3:
                        str2 = "com.sina.weibo";
                        break;
                    default:
                        str2 = "";
                        break;
                }
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        Intent launchIntentForPackage = ShareActivity.this.getPackageManager().getLaunchIntentForPackage(str2);
                        if (launchIntentForPackage != null) {
                            ShareActivity.this.startActivity(launchIntentForPackage);
                        }
                    } catch (Throwable th) {
                        OKLog.d("ShareActivity", th.toString());
                    }
                }
                ShareActivity.this.p = System.currentTimeMillis();
                ShareActivity.this.f7955l.a = 11;
                ShareActivity.this.f7955l.d = ShareActivity.this.f7957n;
                com.jingdong.sdk.jdshare.utils.g.m("ShareJingwords_CopyShare", ShareActivity.this.f7957n + CartConstant.KEY_YB_INFO_LINK + ShareActivity.this.f7953j.f12281i.optString("keyId"), "", ShareActivity.this.f7953j.d, com.jingdong.sdk.jdshare.utils.g.h(ShareActivity.this.f7954k, j.this.f7977g));
                ShareActivity.this.y0();
                ShareActivity.this.finish();
            }

            @Override // com.jingdong.app.mall.basic.c.a.c
            public void b() {
                OKLog.d("ShareActivity", "click key share dialog close");
                ShareActivity.this.p = System.currentTimeMillis();
                ShareActivity.this.f7955l.a = 13;
                com.jingdong.sdk.jdshare.utils.g.m("ShareJingwords_Close", ShareActivity.this.f7957n + CartConstant.KEY_YB_INFO_LINK + ShareActivity.this.f7953j.f12281i.optString("keyId"), "", ShareActivity.this.f7953j.d, com.jingdong.sdk.jdshare.utils.g.h(ShareActivity.this.f7954k, j.this.f7977g));
                ShareActivity.this.y0();
                ShareActivity.this.finish();
            }
        }

        j(String str, String str2) {
            this.f7977g = str;
            this.f7978h = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ShareActivity.this.isFinishing()) {
                return;
            }
            String str = ShareActivity.this.f7957n + CartConstant.KEY_YB_INFO_LINK + ShareActivity.this.f7953j.f12281i.optString("keyId");
            String optString = ShareActivity.this.f7953j.f12281i.optString(JshopConst.JSHOP_SEARCH_LIST_KEYTYPE);
            com.jingdong.sdk.jdshare.utils.g.n("ShareJingwords_SharePanel_Expo", str, "", com.jingdong.sdk.jdshare.utils.g.h(ShareActivity.this.f7954k, this.f7977g));
            JDSharedCommandUtils.getInstance().saveShareText(this.f7978h);
            a.b bVar = new a.b(ShareActivity.this);
            bVar.m("10".equals(optString) ? "\u9080\u8bf7\u7801\u5df2\u590d\u5236" : "\u4eac\u53e3\u4ee4\u5df2\u590d\u5236");
            bVar.l(this.f7978h);
            bVar.n(ShareActivity.this.f7957n);
            bVar.k(new a());
            com.jingdong.app.mall.basic.c.a e2 = bVar.e();
            Window window = e2.getWindow();
            if (window == null) {
                return;
            }
            window.setGravity(17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
            e2.show();
            ShareActivity.this.u = true;
        }
    }

    /* loaded from: classes19.dex */
    class k implements Runnable {
        k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareActivity.this.w = true;
            ToastUtils.shortToast(ShareActivity.this, R.string.share_qr_error);
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class l implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f7981g;

        l(String str) {
            this.f7981g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.jdshare.utils.i.d(ShareActivity.this.f7954k, this.f7981g, ShareActivity.this.R0(), true, ShareActivity.this.f7953j.f12286n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class m implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f7983g;

        m(String str) {
            this.f7983g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.jdshare.utils.i.d(ShareActivity.this.f7954k, this.f7983g, false, false, ShareActivity.this.f7953j.f12286n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class n implements Runnable {
        n() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareActivity.this.B.a = ShareActivity.this.f7954k.getTransaction() + "##QQfriends";
            com.jingdong.sdk.jdshare.utils.e.f(ShareActivity.this.f7950g, ShareActivity.this.f7954k, ShareActivity.this.B);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class o implements Runnable {
        o() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareActivity.this.B.a = ShareActivity.this.f7954k.getTransaction() + "##QQzone";
            com.jingdong.sdk.jdshare.utils.e.h(ShareActivity.this.f7950g, ShareActivity.this.f7954k, ShareActivity.this.B);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class p implements Runnable {
        p() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareActivity.this.n1();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class q implements HttpGroup.OnCommonListener {
        q() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            float f2 = 240.0f;
            int i2 = 32768;
            try {
                if (ShareActivity.this.R0()) {
                    f2 = 720.0f;
                    i2 = 131072;
                }
                if (ShareActivity.this.Q0()) {
                    f2 = 570.0f;
                    i2 = 153600;
                }
                Bitmap N0 = ShareActivity.this.N0(httpResponse, f2);
                if (N0 == null) {
                    ShareActivity.this.j1();
                    return;
                }
                Bitmap createBitmap = Bitmap.createBitmap(N0.getWidth(), N0.getHeight(), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(-1);
                canvas.drawBitmap(N0, 0.0f, 0.0f, (Paint) null);
                ShareActivity.this.f7953j.f12286n = com.jingdong.sdk.jdshare.utils.g.c(createBitmap, i2);
                if (ShareActivity.this.v != null) {
                    ShareActivity.this.v.run();
                }
            } catch (Throwable th) {
                OKLog.e("ShareActivity", th);
                ShareActivity.this.j1();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            ShareActivity.this.j1();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class r implements HttpGroup.OnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f7989g;

        r(int i2) {
            this.f7989g = i2;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            Bitmap N0 = ShareActivity.this.N0(httpResponse, 1080.0f);
            if (N0 == null || N0.getByteCount() < 1) {
                return;
            }
            int i2 = this.f7989g;
            if (i2 == 3) {
                ShareActivity.this.r = N0;
                ShareActivity.this.X0();
                return;
            }
            if (i2 == 1) {
                ShareActivity.this.s = N0;
            } else if (i2 == 2) {
                ShareActivity.this.t = N0;
            }
            ShareActivity.this.W0();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class s implements Runnable {
        s() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ToastUtils.shortToast(ShareActivity.this, R.string.share_qr_error);
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class t implements Action1<String> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes19.dex */
        public class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ String f7993g;

            /* renamed from: com.jingdong.app.mall.basic.ShareActivity$t$a$a  reason: collision with other inner class name */
            /* loaded from: classes19.dex */
            class RunnableC0238a implements Runnable {
                RunnableC0238a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    ToastUtils.shortToast(ShareActivity.this, R.string.share_qr_error);
                    ShareActivity.this.finish();
                }
            }

            a(String str) {
                this.f7993g = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                View x0 = ShareActivity.this.f7954k.getShareImageInfo().imageShareType == 2 ? ShareActivity.this.x0(this.f7993g) : ShareActivity.this.w0(this.f7993g);
                if (x0 != null && ShareActivity.this.b1(x0)) {
                    ShareActivity.this.d1();
                    ShareActivity.this.Y0();
                    return;
                }
                ShareActivity.this.post(new RunnableC0238a(), 1500);
            }
        }

        t() {
        }

        @Override // rx.functions.Action1
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void call(String str) {
            ShareActivity.this.post(new a(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class u implements Runnable {
        u() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ToastUtils.shortToast(ShareActivity.this, R.string.share_failed_try_again);
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class v implements View.OnClickListener {
        v() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TextUtils.isEmpty(ShareActivity.this.f7957n) || !"QRCode".equals(ShareActivity.this.f7957n)) {
                ShareActivity.this.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class w implements HttpGroup.OnEndListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f7998g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Runnable f7999h;

        w(long j2, Runnable runnable) {
            this.f7998g = j2;
            this.f7999h = runnable;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (System.currentTimeMillis() - this.f7998g > 3000) {
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            int optInt = fastJsonObject.optInt("code", -1);
            String optString = fastJsonObject.optString("finalurl", "");
            if (optInt != 0 || TextUtils.isEmpty(optString)) {
                return;
            }
            ShareActivity.this.getHandler().removeCallbacks(this.f7999h);
            ShareActivity.this.f7954k.setCpsUrl(optString);
            ShareActivity.this.h1();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class x implements HttpGroup.OnErrorListener {
        x() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            ShareActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class y implements Runnable {
        y() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShareActivity.this.Z0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class z implements HttpGroup.OnEndListener {
        z() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                OKLog.d("ShareActivity", "reportLottery: jsonObj is null");
                return;
            }
            OKLog.d("ShareActivity", "reportLottery: " + fastJsonObject.toJSONString());
            if (fastJsonObject.optInt("resultCode", -1) != 200) {
                return;
            }
            if (!fastJsonObject.optBoolean("showXView", true) || (ShareActivity.this.f7956m.a != 1 && ShareActivity.this.f7956m.a != 2)) {
                ShareActivity.this.y = false;
                ShareActivity.this.finish();
                return;
            }
            ShareActivity.this.z = true;
            ShareActivity.this.A0();
            com.jingdong.sdk.jdshare.utils.g.m("Share_XviewExpo", "", ShareActivity.this.f7956m.f12273c, false, ShareActivity.this.f7954k.getShareSource());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0() {
        if (this.z && this.A) {
            post(new b0());
        }
    }

    private void B0() {
        if (!HWShareHelper.isSupportHwCaasShare()) {
            g1();
            return;
        }
        this.v = new g();
        h1();
    }

    private void C0() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("appCode", (Object) "jApp");
        jDJSONObject.put("command", (Object) this.f7953j.f12281i);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setConnectTimeout(5000);
        httpSetting.setFunctionId("jCommand");
        httpSetting.setPost(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setType(1000);
        httpSetting.putJsonParam(jDJSONObject);
        httpSetting.setListener(new i());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void D0(boolean z2, boolean z3) {
        if (!com.jingdong.sdk.jdshare.utils.e.a(this)) {
            g1();
        } else if (z2) {
            C0();
        } else if (z3) {
            this.B.a = this.f7954k.getTransaction() + "##QQfriends";
            com.jingdong.sdk.jdshare.utils.e.f(this.f7950g, this.f7954k, this.B);
        } else {
            this.B.a = this.f7954k.getTransaction() + "##QQzone";
            com.jingdong.sdk.jdshare.utils.e.h(this.f7950g, this.f7954k, this.B);
        }
    }

    private void E0() {
        ShareInfo shareInfo = this.f7954k;
        if (shareInfo != null && shareInfo.getShareImageInfo() != null && (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().productUrl) || this.f7954k.getShareImageInfo().imageShareType == 2 || !TextUtils.isEmpty(this.f7954k.getShareImageInfo().productPath) || !TextUtils.isEmpty(this.f7954k.getShareImageInfo().directUrl) || !TextUtils.isEmpty(this.f7954k.getShareImageInfo().directPath))) {
            this.w = false;
            if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().directPath)) {
                this.q = this.f7954k.getShareImageInfo().directPath;
                U0();
                return;
            }
            if (!this.f7953j.v) {
                ToastUtils.shortToast(this, R.string.share_making_pic);
            }
            post(this.x, 6000);
            if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().directUrl) && this.f7954k.getShareImageInfo().imageShareType != 2) {
                L0(this.f7954k.getShareImageInfo().isDecodeDirectUrl == 1 ? this.f7954k.getShareImageInfo().directUrl : ShareUtil.urlDecode(this.f7954k.getShareImageInfo().directUrl), 3);
                return;
            }
            if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().productPath)) {
                this.t = com.jingdong.sdk.jdshare.utils.g.g(this.f7954k.getShareImageInfo().productPath, 1080.0f);
                W0();
            } else {
                L0(ShareUtil.urlDecode(this.f7954k.getShareImageInfo().productUrl), 2);
            }
            if (this.f7954k.getShareImageInfo().imageShareType != 2) {
                L0(ShareUtil.urlDecode(this.f7954k.getShareImageInfo().logoUrl), 1);
                return;
            }
            return;
        }
        ToastUtils.shortToast(this, R.string.share_setting_none);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0(@NonNull com.jingdong.c.a.c.b bVar) {
        com.jingdong.sdk.jdshare.utils.a.d(bVar.f12266c, this.f7954k, this.f7957n, this.f7953j.d);
        this.p = System.currentTimeMillis();
        String str = this.f7957n;
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1898171474:
                if (str.equals("QRCode")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1897456180:
                if (str.equals("QQzone")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1676803686:
                if (str.equals("CopyURL")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1541301387:
                if (str.equals("QQfriends")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1291066629:
                if (str.equals("JDFriends")) {
                    c2 = 4;
                    break;
                }
                break;
            case -250999948:
                if (str.equals("Wxfriends")) {
                    c2 = 5;
                    break;
                }
                break;
            case 347823071:
                if (str.equals("Sinaweibo")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1584365650:
                if (str.equals("Wxmoments")) {
                    c2 = 7;
                    break;
                }
                break;
            case 1827474496:
                if (str.equals(ShareUtil.S_Hw_CaasShare)) {
                    c2 = '\b';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                E0();
                return;
            case 1:
                D0(bVar.f12266c, false);
                return;
            case 2:
                com.jingdong.sdk.jdshare.utils.f.a(this, this.f7954k, this);
                return;
            case 3:
                D0(bVar.f12266c, true);
                return;
            case 4:
                com.jingdong.sdk.jdshare.utils.f.b(this, bVar, this.f7954k, this);
                return;
            case 5:
                K0(bVar, true);
                return;
            case 6:
                J0(bVar.f12266c);
                return;
            case 7:
                K0(bVar, false);
                return;
            case '\b':
                B0();
                return;
            default:
                finish();
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G0() {
        boolean b2;
        if (!TextUtils.isEmpty(this.q) && FileUtils.fileIsExists(this.q)) {
            if (this.r == null) {
                this.r = com.jingdong.sdk.jdshare.utils.g.g(this.q, 1080.0f);
            }
            if (this.r != null && !T0()) {
                String b3 = com.jingdong.sdk.jdshare.utils.g.b(this, this.q);
                if (TextUtils.isEmpty(b3)) {
                    ToastUtils.shortToast(this, R.string.share_qr_error);
                    finish();
                    return;
                }
                if (TextUtils.equals("Wxfriends", this.f7957n) || TextUtils.equals("Wxmoments", this.f7957n)) {
                    b2 = com.jingdong.sdk.jdshare.utils.i.b();
                    if (b2) {
                        b3 = com.jingdong.sdk.jdshare.utils.g.o(this, "com.tencent.mm", b3);
                    }
                    if (TextUtils.isEmpty(b3)) {
                        ToastUtils.shortToast(this, R.string.share_qr_error);
                        finish();
                        return;
                    }
                } else {
                    b2 = false;
                }
                this.p = System.currentTimeMillis();
                StringBuilder sb = new StringBuilder();
                sb.append("1_0_");
                sb.append(!TextUtils.isEmpty(this.f7954k.getMpId()) ? "2" : "1");
                sb.append(this.f7953j.d ? "_0" : "_1");
                String sb2 = sb.toString();
                if ("Wxfriends".equals(this.f7957n)) {
                    if (!com.jingdong.sdk.jdshare.utils.i.a()) {
                        g1();
                        return;
                    } else if (b2) {
                        com.jingdong.sdk.jdshare.utils.i.f(this.f7954k, sb2, true, this.f7953j.f12286n, b3);
                        return;
                    } else {
                        com.jingdong.sdk.jdshare.utils.i.e(this.f7954k, sb2, true, this.f7953j.f12286n, M0());
                        return;
                    }
                } else if ("Wxmoments".equals(this.f7957n)) {
                    if (!com.jingdong.sdk.jdshare.utils.i.a()) {
                        g1();
                        return;
                    } else if (b2) {
                        com.jingdong.sdk.jdshare.utils.i.f(this.f7954k, sb2, false, this.f7953j.f12286n, b3);
                        return;
                    } else {
                        com.jingdong.sdk.jdshare.utils.i.e(this.f7954k, sb2, false, this.f7953j.f12286n, M0());
                        return;
                    }
                } else if ("QQfriends".equals(this.f7957n)) {
                    if (com.jingdong.sdk.jdshare.utils.e.a(this)) {
                        this.B.a = this.f7954k.getTransaction() + "##QQfriends";
                        com.jingdong.sdk.jdshare.utils.e.g(this.f7950g, this.f7954k, b3, this.B);
                        return;
                    }
                    g1();
                    return;
                } else if (ShareUtil.S_JD_SAVE_IMG.equals(this.f7957n)) {
                    if (com.jingdong.sdk.jdshare.utils.b.e(this, new File(b3))) {
                        ToastUtils.shortToast(this, "\u56fe\u7247\u5df2\u4fdd\u5b58\u81f3\u76f8\u518c");
                        return;
                    }
                    com.jingdong.sdk.jdshare.utils.b.f(this);
                    ToastUtils.shortToast(this, "\u56fe\u7247\u4fdd\u5b58\u5931\u8d25");
                    return;
                } else {
                    finish();
                    return;
                }
            }
            ToastUtils.shortToast(this, R.string.share_qr_error);
            finish();
            return;
        }
        ToastUtils.shortToast(this, R.string.share_qr_error);
        finish();
    }

    private void H0() {
        if (!"Wxfriends".equalsIgnoreCase(this.f7957n) && !"Wxmoments".equalsIgnoreCase(this.f7957n) && !"QQfriends".equalsIgnoreCase(this.f7957n)) {
            finish();
        } else if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().directPath)) {
            String str = this.f7954k.getShareImageInfo().directPath;
            this.q = str;
            this.r = com.jingdong.sdk.jdshare.utils.g.g(str, 1080.0f);
            d1();
            G0();
        } else if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().directUrl)) {
            this.p = System.currentTimeMillis();
            ToastUtils.shortToast(this, R.string.share_making_pic);
            post(this.x, 6000);
            L0(ShareUtil.urlDecode(this.f7954k.getShareImageInfo().directUrl), 3);
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0() {
        this.p = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("2_");
        sb.append(this.f7956m.f12273c);
        sb.append("_1");
        sb.append(this.f7953j.d ? "_0" : "_1");
        String sb2 = sb.toString();
        if ("Wxfriends".equals(this.f7957n)) {
            if (com.jingdong.sdk.jdshare.utils.i.a()) {
                this.v = new l(sb2);
                v0();
                return;
            }
            g1();
        } else if ("Wxmoments".equals(this.f7957n)) {
            if (com.jingdong.sdk.jdshare.utils.i.a()) {
                this.v = new m(sb2);
                v0();
                return;
            }
            g1();
        } else if ("QQfriends".equals(this.f7957n)) {
            if (com.jingdong.sdk.jdshare.utils.e.a(this)) {
                this.v = new n();
                v0();
                return;
            }
            g1();
        } else if ("QQzone".equals(this.f7957n)) {
            if (com.jingdong.sdk.jdshare.utils.e.a(this)) {
                this.v = new o();
                v0();
                return;
            }
            g1();
        }
    }

    private void J0(boolean z2) {
        if (!WeiboUtils.check(this)) {
            g1();
        } else if (!z2) {
            this.v = new h();
            h1();
        } else {
            C0();
        }
    }

    private void K0(com.jingdong.c.a.c.b bVar, boolean z2) {
        if (!com.jingdong.sdk.jdshare.utils.i.a()) {
            g1();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("1_0_");
        sb.append(!TextUtils.isEmpty(this.f7954k.getMpId()) ? "2" : "1");
        sb.append(this.f7953j.d ? "_0" : "_1");
        String sb2 = sb.toString();
        this.G = bVar.f12270h == 2;
        if (!bVar.f12266c) {
            this.v = new f(sb2, z2);
            h1();
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("url", (Object) this.f7954k.getUrl());
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "Share_Wxfriends_JWords", this.f7954k.getUrl(), "", "", "ShareActivity", sb2, "", jDJSONObject.toJSONString(), null);
        C0();
    }

    private void L0(String str, int i2) {
        if (!TextUtils.isEmpty(str) || i2 == 2) {
            r rVar = new r(i2);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(str);
            httpSetting.setConnectTimeout(5000);
            httpSetting.setAttempts(1);
            httpSetting.setEffect(1);
            httpSetting.setCacheMode(0);
            httpSetting.setType(5000);
            httpSetting.setListener(rVar);
            httpSetting.setNeedShareImage(false);
            IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (this.f7953j.v && currentMyActivity != null) {
                currentMyActivity.getHttpGroupaAsynPool(5000).add(httpSetting);
            } else {
                HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
            }
        }
    }

    private Bitmap M0() {
        return com.jingdong.sdk.jdshare.utils.g.f(com.jingdong.sdk.jdshare.utils.g.c(this.r, 10485760), 1080.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap N0(HttpResponse httpResponse, float f2) {
        File saveFile = httpResponse.getSaveFile();
        if (saveFile != null) {
            return com.jingdong.sdk.jdshare.utils.g.g(saveFile.getPath(), f2);
        }
        byte[] inputData = httpResponse.getInputData();
        if (inputData != null) {
            return com.jingdong.sdk.jdshare.utils.g.f(inputData, f2);
        }
        return null;
    }

    private void O0() {
        setContentView(R.layout.share_activity);
        this.f7951h = (RelativeLayout) findViewById(R.id.share_activity);
        if (!this.f7953j.e()) {
            this.f7951h.setBackgroundColor(Color.parseColor("#aa000000"));
        }
        this.f7951h.setOnClickListener(new v());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean P0() {
        boolean z2 = this.C == 0 || System.currentTimeMillis() - this.C > 220;
        if (z2) {
            this.C = System.currentTimeMillis();
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Q0() {
        return (!ShareUtil.S_Hw_CaasShare.equals(this.f7957n) || TextUtils.isEmpty(this.f7954k.getBundleName()) || TextUtils.isEmpty(this.f7954k.getAbilityName())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean R0() {
        return ShareValues.isNewWeiXinShareUI ? this.G && "Wxfriends".equals(this.f7957n) && !TextUtils.isEmpty(this.f7954k.getMpId()) : "Wxfriends".equals(this.f7957n) && !TextUtils.isEmpty(this.f7954k.getMpId());
    }

    private boolean S0() {
        if (!R0() || TextUtils.isEmpty(this.f7954k.getMpLocalIconPath())) {
            return false;
        }
        try {
            return new File(this.f7954k.getMpLocalIconPath()).exists();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private boolean T0() {
        byte[] bArr = this.f7953j.f12286n;
        if (bArr == null || bArr.length == 0) {
            return true;
        }
        return R0() ? this.f7953j.f12286n.length > 131072 : this.f7953j.f12286n.length > 32768;
    }

    private void U0() {
        Bitmap g2 = com.jingdong.sdk.jdshare.utils.g.g(this.q, 1080.0f);
        this.r = g2;
        if (g2 == null) {
            finish();
            return;
        }
        d1();
        Y0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V0(String str, String str2, String str3) {
        if (!TextUtils.equals(this.f7957n, ShareUtil.S_JD_SAVE_IMG)) {
            closePanelAnimation(false);
        }
        ShareUtil.ClickCallbackListener clickCallbackListener = this.f7953j.r;
        if (clickCallbackListener != null) {
            clickCallbackListener.onClick(this.f7957n);
        }
        String str4 = this.f7957n;
        String str5 = str + str4;
        if (TextUtils.equals("JDFriends", str4)) {
            com.jingdong.sdk.jdshare.utils.g.l(str5, str2, str3, this.f7954k.getShareSource());
        } else if (TextUtils.equals(this.f7957n, "Wxfriends")) {
        } else {
            if ("Share_".equals(str) && !"3_0".equals(str3)) {
                String str6 = str3 + CartConstant.KEY_YB_INFO_LINK;
                StringBuilder sb = new StringBuilder();
                sb.append(str6);
                sb.append((!TextUtils.equals(this.f7957n, "Wxfriends") || TextUtils.isEmpty(this.f7954k.getMpId())) ? "1" : "2");
                str3 = sb.toString();
            }
            com.jingdong.sdk.jdshare.utils.g.m(str5, str2, str3, this.f7953j.d, this.f7954k.getShareSource());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W0() {
        if (this.f7954k == null || TextUtils.isEmpty(this.f7957n) || !"QRCode".equals(this.f7957n)) {
            return;
        }
        if ((!TextUtils.isEmpty(this.f7954k.getShareImageInfo().logoUrl) && this.s == null && this.f7954k.getShareImageInfo().imageShareType != 2) || this.t == null || this.w) {
            return;
        }
        getHandler().removeCallbacks(this.x);
        ShareUtil.convertShortUrl(ShareUtil.getShareUrl(this.f7954k.getUrl(), "QRCode"), new t());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0() {
        if (this.w || this.r == null) {
            return;
        }
        getHandler().removeCallbacks(this.x);
        if (!c1()) {
            post(new s(), 1500);
            return;
        }
        d1();
        if (!"QRCode".equalsIgnoreCase(this.f7957n)) {
            G0();
        } else {
            Y0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y0() {
        post(new d0(), 250);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z0() {
        XViewEntity xViewEntity = new XViewEntity();
        xViewEntity.needAutoDisplay = false;
        xViewEntity.url = "https://" + Configuration.getNewShareHost() + "/share/reward.html?shareActivityId=" + this.f7956m.b + "&shareToken=" + this.f7956m.f12274e;
        XView createXView = XViewHelper.createXView(this.f7950g, this.f7951h, "ShareActivity", xViewEntity, new a0());
        this.f7952i = createXView;
        if (createXView != null) {
            createXView.preloadXView();
        }
    }

    private void a1() {
        if (TextUtils.isEmpty(this.f7956m.f12274e)) {
            return;
        }
        this.y = true;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("shareSuccess");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.putJsonParam("type", this.f7956m.f12273c);
        httpSetting.putJsonParam("bizId", this.f7956m.d);
        httpSetting.putJsonParam("channel", "2");
        httpSetting.putJsonParam("activityId", Long.valueOf(this.f7956m.b));
        httpSetting.putJsonParam("token", this.f7956m.f12274e);
        httpSetting.setListener(new z());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b1(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.RGB_565);
        view.draw(new Canvas(createBitmap));
        Bitmap bitmap = this.s;
        if (bitmap != null) {
            bitmap.recycle();
            this.s = null;
        }
        Bitmap bitmap2 = this.t;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.t = null;
        }
        this.r = com.jingdong.sdk.jdshare.utils.g.j(createBitmap, 1080.0f);
        return c1();
    }

    private boolean c1() {
        Bitmap bitmap = this.r;
        if (bitmap == null) {
            return false;
        }
        byte[] c2 = com.jingdong.sdk.jdshare.utils.g.c(bitmap, 10485760);
        this.q = com.jingdong.sdk.jdshare.utils.g.e();
        return FileService.saveToSDCard(FileService.getDirectory(1), "share_qrcode_image.png", c2);
    }

    private boolean closePanelAnimation(boolean z2) {
        RelativeLayout relativeLayout;
        if (this.E.getAndSet(false) || (relativeLayout = this.f7951h) == null || relativeLayout.getChildCount() != 1) {
            return false;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200L);
        translateAnimation.setAnimationListener(new d(z2));
        post(new e(translateAnimation));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d1() {
        Bitmap bitmap = this.r;
        if (bitmap == null) {
            return;
        }
        Bitmap j2 = com.jingdong.sdk.jdshare.utils.g.j(bitmap, 240.0f);
        this.f7953j.f12286n = com.jingdong.sdk.jdshare.utils.g.c(j2, 32768);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e1() {
        this.f7956m.f12274e = Md5Encrypt.md5(LoginUserBase.getLoginUserName() + System.currentTimeMillis());
        long currentTimeMillis = System.currentTimeMillis();
        u uVar = new u();
        post(uVar, 3500);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setPost(true);
        httpSetting.setFunctionId("shareApp");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.putJsonParam(SocialConstants.PARAM_SHARE_URL, ShareUtil.getShareUrlOnlyRes(this.f7954k.getUrl(), this.f7957n));
        httpSetting.putJsonParam("type", this.f7956m.f12273c);
        httpSetting.putJsonParam("shareRuleType", String.valueOf(this.f7956m.a));
        httpSetting.putJsonParam("activityId", String.valueOf(this.f7956m.b));
        httpSetting.putJsonParam("token", this.f7956m.f12274e);
        httpSetting.setListener(new w(currentTimeMillis, uVar));
        httpSetting.setListener(new x());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        int i2 = this.f7956m.a;
        if (i2 == 1 || i2 == 2) {
            post(new y(), R2.attr.decimalNumber);
        }
    }

    private void f1() {
        String str;
        ShareInfo shareInfo;
        if (this.f7953j == null) {
            return;
        }
        String str2 = this.f7957n;
        ShareInfo shareInfo2 = this.f7954k;
        if (shareInfo2 != null && !TextUtils.isEmpty(shareInfo2.getCpsUrl())) {
            str = str2 + "_2_" + this.f7956m.f12273c;
        } else {
            str = str2 + "_1_0";
        }
        if (TextUtils.isEmpty(this.f7955l.f12287c) && (shareInfo = this.f7954k) != null) {
            this.f7955l.f12287c = shareInfo.getUrl();
        }
        ShareInfo shareInfo3 = this.f7954k;
        String shareSource = shareInfo3 != null ? shareInfo3.getShareSource() : "";
        com.jingdong.c.a.c.g gVar = this.f7955l;
        int i2 = gVar.a;
        if (i2 == 11) {
            com.jingdong.sdk.jdshare.utils.g.m("Share_ShareSuccess", gVar.f12287c, str, this.f7953j.d, shareSource);
        } else if (i2 == 13) {
            com.jingdong.sdk.jdshare.utils.g.m("Share_ShareCancel", gVar.f12287c, str, this.f7953j.d, shareSource);
        } else if (i2 == 12) {
            com.jingdong.sdk.jdshare.utils.g.m("Share_ShareFail", gVar.f12287c, str, this.f7953j.d, shareSource);
        }
    }

    private void g1() {
        com.jingdong.c.a.c.g gVar = this.f7955l;
        gVar.a = 14;
        gVar.b = "check failed";
        y0();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h1() {
        if (this.v == null) {
            return;
        }
        if (!T0()) {
            this.v.run();
        } else if (S0()) {
            i1();
        } else if (TextUtils.isEmpty(this.f7954k.getIconUrl())) {
            j1();
        } else {
            r1();
        }
    }

    private void i1() {
        Bitmap g2 = com.jingdong.sdk.jdshare.utils.g.g(this.f7954k.getMpLocalIconPath(), 720.0f);
        if (g2 != null) {
            this.f7953j.f12286n = com.jingdong.sdk.jdshare.utils.g.c(g2, 131072);
            g2.recycle();
            Runnable runnable = this.v;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        j1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j1() {
        Drawable drawable = ContextCompat.getDrawable(this.f7950g, R.drawable.share_default_icon);
        if (drawable == null) {
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        this.f7953j.f12286n = com.jingdong.sdk.jdshare.utils.g.c(bitmap, 32768);
        Runnable runnable = this.v;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void k1() {
        q qVar = new q();
        String iconUrl = (!R0() || TextUtils.isEmpty(this.f7954k.getMpIconUrl())) ? this.f7954k.getIconUrl() : this.f7954k.getMpIconUrl();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(iconUrl);
        httpSetting.setConnectTimeout(5000);
        httpSetting.setAttempts(1);
        httpSetting.setCacheMode(0);
        httpSetting.setType(5000);
        httpSetting.setListener(qVar);
        httpSetting.setNeedShareImage(false);
        HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
    }

    private void l1() {
        CommonPanelView commonPanelView = new CommonPanelView(this);
        commonPanelView.o(new c0());
        com.jingdong.c.a.c.f fVar = this.f7953j;
        if (!fVar.v) {
            commonPanelView.f(fVar);
        }
        commonPanelView.n(this.f7951h);
        if (this.f7953j.v) {
            this.f7957n = "QRCode";
            E0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m1(String str, String str2) {
        post(new j(str2, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n1() {
        View inflate = getLayoutInflater().inflate(R.layout.share_layout_login, (ViewGroup) null);
        if (inflate == null) {
            return;
        }
        inflate.setLayoutParams(new FrameLayout.LayoutParams(DPIUtil.getWidth(), DPIUtil.getHeight()));
        this.f7951h.addView(inflate);
        ((TextView) findViewById(R.id.share_login_content)).setText(this.f7956m.f12275f);
        findViewById(R.id.share_login_back).setOnClickListener(new b());
        findViewById(R.id.share_login_go).setOnClickListener(new c());
    }

    private void o1() {
        p1(R.layout.share_layout_lottery);
        findViewById(R.id.share_lottery_rule).setOnClickListener(new h0());
        findViewById(R.id.share_layout_cancel).setOnClickListener(new i0());
        JDGridView jDGridView = (JDGridView) findViewById(R.id.share_channels);
        jDGridView.setNumColumns(DPIUtil.getWidth() > DPIUtil.getHeight() ? 5 : 4);
        jDGridView.setAdapter((ListAdapter) new com.jingdong.sdk.jdshare.cell.a(this, this.f7953j.f12276c.a));
        jDGridView.setOnItemClickListener(new a());
        if (!TextUtils.isEmpty(this.f7956m.f12275f)) {
            ((TextView) findViewById(R.id.share_lottery_content)).setText(this.f7956m.f12275f);
        }
        com.jingdong.sdk.jdshare.utils.g.k("Share_SharePanelPop", "2_" + this.f7956m.f12273c, this.f7953j);
    }

    private void p1(int i2) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200L);
        View inflate = getLayoutInflater().inflate(i2, (ViewGroup) null);
        this.f7951h.addView(inflate);
        inflate.startAnimation(translateAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q1() {
        if (this.r == null) {
            return;
        }
        View inflate = getLayoutInflater().inflate(R.layout.share_layout_image_new, (ViewGroup) null);
        if (this.f7953j.v) {
            ((RelativeLayout) inflate.findViewById(R.id.share_layout_image)).setOnClickListener(new e0());
        }
        if (!this.f7953j.v) {
            ScrollView scrollView = (ScrollView) inflate.findViewById(R.id.share_pic_scroll);
            scrollView.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
            if (this.f7954k.getShareImageInfo() != null && this.f7954k.getShareImageInfo().isBizCustom == 1) {
                layoutParams.width = com.jingdong.sdk.jdshare.utils.g.d(this, 400);
                layoutParams.topMargin = com.jingdong.sdk.jdshare.utils.g.d(this, 89);
                layoutParams.bottomMargin = com.jingdong.sdk.jdshare.utils.g.d(this, R2.attr.boxStrokeErrorColor);
            } else {
                layoutParams.width = com.jingdong.sdk.jdshare.utils.g.d(this, R2.attr.blendSrc);
                layoutParams.topMargin = com.jingdong.sdk.jdshare.utils.g.d(this, R2.anim.slide_in_from_left);
                layoutParams.bottomMargin = com.jingdong.sdk.jdshare.utils.g.d(this, 500);
            }
            com.jingdong.sdk.jdshare.utils.h.a(scrollView, com.jingdong.sdk.jdshare.utils.g.d(this, 24));
            ImageView imageView = (ImageView) inflate.findViewById(R.id.share_big_img);
            imageView.setImageBitmap(this.r);
            com.jingdong.sdk.jdshare.utils.h.a(imageView, com.jingdong.sdk.jdshare.utils.g.d(this, 24));
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setShape(0);
        float d2 = com.jingdong.sdk.jdshare.utils.g.d(this, 24);
        gradientDrawable.setCornerRadii(new float[]{d2, d2, d2, d2, 0.0f, 0.0f, 0.0f, 0.0f});
        ((LinearLayout) inflate.findViewById(R.id.img_panel_layout)).setBackgroundDrawable(gradientDrawable);
        TextView textView = (TextView) inflate.findViewById(R.id.img_panel_title);
        textView.setOnClickListener(null);
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).topMargin = com.jingdong.sdk.jdshare.utils.g.d(this, 36);
        textView.setTextSize(0, com.jingdong.sdk.jdshare.utils.g.d(this, 28));
        textView.setText("\u5206\u4eab\u5f53\u524d\u56fe\u7247\u5230");
        List<com.jingdong.c.a.c.b> b2 = com.jingdong.sdk.jdshare.utils.d.b(this);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_share_img_channel);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams2.topMargin = com.jingdong.sdk.jdshare.utils.g.d(this, 28);
        if (b2.size() <= 4) {
            layoutParams2.gravity = 1;
            layoutParams2.width = -2;
        } else {
            recyclerView.setClipToPadding(false);
            recyclerView.setPadding(com.jingdong.sdk.jdshare.utils.g.d(this, 43), 0, com.jingdong.sdk.jdshare.utils.g.d(this, 43), 0);
            layoutParams2.width = -1;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        ChannelAdapter channelAdapter = new ChannelAdapter(this, b2, this.f7954k.getUrl());
        channelAdapter.o(new f0());
        recyclerView.setAdapter(channelAdapter);
        recyclerView.addItemDecoration(new ChannelItemSpaceDecoration(com.jingdong.sdk.jdshare.utils.g.d(this, 14)));
        TextView textView2 = (TextView) inflate.findViewById(R.id.share_layout_img_cancel);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) textView2.getLayoutParams();
        layoutParams3.height = com.jingdong.sdk.jdshare.utils.g.d(this, 102);
        layoutParams3.topMargin = com.jingdong.sdk.jdshare.utils.g.d(this, 35);
        textView2.setTextSize(0, com.jingdong.sdk.jdshare.utils.g.d(this, 32));
        textView2.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
        textView2.setOnClickListener(new g0());
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200L);
        inflate.requestLayout();
        this.f7951h.addView(inflate, new RelativeLayout.LayoutParams(-1, -1));
        inflate.startAnimation(translateAnimation);
    }

    private int r0(int i2) {
        return com.jingdong.sdk.jdshare.utils.g.d(this, i2);
    }

    private void r1() {
        try {
            k1();
        } catch (Throwable th) {
            OKLog.e("ShareActivity", th);
            j1();
        }
    }

    private void s0() {
        OKLog.d("ShareActivity", "callbackForResult");
        ShareUtil.CallbackListener callbackListener = this.f7953j.q;
        if (callbackListener != null) {
            com.jingdong.c.a.c.g gVar = this.f7955l;
            int i2 = gVar.a;
            if (i2 == 11) {
                callbackListener.onComplete(gVar.d);
            } else if (i2 == 13) {
                callbackListener.onCancel();
            } else if (i2 == 12) {
                callbackListener.onError(gVar.b);
            }
        }
    }

    private boolean t0() {
        if (this.f7953j.g()) {
            OKLog.d("ShareActivity", "ShareActivity start action: back,shareChanel: " + this.f7957n);
            f1();
            finish();
            return false;
        } else if (this.f7953j.d()) {
            return true;
        } else {
            ToastUtils.shortToast(this, R.string.share_setting_none);
            finish();
            return false;
        }
    }

    private boolean u0() {
        if (getIntent() == null) {
            ToastUtils.shortToast(this, R.string.share_setting_none);
            finish();
            return false;
        }
        return true;
    }

    private void v0() {
        if (TextUtils.isEmpty(LoginUserBase.getUserPin())) {
            post(new p(), 250);
        } else {
            e1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View w0(String str) {
        LinearLayout linearLayout = new LinearLayout(this.f7950g);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(DPIUtil.getWidth(), -2));
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-1);
        RelativeLayout relativeLayout = new RelativeLayout(this.f7950g);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(DPIUtil.getWidth(), r0(144)));
        relativeLayout.setPadding(r0(40), r0(50), r0(40), r0(40));
        linearLayout.addView(relativeLayout);
        ImageView imageView = new ImageView(this.f7950g);
        int i2 = R.id.img_1;
        imageView.setId(i2);
        relativeLayout.addView(imageView);
        if (this.s != null && r10.getWidth() / this.s.getHeight() < 4.26d) {
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(r0(R2.anim.miaosha_dropdown_in), r0(54)));
            imageView.setImageDrawable(ContextCompat.getDrawable(this.f7950g, R.drawable.share_qr_logo_short));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(r0(10), r0(10));
            layoutParams.leftMargin = r0(14);
            layoutParams.rightMargin = r0(14);
            layoutParams.topMargin = r0(22);
            layoutParams.addRule(1, i2);
            TextView textView = new TextView(this.f7950g);
            int i3 = R.id.text1;
            textView.setId(i3);
            textView.setLayoutParams(layoutParams);
            textView.setBackgroundResource(R.drawable.button_b_02);
            relativeLayout.addView(textView);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((r0(54) * this.s.getWidth()) / this.s.getHeight(), r0(54));
            layoutParams2.addRule(1, i3);
            ImageView imageView2 = new ImageView(this.f7950g);
            imageView2.setLayoutParams(layoutParams2);
            imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView2.setImageBitmap(this.s);
            relativeLayout.addView(imageView2);
        } else {
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(r0(290), r0(54)));
            imageView.setImageDrawable(ContextCompat.getDrawable(this.f7950g, R.drawable.share_qr_logo_long));
        }
        if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().slogan)) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, r0(42));
            layoutParams3.addRule(11, 1);
            layoutParams3.topMargin = r0(12);
            TextView textView2 = new TextView(this.f7950g);
            textView2.setLayoutParams(layoutParams3);
            textView2.setSingleLine(true);
            textView2.setMaxWidth(r0(200));
            textView2.setText(this.f7954k.getShareImageInfo().slogan);
            textView2.setTextSize(0, r0(28));
            textView2.setTextColor(-16777216);
            textView2.setGravity(16);
            textView2.setBackgroundResource(R.drawable.share_qr_slogan_bg);
            textView2.setPadding(r0(23), 0, r0(10), 0);
            relativeLayout.addView(textView2);
        }
        ImageView imageView3 = new ImageView(this.f7950g);
        imageView3.setLayoutParams(new LinearLayout.LayoutParams(DPIUtil.getWidth(), (DPIUtil.getWidth() * this.t.getHeight()) / this.t.getWidth()));
        imageView3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView3.setImageBitmap(this.t);
        linearLayout.addView(imageView3);
        if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().productTitle)) {
            TextView textView3 = new TextView(this.f7950g);
            textView3.setLayoutParams(new LinearLayout.LayoutParams(DPIUtil.getWidth(), -2));
            textView3.setText(this.f7954k.getShareImageInfo().productTitle);
            textView3.setPadding(r0(40), r0(20), r0(40), 0);
            textView3.setTextSize(0, r0(32));
            textView3.setTextColor(-16777216);
            textView3.setLineSpacing(0.0f, 1.3f);
            textView3.setMaxLines(5);
            textView3.setEllipsize(TextUtils.TruncateAt.END);
            linearLayout.addView(textView3);
        }
        if (!TextUtils.isEmpty(this.f7954k.getShareImageInfo().productDesc)) {
            TextView textView4 = new TextView(this.f7950g);
            textView4.setLayoutParams(new LinearLayout.LayoutParams(DPIUtil.getWidth(), -2));
            textView4.setText(this.f7954k.getShareImageInfo().productDesc);
            textView4.setPadding(r0(40), r0(20), r0(40), 0);
            textView4.setTextSize(0, r0(30));
            textView4.setTextColor(-6316129);
            textView4.setLineSpacing(0.0f, 1.3f);
            textView4.setMaxLines(10);
            textView4.setEllipsize(TextUtils.TruncateAt.END);
            linearLayout.addView(textView4);
        }
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(r0(256), r0(256));
        layoutParams4.topMargin = r0(12);
        layoutParams4.bottomMargin = r0(6);
        layoutParams4.gravity = 17;
        ImageView imageView4 = new ImageView(this.f7950g);
        imageView4.setLayoutParams(layoutParams4);
        imageView4.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView4.setImageBitmap(ShareUtil.createQRCode(str));
        linearLayout.addView(imageView4);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams5.gravity = 17;
        TextView textView5 = new TextView(this.f7950g);
        textView5.setLayoutParams(layoutParams5);
        textView5.setText(getString(R.string.share_look_detail));
        textView5.setPadding(0, r0(5), 0, 0);
        textView5.setGravity(17);
        textView5.setTextSize(0, r0(26));
        textView5.setTextColor(-6316129);
        linearLayout.addView(textView5);
        TextView textView6 = new TextView(this.f7950g);
        textView6.setLayoutParams(layoutParams5);
        textView6.setText(getString(R.string.share_download_app));
        textView6.setPadding(0, r0(5), 0, r0(50));
        textView6.setGravity(17);
        textView6.setTextSize(0, r0(26));
        textView6.setTextColor(-6316129);
        linearLayout.addView(textView6);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View x0(String str) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(DPIUtil.getWidth(), -2));
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(17);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(DPIUtil.getWidth(), (DPIUtil.getWidth() * this.t.getHeight()) / this.t.getWidth()));
        ImageView imageView = new ImageView(this);
        imageView.setId(R.id.share_x_activity_img);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageBitmap(this.t);
        relativeLayout.addView(imageView, layoutParams);
        int r0 = r0(260);
        int r02 = r0(4);
        int r03 = r0(98);
        ImageView imageView2 = new ImageView(this.f7950g);
        imageView2.setId(R.id.share_x_qr_code_img);
        int i2 = r0 + r02;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i2, i2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(12);
        imageView2.setPadding(r02, r02, r02, r02);
        imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView2.setBackgroundColor(-1);
        imageView2.setImageBitmap(ShareUtil.createQRCode(str, r0));
        layoutParams2.setMargins(0, 0, 0, r03);
        relativeLayout.addView(imageView2, layoutParams2);
        linearLayout.addView(relativeLayout);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0() {
        if (this.p + Final.HALF_MINUTE < System.currentTimeMillis() || !this.D.getAndSet(false)) {
            return;
        }
        OKLog.d("ShareActivity", "function: dealResult, selectedChannel: " + this.f7957n + " ,SharedResult: " + this.f7955l.a);
        if (this.f7953j.q != null) {
            s0();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("selectedChannel", this.f7957n);
        intent.putExtra("sharedChannel", this.f7955l.d);
        intent.putExtra("sharedMsg", this.f7955l.b);
        setResult(this.f7955l.a, intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean z0() {
        boolean z2;
        com.jingdong.c.a.c.f fVar = this.f7953j;
        List<com.jingdong.c.a.c.b> list = fVar.f12276c.a;
        int i2 = fVar.a;
        boolean z3 = false;
        if (i2 == 1) {
            z2 = list.size() > 0;
            if (z2) {
                l1();
            }
        } else if (i2 != 2) {
            if (i2 == 4) {
                z2 = list.size() > 0;
                if (z2) {
                    o1();
                }
            }
            if (!z3) {
                ToastUtils.shortToast(this, R.string.share_setting_none);
                finish();
            }
            return z3;
        } else {
            z2 = list.size() == 1;
            if (z2) {
                com.jingdong.c.a.c.b bVar = list.get(0);
                this.f7957n = bVar.a;
                com.jingdong.sdk.jdshare.utils.g.m("Share_SendDirect", this.f7954k.getUrl(), this.f7957n, this.f7953j.d, this.f7954k.getShareSource());
                if (bVar.f12266c) {
                    F0(bVar);
                } else if (this.f7954k.getShareImageInfo() != null && !"QRCode".equalsIgnoreCase(this.f7957n)) {
                    H0();
                } else {
                    F0(bVar);
                }
            }
        }
        z3 = z2;
        if (!z3) {
        }
        return z3;
    }

    @Override // com.jingdong.c.a.a
    public void a(int i2, String str, String str2) {
        OKLog.d("ShareActivity", "setSharedResult: result: " + i2);
        com.jingdong.c.a.c.g gVar = this.f7955l;
        gVar.a = i2;
        gVar.b = str2;
        gVar.a(str);
        f1();
        if (TextUtils.equals(this.f7957n, "Wxfriends") || TextUtils.equals(this.f7957n, "Wxmoments")) {
            return;
        }
        if (i2 == 11) {
            ToastUtils.shortToast(this, R.string.share_success);
        } else if (i2 == 13) {
            ToastUtils.shortToast(this, R.string.share_cancel);
        } else if (i2 == 12) {
            ToastUtils.shortToast(this, R.string.share_failed);
        }
    }

    @Override // com.jingdong.c.a.a
    public void d(int i2, String str, boolean z2) {
        com.jingdong.c.a.c.g gVar = this.f7955l;
        gVar.a = i2;
        gVar.d = str;
        if (z2) {
            y0();
            finish();
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        if (this.o + 250 > System.currentTimeMillis()) {
            return;
        }
        this.o = System.currentTimeMillis();
        if (this.f7952i == null && closePanelAnimation(true)) {
            return;
        }
        com.jingdong.c.a.c.g gVar = this.f7955l;
        if (gVar != null && gVar.a == 0) {
            setResult(15, new Intent());
        }
        OKLog.d("ShareActivity", "finish: ");
        super.finish();
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent != null) {
            OKLog.d("ShareActivity", "onActivityResult: " + intent.toString());
        }
        if (com.jingdong.sdk.jdshare.utils.e.c() != null && (TextUtils.equals(this.f7957n, "QQfriends") || TextUtils.equals(this.f7957n, "QQzone"))) {
            com.jingdong.sdk.jdshare.utils.e.d(i2, i3, intent, this.B);
        }
        WeiboUtils.doActivityResultIntent(intent, this);
    }

    @Override // com.sina.weibo.sdk.share.WbShareCallback
    public void onCancel() {
    }

    @Override // com.sina.weibo.sdk.share.WbShareCallback
    public void onComplete() {
        a(11, "", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String config;
        String config2;
        String config3;
        this.needSetOrientation = false;
        super.onCreate(bundle);
        if (!JDPrivacyHelper.isAcceptPrivacy(this) && !ShareValues.isDev) {
            finish();
            return;
        }
        this.statusBarTransparentEnable = true;
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
        this.f7950g = this;
        if (u0()) {
            com.jingdong.c.a.c.f c2 = com.jingdong.sdk.jdshare.utils.d.c(getIntent());
            this.f7953j = c2;
            this.f7955l = c2.p;
            this.f7956m = c2.o;
            if (t0()) {
                Boolean valueOf = Boolean.valueOf(ShareUtil.isUseSwitchQuery());
                if (valueOf.booleanValue()) {
                    config = SwitchQueryFetcher.getSwitchStringValue("fullScreenModeScaleValue", "");
                    config2 = SwitchQueryFetcher.getSwitchStringValue("fullScreenModeCheckValue", "");
                } else {
                    config = JDMobileConfig.getInstance().getConfig("JDShare", "fullScreenMode", "fullScreenModeScaleValue");
                    config2 = JDMobileConfig.getInstance().getConfig("JDShare", "fullScreenMode", "fullScreenModeCheckValue");
                }
                ShareValues.setFullScreenModeScaleValue(!TextUtils.isEmpty(config) ? Float.parseFloat(config) : 1.0f);
                ShareValues.setFullScreenModeCheckValue(!TextUtils.isEmpty(config2) ? Integer.parseInt(config2) : 10000);
                ShareValues.setIsFullScreenPhone1700(DpiUtil.getAppWidth(this) > ShareValues.getFullScreenModeCheckValue());
                ShareValues.newAddUrlQuerySwitch = SwitchQueryFetcher.getSwitchBooleanValue("newAddUrlQuerySwitch", false);
                String str = "isUseSwitchQuery:" + valueOf + " fullScreenModeScaleValue: " + config;
                ShareUtil.init(this);
                HWShareHelper hWShareHelper = new HWShareHelper(this);
                this.F = hWShareHelper;
                hWShareHelper.shareKitInit();
                O0();
                com.jingdong.sdk.jdshare.utils.a.f(this.f7953j);
                com.jingdong.sdk.jdshare.utils.a.e();
                ShareInfo shareInfo = this.f7953j.b;
                com.jingdong.sdk.jdshare.utils.a.g(this, shareInfo);
                this.f7954k = shareInfo;
                if (TextUtils.isEmpty(shareInfo.getIconUrl()) || TextUtils.equals(this.f7954k.getIconUrl(), DYConstants.DY_NULL_STR)) {
                    if (valueOf.booleanValue()) {
                        config3 = SwitchQueryFetcher.getSwitchStringValue(MBaseKeyNames.SHARE_ICONURL, "");
                    } else {
                        config3 = JDMobileConfig.getInstance().getConfig("JDShare", MBaseKeyNames.SHARE_ICONURL, MBaseKeyNames.SHARE_ICONURL);
                    }
                    if (!TextUtils.isEmpty(config3) && !TextUtils.equals(config3, DYConstants.DY_NULL_STR)) {
                        this.f7954k.setIconUrl(config3);
                    } else {
                        this.f7954k.setIconUrl(ShareValues.DEFAULT_ICON_URL);
                    }
                }
                com.jingdong.sdk.jdshare.utils.d.a(this, this.f7953j);
                JDSharedCommandUtils.getInstance().setShareInfo(this.f7954k);
                if (z0()) {
                    UnStatusBarTintUtil.setStatusBarLightMode(this);
                    this.H = SwitchQueryFetcher.getSwitchBooleanValue("shareNativeToast", false);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        HWShareHelper.release();
        super.onDestroy();
        ShareValues.reset();
    }

    @Override // com.sina.weibo.sdk.share.WbShareCallback
    public void onError(UiError uiError) {
        a(12, "", uiError.errorMessage);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null) {
            OKLog.d("ShareActivity", "onNewIntent: " + intent.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        int i2;
        super.onResume();
        IXView iXView = this.f7952i;
        if (iXView != null) {
            iXView.onResume();
        }
        if (this.y) {
            return;
        }
        com.jingdong.c.a.c.g gVar = this.f7955l;
        if (gVar != null && (i2 = gVar.a) != 0) {
            if (i2 == 11 && !TextUtils.isEmpty(this.f7954k.getCpsUrl())) {
                a1();
            } else {
                y0();
                finish();
                return;
            }
        }
        RelativeLayout relativeLayout = this.f7951h;
        if (relativeLayout != null) {
            if (!(relativeLayout.getChildCount() == 0 && this.u) && this.f7951h.getChildCount() == 0) {
                if (TextUtils.isEmpty(this.f7957n) || System.currentTimeMillis() > this.p + 1000) {
                    finish();
                }
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        IXView iXView = this.f7952i;
        if (iXView != null) {
            iXView.onStop();
        }
    }

    public void q0() {
        com.jingdong.sdk.jdshare.utils.a.b(this, this.f7953j);
    }
}
