package com.jingdong.common.unification.navigationbar.theme;

import android.text.TextUtils;
import com.jingdong.common.utils.FileUtils;

/* loaded from: classes6.dex */
public class NavThemeEntity {
    public boolean isBig;
    public int navigationId;
    public String offPath;
    public String onPath;

    public boolean usAble() {
        return !TextUtils.isEmpty(this.offPath) && !TextUtils.isEmpty(this.onPath) && FileUtils.fileIsExists(this.offPath) && FileUtils.fileIsExists(this.onPath);
    }
}
