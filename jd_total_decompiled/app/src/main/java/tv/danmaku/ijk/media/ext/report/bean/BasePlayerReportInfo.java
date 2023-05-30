package tv.danmaku.ijk.media.ext.report.bean;

import java.io.Serializable;
import java.util.HashMap;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;

/* loaded from: classes11.dex */
public abstract class BasePlayerReportInfo implements Serializable {
    protected HashMap<String, String> paramsMap = new HashMap<String, String>() { // from class: tv.danmaku.ijk.media.ext.report.bean.BasePlayerReportInfo.1
        {
            put("chId", BasePlayerReportInfo.this.getChId());
        }
    };

    public void addCommonParams(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        this.paramsMap.putAll(hashMap);
    }

    public void clean() {
        this.paramsMap.clear();
    }

    public HashMap<String, String> generateReportMap() {
        this.paramsMap.put("occurTime", PlayerStringUtils.generateReportTime());
        return this.paramsMap;
    }

    public abstract String getChId();
}
