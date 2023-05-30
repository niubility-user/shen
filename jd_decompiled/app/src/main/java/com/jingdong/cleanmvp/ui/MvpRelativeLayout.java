package com.jingdong.cleanmvp.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public abstract class MvpRelativeLayout<P extends BasePresenter> extends RelativeLayout implements IBaseUI {
    protected P presenter;

    public MvpRelativeLayout(Context context) {
        super(context);
    }

    @Nullable
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

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showToast(String str) {
        Toast.makeText(getContext(), str, 0).show();
    }

    public MvpRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MvpRelativeLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @TargetApi(21)
    public MvpRelativeLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
