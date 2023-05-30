package com.jingdong.common.ntask;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes.dex */
public class NTaskXView implements LifecycleObserver {
    private BaseActivity mActivity;
    private Lifecycle mLifecycle;
    private NXViewListener mListener;
    private FrameLayout mRootView;
    private XView mXView;

    public NTaskXView(BaseActivity baseActivity, NXViewListener nXViewListener) {
        this.mActivity = baseActivity;
        this.mListener = nXViewListener;
        Lifecycle lifecycle = baseActivity.getLifecycle();
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        XView xView = this.mXView;
        if (xView != null) {
            xView.closeXView();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        XView xView = this.mXView;
        if (xView != null) {
            xView.onResume();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onStop() {
        XView xView = this.mXView;
        if (xView != null) {
            xView.onStop();
        }
    }

    public void openXViewH5(String str) {
        if (this.mRootView != null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(this.mActivity);
        this.mRootView = frameLayout;
        frameLayout.setPadding(0, 0, 0, 0);
        final RelativeLayout relativeLayout = new RelativeLayout(this.mActivity);
        relativeLayout.setBackgroundColor(Color.parseColor("#b1000000"));
        ImageView imageView = new ImageView(this.mActivity);
        imageView.setImageResource(R.drawable.xview_close);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(DPIUtil.dip2px(30.0f), DPIUtil.dip2px(30.0f));
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(10, -1);
        layoutParams2.topMargin = DPIUtil.dip2px(33.0f);
        layoutParams2.rightMargin = DPIUtil.dip2px(11.0f);
        relativeLayout.addView(imageView, layoutParams2);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ntask.NTaskXView.1
            {
                NTaskXView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NTaskXView.this.mRootView != null) {
                    NTaskXView.this.mRootView.removeView(relativeLayout);
                }
                if (NTaskXView.this.mXView != null) {
                    NTaskXView.this.mXView.closeXView();
                }
                NTaskXView.this.mRootView = null;
            }
        });
        JDProgressBar jDProgressBar = new JDProgressBar(this.mActivity);
        jDProgressBar.setIndeterminateDrawable(this.mActivity.getResources().getDrawable(com.jingdong.app.mall.mylib.R.drawable.ntask_progressbar_style));
        jDProgressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        relativeLayout.addView(jDProgressBar, layoutParams3);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.ntask.NTaskXView.2
            {
                NTaskXView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.mRootView.addView(relativeLayout);
        ((ViewGroup) ((ViewGroup) this.mActivity.getWindow().getDecorView()).findViewById(16908290)).addView(this.mRootView, layoutParams);
        XViewEntity xViewEntity = new XViewEntity();
        xViewEntity.url = str;
        XViewCallBack xViewCallBack = new XViewCallBack() { // from class: com.jingdong.common.ntask.NTaskXView.3
            {
                NTaskXView.this = this;
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onCloseButtonClicked() {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onError(int i2) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onStart() {
                if (NTaskXView.this.mListener != null) {
                    NTaskXView.this.mListener.xViewOpen();
                }
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewDisplayed() {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewLoadingUrl(String str2) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewReady() {
                if (NTaskXView.this.mRootView != null) {
                    NTaskXView.this.mRootView.removeView(relativeLayout);
                }
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewRequest(XViewRequest xViewRequest) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewVisibleChanged(boolean z) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXVivewClosed() {
                if (NTaskXView.this.mXView != null) {
                    NTaskXView.this.mXView = null;
                }
                if (NTaskXView.this.mRootView != null) {
                    NTaskXView.this.mRootView.removeView(relativeLayout);
                    NTaskXView.this.mRootView = null;
                }
                if (NTaskXView.this.mListener != null) {
                    NTaskXView.this.mListener.xViewClose();
                }
            }
        };
        if (this.mXView != null) {
            this.mXView = null;
        }
        XView createXView = XViewHelper.createXView(this.mActivity, this.mRootView, getClass().getName(), xViewEntity, xViewCallBack);
        this.mXView = createXView;
        createXView.startXView();
    }
}
