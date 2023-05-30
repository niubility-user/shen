package com.jingdong.sdk.baseinfo.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.util.Logger;

/* loaded from: classes7.dex */
public final class a extends BroadcastReceiver {
    private int a = 0;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                Logger.d("BaseInfo.Network", "BaseInfoNetReceiver receive net action broadcast\uff1a" + intent.getExtras());
                Logger.d("BaseInfo.Network", "NetworkInfo:" + networkInfo.toString());
                String typeName = networkInfo.getTypeName();
                String name = networkInfo.getState().name();
                if ("CONNECTED".equals(name)) {
                    Logger.d("BaseInfo.Network", "networkType: " + b.a().f14681e + " => " + typeName);
                    b.a().f14681e = typeName;
                } else if ("DISCONNECTED".equals(name) && TextUtils.equals(typeName, b.a().f14681e)) {
                    Logger.d("BaseInfo.Network", "networkType: " + b.a().f14681e + " => null");
                    b.a().f14681e = null;
                } else {
                    Logger.d("BaseInfo.Network", "ignore state: ".concat(String.valueOf(name)));
                }
                if (this.a == 0) {
                    Logger.d("BaseInfo.Network", "is on launch broadcast");
                } else {
                    b a = b.a();
                    a.b = null;
                    a.d = null;
                }
                this.a++;
            }
        } catch (Exception e2) {
            Logger.e("BaseInfo.Network", "", e2);
        }
    }
}
