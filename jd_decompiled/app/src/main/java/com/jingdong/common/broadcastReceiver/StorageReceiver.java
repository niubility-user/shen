package com.jingdong.common.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class StorageReceiver extends BroadcastReceiver {
    private static IntentFilter intentFilter;
    private static StorageReceiver storageReceiver;

    private static void initFilter() {
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
        intentFilter = intentFilter2;
        intentFilter2.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentFilter.addDataScheme("file");
    }

    public static void registerReceiver(Context context) {
        WeakReference weakReference = new WeakReference(context);
        if (storageReceiver == null) {
            storageReceiver = new StorageReceiver();
        }
        if (weakReference.get() != null) {
            initFilter();
            ((Context) weakReference.get()).registerReceiver(storageReceiver, intentFilter);
        }
    }

    public static void unregisterReceiver(Context context) {
        WeakReference weakReference = new WeakReference(context);
        if (weakReference.get() == null || storageReceiver == null) {
            return;
        }
        ((Context) weakReference.get()).unregisterReceiver(storageReceiver);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (OKLog.D) {
            OKLog.d("StorageReceiver", " StorageReceiver onReceive -->> action: " + intent.getAction());
        }
        FileService.needReSetupStorageState();
    }
}
