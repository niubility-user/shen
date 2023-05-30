package com.jingdong.common.recommend.entity;

import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTendency {
    public ArrayList<String> bgColor;
    public String icon;
    public String isOpenApp;
    public String sourceValue;
    public String tag;
    public String title;
    public String title_id;
    public String txtColor;
    public String url;

    public boolean isStore() {
        return "2".equals(this.tag);
    }
}
