package com.jingdong.app.mall.home.floor.view.view;

import android.text.TextUtils;

/* loaded from: classes4.dex */
public class SearchWordEntity {
    public int carouseTimes;
    public boolean isShowed;
    public boolean isXDef;
    public String pvInfo;
    public String realWord;
    public String showWord;
    public String sourceValue;

    public boolean isValid() {
        return this.isXDef || !(TextUtils.isEmpty(this.realWord) || TextUtils.isEmpty(this.showWord));
    }
}
