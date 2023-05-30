package com.jingdong.app.mall.bundle.jdrhsdk.ui;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.jingdong.app.mall.bundle.jdrhsdk.d.d;
import com.jingdong.app.mall.bundle.jdrhsdk.d.e;

/* loaded from: classes2.dex */
public abstract class a extends Fragment {
    private String a() {
        return getClass().getName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Activity activity) {
        if (activity == null) {
            return;
        }
        e.w(activity);
        m(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Activity activity, View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        f(activity, layoutParams, i2);
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Activity activity, View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        g(activity, layoutParams, i2, i3);
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(Activity activity, View view, int i2, int i3, int i4, int i5) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams.getClass().equals(RelativeLayout.LayoutParams.class)) {
            k(activity, (RelativeLayout.LayoutParams) layoutParams, i2, i3, i4, i5);
        } else if (layoutParams.getClass().equals(LinearLayout.LayoutParams.class)) {
            j(activity, (LinearLayout.LayoutParams) layoutParams, i2, i3, i4, i5);
        }
        view.setLayoutParams(layoutParams);
    }

    protected void f(Activity activity, ViewGroup.LayoutParams layoutParams, int i2) {
        if (e.v(activity)) {
            layoutParams.width = i2 < 0 ? i2 : e.n(activity, i2);
            if (i2 >= 0) {
                i2 = e.n(activity, i2);
            }
        } else {
            layoutParams.width = i2 < 0 ? i2 : e.b(activity, i2);
            if (i2 >= 0) {
                i2 = e.b(activity, i2);
            }
        }
        layoutParams.height = i2;
    }

    protected void g(Activity activity, ViewGroup.LayoutParams layoutParams, int i2, int i3) {
        if (e.v(activity)) {
            if (i2 >= 0) {
                i2 = e.n(activity, i2);
            }
            layoutParams.width = i2;
            if (i3 >= 0) {
                i3 = e.n(activity, i3);
            }
        } else {
            if (i2 >= 0) {
                i2 = e.b(activity, i2);
            }
            layoutParams.width = i2;
            if (i3 >= 0) {
                i3 = e.b(activity, i3);
            }
        }
        layoutParams.height = i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h(Activity activity, Button button, int i2) {
        button.setTextSize(0, e.v(activity) ? e.n(activity, i2) : e.b(activity, i2));
    }

    protected void j(Activity activity, LinearLayout.LayoutParams layoutParams, int i2, int i3, int i4, int i5) {
        int b;
        if (e.v(activity)) {
            layoutParams.topMargin = e.n(activity, i3);
            layoutParams.bottomMargin = e.n(activity, i5);
            layoutParams.leftMargin = e.n(activity, i2);
            b = e.n(activity, i4);
        } else {
            layoutParams.topMargin = e.b(activity, i3);
            layoutParams.bottomMargin = e.b(activity, i5);
            layoutParams.leftMargin = e.b(activity, i2);
            b = e.b(activity, i4);
        }
        layoutParams.rightMargin = b;
    }

    protected void k(Activity activity, RelativeLayout.LayoutParams layoutParams, int i2, int i3, int i4, int i5) {
        int b;
        if (e.v(activity)) {
            layoutParams.topMargin = e.n(activity, i3);
            layoutParams.bottomMargin = e.n(activity, i5);
            layoutParams.leftMargin = e.n(activity, i2);
            b = e.n(activity, i4);
        } else {
            layoutParams.topMargin = e.b(activity, i3);
            layoutParams.bottomMargin = e.b(activity, i5);
            layoutParams.leftMargin = e.b(activity, i2);
            b = e.b(activity, i4);
        }
        layoutParams.rightMargin = b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l(Activity activity, TextView textView, int i2) {
        textView.setTextSize(0, e.v(activity) ? e.n(activity, i2) : e.b(activity, i2));
    }

    protected abstract void m(Activity activity);

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        d.a(a(), "onConfigurationChanged");
        b(getActivity());
    }
}
