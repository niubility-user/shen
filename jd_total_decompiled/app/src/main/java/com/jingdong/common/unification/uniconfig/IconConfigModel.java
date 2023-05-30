package com.jingdong.common.unification.uniconfig;

import android.graphics.Bitmap;
import com.jd.framework.json.anotation.JSONField;

/* loaded from: classes.dex */
public class IconConfigModel {
    @JSONField(serialize = false)
    public Bitmap bitmap;
    public String cachePath;
    public IconExtraConfigModel config;
    public String id = "";
    public String url = "";
    public String path = "";
}
