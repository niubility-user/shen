package tv.danmaku.ijk.media.ext.mta;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class DefaultPlayerReport implements PlayerReportInvoke {
    @Override // tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke
    public void onMtaReportClick(Context context, String str, String str2) {
        DebugLog.d("reportPlayerMta", "eventId = " + str + " ,pageName = " + str2);
    }

    @Override // tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke
    public void onPerfReport(WeakReference<Context> weakReference, String str, String str2, HashMap<String, String> hashMap) {
        if (hashMap.get("chId") == null || !defpackage.b.a(hashMap.get("chId"), "1")) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            sb.append("\"");
            sb.append(entry.getKey());
            sb.append("\":\"");
            sb.append(entry.getValue());
            sb.append("\",");
        }
        DebugLog.e("reportPlayerData", sb.substring(0, sb.length() - 1) + "}");
    }
}
