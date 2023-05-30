package com.jingdong.manto.m;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import java.math.BigDecimal;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.n a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ long f13285c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f13286e;

        /* renamed from: com.jingdong.manto.m.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0544a implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ com.jingdong.manto.q.r a;

            C0544a(a aVar, com.jingdong.manto.q.r rVar) {
                this.a = rVar;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.getView().setScrollY(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        }

        a(com.jingdong.manto.q.n nVar, int i2, long j2, int i3, String str) {
            this.a = nVar;
            this.b = i2;
            this.f13285c = j2;
            this.d = i3;
            this.f13286e = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.jingdong.manto.q.r rVar = this.a.t;
            Animator animator = rVar.w;
            if (animator != null) {
                animator.cancel();
                rVar.w = null;
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(rVar.getView().getScrollY(), this.b);
            ofInt.addUpdateListener(new C0544a(this, rVar));
            ofInt.setInterpolator(new DecelerateInterpolator());
            ofInt.setDuration(this.f13285c);
            ofInt.start();
            rVar.w = ofInt;
            this.a.a(this.d, b.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.f13286e));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.q.n nVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        long optLong = jSONObject.optLong("duration", 300L);
        if (jSONObject.has("scrollTop")) {
            try {
                com.jingdong.manto.sdk.thread.a.a(new a(nVar, Math.round(MantoDensityUtils.convertToDeviceSize(new BigDecimal(jSONObject.optString("scrollTop")).floatValue())), optLong, i2, str));
                return;
            } catch (Exception e2) {
                MantoLog.e("Jsapi_scrollWebviewTo", "opt scrollTop, e = %s", e2);
                str2 = "fail:invalid data " + MantoUtils.getNonNull(e2.getMessage());
            }
        } else {
            str2 = "fail:invalid data";
        }
        nVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "scrollWebviewTo";
    }
}
