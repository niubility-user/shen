package com.jingdong.app.mall.bundle.jd_component.guide;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GuideView implements View.OnClickListener {
    boolean clickable;
    ViewGroup decorView;
    private Listener mListener;
    int showTime;
    List<View> views;

    /* loaded from: classes2.dex */
    public interface Listener {
        void onClickGuideView(GuideView guideView, int i2);
    }

    private GuideView(Activity activity) {
        if (activity != null && activity.getWindow() != null && (activity.getWindow().getDecorView() instanceof ViewGroup)) {
            this.decorView = (ViewGroup) activity.getWindow().getDecorView();
        }
        this.views = new ArrayList();
    }

    private void hintPage(final View view) {
        if (this.showTime <= 0 || view == null) {
            return;
        }
        view.postDelayed(new Runnable() { // from class: com.jingdong.app.mall.bundle.jd_component.guide.GuideView.1
            @Override // java.lang.Runnable
            public void run() {
                if (view == GuideView.this.next()) {
                    view.performClick();
                }
            }
        }, this.showTime * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View next() {
        if (this.views.size() > 0) {
            return this.views.get(0);
        }
        return null;
    }

    public static GuideView with(Activity activity) {
        return new GuideView(activity);
    }

    public GuideView addGuidePage(View view) {
        if (view != null) {
            this.views.add(view);
            return this;
        }
        throw new RuntimeException("View \u4e0d\u80fd\u4e3a\u7a7a");
    }

    public GuideView clickable(boolean z) {
        this.clickable = z;
        return this;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.clickable) {
            showNext();
        }
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onClickGuideView(this, this.views.size());
        }
    }

    public GuideView setListener(Listener listener) {
        this.mListener = listener;
        return this;
    }

    public GuideView setShowTime(int i2) {
        this.showTime = i2;
        return this;
    }

    public boolean show() {
        if (this.decorView != null) {
            View next = next();
            if (next != null) {
                next.setOnClickListener(this);
                this.decorView.addView(next);
                hintPage(next);
                return true;
            }
            return false;
        }
        throw new RuntimeException("Activity \u4e0d\u80fd\u4e3a\u7a7a");
    }

    public void showNext() {
        View next = next();
        if (next != null) {
            ViewParent parent = next.getParent();
            ViewGroup viewGroup = this.decorView;
            if (parent == viewGroup) {
                viewGroup.removeView(next);
                this.views.remove(next);
            }
        }
        if (this.views.size() > 0) {
            show();
        }
    }
}
