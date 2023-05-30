package com.jingdong.common.network;

import android.content.Context;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.jingdong.common.httpdns.GlobalExecutorService;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class ConnectivityChangeObserver {
    public static final String TAG = "ConnectivityChangeObserver";
    public static NetworkInfo lastActiveNetworkInfo = null;
    public static boolean lastConnected = true;
    public Context context;
    private ArrayList<Event> eventListenerList = new ArrayList<>();

    /* loaded from: classes5.dex */
    public interface Event {
        void onNetworkChange();
    }

    public ConnectivityChangeObserver(Context context) {
        this.context = context;
    }

    public void addEventListener(Event event) {
        ArrayList<Event> arrayList = this.eventListenerList;
        if (arrayList != null) {
            arrayList.add(event);
        }
    }

    public void checkConnect(final NetworkInfo networkInfo) {
        GlobalExecutorService.lightExecutorService().execute(new Runnable() { // from class: com.jingdong.common.network.ConnectivityChangeObserver.1
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                NetworkInfo networkInfo2 = networkInfo;
                if (networkInfo2 == null) {
                    ConnectivityChangeObserver.lastActiveNetworkInfo = null;
                    ConnectivityChangeObserver.this.onNetworkChange();
                    return;
                }
                if (networkInfo2.getDetailedState() != NetworkInfo.DetailedState.CONNECTED) {
                    if (ConnectivityChangeObserver.lastConnected) {
                        ConnectivityChangeObserver.lastActiveNetworkInfo = null;
                    }
                    ConnectivityChangeObserver.this.onNetworkChange();
                    z = false;
                } else {
                    if (ConnectivityChangeObserver.this.isNetworkChange(networkInfo)) {
                        ConnectivityChangeObserver.this.onNetworkChange();
                    }
                    z = true;
                }
                ConnectivityChangeObserver.lastConnected = z;
            }
        });
    }

    public boolean isNetworkChange(NetworkInfo networkInfo) {
        try {
            NetworkInfo networkInfo2 = lastActiveNetworkInfo;
            if (networkInfo2 == null || networkInfo2.getExtraInfo() == null || networkInfo.getExtraInfo() == null || !TextUtils.equals(lastActiveNetworkInfo.getExtraInfo(), networkInfo.getExtraInfo()) || lastActiveNetworkInfo.getSubtype() != networkInfo.getSubtype() || lastActiveNetworkInfo.getType() != networkInfo.getType()) {
                NetworkInfo networkInfo3 = lastActiveNetworkInfo;
                if (networkInfo3 != null && networkInfo3.getExtraInfo() == null && networkInfo.getExtraInfo() == null && lastActiveNetworkInfo.getSubtype() == networkInfo.getSubtype() && lastActiveNetworkInfo.getType() == networkInfo.getType()) {
                    OKLog.d("ConnectivityChangeObserver", "Same Network, do not NetworkChanged");
                    return false;
                }
                lastActiveNetworkInfo = networkInfo;
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    public void onNetworkChange() {
        ArrayList<Event> arrayList = this.eventListenerList;
        if (arrayList != null) {
            Iterator<Event> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onNetworkChange();
            }
        }
    }

    public void removeEventListener(Event event) {
        ArrayList<Event> arrayList = this.eventListenerList;
        if (arrayList == null || !arrayList.contains(event)) {
            return;
        }
        this.eventListenerList.remove(event);
    }
}
