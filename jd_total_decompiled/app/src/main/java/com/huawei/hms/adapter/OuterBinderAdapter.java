package com.huawei.hms.adapter;

import android.content.Context;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes12.dex */
public class OuterBinderAdapter extends BinderAdapter {

    /* renamed from: j  reason: collision with root package name */
    private static final Object f1200j = new Object();

    /* renamed from: k  reason: collision with root package name */
    private static BinderAdapter f1201k;

    /* renamed from: l  reason: collision with root package name */
    private static String f1202l;

    /* renamed from: m  reason: collision with root package name */
    private static String f1203m;

    private OuterBinderAdapter(Context context, String str, String str2) {
        super(context, str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030 A[Catch: all -> 0x004b, TryCatch #0 {, blocks: (B:4:0x000a, B:6:0x000e, B:16:0x0047, B:17:0x0049, B:7:0x001a, B:9:0x0022, B:15:0x0030), top: B:22:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static BinderAdapter getInstance(Context context, String str, String str2) {
        boolean z;
        BinderAdapter binderAdapter;
        HMSLog.i("OuterBinderAdapter", "OuterBinderAdapter getInstance.");
        synchronized (f1200j) {
            if (f1201k == null) {
                f1202l = str;
                f1203m = str2;
                f1201k = new OuterBinderAdapter(context, str, str2);
            } else {
                if (Objects.equal(f1202l, str) && Objects.equal(f1203m, str2)) {
                    z = false;
                    if (z) {
                        HMSLog.i("OuterBinderAdapter", "OuterBinderAdapter getInstance refresh adapter");
                        f1202l = str;
                        f1203m = str2;
                        f1201k.unBind();
                        f1201k = new OuterBinderAdapter(context, str, str2);
                    }
                }
                z = true;
                if (z) {
                }
            }
            binderAdapter = f1201k;
        }
        return binderAdapter;
    }

    @Override // com.huawei.hms.adapter.BinderAdapter
    protected int getConnTimeOut() {
        return 1001;
    }

    @Override // com.huawei.hms.adapter.BinderAdapter
    protected int getMsgDelayDisconnect() {
        return 1002;
    }
}
