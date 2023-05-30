package com.jingdong.common.web.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import cn.com.union.fido.common.MIMEType;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jd.libs.hybrid.HybridOfflineLoader;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.BaseGraySwitch;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.datasnapshot.DataSnapshotSDK;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.jsimpl.HybridInlineJsInterface;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.preload.jsimp.HybridJSInterface;
import com.jd.libs.hybrid.requestpreload.jsbridge.PreloadJSBridge;
import com.jd.libs.hybrid.xbehavior.XBehaviorManager;
import com.jd.libs.hybrid.xbehavior.events.PageLifeCycleEvent;
import com.jd.libs.hybrid.xbehavior.events.RequestEvent;
import com.jd.libs.xconsole.XLog;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.ui.LottieLoadingView;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigController;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.web.BaseWebChromeClient;
import com.jingdong.common.web.BaseWebViewClient;
import com.jingdong.common.web.JDWebViewBlackUrlDialog;
import com.jingdong.common.web.R;
import com.jingdong.common.web.WebDebug;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.WebViewPool;
import com.jingdong.common.web.javainterface.impl.Performance;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebPerformance;
import com.jingdong.common.web.managers.WebPerformanceHolder;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.CloseButtonListener;
import com.jingdong.common.web.uilistener.IWebViewUrlInterceptor;
import com.jingdong.common.web.uilistener.PerformanceCallback;
import com.jingdong.common.web.uilistener.TitleBackListener;
import com.jingdong.common.web.uilistener.TitleChangeListener;
import com.jingdong.common.web.uilistener.TitleRightTextViewClickListener;
import com.jingdong.common.web.uilistener.WebBackOrCloseListener;
import com.jingdong.common.web.uilistener.WebViewClientListener;
import com.jingdong.common.web.uilistener.WebViewNaviListener;
import com.jingdong.common.web.uilistener.WebViewScrollListener;
import com.jingdong.common.web.uilistener.impl.WebViewUrlInterceptorImpl;
import com.jingdong.common.web.urlcheck.impl.InterceptRequestImpl;
import com.jingdong.common.web.util.CookieUtil;
import com.jingdong.common.web.util.HybridBackdoorLogger;
import com.jingdong.common.web.util.JDAppearanceHelper;
import com.jingdong.common.web.util.JDWebViewInitRecord;
import com.jingdong.common.web.util.JDWebviewSslErrorDialogController;
import com.jingdong.common.web.util.WebLogHelper;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.common.web.xrender.XRenderManager;
import com.jingdong.common.web.xrender.XRenderWebUiBinder;
import com.jingdong.common.widget.CommonNavigator;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.common.widget.custom.CustomChannelFollowView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.remoteimage.CalorieImageUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.perfmonitor.PerfMonitor;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidget;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClientFactory;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebBackForwardList;
import com.tencent.smtt.sdk.WebHistoryItem;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
public class JDWebView extends FrameLayout implements LifecycleOwner {
    public static final int MAX_PROGRESS = 10000;
    public static final int MAX_SCROLL_HEIGHT_ALPHA = NavigatorHolder.NAVI_BAR_HEIGHT;
    private String TAG;
    private JDAppearanceHelper appearanceHelper;
    private boolean autoSendReadyEvent;
    private boolean backToLocalHtml;
    private Runnable blackRedirectRunnable;
    private double blankThreshold;
    private boolean canUseDarkMode;
    public View circleProgress;
    private CloseButtonListener closeButtonListener;
    public boolean closeWhenNative;
    private float currentProgress;
    private long currentTimeInitStart;
    public DecimalFormat df;
    private String dogId;
    private boolean enableHybrid;
    private boolean enableWebLog;
    private long endTime;
    private XWebErrView errView;
    private volatile ConcurrentHashMap<String, Object> extraSetting;
    private String finalUrl;
    private boolean fixLifeCall;
    private NavigatorHolder.BaseNaviFollowAdapter followBtnAdapter;
    private GenTokenCallback genTokenCallback;
    private boolean hasLongClick;
    public boolean hasOnceShowPage;
    public boolean hasOnceShowPageStarting;
    private boolean hybridStarted;
    private boolean ignoreShouldOverrideUrl;
    private JDWebViewInitRecord initRecord;
    private boolean initRecordReported;
    private IWebViewUrlInterceptor internalInterceptUrlListener;
    private boolean isAttached;
    private boolean isError;
    private boolean isFirstH5Page;
    private volatile boolean isFirstWhiteRenderRatio;
    private boolean isForegroundOrVisible;
    private boolean isHideProgress;
    private boolean isLocalHtmlIntercepted;
    private boolean isLocalHtmlScenario;
    private boolean isMainFrameActivity;
    protected boolean isNeedShare;
    private boolean isPageFinished;
    private boolean isPageLoaded;
    private boolean isPreRender;
    private boolean isTitleFixed;
    private boolean isTopBarGone;
    private boolean isUserCloseBtn;
    private Performance jsPerformance;
    private boolean lazyHybrid;
    private LifecycleRegistry lifecycleRegistry;
    private long loadEnd;
    private long loadStart;
    private String loadingPlaceHolder;
    private String localHtmlData;
    private InputStream localHtmlStream;
    private Context mContext;
    private Handler mHandler;
    private long[] mHits;
    private InterceptTouchEventListener mInterceptTouchEventListener;
    View.OnClickListener mListener;
    private Runnable mRunnable;
    View.OnTouchListener mTouchListener;
    private BaseWebChromeClient mWebChromeClient;
    private WebHybridLogView mWebHybridLogView;
    private WebLogHelper mWebLogHelper;
    private WebLogView mWebLogView;
    private String monitorCookie;
    private NavigatorHolder.NaviListener naviListener;
    private NavigatorHolder navigatorHolder;
    private boolean needShowProgress;
    private String offlineId;
    private boolean onCommitHideProgress;
    private TitleRightTextViewClickListener onRightTextViewClickListener;
    private String overrideUrl;
    private float perWidth;
    private WebPerformanceHolder perfRecordHolder;
    private PerformanceCallback performanceCallback;
    private Runnable placeHolderRunnable;
    private int preScrollState;
    private String preloadId;
    protected ImageView progressImage;
    protected RelativeLayout progressRelativeLayout;
    private Runnable progressRunnable;
    protected PullToRefreshX5WebView ptrWebView;
    protected RelativeLayout rootLayout;
    private boolean shallCheckImmersiveOnInit;
    private boolean shouldReportInitRecord;
    private boolean showErrView;
    private long startTime;
    private boolean switchImmersiveOpen;
    private TitleBackListener titleBackListener;
    private TitleChangeListener titleChangeListener;
    protected ViewGroup titleLayout;
    private String url;
    private LinkedList<String> urlHistory;
    private boolean useXBridge;
    private WebBackOrCloseListener webBackOrCloseListener;
    protected X5WebView webView;
    private WebViewClientListener webViewClientListener;
    private IWebViewUrlInterceptor webViewInterceptUrlListener;
    private WebViewNaviListener webViewNaviListener;
    protected RelativeLayout webview_layout;
    private boolean whiteScreenEnable;
    private WebWhiteScreenHolder whiteScreenHolder;
    private boolean xDogRegistered;
    private XRenderManager xRenderManager;
    private String xRenderUrl;
    private boolean xWidgetAvailable;

    /* loaded from: classes12.dex */
    public interface InterceptTouchEventListener {
        boolean onInterceptTouchEvent(MotionEvent motionEvent);
    }

