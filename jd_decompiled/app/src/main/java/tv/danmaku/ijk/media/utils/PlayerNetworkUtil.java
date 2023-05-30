package tv.danmaku.ijk.media.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.JDPlayerSdk;

/* loaded from: classes11.dex */
public class PlayerNetworkUtil {
    private static final ArrayList<NetChangeListener> netChangeListeners = new ArrayList<>();
    private final AtomicBoolean hasReceiver;

    /* loaded from: classes11.dex */
    public interface NetChangeListener {
        void onNetworkChange(boolean z, boolean z2);
    }

    /* loaded from: classes11.dex */
    public enum NetType {
        NO_NET(0),
        WIFI(1),
        SecondG(2),
        ThirdG(3),
        FourthG(4);
        
        private int value;

        NetType(int i2) {
            this.value = 0;
            this.value = i2;
        }

        public int value() {
            return this.value;
        }
    }

    public PlayerNetworkUtil() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.hasReceiver = atomicBoolean;
        if (JDPlayerSdk.getInstance().getApplicationContext() == null || atomicBoolean.get()) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        JDPlayerSdk.getInstance().getApplicationContext().registerReceiver(new BroadcastReceiver() { // from class: tv.danmaku.ijk.media.utils.PlayerNetworkUtil.1
            {
                PlayerNetworkUtil.this = this;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (PlayerNetworkUtil.netChangeListeners.isEmpty()) {
                    return;
                }
                Iterator it = PlayerNetworkUtil.netChangeListeners.iterator();
                while (it.hasNext()) {
                    ((NetChangeListener) it.next()).onNetworkChange(PlayerNetworkUtil.isNetworkConnected(context), PlayerNetworkUtil.isWifiConnected(context));
                }
            }
        }, intentFilter);
        atomicBoolean.set(true);
    }

    public static NetType getAPNType(Context context) {
        NetType netType = NetType.NO_NET;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return netType;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return NetType.WIFI;
        }
        return type == 0 ? NetType.SecondG : netType;
    }

    public static boolean isMobileNet() {
        return NetUtils.is3GOr2GNetwork();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo[] allNetworkInfo;
        if (context != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1)) == null) {
            return false;
        }
        return networkInfo.isConnected();
    }

    public static void registerNetworkCallback(NetChangeListener netChangeListener) {
        ArrayList<NetChangeListener> arrayList = netChangeListeners;
        if (arrayList.contains(netChangeListener)) {
            return;
        }
        arrayList.add(netChangeListener);
    }

    public static void unregisterNetworkCallback(NetChangeListener netChangeListener) {
        ArrayList<NetChangeListener> arrayList = netChangeListeners;
        if (arrayList.isEmpty()) {
            return;
        }
        arrayList.remove(netChangeListener);
    }
}
