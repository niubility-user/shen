package com.jd.manto.hd.wifi;

import android.net.Network;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jd.manto.hd.wifi.a;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.wifi.JsApiWifi;
import com.jingdong.manto.jsapi.refact.wifi.WifiInfo;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;

/* loaded from: classes17.dex */
public class JsApiWifiNew extends JsApiWifi implements a.h {
    private boolean a;
    private com.jd.manto.hd.wifi.a b;

    /* renamed from: c  reason: collision with root package name */
    private SparseArray<MantoResultCallBack> f6707c;

    /* loaded from: classes17.dex */
    class a implements IPermission.PermissionCallBack {
        final /* synthetic */ MantoResultCallBack a;

        a(MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onDenied() {
            MantoResultCallBack mantoResultCallBack = this.a;
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onFailed(JsApiWifiNew.this.a(false, R2.drawable.wxa_trading_guarantee_icon_green, "not support"));
            }
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onGranted() {
            JsApiWifiNew.this.c();
            JsApiWifiNew.this.a = true;
            if (JsApiWifiNew.this.b != null) {
                JsApiWifiNew.this.b.i();
            }
            MantoResultCallBack mantoResultCallBack = this.a;
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onSuccess(JsApiWifiNew.this.b());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Runnable {
        final /* synthetic */ IPermission.PermissionCallBack a;

        b(IPermission.PermissionCallBack permissionCallBack) {
            this.a = permissionCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a != null) {
                if (MantoPermission.hasPermission("android.permission.ACCESS_FINE_LOCATION")) {
                    this.a.onGranted();
                } else {
                    MantoPermission.requestPermission(JsApiWifiNew.this.getActivity(), "android.permission.ACCESS_FINE_LOCATION", this.a);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle a(boolean z, int i2, String str) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
        } else {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            bundle.putString("message", str);
            bundle.putInt("errCode", i2);
        }
        return bundle;
    }

    private void a(MantoResultCallBack mantoResultCallBack) {
        if (mantoResultCallBack != null) {
            mantoResultCallBack.onSuccess(b());
        }
        ArrayList arrayList = new ArrayList();
        com.jd.manto.hd.wifi.a aVar = this.b;
        if (aVar != null) {
            for (ScanResult scanResult : aVar.c()) {
                if (!TextUtils.isEmpty(scanResult.SSID)) {
                    WifiInfo wifiInfo = new WifiInfo();
                    wifiInfo.mSsid = scanResult.SSID;
                    wifiInfo.mBSSID = scanResult.BSSID;
                    wifiInfo.signalStrength = scanResult.level;
                    if (Build.VERSION.SDK_INT > 21) {
                        wifiInfo.frequency = scanResult.frequency;
                    }
                    arrayList.add(wifiInfo);
                }
            }
        }
        onGetWifiList(arrayList);
    }

    private void a(IPermission.PermissionCallBack permissionCallBack) {
        getActivity().runOnUiThread(new b(permissionCallBack));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle b() {
        return a(true, 1, "");
    }

    private boolean b(MantoResultCallBack mantoResultCallBack) {
        if (!this.a && mantoResultCallBack != null) {
            mantoResultCallBack.onFailed(a(false, R2.drawable.wxa_spinner_white_48_outer_holo, "not init"));
        }
        return !this.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.f6707c == null) {
            this.f6707c = new SparseArray<>();
        }
        if (this.b == null) {
            this.b = new com.jd.manto.hd.wifi.a(getActivity(), this);
        }
    }

    @Override // com.jd.manto.hd.wifi.a.h
    public void a() {
        MantoResultCallBack mantoResultCallBack;
        SparseArray<MantoResultCallBack> sparseArray = this.f6707c;
        if (sparseArray == null || (mantoResultCallBack = sparseArray.get(R2.attr.loadMax)) == null) {
            return;
        }
        a(mantoResultCallBack);
        this.f6707c.remove(R2.attr.loadMax);
    }

    @Override // com.jd.manto.hd.wifi.a.h
    public void a(int i2, String str) {
        MantoResultCallBack mantoResultCallBack;
        String str2 = "onConnectWifiFail errorCode " + i2;
        SparseArray<MantoResultCallBack> sparseArray = this.f6707c;
        if (sparseArray == null || (mantoResultCallBack = sparseArray.get(R2.attr.loadStartAngle)) == null) {
            return;
        }
        mantoResultCallBack.onFailed(a(false, i2, str));
    }

    @Override // com.jd.manto.hd.wifi.a.h
    public void a(Network network) {
        MantoResultCallBack mantoResultCallBack;
        if (this.b != null) {
            SparseArray<MantoResultCallBack> sparseArray = this.f6707c;
            if (sparseArray != null && (mantoResultCallBack = sparseArray.get(R2.attr.loadStartAngle)) != null) {
                mantoResultCallBack.onSuccess(b());
            }
            android.net.wifi.WifiInfo b2 = this.b.b();
            if (b2 != null) {
                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.mSsid = this.b.a(b2);
                wifiInfo.mBSSID = BaseInfo.getWifiBSSID();
                wifiInfo.signalStrength = b2.getRssi();
                if (Build.VERSION.SDK_INT > 21) {
                    wifiInfo.frequency = b2.getFrequency();
                }
                onWifiConnected(wifiInfo);
            }
        }
    }

    @Override // com.jd.manto.hd.wifi.a.h
    public void a(String str) {
        String str2 = "onWifiDisconnected ssid " + str;
    }

    @Override // com.jingdong.manto.jsapi.refact.wifi.JsApiWifi
    public void connectWifi(String str, String str2, String str3, boolean z, MantoResultCallBack mantoResultCallBack) {
        if (b(mantoResultCallBack)) {
            return;
        }
        com.jd.manto.hd.wifi.a aVar = this.b;
        if (aVar != null) {
            aVar.a(str, str3, z);
        }
        SparseArray<MantoResultCallBack> sparseArray = this.f6707c;
        if (sparseArray != null) {
            sparseArray.put(R2.attr.loadStartAngle, mantoResultCallBack);
        }
    }

    @Override // com.jingdong.manto.jsapi.refact.wifi.JsApiWifi
    public void getConnectedWifi(MantoResultCallBack mantoResultCallBack) {
        com.jd.manto.hd.wifi.a aVar;
        if (b(mantoResultCallBack) || (aVar = this.b) == null || mantoResultCallBack == null) {
            return;
        }
        android.net.wifi.WifiInfo b2 = aVar.b();
        if (b2 == null) {
            Bundle bundle = new Bundle();
            bundle.putString("message", "wifiInfo == null");
            mantoResultCallBack.onFailed(bundle);
            return;
        }
        WifiInfo wifiInfo = new WifiInfo();
        wifiInfo.mSsid = this.b.a(b2);
        wifiInfo.mBSSID = BaseInfo.getWifiBSSID();
        wifiInfo.signalStrength = b2.getRssi();
        if (Build.VERSION.SDK_INT > 21) {
            wifiInfo.frequency = b2.getFrequency();
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("wifi", wifiInfo.toJsonObject().toString());
        mantoResultCallBack.onSuccess(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.refact.wifi.JsApiWifi
    public void getWifiList(MantoResultCallBack mantoResultCallBack) {
        com.jd.manto.hd.wifi.a aVar;
        if (b(mantoResultCallBack) || (aVar = this.b) == null) {
            return;
        }
        if (!aVar.h()) {
            a(mantoResultCallBack);
            return;
        }
        SparseArray<MantoResultCallBack> sparseArray = this.f6707c;
        if (sparseArray != null) {
            sparseArray.put(R2.attr.loadMax, mantoResultCallBack);
        }
    }

    @Override // com.jingdong.manto.jsapi.refact.wifi.JsApiWifi
    public void startWifi(MantoResultCallBack mantoResultCallBack) {
        a(new a(mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.refact.wifi.JsApiWifi
    public void stopWifi(MantoResultCallBack mantoResultCallBack) {
        this.a = false;
        com.jd.manto.hd.wifi.a aVar = this.b;
        if (aVar != null) {
            aVar.j();
        }
        if (mantoResultCallBack != null) {
            mantoResultCallBack.onSuccess(b());
        }
    }
}
