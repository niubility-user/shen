package com.jingdong.common.jump;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.common.BaseApplication;
import com.jingdong.jdsdk.JdSdk;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class JumpLoadingWidget {
    private View progressBarLayout;
    private ViewGroup rootLayout;

    /* JADX INFO: Access modifiers changed from: private */
    public void addProgressLayout() {
        if (this.rootLayout != null) {
            View progressBarLayout = getProgressBarLayout();
            this.progressBarLayout = progressBarLayout;
            if (progressBarLayout == null) {
                return;
            }
            this.rootLayout.addView(progressBarLayout, new ViewGroup.LayoutParams(-1, -1));
            this.rootLayout.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ViewGroup getDefaultRootFrameLayout(Activity activity) {
        if (activity == null) {
            return null;
        }
        return (ViewGroup) activity.getWindow().peekDecorView();
    }

    private View getLoadingView() {
        return BaseApplication.getLoadingProgressBar();
    }

    private View getProgressBarLayout() {
        RelativeLayout relativeLayout;
        RelativeLayout relativeLayout2 = null;
        try {
            relativeLayout = new RelativeLayout(JdSdk.getInstance().getApplicationContext());
        } catch (Throwable unused) {
        }
        try {
            relativeLayout.addView(getLoadingView());
            return relativeLayout;
        } catch (Throwable unused2) {
            relativeLayout2 = relativeLayout;
            return relativeLayout2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dismiss() {
        ViewGroup viewGroup = this.rootLayout;
        if (viewGroup != null) {
            viewGroup.post(new Runnable() { // from class: com.jingdong.common.jump.JumpLoadingWidget.3
                @Override // java.lang.Runnable
                public void run() {
                    if (JumpLoadingWidget.this.rootLayout != null && JumpLoadingWidget.this.progressBarLayout != null && JumpLoadingWidget.this.rootLayout.indexOfChild(JumpLoadingWidget.this.progressBarLayout) != -1) {
                        JumpLoadingWidget.this.rootLayout.removeView(JumpLoadingWidget.this.progressBarLayout);
                    }
                    JumpLoadingWidget.this.progressBarLayout = null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showProgressBar(final Context context) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.jingdong.common.jump.JumpLoadingWidget.1
                @Override // java.lang.Runnable
                public void run() {
                    JumpLoadingWidget jumpLoadingWidget = JumpLoadingWidget.this;
                    jumpLoadingWidget.rootLayout = jumpLoadingWidget.getDefaultRootFrameLayout((Activity) context);
                    JumpLoadingWidget.this.addProgressLayout();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showProgressBar(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        this.rootLayout = viewGroup;
        viewGroup.post(new Runnable() { // from class: com.jingdong.common.jump.JumpLoadingWidget.2
            @Override // java.lang.Runnable
            public void run() {
                JumpLoadingWidget.this.addProgressLayout();
            }
        });
    }
}
