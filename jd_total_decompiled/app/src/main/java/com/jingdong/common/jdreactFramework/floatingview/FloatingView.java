package com.jingdong.common.jdreactFramework.floatingview;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.core.view.ViewCompat;
import com.jingdong.jdreactframewok.R;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class FloatingView implements IFloatingView {
    private static volatile FloatingView mInstance;
    private WeakReference<FrameLayout> mContainer;
    private String mContent;
    private FloatingMagnetView mEnFloatingView;
    @LayoutRes
    private int mLayoutId = R.layout.en_floating_view;
    @DrawableRes
    private int mIconRes = R.drawable.jdreact_debug_icon;
    private ViewGroup.LayoutParams mLayoutParams = getParams();

    private FloatingView() {
    }

    private void addViewToWindow(View view) {
        if (getContainer() == null) {
            return;
        }
        getContainer().addView(view);
    }

    private void ensureFloatingView() {
        synchronized (this) {
            FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
            if (floatingMagnetView != null) {
                ((EnFloatingView) floatingMagnetView).setText(this.mContent);
                return;
            }
            EnFloatingView enFloatingView = new EnFloatingView(EnContext.get(), this.mLayoutId);
            this.mEnFloatingView = enFloatingView;
            enFloatingView.setLayoutParams(this.mLayoutParams);
            enFloatingView.setIconImage(this.mIconRes);
            if (!TextUtils.isEmpty(this.mContent)) {
                enFloatingView.setText(this.mContent);
                enFloatingView.setVisibility(0);
            }
            addViewToWindow(enFloatingView);
        }
    }

    public static FloatingView get() {
        if (mInstance == null) {
            synchronized (FloatingView.class) {
                if (mInstance == null) {
                    mInstance = new FloatingView();
                }
            }
        }
        return mInstance;
    }

    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(16908290);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FrameLayout getContainer() {
        WeakReference<FrameLayout> weakReference = this.mContainer;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388691;
        layoutParams.setMargins(13, layoutParams.topMargin, layoutParams.rightMargin, 500);
        return layoutParams;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView add() {
        ensureFloatingView();
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView customView(FloatingMagnetView floatingMagnetView) {
        this.mEnFloatingView = floatingMagnetView;
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingMagnetView getView() {
        return this.mEnFloatingView;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView icon(@DrawableRes int i2) {
        this.mIconRes = i2;
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView layoutParams(ViewGroup.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null) {
            floatingMagnetView.setLayoutParams(layoutParams);
        }
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView listener(MagnetViewListener magnetViewListener) {
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null) {
            floatingMagnetView.setMagnetViewListener(magnetViewListener);
        }
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.floatingview.FloatingView.1
            @Override // java.lang.Runnable
            public void run() {
                if (FloatingView.this.mEnFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(FloatingView.this.mEnFloatingView) && FloatingView.this.getContainer() != null) {
                    FloatingView.this.getContainer().removeView(FloatingView.this.mEnFloatingView);
                }
                FloatingView.this.mEnFloatingView = null;
            }
        });
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView text(String str) {
        this.mContent = str;
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView attach(FrameLayout frameLayout) {
        FloatingMagnetView floatingMagnetView;
        if (frameLayout != null && (floatingMagnetView = this.mEnFloatingView) != null) {
            if (floatingMagnetView.getParent() == frameLayout) {
                return this;
            }
            if (this.mEnFloatingView.getParent() != null) {
                ((ViewGroup) this.mEnFloatingView.getParent()).removeView(this.mEnFloatingView);
            }
            this.mContainer = new WeakReference<>(frameLayout);
            frameLayout.addView(this.mEnFloatingView);
            return this;
        }
        this.mContainer = new WeakReference<>(frameLayout);
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView customView(@LayoutRes int i2) {
        this.mLayoutId = i2;
        return this;
    }

    @Override // com.jingdong.common.jdreactFramework.floatingview.IFloatingView
    public FloatingView detach(FrameLayout frameLayout) {
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null && frameLayout != null && ViewCompat.isAttachedToWindow(floatingMagnetView)) {
            frameLayout.removeView(this.mEnFloatingView);
        }
        if (getContainer() == frameLayout) {
            this.mContainer = null;
        }
        return this;
    }
}
