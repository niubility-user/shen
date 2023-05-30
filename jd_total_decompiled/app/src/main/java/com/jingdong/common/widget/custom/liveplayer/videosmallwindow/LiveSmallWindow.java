package com.jingdong.common.widget.custom.liveplayer.videosmallwindow;

import android.graphics.Point;
import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.listui.Observable;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.widget.custom.liveplayer.LiveDataEntity;
import com.jingdong.common.widget.custom.liveplayer.LivePlayer;
import com.jingdong.common.widget.custom.liveplayer.LiveUIConfigBean;
import com.jingdong.common.widget.custom.liveplayer.bean.LiveSmallWindowBean;
import com.jingdong.common.widget.custom.liveplayer.bean.TemplateFlagBean;
import com.jingdong.common.widget.custom.liveplayer.callback.LiveSmallWindowCallback;
import com.jingdong.common.widget.custom.liveplayer.decoration.BenefitDecoration;
import com.jingdong.common.widget.custom.liveplayer.decoration.PredictDecoration;
import com.jingdong.common.widget.custom.liveplayer.decoration.SimpleDecoration;
import com.jingdong.common.widget.custom.liveplayer.util.TemplateFlag;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.utils.DPIUtil;
import com.tencent.map.sdk.comps.vis.VisualLayer;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes12.dex */
public class LiveSmallWindow {
    public static final int DEFAULT_BOTTOM_SAFE_DISTANCE = 118;
    public static final int DEFAULT_LEFT_RIGHT_SAFE_DISTANCE = 10;
    public static final boolean DEFAULT_NEED_ATTACH_VALUE = true;
    public static final boolean DEFAULT_OPEN_VOICE_VALUE = false;
    public static final int DEFAULT_TOP_SAFE_DISTANCE = 30;
    public static final int ERR_CODE_LOCAL_ERROR_ACTIVITY_FINISH = 10005;
    public static final int ERR_CODE_LOCAL_ERROR_APP_BACKGROUND = 10006;
    public static final int ERR_CODE_LOCAL_ERROR_NO_PERMISSION = 10007;
    public static final int ERR_CODE_NETWORK_ERROR_CODE_ERR = 20002;
    public static final int ERR_CODE_NETWORK_ERROR_DATA_PARSE_ERROR = 20007;
    public static final int ERR_CODE_NETWORK_ERROR_DATE_EMPTY = 20003;
    public static final int ERR_CODE_NETWORK_ERROR_HTTP_ERROR = 20004;
    public static final int ERR_CODE_NETWORK_ERROR_INVALID_STATUS = 20006;
    public static final int ERR_CODE_NETWORK_ERROR_NO_PULL_URL = 20005;
    public static final int ERR_CODE_NETWORK_ERROR_PLAY_TIME_OUT = 30002;
    public static final int ERR_CODE_NETWORK_ERROR_RESPONSE_EMPTY = 20001;
    public static final int ERR_CODE_NETWORK_ERROR_STREAM_ERROR = 30001;
    public static final int LIVE_SMALL_WINDOW_DEFAULT_CORNER_RADIUS = 15;
    public static final int PREDICT_WINDOW_DEFAULT_HEIGHT = 118;
    public static final int PREDICT_WINDOW_DEFAULT_WIDTH = 102;
    public static final String SCREEN_LANDSCAPE = "1";
    public static final String SCREEN_PORTRAIT = "0";
    private static final String TAG = "LiveSmallWindow";
    private LiveSmallWindowCallback mCallback;
    private BaseActivity mContext;
    private DecorationType mDecorationType;
    private DispalyBenefit mDispalyBenefit;
    private ISmallWindowManager.IPlayerChange mIPlayerChange;
    private final LiveSmallWindowCallback mInnerCallBackProxy;
    private boolean mIsMateX;
    private String mLiveId;
    private String mLiveOrigin;
    private LiveSmallWindowBean mLiveSmallWindowBean;
    private boolean mNeedAttachSide;
    private boolean mNeedIgnoreLoadingStatusWhenStart;
    private Observable mObservable;
    private boolean mOpenVoice;
    private int mPlayTimeOutMillis;
    private int mPosX;
    private int mPosY;
    private String mShopId;
    private boolean mShowBorder;
    private boolean mShowDecoration;
    private String mSku;
    private StandaloneSmallWindowManager mStandaloneSmallWindowManager;
    private String mTemplateFlag;
    private WindowConfit mWindowConfit;
    public static final DispalyBenefit DEFAULT_DISPLAY_BENEFIT = DispalyBenefit.FALSE;
    public static HashMap<Integer, String> sErrMap = new HashMap<>();
    private static final BackForegroundWatcher.BackForegroundListener sBackWatcher = new BackForegroundWatcher.BackForegroundListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow.2
        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onBackToForeground() {
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
            StandaloneSmallWindowManager standaloneSmallWindowManager = StandaloneSmallWindowManager.getInstance();
            if (standaloneSmallWindowManager != null) {
                String str = "on background,check smallwinow wheather showing:" + standaloneSmallWindowManager.isSmallShowing();
                if (standaloneSmallWindowManager.isSmallShowing()) {
                    standaloneSmallWindowManager.destory();
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow$8  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$widget$custom$liveplayer$videosmallwindow$LiveSmallWindow$DecorationType;

        static {
            int[] iArr = new int[DecorationType.values().length];
            $SwitchMap$com$jingdong$common$widget$custom$liveplayer$videosmallwindow$LiveSmallWindow$DecorationType = iArr;
            try {
                iArr[DecorationType.SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$widget$custom$liveplayer$videosmallwindow$LiveSmallWindow$DecorationType[DecorationType.BENEFIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class Builder {
        private LiveSmallWindowCallback mCallback;
        private BaseActivity mContext;
        private DecorationType mDecorationType;
        private DispalyBenefit mDispalyBenefit;
        private ISmallWindowManager.IPlayerChange mIPlayerChange;
        private boolean mIsMateX;
        private String mLiveId;
        private String mLiveOrigin;
        private boolean mNeedAttachSide;
        private boolean mNeedIgnoreLoadingStatusWhenStart;
        private boolean mOpenVoice;
        private int mPlayTimeOutMillis;
        private int mPosX;
        private int mPosY;
        private String mShopId;
        private boolean mShowBorder;
        private boolean mShowDecoration;
        private String mSku;
        private String mTemplateFlag;
        private WindowConfit mWindowConfit;

        public Builder(@NotNull BaseActivity baseActivity) {
            this(baseActivity, null);
        }

        private void getTemplateAndInjectParams(String str) {
            if (TextUtils.isEmpty(str) || this.mContext == null) {
                return;
            }
            String str2 = TemplateFlag.sTemplateFlagMap.get(str);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            try {
                TemplateFlagBean templateFlagBean = (TemplateFlagBean) JDJSON.parseObject(str2, TemplateFlagBean.class);
                if (templateFlagBean == null) {
                    return;
                }
                injectParams(templateFlagBean);
            } catch (Exception unused) {
            }
        }

        public LiveSmallWindow build() {
            return new LiveSmallWindow(this.mContext, this.mLiveId, this.mSku, this.mShopId, this.mLiveOrigin, this.mTemplateFlag, this.mDispalyBenefit, this.mCallback, this.mShowBorder, this.mShowDecoration, this.mIPlayerChange, this.mWindowConfit, this.mPosX, this.mPosY, this.mIsMateX, this.mOpenVoice, this.mNeedAttachSide, this.mDecorationType, this.mNeedIgnoreLoadingStatusWhenStart, this.mPlayTimeOutMillis);
        }

        public Builder injectParams(TemplateFlagBean templateFlagBean) {
            if (templateFlagBean != null && this.mContext != null) {
                if (!TextUtils.isEmpty(templateFlagBean.liveId)) {
                    this.mLiveId = templateFlagBean.liveId;
                }
                if (!TextUtils.isEmpty(templateFlagBean.sku)) {
                    this.mSku = templateFlagBean.sku;
                }
                if (!TextUtils.isEmpty(templateFlagBean.shopId)) {
                    this.mShopId = templateFlagBean.shopId;
                }
                if (!TextUtils.isEmpty(templateFlagBean.templateFlag)) {
                    this.mTemplateFlag = templateFlagBean.templateFlag;
                }
                if (this.mWindowConfit == null) {
                    this.mWindowConfit = new WindowConfit();
                }
                Integer num = templateFlagBean.width;
                if (num != null && num.intValue() > 0) {
                    this.mWindowConfit.width = Integer.valueOf(DPIUtil.dip2px(templateFlagBean.width.intValue()));
                }
                Integer num2 = templateFlagBean.height;
                if (num2 != null && num2.intValue() > 0) {
                    this.mWindowConfit.height = Integer.valueOf(DPIUtil.dip2px(templateFlagBean.height.intValue()));
                }
                Integer num3 = templateFlagBean.bottomMargin;
                if (num3 != null && num3.intValue() > 0) {
                    this.mWindowConfit.bottomMargin = templateFlagBean.bottomMargin;
                }
                Integer num4 = templateFlagBean.rightMargin;
                if (num4 != null && num4.intValue() > 0) {
                    this.mWindowConfit.rightMargin = templateFlagBean.rightMargin;
                }
                Integer num5 = templateFlagBean.borderRadius;
                if (num5 != null && num5.intValue() > 0) {
                    this.mWindowConfit.cornerRadius = templateFlagBean.borderRadius;
                }
                Integer num6 = templateFlagBean.borderWidth;
                if (num6 != null && num6.intValue() > 0) {
                    this.mWindowConfit.borderWidth = templateFlagBean.borderWidth;
                }
                if (!TextUtils.isEmpty(templateFlagBean.borderColor)) {
                    this.mWindowConfit.borderColor = templateFlagBean.borderColor;
                }
                Integer num7 = templateFlagBean.closeIconSize;
                if (num7 != null && num7.intValue() > 0) {
                    this.mWindowConfit.closeSize = templateFlagBean.closeIconSize;
                }
                Integer num8 = templateFlagBean.closeIconTopMargin;
                if (num8 != null && num8.intValue() > 0) {
                    this.mWindowConfit.closeIconTopMargin = templateFlagBean.closeIconTopMargin;
                }
                Integer num9 = templateFlagBean.closeIconRightMargin;
                if (num9 != null && num9.intValue() > 0) {
                    this.mWindowConfit.closeIconRightMargin = templateFlagBean.closeIconRightMargin;
                }
                Integer num10 = templateFlagBean.muteIconSize;
                if (num10 != null && num10.intValue() > 0) {
                    this.mWindowConfit.muteSize = templateFlagBean.muteIconSize;
                }
                Integer num11 = templateFlagBean.muteIconBottomMargin;
                if (num11 != null && num11.intValue() > 0) {
                    this.mWindowConfit.muteIconBottomMargin = templateFlagBean.muteIconBottomMargin;
                }
                Integer num12 = templateFlagBean.muteIconRightMargin;
                if (num12 != null && num12.intValue() > 0) {
                    this.mWindowConfit.muteIconRightMargin = templateFlagBean.muteIconRightMargin;
                }
                Integer num13 = templateFlagBean.playStatusIconSize;
                if (num13 != null && num13.intValue() > 0) {
                    this.mWindowConfit.playStatusIconSize = templateFlagBean.playStatusIconSize;
                }
                Integer num14 = templateFlagBean.playStatusIconTopMargin;
                if (num14 != null && num14.intValue() > 0) {
                    this.mWindowConfit.playStatusIconTopMargin = templateFlagBean.playStatusIconTopMargin;
                }
                Integer num15 = templateFlagBean.playStatusIconLeftMargin;
                if (num15 != null && num15.intValue() > 0) {
                    this.mWindowConfit.playStatusIconLeftMargin = templateFlagBean.playStatusIconLeftMargin;
                }
                Boolean bool = templateFlagBean.showMuteIcon;
                if (bool != null) {
                    this.mWindowConfit.showMuteIcon = bool;
                }
                Integer num16 = templateFlagBean.leftRightSafeDistance;
                if (num16 != null && num16.intValue() > 0) {
                    this.mWindowConfit.leftRightSafeDistance = templateFlagBean.leftRightSafeDistance;
                }
                Integer num17 = templateFlagBean.bottomSafeDistance;
                if (num17 != null && num17.intValue() > 0) {
                    this.mWindowConfit.bottomSafeDistance = templateFlagBean.bottomSafeDistance;
                }
                Integer num18 = templateFlagBean.topSafeDistance;
                if (num18 != null && num18.intValue() > 0) {
                    this.mWindowConfit.topSafeDistance = templateFlagBean.topSafeDistance;
                }
                this.mWindowConfit = LiveSmallWindow.checkWindowConfigAndSetDefaultParams(this.mWindowConfit);
                String str = templateFlagBean.origin;
                if (str != null) {
                    this.mLiveOrigin = str;
                }
                if (templateFlagBean.mute != null) {
                    this.mOpenVoice = !r0.booleanValue();
                }
                Boolean bool2 = templateFlagBean.disPlayBenefit;
                if (bool2 != null) {
                    this.mDispalyBenefit = bool2.booleanValue() ? DispalyBenefit.TRUE : DispalyBenefit.FALSE;
                }
                Boolean bool3 = templateFlagBean.needAttachSide;
                if (bool3 != null) {
                    this.mNeedAttachSide = bool3.booleanValue();
                }
            }
            return this;
        }

        public Builder setCallbackListener(LiveSmallWindowCallback liveSmallWindowCallback) {
            this.mCallback = liveSmallWindowCallback;
            return this;
        }

        public Builder setDecorationType(DecorationType decorationType) {
            this.mDecorationType = decorationType;
            return this;
        }

        public Builder setDisplay(DispalyBenefit dispalyBenefit) {
            this.mDispalyBenefit = dispalyBenefit;
            return this;
        }

        public Builder setIPlayerChange(ISmallWindowManager.IPlayerChange iPlayerChange) {
            this.mIPlayerChange = iPlayerChange;
            return this;
        }

        public Builder setIsMateX(boolean z) {
            this.mIsMateX = z;
            return this;
        }

        public Builder setLiveId(String str) {
            this.mLiveId = str;
            return this;
        }

        public Builder setLiveOrigin(String str) {
            this.mLiveOrigin = str;
            return this;
        }

        public Builder setNeedAttachSide(boolean z) {
            this.mNeedAttachSide = z;
            return this;
        }

        public Builder setNeedIgnoreLoadingStatusWhenStart(boolean z) {
            this.mNeedIgnoreLoadingStatusWhenStart = z;
            return this;
        }

        public Builder setOpenVoice(boolean z) {
            this.mOpenVoice = z;
            return this;
        }

        public Builder setPlayTimeOutMillis(int i2) {
            this.mPlayTimeOutMillis = i2;
            return this;
        }

        public Builder setShopId(String str) {
            this.mShopId = str;
            return this;
        }

        public Builder setShowBorder(boolean z) {
            this.mShowBorder = z;
            return this;
        }

        public Builder setShowDecoration(boolean z) {
            this.mShowDecoration = z;
            return this;
        }

        public Builder setSku(String str) {
            this.mSku = str;
            return this;
        }

        public Builder setTemplateFlag(String str) {
            this.mTemplateFlag = str;
            return this;
        }

        public Builder setWindowConfit(WindowConfit windowConfit) {
            this.mWindowConfit = LiveSmallWindow.adjustWindowConfig(this.mWindowConfit, windowConfit);
            return this;
        }

        public Builder setWindowPositon(int i2, int i3) {
            this.mPosX = i2;
            this.mPosY = i3;
            return this;
        }

        public Builder(@NotNull BaseActivity baseActivity, String str) {
            this.mDispalyBenefit = LiveSmallWindow.DEFAULT_DISPLAY_BENEFIT;
            this.mShowBorder = false;
            this.mShowDecoration = true;
            this.mPosX = -1;
            this.mPosY = -1;
            this.mIsMateX = false;
            this.mOpenVoice = false;
            this.mNeedAttachSide = true;
            this.mDecorationType = DecorationType.BENEFIT;
            this.mNeedIgnoreLoadingStatusWhenStart = true;
            this.mPlayTimeOutMillis = 5000;
            this.mContext = baseActivity;
            this.mTemplateFlag = str;
            getTemplateAndInjectParams(str);
        }
    }

    /* loaded from: classes12.dex */
    public enum DecorationType {
        SIMPLE,
        BENEFIT
    }

    /* loaded from: classes12.dex */
    public enum DispalyBenefit {
        TRUE,
        FALSE
    }

    /* loaded from: classes12.dex */
    public static class Error {
        public int errCode;
        public String errMsg;

        public Error(int i2, String str) {
            this.errCode = i2;
            this.errMsg = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class SmallWindowPlayerChangeProxy extends StandaloneSmallWindowManager.SmallWindowPlayerChange {
        SmallWindowPlayerChangeProxy() {
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.SmallWindowPlayerChange, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onDestory() {
            if (LiveSmallWindow.this.mIPlayerChange != null) {
                LiveSmallWindow.this.mIPlayerChange.onDestory();
            } else {
                super.onDestory();
            }
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.SmallWindowPlayerChange, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onMuteClick() {
            if (LiveSmallWindow.this.mIPlayerChange != null) {
                LiveSmallWindow.this.mIPlayerChange.onMuteClick();
            } else {
                super.onMuteClick();
            }
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.SmallWindowPlayerChange, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onPause() {
            if (LiveSmallWindow.this.mIPlayerChange != null) {
                LiveSmallWindow.this.mIPlayerChange.onPause();
            } else {
                super.onPause();
            }
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.SmallWindowPlayerChange, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onSmallClick() {
            LivePlayer livePlayerInstance = LiveSmallWindow.this.getLivePlayerInstance();
            if (livePlayerInstance != null && livePlayerInstance.isJumpOut()) {
                LiveSmallWindow.this.windowClickMd();
            }
            if (LiveSmallWindow.this.mIPlayerChange != null) {
                LiveSmallWindow.this.mIPlayerChange.onSmallClick();
            } else {
                super.onSmallClick();
            }
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.SmallWindowPlayerChange, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void showAtBig() {
            if (LiveSmallWindow.this.mIPlayerChange != null) {
                LiveSmallWindow.this.mIPlayerChange.showAtBig();
            } else {
                super.showAtBig();
            }
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.SmallWindowPlayerChange, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void showAtSmall() {
            if (LiveSmallWindow.this.mIPlayerChange != null) {
                LiveSmallWindow.this.mIPlayerChange.showAtSmall();
            } else {
                super.showAtSmall();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class WindowConfit {
        public String borderColor;
        public Integer borderWidth;
        public Integer bottomMargin;
        public Integer bottomSafeDistance;
        public Integer closeIconRightMargin;
        public Integer closeIconTopMargin;
        public Integer closeSize;
        public Integer cornerRadius;
        public Integer height;
        public Integer leftRightSafeDistance;
        public Integer muteIconBottomMargin;
        public Integer muteIconRightMargin;
        public Integer muteSize;
        public Integer playStatusIconLeftMargin;
        public Integer playStatusIconSize;
        public Integer playStatusIconTopMargin;
        public Integer rightMargin;
        public Boolean showMuteIcon;
        public Integer topSafeDistance;
        public Integer width;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WindowConfit adjustWindowConfig(WindowConfit windowConfit, WindowConfit windowConfit2) {
        if (windowConfit2 == null) {
            return windowConfit;
        }
        if (windowConfit == null) {
            windowConfit = new WindowConfit();
        }
        Integer num = windowConfit2.width;
        if (num != null && num.intValue() > 0) {
            windowConfit.width = windowConfit2.width;
        }
        Integer num2 = windowConfit2.height;
        if (num2 != null && num2.intValue() > 0) {
            windowConfit.height = windowConfit2.height;
        }
        Integer num3 = windowConfit2.bottomMargin;
        if (num3 != null && num3.intValue() > 0) {
            windowConfit.bottomMargin = windowConfit2.bottomMargin;
        }
        Integer num4 = windowConfit2.rightMargin;
        if (num4 != null && num4.intValue() > 0) {
            windowConfit.rightMargin = windowConfit2.rightMargin;
        }
        Integer num5 = windowConfit2.cornerRadius;
        if (num5 != null && num5.intValue() > 0) {
            windowConfit.cornerRadius = windowConfit2.cornerRadius;
        }
        Integer num6 = windowConfit2.borderWidth;
        if (num6 != null && num6.intValue() > 0) {
            windowConfit.borderWidth = windowConfit2.borderWidth;
        }
        if (!TextUtils.isEmpty(windowConfit2.borderColor)) {
            windowConfit.borderColor = windowConfit2.borderColor;
        }
        Integer num7 = windowConfit2.closeSize;
        if (num7 != null && num7.intValue() > 0) {
            windowConfit.closeSize = windowConfit2.closeSize;
        }
        Integer num8 = windowConfit2.closeIconTopMargin;
        if (num8 != null && num8.intValue() > 0) {
            windowConfit.closeIconTopMargin = windowConfit2.closeIconTopMargin;
        }
        Integer num9 = windowConfit2.closeIconRightMargin;
        if (num9 != null && num9.intValue() > 0) {
            windowConfit.closeIconRightMargin = windowConfit2.closeIconRightMargin;
        }
        Integer num10 = windowConfit2.muteSize;
        if (num10 != null && num10.intValue() > 0) {
            windowConfit.muteSize = windowConfit2.muteSize;
        }
        Integer num11 = windowConfit2.muteIconBottomMargin;
        if (num11 != null && num11.intValue() > 0) {
            windowConfit.muteIconBottomMargin = windowConfit2.muteIconBottomMargin;
        }
        Integer num12 = windowConfit2.muteIconRightMargin;
        if (num12 != null && num12.intValue() > 0) {
            windowConfit.muteIconRightMargin = windowConfit2.muteIconRightMargin;
        }
        Integer num13 = windowConfit2.playStatusIconSize;
        if (num13 != null && num13.intValue() > 0) {
            windowConfit.playStatusIconSize = windowConfit2.playStatusIconSize;
        }
        Integer num14 = windowConfit2.playStatusIconTopMargin;
        if (num14 != null && num14.intValue() > 0) {
            windowConfit.playStatusIconTopMargin = windowConfit2.playStatusIconTopMargin;
        }
        Integer num15 = windowConfit2.playStatusIconLeftMargin;
        if (num15 != null && num15.intValue() > 0) {
            windowConfit.playStatusIconLeftMargin = windowConfit2.playStatusIconLeftMargin;
        }
        Boolean bool = windowConfit2.showMuteIcon;
        if (bool != null) {
            windowConfit.showMuteIcon = bool;
        }
        Integer num16 = windowConfit2.leftRightSafeDistance;
        if (num16 != null && num16.intValue() > 0) {
            windowConfit.leftRightSafeDistance = windowConfit2.leftRightSafeDistance;
        }
        Integer num17 = windowConfit2.bottomSafeDistance;
        if (num17 != null && num17.intValue() > 0) {
            windowConfit.bottomSafeDistance = windowConfit2.bottomSafeDistance;
        }
        Integer num18 = windowConfit2.topSafeDistance;
        if (num18 != null && num18.intValue() > 0) {
            windowConfit.topSafeDistance = windowConfit2.topSafeDistance;
        }
        return windowConfit;
    }

    public static WindowConfit checkWindowConfigAndSetDefaultParams(WindowConfit windowConfit) {
        if (windowConfit == null) {
            windowConfit = new WindowConfit();
        }
        if (windowConfit.width == null) {
            windowConfit.width = 0;
        }
        if (windowConfit.height == null) {
            windowConfit.height = 0;
        }
        if (windowConfit.cornerRadius == null) {
            windowConfit.cornerRadius = 12;
        }
        if (windowConfit.borderWidth == null) {
            windowConfit.borderWidth = 2;
        }
        if (windowConfit.closeSize == null) {
            windowConfit.closeSize = 22;
        }
        if (windowConfit.closeIconTopMargin == null) {
            windowConfit.closeIconTopMargin = 6;
        }
        if (windowConfit.closeIconRightMargin == null) {
            windowConfit.closeIconRightMargin = 6;
        }
        if (windowConfit.muteSize == null) {
            windowConfit.muteSize = 26;
        }
        if (windowConfit.muteIconBottomMargin == null) {
            windowConfit.muteIconBottomMargin = 6;
        }
        if (windowConfit.muteIconRightMargin == null) {
            windowConfit.muteIconRightMargin = 6;
        }
        if (windowConfit.playStatusIconSize == null) {
            windowConfit.playStatusIconSize = 12;
        }
        if (windowConfit.playStatusIconTopMargin == null) {
            windowConfit.playStatusIconTopMargin = 6;
        }
        if (windowConfit.playStatusIconLeftMargin == null) {
            windowConfit.playStatusIconLeftMargin = 6;
        }
        if (windowConfit.showMuteIcon == null) {
            windowConfit.showMuteIcon = Boolean.TRUE;
        }
        if (windowConfit.leftRightSafeDistance == null) {
            windowConfit.leftRightSafeDistance = 10;
        }
        if (windowConfit.bottomSafeDistance == null) {
            windowConfit.bottomSafeDistance = 118;
        }
        if (windowConfit.topSafeDistance == null) {
            windowConfit.topSafeDistance = 30;
        }
        return windowConfit;
    }

    private LiveDataEntity convertLiveDataEntity(LiveSmallWindowBean liveSmallWindowBean) {
        LiveDataEntity liveDataEntity = new LiveDataEntity();
        if (!TextUtils.isEmpty(liveSmallWindowBean.skuVideoUrl)) {
            liveDataEntity.status = "1";
        } else {
            int i2 = liveSmallWindowBean.status;
            if (1 == i2) {
                liveDataEntity.status = "2";
            } else if (3 == i2) {
                liveDataEntity.status = "1";
            } else {
                liveDataEntity.status = "-1";
            }
        }
        liveDataEntity.liveId = liveSmallWindowBean.liveId;
        if (!TextUtils.isEmpty(liveSmallWindowBean.skuVideoUrl)) {
            String str = liveSmallWindowBean.skuVideoUrl;
            liveDataEntity.videoUrl = str;
            liveDataEntity.h5PullUrl = str;
            liveDataEntity.liveStreamTag = 8;
        } else {
            String str2 = liveSmallWindowBean.videoUrlNew;
            liveDataEntity.videoUrl = str2;
            liveDataEntity.h5PullUrl = str2;
            liveDataEntity.liveStreamTag = liveSmallWindowBean.status;
        }
        liveDataEntity.indexImage = liveSmallWindowBean.indexImg;
        liveDataEntity.screen = liveSmallWindowBean.screen;
        liveDataEntity.openapp = liveSmallWindowBean.openApp;
        liveDataEntity.skuId = this.mSku;
        if (!TextUtils.isEmpty(liveSmallWindowBean.skuVideoUrl)) {
            liveDataEntity.liveType = "3";
        } else if (liveSmallWindowBean.status == 1) {
            liveDataEntity.liveType = "2";
        } else {
            liveDataEntity.liveType = "3";
        }
        liveDataEntity.explainTime = liveSmallWindowBean.explainTime;
        return liveDataEntity;
    }

    private void createSmallWindowAndShow(LiveSmallWindowBean liveSmallWindowBean) {
        ISmallWindowManager.ISizeChanger smallWindowSizeChanger;
        int height;
        Integer num;
        int i2 = liveSmallWindowBean.status;
        WindowConfit checkWindowConfigAndSetDefaultParams = checkWindowConfigAndSetDefaultParams(this.mWindowConfit);
        this.mWindowConfit = checkWindowConfigAndSetDefaultParams;
        if (i2 == 0) {
            this.mShowBorder = false;
            this.mNeedIgnoreLoadingStatusWhenStart = false;
            checkWindowConfigAndSetDefaultParams.closeSize = 22;
            this.mWindowConfit.closeIconTopMargin = 6;
            this.mWindowConfit.closeIconRightMargin = 6;
            this.mWindowConfit.cornerRadius = 12;
            smallWindowSizeChanger = new ISmallWindowManager.ISizeChanger() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow.6
                @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.ISizeChanger
                public Point getSize() {
                    return new Point(DPIUtil.dip2px(102.0f), DPIUtil.dip2px(118.0f));
                }
            };
        } else if (checkWindowConfigAndSetDefaultParams.width.intValue() > 0 && this.mWindowConfit.height.intValue() > 0) {
            if ("0".equals(liveSmallWindowBean.screen) && this.mWindowConfit.width.intValue() > this.mWindowConfit.height.intValue()) {
                reverseWidthHeight();
            }
            if ("1".equals(liveSmallWindowBean.screen) && this.mWindowConfit.width.intValue() < this.mWindowConfit.height.intValue()) {
                reverseWidthHeight();
            }
            smallWindowSizeChanger = new ISmallWindowManager.ISizeChanger() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow.7
                @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.ISizeChanger
                public Point getSize() {
                    return new Point(LiveSmallWindow.this.mWindowConfit.width.intValue(), LiveSmallWindow.this.mWindowConfit.height.intValue());
                }
            };
        } else {
            smallWindowSizeChanger = new StandaloneSmallWindowManager.SmallWindowSizeChanger();
        }
        StandaloneSmallWindowManager standaloneSmallWindowManager = this.mStandaloneSmallWindowManager;
        BaseActivity baseActivity = this.mContext;
        boolean equals = "1".equals(liveSmallWindowBean.screen);
        SmallWindowPlayerChangeProxy smallWindowPlayerChangeProxy = new SmallWindowPlayerChangeProxy();
        boolean z = this.mShowBorder;
        float intValue = this.mWindowConfit.cornerRadius.intValue();
        int intValue2 = this.mWindowConfit.borderWidth.intValue();
        WindowConfit windowConfit = this.mWindowConfit;
        String str = windowConfit.borderColor;
        if (str == null) {
            str = SmallWindow.DEFAULT_BORDER_COLOR;
        }
        standaloneSmallWindowManager.init(baseActivity, equals, smallWindowPlayerChangeProxy, smallWindowSizeChanger, z, intValue, intValue2, str, new LiveUIConfigBean(windowConfit.closeSize.intValue(), this.mWindowConfit.closeIconTopMargin.intValue(), this.mWindowConfit.closeIconRightMargin.intValue(), this.mWindowConfit.playStatusIconSize.intValue(), this.mWindowConfit.playStatusIconTopMargin.intValue(), this.mWindowConfit.playStatusIconLeftMargin.intValue(), this.mWindowConfit.muteSize.intValue(), this.mWindowConfit.muteIconBottomMargin.intValue(), this.mWindowConfit.muteIconRightMargin.intValue()));
        int intValue3 = this.mWindowConfit.leftRightSafeDistance.intValue();
        int intValue4 = this.mWindowConfit.topSafeDistance.intValue();
        int intValue5 = this.mWindowConfit.bottomSafeDistance.intValue();
        this.mStandaloneSmallWindowManager.setShowMuteIcon(this.mWindowConfit.showMuteIcon.booleanValue());
        int appWidth = DPIUtil.getAppWidth(this.mContext);
        if (UnAndroidUtils.mateXEasyClient(this.mContext)) {
            appWidth = DPIUtil.getWidth(this.mContext);
        }
        int i3 = this.mPosX;
        if (i3 < 0 || (height = this.mPosY) < 0) {
            WindowConfit windowConfit2 = this.mWindowConfit;
            if (windowConfit2.bottomMargin != null && (num = windowConfit2.rightMargin) != null) {
                int intValue6 = num.intValue();
                if (intValue6 < 10) {
                    intValue6 = 10;
                }
                int dip2px = (appWidth - smallWindowSizeChanger.getSize().x) - DPIUtil.dip2px(intValue6);
                if (dip2px < DPIUtil.dip2px(10.0f)) {
                    dip2px = DPIUtil.dip2px(10.0f);
                }
                i3 = dip2px;
                int intValue7 = this.mWindowConfit.bottomMargin.intValue();
                if (intValue7 < 118) {
                    intValue7 = 118;
                }
                height = (DPIUtil.getAppHeight(this.mContext) - smallWindowSizeChanger.getSize().y) - DPIUtil.dip2px(intValue7);
                if (height < DPIUtil.dip2px(30.0f)) {
                    height = DPIUtil.dip2px(30.0f);
                }
            } else {
                i3 = (appWidth - smallWindowSizeChanger.getSize().x) - DPIUtil.dip2px(intValue3);
                height = (DPIUtil.getHeight(this.mContext) - smallWindowSizeChanger.getSize().y) - DPIUtil.dip2px(intValue5);
            }
        }
        this.mStandaloneSmallWindowManager.setWindowPos(i3, height);
        this.mStandaloneSmallWindowManager.setNeedAttach(this.mNeedAttachSide);
        this.mStandaloneSmallWindowManager.setTopSafeDistance(intValue4);
        this.mStandaloneSmallWindowManager.setLeftRightSafeDistance(intValue3);
        this.mStandaloneSmallWindowManager.setBottomSafeDistance(intValue5);
        this.mStandaloneSmallWindowManager.setIgnoreLoadingStatusWhenStart(this.mNeedIgnoreLoadingStatusWhenStart);
        setWindowDecoration(liveSmallWindowBean);
        this.mStandaloneSmallWindowManager.setData(convertLiveDataEntity(liveSmallWindowBean));
        this.mStandaloneSmallWindowManager.setSmallWindowUICallBack(this.mInnerCallBackProxy);
        LivePlayer livePlayerInstance = getLivePlayerInstance();
        if (livePlayerInstance != null) {
            livePlayerInstance.setPlayCallBack(this.mInnerCallBackProxy);
            livePlayerInstance.setPlayTimeOutMillis(this.mPlayTimeOutMillis);
        }
        this.mStandaloneSmallWindowManager.showSmall(this.mOpenVoice);
    }

    public static void destoryWindow() {
        StandaloneSmallWindowManager.getInstance().destroy();
    }

    public static void exposeSmallWindow() {
        StandaloneSmallWindowManager standaloneSmallWindowManager = StandaloneSmallWindowManager.getInstance();
        if (standaloneSmallWindowManager.hasSmallWindow()) {
            standaloneSmallWindowManager.exposeSmallWindow();
        }
        SmallWindowManager smallWindowManager = SmallWindowManager.getInstance();
        if (smallWindowManager.hasSmallWindow()) {
            smallWindowManager.exposeSmallWindow();
        }
    }

    public static Point getLastDismissPosition() {
        return SmallWindow.sLastDismissPosition;
    }

    private Observable getObservable() {
        Observable observable = this.mObservable;
        if (observable != null) {
            return observable;
        }
        Observable subscribe = new Observable().subscribe("success", new Observable.Action<LiveSmallWindowBean>() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow.5
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(LiveSmallWindowBean liveSmallWindowBean) {
                if (liveSmallWindowBean == null) {
                    if (LiveSmallWindow.this.mCallback != null) {
                        LiveSmallWindow.this.mCallback.onError(20003, LiveSmallWindow.sErrMap.get(20003));
                        return;
                    }
                    return;
                }
                int i2 = liveSmallWindowBean.status;
                if ((i2 == 1 || i2 == 3) && TextUtils.isEmpty(liveSmallWindowBean.videoUrlNew)) {
                    LiveSmallWindow.this.mCallback.onError(20005, LiveSmallWindow.sErrMap.get(20005));
                    return;
                }
                int i3 = liveSmallWindowBean.status;
                if (i3 == 1 || i3 == 3 || i3 == 0) {
                    if (LiveSmallWindow.this.mCallback != null) {
                        LiveSmallWindow.this.mCallback.onGetData(liveSmallWindowBean);
                    }
                    LiveSmallWindow.this.mLiveSmallWindowBean = liveSmallWindowBean;
                    if (LiveSmallWindow.this.mContext == null) {
                        if (LiveSmallWindow.this.mCallback != null) {
                            LiveSmallWindow.this.mCallback.onError(10005, LiveSmallWindow.sErrMap.get(10005));
                            return;
                        }
                        return;
                    } else if (LiveSmallWindow.this.mContext.isFinishing()) {
                        if (LiveSmallWindow.this.mCallback != null) {
                            LiveSmallWindow.this.mCallback.onError(10005, LiveSmallWindow.sErrMap.get(10005));
                            return;
                        }
                        return;
                    } else if (!BackForegroundWatcher.getInstance().isAppForeground()) {
                        if (LiveSmallWindow.this.mCallback != null) {
                            LiveSmallWindow.this.mCallback.onError(10006, LiveSmallWindow.sErrMap.get(10006));
                            return;
                        }
                        return;
                    } else {
                        LiveSmallWindow.this.initLiveSmallWindow(liveSmallWindowBean);
                        return;
                    }
                }
                LiveSmallWindow.this.mCallback.onError(20006, LiveSmallWindow.sErrMap.get(20006));
            }
        }).subscribe("error", new Observable.Action<Error>() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow.4
            @Override // com.jingdong.common.listui.Observable.Action
            public void call(Error error) {
                if (LiveSmallWindow.this.mCallback != null) {
                    LiveSmallWindow.this.mCallback.onError(error.errCode, error.errMsg);
                }
            }
        });
        this.mObservable = subscribe;
        return subscribe;
    }

    private String getSmallWindowFoldScreenSwitch() {
        try {
            return JDMobileConfig.getInstance().getConfig("liveroom", "config", "closeFoldScreenSmallwindow");
        } catch (Exception unused) {
            return "0";
        }
    }

    private String getSmallWindowSwitch() {
        try {
            return JDMobileConfig.getInstance().getConfig("liveroom", "config", "closeSmallwindow");
        } catch (Exception unused) {
            return "0";
        }
    }

    private void getWidgetV1052(BaseActivity baseActivity, final Observable observable, final String str, final String str2, final String str3, final String str4, final String str5) {
        if (baseActivity == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("getWidgetV1052");
        httpSetting.setHost(Configuration.getCommonNewHost());
        httpSetting.setPost(false);
        httpSetting.setEffect(0);
        httpSetting.setCacheMode(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse != null && httpResponse.getFastJsonObject() != null) {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (!"0".equals(fastJsonObject.getString("code"))) {
                        observable.postMainThread("error", new Error(20002, fastJsonObject.toJSONString()));
                        return;
                    } else if (!"0".equals(fastJsonObject.optString("subCode"))) {
                        observable.postMainThread("error", new Error(20002, fastJsonObject.toJSONString()));
                        return;
                    } else {
                        JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("data");
                        if (optJSONObject == null) {
                            observable.postMainThread("error", new Error(20003, LiveSmallWindow.sErrMap.get(20003)));
                            return;
                        }
                        try {
                            observable.postMainThread("success", (LiveSmallWindowBean) JDJSON.parseObject(optJSONObject.toJSONString(), LiveSmallWindowBean.class));
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            observable.postMainThread("error", new Error(20007, LiveSmallWindow.sErrMap.get(20007)));
                            return;
                        }
                    }
                }
                observable.postMainThread("error", new Error(20001, LiveSmallWindow.sErrMap.get(20001)));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                observable.postMainThread("error", new Error(20004, httpError == null ? "no error" : httpError.toString()));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                httpSettingParams.putJsonParam(ReportConstant.CommonInfo.LIVE_ID, str);
                httpSettingParams.putJsonParam("sku", str2);
                httpSettingParams.putJsonParam(ViewProps.DISPLAY, str3);
                httpSettingParams.putJsonParam("liveOrigin", str4);
                httpSettingParams.putJsonParam("shopId", str5);
            }
        });
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void hideSmallWindow() {
        StandaloneSmallWindowManager standaloneSmallWindowManager = StandaloneSmallWindowManager.getInstance();
        if (standaloneSmallWindowManager.hasSmallWindow()) {
            standaloneSmallWindowManager.hideSmallWindow();
        }
        SmallWindowManager smallWindowManager = SmallWindowManager.getInstance();
        if (smallWindowManager.hasSmallWindow()) {
            smallWindowManager.hideSmallWindow();
        }
    }

    public static boolean isSmallwindowShow() {
        StandaloneSmallWindowManager standaloneSmallWindowManager = StandaloneSmallWindowManager.getInstance();
        boolean z = standaloneSmallWindowManager.hasSmallWindow() && standaloneSmallWindowManager.isSmallShowing();
        SmallWindowManager smallWindowManager = SmallWindowManager.getInstance();
        return z || (smallWindowManager.hasSmallWindow() && smallWindowManager.isSmallShowing());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void manualCloseClickMd() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("liveroomid", (Object) this.mLiveId);
        jDJSONObject.put("live_origin", (Object) this.mLiveOrigin);
        LiveSmallWindowBean liveSmallWindowBean = this.mLiveSmallWindowBean;
        jDJSONObject.put("interest_point", (Object) ((liveSmallWindowBean == null || TextUtils.isEmpty(liveSmallWindowBean.type)) ? "-100" : this.mLiveSmallWindowBean.type));
        JDMtaUtils.sendClickDataWithExt(this.mContext, "live_float_window_close", null, null, "", "", null, null, jDJSONObject.toJSONString(), null);
    }

    private void setWindowDecoration(LiveSmallWindowBean liveSmallWindowBean) {
        if (liveSmallWindowBean == null) {
            return;
        }
        if (liveSmallWindowBean.status == 0) {
            this.mStandaloneSmallWindowManager.setDecoration(new PredictDecoration(this.mContext, liveSmallWindowBean));
        } else if (this.mShowDecoration) {
            int i2 = AnonymousClass8.$SwitchMap$com$jingdong$common$widget$custom$liveplayer$videosmallwindow$LiveSmallWindow$DecorationType[this.mDecorationType.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    this.mStandaloneSmallWindowManager.setDecoration(new BenefitDecoration(this.mContext, liveSmallWindowBean));
                }
                this.mStandaloneSmallWindowManager.setDecoration(new BenefitDecoration(this.mContext, liveSmallWindowBean));
                return;
            }
            this.mStandaloneSmallWindowManager.setDecoration(new SimpleDecoration(this.mContext, liveSmallWindowBean));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void windowClickMd() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("liveroomid", (Object) this.mLiveId);
        jDJSONObject.put("live_origin", (Object) this.mLiveOrigin);
        LiveSmallWindowBean liveSmallWindowBean = this.mLiveSmallWindowBean;
        jDJSONObject.put("interest_point", (Object) ((liveSmallWindowBean == null || TextUtils.isEmpty(liveSmallWindowBean.type)) ? "-100" : this.mLiveSmallWindowBean.type));
        JDMtaUtils.sendClickDataWithExt(this.mContext, "live_float_window", null, null, "", "", null, null, jDJSONObject.toJSONString(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void windowExpoMd() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("liveroomid", (Object) this.mLiveId);
        jDJSONObject.put("live_origin", (Object) this.mLiveOrigin);
        LiveSmallWindowBean liveSmallWindowBean = this.mLiveSmallWindowBean;
        jDJSONObject.put("interest_point", (Object) ((liveSmallWindowBean == null || TextUtils.isEmpty(liveSmallWindowBean.type)) ? "-100" : this.mLiveSmallWindowBean.type));
        JDMtaUtils.sendExposureDataWithExt(this.mContext, "live_float_window_expo", "", "", "", "", jDJSONObject.toJSONString(), null);
    }

    public LivePlayer getLivePlayerInstance() {
        StandaloneSmallWindowManager standaloneSmallWindowManager = this.mStandaloneSmallWindowManager;
        if (standaloneSmallWindowManager != null) {
            return standaloneSmallWindowManager.playerInstance();
        }
        return null;
    }

    public StandaloneSmallWindowManager getStandaloneSmallWindowManager() {
        return this.mStandaloneSmallWindowManager;
    }

    public void initLiveSmallWindow(LiveSmallWindowBean liveSmallWindowBean) {
        if (this.mStandaloneSmallWindowManager == null) {
            this.mStandaloneSmallWindowManager = StandaloneSmallWindowManager.getInstance();
        }
        if (!this.mStandaloneSmallWindowManager.isSmallShowing()) {
            destoryWindow();
            createSmallWindowAndShow(liveSmallWindowBean);
            return;
        }
        if ("1".equals(liveSmallWindowBean.screen) == this.mStandaloneSmallWindowManager.isLandScape()) {
            if ((liveSmallWindowBean.status + "").equals(this.mStandaloneSmallWindowManager.getLiveDataEntity().status)) {
                this.mStandaloneSmallWindowManager.setData(convertLiveDataEntity(liveSmallWindowBean));
                if (liveSmallWindowBean.status != 0) {
                    this.mStandaloneSmallWindowManager.playerInstance().suspend();
                    this.mStandaloneSmallWindowManager.playerInstance().play();
                }
                setWindowDecoration(liveSmallWindowBean);
                this.mStandaloneSmallWindowManager.notifyDecorationChange();
                return;
            }
        }
        destoryWindow();
        createSmallWindowAndShow(liveSmallWindowBean);
    }

    public void reverseWidthHeight() {
        WindowConfit windowConfit = this.mWindowConfit;
        Integer num = windowConfit.width;
        if (num == null || windowConfit.height == null) {
            return;
        }
        int intValue = num.intValue();
        WindowConfit windowConfit2 = this.mWindowConfit;
        windowConfit2.width = windowConfit2.height;
        windowConfit2.height = Integer.valueOf(intValue);
    }

    public void setCallback(LiveSmallWindowCallback liveSmallWindowCallback) {
        this.mCallback = liveSmallWindowCallback;
    }

    public void setContext(BaseActivity baseActivity) {
        this.mContext = baseActivity;
    }

    public void setDecorationType(DecorationType decorationType) {
        this.mDecorationType = decorationType;
    }

    public void setDispalyBenefit(DispalyBenefit dispalyBenefit) {
        this.mDispalyBenefit = dispalyBenefit;
    }

    public void setIPlayerChange(ISmallWindowManager.IPlayerChange iPlayerChange) {
        this.mIPlayerChange = iPlayerChange;
    }

    public void setIsMateX(boolean z) {
        this.mIsMateX = z;
    }

    public void setLiveId(String str) {
        this.mLiveId = str;
    }

    public void setLiveOrigin(String str) {
        this.mLiveOrigin = str;
    }

    public void setNeedAttachSide(boolean z) {
        this.mNeedAttachSide = z;
    }

    public void setNeedIgnoreLoadingStatusWhenStart(boolean z) {
        this.mNeedIgnoreLoadingStatusWhenStart = z;
    }

    public void setOpenVoice(boolean z) {
        this.mOpenVoice = z;
    }

    public void setPlayTimeOutMillis(int i2) {
        this.mPlayTimeOutMillis = i2;
    }

    public void setPosX(int i2) {
        this.mPosX = i2;
    }

    public void setPosY(int i2) {
        this.mPosY = i2;
    }

    public void setShopId(String str) {
        this.mShopId = str;
    }

    public void setShowBorder(boolean z) {
        this.mShowBorder = z;
    }

    public void setShowDecoration(boolean z) {
        this.mShowDecoration = z;
    }

    public void setSku(String str) {
        this.mSku = str;
    }

    public void setWindowConfit(WindowConfit windowConfit) {
        this.mWindowConfit = adjustWindowConfig(this.mWindowConfit, windowConfit);
    }

    public void startLiveSmallWindow() {
        if ("1".equals(getSmallWindowSwitch())) {
            return;
        }
        if (("1".equals(getSmallWindowFoldScreenSwitch()) && UnAndroidUtils.isMatex(this.mContext)) || this.mIsMateX) {
            return;
        }
        getWidgetV1052(this.mContext, getObservable(), this.mLiveId, this.mSku, this.mDispalyBenefit != DispalyBenefit.TRUE ? "0" : "1", this.mLiveOrigin, this.mShopId);
    }

    private LiveSmallWindow(BaseActivity baseActivity, String str, String str2, String str3, String str4, String str5, DispalyBenefit dispalyBenefit, LiveSmallWindowCallback liveSmallWindowCallback, boolean z, boolean z2, ISmallWindowManager.IPlayerChange iPlayerChange, WindowConfit windowConfit, int i2, int i3, boolean z3, boolean z4, boolean z5, DecorationType decorationType, boolean z6, int i4) {
        sErrMap.put(20001, "http response is empty");
        sErrMap.put(20002, VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_NETWORK);
        sErrMap.put(20003, "request success,but data empty");
        sErrMap.put(20005, "request success,but no pull url");
        sErrMap.put(10005, "activity has finish,smallwindow will not init");
        sErrMap.put(10006, "app is in backgroud,smallwindow will not init ");
        sErrMap.put(20006, "request success,but invalid status");
        sErrMap.put(20007, "request success,but data parse error");
        sErrMap.put(30001, "stream error");
        sErrMap.put(30002, "play time out");
        sErrMap.put(10007, "has no floating window permission");
        this.mInnerCallBackProxy = new LiveSmallWindowCallback() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow.1
            @Override // com.jingdong.common.widget.custom.liveplayer.callback.SmallWindowUICallback
            public void onClose(boolean z7) {
                if (z7) {
                    LiveSmallWindow.this.manualCloseClickMd();
                }
                if (LiveSmallWindow.this.mCallback != null) {
                    LiveSmallWindow.this.mCallback.onClose(z7);
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.ErrorCallback
            public void onError(int i5, String str6) {
                if (LiveSmallWindow.this.mNeedIgnoreLoadingStatusWhenStart && LiveSmallWindow.this.mStandaloneSmallWindowManager != null && LiveSmallWindow.this.mStandaloneSmallWindowManager.isInPostponeSmallWindowStatus()) {
                    LiveSmallWindow.destoryWindow();
                }
                if (LiveSmallWindow.this.mCallback != null) {
                    LiveSmallWindow.this.mCallback.onError(i5, str6);
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.LiveSmallWindowCallback
            public void onGetData(LiveSmallWindowBean liveSmallWindowBean) {
                if (LiveSmallWindow.this.mCallback != null) {
                    LiveSmallWindow.this.mCallback.onGetData(liveSmallWindowBean);
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.PlayCallBack
            public void onPlay() {
                if (LiveSmallWindow.this.mStandaloneSmallWindowManager != null && LiveSmallWindow.this.mNeedIgnoreLoadingStatusWhenStart) {
                    LiveSmallWindow.this.mStandaloneSmallWindowManager.startDisplaySmallwindow();
                }
                if (LiveSmallWindow.this.mCallback != null) {
                    LiveSmallWindow.this.mCallback.onPlay();
                }
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.callback.SmallWindowUICallback
            public void onShowSmallWindow() {
                LiveSmallWindow.this.windowExpoMd();
                if (LiveSmallWindow.this.mCallback != null) {
                    LiveSmallWindow.this.mCallback.onShowSmallWindow();
                }
            }
        };
        this.mContext = baseActivity;
        this.mLiveId = str;
        this.mSku = str2;
        this.mShopId = str3;
        this.mLiveOrigin = str4;
        this.mTemplateFlag = str5;
        this.mDispalyBenefit = dispalyBenefit;
        this.mCallback = liveSmallWindowCallback;
        this.mShowBorder = z;
        this.mShowDecoration = z2;
        this.mIPlayerChange = iPlayerChange;
        this.mWindowConfit = windowConfit;
        this.mPosX = i2;
        this.mPosY = i3;
        this.mIsMateX = z3;
        this.mOpenVoice = z4;
        this.mNeedAttachSide = z5;
        this.mDecorationType = decorationType;
        this.mNeedIgnoreLoadingStatusWhenStart = z6;
        this.mPlayTimeOutMillis = i4;
        BackForegroundWatcher.getInstance().registerListener(sBackWatcher);
    }
}
