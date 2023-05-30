package com.jdjr.risk.device.b;

import android.content.Context;
import android.content.pm.PackageManager;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class j extends a {
    public j() {
        this.a = new com.jdjr.risk.device.entity.i();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_permission_info";
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // com.jdjr.risk.device.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void b(Context context, JSONObject jSONObject) {
        int i2;
        int i3;
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.i iVar = (com.jdjr.risk.device.entity.i) this.a;
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                int i4 = 0;
                try {
                    i2 = packageManager.checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName());
                } catch (Exception unused) {
                    i2 = 0;
                }
                try {
                    i3 = packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName());
                    try {
                        i4 = packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", context.getPackageName());
                    } catch (Exception unused2) {
                    }
                } catch (Exception unused3) {
                    i3 = 0;
                    if (jSONObject.optInt("p_fine_location") == 1) {
                    }
                    if (jSONObject.optInt("p_location") == 1) {
                    }
                    if (jSONObject.optInt("p_phone") != 1) {
                    }
                }
                if (jSONObject.optInt("p_fine_location") == 1) {
                    iVar.a((i4 + 1) + "");
                }
                if (jSONObject.optInt("p_location") == 1) {
                    iVar.b((i3 + 1) + "");
                }
                if (jSONObject.optInt("p_phone") != 1) {
                    iVar.c((i2 + 1) + "");
                }
            }
        }
    }
}
