package com.jingdong.manto.jsapi.bluetooth.sdk.util;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class BleHelpExt {
    private static final String TAG = "BT.BleHelpExt";

    public static final void callFail(d0 d0Var, int i2, h hVar, int i3, String str) {
        MantoLog.d(TAG, String.format("callbackFail: api = %s, reason = %s", d0Var.getClass().getSimpleName(), str));
        HashMap hashMap = new HashMap();
        hashMap.put("errCode", Integer.valueOf(i3));
        hVar.a(i2, d0Var.putErrMsg(str, hashMap));
    }

    public static void callSuccess(d0 d0Var, int i2, h hVar) {
        callSuccess(d0Var, i2, hVar, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void callSuccess(d0 d0Var, int i2, h hVar, Map<String, Object> map) {
        MantoLog.d(TAG, "callbackSuccess: api = " + d0Var.getClass().getSimpleName());
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("errCode", 0);
        hVar.a(i2, d0Var.putErrMsg(IMantoBaseModule.SUCCESS, map));
    }

    @TargetApi(21)
    public static boolean isPeripheralBleSupported() {
        BluetoothAdapter bTAdapter = BTHelper.getBTAdapter();
        if (bTAdapter == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("isPeripheralBleSupported: isMultipleAdvertisementSupported = ");
        sb.append(bTAdapter.isMultipleAdvertisementSupported());
        sb.append(10);
        sb.append("isOffloadedFilteringSupported = ");
        sb.append(bTAdapter.isOffloadedFilteringSupported());
        sb.append(10);
        sb.append("isOffloadedScanBatchingSupported = ");
        sb.append(bTAdapter.isOffloadedScanBatchingSupported());
        sb.append(10);
        sb.append("bluetoothLeAdvertiser != null? = ");
        sb.append(bTAdapter.getBluetoothLeAdvertiser() != null);
        sb.append(10);
        MantoLog.d(TAG, sb.toString());
        return bTAdapter.getBluetoothLeAdvertiser() != null;
    }
}
