package com.facebook.react.modules.netinfo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.core.net.ConnectivityManagerCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

@ReactModule(name = NetInfoModule.NAME)
@SuppressLint({"MissingPermission"})
/* loaded from: classes12.dex */
public class NetInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String CONNECTION_TYPE_BLUETOOTH = "bluetooth";
    private static final String CONNECTION_TYPE_CELLULAR = "cellular";
    private static final String CONNECTION_TYPE_ETHERNET = "ethernet";
    private static final String CONNECTION_TYPE_NONE = "none";
    private static final String CONNECTION_TYPE_NONE_DEPRECATED = "NONE";
    private static final String CONNECTION_TYPE_UNKNOWN = "unknown";
    private static final String CONNECTION_TYPE_UNKNOWN_DEPRECATED = "UNKNOWN";
    private static final String CONNECTION_TYPE_WIFI = "wifi";
    private static final String CONNECTION_TYPE_WIMAX = "wimax";
    private static final String EFFECTIVE_CONNECTION_TYPE_2G = "2g";
    private static final String EFFECTIVE_CONNECTION_TYPE_3G = "3g";
    private static final String EFFECTIVE_CONNECTION_TYPE_4G = "4g";
    private static final String EFFECTIVE_CONNECTION_TYPE_UNKNOWN = "unknown";
    private static final String ERROR_MISSING_PERMISSION = "E_MISSING_PERMISSION";
    private static final String MISSING_PERMISSION_MESSAGE = "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />";
    public static final String NAME = "NetInfo";
    private String mConnectionType;
    private final ConnectivityBroadcastReceiver mConnectivityBroadcastReceiver;
    private String mConnectivityDeprecated;
    private final ConnectivityManager mConnectivityManager;
    private String mEffectiveConnectionType;
    private boolean mNoNetworkPermission;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class ConnectivityBroadcastReceiver extends BroadcastReceiver {
        private boolean isRegistered;

        private ConnectivityBroadcastReceiver() {
            this.isRegistered = false;
        }

        public boolean isRegistered() {
            return this.isRegistered;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetInfoModule.this.updateAndSendConnectionType();
            }
        }

        public void setRegistered(boolean z) {
            this.isRegistered = z;
        }
    }

    public NetInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mNoNetworkPermission = false;
        this.mConnectivityDeprecated = "UNKNOWN";
        this.mConnectionType = "unknown";
        this.mEffectiveConnectionType = "unknown";
        this.mConnectivityManager = (ConnectivityManager) reactApplicationContext.getSystemService("connectivity");
        this.mConnectivityBroadcastReceiver = new ConnectivityBroadcastReceiver();
    }

    private WritableMap createConnectivityEventMap() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("network_info", this.mConnectivityDeprecated);
        writableNativeMap.putString("connectionType", this.mConnectionType);
        writableNativeMap.putString("effectiveConnectionType", this.mEffectiveConnectionType);
        return writableNativeMap;
    }

    private String getCurrentConnectionType() {
        String str = "UNKNOWN";
        try {
            NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (ConnectivityManager.isNetworkTypeValid(activeNetworkInfo.getType())) {
                    str = activeNetworkInfo.getTypeName().toUpperCase();
                    return str;
                }
                return "UNKNOWN";
            }
            return CONNECTION_TYPE_NONE_DEPRECATED;
        } catch (SecurityException unused) {
            this.mNoNetworkPermission = true;
            return str;
        }
    }

    private String getEffectiveConnectionType(NetworkInfo networkInfo) {
        switch (networkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2g";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
                return "3g";
            case 13:
            case 15:
                return "4g";
            default:
                return "unknown";
        }
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            getReactApplicationContext().registerReceiver(this.mConnectivityBroadcastReceiver, intentFilter);
            this.mConnectivityBroadcastReceiver.setRegistered(true);
        } catch (Exception unused) {
            this.mConnectivityBroadcastReceiver.setRegistered(false);
        }
        updateAndSendConnectionType();
    }

    private void sendConnectivityChangedEvent() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("networkStatusDidChange", createConnectivityEventMap());
    }

    private void unregisterReceiver() {
        if (this.mConnectivityBroadcastReceiver.isRegistered()) {
            getReactApplicationContext().unregisterReceiver(this.mConnectivityBroadcastReceiver);
            this.mConnectivityBroadcastReceiver.setRegistered(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0055  */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void updateAndSendConnectionType() {
        String str;
        String currentConnectionType;
        NetworkInfo activeNetworkInfo;
        String str2;
        String str3 = "unknown";
        boolean z = 1;
        try {
            activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        } catch (SecurityException unused) {
            this.mNoNetworkPermission = z;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            int type = activeNetworkInfo.getType();
            if (type != 0) {
                if (type == 1) {
                    str2 = "wifi";
                } else if (type != 4) {
                    if (type == 9) {
                        str2 = CONNECTION_TYPE_ETHERNET;
                    } else if (type == 6) {
                        str2 = CONNECTION_TYPE_WIMAX;
                    } else if (type != 7) {
                        str = str3;
                        currentConnectionType = getCurrentConnectionType();
                        if (!str3.equalsIgnoreCase(this.mConnectionType) && str.equalsIgnoreCase(this.mEffectiveConnectionType) && currentConnectionType.equalsIgnoreCase(this.mConnectivityDeprecated)) {
                            return;
                        }
                        this.mConnectionType = str3;
                        this.mEffectiveConnectionType = str;
                        this.mConnectivityDeprecated = currentConnectionType;
                        sendConnectivityChangedEvent();
                    } else {
                        str2 = CONNECTION_TYPE_BLUETOOTH;
                    }
                }
                String str4 = str2;
                str = "unknown";
                str3 = str4;
                currentConnectionType = getCurrentConnectionType();
                if (!str3.equalsIgnoreCase(this.mConnectionType)) {
                }
                this.mConnectionType = str3;
                this.mEffectiveConnectionType = str;
                this.mConnectivityDeprecated = currentConnectionType;
                sendConnectivityChangedEvent();
            }
            z = getEffectiveConnectionType(activeNetworkInfo);
            str3 = CONNECTION_TYPE_CELLULAR;
            str = z;
            currentConnectionType = getCurrentConnectionType();
            if (!str3.equalsIgnoreCase(this.mConnectionType)) {
            }
            this.mConnectionType = str3;
            this.mEffectiveConnectionType = str;
            this.mConnectivityDeprecated = currentConnectionType;
            sendConnectivityChangedEvent();
        }
        str2 = "none";
        String str42 = str2;
        str = "unknown";
        str3 = str42;
        currentConnectionType = getCurrentConnectionType();
        if (!str3.equalsIgnoreCase(this.mConnectionType)) {
        }
        this.mConnectionType = str3;
        this.mEffectiveConnectionType = str;
        this.mConnectivityDeprecated = currentConnectionType;
        sendConnectivityChangedEvent();
    }

    @ReactMethod
    public void getCurrentConnectivity(Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE);
        } else {
            promise.resolve(createConnectivityEventMap());
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @ReactMethod
    public void isConnectionMetered(Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE);
        } else {
            promise.resolve(Boolean.valueOf(ConnectivityManagerCompat.isActiveNetworkMetered(this.mConnectivityManager)));
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        unregisterReceiver();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        registerReceiver();
    }
}
