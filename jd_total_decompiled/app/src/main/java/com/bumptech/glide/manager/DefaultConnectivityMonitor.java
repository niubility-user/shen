package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.util.Preconditions;

/* loaded from: classes.dex */
final class DefaultConnectivityMonitor implements ConnectivityMonitor {
    private static final String TAG = "ConnectivityMonitor";
    private final BroadcastReceiver connectivityReceiver = new BroadcastReceiver() { // from class: com.bumptech.glide.manager.DefaultConnectivityMonitor.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(@NonNull Context context, Intent intent) {
            DefaultConnectivityMonitor defaultConnectivityMonitor = DefaultConnectivityMonitor.this;
            boolean z = defaultConnectivityMonitor.isConnected;
            defaultConnectivityMonitor.isConnected = defaultConnectivityMonitor.isConnected(context);
            if (z != DefaultConnectivityMonitor.this.isConnected) {
                if (Log.isLoggable(DefaultConnectivityMonitor.TAG, 3)) {
                    String str = "connectivity changed, isConnected: " + DefaultConnectivityMonitor.this.isConnected;
                }
                DefaultConnectivityMonitor defaultConnectivityMonitor2 = DefaultConnectivityMonitor.this;
                defaultConnectivityMonitor2.listener.onConnectivityChanged(defaultConnectivityMonitor2.isConnected);
            }
        }
    };
    private final Context context;
    boolean isConnected;
    private boolean isRegistered;
    final ConnectivityMonitor.ConnectivityListener listener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultConnectivityMonitor(@NonNull Context context, @NonNull ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.context = context.getApplicationContext();
        this.listener = connectivityListener;
    }

    private void register() {
        if (this.isRegistered) {
            return;
        }
        this.isConnected = isConnected(this.context);
        try {
            this.context.registerReceiver(this.connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.isRegistered = true;
        } catch (SecurityException unused) {
            Log.isLoggable(TAG, 5);
        }
    }

    private void unregister() {
        if (this.isRegistered) {
            this.context.unregisterReceiver(this.connectivityReceiver);
            this.isRegistered = false;
        }
    }

    @SuppressLint({"MissingPermission"})
    boolean isConnected(@NonNull Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) Preconditions.checkNotNull((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (RuntimeException unused) {
            Log.isLoggable(TAG, 5);
            return true;
        }
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        register();
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        unregister();
    }
}
