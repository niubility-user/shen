package com.tencent.mapsdk.shell.events;

import androidx.annotation.Keep;
import com.tencent.mapsdk.internal.li;
import java.util.Map;

@Keep
/* loaded from: classes9.dex */
public class ReportEvent {
    public String code;
    public boolean isSucceed;
    public Map<String, String> params;
    public String appKey = li.f16855k;
    public boolean realtime = false;

    public ReportEvent(String str, Map<String, String> map) {
        this.code = str;
        this.params = map;
    }
}
