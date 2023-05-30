package com.jingdong.manto.m.t0.d.c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.manto.m.t0.d.d.g;
import com.jingdong.manto.m.t0.d.d.h;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@TargetApi(18)
/* loaded from: classes15.dex */
public class e {
    public Context a;
    public Map<String, d> b;

    /* renamed from: c  reason: collision with root package name */
    public com.jingdong.manto.m.t0.d.d.a f13641c;
    public g d;

    public e(Context context) {
        this.a = context;
    }

    public static boolean a(List<String> list, List<String> list2) {
        Iterator<String> it = list.iterator();
        if (!it.hasNext()) {
            return false;
        }
        String next = it.next();
        Iterator<String> it2 = list2.iterator();
        while (true) {
            if (it2.hasNext() && TextUtils.equals(next, it2.next())) {
                return true;
            }
        }
    }

    public final List<h> a() {
        BluetoothGatt bluetoothGatt;
        IPermission iPermission;
        ArrayList arrayList = new ArrayList();
        Map<String, d> map = this.b;
        if (map != null) {
            boolean z = false;
            try {
                if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                    z = true;
                }
                if (z) {
                    for (d dVar : new ArrayList(map.values())) {
                        if (dVar != null && (bluetoothGatt = dVar.b) != null) {
                            arrayList.add(new h(bluetoothGatt.getDevice().getName(), dVar.a));
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return arrayList;
    }
}
