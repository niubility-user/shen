package com.jd.lib.un.basewidget.widget.watermark;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes16.dex */
public class WatermarkHelper {
    private WatermarkConfig config = WatermarkConfig.getConfig();
    private boolean isAdded;
    private FrameLayout layout;
    private ViewGroup rootView;
    private String text;

    public boolean isAdded() {
        return this.isAdded;
    }

    public void remove() {
        FrameLayout frameLayout;
        try {
            ViewGroup viewGroup = this.rootView;
            if (viewGroup == null || (frameLayout = this.layout) == null) {
                return;
            }
            viewGroup.removeView(frameLayout);
            this.isAdded = false;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setConfig(WatermarkConfig watermarkConfig) {
        this.config = watermarkConfig;
    }

    public void show(Activity activity) {
        show(activity, this.config.getmText());
    }

    public void showByCanAdd(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        if ((!this.isAdded || !TextUtils.equals(str, this.text)) && this.config.isCanAdd() && !TextUtils.isEmpty(str)) {
            show(activity, str);
        } else if (!this.isAdded || this.config.isCanAdd()) {
        } else {
            remove();
        }
    }

    public void show(Dialog dialog) {
        show(dialog, this.config.getmText());
    }

    public void show(Activity activity, String str) {
        try {
            this.config.setmText(str);
            this.text = str;
            WatermarkDrawable watermarkDrawable = new WatermarkDrawable(activity);
            watermarkDrawable.setmText(str).setmRotation(this.config.getmRotation()).setmTextColor(this.config.getmTextColor()).setmTextSize(this.config.getmTextSize());
            this.rootView = (ViewGroup) activity.getWindow().getDecorView();
            FrameLayout frameLayout = new FrameLayout(activity);
            this.layout = frameLayout;
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.layout.setBackgroundDrawable(watermarkDrawable);
            this.rootView.addView(this.layout);
            this.isAdded = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void showByCanAdd(Dialog dialog) {
        if (dialog == null || !this.config.isCanAdd() || TextUtils.isEmpty(this.config.getmText())) {
            return;
        }
        show(dialog);
    }

    public void show(Dialog dialog, String str) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.text = str;
        this.config.setmText(str);
        WatermarkDrawable watermarkDrawable = new WatermarkDrawable(dialog.getContext());
        watermarkDrawable.setmText(str).setmRotation(this.config.getmRotation()).setmTextColor(this.config.getmTextColor()).setmTextSize(this.config.getmTextSize());
        this.rootView = (ViewGroup) dialog.getWindow().getDecorView();
        FrameLayout frameLayout = new FrameLayout(dialog.getContext());
        this.layout = frameLayout;
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.layout.setBackgroundDrawable(watermarkDrawable);
        this.rootView.addView(this.layout);
        this.isAdded = true;
    }
}
