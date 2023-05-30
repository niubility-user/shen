package com.jingdong.common.widget.custom.livewidget.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes12.dex */
public class NetWorkManager {
    private static volatile NetWorkManager manager;
    private BroadcastReceiver myNetReceiver;
    private HashSet<NetCall> mNetCalls = new HashSet<>();
    private boolean isHasRegister = false;
    private int mLastNetType = -1;

    /* loaded from: classes12.dex */
    public interface NetCall {
        void netCall(Context context, int i2);
    }

    private NetWorkManager() {
        initReceiver();
    }

    public static NetWorkManager getInstance() {
        if (manager == null) {
            manager = new NetWorkManager();
        }
        return manager;
    }

    private void initReceiver() {
        this.myNetReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.widget.custom.livewidget.util.NetWorkManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (NetWorkManager.this.mNetCalls.isEmpty()) {
                    return;
                }
                OKLog.d("NetWorkManager", Integer.valueOf(NetWorkManager.this.mNetCalls.size()));
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    NetworkInfo networkInfo = null;
                    try {
                        networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                    } catch (Exception unused) {
                    }
                    if (networkInfo == null || !networkInfo.isAvailable()) {
                        NetWorkManager.this.netCall(context, -1);
                        return;
                    }
                    if (OKLog.D) {
                        OKLog.e("MMM", "=========================> mNetCall.netCall type : " + networkInfo.getType());
                    }
                    if (networkInfo.getType() == 1) {
                        NetWorkManager.this.netCall(context, 1);
                    } else if (networkInfo.getType() == 9) {
                        NetWorkManager.this.netCall(context, 9);
                    } else if (networkInfo.getType() == 0) {
                        NetWorkManager.this.netCall(context, 0);
                    }
                    NetWorkManager.this.mLastNetType = networkInfo.getType();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void netCall(Context context, int i2) {
        Iterator<NetCall> it = this.mNetCalls.iterator();
        while (it.hasNext()) {
            NetCall next = it.next();
            if (next != null) {
                next.netCall(context, i2);
            }
        }
    }

    private void registerNetworkStatus() {
        this.isHasRegister = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            OKLog.d("NetWorkManager", "registerNetworkStatus");
            JdSdk.getInstance().getApplication().registerReceiver(this.myNetReceiver, intentFilter);
        } catch (AssertionError unused) {
        }
    }

    private void unregisterNetworkStatus() {
        this.isHasRegister = false;
        try {
            OKLog.d("NetWorkManager", "unregisterNetworkStatus");
            JdSdk.getInstance().getApplication().unregisterReceiver(this.myNetReceiver);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    public void build() {
        if (this.isHasRegister) {
            return;
        }
        registerNetworkStatus();
    }

    public int getLastNetType() {
        return this.mLastNetType;
    }

    public void onDestroy() {
        this.mNetCalls.clear();
        unregisterNetworkStatus();
        manager = null;
    }

    public void removeNetCall(NetCall netCall) {
        OKLog.d("NetWorkManager", "removeNetCall" + netCall.hashCode() + netCall.getClass());
        this.mNetCalls.remove(netCall);
    }

    public NetWorkManager setNetCall(NetCall netCall) {
        if (!this.mNetCalls.contains(netCall)) {
            OKLog.d("NetWorkManager", "setNetCall" + netCall.hashCode() + netCall.getClass());
            this.mNetCalls.add(netCall);
        }
        return this;
    }
}
