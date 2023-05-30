package com.jingdong.common.widget.shadow.utils;

import android.content.res.ColorStateList;
import com.jd.aips.common.utils.SystemBarTintManager;
import com.tencent.wcdb.database.SQLiteDatabase;

/* loaded from: classes12.dex */
public class ColorUtils {
    private static final int CHECKED_ATTR = 16842912;
    private static final int ENABLE_ATTR = 16842910;
    private static final int PRESSED_ATTR = 16842919;

    public static ColorStateList generateBackColorWithTintColor(int i2) {
        int i3 = i2 - (-805306368);
        return new ColorStateList(new int[][]{new int[]{-16842910, CHECKED_ATTR}, new int[]{-16842910}, new int[]{CHECKED_ATTR, PRESSED_ATTR}, new int[]{-16842912, PRESSED_ATTR}, new int[]{CHECKED_ATTR}, new int[]{-16842912}}, new int[]{i2 - (-520093696), 268435456, i3, SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, i3, SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING});
    }

    public static ColorStateList generateThumbColorWithTintColor(int i2) {
        int[][] iArr = {new int[]{-16842910, CHECKED_ATTR}, new int[]{-16842910}, new int[]{PRESSED_ATTR, -16842912}, new int[]{PRESSED_ATTR, CHECKED_ATTR}, new int[]{CHECKED_ATTR}, new int[]{-16842912}};
        int i3 = i2 - SystemBarTintManager.DEFAULT_TINT_COLOR;
        return new ColorStateList(iArr, new int[]{i2 - (-1442840576), -4539718, i3, i3, i2 | (-16777216), -1118482});
    }
}
