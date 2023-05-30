package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes15.dex */
public class WareBusinessBigPlus {
    private static final String CURRENT_DAY = "pd.bigplus.current.day";
    private static final String CURRENT_DAY_MANUAL_CLOSE = "pd.bigplus.manual.close";
    private static final String CURRENT_DAY_SHOW_TIME = "pd.bigplus.show.time";
    public String bgUrl;
    public String btnTxt;
    public int btnType;
    public String cha;
    public String commoncolor;
    public String iconUrl;
    public int jumpTime;
    public ArrayList<SpecData> specData;
    public String specolor;
    public String text;
    public long time;
    public String timecolor;

    /* loaded from: classes15.dex */
    public static class SpecData {
        public boolean isBold;
        public boolean isSpecColor;
        public int startIndex;
        public int textLength;
    }

    private void checkNeedResetShowTimes() {
        if (TextUtils.equals(SharedPreferencesUtil.getString(CURRENT_DAY, ""), getCurrentDay())) {
            return;
        }
        SharedPreferencesUtil.putString(CURRENT_DAY, getCurrentDay());
        SharedPreferencesUtil.putBoolean(CURRENT_DAY_MANUAL_CLOSE, false);
        SharedPreferencesUtil.putInt(CURRENT_DAY_SHOW_TIME, 0);
    }

    private String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1) + CartConstant.KEY_YB_INFO_LINK + calendar.get(6);
    }

    public void close() {
        checkNeedResetShowTimes();
        SharedPreferencesUtil.putBoolean(CURRENT_DAY_MANUAL_CLOSE, true);
    }

    public int increaseShowTime() {
        checkNeedResetShowTimes();
        int i2 = SharedPreferencesUtil.getInt(CURRENT_DAY_SHOW_TIME, 0) + 1;
        SharedPreferencesUtil.putInt(CURRENT_DAY_SHOW_TIME, i2);
        return i2;
    }

    public boolean isHasShowMaxTime(int i2) {
        checkNeedResetShowTimes();
        return SharedPreferencesUtil.getInt(CURRENT_DAY_SHOW_TIME, 0) >= i2;
    }

    public boolean isManualClosedToday() {
        checkNeedResetShowTimes();
        return SharedPreferencesUtil.getBoolean(CURRENT_DAY_MANUAL_CLOSE, false);
    }

    public boolean isValid() {
        boolean isManualClosedToday = isManualClosedToday();
        String str = "isValid hasClosed = " + isManualClosedToday;
        if (isManualClosedToday) {
            return false;
        }
        boolean isHasShowMaxTime = isHasShowMaxTime(this.jumpTime);
        String str2 = "isValid hasShowMaxTime = " + isHasShowMaxTime;
        if (isHasShowMaxTime || TextUtils.isEmpty(this.text)) {
            return false;
        }
        int i2 = this.btnType;
        return !(i2 == 3 || i2 == 4) || this.time > 0;
    }
}
