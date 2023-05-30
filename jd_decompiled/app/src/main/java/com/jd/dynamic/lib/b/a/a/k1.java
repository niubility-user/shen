package com.jd.dynamic.lib.b.a.a;

import android.text.TextUtils;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.timer.TimerManager;
import com.jd.dynamic.base.timer.TimerRequest;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class k1 extends e1 {
    private void a(JSONObject jSONObject) {
        String timerId = TimerRequest.getTimerId(jSONObject.optString("timerId"), this.mEngine);
        com.jd.dynamic.lib.utils.t.e("Timer", "startTimer = " + timerId);
        String optString = jSONObject.optString("formatType");
        String optString2 = jSONObject.optString("type");
        long optLong = jSONObject.optLong("startValue", 0L);
        TimerRequest build = new TimerRequest.Builder().setTimerId(timerId).setStartValue(optLong).setEndValue(jSONObject.optLong("endValue", 0L)).setInterval(jSONObject.optLong("interval", 1000L)).setStep(jSONObject.optInt("step", 0)).setType(TimerRequest.getTimerType(optString2)).setFormatType(optString).build();
        TimerManager timerManager = this.mEngine.getTimerManager();
        if (timerManager == null) {
            timerManager = new TimerManager();
            this.mEngine.setTimerManager(timerManager);
        }
        timerManager.insertTimerRequest(build);
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        this.mEngine = dynamicTemplateEngine;
        if (dynamicTemplateEngine == null) {
            return null;
        }
        String str = (String) jSONObject.remove("fun");
        if (!TextUtils.isEmpty(str)) {
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -1583628285:
                    if (str.equals("startTimer")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 1619640419:
                    if (str.equals("stopTimer")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1870120173:
                    if (str.equals("stopAllTimers")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    a(jSONObject);
                    break;
                case 1:
                    TimerManager timerManager = dynamicTemplateEngine.getTimerManager();
                    if (timerManager != null) {
                        timerManager.cancelTimerRequest(TimerRequest.getTimerId(jSONObject.optString("timerId"), dynamicTemplateEngine));
                        break;
                    }
                    break;
                case 2:
                    TimerManager timerManager2 = dynamicTemplateEngine.getTimerManager();
                    if (timerManager2 != null) {
                        timerManager2.stopAllTimer();
                        break;
                    }
                    break;
            }
        }
        return null;
    }
}
