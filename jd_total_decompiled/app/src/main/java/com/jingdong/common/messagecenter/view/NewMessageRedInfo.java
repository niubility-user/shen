package com.jingdong.common.messagecenter.view;

import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes5.dex */
public class NewMessageRedInfo {
    public int pattern;
    public boolean redPoint;
    public int numPattern = 1;
    public int num = 0;

    /* loaded from: classes5.dex */
    public enum ChannalIDEnum {
        ChannalID0(0, "MDChannelUnknown"),
        ChannalID1(1, "MDChannelHomePage"),
        ChannalID2(2, "MDChannelCategory"),
        ChannalID3(3, "MDChannelMiMe"),
        ChannalID4(4, "MDChannelShopCart"),
        ChannalID5(5, "MDChannelMyJD"),
        ChannalID6(6, "MDChannelGoodShop"),
        ChannalID7(7, "MDChannelAllOrders");
        
        public String ChannalPageName;
        public int Channal_id;

        ChannalIDEnum(int i2, String str) {
            this.Channal_id = i2;
            this.ChannalPageName = str;
        }

        public static String getChannalPageName(int i2) {
            try {
                for (ChannalIDEnum channalIDEnum : values()) {
                    if (i2 == channalIDEnum.Channal_id) {
                        return channalIDEnum.ChannalPageName;
                    }
                }
            } catch (Exception unused) {
            }
            return "MDChannelUnknown";
        }
    }

    public static int getPreRedDotParams() {
        if (TextUtils.isEmpty(LoginUserBase.getUserPin())) {
            return 0;
        }
        return SharedPreferencesUtil.getSharedPreferences().getInt("user_pre_rednum_" + Md5Encrypt.md5(LoginUserBase.getUserPin()), 0);
    }

    public static boolean getRedPointStatus() {
        if (TextUtils.isEmpty(LoginUserBase.getUserPin())) {
            return false;
        }
        return SharedPreferencesUtil.getBoolean("user_redpoint_status_" + Md5Encrypt.md5(LoginUserBase.getUserPin()), false);
    }

    public static void putPreRedDotParams(int i2) {
        if (TextUtils.isEmpty(LoginUserBase.getUserPin())) {
            return;
        }
        SharedPreferencesUtil.getSharedPreferences().edit().putInt("user_pre_rednum_" + Md5Encrypt.md5(LoginUserBase.getUserPin()), i2).apply();
    }

    public static void putRedPointStatus(boolean z) {
        if (TextUtils.isEmpty(LoginUserBase.getUserPin())) {
            return;
        }
        SharedPreferencesUtil.putBoolean("user_redpoint_status_" + Md5Encrypt.md5(LoginUserBase.getUserPin()), z);
    }

    public boolean isShow99Number() {
        return this.num > 0;
    }

    public boolean isShow9Number() {
        return false;
    }

    public boolean isShowRedDot() {
        return this.redPoint && this.num <= 0;
    }
}
