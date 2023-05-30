package com.jingdong.app.mall.utils;

import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes4.dex */
public class h {
    public static int a() {
        if (c()) {
            return SharedPreferencesUtil.getInt("show_coupon_excitation_dialog_time", 0);
        }
        SharedPreferencesUtil.putInt("show_coupon_excitation_dialog_time", 0);
        return 0;
    }

    public static int b() {
        if (d()) {
            return SharedPreferencesUtil.getInt("show_excitation_dialog_time", 0);
        }
        SharedPreferencesUtil.putInt("show_excitation_dialog_time", 0);
        return 0;
    }

    public static boolean c() {
        return SharedPreferencesUtil.getString("show_coupon_excitation_dialog_date", JDDateTimePickerDialog.SELECT_DATE_MODE).equals(t.a());
    }

    public static boolean d() {
        return SharedPreferencesUtil.getString("show_excitation_dialog_date", JDDateTimePickerDialog.SELECT_DATE_MODE).equals(t.a());
    }
}
