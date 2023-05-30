package com.jingdong.common.entity.navigationbar;

import com.jd.framework.json.anotation.JSONField;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;

/* loaded from: classes.dex */
public class NavigationBar {
    public static final String TAG = "NavigationBar";
    public int bigIconTag;
    public String categoryId;
    public String dataVersion;
    public int defaultTag;
    public int displayTag;
    public String endTime;
    public String functionId;
    public int iconType;
    public int id;
    public String mUrl;
    public String naviLabel;
    @JSONField(name = "sort")
    public Integer naviOrder;
    public int naviTask;
    public String offPath;
    @JSONField(name = CustomThemeConstance.NAV_UNSELECT)
    public String offUrl;
    public String onPath;
    @JSONField(name = CustomThemeConstance.NAV_SELECT_PATH)
    public String onUrl;
    public String startTime;
}
