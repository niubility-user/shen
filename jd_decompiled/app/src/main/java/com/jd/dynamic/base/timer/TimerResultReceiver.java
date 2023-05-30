package com.jd.dynamic.base.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.core.util.ObjectsCompat;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.s;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.common.XView2.common.XView2Constants;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class TimerResultReceiver extends BroadcastReceiver {
    private View a;
    private String b;

    /* renamed from: c */
    private String f2066c;
    private DynamicTemplateEngine d;

    /* renamed from: e */
    private int f2067e;

    public TimerResultReceiver(View view, String str, String str2, DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = view;
        this.b = str;
        this.f2066c = str2;
        this.d = dynamicTemplateEngine;
        this.f2067e = view.getId();
    }

    private void a(String str) {
        String str2 = (String) this.d.getCachePool().getData("timerData");
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str2);
            jSONObject.remove(str);
            this.d.getCachePool().putData("timerData", jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    public /* synthetic */ void b(String str) {
        View view = this.a;
        s.b(str, view, this.d, view);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TimerResultReceiver) {
            TimerResultReceiver timerResultReceiver = (TimerResultReceiver) obj;
            return this.f2067e == timerResultReceiver.f2067e && TextUtils.equals(this.f2066c, timerResultReceiver.f2066c);
        }
        return false;
    }

    public String getTimerId() {
        return this.f2066c;
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.f2066c, Integer.valueOf(this.f2067e));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), this.f2066c)) {
            String stringExtra = intent.getStringExtra("currentValue");
            int intExtra = intent.getIntExtra(XView2Constants.STATE, 0);
            boolean z = true;
            t.e("TimerResultReceiver", "timerId = " + this.f2066c, " state = " + intExtra);
            DynamicTemplateEngine dynamicTemplateEngine = this.d;
            if (dynamicTemplateEngine != null && !dynamicTemplateEngine.isRelease) {
                String str = (String) dynamicTemplateEngine.getCachePool().getData("timerData");
                try {
                    JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("currentValue", stringExtra);
                    jSONObject2.put(XView2Constants.STATE, intExtra);
                    jSONObject.put(this.f2066c, jSONObject2);
                    this.d.getCachePool().putData("timerData", jSONObject.toString());
                } catch (Exception unused) {
                }
            }
            if (TextUtils.equals((String) this.a.getTag(R.id.dynamic_timer_receiver), this.f2066c)) {
                View view = this.a;
                if (view != null) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        z = view.isAttachedToWindow();
                    } else {
                        z = view.getWindowToken() != null;
                    }
                }
                if (z) {
                    Observable.from(s.i(this.b)).forEach(new Action1() { // from class: com.jd.dynamic.base.timer.c
                        {
                            TimerResultReceiver.this = this;
                        }

                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            TimerResultReceiver.this.b((String) obj);
                        }
                    });
                    if (intExtra == 2) {
                        a(this.f2066c);
                    }
                }
            }
        }
    }
}
