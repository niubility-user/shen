package com.jingdong.common.unification.navigationbar.newbar;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.skin.lib.bean.ResourceItems;
import com.jingdong.common.R;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.AnimationEndListener;
import com.jingdong.common.unification.navigationbar.IButtonAction;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.navigationbar.NavigationParam;
import com.jingdong.common.unification.navigationbar.NavigationTabLocationEntry;
import com.jingdong.common.unification.navigationbar.utils.NavigationUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class NavigationButton extends FrameLayout {
    private IButtonAction action;
    private Animator.AnimatorListener animatorListener;
    public boolean bigIconTag;
    private int bubbleLottieType;
    public boolean isClick;
    private boolean isClicked;
    private boolean isIndex;
    public boolean isJumpM;
    private boolean isSetUpdateIcon;
    private String label;
    public String mUrl;
    private INaviIcon naviIconView;
    private TextView naviText;
    private int navigationId;
    private NavigationInfo navigationInfo;
    private ObjectAnimator objectAnimator;
    private RedPointView redPoint;
    private ResourceItems resourceItems;
    public String url;

    public NavigationButton(Context context, int i2) {
        super(context);
        this.isIndex = false;
        this.isJumpM = false;
        this.bigIconTag = false;
        this.isClick = false;
        this.isSetUpdateIcon = false;
        this.bubbleLottieType = -1;
        this.isClicked = false;
        this.animatorListener = new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationCancel-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                    NavigationButton.this.naviText.setVisibility(0);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationEnd-------");
                }
                boolean z = true;
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                    NavigationButton.this.setDefault(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4) {
                    if (NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                        NavigationButton.this.naviText.setVisibility(0);
                    }
                    if (NavigationBase.getInstance().angleSwitch() && NavigationButton.this.getButtonState() && NavigationButton.this.getStateController() != null && !TextUtils.isEmpty(NavigationButton.this.getStateController().getButtonLabel())) {
                        NavigationButton.this.getStateController().setButtonLabel("");
                    }
                    NavigationButton.this.setDefault(false);
                    try {
                        if (NavigationBase.getInstance().getBubbleState() != 1) {
                            z = false;
                        }
                        NavigationConstants.BUBBLE_STUTE_BEFOR = z;
                        if (z) {
                            NavigationBase.getInstance().hideBubble(false);
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e("NavigationButton", "NavigationButton==" + e2);
                        }
                    }
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationStart-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 0) {
                    NavigationButton.this.naviText.setVisibility(8);
                }
            }
        };
        this.navigationId = i2;
        init();
    }

    private void init() {
        FrameLayout.inflate(getContext(), R.layout.navigation_button, this);
        this.naviIconView = (INaviIcon) findViewById(R.id.button);
        this.naviText = (TextView) findViewById(R.id.navigation_text);
        RedPointView redPointView = (RedPointView) findViewById(R.id.redpoint);
        this.redPoint = redPointView;
        int i2 = this.navigationId;
        boolean z = this.bigIconTag;
        NavigationInfo navigationInfo = this.navigationInfo;
        redPointView.setState(i2, z, navigationInfo != null && navigationInfo.angleSwitch == 1);
        if (this.navigationInfo != null) {
            if (this.redPoint.getStateController() != null) {
                this.redPoint.getStateController().functionId = this.navigationInfo.functionId;
            }
            if (this.redPoint.getTabShowNew() != null) {
                this.redPoint.getTabShowNew().functionId = this.navigationInfo.functionId;
            }
        }
        setNavigationId(this.navigationId);
        NavigationInfo navigationInfo2 = this.navigationInfo;
        if (navigationInfo2 == null || !"1".equals(navigationInfo2.bigIconIsOpen)) {
            return;
        }
        NavigationBase.getInstance().buttonMarketExp(getContext(), "1", this.navigationInfo);
    }

    private void initSeparateText(boolean z) {
        INaviIcon iNaviIcon = this.naviIconView;
        if (iNaviIcon == null || iNaviIcon.isDefaultIcon() || JDElderModeUtils.isElderMode() || this.naviText == null) {
            return;
        }
        NavigationInfo navigationInfo = getNavigationInfo();
        try {
            if (navigationInfo != null && !TextUtils.isEmpty(navigationInfo.cutLabelName) && !TextUtils.isEmpty(navigationInfo.tabNameSelected) && !"1".equals(navigationInfo.bigIconIsOpen)) {
                if (this.naviText.getVisibility() == 8) {
                    this.naviText.setVisibility(0);
                }
                if (z) {
                    this.naviText.setText(navigationInfo.tabNameSelected);
                    this.naviText.setTextColor(Color.parseColor(navigationInfo.optLabelColor));
                    return;
                }
                this.naviText.setText(navigationInfo.cutLabelName);
                this.naviText.setTextColor(Color.parseColor(navigationInfo.labelColor));
            } else if (this.naviText.getVisibility() == 0) {
                this.naviText.setVisibility(8);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x006e, code lost:
        if (((float) (r0.getTime() - r2)) >= 8.64E7f) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean isTimeToShow() {
        String string;
        NavigationInfo navigationInfo = this.navigationInfo;
        boolean z = false;
        if (navigationInfo != null && !TextUtils.isEmpty(navigationInfo.marketingId)) {
            Date date = new Date();
            try {
                string = SharedPreferencesUtil.getString(NavigationConstants.NAVIGATION_MARKETING_ID, "");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject = new JSONObject(string);
                String next = jSONObject.keys().next();
                if (this.navigationInfo.marketingId.equals(next)) {
                    long j2 = jSONObject.getLong(next);
                    int i2 = this.navigationInfo.marketingPlayTimes;
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 == 4 && !NavigationConstants.navigationMarketingShow) {
                                }
                            } else if (((float) (date.getTime() - j2)) >= 6.048E8f) {
                            }
                        }
                        e2.printStackTrace();
                    }
                }
            }
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOrHideText(boolean z) {
        NavigationInfo navigationInfo;
        if (this.naviText == null || (navigationInfo = this.navigationInfo) == null || !"0".equals(navigationInfo.bigIconIsOpen) || 1 != this.navigationInfo.bigLottieFlag) {
            return;
        }
        if (z) {
            if (this.naviText.getVisibility() == 8) {
                this.naviText.setVisibility(0);
            }
        } else if (this.naviText.getVisibility() == 0) {
            this.naviText.setVisibility(8);
        }
    }

    public void changeTabIconFromBubble(Bitmap bitmap, int i2, String str, final NavigationParam navigationParam) {
        TextView textView;
        int i3;
        NavigationInfo navigationInfo;
        try {
            int skinType = UnCustomThemeHelper.getInstance().getSkinType();
            RedPointView redPointView = this.redPoint;
            if (redPointView != null) {
                redPointView.setNavigationParam(navigationParam);
            }
            if (this.isClicked || bitmap == null) {
                return;
            }
            if (skinType == 2 || skinType == 0) {
                int i4 = navigationParam == null ? 0 : navigationParam.bucketType;
                if (i2 == 0 && "1".equals(this.navigationInfo.bigIconIsOpen)) {
                    return;
                }
                if (i4 != 1) {
                    if (i2 == 1) {
                        if (this.naviText != null && (navigationInfo = this.navigationInfo) != null && "0".equals(navigationInfo.bigIconIsOpen) && this.naviText.getVisibility() == 0) {
                            this.naviText.setVisibility(8);
                        }
                    } else if (navigationParam != null && (((i3 = navigationParam.dynamicType) == 1 || i3 == 3) && TextUtils.equals("0", this.navigationInfo.bigIconIsOpen) && this.naviText.getVisibility() == 8)) {
                        this.naviText.setVisibility(0);
                    }
                }
                INaviIcon iNaviIcon = this.naviIconView;
                if (iNaviIcon != null) {
                    iNaviIcon.setBubbleIcon(bitmap, this.label, str, i2, navigationParam, new AnimationEndListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationButton.1
                        @Override // com.jingdong.common.unification.navigationbar.AnimationEndListener
                        public void onAnimationEnd() {
                            NavigationParam navigationParam2 = navigationParam;
                            if (navigationParam2 != null && !TextUtils.isEmpty(navigationParam2.angleText)) {
                                NavigationButton.this.getStateController().setButtonLabel(navigationParam.angleText);
                            }
                            if (NavigationButton.this.redPoint != null) {
                                NavigationButton.this.redPoint.setDefault();
                            }
                        }
                    });
                    if (i4 == 1 || navigationParam == null) {
                        return;
                    }
                    int i5 = navigationParam.dynamicType;
                    if ((i5 == 1 || i5 == 3) && (textView = this.naviText) != null) {
                        this.objectAnimator = NavigationUtils.startRotationAnimation(textView, null, new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationButton.2
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                if ((valueAnimator.getAnimatedValue() instanceof Float) && ((Float) valueAnimator.getAnimatedValue()).floatValue() >= 90.0f && TextUtils.equals("1", NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 0) {
                                    NavigationButton.this.naviText.setVisibility(8);
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e2) {
            if (Log.E) {
                Log.e("tianchuangxin1", "changeTabIconFromBubble====>" + e2.toString());
            }
        }
    }

    public IButtonAction getAction() {
        return this.action;
    }

    public boolean getButtonState() {
        INaviIcon iNaviIcon = this.naviIconView;
        if (iNaviIcon != null) {
            return iNaviIcon.isNewIconState();
        }
        return false;
    }

    public NavigationTabLocationEntry getIconLocation() {
        try {
            NavigationTabLocationEntry navigationTabLocationEntry = new NavigationTabLocationEntry();
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            int i2 = iArr[0];
            int i3 = iArr[1];
            navigationTabLocationEntry.topX = i2;
            navigationTabLocationEntry.topY = i3;
            navigationTabLocationEntry.height = getHeight();
            navigationTabLocationEntry.width = getWidth();
            if (Log.D) {
                Log.d("navigation-location", "====>" + navigationTabLocationEntry.toString());
            }
            return navigationTabLocationEntry;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("navigation-location", "====>" + e2.toString());
                return null;
            }
            return null;
        }
    }

    public String getLabel() {
        return this.label;
    }

    public int getNavigationId() {
        return this.navigationId;
    }

    public NavigationInfo getNavigationInfo() {
        INaviIcon iNaviIcon = this.naviIconView;
        if (iNaviIcon != null && !iNaviIcon.isDefaultIcon() && this.navigationInfo == null) {
            this.navigationInfo = UnCustomThemeHelper.getInstance().getNavigationInfoByNavigationId(this.navigationId);
            if (OKLog.D) {
                OKLog.d("NavigationButton", "getNavigationInfo()-\u4ece\u6570\u636e\u5e93\u4e2d\u8bfb\u53d6navigationInfo=" + this.navigationInfo);
            }
        }
        return this.navigationInfo;
    }

    public ResourceItems getResourceItems() {
        return this.resourceItems;
    }

    public StateController getStateController() {
        return this.redPoint.getStateController();
    }

    public TabShowNew getTabShowNew() {
        TabShowNew tabShowNew = this.redPoint.getTabShowNew();
        if (tabShowNew != null) {
            boolean z = false;
            if (getStateController() != null && !TextUtils.isEmpty(getStateController().getButtonLabel())) {
                z = true;
            }
            tabShowNew.hasAngle = z;
        }
        return this.redPoint.getTabShowNew();
    }

    public void isAdd() {
        NavigationInfo navigationInfo = this.navigationInfo;
        if (navigationInfo == null || navigationInfo.marketingAuto != 1) {
            return;
        }
        playMarketingEffect();
    }

    public boolean isIndex() {
        return this.isIndex;
    }

    public boolean isNeedChangeDefaultIcon() {
        TextView textView = this.naviText;
        if (textView != null && textView.getVisibility() == 8) {
            this.naviText.setVisibility(0);
        }
        return this.isSetUpdateIcon;
    }

    public void isShowAnim(boolean z) {
        this.naviIconView.isShowAnim(z);
    }

    public void playLottieFromBubble(String str, int i2, String str2, int i3) {
        NavigationInfo navigationInfo;
        try {
            int skinType = UnCustomThemeHelper.getInstance().getSkinType();
            if (this.isClicked || TextUtils.isEmpty(str) || (navigationInfo = this.navigationInfo) == null) {
                return;
            }
            if (skinType == 2 || skinType == 0) {
                if (i2 == 0 && "1".equals(navigationInfo.bigIconIsOpen)) {
                    return;
                }
                this.bubbleLottieType = i2;
                this.naviIconView.showBubbleLottie(str, this.animatorListener, str2, i3);
            }
        } catch (Exception e2) {
            if (Log.E) {
                Log.e("tianchuangxin1", "playLottieFromBubble====>" + e2.toString());
            }
        }
    }

    public void playMarketingEffect() {
        NavigationInfo navigationInfo;
        if (this.naviIconView == null || (navigationInfo = this.navigationInfo) == null || TextUtils.isEmpty(navigationInfo.marketingLottiePath)) {
            return;
        }
        int skinType = UnCustomThemeHelper.getInstance().getSkinType();
        if (JDElderModeUtils.isElderMode()) {
            return;
        }
        if ((skinType == 2 || skinType == 0) && isTimeToShow()) {
            this.naviIconView.showMarketingEffect(this.animatorListener);
        }
    }

    public void setButtonAction(IButtonAction iButtonAction) {
        this.action = iButtonAction;
    }

    public void setClick(boolean z) {
        this.naviIconView.setClick(z);
        initSeparateText(true);
        this.redPoint.setClick();
        this.isSetUpdateIcon = false;
        this.isClicked = true;
    }

    public void setDefault(boolean z) {
        this.naviIconView.setDefault(z);
        initSeparateText(false);
        this.redPoint.setDefault();
        this.isSetUpdateIcon = false;
    }

    public void setIndex(boolean z) {
        this.isIndex = z;
        this.naviIconView.setIndex(z);
    }

    public void setIsDefaultIcon(boolean z) {
        INaviIcon iNaviIcon = this.naviIconView;
        if (iNaviIcon != null) {
            iNaviIcon.setIsDefaultIcon(z);
        }
    }

    public void setNavigationId(int i2) {
        this.navigationId = i2;
        this.naviIconView.setNavigationId(i2);
    }

    public void setRedPointDefault() {
        this.redPoint.setDefault();
    }

    public void setRedPointNavigationParam(NavigationParam navigationParam) {
        if (this.redPoint.getStateController() != null) {
            this.redPoint.getStateController().setNavigationParam(navigationParam);
        }
    }

    public void showNavigationButtonEffect(int i2) {
        INaviIcon iNaviIcon = this.naviIconView;
        if (iNaviIcon != null) {
            iNaviIcon.showEffect(i2);
        }
    }

    public boolean updateIcon(String str, String str2) {
        if (this.naviIconView == null || TextUtils.isEmpty(str)) {
            return false;
        }
        boolean updateState = this.naviIconView.setUpdateState(this.label, str, str2);
        if (OKLog.D) {
            OKLog.d("NavigationButton", "updateIcon-isSetSuccess=" + updateState);
        }
        if (updateState) {
            TextView textView = this.naviText;
            if (textView != null && textView.getVisibility() == 0) {
                this.naviText.setVisibility(8);
            }
            this.isSetUpdateIcon = true;
        }
        return updateState;
    }

    public NavigationButton(Context context, int i2, String str, int i3, int i4, String str2) {
        this(context, i2);
        this.label = str;
        this.naviIconView.setState(str, i3, i4, str2);
    }

    public NavigationButton(Context context, int i2, String str, String str2, String str3, boolean z) {
        super(context);
        this.isIndex = false;
        this.isJumpM = false;
        this.bigIconTag = false;
        this.isClick = false;
        this.isSetUpdateIcon = false;
        this.bubbleLottieType = -1;
        this.isClicked = false;
        this.animatorListener = new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationCancel-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                    NavigationButton.this.naviText.setVisibility(0);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationEnd-------");
                }
                boolean z2 = true;
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                    NavigationButton.this.setDefault(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4) {
                    if (NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                        NavigationButton.this.naviText.setVisibility(0);
                    }
                    if (NavigationBase.getInstance().angleSwitch() && NavigationButton.this.getButtonState() && NavigationButton.this.getStateController() != null && !TextUtils.isEmpty(NavigationButton.this.getStateController().getButtonLabel())) {
                        NavigationButton.this.getStateController().setButtonLabel("");
                    }
                    NavigationButton.this.setDefault(false);
                    try {
                        if (NavigationBase.getInstance().getBubbleState() != 1) {
                            z2 = false;
                        }
                        NavigationConstants.BUBBLE_STUTE_BEFOR = z2;
                        if (z2) {
                            NavigationBase.getInstance().hideBubble(false);
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e("NavigationButton", "NavigationButton==" + e2);
                        }
                    }
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationStart-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 0) {
                    NavigationButton.this.naviText.setVisibility(8);
                }
            }
        };
        this.navigationId = i2;
        this.bigIconTag = z;
        this.label = str;
        init();
        this.naviIconView.setState(str, str2, str3, false, z);
    }

    public NavigationButton(Context context, int i2, String str, String str2, String str3, boolean z, NavigationInfo navigationInfo) {
        super(context);
        this.isIndex = false;
        this.isJumpM = false;
        this.bigIconTag = false;
        this.isClick = false;
        this.isSetUpdateIcon = false;
        this.bubbleLottieType = -1;
        this.isClicked = false;
        this.animatorListener = new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationCancel-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                    NavigationButton.this.naviText.setVisibility(0);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationEnd-------");
                }
                boolean z2 = true;
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                    NavigationButton.this.setDefault(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4) {
                    if (NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                        NavigationButton.this.naviText.setVisibility(0);
                    }
                    if (NavigationBase.getInstance().angleSwitch() && NavigationButton.this.getButtonState() && NavigationButton.this.getStateController() != null && !TextUtils.isEmpty(NavigationButton.this.getStateController().getButtonLabel())) {
                        NavigationButton.this.getStateController().setButtonLabel("");
                    }
                    NavigationButton.this.setDefault(false);
                    try {
                        if (NavigationBase.getInstance().getBubbleState() != 1) {
                            z2 = false;
                        }
                        NavigationConstants.BUBBLE_STUTE_BEFOR = z2;
                        if (z2) {
                            NavigationBase.getInstance().hideBubble(false);
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e("NavigationButton", "NavigationButton==" + e2);
                        }
                    }
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationStart-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 0) {
                    NavigationButton.this.naviText.setVisibility(8);
                }
            }
        };
        this.navigationId = i2;
        this.bigIconTag = z;
        this.navigationInfo = navigationInfo;
        this.label = str;
        this.url = navigationInfo != null ? navigationInfo.url : "";
        init();
        this.naviIconView.setNavigationInfo(getNavigationInfo());
        this.naviIconView.setState(str, str2, str3, false, z);
    }

    public NavigationButton(Context context, int i2, String str, String str2, String str3, boolean z, ResourceItems resourceItems) {
        super(context);
        this.isIndex = false;
        this.isJumpM = false;
        this.bigIconTag = false;
        this.isClick = false;
        this.isSetUpdateIcon = false;
        this.bubbleLottieType = -1;
        this.isClicked = false;
        this.animatorListener = new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NavigationButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationCancel-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                    NavigationButton.this.naviText.setVisibility(0);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationEnd-------");
                }
                boolean z2 = true;
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(true);
                    NavigationButton.this.setDefault(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4) {
                    if (NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 8) {
                        NavigationButton.this.naviText.setVisibility(0);
                    }
                    if (NavigationBase.getInstance().angleSwitch() && NavigationButton.this.getButtonState() && NavigationButton.this.getStateController() != null && !TextUtils.isEmpty(NavigationButton.this.getStateController().getButtonLabel())) {
                        NavigationButton.this.getStateController().setButtonLabel("");
                    }
                    NavigationButton.this.setDefault(false);
                    try {
                        if (NavigationBase.getInstance().getBubbleState() != 1) {
                            z2 = false;
                        }
                        NavigationConstants.BUBBLE_STUTE_BEFOR = z2;
                        if (z2) {
                            NavigationBase.getInstance().hideBubble(false);
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e("NavigationButton", "NavigationButton==" + e2);
                        }
                    }
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (Log.D) {
                    Log.d("tianchuangxin1", "-------onAnimationStart-------");
                }
                if (NavigationButton.this.naviIconView.getLottieType() == 2) {
                    NavigationButton.this.showOrHideText(false);
                } else if (NavigationButton.this.naviIconView.getLottieType() == 4 && NavigationButton.this.bubbleLottieType == 1 && NavigationButton.this.naviText != null && NavigationButton.this.navigationInfo != null && "0".equals(NavigationButton.this.navigationInfo.bigIconIsOpen) && NavigationButton.this.naviText.getVisibility() == 0) {
                    NavigationButton.this.naviText.setVisibility(8);
                }
            }
        };
        this.navigationId = i2;
        this.bigIconTag = z;
        this.resourceItems = resourceItems;
        this.label = str;
        init();
        this.naviIconView.setResourceItems(resourceItems);
        this.naviIconView.setState(str, str2, str3, false, z);
    }
}
