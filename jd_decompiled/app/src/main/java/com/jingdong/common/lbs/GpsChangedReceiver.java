package com.jingdong.common.lbs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes5.dex */
public class GpsChangedReceiver extends BroadcastReceiver {
    private String GPS_ACTION = "android.location.PROVIDERS_CHANGED";

    public boolean isOpenGps() {
        LocationManager locationManager = (LocationManager) JdSdk.getInstance().getApplicationContext().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Log.D) {
            Log.d("GpsChangedReceiver", "Gps is open " + context.getClass().getSimpleName() + "   " + isOpenGps());
        }
        if (isOpenGps()) {
            JDLocationManager.getInstance().getAddress(new JDLocationOption(), new JDLocationListener() { // from class: com.jingdong.common.lbs.GpsChangedReceiver.1
                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onFail(JDLocationError jDLocationError) {
                }

                @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
                public void onSuccess(JDLocation jDLocation) {
                }
            });
        }
    }

    public void regist(Context context) {
        if (context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.GPS_ACTION);
        context.registerReceiver(this, intentFilter);
    }

    public void unRegist(Context context) {
        if (context == null) {
            return;
        }
        context.unregisterReceiver(this);
    }
}
