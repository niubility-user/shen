package com.jd.manto.hd.bluetooth.peripheral;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
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
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import java.util.UUID;
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
    */
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        String str2;
        Bundle bundle2;
        String str3;
        String optString;
        String optString2;
        boolean optBoolean;
        int optInt;
        String optString3;
        StringBuilder sb;
        int i2;
        IPermission iPermission;
        boolean removeService;
        IPermission iPermission2;
        IPermission iPermission3;
        super.handleMethod(str, mantoCore, bundle, mantoResultCallBack);
        f.b().a(mantoCore);
        Activity activity = mantoCore.getActivity();
        int i3 = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        bundle.getString("appid", "");
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (Throwable unused) {
            jSONObject = null;
        }
        if (TextUtils.equals("closeBLEPeripheralServer", str)) {
            if (!BTHelper.btEnabled()) {
                Bundle bundle3 = new Bundle();
                bundle3.putInt("errCode", 10001);
                bundle3.putString("message", "fail:not available");
                mantoResultCallBack.onFailed(bundle3);
            } else if (jSONObject == null || !jSONObject.has("serverId")) {
                Bundle bundle4 = new Bundle();
                bundle4.putInt("errCode", 10013);
                bundle4.putString("message", "fail:invalid data");
                mantoResultCallBack.onFailed(bundle4);
            } else if (f.b().a(jSONObject.optLong("serverId"))) {
                mantoResultCallBack.onSuccess(new Bundle());
            } else {
                Bundle bundle5 = new Bundle();
                bundle5.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                bundle5.putString("message", "fail: no server");
                mantoResultCallBack.onFailed(bundle5);
            }
        } else if (TextUtils.equals("addBLEPeripheralService", str)) {
            if (!BTHelper.btEnabled()) {
                Bundle bundle6 = new Bundle();
                bundle6.putInt("errCode", 10001);
                bundle6.putString("message", "fail:not available");
                mantoResultCallBack.onFailed(bundle6);
                return;
            }
            BluetoothGattService a2 = com.jd.manto.hd.bluetooth.peripheral.b.a(jSONObject);
            if (a2 == null) {
                Bundle bundle7 = new Bundle();
                bundle7.putInt("errCode", 10013);
                bundle7.putString("message", "fail:invalid data");
                mantoResultCallBack.onFailed(bundle7);
                return;
            }
            long optLong = jSONObject.optLong("serverId");
            e b2 = f.b().b(optLong);
            if (b2 == null) {
                MantoLog.i("JsApiPeripheral", "invoke: retrieve a null server by #" + optLong);
                Bundle bundle8 = new Bundle();
                bundle8.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                bundle8.putString("message", "fail: no server");
                mantoResultCallBack.onFailed(bundle8);
                return;
            }
            b2.a(this);
            b2.a(i3);
            BluetoothGattServer c2 = b2.c();
            if (c2 == null) {
                Bundle bundle9 = new Bundle();
                bundle9.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                bundle9.putString("message", "fail: no gatt server");
                mantoResultCallBack.onFailed(bundle9);
                return;
            }
            if ((Build.VERSION.SDK_INT < 31 || ((iPermission3 = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission3.hasPermission("android.permission.BLUETOOTH_CONNECT"))) ? c2.addService(a2) : false) {
                mantoResultCallBack.onSuccess(new Bundle());
                return;
            }
            Bundle bundle10 = new Bundle();
            bundle10.putInt("errCode", 10008);
            bundle10.putString("message", "fail:system error, add service fail");
            mantoResultCallBack.onFailed(bundle10);
        } else {
            if (!TextUtils.equals("removeBLEPeripheralService", str)) {
                int i4 = 1;
                if (TextUtils.equals("startBLEPeripheralAdvertising", str)) {
                    if (!BTHelper.btEnabled()) {
                        Bundle bundle11 = new Bundle();
                        bundle11.putInt("errCode", 10001);
                        bundle11.putString("message", "fail:not available");
                        mantoResultCallBack.onFailed(bundle11);
                        return;
                    }
                    h b3 = com.jd.manto.hd.bluetooth.peripheral.b.b(jSONObject);
                    if (b3 == null) {
                        Bundle bundle12 = new Bundle();
                        bundle12.putInt("errCode", 10013);
                        bundle12.putString("message", "fail:invalid data");
                        mantoResultCallBack.onFailed(bundle12);
                        return;
                    }
                    e b4 = f.b().b(b3.d);
                    if (b4 == null) {
                        Bundle bundle13 = new Bundle();
                        bundle13.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                        bundle13.putString("message", "fail: no server");
                        mantoResultCallBack.onFailed(bundle13);
                        return;
                    }
                    b4.a(this);
                    b4.a(i3);
                    BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                    BluetoothLeAdvertiser bluetoothLeAdvertiser = defaultAdapter != null ? defaultAdapter.getBluetoothLeAdvertiser() : null;
                    if (defaultAdapter == null || bluetoothLeAdvertiser == null) {
                        Bundle bundle14 = new Bundle();
                        bundle14.putInt("errCode", 10001);
                        bundle14.putString("message", "fail:not available");
                        mantoResultCallBack.onFailed(bundle14);
                        return;
                    }
                    AdvertiseData a3 = com.jd.manto.hd.bluetooth.peripheral.b.a(b3);
                    String str4 = b3.f6692c;
                    if (TextUtils.equals("medium", str4)) {
                        i2 = 2;
                    } else if (TextUtils.equals("low", str4)) {
                        i2 = 1;
                        i4 = 0;
                    } else {
                        i2 = 3;
                        i4 = 2;
                    }
                    AdvertiseSettings build = new AdvertiseSettings.Builder().setAdvertiseMode(i4).setTxPowerLevel(i2).setConnectable(b3.f6693e).build();
                    b bVar = new b(this, mantoResultCallBack);
                    b4.a(bVar);
                    if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_ADVERTISE"))) {
                        bluetoothLeAdvertiser.startAdvertising(build, a3, bVar);
                        return;
                    } else {
                        MantoUtils.runOnUiThread(new c(this, activity, bluetoothLeAdvertiser, build, a3, bVar, mantoResultCallBack));
                        return;
                    }
                } else if (TextUtils.equals("stopBLEPeripheralAdvertising", str)) {
                    if (!BTHelper.btEnabled()) {
                        Bundle bundle15 = new Bundle();
                        bundle15.putInt("errCode", 10001);
                        bundle15.putString("message", "fail:not available");
                        mantoResultCallBack.onFailed(bundle15);
                        return;
                    } else if (jSONObject == null || !jSONObject.has("serverId")) {
                        Bundle bundle16 = new Bundle();
                        bundle16.putInt("errCode", 10013);
                        bundle16.putString("message", "fail:invalid data");
                        mantoResultCallBack.onFailed(bundle16);
                        return;
                    } else {
                        e b5 = f.b().b(jSONObject.optLong("serverId"));
                        if (b5 == null) {
                            Bundle bundle17 = new Bundle();
                            bundle17.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                            bundle17.putString("message", "fail: no server");
                            mantoResultCallBack.onFailed(bundle17);
                            return;
                        }
                        BluetoothAdapter defaultAdapter2 = BluetoothAdapter.getDefaultAdapter();
                        if (defaultAdapter2 != null) {
                            b5.a(defaultAdapter2);
                            mantoResultCallBack.onSuccess(new Bundle());
                            return;
                        }
                        Bundle bundle18 = new Bundle();
                        bundle18.putInt("errCode", 10001);
                        bundle18.putString("message", "fail:not available");
                        mantoResultCallBack.onFailed(bundle18);
                        return;
                    }
                } else if (TextUtils.equals("writeBLEPeripheralCharacteristicValue", str)) {
                    if (!BTHelper.btEnabled()) {
                        Bundle bundle19 = new Bundle();
                        bundle19.putInt("errCode", 10001);
                        bundle19.putString("message", "fail:not available");
                        mantoResultCallBack.onFailed(bundle19);
                        return;
                    } else if (jSONObject == null || !jSONObject.has("serverId")) {
                        Bundle bundle20 = new Bundle();
                        bundle20.putInt("errCode", 10013);
                        bundle20.putString("message", "fail:invalid data");
                        mantoResultCallBack.onFailed(bundle20);
                        return;
                    } else {
                        e b6 = f.b().b(jSONObject.optLong("serverId"));
                        if (b6 == null) {
                            Bundle bundle21 = new Bundle();
                            bundle21.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                            bundle21.putString("message", "fail: no server");
                            mantoResultCallBack.onFailed(bundle21);
                            return;
                        } else if (b6.a != g.CONNECTED) {
                            Bundle bundle22 = new Bundle();
                            bundle22.putInt("errCode", 10000);
                            bundle22.putString("message", "fail: not connected");
                            mantoResultCallBack.onFailed(bundle22);
                            return;
                        } else {
                            try {
                                b6.a(this);
                                b6.a(i3);
                                optString = jSONObject.optString("serviceId", "");
                                optString2 = jSONObject.optString("characteristicId", "");
                                optBoolean = jSONObject.optBoolean("needNotify", false);
                                optInt = jSONObject.optInt("callbackId", -1);
                                optString3 = jSONObject.optString("value");
                                sb = new StringBuilder();
                                sb.append("write characteristic = ");
                                sb.append(optString3);
                                sb.append(String.format(" serviceId = %s, characteristicId = %s, needNotify = %s, jCallbackId = %s", optString, optString2, Boolean.valueOf(optBoolean), Integer.valueOf(optInt)));
                                str2 = "JsApiPeripheral";
                            } catch (Throwable th) {
                                th = th;
                                str2 = "JsApiPeripheral";
                            }
                            try {
                                MantoLog.i(str2, sb.toString());
                                b6.a(UUID.fromString(optString), UUID.fromString(optString2), optBoolean, optInt, optString3);
                                mantoResultCallBack.onSuccess(new Bundle());
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                                MantoLog.e(str2, "write value error ", th);
                                bundle2 = new Bundle();
                                bundle2.putInt("errCode", 10013);
                                str3 = "fail: write value error";
                                bundle2.putString("message", str3);
                                mantoResultCallBack.onFailed(bundle2);
                            }
                        }
                    }
                } else {
                    return;
                }
            } else if (!BTHelper.btEnabled()) {
                Bundle bundle23 = new Bundle();
                bundle23.putInt("errCode", 10001);
                bundle23.putString("message", "fail:not available");
                mantoResultCallBack.onFailed(bundle23);
                return;
            } else if (jSONObject == null || !jSONObject.has("serverId") || !jSONObject.has("serviceId")) {
                Bundle bundle24 = new Bundle();
                bundle24.putInt("errCode", 10013);
                bundle24.putString("message", "fail:invalid data");
                mantoResultCallBack.onFailed(bundle24);
                return;
            } else {
                e b7 = f.b().b(jSONObject.optLong("serverId"));
                if (b7 == null) {
                    Bundle bundle25 = new Bundle();
                    bundle25.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                    bundle25.putString("message", "fail: no server");
                    mantoResultCallBack.onFailed(bundle25);
                    return;
                }
                b7.a(this);
                b7.a(i3);
                try {
                    BluetoothGattService service = b7.c().getService(UUID.fromString(jSONObject.optString("serviceId")));
                    if (service == null) {
                        Bundle bundle26 = new Bundle();
                        bundle26.putInt("errCode", 10004);
                        bundle26.putString("message", "fail: no service");
                        mantoResultCallBack.onFailed(bundle26);
                        return;
                    }
                    BluetoothGattServer c3 = b7.c();
                    if (c3 == null) {
                        Bundle bundle27 = new Bundle();
                        bundle27.putInt("errCode", R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector);
                        bundle27.putString("message", "fail: no gatt server");
                        mantoResultCallBack.onFailed(bundle27);
                        return;
                    }
                    if (Build.VERSION.SDK_INT >= 31 && ((iPermission2 = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) == null || !iPermission2.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                        removeService = false;
                        if (!removeService) {
                            mantoResultCallBack.onSuccess(new Bundle());
                            return;
                        }
                        Bundle bundle28 = new Bundle();
                        bundle28.putInt("errCode", 10008);
                        bundle28.putString("message", "fail:remove service fail");
                        mantoResultCallBack.onFailed(bundle28);
                        return;
                    }
                    removeService = c3.removeService(service);
                    if (!removeService) {
                    }
                } catch (Throwable unused2) {
                    bundle2 = new Bundle();
                    bundle2.putInt("errCode", 10008);
                    str3 = "fail:system error, remove service fail";
                }
            }
            bundle2.putString("message", str3);
            mantoResultCallBack.onFailed(bundle2);
        }
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
