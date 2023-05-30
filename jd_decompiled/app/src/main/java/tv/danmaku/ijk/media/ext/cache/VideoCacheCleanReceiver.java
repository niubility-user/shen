package tv.danmaku.ijk.media.ext.cache;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes11.dex */
public class VideoCacheCleanReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        JDPlayerVideoCache.getInstance().clearCache(context);
    }
}
