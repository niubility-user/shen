package com.jingdong.common.widget.custom.livewidget.util;

import com.jingdong.common.network.ReportVideoSpeedUtil;

/* loaded from: classes12.dex */
public class ReportSpeedHelp extends ReportVideoSpeedUtil {
    private static volatile ReportSpeedHelp defaultInstance;

    public static void clear() {
        if (defaultInstance != null) {
            defaultInstance.stop();
        }
        defaultInstance = null;
    }

    public static ReportSpeedHelp getDefault() {
        if (defaultInstance == null) {
            synchronized (ReportSpeedHelp.class) {
                if (defaultInstance == null) {
                    defaultInstance = new ReportSpeedHelp();
                }
            }
        }
        return defaultInstance;
    }
}
