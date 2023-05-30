package com.jd.manto.hd.bluetooth.peripheral;

import android.app.Activity;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoUtils;
import java.util.List;
import org.json.JSONObject;

@RequiresApi(api = 21)
/* loaded from: classes17.dex */
public class JsApiPeripheral extends AbstractMantoModule {

    /* loaded from: classes17.dex */
    class a implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ String[] b;

        /* renamed from: com.jd.manto.hd.bluetooth.peripheral.JsApiPeripheral$a$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class C0193a implements IPermission.PermissionCallBack {
            C0193a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(a.this.a, "\u84dd\u7259\u626b\u63cf\u3001\u94fe\u63a5\u3001\u5e7f\u64ad\u6743\u9650\u5c1a\u672a\u5f00\u542f,\u8bf7\u5f00\u542f\u540e\u91cd\u8bd5", 0).show();
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
            }
        }

        a(JsApiPeripheral jsApiPeripheral, Activity activity, String[] strArr) {
            this.a = activity;
            this.b = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermissions(this.a, this.b, new C0193a());
        }
    }

    /* loaded from: classes17.dex */
    class b extends AdvertiseCallback {
        final /* synthetic */ MantoResultCallBack a;

        b(JsApiPeripheral jsApiPeripheral, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartFailure(int i2) {
            String str;
            super.onStartFailure(i2);
            if (i2 == 1) {
                str = "fail:data too large";
            } else if (i2 == 2) {
                str = "fail:too many advertisers";
            } else if (i2 == 3) {
                str = "fail:already started";
            } else if (i2 == 4) {
                str = "fail:internal error";
            } else if (i2 != 5) {
                str = "fail:Unhandled " + i2 + " error";
            } else {
                str = "fail:feature unsupported";
            }
            Bundle bundle = new Bundle();
            bundle.putInt("errCode", 10008);
            bundle.putString("message", str);
            this.a.onFailed(bundle);
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartSuccess(AdvertiseSettings advertiseSettings) {
            super.onStartSuccess(advertiseSettings);
            this.a.onSuccess(new Bundle());
        }
    }

    /* loaded from: classes17.dex */
    class c implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ BluetoothLeAdvertiser b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ AdvertiseSettings f6677c;
        final /* synthetic */ AdvertiseData d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ AdvertiseCallback f6678e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6679f;

        /* loaded from: classes17.dex */
        class a implements IPermission.PermissionCallBack {
            a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(c.this.a, "\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f", 0).show();
                Bundle bundle = new Bundle();
                bundle.putInt("errCode", 10008);
                bundle.putString("message", "\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f");
                c.this.f6679f.onFailed(bundle);
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                c cVar = c.this;
                cVar.b.startAdvertising(cVar.f6677c, cVar.d, cVar.f6678e);
            }
        }

        c(JsApiPeripheral jsApiPeripheral, Activity activity, BluetoothLeAdvertiser bluetoothLeAdvertiser, AdvertiseSettings advertiseSettings, AdvertiseData advertiseData, AdvertiseCallback advertiseCallback, MantoResultCallBack mantoResultCallBack) {
            this.a = activity;
            this.b = bluetoothLeAdvertiser;
            this.f6677c = advertiseSettings;
            this.d = advertiseData;
            this.f6678e = advertiseCallback;
            this.f6679f = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermission(this.a, "android.permission.BLUETOOTH_ADVERTISE", new a());
        }
    }

    private Bundle a(String str, int i2) {
        if (f.b().c()) {
            Bundle bundle = new Bundle();
            bundle.putInt("errCode", 10008);
            bundle.putString("message", "fail: servers count overflow");
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            return bundle;
        }
        long a2 = f.b().a(str);
        e b2 = f.b().b(a2);
        if (b2 == null || b2.a != g.CREATED) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("errCode", 10001);
            bundle2.putString("message", "fail:not available");
            bundle2.putString(IMantoBaseModule.ERROR_CODE, "0");
            return bundle2;
        }
        b2.a(this);
        b2.a(i2);
        Bundle bundle3 = new Bundle();
        bundle3.putLong("serverId", a2);
        bundle3.putString(IMantoBaseModule.ERROR_CODE, "1");
        return bundle3;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "BlePeripheral";
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x0222 A[Catch: all -> 0x023f, TryCatch #3 {all -> 0x023f, blocks: (B:72:0x01c9, B:74:0x01db, B:76:0x01ee, B:78:0x01f4, B:80:0x0205, B:82:0x020b, B:84:0x0213, B:90:0x0222, B:92:0x0235, B:88:0x021c), top: B:196:0x01c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0235 A[Catch: all -> 0x023f, TRY_LEAVE, TryCatch #3 {all -> 0x023f, blocks: (B:72:0x01c9, B:74:0x01db, B:76:0x01ee, B:78:0x01f4, B:80:0x0205, B:82:0x020b, B:84:0x0213, B:90:0x0222, B:92:0x0235, B:88:0x021c), top: B:196:0x01c9 }] */
    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleMethod(java.lang.String r27, com.jingdong.manto.MantoCore r28, android.os.Bundle r29, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r30) {
        /*
            Method dump skipped, instructions count: 1218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.hd.bluetooth.peripheral.JsApiPeripheral.handleMethod(java.lang.String, com.jingdong.manto.MantoCore, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        f.b().a(mantoCore);
        Activity activity = mantoCore.getActivity();
        int i2 = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        String string = bundle.getString("appid", "");
        try {
            new JSONObject(bundle.getString("json"));
        } catch (Throwable unused) {
        }
        if (TextUtils.equals(str, "createBLEPeripheralServer")) {
            if (!BTHelper.btEnabled()) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("errCode", 10001);
                bundle2.putString("message", "fail:not available");
                bundle2.putString(IMantoBaseModule.ERROR_CODE, "0");
                return bundle2;
            } else if (Build.VERSION.SDK_INT < 31) {
                return a(string, i2);
            } else {
                IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
                String[] strArr = {"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_ADVERTISE"};
                if (iPermission == null || iPermission.hasPermissions(strArr)) {
                    return a(string, i2);
                }
                MantoUtils.runOnUiThread(new a(this, activity, strArr));
            }
        }
        return super.handleMethodSync(str, mantoCore, bundle);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("createBLEPeripheralServer", 3));
        list.add(new JsApiMethod("addBLEPeripheralService", 1));
        list.add(new JsApiMethod("closeBLEPeripheralServer", 1));
        list.add(new JsApiMethod("removeBLEPeripheralService", 1));
        list.add(new JsApiMethod("startBLEPeripheralAdvertising", 1));
        list.add(new JsApiMethod("stopBLEPeripheralAdvertising", 1));
        list.add(new JsApiMethod("writeBLEPeripheralCharacteristicValue", 1));
    }
}
