package com.jingdong.cleanmvp.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public abstract class MvpFrameLayout<P extends BasePresenter> extends FrameLayout implements IBaseUI {
    protected P presenter;

    public MvpFrameLayout(Context context) {
        super(context);
        this.presenter = null;
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        P p = this.presenter;
        if (p != null) {
            return p;
        }
        throw new NullPointerException("presenter is null ! you should create a presenter.");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        P p = this.presenter;
        if (p != null) {
            p.attachUI(this);
        }
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        P p = this.presenter;
        if (p != null) {
            p.detachUI(this);
        }
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(BaseEvent baseEvent) {
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        P p = this.presenter;
        if (p != null) {
            if (i2 == 0) {
                p.setIsShow(true);
            } else {
                p.setIsShow(false);
            }
        }
    }

    protected void setPresenter(P p) {
        this.presenter = p;
    }

    public MvpFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.presenter = null;
    }

    public MvpFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.presenter = null;
    }

    @TargetApi(21)
    public MvpFrameLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.presenter = null;
    }
}
