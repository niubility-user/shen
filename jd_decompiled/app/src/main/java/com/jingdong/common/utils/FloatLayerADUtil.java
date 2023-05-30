package com.jingdong.common.utils;

import com.jingdong.common.utils.personal.JDPersonalSharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0019\u0010\u000fJ\u001b\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\n\u0010\u0006J\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000fR\u0016\u0010\u0015\u001a\u00020\u00028\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00028\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00028\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0016\u00a8\u0006\u001a"}, d2 = {"Lcom/jingdong/common/utils/FloatLayerADUtil;", "", "", FloatLayerADUtil.KEY_CLOSE_JXSU, "", "putCloseJXSu", "(Ljava/lang/String;)V", "getCloseJXSu", "()Ljava/lang/String;", "closeId", "putAdFourTypeCloseSu", "", "getAdFourTypeCloseSuList", "()Ljava/util/List;", "clearAdFourTypeCloseSu", "()V", "putAdFourTypeDay", "", "getAdFourTypeDay", "()I", "clearAdFourTypeDay", "KEY_SHOW_DAY", "Ljava/lang/String;", "KEY_CLOSE_JXSU", "KEY_FOUR_AD_TYPE", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class FloatLayerADUtil {
    public static final FloatLayerADUtil INSTANCE = new FloatLayerADUtil();
    private static final String KEY_CLOSE_JXSU = "closeJXSu";
    private static final String KEY_FOUR_AD_TYPE = "closeAdFourType";
    private static final String KEY_SHOW_DAY = "fourTypeShowDay";

    private FloatLayerADUtil() {
    }

    private final void clearAdFourTypeCloseSu() {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putString(KEY_FOUR_AD_TYPE, "").apply();
    }

    private final void clearAdFourTypeDay() {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putInt(KEY_SHOW_DAY, -1).apply();
    }

    @JvmStatic
    @NotNull
    public static final List<String> getAdFourTypeCloseSuList() {
        ArrayList arrayList = new ArrayList();
        FloatLayerADUtil floatLayerADUtil = INSTANCE;
        if (floatLayerADUtil.getAdFourTypeDay() != Calendar.getInstance().get(6)) {
            floatLayerADUtil.clearAdFourTypeDay();
            floatLayerADUtil.clearAdFourTypeCloseSu();
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(JDPersonalSharedPreferencesUtil.getSharedPreferences().getString(KEY_FOUR_AD_TYPE, ""));
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                String string = jSONArray.getString(i2);
                Intrinsics.checkExpressionValueIsNotNull(string, "jsonArray.getString(i)");
                arrayList.add(string);
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    private final int getAdFourTypeDay() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getInt(KEY_SHOW_DAY, -1);
    }

    @JvmStatic
    @NotNull
    public static final String getCloseJXSu() {
        String string = JDPersonalSharedPreferencesUtil.getSharedPreferences().getString(KEY_CLOSE_JXSU, "0");
        return string != null ? string : "0";
    }

    @JvmStatic
    public static final void putAdFourTypeCloseSu(@Nullable String closeId) {
        if (closeId == null || closeId.length() == 0) {
            return;
        }
        List<String> adFourTypeCloseSuList = getAdFourTypeCloseSuList();
        INSTANCE.putAdFourTypeDay();
        try {
            JSONArray jSONArray = new JSONArray((Collection) adFourTypeCloseSuList);
            jSONArray.put(closeId);
            JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putString(KEY_FOUR_AD_TYPE, jSONArray.toString()).apply();
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void putAdFourTypeCloseSu$default(String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        putAdFourTypeCloseSu(str);
    }

    private final void putAdFourTypeDay() {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putInt(KEY_SHOW_DAY, Calendar.getInstance().get(6)).apply();
    }

    @JvmStatic
    public static final void putCloseJXSu(@Nullable String closeJXSu) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putString(KEY_CLOSE_JXSU, closeJXSu).apply();
    }

    public static /* synthetic */ void putCloseJXSu$default(String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "0";
        }
        putCloseJXSu(str);
    }
}
