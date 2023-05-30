package com.jingdong.manto.sdk.impl.shortcut;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/* loaded from: classes16.dex */
public class ShortcutCallbackReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "\u5df2\u6dfb\u52a0", 1).show();
    }
}
