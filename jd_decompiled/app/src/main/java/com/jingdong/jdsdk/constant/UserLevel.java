package com.jingdong.jdsdk.constant;

import android.text.TextUtils;
import com.jingdong.common.R;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum Register uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes14.dex */
public final class UserLevel {
    private static final /* synthetic */ UserLevel[] $VALUES;
    public static final UserLevel Bronze;
    public static final UserLevel Coporate;
    public static final UserLevel Diamond;
    public static final UserLevel Gold;
    public static final UserLevel Others;
    public static final UserLevel Register;
    public static final UserLevel Silver;
    public static final UserLevel VIP;
    private int id;
    private String name;
    private int tumbleResId;

    static {
        UserLevel userLevel = new UserLevel("Diamond", 0, 0, "\u94bb\u77f3\u4f1a\u5458", R.drawable.user_level_icon_4);
        Diamond = userLevel;
        UserLevel userLevel2 = new UserLevel("Gold", 1, 1, "\u91d1\u724c\u4f1a\u5458", R.drawable.user_level_icon_3);
        Gold = userLevel2;
        UserLevel userLevel3 = new UserLevel("Silver", 2, 2, "\u94f6\u724c\u4f1a\u5458", R.drawable.user_level_icon_2);
        Silver = userLevel3;
        UserLevel userLevel4 = new UserLevel("Bronze", 3, 3, "\u94dc\u724c\u4f1a\u5458", R.drawable.user_level_icon_1);
        Bronze = userLevel4;
        int i2 = R.drawable.user_level_icon_0;
        UserLevel userLevel5 = new UserLevel("Register", 4, 4, "\u6ce8\u518c\u4f1a\u5458", i2);
        Register = userLevel5;
        UserLevel userLevel6 = new UserLevel("Coporate", 5, 5, "\u4f01\u4e1a\u4f1a\u5458", i2);
        Coporate = userLevel6;
        UserLevel userLevel7 = new UserLevel("VIP", 6, 6, "VIP\u4f1a\u5458", R.drawable.user_level_icon_vip);
        VIP = userLevel7;
        UserLevel userLevel8 = new UserLevel("Others", 7, -1, "\u5176\u4ed6", i2);
        Others = userLevel8;
        $VALUES = new UserLevel[]{userLevel, userLevel2, userLevel3, userLevel4, userLevel5, userLevel6, userLevel7, userLevel8};
    }

    private UserLevel(String str, int i2, int i3, String str2, int i4) {
        this.id = i3;
        this.name = str2;
        this.tumbleResId = i4;
    }

    private static UserLevel getTypeById(int i2) {
        for (UserLevel userLevel : values()) {
            if (i2 == userLevel.getId()) {
                return userLevel;
            }
        }
        return Others;
    }

    public static UserLevel valueOf(String str) {
        return (UserLevel) Enum.valueOf(UserLevel.class, str);
    }

    public static UserLevel[] values() {
        return (UserLevel[]) $VALUES.clone();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getTumbleResId() {
        return this.tumbleResId;
    }

    public static UserLevel getTypeById(String str) {
        if (TextUtils.isEmpty(str)) {
            return Others;
        }
        int i2 = -1;
        try {
            i2 = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
        return getTypeById(i2);
    }
}
