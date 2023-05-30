package com.jingdong.common.unification.navigationbar.newbar;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.airbnb.lottie.LottieListener;
import com.jd.skin.lib.bean.ResourceItems;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.AnimationEndListener;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.navigationbar.NavigationParam;
import com.jingdong.common.unification.navigationbar.RadioStateDrawable;
import com.jingdong.common.unification.navigationbar.utils.NavigationUtils;
import com.jingdong.common.unification.uniwidget.JDLottieAnimationView;
import com.jingdong.common.utils.UnLottieUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class NavigationIconView extends JDLottieAnimationView implements INaviIcon {
    private static final String TAG = "NavigationIconView";
    private int bucketType;
    private RadioStateDrawable clickDrawable;
    private Context context;
    private RadioStateDrawable defaultDrawable;
    private EventListener eventListener;
    private int iconBubbleType;
    private boolean isCheck;
    public boolean isDefaultIcon;
    private boolean isIndex;
    private boolean isLoadLottie;
    private boolean isLottieException;
    private boolean isNewIcon;
    private boolean isSetUpdateDrawable;
    private boolean isShowAnim;
    private boolean isUpdateIconLottieException;
    public String jsonPath;
    private String localLottiePath;
    private int lottieType;
    private int navigationId;
    private NavigationInfo navigationInfo;
    private ObjectAnimator objectAnimator;
    private ResourceItems resourceItems;
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat shortSdf;
    private RadioStateDrawable updateDrawable;

    public NavigationIconView(Context context) {
        super(context);
        this.isIndex = false;
        this.isNewIcon = false;
        this.jsonPath = "";
        this.isDefaultIcon = true;
        this.isLoadLottie = true;
        this.isLottieException = false;
        this.isUpdateIconLottieException = false;
        this.isCheck = false;
        this.isSetUpdateDrawable = false;
        this.localLottiePath = "";
        this.lottieType = 0;
        this.iconBubbleType = -1;
        this.shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        this.context = context;
        initListener();
    }

    private String getSdCardLottieContent(String str) {
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

    private void initListener() {
        this.eventListener = new EventListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationIconView.1
            @Override // com.jingdong.common.unification.navigationbar.newbar.EventListener
            public void onEvent() {
                if (NavigationBase.getInstance().mCurrentIndex != NavigationIconView.this.navigationId) {
                    if (Log.D) {
                        Log.d(NavigationIconView.TAG, "mCurrentIndex=" + NavigationBase.getInstance().mCurrentIndex + " navigationId=" + NavigationIconView.this.navigationId);
                    }
                    if (NavigationIconView.this.enableRefresh()) {
                        NavigationIconView.this.setLottieDefault();
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLottieDefault() {
        try {
            if (this.isLoadLottie) {
                if (this.isDefaultIcon) {
                    this.jsonPath = this.localLottiePath;
                } else if (JDElderModeUtils.isElderMode()) {
                    ResourceItems resourceItems = this.resourceItems;
                    if (resourceItems != null) {
                        this.jsonPath = getSdCardLottieContent(resourceItems.getLocalResource());
                    }
                } else {
                    NavigationInfo navigationInfo = this.navigationInfo;
                    if (navigationInfo != null) {
                        this.jsonPath = getSdCardLottieContent(navigationInfo.lottiePath);
                    }
                }
                if (Log.D) {
                    Log.d("navigation-default", "isDefaultIcon=" + this.isDefaultIcon + " jsonPath=" + this.jsonPath);
                }
                if (!TextUtils.isEmpty(this.jsonPath)) {
                    setLottieFailureListener(false);
                    if (this.isDefaultIcon) {
                        setAnimation(this.jsonPath);
                    } else {
                        setAnimationFromJson(this.jsonPath, System.currentTimeMillis() + "");
                    }
                    this.isLoadLottie = false;
                } else {
                    throw new Exception("json \u6587\u4ef6\u5730\u5740null");
                }
            }
            if (!TextUtils.isEmpty(this.jsonPath) && this.isSetUpdateDrawable) {
                if (Log.D) {
                    Log.d("navigation-click", "\u91cd\u65b0\u8bbe\u7f6eLottie\u52a8\u753b");
                }
                if (this.isDefaultIcon) {
                    setAnimation(this.jsonPath);
                } else {
                    setAnimationFromJson(this.jsonPath, System.currentTimeMillis() + "");
                }
                this.isSetUpdateDrawable = false;
            }
            this.lottieType = 0;
            cancelAnimation();
            setProgress(0.0f);
        } catch (Exception e2) {
            RadioStateDrawable radioStateDrawable = this.defaultDrawable;
            if (radioStateDrawable != null) {
                setImageDrawable(radioStateDrawable);
            }
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    private void setLottieFailureListener(final boolean z) {
        try {
            setFailureListener(new LottieListener<Throwable>() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationIconView.5
                @Override // com.airbnb.lottie.LottieListener
                public void onResult(Throwable th) {
                    if (!NavigationIconView.this.isLottieException) {
                        ExceptionReporter.reportExceptionToBugly(new Exception("NavigationIconView_Lottie_Exception_" + NavigationIconView.this.navigationId + CartConstant.KEY_YB_INFO_LINK + th));
                    }
                    NavigationIconView.this.isLottieException = true;
                    if (z) {
                        if (NavigationIconView.this.clickDrawable != null) {
                            NavigationIconView navigationIconView = NavigationIconView.this;
                            navigationIconView.setImageDrawable(navigationIconView.clickDrawable);
                        }
                    } else if (NavigationIconView.this.defaultDrawable != null) {
                        NavigationIconView navigationIconView2 = NavigationIconView.this;
                        navigationIconView2.setImageDrawable(navigationIconView2.defaultDrawable);
                    }
                }
            });
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, "setLottieFailureListener-" + e2);
            }
        }
    }

    private void setStateDrawableListener() {
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        if (radioStateDrawable != null) {
            radioStateDrawable.setEventListener(this.eventListener);
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        try {
            super.draw(canvas);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "NavigationIconView-draw=" + e2.getMessage() + " isCheck=" + this.isCheck);
            }
            if (!this.isLottieException) {
                ExceptionReporter.reportExceptionToBugly(new Exception("NavigationIconView_draw_Exception_" + this.navigationId + CartConstant.KEY_YB_INFO_LINK + e2));
            }
            this.isLottieException = true;
            if (this.isCheck) {
                RadioStateDrawable radioStateDrawable = this.clickDrawable;
                if (radioStateDrawable != null) {
                    setImageDrawable(radioStateDrawable);
                    return;
                }
                return;
            }
            RadioStateDrawable radioStateDrawable2 = this.defaultDrawable;
            if (radioStateDrawable2 != null) {
                setImageDrawable(radioStateDrawable2);
            }
        }
    }

    public boolean enableAnim() {
        if (UnLottieUtils.enableLottie()) {
            return this.isDefaultIcon || this.isShowAnim;
        }
        return false;
    }

    public boolean enableRefresh() {
        return this.isDefaultIcon && UnLottieUtils.enableLottie();
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public int getLottieType() {
        return this.lottieType;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean isDefaultIcon() {
        return this.isDefaultIcon;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean isIndex() {
        return this.isIndex;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean isNewIconState() {
        return this.isNewIcon;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void isShowAnim(boolean z) {
        this.isShowAnim = z;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setBubbleIcon(final Bitmap bitmap, final String str, String str2, final int i2, final NavigationParam navigationParam, final AnimationEndListener animationEndListener) {
        int i3;
        if (bitmap != null) {
            try {
                this.iconBubbleType = i2;
                this.isNewIcon = true;
                int i4 = navigationParam == null ? 0 : navigationParam.bucketType;
                this.bucketType = i4;
                if (i4 != 1) {
                    if (navigationParam != null && ((i3 = navigationParam.dynamicType) == 1 || i3 == 3)) {
                        this.objectAnimator = NavigationUtils.startRotationAnimation(this, new AnimationEndListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationIconView.2
                            @Override // com.jingdong.common.unification.navigationbar.AnimationEndListener
                            public void onAnimationEnd() {
                                AnimationEndListener animationEndListener2 = animationEndListener;
                                if (animationEndListener2 != null) {
                                    animationEndListener2.onAnimationEnd();
                                }
                            }
                        }, new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationIconView.3
                            boolean isSet = false;

                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                if (OKLog.D) {
                                    OKLog.d("TEST", "  rotationY onAnimationUpdate getAnimatedValue : " + valueAnimator.getAnimatedValue());
                                }
                                if (!(valueAnimator.getAnimatedValue() instanceof Float) || ((Float) valueAnimator.getAnimatedValue()).floatValue() < -90.0f || this.isSet) {
                                    return;
                                }
                                this.isSet = true;
                                NavigationIconView.this.setImageDrawable(new RadioStateDrawable(NavigationIconView.this.context, bitmap, str, false, true, i2, navigationParam));
                            }
                        });
                    } else {
                        setImageDrawable(new RadioStateDrawable(this.context, bitmap, str, false, true, i2, navigationParam));
                        if (navigationParam != null && navigationParam.dynamicType == 2 && animationEndListener != null) {
                            animationEndListener.onAnimationEnd();
                        }
                    }
                }
                NavigationBase.getInstance().buttonIconMarketExp(this.context, str2);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(TAG, "setBubbleIcon=" + e2.toString());
                }
            }
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setClick(boolean z) {
        RadioStateDrawable radioStateDrawable;
        if (Log.D) {
            Log.d("navigation-click", "loadAnime=" + z + " isLottieException=" + this.isLottieException + " enableAnim()=" + enableAnim() + " isLoadLottie=" + this.isLoadLottie + "  " + this.navigationId);
        }
        if (this.isNewIcon && this.bucketType != 1 && this.iconBubbleType == 1 && (radioStateDrawable = this.defaultDrawable) != null) {
            setImageDrawable(radioStateDrawable);
        }
        this.isCheck = true;
        this.isNewIcon = false;
        if (!this.isLottieException && enableAnim() && z) {
            try {
                if (this.isLoadLottie) {
                    if (this.isDefaultIcon) {
                        this.jsonPath = this.localLottiePath;
                    } else if (JDElderModeUtils.isElderMode()) {
                        ResourceItems resourceItems = this.resourceItems;
                        if (resourceItems != null) {
                            this.jsonPath = getSdCardLottieContent(resourceItems.getLocalResource());
                        }
                    } else {
                        NavigationInfo navigationInfo = this.navigationInfo;
                        if (navigationInfo != null) {
                            this.jsonPath = getSdCardLottieContent(navigationInfo.lottiePath);
                        }
                    }
                    if (Log.D) {
                        Log.d("navigation-click", "isDefaultIcon=" + this.isDefaultIcon + " jsonPath=" + this.jsonPath);
                    }
                    if (!TextUtils.isEmpty(this.jsonPath)) {
                        setLottieFailureListener(true);
                        if (this.isDefaultIcon) {
                            setAnimation(this.jsonPath);
                        } else {
                            setAnimationFromJson(this.jsonPath, System.currentTimeMillis() + "");
                        }
                        this.isLoadLottie = false;
                    } else {
                        throw new Exception("json \u6587\u4ef6\u5730\u5740null");
                    }
                }
                if (!TextUtils.isEmpty(this.jsonPath) && this.isSetUpdateDrawable) {
                    if (Log.D) {
                        Log.d("navigation-click", "\u91cd\u65b0\u8bbe\u7f6eLottie\u52a8\u753b");
                    }
                    if (this.isDefaultIcon) {
                        setAnimation(this.jsonPath);
                    } else {
                        setAnimationFromJson(this.jsonPath, System.currentTimeMillis() + "");
                    }
                    this.isSetUpdateDrawable = false;
                }
                this.lottieType = 1;
                playAnimation();
            } catch (Exception e2) {
                RadioStateDrawable radioStateDrawable2 = this.clickDrawable;
                if (radioStateDrawable2 != null) {
                    setImageDrawable(radioStateDrawable2);
                }
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        } else if (this.clickDrawable != null) {
            if (Log.D) {
                Log.d("navigation-click", "clickDrawable");
            }
            setImageDrawable(this.clickDrawable);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setDefault(boolean z) {
        if (Log.D) {
            Log.d("navigation-default", "loadAnime=" + z + " isLottieException=" + this.isLottieException + " enableAnim()=" + enableAnim() + " isLoadLottie=" + this.isLoadLottie + "  " + this.navigationId);
        }
        this.isCheck = false;
        this.isNewIcon = false;
        if (!this.isLottieException && enableAnim() && z) {
            setLottieDefault();
            return;
        }
        if (Log.D) {
            Log.d("navigation-default", "defaultDrawable");
        }
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        if (radioStateDrawable != null) {
            setImageDrawable(radioStateDrawable);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setIndex(boolean z) {
        this.isIndex = z;
        if (z) {
            setBackgroundDrawable(this.clickDrawable);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setIsDefaultIcon(boolean z) {
        this.isDefaultIcon = z;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setNavigationId(int i2) {
        this.navigationId = i2;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setNavigationInfo(NavigationInfo navigationInfo) {
        this.navigationInfo = navigationInfo;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setResourceItems(ResourceItems resourceItems) {
        this.resourceItems = resourceItems;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setState(String str, String str2, String str3, boolean z, boolean z2) {
        this.isDefaultIcon = z;
        if (z) {
            setState(str, Integer.parseInt(str2), Integer.parseInt(str3), "");
            return;
        }
        this.defaultDrawable = new RadioStateDrawable(this.context, str2, str, false, z2);
        this.clickDrawable = new RadioStateDrawable(this.context, str3, str, true, z2);
        setStateDrawableListener();
    }

    public void setUpdateIconLottie(String str) {
        this.isSetUpdateDrawable = true;
        if (UnLottieUtils.enableLottie() && !TextUtils.isEmpty(str)) {
            if (Log.D) {
                Log.d(TAG, "setUpdateIconLottie-lottiePath=" + str);
            }
            try {
                String sdCardLottieContent = getSdCardLottieContent(str);
                if (!TextUtils.isEmpty(sdCardLottieContent)) {
                    setFailureListener(new LottieListener<Throwable>() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationIconView.4
                        @Override // com.airbnb.lottie.LottieListener
                        public void onResult(Throwable th) {
                            if (!NavigationIconView.this.isUpdateIconLottieException) {
                                ExceptionReporter.reportExceptionToBugly(new Exception("NavigationIconView_setUpdateIconLottie_Exception_" + th));
                            }
                            NavigationIconView.this.isUpdateIconLottieException = true;
                            if (NavigationIconView.this.updateDrawable != null) {
                                NavigationIconView navigationIconView = NavigationIconView.this;
                                navigationIconView.setImageDrawable(navigationIconView.updateDrawable);
                            }
                        }
                    });
                    setAnimationFromJson(sdCardLottieContent, System.currentTimeMillis() + "");
                    playAnimation();
                    return;
                }
                throw new Exception("json \u6587\u4ef6\u5730\u5740null");
            } catch (Exception e2) {
                RadioStateDrawable radioStateDrawable = this.updateDrawable;
                if (radioStateDrawable != null) {
                    setImageDrawable(radioStateDrawable);
                }
                if (Log.E) {
                    e2.printStackTrace();
                    return;
                }
                return;
            }
        }
        RadioStateDrawable radioStateDrawable2 = this.updateDrawable;
        if (radioStateDrawable2 != null) {
            setImageDrawable(radioStateDrawable2);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean setUpdateState(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(str2);
        if (Log.D) {
            Log.d(TAG, "setUpdateState-bitmap=" + decodeFile);
        }
        if (decodeFile == null) {
            return false;
        }
        this.updateDrawable = new RadioStateDrawable(this.context, decodeFile, str, false);
        setUpdateIconLottie(str3);
        return true;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void showBubbleLottie(String str, Animator.AnimatorListener animatorListener, String str2, int i2) {
        if (this.isLottieException || this.isCheck) {
            return;
        }
        try {
            if (this.navigationInfo != null) {
                this.jsonPath = getSdCardLottieContent(str);
            }
            if (!TextUtils.isEmpty(this.jsonPath)) {
                this.isNewIcon = true;
                this.bucketType = i2;
                if (i2 != 1) {
                    this.isLoadLottie = true;
                    setLottieFailureListener(true);
                    setAnimationFromJson(this.jsonPath, System.currentTimeMillis() + "");
                    this.lottieType = 4;
                    playAnimation();
                    if (animatorListener != null) {
                        addAnimatorListener(animatorListener);
                    }
                }
                NavigationBase.getInstance().buttonIconMarketExp(this.context, str2);
                return;
            }
            throw new Exception("json \u6587\u4ef6\u5730\u5740null");
        } catch (Exception e2) {
            this.isLoadLottie = true;
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void showEffect(int i2) {
        String str;
        int skinType = UnCustomThemeHelper.getInstance().getSkinType();
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("isLottieException=");
            sb.append(this.isLottieException);
            sb.append(" isElderMode()=");
            sb.append(!JDElderModeUtils.isElderMode());
            sb.append(" isCheck=");
            sb.append(!this.isCheck);
            sb.append(" skinType=");
            sb.append(skinType);
            sb.append(" isDefaultIcon ");
            sb.append(this.isDefaultIcon);
            Log.d("navigation", sb.toString());
        }
        if (this.isLottieException || JDElderModeUtils.isElderMode() || this.isCheck) {
            return;
        }
        if (skinType == 2 || skinType == 0) {
            try {
                NavigationInfo navigationInfo = this.navigationInfo;
                if (navigationInfo != null) {
                    if (Log.D) {
                        Log.d("navigation", navigationInfo.toString());
                    }
                    if (i2 == 0) {
                        str = this.navigationInfo.effectStartLottiePath;
                    } else {
                        str = this.navigationInfo.effectEndLottiePath;
                    }
                    this.jsonPath = getSdCardLottieContent(str);
                }
                if (!TextUtils.isEmpty(this.jsonPath)) {
                    this.isLoadLottie = true;
                    setLottieFailureListener(true);
                    setAnimationFromJson(this.jsonPath, System.currentTimeMillis() + "");
                    this.lottieType = 3;
                    playAnimation();
                    return;
                }
                throw new Exception("json \u6587\u4ef6\u5730\u5740null");
            } catch (Exception e2) {
                this.isLoadLottie = true;
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void showMarketingEffect(Animator.AnimatorListener animatorListener) {
        if (this.isLottieException || this.isCheck) {
            return;
        }
        try {
            NavigationInfo navigationInfo = this.navigationInfo;
            if (navigationInfo != null) {
                this.jsonPath = getSdCardLottieContent(navigationInfo.marketingLottiePath);
            }
            if (!TextUtils.isEmpty(this.jsonPath)) {
                this.isLoadLottie = true;
                setLottieFailureListener(true);
                setAnimationFromJson(this.jsonPath, System.currentTimeMillis() + "");
                this.lottieType = 2;
                playAnimation();
                if (animatorListener != null) {
                    addAnimatorListener(animatorListener);
                }
                NavigationConstants.navigationMarketingShow = true;
                SimpleDateFormat simpleDateFormat = this.shortSdf;
                Date parse = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                if (parse != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(this.navigationInfo.marketingId, parse.getTime());
                    SharedPreferencesUtil.putString(NavigationConstants.NAVIGATION_MARKETING_ID, jSONObject.toString());
                }
                NavigationBase.getInstance().buttonMarketExp(getContext(), "2", this.navigationInfo);
                return;
            }
            throw new Exception("json \u6587\u4ef6\u5730\u5740null");
        } catch (Exception e2) {
            this.isLoadLottie = true;
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setState(String str, int i2, int i3, String str2) {
        this.isDefaultIcon = true;
        this.localLottiePath = str2;
        this.defaultDrawable = new RadioStateDrawable(this.context, i2, str);
        this.clickDrawable = new RadioStateDrawable(this.context, i3, str);
        setStateDrawableListener();
    }

    public NavigationIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isIndex = false;
        this.isNewIcon = false;
        this.jsonPath = "";
        this.isDefaultIcon = true;
        this.isLoadLottie = true;
        this.isLottieException = false;
        this.isUpdateIconLottieException = false;
        this.isCheck = false;
        this.isSetUpdateDrawable = false;
        this.localLottiePath = "";
        this.lottieType = 0;
        this.iconBubbleType = -1;
        this.shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        this.context = context;
        initListener();
    }

    public NavigationIconView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isIndex = false;
        this.isNewIcon = false;
        this.jsonPath = "";
        this.isDefaultIcon = true;
        this.isLoadLottie = true;
        this.isLottieException = false;
        this.isUpdateIconLottieException = false;
        this.isCheck = false;
        this.isSetUpdateDrawable = false;
        this.localLottiePath = "";
        this.lottieType = 0;
        this.iconBubbleType = -1;
        this.shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        this.context = context;
        initListener();
    }
}
