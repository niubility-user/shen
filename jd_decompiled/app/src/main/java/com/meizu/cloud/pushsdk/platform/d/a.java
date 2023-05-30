package com.meizu.cloud.pushsdk.platform.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class a extends c {

    /* renamed from: j  reason: collision with root package name */
    private int[] f16016j;

    /* renamed from: k  reason: collision with root package name */
    private int f16017k;

    /* renamed from: l  reason: collision with root package name */
    private String f16018l;

    public a(Context context, String str, String str2, com.meizu.cloud.pushsdk.platform.c.a aVar, ScheduledExecutorService scheduledExecutorService) {
        super(context, str, str2, aVar, scheduledExecutorService);
        this.f16022g = MinSdkChecker.isSupportSetDrawableSmallIcon();
    }

    public a(Context context, ScheduledExecutorService scheduledExecutorService, boolean z) {
        this(context, null, null, null, scheduledExecutorService);
        this.f16023h = z;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected BasicPushStatus a() {
        return null;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected void h(BasicPushStatus basicPushStatus) {
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected BasicPushStatus k() {
        return null;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected boolean m() {
        int i2 = this.f16017k;
        if (i2 != 0) {
            int[] iArr = this.f16016j;
            if (iArr == null || iArr.length <= 0 || i2 != 1) {
                return i2 == 2 && !TextUtils.isEmpty(this.f16018l);
            }
            return true;
        }
        return true;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected BasicPushStatus n() {
        int i2 = this.f16017k;
        if (i2 == 0) {
            if (!MinSdkChecker.isSupportSetDrawableSmallIcon()) {
                DebugLogger.e("Strategy", "android 6.0 blow so cancel all by context");
                com.meizu.cloud.pushsdk.notification.g.b.c(this.b);
            }
            com.meizu.cloud.pushsdk.notification.g.b.d(this.b, this.f16020e);
            return null;
        } else if (i2 != 1) {
            if (i2 != 2) {
                return null;
            }
            com.meizu.cloud.pushsdk.notification.g.b.f(this.b, this.f16020e, this.f16018l);
            return null;
        } else {
            int[] iArr = this.f16016j;
            if (iArr != null) {
                for (int i3 : iArr) {
                    DebugLogger.e("Strategy", "clear notifyId " + i3);
                    com.meizu.cloud.pushsdk.notification.g.b.e(this.b, this.f16020e, i3);
                }
                return null;
            }
            return null;
        }
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected Intent q() {
        Intent intent = new Intent();
        intent.putExtra("strategy_package_name", this.b.getPackageName());
        intent.putExtra("strategy_type", s());
        intent.putExtra("strategy_child_type", this.f16017k);
        int i2 = this.f16017k;
        if (i2 == 2) {
            intent.putExtra("strategy_params", this.f16018l);
            return intent;
        } else if (i2 == 1) {
            return null;
        } else {
            return intent;
        }
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected Intent[] r() {
        int[] iArr = this.f16016j;
        if (iArr != null) {
            Intent[] intentArr = new Intent[iArr.length];
            for (int i2 = 0; i2 < this.f16016j.length; i2++) {
                DebugLogger.i("Strategy", "send notifyId " + this.f16016j[i2] + " to PushManagerService");
                Intent intent = new Intent();
                intent.putExtra("strategy_package_name", this.b.getPackageName());
                intent.putExtra("strategy_type", s());
                intent.putExtra("strategy_child_type", this.f16017k);
                intent.putExtra("strategy_params", "" + this.f16016j[i2]);
                intentArr[i2] = intent;
            }
            return intentArr;
        }
        return null;
    }

    @Override // com.meizu.cloud.pushsdk.platform.d.c
    protected int s() {
        return 64;
    }

    public void v(int... iArr) {
        this.f16016j = iArr;
    }

    public void w(int i2) {
        this.f16017k = i2;
    }

    public void x(String str) {
        this.f16018l = str;
    }
}
