package com.jingdong.common.utils;

import com.jingdong.common.utils.personal.JDPersonalSharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\n\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0016\u0010\u000f\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0016\u0010\u0010\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/common/utils/PersonalEnterUtils;", "", "", "todayFirst", "", "putCurrentDay", "(I)V", "getLastDay", "()I", "", "isFirstEnterPersonal", "()Ljava/lang/String;", "TAG_FIRST", "Ljava/lang/String;", "TAG_NO_FIRST", "PERSONAL_FIRST_ENTER", "TAG", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalEnterUtils {
    public static final PersonalEnterUtils INSTANCE = new PersonalEnterUtils();
    private static final String PERSONAL_FIRST_ENTER = "personal_first_enter";
    private static final String TAG = "PersonalEnterUtils";
    private static final String TAG_FIRST = "1";
    private static final String TAG_NO_FIRST = "2";

    private PersonalEnterUtils() {
    }

    @JvmStatic
    private static final int getLastDay() {
        if (OKLog.D) {
            OKLog.d(TAG, "PersonalEnterUtils: getLastDay " + JDPersonalSharedPreferencesUtil.getSharedPreferences().getInt(PERSONAL_FIRST_ENTER, 0));
        }
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getInt(PERSONAL_FIRST_ENTER, 0);
    }

    @JvmStatic
    @Nullable
    public static final String isFirstEnterPersonal() {
        int i2 = Calendar.getInstance().get(6);
        if (OKLog.D) {
            OKLog.d(TAG, "PersonalEnterUtils: isFirstEnterPersonal curDay=" + i2);
        }
        String str = i2 != getLastDay() ? "1" : "2";
        if (OKLog.D) {
            OKLog.d(TAG, "PersonalEnterUtils: isFirstEnterPersonal status=" + str);
        }
        putCurrentDay(i2);
        return str;
    }

    @JvmStatic
    private static final void putCurrentDay(int todayFirst) {
        if (OKLog.D) {
            OKLog.d(TAG, "PersonalEnterUtils: getLastDay " + todayFirst);
        }
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putInt(PERSONAL_FIRST_ENTER, todayFirst).apply();
    }
}
