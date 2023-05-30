package com.jingdong.common.unification.translation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.unification.R;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.translation.JDTransitionManager;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class JDTransitionAnimView extends FrameLayout {
    private final long ANIM_DURATION;
    private final String LOADING_VIEW_TAG;
    private final String TRANSITION_VIEW_TAG;
    private final int TRANSLATION_Y;
    private Activity activity;
    private final JDTransitionAnimHelper animHelper;
    private Drawable backAlphaDrawable;
    private View backView;
    private View bgView;
    private SimpleDraweeView bigPicView;
    private List<Object> cacheAnim;
    private View divideLineView;
    private Handler handler;
    private boolean isAnimEndReset;
    private boolean isRemoveAnimEndReset;
    private JDTransitionManager.ITransitionAnimStateListener listener;
    private JDTransitionManager manager;
    private View moreView;
    private View placeHolderBottomView;
    private View placeHolderTopView;
    private View placeHolderView;
    private Bitmap postViewCacheBitmap;
    private Runnable removeAnimEndRunnable;
    private View rootView;
    private View shareView;
    private int statusBarHeight;
    private Runnable transitionAnimEndRunnable;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class RemoveAnimEndResetRunnable implements Runnable {
        private RemoveAnimEndResetRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JDTransitionAnimView.this.onRemoveAnimEndReset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class TransitionAnimEndResetRunnable implements Runnable {
        private TransitionAnimEndResetRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JDTransitionAnimView.this.onTransitionAnimEndReset();
        }
    }

    public JDTransitionAnimView(@NonNull Context context) {
        super(context);
        this.postViewCacheBitmap = null;
        this.TRANSITION_VIEW_TAG = "transition_view_tag";
        this.LOADING_VIEW_TAG = "LoadingViewTag";
        this.ANIM_DURATION = 400L;
        this.TRANSLATION_Y = DPIUtil.dip2px(getContext(), 28.0f);
        this.animHelper = new JDTransitionAnimHelper();
        this.cacheAnim = new ArrayList();
        this.isAnimEndReset = false;
        this.isRemoveAnimEndReset = false;
        init();
    }

    private void adjustLayout() {
        if (!checkIsNull(this.activity)) {
            this.statusBarHeight = UnStatusBarTintUtil.getStatusBarHeight(this.activity);
        }
        View childAt = getChildAt(0);
        if (checkIsNull(childAt)) {
            return;
        }
        if (this.statusBarHeight != childAt.getPaddingTop()) {
            childAt.setPadding(0, this.statusBarHeight, 0, 0);
        }
    }

    private void cancelAnim() {
        if (checkIsNull(this.cacheAnim)) {
            return;
        }
        Object[] array = this.cacheAnim.toArray();
        this.cacheAnim.clear();
        for (Object obj : array) {
            if (obj != null) {
                if (obj instanceof ViewPropertyAnimator) {
                    ((ViewPropertyAnimator) obj).cancel();
                } else if (obj instanceof Animator) {
                    ((Animator) obj).cancel();
                }
            }
        }
    }

    private void checkIsNeedReset() {
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this);
            handlerRemoveAllCallbacks();
            cancelAnim();
            removeBigPicViewController();
            recyclePostViewCacheBitmap();
            resetAllView();
        }
    }

    private boolean checkIsNull(Object obj) {
        return obj == null;
    }

    private void clearAllData() {
        clearData();
        this.manager = null;
        this.activity = null;
        this.listener = null;
    }

    private void clearData() {
        if (checkIsNull(this.manager)) {
            return;
        }
        this.manager.clearData();
    }

    private void configBigPicViewPosition() {
        if (checkIsNull(this.manager) || checkIsNull(this.bigPicView)) {
            return;
        }
        int frontWidth = this.manager.getFrontWidth();
        int frontHeight = this.manager.getFrontHeight();
        int postWidth = this.manager.getPostWidth();
        int postHeight = this.manager.getPostHeight();
        int postTop = this.manager.getPostTop() - this.statusBarHeight;
        int postLeft = this.manager.getPostLeft();
        float f2 = frontWidth;
        float f3 = postWidth / (f2 * 1.0f);
        float f4 = frontHeight;
        float f5 = postHeight / (1.0f * f4);
        boolean z = true;
        if (f3 < f5) {
            int i2 = (int) (f4 * f3);
            postTop += (postHeight - i2) / 2;
            postHeight = i2;
        } else {
            int i3 = (int) (f2 * f5);
            postLeft += (postWidth - i3) / 2;
            postWidth = i3;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bigPicView.getLayoutParams();
        if (postTop == layoutParams.topMargin && postLeft == layoutParams.leftMargin && postWidth == layoutParams.width && postHeight == layoutParams.height) {
            z = false;
        }
        if (z) {
            layoutParams.topMargin = postTop;
            layoutParams.leftMargin = postLeft;
            layoutParams.width = postWidth;
            layoutParams.height = postHeight;
            this.bigPicView.setLayoutParams(layoutParams);
        }
    }

    private void configPlaceHolderTopViewHeight() {
        if (checkIsNull(this.placeHolderTopView)) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.placeHolderTopView.getLayoutParams();
        int width = (int) (DPIUtil.getWidth() / 1.5f);
        if (width != layoutParams.height) {
            layoutParams.height = width;
            this.placeHolderTopView.setLayoutParams(layoutParams);
        }
    }

    private void configPlaceHolderViewPosition() {
        if (checkIsNull(this.manager) || checkIsNull(this.bigPicView) || checkIsNull(this.placeHolderView)) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bigPicView.getLayoutParams();
        int i2 = layoutParams.height;
        int i3 = layoutParams.topMargin;
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.placeHolderView.getLayoutParams();
        int i4 = i2 + i3;
        if (i4 != layoutParams2.topMargin) {
            layoutParams2.topMargin = i4;
            this.placeHolderView.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configUiPosition() {
        adjustLayout();
        configBigPicViewPosition();
        configPlaceHolderViewPosition();
        configPlaceHolderTopViewHeight();
    }

    private ViewPropertyAnimator createAlphaAnim(View view, long j2) {
        if (checkIsNull(view)) {
            return null;
        }
        return view.animate().alpha(0.0f).setDuration(j2).setInterpolator(new LinearInterpolator()).setListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.unification.translation.JDTransitionAnimView.4
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean z) {
                JDTransitionAnimView.this.onRemoveAnimEndReset();
            }
        });
    }

    private Animator createBackAlphaAnim() {
        if (checkIsNull(this.rootView)) {
            return null;
        }
        final ColorDrawable colorDrawable = new ColorDrawable(DeepDarkChangeManager.getInstance().getUIMode() == 1 ? Color.parseColor("#1d1b1b") : -1);
        colorDrawable.setAlpha(0);
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 255);
        ofInt.setDuration(400L);
        ofInt.setInterpolator(createInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.translation.JDTransitionAnimView.3
            int lastAlpha = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (intValue != this.lastAlpha) {
                    colorDrawable.setAlpha(intValue);
                    ViewCompat.setBackground(JDTransitionAnimView.this.rootView, colorDrawable);
                    this.lastAlpha = intValue;
                }
            }
        });
        return ofInt;
    }

    private ViewPropertyAnimator createBigPicViewAnim() {
        if (checkIsNull(this.bigPicView) || checkIsNull(this.manager)) {
            return null;
        }
        int frontWidth = this.manager.getFrontWidth();
        int frontHeight = this.manager.getFrontHeight();
        int postWidth = this.manager.getPostWidth();
        int postHeight = this.manager.getPostHeight();
        int postTop = this.manager.getPostTop() - this.statusBarHeight;
        int postLeft = this.manager.getPostLeft();
        float f2 = frontWidth;
        float f3 = postWidth / (f2 * 1.0f);
        float f4 = frontHeight;
        float f5 = postHeight / (f4 * 1.0f);
        if (f3 < f5) {
            int i2 = (int) (f4 * f3);
            postTop += (postHeight - i2) / 2;
            postHeight = i2;
        } else {
            int i3 = (int) (f2 * f5);
            postLeft += (postWidth - i3) / 2;
            postWidth = i3;
        }
        int frontLeft = (this.manager.getFrontLeft() + (this.manager.getFrontWidth() / 2)) - (postLeft + (postWidth / 2));
        int frontTop = (this.manager.getFrontTop() + (this.manager.getFrontHeight() / 2)) - (postTop + (postHeight / 2));
        float frontWidth2 = this.manager.getFrontWidth() / (postWidth * 1.0f);
        this.bigPicView.setTranslationX(frontLeft);
        this.bigPicView.setTranslationY(frontTop);
        this.bigPicView.setScaleX(frontWidth2);
        this.bigPicView.setScaleY(this.manager.getFrontHeight() / (postHeight * 1.0f));
        return this.bigPicView.animate().setDuration(400L).scaleX(1.0f).scaleY(1.0f).translationX(0.0f).translationY(0.0f).setInterpolator(createInterpolator());
    }

    private ViewPropertyAnimator createDivideAnim() {
        if (checkIsNull(this.divideLineView)) {
            return null;
        }
        this.divideLineView.setTranslationY(this.TRANSLATION_Y);
        this.divideLineView.setAlpha(0.0f);
        return this.divideLineView.animate().setStartDelay(200L).setDuration(200L).setInterpolator(new DecelerateInterpolator()).alpha(1.0f).translationY(0.0f);
    }

    private TimeInterpolator createInterpolator() {
        return PathInterpolatorCompat.create(0.0f, 0.29f, 0.18f, 1.0f);
    }

    private ViewPropertyAnimator createPlaceHolderAnim() {
        if (checkIsNull(this.placeHolderView)) {
            return null;
        }
        this.placeHolderView.setTranslationY(this.TRANSLATION_Y);
        this.placeHolderView.setAlpha(0.0f);
        return this.placeHolderView.animate().setStartDelay(200L).setDuration(200L).setInterpolator(new DecelerateInterpolator()).alpha(1.0f).translationY(0.0f);
    }

    private ViewPropertyAnimator createViewScaleAnim(View view) {
        if (checkIsNull(view)) {
            return null;
        }
        view.setAlpha(0.0f);
        view.setScaleX(0.0f);
        view.setScaleY(0.0f);
        return view.animate().setStartDelay(200L).setDuration(200L).setInterpolator(new DecelerateInterpolator()).alpha(1.0f).scaleX(1.0f).scaleY(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayBigPicViewByUrl(String str, JDImageLoadingListener jDImageLoadingListener) {
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.displayImageOnlyCache(str, this.bigPicView, new JDDisplayImageOptions(), jDImageLoadingListener);
        } else if (jDImageLoadingListener != null) {
            jDImageLoadingListener.onLoadingFailed(null, null, null);
        }
    }

    private int findLoadingViewIndex(ViewGroup viewGroup) {
        View findViewWithTag = viewGroup.findViewWithTag("LoadingViewTag");
        if (findViewWithTag == null) {
            return -1;
        }
        return viewGroup.indexOfChild(findViewWithTag);
    }

    private ViewGroup getDecorView(Activity activity) {
        if (activity == null) {
            return null;
        }
        return (ViewGroup) activity.getWindow().getDecorView();
    }

    private void handlerRemoveAllCallbacks() {
        this.handler.removeCallbacksAndMessages(null);
    }

    private void handlerTimeOutRemoveAnimEndReset(long j2) {
        this.handler.removeCallbacks(this.removeAnimEndRunnable);
        this.handler.postDelayed(this.removeAnimEndRunnable, j2);
    }

    private void handlerTimeOutTransitionAnimReset(long j2) {
        this.handler.removeCallbacks(this.transitionAnimEndRunnable);
        this.handler.postDelayed(this.transitionAnimEndRunnable, j2);
    }

    private void hiddenViewExcludeAnimLayout() {
        ViewGroup decorView = getDecorView(this.activity);
        if (checkIsNull(decorView)) {
            return;
        }
        int childCount = decorView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = decorView.getChildAt(i2);
            View statusBarView = UnStatusBarTintUtil.getStatusBarView(this.activity);
            if (childAt != this && childAt != statusBarView) {
                childAt.setAlpha(0.0f);
            }
        }
    }

    private void init() {
        loadLayout();
        initLayout();
    }

    private void initLayout() {
        this.backView = findViewById(R.id.lib_transition_back_view);
        this.shareView = findViewById(R.id.lib_transition_share_view);
        this.moreView = findViewById(R.id.lib_transition_more_view);
        this.bigPicView = (SimpleDraweeView) findViewById(R.id.lib_transition_big_pic_view);
        this.divideLineView = findViewById(R.id.lib_transition_divider_view);
        this.placeHolderView = findViewById(R.id.lib_transition_place_holder_view);
        this.placeHolderTopView = findViewById(R.id.lib_transition_place_holder_top_view);
        this.placeHolderBottomView = findViewById(R.id.lib_transition_place_holder_bottom_view);
        this.bgView = findViewById(R.id.lib_transition_back_alpha_view);
        this.rootView = this;
    }

    private Bitmap loadBitmapFromViewBySystem(View view) {
        if (view == null) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null || drawingCache.isRecycled()) {
            return null;
        }
        recyclePostViewCacheBitmap();
        this.postViewCacheBitmap = Bitmap.createBitmap(drawingCache);
        view.setDrawingCacheEnabled(false);
        return this.postViewCacheBitmap;
    }

    private void loadLayout() {
        this.handler = new Handler(Looper.getMainLooper());
        this.removeAnimEndRunnable = new RemoveAnimEndResetRunnable();
        this.transitionAnimEndRunnable = new TransitionAnimEndResetRunnable();
        ViewCompat.setBackground(this, null);
        LayoutInflater.from(getContext()).inflate(R.layout.lib_transition_anim_layout, (ViewGroup) this, true);
    }

    private void notifyAnimStateAndResetState() {
        notifyAnimStateChange();
        resetActivityState();
        reset();
    }

    private void notifyAnimStateChange() {
        if (checkIsNull(this.listener)) {
            return;
        }
        this.listener.onEnterAnimFinish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStartAnim(boolean z) {
        if (checkIsNull(this.manager)) {
            notifyAnimStateAndResetState();
            return;
        }
        View postView = this.manager.getPostView();
        if (checkIsNull(this.bigPicView)) {
            notifyAnimStateAndResetState();
            return;
        }
        if (!z) {
            this.bigPicView.setImageBitmap(loadBitmapFromViewBySystem(postView));
        }
        hiddenViewExcludeAnimLayout();
        if (checkIsNull(this.activity)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.manager.changeEnterAnimState(this.activity, true);
        ViewPropertyAnimator createBigPicViewAnim = createBigPicViewAnim();
        if (checkIsNull(createBigPicViewAnim)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.cacheAnim.add(createBigPicViewAnim);
        ViewPropertyAnimator createPlaceHolderAnim = createPlaceHolderAnim();
        if (checkIsNull(createPlaceHolderAnim)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.cacheAnim.add(createPlaceHolderAnim);
        Animator createBackAlphaAnim = createBackAlphaAnim();
        if (checkIsNull(createBackAlphaAnim)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.cacheAnim.add(createBackAlphaAnim);
        ViewPropertyAnimator createDivideAnim = createDivideAnim();
        if (checkIsNull(createDivideAnim)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.cacheAnim.add(createDivideAnim);
        ViewPropertyAnimator createViewScaleAnim = createViewScaleAnim(this.backView);
        if (checkIsNull(createViewScaleAnim)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.cacheAnim.add(createViewScaleAnim);
        ViewPropertyAnimator createViewScaleAnim2 = createViewScaleAnim(this.shareView);
        if (checkIsNull(createViewScaleAnim2)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.cacheAnim.add(createViewScaleAnim2);
        ViewPropertyAnimator createViewScaleAnim3 = createViewScaleAnim(this.moreView);
        if (checkIsNull(createViewScaleAnim3)) {
            notifyAnimStateAndResetState();
            return;
        }
        this.cacheAnim.add(createViewScaleAnim3);
        this.animHelper.setAnimatorListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.unification.translation.JDTransitionAnimView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                JDTransitionAnimView.this.onTransitionAnimEndReset();
            }
        });
        this.isAnimEndReset = false;
        this.animHelper.playTogether(createBigPicViewAnim, createViewScaleAnim, createViewScaleAnim2, createViewScaleAnim3, createDivideAnim, createPlaceHolderAnim, createBackAlphaAnim);
        handlerTimeOutTransitionAnimReset(450L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRemoveAnimEndReset() {
        if (this.isRemoveAnimEndReset) {
            return;
        }
        reset();
        this.isRemoveAnimEndReset = true;
    }

    private void preStartConfig() {
        if (checkIsNull(this.manager) || checkIsNull(this.activity)) {
            return;
        }
        this.manager.changeToTransparent(this.activity);
        this.manager.changeEnterAnimState(this.activity, false);
    }

    private void recyclePostViewCacheBitmap() {
        Bitmap bitmap = this.postViewCacheBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.postViewCacheBitmap.recycle();
        this.postViewCacheBitmap = null;
    }

    private void removeBigPicViewController() {
        if (checkIsNull(this.bigPicView)) {
            return;
        }
        this.bigPicView.setController(null);
    }

    private void removeFrontUrlCache() {
        if (checkIsNull(this.manager)) {
            return;
        }
        this.manager.removeFrontUrlFromCache();
    }

    private void removeFrontViewCache() {
        if (checkIsNull(this.manager)) {
            return;
        }
        this.manager.removeFrontViewFromCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    private void resetActivityState() {
        if (checkIsNull(this.manager) || checkIsNull(this.activity)) {
            return;
        }
        this.manager.changeEnterAnimState(this.activity, true);
    }

    private void resetAllView() {
        if (!checkIsNull(this.backView)) {
            this.backView.setAlpha(0.0f);
        }
        if (!checkIsNull(this.shareView)) {
            this.shareView.setAlpha(0.0f);
        }
        if (!checkIsNull(this.moreView)) {
            this.moreView.setAlpha(0.0f);
        }
        if (!checkIsNull(this.bigPicView)) {
            this.bigPicView.setAlpha(1.0f);
        }
        if (!checkIsNull(this.divideLineView)) {
            this.divideLineView.setAlpha(0.0f);
        }
        if (!checkIsNull(this.placeHolderView)) {
            ViewCompat.setBackground(this.placeHolderView, null);
            this.placeHolderView.setAlpha(0.0f);
        }
        if (checkIsNull(this.rootView)) {
            return;
        }
        ViewCompat.setBackground(this.rootView, null);
    }

    private void resetDecorView() {
        ViewGroup decorView = getDecorView(this.activity);
        if (checkIsNull(decorView) || checkIsNull(this.manager)) {
            return;
        }
        this.manager.changeChildViewAlpha(decorView, 1.0f);
    }

    private void resetFadeAnimDuration() {
        if (checkIsNull(this.manager) || checkIsNull(this.activity)) {
            return;
        }
        this.manager.resetFadeAnimDuration(this.activity);
    }

    private void showViewExcludeAnimLayout() {
        ViewGroup decorView = getDecorView(this.activity);
        if (checkIsNull(decorView)) {
            return;
        }
        int childCount = decorView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = decorView.getChildAt(i2);
            View statusBarView = UnStatusBarTintUtil.getStatusBarView(this.activity);
            if (childAt != this && childAt != statusBarView) {
                childAt.setAlpha(1.0f);
            }
        }
    }

    private void startListenerPostViewLayout() {
        if (!checkIsNull(this.manager) && !checkIsNull(this.activity)) {
            final View postView = this.manager.getPostView();
            if (checkIsNull(postView)) {
                notifyAnimStateAndResetState();
                return;
            }
            this.manager.getUrl();
            postView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0030: INVOKE 
                  (wrap: android.view.ViewTreeObserver : 0x0027: INVOKE (r0v5 'postView' android.view.View) type: VIRTUAL call: android.view.View.getViewTreeObserver():android.view.ViewTreeObserver A[MD:():android.view.ViewTreeObserver (c), WRAPPED] (LINE:6))
                  (wrap: android.view.ViewTreeObserver$OnGlobalLayoutListener : 0x002d: CONSTRUCTOR 
                  (r4v0 'this' com.jingdong.common.unification.translation.JDTransitionAnimView A[IMMUTABLE_TYPE, THIS])
                  (r0v5 'postView' android.view.View A[DONT_INLINE])
                  (r1 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jingdong.common.unification.translation.JDTransitionAnimView, android.view.View, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.unification.translation.JDTransitionAnimView.1.<init>(com.jingdong.common.unification.translation.JDTransitionAnimView, android.view.View, java.lang.String):void type: CONSTRUCTOR)
                 type: VIRTUAL call: android.view.ViewTreeObserver.addOnGlobalLayoutListener(android.view.ViewTreeObserver$OnGlobalLayoutListener):void A[MD:(android.view.ViewTreeObserver$OnGlobalLayoutListener):void (c)] (LINE:6) in method: com.jingdong.common.unification.translation.JDTransitionAnimView.startListenerPostViewLayout():void, file: classes6.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 27 more
                */
            /*
                this = this;
                com.jingdong.common.unification.translation.JDTransitionManager r0 = r4.manager
                boolean r0 = r4.checkIsNull(r0)
                if (r0 != 0) goto L34
                android.app.Activity r0 = r4.activity
                boolean r0 = r4.checkIsNull(r0)
                if (r0 == 0) goto L11
                goto L34
            L11:
                com.jingdong.common.unification.translation.JDTransitionManager r0 = r4.manager
                android.view.View r0 = r0.getPostView()
                boolean r1 = r4.checkIsNull(r0)
                if (r1 == 0) goto L21
                r4.notifyAnimStateAndResetState()
                return
            L21:
                com.jingdong.common.unification.translation.JDTransitionManager r1 = r4.manager
                java.lang.String r1 = r1.getUrl()
                android.view.ViewTreeObserver r2 = r0.getViewTreeObserver()
                com.jingdong.common.unification.translation.JDTransitionAnimView$1 r3 = new com.jingdong.common.unification.translation.JDTransitionAnimView$1
                r3.<init>()
                r2.addOnGlobalLayoutListener(r3)
                return
            L34:
                r4.notifyAnimStateAndResetState()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.translation.JDTransitionAnimView.startListenerPostViewLayout():void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updatePostViewData() {
            if (checkIsNull(this.manager)) {
                return;
            }
            this.manager.updatePostViewData();
        }

        public void attachToDecorView() {
            ViewGroup decorView = getDecorView(this.activity);
            if (checkIsNull(decorView)) {
                return;
            }
            View findViewWithTag = decorView.findViewWithTag("transition_view_tag");
            if (findViewWithTag != null) {
                decorView.removeView(findViewWithTag);
            }
            setTag("transition_view_tag");
            decorView.addView(this, findLoadingViewIndex(decorView));
            setAlpha(1.0f);
        }

        public void detachToDecorView() {
            ViewGroup decorView = getDecorView(this.activity);
            if (checkIsNull(decorView)) {
                return;
            }
            setAlpha(0.0f);
            decorView.removeView(this);
        }

        public void onTransitionAnimEndReset() {
            if (this.isAnimEndReset) {
                return;
            }
            removeFrontViewCache();
            removeFrontUrlCache();
            resetFadeAnimDuration();
            showViewExcludeAnimLayout();
            clearData();
            if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                this.placeHolderView.setBackgroundColor(Color.parseColor("#1d1b1b"));
            } else {
                this.placeHolderView.setBackgroundColor(-1);
            }
            ViewCompat.setBackground(this.rootView, null);
            this.backView.setAlpha(0.0f);
            this.shareView.setAlpha(0.0f);
            this.moreView.setAlpha(0.0f);
            this.bigPicView.setAlpha(0.0f);
            removeBigPicViewController();
            this.divideLineView.setAlpha(0.0f);
            notifyAnimStateChange();
            this.isAnimEndReset = true;
        }

        public void removePlaceHolderView(long j2) {
            ViewPropertyAnimator createAlphaAnim = createAlphaAnim(this.placeHolderView, j2);
            if (checkIsNull(createAlphaAnim)) {
                return;
            }
            this.cacheAnim.add(createAlphaAnim);
            this.isRemoveAnimEndReset = false;
            createAlphaAnim.start();
            handlerTimeOutRemoveAnimEndReset(j2 + 50);
        }

        public void reset() {
            handlerRemoveAllCallbacks();
            cancelAnim();
            removeBigPicViewController();
            detachToDecorView();
            resetDecorView();
            resetFadeAnimDuration();
            removeFrontViewCache();
            removeFrontUrlCache();
            recyclePostViewCacheBitmap();
            clearAllData();
            resetAllView();
        }

        public void startAnim(Activity activity, JDTransitionManager jDTransitionManager, JDTransitionManager.ITransitionAnimStateListener iTransitionAnimStateListener) {
            View view;
            this.manager = jDTransitionManager;
            this.activity = activity;
            this.listener = iTransitionAnimStateListener;
            if (!JDPrivacyHelper.isAcceptPrivacy(activity) && (view = this.moreView) != null && this.shareView != null) {
                view.setVisibility(8);
                this.shareView.setVisibility(8);
            }
            checkIsNeedReset();
            preStartConfig();
            attachToDecorView();
            if ((this.placeHolderTopView == null || this.placeHolderBottomView == null || this.bgView == null || this.placeHolderView == null) ? false : true) {
                if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                    this.bgView.setBackgroundColor(Color.parseColor("#1d1b1b"));
                    this.placeHolderView.setBackgroundColor(Color.parseColor("#1d1b1b"));
                    this.placeHolderTopView.setBackgroundResource(R.drawable.lib_transition_place_holder_top_dark);
                    this.placeHolderBottomView.setBackgroundResource(R.drawable.lib_transition_place_holder_bottom_dark);
                } else {
                    this.bgView.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
                    this.placeHolderView.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
                    this.placeHolderTopView.setBackgroundResource(R.drawable.lib_transition_place_holder_top);
                    this.placeHolderBottomView.setBackgroundResource(R.drawable.lib_transition_place_holder_bottom);
                }
            }
            startListenerPostViewLayout();
        }

        public JDTransitionAnimView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.postViewCacheBitmap = null;
            this.TRANSITION_VIEW_TAG = "transition_view_tag";
            this.LOADING_VIEW_TAG = "LoadingViewTag";
            this.ANIM_DURATION = 400L;
            this.TRANSLATION_Y = DPIUtil.dip2px(getContext(), 28.0f);
            this.animHelper = new JDTransitionAnimHelper();
            this.cacheAnim = new ArrayList();
            this.isAnimEndReset = false;
            this.isRemoveAnimEndReset = false;
            init();
        }

        public JDTransitionAnimView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.postViewCacheBitmap = null;
            this.TRANSITION_VIEW_TAG = "transition_view_tag";
            this.LOADING_VIEW_TAG = "LoadingViewTag";
            this.ANIM_DURATION = 400L;
            this.TRANSLATION_Y = DPIUtil.dip2px(getContext(), 28.0f);
            this.animHelper = new JDTransitionAnimHelper();
            this.cacheAnim = new ArrayList();
            this.isAnimEndReset = false;
            this.isRemoveAnimEndReset = false;
            init();
        }
    }