    public JDWebView(Context context) {
        super(context);
        this.TAG = "JDWebView";
        this.currentTimeInitStart = System.currentTimeMillis();
        this.shouldReportInitRecord = true;
        this.initRecordReported = false;
        this.isMainFrameActivity = false;
        this.isTitleFixed = false;
        this.needShowProgress = true;
        this.isPageLoaded = false;
        this.enableWebLog = WebLogHelper.sJsReportLevel >= 0;
        this.isUserCloseBtn = true;
        this.perWidth = DPIUtil.getWidth() / 10000.0f;
        this.closeWhenNative = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isTopBarGone = false;
        this.switchImmersiveOpen = true;
        this.isForegroundOrVisible = false;
        this.isFirstH5Page = true;
        this.preScrollState = -1;
        this.canUseDarkMode = false;
        this.enableHybrid = false;
        this.hybridStarted = false;
        this.onCommitHideProgress = false;
        this.fixLifeCall = true;
        this.useXBridge = false;
        this.loadingPlaceHolder = null;
        this.showErrView = false;
        this.xDogRegistered = false;
        this.xWidgetAvailable = false;
        this.df = new DecimalFormat("#.###");
        this.whiteScreenEnable = false;
        this.blankThreshold = 0.95d;
        this.isFirstWhiteRenderRatio = false;
        this.monitorCookie = null;
        this.isError = false;
        this.backToLocalHtml = false;
        this.ignoreShouldOverrideUrl = false;
        this.shallCheckImmersiveOnInit = true;
        this.lazyHybrid = false;
        this.endTime = 0L;
        this.startTime = 0L;
        this.hasLongClick = false;
        this.mHits = new long[7];
        this.mTouchListener = new View.OnTouchListener() { // from class: com.jingdong.common.web.ui.JDWebView.13
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    JDWebView.this.endTime = 0L;
                    JDWebView.this.startTime = SystemClock.uptimeMillis();
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    JDWebView.this.endTime = SystemClock.uptimeMillis();
                    if (JDWebView.this.endTime - JDWebView.this.startTime >= Final.FIVE_SECOND) {
                        JDWebView.this.hasLongClick = true;
                        return false;
                    }
                    return false;
                }
            }
        };
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.web.ui.JDWebView.14
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDWebView.this.hasLongClick) {
                    System.arraycopy(JDWebView.this.mHits, 1, JDWebView.this.mHits, 0, JDWebView.this.mHits.length - 1);
                    JDWebView.this.mHits[JDWebView.this.mHits.length - 1] = SystemClock.uptimeMillis();
                    if (JDWebView.this.mHits[JDWebView.this.mHits.length - 2] != 0 && JDWebView.this.mHits[JDWebView.this.mHits.length - 1] - JDWebView.this.mHits[JDWebView.this.mHits.length - 2] > 500) {
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                    }
                }
                if (JDWebView.this.hasLongClick) {
                    if (JDWebView.this.mHits[0] != 0) {
                        if (JDWebView.this.navigatorHolder != null) {
                            JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(false);
                            JDWebView.this.navigatorHolder.getTitleImg().setEnabled(false);
                        }
                        JDWebView.this.addView(new SecretDoor(JDWebView.this.mContext, JDWebView.this), new ViewGroup.LayoutParams(-1, -1));
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                        JDWebView.this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.14.1
                            {
                                AnonymousClass14.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (JDWebView.this.navigatorHolder != null) {
                                    JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(true);
                                    JDWebView.this.navigatorHolder.getTitleImg().setEnabled(true);
                                }
                            }
                        }, 1000L);
                    }
                } else if (JDWebView.this.preScrollState <= 0) {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked()");
                } else {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked(" + JDWebView.this.preScrollState + ")");
                }
            }
        };
        this.naviListener = new CommonNavigator.NaviClickAdaper() { // from class: com.jingdong.common.web.ui.JDWebView.15
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCalendar() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCalendar();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCart() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickClose() {
                if (JDWebView.this.closeButtonListener != null) {
                    JDWebView.this.closeButtonListener.close();
                } else {
                    JDWebView.this.stopLoading();
                    if (JDWebView.this.webBackOrCloseListener != null) {
                        JDWebView.this.webBackOrCloseListener.onWebClose();
                    }
                    ((Activity) JDWebView.this.mContext).finish();
                }
                JDMtaUtils.onClick(JDWebView.this.getContext(), "MWebview_CloseButton", JDWebView.this.getContext().getClass().getName(), "", JDWebView.this.finalUrl);
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCustom(String str) {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickHome() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMore() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMore();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMsg() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCart() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCustom(String str) {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopFeedback() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopFeedback();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopHome() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopMsg() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopSearch() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopShare() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickSearch() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickShare() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickTitleBack() {
                JDMtaUtils.onClick(JDWebView.this.mContext, "MWebview_BackForward", "", JDWebView.this.finalUrl);
                if (JDWebView.this.titleBackListener != null) {
                    JDWebView.this.titleBackListener.titleBack();
                } else {
                    JDWebView.this.backOrClose();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onRightTextView() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.onRightTextViewClickListener != null) {
                    JDWebView.this.onRightTextViewClickListener.onRightTextViewClickListener(null);
                }
            }
        };
        this.followBtnAdapter = new NavigatorHolder.BaseNaviFollowAdapter() { // from class: com.jingdong.common.web.ui.JDWebView.16
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onChannelIdChanged(@Nullable View view, String str) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).setChannelId(str, getStyle() != 2);
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            @Nullable
            public View onCreateFollowView(int i2, int i3, int i4) {
                if (JDWebView.this.isTopBarGone()) {
                    return null;
                }
                CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(JDWebView.this.mContext);
                customChannelFollowView.resetFollowViewWidthAndHeight(i2, i3);
                customChannelFollowView.changeIcon(i4 != 2);
                return customChannelFollowView;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onStyleChanged(View view, int i2) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).changeIcon(i2 != 2);
            }
        };
        init(context);
    }

    /* renamed from: a */
    public /* synthetic */ void b(int i2) {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder != null) {
            webPerformanceHolder.appendDataToCurrRecord("preloadStatus", String.valueOf(i2));
            if (i2 == 200) {
                com.jd.libs.xdog.b.j(this.dogId, "text", "\u63a5\u53e3\u9884\u52a0\u8f7d", "yes");
            }
        }
        appendWhiteScreenData("preloadStatus", String.valueOf(i2));
    }

    static /* synthetic */ float access$516(JDWebView jDWebView, float f2) {
        float f3 = jDWebView.currentProgress + f2;
        jDWebView.currentProgress = f3;
        return f3;
    }

    static /* synthetic */ float access$518(JDWebView jDWebView, double d) {
        double d2 = jDWebView.currentProgress;
        Double.isNaN(d2);
        float f2 = (float) (d2 + d);
        jDWebView.currentProgress = f2;
        return f2;
    }

    public void addUrlToHistory(String str) {
        if (this.urlHistory == null) {
            this.urlHistory = new LinkedList<>();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.urlHistory.size() <= 0 || !str.equals(this.urlHistory.getLast())) {
            if (this.urlHistory.size() >= 10) {
                this.urlHistory.removeFirst();
            }
            this.urlHistory.addLast(str);
        }
    }

    public void autoHidePullState(long j2) {
        Runnable runnable = this.mRunnable;
        if (runnable == null) {
            this.mRunnable = new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.17
                {
                    JDWebView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    JDWebView.this.onRefreshComplete();
                }
            };
        } else {
            PullToRefreshX5WebView pullToRefreshX5WebView = this.ptrWebView;
            if (pullToRefreshX5WebView != null) {
                pullToRefreshX5WebView.removeCallbacks(runnable);
            }
        }
        PullToRefreshX5WebView pullToRefreshX5WebView2 = this.ptrWebView;
        if (pullToRefreshX5WebView2 != null) {
            pullToRefreshX5WebView2.postDelayed(this.mRunnable, j2);
        }
    }

    /* renamed from: c */
    public /* synthetic */ void d(HybridJSInterface hybridJSInterface) {
        addJavascriptInterface(hybridJSInterface, WebUiConstans.JavaInterfaceNames.JDHYBRID);
    }

    public boolean checkIsClose(Uri uri) {
        WebBackOrCloseListener webBackOrCloseListener;
        if (uri != null) {
            boolean isCloseUri = WebUtils.isCloseUri(this.mContext, uri);
            if (isCloseUri && (webBackOrCloseListener = this.webBackOrCloseListener) != null) {
                webBackOrCloseListener.onWebClose();
            }
            return isCloseUri;
        }
        return false;
    }

    public void clearMHits() {
        int i2 = 0;
        while (true) {
            long[] jArr = this.mHits;
            if (i2 >= jArr.length) {
                return;
            }
            jArr[i2] = 0;
            i2++;
        }
    }

    public void destroyLocalHtml() {
        InputStream inputStream = this.localHtmlStream;
        if (inputStream != null) {
            try {
                inputStream.close();
                this.localHtmlStream = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.localHtmlData = null;
    }

    /* renamed from: e */
    public /* synthetic */ void f(int i2) {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder != null) {
            webPerformanceHolder.appendDataToCurrRecord("preloadStatus", String.valueOf(i2));
            if (i2 == 200) {
                com.jd.libs.xdog.b.j(this.dogId, "text", "\u63a5\u53e3\u9884\u52a0\u8f7d", "yes");
            }
        }
        appendWhiteScreenData("preloadStatus", String.valueOf(i2));
    }

    /* renamed from: g */
    public /* synthetic */ void h(PreloadJSBridge preloadJSBridge) {
        addJavascriptInterface(preloadJSBridge, PreloadJSBridge.JS_OBJ_NAME);
    }

    public void hideErrView() {
        XWebErrView xWebErrView = this.errView;
        if (xWebErrView == null || xWebErrView.getParent() == null) {
            return;
        }
        ((ViewGroup) this.errView.getParent()).removeView(this.errView);
    }

    public void hideProgress() {
        View view = this.circleProgress;
        if (view != null && view.getVisibility() == 0 && TextUtils.isEmpty(this.loadingPlaceHolder)) {
            this.circleProgress.setVisibility(8);
        }
    }

    public void hideSoftInput() {
        InputMethodManager inputMethodManager;
        Context context = this.mContext;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return;
        }
        try {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        } catch (Exception e2) {
            Log.e(this.TAG, e2.getMessage(), e2);
        }
    }

    private void hybridLoad() {
        if (!this.enableHybrid || this.hybridStarted || this.isPreRender) {
            return;
        }
        if (TextUtils.isEmpty(this.offlineId) && !TextUtils.isEmpty(this.url)) {
            Log.d("JDHybrid", "fetch hybrid offlineLoader in JDWebView");
            String createOfflineLoader = WebOfflineLoaderManager.createOfflineLoader(this.url);
            this.offlineId = createOfflineLoader;
            if (!TextUtils.isEmpty(createOfflineLoader)) {
                this.hybridStarted = true;
            }
        }
        if (!TextUtils.isEmpty(this.preloadId) || TextUtils.isEmpty(this.url)) {
            return;
        }
        Log.d("JDHybrid", "preload hybrid data in JDWebView");
        String preLoad = WebPreLoadHelper.preLoad(this.url);
        this.preloadId = preLoad;
        if (TextUtils.isEmpty(preLoad)) {
            return;
        }
        this.hybridStarted = true;
    }

    /* renamed from: i */
    public /* synthetic */ void j(String str, String str2) {
        if ("0".equals(str2)) {
            loadUrl(str);
        }
    }

    private void init(Context context) {
        String str;
        String str2;
        String str3;
        Pair<Boolean, Integer> hasPreRenderData;
        this.mContext = context;
        markInitPhaseEnd(UnIconConfigController.A_B_SWITCH_A);
        WebPerformanceHolder webPerformanceHolder = new WebPerformanceHolder(this);
        this.perfRecordHolder = webPerformanceHolder;
        webPerformanceHolder.appendDataToCurrRecord("initStart", String.valueOf(this.currentTimeInitStart));
        this.perfRecordHolder.appendDataToCurrRecord("lastPage", HybridSDK.getLastPageName());
        initWhiteScreen();
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED);
        hybridLoad();
        markInitPhaseEnd("hybridLoad");
        XBehaviorManager.getInstance().dispatchEvent(new RequestEvent(this.url, RequestEvent.TYPE_H5_PAGE));
        DataSnapshotSDK.INSTANCE.getInstance().onCreate();
        boolean z = false;
        try {
            WebUtils.fix64();
            X5InitUtil.preloadX5(context);
            markInitPhaseEnd("loadX5");
            if (this.isPreRender && !SwitchQueryFetcher.getSwitchBooleanValue("wvDisableInflateFix", false) && (context instanceof MutableContextWrapper) && (((MutableContextWrapper) context).getBaseContext() instanceof Application)) {
                LayoutInflater.from(context).inflate(getInflateLayoutRes(), (ViewGroup) this, true);
            } else {
                ImageUtil.inflate(getInflateLayoutRes(), (ViewGroup) this, true);
            }
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(this.TAG, e2.getMessage(), e2);
            }
            try {
                str = ProcessUtil.getProcessName(context);
            } catch (Exception e3) {
                if (Log.E) {
                    Log.e(this.TAG, e3.getMessage(), e3);
                }
                str = null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(e2.getMessage());
            if (context == null) {
                str2 = "";
            } else {
                str2 = " | Page: " + context.getClass().getSimpleName();
            }
            sb.append(str2);
            if (TextUtils.isEmpty(str)) {
                str3 = "";
            } else {
                str3 = " | Process: " + str;
            }
            sb.append(str3);
            ExceptionReporter.reportWebViewCommonError("WebView_XmlInflateError", "", sb.toString(), ExceptionReporter.getStackStringFromException(e2));
        }
        markInitPhaseEnd("inflateView");
        initView();
        onPostInit();
        markInitPhaseEnd("postInit");
        WebUnifiedMtaUtil.sendCoreMta(this, this.mContext);
        PerformanceManager.getInstance().init();
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED);
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            this.perfRecordHolder.appendExtraToCurrRecord("cachedWebView", String.valueOf(x5WebView.isCachedWebView()));
        }
        this.perfRecordHolder.appendDataToCurrRecord(WebPerfManager.INIT_FINISH, String.valueOf(System.currentTimeMillis()));
        appendInitPerformanceData(null);
        if (this.shouldReportInitRecord) {
            reportInitPerformance(this.webView != null, null);
        }
        if (Build.VERSION.SDK_INT >= 23 && ((isSystemCoreNotX5() || QbSdk.getTbsVersion(context) > 45745) && SwitchQueryFetcher.getSwitchBooleanValue("h_hide_p", false))) {
            z = true;
        }
        this.onCommitHideProgress = z;
        if (TextUtils.isEmpty(this.url) || (hasPreRenderData = XRender.getInstance().hasPreRenderData(this.url)) == null) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(((Boolean) hasPreRenderData.first).booleanValue() ? "1" : "0");
        sb2.append("-");
        sb2.append(XRender.getInstance().getLastPreRenderDataType());
        appendPerformanceData(WebPerfManager.HAS_XRENDER_CFG, sb2.toString());
    }

    public void initHybridFunctions() {
        if (this.enableHybrid) {
            if (!TextUtils.isEmpty(this.offlineId)) {
                initOfflineLoad(this.offlineId);
                this.hybridStarted = true;
            }
            initPreloadFunction();
        }
    }

    private void initOfflineLoad(String str) {
        WebPerformanceHolder webPerformanceHolder;
        InterceptRequestImpl interceptRequestImpl = new InterceptRequestImpl(this, str);
        if (this.internalInterceptUrlListener == null) {
            this.internalInterceptUrlListener = new WebViewUrlInterceptorImpl();
        }
        this.internalInterceptUrlListener.setShouldInterceptRequest(interceptRequestImpl);
        this.internalInterceptUrlListener.addUrlCheckOnPageStart(interceptRequestImpl.checkOnStart);
        this.internalInterceptUrlListener.addUrlCheckOnPageFinish(interceptRequestImpl.checkOnFinish);
        addJavascriptInterface(new HybridInlineJsInterface(this.webView), WebUiConstans.JavaInterfaceNames.JDHYBRIDTEST);
        HybridOfflineLoader fetchOfflineLoader = WebOfflineLoaderManager.fetchOfflineLoader(str);
        OfflineFiles offlineFiles = fetchOfflineLoader != null ? fetchOfflineLoader.getOfflineFiles() : null;
        if (offlineFiles == null || (webPerformanceHolder = this.perfRecordHolder) == null) {
            return;
        }
        webPerformanceHolder.appendDataToCurrRecord(WebPerfManager.HTML_PRE_DOWNLOAD_START, String.valueOf(offlineFiles.getPreloadHtmlDownloadStart()));
    }

    private void initPreload(String str) {
        if (GraySwitch.oldPreloadOffline) {
            return;
        }
        final HybridJSInterface hybridJSInterface = new HybridJSInterface(this.webView, str);
        hybridJSInterface.setPreloadCallback(new HybridJSInterface.PreloadDataCallback() { // from class: com.jingdong.common.web.ui.i
            {
                JDWebView.this = this;
            }

            @Override // com.jd.libs.hybrid.preload.jsimp.HybridJSInterface.PreloadDataCallback
            public final void onWebFetchData(int i2) {
                JDWebView.this.b(i2);
            }
        });
        if (Looper.myLooper() == Looper.getMainLooper()) {
            addJavascriptInterface(hybridJSInterface, WebUiConstans.JavaInterfaceNames.JDHYBRID);
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jingdong.common.web.ui.k
                {
                    JDWebView.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDWebView.this.d(hybridJSInterface);
                }
            });
        }
    }

    private void initPreloadNew(String str) {
        if (Build.VERSION.SDK_INT <= 23 && this.isPreRender) {
            XRender.Log("6.0\u7cfb\u7edf\u4e0d\u4f7f\u7528\u63a5\u53e3\u9884\u52a0\u8f7d\uff0c\u6709bug");
            return;
        }
        final PreloadJSBridge preloadJSBridge = new PreloadJSBridge(this.webView, str);
        preloadJSBridge.setPreloadCallback(new PreloadJSBridge.PreloadDataCallback() { // from class: com.jingdong.common.web.ui.l
            {
                JDWebView.this = this;
            }

            @Override // com.jd.libs.hybrid.requestpreload.jsbridge.PreloadJSBridge.PreloadDataCallback
            public final void onWebFetchData(int i2) {
                JDWebView.this.f(i2);
            }
        });
        if (Looper.myLooper() == Looper.getMainLooper()) {
            addJavascriptInterface(preloadJSBridge, PreloadJSBridge.JS_OBJ_NAME);
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jingdong.common.web.ui.h
                {
                    JDWebView.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDWebView.this.h(preloadJSBridge);
                }
            });
        }
    }

    @SuppressLint({"NewApi"})
    private void initView() {
        inflateView();
        markInitPhaseEnd("getView");
        if (this.isPreRender) {
            XRenderManager xRenderManager = new XRenderManager();
            this.xRenderManager = xRenderManager;
            xRenderManager.init(this);
        }
        setCanPullRefresh(false);
        markInitPhaseEnd("setCanPull");
        X5WebView x5WebView = this.webView;
        if (x5WebView == null) {
            return;
        }
        appendWhiteScreenData(WebPerfManager.CORE_TYPE, x5WebView.getX5WebViewExtension() != null ? "x5" : "system");
        appendWhiteScreenData(WebPerfManager.CORE_VER, String.valueOf(WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication())));
        if (SwitchQueryFetcher.getSwitchBooleanValue("xWidgetAvailable", false) && this.webView.getX5WebViewExtension() != null && WebUtils.isX5CoreNumInSwitch(this.mContext, "XWidgetX5Version")) {
            SharedPreferences.Editor edit = this.mContext.getSharedPreferences("tbs_public_settings", 0).edit();
            edit.putInt("MTT_CORE_EMBEDDED_WIDGET_ENABLE", 1);
            edit.apply();
            boolean registerEmbeddedWidget = this.webView.getX5WebViewExtension().registerEmbeddedWidget(new String[]{"hybrid-video"}, new IEmbeddedWidgetClientFactory() { // from class: com.jingdong.common.web.ui.JDWebView.1
                {
                    JDWebView.this = this;
                }

                @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClientFactory
                public IEmbeddedWidgetClient createWidgetClient(String str, Map<String, String> map, IEmbeddedWidget iEmbeddedWidget) {
                    if (!"HYBRID-VIDEO".equals(str) || (map != null && map.containsKey("hybrid_xsl_id"))) {
                        JDWebView.this.xLogD("\u5f00\u542f\u540c\u5c42\u6e32\u67d3\u6210\u529f: s=" + str + "  map=" + map.toString());
                        try {
                            com.jd.libs.xwidget.b newInstance = com.jd.libs.xwidget.a.a(str.toLowerCase()).newInstance();
                            newInstance.getData(JDWebView.this.mContext, map);
                            newInstance.getIEmbeddedWidget(iEmbeddedWidget);
                            return newInstance;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                }
            });
            this.xWidgetAvailable = registerEmbeddedWidget;
            if (!registerEmbeddedWidget) {
                xLogD("\u5f00\u542f\u540c\u5c42\u6e32\u67d3\u5931\u8d25");
            }
        }
        if (this.canUseDarkMode) {
            this.appearanceHelper = new JDAppearanceHelper(this);
            markInitPhaseEnd("initDarkMode");
        }
        boolean isDarkMode = isDarkMode();
        WebViewHelper.initJdUaForDarkMode(this.webView, isDarkMode ? 1 : 0, false);
        markInitPhaseEnd("initUaForDark");
        String string = SharedPreferencesUtil.getString(MBaseKeyNames.KEY_PRELOAD_ORI_UA, "");
        if (TextUtils.isEmpty(string) && !string.equals(this.webView.getOrgUserAgent())) {
            SharedPreferencesUtil.putString(MBaseKeyNames.KEY_PRELOAD_ORI_UA, this.webView.getOrgUserAgent());
        }
        NavigatorHolder navigatorHolder = new NavigatorHolder(this.mContext, this, isDarkMode, isOfflineBiz());
        this.navigatorHolder = navigatorHolder;
        navigatorHolder.setNaviListener(this.naviListener);
        this.navigatorHolder.setNaviFollowAdapter(this.followBtnAdapter);
        markInitPhaseEnd("navi");
        String str = this.url;
        this.finalUrl = str;
        this.overrideUrl = str;
        PullToRefreshX5WebView pullToRefreshX5WebView = this.ptrWebView;
        if (pullToRefreshX5WebView != null) {
            pullToRefreshX5WebView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<X5WebView>() { // from class: com.jingdong.common.web.ui.JDWebView.2
                {
                    JDWebView.this = this;
                }

                @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
                public void onRefresh(PullToRefreshBase<X5WebView> pullToRefreshBase) {
                    JDWebView.this.showProgress();
                    JDWebView.this.reload();
                    JDWebView.this.autoHidePullState(10000L);
                    WebUnifiedMtaUtil.sendExposureMta(JDWebView.this.getContext(), "com.jingdong.common.web.ui.JDWebView", "", JDWebView.this.finalUrl, WebUnifiedMtaUtil.MWEBVIEW_PULLREFRESH, "");
                }
            });
        }
        PullToRefreshX5WebView pullToRefreshX5WebView2 = this.ptrWebView;
        if (pullToRefreshX5WebView2 != null) {
            pullToRefreshX5WebView2.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener<X5WebView>() { // from class: com.jingdong.common.web.ui.JDWebView.3
                {
                    JDWebView.this = this;
                }

                @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener
                public void onPullEvent(PullToRefreshBase<X5WebView> pullToRefreshBase, PullToRefreshBase.State state, PullToRefreshBase.Mode mode) {
                    ViewGroup viewGroup;
                    if (state != PullToRefreshBase.State.PULL_TO_REFRESH && state != PullToRefreshBase.State.MANUAL_REFRESHING) {
                        if (state != PullToRefreshBase.State.RESET || JDWebView.this.isTopBarGone || (viewGroup = JDWebView.this.titleLayout) == null || viewGroup.getVisibility() == 0) {
                            return;
                        }
                        JDWebView.this.titleLayout.setVisibility(0);
                        return;
                    }
                    JDWebView jDWebView = JDWebView.this;
                    if (jDWebView.titleLayout != null && jDWebView.isNaviImmersive() && JDWebView.this.titleLayout.getVisibility() == 0) {
                        JDWebView.this.titleLayout.setVisibility(4);
                    }
                }
            });
        }
        this.progressRunnable = new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.4
            {
                JDWebView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JDWebView jDWebView;
                ImageView imageView;
                if (JDWebView.this.isPageLoaded) {
                    if (JDWebView.this.currentProgress <= 9000.0d) {
                        JDWebView.access$516(JDWebView.this, 340.0f);
                    } else {
                        JDWebView.access$518(JDWebView.this, 28.333333333333336d);
                    }
                } else if (JDWebView.this.currentProgress >= 6000.0d) {
                    if (JDWebView.this.currentProgress < 6000.0d || JDWebView.this.currentProgress >= 8000.0d) {
                        if (JDWebView.this.currentProgress >= 8000.0d && JDWebView.this.currentProgress < 9000.0d) {
                            JDWebView.access$518(JDWebView.this, 17.0d);
                        }
                    } else {
                        JDWebView.access$518(JDWebView.this, 34.0d);
                    }
                } else {
                    JDWebView.access$518(JDWebView.this, 102.0d);
                }
                if (JDWebView.this.currentProgress >= 10000.0f) {
                    JDWebView.this.mHandler.removeCallbacks(JDWebView.this.progressRunnable);
                    ImageView imageView2 = JDWebView.this.progressImage;
                    if (imageView2 != null) {
                        imageView2.setVisibility(8);
                        JDWebView.this.progressImage.setAlpha(1.0f);
                    }
                    RelativeLayout relativeLayout = JDWebView.this.progressRelativeLayout;
                    if (relativeLayout != null) {
                        relativeLayout.setVisibility(8);
                    }
                    JDWebView.this.currentProgress = 0.0f;
                    JDWebView.this.setImageProgress(0);
                    return;
                }
                JDWebView jDWebView2 = JDWebView.this;
                jDWebView2.setImageProgress((int) jDWebView2.currentProgress);
                JDWebView.this.mHandler.postDelayed(JDWebView.this.progressRunnable, 17L);
                if (JDWebView.this.currentProgress < 9000.0d || (imageView = (jDWebView = JDWebView.this).progressImage) == null) {
                    return;
                }
                double d = jDWebView.currentProgress;
                Double.isNaN(d);
                imageView.setAlpha(1.0f - ((float) ((d - 9000.0d) / 1000.0d)));
            }
        };
        this.blackRedirectRunnable = new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.5
            {
                JDWebView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                JDWebView.this.xLogD("Url in black list, do not load it, redirect!");
                JDWebView.this.stopLoading();
                View view = JDWebView.this.circleProgress;
                if (view != null) {
                    view.setVisibility(8);
                }
                new JDWebViewBlackUrlDialog(JDWebView.this.mContext, JDWebView.this.url).show();
                ExceptionReporter.reportWebViewCommonError(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, "", "JDWebView black list error", "");
            }
        };
        this.webView.setWebViewClient(new BaseWebViewClient() { // from class: com.jingdong.common.web.ui.JDWebView.6
            {
                JDWebView.this = this;
            }

            private boolean combineShouldOverrideUrlLoading(WebView webView, String str2) {
                if (JDWebView.this.whiteScreenHolder != null) {
                    JDWebView.this.whiteScreenHolder.addOverrideUrl(str2);
                }
                if (!JDWebView.this.isLocalHtmlScenario || !WebUtils.isBlankUrl(str2)) {
                    if (JDWebView.this.isLocalHtmlScenario && JDWebView.this.ignoreShouldOverrideUrl) {
                        JDWebView.this.ignoreShouldOverrideUrl = false;
                        return false;
                    }
                    if (Log.D || WebLogHelper.showXLog) {
                        JDWebView.this.xLogD("JDWebView [shouldOverrideUrlLoading]  url: " + str2);
                    }
                    JDWebView.this.overrideUrl = str2;
                    Uri parse = !TextUtils.isEmpty(str2) ? Uri.parse(str2) : null;
                    System.currentTimeMillis();
                    JDWebView.this.addUrlToHistory(str2);
                    if (WebUtils.needReportUnknownScheme(parse)) {
                        WebUnifiedMtaUtil.sendUnknownSchemeMta(webView.getOriginalUrl(), JDWebView.this.getUrlHistory(), parse.getScheme(), parse.toString());
                    }
                    WebPerformance currentRecord = JDWebView.this.perfRecordHolder.getCurrentRecord();
                    if (WebUtils.isSchemeInBlackList(parse != null ? parse.getScheme() : null)) {
                        JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                        if (currentRecord != null) {
                            currentRecord.setInterrupted(null);
                        }
                        return true;
                    }
                    if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, false)) {
                        if (WebUtils.checkUrlInIllegalList(str2)) {
                            ExceptionReporter.reportWebViewCommonError(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, "", "JDWebView black list error", "");
                            return true;
                        } else if (WebUtils.checkUrlInBlackList(str2)) {
                            WebUnifiedMtaUtil.sendUrlInBlackListMta(str2, JDWebView.this.getUrlHistory(), "from url: " + webView.getOriginalUrl(), "JDWebView.combineShouldOverrideUrlLoading");
                            JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                            if (currentRecord != null) {
                                currentRecord.setInterrupted(null);
                            }
                            if (JDWebView.this.blackRedirectRunnable != null) {
                                JDWebView.this.blackRedirectRunnable.run();
                            }
                            return true;
                        }
                    }
                    WebUtils.setAcceptThirdCookie(webView, parse);
                    if (JDWebView.this.checkIsClose(parse)) {
                        JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                        if (currentRecord != null) {
                            currentRecord.setInterrupted(null);
                        }
                        return true;
                    } else if ((JDWebView.this.webViewInterceptUrlListener == null || !JDWebView.this.webViewInterceptUrlListener.interceptShouldOverrideLoading(webView, str2)) && (JDWebView.this.internalInterceptUrlListener == null || !JDWebView.this.internalInterceptUrlListener.interceptShouldOverrideLoading(webView, str2))) {
                        if (JDWebView.this.webViewClientListener != null) {
                            JDWebView.this.webViewClientListener.shouldOverrideUrlLoading(webView, str2);
                        }
                        JDWebView.this.showImageProgress();
                        return false;
                    } else {
                        JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                        if (currentRecord != null) {
                            currentRecord.setInterrupted(null);
                        }
                        JDWebView.this.removeLastHistory();
                        return true;
                    }
                }
                JDWebView jDWebView = JDWebView.this;
                jDWebView.loadUrl(jDWebView.url);
                return true;
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onDetectedBlankScreen(String str2, int i2) {
                super.onDetectedBlankScreen(str2, i2);
                ExceptionReporter.reportWebViewCommonError("WebViewBlankScreen", JDWebView.this.finalUrl, "onDetectedBlankScreen", "s = " + str2 + ", i = " + i2);
                JDWebView.this.addLoadEvent(WebWhiteScreenHolder.PAGE_DETECTED_BLANKSCREEN);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onPageCommitVisible(WebView webView, String str2) {
                super.onPageCommitVisible(webView, str2);
                JDWebView.this.perfRecordHolder.appendDataToCurrRecord("pageCommit", String.valueOf(System.currentTimeMillis()));
                if (Log.D) {
                    JDWebView.this.xLogD("JDWebView [onPageCommitVisible], url: " + JDWebView.this.url);
                }
                WebDebug.log("webview", "[onPageCommitVisible], url: " + JDWebView.this.url, JDWebView.this);
                if (JDWebView.this.onCommitHideProgress) {
                    JDWebView.this.hideProgress();
                    JDWebView.this.autoHidePullState(0L);
                }
                JDWebView.this.addLoadEvent("pageCommit");
                if (JDWebView.this.webViewInterceptUrlListener != null) {
                    JDWebView.this.webViewInterceptUrlListener.onPageCommitVisible(webView, JDWebView.this.url);
                }
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onPageFinished(WebView webView, String str2) {
                String str3;
                if (Log.D || WebLogHelper.showXLog) {
                    JDWebView.this.xLogD("JDWebView [onPageFinished], progress = " + webView.getProgress() + "%,  url: " + str2);
                }
                JDWebView.this.isPageFinished = true;
                JDWebView.this.destroyLocalHtml();
                WebDebug.log("webview", "[onPageFinished], progress = " + webView.getProgress() + "%,  url: " + str2, JDWebView.this);
                JDWebView.this.loadEnd = System.currentTimeMillis();
                if (WebUtils.isBlankUrl(str2)) {
                    return;
                }
                String unpl = JDMtaUtils.getUnpl();
                JDWebView jDWebView = JDWebView.this;
                if (unpl == null) {
                    unpl = "";
                }
                CookieUtil.setCookie(jDWebView, "unpl", unpl);
                if (JDWebView.this.webViewInterceptUrlListener != null) {
                    JDWebView.this.webViewInterceptUrlListener.onPageFinished(webView, str2);
                }
                if (JDWebView.this.internalInterceptUrlListener != null) {
                    JDWebView.this.internalInterceptUrlListener.onPageFinished(webView, str2);
                }
                if (JDWebView.this.webViewClientListener != null) {
                    JDWebView.this.webViewClientListener.onPageFinished(webView, str2);
                }
                super.onPageFinished(webView, str2);
                if (!SwitchQueryFetcher.getSwitchBooleanValue("removeDefaultTitle", true)) {
                    JDWebView.this.receivedTitle(webView.getTitle());
                }
                if (JDWebView.this.isPreRender) {
                    XRender.Log("webview\u52a0\u8f7d\u5b8c\u6bd5");
                }
                JDWebView.this.isPageLoaded = true;
                JDWebView jDWebView2 = JDWebView.this;
                if (!jDWebView2.hasOnceShowPage) {
                    jDWebView2.closeWhenNative = WebUtils.checkHostCloseListForNative(jDWebView2.overrideUrl);
                } else {
                    jDWebView2.closeWhenNative = false;
                }
                JDWebView jDWebView3 = JDWebView.this;
                if (jDWebView3.hasOnceShowPageStarting) {
                    jDWebView3.hasOnceShowPage = true;
                }
                jDWebView3.perfRecordHolder.setFirstInterruptMsg(null);
                WebPerformance recordWithoutFinishFromUrl = JDWebView.this.perfRecordHolder.getRecordWithoutFinishFromUrl(str2);
                if (recordWithoutFinishFromUrl != null) {
                    if (JDWebView.this.perfRecordHolder.shouldCombineRecordForUrl(str2)) {
                        recordWithoutFinishFromUrl.appendData(WebPerfManager.COMBINED_PAGE_FINISH, String.valueOf(JDWebView.this.loadEnd));
                    } else {
                        recordWithoutFinishFromUrl.appendData("pageFinish", String.valueOf(JDWebView.this.loadEnd));
                    }
                    recordWithoutFinishFromUrl.setPath(JDWebView.this.getUrlHistory());
                    recordWithoutFinishFromUrl.setInterrupted(null);
                }
                JDWebView jDWebView4 = JDWebView.this;
                jDWebView4.appendWhiteScreenData("pageFinish", String.valueOf(jDWebView4.loadEnd));
                JDWebView.this.addLoadEvent("pageFinish");
                if (webView.getProgress() >= 100) {
                    if (recordWithoutFinishFromUrl == null) {
                        recordWithoutFinishFromUrl = JDWebView.this.perfRecordHolder.getRecordWithoutFinish100FromUrl(str2);
                    }
                    if (recordWithoutFinishFromUrl != null) {
                        recordWithoutFinishFromUrl.setInterrupted(null);
                        recordWithoutFinishFromUrl.appendData(WebPerfManager.PAGE_FINISH100, String.valueOf(JDWebView.this.loadEnd));
                    }
                    if (!JDWebView.this.isError) {
                        long createTime = JDWebView.this.perfRecordHolder.getCurrentRecord() != null ? JDWebView.this.perfRecordHolder.getCurrentRecord().getCreateTime() : 0L;
                        if (createTime <= 0) {
                            createTime = System.currentTimeMillis();
                        }
                        String valueOf = String.valueOf(createTime);
                        String loadType = PerformanceManager.getInstance().getLoadType();
                        if (PerformanceManager.getInstance().shouldReport()) {
                            PerformanceManager.getInstance().appendData("mloadType", loadType);
                            PerformanceManager performanceManager = PerformanceManager.getInstance();
                            JDWebView jDWebView5 = JDWebView.this;
                            performanceManager.appendData("secondType", jDWebView5.getJDWebViewContainerName(jDWebView5.mContext));
                            PerformanceManager.getInstance().appendData(PerformanceManager.LOAD_URL, str2);
                            PerformanceManager performanceManager2 = PerformanceManager.getInstance();
                            if (JDWebView.this.isSystemCoreNotX5()) {
                                str3 = "system";
                            } else {
                                str3 = "x5-" + WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication());
                            }
                            performanceManager2.appendData("kernelType", str3);
                            PerformanceManager.getInstance().appendData("mloadingTime", String.valueOf(JDWebView.this.loadEnd - JDWebView.this.loadStart));
                            PerformanceManager.getInstance().appendData("sessionId", valueOf);
                            PerformanceManager.getInstance().appendPath(JDWebView.this.getUrlHistory());
                            PerformanceManager.getInstance().setOccurTime(JDWebView.this.loadEnd);
                            PerformanceManager.getInstance().report();
                        }
                        if (WebPerfManager.getInstance().isEnable()) {
                            WebPerfManager.getInstance().getPerformance(webView, str2, valueOf);
                        }
                    }
                }
                if (recordWithoutFinishFromUrl != null) {
                    recordWithoutFinishFromUrl.setOccurTime(JDWebView.this.loadEnd);
                    if ("1".equals(HybridBase.getInstance().getSetting(HybridSDK.SWITCH_IO_DETAIL))) {
                        recordWithoutFinishFromUrl.recordIoDetail();
                    }
                    if (!JDWebView.this.isPreRender || JDWebView.this.xRenderManager == null || JDWebView.this.isAttached) {
                        JDWebView.this.perfRecordHolder.reportRecordBefore(recordWithoutFinishFromUrl, false);
                    } else if (!JDWebView.this.xRenderManager.isPerformanceReported()) {
                        JDWebView.this.perfRecordHolder.reportRecordBefore(recordWithoutFinishFromUrl, true, true);
                        JDWebView.this.xRenderManager.setPerformanceReported(true);
                    }
                }
                if (WebDebug.report & JDWebView.this.isError) {
                    WebDebug.log("webview", "cookies:" + CookieManager.getInstance().getCookie(str2));
                }
                JDWebView.this.hidePlaceHolder();
                if (com.jd.libs.xdog.f.v && JDWebView.this.xDogRegistered) {
                    com.jd.libs.xdog.b.m(JDWebView.this.dogId, JDWebView.this.perfRecordHolder.getCurrentRecord().getData());
                }
                if (JDWebView.this.extraSetting != null) {
                    Object obj = JDWebView.this.extraSetting.get(OpenAppJumpController.KEY_OPEN_LINK);
                    if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                        OpenLinkTimeManager.getInstance().webviewRender(JDWebView.this.getFinalUrl(), JDWebView.this.getMtaSequence());
                    }
                }
            }

            @Override // com.jingdong.common.web.BaseWebViewClient, com.tencent.smtt.sdk.WebViewClient
            public void onPageStarted(WebView webView, String str2, Bitmap bitmap) {
                CookieUtil.monitorCookie(JDWebView.this);
                JDWebView.this.loadStart = System.currentTimeMillis();
                if (JDWebView.this.jsPerformance != null) {
                    JDWebView.this.jsPerformance.onPageStarted();
                }
                if (!JDWebView.this.isPageLoaded) {
                    JDWebView.this.addLoadEvent("pageStart");
                }
                JDWebView.this.perfRecordHolder.setFirstInterruptMsg(TrackLoadSettingsAtom.TYPE);
                WebPerformance currentRecord = JDWebView.this.perfRecordHolder.getCurrentRecord();
                if (currentRecord == null || currentRecord.containKeyExact("pageStart") || currentRecord.isReported()) {
                    currentRecord = JDWebView.this.perfRecordHolder.createRecord();
                }
                if (JDWebView.this.perfRecordHolder.shouldCombineRecordForUrl(str2)) {
                    JDWebView.this.perfRecordHolder.appendDataToCurrRecord(WebPerfManager.COMBINED_PAGE_START, String.valueOf(JDWebView.this.loadStart));
                } else {
                    JDWebView.this.perfRecordHolder.appendDataToCurrRecord("pageStart", String.valueOf(JDWebView.this.loadStart));
                }
                JDWebView.this.perfRecordHolder.appendDataToCurrRecord("url", str2);
                WebPerfManager.getInstance().onLoad(str2);
                if (currentRecord != null) {
                    currentRecord.setInterrupted(TrackLoadSettingsAtom.TYPE);
                }
                super.onPageStarted(JDWebView.this.webView, str2, bitmap);
                WebDebug.log("webview", "[onPageStarted]:" + str2, JDWebView.this);
                JDWebView.this.hideErrView();
                if (Log.D || WebLogHelper.showXLog) {
                    JDWebView.this.xLogD("JDWebView [onPageStarted] url: " + str2);
                }
                X5WebView x5WebView2 = JDWebView.this.webView;
                if (x5WebView2 != null) {
                    x5WebView2.setMtaDataInjected(false);
                }
                JdCrashReport.appendUrl(str2);
                JDWebView.this.isError = false;
                Uri parse = !TextUtils.isEmpty(str2) ? Uri.parse(str2) : null;
                if (WebUtils.needReportUnknownScheme(parse)) {
                    WebUnifiedMtaUtil.sendUnknownSchemeMta(webView.getOriginalUrl(), JDWebView.this.getUrlHistory(), parse.getScheme(), parse.toString());
                }
                if (WebUtils.isSchemeInBlackList(parse != null ? parse.getScheme() : null)) {
                    JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                    if (currentRecord != null) {
                        currentRecord.setInterrupted(null);
                    }
                    JDWebView.this.stopLoading();
                } else if (!SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, false) || !WebUtils.checkUrlInBlackList(str2)) {
                    if ((JDWebView.this.webViewInterceptUrlListener == null || !JDWebView.this.webViewInterceptUrlListener.interceptOnPageStart(webView, str2)) && (JDWebView.this.internalInterceptUrlListener == null || !JDWebView.this.internalInterceptUrlListener.interceptOnPageStart(webView, str2))) {
                        if (JDWebView.this.webViewClientListener != null) {
                            JDWebView.this.webViewClientListener.onPageStarted(webView, str2, bitmap);
                        }
                        if (JDWebView.this.enableWebLog && JDWebView.this.isPageLoaded && JDWebView.this.mWebLogHelper != null) {
                            JDWebView.this.mWebLogHelper.reportLogs();
                        }
                        JDWebView.this.isPageLoaded = false;
                        JDWebView jDWebView = JDWebView.this;
                        jDWebView.hasOnceShowPageStarting = true;
                        if (Log.D || WebLogHelper.showXLog) {
                            jDWebView.xLogD("start load progress bar");
                        }
                        JDWebView.this.currentProgress = 0.0f;
                        JDWebView.this.mHandler.post(JDWebView.this.progressRunnable);
                        if (!JDWebView.this.onCommitHideProgress) {
                            JDWebView.this.hideProgress();
                            JDWebView.this.autoHidePullState(0L);
                        }
                        JDWebView.this.navigatorHolder.defaultNaviWithoutClostBtn();
                        if (JDWebView.this.webViewInterceptUrlListener != null) {
                            JDWebView.this.webViewInterceptUrlListener.interceptOnPageStartAfterDefaultNavi(webView, str2);
                        }
                        if (JDWebView.this.internalInterceptUrlListener != null) {
                            JDWebView.this.internalInterceptUrlListener.interceptOnPageStartAfterDefaultNavi(webView, str2);
                        }
                        JDWebView.this.showImageProgress();
                        JDWebView.this.finalUrl = str2;
                        JDWebView.this.overrideUrl = str2;
                        if (JDWebView.this.mWebChromeClient != null) {
                            JDWebView.this.mWebChromeClient.setUrl(JDWebView.this.finalUrl);
                        }
                        PerfMonitor.getInstance().refreshCurrentWebViewUrl(str2);
                        if (WebPerfManager.getInstance().isEnable()) {
                            WebPerfManager.getInstance().onLCPListening(webView);
                        }
                        JDWebView.this.registerXDog();
                        return;
                    }
                    JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                    if (currentRecord != null) {
                        currentRecord.setInterrupted(null);
                    }
                    JDWebView.this.stopLoading();
                } else {
                    WebUnifiedMtaUtil.sendUrlInBlackListMta(str2, JDWebView.this.getUrlHistory(), "from url: " + webView.getOriginalUrl(), "JDWebView.onPageStarted");
                    JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                    if (currentRecord != null) {
                        currentRecord.setInterrupted(null);
                    }
                    if (JDWebView.this.blackRedirectRunnable != null) {
                        JDWebView.this.mHandler.postDelayed(JDWebView.this.blackRedirectRunnable, 100L);
                    }
                }
            }

            @Override // com.jingdong.common.web.BaseWebViewClient, com.tencent.smtt.sdk.WebViewClient
            public void onReceivedError(WebView webView, int i2, String str2, String str3) {
                String str4;
                super.onReceivedError(webView, i2, str2, str3);
                if (Log.E || WebLogHelper.showXLog) {
                    JDWebView.this.xLogE("JDWebView [onReceivedError]  errorCode: " + i2 + "  |description: " + str2 + "  |failingUrl: " + str3);
                }
                JDWebView.this.isError = true;
                JDWebView.this.loadEnd = System.currentTimeMillis();
                if (JDWebView.this.webViewClientListener != null) {
                    JDWebView.this.webViewClientListener.onReceivedError(webView, i2, str2, str3);
                }
                JDWebView.this.perfRecordHolder.setFirstInterruptMsg(null);
                WebPerformance currentRecord = JDWebView.this.perfRecordHolder.getCurrentRecord();
                if (currentRecord != null) {
                    currentRecord.setInterrupted(null);
                }
                JDWebView.this.perfRecordHolder.setNetErrorToCurrRecord(true);
                JDWebView.this.perfRecordHolder.setFailUrlToCurrRecord(str3);
                JDWebView.this.perfRecordHolder.setPathToCurrRecord(JDWebView.this.getUrlHistory());
                JDWebView.this.perfRecordHolder.appendDataToCurrRecord("errorCode", String.valueOf(i2));
                JDWebView.this.perfRecordHolder.appendDataToCurrRecord("errorMsg", str2);
                if (PerformanceManager.getInstance().shouldReport()) {
                    PerformanceManager.getInstance().appendData("mloadingTime", String.valueOf(JDWebView.this.loadEnd - JDWebView.this.loadStart));
                    PerformanceManager.getInstance().appendData("mloadType", PerformanceManager.getInstance().getLoadType());
                    PerformanceManager performanceManager = PerformanceManager.getInstance();
                    JDWebView jDWebView = JDWebView.this;
                    performanceManager.appendData("secondType", jDWebView.getJDWebViewContainerName(jDWebView.mContext));
                    PerformanceManager.getInstance().appendData(PerformanceManager.ERR_TYPE, "service error");
                    PerformanceManager.getInstance().appendData("errMsg", "code:" + i2 + "  des:" + str2);
                    PerformanceManager.getInstance().appendData("userAgent", JDWebView.this.getUaInfo());
                    PerformanceManager.getInstance().appendPath(JDWebView.this.getUrlHistory());
                    PerformanceManager.getInstance().appendData("isError", "1");
                    PerformanceManager.getInstance().appendData(PerformanceManager.LOAD_URL, str3 == null ? "" : str3);
                    PerformanceManager performanceManager2 = PerformanceManager.getInstance();
                    if (JDWebView.this.isSystemCoreNotX5()) {
                        str4 = "system";
                    } else {
                        str4 = "x5-" + WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication());
                    }
                    performanceManager2.appendData("kernelType", str4);
                    HashMap hashMap = new HashMap();
                    hashMap.put(BabelLoginCallback.KEY_COOKIES, CookieManager.getInstance().getCookie(str3));
                    PerformanceManager.getInstance().appendData("header", hashMap);
                    PerformanceManager.getInstance().report();
                }
                JDWebView.this.appendWhiteScreenData(WebWhiteScreenHolder.WEB_ERR, "service error");
                JDWebView.this.appendWhiteScreenData(WebWhiteScreenHolder.WEB_ERR_MSG, "code:" + i2 + "  des:" + str2);
                JDWebView.this.addLoadEvent(WebWhiteScreenHolder.PAGE_FAIL);
                if (WebDebug.report) {
                    WebDebug.log("webview", "[onReceivedError]: errorCode" + i2 + "--desc:" + str2 + "--url:" + str3, JDWebView.this);
                    StringBuilder sb = new StringBuilder();
                    sb.append("cookies:");
                    sb.append(CookieManager.getInstance().getCookie(str3));
                    WebDebug.log("webview", sb.toString());
                }
                if (JDWebView.this.isPreRender && JDWebView.this.xRenderManager != null) {
                    JDWebView.this.xRenderManager.onReceivedError("webview\u52a0\u8f7d\u5931\u8d25 errorCode=" + i2, "3");
                }
                JDWebView.this.showErrView(i2, str3);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str2, String str3) {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str2, str3);
            }

            /* JADX WARN: Removed duplicated region for block: B:125:0x0209  */
            @Override // com.jingdong.common.web.BaseWebViewClient, com.tencent.smtt.sdk.WebViewClient
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onReceivedHttpError(com.tencent.smtt.sdk.WebView r19, com.tencent.smtt.export.external.interfaces.WebResourceRequest r20, com.tencent.smtt.export.external.interfaces.WebResourceResponse r21) {
                /*
                    Method dump skipped, instructions count: 702
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.ui.JDWebView.AnonymousClass6.onReceivedHttpError(com.tencent.smtt.sdk.WebView, com.tencent.smtt.export.external.interfaces.WebResourceRequest, com.tencent.smtt.export.external.interfaces.WebResourceResponse):void");
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (Log.E || WebLogHelper.showXLog) {
                    JDWebView.this.xLogE("JDWebView [onReceivedSslError] errCode: " + sslError.getPrimaryError() + "  |cer: " + sslError.getCertificate() + "  |url: " + JDWebView.this.finalUrl);
                }
                if (WebDebug.report) {
                    WebDebug.log("webview", "[onReceivedSslError]: " + JDWebView.this.finalUrl, JDWebView.this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("WebView usingX5Core: ");
                sb.append(!JDWebView.this.isSystemCoreNotX5());
                sb.append("    TbsVersion: ");
                sb.append(QbSdk.getTbsVersion(JDWebView.this.mContext));
                String sb2 = sb.toString();
                WebUtils.reportSslException(JDWebView.this.finalUrl, sslError, sb2);
                if (JDWebView.this.mContext != null && (!(JDWebView.this.mContext instanceof Activity) || !((Activity) JDWebView.this.mContext).isFinishing())) {
                    try {
                        new JDWebviewSslErrorDialogController(JDWebView.this.getContext()).configData(sslError, sslErrorHandler, JDWebView.this.finalUrl, sb2);
                    } catch (Exception unused) {
                    }
                }
                if (PerformanceManager.getInstance().shouldReport()) {
                    PerformanceManager.getInstance().appendData("isError", "1");
                    PerformanceManager.getInstance().appendData(PerformanceManager.ERR_TYPE, "ssl error");
                    PerformanceManager.getInstance().appendData("errMsg", "ssl \u8bc1\u4e66\u9519\u8bef:" + sb2);
                }
                JDWebView.this.perfRecordHolder.setErrorToCurrRecord(true);
                JDWebView.this.perfRecordHolder.setFailUrlToCurrRecord(JDWebView.this.finalUrl);
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("url", (Object) JDWebView.this.finalUrl);
                jDJSONObject.put("errorCode", (Object) String.valueOf(sslError.getPrimaryError()));
                jDJSONObject.put("msg", (Object) WebUtils.getSslErrMsg(sslError, sb2));
                JDWebView.this.perfRecordHolder.addSslErrToCurrRecord(jDJSONObject);
                JDWebView.this.appendWhiteScreenData(WebWhiteScreenHolder.WEB_ERR, "ssl error");
                JDWebView.this.appendWhiteScreenData(WebWhiteScreenHolder.WEB_ERR_MSG, "ssl \u8bc1\u4e66\u9519\u8bef:" + sb2);
                JDWebView.this.addLoadEvent(WebWhiteScreenHolder.PAGE_FAIL);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean onRenderProcessGone(WebView webView, WebViewClient.RenderProcessGoneDetail renderProcessGoneDetail) {
                String str2 = JDWebView.this.finalUrl;
                StringBuilder sb = new StringBuilder();
                sb.append("didCrash = ");
                sb.append(renderProcessGoneDetail != null ? Boolean.valueOf(renderProcessGoneDetail.didCrash()) : DYConstants.DY_NULL_STR);
                ExceptionReporter.reportWebViewCommonError("WebViewRenderProcGone", str2, "onRenderProcessGone", sb.toString());
                return super.onRenderProcessGone(webView, renderProcessGoneDetail);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str2) {
                WebResourceResponse interceptRequest;
                WebResourceResponse interceptRequest2;
                if (BaseInfo.getAndroidSDKVersion() < 21) {
                    WebResourceResponse interceptLocalHtml = JDWebView.this.interceptLocalHtml(str2);
                    if (interceptLocalHtml == null) {
                        if (JDWebView.this.webViewInterceptUrlListener != null && (interceptRequest2 = JDWebView.this.webViewInterceptUrlListener.interceptRequest(webView, str2)) != null) {
                            return interceptRequest2;
                        }
                        if (JDWebView.this.internalInterceptUrlListener != null && (interceptRequest = JDWebView.this.internalInterceptUrlListener.interceptRequest(webView, str2)) != null) {
                            return interceptRequest;
                        }
                    } else {
                        com.jd.libs.hybrid.base.util.Log.xLogD("loadHtml", "\u65b0\u6d41\u7a0b\u62e6\u622a\u83b7\u53d6HTML");
                        return interceptLocalHtml;
                    }
                }
                return super.shouldInterceptRequest(webView, str2);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                String uri = webResourceRequest.getUrl().toString();
                if (Log.D) {
                    XLog.d(JDWebView.this.TAG, "JDWebView [shouldOverrideUrlLoading]  isRedirect: " + webResourceRequest.isRedirect() + " url:" + uri);
                }
                return combineShouldOverrideUrlLoading(webView, uri);
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str2) {
                WebDebug.log("webview", "[shouldOverrideUrlLoading]:" + str2, JDWebView.this);
                if (Log.D || WebLogHelper.showXLog) {
                    JDWebView.this.xLogD("[shouldOverrideUrlLoading]: " + str2);
                }
                if (SwitchQueryFetcher.getSwitchBooleanValue("fixReplaceBackIssue", false)) {
                    return combineShouldOverrideUrlLoading(webView, str2);
                }
                if (combineShouldOverrideUrlLoading(webView, str2)) {
                    if (SwitchQueryFetcher.getSwitchBooleanValue("wvLoginReplaceIsFirstH5Switch", false) && JDWebView.this.isFirstH5Page) {
                        if (Log.D || WebLogHelper.showXLog) {
                            JDWebView.this.xLogD("[shouldOverrideUrlLoading]: isFirstH5Page = false");
                        }
                        JDWebView.this.isFirstH5Page = false;
                    }
                    return true;
                }
                WebView.HitTestResult hitTestResult = webView.getHitTestResult();
                if (JDWebView.this.isFirstH5Page || !(hitTestResult == null || hitTestResult.getType() == 0)) {
                    if (JDWebView.this.isFirstH5Page) {
                        JDWebView.this.isFirstH5Page = false;
                    }
                    if (Log.D || WebLogHelper.showXLog) {
                        JDWebView.this.xLogD("[shouldOverrideUrlLoading]: loadUrl");
                    }
                    webView.loadUrl(str2);
                    return true;
                }
                return false;
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                WebResourceResponse interceptRequest;
                WebResourceResponse interceptRequest2;
                WebResourceResponse interceptLocalHtml = JDWebView.this.interceptLocalHtml((webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : webResourceRequest.getUrl().toString());
                if (interceptLocalHtml == null) {
                    return (JDWebView.this.webViewInterceptUrlListener == null || (interceptRequest2 = JDWebView.this.webViewInterceptUrlListener.interceptRequest(webView, webResourceRequest)) == null) ? (JDWebView.this.internalInterceptUrlListener == null || (interceptRequest = JDWebView.this.internalInterceptUrlListener.interceptRequest(webView, webResourceRequest)) == null) ? super.shouldInterceptRequest(webView, webResourceRequest) : interceptRequest : interceptRequest2;
                }
                com.jd.libs.hybrid.base.util.Log.xLogD("loadHtml", "\u65b0\u94fe\u8def \u65b0\u6d41\u7a0b\u62e6\u622a\u83b7\u53d6HTML");
                return interceptLocalHtml;
            }
        });
        X5WebView x5WebView2 = this.webView;
        BaseWebChromeClient baseWebChromeClient = new BaseWebChromeClient(this.mContext) { // from class: com.jingdong.common.web.ui.JDWebView.7
            {
                JDWebView.this = this;
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (Log.D && JDWebView.this.mWebLogView != null) {
                    JDWebView.this.mWebLogView.console(consoleMessage);
                }
                if (Log.D || WebLogHelper.showXLog) {
                    WebUtils.onJSConsoleMessage(consoleMessage);
                }
                if (consoleMessage != null && consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR && JDWebView.this.whiteScreenHolder != null) {
                    JDWebView.this.whiteScreenHolder.setJsLog(consoleMessage.message());
                }
                if (JDWebView.this.enableWebLog) {
                    if (JDWebView.this.mWebLogHelper == null) {
                        JDWebView.this.mWebLogHelper = new WebLogHelper();
                    }
                    JDWebView.this.mWebLogHelper.addJsLog(JDWebView.this.finalUrl, consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onProgressChanged(WebView webView, int i2) {
                super.onProgressChanged(webView, i2);
                X5WebView x5WebView3 = JDWebView.this.webView;
                if (x5WebView3 == null || x5WebView3.isMtaDataInjected()) {
                    return;
                }
                JDWebView.this.webView.injectMtaData();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient
            public void onReceivedTitle(WebView webView, String str2) {
                super.onReceivedTitle(webView, str2);
                X5WebView x5WebView3 = JDWebView.this.webView;
                if (x5WebView3 != null && !x5WebView3.isMtaDataInjected()) {
                    JDWebView.this.webView.injectMtaData();
                }
                JDWebView.this.receivedTitle(str2);
                if (WebDebug.report) {
                    WebDebug.log("webview", "[onReceivedTitle]: " + str2, JDWebView.this);
                }
                JDWebView.this.appendPerformanceData(WebPerfManager.NAME, str2);
            }
        };
        this.mWebChromeClient = baseWebChromeClient;
        x5WebView2.setWebChromeClient(baseWebChromeClient);
        if (this.webView.getX5WebViewExtension() != null && WebUtils.isX5CoreNumInSwitch(this.mContext, "HttpDnsX5Version")) {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("HybridDnsHost", "");
            if (WebHybridUtils.needSetDnsHost) {
                QbSdk.setNewDnsHostList(switchStringValue);
                WebHybridUtils.needSetDnsHost = false;
            }
            this.webView.getX5WebViewExtension().setWebViewClientExtension(new ProxyWebViewClientExtension() { // from class: com.jingdong.common.web.ui.JDWebView.8
                {
                    JDWebView.this = this;
                }

                @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                public int getHostByName(String str2, List<String> list) {
                    IpModel ipFromMemoryCache;
                    if (!TextUtils.isEmpty(str2) && (ipFromMemoryCache = JDHttpDnsToolkit.getInstance().getIpFromMemoryCache(str2)) != null) {
                        String str3 = ipFromMemoryCache.master;
                        String[] strArr = ipFromMemoryCache.v4;
                        String[] strArr2 = ipFromMemoryCache.v6;
                        if (!TextUtils.isEmpty(str3)) {
                            list.add(str3);
                        }
                        if (strArr2 != null && strArr2.length > 0) {
                            list.addAll(Arrays.asList(strArr2));
                        }
                        if (strArr != null && strArr.length > 0) {
                            list.addAll(Arrays.asList(strArr));
                        }
                    }
                    if (Log.D) {
                        Log.d(JDWebView.this.TAG, "getHostByName = " + str2 + "   vList = " + list);
                    }
                    return list.size() > 0 ? 1 : 0;
                }

                @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
                public void onScrollChanged(int i2, int i3, int i4, int i5) {
                    super.onScrollChanged(i2, i3, i4, i5);
                    if (JDWebView.this.webView.useWebScrollListener()) {
                        Log.d(JDWebView.this.TAG, "own onScrollChanged:" + i2 + "  " + i3 + "  " + i4 + "  " + i5);
                        JDWebView.this.webView.setWebScrollChanged(i4, i5, i2, i3);
                    }
                }
            });
        }
        markInitPhaseEnd("listeners");
    }

    private void initWhiteScreen() {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("webWhiteScreenMonitor", "");
        if (TextUtils.isEmpty(switchStringValue)) {
            xLogD("webWhiteScreenMonitor = null");
            return;
        }
        JDJSONObject parseObject = JDJSON.parseObject(switchStringValue);
        if (parseObject != null) {
            if (TextUtils.equals("1", parseObject.getString("enable"))) {
                this.whiteScreenEnable = true;
            }
            if (!TextUtils.isEmpty(parseObject.getString("blankThreshold"))) {
                this.blankThreshold = Double.parseDouble(parseObject.getString("blankThreshold"));
            }
        }
        if (!this.whiteScreenEnable) {
            xLogD("enable = 0");
            return;
        }
        this.whiteScreenHolder = new WebWhiteScreenHolder();
        appendWhiteScreenData("initStart", String.valueOf(this.currentTimeInitStart));
        addLoadEvent("webInit");
    }

    public WebResourceResponse interceptLocalHtml(String str) {
        if (!this.isLocalHtmlIntercepted && this.localHtmlStream != null && !TextUtils.isEmpty(str) && str.trim().equals(this.url)) {
            try {
                this.isLocalHtmlIntercepted = true;
                if (!SwitchQueryFetcher.getSwitchBooleanValue("loadHtmlUseDefaultParam", false) && !BaseGraySwitch.loadHtmlUseDefaultParam) {
                    return new WebResourceResponse("", "", this.localHtmlStream);
                }
                return new WebResourceResponse(MIMEType.MIME_TYPE_HTML, "utf-8", this.localHtmlStream);
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private boolean isOfflineBiz() {
        HybridOfflineLoader hybridOfflineLoader = getHybridOfflineLoader();
        return hybridOfflineLoader != null && hybridOfflineLoader.hasOfflineConfig();
    }

    /* renamed from: k */
    public /* synthetic */ void l(final String str) {
        getWebView().evaluateJavascript(String.format("try {\n  (function() {\n    try {\n      if (!navigator.userAgent.startsWith('jdapp;android')) {\n        return 0;\n      } else {\n        window.location.replace('%s');\n        return 1;\n      }\n    } catch (error) {\n      return 0;\n    }\n  })()\n} catch (error) {\n}", str), new ValueCallback() { // from class: com.jingdong.common.web.ui.f
            {
                JDWebView.this = this;
            }

            @Override // com.tencent.smtt.sdk.ValueCallback, android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                JDWebView.this.j(str, (String) obj);
            }
        });
    }

    private void loadLoadingImg() {
        Uri remoteImageUriSync = CalorieImageUtil.getRemoteImageUriSync("112", this.loadingPlaceHolder);
        if (this.circleProgress != null) {
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.useDefaultPlaceholder(false);
            JDImageLoader.display(remoteImageUriSync, this.circleProgress, jDDisplayImageOptions, (ImageRequestListener<ImageInfo>) null);
        }
        if (this.placeHolderRunnable == null) {
            this.placeHolderRunnable = new Runnable() { // from class: com.jingdong.common.web.ui.u
                {
                    JDWebView.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDWebView.this.hidePlaceHolder();
                }
            };
        }
        this.mHandler.postDelayed(this.placeHolderRunnable, SwitchQueryFetcher.getSwitchIntValue("placeHolderRemove", 3000));
    }

    /* renamed from: m */
    public /* synthetic */ void n(String str) {
        getWebView().evaluateJavascript(String.format("window.location.replace('%s')", str), (ValueCallback<String>) null);
    }

    private void markInitPhaseEnd(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.initRecord == null) {
            this.initRecord = new JDWebViewInitRecord(this.currentTimeInitStart);
        }
        this.initRecord.markPhaseEnd(str);
    }

    /* renamed from: o */
    public /* synthetic */ void p(ErrCallback errCallback, View view) {
        if (errCallback != null) {
            errCallback.onErrReload();
            hideErrView();
            return;
        }
        reload();
    }

    private void onResumeNew() {
        XRenderManager xRenderManager;
        if (this.isForegroundOrVisible) {
            if (this.useXBridge) {
                X5WebView x5WebView = this.webView;
                if (x5WebView != null) {
                    x5WebView.onActive();
                    return;
                }
                return;
            }
            WebUtils.dispatchEvent(this, PageLifeCycleEvent.STATE_ACTIVE);
            return;
        }
        if (!this.useXBridge) {
            WebUtils.dispatchEvent(this, PageLifeCycleEvent.STATE_SHOW);
            WebUtils.dispatchEvent(this, PageLifeCycleEvent.STATE_ACTIVE);
        }
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.STARTED);
        }
        this.isForegroundOrVisible = true;
        X5WebView x5WebView2 = this.webView;
        if (x5WebView2 != null) {
            x5WebView2.onResume();
            this.webView.onActive();
            notifyWebViewVisible(true);
        }
        LifecycleRegistry lifecycleRegistry2 = this.lifecycleRegistry;
        if (lifecycleRegistry2 != null) {
            lifecycleRegistry2.markState(Lifecycle.State.RESUMED);
        }
        WebHybridLogView webHybridLogView = this.mWebHybridLogView;
        if (webHybridLogView != null) {
            webHybridLogView.onResume();
        }
        if (!this.isPreRender || (xRenderManager = this.xRenderManager) == null) {
            return;
        }
        xRenderManager.onResume();
    }

    private void onStopNew() {
        WebLogHelper webLogHelper;
        if (this.isForegroundOrVisible) {
            LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
            if (lifecycleRegistry != null) {
                lifecycleRegistry.markState(Lifecycle.State.STARTED);
            }
            this.isForegroundOrVisible = false;
            if (!this.useXBridge) {
                WebUtils.dispatchEvent(this, PageLifeCycleEvent.STATE_HIDE);
            }
            X5WebView x5WebView = this.webView;
            if (x5WebView != null) {
                x5WebView.onPause();
                notifyWebViewVisible(false);
            }
            if (this.enableWebLog && (webLogHelper = this.mWebLogHelper) != null) {
                webLogHelper.reportLogs();
            }
            reportPerformanceNow();
            LifecycleRegistry lifecycleRegistry2 = this.lifecycleRegistry;
            if (lifecycleRegistry2 != null) {
                lifecycleRegistry2.markState(Lifecycle.State.CREATED);
            }
        }
    }

    public void registerXDog() {
        if (TextUtils.isEmpty(this.finalUrl)) {
            return;
        }
        if ((!com.jd.libs.xdog.f.v && !com.jd.libs.xdog.f.u) || "xdog-pro.pf.jd.com".equals(Uri.parse(this.finalUrl).getHost()) || TextUtils.isEmpty(this.dogId) || this.xDogRegistered) {
            return;
        }
        this.xDogRegistered = true;
        com.jd.libs.xdog.b.c(this.rootLayout, getContext(), this.dogId);
        com.jd.libs.xdog.b.e(String.valueOf(QbSdk.getTbsVersion(getContext())), this.finalUrl, getUaInfo());
    }

    public void removeLastHistory() {
        LinkedList<String> linkedList = this.urlHistory;
        if (linkedList == null || linkedList.isEmpty()) {
            return;
        }
        this.urlHistory.removeLast();
    }

    private void reportInitPerformance(boolean z, Map<String, Object> map) {
        String str;
        if (this.initRecordReported) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        JDWebViewInitRecord jDWebViewInitRecord = this.initRecord;
        hashMap.put("mloadingTime", String.valueOf(jDWebViewInitRecord != null ? jDWebViewInitRecord.getElapsedTime() : 0L));
        hashMap.put("mloadType", "JDWebViewInit");
        hashMap.put("secondType", getJDWebViewContainerName(this.mContext));
        if (isSystemCoreNotX5()) {
            str = "system";
        } else {
            str = "x5-" + WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication());
        }
        hashMap.put("kernelType", str);
        if (!z) {
            hashMap.put("isError", "1");
        }
        JDWebViewInitRecord jDWebViewInitRecord2 = this.initRecord;
        String recordJsonStringWithExtra = jDWebViewInitRecord2 != null ? jDWebViewInitRecord2.getRecordJsonStringWithExtra(map) : "";
        if (Log.D) {
            Log.d("JDWebViewInit", "phases data = " + recordJsonStringWithExtra);
        }
        hashMap.put("extra", recordJsonStringWithExtra);
        PerformanceManager.getInstance().reportSimpleData(hashMap);
        this.initRecord = null;
        this.initRecordReported = true;
    }

    private boolean shouldFixLoadHtmlBackIssue(String str) {
        WebBackForwardList copyBackForwardList;
        try {
            boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("fixLoadHtmlBackIssue", false);
            if (this.isLocalHtmlScenario && switchBooleanValue) {
                if (TextUtils.isEmpty(str) && (copyBackForwardList = this.webView.copyBackForwardList()) != null) {
                    WebHistoryItem itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() - 1);
                    str = itemAtIndex != null ? itemAtIndex.getUrl() : "";
                }
                if (!TextUtils.isEmpty(str) && WebUtils.isBlankUrl(str)) {
                    if (!this.backToLocalHtml) {
                        this.ignoreShouldOverrideUrl = true;
                        replaceUrl(this.url);
                        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
                        if (webPerformanceHolder != null) {
                            webPerformanceHolder.appendExtraToCurrRecord("TTTNewRouteBackWhite", "1");
                        }
                    }
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public void showErrView(int i2, String str) {
        showErrView(i2, str, null);
    }

    private int[] transferNaviIconAndTitleStyle(String str, boolean z) {
        int[] iArr = z ? new int[]{2, 1} : new int[]{1, 2};
        if (str != null) {
            str = str.trim();
        }
        if (!TextUtils.isEmpty(str)) {
            if (!"ww".equalsIgnoreCase(str) && !JshopConst.JSHOP_PROMOTIO_W.equalsIgnoreCase(str)) {
                if ("wb".equalsIgnoreCase(str)) {
                    iArr[0] = 2;
                    iArr[1] = 1;
                } else if (!"bb".equalsIgnoreCase(str) && !"b".equalsIgnoreCase(str)) {
                    if ("bw".equalsIgnoreCase(str)) {
                        iArr[0] = 1;
                        iArr[1] = 2;
                    }
                } else {
                    iArr[0] = 1;
                    iArr[1] = 1;
                }
            } else {
                iArr[0] = 2;
                iArr[1] = 2;
            }
        }
        return iArr;
    }

    public void addJavascriptInterface(Object obj, String str) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.addJavascriptInterface(obj, str);
        }
    }

    public void addLoadEvent(String str) {
        WebWhiteScreenHolder webWhiteScreenHolder = this.whiteScreenHolder;
        if (webWhiteScreenHolder != null) {
            webWhiteScreenHolder.addLoadEvent(str);
        }
    }

    public void addWebViewScrollListener(WebViewScrollListener webViewScrollListener) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.addWebViewScrollListener(webViewScrollListener);
        }
    }

    public void appendInitPerformanceData(Map<String, Object> map) {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder == null || webPerformanceHolder.getCurrentRecord() == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        PullToRefreshX5WebView pullToRefreshX5WebView = this.ptrWebView;
        if (pullToRefreshX5WebView != null) {
            jDJSONObject.put("ptrView", (Object) pullToRefreshX5WebView.getInitPerformance());
        }
        JDWebViewInitRecord jDWebViewInitRecord = this.initRecord;
        if (jDWebViewInitRecord != null && jDWebViewInitRecord.getRecordJson() != null) {
            jDJSONObject.put("JDWebViewInit", (Object) this.initRecord.getRecordJson());
        }
        if (map != null) {
            jDJSONObject.putAll(map);
        }
        this.perfRecordHolder.appendExtraToCurrRecord("viewInitTime", jDJSONObject);
    }

    public void appendPerformanceData(String str, String str2) {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder == null || webPerformanceHolder.getCurrentRecord() == null) {
            return;
        }
        this.perfRecordHolder.appendDataToCurrRecord(str, str2);
    }

    public void appendPerformanceExtraData(String str, Object obj) {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder == null || webPerformanceHolder.getCurrentRecord() == null) {
            return;
        }
        this.perfRecordHolder.appendExtraToCurrRecord(str, obj);
    }

    public void appendWhiteScreenData(String str, String str2) {
        WebWhiteScreenHolder webWhiteScreenHolder = this.whiteScreenHolder;
        if (webWhiteScreenHolder != null) {
            webWhiteScreenHolder.appendData(str, str2);
        }
    }

    public boolean backOrClose() {
        if (onBack()) {
            return true;
        }
        if (this.isMainFrameActivity) {
            return false;
        }
        WebBackOrCloseListener webBackOrCloseListener = this.webBackOrCloseListener;
        if (webBackOrCloseListener != null) {
            webBackOrCloseListener.onWebClose();
        }
        ((Activity) this.mContext).finish();
        return true;
    }

    public boolean canBack() {
        X5WebView x5WebView = this.webView;
        return x5WebView != null && x5WebView.canGoBack();
    }

    public boolean canIUse(String str) {
        return this.xWidgetAvailable && com.jd.libs.xwidget.a.a.containsKey(str) && WebUtils.isX5CoreNumInSwitch(this.mContext, "XWidgetX5Version");
    }

    public void changeUIMode(int i2) {
        JDAppearanceHelper jDAppearanceHelper = this.appearanceHelper;
        if (jDAppearanceHelper != null) {
            jDAppearanceHelper.changeUiMode(i2);
        }
    }

    public void clearScrollState(boolean z) {
        this.preScrollState = -1;
        X5WebView x5WebView = this.webView;
        if (x5WebView == null || !z) {
            return;
        }
        x5WebView.setWebViewScrollListener(null);
    }

    public void customListenDarkMode(boolean z) {
        JDAppearanceHelper jDAppearanceHelper = this.appearanceHelper;
        if (jDAppearanceHelper != null) {
            jDAppearanceHelper.setCustomListenDarkMode(z, z);
        }
    }

    public void enableHybrid(String str) {
        if (!WebHybridUtils.hybridEnable) {
            if (Log.D) {
                Log.d(this.TAG, "Hybrid is disable now.");
            }
        } else if (this.loadStart > 0) {
            if (Log.D) {
                Log.d(this.TAG, "cannot enable hybrid after loading started.");
            }
        } else if (this.hybridStarted) {
            if (Log.D) {
                Log.d(this.TAG, "cannot enable hybrid after hybrid started pre-loading.");
            }
        } else {
            this.enableHybrid = true;
            this.offlineId = null;
            this.preloadId = null;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.url = str;
            hybridLoad();
            initHybridFunctions();
        }
    }

    public String getDogId() {
        return this.dogId;
    }

    public XRenderManager getExistedXRenderManager() {
        return this.xRenderManager;
    }

    public Object getExtraSetting(String str) {
        if (this.extraSetting == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return this.extraSetting.get(str);
    }

    public String getFinalUrl() {
        return this.finalUrl;
    }

    public GenTokenCallback getGenTokenCallback() {
        return this.genTokenCallback;
    }

    @Override // android.view.View
    public Handler getHandler() {
        return this.mHandler;
    }

    public String getHybridOfflineBConfig(String str) {
        HybridOfflineLoader fetchOfflineLoader = WebOfflineLoaderManager.fetchOfflineLoader(str);
        return fetchOfflineLoader != null ? fetchOfflineLoader.getBConfig() : "0";
    }

    public HybridOfflineLoader getHybridOfflineLoader() {
        if (WebHybridUtils.hybridEnable && this.enableHybrid && !TextUtils.isEmpty(this.offlineId)) {
            return WebOfflineLoaderManager.fetchOfflineLoader(this.offlineId);
        }
        return null;
    }

    protected int getInflateLayoutRes() {
        return R.layout.jd_webview;
    }

    public String getJDWebViewContainerName(Context context) {
        return (context != null ? context.getClass() : getClass()).getSimpleName();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public long getLoadEnd() {
        return this.loadEnd;
    }

    public String getLoadingPlaceHolder() {
        return this.loadingPlaceHolder;
    }

    public int getMtaSequence() {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            return x5WebView.getMtaSequence();
        }
        return 0;
    }

    public NavigatorHolder getNavigatorHolder() {
        return this.navigatorHolder;
    }

    public String getOfflineId() {
        return this.offlineId;
    }

    public PerformanceCallback getPerformanceCallback() {
        return this.performanceCallback;
    }

    public WebPerformanceHolder getPerformanceHolder() {
        return this.perfRecordHolder;
    }

    public PullToRefreshX5WebView getPullToRefreshView() {
        return this.ptrWebView;
    }

    public RelativeLayout getRootLayout() {
        return this.rootLayout;
    }

    public boolean getShallCheckImmersiveOnInit() {
        return this.shallCheckImmersiveOnInit;
    }

    public String getTitleText() {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        CharSequence text = (navigatorHolder == null || navigatorHolder.getTitleTextView() == null) ? null : this.navigatorHolder.getTitleTextView().getText();
        return !TextUtils.isEmpty(text) ? text.toString() : "";
    }

    public String getUaInfo() {
        X5WebView x5WebView = this.webView;
        return (x5WebView == null || x5WebView.getSettings() == null) ? DYConstants.DY_NULL_STR : this.webView.getSettings().getUserAgentString();
    }

    public String getUrl() {
        return this.url;
    }

    public List<String> getUrlHistory() {
        return this.urlHistory;
    }

    public boolean getVisibleStatus() {
        return this.isForegroundOrVisible;
    }

    public WebBackOrCloseListener getWebBackListener() {
        return this.webBackOrCloseListener;
    }

    public X5WebView getWebView() {
        return this.webView;
    }

    public WebWhiteScreenHolder getWhiteScreenHolder() {
        return this.whiteScreenHolder;
    }

    public XRenderManager getXRenderManager() {
        if (this.xRenderManager == null) {
            XRenderManager xRenderManager = new XRenderManager();
            this.xRenderManager = xRenderManager;
            xRenderManager.init(this);
        }
        return this.xRenderManager;
    }

    public XRenderWebUiBinder getXUiBinder() {
        return getXRenderManager().getXUiBinder();
    }

    public String getxRenderUrl() {
        return this.xRenderUrl;
    }

    public boolean hasHybridAvailableFiles() {
        HybridOfflineLoader hybridOfflineLoader = getHybridOfflineLoader();
        if (hybridOfflineLoader != null) {
            return hybridOfflineLoader.hasOfflineFiles();
        }
        return false;
    }

    public boolean hasHybridOfflineConfig() {
        HybridOfflineLoader hybridOfflineLoader = getHybridOfflineLoader();
        if (hybridOfflineLoader != null) {
            return hybridOfflineLoader.hasOfflineConfig();
        }
        return false;
    }

    public void hideOrShowNavigator(boolean z) {
        if (z) {
            this.titleLayout.setVisibility(4);
        } else {
            this.titleLayout.setVisibility(0);
        }
    }

    public void hidePlaceHolder() {
        if (TextUtils.isEmpty(this.loadingPlaceHolder)) {
            return;
        }
        Runnable runnable = this.placeHolderRunnable;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
        View view = this.circleProgress;
        if (view != null) {
            view.animate().alpha(0.0f).setDuration(500L).setListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.web.ui.JDWebView.18
                {
                    JDWebView.this = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    JDWebView.this.circleProgress.setVisibility(8);
                    JDWebView.this.circleProgress.setAlpha(1.0f);
                }
            });
        }
    }

    protected void inflateView() {
        String str;
        this.progressImage = (SimpleDraweeView) findViewById(R.id.progress_image);
        this.progressRelativeLayout = (RelativeLayout) findViewById(R.id.progressImage_layout);
        View findViewById = findViewById(R.id.webView);
        try {
            if (findViewById instanceof PullToRefreshX5WebView) {
                PullToRefreshX5WebView pullToRefreshX5WebView = (PullToRefreshX5WebView) findViewById;
                this.ptrWebView = pullToRefreshX5WebView;
                this.webView = pullToRefreshX5WebView.getRefreshableView();
            } else if (findViewById instanceof X5WebView) {
                this.webView = (X5WebView) findViewById;
            } else {
                if (findViewById == null) {
                    str = "R.id.webview is null";
                } else {
                    str = "R.id.webview is " + findViewById.getClass().getName();
                }
                ExceptionReporter.reportWebViewCommonError("WebViewInitViewElse", "", "JDWebView initView() else branch", str);
            }
            this.webview_layout = (RelativeLayout) findViewById(R.id.webview_layout);
            this.titleLayout = (ViewGroup) findViewById(R.id.app_webview_title);
            if (!TextUtils.isEmpty(this.loadingPlaceHolder)) {
                this.circleProgress = new WebPlaceHolderView(this.mContext);
            } else if (BaseInfo.getAndroidSDKVersion() >= 16 && BaseInfo.getAndroidSDKVersion() != 26 && BaseInfo.getAndroidSDKVersion() != 27) {
                this.circleProgress = new LottieLoadingView(this.mContext);
            } else {
                this.circleProgress = new JDProgressBar(this.mContext);
            }
            this.rootLayout = (RelativeLayout) findViewById(R.id.root_layout);
            if (!TextUtils.isEmpty(this.loadingPlaceHolder) && this.webview_layout != null) {
                this.webview_layout.addView(this.circleProgress, new RelativeLayout.LayoutParams(-1, -1));
                loadLoadingImg();
            } else if (this.rootLayout == null || this.circleProgress == null) {
            } else {
                int dip2px = DPIUtil.dip2px(45.0f);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px, dip2px);
                layoutParams.addRule(13);
                this.rootLayout.addView(this.circleProgress, layoutParams);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportWebViewCommonError("WebViewInitViewError", "", "JDWebView initView() error", e2.toString());
        }
    }

    public void initPreloadFunction() {
        if (TextUtils.isEmpty(this.preloadId)) {
            return;
        }
        initPreload(this.preloadId);
        initPreloadNew(this.preloadId);
        this.dogId = this.preloadId;
        this.hybridStarted = true;
    }

    public void injectJs(String str) {
        injectJs(str, 0L);
    }

    public boolean isAttached() {
        return this.isAttached;
    }

    public boolean isDarkMode() {
        JDAppearanceHelper jDAppearanceHelper;
        return this.canUseDarkMode && (jDAppearanceHelper = this.appearanceHelper) != null && jDAppearanceHelper.isDarkMode();
    }

    public boolean isError() {
        return this.isError;
    }

    public boolean isHybridPassGenToken() {
        HybridOfflineLoader hybridOfflineLoader = getHybridOfflineLoader();
        if (hybridOfflineLoader != null) {
            if (hybridOfflineLoader.useNewClient()) {
                return WebUtils.getBinarySwitch(hybridOfflineLoader.getBConfig(), 3);
            }
            return hybridOfflineLoader.canPassGentoken();
        }
        return false;
    }

    public boolean isNaviImmersive() {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            return navigatorHolder.isNaviImmersive();
        }
        return false;
    }

    public boolean isOfflineXView(String str) {
        try {
            return WebUtils.getBinarySwitch(getHybridOfflineBConfig(str), 1);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isPageFinished() {
        return this.isPageFinished;
    }

    public boolean isPageLoaded() {
        return this.isPageLoaded;
    }

    public boolean isPreRender() {
        return this.isPreRender;
    }

    public boolean isSystemCoreNotX5() {
        X5WebView x5WebView = this.webView;
        return x5WebView == null || x5WebView.getX5WebViewExtension() == null;
    }

    public boolean isTopBarGone() {
        return this.isTopBarGone;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void loadLocalHtml(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = "hybridTTTHtmlPlanB"
            r1 = 0
            boolean r0 = com.jingdong.common.utils.SwitchQueryFetcher.getSwitchBooleanValue(r0, r1)     // Catch: java.lang.Throwable -> L43
            r2 = 1
            if (r0 != 0) goto L11
            boolean r0 = com.jd.libs.hybrid.base.BaseGraySwitch.loadHtmlUsePlanB     // Catch: java.lang.Throwable -> L43
            if (r0 == 0) goto Lf
            goto L11
        Lf:
            r0 = 0
            goto L12
        L11:
            r0 = 1
        L12:
            com.jingdong.common.web.managers.WebPerformanceHolder r3 = r6.perfRecordHolder     // Catch: java.lang.Throwable -> L43
            if (r3 == 0) goto L22
            java.lang.String r4 = "TTTNewRoute"
            if (r0 == 0) goto L1d
            java.lang.String r5 = "A"
            goto L1f
        L1d:
            java.lang.String r5 = "B"
        L1f:
            r3.appendExtraToCurrRecord(r4, r5)     // Catch: java.lang.Throwable -> L43
        L22:
            boolean r3 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L43
            if (r3 != 0) goto L3f
            if (r0 == 0) goto L2d
            r6.localHtmlData = r8     // Catch: java.lang.Throwable -> L43
            goto L3d
        L2d:
            r6.isLocalHtmlIntercepted = r1     // Catch: java.lang.Throwable -> L43
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L43
            java.lang.String r1 = "utf-8"
            byte[] r8 = r8.getBytes(r1)     // Catch: java.lang.Throwable -> L43
            r0.<init>(r8)     // Catch: java.lang.Throwable -> L43
            r6.localHtmlStream = r0     // Catch: java.lang.Throwable -> L43
        L3d:
            r6.isLocalHtmlScenario = r2     // Catch: java.lang.Throwable -> L43
        L3f:
            r6.loadUrl(r7)     // Catch: java.lang.Throwable -> L43
            goto L46
        L43:
            r6.loadUrl(r7)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.ui.JDWebView.loadLocalHtml(java.lang.String, java.lang.String):void");
    }

    public void loadUrl(final String str) {
        if (str != null) {
            str = str.trim();
        }
        appendWhiteScreenData(WebWhiteScreenHolder.INIT_START_URL, str);
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, false)) {
            if (WebUtils.checkUrlInIllegalList(str)) {
                ExceptionReporter.reportWebViewCommonError(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, "", "JDWebView black list error", "");
                return;
            } else if (WebUtils.checkUrlInBlackList(str)) {
                Runnable runnable = this.blackRedirectRunnable;
                if (runnable != null) {
                    runnable.run();
                    return;
                }
                return;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.enableHybrid && !this.hybridStarted && this.loadStart <= 0) {
            setUrl(str);
            hybridLoad();
            this.lazyHybrid = true;
        }
        Runnable runnable2 = new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.9
            {
                JDWebView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                String str2 = "2";
                try {
                    if (JDWebView.this.lazyHybrid) {
                        JDWebView.this.lazyHybrid = false;
                        JDWebView.this.initHybridFunctions();
                    }
                    if (Log.D || WebLogHelper.showXLog) {
                        JDWebView.this.xLogD("start load url -->> " + str);
                    }
                    JDWebView.this.addUrlToHistory(str);
                    JDWebView.this.perfRecordHolder.setFirstInterruptMsg(TrackLoadSettingsAtom.TYPE);
                    WebPerformance currentRecord = JDWebView.this.perfRecordHolder.getCurrentRecord();
                    if (currentRecord != null) {
                        JDWebView.this.perfRecordHolder.appendDataToCurrRecord("url", str);
                        JDWebView.this.perfRecordHolder.appendDataToCurrRecord(WebPerfManager.LOAD_START, String.valueOf(System.currentTimeMillis()));
                        currentRecord.setInterrupted(TrackLoadSettingsAtom.TYPE);
                    }
                    JDWebView jDWebView = JDWebView.this;
                    WebUtils.setAcceptThirdCookie(jDWebView.webView, jDWebView.url);
                    JDWebView.this.setUrl(str);
                    if (JDWebView.this.extraSetting != null) {
                        Object obj = JDWebView.this.extraSetting.get(OpenAppJumpController.KEY_OPEN_LINK);
                        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                            OpenLinkTimeManager.getInstance().webviewLoadUrl(JDWebView.this.getMtaSequence());
                        }
                    }
                    if (TextUtils.isEmpty(JDWebView.this.localHtmlData)) {
                        if (JDWebView.this.isPreRender) {
                            HashMap hashMap = new HashMap();
                            if (!"2".equals(JDWebView.this.getExtraSetting("xrenderType"))) {
                                str2 = "1";
                            }
                            hashMap.put(WebWhiteScreenHolder.X_RENDER, str2);
                            XRender.Log("\u6dfb\u52a0\u8bf7\u6c42\u5934, xrender = " + str2);
                            JDWebView.this.webView.loadUrl(str, hashMap);
                        } else {
                            JDWebView.this.webView.loadUrl(str);
                        }
                    } else {
                        if (!SwitchQueryFetcher.getSwitchBooleanValue("loadHtmlUseDefaultParam", false) && !BaseGraySwitch.loadHtmlUseDefaultParam) {
                            JDWebView jDWebView2 = JDWebView.this;
                            jDWebView2.webView.loadDataWithBaseURL(str, jDWebView2.localHtmlData, "", "", null);
                            com.jd.libs.hybrid.base.util.Log.xLogD("loadHtml", "\u65b0\u94fe\u8def \u65b0\u6d41\u7a0bloadHTML\u52a0\u8f7d");
                            boolean unused = JDWebView.this.isPreRender;
                        }
                        JDWebView jDWebView3 = JDWebView.this;
                        jDWebView3.webView.loadDataWithBaseURL(str, jDWebView3.localHtmlData, MIMEType.MIME_TYPE_HTML, "utf-8", null);
                        com.jd.libs.hybrid.base.util.Log.xLogD("loadHtml", "\u65b0\u94fe\u8def \u65b0\u6d41\u7a0bloadHTML\u52a0\u8f7d");
                        boolean unused2 = JDWebView.this.isPreRender;
                    }
                    if (WebDebug.report) {
                        WebDebug.log("webview", "loadUrl:" + str, this);
                    }
                } catch (Exception e2) {
                    ExceptionReporter.reportWebViewCommonError("ErrorInLoadUrl", str, e2.getMessage(), ExceptionReporter.getStackStringFromException(e2));
                    if (Log.E || WebLogHelper.showXLog) {
                        JDWebView.this.xLogE(e2.getMessage());
                    }
                    if (!JDWebView.this.isPreRender || JDWebView.this.xRenderManager == null) {
                        return;
                    }
                    JDWebView.this.xRenderManager.clearData();
                }
            }
        };
        if (isOfflineBiz() && Looper.getMainLooper() == Looper.myLooper()) {
            runnable2.run();
        } else {
            this.mHandler.post(runnable2);
        }
    }

    public void notifyWebViewVisible(boolean z) {
        if (Log.D || WebLogHelper.showXLog) {
            xLogD("notifyWebViewVisible:" + z + "  isVisibleOrForeground: " + this.isForegroundOrVisible);
        }
        if (z) {
            injectJs("javascript:window.webviewVisible&&webviewVisible(1);");
        } else {
            injectJs("javascript:window.webviewVisible&&webviewVisible(0);");
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttached = true;
        appendPerformanceData(WebPerfManager.IS_PRE_RENDER, "0");
        if (this.isPreRender && this.xRenderManager != null) {
            WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
            if (webPerformanceHolder != null) {
                Context context = this.mContext;
                webPerformanceHolder.appendDataToCurrRecord(WebPerfManager.PAGE_NAME, context != null ? context.getClass().getSimpleName() : "");
                this.perfRecordHolder.appendDataToCurrRecord("url", this.xRenderManager.getBusinessUrl());
                this.perfRecordHolder.appendExtraToCurrRecord("onAttachedToWindow", Long.valueOf(System.currentTimeMillis()));
            }
            this.xRenderManager.onAttach(this.autoSendReadyEvent);
            if (this.isPageFinished) {
                reportPerformanceNow();
                this.xRenderManager.setPerformanceReported(true);
            }
        } else if (Log.D || WebLogHelper.showXLog) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u4e0d\u6267\u884conAttach \u539f\u56e0\uff1aisPreRender : ");
            sb.append(this.isPreRender);
            sb.append(" xRenderManager == null : ");
            sb.append(this.xRenderManager == null);
            xLogD(sb.toString());
        }
    }

    public boolean onBack() {
        if (shouldFixLoadHtmlBackIssue(null)) {
            if (this.backToLocalHtml) {
                return false;
            }
            this.backToLocalHtml = true;
            return true;
        } else if (canBack()) {
            WebBackOrCloseListener webBackOrCloseListener = this.webBackOrCloseListener;
            if (webBackOrCloseListener != null) {
                webBackOrCloseListener.onWebBack();
            }
            this.webView.goBack();
            return true;
        } else {
            return false;
        }
    }

    public void onDestory() {
        WebLogHelper webLogHelper;
        WebPerformanceHolder webPerformanceHolder;
        WebWhiteScreenHolder webWhiteScreenHolder = this.whiteScreenHolder;
        appendWhiteScreenData(WebWhiteScreenHolder.PAGE_DWELL_TIME, webWhiteScreenHolder != null ? webWhiteScreenHolder.getPageDwellTime() : "0");
        XRenderManager xRenderManager = this.xRenderManager;
        if (xRenderManager != null) {
            if (this.isPreRender && !this.isAttached && xRenderManager.isPerformanceReported() && (webPerformanceHolder = this.perfRecordHolder) != null) {
                webPerformanceHolder.clearAll();
            }
            this.xRenderManager.onDestroy();
            this.xRenderManager = null;
        }
        DataSnapshotSDK.INSTANCE.getInstance().onDestroy();
        destroyLocalHtml();
        WebHybridLogView webHybridLogView = this.mWebHybridLogView;
        if (webHybridLogView != null) {
            webHybridLogView.onDestroy();
            this.mWebHybridLogView = null;
        }
        if (this.enableWebLog && (webLogHelper = this.mWebLogHelper) != null) {
            webLogHelper.reportLogs();
        }
        reportPerformanceNow();
        if (this.whiteScreenEnable && !this.isFirstWhiteRenderRatio) {
            this.isFirstWhiteRenderRatio = true;
            WebUtils.whiteRenderRatio(this, this.blankThreshold);
        }
        JDAppearanceHelper jDAppearanceHelper = this.appearanceHelper;
        if (jDAppearanceHelper != null) {
            jDAppearanceHelper.destroy();
        }
        if (WebHybridUtils.hybridEnable) {
            if (!TextUtils.isEmpty(this.offlineId)) {
                WebOfflineLoaderManager.deleteOfflineLoader(this.offlineId);
            }
            if (!TextUtils.isEmpty(this.preloadId)) {
                WebPreLoadHelper.clearPreLoadData(this.preloadId);
            }
        }
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        }
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            WebViewPool.recycleWebView(x5WebView);
            this.webView = null;
        }
        if ((com.jd.libs.xdog.f.v || com.jd.libs.xdog.f.u) && !TextUtils.isEmpty(this.finalUrl)) {
            try {
                if (WebHybridUtils.degradeOfflineFromQuery(this.finalUrl)) {
                    com.jd.libs.xdog.b.a();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Runnable runnable = this.placeHolderRunnable;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Handler handler = this.mHandler;
        if (handler != null) {
            Runnable runnable = this.progressRunnable;
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                ImageView imageView = this.progressImage;
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
            }
            Runnable runnable2 = this.blackRedirectRunnable;
            if (runnable2 != null) {
                this.mHandler.removeCallbacks(runnable2);
            }
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        InterceptTouchEventListener interceptTouchEventListener = this.mInterceptTouchEventListener;
        if (interceptTouchEventListener == null || !interceptTouchEventListener.onInterceptTouchEvent(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && (this.webView.getWebChromeClient() instanceof BaseWebChromeClient)) {
            return ((BaseWebChromeClient) this.webView.getWebChromeClient()).onBack();
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public void onPause() {
        CookieUtil.compareCookieChanged(this.monitorCookie, this.url);
        if (this.useXBridge) {
            X5WebView x5WebView = this.webView;
            if (x5WebView != null) {
                x5WebView.onInactive();
            }
        } else {
            WebUtils.dispatchEvent(this, "ContainerInactive");
        }
        JDWebviewSslErrorDialogController.closeAllDialogs();
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.closePopup();
        }
    }

    @CallSuper
    protected void onPostInit() {
        JDAppearanceHelper jDAppearanceHelper;
        showLogLayout();
        showHybridLogLayout();
        if (this.webView != null && SwitchQueryFetcher.getSwitchBooleanValue("disable_third_cookie", false)) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this.webView, false);
        }
        if (this.canUseDarkMode && (jDAppearanceHelper = this.appearanceHelper) != null) {
            addJavascriptInterface(jDAppearanceHelper, jDAppearanceHelper.getName());
        }
        initHybridFunctions();
        if (WebDebug.report) {
            WebDebug.log("webview", "UserAgent:  " + getUaInfo(), getContext());
        }
        this.fixLifeCall = !SwitchQueryFetcher.getSwitchBooleanValue("wvFixLifeCall", false);
        if (this.webView != null) {
            boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("xbridge_ab", false);
            this.useXBridge = switchBooleanValue;
            this.webView.bindXBridge(switchBooleanValue);
        }
    }

    public void onRefreshComplete() {
        hideProgress();
        PullToRefreshX5WebView pullToRefreshX5WebView = this.ptrWebView;
        if (pullToRefreshX5WebView != null) {
            pullToRefreshX5WebView.onRefreshComplete();
        }
    }

    public void onResume() {
        XRenderManager xRenderManager;
        this.monitorCookie = CookieManager.getInstance().getCookie("jd.com");
        if (this.fixLifeCall) {
            onResumeNew();
            return;
        }
        if (this.useXBridge) {
            X5WebView x5WebView = this.webView;
            if (x5WebView != null) {
                x5WebView.onActive();
            }
        } else {
            WebUtils.dispatchEvent(this, PageLifeCycleEvent.STATE_ACTIVE);
        }
        if (this.isForegroundOrVisible) {
            return;
        }
        if (!this.useXBridge) {
            WebUtils.dispatchEvent(this, PageLifeCycleEvent.STATE_SHOW);
        }
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.STARTED);
        }
        this.isForegroundOrVisible = true;
        X5WebView x5WebView2 = this.webView;
        if (x5WebView2 != null) {
            x5WebView2.onResume();
            notifyWebViewVisible(true);
        }
        LifecycleRegistry lifecycleRegistry2 = this.lifecycleRegistry;
        if (lifecycleRegistry2 != null) {
            lifecycleRegistry2.markState(Lifecycle.State.RESUMED);
        }
        WebHybridLogView webHybridLogView = this.mWebHybridLogView;
        if (webHybridLogView != null) {
            webHybridLogView.onResume();
        }
        if (!this.isPreRender || (xRenderManager = this.xRenderManager) == null) {
            return;
        }
        xRenderManager.onResume();
    }

    public void onStop() {
        WebLogHelper webLogHelper;
        if (this.fixLifeCall) {
            onStopNew();
            return;
        }
        if (!this.useXBridge) {
            WebUtils.dispatchEvent(this, PageLifeCycleEvent.STATE_HIDE);
        }
        if (this.isForegroundOrVisible) {
            LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
            if (lifecycleRegistry != null) {
                lifecycleRegistry.markState(Lifecycle.State.STARTED);
            }
            this.isForegroundOrVisible = false;
            X5WebView x5WebView = this.webView;
            if (x5WebView != null) {
                x5WebView.onPause();
                notifyWebViewVisible(false);
            }
            if (this.enableWebLog && (webLogHelper = this.mWebLogHelper) != null) {
                webLogHelper.reportLogs();
            }
            reportPerformanceNow();
            LifecycleRegistry lifecycleRegistry2 = this.lifecycleRegistry;
            if (lifecycleRegistry2 != null) {
                lifecycleRegistry2.markState(Lifecycle.State.CREATED);
            }
        }
    }

    public void reSetRightTextView(String str) {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.reSetRightTextView(str);
        }
    }

    public void receivedTitle(String str) {
        if (Log.D || WebLogHelper.showXLog) {
            xLogD("onReceivedTitle() title -->> " + str);
        }
        if (this.isLocalHtmlScenario && WebUtils.isBlankUrl(str)) {
            return;
        }
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        TextView titleTextView = navigatorHolder != null ? navigatorHolder.getTitleTextView() : null;
        if (titleTextView == null || this.isTitleFixed) {
            return;
        }
        if (!TextUtils.isEmpty(str) && !str.startsWith("http")) {
            titleTextView.setText(StringUtil.cutAppointedLengthText(10, str));
        } else if (SwitchQueryFetcher.getSwitchBooleanValue("removeDefaultTitle", true)) {
            titleTextView.setText(LangUtils.SINGLE_SPACE);
        } else {
            titleTextView.setText(StringUtil.cutAppointedLengthText(10, R.string.app_name));
        }
        TitleChangeListener titleChangeListener = this.titleChangeListener;
        if (titleChangeListener != null) {
            titleChangeListener.onTitleChange(str);
        }
        if (this.isPreRender) {
            setShareBtnState(this.isNeedShare);
        }
    }

    public void recordMediaBack(Intent intent, boolean z, int i2, int i3) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.recordMediaBack(intent, z, i2, i3);
        }
    }

    public void refreshCartCount() {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.refreshCart(ShoppingBaseController.getProductCount());
        }
    }

    public void registerTitleThemeChangeListener() {
        this.navigatorHolder.registerTitleThemeChangeListener();
        setNaviBgCustomized(true);
        setNaviIconCustomized(true);
    }

    public void reload() {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.reload();
        }
    }

    public void removeWebViewScrollListener(WebViewScrollListener webViewScrollListener) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.removeWebViewScrollListener(webViewScrollListener);
        }
    }

    public void replaceUrl(String str) {
        replaceUrl(str, false);
    }

    public void reportInitPerformanceWithExtra(Map<String, Object> map) {
        reportInitPerformance(this.webView != null, map);
    }

    public void reportPerformanceNow() {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder != null) {
            webPerformanceHolder.reportAll();
        }
    }

    public void selectFileBack(Intent intent, int i2, int i3, boolean z) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.selectFileBack(intent, i2, i3, z);
        }
    }

    public void selectImageBack(Intent intent, int i2, int i3, boolean z) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.selectImageBack(intent, i2, i3, z);
        }
    }

    public void selectVideoBack(Intent intent, int i2, int i3, boolean z) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.selectVideoBack(intent, i2, i3, z);
        }
    }

    public void setCanPullRefresh(boolean z) {
        PullToRefreshX5WebView pullToRefreshX5WebView = this.ptrWebView;
        if (pullToRefreshX5WebView == null) {
            return;
        }
        if (z) {
            pullToRefreshX5WebView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        } else {
            pullToRefreshX5WebView.setMode(PullToRefreshBase.Mode.DISABLED);
        }
    }

    public void setCloseBtnVisible(boolean z) {
        NavigatorHolder navigatorHolder;
        if (!this.isUserCloseBtn || (navigatorHolder = this.navigatorHolder) == null) {
            return;
        }
        navigatorHolder.setCloseBtnVisible(z);
    }

    public void setCloseButtonListener(CloseButtonListener closeButtonListener) {
        this.closeButtonListener = closeButtonListener;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setCpsMtaData(Bundle bundle) {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            x5WebView.setCpsMtaData(bundle);
        }
    }

    public void setFixedTitle(String str) {
        this.isTitleFixed = true;
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        TextView titleTextView = navigatorHolder != null ? navigatorHolder.getTitleTextView() : null;
        if (titleTextView == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            titleTextView.setText(StringUtil.cutAppointedLengthText(10, str));
        } else {
            titleTextView.setText(StringUtil.cutAppointedLengthText(10, R.string.app_name));
        }
    }

    public void setGenTokenCallback(GenTokenCallback genTokenCallback) {
        this.genTokenCallback = genTokenCallback;
    }

    public void setImageProgress(int i2) {
        ImageView imageView;
        if (!this.needShowProgress || (imageView = this.progressImage) == null || this.isHideProgress) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = (int) (this.perWidth * i2);
        layoutParams.height = DPIUtil.dip2px(3.0f);
        this.progressImage.setLayoutParams(layoutParams);
        this.progressImage.invalidate();
    }

    public void setImmersive(boolean z) {
        setImmersive(z, null);
    }

    public void setImmersiveOnInit(boolean z) {
        if (SwitchQueryFetcher.getSwitchBooleanValue("immersiveOnInit", false)) {
            if (com.jd.libs.hybrid.base.util.Log.isDebug()) {
                XLog.d(this.TAG, "setImmersiveOnInit()");
            }
            this.shallCheckImmersiveOnInit = false;
            setImmersive(z, null);
        }
    }

    public void setInterceptTouchEventListener(InterceptTouchEventListener interceptTouchEventListener) {
        this.mInterceptTouchEventListener = interceptTouchEventListener;
    }

    public void setIsHideProgressForPrivacy(boolean z) {
        this.isHideProgress = z;
    }

    public void setIsMainFrameActivity(boolean z) {
        this.isMainFrameActivity = z;
    }

    public void setJDWebViewBuilder(JDWebViewBuilder jDWebViewBuilder) {
        if (jDWebViewBuilder != null) {
            this.url = jDWebViewBuilder.hybridUrl;
            this.shouldReportInitRecord = jDWebViewBuilder.shouldReportInitRecord;
            this.canUseDarkMode = jDWebViewBuilder.canUseDarkMode;
            this.enableHybrid = WebHybridUtils.hybridEnable && (WebHybridUtils.autoHybridInViewGlobal || jDWebViewBuilder.enableHybrid);
            this.hybridStarted = jDWebViewBuilder.hybridStarted;
            this.offlineId = jDWebViewBuilder.offlineId;
            String str = jDWebViewBuilder.preloadId;
            this.preloadId = str;
            this.dogId = str;
            this.isNeedShare = jDWebViewBuilder.isNeedShare;
            this.isPreRender = jDWebViewBuilder.isPreRender;
            this.autoSendReadyEvent = jDWebViewBuilder.autoSendReadyEvent;
            this.loadingPlaceHolder = jDWebViewBuilder.loadingPlaceHolder;
            this.showErrView = jDWebViewBuilder.showErrView;
            if (jDWebViewBuilder.extraSetting != null) {
                this.extraSetting = new ConcurrentHashMap<>(jDWebViewBuilder.extraSetting);
            }
        }
    }

    public void setLoadInterrupted(String str) {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder != null) {
            webPerformanceHolder.setFirstInterruptMsg(str);
            WebPerformance currentRecord = this.perfRecordHolder.getCurrentRecord();
            if (currentRecord != null) {
                currentRecord.setInterrupted(str);
            }
        }
    }

    public void setMRefreshCallback(final IWebUiBinder iWebUiBinder, final String str, final int i2) {
        PullToRefreshX5WebView pullToRefreshX5WebView = this.ptrWebView;
        if (pullToRefreshX5WebView != null) {
            pullToRefreshX5WebView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<X5WebView>() { // from class: com.jingdong.common.web.ui.JDWebView.19
                {
                    JDWebView.this = this;
                }

                @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
                public void onRefresh(PullToRefreshBase<X5WebView> pullToRefreshBase) {
                    JDWebView jDWebView = JDWebView.this;
                    int i3 = i2;
                    jDWebView.autoHidePullState(i3 > 0 ? i3 : 10000L);
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData(true));
                }
            });
        }
    }

    public void setMoreBtnVisible(boolean z) {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            if (z) {
                navigatorHolder.showMoreBtn();
            } else {
                navigatorHolder.removeMoreBtn();
            }
        }
    }

    public void setMsgRedPointNum(int i2, boolean z) {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setMsgRedPointNum(i2, z);
        }
    }

    public void setNaviBgCustomized(boolean z) {
        JDAppearanceHelper jDAppearanceHelper = this.appearanceHelper;
        if (jDAppearanceHelper != null) {
            jDAppearanceHelper.setNaviBackgroundCustomized(z);
        }
    }

    public void setNaviIconCustomized(boolean z) {
        JDAppearanceHelper jDAppearanceHelper = this.appearanceHelper;
        if (jDAppearanceHelper != null) {
            jDAppearanceHelper.setNaviIconStyleCustomized(z);
        }
    }

    public void setNeedShowProgress(boolean z) {
        RelativeLayout relativeLayout;
        this.needShowProgress = z;
        if (z || (relativeLayout = this.progressRelativeLayout) == null) {
            return;
        }
        relativeLayout.setVisibility(8);
    }

    public void setNormalNaviUIChange(String str) {
        String str2;
        final String str3;
        final String str4;
        if (this.isTopBarGone) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str4 = "";
            str2 = str4;
            str3 = str2;
        } else {
            try {
                JDJSONObject parseObject = JDJSON.parseObject(str);
                str2 = parseObject.optString("naviMenuType", "");
                try {
                    str3 = parseObject.optString("titleFirstImg", "");
                    try {
                        str4 = parseObject.optString("titleSecondImg", "");
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        str4 = "";
                        if (TextUtils.isEmpty(str3)) {
                            return;
                        }
                        return;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str3 = "";
                }
            } catch (Exception e4) {
                e = e4;
                str2 = "";
                str3 = str2;
            }
        }
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return;
        }
        this.preScrollState = -1;
        final int[] transferNaviIconAndTitleStyle = transferNaviIconAndTitleStyle(str2, false);
        setNaviIconCustomized(true);
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setNaviAndStatusBarHeightViewAlpha(1.0f);
        }
        NavigatorHolder navigatorHolder2 = this.navigatorHolder;
        int naviHeight = navigatorHolder2 != null ? navigatorHolder2.getNaviHeight() : 0;
        if (naviHeight <= 0) {
            naviHeight = NavigatorHolder.NAVI_BAR_HEIGHT;
            Context context = this.mContext;
            if (context != null && UnStatusBarTintUtil.isEnable(context)) {
                naviHeight += UnStatusBarTintUtil.getStatusBarHeight(this.mContext);
            }
        }
        RelativeLayout relativeLayout = this.webview_layout;
        if (relativeLayout != null) {
            relativeLayout.setPadding(relativeLayout.getPaddingLeft(), naviHeight, this.webview_layout.getPaddingRight(), this.webview_layout.getPaddingBottom());
        }
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            if (this.navigatorHolder != null) {
                if (x5WebView.getScrollY() < MAX_SCROLL_HEIGHT_ALPHA) {
                    this.preScrollState = 1;
                    this.navigatorHolder.updateNaviIconAndTitleColor(transferNaviIconAndTitleStyle[0]);
                    this.navigatorHolder.setTitleTextOrImg("", str3);
                } else {
                    this.preScrollState = 2;
                    this.navigatorHolder.updateNaviIconAndTitleColor(transferNaviIconAndTitleStyle[1]);
                    this.navigatorHolder.setTitleTextOrImg("", str4);
                }
            }
            this.webView.setWebViewScrollListener(new WebViewScrollListener() { // from class: com.jingdong.common.web.ui.JDWebView.12
                {
                    JDWebView.this = this;
                }

                @Override // com.jingdong.common.web.uilistener.WebViewScrollListener
                public void onScrollChanged(int i2, int i3, int i4, int i5) {
                    if (JDWebView.this.navigatorHolder != null) {
                        int i6 = JDWebView.MAX_SCROLL_HEIGHT_ALPHA;
                        if (i3 > i6) {
                            i3 = i6;
                        }
                        if (i3 <= 0 || i3 < i6) {
                            if (JDWebView.this.preScrollState == 1) {
                                return;
                            }
                            JDWebView.this.preScrollState = 1;
                            JDWebView.this.navigatorHolder.updateNaviIconAndTitleColor(transferNaviIconAndTitleStyle[0]);
                            JDWebView.this.navigatorHolder.changeTitleImgSmoothly(str3);
                        } else if (JDWebView.this.preScrollState == 2) {
                        } else {
                            JDWebView.this.preScrollState = 2;
                            JDWebView.this.navigatorHolder.updateNaviIconAndTitleColor(transferNaviIconAndTitleStyle[1]);
                            JDWebView.this.navigatorHolder.changeTitleImgSmoothly(str4);
                        }
                    }
                }
            });
        }
        setNeedShowProgress(true);
    }

    public void setPageType(String str) {
        WebPerformanceHolder webPerformanceHolder = this.perfRecordHolder;
        if (webPerformanceHolder != null) {
            webPerformanceHolder.setPageType(str);
        }
    }

    public void setPerformanceCallback(PerformanceCallback performanceCallback) {
        this.performanceCallback = performanceCallback;
    }

    public void setRedPointVisibility(boolean z) {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setRedPointVisibility(z);
        }
    }

    public void setRightTextViewState(boolean z) {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setRightTextViewState(z);
        }
    }

    public void setShareBtnState(boolean z) {
        setShareBtnState(z, false);
    }

    public void setStatusBarAlwaysTransparent(boolean z) {
        if (this.navigatorHolder == null || !UnStatusBarTintUtil.isEnable(this.mContext)) {
            return;
        }
        this.navigatorHolder.setStatusBarAlwaysTransparent(z);
    }

    public void setSwitchImmersiveOpen(boolean z) {
        this.switchImmersiveOpen = z;
    }

    public void setTimingReportEnable(boolean z, Performance performance2) {
        this.jsPerformance = performance2;
    }

    public void setTitleBackBtnVisible(boolean z) {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setTitleBackBtnVisible(z);
        }
    }

    public void setTitleBackListener(TitleBackListener titleBackListener) {
        this.titleBackListener = titleBackListener;
    }

    public void setTitleChangeListener(TitleChangeListener titleChangeListener) {
        this.titleChangeListener = titleChangeListener;
    }

    public void setTitleRightTextViewClickListener(TitleRightTextViewClickListener titleRightTextViewClickListener) {
        this.onRightTextViewClickListener = titleRightTextViewClickListener;
    }

    public void setTopBarGone(boolean z) {
        Context context;
        this.isTopBarGone = z;
        if (z) {
            ViewGroup viewGroup = this.titleLayout;
            if (viewGroup != null) {
                viewGroup.setVisibility(8);
            }
            RelativeLayout relativeLayout = this.webview_layout;
            if (relativeLayout != null) {
                relativeLayout.setPadding(relativeLayout.getPaddingLeft(), 0, this.webview_layout.getPaddingRight(), this.webview_layout.getPaddingBottom());
                return;
            }
            return;
        }
        ViewGroup viewGroup2 = this.titleLayout;
        if (viewGroup2 != null) {
            viewGroup2.setVisibility(0);
        }
        int i2 = NavigatorHolder.NAVI_BAR_HEIGHT;
        if (this.webview_layout == null || (context = this.mContext) == null) {
            return;
        }
        if (UnStatusBarTintUtil.isEnable(context)) {
            i2 += UnStatusBarTintUtil.getStatusBarHeight(this.mContext);
        }
        RelativeLayout relativeLayout2 = this.webview_layout;
        relativeLayout2.setPadding(relativeLayout2.getPaddingLeft(), i2, this.webview_layout.getPaddingRight(), this.webview_layout.getPaddingBottom());
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setUseCache(boolean z) {
        X5WebView x5WebView = this.webView;
        if (x5WebView == null) {
            return;
        }
        x5WebView.setUseCache(z);
    }

    public void setUseCloseBtn(boolean z) {
        this.isUserCloseBtn = z;
        if (z) {
            return;
        }
        setCloseBtnVisible(false);
    }

    public void setVisibleStatus(boolean z) {
        this.isForegroundOrVisible = z;
    }

    public void setWebBackListener(WebBackOrCloseListener webBackOrCloseListener) {
        this.webBackOrCloseListener = webBackOrCloseListener;
    }

    public void setWebViewClientListener(WebViewClientListener webViewClientListener) {
        this.webViewClientListener = webViewClientListener;
    }

    public void setWebViewInterceptUrlListener(IWebViewUrlInterceptor iWebViewUrlInterceptor) {
        this.webViewInterceptUrlListener = iWebViewUrlInterceptor;
    }

    public void setWebViewNaviListener(WebViewNaviListener webViewNaviListener) {
        this.webViewNaviListener = webViewNaviListener;
    }

    public void setXRenderPreload(String str) {
        this.preloadId = str;
    }

    public void setupShadowTrigger() {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.getTitleImg().setOnTouchListener(this.mTouchListener);
            this.navigatorHolder.getTitleTextView().setOnTouchListener(this.mTouchListener);
            this.navigatorHolder.getTitleImg().setOnClickListener(this.mListener);
            this.navigatorHolder.getTitleTextView().setOnClickListener(this.mListener);
        }
    }

    public void setxRenderUrl(String str) {
        this.xRenderUrl = str;
        getXRenderManager().setUrl(str);
    }

    public void showHybridLogLayout() {
        if (WebHybridLogView.showLog) {
            WebHybridLogView webHybridLogView = this.mWebHybridLogView;
            if (webHybridLogView == null) {
                this.mWebHybridLogView = new WebHybridLogView(getContext());
            } else {
                removeView(webHybridLogView);
            }
            addView(this.mWebHybridLogView, new ViewGroup.LayoutParams(-1, -1));
            HybridBackdoorLogger.setLogChangeListener(this.mWebHybridLogView);
            return;
        }
        WebHybridLogView webHybridLogView2 = this.mWebHybridLogView;
        if (webHybridLogView2 == null) {
            return;
        }
        removeView(webHybridLogView2);
        this.mWebHybridLogView = null;
        HybridBackdoorLogger.setLogChangeListener(null);
    }

    protected void showImageProgress() {
        if (!this.needShowProgress || this.isHideProgress) {
            return;
        }
        Log.d(this.TAG, "show image progress");
        RelativeLayout relativeLayout = this.progressRelativeLayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
        ImageView imageView = this.progressImage;
        if (imageView != null) {
            imageView.setBackgroundColor(Color.parseColor("#f02b2b"));
            this.progressImage.setVisibility(0);
        }
    }

    public void showLogLayout() {
        if (WebLogView.showLog) {
            if (this.mWebLogView == null) {
                this.mWebLogView = new WebLogView(getContext());
            }
            addView(this.mWebLogView, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        WebLogView webLogView = this.mWebLogView;
        if (webLogView == null) {
            return;
        }
        removeView(webLogView);
        this.mWebLogView = null;
    }

    public void showProgress() {
        View view = this.circleProgress;
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        this.circleProgress.setVisibility(0);
    }

    public void stopLoading() {
        X5WebView x5WebView = this.webView;
        if (x5WebView != null) {
            try {
                x5WebView.stopLoading();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void xLogD(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        XLog.d(this.TAG, null, str, "webview");
    }

    public void xLogE(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        XLog.e(this.TAG, null, str, "webview");
        com.jd.libs.xdog.b.h(str + "\u6392\u67e5\u94fe\u63a5: https://cf.jd.com/pages/viewpage.action?pageId=307547104");
    }

    public String getUrlHistory(int i2) {
        int size;
        int i3;
        try {
            LinkedList<String> linkedList = this.urlHistory;
            if (linkedList != null && i2 < (size = linkedList.size()) && (i3 = size + i2) >= 0) {
                return i2 >= 0 ? this.urlHistory.get(i2) : this.urlHistory.get(i3);
            }
            return null;
        } catch (Exception e2) {
            Log.e(this.TAG, e2.getMessage(), e2);
            return null;
        }
    }

    public void injectJs(final String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.10
            {
                JDWebView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                String trim = str.trim();
                if (!trim.startsWith("javascript:")) {
                    trim = "javascript:" + trim;
                }
                if (!trim.endsWith(";")) {
                    trim = trim + ";";
                }
                if (Log.D || WebLogHelper.showXLog) {
                    JDWebView.this.xLogD("injectJs:" + trim);
                }
                try {
                    X5WebView x5WebView = JDWebView.this.webView;
                    if (x5WebView != null) {
                        x5WebView.loadUrl(trim);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, j2);
    }

    public void replaceUrl(final String str, boolean z) {
        if (TextUtils.isEmpty(str) || getWebView() == null) {
            return;
        }
        if (z && SwitchQueryFetcher.getSwitchBooleanValue("fixUaChangeIssue", false)) {
            if (Build.VERSION.SDK_INT >= 19) {
                WebUtils.runOnMain(new Runnable() { // from class: com.jingdong.common.web.ui.g
                    {
                        JDWebView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        JDWebView.this.l(str);
                    }
                });
            } else {
                loadUrl(str);
            }
        } else if (SwitchQueryFetcher.getSwitchBooleanValue("replaceTTTNewRoute", false)) {
            loadUrl(str);
        } else {
            WebUtils.runOnMain(new Runnable() { // from class: com.jingdong.common.web.ui.m
                {
                    JDWebView.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    JDWebView.this.n(str);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setImmersive(boolean r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.ui.JDWebView.setImmersive(boolean, java.lang.String):boolean");
    }

    public void setShareBtnState(boolean z, boolean z2) {
        NavigatorHolder navigatorHolder = this.navigatorHolder;
        if (navigatorHolder != null) {
            navigatorHolder.setShareBtnState(z, z2);
        }
    }

    public void showErrView(int i2, String str, final ErrCallback errCallback) {
        if (this.showErrView) {
            View view = this.circleProgress;
            if (view != null) {
                view.setVisibility(8);
            }
            if (this.errView == null) {
                this.errView = new XWebErrView(getContext());
            }
            this.errView.setOnButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.ui.j
                {
                    JDWebView.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    JDWebView.this.p(errCallback, view2);
                }
            });
            this.errView.setErrCode(i2);
            if (this.webview_layout == null || this.errView.getParent() != null) {
                return;
            }
            this.webview_layout.addView(this.errView, new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    public void customListenDarkMode(boolean z, boolean z2) {
        JDAppearanceHelper jDAppearanceHelper = this.appearanceHelper;
        if (jDAppearanceHelper != null) {
            jDAppearanceHelper.setCustomListenDarkMode(z, z2);
        }
    }

    public void setMsgRedPointNum(int i2) {
        setMsgRedPointNum(i2, true);
    }

    public void enableHybrid(String str, String str2) {
        if (!WebHybridUtils.hybridEnable) {
            if (Log.D) {
                Log.d(this.TAG, "Hybrid is disable now.");
            }
        } else if (this.loadStart > 0) {
            if (Log.D) {
                Log.d(this.TAG, "cannot enable hybrid after loading started.");
            }
        } else if (this.hybridStarted) {
            if (Log.D) {
                Log.d(this.TAG, "cannot enable hybrid after hybrid started pre-loading.");
            }
        } else {
            this.enableHybrid = true;
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                return;
            }
            this.offlineId = str;
            this.preloadId = str2;
            hybridLoad();
            initHybridFunctions();
        }
    }

    public JDWebView(Context context, JDWebViewBuilder jDWebViewBuilder) {
        super(context);
        this.TAG = "JDWebView";
        this.currentTimeInitStart = System.currentTimeMillis();
        this.shouldReportInitRecord = true;
        this.initRecordReported = false;
        this.isMainFrameActivity = false;
        this.isTitleFixed = false;
        this.needShowProgress = true;
        this.isPageLoaded = false;
        this.enableWebLog = WebLogHelper.sJsReportLevel >= 0;
        this.isUserCloseBtn = true;
        this.perWidth = DPIUtil.getWidth() / 10000.0f;
        this.closeWhenNative = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isTopBarGone = false;
        this.switchImmersiveOpen = true;
        this.isForegroundOrVisible = false;
        this.isFirstH5Page = true;
        this.preScrollState = -1;
        this.canUseDarkMode = false;
        this.enableHybrid = false;
        this.hybridStarted = false;
        this.onCommitHideProgress = false;
        this.fixLifeCall = true;
        this.useXBridge = false;
        this.loadingPlaceHolder = null;
        this.showErrView = false;
        this.xDogRegistered = false;
        this.xWidgetAvailable = false;
        this.df = new DecimalFormat("#.###");
        this.whiteScreenEnable = false;
        this.blankThreshold = 0.95d;
        this.isFirstWhiteRenderRatio = false;
        this.monitorCookie = null;
        this.isError = false;
        this.backToLocalHtml = false;
        this.ignoreShouldOverrideUrl = false;
        this.shallCheckImmersiveOnInit = true;
        this.lazyHybrid = false;
        this.endTime = 0L;
        this.startTime = 0L;
        this.hasLongClick = false;
        this.mHits = new long[7];
        this.mTouchListener = new View.OnTouchListener() { // from class: com.jingdong.common.web.ui.JDWebView.13
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    JDWebView.this.endTime = 0L;
                    JDWebView.this.startTime = SystemClock.uptimeMillis();
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    JDWebView.this.endTime = SystemClock.uptimeMillis();
                    if (JDWebView.this.endTime - JDWebView.this.startTime >= Final.FIVE_SECOND) {
                        JDWebView.this.hasLongClick = true;
                        return false;
                    }
                    return false;
                }
            }
        };
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.web.ui.JDWebView.14
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDWebView.this.hasLongClick) {
                    System.arraycopy(JDWebView.this.mHits, 1, JDWebView.this.mHits, 0, JDWebView.this.mHits.length - 1);
                    JDWebView.this.mHits[JDWebView.this.mHits.length - 1] = SystemClock.uptimeMillis();
                    if (JDWebView.this.mHits[JDWebView.this.mHits.length - 2] != 0 && JDWebView.this.mHits[JDWebView.this.mHits.length - 1] - JDWebView.this.mHits[JDWebView.this.mHits.length - 2] > 500) {
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                    }
                }
                if (JDWebView.this.hasLongClick) {
                    if (JDWebView.this.mHits[0] != 0) {
                        if (JDWebView.this.navigatorHolder != null) {
                            JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(false);
                            JDWebView.this.navigatorHolder.getTitleImg().setEnabled(false);
                        }
                        JDWebView.this.addView(new SecretDoor(JDWebView.this.mContext, JDWebView.this), new ViewGroup.LayoutParams(-1, -1));
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                        JDWebView.this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.14.1
                            {
                                AnonymousClass14.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (JDWebView.this.navigatorHolder != null) {
                                    JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(true);
                                    JDWebView.this.navigatorHolder.getTitleImg().setEnabled(true);
                                }
                            }
                        }, 1000L);
                    }
                } else if (JDWebView.this.preScrollState <= 0) {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked()");
                } else {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked(" + JDWebView.this.preScrollState + ")");
                }
            }
        };
        this.naviListener = new CommonNavigator.NaviClickAdaper() { // from class: com.jingdong.common.web.ui.JDWebView.15
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCalendar() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCalendar();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCart() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickClose() {
                if (JDWebView.this.closeButtonListener != null) {
                    JDWebView.this.closeButtonListener.close();
                } else {
                    JDWebView.this.stopLoading();
                    if (JDWebView.this.webBackOrCloseListener != null) {
                        JDWebView.this.webBackOrCloseListener.onWebClose();
                    }
                    ((Activity) JDWebView.this.mContext).finish();
                }
                JDMtaUtils.onClick(JDWebView.this.getContext(), "MWebview_CloseButton", JDWebView.this.getContext().getClass().getName(), "", JDWebView.this.finalUrl);
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCustom(String str) {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickHome() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMore() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMore();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMsg() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCart() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCustom(String str) {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopFeedback() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopFeedback();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopHome() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopMsg() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopSearch() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopShare() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickSearch() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickShare() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickTitleBack() {
                JDMtaUtils.onClick(JDWebView.this.mContext, "MWebview_BackForward", "", JDWebView.this.finalUrl);
                if (JDWebView.this.titleBackListener != null) {
                    JDWebView.this.titleBackListener.titleBack();
                } else {
                    JDWebView.this.backOrClose();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onRightTextView() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.onRightTextViewClickListener != null) {
                    JDWebView.this.onRightTextViewClickListener.onRightTextViewClickListener(null);
                }
            }
        };
        this.followBtnAdapter = new NavigatorHolder.BaseNaviFollowAdapter() { // from class: com.jingdong.common.web.ui.JDWebView.16
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onChannelIdChanged(@Nullable View view, String str) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).setChannelId(str, getStyle() != 2);
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            @Nullable
            public View onCreateFollowView(int i2, int i3, int i4) {
                if (JDWebView.this.isTopBarGone()) {
                    return null;
                }
                CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(JDWebView.this.mContext);
                customChannelFollowView.resetFollowViewWidthAndHeight(i2, i3);
                customChannelFollowView.changeIcon(i4 != 2);
                return customChannelFollowView;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onStyleChanged(View view, int i2) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).changeIcon(i2 != 2);
            }
        };
        setJDWebViewBuilder(jDWebViewBuilder);
        init(context);
    }

    @Deprecated
    public JDWebView(Context context, boolean z) {
        super(context);
        this.TAG = "JDWebView";
        this.currentTimeInitStart = System.currentTimeMillis();
        this.shouldReportInitRecord = true;
        this.initRecordReported = false;
        this.isMainFrameActivity = false;
        this.isTitleFixed = false;
        this.needShowProgress = true;
        this.isPageLoaded = false;
        this.enableWebLog = WebLogHelper.sJsReportLevel >= 0;
        this.isUserCloseBtn = true;
        this.perWidth = DPIUtil.getWidth() / 10000.0f;
        this.closeWhenNative = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isTopBarGone = false;
        this.switchImmersiveOpen = true;
        this.isForegroundOrVisible = false;
        this.isFirstH5Page = true;
        this.preScrollState = -1;
        this.canUseDarkMode = false;
        this.enableHybrid = false;
        this.hybridStarted = false;
        this.onCommitHideProgress = false;
        this.fixLifeCall = true;
        this.useXBridge = false;
        this.loadingPlaceHolder = null;
        this.showErrView = false;
        this.xDogRegistered = false;
        this.xWidgetAvailable = false;
        this.df = new DecimalFormat("#.###");
        this.whiteScreenEnable = false;
        this.blankThreshold = 0.95d;
        this.isFirstWhiteRenderRatio = false;
        this.monitorCookie = null;
        this.isError = false;
        this.backToLocalHtml = false;
        this.ignoreShouldOverrideUrl = false;
        this.shallCheckImmersiveOnInit = true;
        this.lazyHybrid = false;
        this.endTime = 0L;
        this.startTime = 0L;
        this.hasLongClick = false;
        this.mHits = new long[7];
        this.mTouchListener = new View.OnTouchListener() { // from class: com.jingdong.common.web.ui.JDWebView.13
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    JDWebView.this.endTime = 0L;
                    JDWebView.this.startTime = SystemClock.uptimeMillis();
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    JDWebView.this.endTime = SystemClock.uptimeMillis();
                    if (JDWebView.this.endTime - JDWebView.this.startTime >= Final.FIVE_SECOND) {
                        JDWebView.this.hasLongClick = true;
                        return false;
                    }
                    return false;
                }
            }
        };
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.web.ui.JDWebView.14
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDWebView.this.hasLongClick) {
                    System.arraycopy(JDWebView.this.mHits, 1, JDWebView.this.mHits, 0, JDWebView.this.mHits.length - 1);
                    JDWebView.this.mHits[JDWebView.this.mHits.length - 1] = SystemClock.uptimeMillis();
                    if (JDWebView.this.mHits[JDWebView.this.mHits.length - 2] != 0 && JDWebView.this.mHits[JDWebView.this.mHits.length - 1] - JDWebView.this.mHits[JDWebView.this.mHits.length - 2] > 500) {
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                    }
                }
                if (JDWebView.this.hasLongClick) {
                    if (JDWebView.this.mHits[0] != 0) {
                        if (JDWebView.this.navigatorHolder != null) {
                            JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(false);
                            JDWebView.this.navigatorHolder.getTitleImg().setEnabled(false);
                        }
                        JDWebView.this.addView(new SecretDoor(JDWebView.this.mContext, JDWebView.this), new ViewGroup.LayoutParams(-1, -1));
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                        JDWebView.this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.14.1
                            {
                                AnonymousClass14.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (JDWebView.this.navigatorHolder != null) {
                                    JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(true);
                                    JDWebView.this.navigatorHolder.getTitleImg().setEnabled(true);
                                }
                            }
                        }, 1000L);
                    }
                } else if (JDWebView.this.preScrollState <= 0) {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked()");
                } else {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked(" + JDWebView.this.preScrollState + ")");
                }
            }
        };
        this.naviListener = new CommonNavigator.NaviClickAdaper() { // from class: com.jingdong.common.web.ui.JDWebView.15
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCalendar() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCalendar();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCart() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickClose() {
                if (JDWebView.this.closeButtonListener != null) {
                    JDWebView.this.closeButtonListener.close();
                } else {
                    JDWebView.this.stopLoading();
                    if (JDWebView.this.webBackOrCloseListener != null) {
                        JDWebView.this.webBackOrCloseListener.onWebClose();
                    }
                    ((Activity) JDWebView.this.mContext).finish();
                }
                JDMtaUtils.onClick(JDWebView.this.getContext(), "MWebview_CloseButton", JDWebView.this.getContext().getClass().getName(), "", JDWebView.this.finalUrl);
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCustom(String str) {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickHome() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMore() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMore();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMsg() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCart() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCustom(String str) {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopFeedback() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopFeedback();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopHome() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopMsg() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopSearch() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopShare() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickSearch() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickShare() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickTitleBack() {
                JDMtaUtils.onClick(JDWebView.this.mContext, "MWebview_BackForward", "", JDWebView.this.finalUrl);
                if (JDWebView.this.titleBackListener != null) {
                    JDWebView.this.titleBackListener.titleBack();
                } else {
                    JDWebView.this.backOrClose();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onRightTextView() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.onRightTextViewClickListener != null) {
                    JDWebView.this.onRightTextViewClickListener.onRightTextViewClickListener(null);
                }
            }
        };
        this.followBtnAdapter = new NavigatorHolder.BaseNaviFollowAdapter() { // from class: com.jingdong.common.web.ui.JDWebView.16
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onChannelIdChanged(@Nullable View view, String str) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).setChannelId(str, getStyle() != 2);
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            @Nullable
            public View onCreateFollowView(int i2, int i3, int i4) {
                if (JDWebView.this.isTopBarGone()) {
                    return null;
                }
                CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(JDWebView.this.mContext);
                customChannelFollowView.resetFollowViewWidthAndHeight(i2, i3);
                customChannelFollowView.changeIcon(i4 != 2);
                return customChannelFollowView;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onStyleChanged(View view, int i2) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).changeIcon(i2 != 2);
            }
        };
        this.canUseDarkMode = z;
        init(context);
    }

    @Deprecated
    public JDWebView(Context context, boolean z, boolean z2) {
        super(context);
        this.TAG = "JDWebView";
        this.currentTimeInitStart = System.currentTimeMillis();
        this.shouldReportInitRecord = true;
        this.initRecordReported = false;
        this.isMainFrameActivity = false;
        this.isTitleFixed = false;
        this.needShowProgress = true;
        this.isPageLoaded = false;
        this.enableWebLog = WebLogHelper.sJsReportLevel >= 0;
        this.isUserCloseBtn = true;
        this.perWidth = DPIUtil.getWidth() / 10000.0f;
        this.closeWhenNative = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isTopBarGone = false;
        this.switchImmersiveOpen = true;
        this.isForegroundOrVisible = false;
        this.isFirstH5Page = true;
        this.preScrollState = -1;
        this.canUseDarkMode = false;
        this.enableHybrid = false;
        this.hybridStarted = false;
        this.onCommitHideProgress = false;
        this.fixLifeCall = true;
        this.useXBridge = false;
        this.loadingPlaceHolder = null;
        this.showErrView = false;
        this.xDogRegistered = false;
        this.xWidgetAvailable = false;
        this.df = new DecimalFormat("#.###");
        this.whiteScreenEnable = false;
        this.blankThreshold = 0.95d;
        this.isFirstWhiteRenderRatio = false;
        this.monitorCookie = null;
        this.isError = false;
        this.backToLocalHtml = false;
        this.ignoreShouldOverrideUrl = false;
        this.shallCheckImmersiveOnInit = true;
        this.lazyHybrid = false;
        this.endTime = 0L;
        this.startTime = 0L;
        this.hasLongClick = false;
        this.mHits = new long[7];
        this.mTouchListener = new View.OnTouchListener() { // from class: com.jingdong.common.web.ui.JDWebView.13
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    JDWebView.this.endTime = 0L;
                    JDWebView.this.startTime = SystemClock.uptimeMillis();
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    JDWebView.this.endTime = SystemClock.uptimeMillis();
                    if (JDWebView.this.endTime - JDWebView.this.startTime >= Final.FIVE_SECOND) {
                        JDWebView.this.hasLongClick = true;
                        return false;
                    }
                    return false;
                }
            }
        };
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.web.ui.JDWebView.14
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDWebView.this.hasLongClick) {
                    System.arraycopy(JDWebView.this.mHits, 1, JDWebView.this.mHits, 0, JDWebView.this.mHits.length - 1);
                    JDWebView.this.mHits[JDWebView.this.mHits.length - 1] = SystemClock.uptimeMillis();
                    if (JDWebView.this.mHits[JDWebView.this.mHits.length - 2] != 0 && JDWebView.this.mHits[JDWebView.this.mHits.length - 1] - JDWebView.this.mHits[JDWebView.this.mHits.length - 2] > 500) {
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                    }
                }
                if (JDWebView.this.hasLongClick) {
                    if (JDWebView.this.mHits[0] != 0) {
                        if (JDWebView.this.navigatorHolder != null) {
                            JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(false);
                            JDWebView.this.navigatorHolder.getTitleImg().setEnabled(false);
                        }
                        JDWebView.this.addView(new SecretDoor(JDWebView.this.mContext, JDWebView.this), new ViewGroup.LayoutParams(-1, -1));
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                        JDWebView.this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.14.1
                            {
                                AnonymousClass14.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (JDWebView.this.navigatorHolder != null) {
                                    JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(true);
                                    JDWebView.this.navigatorHolder.getTitleImg().setEnabled(true);
                                }
                            }
                        }, 1000L);
                    }
                } else if (JDWebView.this.preScrollState <= 0) {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked()");
                } else {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked(" + JDWebView.this.preScrollState + ")");
                }
            }
        };
        this.naviListener = new CommonNavigator.NaviClickAdaper() { // from class: com.jingdong.common.web.ui.JDWebView.15
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCalendar() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCalendar();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCart() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickClose() {
                if (JDWebView.this.closeButtonListener != null) {
                    JDWebView.this.closeButtonListener.close();
                } else {
                    JDWebView.this.stopLoading();
                    if (JDWebView.this.webBackOrCloseListener != null) {
                        JDWebView.this.webBackOrCloseListener.onWebClose();
                    }
                    ((Activity) JDWebView.this.mContext).finish();
                }
                JDMtaUtils.onClick(JDWebView.this.getContext(), "MWebview_CloseButton", JDWebView.this.getContext().getClass().getName(), "", JDWebView.this.finalUrl);
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCustom(String str) {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickHome() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMore() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMore();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMsg() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCart() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCustom(String str) {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopFeedback() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopFeedback();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopHome() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopMsg() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopSearch() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopShare() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickSearch() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickShare() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickTitleBack() {
                JDMtaUtils.onClick(JDWebView.this.mContext, "MWebview_BackForward", "", JDWebView.this.finalUrl);
                if (JDWebView.this.titleBackListener != null) {
                    JDWebView.this.titleBackListener.titleBack();
                } else {
                    JDWebView.this.backOrClose();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onRightTextView() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.onRightTextViewClickListener != null) {
                    JDWebView.this.onRightTextViewClickListener.onRightTextViewClickListener(null);
                }
            }
        };
        this.followBtnAdapter = new NavigatorHolder.BaseNaviFollowAdapter() { // from class: com.jingdong.common.web.ui.JDWebView.16
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onChannelIdChanged(@Nullable View view, String str) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).setChannelId(str, getStyle() != 2);
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            @Nullable
            public View onCreateFollowView(int i2, int i3, int i4) {
                if (JDWebView.this.isTopBarGone()) {
                    return null;
                }
                CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(JDWebView.this.mContext);
                customChannelFollowView.resetFollowViewWidthAndHeight(i2, i3);
                customChannelFollowView.changeIcon(i4 != 2);
                return customChannelFollowView;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onStyleChanged(View view, int i2) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).changeIcon(i2 != 2);
            }
        };
        this.shouldReportInitRecord = z;
        this.canUseDarkMode = z2;
        init(context);
    }

    public JDWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "JDWebView";
        this.currentTimeInitStart = System.currentTimeMillis();
        this.shouldReportInitRecord = true;
        this.initRecordReported = false;
        this.isMainFrameActivity = false;
        this.isTitleFixed = false;
        this.needShowProgress = true;
        this.isPageLoaded = false;
        this.enableWebLog = WebLogHelper.sJsReportLevel >= 0;
        this.isUserCloseBtn = true;
        this.perWidth = DPIUtil.getWidth() / 10000.0f;
        this.closeWhenNative = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isTopBarGone = false;
        this.switchImmersiveOpen = true;
        this.isForegroundOrVisible = false;
        this.isFirstH5Page = true;
        this.preScrollState = -1;
        this.canUseDarkMode = false;
        this.enableHybrid = false;
        this.hybridStarted = false;
        this.onCommitHideProgress = false;
        this.fixLifeCall = true;
        this.useXBridge = false;
        this.loadingPlaceHolder = null;
        this.showErrView = false;
        this.xDogRegistered = false;
        this.xWidgetAvailable = false;
        this.df = new DecimalFormat("#.###");
        this.whiteScreenEnable = false;
        this.blankThreshold = 0.95d;
        this.isFirstWhiteRenderRatio = false;
        this.monitorCookie = null;
        this.isError = false;
        this.backToLocalHtml = false;
        this.ignoreShouldOverrideUrl = false;
        this.shallCheckImmersiveOnInit = true;
        this.lazyHybrid = false;
        this.endTime = 0L;
        this.startTime = 0L;
        this.hasLongClick = false;
        this.mHits = new long[7];
        this.mTouchListener = new View.OnTouchListener() { // from class: com.jingdong.common.web.ui.JDWebView.13
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    JDWebView.this.endTime = 0L;
                    JDWebView.this.startTime = SystemClock.uptimeMillis();
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    JDWebView.this.endTime = SystemClock.uptimeMillis();
                    if (JDWebView.this.endTime - JDWebView.this.startTime >= Final.FIVE_SECOND) {
                        JDWebView.this.hasLongClick = true;
                        return false;
                    }
                    return false;
                }
            }
        };
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.web.ui.JDWebView.14
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDWebView.this.hasLongClick) {
                    System.arraycopy(JDWebView.this.mHits, 1, JDWebView.this.mHits, 0, JDWebView.this.mHits.length - 1);
                    JDWebView.this.mHits[JDWebView.this.mHits.length - 1] = SystemClock.uptimeMillis();
                    if (JDWebView.this.mHits[JDWebView.this.mHits.length - 2] != 0 && JDWebView.this.mHits[JDWebView.this.mHits.length - 1] - JDWebView.this.mHits[JDWebView.this.mHits.length - 2] > 500) {
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                    }
                }
                if (JDWebView.this.hasLongClick) {
                    if (JDWebView.this.mHits[0] != 0) {
                        if (JDWebView.this.navigatorHolder != null) {
                            JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(false);
                            JDWebView.this.navigatorHolder.getTitleImg().setEnabled(false);
                        }
                        JDWebView.this.addView(new SecretDoor(JDWebView.this.mContext, JDWebView.this), new ViewGroup.LayoutParams(-1, -1));
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                        JDWebView.this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.14.1
                            {
                                AnonymousClass14.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (JDWebView.this.navigatorHolder != null) {
                                    JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(true);
                                    JDWebView.this.navigatorHolder.getTitleImg().setEnabled(true);
                                }
                            }
                        }, 1000L);
                    }
                } else if (JDWebView.this.preScrollState <= 0) {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked()");
                } else {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked(" + JDWebView.this.preScrollState + ")");
                }
            }
        };
        this.naviListener = new CommonNavigator.NaviClickAdaper() { // from class: com.jingdong.common.web.ui.JDWebView.15
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCalendar() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCalendar();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCart() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickClose() {
                if (JDWebView.this.closeButtonListener != null) {
                    JDWebView.this.closeButtonListener.close();
                } else {
                    JDWebView.this.stopLoading();
                    if (JDWebView.this.webBackOrCloseListener != null) {
                        JDWebView.this.webBackOrCloseListener.onWebClose();
                    }
                    ((Activity) JDWebView.this.mContext).finish();
                }
                JDMtaUtils.onClick(JDWebView.this.getContext(), "MWebview_CloseButton", JDWebView.this.getContext().getClass().getName(), "", JDWebView.this.finalUrl);
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCustom(String str) {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickHome() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMore() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMore();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMsg() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCart() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCustom(String str) {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopFeedback() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopFeedback();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopHome() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopMsg() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopSearch() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopShare() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickSearch() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickShare() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickTitleBack() {
                JDMtaUtils.onClick(JDWebView.this.mContext, "MWebview_BackForward", "", JDWebView.this.finalUrl);
                if (JDWebView.this.titleBackListener != null) {
                    JDWebView.this.titleBackListener.titleBack();
                } else {
                    JDWebView.this.backOrClose();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onRightTextView() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.onRightTextViewClickListener != null) {
                    JDWebView.this.onRightTextViewClickListener.onRightTextViewClickListener(null);
                }
            }
        };
        this.followBtnAdapter = new NavigatorHolder.BaseNaviFollowAdapter() { // from class: com.jingdong.common.web.ui.JDWebView.16
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onChannelIdChanged(@Nullable View view, String str) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).setChannelId(str, getStyle() != 2);
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            @Nullable
            public View onCreateFollowView(int i2, int i3, int i4) {
                if (JDWebView.this.isTopBarGone()) {
                    return null;
                }
                CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(JDWebView.this.mContext);
                customChannelFollowView.resetFollowViewWidthAndHeight(i2, i3);
                customChannelFollowView.changeIcon(i4 != 2);
                return customChannelFollowView;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onStyleChanged(View view, int i2) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).changeIcon(i2 != 2);
            }
        };
        init(context);
    }

    public JDWebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "JDWebView";
        this.currentTimeInitStart = System.currentTimeMillis();
        this.shouldReportInitRecord = true;
        this.initRecordReported = false;
        this.isMainFrameActivity = false;
        this.isTitleFixed = false;
        this.needShowProgress = true;
        this.isPageLoaded = false;
        this.enableWebLog = WebLogHelper.sJsReportLevel >= 0;
        this.isUserCloseBtn = true;
        this.perWidth = DPIUtil.getWidth() / 10000.0f;
        this.closeWhenNative = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isTopBarGone = false;
        this.switchImmersiveOpen = true;
        this.isForegroundOrVisible = false;
        this.isFirstH5Page = true;
        this.preScrollState = -1;
        this.canUseDarkMode = false;
        this.enableHybrid = false;
        this.hybridStarted = false;
        this.onCommitHideProgress = false;
        this.fixLifeCall = true;
        this.useXBridge = false;
        this.loadingPlaceHolder = null;
        this.showErrView = false;
        this.xDogRegistered = false;
        this.xWidgetAvailable = false;
        this.df = new DecimalFormat("#.###");
        this.whiteScreenEnable = false;
        this.blankThreshold = 0.95d;
        this.isFirstWhiteRenderRatio = false;
        this.monitorCookie = null;
        this.isError = false;
        this.backToLocalHtml = false;
        this.ignoreShouldOverrideUrl = false;
        this.shallCheckImmersiveOnInit = true;
        this.lazyHybrid = false;
        this.endTime = 0L;
        this.startTime = 0L;
        this.hasLongClick = false;
        this.mHits = new long[7];
        this.mTouchListener = new View.OnTouchListener() { // from class: com.jingdong.common.web.ui.JDWebView.13
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    JDWebView.this.endTime = 0L;
                    JDWebView.this.startTime = SystemClock.uptimeMillis();
                    return false;
                } else if (action != 1) {
                    return false;
                } else {
                    JDWebView.this.endTime = SystemClock.uptimeMillis();
                    if (JDWebView.this.endTime - JDWebView.this.startTime >= Final.FIVE_SECOND) {
                        JDWebView.this.hasLongClick = true;
                        return false;
                    }
                    return false;
                }
            }
        };
        this.mListener = new View.OnClickListener() { // from class: com.jingdong.common.web.ui.JDWebView.14
            {
                JDWebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDWebView.this.hasLongClick) {
                    System.arraycopy(JDWebView.this.mHits, 1, JDWebView.this.mHits, 0, JDWebView.this.mHits.length - 1);
                    JDWebView.this.mHits[JDWebView.this.mHits.length - 1] = SystemClock.uptimeMillis();
                    if (JDWebView.this.mHits[JDWebView.this.mHits.length - 2] != 0 && JDWebView.this.mHits[JDWebView.this.mHits.length - 1] - JDWebView.this.mHits[JDWebView.this.mHits.length - 2] > 500) {
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                    }
                }
                if (JDWebView.this.hasLongClick) {
                    if (JDWebView.this.mHits[0] != 0) {
                        if (JDWebView.this.navigatorHolder != null) {
                            JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(false);
                            JDWebView.this.navigatorHolder.getTitleImg().setEnabled(false);
                        }
                        JDWebView.this.addView(new SecretDoor(JDWebView.this.mContext, JDWebView.this), new ViewGroup.LayoutParams(-1, -1));
                        JDWebView.this.hasLongClick = false;
                        JDWebView.this.clearMHits();
                        JDWebView.this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.JDWebView.14.1
                            {
                                AnonymousClass14.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (JDWebView.this.navigatorHolder != null) {
                                    JDWebView.this.navigatorHolder.getTitleTextView().setEnabled(true);
                                    JDWebView.this.navigatorHolder.getTitleImg().setEnabled(true);
                                }
                            }
                        }, 1000L);
                    }
                } else if (JDWebView.this.preScrollState <= 0) {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked()");
                } else {
                    JDWebView.this.injectJs("javascript:window.headTitleClicked&&headTitleClicked(" + JDWebView.this.preScrollState + ")");
                }
            }
        };
        this.naviListener = new CommonNavigator.NaviClickAdaper() { // from class: com.jingdong.common.web.ui.JDWebView.15
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCalendar() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCalendar();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCart() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickClose() {
                if (JDWebView.this.closeButtonListener != null) {
                    JDWebView.this.closeButtonListener.close();
                } else {
                    JDWebView.this.stopLoading();
                    if (JDWebView.this.webBackOrCloseListener != null) {
                        JDWebView.this.webBackOrCloseListener.onWebClose();
                    }
                    ((Activity) JDWebView.this.mContext).finish();
                }
                JDMtaUtils.onClick(JDWebView.this.getContext(), "MWebview_CloseButton", JDWebView.this.getContext().getClass().getName(), "", JDWebView.this.finalUrl);
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickCustom(String str) {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickHome() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMore() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMore();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickMsg() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCart() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCart();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopCustom(String str) {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopCustom(str);
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopFeedback() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopFeedback();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopHome() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopHome();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopMsg() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopMsg();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopSearch() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickPopShare() {
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickPopShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickSearch() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickSearch();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickShare() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.webViewNaviListener != null) {
                    JDWebView.this.webViewNaviListener.onClickShare();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onClickTitleBack() {
                JDMtaUtils.onClick(JDWebView.this.mContext, "MWebview_BackForward", "", JDWebView.this.finalUrl);
                if (JDWebView.this.titleBackListener != null) {
                    JDWebView.this.titleBackListener.titleBack();
                } else {
                    JDWebView.this.backOrClose();
                }
            }

            @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
            public void onRightTextView() {
                JDWebView.this.hideSoftInput();
                if (JDWebView.this.onRightTextViewClickListener != null) {
                    JDWebView.this.onRightTextViewClickListener.onRightTextViewClickListener(null);
                }
            }
        };
        this.followBtnAdapter = new NavigatorHolder.BaseNaviFollowAdapter() { // from class: com.jingdong.common.web.ui.JDWebView.16
            {
                JDWebView.this = this;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onChannelIdChanged(@Nullable View view, String str) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).setChannelId(str, getStyle() != 2);
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            @Nullable
            public View onCreateFollowView(int i22, int i3, int i4) {
                if (JDWebView.this.isTopBarGone()) {
                    return null;
                }
                CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(JDWebView.this.mContext);
                customChannelFollowView.resetFollowViewWidthAndHeight(i22, i3);
                customChannelFollowView.changeIcon(i4 != 2);
                return customChannelFollowView;
            }

            @Override // com.jingdong.common.widget.NavigatorHolder.BaseNaviFollowAdapter
            public void onStyleChanged(View view, int i22) {
                if (JDWebView.this.isTopBarGone() || !(view instanceof CustomChannelFollowView)) {
                    return;
                }
                ((CustomChannelFollowView) view).changeIcon(i22 != 2);
            }
        };
        init(context);
    }
}
