package com.jingdong.sdk.jdhttpdns.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class NetworkStateObserver {
    private static ArrayList<NetChangeEvent> eventListenerList = new ArrayList<>();

    /* loaded from: classes7.dex */
    public interface NetChangeEvent {
        void onNetworkChange();
    }

    /* loaded from: classes7.dex */
    static class NetStateBroadCast extends BroadcastReceiver {
        NetStateBroadCast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (NetworkStateObserver.eventListenerList != null) {
                Iterator it = NetworkStateObserver.eventListenerList.iterator();
                while (it.hasNext()) {
                    ((NetChangeEvent) it.next()).onNetworkChange();
                }
            }
        }
    }

    public static void addEventListener(NetChangeEvent netChangeEvent) {
        ArrayList<NetChangeEvent> arrayList = eventListenerList;
        if (arrayList != null) {
            arrayList.add(netChangeEvent);
        }
    }

    public static void registListenBroadCast(Context context) {
        context.registerReceiver(new NetStateBroadCast(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void removeEventListener(NetChangeEvent netChangeEvent) {
        ArrayList<NetChangeEvent> arrayList = eventListenerList;
        if (arrayList == null || !arrayList.contains(netChangeEvent)) {
            return;
        }
        eventListenerList.remove(netChangeEvent);
    }
}
