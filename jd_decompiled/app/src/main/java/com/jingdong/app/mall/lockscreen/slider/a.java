package com.jingdong.app.mall.lockscreen.slider;

import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.lockscreen.slider.SliderPanel;

/* loaded from: classes4.dex */
public class a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.lockscreen.slider.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0343a implements SliderPanel.i {
        private final ArgbEvaluator a = new ArgbEvaluator();
        final /* synthetic */ com.jingdong.app.mall.lockscreen.slider.b b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Activity f11173c;

        C0343a(com.jingdong.app.mall.lockscreen.slider.b bVar, Activity activity) {
            this.b = bVar;
            this.f11173c = activity;
        }

        @Override // com.jingdong.app.mall.lockscreen.slider.SliderPanel.i
        @TargetApi(21)
        public void a(float f2) {
            if (Build.VERSION.SDK_INT >= 21 && this.b.h()) {
                this.f11173c.getWindow().setStatusBarColor(((Integer) this.a.evaluate(f2, Integer.valueOf(this.b.m()), Integer.valueOf(this.b.q()))).intValue());
            }
            if (this.b.k() != null) {
                this.b.k().a(f2);
            }
        }

        @Override // com.jingdong.app.mall.lockscreen.slider.SliderPanel.i
        public void b() {
            if (this.b.k() != null) {
                this.b.k().c();
            }
        }

        @Override // com.jingdong.app.mall.lockscreen.slider.SliderPanel.i
        public void onClosed() {
            if (this.b.k() != null) {
                this.b.k().d();
            }
            this.f11173c.finish();
            this.f11173c.overridePendingTransition(0, 0);
        }

        @Override // com.jingdong.app.mall.lockscreen.slider.SliderPanel.i
        public void onStateChanged(int i2) {
            if (this.b.k() != null) {
                this.b.k().b(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements c {
        b(SliderPanel sliderPanel) {
        }
    }

    public static c a(Activity activity, com.jingdong.app.mall.lockscreen.slider.b bVar) {
        SliderPanel c2 = c(activity, bVar);
        c2.n(new C0343a(bVar, activity));
        return b(c2);
    }

    private static c b(SliderPanel sliderPanel) {
        return new b(sliderPanel);
    }

    private static SliderPanel c(Activity activity, com.jingdong.app.mall.lockscreen.slider.b bVar) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View childAt = viewGroup.getChildAt(0);
        viewGroup.removeViewAt(0);
        SliderPanel sliderPanel = new SliderPanel(activity, childAt, bVar);
        sliderPanel.setId(R.id.sliderable_panel);
        childAt.setId(R.id.sliderable_content);
        sliderPanel.addView(childAt);
        viewGroup.addView(sliderPanel, 0);
        return sliderPanel;
    }
}
