package com.jingdong.manto.m.n0;

import android.animation.ArgbEvaluator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i extends d0 {

    /* loaded from: classes15.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ n a;

        a(i iVar, n nVar) {
            this.a = nVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.a(((Integer) valueAnimator.getAnimatedValue()).intValue());
            this.a.c(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    /* loaded from: classes15.dex */
    class b implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ n a;

        b(i iVar, n nVar) {
            this.a = nVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.b(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    /* loaded from: classes15.dex */
    class c implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ n a;

        c(i iVar, n nVar) {
            this.a = nVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        int i3;
        com.jingdong.manto.h hVar2 = hVar;
        try {
            int a2 = com.jingdong.manto.ui.d.a(jSONObject.optString("frontColor"), -1);
            int a3 = com.jingdong.manto.ui.d.a(jSONObject.getString(ViewProps.BACKGROUND_COLOR), -1);
            double optDouble = jSONObject.optDouble("alpha", 1.0d);
            JSONObject optJSONObject = jSONObject.optJSONObject("animation");
            if (optJSONObject != null) {
                i3 = optJSONObject.optInt("duration");
                str2 = optJSONObject.optString("timingFunc");
            } else {
                str2 = "";
                i3 = 0;
            }
            n pageView = c0.getPageView(hVar);
            if (pageView == null) {
                hVar2.a(i2, putErrMsg("fail:no page for now", null, str));
                return;
            }
            int backgroundColor = pageView.q.getBackgroundColor();
            int foregroundColor = pageView.q.getForegroundColor();
            double alpha = pageView.q.getAlpha();
            TimeInterpolator linearInterpolator = "linear".equals(str2) ? new LinearInterpolator() : "easeIn".equals(str2) ? new AccelerateInterpolator() : "easeOut".equals(str2) ? new DecelerateInterpolator() : "easeInOut".equals(str2) ? new AccelerateDecelerateInterpolator() : null;
            ValueAnimator valueAnimator = new ValueAnimator();
            try {
                valueAnimator.setIntValues(backgroundColor, a3);
                valueAnimator.setEvaluator(new ArgbEvaluator());
                long j2 = i3;
                valueAnimator.setDuration(j2);
                valueAnimator.setInterpolator(linearInterpolator);
                valueAnimator.addUpdateListener(new a(this, pageView));
                ValueAnimator valueAnimator2 = new ValueAnimator();
                valueAnimator2.setIntValues(foregroundColor, a2);
                valueAnimator2.setEvaluator(new ArgbEvaluator());
                valueAnimator2.setDuration(j2);
                valueAnimator2.setInterpolator(linearInterpolator);
                valueAnimator2.addUpdateListener(new b(this, pageView));
                ValueAnimator valueAnimator3 = new ValueAnimator();
                valueAnimator3.setFloatValues((float) alpha, (float) optDouble);
                valueAnimator3.setDuration(j2);
                valueAnimator3.setInterpolator(linearInterpolator);
                valueAnimator3.addUpdateListener(new c(this, pageView));
                valueAnimator.start();
                valueAnimator2.start();
                hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
            } catch (Exception e2) {
                e = e2;
                hVar2 = hVar;
                MantoLog.e("SetNavigationBarColor", "Color parse error  " + e.getMessage());
                hVar2.a(i2, putErrMsg("fail", null, str));
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setNavigationBarColor";
    }
}
