package com.jd.dynamic.lib.viewparse.c.e;

import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.timer.TimerManager;
import com.jd.dynamic.base.timer.TimerRequest;
import com.jd.dynamic.base.timer.TimerResultReceiver;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class u0 extends p0<View> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    public void a(HashMap<String, String> hashMap, View view) {
        DynamicTemplateEngine dynamicTemplateEngine = this.a;
        if (dynamicTemplateEngine == null || dynamicTemplateEngine.isRelease) {
            return;
        }
        String str = hashMap.get("timerId");
        String str2 = hashMap.get("onTimer");
        String timerId = TextUtils.isEmpty(str) ? (String) view.getTag(R.id.dynamic_timer_receiver) : TimerRequest.getTimerId(str, this.a);
        if (TextUtils.isEmpty(timerId) || TextUtils.isEmpty(str2)) {
            return;
        }
        DynamicTemplateEngine dynamicTemplateEngine2 = this.a;
        if (dynamicTemplateEngine2.isAttached) {
            TimerManager timerManager = dynamicTemplateEngine2.getTimerManager();
            if (timerManager == null) {
                timerManager = new TimerManager();
                this.a.setTimerManager(timerManager);
            }
            timerManager.unregisterTimerReceiver(timerId);
            view.setTag(R.id.dynamic_timer_receiver, timerId);
            com.jd.dynamic.lib.utils.t.e("TimerAttrParse", "add " + timerId, "mEngine isAttached = " + this.a.isAttached);
            timerManager.registerTimerReceiver(new TimerResultReceiver(view, str2, timerId, this.a));
        }
    }
}
