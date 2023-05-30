package com.jingdong.common.jdmiaosha.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.R;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jdmiaosha.entity.SupernatantEntiy;
import com.jingdong.common.jdmiaosha.listener.AccountWithCloseClickListener;
import com.jingdong.common.jdmiaosha.listener.NewAccountDisplayedListener;
import com.jingdong.common.jdmiaosha.listener.SupernatantClickListener;
import com.jingdong.common.jdmiaosha.listener.SupernatantDisplayedListener;
import com.jingdong.common.jdmiaosha.utils.JDMiaoShaUtils;
import com.jingdong.common.jdmiaosha.utils.UResize;
import com.jingdong.common.jdmiaosha.view.DragWithCloseImageView;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes5.dex */
public class MiaoshaFloatingView extends FrameLayout implements AbstractFloatingView {
    private static final String ACCOUNT_IMG = "accountImg";
    private static final int ANIMATION_TIME = 500;
    private static final float BIG_SUPERNATANT_ASPECT_RATIO = 3.5f;
    private static final float BIG_SUPERNATANT_IMG_SCALE = 0.1f;
    private static final float FLOATF = 0.0f;
    private static final String MIAOSHA_FUCENG_ID = "MIAOSHA_FUCENG_ID";
    private static final float NEWACCOUNT_MAX_TOP_PERCENT = 100.0f;
    private static final float NEWACCOUNT_SCALE = 1.2f;
    private static final float NEWACCOUNT_TENSION = 5.0f;
    private static final float NEWACCOUNT_TRANSLUCENT = 0.4f;
    private static final int PARABOLA_ANIM_DURATION = 2000;
    public static final int SUPERNATANT_CLOSE_ANIMA_TIME = 1000;
    private ObjectAnimator animA;
    private ObjectAnimator animAY;
    private ObjectAnimator animDisplay;
    private ObjectAnimator animHide;
    private AnimatorSet animSet;
    private ObjectAnimator animX;
    private ObjectAnimator animY;
    private SimpleDraweeView bigSupernatant;
    private SimpleDraweeView bigSupernatantImg;
    private View.OnClickListener bigSupernatantOnClickListener;
    private boolean canShowNewAccount;
    private Button closeButton;
    private View.OnClickListener closeButtonOnClickListener;
    private boolean closedNewAccount;
    private int formx;
    private int formy;
    private boolean hasSupernatantNotShow;
    private JumpEntity mFloatJumpEntity;
    private AccountWithCloseClickListener mNewAccountClickListener;
    private NewAccountDisplayedListener mNewAccountDisplayedListener;
    private DragWithCloseImageView mNewAccountView;
    SupernatantEntiy mSTE;
    private JumpEntity mSuperJumpEntity;
    private SupernatantClickListener mSupernatantClickListener;
    private SupernatantDisplayedListener mSupernatantDisplayedListener;
    private View mTotalOverflowView;
    private AnimatorSet newAccountAnimSet;
    private ObjectAnimator newAccountAnimX;
    private ObjectAnimator newAccountAnimY;
    private int newAccountInitialY;
    private boolean newAcctountHide;
    private boolean newAcctountReady;
    private int newAcctountX;
    private RelativeLayout overflowRLSupernatant;
    private String thisID;
    private static final int NEWACCOUNT_INITIAL_TOP = DPIUtil.dip2px(207.0f);
    private static final int BIG_SUPERNATANT_IMG_HEIGHT = getScaleHeightWithFullParent(1065.0f, 300.0f);
    private static final int BIG_SUPERNATANT_IMG_LEFT_MARGIN = DPIUtil.dip2px(9.0f);
    private static final int BIG_SUPERNATANT_MARGIN = DPIUtil.dip2px(10.0f);
    private static final float NEWACCOUNT_TRANSLATION_X = DPIUtil.dip2px(43.0f);
    private static final int NEWACCOUNT_WIDTH_HEIGHT = DPIUtil.dip2px(64.0f);
    private static final int NEWACCOUNT_CLOSE_WIDTH_HEIGHT = DPIUtil.dip2px(12.0f);
    private static final int NEWACCOUNT_RIGHT_MARGIN = DPIUtil.dip2px(5.0f);
    private static final int CLOSE_BTN_WIDTH_HEIGHT = UResize.getWidthByDesignValue720(40);
    private static final int CLOSE_BTN_LEFT_MARGIN = UResize.getWidthByDesignValue720(53);
    private static final int CLOSE_BTN_RIGHT_MARGIN = UResize.getWidthByDesignValue720(10);
    private static final int CLOSE_BTN_PADDING_TOP = DPIUtil.dip2px(3.0f);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class NullListener implements JDImageLoadingListener {
        NullListener() {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }
    }

