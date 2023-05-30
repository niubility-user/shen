package com.jingdong.jdma.iml;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.jdma.common.utils.i;
import com.jingdong.jdma.minterface.BaseEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

/* loaded from: classes12.dex */
public class a implements Application.ActivityLifecycleCallbacks {
    private long a = 0;
    private boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    private InterfaceC0495a f12772c;
    private com.jingdong.jdma.g.a d;

    /* renamed from: com.jingdong.jdma.iml.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public interface InterfaceC0495a {
        void a(HashMap<String, String> hashMap, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public final class b extends BaseEvent {
        b(a aVar) {
        }

        @Override // com.jingdong.jdma.minterface.BaseEvent
        protected void addDataToMap(HashMap<String, String> hashMap) {
        }

        @Override // com.jingdong.jdma.minterface.BaseEvent
        public String getLogType() {
            return "sr";
        }

        @Override // com.jingdong.jdma.minterface.BaseEvent
        public String getLts() {
            return "st";
        }
    }

    public a(com.jingdong.jdma.g.a aVar) {
        this.d = aVar;
    }

    private void a() {
        if (this.a == 0) {
            a("Create");
            this.a = System.currentTimeMillis();
            return;
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(this.a);
        Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
        if (calendar.get(1) != calendar2.get(1)) {
            a(this.b ? "Wakeup" : "Active");
            this.a = System.currentTimeMillis();
        } else if (calendar.get(2) != calendar2.get(2)) {
            a(this.b ? "Wakeup" : "Active");
            this.a = System.currentTimeMillis();
        } else if (calendar.get(5) != calendar2.get(5)) {
            a(this.b ? "Wakeup" : "Active");
            this.a = System.currentTimeMillis();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.d.i();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        com.jingdong.jdma.common.utils.c.f12679i = true;
        if (i.a(activity)) {
            a();
            this.b = false;
        }
        this.d.b(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (i.a(activity)) {
            a();
            this.b = !com.jingdong.jdma.common.utils.c.f12679i;
        }
    }

    public void a(InterfaceC0495a interfaceC0495a) {
        this.f12772c = interfaceC0495a;
    }

    private void a(String str) {
        HashMap<String, String> map = new b(this).toMap();
        map.put("start_type", str);
        InterfaceC0495a interfaceC0495a = this.f12772c;
        if (interfaceC0495a != null) {
            interfaceC0495a.a(map, "st");
        }
    }
}
