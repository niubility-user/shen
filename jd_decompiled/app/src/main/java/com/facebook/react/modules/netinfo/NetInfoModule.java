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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateAndSendConnectionType() {
        /*
            r6 = this;
            java.lang.String r0 = "unknown"
            r1 = 1
            android.net.ConnectivityManager r2 = r6.mConnectivityManager     // Catch: java.lang.SecurityException -> L46
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()     // Catch: java.lang.SecurityException -> L46
            if (r2 == 0) goto L40
            boolean r3 = r2.isConnected()     // Catch: java.lang.SecurityException -> L46
            if (r3 != 0) goto L13
            goto L40
        L13:
            int r3 = r2.getType()     // Catch: java.lang.SecurityException -> L46
            if (r3 == 0) goto L37
            if (r3 == r1) goto L33
            r4 = 4
            if (r3 == r4) goto L37
            r2 = 9
            if (r3 == r2) goto L30
            r2 = 6
            if (r3 == r2) goto L2c
            r2 = 7
            if (r3 == r2) goto L29
            goto L48
        L29:
            java.lang.String r1 = "bluetooth"
            goto L42
        L2c:
            java.lang.String r1 = "wimax"
            goto L42
        L30:
            java.lang.String r1 = "ethernet"
            goto L42
        L33:
            java.lang.String r1 = "wifi"
            goto L42
        L37:
            java.lang.String r3 = "cellular"
            java.lang.String r0 = r6.getEffectiveConnectionType(r2)     // Catch: java.lang.SecurityException -> L46
            r1 = r0
            r0 = r3
            goto L49
        L40:
            java.lang.String r1 = "none"
        L42:
            r5 = r1
            r1 = r0
            r0 = r5
            goto L49
        L46:
            r6.mNoNetworkPermission = r1
        L48:
            r1 = r0
        L49:
            java.lang.String r2 = r6.getCurrentConnectionType()
            java.lang.String r3 = r6.mConnectionType
            boolean r3 = r0.equalsIgnoreCase(r3)
            if (r3 == 0) goto L65
            java.lang.String r3 = r6.mEffectiveConnectionType
            boolean r3 = r1.equalsIgnoreCase(r3)
            if (r3 == 0) goto L65
            java.lang.String r3 = r6.mConnectivityDeprecated
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 != 0) goto L6e
        L65:
            r6.mConnectionType = r0
            r6.mEffectiveConnectionType = r1
            r6.mConnectivityDeprecated = r2
            r6.sendConnectivityChangedEvent()
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.netinfo.NetInfoModule.updateAndSendConnectionType():void");
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
