package com.facebook.react.modules.location;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import javax.annotation.Nullable;

@ReactModule(name = LocationModule.NAME)
@SuppressLint({"MissingPermission"})
/* loaded from: classes12.dex */
public class LocationModule extends ReactContextBaseJavaModule {
    public static final String NAME = "LocationObserver";
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener;
    @Nullable
    private String mWatchedProvider;

    /* loaded from: classes12.dex */
    private static class LocationOptions {
        private final float distanceFilter;
        private final boolean highAccuracy;
        private final double maximumAge;
        private final long timeout;

        private LocationOptions(long j2, double d, boolean z, float f2) {
            this.timeout = j2;
            this.maximumAge = d;
            this.highAccuracy = z;
            this.distanceFilter = f2;
        }

        private static LocationOptions fromReactMap(ReadableMap readableMap) {
            return new LocationOptions(readableMap.hasKey("timeout") ? (long) readableMap.getDouble("timeout") : Long.MAX_VALUE, readableMap.hasKey("maximumAge") ? readableMap.getDouble("maximumAge") : Double.POSITIVE_INFINITY, readableMap.hasKey("enableHighAccuracy") && readableMap.getBoolean("enableHighAccuracy"), readableMap.hasKey("distanceFilter") ? (float) readableMap.getDouble("distanceFilter") : 100.0f);
        }
    }

    public LocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mLocationListener = new LocationListener() { // from class: com.facebook.react.modules.location.LocationModule.1
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) LocationModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationDidChange", LocationModule.locationToMap(location));
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String str) {
            }

            @Override // android.location.LocationListener
            public void onProviderEnabled(String str) {
            }

            @Override // android.location.LocationListener
            public void onStatusChanged(String str, int i2, Bundle bundle) {
                if (i2 == 0) {
                    LocationModule.this.emitError(PositionError.POSITION_UNAVAILABLE, "Provider " + str + " is out of service.");
                } else if (i2 == 1) {
                    LocationModule.this.emitError(PositionError.TIMEOUT, "Provider " + str + " is temporarily unavailable.");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitError(int i2, String str) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationError", PositionError.buildError(i2, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WritableMap locationToMap(Location location) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble(PdLVBody.LATITUDE, location.getLatitude());
        createMap2.putDouble(PdLVBody.LONGITUDE, location.getLongitude());
        createMap2.putDouble("altitude", location.getAltitude());
        createMap2.putDouble("accuracy", location.getAccuracy());
        createMap2.putDouble("heading", location.getBearing());
        createMap2.putDouble("speed", location.getSpeed());
        createMap.putMap("coords", createMap2);
        createMap.putDouble(VerifyTracker.KEY_TIMESTAMP, location.getTime());
        if (Build.VERSION.SDK_INT >= 18) {
            createMap.putBoolean("mocked", location.isFromMockProvider());
        }
        return createMap;
    }

    private static void throwLocationPermissionMissing(SecurityException securityException) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", securityException);
    }

    @ReactMethod
    public void getCurrentPosition(ReadableMap readableMap, Callback callback, Callback callback2) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void startObserving(ReadableMap readableMap) {
    }

    @ReactMethod
    public void stopObserving() {
    }
}
