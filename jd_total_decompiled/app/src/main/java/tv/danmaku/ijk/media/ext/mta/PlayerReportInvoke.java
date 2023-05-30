package tv.danmaku.ijk.media.ext.mta;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes11.dex */
public interface PlayerReportInvoke {
    void onMtaReportClick(Context context, String str, String str2);

    void onPerfReport(WeakReference<Context> weakReference, String str, String str2, HashMap<String, String> hashMap);
}