    public MiaoshaFloatingView(@NonNull Context context) {
        this(context, null);
    }

    private void clearFloatData() {
        this.mSTE.clear();
        this.thisID = "";
        if (this.overflowRLSupernatant != null) {
            overflowRLSupernatantsetVisibility(4);
        }
    }

    public static int getScaleHeightWithFullParent(float f2, float f3) {
        return (int) ((f3 * (DPIUtil.getWidth() - (DPIUtil.dip2px(10.0f) * 2.0f))) / f2);
    }

    private void initView() {
        ImageUtil.inflate(getContext(), R.layout.layout_floating_view, this);
        DragWithCloseImageView dragWithCloseImageView = (DragWithCloseImageView) findViewById(R.id.id_miaosha_fragment_list_new_account_iv);
        this.mNewAccountView = dragWithCloseImageView;
        int i2 = this.newAccountInitialY;
        int i3 = NEWACCOUNT_WIDTH_HEIGHT;
        int i4 = NEWACCOUNT_CLOSE_WIDTH_HEIGHT;
        dragWithCloseImageView.initSize(i2, i3, i3, i4, i4);
        this.mNewAccountView.setDragViewWithCloseListener(new DragWithCloseImageView.DragViewWithCloseListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.1
            @Override // com.jingdong.common.jdmiaosha.view.DragWithCloseImageView.DragViewWithCloseListener
            public void onClickCloseView(View view) {
                MiaoshaFloatingView.this.closedNewAccount = true;
                MiaoshaFloatingView.this.setNewAccountVisibility(8);
                if (MiaoshaFloatingView.this.mNewAccountClickListener != null) {
                    MiaoshaFloatingView.this.mNewAccountClickListener.onClickCloseView(view, MiaoshaFloatingView.this.mFloatJumpEntity == null ? "" : MiaoshaFloatingView.this.mFloatJumpEntity.srv);
                }
            }

            @Override // com.jingdong.common.jdmiaosha.view.DragWithCloseImageView.DragViewWithCloseListener
            public void onClickDragView(View view) {
                if (MiaoshaFloatingView.this.newAcctountHide) {
                    JDMiaoShaUtils.sendClickDataWithExt("HandSeckill_NewFloatingTra", "HandSeckill_Main", MiaoshaFloatingView.this.mFloatJumpEntity != null ? MiaoshaFloatingView.this.mFloatJumpEntity.srv : "");
                    MiaoshaFloatingView.this.displayNewAccountByAmin();
                } else if (MiaoshaFloatingView.this.mFloatJumpEntity != null) {
                    JumpUtil.execJump(MiaoshaFloatingView.this.getContext(), MiaoshaFloatingView.this.mFloatJumpEntity, 0);
                    if (MiaoshaFloatingView.this.mNewAccountClickListener != null) {
                        MiaoshaFloatingView.this.mNewAccountClickListener.onClickDragView(view, MiaoshaFloatingView.this.mFloatJumpEntity != null ? MiaoshaFloatingView.this.mFloatJumpEntity.srv : "");
                    }
                }
            }
        });
    }

    private void resetNewAccountPosition(Activity activity) {
        ObjectAnimator objectAnimator = this.animHide;
        if (objectAnimator != null) {
            objectAnimator.end();
        }
        ObjectAnimator objectAnimator2 = this.animDisplay;
        if (objectAnimator2 != null) {
            objectAnimator2.end();
        }
        DragWithCloseImageView dragWithCloseImageView = this.mNewAccountView;
        if (dragWithCloseImageView == null || dragWithCloseImageView.getVisibility() != 0) {
            return;
        }
        int i2 = this.newAcctountX;
        if (i2 != 0) {
            this.mNewAccountView.setX(i2);
        }
        this.newAcctountX = (com.jingdong.sdk.utils.DPIUtil.getAppWidth(activity) - NEWACCOUNT_WIDTH_HEIGHT) - NEWACCOUNT_RIGHT_MARGIN;
    }

    private void resetSupernatant() {
        JDImageLoadingListener jDImageLoadingListener = new JDImageLoadingListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.4
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                MiaoshaFloatingView miaoshaFloatingView = MiaoshaFloatingView.this;
                SupernatantEntiy supernatantEntiy = miaoshaFloatingView.mSTE;
                supernatantEntiy.supernatantReady = true;
                if (supernatantEntiy.isShowSupernatant) {
                    return;
                }
                miaoshaFloatingView.showSupernatant();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str, View view) {
            }
        };
        JDImageLoadingListener jDImageLoadingListener2 = new JDImageLoadingListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.5
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                MiaoshaFloatingView miaoshaFloatingView = MiaoshaFloatingView.this;
                SupernatantEntiy supernatantEntiy = miaoshaFloatingView.mSTE;
                supernatantEntiy.supernatantImgReady = true;
                if (supernatantEntiy.isShowSupernatant) {
                    return;
                }
                miaoshaFloatingView.showSupernatant();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str, View view) {
            }
        };
        overflowRLSupernatantsetVisibility(4);
        setNewAccountVisibility(8);
        SupernatantEntiy supernatantEntiy = this.mSTE;
        supernatantEntiy.isShowSupernatant = false;
        supernatantEntiy.supernatantImgReady = false;
        supernatantEntiy.supernatantReady = false;
        JDImageUtils.displayImage(supernatantEntiy.supernatantUrl, this.bigSupernatant, jDImageLoadingListener);
        JDImageUtils.displayImage(this.mSTE.supernatantimgUrl, this.bigSupernatantImg, jDImageLoadingListener2);
    }

    private void setNewAccountIcoViewUrl(String str) {
        try {
            if (getContext() instanceof Activity) {
                resetNewAccountPosition((Activity) getContext());
            }
            this.mTotalOverflowView.setVisibility(0);
            this.mNewAccountView.setDragViewLoadingListener(new DragWithCloseImageView.DragViewLoadingListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.8
                @Override // com.jingdong.common.jdmiaosha.view.DragWithCloseImageView.DragViewLoadingListener
                public void onLoadingCancelled() {
                }

                @Override // com.jingdong.common.jdmiaosha.view.DragWithCloseImageView.DragViewLoadingListener
                public void onLoadingComplete() {
                    MiaoshaFloatingView miaoshaFloatingView = MiaoshaFloatingView.this;
                    SupernatantEntiy supernatantEntiy = miaoshaFloatingView.mSTE;
                    supernatantEntiy.newAccounttReady = true;
                    if (supernatantEntiy.bottomFloatViewObj == null || miaoshaFloatingView.hasSupernatantNotShow) {
                        if (MiaoshaFloatingView.this.canShowNewAccount) {
                            MiaoshaFloatingView.this.setNewAccountVisibility(0);
                            if (MiaoshaFloatingView.this.mNewAccountDisplayedListener != null) {
                                MiaoshaFloatingView.this.mNewAccountDisplayedListener.onDisplay(MiaoshaFloatingView.this.mFloatJumpEntity == null ? "" : MiaoshaFloatingView.this.mFloatJumpEntity.srv);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    MiaoshaFloatingView miaoshaFloatingView2 = MiaoshaFloatingView.this;
                    if (miaoshaFloatingView2.mSTE.isShowSupernatant) {
                        return;
                    }
                    miaoshaFloatingView2.showSupernatant();
                }

                @Override // com.jingdong.common.jdmiaosha.view.DragWithCloseImageView.DragViewLoadingListener
                public void onLoadingFailed() {
                    MiaoshaFloatingView miaoshaFloatingView = MiaoshaFloatingView.this;
                    if (miaoshaFloatingView.mSTE.bottomFloatViewObj == null) {
                        miaoshaFloatingView.setNewAccountVisibility(0);
                    }
                }

                @Override // com.jingdong.common.jdmiaosha.view.DragWithCloseImageView.DragViewLoadingListener
                public void onLoadingStarted() {
                    SupernatantEntiy supernatantEntiy = MiaoshaFloatingView.this.mSTE;
                    if (supernatantEntiy != null) {
                        supernatantEntiy.newAccounttReady = false;
                    }
                }
            });
            this.mNewAccountView.setDragViewUrl(str);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewAccountVisibility(int i2) {
        DragWithCloseImageView dragWithCloseImageView = this.mNewAccountView;
        if (dragWithCloseImageView == null) {
            return;
        }
        if (i2 == 0 && !this.closedNewAccount) {
            if (dragWithCloseImageView.getAlpha() == 0.0f) {
                this.mNewAccountView.setAlpha(1.0f);
                this.mNewAccountView.setClickable(true);
                return;
            }
            return;
        }
        dragWithCloseImageView.setAlpha(0.0f);
        this.mNewAccountView.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSupernatant() {
        try {
            SupernatantEntiy supernatantEntiy = this.mSTE;
            if (supernatantEntiy.newAccounttReady && supernatantEntiy.supernatantImgReady && supernatantEntiy.supernatantReady) {
                supernatantEntiy.isShowSupernatant = true;
                setNewAccountVisibility(8);
                overflowRLSupernatantsetVisibility(0);
                this.closeButton.setOnClickListener(this.closeButtonOnClickListener);
                this.bigSupernatant.setOnClickListener(this.bigSupernatantOnClickListener);
                SupernatantDisplayedListener supernatantDisplayedListener = this.mSupernatantDisplayedListener;
                if (supernatantDisplayedListener != null) {
                    JumpEntity jumpEntity = this.mSuperJumpEntity;
                    supernatantDisplayedListener.onDisplay(jumpEntity == null ? "" : jumpEntity.srv);
                }
                JDImageUtils.displayImage(this.mSTE.supernatantUrl, this.bigSupernatant, new NullListener());
                JDImageUtils.displayImage(this.mSTE.supernatantimgUrl, this.bigSupernatantImg, new NullListener());
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public boolean bSupernatantShow() {
        return this.mSTE.isShowSupernatant;
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void cancelAllAnim() {
        ObjectAnimator objectAnimator = this.animHide;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            this.animHide.cancel();
        }
        ObjectAnimator objectAnimator2 = this.animDisplay;
        if (objectAnimator2 != null) {
            objectAnimator2.removeAllListeners();
            this.animDisplay.cancel();
        }
        AnimatorSet animatorSet = this.animSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.animSet.cancel();
        }
        AnimatorSet animatorSet2 = this.newAccountAnimSet;
        if (animatorSet2 != null) {
            animatorSet2.removeAllListeners();
            this.newAccountAnimSet.cancel();
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void closeAnim(int i2) {
        if (this.mSTE.isShowSupernatant) {
            SharedPreferencesUtil.putString(MIAOSHA_FUCENG_ID + this.mSTE.supernatantID, "1");
            this.mSTE.isShowSupernatant = false;
            this.bigSupernatant.setVisibility(4);
            this.closeButton.setVisibility(8);
            this.bigSupernatantImg.setAlpha(1.0f);
            int x = (int) this.mNewAccountView.getX();
            int i3 = this.newAccountInitialY;
            if (this.formx == -1) {
                this.formx = this.bigSupernatantImg.getLeft();
            }
            if (this.formy == -1) {
                this.formy = this.bigSupernatantImg.getTop();
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mNewAccountView, "scaleX", NEWACCOUNT_SCALE, 1.0f);
            this.newAccountAnimX = ofFloat;
            ofFloat.setInterpolator(new OvershootInterpolator(5.0f));
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mNewAccountView, "scaleY", NEWACCOUNT_SCALE, 1.0f);
            this.newAccountAnimY = ofFloat2;
            ofFloat2.setInterpolator(new OvershootInterpolator(5.0f));
            this.newAccountAnimSet.play(this.newAccountAnimX).with(this.newAccountAnimY);
            this.newAccountAnimSet.setDuration(2000L);
            this.animY = ObjectAnimator.ofFloat(this.bigSupernatantImg, JshopConst.JSHOP_PROMOTIO_Y, this.formy, i3);
            this.animX = ObjectAnimator.ofFloat(this.bigSupernatantImg, JshopConst.JSHOP_PROMOTIO_X, this.formx, x);
            this.animY.setInterpolator(new OvershootInterpolator());
            this.animA = ObjectAnimator.ofFloat(this.bigSupernatantImg, "scaleX", 1.0f, BIG_SUPERNATANT_IMG_SCALE);
            this.animAY = ObjectAnimator.ofFloat(this.bigSupernatantImg, "scaleY", 1.0f, BIG_SUPERNATANT_IMG_SCALE);
            AnimatorSet animatorSet = new AnimatorSet();
            this.animSet = animatorSet;
            animatorSet.play(this.animY).with(this.animX).with(this.animA).with(this.animAY);
            this.animSet.setDuration(i2);
            this.animSet.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.6
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    try {
                        MiaoshaFloatingView.this.newAcctountReady = false;
                        if (MiaoshaFloatingView.this.mNewAccountView != null) {
                            MiaoshaFloatingView.this.newAccountAnimSet.start();
                        }
                        MiaoshaFloatingView.this.setNewAccountVisibility(0);
                        MiaoshaFloatingView.this.overflowRLSupernatantsetVisibility(4);
                        if (MiaoshaFloatingView.this.mNewAccountDisplayedListener != null) {
                            MiaoshaFloatingView.this.mNewAccountDisplayedListener.onDisplay(MiaoshaFloatingView.this.mFloatJumpEntity == null ? "" : MiaoshaFloatingView.this.mFloatJumpEntity.srv);
                        }
                        MiaoshaFloatingView.this.mSTE.clear();
                    } catch (Exception unused) {
                    }
                }
            });
            this.animSet.start();
            this.newAccountAnimSet.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.7
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    try {
                        MiaoshaFloatingView.this.newAcctountReady = true;
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void displayNewAccountByAmin() {
        DragWithCloseImageView dragWithCloseImageView;
        try {
            if (!this.newAcctountReady || this.newAcctountX == 0 || (dragWithCloseImageView = this.mNewAccountView) == null || dragWithCloseImageView.getVisibility() != 0 || this.mNewAccountView.getAlpha() == 0.0f || !this.newAcctountHide) {
                return;
            }
            ObjectAnimator objectAnimator = this.animHide;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            DragWithCloseImageView dragWithCloseImageView2 = this.mNewAccountView;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dragWithCloseImageView2, JshopConst.JSHOP_PROMOTIO_X, dragWithCloseImageView2.getX(), this.newAcctountX);
            this.animDisplay = ofFloat;
            ofFloat.setInterpolator(new DecelerateInterpolator());
            this.animDisplay.setDuration(500L);
            this.animDisplay.start();
            this.newAcctountHide = false;
            if (this.closedNewAccount) {
                return;
            }
            this.mNewAccountView.setAlpha(1.0f);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public ViewParent getParentView() {
        return getParent();
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public String getSuperEventSrv() {
        JumpEntity jumpEntity = this.mSuperJumpEntity;
        return jumpEntity == null ? "" : jumpEntity.srv;
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void hideNewAccountByAmin() {
        DragWithCloseImageView dragWithCloseImageView;
        try {
            if (!this.newAcctountReady || (dragWithCloseImageView = this.mNewAccountView) == null || dragWithCloseImageView.getVisibility() != 0 || this.mNewAccountView.getAlpha() == 0.0f || this.newAcctountHide) {
                return;
            }
            DragWithCloseImageView dragWithCloseImageView2 = this.mNewAccountView;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dragWithCloseImageView2, JshopConst.JSHOP_PROMOTIO_X, dragWithCloseImageView2.getX(), this.newAcctountX + NEWACCOUNT_TRANSLATION_X);
            this.animHide = ofFloat;
            ofFloat.setInterpolator(new DecelerateInterpolator());
            this.animHide.setDuration(500L);
            this.animHide.start();
            this.newAcctountHide = true;
            if (this.closedNewAccount) {
                return;
            }
            this.mNewAccountView.setAlpha(NEWACCOUNT_TRANSLUCENT);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void initNewAccountData(JDJSONObject jDJSONObject, int i2) {
        int optInt;
        if (jDJSONObject != null) {
            if (jDJSONObject.containsKey("areaDistance") && i2 != 0 && (optInt = jDJSONObject.optInt("areaDistance")) >= 0) {
                float f2 = optInt;
                if (f2 <= 100.0f) {
                    int i3 = (int) ((f2 / 100.0f) * (i2 - ((NEWACCOUNT_WIDTH_HEIGHT + NEWACCOUNT_CLOSE_WIDTH_HEIGHT) + CLOSE_BTN_PADDING_TOP)));
                    this.newAccountInitialY = i3;
                    this.mNewAccountView.setTopMargin(i3);
                }
            }
            String optString = jDJSONObject.optString(ACCOUNT_IMG);
            JDJSONObject jSONObject = jDJSONObject.getJSONObject("jump");
            if (jSONObject != null) {
                this.mFloatJumpEntity = (JumpEntity) JDJSON.parseObject(jSONObject.toString(), JumpEntity.class);
            }
            if (this.mNewAccountView != null) {
                setNewAccountIcoViewUrl(optString);
                return;
            }
            return;
        }
        setNewAccountVisibility(8);
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void initSupernatant() {
        this.overflowRLSupernatant = (RelativeLayout) findViewById(R.id.overflow_rl_supernatant);
        int i2 = R.id.app_big_supernatant;
        this.bigSupernatant = (SimpleDraweeView) findViewById(i2);
        int i3 = R.id.picture_supernatant;
        this.bigSupernatantImg = (SimpleDraweeView) findViewById(i3);
        this.bigSupernatantImg = (SimpleDraweeView) findViewById(i3);
        this.closeButton = (Button) findViewById(R.id.bt_close_supernatant);
        this.mTotalOverflowView = findViewById(R.id.total_overflow_layout);
        this.overflowRLSupernatant.setVisibility(0);
        this.bigSupernatant.setVisibility(0);
        this.bigSupernatantImg.setVisibility(0);
        this.closeButton.setVisibility(0);
        this.bigSupernatantImg.setAlpha(0.0f);
        this.hasSupernatantNotShow = false;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        layoutParams.addRule(14, -1);
        int i4 = BIG_SUPERNATANT_MARGIN;
        layoutParams.setMargins(i4, 0, i4, i4);
        this.bigSupernatant.setAspectRatio(BIG_SUPERNATANT_ASPECT_RATIO);
        this.bigSupernatant.setLayoutParams(layoutParams);
        this.bigSupernatantOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    MiaoshaFloatingView.this.closeAnim(1);
                    if (MiaoshaFloatingView.this.mSuperJumpEntity != null) {
                        JumpUtil.execJump(MiaoshaFloatingView.this.getContext(), MiaoshaFloatingView.this.mSuperJumpEntity, 0);
                    }
                    if (MiaoshaFloatingView.this.mSupernatantClickListener != null) {
                        MiaoshaFloatingView.this.mSupernatantClickListener.onClick(view, false, MiaoshaFloatingView.this.mSuperJumpEntity == null ? "" : MiaoshaFloatingView.this.mSuperJumpEntity.srv);
                    }
                } catch (Exception unused) {
                }
            }
        };
        int i5 = BIG_SUPERNATANT_IMG_HEIGHT;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i5, i5);
        layoutParams2.addRule(6, i2);
        layoutParams2.addRule(5, i2);
        layoutParams2.setMargins(BIG_SUPERNATANT_IMG_LEFT_MARGIN, 0, 0, 0);
        this.bigSupernatantImg.setLayoutParams(layoutParams2);
        resetCloseButton(true);
        this.closeButtonOnClickListener = new View.OnClickListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoshaFloatingView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    MiaoshaFloatingView.this.closeAnim(1000);
                    if (MiaoshaFloatingView.this.mSupernatantClickListener != null) {
                        MiaoshaFloatingView.this.mSupernatantClickListener.onClick(view, true, MiaoshaFloatingView.this.mSuperJumpEntity == null ? "" : MiaoshaFloatingView.this.mSuperJumpEntity.srv);
                    }
                } catch (Exception unused) {
                }
            }
        };
        overflowRLSupernatantsetVisibility(4);
        setNewAccountVisibility(4);
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void initSupernatantData(JDJSONObject jDJSONObject) {
        try {
            if (jDJSONObject == null) {
                clearFloatData();
                return;
            }
            SupernatantEntiy supernatantEntiy = this.mSTE;
            supernatantEntiy.bottomFloatViewObj = jDJSONObject;
            supernatantEntiy.supernatantID = jDJSONObject.getString("id");
            if (this.bigSupernatant == null || TextUtils.isEmpty(this.mSTE.bottomFloatViewObj.getString(ACCOUNT_IMG)) || this.thisID.equals(this.mSTE.supernatantID)) {
                return;
            }
            if (TextUtils.isEmpty(SharedPreferencesUtil.getString(MIAOSHA_FUCENG_ID + this.mSTE.supernatantID, ""))) {
                this.closedNewAccount = false;
                initSupernatant();
                overflowRLSupernatantsetVisibility(4);
                setNewAccountVisibility(4);
                this.thisID = this.mSTE.bottomFloatViewObj.getString("id");
                SupernatantEntiy supernatantEntiy2 = this.mSTE;
                supernatantEntiy2.supernatantJump = supernatantEntiy2.bottomFloatViewObj.getJSONObject("jump");
                SupernatantEntiy supernatantEntiy3 = this.mSTE;
                supernatantEntiy3.supernatantUrl = supernatantEntiy3.bottomFloatViewObj.getString(ACCOUNT_IMG);
                SupernatantEntiy supernatantEntiy4 = this.mSTE;
                supernatantEntiy4.supernatantimgUrl = supernatantEntiy4.bottomFloatViewObj.getString("accountSmallImg");
                this.mSuperJumpEntity = (JumpEntity) JDJSON.parseObject(this.mSTE.supernatantJump.toString(), JumpEntity.class);
                this.mSTE.supernatantID = this.thisID;
                this.mTotalOverflowView.setVisibility(0);
                resetSupernatant();
                return;
            }
            this.hasSupernatantNotShow = true;
        } catch (Exception unused) {
            clearFloatData();
        }
    }

    public void overflowRLSupernatantsetVisibility(int i2) {
        if (i2 == 0) {
            this.overflowRLSupernatant.setAlpha(1.0f);
            this.closeButton.setClickable(true);
            this.bigSupernatant.setClickable(true);
        }
        if (i2 == 4) {
            this.overflowRLSupernatant.setAlpha(0.0f);
            this.closeButton.setClickable(false);
            this.bigSupernatant.setClickable(false);
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void resetCloseButton(boolean z) {
        Button button = this.closeButton;
        if (button == null) {
            return;
        }
        if (z || button.getVisibility() == 0) {
            int i2 = CLOSE_BTN_WIDTH_HEIGHT;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i2);
            int i3 = R.id.app_big_supernatant;
            layoutParams.addRule(6, i3);
            layoutParams.addRule(7, i3);
            layoutParams.setMargins(0, CLOSE_BTN_LEFT_MARGIN, CLOSE_BTN_RIGHT_MARGIN, 0);
            this.closeButton.setLayoutParams(layoutParams);
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void resetNewAccountIcoView(Activity activity) {
        if (this.mNewAccountView != null) {
            this.newAcctountX = (com.jingdong.sdk.utils.DPIUtil.getAppWidth(activity) - NEWACCOUNT_WIDTH_HEIGHT) - NEWACCOUNT_RIGHT_MARGIN;
        }
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void setAccountWithCloseClickListener(AccountWithCloseClickListener accountWithCloseClickListener) {
        this.mNewAccountClickListener = accountWithCloseClickListener;
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void setCanShow(boolean z) {
        this.canShowNewAccount = z;
        setNewAccountVisibility((!z || bSupernatantShow()) ? 8 : 0);
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void setOnNewAccountDisplayedListener(NewAccountDisplayedListener newAccountDisplayedListener) {
        this.mNewAccountDisplayedListener = newAccountDisplayedListener;
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void setOnSupernatantClickListener(SupernatantClickListener supernatantClickListener) {
        this.mSupernatantClickListener = supernatantClickListener;
    }

    @Override // com.jingdong.common.jdmiaosha.view.AbstractFloatingView
    public void setOnSupernatantDisplayedListener(SupernatantDisplayedListener supernatantDisplayedListener) {
        this.mSupernatantDisplayedListener = supernatantDisplayedListener;
    }

    public MiaoshaFloatingView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiaoshaFloatingView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.newAcctountHide = false;
        this.newAcctountReady = true;
        this.hasSupernatantNotShow = false;
        this.newAcctountX = 0;
        this.mSTE = new SupernatantEntiy();
        this.formx = -1;
        this.formy = -1;
        this.newAccountAnimSet = new AnimatorSet();
        this.animSet = new AnimatorSet();
        this.thisID = "thisID";
        this.closedNewAccount = false;
        this.newAccountInitialY = NEWACCOUNT_INITIAL_TOP;
        this.canShowNewAccount = true;
        initView();
    }
}
