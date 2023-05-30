package com.jingdong.common.listui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import com.jingdong.listui.R;

/* loaded from: classes5.dex */
public class BaseUI {
    protected FrameLayout frameContainer;
    private View mErrorView;
    protected TitleView titleView;
    protected ViewStub vsBottom;
    protected ViewStub vsTop;

    private void initView(View view) {
        this.titleView = (TitleView) view.findViewById(R.id.base_ui_title);
        this.vsTop = (ViewStub) view.findViewById(R.id.base_ui_vs_top);
        this.vsBottom = (ViewStub) view.findViewById(R.id.base_ui_vs_bottom);
        this.frameContainer = (FrameLayout) view.findViewById(R.id.base_ui_container);
    }

    public void addView(View view, FrameLayout.LayoutParams layoutParams) {
        FrameLayout frameLayout = this.frameContainer;
        if (frameLayout == null) {
            return;
        }
        frameLayout.addView(view, layoutParams);
    }

    protected void ensureUI(Context context, FrameLayout frameLayout) {
    }

    public View getErrorView() {
        return this.mErrorView;
    }

    public FrameLayout getFrameContainer() {
        return this.frameContainer;
    }

    public FrameLayout getLayoutContainer(FrameLayout frameLayout) {
        return frameLayout;
    }

    public TitleView getTitleView() {
        return this.titleView;
    }

    public ViewStub getVsBottom() {
        return this.vsBottom;
    }

    public ViewStub getVsTop() {
        return this.vsTop;
    }

    public void onClick(View view) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.liui_base_ui_layout, viewGroup, false);
        initView(inflate);
        ensureUI(layoutInflater.getContext(), getLayoutContainer(this.frameContainer));
        return inflate;
    }

    public void removeError() {
        FrameLayout frameLayout;
        View view = this.mErrorView;
        if (view == null || (frameLayout = this.frameContainer) == null) {
            return;
        }
        frameLayout.removeView(view);
        this.mErrorView = null;
    }

    public void showError(View view, int i2, View.OnClickListener onClickListener) {
        if (this.mErrorView == null) {
            if (view != null) {
                this.mErrorView = view;
            } else {
                this.mErrorView = new ErrorView().setOnBtnListener(onClickListener).getView(this.frameContainer.getContext(), i2);
            }
            addView(this.mErrorView, new FrameLayout.LayoutParams(-1, -1));
        }
    }
}
