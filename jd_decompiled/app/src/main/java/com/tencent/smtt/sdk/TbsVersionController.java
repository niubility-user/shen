package com.tencent.smtt.sdk;

import android.content.Context;
import com.jd.lib.un.utils.UnTimeUtils;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;

/* loaded from: classes9.dex */
public class TbsVersionController {
    private static IntervalChoice a = IntervalChoice.SIX_HOUR;

    /* loaded from: classes9.dex */
    public interface CallBack {
        void canLocalVersionUsed(int i2, boolean z);

        void latestVersion(int i2);
    }

    /* loaded from: classes9.dex */
    public enum IntervalChoice {
        TEN_MINUTE(ThemeTitleDataController.DELAY_TIME),
        HALF_HOUR(1800000),
        ONE_HOUR(UnTimeUtils.HOUR),
        SIX_HOUR(21600000),
        TWELVE_HOUR(43200000);
        
        public final int value;

        IntervalChoice(int i2) {
            this.value = i2;
        }
    }

    public static void checkVersion(Context context, CallBack callBack) {
        throw new com.tencent.smtt.utils.a.a();
    }

    public static void setCheckInterval(IntervalChoice intervalChoice) {
        throw new UnsupportedOperationException("Current TBS SDK doesn't support");
    }
}
