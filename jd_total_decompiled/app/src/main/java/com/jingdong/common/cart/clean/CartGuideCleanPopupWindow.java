package com.jingdong.common.cart.clean;

import android.annotation.TargetApi;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.methodEntity.CartAddFullEntity;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.ImageUtil;

/* loaded from: classes.dex */
public class CartGuideCleanPopupWindow extends PopupWindow implements View.OnClickListener, LifecycleObserver {
    private static volatile CartGuideCleanPopupWindow guideCleanPopupWindow;
    private BaseActivity activity;
    private ImageView closeBtn;
    private TextView contentTv;
    public CountDownTimer countDownTimer;
    private CartGuideCleanClick guideCleanClick;
    private ShoppingBaseController.PDShoppingCartListener pdShoppingCartListener;
    private FrameLayout rootView;

    /* loaded from: classes5.dex */
    public interface CartGuideCleanClick {
        void onCloseClick();

        void onContentClick();
    }

    private CartGuideCleanPopupWindow(BaseActivity baseActivity) {
        super(baseActivity);
    }

    public static CartGuideCleanPopupWindow getInstance(BaseActivity baseActivity) {
        if (guideCleanPopupWindow == null) {
            synchronized (CartGuideCleanPopupWindow.class) {
                if (guideCleanPopupWindow == null) {
                    guideCleanPopupWindow = new CartGuideCleanPopupWindow(baseActivity);
                }
            }
        }
        return guideCleanPopupWindow;
    }

    private void registerObserver() {
        BaseActivity baseActivity = this.activity;
        if (baseActivity instanceof LifecycleOwner) {
            baseActivity.getLifecycle().addObserver(this);
        }
    }

    private void setListener() {
        this.rootView.setOnClickListener(this);
        this.closeBtn.setOnClickListener(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void dismissGuideToast() {
        this.activity = null;
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener = this.pdShoppingCartListener;
        if (pDShoppingCartListener != null) {
            pDShoppingCartListener.dismissGuideToast();
        }
        dismiss();
        guideCleanPopupWindow = null;
    }

    public void init(CartAddFullEntity cartAddFullEntity) {
        BaseActivity baseActivity = cartAddFullEntity.activity;
        this.activity = baseActivity;
        if (baseActivity == null) {
            return;
        }
        View inflate = ImageUtil.inflate(baseActivity.getApplicationContext(), R.layout.lib_cart_guide_clean_popupwindow, (ViewGroup) null);
        setContentView(inflate);
        this.contentTv = (TextView) inflate.findViewById(R.id.cart_clean_guide_tv);
        this.closeBtn = (ImageView) inflate.findViewById(R.id.cart_clean_close_icon);
        this.rootView = (FrameLayout) inflate.findViewById(R.id.cart_guide_clean_popup);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(false);
        setWidth(-1);
        setHeight(-2);
        registerObserver();
        setListener();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.closeBtn) {
            dismissGuideToast();
            CartGuideCleanClick cartGuideCleanClick = this.guideCleanClick;
            if (cartGuideCleanClick != null) {
                cartGuideCleanClick.onCloseClick();
            }
        } else if (view == this.rootView) {
            dismissGuideToast();
            CartGuideCleanClick cartGuideCleanClick2 = this.guideCleanClick;
            if (cartGuideCleanClick2 != null) {
                cartGuideCleanClick2.onContentClick();
            }
        }
    }

    public void setGuideCleanClick(CartGuideCleanClick cartGuideCleanClick) {
        this.guideCleanClick = cartGuideCleanClick;
    }

    @TargetApi(12)
    public void showAtLocation(final View view) {
        BaseActivity baseActivity = this.activity;
        if (baseActivity == null || baseActivity.isFinishing() || view == null) {
            return;
        }
        view.measure(0, 0);
        final int measuredHeight = view.getMeasuredHeight();
        getContentView().measure(0, 0);
        final int measuredHeight2 = getContentView().getMeasuredHeight();
        if (view.getWindowToken() != null) {
            showAsDropDown(view, 0, ((-measuredHeight) - measuredHeight2) - DpiUtil.dip2px(this.activity, 5.0f));
            startCountDown();
            return;
        }
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.cart.clean.CartGuideCleanPopupWindow.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
                View view3;
                if (CartGuideCleanPopupWindow.this.activity == null || CartGuideCleanPopupWindow.this.activity.isFinishing() || (view3 = view) == null || view3.getWindowToken() == null) {
                    return;
                }
                CartGuideCleanPopupWindow cartGuideCleanPopupWindow = CartGuideCleanPopupWindow.this;
                cartGuideCleanPopupWindow.showAsDropDown(view, 0, ((-measuredHeight) - measuredHeight2) - DpiUtil.dip2px(cartGuideCleanPopupWindow.activity, 5.0f));
                CartGuideCleanPopupWindow.this.startCountDown();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                view.removeOnAttachStateChangeListener(this);
                CartGuideCleanPopupWindow.this.dismissGuideToast();
            }
        });
    }

    public void showGuideToast(CartAddFullEntity cartAddFullEntity, String str) {
        if (cartAddFullEntity == null) {
            return;
        }
        this.pdShoppingCartListener = cartAddFullEntity.pdShoppingCartListener;
        if (!isShowing()) {
            init(cartAddFullEntity);
            showAtLocation(cartAddFullEntity.anchorView);
        }
        this.contentTv.setText(str);
    }

    public void startCountDown() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(Final.FIVE_SECOND, 1000L) { // from class: com.jingdong.common.cart.clean.CartGuideCleanPopupWindow.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                CartGuideCleanPopupWindow.this.dismissGuideToast();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
            }
        };
        this.countDownTimer = countDownTimer2;
        countDownTimer2.start();
    }
}
